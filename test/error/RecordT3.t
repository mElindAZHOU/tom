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

import java.util.*;

public class RecordT3 {

    // ------------------------------------------------------------
  abstract class Exp {
    public abstract String getOperator();
  }
  
  class CstExp extends Exp {
    public Object value;
    public CstExp(Object value) {
      this.value = value;
    }
    public String getOperator() {
      return "" + value;
    }
  }

  class IntExp extends CstExp {
    public IntExp(int value) {
      super(new Integer(value));
    }
  }
  
  class StringExp extends CstExp {
    public StringExp(String value) {
      super(value);
    }
  }

  class UnaryOperator extends Exp {
    public Exp first;
    public UnaryOperator(Exp first) {
      this.first = first;
    } 
    public String getOperator() { return ""; }
  }

  class BinaryOperator extends Exp {
    public Exp first;
    public Exp second;
    public BinaryOperator(Exp first, Exp second) {
      this.first = first;
      this.second = second;
    }
    public String getOperator() { return ""; }
  }
  
  class Plus extends BinaryOperator {
    public Plus(Exp first, Exp second) {
      super(first,second);
    }
    public String getOperator() { return "Plus"; }
  }

  class Mult extends BinaryOperator {
    public Mult(Exp first, Exp second) {
      super(first,second);
    }
    public String getOperator() { return "Mult"; }
  }
  
  class Uminus extends UnaryOperator {
    public Uminus(Exp first) {
      super(first);
    }
    public String getOperator() { return "Uminus"; }
  }

    // ------------------------------------------------------------
  
  private Object myGetSubterm(Object t, int n) {
    try {
        //System.out.println("fields[" + n + "] = " + (t.getClass().getFields()[n]));
      return (t.getClass().getFields()[n]).get(t);
    } catch (Exception e) {
      return null;
    }
  }
  
  %typeterm TomObject {
    implement { Object }
    get_fun_sym(t)      { t.getClass() }
    cmp_fun_sym(subjectFunSym,patternFunSym)  {
      ((Class)patternFunSym).isAssignableFrom(((Class)subjectFunSym))
        }
    get_subterm(t, n)   { myGetSubterm(t,n) }
  }

  %typeterm TomExp {
    implement { Exp }
    get_fun_sym(t)      { t.getClass() }
    cmp_fun_sym(subjectFunSym,patternFunSym)  {
      ((Class)patternFunSym).isAssignableFrom(((Class)subjectFunSym))
        }
    get_subterm(t, n)   { myGetSubterm(t,n) }
  }

  %typeterm TomBinaryOperator {
    implement { BinaryOperator }
    get_fun_sym(t)      { t.getClass() }
    cmp_fun_sym(subjectFunSym,patternFunSym)  {
      ((Class)patternFunSym).isAssignableFrom(((Class)subjectFunSym))
        }
    get_subterm(t, n)   { (n==0)?((BinaryOperator)t).first:((BinaryOperator)t).second }
  }
  
  %typeterm TomUnaryOperator {
    implement { UnaryOperator }
    get_fun_sym(t)      { t.getClass() }
    cmp_fun_sym(subjectFunSym,patternFunSym)  {
      ((Class)patternFunSym).isAssignableFrom(((Class)subjectFunSym))
        }
    get_subterm(t, n)   { ((UnaryOperator)t).first }
  }

  %typeterm TomCstExp {
    implement { CstExp }
    get_fun_sym(t)      { t.getClass() }
    cmp_fun_sym(subjectFunSym,patternFunSym)  {
      ((Class)patternFunSym).isAssignableFrom(((Class)subjectFunSym))
        }
    get_subterm(t, n)   { ((CstExp)t).value }
  }

    // ------------------------------------------------------------
  
  %op TomBinaryOperator BinaryOperator(first:TomExp, second:TomExp) {
    fsym { (new BinaryOperator(null,null)).getClass() }
  }

  %op TomUnaryOperator UnaryOperator(first:TomExp) {
    fsym { (new UnaryOperator(null)).getClass() }
  }

  %op TomBinaryOperator Plus(first:TomExp, second:TomExp) {
    fsym { (new Plus(null,null)).getClass() }
  }

