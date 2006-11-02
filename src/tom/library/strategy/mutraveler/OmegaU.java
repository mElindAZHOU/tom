/*
 *
 * Copyright (c) 2000-2006, Pierre-Etienne Moreau
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
 * 
 **/
package tom.library.strategy.mutraveler;
import tom.library.strategy.mutraveler.AbstractMuStrategy;
import jjtraveler.Visitable;
import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.VisitFailure;

/**
 * <code>OmegaU(i,v)</code> 
 * <p>
 * Basic visitor combinator which applies Omega(i,v) with
 * i in [1..arity] with a uniform probability
 * i.e. with probability 1/getChildCount
 * <p>
 */

public class OmegaU extends AbstractMuStrategy {
  public final static int ARG = 0;
  protected VisitableVisitor defaultStrategy;
  private static java.util.Random random = null;

  public OmegaU(VisitableVisitor v,VisitableVisitor d) {
    initSubterm(v);
    this.defaultStrategy = d;
    if(random == null) {
      random = new java.util.Random();
    }
  }

  public VisitableVisitor getDefaultStrategy() {
    return defaultStrategy;
  }

  public Visitable visit(Visitable any) throws VisitFailure {
    int arity = any.getChildCount();
    int randomInt = Math.abs(random.nextInt());
    if(arity==0) {
      return defaultStrategy.visit(any);
    } else {
      int selectedSubterm = (randomInt%arity)+1;
      return (new Omega(selectedSubterm,getArgument(ARG))).visit(any);
    }
  }
}

