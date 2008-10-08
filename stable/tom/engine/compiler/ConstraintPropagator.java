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
package tom.engine.compiler;

import java.util.ArrayList;

import tom.engine.TomBase;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.compiler.propagator.*;
import tom.engine.exception.TomRuntimeException;
import tom.library.sl.*;

/**
 * This class is in charge with launching all the propagators,
 * until no more propagations can be made 
 */
public class ConstraintPropagator {

//------------------------------------------------------	
  /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */  /* Generated by TOM (version 2.6): Do not edit this file */    public static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyAndConstraint() ) {       return l2;     } else if( l2.isEmptyAndConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ).isEmptyAndConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),tom_append_list_AndConstraint((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;     }   }   public static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),end,tail)) ;   }      public static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   public static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }    /* Generated by TOM (version 2.6): Do not edit this file */             /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */ /* Generated by TOM (version 2.6): Do not edit this file */ /* Generated by TOM (version 2.6): Do not edit this file */   /* Generated by TOM (version 2.6): Do not edit this file */ 



//------------------------------------------------------

  private static final String propagatorsPackage = "tom.engine.compiler.propagator.";

  private static final String[] propagatorsNames = {"SyntacticPropagator","VariadicPropagator","ArrayPropagator","GeneralPurposePropagator"};

  public static Constraint performPropagations(Constraint constraintToCompile) 
    throws ClassNotFoundException,InstantiationException,IllegalAccessException,VisitFailure{
    
    // counts the propagators that didn't change the expression
    int propCounter = 0;
    int propNb = propagatorsNames.length;    	

    // cache the propagators
    IBasePropagator[] prop = new IBasePropagator[propNb];
    for(int i=0 ; i < propNb ; i++) {
      prop[i] = (IBasePropagator)Class.forName(propagatorsPackage + propagatorsNames[i]).newInstance();
    }
    
    Constraint result= null;
    mainLoop: while(true) {
      for(int i=0 ; i < propNb ; i++) {
        result = prop[i].propagate(constraintToCompile);
        // if nothing was done, start counting 
        propCounter = (result == constraintToCompile) ? (propCounter+1) : 0;        
        // if we applied all the propagators and nothing changed,
        // it's time to stop
        if(propCounter == propNb) { break mainLoop; }
        // reinitialize
        constraintToCompile = result;
      }
    } // end while    
    return result;
  }
  
  
  //[radu] TODO: looks a little bit too complicated: I think we can treat the both cases
  // uniformly
  
  /**
   * Detaches the annotations
   *  
   * a@...b@g(y) << t -> g(y) << t /\ a << t /\ ... /\ b << t
   * 
   * For variableStars: a@...b@X* << t -> Z* << t /\ X* << Z* /\ a << Z* /\ ... /\ b << Z*  
   * This is because the varStars can have at the rhs something that will generate loops,
   * and we don't want to duplicate that to the constraints  
   */
  public static Constraint performDetach(Constraint subject) {    
    Constraint result =  tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ; 
    {{ Object tomMatch149NameNumber_freshVar_0=subject;if ( (tomMatch149NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch149NameNumber_freshSubject_1=(( tom.engine.adt.tomconstraint.types.Constraint )tomMatch149NameNumber_freshVar_0);{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch149NameNumber_freshVar_3=tomMatch149NameNumber_freshSubject_1;if ( (tomMatch149NameNumber_freshVar_3 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch149NameNumber_freshVar_1= tomMatch149NameNumber_freshVar_3.getPattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch149NameNumber_freshVar_2= tomMatch149NameNumber_freshVar_3.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch149NameNumber_freshVar_5=tomMatch149NameNumber_freshVar_1;{ boolean tomMatch149NameNumber_freshVar_9= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_4= null ;if ( (tomMatch149NameNumber_freshVar_5 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {{tomMatch149NameNumber_freshVar_9= true ;tomMatch149NameNumber_freshVar_4= tomMatch149NameNumber_freshVar_5.getConstraints() ;}} else {if ( (tomMatch149NameNumber_freshVar_5 instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {{tomMatch149NameNumber_freshVar_9= true ;tomMatch149NameNumber_freshVar_4= tomMatch149NameNumber_freshVar_5.getConstraints() ;}} else {if ( (tomMatch149NameNumber_freshVar_5 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable) ) {{tomMatch149NameNumber_freshVar_9= true ;tomMatch149NameNumber_freshVar_4= tomMatch149NameNumber_freshVar_5.getConstraints() ;}}}}if ((tomMatch149NameNumber_freshVar_9 ==  true )) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tom_constraints=tomMatch149NameNumber_freshVar_4;{ boolean tomMatch149NameNumber_freshVar_8= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_6=tomMatch149NameNumber_freshVar_4;if ( ((tomMatch149NameNumber_freshVar_6 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch149NameNumber_freshVar_6 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_7=tomMatch149NameNumber_freshVar_4;if ( (tomMatch149NameNumber_freshVar_7==tom_constraints) ) {if ( tomMatch149NameNumber_freshVar_6.isEmptyconcConstraint() ) {tomMatch149NameNumber_freshVar_8= true ;}}}}}if ((tomMatch149NameNumber_freshVar_8 ==  false )) {{{ Object tomMatch150NameNumber_freshVar_0=tom_constraints;if ( (tomMatch150NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.ConstraintList) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch150NameNumber_freshSubject_1=(( tom.engine.adt.tomconstraint.types.ConstraintList )tomMatch150NameNumber_freshVar_0);{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch150NameNumber_freshVar_1=tomMatch150NameNumber_freshSubject_1;if ( ((tomMatch150NameNumber_freshVar_1 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch150NameNumber_freshVar_1 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch150NameNumber_begin_3=tomMatch150NameNumber_freshVar_1;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch150NameNumber_end_4=tomMatch150NameNumber_freshVar_1;do {{{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch150NameNumber_freshVar_2=tomMatch150NameNumber_end_4;if (!( tomMatch150NameNumber_freshVar_2.isEmptyconcConstraint() )) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch150NameNumber_freshVar_8= tomMatch150NameNumber_freshVar_2.getHeadconcConstraint() ;if ( (tomMatch150NameNumber_freshVar_8 instanceof tom.engine.adt.tomconstraint.types.constraint.AssignTo) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch150NameNumber_freshVar_7= tomMatch150NameNumber_freshVar_8.getVariable() ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch150NameNumber_freshVar_5= tomMatch150NameNumber_freshVar_2.getTailconcConstraint() ;



            // add constraint to the list
            result =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tomMatch150NameNumber_freshVar_7, tomMatch149NameNumber_freshVar_2) ,tom_append_list_AndConstraint(result, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )) ;                                                                                                                       
          }}}}}}if ( tomMatch150NameNumber_end_4.isEmptyconcConstraint() ) {tomMatch150NameNumber_end_4=tomMatch150NameNumber_begin_3;} else {tomMatch150NameNumber_end_4= tomMatch150NameNumber_end_4.getTailconcConstraint() ;}}} while(!( (tomMatch150NameNumber_end_4==tomMatch150NameNumber_begin_3) ));}}}}}}}}
// end match   
      }}}}}}}}}}}}}}{ Object tomMatch149NameNumber_freshVar_10=subject;if ( (tomMatch149NameNumber_freshVar_10 instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch149NameNumber_freshSubject_1=(( tom.engine.adt.tomconstraint.types.Constraint )tomMatch149NameNumber_freshVar_10);{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch149NameNumber_freshVar_13=tomMatch149NameNumber_freshSubject_1;if ( (tomMatch149NameNumber_freshVar_13 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch149NameNumber_freshVar_11= tomMatch149NameNumber_freshVar_13.getPattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch149NameNumber_freshVar_12= tomMatch149NameNumber_freshVar_13.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch149NameNumber_freshVar_16=tomMatch149NameNumber_freshVar_11;{ boolean tomMatch149NameNumber_freshVar_20= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_15= null ;{  tom.engine.adt.tomtype.types.TomType  tomMatch149NameNumber_freshVar_14= null ;if ( (tomMatch149NameNumber_freshVar_16 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {{tomMatch149NameNumber_freshVar_20= true ;tomMatch149NameNumber_freshVar_14= tomMatch149NameNumber_freshVar_16.getAstType() ;tomMatch149NameNumber_freshVar_15= tomMatch149NameNumber_freshVar_16.getConstraints() ;}} else {if ( (tomMatch149NameNumber_freshVar_16 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar) ) {{tomMatch149NameNumber_freshVar_20= true ;tomMatch149NameNumber_freshVar_14= tomMatch149NameNumber_freshVar_16.getAstType() ;tomMatch149NameNumber_freshVar_15= tomMatch149NameNumber_freshVar_16.getConstraints() ;}}}if ((tomMatch149NameNumber_freshVar_20 ==  true )) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tom_constraints=tomMatch149NameNumber_freshVar_15;{ boolean tomMatch149NameNumber_freshVar_19= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_17=tomMatch149NameNumber_freshVar_15;if ( ((tomMatch149NameNumber_freshVar_17 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch149NameNumber_freshVar_17 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_18=tomMatch149NameNumber_freshVar_15;if ( (tomMatch149NameNumber_freshVar_18==tom_constraints) ) {if ( tomMatch149NameNumber_freshVar_17.isEmptyconcConstraint() ) {tomMatch149NameNumber_freshVar_19= true ;}}}}}if ((tomMatch149NameNumber_freshVar_19 ==  false )) {
        
        TomTerm freshVariable = Compiler.getFreshVariableStar(tomMatch149NameNumber_freshVar_14);
        {{ Object tomMatch151NameNumber_freshVar_0=tom_constraints;if ( (tomMatch151NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.ConstraintList) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch151NameNumber_freshSubject_1=(( tom.engine.adt.tomconstraint.types.ConstraintList )tomMatch151NameNumber_freshVar_0);{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch151NameNumber_freshVar_1=tomMatch151NameNumber_freshSubject_1;if ( ((tomMatch151NameNumber_freshVar_1 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch151NameNumber_freshVar_1 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch151NameNumber_begin_3=tomMatch151NameNumber_freshVar_1;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch151NameNumber_end_4=tomMatch151NameNumber_freshVar_1;do {{{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch151NameNumber_freshVar_2=tomMatch151NameNumber_end_4;if (!( tomMatch151NameNumber_freshVar_2.isEmptyconcConstraint() )) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch151NameNumber_freshVar_8= tomMatch151NameNumber_freshVar_2.getHeadconcConstraint() ;if ( (tomMatch151NameNumber_freshVar_8 instanceof tom.engine.adt.tomconstraint.types.constraint.AssignTo) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch151NameNumber_freshVar_7= tomMatch151NameNumber_freshVar_8.getVariable() ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch151NameNumber_freshVar_5= tomMatch151NameNumber_freshVar_2.getTailconcConstraint() ;

            result =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tomMatch151NameNumber_freshVar_7, freshVariable) ,tom_append_list_AndConstraint(result, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )) ;                                                                                                                       
          }}}}}}if ( tomMatch151NameNumber_end_4.isEmptyconcConstraint() ) {tomMatch151NameNumber_end_4=tomMatch151NameNumber_begin_3;} else {tomMatch151NameNumber_end_4= tomMatch151NameNumber_end_4.getTailconcConstraint() ;}}} while(!( (tomMatch151NameNumber_end_4==tomMatch151NameNumber_begin_3) ));}}}}}}}}
// end match   
        result =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(freshVariable, tomMatch149NameNumber_freshVar_12) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tomMatch149NameNumber_freshVar_11.setConstraints( tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ), freshVariable) ,tom_append_list_AndConstraint(result, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )) ) 
;
      }}}}}}}}}}}}}}}}

    return result;
  }  
}
