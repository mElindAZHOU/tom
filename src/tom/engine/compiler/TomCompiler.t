/**
 *
 * The TomCompiler plugin.
 *
 */

package jtom.compiler;

import java.util.*;

import jtom.adt.tomsignature.types.*;
import jtom.adt.options.types.*;

import jtom.runtime.Replace1;
import jtom.tools.*;
import jtom.TomMessage;
import aterm.*;
import jtom.exception.TomRuntimeException;
import jtom.*;


public class TomCompiler extends TomBase implements TomPlugin
{
    %include { ../adt/TomSignature.tom }
    %include{ ../adt/Options.tom }

    private TomTerm term;
    private TomOptionList myOptions;

    private TomKernelCompiler tomKernelCompiler = new TomKernelCompiler();
    private TomFactory tomFactory = new TomFactory();
    private int absVarNumber = 0;

    public static final String COMPILED_SUFFIX = ".tfix.compiled"; // was previously in TomTaskInput

    public TomCompiler()
    {
	myOptions = `concTomOption(OptionBoolean("compile","","",True()) // activationFlag
				);
    }

    public void setInput(ATerm term)
    {
	if (term instanceof TomTerm)
	    this.term = (TomTerm)term;
	else
	    environment().messageError(TomMessage.getString("TomTermExpected"),
				       "TomCompiler", TomMessage.DEFAULT_ERROR_LINE_NUMBER);
    }

    public ATerm getOutput()
    {
	return term;
    }

    public void run()
    {
	try
	    {
		long startChrono = System.currentTimeMillis();
		boolean verbose = ((Boolean)getServer().getOptionValue("verbose")).booleanValue();
		boolean intermediate = ((Boolean)getServer().getOptionValue("intermediate")).booleanValue();

		TomTerm preCompiledTerm = preProcessing(term);
		//System.out.println("preCompiledTerm = \n" + preCompiledTerm);
		TomTerm compiledTerm = tomKernelCompiler.compileMatching(preCompiledTerm);
      
		if(verbose)
		    System.out.println("TOM compilation phase (" + (System.currentTimeMillis()-startChrono)+ " ms)");

		if(intermediate)
		    Tools.generateOutput(environment().getOutputFileNameWithoutSuffix() + COMPILED_SUFFIX, compiledTerm);

		term = compiledTerm;

		environment().printAlertMessage("TomCompiler");
		if(!environment().isEclipseMode()) {
		    // remove all warning (in command line only)
		    environment().clearWarnings();
		}
	    }
	catch (Exception e)
	    {
		environment().messageError("Exception occurs in TomCompiler: "+e.getMessage(), 
					   environment().getInputFile().getName(), 
					   TomMessage.DEFAULT_ERROR_LINE_NUMBER);
		e.printStackTrace();
	    }
    }
  

  private OptionList option() {
    return ast().makeOption();
  }

    /* 
     * preProcessing:
     * replaces BuildReducedTerm by BuildList, BuildArray or BuildTerm
     *
     * transforms RuleSet into Function + Match + BuildReducedTerm
     * abstract list-matching patterns
     * rename non-linear patterns
     */

  Replace1 replace_preProcessing = new Replace1() {
      public ATerm apply(ATerm subject) {
        String debugKey = "";
        if(subject instanceof TomTerm) {
          %match(TomTerm subject) {
            BuildReducedTerm(var@(Variable|VariableStar)[]) -> {
              return `var;
            }    

