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

import aterm.*;
import aterm.pure.*;
import prodrule.fib3.*;
import prodrule.fib3.types.*;
//import java.util.*;

class Fib5 {
    private Factory factory;

    %vas {
	// extension of adt syntax
	module fib3
      
	    public
	    sorts Element
      
	    abstract syntax
	    Undef -> Element
	    Nat( value:Int ) -> Element
	    Fib(arg:Int, val:Element) -> Element
	    }

    
    %typelist Space {
	implement { MyList }
	get_fun_sym(t)   { ((t instanceof MyList)?factory.getPureFactory().makeAFun("concElement", 1, false):null) }
	cmp_fun_sym(t1,t2) { t1 == t2 }
	equals(l1,l2)      { l1.equals(l2) }
	get_head(l)        { (Element)l.getHead() }
	get_tail(l)        { (MyList)l.getTail() }
	is_empty(l)        { l.isEmpty() }
    }

    %oplist Space concElement( Element* ) {
	fsym             { factory.getPureFactory().makeAFun("concElement", 1, false) }
	make_empty()     { new MyList() }
	make_insert(e,l) { new MyList(e,l) }
    }
      
    public Fib5(Factory factory) {
	this.factory = factory;
    } 

    public Factory getFib3Factory() {
	return factory;
    }
  
    private static boolean opt = true;
    private static int fire=0;
    private MyList WM = new MyList();
    public void run() {
	long startChrono = System.currentTimeMillis();
	System.out.println("running...");
	int n = 400;
	WM = WM.add(`Fib(0,Nat(1)));
	WM = WM.add(`Fib(1,Nat(1)));
	WM = WM.add(`Fib(n,Undef));
	loop(WM);
	System.out.println("fib(" + n + ") = " + result(n) + " (in " + (System.currentTimeMillis()-startChrono)+ " ms)");
	System.out.println("fire = " + fire);
    } 
  
    public void loop(MyList WM) {
	boolean modified = true;
	while(modified) {
	    modified = modified && (rec() || compute());
	    //System.out.println("WM (" + fire + ") = "+ WM);
	} 
    }

    public final static void main(String[] args) {
	Fib5 test = new Fib5(new Factory(new PureFactory(16)));
	test.run();
    }

    public boolean rec() {
	%match(Space WM) {
	    concElement(_*, Fib[arg=n,val=Undef()], _*) -> {
		if(`n >2 && !`occursFib(n-1)) {
		    WM = WM.add(`Fib(n-1,Undef));
		    fire++;
		    return true;
		}
	    }
	}
	return false;
    }

    public boolean compute() {
	%match(Space WM) {
	    concElement(_*, f@Fib[arg=n,val=Undef()], _*) -> {
		%match(Space WM) {
		    concElement(_*, f1@Fib[arg=n1,val=Nat(v1)], _*, f2@Fib[arg=n2,val=Nat(v2)], _*) -> {
			//if(`(f!=f1 && f!=f2)) {
			    if(`(n1+1==n && n2+2==n) || `(n2+1==n && n1+2==n)) {
				int modulo = (`v1+`v2)%1000000;
				WM = WM.remove(`f);
				WM = WM.add(`Fib(n,Nat(modulo)));
				if(opt) {
				    WM = WM.remove(`(n2+2==n)?`f2:`f1);
				}
				fire++;
				return true;
			    }
			    //}

		    }
		}
	    }
	}
	return false;
    }

    public boolean occursFib(int value) {
	%match(Space WM) {
	    concElement(_*, Fib[arg=n], _*) -> {
		if(`n == value) {
		    return true;
		}
	    }
	}
	return false;
    }

    public int result(int value) {
	%match(Space WM) {
	    concElement(_*, Fib[arg=n, val=Nat(v)], _*) -> {
		if(`n == value) {
		    return `v;
		}
	    }
	}
	return 0;
    }

    
}

class MyList {
    private Element head;
    private MyList tail;

    public Element getHead() {
	return head;
    }

    public MyList getTail() {
	return tail;
    }

    public MyList(Element head,MyList tail) {
	this.head = head;
	this.tail = tail;
    }

    public MyList() {
	this.head = null;
	this.tail = null;
    }

    public boolean isEmpty() {
	return head==null && tail==null;
    }

    public int size() {
	if(isEmpty()) {
	    return 0;
	} else {
	    return 1+getTail().size();
	}
    }

    public MyList add(Element e) {
	return new MyList(e,this);
    }

    public MyList remove(Element e) {
	if(isEmpty()) {
	    return this;
	} else if(head==e) {
	    return tail;
	} else {
	    return new MyList(head,tail.remove(e));
	}
    }

    public String toString() {
	if(isEmpty()) {
	    return "nil";
	} else {
	    return head + "." + tail.toString();
	}
    }

}
