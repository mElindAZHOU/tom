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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Antoine Reilles        e-mail: Antoine.Reilles@loria.fr
 **/

package tom.engine.verifier;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.*;
import java.util.logging.Level;

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

import tom.engine.TomMessage;
import tom.engine.tools.Tools;
import tom.engine.tools.TomGenericPlugin;
import tom.platform.OptionParser;
import tom.platform.adt.platformoption.types.*;

import aterm.ATerm;
import tom.engine.adt.il.types.*;
import tom.engine.adt.zenon.types.*;
import tom.library.sl.*;
/**
 * The TomVerifier plugin.
 */
public class TomVerifier extends TomGenericPlugin {

        private static   tom.engine.adt.tominstruction.types.InstructionList  tom_append_list_concInstruction( tom.engine.adt.tominstruction.types.InstructionList l1,  tom.engine.adt.tominstruction.types.InstructionList  l2) {     if( l1.isEmptyconcInstruction() ) {       return l2;     } else if( l2.isEmptyconcInstruction() ) {       return l1;     } else if(  l1.getTailconcInstruction() .isEmptyconcInstruction() ) {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,tom_append_list_concInstruction( l1.getTailconcInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.InstructionList  tom_get_slice_concInstruction( tom.engine.adt.tominstruction.types.InstructionList  begin,  tom.engine.adt.tominstruction.types.InstructionList  end, tom.engine.adt.tominstruction.types.InstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcInstruction()  ||  (end== tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( begin.getHeadconcInstruction() ,( tom.engine.adt.tominstruction.types.InstructionList )tom_get_slice_concInstruction( begin.getTailconcInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {     if( l1.isEmptyconcTomName() ) {       return l2;     } else if( l2.isEmptyconcTomName() ) {       return l1;     } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;     } else {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;     }   }   private static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end== tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyAndConstraint() ) {       return l2;     } else if( l2.isEmptyAndConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ).isEmptyAndConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),tom_append_list_AndConstraint((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyOrConstraint() ) {       return l2;     } else if( l2.isEmptyOrConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? l1.getTailOrConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ).isEmptyOrConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? l1.getHeadOrConstraint() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? l1.getHeadOrConstraint() :l1),tom_append_list_OrConstraint((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? l1.getTailOrConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyOrConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? begin.getHeadOrConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_OrConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? begin.getTailOrConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ),end,tail)) ;   }         private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }     private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}   






  public static final String DECLARED_OPTIONS =
    "<options>" +
    "<boolean name='verify' altName='' description='Verify correctness of match compilation' value='false'/>" +
    "<boolean name='noReduce' altName='' description='Do not simplify extracted constraints (depends on --verify)' value='false'/>" +
    "<boolean name='camlSemantics' altName='' description='Verify with caml semantics for match' value='false'/>" +
    "</options>";

  public static final String ZENON_SUFFIX = ".zv";
  public static final String INTERMEDIATE_SUFFIX = ".tfix.zenon";

  protected Verifier verif;
  protected ZenonOutput zenon;

  public TomVerifier() {
    super("TomVerifier");
  }

