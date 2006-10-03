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

package tom.gom.backend.strategy;

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.backend.TemplateClass;
import java.io.*;
import tom.gom.adt.objects.types.*;

public class StratMappingTemplate extends TemplateClass {
  GomClassList operatorClasses;

  /* Generated by TOM (version 2.4rc1): Do not edit this file *//* Generated by TOM (version 2.4rc1): Do not edit this file *//* Generated by TOM (version 2.4rc1): Do not edit this file */   private static boolean tom_terms_equal_HookList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClass(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClassList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.OperatorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ExtendsType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getExtendsType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_Slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSlots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortName()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Empty( tom.gom.adt.objects.types.GomClass  t) {  return  t.getEmpty()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Cons( tom.gom.adt.objects.types.GomClass  t) {  return  t.getCons()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_VariadicOperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) {  return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getHeadconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getTailconcGomClass()  ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.isEmptyconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList  l1,  tom.gom.adt.objects.types.GomClassList  l2) {    if(tom_is_empty_concGomClass_GomClassList(l1)) {     return l2;    } else if(tom_is_empty_concGomClass_GomClassList(l2)) {     return l1;    } else if(tom_is_empty_concGomClass_GomClassList(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1))) {     return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end) {    if(tom_terms_equal_GomClassList(begin,end)) {      return ( tom.gom.adt.objects.types.GomClassList )tom_empty_list_concGomClass();    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(begin),end));    }   }   

  public StratMappingTemplate(ClassName className, GomClassList operatorClasses) {
    super(className);
    this.operatorClasses = operatorClasses;
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    writer.write("\n%include { mustrategy.tom }\n"

);
    /* XXX: i could introduce an interface providing generateMapping() */
     if(operatorClasses instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses); if ( ( tom_is_fun_sym_concGomClass(tom_match1_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match1_1_2=tom_get_head_concGomClass_GomClassList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_list1); if ( ( tom_is_fun_sym_OperatorClass(tom_match1_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_ClassName=tom_get_slot_OperatorClass_ClassName(tom_match1_1_2); { tom.gom.adt.objects.types.SlotFieldList  tom_match1_1_2_Slots=tom_get_slot_OperatorClass_Slots(tom_match1_1_2); { tom.gom.adt.objects.types.ClassName  tom_opName=tom_match1_1_2_ClassName; { tom.gom.adt.objects.types.SlotFieldList  tom_slotList=tom_match1_1_2_Slots; {boolean tom_match1_tom_anti_constraints_status= true ; if ((tom_match1_tom_anti_constraints_status ==  true )) { if ( true ) {




      writer.write(
        (new tom.gom.backend.strategy.IsOpTemplate(tom_opName)).generateMapping());

      writer.write(
        (new tom.gom.backend.strategy.SOpTemplate(tom_opName,tom_slotList)).generateMapping());
      writer.write(
        (new tom.gom.backend.strategy.MakeOpTemplate(tom_opName,tom_slotList)).generateMapping());
       } } } } } } } } }tom_match1_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } if ( ( tom_is_fun_sym_concGomClass(tom_match1_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match1_1_2=tom_get_head_concGomClass_GomClassList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_list1); if ( ( tom_is_fun_sym_VariadicOperatorClass(tom_match1_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_ClassName=tom_get_slot_VariadicOperatorClass_ClassName(tom_match1_1_2); { tom.gom.adt.objects.types.GomClass  tom_match1_1_2_Empty=tom_get_slot_VariadicOperatorClass_Empty(tom_match1_1_2); { tom.gom.adt.objects.types.GomClass  tom_match1_1_2_Cons=tom_get_slot_VariadicOperatorClass_Cons(tom_match1_1_2); { tom.gom.adt.objects.types.ClassName  tom_vopName=tom_match1_1_2_ClassName; if ( ( tom_is_fun_sym_OperatorClass(tom_match1_1_2_Empty) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_Empty_ClassName=tom_get_slot_OperatorClass_ClassName(tom_match1_1_2_Empty); { tom.gom.adt.objects.types.ClassName  tom_empty=tom_match1_1_2_Empty_ClassName; if ( ( tom_is_fun_sym_OperatorClass(tom_match1_1_2_Cons) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_Cons_ClassName=tom_get_slot_OperatorClass_ClassName(tom_match1_1_2_Cons); { tom.gom.adt.objects.types.ClassName  tom_cons=tom_match1_1_2_Cons_ClassName; {boolean tom_match1_tom_anti_constraints_status= true ; if ((tom_match1_tom_anti_constraints_status ==  true )) { if ( true ) {





        writer.write("\n%op Strategy _"/* Generated by TOM (version 2.4rc1): Do not edit this file */+className(tom_vopName)+"(sub:Strategy) {\n  is_fsym(t) { false }\n  make(sub)  { `mu(MuVar(\"x\"),Choice(_"/* Generated by TOM (version 2.4rc1): Do not edit this file */+className(tom_cons)+"(sub,MuVar(\"x\")),_"/* Generated by TOM (version 2.4rc1): Do not edit this file */+className(tom_empty)+"())) }\n}\n"




);
       } } } } } } } } } } } } } } }tom_match1_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } } }

  }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".tom";
  }

}
