/* Generated by TOM (version 2.0): Do not edit this file *//*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2004, Pierre-Etienne Moreau
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 *  - Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.  
 *  - Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  - Neither the name of the INRIA nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * INRIA, Nancy, France 
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/

package jtom.runtime.set;

import java.util.*;

import aterm.*;
import aterm.pure.PureFactory;

import jtom.adt.set.*;
import jtom.adt.set.types.*;

import jtom.runtime.Replace1;
import jtom.runtime.Collect1;
 
public class SharedMultiSet extends ATermSet {

  /* Generated by TOM (version 2.0): Do not edit this file *//* Generated by TOM (version 2.0): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.0): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  public  char  tom_get_fun_sym_char( char  t) { return  t ; }public boolean tom_cmp_fun_sym_char( char  s1,  char  s2) { return  (s1==s2) ; }public Object tom_get_subterm_char( char  t,  int  n) { return  null ; }public boolean tom_terms_equal_char( char  t1,  char  t2) { return  (t1==t2) ; }public Object tom_get_fun_sym_Character( Character  t) { return  t ; }public boolean tom_cmp_fun_sym_Character(Object s1, Object s2) { return  (s1.equals(s2)) ; }public Object tom_get_subterm_Character( Character  t,  int  n) { return  null ; }public boolean tom_terms_equal_Character(Object t1, Object t2) { return  (t1.equals(t2)) ; }public boolean tom_is_fun_sym_Char( Character  t) { return  (t!= null) && (t instanceof Character) ; }public  Character  tom_make_Char( char  c) { return  new Character(c) ; }public  char  tom_get_slot_Char_c( Character  t) { return  t.charValue() ; } public  String  tom_get_fun_sym_String( String  t) { return  t ; }public boolean tom_cmp_fun_sym_String( String  s1,  String  s2) { return  (s1.equals(s2)) ; }public boolean tom_terms_equal_String( String  t1,  String  t2) { return  (t1.equals(t2)) ; }public  char  tom_get_head_String( String  s) { return  s.charAt(0) ; }public  String  tom_get_tail_String( String  s) { return  s.substring(1) ; }public boolean tom_is_empty_String( String  s) { return  (s.length()==0) ; }public boolean tom_is_fun_sym_concString( String  t) { return  (t!= null) && (t instanceof String) ; }public  String  tom_empty_list_concString() { return  "" ; }public  String  tom_cons_list_concString( char  c,  String  s) { return  (c+s) ; }public  String  tom_append_list_concString( String  l1,  String  l2) {    if(tom_is_empty_String(l1)) {     return l2;    } else if(tom_is_empty_String(l2)) {     return l1;    } else if(tom_is_empty_String(( String )tom_get_tail_String(l1))) {     return ( String )tom_cons_list_concString(( char )tom_get_head_String(l1),l2);    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_String(l1),tom_append_list_concString(( String )tom_get_tail_String(l1),l2));    }   }  public  String  tom_get_slice_concString( String  begin,  String  end) {    if(tom_terms_equal_String(begin,end)) {      return ( String )tom_empty_list_concString();    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_String(begin),( String )tom_get_slice_concString(( String )tom_get_tail_String(begin),end));    }   }    /*  * old definition of String %typeterm String {   implement           { String }   get_fun_sym(t)      { t }   cmp_fun_sym(s1,s2)  { s1.equals(s2) }   get_subterm(t, n)   { null }   equals(t1,t2)       { t1.equals(t2) } } */ /* Generated by TOM (version 2.0): Do not edit this file *//*  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */  public  int  tom_get_fun_sym_int( int  t) { return  t ; }public boolean tom_cmp_fun_sym_int( int  s1,  int  s2) { return  (s1==s2) ; }public Object tom_get_subterm_int( int  t,  int  n) { return  null ; }public boolean tom_terms_equal_int( int  t1,  int  t2) { return  (t1==t2) ; } /* Generated by TOM (version 2.0): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  public  double  tom_get_fun_sym_double( double  t) { return  t ; }public boolean tom_cmp_fun_sym_double( double  s1,  double  s2) { return  (s1==s2) ; }public Object tom_get_subterm_double( double  t,  int  n) { return  null ; }public boolean tom_terms_equal_double( double  t1,  double  t2) { return  (t1==t2) ; } public Object tom_get_fun_sym_ATerm( aterm.ATerm t) { return ((t instanceof ATermAppl)?((ATermAppl)t).getAFun():null); }public boolean tom_cmp_fun_sym_ATerm(Object s1, Object s2) { return  s1==s2; }public Object tom_get_subterm_ATerm( aterm.ATerm t,  int  n) { return (((ATermAppl)t).getArgument(n)); }public boolean tom_terms_equal_ATerm(Object t1, Object t2) { return t1.equals(t2); }public Object tom_get_fun_sym_ATermList( aterm.ATermList t) { return ((t instanceof ATermList)?getSetFactory().getPureFactory().makeAFun("conc",1,false):null); }public boolean tom_cmp_fun_sym_ATermList(Object s1, Object s2) { return  s1==s2; }public boolean tom_terms_equal_ATermList(Object t1, Object t2) { return t1.equals(t2); }public Object tom_get_head_ATermList( aterm.ATermList l) { return l.getFirst(); }public  aterm.ATermList tom_get_tail_ATermList( aterm.ATermList l) { return l.getNext(); }public boolean tom_is_empty_ATermList( aterm.ATermList l) { return l.isEmpty(); }public Object tom_get_fun_sym_JGTreeSet( jtom.adt.set.types.JGTreeSet t) { return null; }public boolean tom_cmp_fun_sym_JGTreeSet(Object s1, Object s2) { return  false; }public Object tom_get_subterm_JGTreeSet( jtom.adt.set.types.JGTreeSet t,  int  n) { return null; }public boolean tom_terms_equal_JGTreeSet(Object t1, Object t2) { return t1.equals(t2); }public boolean tom_is_fun_sym_emptySet( jtom.adt.set.types.JGTreeSet t) { return  (t!= null) && t.isEmptySet(); }public  jtom.adt.set.types.JGTreeSet tom_make_emptySet() { return  getSetFactory().makeJGTreeSet_EmptySet(); }public boolean tom_is_fun_sym_singleton( jtom.adt.set.types.JGTreeSet t) { return  (t!= null) && t.isSingleton(); }public  jtom.adt.set.types.JGTreeSet tom_make_singleton( aterm.ATerm t0) { return  getSetFactory().makeJGTreeSet_Singleton(t0); }public  aterm.ATerm tom_get_slot_singleton_value( jtom.adt.set.types.JGTreeSet t) { return  t.getValue(); }public boolean tom_is_fun_sym_pair( jtom.adt.set.types.JGTreeSet t) { return  (t!= null) && t.isPair(); }public  jtom.adt.set.types.JGTreeSet tom_make_pair( aterm.ATerm t0,  int  t1) { return  getSetFactory().makeJGTreeSet_Pair(t0, t1); }public  aterm.ATerm tom_get_slot_pair_value( jtom.adt.set.types.JGTreeSet t) { return  t.getValue(); }public  int  tom_get_slot_pair_multiplicity( jtom.adt.set.types.JGTreeSet t) { return  t.getMultiplicity(); }public boolean tom_is_fun_sym_branch( jtom.adt.set.types.JGTreeSet t) { return  (t!= null) && t.isBranch(); }public  jtom.adt.set.types.JGTreeSet tom_make_branch( jtom.adt.set.types.JGTreeSet t0,  jtom.adt.set.types.JGTreeSet t1) { return  getSetFactory().makeJGTreeSet_Branch(t0, t1); }public  jtom.adt.set.types.JGTreeSet tom_get_slot_branch_left( jtom.adt.set.types.JGTreeSet t) { return  t.getLeft(); }public  jtom.adt.set.types.JGTreeSet tom_get_slot_branch_right( jtom.adt.set.types.JGTreeSet t) { return  t.getRight(); }  
  
  public SharedMultiSet(PureFactory pureFactory) {
    if (factory==null) {
      factory = new Factory(pureFactory);
    }
    emptyTree = getSetFactory().makeJGTreeSet_EmptySet();
    this.tree = makeEmptySet();
  }
  
  private SharedMultiSet(Factory fact, JGTreeSet tree, int count) {
    factory = fact;
    this.tree = tree;
    this.count = count;
  }
  
  public Object[] toArray() {
    final Collection res = new ArrayList();
    Collect1 collect = new Collect1() {
        public boolean apply(ATerm t) {
          if(t instanceof JGTreeSet) {
             { jtom.adt.set.types.JGTreeSet tom_match1_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match1_1) ||  false ) {
return false; } if(tom_is_fun_sym_pair(tom_match1_1) ||  false ) { { aterm.ATerm tom_match1_1_1=tom_get_slot_pair_value(tom_match1_1); { aterm.ATerm x=tom_match1_1_1;

                res.add(x);
                return false;
              }} }
return true;}}

          } else {
            return true;
          }
        } // Apply
      }; //new
    
    ATermSet.traversal.genericCollect(tree, collect);
    ATerm[] result = new ATerm[res.size()];
    for(int i=0;i<res.size();i++) {
      result[i] = (ATerm) (((ArrayList)res).get(i));
    }
    return result;
  }
  
  public Object[] toArray(Object[] o) {
    throw new RuntimeException("Not Yet Implemented");
  }
  
  public SharedMultiSet getTail() {
    JGTreeSet set = remove(getHead(tree), tree);
    return new SharedMultiSet(getSetFactory(), set, count-1);
  }
  
  private JGTreeSet makePair(ATerm trm, int i) {
    return getSetFactory().makeJGTreeSet_Pair(trm, i);
  }
  
    // Low interface
  
  protected int size(JGTreeSet t) {
     { jtom.adt.set.types.JGTreeSet tom_match2_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match2_1) ||  false ) {
 return 0;  } if(tom_is_fun_sym_pair(tom_match2_1) ||  false ) { { aterm.ATerm tom_match2_1_1=tom_get_slot_pair_value(tom_match2_1); { aterm.ATerm x=tom_match2_1_1;
 return 1; }} } if(tom_is_fun_sym_branch(tom_match2_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match2_1_1=tom_get_slot_branch_left(tom_match2_1); { jtom.adt.set.types.JGTreeSet tom_match2_1_2=tom_get_slot_branch_right(tom_match2_1); { jtom.adt.set.types.JGTreeSet l=tom_match2_1_1; { jtom.adt.set.types.JGTreeSet r=tom_match2_1_2;
return size(l) + size(r);}}}} }}}

    return 0;
  }
  
  public int multiplicitySize() {
    return multiplicitySize(tree);
  }

  private int multiplicitySize(JGTreeSet t) {
     { jtom.adt.set.types.JGTreeSet tom_match3_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match3_1) ||  false ) {
 return 0;  } if(tom_is_fun_sym_pair(tom_match3_1) ||  false ) { { int  tom_match3_1_2=tom_get_slot_pair_multiplicity(tom_match3_1); { int  m=tom_match3_1_2;

        return m;
      }} } if(tom_is_fun_sym_branch(tom_match3_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match3_1_1=tom_get_slot_branch_left(tom_match3_1); { jtom.adt.set.types.JGTreeSet tom_match3_1_2=tom_get_slot_branch_right(tom_match3_1); { jtom.adt.set.types.JGTreeSet l=tom_match3_1_1; { jtom.adt.set.types.JGTreeSet r=tom_match3_1_2;
return multiplicitySize(l) + multiplicitySize(r);}}}} }}}

    return 0;
  }
      // getHead return the first left inner element found
  protected ATerm getHead(JGTreeSet t) {
     { jtom.adt.set.types.JGTreeSet tom_match4_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match4_1) ||  false ) {

        return null;
       } if(tom_is_fun_sym_pair(tom_match4_1) ||  false ) { { aterm.ATerm tom_match4_1_1=tom_get_slot_pair_value(tom_match4_1); { aterm.ATerm x=tom_match4_1_1;
return x;}} } if(tom_is_fun_sym_branch(tom_match4_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match4_1_1=tom_get_slot_branch_left(tom_match4_1); { jtom.adt.set.types.JGTreeSet tom_match4_1_2=tom_get_slot_branch_right(tom_match4_1); { jtom.adt.set.types.JGTreeSet l=tom_match4_1_1; { jtom.adt.set.types.JGTreeSet r=tom_match4_1_2;

        ATerm left = getHead(l);
        if(left != null) {
          return left;
        }
        return getHead(r);
      }}}} }}}

    return null;
  }
  
  /* Simple binary operation skeleton
 private JGTreeSet f(JGTreeSet m1, JGTreeSet m2) {
   %match(JGTreeSet m1, JGTreeSet m2) {
      emptySet(), x -> {
        return f2(m2);
      }
      x, emptySet -> {
        return f1(m1);
      }
      singleton(y) , x -> {
        return g2(y, m2);
      }
      x, singleton(y) -> {
        return g1(y, m1)
      }
      branch(l1, r1), branch(l2, r2) -> {
        return `branch(f(l1, l2, level+1), f(r1, r2, level+1));
      }
    }
  }*/

  protected JGTreeSet reworkJGTreeSet(JGTreeSet t) {
    Replace1 replace = new Replace1() {
        public ATerm apply(ATerm t) {
           { jtom.adt.set.types.JGTreeSet tom_match5_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match5_1) ||  false ) {
return t; } if(tom_is_fun_sym_pair(tom_match5_1) ||  false ) {
return t; } if(tom_is_fun_sym_branch(tom_match5_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match5_1_1=tom_get_slot_branch_left(tom_match5_1); { jtom.adt.set.types.JGTreeSet tom_match5_1_2=tom_get_slot_branch_right(tom_match5_1); if(tom_is_fun_sym_emptySet(tom_match5_1_1) ||  false ) { if(tom_is_fun_sym_pair(tom_match5_1_2) ||  false ) { { jtom.adt.set.types.JGTreeSet p=tom_match5_1_2;
return p;} } }}} } if(tom_is_fun_sym_branch(tom_match5_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match5_1_1=tom_get_slot_branch_left(tom_match5_1); { jtom.adt.set.types.JGTreeSet tom_match5_1_2=tom_get_slot_branch_right(tom_match5_1); if(tom_is_fun_sym_pair(tom_match5_1_1) ||  false ) { { jtom.adt.set.types.JGTreeSet p=tom_match5_1_1; if(tom_is_fun_sym_emptySet(tom_match5_1_2) ||  false ) {
return p; }} }}} } if(tom_is_fun_sym_branch(tom_match5_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match5_1_1=tom_get_slot_branch_left(tom_match5_1); { jtom.adt.set.types.JGTreeSet tom_match5_1_2=tom_get_slot_branch_right(tom_match5_1); if(tom_is_fun_sym_emptySet(tom_match5_1_1) ||  false ) { { jtom.adt.set.types.JGTreeSet e=tom_match5_1_1; if(tom_is_fun_sym_emptySet(tom_match5_1_2) ||  false ) {
return e; }} }}} } if(tom_is_fun_sym_branch(tom_match5_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match5_1_1=tom_get_slot_branch_left(tom_match5_1); { jtom.adt.set.types.JGTreeSet tom_match5_1_2=tom_get_slot_branch_right(tom_match5_1); { jtom.adt.set.types.JGTreeSet l1=tom_match5_1_1; { jtom.adt.set.types.JGTreeSet l2=tom_match5_1_2;
return tom_make_branch(reworkJGTreeSet(l1), reworkJGTreeSet(l2));}}}} }
 return traversal.genericTraversal(t,this); }}

        }
      };
    
    JGTreeSet res = (JGTreeSet)replace.apply(t);
    if(res != t) {
      res = reworkJGTreeSet(res);
    }
    return res;
  }
  
  protected JGTreeSet union(JGTreeSet m1, JGTreeSet m2, int level) {
     { jtom.adt.set.types.JGTreeSet tom_match6_1=(( jtom.adt.set.types.JGTreeSet)m1);{ { jtom.adt.set.types.JGTreeSet tom_match6_2=(( jtom.adt.set.types.JGTreeSet)m2);{ if(tom_is_fun_sym_emptySet(tom_match6_1) ||  false ) { { jtom.adt.set.types.JGTreeSet x=tom_match6_2;

        return m2;
      } } { jtom.adt.set.types.JGTreeSet x=tom_match6_1; if(tom_is_fun_sym_emptySet(tom_match6_2) ||  false ) {


        return m1;
       }} if(tom_is_fun_sym_pair(tom_match6_1) ||  false ) { { aterm.ATerm tom_match6_1_1=tom_get_slot_pair_value(tom_match6_1); { int  tom_match6_1_2=tom_get_slot_pair_multiplicity(tom_match6_1); { aterm.ATerm elt=tom_match6_1_1; { int  mult=tom_match6_1_2; { jtom.adt.set.types.JGTreeSet x=tom_match6_2;


        return override(elt, mult, x, level);
      }}}}} } { jtom.adt.set.types.JGTreeSet x=tom_match6_1; if(tom_is_fun_sym_pair(tom_match6_2) ||  false ) { { aterm.ATerm tom_match6_2_1=tom_get_slot_pair_value(tom_match6_2); { int  tom_match6_2_2=tom_get_slot_pair_multiplicity(tom_match6_2); { aterm.ATerm elt=tom_match6_2_1; { int  mult=tom_match6_2_2;


          // underride dont worry about multiplicity
        return underride(elt, x, level);
      }}}} }} if(tom_is_fun_sym_branch(tom_match6_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match6_1_1=tom_get_slot_branch_left(tom_match6_1); { jtom.adt.set.types.JGTreeSet tom_match6_1_2=tom_get_slot_branch_right(tom_match6_1); { jtom.adt.set.types.JGTreeSet l1=tom_match6_1_1; { jtom.adt.set.types.JGTreeSet r1=tom_match6_1_2; if(tom_is_fun_sym_branch(tom_match6_2) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match6_2_1=tom_get_slot_branch_left(tom_match6_2); { jtom.adt.set.types.JGTreeSet tom_match6_2_2=tom_get_slot_branch_right(tom_match6_2); { jtom.adt.set.types.JGTreeSet l2=tom_match6_2_1; { jtom.adt.set.types.JGTreeSet r2=tom_match6_2_2;


        int l = level+1;
        return tom_make_branch(union(l1, l2, l), union(r1, r2, l));
      }}}} }}}}} }}}}}

    return null;
  }
  
  protected JGTreeSet intersection(JGTreeSet m1, JGTreeSet m2, int level) {
     { jtom.adt.set.types.JGTreeSet tom_match7_1=(( jtom.adt.set.types.JGTreeSet)m1);{ { jtom.adt.set.types.JGTreeSet tom_match7_2=(( jtom.adt.set.types.JGTreeSet)m2);{ if(tom_is_fun_sym_emptySet(tom_match7_1) ||  false ) { { jtom.adt.set.types.JGTreeSet x=tom_match7_2;

 
        return tom_make_emptySet();
      } } { jtom.adt.set.types.JGTreeSet x=tom_match7_1; if(tom_is_fun_sym_emptySet(tom_match7_2) ||  false ) {          return tom_make_emptySet();        }} if(tom_is_fun_sym_pair(tom_match7_1) ||  false ) { { jtom.adt.set.types.JGTreeSet p=tom_match7_1; { aterm.ATerm tom_match7_1_1=tom_get_slot_pair_value(tom_match7_1); { int  tom_match7_1_2=tom_get_slot_pair_multiplicity(tom_match7_1); { aterm.ATerm elt=tom_match7_1_1; { int  mult=tom_match7_1_2; { jtom.adt.set.types.JGTreeSet x=tom_match7_2;



        if (contains(elt, x, level)) {
            /* Warning: we have to choose one of the 2 element: with the lowest multiplicity*/
          return p;
        } else {
          return tom_make_emptySet();
        }
      }}}}}} } { jtom.adt.set.types.JGTreeSet x=tom_match7_1; if(tom_is_fun_sym_pair(tom_match7_2) ||  false ) { { jtom.adt.set.types.JGTreeSet p=tom_match7_2; { aterm.ATerm tom_match7_2_1=tom_get_slot_pair_value(tom_match7_2); { int  tom_match7_2_2=tom_get_slot_pair_multiplicity(tom_match7_2); { aterm.ATerm elt=tom_match7_2_1; { int  mult=tom_match7_2_2;         if (contains(elt, x, level)) {             /* Warning: we have to choose one of the 2 element: with the lowest multiplicity*/           return p;         } else {           return tom_make_emptySet();         }       }}}}} }} if(tom_is_fun_sym_branch(tom_match7_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match7_1_1=tom_get_slot_branch_left(tom_match7_1); { jtom.adt.set.types.JGTreeSet tom_match7_1_2=tom_get_slot_branch_right(tom_match7_1); { jtom.adt.set.types.JGTreeSet l1=tom_match7_1_1; { jtom.adt.set.types.JGTreeSet r1=tom_match7_1_2; if(tom_is_fun_sym_branch(tom_match7_2) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match7_2_1=tom_get_slot_branch_left(tom_match7_2); { jtom.adt.set.types.JGTreeSet tom_match7_2_2=tom_get_slot_branch_right(tom_match7_2); { jtom.adt.set.types.JGTreeSet l2=tom_match7_2_1; { jtom.adt.set.types.JGTreeSet r2=tom_match7_2_2;


        int l = level+1;
        return tom_make_branch(intersection(l1, l2, l), intersection(r1, r2, l));        
      }}}} }}}}} }}}}}

    return null;
  }
  
  protected JGTreeSet restriction(JGTreeSet m1, JGTreeSet m2, int level) {
     { jtom.adt.set.types.JGTreeSet tom_match8_1=(( jtom.adt.set.types.JGTreeSet)m1);{ { jtom.adt.set.types.JGTreeSet tom_match8_2=(( jtom.adt.set.types.JGTreeSet)m2);{ if(tom_is_fun_sym_emptySet(tom_match8_1) ||  false ) { { jtom.adt.set.types.JGTreeSet x=tom_match8_2;

 
        return tom_make_emptySet();
      } } { jtom.adt.set.types.JGTreeSet x=tom_match8_1; if(tom_is_fun_sym_emptySet(tom_match8_2) ||  false ) {          return tom_make_emptySet();        }} if(tom_is_fun_sym_pair(tom_match8_1) ||  false ) { { aterm.ATerm tom_match8_1_1=tom_get_slot_pair_value(tom_match8_1); { aterm.ATerm y=tom_match8_1_1; { jtom.adt.set.types.JGTreeSet x=tom_match8_2;


        return remove(y, x, level);
      }}} } { jtom.adt.set.types.JGTreeSet x=tom_match8_1; if(tom_is_fun_sym_pair(tom_match8_2) ||  false ) { { aterm.ATerm tom_match8_2_1=tom_get_slot_pair_value(tom_match8_2); { aterm.ATerm y=tom_match8_2_1;


        if (contains(y, x)) {
            // Warning: we have to choose one of the 2 element: with the lowest multiplicity
          return m2;
        } else {
          return tom_make_emptySet();
        }
      }} }} if(tom_is_fun_sym_branch(tom_match8_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match8_1_1=tom_get_slot_branch_left(tom_match8_1); { jtom.adt.set.types.JGTreeSet tom_match8_1_2=tom_get_slot_branch_right(tom_match8_1); { jtom.adt.set.types.JGTreeSet l1=tom_match8_1_1; { jtom.adt.set.types.JGTreeSet r1=tom_match8_1_2; if(tom_is_fun_sym_branch(tom_match8_2) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match8_2_1=tom_get_slot_branch_left(tom_match8_2); { jtom.adt.set.types.JGTreeSet tom_match8_2_2=tom_get_slot_branch_right(tom_match8_2); { jtom.adt.set.types.JGTreeSet l2=tom_match8_2_1; { jtom.adt.set.types.JGTreeSet r2=tom_match8_2_2;


        int l = level+1;
        return tom_make_branch(restriction(l1, l2, l), restriction(r1, r2, l));
      }}}} }}}}} }}}}}

    return null;
  }
  
  protected JGTreeSet remove(ATerm elt, JGTreeSet t, int level) {
     { jtom.adt.set.types.JGTreeSet tom_match9_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match9_1) ||  false ) {
return t; } if(tom_is_fun_sym_pair(tom_match9_1) ||  false ) { { aterm.ATerm tom_match9_1_1=tom_get_slot_pair_value(tom_match9_1); { aterm.ATerm x=tom_match9_1_1;


        if (x == elt) {return tom_make_emptySet();}
        else {return t;}
      }} } if(tom_is_fun_sym_branch(tom_match9_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match9_1_1=tom_get_slot_branch_left(tom_match9_1); { jtom.adt.set.types.JGTreeSet tom_match9_1_2=tom_get_slot_branch_right(tom_match9_1); { jtom.adt.set.types.JGTreeSet l=tom_match9_1_1; { jtom.adt.set.types.JGTreeSet r=tom_match9_1_2;


        JGTreeSet l1 = null, r1=null;
        if( isBitZero(elt, level) ) {
          l1 = remove(elt, l, level+1);
          r1 = r;
        } else {
          l1 = l;
          r1 = remove(elt, r, level+1);
        }
         { jtom.adt.set.types.JGTreeSet tom_match10_1=(( jtom.adt.set.types.JGTreeSet)l1);{ { jtom.adt.set.types.JGTreeSet tom_match10_2=(( jtom.adt.set.types.JGTreeSet)r1);{ if(tom_is_fun_sym_emptySet(tom_match10_1) ||  false ) { if(tom_is_fun_sym_pair(tom_match10_2) ||  false ) {
return r1; } } if(tom_is_fun_sym_pair(tom_match10_1) ||  false ) { if(tom_is_fun_sym_emptySet(tom_match10_2) ||  false ) {
return l1; } }
return tom_make_branch(l1, r1);}}}}

      }}}} }}}

    return null;
  }

  protected boolean contains(ATerm elt, JGTreeSet t, int level) {
     { jtom.adt.set.types.JGTreeSet tom_match11_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match11_1) ||  false ) {
return false; } if(tom_is_fun_sym_pair(tom_match11_1) ||  false ) { { aterm.ATerm tom_match11_1_1=tom_get_slot_pair_value(tom_match11_1); { aterm.ATerm x=tom_match11_1_1;


        if(x == elt) return true;
      }} } if(tom_is_fun_sym_branch(tom_match11_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match11_1_1=tom_get_slot_branch_left(tom_match11_1); { jtom.adt.set.types.JGTreeSet tom_match11_1_2=tom_get_slot_branch_right(tom_match11_1); { jtom.adt.set.types.JGTreeSet l=tom_match11_1_1; { jtom.adt.set.types.JGTreeSet r=tom_match11_1_2;


        if(level == depth) {
          return (contains(elt, l, level) || contains(elt, r, level));
        }
        if( isBitZero(elt, level)) {
          return contains(elt, l, level+1);
        } else {
          return contains(elt, r, level+1);
        }
      }}}} }}}

    return false;
  }
  
  protected JGTreeSet override(ATerm elt, int multiplicity, JGTreeSet t, int level) {
    int lev = level+1;
     { jtom.adt.set.types.JGTreeSet tom_match12_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match12_1) ||  false ) {

        return makePair(elt, multiplicity);
       } if(tom_is_fun_sym_pair(tom_match12_1) ||  false ) { { aterm.ATerm tom_match12_1_1=tom_get_slot_pair_value(tom_match12_1); { int  tom_match12_1_2=tom_get_slot_pair_multiplicity(tom_match12_1); { aterm.ATerm x=tom_match12_1_1; { int  mult=tom_match12_1_2;


        if(x == elt) {  return makePair(elt, mult+multiplicity);}
        else if( level >= depth ) {
          System.out.println("Collision!!!!!!!!");
          collisions++;
            // Create 1rst list of element as it was a branch
          return tom_make_branch(t, tom_make_singleton(elt));
          
        }
        else if ( isBitZero(elt, level) && isBitZero(x, level) )  { return tom_make_branch(override(elt, multiplicity, t, lev), tom_make_emptySet());}
        else if ( isBitOne(elt, level)  && isBitOne(x, level) )   { return tom_make_branch(tom_make_emptySet(), override(elt, multiplicity, t, lev));}
        else if ( isBitZero(elt, level) && isBitOne(x, level) ) { return tom_make_branch(makePair(elt, multiplicity), t);}
        else if ( isBitOne(elt, level)  && isBitZero(x, level) ){ return tom_make_branch(t, makePair(elt, multiplicity));}
      }}}} } if(tom_is_fun_sym_branch(tom_match12_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match12_1_1=tom_get_slot_branch_left(tom_match12_1); { jtom.adt.set.types.JGTreeSet tom_match12_1_2=tom_get_slot_branch_right(tom_match12_1); { jtom.adt.set.types.JGTreeSet l=tom_match12_1_1; { jtom.adt.set.types.JGTreeSet r=tom_match12_1_2;


        if(level >= depth) {
          System.out.println("Collision!!!!!!!!");
          collisions++;
            //continue list of element
          return tom_make_branch(t, tom_make_singleton(elt));
        }
        if (isBitZero(elt, level)) {
          return tom_make_branch(override(elt, multiplicity, l, lev), r);
        } else {
          return tom_make_branch(l, override(elt, multiplicity, r, lev));
        }
      }}}} }}}

    return null;
  }
  
  protected JGTreeSet underride(ATerm elt, JGTreeSet t, int level) {
    int lev = level+1;
     { jtom.adt.set.types.JGTreeSet tom_match13_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match13_1) ||  false ) {
return tom_make_singleton(elt); } if(tom_is_fun_sym_singleton(tom_match13_1) ||  false ) { { aterm.ATerm tom_match13_1_1=tom_get_slot_singleton_value(tom_match13_1); { aterm.ATerm x=tom_match13_1_1;


        if(x == elt) {  return t;}
        else if( level >= depth ) {
          System.out.println("Collision!!!!!!!!");
          collisions++;
            // Create 1rst list of element as it was a branch
          return tom_make_branch(t, tom_make_singleton(elt));
          
        }
        else if ( isBitZero(elt, level) && isBitZero(x, level) )  { return tom_make_branch(underride(elt, t, lev), tom_make_emptySet());}
        else if ( isBitOne(elt, level)  && isBitOne(x, level) )   { return tom_make_branch(tom_make_emptySet(), underride(elt, t, lev));}
        else if ( isBitZero(elt, level) && isBitOne(x, level) ) { return tom_make_branch(tom_make_singleton(elt), t);}
        else if ( isBitOne(elt, level)  && isBitZero(x, level) ){ return tom_make_branch(t, tom_make_singleton(elt));}
      }} } if(tom_is_fun_sym_branch(tom_match13_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match13_1_1=tom_get_slot_branch_left(tom_match13_1); { jtom.adt.set.types.JGTreeSet tom_match13_1_2=tom_get_slot_branch_right(tom_match13_1); { jtom.adt.set.types.JGTreeSet l=tom_match13_1_1; { jtom.adt.set.types.JGTreeSet r=tom_match13_1_2;


        if (isBitZero(elt, level)) {return tom_make_branch(underride(elt, l, lev), r);}
        else {return tom_make_branch(l, underride(elt, r, lev));}
      }}}} }}}

    return null;
  }
  
} //Class SharedMultiSet
