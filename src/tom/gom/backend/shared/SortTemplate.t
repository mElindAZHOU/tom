/*
 * Gom
 *
 * Copyright (c) 2006-2012, INPL, INRIA
 * Nancy, France.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 *
 * Antoine Reilles  e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.gom.backend.shared;

import java.io.*;
import java.util.*;

import tom.gom.backend.TemplateClass;
import tom.gom.backend.TemplateHookedClass;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;
import tom.gom.tools.GomEnvironment;
import tom.platform.OptionManager;

public class SortTemplate extends TemplateHookedClass {
  ClassName abstractType;
  ClassNameList operatorList;
  ClassNameList variadicOperatorList;
  SlotFieldList slotList;
  boolean maximalsharing;

  %include { ../../adt/objects/Objects.tom}

  public SortTemplate(File tomHomePath,
                      OptionManager manager,
                      boolean maximalsharing,
                      List importList, 	
                      GomClass gomClass,
                      TemplateClass mapping,
                      GomEnvironment gomEnvironment) {
    super(gomClass,manager,tomHomePath,importList,mapping,gomEnvironment);
    this.maximalsharing = maximalsharing;
    %match(gomClass) {
      SortClass[AbstractType=abstractType,
                Operators=ops,
                VariadicOperators=variops,
                SlotFields=slots] -> {
        this.abstractType = `abstractType;
        this.operatorList = `ops;
        this.variadicOperatorList = `variops;
        this.slotList = `slots;
        return;
      }
    }
    throw new GomRuntimeException(
        "Bad argument for SortTemplate: " + gomClass);
  }

  protected String generateInterface() {
    String interfaces =  super.generateInterface();
    if (interfaces.equals("")) return "";
    else return %[implements @interfaces.substring(1)@]%;
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    writer.write(%[
package @getPackage()@;
@generateImport()@

public abstract class @className()@ extends @fullClassName(abstractType)@ @generateInterface()@ {
  /**
   * Sole constructor.  (For invocation by subclass
   * constructors, typically implicit.)
   */
  protected @className()@() {}

