/*
  
    TOM - To One Matching Compiler

    Copyright (C) 2000-2001  LORIA (CNRS, INPL, INRIA, UHP, U-Nancy 2)
			     Nancy, France.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA

    Pierre-Etienne Moreau	e-mail: Pierre-Etienne.Moreau@loria.fr
    Christophe Ringeissen	e-mail: Christophe.Ringeissen@loria.fr
    Marian Vittek		e-mail: vittek@guma.ii.fmph.uniba.sk

*/

import jaterm.api.*;
import jaterm.shared.*;
import java.util.*;

public class Array1 {

  private ATermFactory factory;
  
  private AFun fzero, fsuc, fplus,ffib;
  public ATermAppl tzero;


  %typearray L {
    implement { ArrayList }
    get_fun_sym(t)   { ((t instanceof ArrayList)?factory.makeAFun("conc", 1, false):null) }
    cmp_fun_sym(t1,t2) { t1 == t2 }
    equals(l1,l2)    { l1.equals(l2) }
    get_element(l,n) { ((List)l).get(n) }
    get_size(l)      { ((List)l).size() }
  }

// ****** erreur ****** type L already exists

  %typearray L {
    implement { ArrayList }
    get_fun_sym(t)   { ((t instanceof ArrayList)?factory.makeAFun("conc", 1, false):null) }
    cmp_fun_sym(t1,t2) { t1 == t2 }
    equals(l1,l2)    { l1.equals(l2) }
    get_element(l,n) { ((List)l).get(n) }
    get_size(l)      { ((List)l).size() }
  }

// ****** erreur ******

  %oparray L conc( E* ) {
    fsym            { factory.makeAFun("conc", 1, false) }
    make_empty(n)   { new ArrayList(n) }
    make_add(l,e,n) { myAdd((List)l,e,n) }
  }

// ****** erreur ******  operator conc already exists

  %oparray L conc( E* ) {
    fsym            { factory.makeAFun("conc", 1, false) }
    make_empty(n)   { new ArrayList(n) }
    make_add(l,e,n) { myAdd((List)l,e,n) }
  }

// ****** erreur ******

  private Object myAdd(List l, Object e, int n) {
    l.add(n,e);
    return l;
  }
 
  %typeterm E {
    implement           { ATerm }
    cmp_fun_sym(t1,t2)  { t1 == t2 }
    get_fun_sym(t)      { (((ATermAppl)t).getAFun()) }
    get_subterm(t, n)   { (((ATermAppl)t).getArgument(n)) }
    equals(t1, t2)      { (t1.equals(t2)) }
  }

  %op E a {
    fsym { factory.makeAFun("a", 0, false) }
  }
  
  %op E b {
    fsym { factory.makeAFun("b", 0, false) }
  }

  %op E c {
    fsym { factory.makeAFun("c", 0, false) }
  }
  
  public Array1(ATermFactory factory) {
    this.factory = factory;
  }

  public final static void main(String[] args) {
    Array1 test = new Array1(new PureFactory(16));
    test.run1();
  }

  public void run1() {
    ATerm ta = factory.makeAppl(factory.makeAFun("a", 0, false));
    ATerm tb = factory.makeAppl(factory.makeAFun("b", 0, false));
    ATerm tc = factory.makeAppl(factory.makeAFun("c", 0, false));

    ArrayList l = new ArrayList();
    l.add(ta);
    l.add(tb);
    l.add(tc);
    l.add(ta);
    l.add(tb);
    l.add(tc);

    List res = sort1(l);
    System.out.println(" l       = " + l);
    System.out.println("sorted l = " + res);
  }


  public ArrayList sort1(ArrayList l) {
    
    %match(L l) {
        
      conc(X1*,x,X2*,y,X3*) -> {
          /*
            System.out.print("X1 = " + X1);
            System.out.print("\tx  = " + x);
            System.out.print("\tX2 = " + X2);
            System.out.print("\ty  = " + y);
            System.out.println("\tX3 = " + X3);
          */
          
        String xname = ((ATermAppl)x).getName();
        String yname = ((ATermAppl)y).getName();

        if(xname.compareTo(yname) > 0) {
          ArrayList result = X1;
          result.add(y                                                                                                                                                                                                          