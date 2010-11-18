/*
 * Copyright (c) 2004-2010, INPL, INRIA
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
package subtypeinference;

public class Problem2 {

  static class A {
    public A num1;
    public A() {}
    public A(A num1) { this.num1 = num1; }
    public String getOp() { return ""; }
  }

  static class Javaa extends A {
    public Javaa() { super(); }
    public String getOp() { return "a"; }
  }

  static class Javaf extends A {
    public Javaf(A num1) { super(num1); }
    public String getOp() { return "f"; }
  }

  static class B extends A {
    public B num2;
    public B() {}
    public B(B num2) { this.num2 = num2; }
    public String getOp() { return ""; }
  }
  
  static class Javab extends B {
    public Javab() { super(); }
    public String getOp() { return "b"; }
  }

  static class Javag extends B {
    public Javag(B num2) { super(num2); }
    public String getOp() { return "g"; }
  }
  
// ------------------------------------------------------------
  %typeterm TomA {
    implement { A }
    is_sort(t) { $t instanceof A }
  }

  %typeterm TomC {
    implement { A }
    is_sort(t) { $t instanceof A }
  }

  %typeterm TomB {
    implement { B }
    is_sort(t) { $t instanceof B }
  }

  %subtype TomB <: TomA
  %subtype TomA <: TomC

// ------------------------------------------------------------
  %op TomA a() {
    is_fsym(t) { $t instanceof Javaa }
    make() { new Javaa() }
  }

  %op TomA f(num1:TomA) {
    is_fsym(t) { $t instanceof Javaf }
    make(t) { new Javaf($t) }
    get_slot(num1,t) { ((Javaf)$t).num1 }
  }

  %op TomB b() {
    is_fsym(t) { $t instanceof Javab }
    make() { new Javab() }
  }

  %op TomB g(num2:TomB) {
    is_fsym(t) { $t instanceof Javag }
    make(t) { new Javag($t) }
    get_slot(num2,t) { ((Javag)$t).num2 }
  }

// ------------------------------------------------------------

  public final static void main(String[] args) {
    Problem2 test = new Problem2();
    test.buildExpA();
    test.buildExpB();
  }

  public void buildExpA() {
    print(`f(a()));
  } 

  public void buildExpB() {
    print(`g(b()));
  } 

  public void print(A term) {
    String op = term.getOp();
    System.out.print("Term = " + `op);
    A y = `b();
    B x = `b();
    %match {
      //x << y && (y == a()) && (g(x) == g(b())) -> { System.out.println("Test!"); }
      g[num2=arg] << TomB term -> { System.out.println("(" + `arg.getOp() + ")"); }
      f[num1=arg] << TomA term -> { System.out.println("(" + `arg.getOp() + ")"); }
    }
  }
}
