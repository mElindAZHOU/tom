/*
 * Copyright (c) 2004-2006, INRIA
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
import aterm.pure.*;
import tom.library.traversal.*;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PolyAdvanced2 extends TestCase {
 
  private ATermFactory factory;
  private GenericTraversal traversal;

  ATermAppl t;
  ATermAppl var1;
  ATermAppl var2;
  ATermAppl res;

  public PolyAdvanced2() {
    t    = `mult(X(),plus(X(),a()));
    var1 = `X();
    var2 = `Y();
    this.factory = new PureFactory();
    this.traversal = new GenericTraversal();
  }

  public PolyAdvanced2(ATermFactory factory) {
    t    = `mult(X(),plus(X(),a()));
    var1 = `X();
    var2 = `Y();
    this.factory = factory;
    this.traversal = new GenericTraversal();
  }

  %include { Poly.signature }
  
  // Simplified version of differentiate
  public ATermAppl differentiate(ATermAppl poly, ATermAppl variable) {
    %match(term poly, term variable) {
      X(), X() -> { return `one(); }
      Y(), Y() -> { return `one(); }
      plus(a1,a2), var  -> { return `plus(differentiate(a1, var),differentiate(a2, var)); }
      mult(a1,a2), var  -> { 
        ATermAppl res1, res2;
        res1 =`mult(a1, differentiate(a2, var));
        res2 =`mult(a2, differentiate(a1, var));
        return `plus(res1,res2);
      }
    }
    return `zero();
  }

  public ATermAppl simplifyFunction(ATermAppl t) {
    %match(term t) {
      plus(zero(),x) -> { return `x; }
      plus(x,zero()) -> { return `x; }
      mult(one(),x)  -> { return `x; }
      mult(x,one())  -> { return `x; }
      mult(zero(),_) -> { return `zero(); }
      mult(_,zero()) -> { return `zero(); }
    }
		return (ATermAppl) traversal.genericTraversal(t,replace); 
  }

	Replace1 replace = new Replace1() {
		public ATerm apply(ATerm t) {
			return simplifyFunction((ATermAppl)t);
		}
	};

	// Simplification using a traversal function
  public ATermAppl simplify(ATermAppl t) {
    ATermAppl result = (ATermAppl)replace.apply(t);
    if(result != t) {
      result = simplify(result);
    }
    return result;
  }
  
  public void testX() {

    res = differentiate(t,var1);
    assertSame("diffentiate(mult(X(),plus(X(),a()),X())) is plus(mult(X,plus(1,0)),mult(plus(X,a),1))",res, `plus(mult(X(),plus(one(),zero())),mult(plus(X(),a()),one())));
    System.out.println("Derivative form of " + t + " wrt. " + var1 + " is:\n\t" + res);
    res = simplify(res);
    assertSame("simplify(plus(mult(X,plus(1,0)),mult(plus(X,a),1)) is plus(X,plus(X,a))",res, `plus(X(),plus(X(),a())));
    System.out.println("Simplified form is:\n\t" + res);
}

  public void testY() {

    res = differentiate(t,var2);
    assertSame("differentiate(mult(X(),plus(X(),a()),Y())) is plus(mult(X,plus(0,0)),mult(plus(X,a),0))", res, `plus(mult(X(),plus(zero(),zero())),mult(plus(X(),a()),zero())));
    System.out.println("Derivative form of " + t + " wrt. " + var2 + " is:\n\t" + res);
    res = simplify(res);
    assertSame("simplify(mult(X(),plus(X(),a()),Y())) is 0", res, `zero());
    System.out.println("Simplified form is:\n\t" + res);
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(new TestSuite(PolyAdvanced2.class));
  }
}

