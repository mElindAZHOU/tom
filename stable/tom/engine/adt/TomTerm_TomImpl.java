package jtom.adt;

import aterm.*;

public class TomTerm_TomImpl
extends TomTerm
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_list = 0;

  public shared.SharedObject duplicate() {
    TomTerm_Tom clone = new TomTerm_Tom();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makeTomTerm_Tom(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("Tom(<term>)");
  }


  static public TomTerm fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      TomTerm tmp = getStaticTomSignatureFactory().makeTomTerm_Tom(TomList.fromTerm( (aterm.ATerm) children.get(0)));
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
  }

  public boolean isTom()
  {
    return true;
  }

  public boolean hasList()
  {
    return true;
  }


  public TomList getList()
  {
    return (TomList) this.getArgument(index_list) ;
  }

  public TomTerm setList(TomList _list)
  {
    return (TomTerm) super.setArgument(_list, index_list);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof TomList)) { 
          throw new RuntimeException("Argument 0 of a TomTerm_Tom should have type TomList");
        }
        break;
      default: throw new RuntimeException("TomTerm_Tom does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }

  protected int hashFunction() {
    int c = 0 + (getAnnotations().hashCode()<<8);
    int a = 0x9e3779b9;
    int b = 0x9e3779b9;
    a += (getArgument(0).hashCode() << 0);

    a -= b; a -= c; a ^= (c >> 13);
    b -= c; b -= a; b ^= (a << 8);
    c -= a; c -= b; c ^= (b >> 13);
    a -= b; a -= c; a ^= (c >> 12);
    b -= c; b -= a; b ^= (a << 16);
    c -= a; c -= b; c ^= (b >> 5);
    a -= b; a -= c; a ^= (c >> 3);
    b -= c; b -= a; b ^= (a << 10);
    c -= a; c -= b; c ^= (b >> 15);

    return c;
  }
}
