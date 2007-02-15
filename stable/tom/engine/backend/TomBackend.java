/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2007, INRIA
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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.*;

import tom.engine.TomMessage;
import tom.engine.TomBase;

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

import tom.engine.tools.*;
import tom.engine.exception.TomRuntimeException;
import tom.platform.OptionParser;
import tom.platform.PluginPlatformMessage;
import tom.platform.PlatformException;
import tom.platform.adt.platformoption.types.PlatformOptionList;

import tom.library.strategy.mutraveler.MuTraveler;
import tom.library.strategy.mutraveler.MuStrategy;
import jjtraveler.VisitFailure;


/**
 * The TomBackend "plugin".
 * Has to create the generator depending on OptionManager, create the output 
 * writer and generting the output code.
 */
public class TomBackend extends TomGenericPlugin {

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_terms_equal_int(int t1, int t2) {  return  (t1==t2)  ;} private static boolean tom_terms_equal_Instruction(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Declaration(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_DeclarationList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Expression(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Pattern(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PatternList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Option(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ConstraintList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_CompiledMatch( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t instanceof tom.engine.adt.tominstruction.types.instruction.CompiledMatch  ;}private static  tom.engine.adt.tominstruction.types.Instruction  tom_get_slot_CompiledMatch_AutomataInst( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getAutomataInst()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_CompiledMatch_Option( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getOption()  ;}private static boolean tom_is_fun_sym_TypedAction( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t instanceof tom.engine.adt.tominstruction.types.instruction.TypedAction  ;}private static  tom.engine.adt.tominstruction.types.Instruction  tom_get_slot_TypedAction_AstInstruction( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getAstInstruction()  ;}private static  tom.engine.adt.tomterm.types.Pattern  tom_get_slot_TypedAction_PositivePattern( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getPositivePattern()  ;}private static  tom.engine.adt.tomterm.types.PatternList  tom_get_slot_TypedAction_NegativePatternList( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getNegativePatternList()  ;}private static boolean tom_is_fun_sym_Type( tom.engine.adt.tomtype.types.TomType  t) {  return  t instanceof tom.engine.adt.tomtype.types.tomtype.Type  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Type_TomType( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getTomType()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Type_TlType( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getTlType()  ;}private static boolean tom_is_fun_sym_ASTTomType( tom.engine.adt.tomtype.types.TomType  t) {  return  t instanceof tom.engine.adt.tomtype.types.tomtype.ASTTomType  ;}private static  String  tom_get_slot_ASTTomType_String( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getString()  ;}private static boolean tom_is_fun_sym_TypeTermDecl( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t instanceof tom.engine.adt.tomdeclaration.types.declaration.TypeTermDecl  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_TypeTermDecl_AstName( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomdeclaration.types.DeclarationList  tom_get_slot_TypeTermDecl_Declarations( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getDeclarations()  ;}private static  tom.engine.adt.tomoption.types.Option  tom_get_slot_TypeTermDecl_OrgTrack( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getOrgTrack()  ;}private static boolean tom_is_fun_sym_Name( tom.engine.adt.tomname.types.TomName  t) {  return  t instanceof tom.engine.adt.tomname.types.tomname.Name  ;}private static  String  tom_get_slot_Name_String( tom.engine.adt.tomname.types.TomName  t) {  return  t.getString()  ;}private static boolean tom_is_fun_sym_GetHead( tom.engine.adt.tomexpression.types.Expression  t) {  return  t instanceof tom.engine.adt.tomexpression.types.expression.GetHead  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_GetHead_Opname( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getOpname()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_GetHead_Codomain( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getCodomain()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_GetHead_Variable( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getVariable()  ;}private static boolean tom_is_fun_sym_GetTail( tom.engine.adt.tomexpression.types.Expression  t) {  return  t instanceof tom.engine.adt.tomexpression.types.expression.GetTail  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_GetTail_Opname( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getOpname()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_GetTail_Variable( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getVariable()  ;}private static boolean tom_is_fun_sym_IsEmptyList( tom.engine.adt.tomexpression.types.Expression  t) {  return  t instanceof tom.engine.adt.tomexpression.types.expression.IsEmptyList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_IsEmptyList_Opname( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getOpname()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_IsEmptyList_Variable( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getVariable()  ;}private static boolean tom_is_fun_sym_IsEmptyArray( tom.engine.adt.tomexpression.types.Expression  t) {  return  t instanceof tom.engine.adt.tomexpression.types.expression.IsEmptyArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_IsEmptyArray_Opname( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getOpname()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_IsEmptyArray_Variable( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getVariable()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_IsEmptyArray_Index( tom.engine.adt.tomexpression.types.Expression  t) {  return  t.getIndex()  ;}private static boolean tom_is_fun_sym_TermAppl( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_TermAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_TermAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getNameList()  ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_TermAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getArgs()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_TermAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_ListAppl( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.ListAppl  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_ListAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_ListAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getNameList()  ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_ListAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getArgs()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_ListAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_RecordAppl( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_RecordAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_RecordAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getNameList()  ;}private static  tom.engine.adt.tomslot.types.SlotList  tom_get_slot_RecordAppl_Slots( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getSlots()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_RecordAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_BuildTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildTerm  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildTerm_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_BuildTerm_Args( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getArgs()  ;}private static  String  tom_get_slot_BuildTerm_ModuleName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getModuleName()  ;}private static boolean tom_is_fun_sym_BuildEmptyList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildEmptyList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildEmptyList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static boolean tom_is_fun_sym_BuildConsList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildConsList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildConsList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsList_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsList_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildAppendList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildAppendList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendList_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendList_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildEmptyArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildEmptyArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildEmptyArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  int  tom_get_slot_BuildEmptyArray_Size( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getSize()  ;}private static boolean tom_is_fun_sym_BuildConsArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildConsArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildConsArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsArray_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsArray_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildAppendArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildAppendArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendArray_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendArray_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;} /* Generated by TOM (version 2.5alpha): Do not edit this file */ /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_terms_equal_Strategy(Object t1, Object t2) {  return t1.equals(t2) ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_mu( tom.library.strategy.mutraveler.MuStrategy  var,  tom.library.strategy.mutraveler.MuStrategy  v) { return  new tom.library.strategy.mutraveler.Mu(var,v) ; }/* Generated by TOM (version 2.5alpha): Do not edit this file */private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Identity() { return  new tom.library.strategy.mutraveler.Identity() ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Fail() { return  new tom.library.strategy.mutraveler.Fail() ; }private static boolean tom_is_fun_sym_Sequence( tom.library.strategy.mutraveler.MuStrategy  t) {  return  (t instanceof tom.library.strategy.mutraveler.Sequence)  ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_empty_list_Sequence() { return  new tom.library.strategy.mutraveler.Identity() ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_cons_list_Sequence( tom.library.strategy.mutraveler.MuStrategy  head,  tom.library.strategy.mutraveler.MuStrategy  tail) { return  new tom.library.strategy.mutraveler.Sequence(head,tail) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_get_head_Sequence_Strategy( tom.library.strategy.mutraveler.MuStrategy  t) {  return  (tom.library.strategy.mutraveler.MuStrategy)t.getChildAt(tom.library.strategy.mutraveler.Sequence.FIRST)  ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_get_tail_Sequence_Strategy( tom.library.strategy.mutraveler.MuStrategy  t) {  return  (tom.library.strategy.mutraveler.MuStrategy)t.getChildAt(tom.library.strategy.mutraveler.Sequence.THEN)  ;}private static boolean tom_is_empty_Sequence_Strategy( tom.library.strategy.mutraveler.MuStrategy  t) {  return  t instanceof tom.library.strategy.mutraveler.Identity  ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_append_list_Sequence( tom.library.strategy.mutraveler.MuStrategy  l1,  tom.library.strategy.mutraveler.MuStrategy  l2) {    if(tom_is_empty_Sequence_Strategy(l1)) {     return l2;    } else if(tom_is_empty_Sequence_Strategy(l2)) {     return l1;    } else if(tom_is_empty_Sequence_Strategy(( tom.library.strategy.mutraveler.MuStrategy )tom_get_tail_Sequence_Strategy(l1))) {     return ( tom.library.strategy.mutraveler.MuStrategy )tom_cons_list_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_head_Sequence_Strategy(l1),l2);    } else {      return ( tom.library.strategy.mutraveler.MuStrategy )tom_cons_list_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_head_Sequence_Strategy(l1),tom_append_list_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_tail_Sequence_Strategy(l1),l2));    }   }  private static  tom.library.strategy.mutraveler.MuStrategy  tom_get_slice_Sequence( tom.library.strategy.mutraveler.MuStrategy  begin,  tom.library.strategy.mutraveler.MuStrategy  end) {    if(tom_terms_equal_Strategy(begin,end)) {      return ( tom.library.strategy.mutraveler.MuStrategy )tom_empty_list_Sequence();    } else {      return ( tom.library.strategy.mutraveler.MuStrategy )tom_cons_list_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_head_Sequence_Strategy(begin),( tom.library.strategy.mutraveler.MuStrategy )tom_get_slice_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_tail_Sequence_Strategy(begin),end));    }   }  private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Choice( tom.library.strategy.mutraveler.MuStrategy  first,  tom.library.strategy.mutraveler.MuStrategy  then) { return  new tom.library.strategy.mutraveler.Choice(first,then) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_All( tom.library.strategy.mutraveler.MuStrategy  v) { return  new tom.library.strategy.mutraveler.All(v) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_MuVar( String  name) { return  new tom.library.strategy.mutraveler.MuVar(name) ; } /* Generated by TOM (version 2.5alpha): Do not edit this file */private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Try( tom.library.strategy.mutraveler.MuStrategy  v) { return tom_make_Choice(v,tom_make_Identity()) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_TopDownCollect( tom.library.strategy.mutraveler.MuStrategy  v) { return tom_make_mu(tom_make_MuVar("_x"),tom_make_Try(tom_cons_list_Sequence(v,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_empty_list_Sequence())))) ; }   



  /** the tabulation starting value */
  private final static int defaultDeep = 2;

  /** the declared options string */
  public static final String DECLARED_OPTIONS = 
    "<options>" +
    "<boolean name='noOutput' altName=''  description='Do not generate code' value='false'/>" +
    "<boolean name='jCode'    altName='j' description='Generate Java code' value='true'/>" + 
    "<boolean name='cCode'    altName='c' description='Generate C code' value='false'/>" +
    "<boolean name='camlCode' altName=''  description='Generate Caml code' value='false'/>" + 
    "<boolean name='pCode'    altName=''  description='Generate Python code' value='false'/>" + 
    "</options>";

  /** the generated file name */
  private String generatedFileName = null;

  /** Constructor*/
  public TomBackend() {
    super("TomBackend");
  }

  /**
   *
   */
  public void run() {
    try {
      if(isActivated() == true) {
	TomAbstractGenerator generator = null;
	Writer writer;
	long startChrono = System.currentTimeMillis();
	try {
	  String encoding = getOptionStringValue("encoding");
	  writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getStreamManager().getOutputFile()),encoding));
	  OutputCode output = new OutputCode(writer, getOptionManager());
	  if(getOptionBooleanValue("noOutput")) {
	    throw new TomRuntimeException("Backend activated, but noOutput is set");
	  } else if(getOptionBooleanValue("cCode")) {
	    generator = new TomCGenerator(output, getOptionManager(), symbolTable());
	  } else if(getOptionBooleanValue("camlCode")) {
	    generator = new TomCamlGenerator(output, getOptionManager(), symbolTable());
	  } else if(getOptionBooleanValue("pCode")) {
	    generator = new TomPythonGenerator(output, getOptionManager(), symbolTable());
	  } else if(getOptionBooleanValue("jCode")) {
	    generator = new TomJavaGenerator(output, getOptionManager(), symbolTable());
	  } else {
	    throw new TomRuntimeException("no selected language for the Backend");
	  }

	  TomTerm pilCode = (TomTerm) getWorkingTerm();

	  markUsedConstructorDestructor(pilCode);

	  generator.generate(defaultDeep, generator.operatorsTogenerate(pilCode),TomBase.DEFAULT_MODULE_NAME);
	  // verbose
	  getLogger().log(Level.INFO, TomMessage.tomGenerationPhase.getMessage(),
	      new Integer((int)(System.currentTimeMillis()-startChrono)));
	  writer.close();
	} catch (IOException e) {
	  getLogger().log( Level.SEVERE, TomMessage.backendIOException.getMessage(),
	      new Object[]{getStreamManager().getOutputFile().getName(), e.getMessage()} );
	  return;
	} catch (Exception e) {
	  String fileName = getStreamManager().getInputFileName();
	  int line = -1;
	  TomMessage.error(getLogger(),fileName,line,TomMessage.exceptionMessage, new Object[]{fileName});
	  e.printStackTrace();
	  return;
	}
	// set the generated File Name
	try {
	  generatedFileName = getStreamManager().getOutputFile().getCanonicalPath();
	} catch (IOException e) {
	  System.out.println("IO Exception when computing generatedFileName");
	  e.printStackTrace();
	}

      } else {
	// backend is desactivated
	getLogger().log(Level.INFO,TomMessage.backendInactivated.getMessage());
      }
    } catch(PlatformException e) {
      getLogger().log( Level.SEVERE, PluginPlatformMessage.platformStopped.getMessage());
      return;
    }
  }

  public void optionChanged(String optionName, Object optionValue) {
    //System.out.println("optionChanged: " + optionName + " --> " + optionValue);
    if(optionName.equals("camlCode") && ((Boolean)optionValue).booleanValue() ) { 
      setOptionValue("jCode", Boolean.FALSE);        
      setOptionValue("cCode", Boolean.FALSE);        
      setOptionValue("pCode", Boolean.FALSE);        
    } else if(optionName.equals("cCode") && ((Boolean)optionValue).booleanValue() ) { 
      setOptionValue("jCode", Boolean.FALSE);        
      setOptionValue("camlCode", Boolean.FALSE);        
      setOptionValue("pCode", Boolean.FALSE);        
    } else if(optionName.equals("jCode") && ((Boolean)optionValue).booleanValue() ) { 
      setOptionValue("cCode", Boolean.FALSE);        
      setOptionValue("camlCode", Boolean.FALSE);        
      setOptionValue("pCode", Boolean.FALSE);        
    } else if(optionName.equals("pCode") && ((Boolean)optionValue).booleanValue() ) { 
      setOptionValue("cCode", Boolean.FALSE);        
      setOptionValue("camlCode", Boolean.FALSE);        
      setOptionValue("jCode", Boolean.FALSE);        
    }
  }


  /**
   * inherited from OptionOwner interface (plugin) 
   */
  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(TomBackend.DECLARED_OPTIONS);
  }

  private boolean isActivated() {
    return !getOptionBooleanValue("noOutput");
  }

  protected SymbolTable getSymbolTable(String moduleName) {
    //TODO//
    //Using of the moduleName
    ////////

    //System.out.println(symbolTable().toTerm());

    return symbolTable();
  }
  /**
   * inherited from plugin interface
   * returns the generated file name
   */
  public Object[] getArgs() {
    return new Object[]{generatedFileName};
  }

  







  private void markUsedConstructorDestructor(TomTerm pilCode) {
    Stack stack = new Stack();
    stack.push(TomBase.DEFAULT_MODULE_NAME);
    tom_make_mu(tom_make_MuVar("markStrategy"),tom_make_TopDownCollect(tom_make_Collector(tom_make_MuVar("markStrategy"),this,stack))).apply(pilCode);
  }

  private void setUsedSymbolConstructor(String moduleName, TomSymbol tomSymbol, MuStrategy markStrategy) {
    SymbolTable st = getSymbolTable(moduleName);
    if(!st.isUsedSymbolConstructor(tomSymbol) && !st.isUsedSymbolDestructor(tomSymbol)) {
      markStrategy.apply(tomSymbol);
    }
    getSymbolTable(moduleName).setUsedSymbolConstructor(tomSymbol);
  }

  private void setUsedSymbolDestructor(String moduleName, TomSymbol tomSymbol, MuStrategy markStrategy) {
    SymbolTable st = getSymbolTable(moduleName);
    if(!st.isUsedSymbolConstructor(tomSymbol) && !st.isUsedSymbolDestructor(tomSymbol)) {
      markStrategy.apply(tomSymbol);
    }
    getSymbolTable(moduleName).setUsedSymbolDestructor(tomSymbol);
  }

  private void setUsedTypeDefinition(String moduleName, String tomTypeName, MuStrategy markStrategy) {
    //SymbolTable st = getSymbolTable(moduleName);
    //if(!st.isUsedTypeDefinition(tomType)) {
    //  markStrategy.apply(tomType);
    //}
    getSymbolTable(moduleName).setUsedTypeDefinition(tomTypeName);
    //System.out.println("use type: " + tomTypeName);
  }

   private static class Collector  extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy   { private  tom.library.strategy.mutraveler.MuStrategy  markStrategy;  private  TomBackend  tb;  private  Stack  stack;  public Collector(  tom.library.strategy.mutraveler.MuStrategy  markStrategy ,   TomBackend  tb ,   Stack  stack ) { super(tom_make_Identity() ); this.markStrategy=markStrategy; this.tb=tb; this.stack=stack; } public  tom.library.strategy.mutraveler.MuStrategy  getmarkStrategy() { return markStrategy;} public  TomBackend  gettb() { return tb;} public  Stack  getstack() { return stack;} public int getChildCount() { return 2; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); case 1: return getmarkStrategy(); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); case 1: markStrategy = ( tom.library.strategy.mutraveler.MuStrategy )child; return this; default: throw new IndexOutOfBoundsException(); }} public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction(  tom.engine.adt.tominstruction.types.Instruction  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tominstruction.types.Instruction ) { { tom.engine.adt.tominstruction.types.Instruction  tom_match1_1=(( tom.engine.adt.tominstruction.types.Instruction )tom__arg); if ( ( tom_is_fun_sym_CompiledMatch(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tominstruction.types.Instruction  tom_inst=tom_get_slot_CompiledMatch_AutomataInst(tom_match1_1); { tom.engine.adt.tomoption.types.OptionList  tom_optionList=tom_get_slot_CompiledMatch_Option(tom_match1_1); if ( true ) {


	String moduleName = getModuleName(tom_optionList);
	/*
	 * push the modulename
	 * or the wrapping modulename if the current one
	 * (nested match for example) does not have one
	 */
	if(moduleName==null) {
	  try {
	    moduleName = (String) stack.peek();
	    stack.push(moduleName);
	    //System.out.println("push2: " + moduleName);
	  } catch (EmptyStackException e) {
	    System.out.println("No moduleName in stack");
	  }
	} else {
	  stack.push(moduleName);
	  //System.out.println("push1: " + moduleName);
	}
	//System.out.println("match -> moduleName = " + moduleName);
	markStrategy.visit(tom_inst);
	//String pop = (String) stack.pop();
	//System.out.println("pop: " + pop);
	tom_make_Fail().visit(null);
       } } } } if ( ( tom_is_fun_sym_TypedAction(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tominstruction.types.Instruction  tom_inst=tom_get_slot_TypedAction_AstInstruction(tom_match1_1); if ( true ) {


	markStrategy.visit(tom_inst);
	tom_make_Fail().visit(null);
       } } } } } return super.visit_Instruction(tom__arg) ;  } public  tom.engine.adt.tomexpression.types.Expression  visit_Expression(  tom.engine.adt.tomexpression.types.Expression  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomexpression.types.Expression ) { { tom.engine.adt.tomexpression.types.Expression  tom_match2_1=(( tom.engine.adt.tomexpression.types.Expression )tom__arg); {boolean tom_bool_match2_1= false ; { tom.engine.adt.tomname.types.TomName  tom_match2_1_Opname= null ; if (tom_is_fun_sym_IsEmptyList(tom_match2_1)) {tom_bool_match2_1= true ;tom_match2_1_Opname=tom_get_slot_IsEmptyList_Opname(tom_match2_1); } else { if (tom_is_fun_sym_IsEmptyArray(tom_match2_1)) {tom_bool_match2_1= true ;tom_match2_1_Opname=tom_get_slot_IsEmptyArray_Opname(tom_match2_1); } else { if (tom_is_fun_sym_GetHead(tom_match2_1)) {tom_bool_match2_1= true ;tom_match2_1_Opname=tom_get_slot_GetHead_Opname(tom_match2_1); } else { if (tom_is_fun_sym_GetTail(tom_match2_1)) {tom_bool_match2_1= true ;tom_match2_1_Opname=tom_get_slot_GetTail_Opname(tom_match2_1); } } } } if (tom_bool_match2_1) { if ( ( tom_is_fun_sym_Name(tom_match2_1_Opname) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tom_match2_1_Opname); if ( true ) {




	try {
	  // System.out.println("list check: " + `name);
	  String moduleName = (String) stack.peek();
	  //System.out.println("moduleName: " + moduleName);
	  TomSymbol tomSymbol = TomBase.getSymbolFromName(tom_name,tb.getSymbolTable(moduleName)); 
	  tb.setUsedSymbolConstructor(moduleName,tomSymbol,markStrategy);
	} catch (EmptyStackException e) {
	  System.out.println("No moduleName in stack");
	}

       } } } } } } } } return super.visit_Expression(tom__arg) ;  } public  tom.engine.adt.tomtype.types.TomType  visit_TomType(  tom.engine.adt.tomtype.types.TomType  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomtype.types.TomType ) { { tom.engine.adt.tomtype.types.TomType  tom_match3_1=(( tom.engine.adt.tomtype.types.TomType )tom__arg); if ( ( tom_is_fun_sym_Type(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomtype.types.TomType  tom_match3_1_TomType=tom_get_slot_Type_TomType(tom_match3_1); if ( ( tom_is_fun_sym_ASTTomType(tom_match3_1_TomType) ||  false  ) ) { { String  tom_type=tom_get_slot_ASTTomType_String(tom_match3_1_TomType); if ( true ) {




	try {
	  String moduleName = (String) stack.peek();
	  tb.setUsedTypeDefinition(moduleName,tom_type,markStrategy);
	} catch (EmptyStackException e) {
	  System.out.println("No moduleName in stack");
	}
       } } } } } } } return super.visit_TomType(tom__arg) ;  } public  tom.engine.adt.tomdeclaration.types.Declaration  visit_Declaration(  tom.engine.adt.tomdeclaration.types.Declaration  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomdeclaration.types.Declaration ) { { tom.engine.adt.tomdeclaration.types.Declaration  tom_match4_1=(( tom.engine.adt.tomdeclaration.types.Declaration )tom__arg); if ( ( tom_is_fun_sym_TypeTermDecl(tom_match4_1) ||  false  ) ) { if ( true ) {




	// should not search under a declaration
	//System.out.println("skip: " + `x);
	tom_make_Fail().visit(null);
       } } } } return super.visit_Declaration(tom__arg) ;  } public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm(  tom.engine.adt.tomterm.types.TomTerm  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match5_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg); {boolean tom_bool_match5_1= false ; { tom.engine.adt.tomname.types.TomNameList  tom_match5_1_NameList= null ; if (tom_is_fun_sym_TermAppl(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_NameList=tom_get_slot_TermAppl_NameList(tom_match5_1); } else { if (tom_is_fun_sym_RecordAppl(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_NameList=tom_get_slot_RecordAppl_NameList(tom_match5_1); } else { if (tom_is_fun_sym_ListAppl(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_NameList=tom_get_slot_ListAppl_NameList(tom_match5_1); } } } if (tom_bool_match5_1) { { tom.engine.adt.tomname.types.TomNameList  tom_nameList=tom_match5_1_NameList; if ( true ) {













    TomNameList l = tom_nameList;
    // System.out.println("dest " + `l);
    while(!l.isEmptyconcTomName()) {
      try {
	//System.out.println("op: " + l.getHead());
	String moduleName = (String) stack.peek();
	//System.out.println("moduleName: " + moduleName);
	TomSymbol tomSymbol = TomBase.getSymbolFromName(l.getHeadconcTomName().getString(),tb.getSymbolTable(moduleName)); 
	//System.out.println("mark: " + tomSymbol);
	tb.setUsedSymbolDestructor(moduleName,tomSymbol,markStrategy);
      } catch (EmptyStackException e) {
	System.out.println("No moduleName in stack");
      }
      l = l.getTailconcTomName();
    }
    /*
     * here we can fail because the subterms appear in isFsym tests
     * therefore, they are marked when traversing the compiledAutomata
     */
    tom_make_Fail().visit(null);
   } } } } } {boolean tom_bool_match5_1= false ; { tom.engine.adt.tomname.types.TomName  tom_match5_1_AstName= null ; if (tom_is_fun_sym_BuildTerm(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_AstName=tom_get_slot_BuildTerm_AstName(tom_match5_1); } else { if (tom_is_fun_sym_BuildEmptyList(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_AstName=tom_get_slot_BuildEmptyList_AstName(tom_match5_1); } else { if (tom_is_fun_sym_BuildEmptyArray(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_AstName=tom_get_slot_BuildEmptyArray_AstName(tom_match5_1); } } } if (tom_bool_match5_1) { if ( ( tom_is_fun_sym_Name(tom_match5_1_AstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tom_match5_1_AstName); if ( true ) {

    try {
      // System.out.println("build: " + `name);
      String moduleName = (String) stack.peek();
      //System.out.println("moduleName: " + moduleName);
      TomSymbol tomSymbol = TomBase.getSymbolFromName(tom_name,tb.getSymbolTable(moduleName)); 
      tb.setUsedSymbolConstructor(moduleName,tomSymbol,markStrategy);
    } catch (EmptyStackException e) {
      System.out.println("No moduleName in stack");
    }
   } } } } } } {boolean tom_bool_match5_1= false ; { tom.engine.adt.tomname.types.TomName  tom_match5_1_AstName= null ; if (tom_is_fun_sym_BuildConsList(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_AstName=tom_get_slot_BuildConsList_AstName(tom_match5_1); } else { if (tom_is_fun_sym_BuildAppendList(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_AstName=tom_get_slot_BuildAppendList_AstName(tom_match5_1); } else { if (tom_is_fun_sym_BuildConsArray(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_AstName=tom_get_slot_BuildConsArray_AstName(tom_match5_1); } else { if (tom_is_fun_sym_BuildAppendArray(tom_match5_1)) {tom_bool_match5_1= true ;tom_match5_1_AstName=tom_get_slot_BuildAppendArray_AstName(tom_match5_1); } } } } if (tom_bool_match5_1) { if ( ( tom_is_fun_sym_Name(tom_match5_1_AstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tom_match5_1_AstName); if ( true ) {

    try {
      // System.out.println("build: " + `name);
      String moduleName = (String) stack.peek();
      //System.out.println("moduleName: " + moduleName);
      TomSymbol tomSymbol = TomBase.getSymbolFromName(tom_name,tb.getSymbolTable(moduleName)); 
      tb.setUsedSymbolConstructor(moduleName,tomSymbol,markStrategy);
      /* XXX: Also mark the destructors as used, since some generated
       * functions will use them */
      tb.setUsedSymbolDestructor(moduleName,tomSymbol,markStrategy);
      // resolve uses in the symbol declaration
    } catch (EmptyStackException e) {
      System.out.println("No moduleName in stack");
    }
   } } } } } } } } return super.visit_TomTerm(tom__arg) ;  } }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Collector( tom.library.strategy.mutraveler.MuStrategy  t0,  TomBackend  t1,  Stack  t2) { return new Collector(t0,t1,t2); }




} // class TomBackend
