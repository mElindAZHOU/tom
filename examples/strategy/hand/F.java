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
package strategy.hand;

public class F extends Term {
  public Term a;
  public Term b;
  public F(Term arg0, Term arg1) {
    a = arg0;
    b= arg1;
  }
  public int getChildCount() { return 2; }

  public jjtraveler.Visitable getChildAt(int index) {
    if(index == 0) {
      return a;
    } else if(index == 1) {
      return b;
    }
    throw new IndexOutOfBoundsException();
  }

  public jjtraveler.Visitable setChildAt(int index, jjtraveler.Visitable v) {
    if(index == 0) {
      return new F((Term)v,b);
    } else if(index == 1) {
      return new F(a,(Term)v);
    }
    throw new IndexOutOfBoundsException();
  }

  public String toString() {
    return "F("+a.toString()+", "+b.toString()+")";
  }

  public boolean equals(Object o) {
    if(o instanceof F) {
      F f = (F) o;
      return a.equals(f.a) && b.equals(f.b);
    }
    return false;
  }
}
