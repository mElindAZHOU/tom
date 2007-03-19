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

package tom.gom.compiler;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.gom.*;
import tom.gom.adt.gom.types.*;
import tom.gom.tools.error.GomRuntimeException;

import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;

import tom.library.sl.*;

public class HookCompiler {

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  private static boolean tom_terms_equal_Code(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Hook(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClass(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotField(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Slot(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ModuleDecl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SortDecl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookDecl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Decl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookDeclList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OperatorDecl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static  tom.gom.adt.objects.types.Hook  tom_make_MakeHook( tom.gom.adt.objects.types.SlotFieldList  t0,  tom.gom.adt.code.types.Code  t1) { return  tom.gom.adt.objects.types.hook.MakeHook.make(t0, t1); }private static  tom.gom.adt.objects.types.Hook  tom_make_BlockHook( tom.gom.adt.code.types.Code  t0) { return  tom.gom.adt.objects.types.hook.BlockHook.make(t0); }private static  tom.gom.adt.objects.types.Hook  tom_make_InterfaceHook( tom.gom.adt.code.types.Code  t0) { return  tom.gom.adt.objects.types.hook.InterfaceHook.make(t0); }private static  tom.gom.adt.objects.types.Hook  tom_make_ImportHook( tom.gom.adt.code.types.Code  t0) { return  tom.gom.adt.objects.types.hook.ImportHook.make(t0); }private static  tom.gom.adt.objects.types.Hook  tom_make_MappingHook( tom.gom.adt.code.types.Code  t0) { return  tom.gom.adt.objects.types.hook.MappingHook.make(t0); }private static boolean tom_is_fun_sym_AbstractTypeClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.AbstractTypeClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_AbstractTypeClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_AbstractTypeClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_AbstractTypeClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_AbstractTypeClass_SortList( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortList()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_AbstractTypeClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_SortClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.SortClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Forward( tom.gom.adt.objects.types.GomClass  t) {  return  t.getForward()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_Operators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getOperators()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_VariadicOperators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVariadicOperators()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_SortClass_Slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSlots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_SortClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.OperatorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ExtendsType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getExtendsType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_Slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSlots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortName()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Empty( tom.gom.adt.objects.types.GomClass  t) {  return  t.getEmpty()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Cons( tom.gom.adt.objects.types.GomClass  t) {  return  t.getCons()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_VariadicOperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static  tom.gom.adt.objects.types.SlotField  tom_make_SlotField( String  t0,  tom.gom.adt.objects.types.ClassName  t1) { return  tom.gom.adt.objects.types.slotfield.SlotField.make(t0, t1); }private static boolean tom_is_fun_sym_Slot( tom.gom.adt.gom.types.Slot  t) {  return  t instanceof tom.gom.adt.gom.types.slot.Slot  ;}private static  String  tom_get_slot_Slot_Name( tom.gom.adt.gom.types.Slot  t) {  return  t.getName()  ;}private static  tom.gom.adt.gom.types.SortDecl  tom_get_slot_Slot_Sort( tom.gom.adt.gom.types.Slot  t) {  return  t.getSort()  ;}private static boolean tom_is_fun_sym_MakeHookDecl( tom.gom.adt.gom.types.HookDecl  t) {  return  t instanceof tom.gom.adt.gom.types.hookdecl.MakeHookDecl  ;}private static  tom.gom.adt.gom.types.Decl  tom_get_slot_MakeHookDecl_Pointcut( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getPointcut()  ;}private static  tom.gom.adt.gom.types.SlotList  tom_get_slot_MakeHookDecl_SlotArgs( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getSlotArgs()  ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_MakeHookDecl_Code( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getCode()  ;}private static boolean tom_is_fun_sym_BlockHookDecl( tom.gom.adt.gom.types.HookDecl  t) {  return  t instanceof tom.gom.adt.gom.types.hookdecl.BlockHookDecl  ;}private static  tom.gom.adt.gom.types.Decl  tom_get_slot_BlockHookDecl_Pointcut( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getPointcut()  ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_BlockHookDecl_Code( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getCode()  ;}private static boolean tom_is_fun_sym_InterfaceHookDecl( tom.gom.adt.gom.types.HookDecl  t) {  return  t instanceof tom.gom.adt.gom.types.hookdecl.InterfaceHookDecl  ;}private static  tom.gom.adt.gom.types.Decl  tom_get_slot_InterfaceHookDecl_Pointcut( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getPointcut()  ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_InterfaceHookDecl_Code( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getCode()  ;}private static boolean tom_is_fun_sym_ImportHookDecl( tom.gom.adt.gom.types.HookDecl  t) {  return  t instanceof tom.gom.adt.gom.types.hookdecl.ImportHookDecl  ;}private static  tom.gom.adt.gom.types.Decl  tom_get_slot_ImportHookDecl_Pointcut( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getPointcut()  ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_ImportHookDecl_Code( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getCode()  ;}private static boolean tom_is_fun_sym_MappingHookDecl( tom.gom.adt.gom.types.HookDecl  t) {  return  t instanceof tom.gom.adt.gom.types.hookdecl.MappingHookDecl  ;}private static  tom.gom.adt.gom.types.Decl  tom_get_slot_MappingHookDecl_Pointcut( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getPointcut()  ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_MappingHookDecl_Code( tom.gom.adt.gom.types.HookDecl  t) {  return  t.getCode()  ;}private static boolean tom_is_fun_sym_CutModule( tom.gom.adt.gom.types.Decl  t) {  return  t instanceof tom.gom.adt.gom.types.decl.CutModule  ;}private static  tom.gom.adt.gom.types.ModuleDecl  tom_get_slot_CutModule_MDecl( tom.gom.adt.gom.types.Decl  t) {  return  t.getMDecl()  ;}private static boolean tom_is_fun_sym_CutSort( tom.gom.adt.gom.types.Decl  t) {  return  t instanceof tom.gom.adt.gom.types.decl.CutSort  ;}private static  tom.gom.adt.gom.types.SortDecl  tom_get_slot_CutSort_Sort( tom.gom.adt.gom.types.Decl  t) {  return  t.getSort()  ;}private static boolean tom_is_fun_sym_CutOperator( tom.gom.adt.gom.types.Decl  t) {  return  t instanceof tom.gom.adt.gom.types.decl.CutOperator  ;}private static  tom.gom.adt.gom.types.OperatorDecl  tom_get_slot_CutOperator_ODecl( tom.gom.adt.gom.types.Decl  t) {  return  t.getODecl()  ;}private static boolean tom_is_fun_sym_concHook( tom.gom.adt.objects.types.HookList  t) {  return  t instanceof tom.gom.adt.objects.types.hooklist.ConsconcHook || t instanceof tom.gom.adt.objects.types.hooklist.EmptyconcHook  ;}private static  tom.gom.adt.objects.types.HookList  tom_empty_list_concHook() { return  tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() ; }private static  tom.gom.adt.objects.types.HookList  tom_cons_list_concHook( tom.gom.adt.objects.types.Hook  e,  tom.gom.adt.objects.types.HookList  l) { return  tom.gom.adt.objects.types.hooklist.ConsconcHook.make(e,l) ; }private static  tom.gom.adt.objects.types.Hook  tom_get_head_concHook_HookList( tom.gom.adt.objects.types.HookList  l) {  return  l.getHeadconcHook()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_tail_concHook_HookList( tom.gom.adt.objects.types.HookList  l) {  return  l.getTailconcHook()  ;}private static boolean tom_is_empty_concHook_HookList( tom.gom.adt.objects.types.HookList  l) {  return  l.isEmptyconcHook()  ;}private static  tom.gom.adt.objects.types.HookList  tom_append_list_concHook( tom.gom.adt.objects.types.HookList  l1,  tom.gom.adt.objects.types.HookList  l2) {    if(tom_is_empty_concHook_HookList(l1)) {     return l2;    } else if(tom_is_empty_concHook_HookList(l2)) {     return l1;    } else if(tom_is_empty_concHook_HookList(( tom.gom.adt.objects.types.HookList )tom_get_tail_concHook_HookList(l1))) {     return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(( tom.gom.adt.objects.types.Hook )tom_get_head_concHook_HookList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(( tom.gom.adt.objects.types.Hook )tom_get_head_concHook_HookList(l1),tom_append_list_concHook(( tom.gom.adt.objects.types.HookList )tom_get_tail_concHook_HookList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.HookList  tom_get_slice_concHook( tom.gom.adt.objects.types.HookList  begin,  tom.gom.adt.objects.types.HookList  end) {    if(tom_terms_equal_HookList(begin,end)) {      return ( tom.gom.adt.objects.types.HookList )tom_empty_list_concHook();    } else {      return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(( tom.gom.adt.objects.types.Hook )tom_get_head_concHook_HookList(begin),( tom.gom.adt.objects.types.HookList )tom_get_slice_concHook(( tom.gom.adt.objects.types.HookList )tom_get_tail_concHook_HookList(begin),end));    }   }  private static boolean tom_is_fun_sym_concSlotField( tom.gom.adt.objects.types.SlotFieldList  t) {  return  t instanceof tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField || t instanceof tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_empty_list_concSlotField() { return  tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField.make() ; }private static  tom.gom.adt.objects.types.SlotFieldList  tom_cons_list_concSlotField( tom.gom.adt.objects.types.SlotField  e,  tom.gom.adt.objects.types.SlotFieldList  l) { return  tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField.make(e,l) ; }private static  tom.gom.adt.objects.types.SlotField  tom_get_head_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.getHeadconcSlotField()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_tail_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.getTailconcSlotField()  ;}private static boolean tom_is_empty_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.isEmptyconcSlotField()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_append_list_concSlotField( tom.gom.adt.objects.types.SlotFieldList  l1,  tom.gom.adt.objects.types.SlotFieldList  l2) {    if(tom_is_empty_concSlotField_SlotFieldList(l1)) {     return l2;    } else if(tom_is_empty_concSlotField_SlotFieldList(l2)) {     return l1;    } else if(tom_is_empty_concSlotField_SlotFieldList(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(l1))) {     return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(l1),tom_append_list_concSlotField(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slice_concSlotField( tom.gom.adt.objects.types.SlotFieldList  begin,  tom.gom.adt.objects.types.SlotFieldList  end) {    if(tom_terms_equal_SlotFieldList(begin,end)) {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_empty_list_concSlotField();    } else {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(begin),( tom.gom.adt.objects.types.SlotFieldList )tom_get_slice_concSlotField(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(begin),end));    }   }  private static  tom.gom.adt.gom.types.SlotList  tom_empty_list_concSlot() { return  tom.gom.adt.gom.types.slotlist.EmptyconcSlot.make() ; }private static  tom.gom.adt.gom.types.SlotList  tom_cons_list_concSlot( tom.gom.adt.gom.types.Slot  e,  tom.gom.adt.gom.types.SlotList  l) { return  tom.gom.adt.gom.types.slotlist.ConsconcSlot.make(e,l) ; }private static  tom.gom.adt.gom.types.Slot  tom_get_head_concSlot_SlotList( tom.gom.adt.gom.types.SlotList  l) {  return  l.getHeadconcSlot()  ;}private static  tom.gom.adt.gom.types.SlotList  tom_get_tail_concSlot_SlotList( tom.gom.adt.gom.types.SlotList  l) {  return  l.getTailconcSlot()  ;}private static boolean tom_is_empty_concSlot_SlotList( tom.gom.adt.gom.types.SlotList  l) {  return  l.isEmptyconcSlot()  ;}private static  tom.gom.adt.gom.types.SlotList  tom_append_list_concSlot( tom.gom.adt.gom.types.SlotList  l1,  tom.gom.adt.gom.types.SlotList  l2) {    if(tom_is_empty_concSlot_SlotList(l1)) {     return l2;    } else if(tom_is_empty_concSlot_SlotList(l2)) {     return l1;    } else if(tom_is_empty_concSlot_SlotList(( tom.gom.adt.gom.types.SlotList )tom_get_tail_concSlot_SlotList(l1))) {     return ( tom.gom.adt.gom.types.SlotList )tom_cons_list_concSlot(( tom.gom.adt.gom.types.Slot )tom_get_head_concSlot_SlotList(l1),l2);    } else {      return ( tom.gom.adt.gom.types.SlotList )tom_cons_list_concSlot(( tom.gom.adt.gom.types.Slot )tom_get_head_concSlot_SlotList(l1),tom_append_list_concSlot(( tom.gom.adt.gom.types.SlotList )tom_get_tail_concSlot_SlotList(l1),l2));    }   }  private static  tom.gom.adt.gom.types.SlotList  tom_get_slice_concSlot( tom.gom.adt.gom.types.SlotList  begin,  tom.gom.adt.gom.types.SlotList  end) {    if(tom_terms_equal_SlotList(begin,end)) {      return ( tom.gom.adt.gom.types.SlotList )tom_empty_list_concSlot();    } else {      return ( tom.gom.adt.gom.types.SlotList )tom_cons_list_concSlot(( tom.gom.adt.gom.types.Slot )tom_get_head_concSlot_SlotList(begin),( tom.gom.adt.gom.types.SlotList )tom_get_slice_concSlot(( tom.gom.adt.gom.types.SlotList )tom_get_tail_concSlot_SlotList(begin),end));    }   }  private static boolean tom_is_fun_sym_concHookDecl( tom.gom.adt.gom.types.HookDeclList  t) {  return  t instanceof tom.gom.adt.gom.types.hookdecllist.ConsconcHookDecl || t instanceof tom.gom.adt.gom.types.hookdecllist.EmptyconcHookDecl  ;}private static  tom.gom.adt.gom.types.HookDeclList  tom_empty_list_concHookDecl() { return  tom.gom.adt.gom.types.hookdecllist.EmptyconcHookDecl.make() ; }private static  tom.gom.adt.gom.types.HookDeclList  tom_cons_list_concHookDecl( tom.gom.adt.gom.types.HookDecl  e,  tom.gom.adt.gom.types.HookDeclList  l) { return  tom.gom.adt.gom.types.hookdecllist.ConsconcHookDecl.make(e,l) ; }private static  tom.gom.adt.gom.types.HookDecl  tom_get_head_concHookDecl_HookDeclList( tom.gom.adt.gom.types.HookDeclList  l) {  return  l.getHeadconcHookDecl()  ;}private static  tom.gom.adt.gom.types.HookDeclList  tom_get_tail_concHookDecl_HookDeclList( tom.gom.adt.gom.types.HookDeclList  l) {  return  l.getTailconcHookDecl()  ;}private static boolean tom_is_empty_concHookDecl_HookDeclList( tom.gom.adt.gom.types.HookDeclList  l) {  return  l.isEmptyconcHookDecl()  ;}private static  tom.gom.adt.gom.types.HookDeclList  tom_append_list_concHookDecl( tom.gom.adt.gom.types.HookDeclList  l1,  tom.gom.adt.gom.types.HookDeclList  l2) {    if(tom_is_empty_concHookDecl_HookDeclList(l1)) {     return l2;    } else if(tom_is_empty_concHookDecl_HookDeclList(l2)) {     return l1;    } else if(tom_is_empty_concHookDecl_HookDeclList(( tom.gom.adt.gom.types.HookDeclList )tom_get_tail_concHookDecl_HookDeclList(l1))) {     return ( tom.gom.adt.gom.types.HookDeclList )tom_cons_list_concHookDecl(( tom.gom.adt.gom.types.HookDecl )tom_get_head_concHookDecl_HookDeclList(l1),l2);    } else {      return ( tom.gom.adt.gom.types.HookDeclList )tom_cons_list_concHookDecl(( tom.gom.adt.gom.types.HookDecl )tom_get_head_concHookDecl_HookDeclList(l1),tom_append_list_concHookDecl(( tom.gom.adt.gom.types.HookDeclList )tom_get_tail_concHookDecl_HookDeclList(l1),l2));    }   }  private static  tom.gom.adt.gom.types.HookDeclList  tom_get_slice_concHookDecl( tom.gom.adt.gom.types.HookDeclList  begin,  tom.gom.adt.gom.types.HookDeclList  end) {    if(tom_terms_equal_HookDeclList(begin,end)) {      return ( tom.gom.adt.gom.types.HookDeclList )tom_empty_list_concHookDecl();    } else {      return ( tom.gom.adt.gom.types.HookDeclList )tom_cons_list_concHookDecl(( tom.gom.adt.gom.types.HookDecl )tom_get_head_concHookDecl_HookDeclList(begin),( tom.gom.adt.gom.types.HookDeclList )tom_get_slice_concHookDecl(( tom.gom.adt.gom.types.HookDeclList )tom_get_tail_concHookDecl_HookDeclList(begin),end));    }   }   /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_terms_equal_Strategy(Object t1, Object t2) {  return t1.equals(t2) ;}/* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static  tom.library.sl.Strategy  tom_make_mu( tom.library.sl.Strategy  var,  tom.library.sl.Strategy  v) { return  new tom.library.sl.Mu(var,v) ; }private static  tom.library.sl.Strategy  tom_make_MuVar( String  name) { return  new tom.library.sl.MuVar(name) ; }private static  tom.library.sl.Strategy  tom_make_Identity() { return  new tom.library.sl.Identity() ; }private static  tom.library.sl.Strategy  tom_make_All( tom.library.sl.Strategy  v) { return  new tom.library.sl.All(v) ; }private static boolean tom_is_fun_sym_Sequence( tom.library.sl.Strategy  t) {  return  (t instanceof tom.library.sl.Sequence)  ;}private static  tom.library.sl.Strategy  tom_empty_list_Sequence() { return  new tom.library.sl.Identity() ; }private static  tom.library.sl.Strategy  tom_cons_list_Sequence( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { return  new tom.library.sl.Sequence(head,tail) ; }private static  tom.library.sl.Strategy  tom_get_head_Sequence_Strategy( tom.library.sl.Strategy  t) {  return  (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.FIRST)  ;}private static  tom.library.sl.Strategy  tom_get_tail_Sequence_Strategy( tom.library.sl.Strategy  t) {  return  (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.THEN)  ;}private static boolean tom_is_empty_Sequence_Strategy( tom.library.sl.Strategy  t) {  return  t instanceof tom.library.sl.Identity  ;}private static  tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {    if(tom_is_empty_Sequence_Strategy(l1)) {     return l2;    } else if(tom_is_empty_Sequence_Strategy(l2)) {     return l1;    } else if(tom_is_empty_Sequence_Strategy(( tom.library.sl.Strategy )tom_get_tail_Sequence_Strategy(l1))) {     return ( tom.library.sl.Strategy )tom_cons_list_Sequence(( tom.library.sl.Strategy )tom_get_head_Sequence_Strategy(l1),l2);    } else {      return ( tom.library.sl.Strategy )tom_cons_list_Sequence(( tom.library.sl.Strategy )tom_get_head_Sequence_Strategy(l1),tom_append_list_Sequence(( tom.library.sl.Strategy )tom_get_tail_Sequence_Strategy(l1),l2));    }   }  private static  tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end) {    if(tom_terms_equal_Strategy(begin,end)) {      return ( tom.library.sl.Strategy )tom_empty_list_Sequence();    } else {      return ( tom.library.sl.Strategy )tom_cons_list_Sequence(( tom.library.sl.Strategy )tom_get_head_Sequence_Strategy(begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(( tom.library.sl.Strategy )tom_get_tail_Sequence_Strategy(begin),end));    }   }   /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */  /* Generated by TOM (version 2.5alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Sequence(v,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_empty_list_Sequence()))) ; }   


  private GomEnvironment environment() {
    return GomEnvironment.getInstance();
  }

  private static Map sortClassNameForSortDecl;
  HookCompiler(Map sortClassNameForSortDecl) {
    this.sortClassNameForSortDecl = sortClassNameForSortDecl;
  }
  /**
    * Process the hooks, and attach them to the correct classes.
    */
  public GomClassList compile(
      HookDeclList declList,
      GomClassList classes,
      Map declToClassName) {
    /* for each hook, find the class, and attach the hook */
     if(declList instanceof  tom.gom.adt.gom.types.HookDeclList ) { { tom.gom.adt.gom.types.HookDeclList  tom_match1_1=(( tom.gom.adt.gom.types.HookDeclList )declList); if ( ( tom_is_fun_sym_concHookDecl(tom_match1_1) ||  false  ) ) { { tom.gom.adt.gom.types.HookDeclList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.gom.types.HookDeclList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.gom.types.HookDeclList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concHookDecl_HookDeclList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.gom.types.HookDeclList  tom_match1_1_end2=tom_match1_1_list1; { { tom.gom.adt.gom.types.HookDecl  tom_hook=tom_get_head_concHookDecl_HookDeclList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concHookDecl_HookDeclList(tom_match1_1_list1); if ( true ) {

        Decl decl = tom_hook.getPointcut();
         if(decl instanceof  tom.gom.adt.gom.types.Decl ) { { tom.gom.adt.gom.types.Decl  tom_match2_1=(( tom.gom.adt.gom.types.Decl )decl); if ( ( tom_is_fun_sym_CutModule(tom_match2_1) ||  false  ) ) { { tom.gom.adt.gom.types.ModuleDecl  tom_mdecl=tom_get_slot_CutModule_MDecl(tom_match2_1); if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tom_mdecl);
            classes = (GomClassList)
              tom_make_TopDown(tom_make_AttachModuleHook(clsName,tom_hook)).fire(classes);
           } } } if ( ( tom_is_fun_sym_CutSort(tom_match2_1) ||  false  ) ) { { tom.gom.adt.gom.types.SortDecl  tom_sdecl=tom_get_slot_CutSort_Sort(tom_match2_1); if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tom_sdecl);
            classes = (GomClassList)
              tom_make_TopDown(tom_make_AttachSortHook(clsName,tom_hook)).fire(classes);
           } } } if ( ( tom_is_fun_sym_CutOperator(tom_match2_1) ||  false  ) ) { { tom.gom.adt.gom.types.OperatorDecl  tom_odecl=tom_get_slot_CutOperator_ODecl(tom_match2_1); if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tom_odecl);
            classes = (GomClassList)
              tom_make_TopDown(tom_make_AttachOperatorHook(clsName,tom_hook)).fire(classes);
           } } } } }

       } }tom_match1_1_list1=tom_match1_1_end2; } }tom_match1_1_end1=tom_get_tail_concHookDecl_HookDeclList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } } }

    return classes;
  }

   private static class AttachModuleHook  extends  tom.gom.adt.objects.ObjectsBasicStrategy   { private  tom.gom.adt.objects.types.ClassName  cName;  private  tom.gom.adt.gom.types.HookDecl  hook;  public AttachModuleHook(  tom.gom.adt.objects.types.ClassName  cName ,   tom.gom.adt.gom.types.HookDecl  hook ) { super(tom_make_Identity() ); this.cName=cName; this.hook=hook; } public  tom.gom.adt.objects.types.ClassName  getcName() { return cName;} public  tom.gom.adt.gom.types.HookDecl  gethook() { return hook;} public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.gom.adt.objects.types.GomClass  visit_GomClass(  tom.gom.adt.objects.types.GomClass  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.gom.adt.objects.types.GomClass ) { { tom.gom.adt.objects.types.GomClass  tom_match3_1=(( tom.gom.adt.objects.types.GomClass )tom__arg); if ( ( tom_is_fun_sym_AbstractTypeClass(tom_match3_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_AbstractTypeClass_ClassName(tom_match3_1); { tom.gom.adt.objects.types.HookList  tom_oldHooks=tom_get_slot_AbstractTypeClass_Hooks(tom_match3_1); { tom.gom.adt.objects.types.GomClass  tom_obj=tom_match3_1; if ( true ) {



        if (tom_className== cName) {
          return
            tom_obj.setHooks(tom_cons_list_concHook(makeHooksFromHookDecl(hook),tom_append_list_concHook(tom_oldHooks,tom_empty_list_concHook())));
        }
       } } } } } } } return super.visit_GomClass(tom__arg) ;  } }private static  tom.library.sl.Strategy  tom_make_AttachModuleHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachModuleHook(t0,t1); } private static class AttachSortHook  extends  tom.gom.adt.objects.ObjectsBasicStrategy   { private  tom.gom.adt.objects.types.ClassName  cName;  private  tom.gom.adt.gom.types.HookDecl  hook;  public AttachSortHook(  tom.gom.adt.objects.types.ClassName  cName ,   tom.gom.adt.gom.types.HookDecl  hook ) { super(tom_make_Identity() ); this.cName=cName; this.hook=hook; } public  tom.gom.adt.objects.types.ClassName  getcName() { return cName;} public  tom.gom.adt.gom.types.HookDecl  gethook() { return hook;} public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.gom.adt.objects.types.GomClass  visit_GomClass(  tom.gom.adt.objects.types.GomClass  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.gom.adt.objects.types.GomClass ) { { tom.gom.adt.objects.types.GomClass  tom_match4_1=(( tom.gom.adt.objects.types.GomClass )tom__arg); if ( ( tom_is_fun_sym_SortClass(tom_match4_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_SortClass_ClassName(tom_match4_1); { tom.gom.adt.objects.types.HookList  tom_oldHooks=tom_get_slot_SortClass_Hooks(tom_match4_1); { tom.gom.adt.objects.types.GomClass  tom_obj=tom_match4_1; if ( true ) {







        if (tom_className== cName) {
          return
            tom_obj.setHooks(tom_cons_list_concHook(makeHooksFromHookDecl(hook),tom_append_list_concHook(tom_oldHooks,tom_empty_list_concHook())));
        }
       } } } } } } } return super.visit_GomClass(tom__arg) ;  } }private static  tom.library.sl.Strategy  tom_make_AttachSortHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachSortHook(t0,t1); } private static class AttachOperatorHook  extends  tom.gom.adt.objects.ObjectsBasicStrategy   { private  tom.gom.adt.objects.types.ClassName  cName;  private  tom.gom.adt.gom.types.HookDecl  hook;  public AttachOperatorHook(  tom.gom.adt.objects.types.ClassName  cName ,   tom.gom.adt.gom.types.HookDecl  hook ) { super(tom_make_Identity() ); this.cName=cName; this.hook=hook; } public  tom.gom.adt.objects.types.ClassName  getcName() { return cName;} public  tom.gom.adt.gom.types.HookDecl  gethook() { return hook;} public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.gom.adt.objects.types.GomClass  visit_GomClass(  tom.gom.adt.objects.types.GomClass  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.gom.adt.objects.types.GomClass ) { { tom.gom.adt.objects.types.GomClass  tom_match5_1=(( tom.gom.adt.objects.types.GomClass )tom__arg); if ( ( tom_is_fun_sym_VariadicOperatorClass(tom_match5_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_VariadicOperatorClass_ClassName(tom_match5_1); { tom.gom.adt.objects.types.HookList  tom_oldHooks=tom_get_slot_VariadicOperatorClass_Hooks(tom_match5_1); { tom.gom.adt.objects.types.GomClass  tom_emptyClass=tom_get_slot_VariadicOperatorClass_Empty(tom_match5_1); { tom.gom.adt.objects.types.GomClass  tom_consClass=tom_get_slot_VariadicOperatorClass_Cons(tom_match5_1); { tom.gom.adt.objects.types.GomClass  tom_obj=tom_match5_1; if ( true ) {








        if (tom_className== cName) {
          /* We may want to attach the hook to the cons or empty */
          if (hook.isMakeHookDecl()) {
            if (hook.getSlotArgs() != tom_empty_list_concSlot()) {
              HookList oldConsHooks = tom_consClass.getHooks();
              GomClass newCons =
                tom_consClass.setHooks(
                    tom_cons_list_concHook(makeHooksFromHookDecl(hook),tom_append_list_concHook(oldConsHooks,tom_empty_list_concHook())));
              return tom_obj.setCons(newCons);
            } else if (hook.getSlotArgs() == tom_empty_list_concSlot()) {
              HookList oldEmptyHooks = tom_emptyClass.getHooks();
              GomClass newEmpty =
                tom_emptyClass.setHooks(
                    tom_cons_list_concHook(makeHooksFromHookDecl(hook),tom_append_list_concHook(oldEmptyHooks,tom_empty_list_concHook())));
              return tom_obj.setEmpty(newEmpty);
            }
          } else {
            return
              tom_obj.setHooks(tom_cons_list_concHook(makeHooksFromHookDecl(hook),tom_append_list_concHook(tom_oldHooks,tom_empty_list_concHook())));
          }
        }
       } } } } } } } if ( ( tom_is_fun_sym_OperatorClass(tom_match5_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_OperatorClass_ClassName(tom_match5_1); { tom.gom.adt.objects.types.HookList  tom_oldHooks=tom_get_slot_OperatorClass_Hooks(tom_match5_1); { tom.gom.adt.objects.types.GomClass  tom_obj=tom_match5_1; if ( true ) {

        if (tom_className== cName) {
          return
            tom_obj.setHooks(tom_cons_list_concHook(makeHooksFromHookDecl(hook),tom_append_list_concHook(tom_oldHooks,tom_empty_list_concHook())));
        }
       } } } } } } } return super.visit_GomClass(tom__arg) ;  } }private static  tom.library.sl.Strategy  tom_make_AttachOperatorHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachOperatorHook(t0,t1); }



  private static Hook makeHooksFromHookDecl(HookDecl hookDecl) {
     if(hookDecl instanceof  tom.gom.adt.gom.types.HookDecl ) { { tom.gom.adt.gom.types.HookDecl  tom_match6_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl); if ( ( tom_is_fun_sym_MakeHookDecl(tom_match6_1) ||  false  ) ) { { tom.gom.adt.gom.types.SlotList  tom_slotArgs=tom_get_slot_MakeHookDecl_SlotArgs(tom_match6_1); { tom.gom.adt.code.types.Code  tom_hookCode=tom_get_slot_MakeHookDecl_Code(tom_match6_1); if ( true ) {

        SlotFieldList newArgs = makeSlotFieldListFromSlotList(tom_slotArgs);
        return tom_make_MakeHook(newArgs,tom_hookCode);
       } } } } if ( ( tom_is_fun_sym_BlockHookDecl(tom_match6_1) ||  false  ) ) { { tom.gom.adt.code.types.Code  tom_hookCode=tom_get_slot_BlockHookDecl_Code(tom_match6_1); if ( true ) {

        return tom_make_BlockHook(tom_hookCode);
       } } } if ( ( tom_is_fun_sym_InterfaceHookDecl(tom_match6_1) ||  false  ) ) { { tom.gom.adt.code.types.Code  tom_hookCode=tom_get_slot_InterfaceHookDecl_Code(tom_match6_1); if ( true ) {

        return tom_make_InterfaceHook(tom_hookCode);
       } } } if ( ( tom_is_fun_sym_ImportHookDecl(tom_match6_1) ||  false  ) ) { { tom.gom.adt.code.types.Code  tom_hookCode=tom_get_slot_ImportHookDecl_Code(tom_match6_1); if ( true ) {

        return tom_make_ImportHook(tom_hookCode);
       } } } if ( ( tom_is_fun_sym_MappingHookDecl(tom_match6_1) ||  false  ) ) { { tom.gom.adt.code.types.Code  tom_hookCode=tom_get_slot_MappingHookDecl_Code(tom_match6_1); if ( true ) {

        return tom_make_MappingHook(tom_hookCode);
       } } } } }

    throw new GomRuntimeException(
        "Hook declaration " + hookDecl+ " not processed");
  }

  private static SlotFieldList makeSlotFieldListFromSlotList(SlotList args) {
    SlotFieldList newArgs = tom_empty_list_concSlotField();
    while(!args.isEmptyconcSlot()) {
      Slot arg = args.getHeadconcSlot();
      args = args.getTailconcSlot();
       if(arg instanceof  tom.gom.adt.gom.types.Slot ) { { tom.gom.adt.gom.types.Slot  tom_match7_1=(( tom.gom.adt.gom.types.Slot )arg); if ( ( tom_is_fun_sym_Slot(tom_match7_1) ||  false  ) ) { { String  tom_slotName=tom_get_slot_Slot_Name(tom_match7_1); { tom.gom.adt.gom.types.SortDecl  tom_sortDecl=tom_get_slot_Slot_Sort(tom_match7_1); if ( true ) {

          ClassName slotClassName = (ClassName)
            sortClassNameForSortDecl.get(tom_sortDecl);
          newArgs = tom_append_list_concSlotField(newArgs,tom_cons_list_concSlotField(tom_make_SlotField(tom_slotName,slotClassName),tom_empty_list_concSlotField()));
         } } } } } }

    }
    return newArgs;
  }
}
