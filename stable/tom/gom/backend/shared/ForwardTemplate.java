/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
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

import tom.gom.backend.TemplateClass;
import tom.gom.adt.objects.types.*;

public class ForwardTemplate extends TemplateClass {
  ClassName visitor;
  ClassNameList importedVisitors;
  ClassName abstractType;
  ClassNameList importedAbstractTypes;
  GomClassList sortClasses;
  GomClassList operatorClasses;

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */   /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClass(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClassList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_SortClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.SortClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_abstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getabstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getvisitor()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_forward( tom.gom.adt.objects.types.GomClass  t) {  return  t.getforward()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_operators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getoperators()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_SortClass_slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getslots()  ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) {  return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getHeadconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getTailconcGomClass()  ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.isEmptyconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList  l1,  tom.gom.adt.objects.types.GomClassList  l2) {    if(tom_is_empty_concGomClass_GomClassList(l1)) {     return l2;    } else if(tom_is_empty_concGomClass_GomClassList(l2)) {     return l1;    } else if(tom_is_empty_concGomClass_GomClassList(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1))) {     return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end) {    if(tom_terms_equal_GomClassList(begin,end)) {      return ( tom.gom.adt.objects.types.GomClassList )tom_empty_list_concGomClass();    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(begin),end));    }   }   

  public ForwardTemplate(ClassName className,
                         ClassName visitor,
                         ClassNameList importedVisitors,
                         ClassName abstractType,
                         ClassNameList importedAbstract,
                         GomClassList sortClasses,
                         GomClassList operatorClasses) {
    super(className);
    this.visitor = visitor;
    this.importedVisitors = importedVisitors;
    this.abstractType = abstractType;
    this.importedAbstractTypes = importedAbstract;
    this.sortClasses = sortClasses;
    this.operatorClasses = operatorClasses;
  }

  /* 
   * We may want to return the stringbuffer itself in the future, or directly
   * write to a Stream
   */
  public String generate() {
    StringBuffer out = new StringBuffer();

    out.append("\npackage "/* Generated by TOM (version 2.4alpha): Do not edit this file */+getPackage()+";\n\npublic class "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className()+" implements "/* Generated by TOM (version 2.4alpha): Do not edit this file */+ className(visitor)+importedVisitorList(importedVisitors) +", jjtraveler.Visitor {\n  protected jjtraveler.Visitor any;\n\n  public "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className()+"(jjtraveler.Visitor v) {\n    this.any = v;\n  }\n\n  public jjtraveler.Visitable visit(jjtraveler.Visitable v) throws jjtraveler.VisitFailure {\n    if (v instanceof "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(abstractType)+") {\n      return (("/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(abstractType)+") v).accept(this);\n    }\n"/* Generated by TOM (version 2.4alpha): Do not edit this file */+generateDispatch(importedAbstractTypes)+"\n    else {\n      return any.visit(v);\n    }\n  }\n\n"/* Generated by TOM (version 2.4alpha): Do not edit this file */+generateVisitMethods()+"\n\n}\n"






















);

    return out.toString();
  }
  private String generateVisitMethods() {
    StringBuffer out = new StringBuffer();
    // generate a visit for each sort
     if(sortClasses instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1=(( tom.gom.adt.objects.types.GomClassList )sortClasses); if ( ( tom_is_fun_sym_concGomClass(tom_match1_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match1_1_2=tom_get_head_concGomClass_GomClassList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_list1); if ( ( tom_is_fun_sym_SortClass(tom_match1_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_className=tom_get_slot_SortClass_className(tom_match1_1_2); { tom.gom.adt.objects.types.ClassName  tom_sortName=tom_match1_1_2_className; if ( true ) {


        out.append("\n  public "/* Generated by TOM (version 2.4alpha): Do not edit this file */+ fullClassName(tom_sortName) +" "/* Generated by TOM (version 2.4alpha): Do not edit this file */+visitMethod(tom_sortName)+"("/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(tom_sortName)+" arg) throws jjtraveler.VisitFailure {\n    return ("/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(tom_sortName)+") any.visit(arg);\n  }\n"



);
       } } } } }tom_match1_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } } }

    return out.toString();
  }

  private String generateDispatch(ClassNameList types) {
    StringBuffer out = new StringBuffer();
    while(!types.isEmptyconcClassName()) {
      out.append("    else if (v instanceof "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(types.getHeadconcClassName())+") {\n      return (("/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(types.getHeadconcClassName())+") v).accept(this);\n    }"

);
      types = types.getTailconcClassName();
    }
    return out.toString();
  }
  String importedVisitorList(ClassNameList list) {
    StringBuffer out = new StringBuffer();
    while(!list.isEmptyconcClassName()) {
      out.append(", "+fullClassName(list.getHeadconcClassName()));
      list = list.getTailconcClassName();
    }
    return out.toString();
  }
}