  public void run(Map informationTracker) {
    boolean camlsemantics = getOptionBooleanValue("camlSemantics");
    boolean intermediate = getOptionBooleanValue("intermediate");
    boolean optimize2 = getOptionBooleanValue("optimize2");
    //System.out.println("(debug) I'm in the Tom Verifier : TSM"+getStreamManager().toString());

    if(optimize2 && isActivated()) {
      getLogger().log(Level.SEVERE, TomMessage.verifierNotCompatibleWithOptimize.getMessage());
    }

    verif = new Verifier(camlsemantics);
    verif.setSymbolTable(this.symbolTable());
    // delay the zenonoutput creation, as it needs the verifiers
    // symboltable to be properly set
    if(isActivated()) {
      zenon = new ZenonOutput(verif);
      long startChrono = System.currentTimeMillis();
      try {

        // collects all automata
        Collection matchingCode = getMatchingCode();

        // Collection derivations = getDerivations(matchingCode);
        //System.out.println("Derivations : " + derivations);

        Map rawConstraints = getRawConstraints(matchingCode);
        //System.out.println(rawConstraints);

        // reduce constraints
        verif.mappingReduce(rawConstraints);
        if (!getOptionBooleanValue("noReduce")) {
          verif.booleanReduce(rawConstraints);
        }

        Collection zspecSet = zenon.zspecSetFromConstraintMap(rawConstraints);
        if(intermediate) {
          Tools.generateOutputFromCollection(getStreamManager().getOutputFileName() + INTERMEDIATE_SUFFIX, zspecSet);
        }

        ZenonBackend back = new ZenonBackend(verif);
        //System.out.println("output: "+back.genZSpecCollection(zspecSet));
        String output = back.genZSpecCollection(zspecSet);

        // do not generate a file if there is no proof to do
        if (!zspecSet.isEmpty()) {
          try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
                      getStreamManager().getOutputFileName() + ZENON_SUFFIX
                      ))));
            writer.write(output);
            writer.close();
          } catch (IOException e) {
            getLogger().log( Level.SEVERE, TomMessage.backendIOException.getMessage(),
                new Object[]{getStreamManager().getOutputFile().getName(), e.getMessage()} );
            return;
          }
        }

        // verbose
        getLogger().log(Level.INFO, TomMessage.tomVerificationPhase.getMessage(),
                        new Integer((int)(System.currentTimeMillis()-startChrono)));

      } catch (Exception e) {
        getLogger().log(Level.SEVERE, TomMessage.exceptionMessage.getMessage(),
                         new Object[]{getClass().getName(),
                                      getStreamManager().getInputFileName(),
                                      e.getMessage()} );
        e.printStackTrace();
      }
    } else {
      getLogger().log(Level.INFO, TomMessage.verifierInactivated.getMessage());
    }
  }

  protected Collection getMatchingCode() {
        // here the extraction stuff
        Collection matchSet = collectMatch((TomTerm)getWorkingTerm());

        Collection purified = purify(matchSet);
         //System.out.println("Purified : " + purified);

        // removes all associative patterns
        filterAssociative(purified);

        return purified;
  }

  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(TomVerifier.DECLARED_OPTIONS);
  }

  private boolean isActivated() {
    return getOptionBooleanValue("verify");
  }

  public static class collectMatch extends tom.library.sl.BasicStrategy {private  java.util.Collection  collection;public collectMatch( java.util.Collection  collection) {super(( new tom.library.sl.Identity() ));this.collection=collection;}public  java.util.Collection  getcollection() {return collection;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.CompiledMatch) ) {


        collection.add( (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getAutomataInst() );
      }}}}return _visit_Instruction(tom__arg,introspector); }public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_collectMatch( java.util.Collection  t0) { return new collectMatch(t0);}



  public Collection collectMatch(TomTerm subject) {
    Collection result = new HashSet();
    try {
      tom_make_TopDown(tom_make_collectMatch(result)).visitLight(subject);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy collectMatch failed");
    }
    return result;
  }

  public Collection purify(Collection subject) {
    Collection purified = new HashSet();
    Iterator it = subject.iterator();
    while (it.hasNext()) {
      Instruction cm = (Instruction)it.next();
      // simplify the IL automata
      purified.add((simplifyIl(cm)));
    }
    return purified;
  }

  public static class ilSimplifier extends tom.library.sl.BasicStrategy {public ilSimplifier() {super(( new tom.library.sl.Identity() ));}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomexpression.types.Expression  visit_Expression( tom.engine.adt.tomexpression.types.Expression  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tom__arg) instanceof tom.engine.adt.tomexpression.types.expression.Or) ) {if ( ( (( tom.engine.adt.tomexpression.types.Expression )tom__arg).getArg2()  instanceof tom.engine.adt.tomexpression.types.expression.FalseTL) ) {


        return  (( tom.engine.adt.tomexpression.types.Expression )tom__arg).getArg1() ;
      }}}}{if ( (tom__arg instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tom__arg) instanceof tom.engine.adt.tomexpression.types.expression.TomTermToExpression) ) { tom.engine.adt.tomterm.types.TomTerm  tomMatch298NameNumber_freshVar_6= (( tom.engine.adt.tomexpression.types.Expression )tom__arg).getAstTerm() ;if ( (tomMatch298NameNumber_freshVar_6 instanceof tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm) ) {

        return  tomMatch298NameNumber_freshVar_6.getAstExpression() ;
      }}}}}return _visit_Expression(tom__arg,introspector); }public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) {if ( ( (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getCondition()  instanceof tom.engine.adt.tomexpression.types.expression.TrueTL) ) {if ( ( (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getFailureInst()  instanceof tom.engine.adt.tominstruction.types.instruction.Nop) ) {




        return  (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getSuccesInst() ;
      }}}}}{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {boolean tomMatch299NameNumber_freshVar_12= false ; tom.engine.adt.tominstruction.types.InstructionList  tomMatch299NameNumber_freshVar_8= null ;if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.UnamedBlock) ) {{tomMatch299NameNumber_freshVar_12= true ;tomMatch299NameNumber_freshVar_8= (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getInstList() ;}} else {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) {{tomMatch299NameNumber_freshVar_12= true ;tomMatch299NameNumber_freshVar_8= (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getInstList() ;}}}if ((tomMatch299NameNumber_freshVar_12 ==  true )) {if ( ((tomMatch299NameNumber_freshVar_8 instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || (tomMatch299NameNumber_freshVar_8 instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) {if (!( tomMatch299NameNumber_freshVar_8.isEmptyconcInstruction() )) {if (  tomMatch299NameNumber_freshVar_8.getTailconcInstruction() .isEmptyconcInstruction() ) {

        return  tomMatch299NameNumber_freshVar_8.getHeadconcInstruction() ;
      }}}}}}{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {boolean tomMatch299NameNumber_freshVar_19= false ; tom.engine.adt.tominstruction.types.Instruction  tomMatch299NameNumber_freshVar_15= null ; tom.engine.adt.tomterm.types.TomTerm  tomMatch299NameNumber_freshVar_14= null ;if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.Let) ) {{tomMatch299NameNumber_freshVar_19= true ;tomMatch299NameNumber_freshVar_14= (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getVariable() ;tomMatch299NameNumber_freshVar_15= (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getAstInstruction() ;}} else {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.LetRef) ) {{tomMatch299NameNumber_freshVar_19= true ;tomMatch299NameNumber_freshVar_14= (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getVariable() ;tomMatch299NameNumber_freshVar_15= (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getAstInstruction() ;}}}if ((tomMatch299NameNumber_freshVar_19 ==  true )) {boolean tomMatch299NameNumber_freshVar_18= false ;if ( (tomMatch299NameNumber_freshVar_14 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable) ) {tomMatch299NameNumber_freshVar_18= true ;} else {if ( (tomMatch299NameNumber_freshVar_14 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar) ) {tomMatch299NameNumber_freshVar_18= true ;}}if ((tomMatch299NameNumber_freshVar_18 ==  true )) {

        return tomMatch299NameNumber_freshVar_15;
      }}}}{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.Assign) ) { tom.engine.adt.tomterm.types.TomTerm  tomMatch299NameNumber_freshVar_21= (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getVariable() ;boolean tomMatch299NameNumber_freshVar_24= false ;if ( (tomMatch299NameNumber_freshVar_21 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable) ) {tomMatch299NameNumber_freshVar_24= true ;} else {if ( (tomMatch299NameNumber_freshVar_21 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar) ) {tomMatch299NameNumber_freshVar_24= true ;}}if ((tomMatch299NameNumber_freshVar_24 ==  true )) {

        return  tom.engine.adt.tominstruction.types.instruction.Nop.make() ;
      }}}}{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.CompiledPattern) ) {


        return  (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getAutomataInst() ;
      }}}}return _visit_Instruction(tom__arg,introspector); }public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }public  tom.engine.adt.tomexpression.types.Expression  _visit_Expression( tom.engine.adt.tomexpression.types.Expression  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomexpression.types.Expression )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if ( (v instanceof tom.engine.adt.tomexpression.types.Expression) ) {return ((T)visit_Expression((( tom.engine.adt.tomexpression.types.Expression )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_ilSimplifier() { return new ilSimplifier();}



  private Instruction simplifyIl(Instruction subject) {
    try {
      subject = tom_make_TopDown(tom_make_ilSimplifier()).visitLight(subject);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy simplifyIl failed");
    }
    return subject;
  }

  void filterAssociative(Collection c) {
    for (Iterator i = c.iterator(); i.hasNext(); )
      if (containsAssociativeOperator((Instruction) i.next()))
        i.remove();
  }

  boolean containsAssociativeOperator(Instruction subject) {
    Collection result = new HashSet();
    try {
      tom_make_TopDown(tom_make_associativeOperatorCollector(result)).visitLight(subject);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy containsAssociativeOperator failed");
    }
    return !result.isEmpty();
  }

  public static class associativeOperatorCollector extends tom.library.sl.BasicStrategy {private  java.util.Collection  store;public associativeOperatorCollector( java.util.Collection  store) {super(( new tom.library.sl.Identity() ));this.store=store;}public  java.util.Collection  getstore() {return store;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.WhileDo) ) {







        store.add((( tom.engine.adt.tominstruction.types.Instruction )tom__arg));
      }}}{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.DoWhile) ) {

        store.add((( tom.engine.adt.tominstruction.types.Instruction )tom__arg));
      }}}}return _visit_Instruction(tom__arg,introspector); }public  tom.engine.adt.tomexpression.types.Expression  visit_Expression( tom.engine.adt.tomexpression.types.Expression  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{}return _visit_Expression(tom__arg,introspector); }public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }public  tom.engine.adt.tomexpression.types.Expression  _visit_Expression( tom.engine.adt.tomexpression.types.Expression  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomexpression.types.Expression )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if ( (v instanceof tom.engine.adt.tomexpression.types.Expression) ) {return ((T)visit_Expression((( tom.engine.adt.tomexpression.types.Expression )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_associativeOperatorCollector( java.util.Collection  t0) { return new associativeOperatorCollector(t0);}











  public Collection getDerivations(Collection subject) {
    Collection derivations = new HashSet();
    Iterator it = subject.iterator();

    while (it.hasNext()) {
      Instruction automata = (Instruction) it.next();
      Collection trees = verif.build_tree(automata);
      derivations.addAll(trees);
    }
    return derivations;
  }

  public Map getRawConstraints(Collection subject) {
    Map rawConstraints = new HashMap();
    Iterator it = subject.iterator();

    while (it.hasNext()) {
      Instruction automata = (Instruction) it.next();
      Map trees = verif.getConstraints(automata);
      rawConstraints.putAll(trees);
    }
    return rawConstraints;
  }

  public String constraintToString(ATerm patternList) {
    return constraintToString((ConstraintList) patternList);
  }

  public String constraintToString(ConstraintList constraintList) {
    StringBuilder result = new StringBuilder();
    Constraint h = null;
    ConstraintList tail = constraintList;
    if(!tail.isEmptyconcConstraint()) {
      h = tail.getHeadconcConstraint();
      tail = tail.getTailconcConstraint();
      result.append(constraintToString(h));
    }

    while(!tail.isEmptyconcConstraint()) {
      h = tail.getHeadconcConstraint();
      result.append("," + constraintToString(h));
      tail = tail.getTailconcConstraint();
    }
    return result.toString();
  }

  public String constraintToString(Constraint constraint) {
    String result = "";
    {{if ( (constraint instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( (((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || ((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {if (!( (  (( tom.engine.adt.tomconstraint.types.Constraint )constraint).isEmptyAndConstraint()  ||  ((( tom.engine.adt.tomconstraint.types.Constraint )constraint)== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )  ) )) {

        return constraintToString((( (((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || ((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )?( (( tom.engine.adt.tomconstraint.types.Constraint )constraint).getHeadAndConstraint() ):((( tom.engine.adt.tomconstraint.types.Constraint )constraint)))) + " && " + constraintToString(tom_append_list_AndConstraint((( (((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || ((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )?( (( tom.engine.adt.tomconstraint.types.Constraint )constraint).getTailAndConstraint() ):( tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )), tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ));
      }}}}{if ( (constraint instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( (((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || ((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) ) {if (!( (  (( tom.engine.adt.tomconstraint.types.Constraint )constraint).isEmptyOrConstraint()  ||  ((( tom.engine.adt.tomconstraint.types.Constraint )constraint)== tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() )  ) )) {

        return constraintToString((( (((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || ((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )?( (( tom.engine.adt.tomconstraint.types.Constraint )constraint).getHeadOrConstraint() ):((( tom.engine.adt.tomconstraint.types.Constraint )constraint)))) + " || " + constraintToString(tom_append_list_OrConstraint((( (((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || ((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )?( (( tom.engine.adt.tomconstraint.types.Constraint )constraint).getTailOrConstraint() ):( tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() )), tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ));
      }}}}{if ( (constraint instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( ((( tom.engine.adt.tomconstraint.types.Constraint )constraint) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {

        return constraintToString( (( tom.engine.adt.tomconstraint.types.Constraint )constraint).getPattern() ) + " << " + constraintToString( (( tom.engine.adt.tomconstraint.types.Constraint )constraint).getSubject() );
      }}}}
    
    return result;
  }

  public String constraintToString(TomList tomList) {
    StringBuilder result = new StringBuilder();
    TomTerm h = null;
    TomList tail = tomList;
    if(!tail.isEmptyconcTomTerm()) {
      h = tail.getHeadconcTomTerm();
      tail = tail.getTailconcTomTerm();
      result.append(constraintToString(h));
    }

    while(!tail.isEmptyconcTomTerm()) {
      h = tail.getHeadconcTomTerm();
      result.append("," + constraintToString(h));
      tail = tail.getTailconcTomTerm();
    }
    return result.toString();
  }
  
  public String constraintToString(TomTerm tomTerm) {
    {{if ( (tomTerm instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomTerm) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) { tom.engine.adt.tomname.types.TomNameList  tomMatch303NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getNameList() ;if ( ((tomMatch303NameNumber_freshVar_1 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch303NameNumber_freshVar_1 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {if (!( tomMatch303NameNumber_freshVar_1.isEmptyconcTomName() )) { tom.engine.adt.tomname.types.TomName  tomMatch303NameNumber_freshVar_8= tomMatch303NameNumber_freshVar_1.getHeadconcTomName() ;if ( (tomMatch303NameNumber_freshVar_8 instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom_name= tomMatch303NameNumber_freshVar_8.getString() ; tom.engine.adt.tomterm.types.TomList  tom_childrens= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getArgs() ;

        if (tom_childrens.isEmptyconcTomTerm()) {
          return tom_name;
        } else {
          tom_name= tom_name+ "(";
          TomTerm head = tom_childrens.getHeadconcTomTerm();
          tom_name+= constraintToString(head);
          TomList tail = tom_childrens.getTailconcTomTerm();
          while(!tail.isEmptyconcTomTerm()) {
            head = tail.getHeadconcTomTerm();
            tom_name+= "," + constraintToString(head);
            tail = tail.getTailconcTomTerm();
          }
          tom_name+= ")";
          return tom_name;
        }
      }}}}}}{if ( (tomTerm instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomTerm) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) { tom.engine.adt.tomname.types.TomName  tomMatch303NameNumber_freshVar_10= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getAstName() ;if ( (tomMatch303NameNumber_freshVar_10 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

        return  tomMatch303NameNumber_freshVar_10.getString() ;
      }}}}{if ( (tomTerm instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomTerm) instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable) ) {

        return "\\_";
      }}}}

    return "StrangePattern" + tomTerm;
  }
}
