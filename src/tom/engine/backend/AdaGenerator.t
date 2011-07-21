/*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2011, INPL, INRIA
 * Nancy, France.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 * 
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 
 **/

package tom.engine.backend;

import java.io.IOException;
import java.util.ArrayList;

import tom.engine.TomBase;
import tom.engine.exception.TomRuntimeException;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;

import tom.engine.tools.OutputCode;
import tom.engine.tools.SymbolTable;
import tom.engine.tools.ASTFactory;
import tom.platform.OptionManager;

import java.util.Vector;


public class AdaGenerator extends GenericGenerator {

  // ------------------------------------------------------------
  %include { ../adt/tomsignature/TomSignature.tom }
  // ------------------------------------------------------------

  /* modifier associated to classes generated by %strategy */
  protected String stratmodifier = "";
  
  /* to store the link between a Type name and the code of its is_sort function. */
  private Vector<String> isSortType = new Vector<String>(1);
  private Vector<Expression> isSortCode = new Vector<Expression>(1);

  public AdaGenerator(OutputCode output, OptionManager optionManager,
                                SymbolTable symbolTable) {
    super(output, optionManager, symbolTable);
  }

  protected void buildExpIsSort(int deep, String type, BQTerm exp, String moduleName) throws IOException {
    if(getSymbolTable(moduleName).isBuiltinType(type)) {
      generateExpression(deep,`TrueTL(),moduleName);
      return;
    }

    String template = getSymbolTable(moduleName).getIsSort(type);
    String opname="";
    if(instantiateTemplate(deep,template,opname,`concBQTerm(exp),moduleName) == false) {
        // We retreive the code saved when the function tom_is_sort was declared
		Expression code = getIsSortExpressionFromType(type);

		if (code == null) { // If no such function have been declared
			// We use the original way of doing things
			buildExpIsSortOriginal(deep, type, exp, moduleName);
			return;
		}
		
		if(code.isCode()) { //if the code of the function use the parameters
		  // perform the instantiation
		  String ocode = code.getCode();
		  String codeTab[] = ocode.split("\\{0\\}");
		  for(int i = 0 ; i < codeTab.length - 1 ; i++) {
			code = code.setCode( codeTab[i] );
			generateBQTerm(0,`ExpressionToBQTerm(code),moduleName);
			generateBQTerm(0,exp,moduleName);
		  }
		 code = code.setCode( codeTab[codeTab.length - 1] );
		  generateBQTerm(0,`ExpressionToBQTerm(code),moduleName); 
			
		} else {
			generateBQTerm(0,`ExpressionToBQTerm(code),moduleName);
		}
    }
  }
  
  protected void buildExpIsSortOriginal(int deep, String type, BQTerm exp, String moduleName) throws IOException {
    if(getSymbolTable(moduleName).isBuiltinType(type)) {
      generateExpression(deep,`TrueTL(),moduleName);
      return;
    }

    String template = getSymbolTable(moduleName).getIsSort(type);
    String opname="";
    if(instantiateTemplate(deep,template,opname,`concBQTerm(exp),moduleName) == false) {
      output.write("tom_is_sort_" + type + "(");
      generateBQTerm(0,exp,moduleName);
      output.write(")");
    }
  }
  
  protected void buildIsSortDeclOriginal(int deep, String varName, String type, Expression code, String moduleName) throws IOException {
    boolean inlined = inlineplus;
    if(code.isCode()) {
      // perform the instantiation
      String ocode = code.getCode();
      String ncode = ocode.replace("{0}",varName);
      if(!ncode.equals(ocode)) {
        inlined = true;
        code = code.setCode(ncode);
      }
    }
    if(!inline || !code.isCode() || !inlined) {
      TomType argType = getSymbolTable(moduleName).getType(type);
      if(getSymbolTable(moduleName).isBuiltinType(type)) {
        argType = getSymbolTable(moduleName).getBuiltinType(type);
      }
      genDeclInstr(TomBase.getTLType(getSymbolTable(moduleName).getBooleanType()), "tom_is_sort", type,
          new String[] { TomBase.getTLType(argType), varName },
          `Return(ExpressionToBQTerm(code)),deep,moduleName);
    }
  }
  
  protected void buildIsSortDecl(int deep, String varName, String type, Expression code, String moduleName) throws IOException {
	/*
	 * Unlike other languages we cannot create easily tom_is_sort functions.
	 * So instead, we replace the function call by its return expression.
	 * To do so we need to be able to associate a Term and the expression that 
	 * identificate it. Hence two vector, the first contains the type, the second
	 * contains the expression. The index number do the link between both informations.
	 */  
	 
	 //save the code of each tom_is_sort function in memory
    isSortType.add(type);
    isSortCode.add(code);
    //declare the tom_is_sort function in case something goes wrong, may be suppressed in the future
    buildIsSortDeclOriginal(deep, varName, type, code, moduleName);
  }
  
  //Retreive the code of tom_is_sort function with the type name
  private Expression getIsSortExpressionFromType(String type) {
    for(int i = 0 ; i < isSortType.size() ; i++) {
		if ( type.equals( isSortType.get(i) ) ) {
			return isSortCode.get(i);
		}
	}  
	return null;
  }

  
  protected void buildTerm(int deep, String opname, BQTermList argList, String moduleName) throws IOException {
    String template = getSymbolTable(moduleName).getMake(opname);
    if(instantiateTemplate(deep,template,opname,argList,moduleName) == false) {
		boolean parenthesis = !argList.isEmptyconcBQTerm();
        String prefix = "tom_make_";
        output.write(deep, prefix+opname);
        if (parenthesis) { output.writeOpenBrace(); }
        int index=0;
        while(!argList.isEmptyconcBQTerm()) {
          BQTerm bqt = argList.getHeadconcBQTerm();
          //System.out.println("bqt = " + bqt);
          boolean generatebqt = true;
          %match(bqt) {
            Composite(CompositeBQTerm(BQDefault()),_*) -> {
              TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(opname);
              //System.out.println("name = " + opname);
              //System.out.println("symbol = " + tomSymbol);
              TomName slotName = TomBase.getSlotName(tomSymbol,index);
              //System.out.println("slotname = " + slotName);
              buildExpGetDefault(0, opname, slotName.getString(), moduleName);
              generatebqt = false;
            }
          }

          if(generatebqt) {
            generateBQTerm(0,bqt,moduleName);
          }

          argList = argList.getTailconcBQTerm();
          if(!argList.isEmptyconcBQTerm()) {
            output.writeComa();
          }
          index++;
        }
        if (parenthesis) { output.writeCloseBrace(); }
      
    }
  }
  
  protected void buildExpGetDefault(int deep, String opname, String slotName, String moduleName) throws IOException {
    output.write("tom_get_default_");
    output.write(opname);
    output.writeUnderscore();
    output.write(slotName);
  }
  
  protected void buildExpIsFsym(int deep, String opname, BQTerm exp, String moduleName) throws IOException {
    String template = getSymbolTable(moduleName).getIsFsym(opname);
    if(instantiateTemplate(deep,template,opname,`concBQTerm(exp),moduleName) == false) {
      String s = isFsymMap.get(opname);
      if(s == null) {
        s = "tom_is_fun_sym_" + opname + "(";
        isFsymMap.put(opname,s);
      }
      output.write(s);
      generateBQTerm(0,exp,moduleName);
      output.write(")");
    }
  }

  protected void buildAssign(int deep, BQTerm var, OptionList optionList, Expression exp, String moduleName) throws IOException {
    generateBQTerm(deep,var,moduleName);
    output.write(":=");
    generateExpression(0,exp,moduleName);
    output.writeln("; ");
  } 

  protected void buildComment(int deep, String text) throws IOException {
    output.write(deep, "--" + text.replace("\n","\n--") + "\n");
  }
 
  protected void buildDoWhile(int deep, Instruction succes, Expression exp, String moduleName) throws IOException {
    output.writeln(deep,"loop ");
    generateInstruction(deep,succes,moduleName);
    output.write(deep+1,"exit when not(");
    generateExpression(0,exp,moduleName);
    output.writeln("); ");
    output.writeln(deep, "end loop; ");
  }

  protected void buildExpEqualTerm(int deep, TomType type, BQTerm exp1, TomTerm exp2, String moduleName) throws IOException {
	if(getSymbolTable(moduleName).isBooleanType(TomBase.getTomType(type))) {
		output.write("(");
		generateBQTerm(0,exp1,moduleName);
		output.write(" = ");
		generateTomTerm(0,exp2,moduleName);
		output.write(")");
    } else {
		output.write(deep, "tom_equal_term_" + TomBase.getTomType(type) + "(");
		generateBQTerm(0,exp1,moduleName);
		output.write(", ");
		generateTomTerm(0,exp2,moduleName);
		output.write(")");
    }
  }

  protected void buildExpEqualBQTerm(int deep, TomType type, BQTerm exp1, BQTerm exp2, String moduleName) throws IOException {
    if(getSymbolTable(moduleName).isBooleanType(TomBase.getTomType(type))) {
		output.write("(");
		generateBQTerm(0,exp1,moduleName);
		output.write(" = ");
		generateBQTerm(0,exp2,moduleName);
		output.write(")");
    } else {
		output.write("tom_equal_term_" + TomBase.getTomType(type) + "(");
		generateBQTerm(0,exp1,moduleName);
		output.write(", ");
		generateBQTerm(0,exp2,moduleName);
		output.write(")");
    }
  }
  
  protected void buildEqualTermDecl(int deep, String varname1, String varname2,
                                     String type1, String type2, Expression code, String moduleName) throws IOException {
	TomType argType1 = getSymbolTable(moduleName).getType(type1);
	if(getSymbolTable(moduleName).isBuiltinType(type1)) {
	  argType1 = getSymbolTable(moduleName).getBuiltinType(type1);
	}
	TomType argType2 = getSymbolTable(moduleName).getType(type2);
	if(getSymbolTable(moduleName).isBuiltinType(type2)) {
	  argType2 = getSymbolTable(moduleName).getBuiltinType(type2);
	}
	boolean inlined = inlineplus;
	if(code.isCode()) {
	  // perform the instantiation
	  String ocode = code.getCode();
	  String ncode = ocode.replace("{0}",varname1).replace("{1}",varname2);
	  if(!ncode.equals(ocode)) {
		inlined = true;
		code = code.setCode(ncode);
	  }
	}
	if(!inline || !code.isCode() || !inlined) {
	  genDeclInstr(TomBase.getTLType(getSymbolTable(moduleName).getBooleanType()), "tom_equal_term", type1,
		  new String[] {
		  TomBase.getTLType(argType1), varname1,
		  TomBase.getTLType(argType2), varname2
		  },
		  `Return(ExpressionToBQTerm(code)),deep,moduleName);
	}
  }

  protected void buildExpConditional(int deep, Expression cond,Expression exp1, Expression exp2, String moduleName) throws IOException {
    output.write("(if ");
    generateExpression(0,cond,moduleName);
    output.write(" then ");
    generateExpression(0,exp1,moduleName);
    output.write(" else ");
    generateExpression(0,exp2,moduleName);
    output.write(")");
  }

  protected void buildExpAnd(int deep, Expression exp1, Expression exp2, String moduleName) throws IOException {
    output.write("( ");
    generateExpression(0,exp1,moduleName);
    output.write(" and ");
    generateExpression(0,exp2,moduleName);
    output.write(" )");
  }

  protected void buildExpOr(int deep, Expression exp1, Expression exp2, String moduleName) throws IOException {
    output.write("( ");
    generateExpression(0,exp1,moduleName);
    output.write(" or ");
    generateExpression(0,exp2,moduleName);
    output.write(" )");
  }

  protected void buildExpCast(int deep, TargetLanguageType tlType, Expression exp, String moduleName) throws IOException {
    // If cast are necessary: 
    
    String cast = TomBase.getTLCode(tlType);
    String s[] = cast.trim().split("\\s");
    if (s.length == 1) {
		output.write(cast + "(");
		generateExpression(0,exp,moduleName);
		output.write(")");
	} else {
		generateExpression(deep,exp,moduleName);
	}
  }

  protected void buildExpNegation(int deep, Expression exp, String moduleName) throws IOException {
    output.write("not (");
    generateExpression(0,exp,moduleName);
    output.write(")");
  }

  protected void buildIf(int deep, Expression exp, Instruction succes, String moduleName) throws IOException {
    output.write(deep,"if "); 
    generateExpression(0,exp, moduleName); 
    output.writeln(" then ");
    generateInstruction(deep+1,succes, moduleName);
    output.writeln(deep,"end if; "); 
  }

  protected void buildIfWithFailure(int deep, Expression exp, Instruction succes, Instruction failure, String moduleName) throws IOException {
    output.write(deep,"if "); 
    generateExpression(0,exp,moduleName); 
    output.writeln(" then ");
    generateInstruction(deep+1,succes,moduleName);
    output.writeln(deep,"else ");
    generateInstruction(deep+1,failure,moduleName);
    output.writeln(deep,"end if; "); 
  }

  protected void buildInstructionSequence(int deep, InstructionList instructionList, String moduleName) throws IOException {
    generateInstructionList(deep, instructionList, moduleName);
  }

  protected void buildLet(int deep, BQTerm var, OptionList optionList, TargetLanguageType tlType, 
  Expression exp, Instruction body, String moduleName) throws IOException {
    output.write(deep, "declare ");
    generateBQTerm(0,var,moduleName);
    output.write(": ");
    output.write(0,TomBase.getTLCode(tlType) + ":=");

    generateExpression(0,exp,moduleName);
    output.writeln("; begin ");
    generateInstruction(deep+1,body,moduleName);
    output.writeln(deep, "end; ");
  }

 
  protected void buildLetRef(int deep, BQTerm var, OptionList optionList, TargetLanguageType tlType, 
      Expression exp, Instruction body, String moduleName) throws IOException {
    buildLet(deep,var,optionList,tlType,exp,body, moduleName);
  }
 
  protected void buildReturn(int deep, BQTerm exp, String moduleName) throws IOException {
    output.write(deep,"return ");
    generateBQTerm(0,exp,moduleName);
    output.writeln("; ");
  }

  protected void buildUnamedBlock(int deep, InstructionList instList, String moduleName) throws IOException {
    generateInstructionList(deep+1,instList, moduleName);
  }
 
  protected void buildWhileDo(int deep, Expression exp, Instruction succes, String moduleName) throws IOException {
    output.writeln(deep,"while ");
    generateExpression(0,exp,moduleName);
    output.writeln(" loop ");
    generateInstruction(deep,succes,moduleName);
    output.writeln(deep, "end loop; ");
  }
  
	private boolean isBuiltinType(String type) {
		return type.equals("Integer") || type.equals("Long") || type.equals("Character") ||
		type.equals("String") || type.equals("Boolean") || type.equals("Long_Float") ||
		type.equals("Float)");
	}
	
	private boolean isAccessType(String type) {
		return type.toLowerCase().startsWith("access");
	}
	
	private String getRawType(String type) {
		if (isAccessType(type))
			return type.substring(7);
		else
			return type;
	}
	
	private String getClassWideAccess(String type) {
		String rtype = getRawType(type.trim());
		if (isBuiltinType(rtype)) {
			return type;
		} else {
			return "access " + rtype + "\'Class";
		}
	}
	
  protected void genDeclEqualTermString(String returnType,
                         String declName,
                         String suffix,
                         String args[],
                         Instruction instr,
                         int deep, String moduleName) throws IOException {
    StringBuilder s = new StringBuilder();
    s.append("function ");
    s.append(declName);
    s.append("_");
    s.append(suffix);

    s.append("(");
    for(int i=0 ; i<args.length ; ) {
		s.append(args[i+1]); // parameter name
		s.append(": ");

		s.append("access String");
		//s.append(args[i]); // parameter type
		i+=2;
		if(i<args.length) {
		s.append("; ");
		}
    }
    s.append(")");
    s.append(" return ");
    s.append(returnType);
    output.write(deep, s);
    output.writeln(" is ");
    output.writeln(deep, "begin ");
    generateInstruction(deep+1,instr,moduleName);
    output.writeln(deep, "end " + declName + "_" + suffix + "; ");	
    
	output.writeln(deep, "function tom_equal_term_String(t1: String; t2: access String) return Boolean is "); 
	output.writeln(deep, "begin ");
	output.writeln(deep+1, "return t1 = t2.all; ");
	output.writeln(deep, "end tom_equal_term_String; ");
	output.writeln(); 
  }

  protected void genDeclInstr(String returnType,
                         String declName,
                         String suffix,
                         String args[],
                         Instruction instr,
                         int deep, String moduleName) throws IOException {
	
	if ("tom_equal_term_String".equals(declName + "_" + suffix)) {
		genDeclEqualTermString(returnType, declName, suffix, args, instr, deep, moduleName);
		return;
	}
	
    StringBuilder s = new StringBuilder();
	boolean useClassWideAccess = "tom_get_slot".equals(declName) || "tom_is_fun_sym".equals(declName) || "tom_equal_term".equals(declName);
    s.append("function ");
    s.append(declName);
    s.append("_");
    s.append(suffix);
	if (args.length > 0) { s.append("("); }


    for(int i=0 ; i<args.length ; ) {
		s.append(args[i+1]); // parameter name
		s.append(": ");
		if (useClassWideAccess) {
			s.append(getClassWideAccess(args[i]));
		} else {
			s.append(args[i]); // parameter type
		}
		i+=2;
		if(i<args.length) {
		s.append("; ");
		}
    }
    if (args.length > 0) { s.append(")"); }
    s.append(" return ");
    if (useClassWideAccess) { s.append(getClassWideAccess(returnType)); } else { s.append(returnType); }
    output.write(deep, s);
    output.writeln(" is ");

    
    output.writeln(deep,"begin ");
    generateInstruction(deep+1,instr,moduleName);
    output.writeln(deep,"end " + declName + "_" + suffix + "; ");
    output.writeln();
  }

  protected void genDeclList(String name, String moduleName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(name);
    TomType listType = TomBase.getSymbolCodomain(tomSymbol);
    TomType eltType = TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType();

    if(nodeclMode) {
    return;
    }

    String tomType = TomBase.getTomType(listType);
    String glType = TomBase.getTLType(listType);
    //String tlEltType = getTLType(eltType);

    //String utype = glType;
    //if(lazyType) {
    //  utype = getTLType(getUniversalType());
    //}

    String is_empty = "tom_is_empty_" + name + "_" + tomType;
    String equal_term = "tom_equal_term_" + tomType;
    String make_insert = "tom_cons_list_" + name;
    String make_empty = "tom_empty_list_" + name;
    String get_head = "tom_get_head_" + name + "_" + tomType;
    String get_tail = "tom_get_tail_" + name + "_" + tomType;
    String get_slice = "tom_get_slice_" + name;
    
    int deep = BackendPlugin.defaultDeep;

    output.writeln(deep,"function tom_append_list_" + name +  "(l1: "+ glType+"; l2: "+glType+") return "+ glType + " is ");
    output.writeln(deep,"begin ");
    output.writeln(deep+1,"if " + is_empty + "(l1) then ");
    output.writeln(deep+2,"return l2; ");  
    output.writeln(deep+1,"elsif " + is_empty + "(l2) then ");
    output.writeln(deep+2,"return l1; ");  
    output.writeln(deep+1,"elsif " + is_empty + "(" + get_tail + "(l1)) then ");  
    output.writeln(deep+2,"return " + make_insert + "(" + get_head + "(l1),l2); ");
    output.writeln(deep+1,"else ");  
    output.writeln(deep+2,"return " + make_insert + "(" + get_head + "(l1),tom_append_list_" + name +  "(" + get_tail + "(l1),l2)); ");
    output.writeln(deep+1,"end if; ");
    output.writeln(deep,"end tom_append_list_" + name + "; ");
    output.writeln();

	output.writeln(deep,"function tom_get_slice_" + name + "(begining: "+ glType+"; ending: "+ glType+"; tail: "+glType+") return "+ glType + " is ");
	output.writeln(deep,"begin ");
	output.writeln(deep+1,"if " + equal_term + "(begining,ending) then ");
	output.writeln(deep+2,"return tail; ");
	output.writeln(deep+1,"else ");
	output.writeln(deep+2,"return " +  make_insert + "(" + get_head + "(begining)," +  get_slice + "(" + get_tail + "(begining),ending,tail)); ");
	output.writeln(deep+1,"end if; ");
	output.writeln(deep,"end tom_get_slice_" + name + "; ");
	output.writeln();
    
  }

  protected void genDeclMake(String prefix,String funName, TomType returnType, 
      BQTermList argList, Instruction instr, String moduleName) throws IOException {
    if(nodeclMode) {
      return;
    }
	boolean parenthesis = !argList.isEmptyconcBQTerm();
    boolean inlined = inlineplus;
    boolean isCode = false;
    
    int deep = BackendPlugin.defaultDeep;
    
    %match(instr) {
      ExpressionToInstruction(Code(code)) -> {
        isCode = true;
        // perform the instantiation
        String ncode = `code;
        int index = 0;
        %match(argList) {
          concBQTerm(_*,BQVariable[AstName=Name(varname)],_*) -> {
            ncode = ncode.replace("{"+index+"}",`varname);
            index++;
          }
        }

        if(!ncode.equals(`code)) {
          inlined = true;
          instr = `ExpressionToInstruction(Code(ncode));
        }
      }
    }
    if(!inline || !isCode || !inlined) {
      StringBuilder s = new StringBuilder();
      s.append(modifier + "function " + prefix + funName);
      if (parenthesis) { s.append("("); }
      while(!argList.isEmptyconcBQTerm()) {
        BQTerm arg = argList.getHeadconcBQTerm();
matchBlock: {
              %match(arg) {
                BQVariable[AstName=Name(name), AstType=Type[TlType=tlType@TLType[]]] -> {
                  s.append(`name + ": " + getClassWideAccess( TomBase.getTLCode(`tlType) ));
                  break matchBlock;
                }

                _ -> {
                  System.out.println("genDeclMake: strange term: " + arg);
                  throw new TomRuntimeException("genDeclMake: strange term: " + arg);
                }
              }
            }
            argList = argList.getTailconcBQTerm();
            if(!argList.isEmptyconcBQTerm()) {
              s.append("; ");
            }
      }
    if (parenthesis) {	s.append(")"); } 
    s.append(" return " + getClassWideAccess( TomBase.getTLType(returnType) ));
    output.write(deep, s);
    output.writeln(" is ");
    output.writeln(deep, "begin ");
    output.write(deep+1, "return ");
    generateInstruction(0,instr,moduleName);
    output.writeln("; ");
    output.writeln(deep, "end "+ prefix+funName + "; ");
    output.writeln();
    }
  }

  protected void buildNamedBlock(int deep, String blockName, InstructionList instList, String moduleName) throws IOException {
    output.writeln(deep, "<<"+ blockName + ">>; ");
    generateInstructionList(deep+1,instList,moduleName);
  }

  protected void buildExpTrue(int deep) throws IOException {
    output.write(deep,"True");
  }

  protected void buildExpFalse(int deep) throws IOException {
    output.write(deep, "False");
  }

  protected void buildExpBottom(int deep, TomType type, String moduleName) throws IOException {
    if ((getSymbolTable(moduleName).getIntType() == type)
        || (getSymbolTable(moduleName).getCharType() == type)
        || (getSymbolTable(moduleName).getLongType() == type)
        || (getSymbolTable(moduleName).getFloatType() == type)
        || (getSymbolTable(moduleName).getDoubleType() == type)) {
      output.write(deep, " 0 ");
    } else if (getSymbolTable(moduleName).getBooleanType() == type) {
      output.write(deep, " False ");
    } else if (getSymbolTable(moduleName).getStringType() == type) {
      output.write(deep, " \"\" ");
    } else {
      output.write(deep, " null ");
    }
  }
  
  protected void buildListOrArray(int deep, BQTerm list, String moduleName) throws IOException {
	%match(list) {
	  BuildEmptyList(Name(name)) -> {
		String prefix = "tom_empty_list_";
		String template = getSymbolTable(moduleName).getMakeEmptyList(`name);
		if(instantiateTemplate(deep,template,`name,`concBQTerm(),moduleName) == false) {
		  output.write(deep, prefix + `name);
		}
		return;
	  }

	  BuildConsList(Name(name), headTerm, tailTerm) -> {
		String prefix = "tom_cons_list_";
		String template = getSymbolTable(moduleName).getMakeAddList(`name);
		if(instantiateTemplate(deep,template,`name,`concBQTerm(headTerm,tailTerm),moduleName) == false) {
		  output.write(deep, prefix + `name + "(");
		  generateBQTerm(0,`headTerm,moduleName);
		  output.write(",");
		  generateBQTerm(0,`tailTerm,moduleName);
		  output.write(")");
		}
		return;
	  }

	  BuildAppendList(Name(name), headTerm, tailTerm) -> {
		output.write(deep, "tom_append_list_" + `name + "(");
		generateBQTerm(0,`headTerm,moduleName);
		output.write(",");
		generateBQTerm(0,`tailTerm,moduleName);
		output.write(")");
		return;
	  }

	  BuildEmptyArray(Name(name),size) -> {
		String prefix = "tom_empty_array_";
		String template = getSymbolTable(moduleName).getMakeEmptyArray(`name);
		if(instantiateTemplate(deep,template,`name,`concBQTerm(size),moduleName) == false) {
		  output.write(deep, prefix + `name + "(");
		  generateBQTerm(0,`size,moduleName);
		  output.write(")");
		}
		return;
	  }

	  BuildConsArray(Name(name), headTerm, tailTerm) -> {
		String template = getSymbolTable(moduleName).getMakeAddArray(`name);
		if(instantiateTemplate(deep,template,`name,`concBQTerm(headTerm,tailTerm),moduleName) == false) {
		  String prefix = "tom_cons_array_";
		  output.write(deep, prefix + `name + "(");
		  generateBQTerm(0,`headTerm,moduleName);
		  output.write(",");
		  generateBQTerm(0,`tailTerm,moduleName);
		  output.write(")");
		}
		return;
	  }

	  BuildAppendArray(Name(name), headTerm, tailTerm) -> {
		output.write(deep, "tom_append_array_" + `name + "(");
		generateBQTerm(0,`headTerm,moduleName);
		output.write(",");
		generateBQTerm(0,`tailTerm,moduleName);
		output.write(")");
		return;
	  }
	}
  }
  
	protected void buildFunctionCall(int deep, String name, BQTermList argList, String moduleName) throws IOException {
		output.write(deep, name);
		boolean parenthesis = !argList.isEmptyconcBQTerm();
		if (parenthesis) { output.writeOpenBrace(); }
		while(!argList.isEmptyconcBQTerm()) {
			generateBQTerm(deep,argList.getHeadconcBQTerm(),moduleName);
			argList = argList.getTailconcBQTerm();
			if(!argList.isEmptyconcBQTerm()) {
				output.writeComa();
			}
		}
		if (parenthesis) { output.writeCloseBrace(); }
	}

  protected void genDecl(String returnType,
      String declName,
      String suffix,
      String args[],
      TargetLanguage tlCode,
      String moduleName) throws IOException {

    if(nodeclMode) {
      return;
    }

    StringBuilder s = new StringBuilder();
    int deep = 0;
    
    s.append(modifier + "function " + declName + suffix);
    if (args.length > 0) { s.append("("); }
    for(int i=0 ; i<args.length ; ) {
		s.append(args[i+1]); // parameter name
		s.append(": ");
		s.append(args[i]); // parameter type
		i+=2;
		if(i<args.length) { s.append("; "); }
    }
    if (args.length > 0) { s.append(")"); }
    s.append(" return " + returnType);
    output.write(deep, s);
    output.writeln(" is ");
        
    output.writeln(deep, "begin ");
    
    String returnValue = getSymbolTable(moduleName).isVoidType(returnType)?tlCode.getCode():"return " + tlCode.getCode();
    %match(tlCode) {
      TL(_,TextPosition[Line=startLine], TextPosition[Line=endLine]) -> {
        output.write(0,returnValue, `startLine, `endLine - `startLine);
        return;
      }

      ITL(_) -> {
        output.write(deep, returnValue);
        return;
      }

    }
    output.writeln("; ");
    output.writeln(deep, "end " + declName + suffix + "; ");
    output.writeln();
  }

  protected void buildAssignArray(int deep, BQTerm var, OptionList optionList, BQTerm index, 
      Expression exp, String moduleName) throws IOException {
    buildAssignArrayVar(deep,var,optionList, index, exp, moduleName);
  }

  protected void buildAssignArrayVar(int deep, BQTerm var, OptionList optionList, BQTerm index, 
      Expression exp, String moduleName) throws IOException {    
    generateArray(deep,var,index,moduleName);
    output.write(":=");
    generateExpression(0,exp,moduleName);
    output.writeln("; ");
  } 
  
  protected void buildFunctionDef(int deep, String tomName, BQTermList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,this.modifier);
  }

  protected void buildMethodDef(int deep, String tomName, BQTermList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,"public ");
  }
  
  private void buildMethod(int deep, String tomName, BQTermList varList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName, String methodModifier) throws IOException {
	boolean parenthesis = !varList.isEmptyconcBQTerm();
	output.write(deep, "function " + tomName);
	if (parenthesis) { output.write("("); }
	
    while(!varList.isEmptyconcBQTerm()) {
      BQTerm localVar = varList.getHeadconcBQTerm();
	matchBlock: {
              %match(localVar) {
                v@BQVariable[AstType=type2] -> {
				  generateBQTerm(deep,`v,moduleName);
                  output.write(": " + getClassWideAccess( TomBase.getTLType(`type2) ));
                  break matchBlock;
                }
                _ -> {
                  System.out.println("MakeFunction: strange term: " + localVar);
                  throw new TomRuntimeException("MakeFunction: strange term: " + localVar);
                }
              }
            }
            varList = varList.getTailconcBQTerm();
            if(!varList.isEmptyconcBQTerm()) {
              output.write(deep,", ");

            }
    }
    if (parenthesis) { output.write(deep,")"); }

	output.writeln(" return " + TomBase.getTLType(codomain) + " is ");
	output.writeln(deep, "begin ");
	generateInstruction(deep+1,instruction,moduleName);
	output.writeln(deep,"end " + tomName + "; ");
	output.writeln();
  }
  
  // For strategy generation
  protected void buildClass(int deep, String tomName, TomType extendsType, BQTerm superTerm, Declaration declaration, String moduleName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(tomName);
    TomTypeList tomTypes = TomBase.getSymbolDomain(tomSymbol);
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<Integer> stratChild = new ArrayList<Integer>(); // child of type Strategy.

    //initialize arrayList with argument names
    int index = 0;
    while(!tomTypes.isEmptyconcTomType()) {
	    TomType type = tomTypes.getHeadconcTomType();
	    types.add(TomBase.getTLType(type));
      String name = TomBase.getSlotName(tomSymbol, index).getString();
      names.add(name);

      // test if the argument is a Strategy
      %match(type) {
        Type[TomType="Strategy"] -> {
          stratChild.add(Integer.valueOf(index));
        }
      }

	    tomTypes = tomTypes.getTailconcTomType();
	    index++;
    }
    output.write(deep, stratmodifier + "class " + tomName);
    //write extends
matchblock: {
              %match(extendsType) {
			Type[TlType=TLType(code)] -> {
				output.write(deep," extends " + `code);
        break matchblock;
			}

			Type[TomType=code,TlType=EmptyTargetLanguageType()] -> {
				output.write(deep," extends " + `code);
        break matchblock;
			}
    }
            }
    output.writeln(deep," {");
    int args = names.size();
    //write Declarations
    for(int i = 0 ; i < args ; i++) {
      output.writeln(deep, "private " + types.get(i) + " " + names.get(i) + ";");
    }

    //write constructor
    output.write(deep, "public " + tomName + "(");
    //write constructor parameters
    for(int i = 0 ; i < args ; i++) {
	    output.write(deep,types.get(i) + " " + names.get(i));
	    if(i+1<args) {//if many parameters
		    output.write(deep,", ");
	    }
    }

    //write constructor initialization
    output.writeln(deep,") {");
    output.write(deep+1,"super(");
    generateBQTerm(deep,superTerm,moduleName);
    output.writeln(");");

    //here index represents the parameter number
    for(int i = 0 ; i < args ; i++) {
	    String param = names.get(i);
	    output.writeln(deep+1, "this." + param + "=" + param + ";");
    }
    output.writeln(deep,"}");

    // write getters
    for(int i = 0 ; i < args ; i++) {
      output.writeln(deep, "public " + types.get(i) + " get" + names.get(i) + "() {");
      output.writeln(deep+1,"return " + names.get(i) + ";");
      output.writeln(deep,"}");
    }

    // write getChildCount (= 1 + stratChildCount because of the %strategy `extends' which is the first child)
    int stratChildCount = stratChild.size();

    output.writeln(deep, "public tom.library.sl.Visitable[] getChildren() {");
    output.writeln(deep, "tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];");
    output.writeln(deep, "stratChilds[0] = super.getChildAt(0);");
    for(int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep, "stratChilds[" + (i+1) + "] = get" + names.get(j) + "();");
    }
    output.writeln(deep, "return stratChilds;}");

    output.writeln(deep, "public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {");
    output.writeln(deep,"super.setChildAt(0, children[0]);");
    for(int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep, names.get(j) + " = (" + types.get(j) + ") children[" + (i+1) + "];");
    }
    output.writeln(deep, "return this;");
    output.writeln(deep, "}");

    output.writeln(deep, "public int getChildCount() {");
    output.writeln(deep, "return " + (stratChildCount + 1) + ";");
    output.writeln(deep, "}");

    // write getChildAt
    output.writeln(deep, "public tom.library.sl.Visitable getChildAt(int index) {");
    output.writeln(deep, "switch (index) {");
    output.writeln(deep, "case 0: return super.getChildAt(0);");
    for (int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep, "case " + (i+1) + ": return get" + names.get(j) + "();");
    }
    output.writeln(deep, "default: throw new IndexOutOfBoundsException();");
    output.writeln(deep, "}");
    output.writeln(deep, "}");

    // write setChildAt
    output.writeln(deep, "public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {");
    output.writeln(deep, "switch (index) {");
    output.writeln(deep, "case 0: return super.setChildAt(0, child);");
    for (int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep, "case " + (i+1) + ": " + names.get(j) + " = (" + types.get(j) + ")child; return this;");
    }
    output.writeln(deep, "default: throw new IndexOutOfBoundsException();");
    output.writeln(deep, "}");
    output.writeln(deep, "}");

    generateDeclaration(deep,`declaration,moduleName);
    output.writeln(deep,"}");
  }

}
