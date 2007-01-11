/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
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
 * Julien Guyon
 *
 **/

package tom.engine.checker;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.engine.TomMessage;
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

import tom.engine.tools.TomGenericPlugin;
import tom.platform.PlatformLogRecord;


abstract public class TomChecker extends TomGenericPlugin {
  
    // ------------------------------------------------------------
  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_terms_equal_int(int t1, int t2) {  return  (t1==t2)  ;} private static boolean tom_terms_equal_TomType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Option(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ConstraintList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_Name( tom.engine.adt.tomname.types.TomName  t) {  return  t instanceof tom.engine.adt.tomname.types.tomname.Name  ;}private static  String  tom_get_slot_Name_String( tom.engine.adt.tomname.types.TomName  t) {  return  t.getString()  ;}private static boolean tom_is_fun_sym_TermAppl( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_TermAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_TermAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getNameList()  ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_TermAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getArgs()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_TermAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_RecordAppl( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_RecordAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_RecordAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getNameList()  ;}private static  tom.engine.adt.tomslot.types.SlotList  tom_get_slot_RecordAppl_Slots( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getSlots()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_RecordAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_XMLAppl( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.XMLAppl  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_XMLAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_XMLAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getNameList()  ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_XMLAppl_AttrList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAttrList()  ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_XMLAppl_ChildList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getChildList()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_XMLAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_Variable( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.Variable  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_Variable_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_Variable_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Variable_AstType( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstType()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_Variable_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_VariableStar( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_VariableStar_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_VariableStar_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_VariableStar_AstType( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstType()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_VariableStar_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_UnamedVariable( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_UnamedVariable_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_UnamedVariable_AstType( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstType()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_UnamedVariable_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_UnamedVariableStar( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_UnamedVariableStar_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_UnamedVariableStar_AstType( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstType()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_UnamedVariableStar_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_OriginTracking( tom.engine.adt.tomoption.types.Option  t) {  return  t instanceof tom.engine.adt.tomoption.types.option.OriginTracking  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_OriginTracking_AstName( tom.engine.adt.tomoption.types.Option  t) {  return  t.getAstName()  ;}private static  int  tom_get_slot_OriginTracking_Line( tom.engine.adt.tomoption.types.Option  t) {  return  t.getLine()  ;}private static  String  tom_get_slot_OriginTracking_FileName( tom.engine.adt.tomoption.types.Option  t) {  return  t.getFileName()  ;}private static boolean tom_is_fun_sym_concTomName( tom.engine.adt.tomname.types.TomNameList  t) {  return  t instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName || t instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_empty_list_concTomName() { return  tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ; }private static  tom.engine.adt.tomname.types.TomNameList  tom_cons_list_concTomName( tom.engine.adt.tomname.types.TomName  e,  tom.engine.adt.tomname.types.TomNameList  l) { return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make(e,l) ; }private static  tom.engine.adt.tomname.types.TomName  tom_get_head_concTomName_TomNameList( tom.engine.adt.tomname.types.TomNameList  l) {  return  l.getHeadconcTomName()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_tail_concTomName_TomNameList( tom.engine.adt.tomname.types.TomNameList  l) {  return  l.getTailconcTomName()  ;}private static boolean tom_is_empty_concTomName_TomNameList( tom.engine.adt.tomname.types.TomNameList  l) {  return  l.isEmptyconcTomName()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList  l1,  tom.engine.adt.tomname.types.TomNameList  l2) {    if(tom_is_empty_concTomName_TomNameList(l1)) {     return l2;    } else if(tom_is_empty_concTomName_TomNameList(l2)) {     return l1;    } else if(tom_is_empty_concTomName_TomNameList(( tom.engine.adt.tomname.types.TomNameList )tom_get_tail_concTomName_TomNameList(l1))) {     return ( tom.engine.adt.tomname.types.TomNameList )tom_cons_list_concTomName(( tom.engine.adt.tomname.types.TomName )tom_get_head_concTomName_TomNameList(l1),l2);    } else {      return ( tom.engine.adt.tomname.types.TomNameList )tom_cons_list_concTomName(( tom.engine.adt.tomname.types.TomName )tom_get_head_concTomName_TomNameList(l1),tom_append_list_concTomName(( tom.engine.adt.tomname.types.TomNameList )tom_get_tail_concTomName_TomNameList(l1),l2));    }   }  private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end) {    if(tom_terms_equal_TomNameList(begin,end)) {      return ( tom.engine.adt.tomname.types.TomNameList )tom_empty_list_concTomName();    } else {      return ( tom.engine.adt.tomname.types.TomNameList )tom_cons_list_concTomName(( tom.engine.adt.tomname.types.TomName )tom_get_head_concTomName_TomNameList(begin),( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName(( tom.engine.adt.tomname.types.TomNameList )tom_get_tail_concTomName_TomNameList(begin),end));    }   }  private static boolean tom_is_fun_sym_concOption( tom.engine.adt.tomoption.types.OptionList  t) {  return  t instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption || t instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_empty_list_concOption() { return  tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ; }private static  tom.engine.adt.tomoption.types.OptionList  tom_cons_list_concOption( tom.engine.adt.tomoption.types.Option  e,  tom.engine.adt.tomoption.types.OptionList  l) { return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make(e,l) ; }private static  tom.engine.adt.tomoption.types.Option  tom_get_head_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {  return  l.getHeadconcOption()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_tail_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {  return  l.getTailconcOption()  ;}private static boolean tom_is_empty_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {  return  l.isEmptyconcOption()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList  l1,  tom.engine.adt.tomoption.types.OptionList  l2) {    if(tom_is_empty_concOption_OptionList(l1)) {     return l2;    } else if(tom_is_empty_concOption_OptionList(l2)) {     return l1;    } else if(tom_is_empty_concOption_OptionList(( tom.engine.adt.tomoption.types.OptionList )tom_get_tail_concOption_OptionList(l1))) {     return ( tom.engine.adt.tomoption.types.OptionList )tom_cons_list_concOption(( tom.engine.adt.tomoption.types.Option )tom_get_head_concOption_OptionList(l1),l2);    } else {      return ( tom.engine.adt.tomoption.types.OptionList )tom_cons_list_concOption(( tom.engine.adt.tomoption.types.Option )tom_get_head_concOption_OptionList(l1),tom_append_list_concOption(( tom.engine.adt.tomoption.types.OptionList )tom_get_tail_concOption_OptionList(l1),l2));    }   }  private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end) {    if(tom_terms_equal_OptionList(begin,end)) {      return ( tom.engine.adt.tomoption.types.OptionList )tom_empty_list_concOption();    } else {      return ( tom.engine.adt.tomoption.types.OptionList )tom_cons_list_concOption(( tom.engine.adt.tomoption.types.Option )tom_get_head_concOption_OptionList(begin),( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption(( tom.engine.adt.tomoption.types.OptionList )tom_get_tail_concOption_OptionList(begin),end));    }   }   
    // ------------------------------------------------------------
  
    // Different kind of structures
  protected final static int TERM_APPL               = 0;
  protected final static int UNAMED_APPL             = 1;
  protected final static int APPL_DISJUNCTION        = 2;
  protected final static int RECORD_APPL             = 3;
  protected final static int RECORD_APPL_DISJUNCTION = 4;
  protected final static int XML_APPL                = 5;
  protected final static int VARIABLE_STAR           = 6;
  protected final static int UNAMED_VARIABLE_STAR    = 7;
  protected final static int UNAMED_VARIABLE         = 8;
  protected final static int VARIABLE                = 9;
  
  protected boolean strictType = false;
  protected Option currentTomStructureOrgTrack;
    
  public TomChecker(String name) {
    super(name);
  }

  protected void reinit() {
    currentTomStructureOrgTrack = null;
  }
 
  public int getClass(TomTerm term) {
     if(term instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match1_1=(( tom.engine.adt.tomterm.types.TomTerm )term); if ( ( tom_is_fun_sym_TermAppl(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList=tom_get_slot_TermAppl_NameList(tom_match1_1); if ( ( tom_is_fun_sym_concTomName(tom_match1_1_NameList) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_list1=tom_match1_1_NameList; { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_end1=tom_match1_1_NameList_list1; { if (!(tom_is_empty_concTomName_TomNameList(tom_match1_1_NameList_list1))) { { tom.engine.adt.tomname.types.TomName  tom_match1_1_NameList_1=tom_get_head_concTomName_TomNameList(tom_match1_1_NameList_list1);tom_match1_1_NameList_list1=tom_get_tail_concTomName_TomNameList(tom_match1_1_NameList_list1); if ( ( tom_is_fun_sym_Name(tom_match1_1_NameList_1) ||  false  ) ) { if ( ( tom_terms_equal_String("", tom_get_slot_Name_String(tom_match1_1_NameList_1)) ||  false  ) ) { if (tom_is_empty_concTomName_TomNameList(tom_match1_1_NameList_list1)) { if ( true ) {
 return UNAMED_APPL; } } } } } }tom_match1_1_NameList_list1=tom_match1_1_NameList_end1; } } } } } } if ( ( tom_is_fun_sym_TermAppl(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList=tom_get_slot_TermAppl_NameList(tom_match1_1); if ( ( tom_is_fun_sym_concTomName(tom_match1_1_NameList) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_list1=tom_match1_1_NameList; { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_end1=tom_match1_1_NameList_list1; { if (!(tom_is_empty_concTomName_TomNameList(tom_match1_1_NameList_list1))) { { tom.engine.adt.tomname.types.TomName  tom_match1_1_NameList_1=tom_get_head_concTomName_TomNameList(tom_match1_1_NameList_list1);tom_match1_1_NameList_list1=tom_get_tail_concTomName_TomNameList(tom_match1_1_NameList_list1); if ( ( tom_is_fun_sym_Name(tom_match1_1_NameList_1) ||  false  ) ) { if (tom_is_empty_concTomName_TomNameList(tom_match1_1_NameList_list1)) { if ( true ) {
 return TERM_APPL; } } } } }tom_match1_1_NameList_list1=tom_match1_1_NameList_end1; } } } } } } if ( ( tom_is_fun_sym_TermAppl(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList=tom_get_slot_TermAppl_NameList(tom_match1_1); if ( ( tom_is_fun_sym_concTomName(tom_match1_1_NameList) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_list1=tom_match1_1_NameList; { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_end1=tom_match1_1_NameList_list1; { if (!(tom_is_empty_concTomName_TomNameList(tom_match1_1_NameList_list1))) { { tom.engine.adt.tomname.types.TomName  tom_match1_1_NameList_1=tom_get_head_concTomName_TomNameList(tom_match1_1_NameList_list1);tom_match1_1_NameList_list1=tom_get_tail_concTomName_TomNameList(tom_match1_1_NameList_list1); if ( ( tom_is_fun_sym_Name(tom_match1_1_NameList_1) ||  false  ) ) { if ( true ) {
 return APPL_DISJUNCTION; } } } }tom_match1_1_NameList_list1=tom_match1_1_NameList_end1; } } } } } } if ( ( tom_is_fun_sym_RecordAppl(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList=tom_get_slot_RecordAppl_NameList(tom_match1_1); if ( ( tom_is_fun_sym_concTomName(tom_match1_1_NameList) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_list1=tom_match1_1_NameList; { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_end1=tom_match1_1_NameList_list1; { if (!(tom_is_empty_concTomName_TomNameList(tom_match1_1_NameList_list1))) { { tom.engine.adt.tomname.types.TomName  tom_match1_1_NameList_1=tom_get_head_concTomName_TomNameList(tom_match1_1_NameList_list1);tom_match1_1_NameList_list1=tom_get_tail_concTomName_TomNameList(tom_match1_1_NameList_list1); if ( ( tom_is_fun_sym_Name(tom_match1_1_NameList_1) ||  false  ) ) { if (tom_is_empty_concTomName_TomNameList(tom_match1_1_NameList_list1)) { if ( true ) {
 return RECORD_APPL; } } } } }tom_match1_1_NameList_list1=tom_match1_1_NameList_end1; } } } } } } if ( ( tom_is_fun_sym_RecordAppl(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList=tom_get_slot_RecordAppl_NameList(tom_match1_1); if ( ( tom_is_fun_sym_concTomName(tom_match1_1_NameList) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_list1=tom_match1_1_NameList; { tom.engine.adt.tomname.types.TomNameList  tom_match1_1_NameList_end1=tom_match1_1_NameList_list1; { if (!(tom_is_empty_concTomName_TomNameList(tom_match1_1_NameList_list1))) { { tom.engine.adt.tomname.types.TomName  tom_match1_1_NameList_1=tom_get_head_concTomName_TomNameList(tom_match1_1_NameList_list1);tom_match1_1_NameList_list1=tom_get_tail_concTomName_TomNameList(tom_match1_1_NameList_list1); if ( ( tom_is_fun_sym_Name(tom_match1_1_NameList_1) ||  false  ) ) { if ( true ) {
 return RECORD_APPL_DISJUNCTION; } } } }tom_match1_1_NameList_list1=tom_match1_1_NameList_end1; } } } } } } if ( ( tom_is_fun_sym_XMLAppl(tom_match1_1) ||  false  ) ) { if ( true ) {
 return XML_APPL; } } if ( ( tom_is_fun_sym_UnamedVariable(tom_match1_1) ||  false  ) ) { if ( true ) {
 return UNAMED_VARIABLE; } } if ( ( tom_is_fun_sym_VariableStar(tom_match1_1) ||  false  ) ) { if ( true ) {
 return VARIABLE_STAR; } } if ( ( tom_is_fun_sym_Variable(tom_match1_1) ||  false  ) ) { if ( true ) {
 return VARIABLE; } } if ( ( tom_is_fun_sym_UnamedVariableStar(tom_match1_1) ||  false  ) ) { if ( true ) {
 return UNAMED_VARIABLE_STAR; } } } }

    throw new TomRuntimeException("Invalid Term");
  }
  
  public String getName(TomTerm term) {
    String dijunctionName = "";
     if(term instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match2_1=(( tom.engine.adt.tomterm.types.TomTerm )term); if ( ( tom_is_fun_sym_TermAppl(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList=tom_get_slot_TermAppl_NameList(tom_match2_1); if ( ( tom_is_fun_sym_concTomName(tom_match2_1_NameList) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList_list1=tom_match2_1_NameList; { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList_end1=tom_match2_1_NameList_list1; { if (!(tom_is_empty_concTomName_TomNameList(tom_match2_1_NameList_list1))) { { tom.engine.adt.tomname.types.TomName  tom_match2_1_NameList_1=tom_get_head_concTomName_TomNameList(tom_match2_1_NameList_list1);tom_match2_1_NameList_list1=tom_get_tail_concTomName_TomNameList(tom_match2_1_NameList_list1); if ( ( tom_is_fun_sym_Name(tom_match2_1_NameList_1) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tom_match2_1_NameList_1); if (tom_is_empty_concTomName_TomNameList(tom_match2_1_NameList_list1)) { if ( true ) {
 return tom_name; } } } } } }tom_match2_1_NameList_list1=tom_match2_1_NameList_end1; } } } } } } if ( ( tom_is_fun_sym_TermAppl(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_nameList=tom_get_slot_TermAppl_NameList(tom_match2_1); if ( true ) {

        String head;
        dijunctionName = tom_nameList.getHeadconcTomName().getString();
        while(!tom_nameList.isEmptyconcTomName()) {
          head = tom_nameList.getHeadconcTomName().getString();
          dijunctionName = ( dijunctionName.compareTo(head) > 0)?dijunctionName:head;
          tom_nameList= tom_nameList.getTailconcTomName();
        }
        return dijunctionName;
       } } } if ( ( tom_is_fun_sym_RecordAppl(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList=tom_get_slot_RecordAppl_NameList(tom_match2_1); if ( ( tom_is_fun_sym_concTomName(tom_match2_1_NameList) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList_list1=tom_match2_1_NameList; { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList_end1=tom_match2_1_NameList_list1; { if (!(tom_is_empty_concTomName_TomNameList(tom_match2_1_NameList_list1))) { { tom.engine.adt.tomname.types.TomName  tom_match2_1_NameList_1=tom_get_head_concTomName_TomNameList(tom_match2_1_NameList_list1);tom_match2_1_NameList_list1=tom_get_tail_concTomName_TomNameList(tom_match2_1_NameList_list1); if ( ( tom_is_fun_sym_Name(tom_match2_1_NameList_1) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tom_match2_1_NameList_1); if (tom_is_empty_concTomName_TomNameList(tom_match2_1_NameList_list1)) { if ( true ) {
 return tom_name; } } } } } }tom_match2_1_NameList_list1=tom_match2_1_NameList_end1; } } } } } } if ( ( tom_is_fun_sym_RecordAppl(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_nameList=tom_get_slot_RecordAppl_NameList(tom_match2_1); if ( true ) {

        String head;
        dijunctionName = tom_nameList.getHeadconcTomName().getString();
        while(!tom_nameList.isEmptyconcTomName()) {
          head = tom_nameList.getHeadconcTomName().getString();
          dijunctionName = ( dijunctionName.compareTo(head) > 0)?dijunctionName:head;
          tom_nameList= tom_nameList.getTailconcTomName();
        }
        return dijunctionName;
       } } } if ( ( tom_is_fun_sym_XMLAppl(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList=tom_get_slot_XMLAppl_NameList(tom_match2_1); if ( ( tom_is_fun_sym_concTomName(tom_match2_1_NameList) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList_list1=tom_match2_1_NameList; { tom.engine.adt.tomname.types.TomNameList  tom_match2_1_NameList_end1=tom_match2_1_NameList_list1; { if (!(tom_is_empty_concTomName_TomNameList(tom_match2_1_NameList_list1))) { { tom.engine.adt.tomname.types.TomName  tom_match2_1_NameList_1=tom_get_head_concTomName_TomNameList(tom_match2_1_NameList_list1);tom_match2_1_NameList_list1=tom_get_tail_concTomName_TomNameList(tom_match2_1_NameList_list1); if ( ( tom_is_fun_sym_Name(tom_match2_1_NameList_1) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tom_match2_1_NameList_1); if ( true ) {
 return tom_name; } } } } }tom_match2_1_NameList_list1=tom_match2_1_NameList_end1; } } } } } } if ( ( tom_is_fun_sym_XMLAppl(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomNameList  tom_nameList=tom_get_slot_XMLAppl_NameList(tom_match2_1); if ( true ) {

        String head;
        dijunctionName = tom_nameList.getHeadconcTomName().getString();
        while(!tom_nameList.isEmptyconcTomName()) {
          head = tom_nameList.getHeadconcTomName().getString();
          dijunctionName = ( dijunctionName.compareTo(head) > 0)?dijunctionName:head;
          tom_nameList= tom_nameList.getTailconcTomName();
        }
        return dijunctionName;
       } } } if ( ( tom_is_fun_sym_Variable(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match2_1_AstName=tom_get_slot_Variable_AstName(tom_match2_1); if ( ( tom_is_fun_sym_Name(tom_match2_1_AstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tom_match2_1_AstName); if ( true ) {
 return tom_name; } } } } } if ( ( tom_is_fun_sym_VariableStar(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match2_1_AstName=tom_get_slot_VariableStar_AstName(tom_match2_1); if ( ( tom_is_fun_sym_Name(tom_match2_1_AstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tom_match2_1_AstName); if ( true ) {
 return tom_name+"*"; } } } } } if ( ( tom_is_fun_sym_UnamedVariable(tom_match2_1) ||  false  ) ) { if ( true ) {
 return "_"; } } if ( ( tom_is_fun_sym_UnamedVariableStar(tom_match2_1) ||  false  ) ) { if ( true ) {
 return "_*"; } } } }

    throw new TomRuntimeException("Invalid Term");
  }
  
  /**
   * Shared Functions 
   */
  protected String extractType(TomSymbol symbol) {
    TomType type = getSymbolCodomain(symbol);
    return getTomType(type);
  }
  
  protected static String findOriginTrackingFileName(OptionList optionList) {
     if(optionList instanceof  tom.engine.adt.tomoption.types.OptionList ) { { tom.engine.adt.tomoption.types.OptionList  tom_match3_1=(( tom.engine.adt.tomoption.types.OptionList )optionList); if ( ( tom_is_fun_sym_concOption(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomoption.types.OptionList  tom_match3_1_list1=tom_match3_1; { tom.engine.adt.tomoption.types.OptionList  tom_match3_1_begin1=tom_match3_1_list1; { tom.engine.adt.tomoption.types.OptionList  tom_match3_1_end1=tom_match3_1_list1; { while (!(tom_is_empty_concOption_OptionList(tom_match3_1_end1))) {tom_match3_1_list1=tom_match3_1_end1; { { tom.engine.adt.tomoption.types.OptionList  tom_match3_1_end2=tom_match3_1_list1; { { tom.engine.adt.tomoption.types.Option  tom_match3_1_2=tom_get_head_concOption_OptionList(tom_match3_1_list1);tom_match3_1_list1=tom_get_tail_concOption_OptionList(tom_match3_1_list1); if ( ( tom_is_fun_sym_OriginTracking(tom_match3_1_2) ||  false  ) ) { { String  tom_fileName=tom_get_slot_OriginTracking_FileName(tom_match3_1_2); if ( true ) {
 return tom_fileName;  } } } }tom_match3_1_list1=tom_match3_1_end2; } }tom_match3_1_end1=tom_get_tail_concOption_OptionList(tom_match3_1_end1); } }tom_match3_1_list1=tom_match3_1_begin1; } } } } } } }

    return "unknown filename";
  }

  protected static int findOriginTrackingLine(OptionList optionList) {
     if(optionList instanceof  tom.engine.adt.tomoption.types.OptionList ) { { tom.engine.adt.tomoption.types.OptionList  tom_match4_1=(( tom.engine.adt.tomoption.types.OptionList )optionList); if ( ( tom_is_fun_sym_concOption(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomoption.types.OptionList  tom_match4_1_list1=tom_match4_1; { tom.engine.adt.tomoption.types.OptionList  tom_match4_1_begin1=tom_match4_1_list1; { tom.engine.adt.tomoption.types.OptionList  tom_match4_1_end1=tom_match4_1_list1; { while (!(tom_is_empty_concOption_OptionList(tom_match4_1_end1))) {tom_match4_1_list1=tom_match4_1_end1; { { tom.engine.adt.tomoption.types.OptionList  tom_match4_1_end2=tom_match4_1_list1; { { tom.engine.adt.tomoption.types.Option  tom_match4_1_2=tom_get_head_concOption_OptionList(tom_match4_1_list1);tom_match4_1_list1=tom_get_tail_concOption_OptionList(tom_match4_1_list1); if ( ( tom_is_fun_sym_OriginTracking(tom_match4_1_2) ||  false  ) ) { { int  tom_line=tom_get_slot_OriginTracking_Line(tom_match4_1_2); if ( true ) {
 return tom_line;  } } } }tom_match4_1_list1=tom_match4_1_end2; } }tom_match4_1_end1=tom_get_tail_concOption_OptionList(tom_match4_1_end1); } }tom_match4_1_list1=tom_match4_1_begin1; } } } } } } }

    return -1;
  }

  protected void ensureOriginTrackingLine(int line) {
    if(line < 0) {
      getLogger().log(Level.SEVERE,
                      TomMessage.findOTL.getMessage(),
                      getStreamManager().getInputFileName());
      //System.out.println("findOriginTrackingLine: not found ");
    }
  }

  /**
   * Message Functions
   */
  protected void messageError(String fileName, int errorLine, TomMessage msg, Object[] msgArgs) {
    getLogger().log(new PlatformLogRecord(Level.SEVERE, msg, msgArgs,fileName, errorLine));
  }
  
  protected void messageWarning(String fileName, int errorLine, TomMessage msg, Object[] msgArgs) {
    getLogger().log(new PlatformLogRecord(Level.WARNING,msg,msgArgs,fileName, errorLine));
  }
  
  public static void messageError(String className,String fileName, int errorLine, TomMessage msg, Object[] msgArgs) {
    Logger.getLogger(className).log(new PlatformLogRecord(Level.SEVERE, msg, msgArgs,fileName, errorLine));
  }
  
  public static void messageWarning(String className,String fileName, int errorLine, TomMessage msg, Object[] msgArgs) {
    Logger.getLogger(className).log(new PlatformLogRecord(Level.WARNING, msg, msgArgs,fileName, errorLine));
  }
  
}  //Class TomChecker
