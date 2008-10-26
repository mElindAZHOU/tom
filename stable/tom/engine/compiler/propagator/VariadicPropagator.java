/* Generated by TOM (version 2.6): Do not edit this file *//*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2008, INRIA
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
 * Radu Kopetz e-mail: Radu.Kopetz@loria.fr
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/
package tom.engine.compiler.propagator;

import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomterm.types.tomterm.*;
import tom.library.sl.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.compiler.*;
import tom.engine.TomBase;
import tom.engine.exception.TomRuntimeException;
import java.util.ArrayList;
import tom.engine.compiler.Compiler;

/**
 * Syntactic propagator
 */
public class VariadicPropagator implements IBasePropagator {

//--------------------------------------------------------	
  /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */  /* Generated by TOM (version 2.6): Do not edit this file */    public static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {     if( l1.isEmptyconcTomName() ) {       return l2;     } else if( l2.isEmptyconcTomName() ) {       return l1;     } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;     } else {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;     }   }   public static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end== tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;   }      public static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyAndConstraint() ) {       return l2;     } else if( l2.isEmptyAndConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ).isEmptyAndConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),tom_append_list_AndConstraint((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;     }   }   public static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),end,tail)) ;   }      public static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   public static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }      public static   tom.engine.adt.tomslot.types.SlotList  tom_append_list_concSlot( tom.engine.adt.tomslot.types.SlotList l1,  tom.engine.adt.tomslot.types.SlotList  l2) {     if( l1.isEmptyconcSlot() ) {       return l2;     } else if( l2.isEmptyconcSlot() ) {       return l1;     } else if(  l1.getTailconcSlot() .isEmptyconcSlot() ) {       return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,l2) ;     } else {       return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,tom_append_list_concSlot( l1.getTailconcSlot() ,l2)) ;     }   }   public static   tom.engine.adt.tomslot.types.SlotList  tom_get_slice_concSlot( tom.engine.adt.tomslot.types.SlotList  begin,  tom.engine.adt.tomslot.types.SlotList  end, tom.engine.adt.tomslot.types.SlotList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcSlot()  ||  (end== tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( begin.getHeadconcSlot() ,( tom.engine.adt.tomslot.types.SlotList )tom_get_slice_concSlot( begin.getTailconcSlot() ,end,tail)) ;   }    /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */   public static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   public static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }    /* Generated by TOM (version 2.6): Do not edit this file */ /* Generated by TOM (version 2.6): Do not edit this file */public static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}   

