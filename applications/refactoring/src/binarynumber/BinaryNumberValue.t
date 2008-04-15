package binarynumber;
import binarynumber.binarynumber.*;
import binarynumber.binarynumber.types.*;
import binarynumber.binarynumber.types.bit.*;
import binarynumber.binarynumber.types.bitlist.*;
import binarynumber.binarynumber.types.binarynumber.*;
import tom.library.sl.*;
import java.util.*;

public class BinaryNumberValue {

  %include{ sl.tom }
  %include{ binarynumber/BinaryNumber.tom }

  static class Context {
    public HashMap<Position,Double> values = new HashMap();
    public HashMap<Position,Integer> scales = new HashMap();
    public HashMap<Position,Integer> lengths = new HashMap();
    public Context() {}
  }

  %typeterm Context {
    implement { Context }
  }

  public static Double value(BinaryNumber n) {
    Context context = new Context();
    try {
      `BottomUp(length(context)).visit(n);
      `TopDown(scale(context)).visit(n);
      `BottomUp(value(context)).visit(n);
      return context.values.get(new Position());
    } catch (VisitFailure v) {
      throw new RuntimeException("Unexpected VisitFailure");
    }
  }


  %strategy value(context:Context) extends Identity() {
    visit Bit {
      // eq Zero.value() = 0;
      Zero() -> {
        context.values.put(getPosition(),new Double(0));
      }
      //eq OneB.value() = java.lang.Math.pow(2.0, scale());
      OneB() -> {
        Double d = new Double(context.scales.get(getPosition()));
        context.values.put(getPosition(),java.lang.Math.pow(2.0, d));
      }
    }

    visit BitList {
      //eq SingularBitList.value() = getBit().value();
      SingularBitList(bit) -> {
        Double d = context.values.get(getPosition().down(1));
        context.values.put(getPosition(),d);
      }
      //eq PluralBitList.value() = getBit().value() + getBitList().value();
      PluralBitList(bitList,bit) -> {
        Double d1 = context.values.get(getPosition().down(1));
        Double d2 = context.values.get(getPosition().down(2));
        context.values.put(getPosition(),d1+d2);
      }
    }

    visit BinaryNumber {
      //eq IntegralNumber.value() = getIntegralPart().value();
      IntegralNumber(IntegralPart) -> {
        Double d = context.values.get(getPosition().down(1));
        context.values.put(getPosition(),d);
      }

      //eq RationalNumber.value() = getIntegralPart().value() + getFractionalPart().value();
      RationalNumber(IntegralPart,FractionalPart) -> {
        Double d1 = context.values.get(getPosition().down(1));
        Double d2 = context.values.get(getPosition().down(2));
        context.values.put(getPosition(),d1+d2);
      }
    }
  }


  %strategy scale(context:Context) extends Identity() {
    visit Bit {
      x -> {
        getEnvironment().up();
        if (getEnvironment().getSubject() instanceof SingularBitList) {      
          //eq SingularBitList.getBit().scale() = scale();
          Integer i = context.scales.get(getPosition());
          getEnvironment().down(1);
          context.scales.put(getPosition(),i);
        } else {
          //eq PluralBitList.getBit().scale() = scale();
          if (getEnvironment().getSubject() instanceof PluralBitList) {      
            Integer i = context.scales.get(getPosition());
            getEnvironment().down(2);
            context.scales.put(getPosition(),i);
          } else {
            getEnvironment().down(1);
          }
        }
      }
    }

    visit BitList {
      x  -> {
        Integer index = getEnvironment().getSubOmega();
        getEnvironment().up();
        if (getEnvironment().getSubject() instanceof PluralBitList) {      
          //eq PluralBitList.getBitList().scale() = scale() + 1;
          Integer i = context.scales.get(getPosition());
          getEnvironment().down(1);
          context.scales.put(getPosition(),i+1);
        } else {
          //eq IntegralNumber.getIntegralPart().scale() = 0;
          if (getEnvironment().getSubject() instanceof IntegralNumber) {      
            getEnvironment().down(1);
            context.scales.put(getPosition(),0);
          } else {
            if (getEnvironment().getSubject() instanceof RationalNumber) {      
              if (index == 1) {
                //eq RationalNumber.getIntegralPart().scale() = 0;
                getEnvironment().down(1);
                context.scales.put(getPosition(),0);
              } else {
                //eq RationalNumber.getFractionalPart().scale() = -getFractionalPart().length();
                getEnvironment().down(2);
                Integer i = context.lengths.get(getPosition());
                context.scales.put(getPosition(),-i);
              } 
            } else {
              getEnvironment().down(index);
            }
          }
        }
      }
    }

  }


  %strategy length(context:Context) extends Identity() {
    visit BitList {
      //eq SingularBitList.length() = 1;
      SingularBitList[] -> {
        context.lengths.put(getPosition(),1);
      }
      //eq PluralBitList.length() = getBitList().length() + 1;
      PluralBitList(bitList,bit) -> {
        Integer i = context.lengths.get(getPosition().down(1));
        context.lengths.put(getPosition(),i+1);
      }
    }
  }

}
