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
 * Julien Guyon
 *
 **/

package tom.engine.checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

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
import tom.engine.adt.tomconstraint.types.constraint.*;

import tom.engine.xml.Constants;
import tom.platform.OptionParser;
import tom.platform.adt.platformoption.types.PlatformOptionList;
import aterm.ATerm;
import tom.engine.tools.ASTFactory;

import tom.library.strategy.mutraveler.MuStrategy;
import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.VisitFailure;

/**
 * The TomSyntaxChecker plugin - justs adds anti-pattern facilities to the TomSyntaxChecker.
 */
public class TomSyntaxCheckerAp extends TomSyntaxChecker {

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */private static boolean tom_terms_equal_int( int  t1,  int  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_TomType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Constraint(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ConstraintList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_AntiTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.AntiTerm  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_AntiTerm_TomTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTomTerm()  ;}private static boolean tom_is_fun_sym_Variable( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.Variable  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_Variable_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_Variable_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Variable_AstType( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstType()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_Variable_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_TermAppl( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_TermAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_TermAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getNameList()  ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_TermAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getArgs()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_TermAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_AssignTo( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraint.AssignTo  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_AssignTo_Variable( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getVariable()  ;}private static boolean tom_is_fun_sym_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint || t instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_empty_list_concConstraint() { return  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ; }private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_cons_list_concConstraint( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.ConstraintList  l) { return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make(e,l) ; }private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {  return  l.getHeadconcConstraint()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_tail_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {  return  l.getTailconcConstraint()  ;}private static boolean tom_is_empty_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {  return  l.isEmptyconcConstraint()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {    if(tom_is_empty_concConstraint_ConstraintList(l1)) {     return l2;    } else if(tom_is_empty_concConstraint_ConstraintList(l2)) {     return l1;    } else if(tom_is_empty_concConstraint_ConstraintList(( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_tail_concConstraint_ConstraintList(l1))) {     return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concConstraint_ConstraintList(l1),l2);    } else {      return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concConstraint_ConstraintList(l1),tom_append_list_concConstraint(( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_tail_concConstraint_ConstraintList(l1),l2));    }   }  private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end) {    if(tom_terms_equal_ConstraintList(begin,end)) {      return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_empty_list_concConstraint();    } else {      return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concConstraint_ConstraintList(begin),( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint(( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_tail_concConstraint_ConstraintList(begin),end));    }   }   /* Generated by TOM (version 2.4alpha): Do not edit this file */private static boolean tom_terms_equal_Strategy(Object t1, Object t2) {  return t1.equals(t2) ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_mu( tom.library.strategy.mutraveler.MuStrategy  var,  tom.library.strategy.mutraveler.MuStrategy  v) { return  new tom.library.strategy.mutraveler.Mu(var,v) ; }/* Generated by TOM (version 2.4alpha): Do not edit this file */private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Identity() { return  new tom.library.strategy.mutraveler.Identity() ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Sequence( tom.library.strategy.mutraveler.MuStrategy  first,  tom.library.strategy.mutraveler.MuStrategy  then) { return  new tom.library.strategy.mutraveler.Sequence(first,then) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_All( tom.library.strategy.mutraveler.MuStrategy  v) { return  new tom.library.strategy.mutraveler.All(v) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_MuVar( String  name) { return  new tom.library.strategy.mutraveler.MuVar(name) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_TopDown( tom.library.strategy.mutraveler.MuStrategy  v) { return tom_make_mu(tom_make_MuVar("_x"),tom_make_Sequence(v,tom_make_All(tom_make_MuVar("_x")))) ; }  

  /**
   * Basicaly ignores the anti-symbol
   */
  public  TermDescription validateTerm(TomTerm term, TomType expectedType, boolean listSymbol, boolean topLevel, boolean permissive) {
     if(term instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match1_1=(( tom.engine.adt.tomterm.types.TomTerm )term); if ( ( tom_is_fun_sym_AntiTerm(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match1_1_TomTerm=tom_get_slot_AntiTerm_TomTerm(tom_match1_1); { tom.engine.adt.tomterm.types.TomTerm  tom_t=tom_match1_1_TomTerm; {boolean tom_bool_match1_1_TomTerm= false ; { tom.engine.adt.tomoption.types.OptionList  tom_match1_1_TomTerm_Option= null ; if (tom_is_fun_sym_TermAppl(tom_match1_1_TomTerm)) {tom_bool_match1_1_TomTerm= true ;tom_match1_1_TomTerm_Option=tom_get_slot_TermAppl_Option(tom_match1_1_TomTerm); } else { if (tom_is_fun_sym_Variable(tom_match1_1_TomTerm)) {tom_bool_match1_1_TomTerm= true ;tom_match1_1_TomTerm_Option=tom_get_slot_Variable_Option(tom_match1_1_TomTerm); } } if (tom_bool_match1_1_TomTerm) { { tom.engine.adt.tomoption.types.OptionList  tom_options=tom_match1_1_TomTerm_Option; if ( true ) {


        checkForAnnotations(tom_t,tom_options);
        return super.validateTerm(tom_t, expectedType, listSymbol, topLevel, permissive);
       } } } } } } } } } }


    return super.validateTerm(term, expectedType, listSymbol, topLevel, permissive);
  }

  public  TermDescription analyseTerm(TomTerm term) {
     if(term instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match2_1=(( tom.engine.adt.tomterm.types.TomTerm )term); if ( ( tom_is_fun_sym_AntiTerm(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match2_1_TomTerm=tom_get_slot_AntiTerm_TomTerm(tom_match2_1); if ( ( tom_is_fun_sym_Variable(tom_match2_1_TomTerm) ||  ( tom_is_fun_sym_TermAppl(tom_match2_1_TomTerm) ||  false  )  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_t=tom_match2_1_TomTerm; if ( true ) {


        return super.analyseTerm(tom_t);
       } } } } } } }

    return super.analyseTerm(term);
  }

  /**
   * Checks if the given term contains annotations
   * 
   * @param t the term to search
   */
  private void checkForAnnotations(TomTerm t, OptionList options){	  
    String fileName = findOriginTrackingFileName(options);
    int decLine = findOriginTrackingLine(options);
    tom_make_TopDown(tom_make_CheckForAnnotations(fileName,decLine)).apply(t);
  }


  /**
   * Given a term, it checks if it contains annotations
   */  
   private static class CheckForAnnotations  extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy   { private  String  fileName;  private  int  decLine;  public CheckForAnnotations(  String  fileName ,   int  decLine ) { super(tom_make_Identity() ); this.fileName=fileName; this.decLine=decLine; } public  String  getfileName() { return fileName;} public  int  getdecLine() { return decLine;} public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm(  tom.engine.adt.tomterm.types.TomTerm  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg); { tom.engine.adt.tomterm.types.TomTerm  tom_t=tom_match3_1; {boolean tom_bool_match3_1= false ; { tom.engine.adt.tomconstraint.types.ConstraintList  tom_match3_1_Constraints= null ; if (tom_is_fun_sym_TermAppl(tom_match3_1)) {tom_bool_match3_1= true ;tom_match3_1_Constraints=tom_get_slot_TermAppl_Constraints(tom_match3_1); } else { if (tom_is_fun_sym_Variable(tom_match3_1)) {tom_bool_match3_1= true ;tom_match3_1_Constraints=tom_get_slot_Variable_Constraints(tom_match3_1); } } if (tom_bool_match3_1) { if ( ( tom_is_fun_sym_concConstraint(tom_match3_1_Constraints) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.ConstraintList  tom_match3_1_Constraints_list1=tom_match3_1_Constraints; { tom.engine.adt.tomconstraint.types.ConstraintList  tom_match3_1_Constraints_begin1=tom_match3_1_Constraints_list1; { tom.engine.adt.tomconstraint.types.ConstraintList  tom_match3_1_Constraints_end1=tom_match3_1_Constraints_list1; { while (!(tom_is_empty_concConstraint_ConstraintList(tom_match3_1_Constraints_end1))) {tom_match3_1_Constraints_list1=tom_match3_1_Constraints_end1; { { tom.engine.adt.tomconstraint.types.Constraint  tom_match3_1_Constraints_2=tom_get_head_concConstraint_ConstraintList(tom_match3_1_Constraints_list1);tom_match3_1_Constraints_list1=tom_get_tail_concConstraint_ConstraintList(tom_match3_1_Constraints_list1); if ( ( tom_is_fun_sym_AssignTo(tom_match3_1_Constraints_2) ||  false  ) ) { if ( true ) {


        TomChecker.messageError("TomSyntaxChecker",fileName,decLine,TomMessage.illegalAnnotationInAntiPattern, new Object[]{});
        throw new TomRuntimeException("Illegal use of annotations in " + tom_t);
       } } }tom_match3_1_Constraints_end1=tom_get_tail_concConstraint_ConstraintList(tom_match3_1_Constraints_end1); } }tom_match3_1_Constraints_list1=tom_match3_1_Constraints_begin1; } } } } } } } } } } } return super.visit_TomTerm(tom__arg) ;  } }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_CheckForAnnotations( String  t0,  int  t1) { return new CheckForAnnotations(t0,t1); }



}
