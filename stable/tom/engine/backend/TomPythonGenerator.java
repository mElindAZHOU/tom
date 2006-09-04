/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2006, INRIA
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
 *
 **/

package tom.engine.backend;

import java.io.IOException;

import tom.engine.exception.TomRuntimeException;

import tom.engine.adt.tomsignature.*;
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

public class TomPythonGenerator extends TomImperativeGenerator {

  // ------------------------------------------------------------
  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */private static boolean tom_terms_equal_int( int  t1,  int  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_TomType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Position(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TargetLanguage(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ConstraintList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_TLType( tom.engine.adt.tomtype.types.TomType  t) {  return  t instanceof tom.engine.adt.tomtype.types.tomtype.TLType  ;}private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_get_slot_TLType_Tl( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getTl()  ;}private static boolean tom_is_fun_sym_Type( tom.engine.adt.tomtype.types.TomType  t) {  return  t instanceof tom.engine.adt.tomtype.types.tomtype.Type  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Type_TomType( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getTomType()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Type_TlType( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getTlType()  ;}private static boolean tom_is_fun_sym_TextPosition( tom.engine.adt.tomsignature.types.Position  t) {  return  t instanceof tom.engine.adt.tomsignature.types.position.TextPosition  ;}private static  int  tom_get_slot_TextPosition_Line( tom.engine.adt.tomsignature.types.Position  t) {  return  t.getLine()  ;}private static  int  tom_get_slot_TextPosition_Column( tom.engine.adt.tomsignature.types.Position  t) {  return  t.getColumn()  ;}private static boolean tom_is_fun_sym_TL( tom.engine.adt.tomsignature.types.TargetLanguage  t) {  return  t instanceof tom.engine.adt.tomsignature.types.targetlanguage.TL  ;}private static  String  tom_get_slot_TL_Code( tom.engine.adt.tomsignature.types.TargetLanguage  t) {  return  t.getCode()  ;}private static  tom.engine.adt.tomsignature.types.Position  tom_get_slot_TL_Start( tom.engine.adt.tomsignature.types.TargetLanguage  t) {  return  t.getStart()  ;}private static  tom.engine.adt.tomsignature.types.Position  tom_get_slot_TL_End( tom.engine.adt.tomsignature.types.TargetLanguage  t) {  return  t.getEnd()  ;}private static boolean tom_is_fun_sym_ITL( tom.engine.adt.tomsignature.types.TargetLanguage  t) {  return  t instanceof tom.engine.adt.tomsignature.types.targetlanguage.ITL  ;}private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_make_ITL( String  t0) { return  tom.engine.adt.tomsignature.types.targetlanguage.ITL.make(t0); }private static  String  tom_get_slot_ITL_Code( tom.engine.adt.tomsignature.types.TargetLanguage  t) {  return  t.getCode()  ;}private static boolean tom_is_fun_sym_Name( tom.engine.adt.tomname.types.TomName  t) {  return  t instanceof tom.engine.adt.tomname.types.tomname.Name  ;}private static  String  tom_get_slot_Name_String( tom.engine.adt.tomname.types.TomName  t) {  return  t.getString()  ;}private static boolean tom_is_fun_sym_Variable( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.Variable  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_Variable_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_Variable_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Variable_AstType( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstType()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_Variable_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;} 
  // ------------------------------------------------------------


  public TomPythonGenerator(OutputCode output, OptionManager optionManager,
                                SymbolTable symbolTable) {
    super(output, optionManager, symbolTable);
  }

  protected void buildAssignVar(int deep, TomTerm var, OptionList list, Expression exp, String moduleName) throws IOException {
    //output.indent(deep);
    generate(deep,var,moduleName);
    output.write("=");
    generateExpression(deep,exp,moduleName);
  } 

  protected void buildCheckStamp(int deep, TomType type, TomTerm variable, String moduleName) throws IOException {
    if(((Boolean)optionManager.getOptionValue("stamp")).booleanValue()) {
      output.write("tom_check_stamp_" + getTomType(type) + "(");
      generate(deep,variable,moduleName);
      output.write(")");
    }
  }

  protected void buildComment(int deep, String text) throws IOException {
    //output.writeln("#" + text.replace("\n","\n#"));
  }
 
  protected void buildDoWhile(int deep, Instruction succes, Expression exp, String moduleName) throws IOException {
    generateInstruction(deep,succes,moduleName);
    buildWhileDo(deep,exp,succes,moduleName);
  }
  
  protected void buildExpAnd(int deep, Expression exp1, Expression exp2, String moduleName) throws IOException {
    output.write(" ( ");
    generateExpression(deep,exp1,moduleName);
    output.write(" and ");
    generateExpression(deep,exp2,moduleName);
    output.write(" ) ");
  }

  protected void buildExpOr(int deep, Expression exp1, Expression exp2, String moduleName) throws IOException {
    output.write(" ( ");
    generateExpression(deep,exp1,moduleName);
    output.write(" or ");
    generateExpression(deep,exp2,moduleName);
    output.write(" ) ");
  }

  protected void buildExpCast(int deep, TomType tlType, Expression exp, String moduleName) throws IOException {
    generateExpression(deep,exp,moduleName);
  }

  protected void buildExpNegation(int deep, Expression exp, String moduleName) throws IOException {
    output.write("not (");
    generateExpression(deep,exp,moduleName);
    output.write(")");
  }

  protected void buildIf(int deep, Expression exp, Instruction succes, String moduleName) throws IOException {
    output.write(deep,"if "); 
    generateExpression(deep,exp, moduleName); 
    output.writeln(":\n");
    generateInstruction(deep+1,succes, moduleName);
  }

  protected void buildIfWithFailure(int deep, Expression exp, Instruction succes, Instruction failure, String moduleName) throws IOException {
    output.write(deep,"if "); 
    generateExpression(deep,exp,moduleName); 
    output.writeln(":\n");
    generateInstruction(deep+1,succes,moduleName);
    output.writeln(deep,"else:\n");
    generateInstruction(deep+1,failure,moduleName);
  }

  protected void buildInstructionSequence(int deep, InstructionList instructionList, String moduleName) throws IOException {
    generateInstructionList(deep, instructionList, moduleName);
  }

  protected void buildLet(int deep, TomTerm var, OptionList optionList, TomType tlType, 
      Expression exp, Instruction body, String moduleName) throws IOException {
    buildAssignVar(deep,var,optionList,exp,moduleName);
    generateInstruction(deep,body,moduleName);
  }

  protected void buildLetRef(int deep, TomTerm var, OptionList optionList, TomType tlType, 
      Expression exp, Instruction body, String moduleName) throws IOException {
    buildLet(deep,var,optionList,tlType,exp,body, moduleName);
  }

  protected void buildLetAssign(int deep, TomTerm var, OptionList list, Expression exp, Instruction body, String moduleName) throws IOException {
    buildAssignVar(deep, var, list, exp, moduleName);
    generateInstruction(deep,body, moduleName);
  }

  protected void buildReturn(int deep, TomTerm exp, String moduleName) throws IOException {
    output.write(deep,"return ");
    generate(deep,exp,moduleName);
  }

  protected void buildSemiColon() throws IOException {
    output.write(";");
  }

  /* FIXME */
  protected void buildUnamedBlock(int deep, InstructionList instList, String moduleName) throws IOException {
    generateInstructionList(deep+1,instList, moduleName);
  }

  protected void buildWhileDo(int deep, Expression exp, Instruction succes, String moduleName) throws IOException {
    output.write(deep,"while ");
    generateExpression(deep,exp,moduleName);
    output.writeln(":");
    generateInstruction(deep+1,succes,moduleName);
  }

  protected void genDeclInstr(String returnType,
                         String declName,
                         String suffix,
                         String args[],
                         Instruction instr,
                         int deep, String moduleName) throws IOException {
    StringBuffer s = new StringBuffer();
    if(nodeclMode) {
      return;
    }
    s.append("def " + declName + "_" + suffix + "(");
    for(int i=0 ; i<args.length ; ) {
      s.append(args[i+1]);
      i+=2;
      if(i<args.length) {
        s.append(", ");
      }
    } 

    s.append("): ");
    output.write(deep,s);
    generateInstruction(deep+1,instr,moduleName);
    /*
     * path to add a semi-colon for 'void instruction'
     * This is the case of CheckStampDecl
     */
    if(instr.isTargetLanguageToInstruction()) {
      buildSemiColon();  
    }
    output.writeln();
  }


  protected void genDeclList(String name, String moduleName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(name);
    TomType listType = getSymbolCodomain(tomSymbol);
    TomType eltType = getSymbolDomain(tomSymbol).getHeadconcTomType();

    String s = "";
    if(nodeclMode) {
      return;
    }

    String tomType = getTomType(listType);
    String glType = getTLType(listType);
    String tlEltType = getTLType(eltType);

    String utype = glType;
    if(lazyMode) {
      utype = getTLType(getUniversalType());
    }
    
    String is_empty = "tom_is_empty_" + name + "_" + tomType;
    String term_equal = "tom_terms_equal_" + tomType;
    String make_insert = "tom_cons_list_" + name;
    String make_empty = "tom_empty_list_" + name;
    String get_head = "tom_get_head_" + name + "_" + tomType;
    String get_tail = "tom_get_tail_" + name + "_" + tomType;
    String get_slice = "tom_get_slice_" + name;

    s+= "def tom_append_list_" + name +  "(l1, l2):\n";
    s+= "   if " + is_empty + "(l1):\n";
    s+= "     return l2\n";  
    s+= "   elif " + is_empty + "(l2):\n";
    s+= "     return l1\n";  
    s+= "   elif " + is_empty + "(" + get_tail + "(l1)): \n";  
    s+= "     return " + make_insert + "(" + get_head + "(l1),l2)\n";
    s+= "   else:\n";  
    s+= "     return " + make_insert + "(" + get_head + "(l1),tom_append_list_" + name +  "(" + get_tail + "(l1),l2));\n";
    s+= "\n";
    
    s+= "def tom_get_slice_" + name + "(begin, end):\n"; 
    s+= "   if " + term_equal + "(begin,end):\n";
    s+= "      return " +  make_empty + "()\n";
    s+= "   else:\n";
    s+= "      return " +  make_insert + "(" + get_head + "(begin)," + 
      get_slice + "(" + get_tail + "(begin),end));\n";
    s+= "\n";
    //If necessary we remove \n code depending on pretty option
    TargetLanguage itl = ASTFactory.reworkTLCode(tom_make_ITL(s), prettyMode);
    output.write(itl.getCode()); 
  }
  

  protected void genDeclMake(String funName, TomType returnType, 
      TomList argList, Instruction instr, String moduleName) throws IOException {
    StringBuffer s = new StringBuffer();
    StringBuffer check = new StringBuffer();
    if( nodeclMode) {
      return;
    }

    s.append("def " + funName + "(");
    while(!argList.isEmptyconcTomTerm()) {
      TomTerm arg = argList.getHeadconcTomTerm();
matchBlock: {
               if(arg instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match1_1=(( tom.engine.adt.tomterm.types.TomTerm )arg); if ( ( tom_is_fun_sym_Variable(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match1_1_AstName=tom_get_slot_Variable_AstName(tom_match1_1); { tom.engine.adt.tomtype.types.TomType  tom_match1_1_AstType=tom_get_slot_Variable_AstType(tom_match1_1); if ( ( tom_is_fun_sym_Name(tom_match1_1_AstName) ||  false  ) ) { { String  tom_match1_1_AstName_String=tom_get_slot_Name_String(tom_match1_1_AstName); { String  tom_name=tom_match1_1_AstName_String; if ( ( tom_is_fun_sym_Type(tom_match1_1_AstType) ||  false  ) ) { { tom.engine.adt.tomtype.types.TomType  tom_match1_1_AstType_TomType=tom_get_slot_Type_TomType(tom_match1_1_AstType); { tom.engine.adt.tomtype.types.TomType  tom_match1_1_AstType_TlType=tom_get_slot_Type_TlType(tom_match1_1_AstType); { tom.engine.adt.tomtype.types.TomType  tom_tomType=tom_match1_1_AstType_TomType; if ( ( tom_is_fun_sym_TLType(tom_match1_1_AstType_TlType) ||  false  ) ) { if ( true ) {

                  s.append(tom_name);
                  if(((Boolean)optionManager.getOptionValue("stamp")).booleanValue()) {
                    check.append("tom_check_stamp_" + getTomType(tom_tomType) + "(" + tom_name+ ");\n");
                  }
                  break matchBlock;
                 } } } } } } } } } } } } if ( true ) {


                  System.out.println("genDeclMake: strange term: " + arg);
                  throw new TomRuntimeException("genDeclMake: strange term: " + arg);
                 } } }

            }
            argList = argList.getTailconcTomTerm();
            if(!argList.isEmptyconcTomTerm()) {
              s.append(", ");
            }
    }
    s.append("): ");
    s.append(check);

    output.write(s);
    output.write("return ");
    if(((Boolean)optionManager.getOptionValue("stamp")).booleanValue()) {
      output.write("tom_set_stamp_" + getTomType(returnType) + "(");
      generateInstruction(0,instr,moduleName);
      output.write(")");
    } else {
      generateInstruction(0,instr,moduleName);
    }
  }

  protected void buildCheckInstance(int deep, String typeName, TomType tlType, Expression exp, Instruction instruction, String moduleName) throws IOException {
    if(getSymbolTable(moduleName).isBuiltinType(typeName)) {
      generateInstruction(deep,instruction,moduleName);
    } else {
      output.write(deep,"if isinstance(");
      generateExpression(deep,exp,moduleName);
      output.writeln(getTLCode(tlType) + "):\n");
      generateInstruction(deep+1,instruction,moduleName);
    }
  }

  // FIXME
  protected void buildNamedBlock(int deep, String blockName, InstructionList instList, String moduleName) throws IOException {
    //output.writeln(blockName + ": {");
    generateInstructionList(deep+1,instList,moduleName);
    //output.writeln("}");
  }

  protected void buildExpTrue(int deep) throws IOException {
    output.write(" True ");
  }

  protected void buildExpFalse(int deep) throws IOException {
    output.write(" False ");
  }

  protected void buildExpBottom(int deep, TomType type, String moduleName) throws IOException {
    if ((getSymbolTable(moduleName).getIntType() == type)
        || (getSymbolTable(moduleName).getCharType() == type)
        || (getSymbolTable(moduleName).getLongType() == type)
        || (getSymbolTable(moduleName).getFloatType() == type)
        || (getSymbolTable(moduleName).getDoubleType() == type)) {
      output.write(" 0 ");
    } else if (getSymbolTable(moduleName).getBooleanType() == type) {
      output.write(" False ");
    } else if (getSymbolTable(moduleName).getStringType() == type) {
      output.write(" \"\" ");
    } else {
      output.write(" None ");
    }
  }

  protected void buildFunctionDef(int deep, String tomName, TomList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,this.modifier);
  }

  protected void buildMethodDef(int deep, String tomName, TomList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,"public ");
  }

  private void buildMethod(int deep, String tomName, TomList varList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName, String methodModifier) throws IOException {
    output.write(deep, "def " + tomName + "(");
    while(!varList.isEmptyconcTomTerm()) {
      TomTerm localVar = varList.getHeadconcTomTerm();
matchBlock: {
               if(localVar instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match2_1=(( tom.engine.adt.tomterm.types.TomTerm )localVar); if ( ( tom_is_fun_sym_Variable(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_v=tom_match2_1; { tom.engine.adt.tomtype.types.TomType  tom_match2_1_AstType=tom_get_slot_Variable_AstType(tom_match2_1); if ( true ) {

                  //output.write(deep,getTLType(`type2) + " ");
                  generate(deep,tom_v,moduleName);
                  break matchBlock;
                 } } } } if ( true ) {

                  System.out.println("MakeFunction: strange term: " + localVar);
                  throw new TomRuntimeException("MakeFunction: strange term: " + localVar);
                 } } }

            }
            varList = varList.getTailconcTomTerm();
            if(!varList.isEmptyconcTomTerm()) {
              output.write(deep,", ");

            }
    }
    output.writeln(deep,")");

    output.writeln(": ");
    generateInstruction(deep+1,instruction,moduleName);
  }

  protected void buildRef(int deep, TomTerm term, String moduleName) throws IOException {
    generate(deep,term,moduleName);
  }

  protected void genDecl(String returnType,
      String declName,
      String suffix,
      String args[],
      TargetLanguage tlCode,
      String moduleName) throws IOException {
    StringBuffer s = new StringBuffer();
    if(nodeclMode) {
      return;
    }
    s.append("def " + declName + "_" + suffix + "(");
    for(int i=0 ; i<args.length ; ) {
      s.append(args[i+1]);
      i+=2;
      if(i<args.length) {
        s.append(", ");
      }
    } 
    String returnValue = getSymbolTable(moduleName).isVoidType(returnType)?tlCode.getCode():"return " + tlCode.getCode();
    s.append("):\n " + returnValue + "\n");

     if(tlCode instanceof  tom.engine.adt.tomsignature.types.TargetLanguage ) { { tom.engine.adt.tomsignature.types.TargetLanguage  tom_match3_1=(( tom.engine.adt.tomsignature.types.TargetLanguage )tlCode); if ( ( tom_is_fun_sym_TL(tom_match3_1) ||  false  ) ) { { String  tom_match3_1_Code=tom_get_slot_TL_Code(tom_match3_1); { tom.engine.adt.tomsignature.types.Position  tom_match3_1_Start=tom_get_slot_TL_Start(tom_match3_1); { tom.engine.adt.tomsignature.types.Position  tom_match3_1_End=tom_get_slot_TL_End(tom_match3_1); if ( ( tom_is_fun_sym_TextPosition(tom_match3_1_Start) ||  false  ) ) { { int  tom_match3_1_Start_Line=tom_get_slot_TextPosition_Line(tom_match3_1_Start); { int  tom_startLine=tom_match3_1_Start_Line; if ( ( tom_is_fun_sym_TextPosition(tom_match3_1_End) ||  false  ) ) { { int  tom_match3_1_End_Line=tom_get_slot_TextPosition_Line(tom_match3_1_End); { int  tom_endLine=tom_match3_1_End_Line; if ( true ) {

        output.write(0,s, tom_startLine, tom_endLine- tom_startLine);
        return;
       } } } } } } } } } } } if ( ( tom_is_fun_sym_ITL(tom_match3_1) ||  false  ) ) { { String  tom_match3_1_Code=tom_get_slot_ITL_Code(tom_match3_1); if ( true ) {

  // pas de \n donc pas besoin de reworkTL
        output.write(s);
        return;
       } } } } }


  }


}
