
























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

  
     private static   tom.engine.adt.code.types.BQTermList  tom_append_list_concBQTerm( tom.engine.adt.code.types.BQTermList l1,  tom.engine.adt.code.types.BQTermList  l2) {     if( l1.isEmptyconcBQTerm() ) {       return l2;     } else if( l2.isEmptyconcBQTerm() ) {       return l1;     } else if(  l1.getTailconcBQTerm() .isEmptyconcBQTerm() ) {       return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,l2) ;     } else {       return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,tom_append_list_concBQTerm( l1.getTailconcBQTerm() ,l2)) ;     }   }   private static   tom.engine.adt.code.types.BQTermList  tom_get_slice_concBQTerm( tom.engine.adt.code.types.BQTermList  begin,  tom.engine.adt.code.types.BQTermList  end, tom.engine.adt.code.types.BQTermList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcBQTerm()  ||  (end== tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( begin.getHeadconcBQTerm() ,( tom.engine.adt.code.types.BQTermList )tom_get_slice_concBQTerm( begin.getTailconcBQTerm() ,end,tail)) ;   }      private static   tom.engine.adt.code.types.BQTerm  tom_append_list_Composite( tom.engine.adt.code.types.BQTerm l1,  tom.engine.adt.code.types.BQTerm  l2) {     if( l1.isEmptyComposite() ) {       return l2;     } else if( l2.isEmptyComposite() ) {       return l1;     } else if(  l1.getTailComposite() .isEmptyComposite() ) {       return  tom.engine.adt.code.types.bqterm.ConsComposite.make( l1.getHeadComposite() ,l2) ;     } else {       return  tom.engine.adt.code.types.bqterm.ConsComposite.make( l1.getHeadComposite() ,tom_append_list_Composite( l1.getTailComposite() ,l2)) ;     }   }   private static   tom.engine.adt.code.types.BQTerm  tom_get_slice_Composite( tom.engine.adt.code.types.BQTerm  begin,  tom.engine.adt.code.types.BQTerm  end, tom.engine.adt.code.types.BQTerm  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyComposite()  ||  (end== tom.engine.adt.code.types.bqterm.EmptyComposite.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.code.types.bqterm.ConsComposite.make( begin.getHeadComposite() ,( tom.engine.adt.code.types.BQTerm )tom_get_slice_Composite( begin.getTailComposite() ,end,tail)) ;   }   
  

  
  protected String stratmodifier = "";
  
  
  private Vector<String> isSortType = new Vector<String>(1);
  private Vector<Expression> isSortCode = new Vector<Expression>(1);
  
  private static final String builtinDescriptor = "builtin";
  private static final String taggedDescriptor = "tagged";
  
  public AdaGenerator(OutputCode output, OptionManager optionManager,
                                SymbolTable symbolTable) {
    super(output, optionManager, symbolTable);
  }

  protected void buildExpIsSort(int deep, String type, BQTerm exp, String moduleName) throws IOException {
	TomType tomType = getSymbolTable(moduleName).getType(type);
	String declaredType = TomBase.getTLType(tomType);
	  
    

    String template = getSymbolTable(moduleName).getIsSort(type);
    String opname="";
    if(instantiateTemplate(deep,template,opname, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(exp, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ,moduleName) == false) {
        
		Expression code = getIsSortExpressionFromType(type);

		if (code == null) { 
			
			output.write("tom_is_sort_" + type + "(");
			generateBQTerm(0,exp,moduleName);
			output.write(")");
			return;
		}
		
		if(code.isCode()) { 
		  
		  String ocode = code.getCode();
		  String codeTab[] = ocode.split("\\{0\\}");
		  for(int i = 0 ; i < codeTab.length - 1 ; i++) {
			code = code.setCode( codeTab[i] );
			generateBQTerm(0, tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make(code) ,moduleName);
			generateBQTerm(0,exp,moduleName);
		  }
		 code = code.setCode( codeTab[codeTab.length - 1] );
		  generateBQTerm(0, tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make(code) ,moduleName); 
			
		} else {
			generateBQTerm(0, tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make(code) ,moduleName);
		}
    }
  }
  
  protected void buildIsSortDeclOriginal(int deep, String varName, String type, Expression code, String moduleName) throws IOException {
    boolean inlined = inlineplus;
    if(code.isCode()) {
      
      String ocode = code.getCode();
      String ncode = ocode.replace("{0}",varName);
      if(!ncode.equals(ocode)) {
        inlined = true;
        code = code.setCode(ncode);
      }
    }
    if(!inline || !code.isCode() || !inlined) {
      TomType argType = getSymbolTable(moduleName).getType(type);
      genDeclInstr(TomBase.getTLType(getSymbolTable(moduleName).getBooleanType()), "tom_is_sort", type,
          new String[] { TomBase.getTLType(argType), varName },
           tom.engine.adt.tominstruction.types.instruction.Return.make( tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make(code) ) ,deep,moduleName);
    }
  }
  
  protected void buildIsSortDecl(int deep, String varName, String type, Expression code, String moduleName) throws IOException {
	  
	 
	 
    isSortType.add(type);
    isSortCode.add(code);

    
    buildIsSortDeclOriginal(deep, varName, type, code, moduleName);
  }
  
  
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
          
          boolean generatebqt = true;
          { /* unamed block */{ /* unamed block */if ( (bqt instanceof tom.engine.adt.code.types.BQTerm) ) {if ( (((( tom.engine.adt.code.types.BQTerm )bqt) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )bqt) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) {if (!( (( tom.engine.adt.code.types.BQTerm )bqt).isEmptyComposite() )) { tom.engine.adt.code.types.CompositeMember  tomMatch71_5= (( tom.engine.adt.code.types.BQTerm )bqt).getHeadComposite() ;if ( ((( tom.engine.adt.code.types.CompositeMember )tomMatch71_5) instanceof tom.engine.adt.code.types.compositemember.CompositeBQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm ) tomMatch71_5.getterm() ) instanceof tom.engine.adt.code.types.bqterm.BQDefault) ) {

              TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(opname);
              
              
              TomName slotName = TomBase.getSlotName(tomSymbol,index);
              
              buildExpGetDefault(0, opname, slotName.getString(), moduleName);
              generatebqt = false;
            }}}}}}}


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
    if(instantiateTemplate(deep,template,opname, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(exp, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ,moduleName) == false) {
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
	TomType argType2 = getSymbolTable(moduleName).getType(type2);

	boolean inlined = inlineplus;
	if(code.isCode()) {
	  
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
		   tom.engine.adt.tominstruction.types.instruction.Return.make( tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make(code) ) ,deep,moduleName);
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
  
  private String extractImplementedType(String declaredType) throws IOException {
	  String[] splited = declaredType.split(":");
	  
	  if (splited.length == 1) {
		  return declaredType;
	  } else if (splited.length == 2) {
		  return splited[1];
	  } else {
		  throw new IOException("':' not expected in '" + declaredType + "'");
	  }
  }
  
  private String extractDescriptor(String declaredType) throws IOException {
	  String[] splited = declaredType.split(":");
	  
	  if (splited.length == 1) {
		  return builtinDescriptor;
	  } else if (splited.length == 2) {
		  return splited[0];
	  } else {
		  throw new IOException("':' not expected in '" + declaredType + "'");
	  }
  }


  protected void buildExpCast(int deep, TargetLanguageType tlType, Expression exp, String moduleName) throws IOException {
    
    String code = TomBase.getTLCode(tlType);
    String cast = extractImplementedType(code);
    String[] cut = cast.trim().split(" "); 
    
	if (cut.length==1 && builtinDescriptor.equals( extractDescriptor(code) )) {
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
    output.write(0, extractImplementedType(TomBase.getTLCode(tlType)) + ":=");

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
    generateInstructionList(deep,instList, moduleName);
  }
 
  protected void buildWhileDo(int deep, Expression exp, Instruction succes, String moduleName) throws IOException {
    output.writeln(deep,"while ");
    generateExpression(0,exp,moduleName);
    output.writeln(" loop ");
    generateInstruction(deep+1,succes,moduleName);
    output.writeln(deep, "end loop; ");
  }
 
 
 
	private boolean isBuiltinType(String type) throws IOException {
		return builtinDescriptor.equals( extractDescriptor(type).trim() );
	}
	
	private boolean isTaggedType(String type) throws IOException {
		return taggedDescriptor.equals( extractDescriptor(type).trim() );
	}
	
	private String getClassWideType(String type) throws IOException {
		if ( isTaggedType(type) ) {
			return extractImplementedType(type) + "\'Class";
		} else {
			return extractImplementedType(type);
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
		s.append(args[i+1]); 
		s.append(": ");
		s.append(getClassWideType(args[i]));
		i+=2;
		if(i<args.length) {
		s.append("; ");
		}
    }
    s.append(")");
    s.append(" return ");
    s.append(getClassWideType(returnType));
    output.write(deep, s);
    output.writeln(" is ");
    output.writeln(deep, "begin ");
    generateInstruction(deep+1,instr,moduleName);
    output.writeln(deep, "end " + declName + "_" + suffix + "; ");	
    output.writeln();
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
    s.append("function ");
    s.append(declName);
    s.append("_");
    s.append(suffix);
	if (args.length > 0) { s.append("("); }

    for(int i=0 ; i<args.length ; ) {
		s.append(args[i+1]); 
		s.append(": ");
		s.append(getClassWideType(args[i]));
		i+=2;
		if(i<args.length) {
		s.append("; ");
		}
    }
    if (args.length > 0) { s.append(")"); }
    s.append(" return ");
    s.append(getClassWideType(returnType));
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
    String glType = getClassWideType(TomBase.getTLType(listType));


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
    
    { /* unamed block */{ /* unamed block */if ( (instr instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )instr) instanceof tom.engine.adt.tominstruction.types.instruction.ExpressionToInstruction) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch72_1= (( tom.engine.adt.tominstruction.types.Instruction )instr).getExpr() ;if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch72_1) instanceof tom.engine.adt.tomexpression.types.expression.Code) ) { String  tom___code= tomMatch72_1.getCode() ;

        isCode = true;
        
        String ncode = tom___code;
        int index = 0;
        { /* unamed block */{ /* unamed block */if ( (argList instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )argList) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )argList) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) { tom.engine.adt.code.types.BQTermList  tomMatch73_end_4=(( tom.engine.adt.code.types.BQTermList )argList);do {{ /* unamed block */if (!( tomMatch73_end_4.isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch73_8= tomMatch73_end_4.getHeadconcBQTerm() ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch73_8) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) { tom.engine.adt.tomname.types.TomName  tomMatch73_7= tomMatch73_8.getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch73_7) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

            ncode = ncode.replace("{"+index+"}", tomMatch73_7.getString() );
            index++;
          }}}if ( tomMatch73_end_4.isEmptyconcBQTerm() ) {tomMatch73_end_4=(( tom.engine.adt.code.types.BQTermList )argList);} else {tomMatch73_end_4= tomMatch73_end_4.getTailconcBQTerm() ;}}} while(!( (tomMatch73_end_4==(( tom.engine.adt.code.types.BQTermList )argList)) ));}}}}


        if(!ncode.equals(tom___code)) { /* unamed block */
          inlined = true;
          instr =  tom.engine.adt.tominstruction.types.instruction.ExpressionToInstruction.make( tom.engine.adt.tomexpression.types.expression.Code.make(ncode) ) ;
        }}}}}}


    if(!inline || !isCode || !inlined) {
      StringBuilder s = new StringBuilder();
      s.append(modifier + "function " + prefix + funName);
      if (parenthesis) { s.append("("); }
      while(!argList.isEmptyconcBQTerm()) {
        BQTerm arg = argList.getHeadconcBQTerm();
matchBlock: {
              { /* unamed block */{ /* unamed block */if ( (arg instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )arg) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) { tom.engine.adt.tomname.types.TomName  tomMatch74_1= (( tom.engine.adt.code.types.BQTerm )arg).getAstName() ; tom.engine.adt.tomtype.types.TomType  tomMatch74_2= (( tom.engine.adt.code.types.BQTerm )arg).getAstType() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch74_1) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )tomMatch74_2) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) { tom.engine.adt.tomtype.types.TargetLanguageType  tomMatch74_8= tomMatch74_2.getTlType() ;if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType )tomMatch74_8) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.TLType) ) {

                  s.append( tomMatch74_1.getString()  + ": " + getClassWideType( TomBase.getTLCode(tomMatch74_8) ));
                  break matchBlock;
                }}}}}}{ /* unamed block */if ( (arg instanceof tom.engine.adt.code.types.BQTerm) ) {


                  System.out.println("genDeclMake: strange term: " + arg);
                  throw new TomRuntimeException("genDeclMake: strange term: " + arg);
                }}}

            }
            argList = argList.getTailconcBQTerm();
            if(!argList.isEmptyconcBQTerm()) {
              s.append("; ");
            }
      }
    if (parenthesis) {	s.append(")"); } 
    s.append(" return " + getClassWideType( TomBase.getTLType(returnType) ));
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
    generateInstructionList(deep,instList,moduleName);
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
	{ /* unamed block */{ /* unamed block */if ( (list instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )list) instanceof tom.engine.adt.code.types.bqterm.BuildEmptyList) ) { tom.engine.adt.tomname.types.TomName  tomMatch75_1= (( tom.engine.adt.code.types.BQTerm )list).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch75_1) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom___name= tomMatch75_1.getString() ;

		String prefix = "tom_empty_list_";
		String template = getSymbolTable(moduleName).getMakeEmptyList(tom___name);
		if(instantiateTemplate(deep,template,tom___name, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ,moduleName) == false) { /* unamed block */
		  output.write(deep, prefix + tom___name);
		}
		return;
	  }}}}{ /* unamed block */if ( (list instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )list) instanceof tom.engine.adt.code.types.bqterm.BuildConsList) ) { tom.engine.adt.tomname.types.TomName  tomMatch75_8= (( tom.engine.adt.code.types.BQTerm )list).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch75_8) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom___name= tomMatch75_8.getString() ; tom.engine.adt.code.types.BQTerm  tom___headTerm= (( tom.engine.adt.code.types.BQTerm )list).getHeadTerm() ; tom.engine.adt.code.types.BQTerm  tom___tailTerm= (( tom.engine.adt.code.types.BQTerm )list).getTailTerm() ;


		String prefix = "tom_cons_list_";
		String template = getSymbolTable(moduleName).getMakeAddList(tom___name);
		if(instantiateTemplate(deep,template,tom___name, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(tom___headTerm, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(tom___tailTerm, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ) ,moduleName) == false) { /* unamed block */
		  output.write(deep, prefix + tom___name + "(");
		  generateBQTerm(0,tom___headTerm,moduleName);
		  output.write(",");
		  generateBQTerm(0,tom___tailTerm,moduleName);
		  output.write(")");
		}
		return;
	  }}}}{ /* unamed block */if ( (list instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )list) instanceof tom.engine.adt.code.types.bqterm.BuildAppendList) ) { tom.engine.adt.tomname.types.TomName  tomMatch75_17= (( tom.engine.adt.code.types.BQTerm )list).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch75_17) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {


		output.write(deep, "tom_append_list_" +  tomMatch75_17.getString()  + "(");
		generateBQTerm(0, (( tom.engine.adt.code.types.BQTerm )list).getHeadTerm() ,moduleName);
		output.write(",");
		generateBQTerm(0, (( tom.engine.adt.code.types.BQTerm )list).getTailTerm() ,moduleName);
		output.write(")");
		return;
	  }}}}{ /* unamed block */if ( (list instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )list) instanceof tom.engine.adt.code.types.bqterm.BuildEmptyArray) ) { tom.engine.adt.tomname.types.TomName  tomMatch75_26= (( tom.engine.adt.code.types.BQTerm )list).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch75_26) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom___name= tomMatch75_26.getString() ; tom.engine.adt.code.types.BQTerm  tom___size= (( tom.engine.adt.code.types.BQTerm )list).getSize() ;


		String prefix = "tom_empty_array_";
		String template = getSymbolTable(moduleName).getMakeEmptyArray(tom___name);
		if(instantiateTemplate(deep,template,tom___name, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(tom___size, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ,moduleName) == false) { /* unamed block */
		  output.write(deep, prefix + tom___name + "(");
		  generateBQTerm(0,tom___size,moduleName);
		  output.write(")");
		}
		return;
	  }}}}{ /* unamed block */if ( (list instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )list) instanceof tom.engine.adt.code.types.bqterm.BuildConsArray) ) { tom.engine.adt.tomname.types.TomName  tomMatch75_34= (( tom.engine.adt.code.types.BQTerm )list).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch75_34) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom___name= tomMatch75_34.getString() ; tom.engine.adt.code.types.BQTerm  tom___headTerm= (( tom.engine.adt.code.types.BQTerm )list).getHeadTerm() ; tom.engine.adt.code.types.BQTerm  tom___tailTerm= (( tom.engine.adt.code.types.BQTerm )list).getTailTerm() ;


		String template = getSymbolTable(moduleName).getMakeAddArray(tom___name);
		if(instantiateTemplate(deep,template,tom___name, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(tom___headTerm, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(tom___tailTerm, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ) ,moduleName) == false) { /* unamed block */
		  String prefix = "tom_cons_array_";
		  output.write(deep, prefix + tom___name + "(");
		  generateBQTerm(0,tom___headTerm,moduleName);
		  output.write(",");
		  generateBQTerm(0,tom___tailTerm,moduleName);
		  output.write(")");
		}
		return;
	  }}}}{ /* unamed block */if ( (list instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )list) instanceof tom.engine.adt.code.types.bqterm.BuildAppendArray) ) { tom.engine.adt.tomname.types.TomName  tomMatch75_43= (( tom.engine.adt.code.types.BQTerm )list).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch75_43) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {


		output.write(deep, "tom_append_array_" +  tomMatch75_43.getString()  + "(");
		generateBQTerm(0, (( tom.engine.adt.code.types.BQTerm )list).getHeadTerm() ,moduleName);
		output.write(",");
		generateBQTerm(0, (( tom.engine.adt.code.types.BQTerm )list).getTailTerm() ,moduleName);
		output.write(")");
		return;
	  }}}}}

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
		s.append(args[i+1]); 
		s.append(": ");
		s.append(getClassWideType( args[i] )); 
		i+=2;
		if(i<args.length) { s.append("; "); }
    }
    if (args.length > 0) { s.append(")"); }
    s.append(" return " + getClassWideType(returnType));
    output.write(deep, s);
    output.writeln(" is ");
        
    output.writeln(deep, "begin ");
    
    String returnValue = getSymbolTable(moduleName).isVoidType(returnType)?tlCode.getCode():"return " + tlCode.getCode();
    returnValue = getClassWideType(returnValue);
    { /* unamed block */{ /* unamed block */if ( (tlCode instanceof tom.engine.adt.code.types.TargetLanguage) ) {if ( ((( tom.engine.adt.code.types.TargetLanguage )tlCode) instanceof tom.engine.adt.code.types.targetlanguage.TL) ) { tom.engine.adt.tomsignature.types.TextPosition  tomMatch76_2= (( tom.engine.adt.code.types.TargetLanguage )tlCode).getStart() ; tom.engine.adt.tomsignature.types.TextPosition  tomMatch76_3= (( tom.engine.adt.code.types.TargetLanguage )tlCode).getEnd() ;if ( ((( tom.engine.adt.tomsignature.types.TextPosition )tomMatch76_2) instanceof tom.engine.adt.tomsignature.types.textposition.TextPosition) ) { int  tom___startLine= tomMatch76_2.getLine() ;if ( ((( tom.engine.adt.tomsignature.types.TextPosition )tomMatch76_3) instanceof tom.engine.adt.tomsignature.types.textposition.TextPosition) ) {

        output.write(0,returnValue, tom___startLine,  tomMatch76_3.getLine()  - tom___startLine);
        return;
      }}}}}{ /* unamed block */if ( (tlCode instanceof tom.engine.adt.code.types.TargetLanguage) ) {if ( ((( tom.engine.adt.code.types.TargetLanguage )tlCode) instanceof tom.engine.adt.code.types.targetlanguage.ITL) ) {


        output.write(deep, returnValue);
        return;
      }}}}


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
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,"");
  }

  protected void buildMethodDef(int deep, String tomName, BQTermList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,"");
  }
  
 
  private void buildMethod(int deep, String tomName, BQTermList varList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName, String methodModifier) throws IOException {
	boolean parenthesis = !varList.isEmptyconcBQTerm();
	
	
	
	output.write(deep, "function " + tomName);
	if (parenthesis) { output.write("("); }
	
    while(!varList.isEmptyconcBQTerm()) {
      BQTerm localVar = varList.getHeadconcBQTerm();
	matchBlock: {
              { /* unamed block */{ /* unamed block */if ( (localVar instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )localVar) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {

				  generateBQTerm(0,(( tom.engine.adt.code.types.BQTerm )localVar),moduleName);
                  output.write(": " + getClassWideType( TomBase.getTLType( (( tom.engine.adt.code.types.BQTerm )localVar).getAstType() ) ));
                  break matchBlock;
                }}}{ /* unamed block */if ( (localVar instanceof tom.engine.adt.code.types.BQTerm) ) {

                  System.out.println("MakeFunction: strange term: " + localVar);
                  throw new TomRuntimeException("MakeFunction: strange term: " + localVar);
                }}}

            }
            varList = varList.getTailconcBQTerm();
            if(!varList.isEmptyconcBQTerm()) {
              output.write("; ");

            }
    }
    if (parenthesis) { output.write(")"); }

	output.writeln(" return " + getClassWideType(TomBase.getTLType(codomain)) + " is ");
	output.writeln(deep, "begin ");
	generateInstruction(deep+1,instruction,moduleName);
	output.writeln(deep,"end " + tomName + "; ");
	output.writeln();
  }
  
  protected void buildIntrospectorClass(int deep, String tomName, Declaration declaration, String moduleName) throws IOException {
    output.writeln(deep, "type " + tomName+ "is new Introspector with null record;");
    output.writeln(deep, "-- DEBUT DECLARATION");
    generateDeclaration(deep,declaration,moduleName);
    output.writeln(deep,"-- FIN DECLARATION");
  }

  protected void buildClass(int deep, String tomName, TomType extendsType, BQTerm superTerm, Declaration declaration, String moduleName) throws IOException {
   TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(tomName);
    TomTypeList tomTypes = TomBase.getSymbolDomain(tomSymbol);
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<Integer> stratChild = new ArrayList<Integer>(); 
    String inheritedClass = "";

    
    int index = 0;
    while(!tomTypes.isEmptyconcTomType()) {
	    TomType type = tomTypes.getHeadconcTomType();
	    types.add(TomBase.getTLType(type));
      String name = TomBase.getSlotName(tomSymbol, index).getString();
      names.add(name);

      
      { /* unamed block */{ /* unamed block */if ( (type instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )type) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {if ( "Strategy".equals( (( tom.engine.adt.tomtype.types.TomType )type).getTomType() ) ) {

          stratChild.add(Integer.valueOf(index));
        }}}}}


	    tomTypes = tomTypes.getTailconcTomType();
	    index++;
    }
    output.write(deep, "type " + tomName);
    
    
	matchblock: {
		{ /* unamed block */{ /* unamed block */if ( (extendsType instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )extendsType) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) { tom.engine.adt.tomtype.types.TargetLanguageType  tomMatch79_1= (( tom.engine.adt.tomtype.types.TomType )extendsType).getTlType() ;if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType )tomMatch79_1) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.TLType) ) {

				inheritedClass =  tomMatch79_1.getString() ;
				break matchblock;
			}}}}{ /* unamed block */if ( (extendsType instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )extendsType) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType ) (( tom.engine.adt.tomtype.types.TomType )extendsType).getTlType() ) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.EmptyTargetLanguageType) ) {


				inheritedClass =  (( tom.engine.adt.tomtype.types.TomType )extendsType).getTomType() ;
				break matchblock;
			}}}}}

	}
	
	output.write(" is new " + inheritedClass);
	
	
	
	int args = names.size();
	
	if (args == 0) {
		output.writeln(" with null record;");
	} else {    
		output.writeln(" with");
		output.writeln(deep, "record");
		
		for(int i = 0 ; i < args ; i++) {
		  output.writeln(deep+1, names.get(i) + " : " + types.get(i) + ";");
		}
		output.writeln(deep, "end record;");
	}

	output.writeln(deep+1, "overriding function toString(t: " + tomName + ") return String;");
	output.writeln(deep+1, "overriding function getChildren(v: access " + tomName + ") return ObjectPtrArrayPtr;");
	output.writeln(deep+1, "overriding function setChildren(v: access " + tomName + " ; children: ObjectPtrArrayPtr) return VisitablePtr;");
	output.writeln(deep+1, "overriding function getChildCount(v: access " + tomName + ") return Integer;");
	output.writeln(deep+1, "overriding function getChildAt(v: access " + tomName + "; i : Integer) return VisitablePtr;");
	output.writeln(deep+1, "overriding function setChildAt(v: access " + tomName + "; i: in Integer; child: in VisitablePtr) return VisitablePtr;");
	output.writeln(deep+1, "overriding function visitLight(str:access " + tomName + "; v: ObjectPtr; intro: access Introspector'Class) return ObjectPtr;");
	
	output.writeln();
	
    
    output.write(deep, "function new" + tomName);
    if (args > 0) { output.write("("); }
    
    for(int i = 0 ; i < args ; i++) {
	    output.write(names.get(i) + ": " + types.get(i));
	    if(i+1<args) {
		    output.write("; ");
	    }
    }
    
    if (args > 0) { output.write(")"); }

    
    output.writeln(" return StrategyPtr is");
    output.write(deep+1, "newStrat : StrategyPtr := new " + tomName);
    
    if (args > 0) { output.write("\'("); }
        
    
    for(int i = 0 ; i < args ; i++) {
	    String param = names.get(i);
	    output.write(param + " => " + param);
	    if(i+1<args) {
		    output.write(", ");
	    }
    }
	if (args > 0) { output.write(", others => <>)"); }
	output.writeln(";");
	output.writeln(deep, "begin");
    
    output.write(deep+1,"make" + inheritedClass + "(" + inheritedClass + "\'Class(newStrat.all), ");
    generateBQTerm(0, superTerm,moduleName);
    output.writeln(");");
    
    output.writeln(deep+1, "return newStrat;");
    
    output.writeln(deep,"end new" + tomName + ";");
    
    output.writeln();
    
    
    output.writeln(deep, "function toString(t: " + tomName + ") return String is begin return \"" + tomName + "()\"; end;");
    
	output.writeln();

    
    for(int i = 0 ; i < args ; i++) {
      output.writeln(deep, "function get" + names.get(i) + "(s: " + tomName + ") return " + types.get(i) + " is");
      output.writeln(deep, "begin");
      output.writeln(deep+1,"return s." + names.get(i) + ";");
      output.writeln(deep,"end get" + names.get(i) + ";");
    }
    
    output.writeln();

    
    int stratChildCount = stratChild.size();

	output.writeln(deep, "function  getChildren(v: access " + tomName + ") return ObjectPtrArrayPtr is");
	output.writeln(deep+1, "stratChilds : ObjectPtrArrayPtr := new ObjectPtrArray(0..getChildCount(v));");
	output.writeln(deep, "begin");
	output.writeln(deep+1, "stratChilds(0) := ObjectPtr( getChildAt(" + inheritedClass + "(v.all)'Access,0) );");
	for(int i = 0; i < stratChildCount; i++) {
		int j = (stratChild.get(i)).intValue();
		output.writeln(deep+1, "stratChilds(" + (i+1) + ") := new Object'Class'(Object'Class( get" + names.get(j) + "(v) ));");
	}
	output.writeln(deep+1, "return stratChilds;");
	output.writeln(deep, "end getChildren;");
    
	output.writeln();

    output.writeln(deep, "function setChildren(v: access " + tomName + " ; children: ObjectPtrArrayPtr) return VisitablePtr is");
    output.writeln(deep, "begin");
    output.writeln(deep+1,"return setChildAt(" + inheritedClass + "(v.all)'Access, 0, VisitablePtr(children(0)));");
    for(int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep+1, names.get(j) + " := " + types.get(j) + "( children(" + (i+1) + ") );");
    }
    output.writeln(deep, "end setChildren;");
    
    output.writeln();

	output.writeln(deep, "function getChildCount(v: access " + tomName +") return Integer is");
	output.writeln(deep, "begin");
	output.writeln(deep+1, "return " + (stratChildCount + 1) + ";");
	output.writeln(deep, "end getChildCount;");
    
	output.writeln();

	
	output.writeln(deep, "function getChildAt(v: access " + tomName + "; i : Integer) return VisitablePtr is");
	output.writeln(deep+1, "IndexOutOfBoundsException: exception;");
	output.writeln(deep, "begin");
	output.writeln(deep+1, "if i = 0 then");
	output.writeln(deep+2, "return getChildAt(" + inheritedClass + "(v.all)'Access, 0);");
	for (int i = 0; i < stratChildCount; i++) {
		int j = (stratChild.get(i)).intValue();
		output.writeln(deep+1,"elsif i = " + (i+1) + " then");
		output.writeln(deep+2, "return VisitablePtr(v." + names.get(j) + ");");
	}
	output.writeln(deep+1,"else");
	output.writeln(deep+2,"raise IndexOutOfBoundsException;");
	output.writeln(deep+1,"end if;"); 
	output.writeln(deep, "end getChildAt;");

	output.writeln();

	
	output.writeln(deep, "function setChildAt(v: access " + tomName + "; i: in Integer; child: in VisitablePtr) return VisitablePtr is");
	output.writeln(deep+1, "IndexOutOfBoundsException: exception;");
	output.writeln(deep, "begin");
	output.writeln(deep+1, "if i = 0 then");
	output.writeln(deep+2, "return setChildAt(" + inheritedClass + "(v.all)'Access, 0, child);");
	for (int i = 0; i < stratChildCount; i++) {
		int j = (stratChild.get(i)).intValue();
		output.writeln(deep+1,"elsif i = " + (i+1) + " then");
		output.writeln(deep+2, "v." + names.get(j) + " := " + types.get(j) + "( child );");
		output.writeln(deep+2, "return VisitablePtr(v);");
	}
	output.writeln(deep+1,"else");
	output.writeln(deep+2,"raise IndexOutOfBoundsException;");
	output.writeln(deep+1,"end if;"); 
	output.writeln(deep, "end setChildAt;");
	
	output.writeln();
	generateDeclaration(deep,declaration, moduleName);

	output.writeln();
  }


  
  protected String genResolveIsSortCode(String varName, String resolveStringName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveIsSort) not yet supported in Ada");
    
  }

  protected String genResolveIsFsymCode(String tomName, String varname) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveIsFsym) not yet supported in Ada");
    
  }

  protected String genResolveGetSlotCode(String tomName, String varname, String slotName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveGetSlot) not yet supported in Ada");
    
  }
  
  protected void buildResolveClass(String wName, String tName, String extendsName, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveClass) not yet supported in Ada");
  }

  protected void buildResolveInverseLinks(int deep, String fileFrom, String fileTo, TomNameList resolveNameList, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveInverseLinks) not yet supported in Ada");
  }

  protected void genResolveDeclMake(String prefix, String funName, TomType returnType, BQTermList argList, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveMakeDecl) not yet supported in Ada");
  }

  protected String genResolveMakeCode(String funName, BQTermList argList) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveMakeCode) not yet supported in Ada");
  }

  protected void buildReferenceClass(int deep, String refname, RefClassTracelinkInstructionList refclassTInstructions, String  moduleName) {
    throw new TomRuntimeException("%transformation (ResolveReferenceClass) not yet supported in Ada");
  }

  protected void buildTracelink(int deep, String type, String name, Expression expr, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (Tracelink instruction) not yet supported in Ada");
  }

  protected void buildTracelinkPopulateResolve(int deep, String refClassName, TomNameList tracedLinks, BQTerm current, BQTerm link, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (TracelinkPopulateResolve instruction) not yet supported in Ada");
  }

  
  protected void buildResolve(int deep, BQTerm bqterm, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (Resolve2 instruction) not yet supported in Ada");
  }





}
