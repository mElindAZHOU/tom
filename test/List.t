import aterm.*;
import aterm.pure.*;
import java.util.*;

public class List {
  private ATermFactory factory;

  %typelist L {
    implement { ATermList }
    get_fun_sym(t) { ((t instanceof ATermList)?factory.makeAFun("conc", 1, false):null) }
    cmp_fun_sym(t1,t2) { t1 == t2 }
    equals(l1,l2)  { l1==l2 }
    get_head(l)    { ((ATermList)l).getFirst() }
    get_tail(l)    { ((ATermList)l).getNext() }
    is_empty(l)    { ((ATermList)l).isEmpty() }
  }

  %oplist L conc( E* ) {
    fsym { factory.makeAFun("conc", 1, false) }
    make_empty()  { factory.makeList() }
    make_insert(e,l) { ((ATermList)l).insert((ATerm)e) }
  }
  
  %typeterm E {
    implement { ATerm }
    cmp_fun_sym(t1,t2)  { t1 == t2 }
    get_fun_sym(t)      { (((ATermAppl)t).getAFun()) }
    get_subterm(t, n)   { (((ATermAppl)t).getArgument(n)) }
    equals(t1, t2)      { t1==t2 }
  }

  %op E a {
    fsym { factory.makeAFun("a", 0, false) }
    make() { factory.makeAppl(factory.makeAFun("a", 0, false)) }
  }
  
  %op E b {
    fsym { factory.makeAFun("b", 0, false) }
    make() { factory.makeAppl(factory.makeAFun("b", 0, false)) }
  }

  %op E c {
    fsym { factory.makeAFun("c", 0, false) }
    make() { factory.makeAppl(factory.makeAFun("c", 0, false)) }
  }
  
  %op L double3(L) {
    fsym { factory.makeAFun("double3", 1, false) }
    make(l) { double3(l) }
  }

  %op L id(L) {
    fsym { factory.makeAFun("id", 1, false) }
    make(l) { l }
  }

  %rule {
    double3(conc(X1*,x,X2*,x,X3*)) -> double3(conc(X1*,X2*,x,X3*))
    double3(conc(X*)) -> conc(X*)
  } 

  public List(ATermFactory factory) {
    this.factory = factory;
  }

  public final static void main(String[] args) {
    List test = new List(new PureFactory(16));
    test.testList1();
    test.testList2();
  }

  public ATermList sort1(ATermList l) {
    %match(L l) {
      conc(X1*,x,X2*,y,X3*) -> {
        String xname = ((ATermAppl)x).getName();
        String yname = ((ATermAppl)y).getName();
        
        if(xname.compareTo(yname) > 0) {
          return sort1(
            X1
            .append(y)
            .concat(X2)
            .append(x)
            .concat(X3)
            );
        }
      }

      _ -> { return l; }
    }
  }

  public ATermList double1(ATermList l) {
    %match(L l) {
      conc(X1*,x,X2*,x,X3*) -> {
        return double1(
          X1
          .concat(X2)
          .append(x)
          .concat(X3)
          );
      }
      
      _ -> { return l; }
    }
  }

  public ATermList sort2(ATermList l) {
    %match(L l) {
      conc(X1*,x,y,X2*) -> {
        String xname = ((ATermAppl)x).getName();
        String yname = ((ATermAppl)y).getName();
        if(xname.compareTo(yname) > 0) {
          return `sort2(conc(X1*,y,x,X2*));
        }
      }

      _ -> { return l; }
    }
  }

  public ATermList double2(ATermList l) {
    %match(L l) {
      (X1*,x,X2*,x,X3*) -> {
        return `double2(id( conc(X1*,X2*,x,X3*) ));
      }
      
      _ -> { return l; }
    }
  }

  public ATermList double4(ATermList l) {
    %match(L l) {
      conc(X1*,x@_,X2@_*,x,X3@_*) -> { return `double4(conc(X1*,X2*,x,X3*)); }
      _ -> { return l; }
    }
  }

  public ATermList double5(ATermList l) {
    %match(L l) {
      conc(X1*,x@a(),X2*,x@a(),X3*) -> { return `double5(conc(X1*,X2*,x,X3*)); }
      conc(X1*,x@_,X2*,x@_,X3*) -> { return `double5(conc(X1*,X2*,x,X3*)); }
      conc(X1*,x@y,X2*,y@x,X3*) -> { return `double5(conc(X1*,X2*,x,X3*)); }
      _ -> { return l; }
    }
  }

  public void testList1() {
    ATerm ta = factory.makeAppl(factory.makeAFun("a", 0, false));
    ATerm tb = factory.makeAppl(factory.makeAFun("b", 0, false));
    ATerm tc = factory.makeAppl(factory.makeAFun("c", 0, false));
    ATermList l = factory.makeList()
      .append(ta).append(tb).append(tc)
      .append(ta).append(tb).append(tc)
      .append(ta);

    ATermList res = factory.makeList()
      .append(ta).append(ta).append(ta)
      .append(tb).append(tb).append(tc)
      .append(tc);
    
    assertTrue(sort1(l) == res);
    assertTrue(sort2(l) == res);
  }
 
  public void testList2() {
    ATermList l   = `conc(a,b,c,a,b,c,a);
    ATermList res = `conc(a,b,c);
    
    assertTrue(double1(sort1(l)) == res);

    assertTrue(double2(sort2(l)) == res);
    assertTrue(double3(sort2(l)) == res);
    assertTrue(double4(sort2(l)) == res);
    assertTrue(double5(sort2(l)) == res);


      //System.out.println("l        = " + l);
      //System.out.println("sorted l = " + sort2(l));
      //System.out.println("double l = " + double2(sort2(l)));
  }
  
  static void  assertTrue(boolean condition) {
    if(!condition) {
      throw new RuntimeException("assertion failed.");
    }
  }
 
}

