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
  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_char( char  t1,  char  t2) {  return  (t1==t2)  ;}private static boolean tom_terms_equal_Character(Object t1, Object t2) {  return  (t1.equals(t2))  ;} private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */ private static boolean tom_terms_equal_int( int  t1,  int  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_double( double  t1,  double  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_ATerm(Object t1, Object t2) {  return  t1 == t2 ;} /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_ATermList(Object l1, Object l2) {  return  l1==l2  ;} private static boolean tom_terms_equal_ZSpec(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_zthm( tom.engine.adt.zenon.types.ZSpec t) {  return  (t!= null) && t.isZthm() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zthm_thm( tom.engine.adt.zenon.types.ZSpec t) {  return  t.getThm() ;}private static  tom.engine.adt.zenon.types.ZAxiomList tom_get_slot_zthm_by( tom.engine.adt.zenon.types.ZSpec t) {  return  t.getBy() ;}private static boolean tom_terms_equal_ZAxiomList(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_zby( tom.engine.adt.zenon.types.ZAxiomList t) {  return (t!= null) && t.isSortZAxiomList() ;}private static  tom.engine.adt.zenon.types.ZAxiomList tom_empty_list_zby() { return tom.engine.adt.zenon.ZenonFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeZAxiomList(); }private static  tom.engine.adt.zenon.types.ZAxiomList tom_cons_list_zby( tom.engine.adt.zenon.types.ZAxiom e,  tom.engine.adt.zenon.types.ZAxiomList l) { return tom.engine.adt.zenon.ZenonFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeZAxiomList(e,l); }private static  tom.engine.adt.zenon.types.ZAxiom tom_get_head_zby_ZAxiomList( tom.engine.adt.zenon.types.ZAxiomList l) {  return l.getHead() ;}private static  tom.engine.adt.zenon.types.ZAxiomList tom_get_tail_zby_ZAxiomList( tom.engine.adt.zenon.types.ZAxiomList l) {  return l.getTail() ;}private static boolean tom_is_empty_zby_ZAxiomList( tom.engine.adt.zenon.types.ZAxiomList l) {  return l.isEmpty() ;}private static  tom.engine.adt.zenon.types.ZAxiomList tom_append_list_zby( tom.engine.adt.zenon.types.ZAxiomList l1,  tom.engine.adt.zenon.types.ZAxiomList l2) {    if(tom_is_empty_zby_ZAxiomList(l1)) {     return l2;    } else if(tom_is_empty_zby_ZAxiomList(l2)) {     return l1;    } else if(tom_is_empty_zby_ZAxiomList(( tom.engine.adt.zenon.types.ZAxiomList)tom_get_tail_zby_ZAxiomList(l1))) {     return ( tom.engine.adt.zenon.types.ZAxiomList)tom_cons_list_zby(( tom.engine.adt.zenon.types.ZAxiom)tom_get_head_zby_ZAxiomList(l1),l2);    } else {      return ( tom.engine.adt.zenon.types.ZAxiomList)tom_cons_list_zby(( tom.engine.adt.zenon.types.ZAxiom)tom_get_head_zby_ZAxiomList(l1),tom_append_list_zby(( tom.engine.adt.zenon.types.ZAxiomList)tom_get_tail_zby_ZAxiomList(l1),l2));    }   }  private static  tom.engine.adt.zenon.types.ZAxiomList tom_get_slice_zby( tom.engine.adt.zenon.types.ZAxiomList begin,  tom.engine.adt.zenon.types.ZAxiomList end) {    if(tom_terms_equal_ZAxiomList(begin,end)) {      return ( tom.engine.adt.zenon.types.ZAxiomList)tom_empty_list_zby();    } else {      return ( tom.engine.adt.zenon.types.ZAxiomList)tom_cons_list_zby(( tom.engine.adt.zenon.types.ZAxiom)tom_get_head_zby_ZAxiomList(begin),( tom.engine.adt.zenon.types.ZAxiomList)tom_get_slice_zby(( tom.engine.adt.zenon.types.ZAxiomList)tom_get_tail_zby_ZAxiomList(begin),end));    }   }  private static boolean tom_terms_equal_ZAxiom(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_zaxiom( tom.engine.adt.zenon.types.ZAxiom t) {  return  (t!= null) && t.isZaxiom() ;}private static  String  tom_get_slot_zaxiom_name( tom.engine.adt.zenon.types.ZAxiom t) {  return  t.getName() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zaxiom_ax( tom.engine.adt.zenon.types.ZAxiom t) {  return  t.getAx() ;}private static boolean tom_terms_equal_ZExpr(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_zequiv( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZequiv() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zequiv_lte( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getLte() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zequiv_rte( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getRte() ;}private static boolean tom_is_fun_sym_znot( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZnot() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_znot_nex( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getNex() ;}private static boolean tom_is_fun_sym_zor( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZor() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zor_lte( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getLte() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zor_rte( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getRte() ;}private static boolean tom_is_fun_sym_zand( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZand() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zand_lte( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getLte() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zand_rte( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getRte() ;}private static boolean tom_is_fun_sym_zexists( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZexists() ;}private static  tom.engine.adt.zenon.types.ZTerm tom_get_slot_zexists_var( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getVar() ;}private static  tom.engine.adt.zenon.types.ZType tom_get_slot_zexists_aztype( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getAztype() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zexists_expr( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getExpr() ;}private static boolean tom_is_fun_sym_zforall( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZforall() ;}private static  tom.engine.adt.zenon.types.ZTerm tom_get_slot_zforall_var( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getVar() ;}private static  tom.engine.adt.zenon.types.ZType tom_get_slot_zforall_aztype( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getAztype() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_get_slot_zforall_expr( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getExpr() ;}private static boolean tom_is_fun_sym_zeq( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZeq() ;}private static  tom.engine.adt.zenon.types.ZTerm tom_get_slot_zeq_lt( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getLt() ;}private static  tom.engine.adt.zenon.types.ZTerm tom_get_slot_zeq_rt( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getRt() ;}private static boolean tom_is_fun_sym_zisfsym( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZisfsym() ;}private static  tom.engine.adt.zenon.types.ZTerm tom_get_slot_zisfsym_t( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getT() ;}private static  tom.engine.adt.zenon.types.ZSymbol tom_get_slot_zisfsym_symbol( tom.engine.adt.zenon.types.ZExpr t) {  return  t.getSymbol() ;}private static boolean tom_is_fun_sym_zfalse( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZfalse() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_make_zfalse() { return  tom.engine.adt.zenon.ZenonFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeZExpr_Zfalse(); }private static boolean tom_is_fun_sym_ztrue( tom.engine.adt.zenon.types.ZExpr t) {  return  (t!= null) && t.isZtrue() ;}private static  tom.engine.adt.zenon.types.ZExpr tom_make_ztrue() { return  tom.engine.adt.zenon.ZenonFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeZExpr_Ztrue(); }private static boolean tom_terms_equal_ZType(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_ztype( tom.engine.adt.zenon.types.ZType t) {  return  (t!= null) && t.isZtype() ;}private static  String  tom_get_slot_ztype_tname( tom.engine.adt.zenon.types.ZType t) {  return  t.getTname() ;}private static boolean tom_terms_equal_ZTermList(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_terms_equal_ZTerm(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_zsl( tom.engine.adt.zenon.types.ZTerm t) {  return  (t!= null) && t.isZsl() ;}private static  tom.engine.adt.zenon.types.ZTerm tom_get_slot_zsl_abst( tom.engine.adt.zenon.types.ZTerm t) {  return  t.getAbst() ;}private static  String  tom_get_slot_zsl_name( tom.engine.adt.zenon.types.ZTerm t) {  return  t.getName() ;}private static boolean tom_is_fun_sym_zst( tom.engine.adt.zenon.types.ZTerm t) {  return  (t!= null) && t.isZst() ;}private static  tom.engine.adt.zenon.types.ZTerm tom_get_slot_zst_abst( tom.engine.adt.zenon.types.ZTerm t) {  return  t.getAbst() ;}private static  int  tom_get_slot_zst_index( tom.engine.adt.zenon.types.ZTerm t) {  return  t.getIndex() ;}private static boolean tom_is_fun_sym_zappl( tom.engine.adt.zenon.types.ZTerm t) {  return  (t!= null) && t.isZappl() ;}private static  tom.engine.adt.zenon.types.ZSymbol tom_get_slot_zappl_zsymb( tom.engine.adt.zenon.types.ZTerm t) {  return  t.getZsymb() ;}private static  tom.engine.adt.zenon.types.ZTermList tom_get_slot_zappl_termlist( tom.engine.adt.zenon.types.ZTerm t) {  return  t.getTermlist() ;}private static boolean tom_is_fun_sym_zvar( tom.engine.adt.zenon.types.ZTerm t) {  return  (t!= null) && t.isZvar() ;}private static  String  tom_get_slot_zvar_varname( tom.engine.adt.zenon.types.ZTerm t) {  return  t.getVarname() ;}private static boolean tom_terms_equal_ZSymbol(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_zsymbol( tom.engine.adt.zenon.types.ZSymbol t) {  return  (t!= null) && t.isZsymbol() ;}private static  tom.engine.adt.zenon.types.ZSymbol tom_make_zsymbol( String  t0) { return  tom.engine.adt.zenon.ZenonFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeZSymbol_Zsymbol(t0); }private static  String  tom_get_slot_zsymbol_name( tom.engine.adt.zenon.types.ZSymbol t) {  return  t.getName() ;}  
  // ------------------------------------------------------------

  private Verifier verifier; // is it useful ?
  private TomIlTools tomiltools;

  public ZenonBackend(Verifier verifier) {
    this.verifier = verifier;
    this.tomiltools = new TomIlTools(verifier);
  }

  public String genZSymbol(ZSymbol symbol) {
     if(symbol instanceof  tom.engine.adt.zenon.types.ZSymbol) { { tom.engine.adt.zenon.types.ZSymbol tom_match1_1=(( tom.engine.adt.zenon.types.ZSymbol)symbol); if (tom_is_fun_sym_zsymbol(tom_match1_1) ||  false ) { { String  tom_match1_1_name=tom_get_slot_zsymbol_name(tom_match1_1); { String  tom_name=tom_match1_1_name; if ( true ) {

        // manage builtins
        String symbolName = tomiltools.replaceNumbersByString(tom_name);
        return symbolName+ "_";
       } } } } } }

    return "errorZSymbol";
  }

  public String genZType(ZType type) {
     if(type instanceof  tom.engine.adt.zenon.types.ZType) { { tom.engine.adt.zenon.types.ZType tom_match2_1=(( tom.engine.adt.zenon.types.ZType)type); if (tom_is_fun_sym_ztype(tom_match2_1) ||  false ) { { String  tom_match2_1_tname=tom_get_slot_ztype_tname(tom_match2_1); { String  tom_name=tom_match2_1_tname; if ( true ) {

        return tom_name;
       } } } } } }

    return "errorZType";
  }

  public String genZTerm(ZTerm term) {
     if(term instanceof  tom.engine.adt.zenon.types.ZTerm) { { tom.engine.adt.zenon.types.ZTerm tom_match3_1=(( tom.engine.adt.zenon.types.ZTerm)term); if (tom_is_fun_sym_zvar(tom_match3_1) ||  false ) { { String  tom_match3_1_varname=tom_get_slot_zvar_varname(tom_match3_1); { String  tom_name=tom_match3_1_varname; if ( true ) {
 return tom_name;  } } } } if (tom_is_fun_sym_zappl(tom_match3_1) ||  false ) { { tom.engine.adt.zenon.types.ZSymbol tom_match3_1_zsymb=tom_get_slot_zappl_zsymb(tom_match3_1); { tom.engine.adt.zenon.types.ZTermList tom_match3_1_termlist=tom_get_slot_zappl_termlist(tom_match3_1); if (tom_is_fun_sym_zsymbol(tom_match3_1_zsymb) ||  false ) { { String  tom_match3_1_zsymb_name=tom_get_slot_zsymbol_name(tom_match3_1_zsymb); { String  tom_name=tom_match3_1_zsymb_name; { tom.engine.adt.zenon.types.ZTermList tom_tlist=tom_match3_1_termlist; if ( true ) {
 
        // manage builtins
        String realName = tomiltools.replaceNumbersByString(tom_name);
        return "(" + realName +" "+genZTermList(tom_tlist)+")"; 
       } } } } } } } } if (tom_is_fun_sym_zst(tom_match3_1) ||  false ) { { tom.engine.adt.zenon.types.ZTerm tom_match3_1_abst=tom_get_slot_zst_abst(tom_match3_1); { int  tom_match3_1_index=tom_get_slot_zst_index(tom_match3_1); { tom.engine.adt.zenon.types.ZTerm tom_t=tom_match3_1_abst; { int  tom_idx=tom_match3_1_index; if ( true ) {
 
        return "(_"+tom_idx+" "+genZTerm(tom_t)+")";
       } } } } } } if (tom_is_fun_sym_zsl(tom_match3_1) ||  false ) { { tom.engine.adt.zenon.types.ZTerm tom_match3_1_abst=tom_get_slot_zsl_abst(tom_match3_1); { String  tom_match3_1_name=tom_get_slot_zsl_name(tom_match3_1); { tom.engine.adt.zenon.types.ZTerm tom_t=tom_match3_1_abst; { String  tom_slot=tom_match3_1_name; if ( true ) {
 
        return "(_"+tom_slot+" "+genZTerm(tom_t)+")";
       } } } } } } } }

    return "errorZTerm";
  }

  public String genZTermList(ZTermList tl) {
    StringBuffer res = new StringBuffer();
    while (!tl.isEmpty()) {
      res.append(" "+genZTerm(tl.getHead()));
      tl = tl.getTail();
    }
    return res.toString();
  }

  public String genZExpr(ZExpr expr) {
     if(expr instanceof  tom.engine.adt.zenon.types.ZExpr) { { tom.engine.adt.zenon.types.ZExpr tom_match4_1=(( tom.engine.adt.zenon.types.ZExpr)expr); if (tom_is_fun_sym_ztrue(tom_match4_1) ||  false ) { if ( true ) {
 return "True"; } } if (tom_is_fun_sym_zfalse(tom_match4_1) ||  false ) { if ( true ) {
 return "False"; } } if (tom_is_fun_sym_zisfsym(tom_match4_1) ||  false ) { { tom.engine.adt.zenon.types.ZTerm tom_match4_1_t=tom_get_slot_zisfsym_t(tom_match4_1); { tom.engine.adt.zenon.types.ZSymbol tom_match4_1_symbol=tom_get_slot_zisfsym_symbol(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm tom_t=tom_match4_1_t; { tom.engine.adt.zenon.types.ZSymbol tom_s=tom_match4_1_symbol; if ( true ) {

        return "((symb "+genZTerm(tom_t)+") = "+genZSymbol(tom_s)+")";
       } } } } } } if (tom_is_fun_sym_zeq(tom_match4_1) ||  false ) { { tom.engine.adt.zenon.types.ZTerm tom_match4_1_lt=tom_get_slot_zeq_lt(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm tom_match4_1_rt=tom_get_slot_zeq_rt(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm tom_l=tom_match4_1_lt; { tom.engine.adt.zenon.types.ZTerm tom_r=tom_match4_1_rt; if ( true ) {

        return "("+genZTerm(tom_l)+" = "+genZTerm(tom_r)+")";
       } } } } } } if (tom_is_fun_sym_zforall(tom_match4_1) ||  false ) { { tom.engine.adt.zenon.types.ZTerm tom_match4_1_var=tom_get_slot_zforall_var(tom_match4_1); { tom.engine.adt.zenon.types.ZType tom_match4_1_aztype=tom_get_slot_zforall_aztype(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_match4_1_expr=tom_get_slot_zforall_expr(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm tom_v=tom_match4_1_var; { tom.engine.adt.zenon.types.ZType tom_type=tom_match4_1_aztype; { tom.engine.adt.zenon.types.ZExpr tom_e=tom_match4_1_expr; if ( true ) {

        return "forall "+genZTerm(tom_v)+" : "+genZType(tom_type)+",\n "+genZExpr(tom_e);
       } } } } } } } } if (tom_is_fun_sym_zexists(tom_match4_1) ||  false ) { { tom.engine.adt.zenon.types.ZTerm tom_match4_1_var=tom_get_slot_zexists_var(tom_match4_1); { tom.engine.adt.zenon.types.ZType tom_match4_1_aztype=tom_get_slot_zexists_aztype(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_match4_1_expr=tom_get_slot_zexists_expr(tom_match4_1); { tom.engine.adt.zenon.types.ZTerm tom_v=tom_match4_1_var; { tom.engine.adt.zenon.types.ZType tom_type=tom_match4_1_aztype; { tom.engine.adt.zenon.types.ZExpr tom_e=tom_match4_1_expr; if ( true ) {

        return "exists "+genZTerm(tom_v)+" : "+genZType(tom_type)+", "+genZExpr(tom_e);
       } } } } } } } } if (tom_is_fun_sym_zand(tom_match4_1) ||  false ) { { tom.engine.adt.zenon.types.ZExpr tom_match4_1_lte=tom_get_slot_zand_lte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_match4_1_rte=tom_get_slot_zand_rte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_l=tom_match4_1_lte; { tom.engine.adt.zenon.types.ZExpr tom_r=tom_match4_1_rte; if ( true ) {

        if(tom_l== tom_make_ztrue()) {
          return "("+ genZExpr(tom_r) +")";
        }
        else if (tom_r== tom_make_ztrue()) {
          return "("+ genZExpr(tom_l) +")";
        }
        return "("+genZExpr(tom_l)+") /\\ ("+genZExpr(tom_r)+")";
       } } } } } } if (tom_is_fun_sym_zor(tom_match4_1) ||  false ) { { tom.engine.adt.zenon.types.ZExpr tom_match4_1_lte=tom_get_slot_zor_lte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_match4_1_rte=tom_get_slot_zor_rte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_l=tom_match4_1_lte; { tom.engine.adt.zenon.types.ZExpr tom_r=tom_match4_1_rte; if ( true ) {

        if(tom_l== tom_make_zfalse()) {
          return "("+ genZExpr(tom_r) +")";
        }
        else if (tom_r== tom_make_zfalse()) {
          return "("+ genZExpr(tom_l) +")";
        }
        return "("+genZExpr(tom_l)+") \\/ ("+genZExpr(tom_r)+")";
       } } } } } } if (tom_is_fun_sym_znot(tom_match4_1) ||  false ) { { tom.engine.adt.zenon.types.ZExpr tom_match4_1_nex=tom_get_slot_znot_nex(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_e=tom_match4_1_nex; if ( true ) {
 return "~("+genZExpr(tom_e)+")";  } } } } if (tom_is_fun_sym_zequiv(tom_match4_1) ||  false ) { { tom.engine.adt.zenon.types.ZExpr tom_match4_1_lte=tom_get_slot_zequiv_lte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_match4_1_rte=tom_get_slot_zequiv_rte(tom_match4_1); { tom.engine.adt.zenon.types.ZExpr tom_l=tom_match4_1_lte; { tom.engine.adt.zenon.types.ZExpr tom_r=tom_match4_1_rte; if ( true ) {

        return "("+genZExpr(tom_l)+") <-> ("+genZExpr(tom_r)+")";
       } } } } } } } }

    return "errorZExpr";
  }

  public String genZAxiom(ZAxiom axiom) {
     if(axiom instanceof  tom.engine.adt.zenon.types.ZAxiom) { { tom.engine.adt.zenon.types.ZAxiom tom_match5_1=(( tom.engine.adt.zenon.types.ZAxiom)axiom); if (tom_is_fun_sym_zaxiom(tom_match5_1) ||  false ) { { String  tom_match5_1_name=tom_get_slot_zaxiom_name(tom_match5_1); { tom.engine.adt.zenon.types.ZExpr tom_match5_1_ax=tom_get_slot_zaxiom_ax(tom_match5_1); { String  tom_name=tom_match5_1_name; { tom.engine.adt.zenon.types.ZExpr tom_ax=tom_match5_1_ax; if ( true ) {

        // manage builtins
        String realName = tomiltools.replaceNumbersByString(tom_name);
        return "Parameter " + realName +" :\n    " + genZExpr(tom_ax) + ".\n";
       } } } } } } } }

    return "errorZAxiom";
  }

  public String genZAxiomList(ZAxiomList axlist) {
    StringBuffer res = new StringBuffer();
    while (!axlist.isEmpty()) {
      res.append(genZAxiom(axlist.getHead()));
      axlist = axlist.getTail();
    }
    return res.toString();
  }

  public String genZSpec(ZSpec spec) {
     if(spec instanceof  tom.engine.adt.zenon.types.ZSpec) { { tom.engine.adt.zenon.types.ZSpec tom_match6_1=(( tom.engine.adt.zenon.types.ZSpec)spec); if (tom_is_fun_sym_zthm(tom_match6_1) ||  false ) { { tom.engine.adt.zenon.types.ZExpr tom_match6_1_thm=tom_get_slot_zthm_thm(tom_match6_1); { tom.engine.adt.zenon.types.ZAxiomList tom_match6_1_by=tom_get_slot_zthm_by(tom_match6_1); { tom.engine.adt.zenon.types.ZExpr tom_thm=tom_match6_1_thm; { tom.engine.adt.zenon.types.ZAxiomList tom_by=tom_match6_1_by; if ( true ) {

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
    while (!axioms.isEmpty()) {
      out.append(genZAxiom(axioms.getHead()));
      axioms = axioms.getTail();
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
