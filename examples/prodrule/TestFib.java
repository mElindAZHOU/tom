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

package prodrule;

import java.util.*;
import java.io.*;
import aterm.*;
import aterm.pure.*;
import prodrule.*;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestFib extends TestCase {
	private static PureFactory pure;
  private Fib1 fib1;
  private Fib2 fib2;
  private Fib3 fib3;
  private Fib4 fib4;
  private Fib5 fib5;
  private int n;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(new TestSuite(TestFib.class));
	}

  public void setUp() {
		if (pure == null) {
			pure = new PureFactory();
		}
    fib1 = new Fib1(prodrule.fib1.fib.fibFactory.getInstance(pure));
    fib2 = new Fib2(prodrule.fib2.fib.fibFactory.getInstance(pure));
    fib3 = new Fib3(prodrule.fib3.fib.fibFactory.getInstance(pure));
    fib4 = new Fib4(prodrule.fib4.fib.fibFactory.getInstance(pure));
    fib5 = new Fib5(prodrule.fib5.fib.fibFactory.getInstance(pure));
    n = 50;
  }

  private int fib(int n) {
    int old = 1;
    int val = 1;
    if(n<2) { 
      return val;
    }

    for(int i=1 ; i<n ; i++) {
      int tmp = val;
      val = (old + val) % 1000000;
      old = tmp;
    }
    return val;
  }




  public void testCheckFib1() {
		assertEquals(fib1.run(n),fib(n));
  }
  
  public void testCheckFib2() {
		assertEquals(fib2.run(n),fib(n));
  }

  public void testCheckFib3() {
		assertEquals(fib3.run(n),fib(n));
  }

  public void testCheckFib4() {
		assertEquals(fib4.run(n),fib(n));
  }

  public void testCheckFib5() {
		assertEquals(fib5.run(n),fib(n));
  }
  
}
