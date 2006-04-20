import junit.framework.TestCase;
import junit.framework.TestSuite;

import tom.library.strategy.mutraveler.MuTraveler;
import tom.library.strategy.mutraveler.Identity;
import tom.library.strategy.mutraveler.Position;
import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.Visitable;
import jjtraveler.VisitFailure;

import teststrategy.term.types.*;

import java.util.*;

public class TestStrategy extends TestCase {

  %include { mutraveler.tom }
  %include { java/util/ArrayList.tom }
  %include { java/util/LinkedList.tom }

%gom{
    module Term

      abstract syntax
      Term = a() | b() | c() | d()
      | f(s1:Term)
      | ff(s1:Term)
      | g(s1:Term,s2:Term)
      | l(s3:L)
      | h(s1:Term,s2:Term)
      Term1 = e()
      L = concTerm(Term*)
  } 

  %typeterm Position {
    implement { tom.library.strategy.mutraveler.Position }
  }

    boolean bool0 = true;

  public static void main(String[] args) {
    junit.textui.TestRunner.run(new TestSuite(TestStrategy.class));
  }

  public void setUp() {
  }

  public void testS(){
    
    ArrayList arrayList = new ArrayList();
    LinkedList linkedList = new LinkedList();
    boolean bool = true;
    long along = 1;
    String string = new String();
    int i = 0;
    
    VisitableVisitor rule0 = `S0();
    VisitableVisitor rule1 = `S1(bool);
    VisitableVisitor rule2 = `S2(along,string);
    VisitableVisitor rule3 = `S3(i);
    VisitableVisitor rule4 = `S4(arrayList, linkedList);
    VisitableVisitor rule5 = `S5();
    VisitableVisitor rule6 = `S6();
    VisitableVisitor rule7 = `S7();
    VisitableVisitor rule8 = `S8(bool);
    VisitableVisitor rule9 = `S9(i);
    
    try{
      assertSame("g(a,a) return a", MuTraveler.init(`rule0).visit(`g(a(),a())), `a());
      assertSame("a return b", MuTraveler.init(`rule1).visit(`a()), `b());
      assertSame("g(a,a) return a", MuTraveler.init(`rule2).visit(`g(a(),a())), `a());
      assertSame("g(a,a) return a", MuTraveler.init(`rule3).visit(`g(a(),a())), `a());
      assertSame("g(a,b) return g(b,a)", MuTraveler.init(`rule4).visit(`g(a(),b())), `g(b(),a()));
      assertSame("g(a,a) return a", MuTraveler.init(`rule5).visit(`g(a(),a())), `a());
      assertSame("g(a,a) return a", MuTraveler.init(`rule6).visit(`g(a(),a())), `a());
      assertSame("g(a,a) return a", MuTraveler.init(`rule7).visit(`g(a(),a())), `a());
      assertSame("g(a,a) return a", MuTraveler.init(`rule8).visit(`g(a(),a())), `a());
      assertSame("g(a,a) return a", MuTraveler.init(`rule9).visit(`g(a(),a())), `a());
    } catch (VisitFailure e){
      System.out.println("VisitFailure");
    }
  }

  public void testPosition() {

    Term t = `f(a());
    Term tBis = `f(b());
    Position p1 = new Position();
    Position p2 = new Position();
    Position p3 = new Position();
    VisitableVisitor getPos1 = `GetPositionA(p1);
    VisitableVisitor getPos2 = `GetPositionA(p2);
    VisitableVisitor getPos3 = `GetPositionA(p3);

    try{
      MuTraveler.init(`BottomUp(getPos1)).visit(t);
      MuTraveler.init(`BottomUp(getPos2)).visit(t);
      MuTraveler.init(`BottomUp(getPos3)).visit(tBis);
    } catch (VisitFailure e){
      System.out.println("VisitFailure");
    }
    assertTrue("equality on Position", p1.equals(p2));
    assertFalse("inequality on Position", p1.equals(p3));
  }

  %strategy GetPositionA(pos:Position) extends `Identity(){
    visit Term {
      a() -> {pos.setPosition(MuTraveler.getPosition(this));}
    }
  }

  %strategy S0() extends `Identity() {
    visit Term {
      g(x,x)            -> { return `x; }
    }
  }

  //with parameter
  %strategy S1(b:boolean) extends `Identity() {
    visit Term {
      a()          -> { return `b(); }
    }
  }

  //with 2 parameters
  %strategy S2(l:long,s:String) extends `Identity() {
    visit Term {
      g(x,x)            -> { return `x; }
    }
  }

  //with builtin parameter
  %strategy S3(i:int) extends `Identity() {
    visit Term {
      g(x,x)            -> { return `x; }
    }
  }

  %strategy S4(arrayList:ArrayList,linkedList:LinkedList) extends `Identity() {
    visit Term {
      g(x,y)            -> { arrayList.add(`x); linkedList.add("ok"); return `g(y,x);}
    }
  }

   //with many visits
  %strategy S5() extends `Identity() {
    visit Term {
      g(x,x)            -> { return `x; }
    }
    visit Term1 {
      e()            -> { return `e(); }
    }
  }
  //with visits in reverse order
  %strategy S6() extends `Identity() {
    visit Term1 {
      e()            -> { return `e(); }
    }

    visit Term {
      g(x,x)            -> { return `x; }
    }
  }

  %strategy S7() extends `S8(bool0) {
    visit Term {
      g(x,x)            -> { return `x; }
    }
  }
  %strategy S8(b:boolean) extends `S9(3) {
    visit Term {
      g(x,x)            -> { return `x; }
    }
  }

  %strategy S9(i:int) extends `Identity() {
    visit Term {
      g(x,x)            -> { return `x; }
    }
  }


  //with no visit
  //%strategy S4() extends `Identity() {
  //}


  //with empty visit

  //with underscore only

}
