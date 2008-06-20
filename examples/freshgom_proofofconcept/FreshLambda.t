package freshgom_proofofconcept;

import tom.library.sl.*;
import freshgom_proofofconcept.lambda.types.*;
import java.util.*;

public class FreshLambda {

  %include { sl.tom }
  %include { tweaked_lambda.tom }

  private static class Context {
    private class Pair { 
      public String x;
      public Atom a;
      public Pair(String x, Atom a) {
        this.x = x;
        this.a = a;
      } 
    }
    private LinkedList<Pair> ctx = new LinkedList<Pair>();
    public void push(String x, Atom a) { ctx.addFirst(new Pair(x,a)); }
    public void pop() { ctx.removeFirst(); }
    public Atom get(String x) { 
      for (Pair p: ctx) {
        if (p.x.equals(x)) return p.a;
      }
      throw new RuntimeException();
    }
  }

  public static LTerm importRLTerm(RLTerm t, Context ctx) {
    %match(t) {
      rapp(t1,t2) -> { 
        return `app(importRLTerm(t1,ctx),importRLTerm(t2,ctx)); 
      }
      rabs(x,t1) -> {
        Atom fresh = LTerm.freshAtom(`x);
        ctx.push(`x,fresh);
        LTerm nt1 = importRLTerm(`t1,ctx);
        ctx.pop();
        return `abs(fresh,nt1);
      }
      rvar(x) -> { return `var(ctx.get(x)); }
    }
    return null;
  }

  private static class Context2 {
    private class Pair { 
      public Atom a;
      public int i;
      public Pair(Atom a, int i) {
        this.a = a;
        this.i = i;
      } 
    }
    private LinkedList<Pair> ctx = new LinkedList<Pair>();
    public int push(Atom a) {
      int i = 0;
      for (Pair p: ctx) {
        if (p.a.gethint().equals(a.gethint())) { 
          i = p.i+1;
          break;
        }
      }
      ctx.addFirst(new Pair(a,i)); 
      return i;
    }
    public void pop() { ctx.removeFirst(); }
    public int get(Atom a) { 
      for (Pair p: ctx) {
        if (p.a.equals(a)) return p.i;
      }
      throw new RuntimeException();
    }
  }

  public static RLTerm exportLTerm(LTerm t, Context2 ctx) {
    %match(t) {
      app(t1,t2) -> { 
        return `rapp(exportLTerm(t1,ctx),exportLTerm(t2,ctx)); 
      }
      abs(x,t1) -> {
        int i = ctx.push(`x);
        RLTerm nt1 = exportLTerm(`t1,ctx);
        ctx.pop();
        return `rabs(x.gethint()+i,nt1);
      }
      var(x) -> { return `rvar(x.gethint()+ctx.get(x)); }
    }
    return null;
  }

  // return t[u/x]
  public static LTerm substitute(LTerm t, Atom x, LTerm u) {
    %match(t) {
      abs(y,t1) -> { return `abs(y,substitute(t1,x,u)); }
      app(t1,t2) -> { return `app(substitute(t1,x,u),substitute(t2,x,u)); }
      var(y) -> { if (`y.equals(x)) return u; else return t; }
    }
    throw new RuntimeException();
  }

  %strategy HeadBeta() extends Fail() {
    visit LTerm {
      app(abs(x,t),u) -> { return `substitute(t,x,u); }
    }
  }

  public static LTerm beta(LTerm t) {
    try { return (LTerm) `Innermost(HeadBeta()).visit(t); }
    catch (VisitFailure e) { return t; }
  }

  public static void main(String[] args) {
    RLTerm rt = `rabs("y",rapp(rabs("x",rabs("y",rapp(rvar("x"),rvar("y")))),rvar("y")));
    System.out.println(rt);

    LTerm t = importRLTerm(rt,new Context());
    t = beta(t);

    rt = exportLTerm(t,new Context2());
    System.out.println(rt);
  }
}
