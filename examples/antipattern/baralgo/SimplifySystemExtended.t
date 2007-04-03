/*
 * Copyright (c) 2004-2007, INRIA
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
 *  - Neither the name of the INRIA nor the names of its
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
package antipattern;

import aterm.*;
import aterm.pure.*;

import antipattern.term.*;
import antipattern.term.types.*;

import jjtraveler.VisitFailure;
import jjtraveler.reflective.VisitableVisitor;

import java.util.Collection;

import tom.library.strategy.mutraveler.MuTraveler;

public class SimplifySystemExtended extends antipattern.SimplifySystem {

	%include{ term/Term.tom }
	%include{ mutraveler.tom }
	
	public SimplifySystemExtended(VisitableVisitor vis) {
		super(vis);      
    }
    
    public Constraint visit_Constraint(Constraint arg) throws VisitFailure {
    	
		Constraint result = null;
    	
    	try{
    		result = super.visit_Constraint(arg);
    		
    		//if it didn't entered the match of the super.visit_Constraint
    		//than maybe it will enter the one of the extendedVisit
    		if (isIdentity && arg == result){
    			return extendedVisit(arg);
    		}
    		
    		return result;
    		
    	}catch(VisitFailure vf){ //can only happen when the visit failed
    		return extendedVisit(arg);
    	}
    }
    
    private Constraint extendedVisit(Constraint arg) throws VisitFailure {
    	
      %match(Constraint arg) {
                
        // Decompose the constraint pattern
        Match(ApplCons(name,a1,constraint),Appl(name,a2)) -> {
          TermList args1 = `a1;
          TermList args2 = `a2;
          AConstraintList argCons = `constraint;
          AConstraintList l = `concAnd();
          while(!`args2.isEmptyconcTerm()) {
            l = `concAnd(Match(args1.getHeadconcTerm(),args2.getHeadconcTerm()),l*);
            args1 = args1.getTailconcTerm();
            args2 = args2.getTailconcTerm();
          }
          
          while(!argCons.isEmptyconcAnd()) {
	    	l = `concAnd(argCons.getHeadconcAnd(),l*);
	        argCons = argCons.getTailconcAnd();
          }
          
          return `And(l);
        }
        
        // SymbolClash with ApplCons
        Match(ApplCons(name1,args1,argCons),Appl(name2,args2))-> {
          if(`name1 != `name2) {
            return `False();
          }
        }
       
        // PropagateSuccess
        And(concAnd(X*,GreaterThan(Appl(var1,_),Appl(var2,_)),Y*)) ->{
        	return (Integer.parseInt(`var1) > Integer.parseInt(`var2) ? 
        			`And(concAnd(X*,Y*)):`False());
        }
        And(concAnd(X*,LessThan(Appl(var1,_),Appl(var2,_)),Y*)) ->{
        	return (Integer.parseInt(`var1) < Integer.parseInt(`var2) ? 
        			`And(concAnd(X*,Y*)):`False());
        }
      }
      
      return (isIdentity ? arg : (Constraint)`Fail().visit(arg));
    }
  }