  %op TomBinaryOperator Mult(first:TomExp, second:TomExp) {
    fsym { (new Mult(null,null)).getClass() }
  }

  %op TomUnaryOperator Uminus(first:TomExp) {
    fsym { (new Uminus(null)).getClass() }
  }

  %op TomCstExp CstExp(value:TomObject) {
    fsym { (new CstExp(null)).getClass() }
  }

  %op TomCstExp IntExp(value:TomInteger) {
    fsym { (new IntExp(0)).getClass() }
  }

    // ------------------------------------------------------------
  
  private final static Integer ZERO = new Integer(0);
  private final static Integer SUC  = new Integer(1);
  
  %typeterm TomInteger {
    implement { Integer }
    get_fun_sym(i)      { (((Integer)i).intValue()==0)?ZERO:SUC }
    cmp_fun_sym(i1,i2)  { i1 == i2 }
    get_subterm(i, n)   { new Integer(((Integer)i).intValue()-1) }
  }

  %op TomInteger zero {
    fsym { ZERO }
  }

  %op TomInteger suc(TomInteger) {
    fsym { SUC }
  }

    // ------------------------------------------------------------

  public final static void main(String[] args) {
    RecordT3 test = new RecordT3();
    test.go();
  }

  public Exp buildExp1() {
    return new Mult(new Plus(new IntExp(2), new IntExp(3)), new IntExp(4));
  }

  public Exp buildExp2() {
    return new Mult(new Plus(new StringExp("a"), new IntExp(0)), new IntExp(1));
  }

  public Exp buildExp3() {
    return new Plus(buildExp2(), new Uminus(new StringExp("a")));
  }

  public Exp simplifiedExp1() {
    return new IntExp(20);
  }
  
  public Exp simplifiedExp2() {
    return new StringExp("a");
  }

  public Exp simplifiedExp3() {
    return new IntExp(0);
  }

  public void go() {
    run("e1", buildExp1());
    run("e2", buildExp2());
    run("e3", buildExp3());
  }


  
  public void run(String comment, Exp e) {
    System.out.println("prettyPrint " + comment + "     = " + prettyPrint(e));
    System.out.println("prettyPrintInv " + comment + "  = " + prettyPrintInv(e));
    System.out.println("simplify " + comment + "        = " + prettyPrint(traversalSimplify(e)));
    System.out.println();
  }
  
  public String prettyPrint(Exp t) {
    String op = t.getOperator();
    %match(TomExp t) {
      CstExp[]  -> { return op; }
      
      UnaryOperator[first=e1] -> {
        return op + "(" + prettyPrint(e1) + ")";
      }

      BinaryOperator[first=e1,second=e2] -> {
        return op + "(" + prettyPrint(e1) + "," + prettyPrint(e2) + ")";
      }
    }
    return "error";
  }

  public String prettyPrintInv(Exp t) {
    String op = t.getOperator();
    %match(TomExp t) {
      CstExp[]  -> { return op; }
      
      UnaryOperator[first=e1] -> {
        return prettyPrintInv(e1) + " " + op;
      }
      
      BinaryOperator[first=e1,second=e2] -> {
        return prettyPrintInv(e1) + " " + prettyPrintInv(e2) + " " + op;
      }
    }
    return "error";
  }

  public Exp traversalSimplify(Exp t) {
    %match(TomExp t) {
      UnaryOperator[first=e1] -> {
        ((UnaryOperator)t).first  = traversalSimplify(e1);
        return simplify(t);
      }
      
      BinaryOperator[first=e1, second=e2] -> {
        ((BinaryOperator)t).first  = traversalSimplify(e1);
        ((BinaryOperator)t).second = traversalSimplify(e2);
        return simplify(t);
      }
    }
    return t;
  }

  public Exp simplify(Exp t) {
    %match(TomExp t) {
      Plus[first=IntExp(v1), second=IntExp(v2)] -> {
        return new IntExp(v1.intValue() + v2.intValue());
      }

      Plus[first=e1, second=IntExp(zero)] -> { return e1                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            