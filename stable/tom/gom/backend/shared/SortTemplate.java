/* Generated by TOM (version 2.4rc1): Do not edit this file *//*
 * Gom
 *
 * Copyright (C) 2006 INRIA
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

import tom.gom.backend.TemplateHookedClass;
import tom.gom.adt.objects.types.*;

public class SortTemplate extends TemplateHookedClass {
  ClassName abstractType;
  ClassName visitor;
  ClassNameList operatorList;
  SlotFieldList slotList;

  public SortTemplate(ClassName className,
                      ClassName abstractType,
                      ClassName visitor,
                      ClassNameList operatorList,
                      SlotFieldList slots,
                      HookList hooks) {
    super(className,hooks);
    this.abstractType = abstractType;
    this.visitor = visitor;
    this.operatorList = operatorList;
    this.slotList = slots;
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    writer.write("\npackage "/* Generated by TOM (version 2.4rc1): Do not edit this file */+getPackage()+";        \n"/* Generated by TOM (version 2.4rc1): Do not edit this file */+generateImport()+"\n\npublic abstract class "/* Generated by TOM (version 2.4rc1): Do not edit this file */+className()+" extends "/* Generated by TOM (version 2.4rc1): Do not edit this file */+fullClassName(abstractType)+" "/* Generated by TOM (version 2.4rc1): Do not edit this file */+generateInterface()+"{\n\n"/* Generated by TOM (version 2.4rc1): Do not edit this file */+generateBlock()+"\n\n  public "/* Generated by TOM (version 2.4rc1): Do not edit this file */+fullClassName(abstractType)+" accept("/* Generated by TOM (version 2.4rc1): Do not edit this file */+fullClassName(visitor)+" v) throws jjtraveler.VisitFailure {\n    return v."/* Generated by TOM (version 2.4rc1): Do not edit this file */+visitMethod(className)+"(this);\n  }\n"










);
generateBody(writer);
writer.write("\n}\n"

);
  }

  public void generateBody(java.io.Writer writer) throws java.io.IOException {
    // methods for each operator
    ClassNameList consum = operatorList;
    while (!consum.isEmptyconcClassName()) {
      ClassName operatorName = consum.getHeadconcClassName();
      consum = consum.getTailconcClassName();

      writer.write("\n  public boolean "/* Generated by TOM (version 2.4rc1): Do not edit this file */+isOperatorMethod(operatorName)+"() {\n    return false;\n  }\n\n"




);
    }
    // methods for each slot
    while (!slotList.isEmptyconcSlotField()) {
      SlotField slot = slotList.getHeadconcSlotField();
      slotList = slotList.getTailconcSlotField();

      /* Do not generate "hasOp" methods for now
      writer.write("\tpublic boolean "+hasMethod(slot)+"() {\n");
      writer.write("\t\treturn false;\n");
      writer.write("\t}\n");
      writer.write("\n");
      */

      writer.write("\n  public "/* Generated by TOM (version 2.4rc1): Do not edit this file */+slotDomain(slot)+" "/* Generated by TOM (version 2.4rc1): Do not edit this file */+getMethod(slot)+"() {\n    throw new UnsupportedOperationException(\"This "/* Generated by TOM (version 2.4rc1): Do not edit this file */+className()+" has no "/* Generated by TOM (version 2.4rc1): Do not edit this file */+slot.getName()+"\");\n  }\n\n  public "/* Generated by TOM (version 2.4rc1): Do not edit this file */+className()+" "/* Generated by TOM (version 2.4rc1): Do not edit this file */+setMethod(slot)+"("/* Generated by TOM (version 2.4rc1): Do not edit this file */+slotDomain(slot)+" _arg) {\n    throw new UnsupportedOperationException(\"This "/* Generated by TOM (version 2.4rc1): Do not edit this file */+className()+" has no "/* Generated by TOM (version 2.4rc1): Do not edit this file */+slot.getName()+"\");\n  }\n\n"








);

    }

    /* fromTerm method, dispatching to operator classes */
    writer.write("\n  public static "/* Generated by TOM (version 2.4rc1): Do not edit this file */+fullClassName()+" fromTerm(aterm.ATerm trm) {\n    "/* Generated by TOM (version 2.4rc1): Do not edit this file */+fullClassName()+" tmp;\n"


);
    generateFromTerm(writer,"trm","tmp");
    writer.write("\n    throw new IllegalArgumentException(\"This is not a "/* Generated by TOM (version 2.4rc1): Do not edit this file */+className()+" \" + trm);\n  }\n\n"



);

    /* length and reverse prototypes, only usable on lists */
    writer.write("\n  public int length() {\n    throw new IllegalArgumentException(\n      \"This \"+this.getClass().getName()+\" is not a list\");\n  }\n\n  public "/* Generated by TOM (version 2.4rc1): Do not edit this file */+fullClassName()+" reverse() {\n    throw new IllegalArgumentException(\n      \"This \"+this.getClass().getName()+\" is not a list\");\n  }\n"









);
  }
  
  protected String generateInterface() {
    String interfaces = super.generateInterface();
    if (! interfaces.equals("")) return "implements "+interfaces.substring(1);
    else return interfaces;
  }

  private void generateFromTerm(java.io.Writer writer, String trm, String tmp) throws java.io.IOException {
    ClassNameList consum = operatorList;
    while (!consum.isEmptyconcClassName()) {
      ClassName operatorName = consum.getHeadconcClassName();
      consum = consum.getTailconcClassName();
      writer.write("\n    "/* Generated by TOM (version 2.4rc1): Do not edit this file */+tmp+" = "/* Generated by TOM (version 2.4rc1): Do not edit this file */+fullClassName(operatorName)+".fromTerm("/* Generated by TOM (version 2.4rc1): Do not edit this file */+trm+");\n    if ("/* Generated by TOM (version 2.4rc1): Do not edit this file */+tmp+" != null) {\n      return tmp;\n    }\n"




);
    }
  }

}
