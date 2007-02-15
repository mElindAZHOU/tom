/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (C) 2006-2007, INRIA
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

package tom.gom.backend.strategy;

import java.io.*;
import java.util.logging.*;
import tom.gom.backend.TemplateClass;
import tom.gom.tools.GomEnvironment;
import tom.gom.tools.error.GomRuntimeException;
import tom.gom.adt.objects.types.*;

public class IsOpTemplate extends TemplateClass {
  ClassName operator;

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_ClassName( tom.gom.adt.objects.types.ClassName  t) {  return  t instanceof tom.gom.adt.objects.types.classname.ClassName  ;}private static  tom.gom.adt.objects.types.ClassName  tom_make_ClassName( String  t0,  String  t1) { return  tom.gom.adt.objects.types.classname.ClassName.make(t0, t1); }private static  String  tom_get_slot_ClassName_Pkg( tom.gom.adt.objects.types.ClassName  t) {  return  t.getPkg()  ;}private static  String  tom_get_slot_ClassName_Name( tom.gom.adt.objects.types.ClassName  t) {  return  t.getName()  ;} 

  /*
   * The argument is an operator class, and this template generates the
   * assotiated isOp strategy
   */
  public IsOpTemplate(ClassName className) {
    super(className);
     if(className instanceof  tom.gom.adt.objects.types.ClassName ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1=(( tom.gom.adt.objects.types.ClassName )className); if ( ( tom_is_fun_sym_ClassName(tom_match1_1) ||  false  ) ) { { String  tom_pkg=tom_get_slot_ClassName_Pkg(tom_match1_1); { String  tom_name=tom_get_slot_ClassName_Name(tom_match1_1); if ( true ) {

        String newpkg = tom_pkg.replaceFirst(".types.",".strategy.");
        String newname = "Is_"+tom_name;
        this.className = tom_make_ClassName(newpkg,newname);
       } } } } } }

    this.operator = className;
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    // generate is normally never called, Is_op is now an alias for When_op(Id)
    /*
writer.write(%[
package @getPackage()@;

import jjtraveler.Visitable;
import jjtraveler.VisitFailure;

public class @className()@ extends tom.library.strategy.mutraveler.Fail {
  private static final String msg = "Not an @className(operator)@";

  public Visitable visit(Visitable any) throws VisitFailure {
    if(any instanceof @fullClassName(operator)@) {
      return any;
    } else {
      throw new VisitFailure(msg);
    }
  }
}
]%);
*/
  }

  public String generateMapping() {
    String whenName = className().replaceFirst("Is","When");
    return "\n%op Strategy "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"() {\n  make() { `"/* Generated by TOM (version 2.5alpha): Do not edit this file */+whenName+"(Identity()) }\n}\n"



;
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
}