@generateBlock()@
]%);
generateBody(writer);
writer.write(%[
}
]%);
  }

  public void generateBody(java.io.Writer writer) throws java.io.IOException {
    // methods for each operator
    ClassNameList consum = operatorList;
    while (!consum.isEmptyConcClassName()) {
      ClassName operatorName = consum.getHeadConcClassName();
      consum = consum.getTailConcClassName();

      writer.write(%[
  /**
   * Returns true if the term is rooted by the symbol @operatorName.getName()@
   *
   * @@return true if the term is rooted by the symbol @operatorName.getName()@
   */
  public boolean @isOperatorMethod(operatorName)@() {
    return false;
  }
]%);
    }
    // methods for each slot
    SlotFieldList sl = slotList;
    while (!sl.isEmptyConcSlotField()) {
      SlotField slot = sl.getHeadConcSlotField();
      sl = sl.getTailConcSlotField();

      writer.write(%[
  /**
   * Returns the subterm corresponding to the slot @slot.getName()@
   *
   * @@return the subterm corresponding to the slot @slot.getName()@
   */
  public @slotDomain(slot)@ @getMethod(slot)@() {
    throw new UnsupportedOperationException("This @className()@ has no @slot.getName()@");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot @slot.getName()@
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @@param _arg the value of the new subterm
   * @@return a new term where the subterm corresponding to the slot @slot.getName()@ is replaced by _arg
   */
  public @className()@ @setMethod(slot)@(@slotDomain(slot)@ _arg) {
    throw new UnsupportedOperationException("This @className()@ has no @slot.getName()@");
  }
]%);

    }

    /* fromTerm method, dispatching to operator classes */
    writer.write(%[
  protected static tom.library.utils.IdConverter idConv = new tom.library.utils.IdConverter();

  /**
   * Returns an ATerm representation of this term.
   *
   * @@return null to indicate to sub-classes that they have to work
   */
  public aterm.ATerm toATerm() {
    // returns null to indicate sub-classes that they have to work
    return null;
  }

  /**
   * Returns a @fullClassName()@ from an ATerm without any conversion
   *
   * @@param trm ATerm to handle to retrieve a Gom term
   * @@return the term from the ATerm
   */
  public static @fullClassName()@ fromTerm(aterm.ATerm trm) {
    return fromTerm(trm,idConv);
  }

  /**
   * Returns a @fullClassName()@ from a String without any conversion
   *
   * @@param s String containing the ATerm
   * @@return the term from the String
   */
  public static @fullClassName()@ fromString(String s) {
    return fromTerm(atermFactory.parse(s),idConv);
  }

  /**
   * Returns a @fullClassName()@ from a Stream without any conversion
   *
   * @@param stream stream containing the ATerm
   * @@return the term from the Stream
   * @@throws java.io.IOException if a problem occurs with the stream
   */
  public static @fullClassName()@ fromStream(java.io.InputStream stream) throws java.io.IOException {
    return fromTerm(atermFactory.readFromFile(stream),idConv);
  }

  /**
   * Apply a conversion on the ATerm and returns a @fullClassName()@
   *
   * @@param trm ATerm to convert into a Gom term
   * @@param atConv ATermConverter used to convert the ATerm
   * @@return the Gom term
   * @@throws IllegalArgumentException
   */
  public static @fullClassName()@ fromTerm(aterm.ATerm trm, tom.library.utils.ATermConverter atConv) {
    aterm.ATerm convertedTerm = atConv.convert(trm);
    @fullClassName()@ tmp;
    java.util.ArrayList<@fullClassName()@> results = new java.util.ArrayList<@fullClassName()@>();
]%);
    ClassNameList constructor = `ConcClassName(operatorList*,variadicOperatorList*);
    while(!constructor.isEmptyConcClassName()) {
      ClassName operatorName = constructor.getHeadConcClassName();
      constructor = constructor.getTailConcClassName();
      writer.write(%[
    tmp = @fullClassName(operatorName)@.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }]%);
    }

    writer.write(%[
    switch(results.size()) {
      case 0:
        throw new IllegalArgumentException(trm + " is not a @className()@");
      case 1:
        return results.get(0);
      default:
        java.util.logging.Logger.getLogger("@className()@").log(java.util.logging.Level.WARNING,"There were many possibilities ({0}) in {1} but the first one was chosen: {2}",new Object[] {results.toString(), "@fullClassName()@", results.get(0).toString()});
        return results.get(0);
    }
  }

  /**
   * Apply a conversion on the ATerm contained in the String and returns a @fullClassName()@ from it
   *
   * @@param s String containing the ATerm
   * @@param atConv ATerm Converter used to convert the ATerm
   * @@return the Gom term
   */
  public static @fullClassName()@ fromString(String s, tom.library.utils.ATermConverter atConv) {
    return fromTerm(atermFactory.parse(s),atConv);
  }

  /**
   * Apply a conversion on the ATerm contained in the Stream and returns a @fullClassName()@ from it
   *
   * @@param stream stream containing the ATerm
   * @@param atConv ATerm Converter used to convert the ATerm
   * @@return the Gom term
   */
  public static @fullClassName()@ fromStream(java.io.InputStream stream, tom.library.utils.ATermConverter atConv) throws java.io.IOException {
    return fromTerm(atermFactory.readFromFile(stream),atConv);
  }
]%);

    /* abstract method to compare two terms represented by objects without maximal sharing */
    /* used in the mapping */
    if(!maximalsharing) {
      writer.write(%[
  /**
   * Abstract method to compare two terms represented by objects without maximal sharing
   *
   * @@param o Object used to compare
   * @@return true if the two objects are equal
   */
  public abstract boolean deepEquals(Object o);
]%);
    }

    /* length and reverse prototypes, only usable on lists */
    writer.write(%[
  /**
   * Returns the length of the list
   *
   * @@return the length of the list
   * @@throws IllegalArgumentException if the term is not a list
   */
  public int length() {
    throw new IllegalArgumentException(
      "This "+this.getClass().getName()+" is not a list");
  }

  /**
   * Returns an inverted term
   *
   * @@return the inverted list
   * @@throws IllegalArgumentException if the term is not a list
   */
  public @fullClassName()@ reverse() {
    throw new IllegalArgumentException(
      "This "+this.getClass().getName()+" is not a list");
  }

/*
 protected static tom.library.enumerator.Enumeration<enumerator.mutual.types.A> sortA = null;

 static final tom.library.enumerator.Enumeration<enumerator.mutual.types.A> enumA = 
   new tom.library.enumerator.Enumeration<enumerator.mutual.types.A>((tom.library.enumerator.LazyList<tom.library.enumerator.Finite<enumerator.mutual.types.A>>) null);

 public static tom.library.enumerator.Enumeration<enumerator.mutual.types.A> getEnumeration() {
   if(sortA == null) { 
     sortA = enumerator.mutual.types.a.a.funMake().apply(enumA)
       .plus(enumerator.mutual.types.a.foo.funMake().apply(B.enumB))
       .plus(enumerator.mutual.types.a.hoo.funMake().apply(enumA).apply(B.enumB));

     enumA.p1 = new tom.library.enumerator.P1<tom.library.enumerator.LazyList<tom.library.enumerator.Finite<enumerator.mutual.types.A>>>() {
       public tom.library.enumerator.LazyList<tom.library.enumerator.Finite<enumerator.mutual.types.A>> _1() { return sortA.parts(); }
     };
   }    
   return sortA;
 }
*/

  /**
    * retrieve an Enumeration associated to the current class
    */
  public static tom.library.enumerator.Enumeration<@fullClassName()@> getEnumeration() {
    if(mapEnumeration.isEmpty()) { initEnumeration(); }    
    return (tom.library.enumerator.Enumeration<@fullClassName()@>) mapEnumeration.get(@fullClassName()@.class);
  }
  
  public static tom.library.enumerator.Enumeration<@fullClassName()@> putEnumeration(tom.library.enumerator.Enumeration<@fullClassName()@> e) {
    return (tom.library.enumerator.Enumeration<@fullClassName()@>) mapEnumeration.put(@fullClassName()@.class,e);
  }
  ]%);

    /*
     * generate a getCollection<OpName>() method for all variadic operators
     */
    ClassNameList varopList = variadicOperatorList;
    while (!varopList.isEmptyConcClassName()) {
      ClassName operatorName = varopList.getHeadConcClassName();
      varopList = varopList.getTailConcClassName();

      String varopName = operatorName.getName();
      SlotFieldList tmpsl = slotList;
      while (!tmpsl.isEmptyConcSlotField()) {
        SlotField slot = tmpsl.getHeadConcSlotField();
        tmpsl = tmpsl.getTailConcSlotField();
        if(slot.getName().equals("Head" + varopName)) {
          String domainClassName = fullClassName(slot.getDomain());
          writer.write(%[
  /**
   * Returns a Collection extracted from the term
   *
   * @@return the collection
   * @@throws UnsupportedOperationException if the term is not a list
   */
  public java.util.Collection<@primitiveToReferenceType(domainClassName)@> getCollection@varopName@() {
    throw new UnsupportedOperationException("This @className()@ cannot be converted into a Collection");
  }
          ]%);
        }
      }
    }

  /*
    // methods for each variadic operator
    consum = variadicOperatorList;
    while(!consum.isEmptyConcClassName()) {
      ClassName operatorName = consum.getHeadConcClassName();
      consum = consum.getTailConcClassName();
      // look for the corresponding domain
matchblock: {
      %match(slotList) {
        ConcSlotField(_*,slot@SlotField[Name=opname,Domain=domain],_*) -> {
          if(`opname.equals("Head"+operatorName.getName())) {
      writer.write(%[
  public java.util.Collection<@primitiveToReferenceType(slotDomain(`slot))@> @getCollectionMethod(operatorName)@() {
    throw new IllegalArgumentException(
      "This "+this.getClass().getName()+" is not a list");
  }
]%);
      break matchblock;
          }
        }
      }
      }
    }
  */
    if(hooks.containsTomCode()) {
      mapping.generate(writer);
    }
  }

  public void generateTomMapping(Writer writer) throws java.io.IOException {
    writer.write(%[
%typeterm @className()@ {
  implement { @fullClassName()@ }
  is_sort(t) { ($t instanceof @fullClassName()@) }
]%);
    if(maximalsharing) {
      writer.write(%[
  equals(t1,t2) { ($t1==$t2) }
]%);
    } else {
      writer.write(%[
  equals(t1,t2) { (((@fullClassName()@)$t1).deepEquals($t2)) }
]%);
    }
    writer.write(%[
}
]%);
 }


}
