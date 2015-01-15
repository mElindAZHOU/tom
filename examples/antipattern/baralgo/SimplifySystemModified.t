/*
 * Copyright (c) 2004-2015, Universite de Lorraine, Inria
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *  - Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  - Neither the name of the Inria nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package antipattern.baralgo;

import aterm.*;
import aterm.pure.*;

import antipattern.*;
import antipattern.term.*;
import antipattern.term.types.*;


import tom.library.sl.*;

public class SimplifySystemModified extends AbstractStrategyBasic {

  %include{ ../term/Term.tom }
  %include{ sl.tom }

  protected boolean isIdentity = true;

  public SimplifySystemModified(Strategy vis) {
    super(vis);
    this.isIdentity = (vis.getClass().equals(`Identity().getClass()) ? 
        true : false ); 
  }

  public <T> T visitLight(T o, Introspector i) throws VisitFailure {
    if (o instanceof Constraint) {
      Constraint arg = (Constraint) o;
      %match(Constraint arg) {

        // NegDef
        Match(Anti(p),s) -> {
          return (T) `Neg(Match(p,s));
        }

        // Decompose
        Match(Appl(name,a1),Appl(name,a2)) -> {
          AConstraintList l = `concAnd();
          TermList args1 = `a1;
          TermList args2 = `a2;
          while(!args1.isEmptyconcTerm()) {
            l = `concAnd(Match(args1.getHeadconcTerm(),args2.getHeadconcTerm()),l*);
            args1 = args1.getTailconcTerm();
            args2 = args2.getTailconcTerm();					
          }
          return (T) `And(l/* .reverseConstraintList() */);
        }

        // SymbolClash
        Match(Appl(name1,args1),Appl(name2,args2)) -> {
          if(`name1 != `name2) {
            return (T) `False();
          }
        }

        // Replace
        input@And(concAnd(X*,match@Match(var@Variable(name),s),Y*)) -> {	            
          Strategy rule,ruleStrategy;            
          if (isIdentity){
            rule = new ReplaceSystem(`var,`s, `Identity());
            ruleStrategy = `InnermostId(rule);          
          }else{
            rule = new ReplaceSystem(`var,`s, `Fail());
            ruleStrategy = `Innermost(rule);          
          }  
          Constraint res = null;

          res = (Constraint) ruleStrategy.visitLight(`And(concAnd(X*,Y*)));

          if (res != `And(concAnd(X*,Y*))){
            return (T) `And(concAnd(match,res));
          }
        }

        // Delete
        Match(Appl(name,concTerm()),Appl(name,concTerm())) -> {
          return (T) `True();
        }

        // PropagateClash
        And(concAnd(_*,False(),_*)) -> {
          return (T) `False();
        }

        // PropagateSuccess
        And(concAnd()) -> {
          return (T) `True();
        }
        And(concAnd(x)) -> {
          return (T) `x;
        }

        And(concAnd(X*,True(),Y*)) -> {
          return (T) `And(concAnd(X*,Y*));
        }

        // BooleanSimplification
        Neg(Neg(x)) -> { return (T) `x; }
        Neg(True()) -> { return (T) `False(); }
        Neg(False()) -> { return (T) `True(); }

      }
    }
    return (isIdentity ? o : (T)`Fail().visitLight(o,i));
  }
}
