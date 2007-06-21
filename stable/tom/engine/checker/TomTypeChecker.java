/* Generated by TOM (version 2.5rc1): Do not edit this file *//*
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
 * Julien Guyon
 *
 **/

package tom.engine.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import tom.engine.TomBase;
import tom.engine.TomMessage;
import tom.platform.OptionParser;

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

import tom.platform.adt.platformoption.types.PlatformOptionList;
import aterm.ATerm;
import tom.library.sl.*;

/**
 * The TomTypeChecker plugin.
 */
public class TomTypeChecker extends TomChecker {

  /* Generated by TOM (version 2.5rc1): Do not edit this file *//* Generated by TOM (version 2.5rc1): Do not edit this file *//* Generated by TOM (version 2.5rc1): Do not edit this file */ private static boolean tom_equal_term_String(String t1, String t2) { return  (t1.equals(t2)) ;}private static boolean tom_is_sort_String(String t) { return  t instanceof String ;}  /* Generated by TOM (version 2.5rc1): Do not edit this file */ private static boolean tom_equal_term_Instruction(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Instruction(Object t) { return  t instanceof tom.engine.adt.tominstruction.types.Instruction ;}private static boolean tom_equal_term_TomType(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomType(Object t) { return  t instanceof tom.engine.adt.tomtype.types.TomType ;}private static boolean tom_equal_term_TomForwardType(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomForwardType(Object t) { return  t instanceof tom.engine.adt.tomtype.types.TomForwardType ;}private static boolean tom_equal_term_TomVisit(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomVisit(Object t) { return  t instanceof tom.engine.adt.tomsignature.types.TomVisit ;}private static boolean tom_equal_term_TomVisitList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomVisitList(Object t) { return  t instanceof tom.engine.adt.tomsignature.types.TomVisitList ;}private static boolean tom_equal_term_TargetLanguage(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TargetLanguage(Object t) { return  t instanceof tom.engine.adt.tomsignature.types.TargetLanguage ;}private static boolean tom_equal_term_Declaration(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Declaration(Object t) { return  t instanceof tom.engine.adt.tomdeclaration.types.Declaration ;}private static boolean tom_equal_term_TomNameList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomNameList(Object t) { return  t instanceof tom.engine.adt.tomname.types.TomNameList ;}private static boolean tom_equal_term_TomName(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomName(Object t) { return  t instanceof tom.engine.adt.tomname.types.TomName ;}private static boolean tom_equal_term_PatternInstructionList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_PatternInstructionList(Object t) { return  t instanceof tom.engine.adt.tomterm.types.PatternInstructionList ;}private static boolean tom_equal_term_TomList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomList(Object t) { return  t instanceof tom.engine.adt.tomterm.types.TomList ;}private static boolean tom_equal_term_TomTerm(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomTerm(Object t) { return  t instanceof tom.engine.adt.tomterm.types.TomTerm ;}private static boolean tom_equal_term_Option(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Option(Object t) { return  t instanceof tom.engine.adt.tomoption.types.Option ;}private static boolean tom_equal_term_OptionList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_OptionList(Object t) { return  t instanceof tom.engine.adt.tomoption.types.OptionList ;}private static boolean tom_equal_term_ConstraintList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_ConstraintList(Object t) { return  t instanceof tom.engine.adt.tomconstraint.types.ConstraintList ;}private static boolean tom_is_fun_sym_Match( tom.engine.adt.tominstruction.types.Instruction  t) { return  t instanceof tom.engine.adt.tominstruction.types.instruction.Match ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_Match_SubjectList( tom.engine.adt.tominstruction.types.Instruction  t) { return  t.getSubjectList() ;}private static  tom.engine.adt.tomterm.types.PatternInstructionList  tom_get_slot_Match_AstPatternInstructionList( tom.engine.adt.tominstruction.types.Instruction  t) { return  t.getAstPatternInstructionList() ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_Match_Option( tom.engine.adt.tominstruction.types.Instruction  t) { return  t.getOption() ;}private static boolean tom_is_fun_sym_Type( tom.engine.adt.tomtype.types.TomType  t) { return  t instanceof tom.engine.adt.tomtype.types.tomtype.Type ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Type_TomType( tom.engine.adt.tomtype.types.TomType  t) { return  t.getTomType() ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Type_TlType( tom.engine.adt.tomtype.types.TomType  t) { return  t.getTlType() ;}private static boolean tom_is_fun_sym_TomTypeAlone( tom.engine.adt.tomtype.types.TomType  t) { return  t instanceof tom.engine.adt.tomtype.types.tomtype.TomTypeAlone ;}private static  String  tom_get_slot_TomTypeAlone_String( tom.engine.adt.tomtype.types.TomType  t) { return  t.getString() ;}private static boolean tom_is_fun_sym_ASTTomType( tom.engine.adt.tomtype.types.TomType  t) { return  t instanceof tom.engine.adt.tomtype.types.tomtype.ASTTomType ;}private static  String  tom_get_slot_ASTTomType_String( tom.engine.adt.tomtype.types.TomType  t) { return  t.getString() ;}private static boolean tom_is_fun_sym_TLType( tom.engine.adt.tomtype.types.TomType  t) { return  t instanceof tom.engine.adt.tomtype.types.tomtype.TLType ;}private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_get_slot_TLType_Tl( tom.engine.adt.tomtype.types.TomType  t) { return  t.getTl() ;}private static  tom.engine.adt.tomtype.types.TomForwardType  tom_make_EmptyForward() { return  tom.engine.adt.tomtype.types.tomforwardtype.EmptyForward.make() ; }private static boolean tom_is_fun_sym_VisitTerm( tom.engine.adt.tomsignature.types.TomVisit  t) { return  t instanceof tom.engine.adt.tomsignature.types.tomvisit.VisitTerm ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_VisitTerm_VNode( tom.engine.adt.tomsignature.types.TomVisit  t) { return  t.getVNode() ;}private static  tom.engine.adt.tomterm.types.PatternInstructionList  tom_get_slot_VisitTerm_AstPatternInstructionList( tom.engine.adt.tomsignature.types.TomVisit  t) { return  t.getAstPatternInstructionList() ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_VisitTerm_Option( tom.engine.adt.tomsignature.types.TomVisit  t) { return  t.getOption() ;}private static boolean tom_is_fun_sym_Strategy( tom.engine.adt.tomdeclaration.types.Declaration  t) { return  t instanceof tom.engine.adt.tomdeclaration.types.declaration.Strategy ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_Strategy_SName( tom.engine.adt.tomdeclaration.types.Declaration  t) { return  t.getSName() ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_Strategy_ExtendsTerm( tom.engine.adt.tomdeclaration.types.Declaration  t) { return  t.getExtendsTerm() ;}private static  tom.engine.adt.tomsignature.types.TomVisitList  tom_get_slot_Strategy_VisitList( tom.engine.adt.tomdeclaration.types.Declaration  t) { return  t.getVisitList() ;}private static  tom.engine.adt.tomoption.types.Option  tom_get_slot_Strategy_OrgTrack( tom.engine.adt.tomdeclaration.types.Declaration  t) { return  t.getOrgTrack() ;}private static boolean tom_is_fun_sym_TermAppl( tom.engine.adt.tomterm.types.TomTerm  t) { return  t instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_TermAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getOption() ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_TermAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getNameList() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_TermAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getArgs() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_TermAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getConstraints() ;} /* Generated by TOM (version 2.5rc1): Do not edit this file */private static boolean tom_equal_term_Strategy(Object t1, Object t2) { return t1.equals(t2);}private static boolean tom_is_sort_Strategy(Object t) { return  t instanceof tom.library.sl.Strategy ;}/* Generated by TOM (version 2.5rc1): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_mu( tom.library.sl.Strategy  var,  tom.library.sl.Strategy  v) { return  new tom.library.sl.Mu(var,v) ; }private static  tom.library.sl.Strategy  tom_make_MuVar( String  name) { return  new tom.library.sl.MuVar(name) ; }private static  tom.library.sl.Strategy  tom_make_Identity() { return  new tom.library.sl.Identity() ; }private static  tom.library.sl.Strategy  tom_make_All( tom.library.sl.Strategy  v) { return  new tom.library.sl.All(v) ; }private static boolean tom_is_fun_sym_Sequence( tom.library.sl.Strategy  t) { return  (t instanceof tom.library.sl.Sequence) ;}private static  tom.library.sl.Strategy  tom_empty_list_Sequence() { return  new tom.library.sl.Identity() ; }private static  tom.library.sl.Strategy  tom_cons_list_Sequence( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { return  new tom.library.sl.Sequence(head,tail) ; }private static  tom.library.sl.Strategy  tom_get_head_Sequence_Strategy( tom.library.sl.Strategy  t) { return  (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.FIRST) ;}private static  tom.library.sl.Strategy  tom_get_tail_Sequence_Strategy( tom.library.sl.Strategy  t) { return  (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.THEN) ;}private static boolean tom_is_empty_Sequence_Strategy( tom.library.sl.Strategy  t) { return  t instanceof tom.library.sl.Identity ;}   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy l1,  tom.library.sl.Strategy  l2) {     if(tom_is_empty_Sequence_Strategy(l1)) {       return l2;     } else if(tom_is_empty_Sequence_Strategy(l2)) {       return l1;     } else if(tom_is_fun_sym_Sequence(l1)) {       if(tom_is_empty_Sequence_Strategy(((tom_is_fun_sym_Sequence(l1))?tom_get_tail_Sequence_Strategy(l1):tom_empty_list_Sequence()))) {         return ( tom.library.sl.Strategy )tom_cons_list_Sequence(((tom_is_fun_sym_Sequence(l1))?tom_get_head_Sequence_Strategy(l1):l1),l2);       } else {         return ( tom.library.sl.Strategy )tom_cons_list_Sequence(((tom_is_fun_sym_Sequence(l1))?tom_get_head_Sequence_Strategy(l1):l1),tom_append_list_Sequence(((tom_is_fun_sym_Sequence(l1))?tom_get_tail_Sequence_Strategy(l1):tom_empty_list_Sequence()),l2));       }     } else {       return ( tom.library.sl.Strategy )tom_cons_list_Sequence(l1, l2);     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if(tom_equal_term_Strategy(begin,end)) {       return tail;     } else {       return ( tom.library.sl.Strategy )tom_cons_list_Sequence(((tom_is_fun_sym_Sequence(begin))?tom_get_head_Sequence_Strategy(begin):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((tom_is_fun_sym_Sequence(begin))?tom_get_tail_Sequence_Strategy(begin):tom_empty_list_Sequence()),end,tail));     }   }   private static  tom.library.sl.Strategy  tom_make_Choice( tom.library.sl.Strategy  first,  tom.library.sl.Strategy  then) { return  new tom.library.sl.Choice(first,then) ; } /* Generated by TOM (version 2.5rc1): Do not edit this file */ /* Generated by TOM (version 2.5rc1): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  v) { return tom_make_Choice(v,tom_make_Identity()) ; }private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Sequence(v,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_empty_list_Sequence()))) ; }   


  /** the declared options string */
  public static final String DECLARED_OPTIONS = "<options><boolean name='noTypeCheck' altName='' description='Do not perform type checking' value='false'/></options>";

  /**
   * inherited from OptionOwner interface (plugin) 
   */
  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(TomTypeChecker.DECLARED_OPTIONS);
  }

  /** Constructor */
  public TomTypeChecker() {
    super("TomTypeChecker");
  }

  public void run() {
    if(isActivated()) {
      strictType = !getOptionBooleanValue("lazyType");
      long startChrono = System.currentTimeMillis();
      try {
        // clean up internals
        reinit();
        // perform analyse
        try {
          tom_make_mu(tom_make_MuVar("x"),tom_make_Try(tom_cons_list_Sequence(tom_make_checkTypeInference(this),tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("x")),tom_empty_list_Sequence())))).visitLight((TomTerm)getWorkingTerm());
        } catch(tom.library.sl.VisitFailure e) {
          System.out.println("strategy failed");
        }
        // verbose
        getLogger().log( Level.INFO, TomMessage.tomTypeCheckingPhase.getMessage(),
            new Integer((int)(System.currentTimeMillis()-startChrono)) );
      } catch (Exception e) {
        getLogger().log( Level.SEVERE, TomMessage.exceptionMessage.getMessage(),
            new Object[]{getClass().getName(), getStreamManager().getInputFileName(),e.getMessage()} );
        e.printStackTrace();
      }
    } else {
      // type checker desactivated    
      getLogger().log(Level.INFO, TomMessage.typeCheckerInactivated.getMessage());
    }
  }

  private boolean isActivated() {
    return !getOptionBooleanValue("noTypeCheck");
  }

  /**
   * Main type checking entry point:
   * We check all Match
   */
  private static boolean tom_is_sort_TomTypeChecker(Object t) { return 

 t instanceof TomTypeChecker ;}private static class checkTypeInference extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy  {private  TomTypeChecker  ttc; public checkTypeInference( TomTypeChecker  ttc) { super(tom_make_Identity());this.ttc=ttc;}public  TomTypeChecker  getttc() { return ttc;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];for (int i = 0; i < getChildCount(); i++) {stratChilds[i]=getChildAt(i);}return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {for (int i = 0; i < getChildCount(); i++) {setChildAt(i,children[i]);}return this;}public int getChildCount() { return 1; }public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg) throws tom.library.sl.VisitFailure {if (tom_is_sort_Instruction(tom__arg)) {{  tom.engine.adt.tominstruction.types.Instruction  tomMatch115NameNumberfreshSubject_1=(( tom.engine.adt.tominstruction.types.Instruction )tom__arg);if (tom_is_fun_sym_Match(tomMatch115NameNumberfreshSubject_1)) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch115NameNumber_freshVar_0=tom_get_slot_Match_SubjectList(tomMatch115NameNumberfreshSubject_1);{  tom.engine.adt.tomterm.types.PatternInstructionList  tomMatch115NameNumber_freshVar_1=tom_get_slot_Match_AstPatternInstructionList(tomMatch115NameNumberfreshSubject_1);{  tom.engine.adt.tomoption.types.OptionList  tomMatch115NameNumber_freshVar_2=tom_get_slot_Match_Option(tomMatch115NameNumberfreshSubject_1);if ( true ) {




  
        ttc.currentTomStructureOrgTrack = TomBase.findOriginTracking(tomMatch115NameNumber_freshVar_2);
        ttc.verifyMatchVariable(tomMatch115NameNumber_freshVar_1);
        throw new tom.library.sl.VisitFailure();
      }}}}}}}return super.visit_Instruction(tom__arg); }public  tom.engine.adt.tomdeclaration.types.Declaration  visit_Declaration( tom.engine.adt.tomdeclaration.types.Declaration  tom__arg) throws tom.library.sl.VisitFailure {if (tom_is_sort_Declaration(tom__arg)) {{  tom.engine.adt.tomdeclaration.types.Declaration  tomMatch116NameNumberfreshSubject_1=(( tom.engine.adt.tomdeclaration.types.Declaration )tom__arg);if (tom_is_fun_sym_Strategy(tomMatch116NameNumberfreshSubject_1)) {{  tom.engine.adt.tomname.types.TomName  tomMatch116NameNumber_freshVar_0=tom_get_slot_Strategy_SName(tomMatch116NameNumberfreshSubject_1);{  tom.engine.adt.tomterm.types.TomTerm  tomMatch116NameNumber_freshVar_1=tom_get_slot_Strategy_ExtendsTerm(tomMatch116NameNumberfreshSubject_1);{  tom.engine.adt.tomsignature.types.TomVisitList  tomMatch116NameNumber_freshVar_2=tom_get_slot_Strategy_VisitList(tomMatch116NameNumberfreshSubject_1);{  tom.engine.adt.tomoption.types.Option  tomMatch116NameNumber_freshVar_3=tom_get_slot_Strategy_OrgTrack(tomMatch116NameNumberfreshSubject_1);if ( true ) {



        ttc.currentTomStructureOrgTrack = tomMatch116NameNumber_freshVar_3;
        ttc.verifyStrategyVariable(tomMatch116NameNumber_freshVar_2);
        throw new tom.library.sl.VisitFailure();
      }}}}}}}}return super.visit_Declaration(tom__arg); }}private static  tom.library.sl.Strategy  tom_make_checkTypeInference( TomTypeChecker  t0) { return new checkTypeInference(t0); }

 //checkTypeInference

  /* 
   * Collect unknown (not in symbol table) appls without ()
   */
  private static class collectUnknownAppls extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy  {private  TomTypeChecker  ttc; public collectUnknownAppls( TomTypeChecker  ttc) { super(tom_make_Identity());this.ttc=ttc;}public  TomTypeChecker  getttc() { return ttc;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];for (int i = 0; i < getChildCount(); i++) {stratChilds[i]=getChildAt(i);}return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {for (int i = 0; i < getChildCount(); i++) {setChildAt(i,children[i]);}return this;}public int getChildCount() { return 1; }public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg) throws tom.library.sl.VisitFailure {if (tom_is_sort_TomTerm(tom__arg)) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch117NameNumberfreshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg);{  tom.engine.adt.tomterm.types.TomTerm  tomMatch117NameNumber_freshVar_0=tomMatch117NameNumberfreshSubject_1;if (tom_is_fun_sym_TermAppl(tomMatch117NameNumber_freshVar_0)) {{  tom.engine.adt.tomterm.types.TomTerm  tom_app=tomMatch117NameNumber_freshVar_0;if ( true ) {


        if(ttc.symbolTable().getSymbolFromName(ttc.getName(tom_app))==null) {
          ttc.messageError(findOriginTrackingFileName(tom_app.getOption()),
              findOriginTrackingLine(tom_app.getOption()),
              TomMessage.unknownVariableInWhen,
              new Object[]{ttc.getName(tom_app)});
        }
        // else, it's actually app()
        // else, it's a unknown (ie : java) function
      }}}}}}return super.visit_TomTerm(tom__arg); }}private static  tom.library.sl.Strategy  tom_make_collectUnknownAppls( TomTypeChecker  t0) { return new collectUnknownAppls(t0); }



  private void verifyMatchVariable(PatternInstructionList patternInstructionList) {
    while(!patternInstructionList.isEmptyconcPatternInstruction()) {
      PatternInstruction pa = patternInstructionList.getHeadconcPatternInstruction();
      Pattern pattern = pa.getPattern();
      // collect variables
      ArrayList variableList = new ArrayList();
      TomBase.collectVariable(variableList, pattern);
      verifyVariableTypeListCoherence(variableList);
      // verify variables in WHEN instruction
      // collect unknown variables
      try {
        tom_make_TopDown(tom_make_collectUnknownAppls(this)).visit(pattern.getGuards());
      } catch(tom.library.sl.VisitFailure e) {
        System.out.println("strategy failed");
      }

      patternInstructionList = patternInstructionList.getTailconcPatternInstruction();
    }
  }

  private void verifyStrategyVariable(TomVisitList list) {
    TomForwardType visitorFwd = null;
    TomForwardType currentVisitorFwd = null;
    while(!list.isEmptyconcTomVisit()) {
      TomVisit visit = list.getHeadconcTomVisit();
      if (tom_is_sort_TomVisit(visit)) {{  tom.engine.adt.tomsignature.types.TomVisit  tomMatch119NameNumberfreshSubject_1=(( tom.engine.adt.tomsignature.types.TomVisit )visit);if (tom_is_fun_sym_VisitTerm(tomMatch119NameNumberfreshSubject_1)) {{  tom.engine.adt.tomtype.types.TomType  tomMatch119NameNumber_freshVar_0=tom_get_slot_VisitTerm_VNode(tomMatch119NameNumberfreshSubject_1);{  tom.engine.adt.tomterm.types.PatternInstructionList  tomMatch119NameNumber_freshVar_1=tom_get_slot_VisitTerm_AstPatternInstructionList(tomMatch119NameNumberfreshSubject_1);{  tom.engine.adt.tomoption.types.OptionList  tomMatch119NameNumber_freshVar_2=tom_get_slot_VisitTerm_Option(tomMatch119NameNumberfreshSubject_1);{  tom.engine.adt.tomtype.types.TomType  tom_visitType=tomMatch119NameNumber_freshVar_0;{  tom.engine.adt.tomoption.types.OptionList  tom_options=tomMatch119NameNumber_freshVar_2;if ( true ) {

          String fileName =findOriginTrackingFileName(tom_options);
          if (tom_is_sort_TomType(tom_visitType)) {{  tom.engine.adt.tomtype.types.TomType  tomMatch118NameNumberfreshSubject_1=(( tom.engine.adt.tomtype.types.TomType )tom_visitType);if (tom_is_fun_sym_TomTypeAlone(tomMatch118NameNumberfreshSubject_1)) {{  String  tomMatch118NameNumber_freshVar_0=tom_get_slot_TomTypeAlone_String(tomMatch118NameNumberfreshSubject_1);if ( true ) {

              messageError(fileName,
                  findOriginTrackingLine(tom_options),
                  TomMessage.unknownVisitedType,
                  new Object[]{(tomMatch118NameNumber_freshVar_0)});
            }}}if (tom_is_fun_sym_Type(tomMatch118NameNumberfreshSubject_1)) {{  tom.engine.adt.tomtype.types.TomType  tomMatch118NameNumber_freshVar_1=tom_get_slot_Type_TomType(tomMatch118NameNumberfreshSubject_1);{  tom.engine.adt.tomtype.types.TomType  tomMatch118NameNumber_freshVar_2=tom_get_slot_Type_TlType(tomMatch118NameNumberfreshSubject_1);if (tom_is_fun_sym_ASTTomType(tomMatch118NameNumber_freshVar_1)) {{  String  tomMatch118NameNumber_freshVar_3=tom_get_slot_ASTTomType_String(tomMatch118NameNumber_freshVar_1);{  String  tom_ASTVisitType=tomMatch118NameNumber_freshVar_3;if (tom_is_fun_sym_TLType(tomMatch118NameNumber_freshVar_2)) {{  tom.engine.adt.tomsignature.types.TargetLanguage  tomMatch118NameNumber_freshVar_4=tom_get_slot_TLType_Tl(tomMatch118NameNumber_freshVar_2);{  tom.engine.adt.tomsignature.types.TargetLanguage  tom_TLVisitType=tomMatch118NameNumber_freshVar_4;if ( true ) {

              //check that all visitType have same visitorFwd

              currentVisitorFwd = symbolTable().getForwardType(tom_ASTVisitType);

              //noVisitorFwd defined for visitType
              if (currentVisitorFwd == null || currentVisitorFwd == tom_make_EmptyForward()){ 
                messageError(fileName,tom_TLVisitType.getStart().getLine(),
                    TomMessage.noVisitorForward,
                    new Object[]{(tom_ASTVisitType)});
              } else if (visitorFwd == null) {
                //first visit 
                visitorFwd = currentVisitorFwd;
              } else {
                //check if current visitor equals to previous visitor
                if (currentVisitorFwd != visitorFwd){ 
                  messageError(fileName,tom_TLVisitType.getStart().getLine(),
                      TomMessage.differentVisitorForward,
                      new Object[]{visitorFwd.getString(),currentVisitorFwd.getString()});
                }
              }
              verifyMatchVariable(tomMatch119NameNumber_freshVar_1);
            }}}}}}}}}}}}

        }}}}}}}}}

      // next visit
      list = list.getTailconcTomVisit();
    }
  }

  private void verifyVariableTypeListCoherence(ArrayList list) {
    // compute multiplicities
    //System.out.println("list = " + list);
    HashMap map = new HashMap();
    Iterator it = list.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName name = variable.getAstName();
      if(map.containsKey(name)) {
        TomTerm var = (TomTerm)map.get(name);
        //System.out.println("variable = " + variable);
        //System.out.println("var = " + var);
        TomType type1 = var.getAstType();
        TomType type2 = variable.getAstType();
        if(!(type1==type2)) {
          messageError(findOriginTrackingFileName(variable.getOption()),
              findOriginTrackingLine(variable.getOption()),
              TomMessage.incoherentVariable,
              new Object[]{name.getString(), TomBase.getTomType(type1), TomBase.getTomType(type2)});
        }
      } else {
        map.put(name, variable);
      }
    }
  }

} // class TomTypeChecker
