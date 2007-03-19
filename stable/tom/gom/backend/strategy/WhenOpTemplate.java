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

public class WhenOpTemplate extends TemplateClass {
  ClassName operator;

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_ClassName( tom.gom.adt.objects.types.ClassName  t) {  return  t instanceof tom.gom.adt.objects.types.classname.ClassName  ;}private static  tom.gom.adt.objects.types.ClassName  tom_make_ClassName( String  t0,  String  t1) { return  tom.gom.adt.objects.types.classname.ClassName.make(t0, t1); }private static  String  tom_get_slot_ClassName_Pkg( tom.gom.adt.objects.types.ClassName  t) {  return  t.getPkg()  ;}private static  String  tom_get_slot_ClassName_Name( tom.gom.adt.objects.types.ClassName  t) {  return  t.getName()  ;} 

  /*
   * The argument is an operator class, and this template generates the
   * assotiated isOp strategy
   */
  public WhenOpTemplate(GomClass gomClass) {
    super(gomClass);
    ClassName clsName = gomClass.getClassName();
     if(clsName instanceof  tom.gom.adt.objects.types.ClassName ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1=(( tom.gom.adt.objects.types.ClassName )clsName); if ( ( tom_is_fun_sym_ClassName(tom_match1_1) ||  false  ) ) { { String  tom_pkg=tom_get_slot_ClassName_Pkg(tom_match1_1); { String  tom_name=tom_get_slot_ClassName_Name(tom_match1_1); if ( true ) {

        String newpkg = tom_pkg.replaceFirst(".types.",".strategy.");
        String newname = "When_"+tom_name;
        this.className = tom_make_ClassName(newpkg,newname);
       } } } } } }

    this.operator = clsName;
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
writer.write("\npackage "/* Generated by TOM (version 2.5alpha): Do not edit this file */+getPackage()+";\n\npublic class "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"  implements tom.library.strategy.mutraveler.MuStrategy, tom.library.sl.Strategy {\n  private static final String msg = \"Not an "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className(operator)+"\";\n  private static jjtraveler.reflective.VisitableVisitor sub;\n\n  public "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"(jjtraveler.reflective.VisitableVisitor v) {\n    sub = v;\n  }\n\n  public int getChildCount() {\n    return 1;\n  }\n  public jjtraveler.Visitable getChildAt(int index) {\n    switch(index) {\n    case 0: return sub;\n      default: throw new IndexOutOfBoundsException();\n    }\n  }\n  public jjtraveler.Visitable setChildAt(int index, jjtraveler.Visitable child) {\n    switch(index) {\n      case 0: sub = (jjtraveler.reflective.VisitableVisitor)child;\n      default: throw new IndexOutOfBoundsException();\n    }\n  }\n\n  public jjtraveler.Visitable[] getChildren() {\n    return new jjtraveler.Visitable[]{sub};\n  }\n\n  public jjtraveler.Visitable setChildren(jjtraveler.Visitable[] children) {\n    sub = (jjtraveler.reflective.VisitableVisitor)children[0];\n    return this;\n  }\n\n  protected tom.library.sl.Environment environment;\n  public void setEnvironment(tom.library.sl.Environment env) {\n    this.environment = env;\n  }\n\n  public tom.library.sl.Environment getEnvironment() {\n    if(environment!=null) {\n      return environment;\n    } else {\n      throw new RuntimeException(\"environment not initialized\");\n    }\n  }\n\n  private tom.library.strategy.mutraveler.Position position;\n  public void setPosition(tom.library.strategy.mutraveler.Position pos) {\n    position = pos;  \n  }\n\n  public tom.library.strategy.mutraveler.Position getPosition() {\n    if(position!=null) {\n      return position;\n    } else {\n      throw new RuntimeException(\"position not initialized\");\n    }\n  }\n\n  public boolean hasPosition() { return false; }\n\n  /*\n   * Apply the strategy, and returns the subject in case of VisitFailure\n   */\n  public jjtraveler.Visitable apply(jjtraveler.Visitable any) {\n    try {\n      return tom.library.strategy.mutraveler.MuTraveler.init(this).visit(any);\n    } catch (jjtraveler.VisitFailure f) {\n      return any;\n    }\n  }\n\n  public tom.library.sl.Visitable fire(tom.library.sl.Visitable any) {\n    tom.library.sl.AbstractStrategy.init(this,new tom.library.sl.Environment());\n    getEnvironment().setRoot(any);\n    visit();\n    if (getEnvironment().getStatus() == tom.library.sl.Environment.SUCCESS) {\n      return getEnvironment().getRoot();\n    } else {\n      throw new tom.library.sl.FireException();\n    }\n  }\n\n  public tom.library.strategy.mutraveler.MuStrategy accept(tom.library.strategy.mutraveler.reflective.StrategyVisitorFwd v) throws jjtraveler.VisitFailure {\n    return v.visit_Strategy(this);\n  }\n\n  public tom.library.sl.Strategy accept(tom.library.sl.reflective.StrategyFwd v) throws jjtraveler.VisitFailure {\n    return v.visit_Strategy(this);\n  }\n\n  public jjtraveler.Visitable visit(jjtraveler.Visitable any) throws jjtraveler.VisitFailure {\n    if(any instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(operator)+") {\n      return sub.visit(any);\n    } else {\n      throw new jjtraveler.VisitFailure(msg);\n    }\n  }\n\n  public void visit() {\n    if(getEnvironment().getSubject() instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(operator)+") {\n      ((tom.library.sl.Strategy)sub).visit();\n      return;\n    } else {\n      getEnvironment().setStatus(tom.library.sl.Environment.FAILURE);\n    }\n  }\n}\n"














































































































);
  }

  public String generateMapping() {
    return "\n%op Strategy "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"(s:Strategy) {\n  is_fsym(t) { (t!=null) && t instanceof ("/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName()+")}\n  make(s) { new "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName()+"(s) }\n}\n"




;
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
}
