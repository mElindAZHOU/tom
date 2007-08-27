/*
 * Copyright (c) 2004-2007, INRIA
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

package gom;

import gom.list.types.*;

public class BenchCons {

  %include { list/List.tom }

  %oplist List conc( int* ) {
    is_fsym(t) { (t!=null) && (t.isCons() || t.isNil()) }
    make_empty()  { gom.list.types.list.Nil.make() }
    make_insert(e,l) { gom.list.types.list.Cons.make(e, l) }
    get_head(l)   { l.gethead() }
    get_tail(l)   { l.gettail() }
    is_empty(l)   { (l!=null) && l.isNil() }
  }
  
  public List genere(int n) {
    if(n>2) {
      List l = genere(n-1);
      return `conc(n,l*);
    } else {
      return `conc(2);
    }
  }

  public List elim(List l) {
    %match(List l) {
      conc(x*,e1,y*,e2,z*) -> {
        if(`e2%`e1 == 0) {
          return `elim(conc(x*,e1,y*,z*));
        }
      }
    }
    return l;
  }

  public List reverse(List l) {
    List res = `conc();
    while(true) {
      %match(List l) {
        conc() -> { return res; }
        conc(h,t*) -> {
          res = `conc(h,res*);
          l = `t;
        }
      }
    }
  }

  public void run(int max) {
    //System.out.println(" l = " + genere(100));
    long startChrono = System.currentTimeMillis();

    //System.out.println(" l = " + elim(reverse(genere(100))));
    elim(reverse(genere(max)));
    
    System.out.println(max + " " + (System.currentTimeMillis()-startChrono));
  }

  public final static void main(String[] args) {
    BenchCons test = new BenchCons();
    int max = 100;
    try {
      max = Integer.parseInt(args[0]);
    } catch (Exception e) {
      System.out.println("Usage: java gom.BenchCons <max>");
      return;
    }
    test.run(max);
  }

}
