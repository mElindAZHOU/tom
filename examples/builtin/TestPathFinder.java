/*
 * Copyright (c) 2004-2009, INRIA
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
package builtin;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Assert;

public class TestPathFinder {
  private PathFinder test;

  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(TestPathFinder.class.getName());
  }

  @Before
  public void setUp() {
    test = new PathFinder();
  }

  @Test
  public void testf2() {
    String s1 = "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaabaa";
    String res = test.f2(s1);
    String[] resTab = res.split("\n");
    Assert.assertEquals("There are 28 ways to find 6 \"b\" among 8",
                 28,resTab.length);
  }

  @Test
  public void testdoubleB() {
    String s1 = "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaabaa";
    int res = test.doubleB(s1);
    Assert.assertEquals("There are 28 ways to find 2 \"b\" among 8",
                 28,res);
  }

  @Ignore
  public void testdoubleBwhen() {
    String s1 = "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaabaa";
    int res = test.doubleBwhen(s1);
    Assert.assertEquals("There are 28 ways to find 2 \"b\" among 8",
            28,res);
  }
}
