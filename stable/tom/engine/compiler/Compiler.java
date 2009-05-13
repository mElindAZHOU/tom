/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2009, INRIA
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

import tom.engine.tools.TomGenericPlugin;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomname.types.tomname.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomterm.types.tomterm.*;
import tom.library.sl.*;
import tom.engine.tools.SymbolTable;
import tom.engine.exception.TomRuntimeException;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.TomBase;
import tom.engine.adt.tomconstraint.types.*;
import java.util.*;
import tom.engine.tools.ASTFactory;
import tom.platform.adt.platformoption.types.PlatformOptionList;
import tom.platform.OptionParser;
import tom.engine.tools.Tools;
import java.util.logging.Level;
import tom.engine.TomMessage;

/**
 * Tom compiler based on constraints.
 * 
 * It controls different phases of compilation:
 * - propagation of constraints
 * - instruction generation from constraints
 * - ...   
 */
public class Compiler extends TomGenericPlugin {

        private static   tom.engine.adt.tominstruction.types.InstructionList  tom_append_list_concInstruction( tom.engine.adt.tominstruction.types.InstructionList l1,  tom.engine.adt.tominstruction.types.InstructionList  l2) {     if( l1.isEmptyconcInstruction() ) {       return l2;     } else if( l2.isEmptyconcInstruction() ) {       return l1;     } else if(  l1.getTailconcInstruction() .isEmptyconcInstruction() ) {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,tom_append_list_concInstruction( l1.getTailconcInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.InstructionList  tom_get_slice_concInstruction( tom.engine.adt.tominstruction.types.InstructionList  begin,  tom.engine.adt.tominstruction.types.InstructionList  end, tom.engine.adt.tominstruction.types.InstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcInstruction()  ||  (end== tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( begin.getHeadconcInstruction() ,( tom.engine.adt.tominstruction.types.InstructionList )tom_get_slice_concInstruction( begin.getTailconcInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_append_list_concConstraintInstruction( tom.engine.adt.tominstruction.types.ConstraintInstructionList l1,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  l2) {     if( l1.isEmptyconcConstraintInstruction() ) {       return l2;     } else if( l2.isEmptyconcConstraintInstruction() ) {       return l1;     } else if(  l1.getTailconcConstraintInstruction() .isEmptyconcConstraintInstruction() ) {       return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( l1.getHeadconcConstraintInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( l1.getHeadconcConstraintInstruction() ,tom_append_list_concConstraintInstruction( l1.getTailconcConstraintInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_get_slice_concConstraintInstruction( tom.engine.adt.tominstruction.types.ConstraintInstructionList  begin,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  end, tom.engine.adt.tominstruction.types.ConstraintInstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraintInstruction()  ||  (end== tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( begin.getHeadconcConstraintInstruction() ,( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_get_slice_concConstraintInstruction( begin.getTailconcConstraintInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tomname.types.TomNumberList  tom_append_list_concTomNumber( tom.engine.adt.tomname.types.TomNumberList l1,  tom.engine.adt.tomname.types.TomNumberList  l2) {     if( l1.isEmptyconcTomNumber() ) {       return l2;     } else if( l2.isEmptyconcTomNumber() ) {       return l1;     } else if(  l1.getTailconcTomNumber() .isEmptyconcTomNumber() ) {       return  tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( l1.getHeadconcTomNumber() ,l2) ;     } else {       return  tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( l1.getHeadconcTomNumber() ,tom_append_list_concTomNumber( l1.getTailconcTomNumber() ,l2)) ;     }   }   private static   tom.engine.adt.tomname.types.TomNumberList  tom_get_slice_concTomNumber( tom.engine.adt.tomname.types.TomNumberList  begin,  tom.engine.adt.tomname.types.TomNumberList  end, tom.engine.adt.tomname.types.TomNumberList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomNumber()  ||  (end== tom.engine.adt.tomname.types.tomnumberlist.EmptyconcTomNumber.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( begin.getHeadconcTomNumber() ,( tom.engine.adt.tomname.types.TomNumberList )tom_get_slice_concTomNumber( begin.getTailconcTomNumber() ,end,tail)) ;   }      private static   tom.engine.adt.tomterm.types.TomList  tom_append_list_concTomTerm( tom.engine.adt.tomterm.types.TomList l1,  tom.engine.adt.tomterm.types.TomList  l2) {     if( l1.isEmptyconcTomTerm() ) {       return l2;     } else if( l2.isEmptyconcTomTerm() ) {       return l1;     } else if(  l1.getTailconcTomTerm() .isEmptyconcTomTerm() ) {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,l2) ;     } else {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,tom_append_list_concTomTerm( l1.getTailconcTomTerm() ,l2)) ;     }   }   private static   tom.engine.adt.tomterm.types.TomList  tom_get_slice_concTomTerm( tom.engine.adt.tomterm.types.TomList  begin,  tom.engine.adt.tomterm.types.TomList  end, tom.engine.adt.tomterm.types.TomList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomTerm()  ||  (end== tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( begin.getHeadconcTomTerm() ,( tom.engine.adt.tomterm.types.TomList )tom_get_slice_concTomTerm( begin.getTailconcTomTerm() ,end,tail)) ;   }      private static   tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList l1,  tom.engine.adt.tomoption.types.OptionList  l2) {     if( l1.isEmptyconcOption() ) {       return l2;     } else if( l2.isEmptyconcOption() ) {       return l1;     } else if(  l1.getTailconcOption() .isEmptyconcOption() ) {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,l2) ;     } else {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,tom_append_list_concOption( l1.getTailconcOption() ,l2)) ;     }   }   private static   tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end, tom.engine.adt.tomoption.types.OptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcOption()  ||  (end== tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( begin.getHeadconcOption() ,( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption( begin.getTailconcOption() ,end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyAndConstraint() ) {       return l2;     } else if( l2.isEmptyAndConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {       if(  l1.getTailAndConstraint() .isEmptyAndConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( l1.getHeadAndConstraint() ,l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( l1.getHeadAndConstraint() ,tom_append_list_AndConstraint( l1.getTailAndConstraint() ,l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }         private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {         return ( (l2==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):new tom.library.sl.Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) );       } else {         return ( (tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):new tom.library.sl.Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }      private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Choice) )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {         return ( (l2==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ):new tom.library.sl.Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) );       } else {         return ( (tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ):new tom.library.sl.Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Choice(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin):new tom.library.sl.Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ):( null )),end,tail)) );   }      private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.ChoiceId) )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {         return ( (l2==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ):new tom.library.sl.ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) );       } else {         return ( (tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ):new tom.library.sl.ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.ChoiceId(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( (begin instanceof tom.library.sl.ChoiceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.ChoiceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin):new tom.library.sl.ChoiceId(((( (begin instanceof tom.library.sl.ChoiceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( (begin instanceof tom.library.sl.ChoiceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ):( null )),end,tail)) );   }     private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  v) { return ( ( (( (( null )==null)?( new tom.library.sl.Identity() ):new tom.library.sl.Choice(( new tom.library.sl.Identity() ),( null )) )==null)?v:new tom.library.sl.Choice(v,( (( null )==null)?( new tom.library.sl.Identity() ):new tom.library.sl.Choice(( new tom.library.sl.Identity() ),( null )) )) ) );}private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}private static  tom.library.sl.Strategy  tom_make_TopDownCollect( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),tom_make_Try(( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) ))) ) );}private static  tom.library.sl.Strategy  tom_make_TopDownIdStopOnSuccess( tom.library.sl.Strategy  v) { return  (( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.ChoiceId(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?v:new tom.library.sl.ChoiceId(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.ChoiceId(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) )) )) ;}     






  private static final String freshVarPrefix = "_freshVar_";
  private static final String freshBeginPrefix = "_begin_";
  private static final String freshEndPrefix = "_end_";

  private CompilerEnvironment compilerEnvironment;

  public CompilerEnvironment getCompilerEnvironment() {
    return compilerEnvironment;
  }
  
  private class CompilerEnvironment {
    
    /** few attributes */
    private SymbolTable symbolTable;
    private TomNumberList rootpath = null;
    // keeps track of the match number to insure distinct variables' 
    // names for distinct match constructs
    private int matchNumber = 0;
    // keeps track of the subject number to insure distinct variables' 
    // names when renaming subjects
    private int freshSubjectCounter = 0;
    private int freshVarCounter = 0;

    private ConstraintPropagator constraintPropagator; 
    private ConstraintGenerator constraintGenerator; 

    /** Constructor */
    public CompilerEnvironment() {
      super();
      this.constraintPropagator = new ConstraintPropagator(Compiler.this); 
      this.constraintGenerator = new ConstraintGenerator(Compiler.this); 
    }

    /** Accessor methods */
    public SymbolTable getSymbolTable() {
      return this.symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
      this.symbolTable = symbolTable;
    }

    public TomNumberList getRootpath() {
      return this.rootpath;
    }

    public void setRootpath(TomNumberList rootpath) {
      this.rootpath = rootpath;
    }

    public int getMatchNumber() {
      return this.matchNumber;
    }

    public int getFreshSubjectCounter() {
      return this.freshSubjectCounter;
    }

    public void setFreshSubjectCounter(int freshSubjectCounter) {
      this.freshSubjectCounter = freshSubjectCounter;
    }

    public int getFreshVarCounter() {
      return this.freshVarCounter;
    }
    
    public void setFreshVarCounter(int freshVarCounter) {
      this.freshVarCounter = freshVarCounter;
    }
   
    public ConstraintPropagator getConstraintPropagator() {
      return this.constraintPropagator;
    }

    public ConstraintGenerator getConstraintGenerator() {
      return this.constraintGenerator;
    }

    /** need more routines ? */

  } // class CompilerEnvironment


  /** some output suffixes */
  public static final String COMPILED_SUFFIX = ".tfix.compiled";

  /** the declared options string*/
  public static final String DECLARED_OPTIONS = "<options>" +
    "<boolean name='compile' altName='' description='Compiler (activated by default)' value='true'/>" +
    "</options>";

  /** Constructor */
  public Compiler() {
    super("Compiler");
    compilerEnvironment = new CompilerEnvironment();
  }

  protected static long startChrono = System.currentTimeMillis();
  public void run(Map informationTracker) {
    boolean intermediate = getOptionBooleanValue("intermediate");    
    //System.out.println("(debug) I'm in the Tom compiler : TSM"+getStreamManager().toString());
    try {
      TomTerm compiledTerm = compile((TomTerm)getWorkingTerm(),getStreamManager().getSymbolTable());
      //System.out.println("compiledTerm = \n" + compiledTerm);            
      Collection hashSet = new HashSet();
      TomTerm renamedTerm = tom_make_TopDownIdStopOnSuccess(tom_make_findRenameVariable(hashSet)).visitLight(compiledTerm);
      setWorkingTerm(renamedTerm);
      if(intermediate) {
        Tools.generateOutput(getStreamManager().getOutputFileName() + COMPILED_SUFFIX, renamedTerm);
      }
      getLogger().log(Level.INFO, TomMessage.tomCompilationPhase.getMessage(),
          Integer.valueOf((int)(System.currentTimeMillis()-startChrono)) );
    } catch (Exception e) {
      getLogger().log(Level.SEVERE, TomMessage.exceptionMessage.getMessage(),
          new Object[]{getStreamManager().getInputFileName(), "Compiler", e.getMessage()} );
      e.printStackTrace();
    }
  }

  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(Compiler.DECLARED_OPTIONS);
  }

  private TomTerm compile(TomTerm termToCompile,SymbolTable symbolTable) throws VisitFailure {
    getCompilerEnvironment().setSymbolTable(symbolTable);
    // we use TopDown and not TopDownIdStopOnSuccess to compile nested-match
    return tom_make_TopDown(tom_make_CompileMatch(this)).visitLight(termToCompile);		
  }

  // looks for a 'Match' instruction:
  // 1. transforms each sequence of patterns into a conjuction of MatchConstraint
  // 2. launch PropagationManager
  // 3. launch PreGenerator
  // 4. launch GenerationManager
  // 5. launch PostGenerator  
  // 6. transforms resulted expression into a CompiledMatch
  public static class CompileMatch extends tom.library.sl.AbstractBasicStrategy {private  Compiler  compiler;public CompileMatch( Compiler  compiler) {super(( new tom.library.sl.Identity() ));this.compiler=compiler;}public  Compiler  getcompiler() {return compiler;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.Match) ) { tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_constraintInstructionList= (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getConstraintInstructionList() ;

        
        compiler.getCompilerEnvironment().matchNumber++;
        compiler.getCompilerEnvironment().setRootpath( tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( tom.engine.adt.tomname.types.tomnumber.MatchNumber.make(compiler.getCompilerEnvironment().getMatchNumber()) , tom.engine.adt.tomname.types.tomnumberlist.EmptyconcTomNumber.make() ) );
        compiler.getCompilerEnvironment().setFreshSubjectCounter(0);
        compiler.getCompilerEnvironment().setFreshVarCounter(0);
        int actionNumber = 0;
        TomList automataList =  tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ;	
        ArrayList<TomTerm> subjectList = new ArrayList<TomTerm>();
        ArrayList<TomTerm> renamedSubjects = new ArrayList<TomTerm>();
        // for each pattern action <term>,...,<term> -> { action }
        // build a matching automata
        {{if ( (tom_constraintInstructionList instanceof tom.engine.adt.tominstruction.types.ConstraintInstructionList) ) {if ( (((( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_constraintInstructionList) instanceof tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction) || ((( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_constraintInstructionList) instanceof tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction)) ) { tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch135NameNumber_end_4=(( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_constraintInstructionList);do {{if (!( tomMatch135NameNumber_end_4.isEmptyconcConstraintInstruction() )) { tom.engine.adt.tominstruction.types.ConstraintInstruction  tomMatch135NameNumber_freshVar_10= tomMatch135NameNumber_end_4.getHeadconcConstraintInstruction() ;if ( (tomMatch135NameNumber_freshVar_10 instanceof tom.engine.adt.tominstruction.types.constraintinstruction.ConstraintInstruction) ) {

            actionNumber++;
            try {
              // get the new names for subjects and generates casts -- needed especially for lists
              // this is performed here, and not above, because in the case of nested matches, we do not want 
              // to go in the action and collect from there              
              Constraint newConstraint = tom_make_TopDownIdStopOnSuccess(tom_make_renameSubjects(subjectList,renamedSubjects,compiler)).visitLight( tomMatch135NameNumber_freshVar_10.getConstraint() );
              Constraint propagationResult = compiler.getCompilerEnvironment().getConstraintPropagator().performPropagations(newConstraint);
              //System.out.println("propag: " + actionNumber + "  " + Integer.valueOf((int)(System.currentTimeMillis()-Compiler.startChrono)) );
              PreGenerator preGenerator = new PreGenerator(compiler.getCompilerEnvironment().getConstraintGenerator());
              Expression preGeneratedExpr = preGenerator.performPreGenerationTreatment(propagationResult);
              //System.out.println("preGeneratedExpr = " + preGeneratedExpr);
              //System.out.println("preGeneration: " + actionNumber + "  " + Integer.valueOf((int)(System.currentTimeMillis()-Compiler.startChrono)) );
              Instruction matchingAutomata = compiler.getCompilerEnvironment().getConstraintGenerator().performGenerations(preGeneratedExpr,  tomMatch135NameNumber_freshVar_10.getAction() );
              Instruction postGenerationAutomata = PostGenerator.performPostGenerationTreatment(matchingAutomata);
              TomNumberList path = compiler.getRootpath();
              TomNumberList numberList = tom_append_list_concTomNumber(path, tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( tom.engine.adt.tomname.types.tomnumber.PatternNumber.make(actionNumber) , tom.engine.adt.tomname.types.tomnumberlist.EmptyconcTomNumber.make() ) );
              TomTerm automata =  tom.engine.adt.tomterm.types.tomterm.Automata.make( tomMatch135NameNumber_freshVar_10.getOption() , newConstraint, numberList, postGenerationAutomata) ;
              automataList =  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(automata,tom_append_list_concTomTerm(automataList, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) ; // insert in reverse order

            } catch(Exception e) {
              e.printStackTrace();
              throw new TomRuntimeException("Propagation or generation exception:" + e);
            }																	    						
          }}if ( tomMatch135NameNumber_end_4.isEmptyconcConstraintInstruction() ) {tomMatch135NameNumber_end_4=(( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_constraintInstructionList);} else {tomMatch135NameNumber_end_4= tomMatch135NameNumber_end_4.getTailconcConstraintInstruction() ;}}} while(!( (tomMatch135NameNumber_end_4==(( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_constraintInstructionList)) ));}}}}
// end %match				
        /*
         * return the compiled Match construction
         */        
        InstructionList astAutomataList = compiler.automataListCompileMatchingList(automataList.reverse());
        // the block is useful in case we have a label on the %match: we would like it to be on the whole Match instruction 
        return  tom.engine.adt.tominstruction.types.instruction.UnamedBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.CompiledMatch.make( tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make(astAutomataList) ,  (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getOption() ) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ;
      }}}}return _visit_Instruction(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_CompileMatch( Compiler  t0) { return new CompileMatch(t0);}

// end strategy  

  /**
   * Takes all MatchConstraints and renames the subjects;
   * (this ensures that the subject is not constructed more than once) 
   * Match(p,s) -> Match(object,s) /\ IsSort(object) /\ Match(freshSubj,Cast(object)) /\ Match(p,freshSubj) 
   * 
   * @param subjectList the list of old subjects
   */
  public static class renameSubjects extends tom.library.sl.AbstractBasicStrategy {private  java.util.ArrayList  subjectList;private  java.util.ArrayList  renamedSubjects;private  Compiler  compiler;public renameSubjects( java.util.ArrayList  subjectList,  java.util.ArrayList  renamedSubjects,  Compiler  compiler) {super(( new tom.library.sl.Identity() ));this.subjectList=subjectList;this.renamedSubjects=renamedSubjects;this.compiler=compiler;}public  java.util.ArrayList  getsubjectList() {return subjectList;}public  java.util.ArrayList  getrenamedSubjects() {return renamedSubjects;}public  Compiler  getcompiler() {return compiler;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) { tom.engine.adt.tomterm.types.TomTerm  tom_subject= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getSubject() ; tom.engine.adt.tomconstraint.types.Constraint  tom_constr=(( tom.engine.adt.tomconstraint.types.Constraint )tom__arg);


        if(renamedSubjects.contains( (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getPattern() ) || renamedSubjects.contains(tom_subject) ) {
          // make sure we don't process generated contraints
          return tom_constr; 
        }        
        // test if we already renamed this subject 
        if(subjectList.contains(tom_subject)) {
          TomTerm renamedSubj = (TomTerm) renamedSubjects.get(subjectList.indexOf(tom_subject));
          Constraint newConstraint = tom_constr.setSubject(renamedSubj);
          TomType freshSubjectType = ((Variable)renamedSubj).getAstType();
          TomTerm freshVar = compiler.getUniversalObjectForSubject(freshSubjectType);
          return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(freshVar, tom_subject) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.IsSortConstraint.make(freshSubjectType, freshVar) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(renamedSubj,  tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm.make( tom.engine.adt.tomexpression.types.expression.Cast.make(freshSubjectType,  tom.engine.adt.tomexpression.types.expression.TomTermToExpression.make(freshVar) ) ) ) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(newConstraint, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ) ) ) 



;
        }
        TomNumberList path = compiler.getRootpath();
        TomName freshSubjectName  =  tom.engine.adt.tomname.types.tomname.PositionName.make(tom_append_list_concTomNumber(path, tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( tom.engine.adt.tomname.types.tomnumber.NameNumber.make( tom.engine.adt.tomname.types.tomname.Name.make("_freshSubject_" + (++(compiler.getCompilerEnvironment().freshSubjectCounter))) ) , tom.engine.adt.tomname.types.tomnumberlist.EmptyconcTomNumber.make() ) )) ;
        TomType freshSubjectType =  tom.engine.adt.tomtype.types.tomtype.EmptyType.make() ;
        {{if ( (tom_subject instanceof tom.engine.adt.tomterm.types.TomTerm) ) {boolean tomMatch137NameNumber_freshVar_3= false ; tom.engine.adt.tomtype.types.TomType  tomMatch137NameNumber_freshVar_1= null ;if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {{tomMatch137NameNumber_freshVar_3= true ;tomMatch137NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstType() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {{tomMatch137NameNumber_freshVar_3= true ;tomMatch137NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstType() ;}}}if ((tomMatch137NameNumber_freshVar_3 ==  true )) {
 
            freshSubjectType = tomMatch137NameNumber_freshVar_1;
          }}}{if ( (tom_subject instanceof tom.engine.adt.tomterm.types.TomTerm) ) {boolean tomMatch137NameNumber_freshVar_9= false ; tom.engine.adt.tomname.types.TomName  tomMatch137NameNumber_freshVar_5= null ;if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.BuildTerm) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.FunctionCall) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.BuildConstant) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.BuildEmptyList) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.BuildConsList) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendList) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.BuildEmptyArray) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.BuildConsArray) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_subject) instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendArray) ) {{tomMatch137NameNumber_freshVar_9= true ;tomMatch137NameNumber_freshVar_5= (( tom.engine.adt.tomterm.types.TomTerm )tom_subject).getAstName() ;}}}}}}}}}}if ((tomMatch137NameNumber_freshVar_9 ==  true )) {if ( (tomMatch137NameNumber_freshVar_5 instanceof tom.engine.adt.tomname.types.tomname.Name) ) { tom.engine.adt.tomterm.types.TomTerm  tom_sv=(( tom.engine.adt.tomterm.types.TomTerm )tom_subject);

            TomSymbol tomSymbol = compiler.getCompilerEnvironment().getSymbolTable().getSymbolFromName( tomMatch137NameNumber_freshVar_5.getString() );                      
            if(tomSymbol != null) {
              freshSubjectType = TomBase.getSymbolCodomain(tomSymbol);
            } else if(tom_sv.isFunctionCall()) {
              freshSubjectType =tom_sv.getAstType();
            }
          }}}}}

        TomTerm renamedVar =  tom.engine.adt.tomterm.types.tomterm.Variable.make( tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() , freshSubjectName, freshSubjectType,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ;
        subjectList.add(tom_subject);
        renamedSubjects.add(renamedVar);
        Constraint newConstraint = tom_constr.setSubject(renamedVar);   
        TomTerm freshVar = compiler.getUniversalObjectForSubject(freshSubjectType);
        return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(freshVar, tom_subject) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.IsSortConstraint.make(freshSubjectType, freshVar) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(renamedVar,  tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm.make( tom.engine.adt.tomexpression.types.expression.Cast.make(freshSubjectType,  tom.engine.adt.tomexpression.types.expression.TomTermToExpression.make(freshVar) ) ) ) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(newConstraint, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ) ) ) 