//--------------------------------------------------------

  public Constraint propagate(Constraint constraint) throws VisitFailure {
    Constraint res =  (Constraint)tom_make_TopDown(tom_make_VariadicPatternMatching()).visitLight(constraint);		
    return res;
  }	

  public static class VariadicPatternMatching extends tom.library.sl.BasicStrategy {public VariadicPatternMatching() {super(( new tom.library.sl.Identity() ));}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{ Object tomMatch178NameNumber_freshVar_0=tom__arg;if ( (tomMatch178NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch178NameNumber_freshSubject_1=(( tom.engine.adt.tomconstraint.types.Constraint )tomMatch178NameNumber_freshVar_0);{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch178NameNumber_freshVar_3=tomMatch178NameNumber_freshSubject_1;if ( (tomMatch178NameNumber_freshVar_3 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch178NameNumber_freshVar_1= tomMatch178NameNumber_freshVar_3.getPattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch178NameNumber_freshVar_2= tomMatch178NameNumber_freshVar_3.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch178NameNumber_freshVar_6=tomMatch178NameNumber_freshVar_1;if ( (tomMatch178NameNumber_freshVar_6 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {{  tom.engine.adt.tomname.types.TomNameList  tomMatch178NameNumber_freshVar_4= tomMatch178NameNumber_freshVar_6.getNameList() ;{  tom.engine.adt.tomslot.types.SlotList  tomMatch178NameNumber_freshVar_5= tomMatch178NameNumber_freshVar_6.getSlots() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch178NameNumber_freshVar_7=tomMatch178NameNumber_freshVar_4;if ( ((tomMatch178NameNumber_freshVar_7 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch178NameNumber_freshVar_7 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {if (!( tomMatch178NameNumber_freshVar_7.isEmptyconcTomName() )) {{  tom.engine.adt.tomname.types.TomName  tomMatch178NameNumber_freshVar_10= tomMatch178NameNumber_freshVar_7.getHeadconcTomName() ;if ( (tomMatch178NameNumber_freshVar_10 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {{  String  tomMatch178NameNumber_freshVar_9= tomMatch178NameNumber_freshVar_10.getString() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch178NameNumber_freshVar_8= tomMatch178NameNumber_freshVar_7.getTailconcTomName() ;if ( tomMatch178NameNumber_freshVar_8.isEmptyconcTomName() ) {{  tom.engine.adt.tomconstraint.types.Constraint  tom_m=tomMatch178NameNumber_freshSubject_1;{ boolean tomMatch178NameNumber_freshVar_12= false ;{  tom.engine.adt.tomslot.types.SlotList  tomMatch178NameNumber_freshVar_11=tomMatch178NameNumber_freshVar_5;if ( ((tomMatch178NameNumber_freshVar_11 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch178NameNumber_freshVar_11 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {if ( tomMatch178NameNumber_freshVar_11.isEmptyconcSlot() ) {tomMatch178NameNumber_freshVar_12= true ;}}}if ((tomMatch178NameNumber_freshVar_12 ==  false )) {











        // if this is not a list, nothing to do
        TomSymbol symb = Compiler.getSymbolTable().getSymbolFromName(tomMatch178NameNumber_freshVar_9);
        if(!TomBase.isListOperator(symb)) {
          return tom_m; 
        }
        Constraint detachedConstr = GeneralPurposePropagator.detachSublists(tom_m);
        if(detachedConstr != tom_m) {
          return detachedConstr; 
        }
      }}}}}}}}}}}}}}}}}}}}}}{ Object tomMatch178NameNumber_freshVar_13=tom__arg;if ( (tomMatch178NameNumber_freshVar_13 instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch178NameNumber_freshSubject_1=(( tom.engine.adt.tomconstraint.types.Constraint )tomMatch178NameNumber_freshVar_13);{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch178NameNumber_freshVar_16=tomMatch178NameNumber_freshSubject_1;if ( (tomMatch178NameNumber_freshVar_16 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch178NameNumber_freshVar_14= tomMatch178NameNumber_freshVar_16.getPattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch178NameNumber_freshVar_15= tomMatch178NameNumber_freshVar_16.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch178NameNumber_freshVar_21=tomMatch178NameNumber_freshVar_14;if ( (tomMatch178NameNumber_freshVar_21 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {{  tom.engine.adt.tomoption.types.OptionList  tomMatch178NameNumber_freshVar_17= tomMatch178NameNumber_freshVar_21.getOption() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch178NameNumber_freshVar_18= tomMatch178NameNumber_freshVar_21.getNameList() ;{  tom.engine.adt.tomslot.types.SlotList  tomMatch178NameNumber_freshVar_19= tomMatch178NameNumber_freshVar_21.getSlots() ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch178NameNumber_freshVar_20= tomMatch178NameNumber_freshVar_21.getConstraints() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch178NameNumber_freshVar_22=tomMatch178NameNumber_freshVar_18;if ( ((tomMatch178NameNumber_freshVar_22 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch178NameNumber_freshVar_22 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {if (!( tomMatch178NameNumber_freshVar_22.isEmptyconcTomName() )) {{  tom.engine.adt.tomname.types.TomName  tomMatch178NameNumber_freshVar_26= tomMatch178NameNumber_freshVar_22.getHeadconcTomName() ;if ( (tomMatch178NameNumber_freshVar_26 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {{  String  tomMatch178NameNumber_freshVar_25= tomMatch178NameNumber_freshVar_26.getString() ;{  tom.engine.adt.tomname.types.TomName  tom_name= tomMatch178NameNumber_freshVar_22.getHeadconcTomName() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch178NameNumber_freshVar_23= tomMatch178NameNumber_freshVar_22.getTailconcTomName() ;{  tom.engine.adt.tomslot.types.SlotList  tom_slots=tomMatch178NameNumber_freshVar_19;{  tom.engine.adt.tomterm.types.TomTerm  tom_g=tomMatch178NameNumber_freshVar_15;{  tom.engine.adt.tomconstraint.types.Constraint  tom_m=tomMatch178NameNumber_freshSubject_1;{ boolean tomMatch178NameNumber_freshVar_29= false ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch178NameNumber_freshVar_27=tomMatch178NameNumber_freshVar_15;if ( (tomMatch178NameNumber_freshVar_27 instanceof tom.engine.adt.tomterm.types.tomterm.SymbolOf) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch178NameNumber_freshVar_28=tomMatch178NameNumber_freshVar_15;if ( (tomMatch178NameNumber_freshVar_28==tom_g) ) {tomMatch178NameNumber_freshVar_29= true ;}}}}if ((tomMatch178NameNumber_freshVar_29 ==  false )) {

















        // if this is not a list, nothing to do
        TomSymbol symb = Compiler.getSymbolTable().getSymbolFromName(tomMatch178NameNumber_freshVar_25);
        if(!TomBase.isListOperator(symb)) {
          return tom_m;
        }        
        // declare fresh variable
        TomType listType = Compiler.getTermTypeFromTerm(tomMatch178NameNumber_freshVar_14);
        TomTerm freshVariable = Compiler.getFreshVariableStar(listType);				
        Constraint freshVarDeclaration =  tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(freshVariable, tom_g) ;
        Constraint isSymbolConstr =  tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(tomMatch178NameNumber_freshVar_17, tomMatch178NameNumber_freshVar_18,  tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,  tom.engine.adt.tomterm.types.tomterm.SymbolOf.make(freshVariable) ) ;
        Constraint l =  tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ;        
        {{ Object tomMatch179NameNumber_freshVar_0=tom_slots;if ( (tomMatch179NameNumber_freshVar_0 instanceof tom.engine.adt.tomslot.types.SlotList) ) {{  tom.engine.adt.tomslot.types.SlotList  tomMatch179NameNumber_freshSubject_1=(( tom.engine.adt.tomslot.types.SlotList )tomMatch179NameNumber_freshVar_0);{  tom.engine.adt.tomslot.types.SlotList  tomMatch179NameNumber_freshVar_1=tomMatch179NameNumber_freshSubject_1;if ( ((tomMatch179NameNumber_freshVar_1 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch179NameNumber_freshVar_1 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {if ( tomMatch179NameNumber_freshVar_1.isEmptyconcSlot() ) {

            l = tom_append_list_AndConstraint(l, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.EmptyListConstraint.make(tom_name, freshVariable) , tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) );
          }}}}}}{ Object tomMatch179NameNumber_freshVar_2=tom_slots;if ( (tomMatch179NameNumber_freshVar_2 instanceof tom.engine.adt.tomslot.types.SlotList) ) {{  tom.engine.adt.tomslot.types.SlotList  tomMatch179NameNumber_freshSubject_1=(( tom.engine.adt.tomslot.types.SlotList )tomMatch179NameNumber_freshVar_2);{  tom.engine.adt.tomslot.types.SlotList  tomMatch179NameNumber_freshVar_3=tomMatch179NameNumber_freshSubject_1;if ( ((tomMatch179NameNumber_freshVar_3 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch179NameNumber_freshVar_3 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {{  tom.engine.adt.tomslot.types.SlotList  tomMatch179NameNumber_begin_5=tomMatch179NameNumber_freshVar_3;{  tom.engine.adt.tomslot.types.SlotList  tomMatch179NameNumber_end_6=tomMatch179NameNumber_freshVar_3;do {{{  tom.engine.adt.tomslot.types.SlotList  tomMatch179NameNumber_freshVar_4=tomMatch179NameNumber_end_6;if (!( tomMatch179NameNumber_freshVar_4.isEmptyconcSlot() )) {{  tom.engine.adt.tomslot.types.Slot  tomMatch179NameNumber_freshVar_10= tomMatch179NameNumber_freshVar_4.getHeadconcSlot() ;if ( (tomMatch179NameNumber_freshVar_10 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch179NameNumber_freshVar_9= tomMatch179NameNumber_freshVar_10.getAppl() ;{  tom.engine.adt.tomterm.types.TomTerm  tom_appl=tomMatch179NameNumber_freshVar_9;{  tom.engine.adt.tomslot.types.SlotList  tomMatch179NameNumber_freshVar_7= tomMatch179NameNumber_freshVar_4.getTailconcSlot() ;{  tom.engine.adt.tomslot.types.SlotList  tom_X=tomMatch179NameNumber_freshVar_7;

            TomTerm newFreshVarList = Compiler.getFreshVariableStar(listType);            
mAppl:      {{ Object tomMatch180NameNumber_freshVar_0=tom_appl;if ( (tomMatch180NameNumber_freshVar_0 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch180NameNumber_freshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )tomMatch180NameNumber_freshVar_0);{  tom.engine.adt.tomterm.types.TomTerm  tomMatch180NameNumber_freshVar_1=tomMatch180NameNumber_freshSubject_1;{ boolean tomMatch180NameNumber_freshVar_2= false ;if ( (tomMatch180NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {tomMatch180NameNumber_freshVar_2= true ;} else {if ( (tomMatch180NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar) ) {tomMatch180NameNumber_freshVar_2= true ;}}if ((tomMatch180NameNumber_freshVar_2 ==  true )) {

                
                // if it is the last element               
                if(tom_X.length() == 0) {
                  // we should only assign it, without generating a loop
                  l = tom_append_list_AndConstraint(l, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tom_appl, freshVariable) , tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) );
                } else {
                  TomTerm beginSublist = Compiler.getBeginVariableStar(listType);
                  TomTerm endSublist = Compiler.getEndVariableStar(listType);              
                  l = tom_append_list_AndConstraint(l, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(beginSublist, freshVariable) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(endSublist, freshVariable) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tom_appl,  tom.engine.adt.tomterm.types.tomterm.VariableHeadList.make(tom_name, beginSublist, endSublist) ) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(newFreshVarList, endSublist) , tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ) ) ) )



;
                }
                break mAppl;
              }}}}}}{ Object tomMatch180NameNumber_freshVar_3=tom_appl;if ( (tomMatch180NameNumber_freshVar_3 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch180NameNumber_freshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )tomMatch180NameNumber_freshVar_3);

                l = tom_append_list_AndConstraint(l, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.Negate.make( tom.engine.adt.tomconstraint.types.constraint.EmptyListConstraint.make(tom_name, freshVariable) ) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tom_appl,  tom.engine.adt.tomterm.types.tomterm.ListHead.make(tom_name, Compiler.getTermTypeFromTerm(tom_appl), freshVariable) ) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(newFreshVarList,  tom.engine.adt.tomterm.types.tomterm.ListTail.make(tom_name, freshVariable) ) , tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ) ) )


;
                // for the last element, we should also check that the list ends
                if(tom_X.length() == 0) {                  
                  l = tom_append_list_AndConstraint(l, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.EmptyListConstraint.make(tom_name, newFreshVarList) , tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) );
                }
              }}}}
// end match
            freshVariable = newFreshVarList;
          }}}}}}}}if ( tomMatch179NameNumber_end_6.isEmptyconcSlot() ) {tomMatch179NameNumber_end_6=tomMatch179NameNumber_begin_5;} else {tomMatch179NameNumber_end_6= tomMatch179NameNumber_end_6.getTailconcSlot() ;}}} while(!( (tomMatch179NameNumber_end_6==tomMatch179NameNumber_begin_5) ));}}}}}}}}
// end match
        // fresh var declaration + add head equality condition + detached constraints
        l =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(freshVarDeclaration, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(isSymbolConstr, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(ConstraintPropagator.performDetach(tom_m),tom_append_list_AndConstraint(l, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )) ) ) ;
        return l;
      }}}}}}}}}}}}}}}}}}}}}}}}}}}return _visit_Constraint(tom__arg,introspector); }public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));} else {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {return visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}public static  tom.library.sl.Strategy  tom_make_VariadicPatternMatching() { return new VariadicPatternMatching();}

// end %strategy

}
