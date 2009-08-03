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
 *
 **/

package tom.engine.typer;

import java.util.*;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.engine.TomBase;
import tom.engine.TomMessage;
import tom.engine.exception.TomRuntimeException;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomconstraint.types.constraint.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.typeconstraints.types.*;

import tom.engine.tools.SymbolTable;
import tom.engine.tools.ASTFactory;

import tom.library.sl.*;

public class KernelTyper {
  %include { ../../library/mapping/java/sl.tom}
  %include { ../../library/mapping/java/util/types/Collection.tom}
  %include { ../adt/tomsignature/TomSignature.tom }
  %include { ../../library/mapping/java/util/types/HashMap.tom}

  %typeterm KernelTyper {
    implement { KernelTyper }
    is_sort(t) { ($t instanceof KernelTyper) }
  }

  /** few attributes */
  private TypeConstraintList constraintsToTypeVariable = `concTypeConstraint();
  private SymbolTable symbolTable;
  private int freshTypeVarCounter = 0;

  public KernelTyper() {
    super();
  }

  /** Accessor methods */
  public SymbolTable getSymbolTable() {
    return this.symbolTable;
  }

  public void setSymbolTable(SymbolTable newSymbolTable) {
    this.symbolTable = newSymbolTable;
  }

  public TomType getFreshTypeVar() {
    return `TypeVar(freshTypeVarCounter++);
  }

  public TypeConstraintList getConstraints() {
    return this.constraintsToTypeVariable;
  }

  public void addConstraints(TypeConstraint newConstraint) {
    TypeConstraintList auxList = this.constraintsToTypeVariable;
    this.constraintsToTypeVariable=
      `concTypeConstraint(newConstraint,auxList*); 
  }

  public TomSymbol getSymbolFromName(String tomName) {
    return TomBase.getSymbolFromName(tomName, getSymbolTable());
  }

  public TomSymbol getSymbolFromType(TomType type) {
    %match(type) {
      TypeWithSymbol[TomType=tomType, TlType=tlType] -> {
        return TomBase.getSymbolFromType(`Type(tomType,tlType), getSymbolTable()); 
      }
    }
    return TomBase.getSymbolFromType(type, getSymbolTable()); 
  }

  /*
   * The "typeVariable" phase types RecordAppl into Variable
   * we focus on
   * - Match
   * The types of subjects are inferred from the patterns
   * Variable is typed in the TomTerm case
   */

  public tom.library.sl.Visitable typeVariable(TomType contextType, tom.library.sl.Visitable subject) {
    if(contextType == null) {
      throw new TomRuntimeException("typeVariable: null contextType");
    }
    try {
      //System.out.println("typeVariable: " + contextType);
      //System.out.println("typeVariable subject: " + subject);
      tom.library.sl.Visitable res =
        `TopDownStopOnSuccess(collectTypeConstraints(contextType,this)).visitLight(subject);
      //System.out.println("res: " + res);
      return res;
    } catch(tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("typeVariable: failure on " + subject);
    }
  }

  %strategy collectTypeConstraints(contextType:TomType,kernelTyper:KernelTyper) extends Fail() {
    visit TomVisit {
      VisitTerm(type,constraintInstructionList,options) -> {
        TomType newType = (TomType)`kernelTyper.typeVariable(contextType,`type);
        HashSet<Constraint> matchAndNumericConstraints = new HashSet<Constraint>();
        // Collect all match and numeric match (with explicit declaration of
        // type)
        `TopDownCollect(CollectMatchAndNumericConstraints(matchAndNumericConstraints)).visitLight(`constraintInstructionList);
        // Type a list of matchs
        return `VisitTerm(newType, kernelTyper.typeConstraintInstructionList(newType,constraintInstructionList,matchAndNumericConstraints),options);
      }
    }

    visit Instruction {
      /*
       * Expansion of a Match construct
       * to add types in subjects
       * to add types in variables of patterns and rhs
       * constraintInstructionList is a list of pair (condition,action)
       */
      Match(constraintInstructionList, options) -> {
        TomType newType = contextType;
        HashSet<Constraint> matchAndNumericConstraints = new HashSet<Constraint>();
        `TopDownCollect(CollectMatchAndNumericConstraints(matchAndNumericConstraints)).visitLight(`constraintInstructionList);
        return `Match(kernelTyper.typeConstraintInstructionList(newType,constraintInstructionList,matchAndNumericConstraints),options);
      }
    }

    // FIXME: maybe the attribute "constraints" in RecordAppl and Variable is
    // not necessary anymore
    visit TermToInfer {
      NewTerm(tomTerm,typeVar) -> {
        // Type a function or a list
        %match(tomTerm) {
          RecordAppl[Option=option,NameList=nameList@(Name(tomName),_*),Slots=slotList,Constraints=constraints] -> {
            TomSymbol tomSymbol = null;
            // Find the TomName
            if(`tomName.equals("")) {
              // Take the symbol with type represented by "contextType", but,
              // why?!? What really is contextType?? Who generates this??
              // Example: if this case is called twice successively (e.g.:
              // f(g())), so the contextType will be the Codomain of the first
              // call (e.g.: codomain of "g") and when tomname is empty, so this
              // can be the case of f(f(g())) (two "f")
              tomSymbol = kernelTyper.getSymbolFromType(contextType);
              if(tomSymbol==null) {
                throw new TomRuntimeException("No symbol found for type '" + contextType + "'");
              }
              // Add the name found to the name list. But why the "tomname" (which
              // is equals to "") is not removed of the nameList???
              `nameList = `concTomName(tomSymbol.getAstName());
            } else {
              tomSymbol = kernelTyper.getSymbolFromName(`tomName);
            }

          /*
           * CT-FUN rule:
           * IF found "f(e1,...,en):A" and "f:T1,...,Tn->T" exists in SymbolTable
           * THEN infers type of arguments and add a type constraint "A = T" and
           * calls the TypeVariableList method to adds a type constraint "Ai =
           * Ti" for each argument, where Ai is a fresh type variable
           */
            if(tomSymbol != null) {
              kernelTyper.addConstraints(`Equation(typeVar,TomBase.getSymbolCodomain(tomSymbol)));
              // Type the arguments 
              SlotList subterm =
                kernelTyper.typeVariableList(tomSymbol,`slotList);
            } else {
              System.out.println("contextType = " + contextType);
              %match(contextType) {
                type@(Type|TypeWithSymbol)[] -> {
                  SlotList subterm = kernelTyper.typeVariableList(`emptySymbol(), `slotList);
                }
              }
            }
          }
          // FIXME: it seems to be useless to match against a "UnamedVariable"
          // since the desugarer has already replaced unknown variables by fresh
          // variables
          /*
           * Type a variable
           * CT-VAR rule: 
           * IF found "x:A" and "x:T" already exists in SymbolTable 
           * THEN add a type constraint "A = T"
           */
          var@(Variable|UnamedVariable)[AstType=Type(tomType,EmptyType()),Constraints=constraints] -> {
            //The variable will always have a type: a primitive (Type) or an unknown
            //(TypeVar) type (a fresh type variable)
            TomType globalType = kernelTyper.getType(`tomType);
            kernelTyper.addConstraints(`Equation(typeVar,globalType));
          }
        }
      }
    }
  }

  /*
   * ConstraintInstructionList
   * @param contextType
   * @param constraintInstructionList a list of ConstraintInstruction
   * @param matchAndNumericConstraints a collection of MatchConstraint and NumericConstraint
   */
  // TOCHECK
  private ConstraintInstructionList typeConstraintInstructionList(TomType contextType, ConstraintInstructionList constraintInstructionList, Collection<Constraint> matchAndNumericConstraints) {
    // TODO
    return `concConstraintInstruction(); 
  }

  /**
   * If a variable with a type X is found, then all the variables that have the same name and 
   * with type 'unknown' get this type
   *  - apply this for each rhs
   */
  // TOCHECK
  protected Code propagateVariablesTypes(Code workingTerm){
    try{
      return `TopDown(ProcessRhsForVarTypePropagation()).visitLight(workingTerm);  
    } catch(tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("propagateVariablesTypes: failure on " + workingTerm);
    }
  }

  // TOCHECK
  %strategy ProcessRhsForVarTypePropagation() extends Identity() {
    visit ConstraintInstruction {
      ConstraintInstruction(constr,action,option) -> {
        HashMap<String,TomType> varTypes = new HashMap<String,TomType>();
        `TopDown(CollectAllVariablesTypes(varTypes)).visitLight(`constr);        
        Constraint c = `TopDown(PropagateVariablesTypes(varTypes)).visitLight(`constr);        
        return `ConstraintInstruction(c,action,option);
      }
    }
  }  

  // TOCHECK
  %strategy CollectAllVariablesTypes(HashMap map) extends Identity() {
    visit TomTerm {       
      //Variable[AstName=Name(name),AstType=type] && !EmptyType[] << type  -> {
        //if(`type!=SymbolTable.TYPE_UNKNOWN) {
      Variable[AstName=Name(name),AstType=type] && !EmptyType[] << type && !TypeVar[] << type -> {
          map.put(`name,`type);
      }
    }
  }

  // TOCHECK
  %strategy PropagateVariablesTypes(HashMap map) extends Identity() {
    visit BQTerm {
      //v@BQVariable[AstName=Name(name),AstType=type] -> {
        //if(`type==SymbolTable.TYPE_UNKNOWN || `type.isEmptyType()) {
      v@BQVariable[AstName=Name(name),AstType=type] && EmptyType[] << type && TypeVar[] << type -> {
        if (map.containsKey(`name)) {
          return `v.setAstType((TomType)map.get(`name)); 
        }
      }
    }
  }
  
  private TomType getType(String tomName) {
    TomType tomType = getSymbolTable().getType(tomName);
    return tomType;
  }

  /**
   * Collect the constraints (match and numeric)
   * NumericConstraint are %match with explicit type declaration
   */
  // TOCHECK
  %strategy CollectMatchAndNumericConstraints(constrList:Collection) extends Identity() {
    visit Constraint {
      c@(MatchConstraint|NumericConstraint)[] -> {        
        constrList.add(`c);
        throw new VisitFailure();// to stop the top-down
      }      
    }
  }

  /*
   * Continuation of CT-FUN rule (applying to premises):
   * IF found "f(e1,...,en):A" and "f:T1,...,Tn->T" exists in SymbolTable
   * THEN infers type of arguments and adds a type constraint "Ai =
   * Ti" for each argument, where Ai is a fresh type variable
   */
  private SlotList typeVariableList(TomSymbol symbol, SlotList subtermList) {
    if(symbol == null) {
      throw new TomRuntimeException("typeVariableList: null symbol");
    }

    if(subtermList.isEmptyconcSlot()) {
      return `concSlot();
    }

    %match(symbol, subtermList) {
      //TODO
      symb@emptySymbol(), concSlot(PairSlotAppl(slotName,slotAppl),tail*) -> {
        /*
         * if the top symbol is unknown, the subterms
         * are typed in an empty context
         */
        SlotList sl = typeVariableList(`symb,`tail);
        return `concSlot(PairSlotAppl(slotName,(TomTerm)typeVariable(EmptyType(),slotAppl)),sl*);
      }

    }
    return `concSlot();
  }
}
 
