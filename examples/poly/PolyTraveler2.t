/*
 * Copyright (c) 2004, INRIA
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 * 	- Redistributions of source code must retain the above copyright
 * 	notice, this list of conditions and the following disclaimer.  
 * 	- Redistributions in binary form must reproduce the above copyright
 * 	notice, this list of conditions and the following disclaimer in the
 * 	documentation and/or other materials provided with the distribution.
 * 	- Neither the name of the INRIA nor the names of its
 * 	contributors may be used to endorse or promote products derived from
 * 	this software without specific prior written permission.
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

package poly;

import aterm.*;
import aterm.pure.PureFactory;
import poly.expression.*;
import poly.expression.types.*;

import java.util.Stack;

public class PolyTraveler2 {

  private Factory factory;

  public PolyTraveler2(Factory factory) {
    this.factory = factory;
  }
  public Factory getExpressionFactory() {
    return factory;
  }

  %include { expression.tom }

  public Expression differentiate(Expression e, Expression v) {
    %match(Expression e, Expression v) {
      // non-linear patterns are allowed
      variable(v1), variable(v1) -> { return `one(); }
      
      plus(a1,a2), vx -> {
        return `plus(differentiate(a1,vx),
                     differentiate(a2,vx));
      }
      mult(a1,a2), vx -> { 
        return `plus(mult(a1,differentiate(a2,vx)),
                     mult(a2,differentiate(a1,vx)));      
      }
      exp(a1), vx     -> { return `mult(differentiate(a1,vx),e); }
    }
    return `zero();
  }
    
  public void run() {
    Expression var = `variable("X");
    Expression t = `mult(var,plus(var,constant("a")));
    Expression res = differentiate(t,var);
    System.out.println("Derivative form of " + t + " wrt. " + var + " is:\n\t" + res);

    jjtraveler.Visitor v = new SimplifyPlus();
    jjtraveler.Visitor bu = new jjtraveler.BottomUp(v);
    try {
      System.out.println(" bu.visit(" + res + ")");
      bu.visit(res);
    } catch (jjtraveler.VisitFailure e) {
      System.out.println("WARNING: VisitFailure: " + e.getMessage());
    }

  }
  
  public final static void main(String[] args) {
    PolyTraveler2 test = new PolyTraveler2(Factory.getInstance(new PureFactory()));
    test.run();
  }

  /*
    class SimplifyExpression implements jjtraveler.Visitor {
    private ATerm term;

    public jjtraveler.Visitable visit(jjtraveler.Visitable visitable) throws jjtraveler.VisitFailure {
    System.out.println("before visit: " + getTerm());
    ATermAppl t = (ATermAppl)getTerm();
    matchlab: {
    %match(term t) {
    plus(zero(),x) | plus(x,zero()) | mult(one(),x)  | mult(x,one())  -> { 
    setTerm(x);
    break matchlab;
    }
    mult(zero(),_) | mult(_,zero()) -> { 
    setTerm(`zero());
    break matchlab;
    }
    //_ -> { 
    //  System.out.println("failure");
    //  throw new jjtraveler.VisitFailure(); 
    //}

    }
    }
    System.out.println("after visit: " + getTerm());
    return visitable;
    }

    public void setTerm(ATerm t) {
    term =t;
    }

    public ATerm getTerm() {
    return term;
    }
    }
  */

    
  // simplification
  public Expression simplify(Expression t) {
    Expression res = t;
    block:{
      %match(Expression t) {
        exp(zero())    -> { res = `one(); break block; }
        plus(zero(),x) -> { res = simplify(`x); break block; }
        plus(x,zero()) -> { res = simplify(`x); break block; }
        mult(one(),x)  -> { res = simplify(`x); break block; }
        mult(x,one())  -> { res = simplify(`x); break block; }
        mult(zero(),_) -> { res = `zero(); break block; }
        mult(_,zero()) -> { res = `zero(); break block; }
        plus(x,y)      -> { res = `plus(simplify(x),simplify(y)); break block; }
        mult(x,y)      -> { res = `mult(simplify(x),simplify(y)); break block; }
        exp(x)         -> { res = `exp(simplify(x)); break block; }
      }
    }
    if(t != res) res = simplify(res);
    return res;
  }

  class SimplifyPlus extends poly.expression.FwdExpression {
    private Stack stack;
    public SimplifyPlus() {
      super(new jjtraveler.Identity());
      stack = new Stack();
    }

    public void push(Expression arg) {
      stack.push(arg);
    }

    public Expression pop() {
      return (Expression) stack.pop();
    }

    private void genericBuild(ATerm t) {
      if(t instanceof ATermAppl) {
        ATermAppl appl = (ATermAppl) t;
        for(int i=0 ; i<appl.getArity() ; i++) {
            ATerm term = subjectAppl.getArgument(i);
            genericCollectArray(term,collect,args); 
          } 

    }

    public void visit_Expression(poly.expression.types.Expression arg) { 
      System.out.println("exp = " + arg);
      Expression res;
      block: {
        %match(Expression arg) {
          exp(zero())    -> { push(`one()); break block; }
          plus(zero(),x) -> { push(`x); break block; }
          plus(x,zero()) -> { push(`x); break block; }
          mult(one(),x)  -> { push(`x); break block; }
          mult(x,one())  -> { push(`x); break block; }
          mult(zero(),_) -> { push(`zero()); break block; }
          mult(_,zero()) -> { push(`zero()); break block; }
          t -> { genericBuild(t); break block; }
        }
      } // end of block
    }

  }


}