;
      }}}}return _visit_Constraint(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_renameSubjects( java.util.ArrayList  t0,  java.util.ArrayList  t1,  Compiler  t2) { return new renameSubjects(t0,t1,t2);}



  private TomTerm getUniversalObjectForSubject(TomType subjectType){    
    if(getSymbolTable().isBuiltinType(TomBase.getTomType(subjectType))) {
      return getFreshVariable(subjectType);
    } else {
      return getFreshVariable(getSymbolTable().getUniversalType());
    }
  }

  /**
   * builds a list of instructions from a list of automata
   */
  private InstructionList automataListCompileMatchingList(TomList automataList) {
    {{if ( (automataList instanceof tom.engine.adt.tomterm.types.TomList) ) {if ( (((( tom.engine.adt.tomterm.types.TomList )automataList) instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || ((( tom.engine.adt.tomterm.types.TomList )automataList) instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {if ( (( tom.engine.adt.tomterm.types.TomList )automataList).isEmptyconcTomTerm() ) {
 return  tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ; }}}}{if ( (automataList instanceof tom.engine.adt.tomterm.types.TomList) ) {if ( (((( tom.engine.adt.tomterm.types.TomList )automataList) instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || ((( tom.engine.adt.tomterm.types.TomList )automataList) instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {if (!( (( tom.engine.adt.tomterm.types.TomList )automataList).isEmptyconcTomTerm() )) { tom.engine.adt.tomterm.types.TomTerm  tomMatch138NameNumber_freshVar_10= (( tom.engine.adt.tomterm.types.TomList )automataList).getHeadconcTomTerm() ;if ( (tomMatch138NameNumber_freshVar_10 instanceof tom.engine.adt.tomterm.types.tomterm.Automata) ) { tom.engine.adt.tomoption.types.OptionList  tom_optionList= tomMatch138NameNumber_freshVar_10.getOption() ; tom.engine.adt.tomconstraint.types.Constraint  tom_constraint= tomMatch138NameNumber_freshVar_10.getConstraint() ; tom.engine.adt.tominstruction.types.Instruction  tom_instruction= tomMatch138NameNumber_freshVar_10.getInst() ;

        InstructionList newList = automataListCompileMatchingList( (( tom.engine.adt.tomterm.types.TomList )automataList).getTailconcTomTerm() );				
        // if a label is assigned to a pattern (label:pattern ->
        // action) we generate corresponding labeled-block				 
        {{if ( (tom_optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {if ( (((( tom.engine.adt.tomoption.types.OptionList )tom_optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )tom_optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) { tom.engine.adt.tomoption.types.OptionList  tomMatch139NameNumber_end_4=(( tom.engine.adt.tomoption.types.OptionList )tom_optionList);do {{if (!( tomMatch139NameNumber_end_4.isEmptyconcOption() )) { tom.engine.adt.tomoption.types.Option  tomMatch139NameNumber_freshVar_8= tomMatch139NameNumber_end_4.getHeadconcOption() ;if ( (tomMatch139NameNumber_freshVar_8 instanceof tom.engine.adt.tomoption.types.option.Label) ) { tom.engine.adt.tomname.types.TomName  tomMatch139NameNumber_freshVar_7= tomMatch139NameNumber_freshVar_8.getAstName() ;if ( (tomMatch139NameNumber_freshVar_7 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

            // UnamedBlock(concInstruction(...)) to put patterns/actions in a fresh environment
            return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.UnamedBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.CompiledPattern.make(tom_constraint,  tom.engine.adt.tominstruction.types.instruction.NamedBlock.make( tomMatch139NameNumber_freshVar_7.getString() ,  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_instruction, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ,tom_append_list_concInstruction(newList, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) ;
          }}}if ( tomMatch139NameNumber_end_4.isEmptyconcOption() ) {tomMatch139NameNumber_end_4=(( tom.engine.adt.tomoption.types.OptionList )tom_optionList);} else {tomMatch139NameNumber_end_4= tomMatch139NameNumber_end_4.getTailconcOption() ;}}} while(!( (tomMatch139NameNumber_end_4==(( tom.engine.adt.tomoption.types.OptionList )tom_optionList)) ));}}}}

        // UnamedBlock(concInstruction(...)) to put patterns/actions in a fresh environment
        return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.UnamedBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.CompiledPattern.make(tom_constraint, tom_instruction) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ,tom_append_list_concInstruction(newList, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) ;
      }}}}}}

    return null;
  }

  /**
   * helper functions - mostly related to free var generation
   */

  public TomNumberList getRootpath() {
    return getCompilerEnvironment().getRootpath();
  }

  public int getFreshVarCounter() {
    return getCompilerEnvironment().getFreshVarCounter();
  }

  public int getFreshSubjectCounter() {
    return getCompilerEnvironment().getFreshSubjectCounter();
  }

  public SymbolTable getSymbolTable() {
    return getCompilerEnvironment().getSymbolTable();
  }

  // used in generator/SyntacticGenerator.t code
  public TomType getTermTypeFromName(TomName tomName) {
    String stringName = ((Name)tomName).getString();
    TomSymbol tomSymbol = getSymbolTable().getSymbolFromName(stringName);    
    return tomSymbol.getTypesToType().getCodomain();
  }


  // used in propagator/SyntacticPropagator.t code
  public TomType getSlotType(TomName tomName, TomName slotName) {
    String stringName = ((Name)tomName).getString();
    TomSymbol tomSymbol = getSymbolTable().getSymbolFromName(stringName);
    return TomBase.getSlotType(tomSymbol,slotName);    
  } 

  public TomType getTermTypeFromTerm(TomTerm tomTerm) {    
    return TomBase.getTermType(tomTerm,getCompilerEnvironment().getSymbolTable());    
  }

  public TomTerm getFreshVariable(TomType type) {
    return getFreshVariable(freshVarPrefix + (getCompilerEnvironment().freshVarCounter++), type);    
  }

  public TomTerm getFreshVariable(String name, TomType type) {
    TomNumberList path = getRootpath();
    TomName freshVarName  =  tom.engine.adt.tomname.types.tomname.PositionName.make(tom_append_list_concTomNumber(path, tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( tom.engine.adt.tomname.types.tomnumber.NameNumber.make( tom.engine.adt.tomname.types.tomname.Name.make(name) ) , tom.engine.adt.tomname.types.tomnumberlist.EmptyconcTomNumber.make() ) )) ;
    return  tom.engine.adt.tomterm.types.tomterm.Variable.make( tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() , freshVarName, type,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ;
  }

  public TomTerm getFreshVariableStar(TomType type) {
    return getFreshVariableStar(freshVarPrefix + (getCompilerEnvironment().freshVarCounter++), type);
  }

  public TomTerm getFreshVariableStar(String name, TomType type) {
    TomNumberList path = getRootpath();
    TomName freshVarName  =  tom.engine.adt.tomname.types.tomname.PositionName.make(tom_append_list_concTomNumber(path, tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( tom.engine.adt.tomname.types.tomnumber.NameNumber.make( tom.engine.adt.tomname.types.tomname.Name.make(name) ) , tom.engine.adt.tomname.types.tomnumberlist.EmptyconcTomNumber.make() ) )) ;
    return  tom.engine.adt.tomterm.types.tomterm.VariableStar.make( tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() , freshVarName, type,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ;
  }

  public TomTerm getBeginVariableStar(TomType type) {
    return getFreshVariableStar(freshBeginPrefix + (getCompilerEnvironment().freshVarCounter++),type);
  }

  public TomTerm getEndVariableStar(TomType type) {
    return getFreshVariableStar(freshEndPrefix + (getCompilerEnvironment().freshVarCounter++),type);
  }

  /*
   * add a prefix (tom_) to back-quoted variables which comes from the lhs
   */
  public static class findRenameVariable extends tom.library.sl.AbstractBasicStrategy {private  java.util.Collection  context;public findRenameVariable( java.util.Collection  context) {super(( new tom.library.sl.Identity() ));this.context=context;}public  java.util.Collection  getcontext() {return context;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {boolean tomMatch140NameNumber_freshVar_5= false ; tom.engine.adt.tomname.types.TomName  tomMatch140NameNumber_freshVar_1= null ;if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {{tomMatch140NameNumber_freshVar_5= true ;tomMatch140NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getAstName() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {{tomMatch140NameNumber_freshVar_5= true ;tomMatch140NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getAstName() ;}}}if ((tomMatch140NameNumber_freshVar_5 ==  true )) {if ( (tomMatch140NameNumber_freshVar_1 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {


        if(context.contains(tomMatch140NameNumber_freshVar_1)) {          
          return (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).setAstName( tom.engine.adt.tomname.types.tomname.Name.make(ASTFactory.makeTomVariableName( tomMatch140NameNumber_freshVar_1.getString() )) );
        }
      }}}}}return _visit_TomTerm(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.CompiledPattern) ) {




        // only variables found in LHS have to be renamed (this avoids that the JAVA ones are renamed)
        Collection newContext = new HashSet();
        tom_make_TopDownCollect(tom_make_CollectLHSVars(newContext)).visitLight( (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getContraint() );        
        newContext.addAll(context);
        return tom_make_TopDownIdStopOnSuccess(tom_make_findRenameVariable(newContext)).visitLight( (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getAutomataInst() );
      }}}}return _visit_Instruction(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public  tom.engine.adt.tomterm.types.TomTerm  _visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomterm.types.TomTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if ( (v instanceof tom.engine.adt.tomterm.types.TomTerm) ) {return ((T)visit_TomTerm((( tom.engine.adt.tomterm.types.TomTerm )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_findRenameVariable( java.util.Collection  t0) { return new findRenameVariable(t0);}public static class CollectLHSVars extends tom.library.sl.AbstractBasicStrategy {private  java.util.Collection  bag;public CollectLHSVars( java.util.Collection  bag) {super(( new tom.library.sl.Identity() ));this.bag=bag;}public  java.util.Collection  getbag() {return bag;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {





        
        Map map = TomBase.collectMultiplicity( (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getPattern() );
        Collection newContext = new HashSet(map.keySet());
        bag.addAll(newContext);
        throw new VisitFailure();// to stop the top-down
      }}}}return _visit_Constraint(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_CollectLHSVars( java.util.Collection  t0) { return new CollectLHSVars(t0);}



}
