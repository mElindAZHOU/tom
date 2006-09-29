/* Generated by TOM (version 2.4rc0): Do not edit this file *//*
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

public abstract class TomImperativeGenerator extends TomGenericGenerator {

  protected String modifier = "";
  protected boolean nodeclMode;


  public TomImperativeGenerator(OutputCode output, OptionManager optionManager,
                                SymbolTable symbolTable) {
    super(output, optionManager, symbolTable);
    nodeclMode = ((Boolean)optionManager.getOptionValue("noDeclaration")).booleanValue();
  }

  // ------------------------------------------------------------
  /* Generated by TOM (version 2.4rc0): Do not edit this file *//* Generated by TOM (version 2.4rc0): Do not edit this file *//* Generated by TOM (version 2.4rc0): Do not edit this file *//* Generated by TOM (version 2.4rc0): Do not edit this file */private static boolean tom_terms_equal_boolean(boolean t1, boolean t2) {  return  (t1==t2)  ;}  private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4rc0): Do not edit this file */private static boolean tom_terms_equal_int(int t1, int t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4rc0): Do not edit this file */ /* Generated by TOM (version 2.4rc0): Do not edit this file */ /* Generated by TOM (version 2.4rc0): Do not edit this file */ /* Generated by TOM (version 2.4rc0): Do not edit this file */ /* Generated by TOM (version 2.4rc0): Do not edit this file */ private static boolean tom_terms_equal_TargetLanguage(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_make_ITL( String  t0) { return  tom.engine.adt.tomsignature.types.targetlanguage.ITL.make(t0); }private static boolean tom_is_fun_sym_EmptyName( tom.engine.adt.tomname.types.TomName  t) {  return  t instanceof tom.engine.adt.tomname.types.tomname.EmptyName  ;}private static boolean tom_is_fun_sym_Name( tom.engine.adt.tomname.types.TomName  t) {  return  t instanceof tom.engine.adt.tomname.types.tomname.Name  ;}private static  String  tom_get_slot_Name_String( tom.engine.adt.tomname.types.TomName  t) {  return  t.getString()  ;}private static boolean tom_is_fun_sym_BuildAppendArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildAppendArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendArray_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendArray_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildConsArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildConsArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildConsArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsArray_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsArray_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildEmptyArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildEmptyArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildEmptyArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  int  tom_get_slot_BuildEmptyArray_Size( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getSize()  ;}private static boolean tom_is_fun_sym_BuildAppendList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildAppendList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendList_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendList_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildConsList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildConsList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildConsList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsList_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsList_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildEmptyList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildEmptyList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildEmptyList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;} 
  // ------------------------------------------------------------

  /*
   * the method implementations are here common to C, Java, caml and python
   */


  protected void buildExpGetHead(int deep, TomName opNameAST, TomType domain, TomType codomain, TomTerm var, String moduleName) throws IOException {
     if(opNameAST instanceof  tom.engine.adt.tomname.types.TomName ) { { tom.engine.adt.tomname.types.TomName  tom_match1_1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tom_match1_1) ||  false  ) ) { {boolean tom_match1_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match1_tom_anti_constraints_status,  true )) { if ( true ) {
 output.write("tom_get_head_" + getTomType(domain) + "(");  } } } } if ( ( tom_is_fun_sym_Name(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_String=tom_get_slot_Name_String(tom_match1_1); { String  tom_opName=tom_match1_1_String; {boolean tom_match1_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match1_tom_anti_constraints_status,  true )) { if ( true ) {
 output.write("tom_get_head_" + tom_opName+ "_" + getTomType(domain) + "(");  } } } } } } } }

    generate(deep,var,moduleName);
    output.write(")");
  }
  
  protected void buildExpGetElement(int deep, TomName opNameAST, TomType domain, TomType codomain, TomTerm varName, TomTerm varIndex, String moduleName) throws IOException {
     if(opNameAST instanceof  tom.engine.adt.tomname.types.TomName ) { { tom.engine.adt.tomname.types.TomName  tom_match2_1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tom_match2_1) ||  false  ) ) { {boolean tom_match2_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match2_tom_anti_constraints_status,  true )) { if ( true ) {
 output.write("tom_get_element_" + getTomType(domain) + "(");  } } } } if ( ( tom_is_fun_sym_Name(tom_match2_1) ||  false  ) ) { { String  tom_match2_1_String=tom_get_slot_Name_String(tom_match2_1); { String  tom_opName=tom_match2_1_String; {boolean tom_match2_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match2_tom_anti_constraints_status,  true )) { if ( true ) {
 output.write("tom_get_element_" + tom_opName+ "_" + getTomType(domain) + "(");  } } } } } } } }


    generate(deep,varName,moduleName);
    output.write(",");
    generate(deep,varIndex,moduleName);
    output.write(")");
  }
 
  protected void buildListOrArray(int deep, TomTerm list, String moduleName) throws IOException {
     if(list instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1=(( tom.engine.adt.tomterm.types.TomTerm )list); if ( ( tom_is_fun_sym_BuildEmptyList(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match3_1_AstName=tom_get_slot_BuildEmptyList_AstName(tom_match3_1); if ( ( tom_is_fun_sym_Name(tom_match3_1_AstName) ||  false  ) ) { { String  tom_match3_1_AstName_String=tom_get_slot_Name_String(tom_match3_1_AstName); { String  tom_name=tom_match3_1_AstName_String; {boolean tom_match3_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match3_tom_anti_constraints_status,  true )) { if ( true ) {

        output.write("tom_empty_list_" + tom_name+ "()");
        return;
       } } } } } } } } if ( ( tom_is_fun_sym_BuildConsList(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match3_1_AstName=tom_get_slot_BuildConsList_AstName(tom_match3_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1_HeadTerm=tom_get_slot_BuildConsList_HeadTerm(tom_match3_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1_TailTerm=tom_get_slot_BuildConsList_TailTerm(tom_match3_1); if ( ( tom_is_fun_sym_Name(tom_match3_1_AstName) ||  false  ) ) { { String  tom_match3_1_AstName_String=tom_get_slot_Name_String(tom_match3_1_AstName); { String  tom_name=tom_match3_1_AstName_String; { tom.engine.adt.tomterm.types.TomTerm  tom_headTerm=tom_match3_1_HeadTerm; { tom.engine.adt.tomterm.types.TomTerm  tom_tailTerm=tom_match3_1_TailTerm; {boolean tom_match3_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match3_tom_anti_constraints_status,  true )) { if ( true ) {


        output.write("tom_cons_list_" + tom_name+ "(");
        generate(deep,tom_headTerm,moduleName);
        output.write(",");
        generate(deep,tom_tailTerm,moduleName);
        output.write(")");
        return;
       } } } } } } } } } } } } if ( ( tom_is_fun_sym_BuildAppendList(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match3_1_AstName=tom_get_slot_BuildAppendList_AstName(tom_match3_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1_HeadTerm=tom_get_slot_BuildAppendList_HeadTerm(tom_match3_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1_TailTerm=tom_get_slot_BuildAppendList_TailTerm(tom_match3_1); if ( ( tom_is_fun_sym_Name(tom_match3_1_AstName) ||  false  ) ) { { String  tom_match3_1_AstName_String=tom_get_slot_Name_String(tom_match3_1_AstName); { String  tom_name=tom_match3_1_AstName_String; { tom.engine.adt.tomterm.types.TomTerm  tom_headTerm=tom_match3_1_HeadTerm; { tom.engine.adt.tomterm.types.TomTerm  tom_tailTerm=tom_match3_1_TailTerm; {boolean tom_match3_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match3_tom_anti_constraints_status,  true )) { if ( true ) {


        output.write("tom_append_list_" + tom_name+ "(");
        generate(deep,tom_headTerm,moduleName);
        output.write(",");
        generate(deep,tom_tailTerm,moduleName);
        output.write(")");
        return;
       } } } } } } } } } } } } if ( ( tom_is_fun_sym_BuildEmptyArray(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match3_1_AstName=tom_get_slot_BuildEmptyArray_AstName(tom_match3_1); { int  tom_match3_1_Size=tom_get_slot_BuildEmptyArray_Size(tom_match3_1); if ( ( tom_is_fun_sym_Name(tom_match3_1_AstName) ||  false  ) ) { { String  tom_match3_1_AstName_String=tom_get_slot_Name_String(tom_match3_1_AstName); { String  tom_name=tom_match3_1_AstName_String; { int  tom_size=tom_match3_1_Size; {boolean tom_match3_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match3_tom_anti_constraints_status,  true )) { if ( true ) {


        output.write("tom_empty_array_" + tom_name+ "(" + tom_size+ ")");
        return;
       } } } } } } } } } } if ( ( tom_is_fun_sym_BuildConsArray(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match3_1_AstName=tom_get_slot_BuildConsArray_AstName(tom_match3_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1_HeadTerm=tom_get_slot_BuildConsArray_HeadTerm(tom_match3_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1_TailTerm=tom_get_slot_BuildConsArray_TailTerm(tom_match3_1); if ( ( tom_is_fun_sym_Name(tom_match3_1_AstName) ||  false  ) ) { { String  tom_match3_1_AstName_String=tom_get_slot_Name_String(tom_match3_1_AstName); { String  tom_name=tom_match3_1_AstName_String; { tom.engine.adt.tomterm.types.TomTerm  tom_headTerm=tom_match3_1_HeadTerm; { tom.engine.adt.tomterm.types.TomTerm  tom_tailTerm=tom_match3_1_TailTerm; {boolean tom_match3_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match3_tom_anti_constraints_status,  true )) { if ( true ) {


        output.write("tom_cons_array_" + tom_name+ "(");
        generate(deep,tom_headTerm,moduleName);
        output.write(",");
        generate(deep,tom_tailTerm,moduleName);
        output.write(")");
        return;
       } } } } } } } } } } } } if ( ( tom_is_fun_sym_BuildAppendArray(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match3_1_AstName=tom_get_slot_BuildAppendArray_AstName(tom_match3_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1_HeadTerm=tom_get_slot_BuildAppendArray_HeadTerm(tom_match3_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1_TailTerm=tom_get_slot_BuildAppendArray_TailTerm(tom_match3_1); if ( ( tom_is_fun_sym_Name(tom_match3_1_AstName) ||  false  ) ) { { String  tom_match3_1_AstName_String=tom_get_slot_Name_String(tom_match3_1_AstName); { String  tom_name=tom_match3_1_AstName_String; { tom.engine.adt.tomterm.types.TomTerm  tom_headTerm=tom_match3_1_HeadTerm; { tom.engine.adt.tomterm.types.TomTerm  tom_tailTerm=tom_match3_1_TailTerm; {boolean tom_match3_tom_anti_constraints_status= true ; if (tom_terms_equal_boolean(tom_match3_tom_anti_constraints_status,  true )) { if ( true ) {


        output.write("tom_append_array_" + tom_name+ "(");
        generate(deep,tom_headTerm,moduleName);
        output.write(",");
        generate(deep,tom_tailTerm,moduleName);
        output.write(")");
        return;
       } } } } } } } } } } } } } }

  }

  protected void buildFunctionCall(int deep, String name, TomList argList, String moduleName) throws IOException {
    output.write(name);
    output.writeOpenBrace();
    while(!argList.isEmptyconcTomTerm()) {
      generate(deep,argList.getHeadconcTomTerm(),moduleName);
      argList = argList.getTailconcTomTerm();
      if(!argList.isEmptyconcTomTerm()) {
        output.writeComa();
      }
    }
    output.writeCloseBrace();
  }


  protected void genDeclArray(String name, String moduleName) throws IOException {
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
      utype =  getTLType(getUniversalType());
    }
    
    String listCast = "(" + glType + ")";
    String eltCast = "(" + getTLType(eltType) + ")";
    String make_empty = listCast + "tom_empty_array_" + name;
    String make_insert = listCast + "tom_cons_array_" + name;
    String get_element = eltCast + "tom_get_element_" + name +"_" + tomType;
    String get_size = "tom_get_size_" + name +"_" + tomType;
    
    s = modifier + utype + " tom_get_slice_" + name +  "(" + utype + " subject, int begin, int end) {\n";
    s+= "   " + glType + " result = " + make_empty + "(end - begin);\n";
    s+= "    while( begin != end ) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(subject, begin),result);\n";
    s+= "      begin++;\n";
    s+= "     }\n";
    s+= "    return result;\n";
    s+= "  }\n";
    s+= "\n";
    
    s+= modifier + utype + " tom_append_array_" + name +  "(" + utype + " l2, " + utype + " l1) {\n";
    s+= "    int size1 = " + get_size + "(l1);\n";
    s+= "    int size2 = " + get_size + "(l2);\n";
    s+= "    int index;\n";
    s+= "   " + glType + " result = " + make_empty + "(size1+size2);\n";

    s+= "    index=size1;\n";
    s+= "    while(index > 0) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(l1,(size1-index)),result);\n";
    s+= "      index--;\n";
    s+= "    }\n";

    s+= "    index=size2;\n";
    s+= "    while(index > 0) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(l2,(size2-index)),result);\n";
    s+= "      index--;\n";
    s+= "    }\n";
   
    s+= "    return result;\n";
    s+= "  }\n";

    //If necessary we remove \n code depending on pretty option
    TargetLanguage itl = ASTFactory.reworkTLCode(tom_make_ITL(s), prettyMode);
    output.write(itl.getCode()); 
  }

  
} // class TomImperativeGenerator
