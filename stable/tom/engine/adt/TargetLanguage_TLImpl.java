package jtom.adt;

import aterm.*;

public class TargetLanguage_TLImpl
extends TargetLanguage
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_code = 0;

  public shared.SharedObject duplicate() {
    TargetLanguage_TL clone = new TargetLanguage_TL();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makeTargetLanguage_TL(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("TL(<str>)");
  }


  static public TargetLanguage fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      TargetLanguage tmp = getStaticTomSignatureFactory().makeTargetLanguage_TL((String) children.get(0));
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
  }

  public aterm.ATerm toTerm() {
    if(term == null) {
      java.util.List args = new java.util.LinkedList();
      args.add(((aterm.ATermAppl) getArgument(0)).getAFun().getName());
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public boolean isTL()
  {
    return true;
  }

  public boolean hasCode()
  {
    return true;
  }


  public String getCode()
  {
   return ((aterm.ATermAppl) this.getArgument(index_code)).getAFun().getName();
  }

  public TargetLanguage setCode(String _code)
  {
    return (TargetLanguage) super.setArgument(getFactory().makeAppl(getFactory().makeAFun(_code, 0, true)), index_code);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermAppl)) { 
          throw new RuntimeException("Argument 0 of a TargetLanguage_TL should have type str");
        }
        break;
      default: throw new RuntimeException("TargetLanguage_TL does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }

}
