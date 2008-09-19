import tom.library.sl.*;
import lambda.types.*;
import org.antlr.runtime.*;
import java.util.*;

public class FreshLambda {

  %include { sl.tom }
  %include { lambda/Lambda.tom }

  // returns t[u/x]
  public static LTerm substitute(LTerm t, LVar x, LTerm u) {
    %match(t) {
      App(t1,t2) -> { return `App(substitute(t1,x,u),substitute(t2,x,u)); }
      Abs(lam(v,t1)) -> { return `Abs(lam(v,substitute(t1,x,u))); }
      Branch(a,b,c) -> { return `Branch(substitute(a,x,u),substitute(b,x,u),substitute(c,x,u)); }
      Let(letin(v,t1,t2)) -> {
        return `Let(letin(v,substitute(t1,x,u),substitute(t2,x,u)));
      }
      Var(y) -> { if (`y.equals(x)) return u; else return t; }
      Plus(t1,t2) -> { return `Plus(substitute(t1,x,u),substitute(t2,x,u)); }
      Eq(t1,t2) -> { return `Eq(substitute(t1,x,u),substitute(t2,x,u)); }
      _ -> { return `t; }
    }
    throw new RuntimeException();
  }

  public static LTerm cpsaux(LTerm t) {
    %match(t) {
      (Var|Integer|Plus|Eq)[] -> { 
        return t;
      }
      Abs(lam(x,m)) -> {
        LVar k = LVar.freshLVar("k");
        return `Abs(lam(x,cps(m)));
      }
    }
    return null;
  }

  public static LTerm cps(LTerm t) {
    %match(t) {
      (Var|Integer|Plus|Eq|Abs)[] -> { 
        LVar k = LVar.freshLVar("k");
        return `Abs(lam(k,App(Var(k),cpsaux(t))));
      }
      App(a,b) -> {
        LVar k = LVar.freshLVar("k");
        LVar va = LVar.freshLVar("va");
        LVar vb = LVar.freshLVar("vb");
        return `Abs(lam(k,App(cps(a),Abs(lam(va,App(cps(b),Abs(lam(vb,App(App(Var(va),Var(vb)),Var(k))))))))));
      }
      Let(letin(x,a,b)) -> {
        LVar k = LVar.freshLVar("k");
        return `Abs(lam(k,App(cps(a),Abs(lam(x,App(cps(b),Var(k)))))));
      }
      Branch(a,b,c) -> {
        LVar k = LVar.freshLVar("k");
        LVar va = LVar.freshLVar("va");
        return `Abs(lam(k,App(cps(a),Abs(lam(va,Branch(Var(va),App(cps(b),Var(k)),App(cps(c),Var(k))))))));
      }
      CallCC(a) -> {
        LVar k = LVar.freshLVar("k");
        return `Abs(lam(k,App(App(cpsaux(a),Var(k)),Var(k))));
      }
      Throw(a,b) -> {
        LVar k = LVar.freshLVar("k");
        LVar va = LVar.freshLVar("va");
        LVar vb = LVar.freshLVar("vb");
        return `Abs(lam(k,App(cps(a),Abs(lam(va,App(cps(b),Abs(lam(vb,App(Var(va),Var(vb))))))))));
      }
    }
    return null;
  }

  public static LTerm eval(LTerm t) {
    %match(t) {
      (Integer|True|False|Abs)[] -> { return `t; }
      Branch(a,b,c) -> {
        %match(eval(a)) {
          True() -> { return `eval(b); }
          False() -> { return `eval(c); }
        }
      }
      Let(letin(x,a,b)) -> {
        LTerm v = `eval(a);
        return `eval(substitute(b,x,v));
      }
      Plus(a,b) && Integer(n) << eval(a) && Integer(m) << eval(b) -> {
        return `Integer(n+m);
      }
      Eq(a,b) -> {
        return `eval(a).equals(`eval(b)) ? `True() : `False();
      }
      App(a,b) && Abs(lam(x,c)) << eval(a) -> {
        LTerm v = `eval(b);
        return `eval(substitute(c,x,v));
      }
    }
    throw new RuntimeException();
  }

  /* parser loop */
  public static void main(String[] args) {
    try{
      LambdaLexer lexer = new LambdaLexer(new ANTLRInputStream(System.in));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      LambdaParser parser = new LambdaParser(tokens);
      for(RawLTerm rt:parser.toplevel()) {
        System.out.println("\nparsed: " + Printer.pretty(rt));
        LTerm ct = rt.convert(); 
        //System.out.println("\nnormal form : " + Printer.pretty(eval(ct).export()));
        LTerm cpst = cps(ct);
        System.out.println("\ncps translation : " + Printer.pretty(cpst.export()));
        LVar fresh = LVar.freshLVar("x");
        LTerm id = `Abs(lam(fresh,Var(fresh)));
        System.out.println("\napplied to id normal form : " + Printer.pretty(eval(`App(cpst,id)).export()));
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