            BuildReducedTerm(Appl[nameList=(name@Name(tomName)),args=termArgs]) -> {
              TomSymbol tomSymbol = symbolTable().getSymbol(`tomName);
              TomList newTermArgs = (TomList) traversal().genericTraversal(`termArgs,replace_preProcessing_makeTerm);
              if(tomSymbol==null || isDefinedSymbol(tomSymbol)) {
                return `FunctionCall(name,newTermArgs);
              } else {
                if(isListOperator(tomSymbol)) {
                  return tomFactory.buildList(`name,newTermArgs);
                } else if(isArrayOperator(tomSymbol)) {
                  return tomFactory.buildArray(`name,newTermArgs);
                } else {
                  return `BuildTerm(name,newTermArgs);
                }
              }
            }

          } // end match
        } else if(subject instanceof Instruction) {
          %match(Instruction subject) {
            Match(SubjectList(l1),PatternList(l2), matchOptionList)  -> {
              TomList patternList = `l2;
              Option orgTrack = findOriginTracking(`matchOptionList);
              if(((Boolean)getServer().getOptionValue("debug")).booleanValue()) {
                debugKey = orgTrack.getFileName().getString() + orgTrack.getLine();
              }
              TomList newPatternList = empty();
              while(!patternList.isEmpty()) {
                /*
                 * the call to preProcessing performs the recursive expansion
                 * of nested match constructs
                 */
                TomTerm elt = preProcessing(patternList.getHead());
                TomTerm newPatternAction = elt;
              
                matchBlock: {
                  %match(TomTerm elt) {
                    PatternAction(TermList(termList),actionInst, option) -> {
                      TomList newTermList = empty();
                      Instruction newActionInst = `actionInst;
                      /* generate equality checks */
                      ArrayList equalityCheck = new ArrayList();
                      TomList renamedTermList = linearizePattern(`termList,equalityCheck);
                      newPatternAction = `PatternAction(TermList(renamedTermList),actionInst, option);        
                    
                      /* abstract patterns */
                      ArrayList abstractedPattern  = new ArrayList();
                      ArrayList introducedVariable = new ArrayList();
                      newTermList = abstractPatternList(renamedTermList, abstractedPattern, introducedVariable);

                      if(abstractedPattern.size() > 0) {
                        /* generate a new match construct */
                      
                        TomTerm generatedPatternAction =
                          `PatternAction(TermList(ast().makeList(abstractedPattern)),newActionInst, concOption());        
                        /* We reconstruct only a list of option with orgTrack and GeneratedMatch*/
                        OptionList generatedMatchOptionList = `concOption(orgTrack,GeneratedMatch());
                        Instruction generatedMatch =
                          `Match(SubjectList(ast().makeList(introducedVariable)),
                                 PatternList(cons(generatedPatternAction,empty())),
                                 generatedMatchOptionList);
                        /*System.out.println("Generate new Match"+generatedMatch); */
                        generatedMatch = preProcessingInstruction(generatedMatch);
                        newPatternAction =
                          `PatternAction(TermList(newTermList),generatedMatch, option);
                      
                        /*System.out.println("newPatternAction = " + newPatternAction); */
                      }
                      /* do nothing */
                      break matchBlock;
                    }
                  
                    _ -> {
                      System.out.println("preProcessing: strange PatternAction: " + elt);
                      //System.out.println("termList = " + elt.getTermList());
                      //System.out.println("tom      = " + elt.getTom()); 
                      throw new TomRuntimeException("preProcessing: strange PatternAction: " + elt);
                    }
                  }
                } // end matchBlock
              
                newPatternList = append(newPatternAction,newPatternList);
                patternList = patternList.getTail();
              }
            
              Instruction newMatch = `Match(SubjectList(l1),
                                            PatternList(newPatternList),
                                            matchOptionList);
              return newMatch;
            }

            RuleSet(rl@manyTomRuleList(RewriteRule[lhs=Term(Appl[nameList=(Name(tomName))])],_), orgTrack) -> {
              TomRuleList ruleList = `rl;
              if(((Boolean)getServer().getOptionValue("debug")).booleanValue()) {
                debugKey = `orgTrack.getFileName().getString() + `orgTrack.getLine();
              }
              TomSymbol tomSymbol = symbolTable().getSymbol(`tomName);
              TomName name = tomSymbol.getAstName();
              TomTypeList typesList = tomSymbol.getTypesToType().getDomain();        
              TomNumberList path = tsf().makeTomNumberList();
              TomList matchArgumentsList = empty();
              TomList patternActionList  = empty();
              TomTerm variable;
              int index = 0;
            
              path = (TomNumberList) path.append(`RuleVar());
            
              while(!typesList.isEmpty()) {
                TomType subtermType = typesList.getHead();
                variable = `Variable(option(),PositionName(appendNumber(index,path)),subtermType,concConstraint());
                matchArgumentsList = append(variable,matchArgumentsList);
                typesList = typesList.getTail();
                index++;
              }
            
              //boolean hasDefaultCase = false;
              while(!ruleList.isEmpty()) {
                TomRule rule = ruleList.getHead();
                %match(TomRule rule) {
                  RewriteRule(Term(Appl[args=matchPatternsList]),
                              Term(rhsTerm),
                              condList,
                              option) -> {
                    TomTerm newRhs = preProcessing(`BuildReducedTerm(rhsTerm));
                    Instruction rhsInst = `IfThenElse(TrueTL(),Return(newRhs),Nop());
                    if(((Boolean)getServer().getOptionValue("debug")).booleanValue()) {
                      TargetLanguage tl = tsf().makeTargetLanguage_ITL("jtom.debug.TomDebugger.debugger.patternSuccess(\""+debugKey+"\");\n");
                      rhsInst = `UnamedBlock(concInstruction(TargetLanguageToInstruction(tl), rhsInst));
                    }
                    Instruction newRhsInst = `buildCondition(condList,rhsInst);
                  
                    patternActionList = append(`PatternAction(TermList(matchPatternsList),newRhsInst, option),patternActionList);
                    //hasDefaultCase = hasDefaultCase || (isDefaultCase(matchPatternsList) && condList.isEmpty());
                  }
                } 
                ruleList = ruleList.getTail();
              }
            
              TomTerm subjectListAST = `SubjectList(matchArgumentsList);
              Instruction makeFunctionBeginAST = `MakeFunctionBegin(name,subjectListAST);
              ArrayList optionList = new ArrayList();
              optionList.add(`orgTrack);
              //optionList.add(tsf().makeOption_GeneratedMatch());
              OptionList generatedOptions = ast().makeOptionList(optionList);
              Instruction matchAST = `Match(SubjectList(matchArgumentsList),
                                            PatternList(patternActionList),
                                            generatedOptions);

              Instruction buildAST;
              //if(hasDefaultCase) {
              //buildAST = `Nop();
              //} else {
                buildAST = `Return(BuildTerm(name,(TomList) traversal().genericTraversal(matchArgumentsList,replace_preProcessing_makeTerm)));
                //}

              InstructionList l;
              if(((Boolean)getServer().getOptionValue("eCode")).booleanValue()) {
                l = `concInstruction(
                                     makeFunctionBeginAST,
                                     LocalVariable(),
                                     EndLocalVariable(),
                                     matchAST,
                                     buildAST,
                                     MakeFunctionEnd()
                                     );
              } else {
                l = `concInstruction(
                                     makeFunctionBeginAST,
                                     matchAST,
                                     buildAST,
                                     MakeFunctionEnd()
                                     );
              }
            
              return preProcessingInstruction(`AbstractBlock(l));
            }

          } // end match

        } // end instanceof Instruction

          /*
           * Defaul case: traversal
           */
        return traversal().genericTraversal(subject,this);
      } // end apply
    };

  /*  
  private boolean isDefaultCase(TomList l) {
    %match(TomList l) {
      emptyTomList() -> {
        return true;
      }
      manyTomList((UnamedVariable|UnamedVariableStar)[],tail) -> {
        return isDefaultCase(tail);
      }
      manyTomList((Variable|VariableStar)[],tail) -> {
        return isDefaultCase(tail);
      }
    }
    return false;
  }
  */

  Replace1 replace_preProcessing_makeTerm = new Replace1() {
      public ATerm apply(ATerm t) {
        return preProcessing(`BuildReducedTerm((TomTerm)t));
      }
    }; 

  private TomTerm preProcessing(TomTerm subject) {
      //System.out.println("preProcessing subject: " + subject);
    return (TomTerm) replace_preProcessing.apply(subject); 
  }
  
  private Instruction preProcessingInstruction(Instruction subject) {
      //System.out.println("preProcessing subject: " + subject);
    return (Instruction) replace_preProcessing.apply(subject); 
  }

 
  private Instruction buildCondition(InstructionList condList, Instruction action) {
    %match(InstructionList condList) {
      emptyInstructionList() -> { return action; }
       
      manyInstructionList(MatchingCondition[lhs=pattern,rhs=subject], tail) -> {
        Instruction newAction = `buildCondition(tail,action);

        TomType subjectType = getTermType(`pattern);
        TomNumberList path = tsf().makeTomNumberList();
        path = (TomNumberList) path.append(`RuleVar());
        TomTerm newSubject = preProcessing(`BuildReducedTerm(subject));
        TomTerm introducedVariable = newSubject;
        TomTerm generatedPatternAction =
          `PatternAction(TermList(cons(pattern,empty())),newAction, option());        

          // Warning: The options are not good
        Instruction generatedMatch =
          `Match(SubjectList(cons(introducedVariable,empty())),
                 PatternList(cons(generatedPatternAction,empty())),
                 option());
        return generatedMatch;
      }

      manyInstructionList(TypedEqualityCondition[tomType=type,lhs=lhs,rhs=rhs], tail) -> {
        Instruction newAction = `buildCondition(tail,action);

        TomTerm newLhs = preProcessing(`BuildReducedTerm(lhs));
        TomTerm newRhs = preProcessing(`BuildReducedTerm(rhs));
        Expression equality = `EqualTerm(type,newLhs,newRhs);
        Instruction generatedTest = `IfThenElse(equality,newAction,Nop());
        return generatedTest;
      }
      
      _ -> {
        throw new TomRuntimeException("buildCondition strange term: " + condList);
      }
    }
  }
  
  private TomTerm renameVariable(TomTerm subject,
                                 Map multiplicityMap,
                                 ArrayList equalityCheck) {
    TomTerm renamedTerm = subject;
    
    %match(TomTerm subject) {
      var@(UnamedVariable|UnamedVariableStar)[constraints=constraints] -> {
        ConstraintList newConstraintList = `renameVariableInConstraintList(constraints,multiplicityMap,equalityCheck);
        return `var.setConstraints(newConstraintList);
      }

      var@(Variable|VariableStar)[astName=name,constraints=clist] -> {
        ConstraintList newConstraintList = renameVariableInConstraintList(`clist,multiplicityMap,equalityCheck);
        if(!multiplicityMap.containsKey(`name)) {
          // We see this variable for the first time
          multiplicityMap.put(`name,new Integer(1));
          renamedTerm = `var.setConstraints(newConstraintList);
        } else {
          // We have already seen this variable
          Integer multiplicity = (Integer) multiplicityMap.get(`name);
          int mult = multiplicity.intValue(); 
          multiplicityMap.put(`name,new Integer(mult+1));
          
          TomNumberList path = tsf().makeTomNumberList();
          path = (TomNumberList) path.append(`RenamedVar(name));
          path = (TomNumberList) path.append(makeNumber(mult));

          renamedTerm = `var.setAstName(`PositionName(path));
          renamedTerm = renamedTerm.setConstraints(`concConstraint(Equal(var.setConstraints(concConstraint())),newConstraintList*));
        }

        return renamedTerm;
      }

      Appl[option=optionList, nameList=nameList, args=arguments, constraints=constraints] -> {
        TomList args = `arguments;
        TomList newArgs = empty();
        while(!args.isEmpty()) {
          TomTerm elt = args.getHead();
          TomTerm newElt = renameVariable(elt,multiplicityMap,equalityCheck);
          newArgs = append(newElt,newArgs);
          args = args.getTail();
        }
        ConstraintList newConstraintList = renameVariableInConstraintList(`constraints,multiplicityMap,equalityCheck);
        renamedTerm = `Appl(optionList,nameList,newArgs,newConstraintList);
        return renamedTerm;
      }
    }
    return renamedTerm;
  }

  private ConstraintList renameVariableInConstraintList(ConstraintList constraintList,
                                                Map multiplicityMap,
                                                ArrayList equalityCheck) {
    ArrayList list = new ArrayList();
    while(!constraintList.isEmpty()) {
      Constraint cstElt = constraintList.getHead();
      Constraint newCstElt = cstElt;
      %match(Constraint cstElt) {
        AssignTo(var@Variable[]) -> {
          newCstElt = `AssignTo(renameVariable(var,multiplicityMap,equalityCheck));
        }
      }
      list.add(newCstElt);
      constraintList = constraintList.getTail();
    }
    return ast().makeConstraintList(list);
  }

  private TomList linearizePattern(TomList subject, ArrayList equalityCheck) {
    Map multiplicityMap = new HashMap();
      // perform the renaming and generate equality checks
    TomList newList = empty();
    while(!subject.isEmpty()) {
      TomTerm elt = subject.getHead();
      TomTerm newElt = renameVariable(elt,multiplicityMap,equalityCheck);
      newList = append(newElt,newList);
      subject = subject.getTail();
    }
    return newList;
  }
  
  private TomTerm abstractPattern(TomTerm subject,
                                  ArrayList abstractedPattern,
                                  ArrayList introducedVariable)  {
    TomTerm abstractedTerm = subject;
    %match(TomTerm subject) {
      Appl[nameList=(Name(tomName),_*), args=arguments] -> {
        TomList args = `arguments;
        TomSymbol tomSymbol = symbolTable().getSymbol(`tomName);
        
        TomList newArgs = empty();
        if(isListOperator(tomSymbol) || isArrayOperator(tomSymbol)) {
          while(!args.isEmpty()) {
            TomTerm elt = args.getHead();
            TomTerm newElt = elt;
            %match(TomTerm elt) {
              appl@Appl[nameList=(Name(tomName2),_*)] -> {
                /*
                 * we no longer abstract syntactic subterm
                 * they are compiled by the TomKernelCompiler
                 */

                  //System.out.println("Abstract: " + appl);
                TomSymbol tomSymbol2 = symbolTable().getSymbol(`tomName2);
                if(isListOperator(tomSymbol2) || isArrayOperator(tomSymbol2)) {
                  TomType type2 = tomSymbol2.getTypesToType().getCodomain();
                  abstractedPattern.add(`appl);
                  
                  TomNumberList path = tsf().makeTomNumberList();
                  //path = append(`AbsVar(makeNumber(introducedVariable.size())),path);
                  absVarNumber++;
                  path = (TomNumberList) path.append(`AbsVar(makeNumber(absVarNumber)));
                  
                  TomTerm newVariable = `Variable(option(),PositionName(path),type2,concConstraint());
                  
                  //System.out.println("newVariable = " + newVariable);
                  
                  introducedVariable.add(newVariable);
                  newElt = newVariable;
                }
              }
            }
            newArgs = append(newElt,newArgs);
            args = args.getTail();
          }
        } else {
          newArgs = abstractPatternList(args,abstractedPattern,introducedVariable);
        }
        abstractedTerm = subject.setArgs(newArgs);
      }
    } // end match
    return abstractedTerm;
  }

  private TomList abstractPatternList(TomList subjectList,
                                      ArrayList abstractedPattern,
                                      ArrayList introducedVariable)  {
    TomList newList = empty();
    while(!subjectList.isEmpty()) {
      TomTerm elt = subjectList.getHead();
      TomTerm newElt = abstractPattern(elt,abstractedPattern,introducedVariable);
      newList = append(newElt,newList);
      subjectList = subjectList.getTail();
    }
    return newList;
  }

    public TomOptionList declareOptions()
    {
// 	int i = 0;
// 	OptionList list = `concOption(myOptions*);
// 	while(!(list.isEmpty()))
// 	    {
// 		i++;
// 		list = list.getTail();
// 	    }

// 	System.out.println("1.6. The compiler declares " +i+ " options.");
	
	return myOptions;
    }

    public TomOptionList requiredOptions()
    {
	return `emptyTomOptionList();
    }

    public void setOption(String optionName, String optionValue)
    {
 	%match(TomOptionList myOptions)
 	    {
		concTomOption(av*, OptionBoolean(n, alt, desc, val), ap*)
		    -> { if(n.equals(optionName)||alt.equals(optionName))
			{
			    %match(String optionValue)
				{
				    ('true') ->
					{ myOptions = `concTomOption(av*, ap*, OptionBoolean(n, alt, desc, True())); }
				    ('false') ->
					{ myOptions = `concTomOption(av*, ap*, OptionBoolean(n, alt, desc, False())); }
				}
			}
		}
		concTomOption(av*, OptionInteger(n, alt, desc, val, attr), ap*)
		    -> { if(n.equals(optionName)||alt.equals(optionName))
			myOptions = `concTomOption(av*, ap*, OptionInteger(n, alt, desc, Integer.parseInt(optionValue), attr));
		}
		concTomOption(av*, OptionString(n, alt, desc, val, attr), ap*)
		    -> { if(n.equals(optionName)||alt.equals(optionName))
			myOptions = `concTomOption(av*, ap*, OptionString(n, alt, desc, optionValue, attr));
		}
	    }
    }

}
