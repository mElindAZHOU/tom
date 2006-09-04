/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2006, INRIA
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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Antoine Reilles        e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.engine.verifier;

import tom.engine.*;
import aterm.*;
import aterm.pure.*;
import java.util.*;
import tom.engine.adt.zenon.*;
import tom.engine.adt.zenon.types.*;

public class ZenonBackend {

  // ------------------------------------------------------------
  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */private static boolean tom_terms_equal_int( int  t1,  int  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_ZTermList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ZType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ZTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ZAxiom(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ZSpec(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ZSymbol(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ZExpr(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ZAxiomList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_ztype( tom.engine.adt.zenon.types.ZType  t) {  return  t instanceof tom.engine.adt.zenon.types.ztype.ztype  ;}private static  String  tom_get_slot_ztype_Tname( tom.engine.adt.zenon.types.ZType  t) {  return  t.getTname()  ;}private static boolean tom_is_fun_sym_zvar( tom.engine.adt.zenon.types.ZTerm  t) {  return  t instanceof tom.engine.adt.zenon.types.zterm.zvar  ;}private static  String  tom_get_slot_zvar_Varname( tom.engine.adt.zenon.types.ZTerm  t) {  return  t.getVarname()  ;}private static boolean tom_is_fun_sym_zappl( tom.engine.adt.zenon.types.ZTerm  t) {  return  t instanceof tom.engine.adt.zenon.types.zterm.zappl  ;}private static  tom.engine.adt.zenon.types.ZSymbol  tom_get_slot_zappl_Zsymb( tom.engine.adt.zenon.types.ZTerm  t) {  return  t.getZsymb()  ;}private static  tom.engine.adt.zenon.types.ZTermList  tom_get_slot_zappl_Termlist( tom.engine.adt.zenon.types.ZTerm  t) {  return  t.getTermlist()  ;}private static boolean tom_is_fun_sym_zst( tom.engine.adt.zenon.types.ZTerm  t) {  return  t instanceof tom.engine.adt.zenon.types.zterm.zst  ;}private static  tom.engine.adt.zenon.types.ZTerm  tom_get_slot_zst_Abst( tom.engine.adt.zenon.types.ZTerm  t) {  return  t.getAbst()  ;}private static  int  tom_get_slot_zst_Index( tom.engine.adt.zenon.types.ZTerm  t) {  return  t.getIndex()  ;}private static boolean tom_is_fun_sym_zsl( tom.engine.adt.zenon.types.ZTerm  t) {  return  t instanceof tom.engine.adt.zenon.types.zterm.zsl  ;}private static  tom.engine.adt.zenon.types.ZTerm  tom_get_slot_zsl_Abst( tom.engine.adt.zenon.types.ZTerm  t) {  return  t.getAbst()  ;}private static  String  tom_get_slot_zsl_Name( tom.engine.adt.zenon.types.ZTerm  t) {  return  t.getName()  ;}private static boolean tom_is_fun_sym_zaxiom( tom.engine.adt.zenon.types.ZAxiom  t) {  return  t instanceof tom.engine.adt.zenon.types.zaxiom.zaxiom  ;}private static  String  tom_get_slot_zaxiom_Name( tom.engine.adt.zenon.types.ZAxiom  t) {  return  t.getName()  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zaxiom_Ax( tom.engine.adt.zenon.types.ZAxiom  t) {  return  t.getAx()  ;}private static boolean tom_is_fun_sym_zthm( tom.engine.adt.zenon.types.ZSpec  t) {  return  t instanceof tom.engine.adt.zenon.types.zspec.zthm  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zthm_Thm( tom.engine.adt.zenon.types.ZSpec  t) {  return  t.getThm()  ;}private static  tom.engine.adt.zenon.types.ZAxiomList  tom_get_slot_zthm_By( tom.engine.adt.zenon.types.ZSpec  t) {  return  t.getBy()  ;}private static boolean tom_is_fun_sym_zsymbol( tom.engine.adt.zenon.types.ZSymbol  t) {  return  t instanceof tom.engine.adt.zenon.types.zsymbol.zsymbol  ;}private static  tom.engine.adt.zenon.types.ZSymbol  tom_make_zsymbol( String  t0) { return  tom.engine.adt.zenon.types.zsymbol.zsymbol.make(t0); }private static  String  tom_get_slot_zsymbol_Name( tom.engine.adt.zenon.types.ZSymbol  t) {  return  t.getName()  ;}private static boolean tom_is_fun_sym_ztrue( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.ztrue  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_make_ztrue() { return  tom.engine.adt.zenon.types.zexpr.ztrue.make(); }private static boolean tom_is_fun_sym_zfalse( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.zfalse  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_make_zfalse() { return  tom.engine.adt.zenon.types.zexpr.zfalse.make(); }private static boolean tom_is_fun_sym_zisfsym( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.zisfsym  ;}private static  tom.engine.adt.zenon.types.ZTerm  tom_get_slot_zisfsym_T( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getT()  ;}private static  tom.engine.adt.zenon.types.ZSymbol  tom_get_slot_zisfsym_Symbol( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getSymbol()  ;}private static boolean tom_is_fun_sym_zeq( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.zeq  ;}private static  tom.engine.adt.zenon.types.ZTerm  tom_get_slot_zeq_Lt( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getLt()  ;}private static  tom.engine.adt.zenon.types.ZTerm  tom_get_slot_zeq_Rt( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getRt()  ;}private static boolean tom_is_fun_sym_zforall( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.zforall  ;}private static  tom.engine.adt.zenon.types.ZTerm  tom_get_slot_zforall_Var( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getVar()  ;}private static  tom.engine.adt.zenon.types.ZType  tom_get_slot_zforall_Aztype( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getAztype()  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zforall_Expr( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getExpr()  ;}private static boolean tom_is_fun_sym_zexists( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.zexists  ;}private static  tom.engine.adt.zenon.types.ZTerm  tom_get_slot_zexists_Var( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getVar()  ;}private static  tom.engine.adt.zenon.types.ZType  tom_get_slot_zexists_Aztype( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getAztype()  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zexists_Expr( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getExpr()  ;}private static boolean tom_is_fun_sym_zand( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.zand  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zand_Lte( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getLte()  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zand_Rte( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getRte()  ;}private static boolean tom_is_fun_sym_zor( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.zor  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zor_Lte( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getLte()  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zor_Rte( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getRte()  ;}private static boolean tom_is_fun_sym_znot( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.znot  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_znot_Nex( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getNex()  ;}private static boolean tom_is_fun_sym_zequiv( tom.engine.adt.zenon.types.ZExpr  t) {  return  t instanceof tom.engine.adt.zenon.types.zexpr.zequiv  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zequiv_Lte( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getLte()  ;}private static  tom.engine.adt.zenon.types.ZExpr  tom_get_slot_zequiv_Rte( tom.engine.adt.zenon.types.ZExpr  t) {  return  t.getRte()  ;}private static boolean tom_is_fun_sym_zby( tom.engine.adt.zenon.types.ZAxiomList  t) {  return  t instanceof tom.engine.adt.zenon.types.zaxiomlist.Conszby || t instanceof tom.engine.adt.zenon.types.zaxiomlist.Emptyzby  ;}private static  tom.engine.adt.zenon.types.ZAxiomList  tom_empty_list_zby() { return  tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ; }private static  tom.engine.adt.zenon.types.ZAxiomList  tom_cons_list_zby( tom.engine.adt.zenon.types.ZAxiom  e,  tom.engine.adt.zenon.types.ZAxiomList  l) { return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make(e,l) ; }private static  tom.engine.adt.zenon.types.ZAxiom  tom_get_head_zby_ZAxiomList( tom.engine.adt.zenon.types.ZAxiomList  l) {  return  l.getHeadzby()  ;}private static  tom.engine.adt.zenon.types.ZAxiomList  tom_get_tail_zby_ZAxiomList( tom.engine.adt.zenon.types.ZAxiomList  l) {  return  l.getTailzby()  ;}private static boolean tom_is_empty_zby_ZAxiomList( tom.engine.adt.zenon.types.ZAxiomList  l) {  return  l.isEmptyzby()  ;}private static  tom.engine.adt.zenon.types.ZAxiomList  tom_append_list_zby( tom.engine.adt.zenon.types.ZAxiomList  l1,  tom.engine.adt.zenon.types.ZAxiomList  l2) {    if(tom_is_empty_zby_ZAxiomList(l1)) {     return l2;    } else if(tom_is_empty_zby_ZAxiomList(l2)) {     return l1;    } else if(tom_is_empty_zby_ZAxiomList(( tom.engine.adt.zenon.types.ZAxiomList )tom_get_tail_zby_ZAxiomList(l1))) {     return ( tom.engine.adt.zenon.types.ZAxiomList )tom_cons_list_zby(( tom.engine.adt.zenon.types.ZAxiom )tom_get_head_zby_ZAxiomList(l1),l2);    } else {      return ( tom.engine.adt.zenon.types.ZAxiomList )tom_cons_list_zby(( tom.engine.adt.zenon.types.ZAxiom )tom_get_head_zby_ZAxiomList(l1),tom_append_list_zby(( tom.engine.adt.zenon.types.ZAxiomList )tom_get_tail_zby_ZAxiomList(l1),l2));    }   }  private static  tom.engine.adt.zenon.types.ZAxiomList  tom_get_slice_zby( tom.engine.adt.zenon.types.ZAxiomList  begin,  tom.engine.adt.zenon.types.ZAxiomList  end) {    if(tom_terms_equal_ZAxiomList(begin,end)) {      return ( tom.engine.adt.zenon.types.ZAxiomList )tom_empty_list_zby();    } else {      return ( tom.engine.adt.zenon.types.ZAxiomList )tom_cons_list_zby(( tom.engine.adt.zenon.types.ZAxiom )tom_get_head_zby_ZAxiomList(begin),( tom.engine.adt.zenon.types.ZAxiomList )tom_get_slice_zby(( tom.engine.adt.zenon.types.ZAxiomList )tom_get_tail_zby_ZAxiomList(begin),end));    }   }   
  // ------------------------------------------------------------

  private Verifier verifier; // is it useful ?
  private TomIlTools tomiltools;

  public ZenonBackend(Verifier verifier) {
    this.verifier = verifier;
    this.tomiltools = new TomIlTools(verifier);
  }

  public String genZSymbol(ZSymbol symbol) {
     if(symbol instanceof  tom.engine.adt.zenon.types.ZSymbol ) { { tom.engine.adt.zenon.types.ZSymbol  tom_match1_1=(( tom.engine.adt.zenon.types.ZSymbol )symbol); if ( ( tom_is_fun_sym_zsymbol(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Name=tom_get_slot_zsymbol_Name(tom_match1_1); { String  tom_name=tom_match1_1_Name; if ( true ) {

        // manage builtins
        String symbolName = tomiltools.replaceNumbersByString(tom_name);
        return symbolName+ "_";
       } } } } } }

    return "errorZSymbol";
  }

  public String genZType(ZType type) {
     if(type instanceof  tom.engine.adt.zenon.types.ZType ) { { tom.engine.adt.zenon.types.ZType  tom_match2_1=(( tom.engine.adt.zenon.types.ZType )type); if ( ( tom_is_fun_sym_ztype(tom_match2_1) ||  false  ) ) { { String  tom_match2_1_Tname=tom_get_slot_ztype_Tname(tom_match2_1); { String  tom_name=tom_match2_1_Tname; if ( true ) {

        return tom_name;
       } } } } } }

    return "errorZType";
  }

  public String genZTerm(ZTerm term) {
     if(term instanceof  tom.engine.adt.zenon.types.ZTerm ) { { tom.engine.adt.zenon.types.ZTerm  tom_match3_1=(( tom.engine.adt.zenon.types.ZTerm )term); if ( ( tom_is_fun_sym_zvar(tom_match3_1) ||  false  ) ) { { String  tom_match3_1_Varname=tom_get_slot_zvar_Varname(tom_match3_1); { String  tom_name=tom_match3_1_Varname; if ( true ) {
 return tom_name;  } } } } if ( ( tom_is_fun_sym_zappl(tom_match3_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZSymbol  tom_match3_1_Zsymb=tom_get_slot_zappl_Zsymb(tom_match3_1); { tom.engine.adt.zenon.types.ZTermList  tom_match3_1_Termlist=tom_get_slot_zappl_Termlist(tom_match3_1); if ( ( tom_is_fun_sym_zsymbol(tom_match3_1_Zsymb) ||  false  ) ) { { String  tom_match3_1_Zsymb_Name=tom_get_slot_zsymbol_Name(tom_match3_1_Zsymb); { String  tom_name=tom_match3_1_Zsymb_Name; { tom.engine.adt.zenon.types.ZTermList  tom_tlist=tom_match3_1_Termlist; if ( true ) {
 
        // manage builtins
        String realName = tomiltools.replaceNumbersByString(tom_name);
        return "(" + realName +" "+genZTermList(tom_tlist)+")"; 
       } } } } } } } } if ( ( tom_is_fun_sym_zst(tom_match3_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZTerm  tom_match3_1_Abst=tom_get_slot_zst_Abst(tom_match3_1); { int  tom_match3_1_Index=tom_get_slot_zst_Index(tom_match3_1); { tom.engine.adt.zenon.types.ZTerm  tom_t=tom_match3_1_Abst; { int  tom_idx=tom_match3_1_Index; if ( true ) {
 
        return "(_"+tom_idx+" "+genZTerm(tom_t)+")";
       } } } } } } if ( ( tom_is_fun_sym_zsl(tom_match3_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZTerm  tom_match3_1_Abst=tom_get_slot_zsl_Abst(tom_match3_1); { String  tom_match3_1_Name=tom_get_slot_zsl_Name(tom_match3_1); { tom.engine.adt.zenon.types.ZTerm  tom_t=tom_match3_1_Abst; { String  tom_slot=tom_match3_1_Name; if ( true ) {
 
        return "(_"+tom_slot+" "+genZTerm(tom_t)+")";
       } } } } } } } }

    return "errorZTerm";
  }

  public String genZTermList(ZTermList tl) {
    StringBuffer res = new StringBuffer();
    while (!tl.isEmptyconcZTerm()) {
      res.append(" "+genZTerm(tl.getHeadconcZTerm()));
      tl = tl.getTailconcZTerm();
    }
    return res.toString();
  }

  public String genZExpr(ZExpr expr) {
     if(expr instanceof  tom.engine.adt.zenon.types.ZExpr ) { { tom.engine.adt.zenon.types.ZExpr  tom_match4_1=(( tom.engine.adt.zenon.types.ZExpr )expr); if ( ( tom_is_fun_sym_ztrue(tom_match4_1) ||  false  ) ) { if ( true ) {
 return "True"; } } if ( ( tom_is_fun_sym_zfalse(tom_match4_1) ||  false  ) ) { if ( true ) {
 return "False"; } } if ( ( tom_is_fun_sym_zisfsym(tom_match4_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZTerm  tom_match4_1_T=tom_get_slot_zisfsym_T(tom_match4_1); { tom.engine.adt.zenon.types.ZSymbol  tom_match4_1_Symbol=tom_get_slot_zisfsym_Symbol(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm  tom_t=tom_match4_1_T; { tom.engine.adt.zenon.types.ZSymbol  tom_s=tom_match4_1_Symbol; if ( true ) {

        return "((symb "+genZTerm(tom_t)+") = "+genZSymbol(tom_s)+")";
       } } } } } } if ( ( tom_is_fun_sym_zeq(tom_match4_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZTerm  tom_match4_1_Lt=tom_get_slot_zeq_Lt(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm  tom_match4_1_Rt=tom_get_slot_zeq_Rt(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm  tom_l=tom_match4_1_Lt; { tom.engine.adt.zenon.types.ZTerm  tom_r=tom_match4_1_Rt; if ( true ) {

        return "("+genZTerm(tom_l)+" = "+genZTerm(tom_r)+")";
       } } } } } } if ( ( tom_is_fun_sym_zforall(tom_match4_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZTerm  tom_match4_1_Var=tom_get_slot_zforall_Var(tom_match4_1); { tom.engine.adt.zenon.types.ZType  tom_match4_1_Aztype=tom_get_slot_zforall_Aztype(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Expr=tom_get_slot_zforall_Expr(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm  tom_v=tom_match4_1_Var; { tom.engine.adt.zenon.types.ZType  tom_type=tom_match4_1_Aztype; { tom.engine.adt.zenon.types.ZExpr  tom_e=tom_match4_1_Expr; if ( true ) {

        return "forall "+genZTerm(tom_v)+" : "+genZType(tom_type)+",\n "+genZExpr(tom_e);
       } } } } } } } } if ( ( tom_is_fun_sym_zexists(tom_match4_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZTerm  tom_match4_1_Var=tom_get_slot_zexists_Var(tom_match4_1); { tom.engine.adt.zenon.types.ZType  tom_match4_1_Aztype=tom_get_slot_zexists_Aztype(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Expr=tom_get_slot_zexists_Expr(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm  tom_v=tom_match4_1_Var; { tom.engine.adt.zenon.types.ZType  tom_type=tom_match4_1_Aztype; { tom.engine.adt.zenon.types.ZExpr  tom_e=tom_match4_1_Expr; if ( true ) {

        return "exists "+genZTerm(tom_v)+" : "+genZType(tom_type)+", "+genZExpr(tom_e);
       } } } } } } } } if ( ( tom_is_fun_sym_zand(tom_match4_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Lte=tom_get_slot_zand_Lte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Rte=tom_get_slot_zand_Rte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_l=tom_match4_1_Lte; { tom.engine.adt.zenon.types.ZExpr  tom_r=tom_match4_1_Rte; if ( true ) {

        if(tom_l== tom_make_ztrue()) {
          return "("+ genZExpr(tom_r) +")";
        }
        else if (tom_r== tom_make_ztrue()) {
          return "("+ genZExpr(tom_l) +")";
        }
        return "("+genZExpr(tom_l)+") /\\ ("+genZExpr(tom_r)+")";
       } } } } } } if ( ( tom_is_fun_sym_zor(tom_match4_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Lte=tom_get_slot_zor_Lte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Rte=tom_get_slot_zor_Rte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_l=tom_match4_1_Lte; { tom.engine.adt.zenon.types.ZExpr  tom_r=tom_match4_1_Rte; if ( true ) {

        if(tom_l== tom_make_zfalse()) {
          return "("+ genZExpr(tom_r) +")";
        }
        else if (tom_r== tom_make_zfalse()) {
          return "("+ genZExpr(tom_l) +")";
        }
        return "("+genZExpr(tom_l)+") \\/ ("+genZExpr(tom_r)+")";
       } } } } } } if ( ( tom_is_fun_sym_znot(tom_match4_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Nex=tom_get_slot_znot_Nex(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_e=tom_match4_1_Nex; if ( true ) {
 return "~("+genZExpr(tom_e)+")";  } } } } if ( ( tom_is_fun_sym_zequiv(tom_match4_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Lte=tom_get_slot_zequiv_Lte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_match4_1_Rte=tom_get_slot_zequiv_Rte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr  tom_l=tom_match4_1_Lte; { tom.engine.adt.zenon.types.ZExpr  tom_r=tom_match4_1_Rte; if ( true ) {

        return "("+genZExpr(tom_l)+") <-> ("+genZExpr(tom_r)+")";
       } } } } } } } }

    return "errorZExpr";
  }

  public String genZAxiom(ZAxiom axiom) {
     if(axiom instanceof  tom.engine.adt.zenon.types.ZAxiom ) { { tom.engine.adt.zenon.types.ZAxiom  tom_match5_1=(( tom.engine.adt.zenon.types.ZAxiom )axiom); if ( ( tom_is_fun_sym_zaxiom(tom_match5_1) ||  false  ) ) { { String  tom_match5_1_Name=tom_get_slot_zaxiom_Name(tom_match5_1); { tom.engine.adt.zenon.types.ZExpr  tom_match5_1_Ax=tom_get_slot_zaxiom_Ax(tom_match5_1); { String  tom_name=tom_match5_1_Name; { tom.engine.adt.zenon.types.ZExpr  tom_ax=tom_match5_1_Ax; if ( true ) {

        // manage builtins
        String realName = tomiltools.replaceNumbersByString(tom_name);
        return "Parameter " + realName +" :\n    " + genZExpr(tom_ax) + ".\n";
       } } } } } } } }

    return "errorZAxiom";
  }

  public String genZAxiomList(ZAxiomList axlist) {
    StringBuffer res = new StringBuffer();
    while (!axlist.isEmptyzby()) {
      res.append(genZAxiom(axlist.getHeadzby()));
      axlist = axlist.getTailzby();
    }
    return res.toString();
  }

  public String genZSpec(ZSpec spec) {
     if(spec instanceof  tom.engine.adt.zenon.types.ZSpec ) { { tom.engine.adt.zenon.types.ZSpec  tom_match6_1=(( tom.engine.adt.zenon.types.ZSpec )spec); if ( ( tom_is_fun_sym_zthm(tom_match6_1) ||  false  ) ) { { tom.engine.adt.zenon.types.ZExpr  tom_match6_1_Thm=tom_get_slot_zthm_Thm(tom_match6_1); { tom.engine.adt.zenon.types.ZAxiomList  tom_match6_1_By=tom_get_slot_zthm_By(tom_match6_1); { tom.engine.adt.zenon.types.ZExpr  tom_thm=tom_match6_1_Thm; { tom.engine.adt.zenon.types.ZAxiomList  tom_by=tom_match6_1_By; if ( true ) {

        return "\n" 
          + genZExpr(tom_thm) 
          + "\n" 
          + genZAxiomList(tom_by)+"\n";
       } } } } } } } }

    return "errorZSpec";
  }

  public String genFunctionSymbolDeclaration(String symbolName) {
    StringBuffer res = new StringBuffer();
    res.append("Parameter ");
    res.append(tomiltools.replaceNumbersByString(symbolName)+" :");
    // take care of the arity
    List names = tomiltools.subtermList(symbolName);
    for(int i = 0; i<names.size();i++) {
      res.append(" T ->");
    }
    res.append(" T.\n");
    return res.toString();
  }

  public String genZSpecCollection(Collection collection) {
    StringBuffer out = new StringBuffer();

    out.append("\nRequire Import zenon.\n");
    out.append("\nParameter T S : Set.\n");

    // collects all used symbols
    Collection symbols = new HashSet();
    Iterator it = collection.iterator();
    while(it.hasNext()) {
      ZSpec local = (ZSpec) it.next();
      symbols.addAll(tomiltools.collectSymbolsFromZSpec(local));
    }

    // Generates types for symbol functions
    it = symbols.iterator();
    while(it.hasNext()) {
      String symbolName = (String) it.next();
      out.append(genFunctionSymbolDeclaration(symbolName));
      // declares the subterm functions if necessary
      List names = tomiltools.subtermList(symbolName);
      if(names.size() != 0) {
        out.append("Parameter ");
        Iterator nameIt = names.iterator();
        while(nameIt.hasNext()) {
          String localName = (String) nameIt.next();
          out.append("_" + localName + " ");
        }
        out.append(": T -> T.\n");
      }
    }


    out.append("Parameter symb : T -> S.\n");
    // XXX: define True
    out.append("Parameter true_is_true : True.\n");
    // Generates types for symbols
    it = symbols.iterator();
    out.append("Parameter ");
    while(it.hasNext()) {
      String symbolName = (String) it.next();
      out.append(genZSymbol(tom_make_zsymbol(symbolName)) +" ");
    }
    out.append(": S.\n");


    // Generates the axioms for coq
    ZAxiomList axiomsDef = tomiltools.symbolsDefinition(symbols);
    ZAxiomList axiomsSub = tomiltools.subtermsDefinition(symbols);
    ZAxiomList axioms = tom_append_list_zby(axiomsDef,tom_append_list_zby(axiomsSub,tom_empty_list_zby()));
    while (!axioms.isEmptyzby()) {
      out.append(genZAxiom(axioms.getHeadzby()));
      axioms = axioms.getTailzby();
    }

    // Generates the different proof obligations
    int number=1;
    it = collection.iterator();
    while (it.hasNext()) {
      out.append("\n%%begin-auto-proof\n");
      //out.append("%%location: []\n");
      out.append("%%name: theorem"+number+"\n");
      //out.append("%%syntax: tom\n");
      //out.append("%%statement\n");
      out.append(genZSpec((ZSpec)it.next()));

      // XXX: Outputs the axiom for True (Newer versions of zenon may remove this need)
      out.append("Parameter true_is_true : True.\n");

      // Generates types for symbol functions
      // (otherwise, zenon can not know T is not empty)
      // also adds a Parameter fake : T. to make sure zenon knows T is
      // not empty
      Iterator symbIt = symbols.iterator();
      while(symbIt.hasNext()) {
        String symbolName = (String) symbIt.next();
        out.append(genFunctionSymbolDeclaration(symbolName));
      }
      out.append("Parameter tom_fake : T.\n");
    
      out.append("%%end-auto-proof\n");
      number++;
    }
    return out.toString();
  }
}
