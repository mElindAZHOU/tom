/* Generated by TOM (version 2.0rc4): Do not edit this file *//*
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import jtom.runtime.Collect1;
import jtom.runtime.GenericTraversal;
import jtom.adt.set.*;
import jtom.adt.set.types.*;
import aterm.*;

public abstract class ATermSet implements Collection {

    // The internal representation of Shared Sets are trees defined by Jean Goubault 
  protected JGTreeSet tree = null;
    // depth of tree allows to represent set with 2^depth elements
  protected int depth = 31;
    // Count how many elements are in the set
  protected int count = 0;
    // Modification counter to detect issues in iteration operation
  protected int modCount = 0;
  protected static GenericTraversal traversal = new GenericTraversal();

  static protected JGTreeSet emptyTree;
  static protected Factory factory = null;
  static protected int collisions = 0;
  static final protected int[] mask =
  { 1 << 0, 
    1 << 1,
    1 << 2,
    1 << 3,
    1 << 4,
    1 << 5,
    1 << 6,
    1 << 7,
    1 << 8,
    1 << 9,
    1 << 10,
    1 << 11,
    1 << 12,
    1 << 13,
    1 << 14,
    1 << 15,
    1 << 16,
    1 << 17,
    1 << 18,
    1 << 19,
    1 << 20,
    1 << 21,
    1 << 22,
    1 << 23,
    1 << 24,
    1 << 25,
    1 << 26,
    1 << 27,
    1 << 28,
    1 << 29,
    1 << 30,
    1 << 31
  };

  /* Generated by TOM (version 2.0rc4): Do not edit this file *//* Generated by TOM (version 2.0rc4): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.0rc4): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  public  char  tom_get_fun_sym_char( char  t) { return  t ; }public boolean tom_cmp_fun_sym_char( char  s1,  char  s2) { return  (s1==s2) ; }public Object tom_get_subterm_char( char  t,  int  n) { return  null ; }public boolean tom_terms_equal_char( char  t1,  char  t2) { return  (t1==t2) ; }public Object tom_get_fun_sym_Character( Character  t) { return  t ; }public boolean tom_cmp_fun_sym_Character(Object s1, Object s2) { return  (s1.equals(s2)) ; }public Object tom_get_subterm_Character( Character  t,  int  n) { return  null ; }public boolean tom_terms_equal_Character(Object t1, Object t2) { return  (t1.equals(t2)) ; }public boolean tom_is_fun_sym_Char( Character  t) { return  (t!= null) && (t instanceof Character) ; }public  Character  tom_make_Char( char  c) { return  new Character(c) ; }public  char  tom_get_slot_Char_c( Character  t) { return  t.charValue() ; } public  String  tom_get_fun_sym_String( String  t) { return  t ; }public boolean tom_cmp_fun_sym_String( String  s1,  String  s2) { return  (s1.equals(s2)) ; }public boolean tom_terms_equal_String( String  t1,  String  t2) { return  (t1.equals(t2)) ; }public  char  tom_get_head_String( String  s) { return  s.charAt(0) ; }public  String  tom_get_tail_String( String  s) { return  s.substring(1) ; }public boolean tom_is_empty_String( String  s) { return  (s.length()==0) ; }public boolean tom_is_fun_sym_concString( String  t) { return  (t!= null) && (t instanceof String) ; }public  String  tom_empty_list_concString() { return  "" ; }public  String  tom_cons_list_concString( char  c,  String  s) { return  (c+s) ; }public  String  tom_append_list_concString( String  l1,  String  l2) {    if(tom_is_empty_String(l1)) {     return l2;    } else if(tom_is_empty_String(l2)) {     return l1;    } else if(tom_is_empty_String(( String )tom_get_tail_String(l1))) {     return ( String )tom_cons_list_concString(( char )tom_get_head_String(l1),l2);    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_String(l1),tom_append_list_concString(( String )tom_get_tail_String(l1),l2));    }   }  public  String  tom_get_slice_concString( String  begin,  String  end) {    if(tom_terms_equal_String(begin,end)) {      return ( String )tom_empty_list_concString();    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_String(begin),( String )tom_get_slice_concString(( String )tom_get_tail_String(begin),end));    }   }    /*  * old definition of String %typeterm String {   implement           { String }   get_fun_sym(t)      { t }   cmp_fun_sym(s1,s2)  { s1.equals(s2) }   get_subterm(t, n)   { null }   equals(t1,t2)       { t1.equals(t2) } } */ /* Generated by TOM (version 2.0rc4): Do not edit this file *//*  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */  public  int  tom_get_fun_sym_int( int  t) { return  t ; }public boolean tom_cmp_fun_sym_int( int  s1,  int  s2) { return  (s1==s2) ; }public Object tom_get_subterm_int( int  t,  int  n) { return  null ; }public boolean tom_terms_equal_int( int  t1,  int  t2) { return  (t1==t2) ; } /* Generated by TOM (version 2.0rc4): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  public  double  tom_get_fun_sym_double( double  t) { return  t ; }public boolean tom_cmp_fun_sym_double( double  s1,  double  s2) { return  (s1==s2) ; }public Object tom_get_subterm_double( double  t,  int  n) { return  null ; }public boolean tom_terms_equal_double( double  t1,  double  t2) { return  (t1==t2) ; } public Object tom_get_fun_sym_ATerm( aterm.ATerm t) { return ((t instanceof ATermAppl)?((ATermAppl)t).getAFun():null); }public boolean tom_cmp_fun_sym_ATerm(Object s1, Object s2) { return  s1==s2; }public Object tom_get_subterm_ATerm( aterm.ATerm t,  int  n) { return (((ATermAppl)t).getArgument(n)); }public boolean tom_terms_equal_ATerm(Object t1, Object t2) { return t1.equals(t2); }public Object tom_get_fun_sym_ATermList( aterm.ATermList t) { return ((t instanceof ATermList)?getSetFactory().getPureFactory().makeAFun("conc",1,false):null); }public boolean tom_cmp_fun_sym_ATermList(Object s1, Object s2) { return  s1==s2; }public boolean tom_terms_equal_ATermList(Object t1, Object t2) { return t1.equals(t2); }public Object tom_get_head_ATermList( aterm.ATermList l) { return l.getFirst(); }public  aterm.ATermList tom_get_tail_ATermList( aterm.ATermList l) { return l.getNext(); }public boolean tom_is_empty_ATermList( aterm.ATermList l) { return l.isEmpty(); }public Object tom_get_fun_sym_JGTreeSet( jtom.adt.set.types.JGTreeSet t) { return null; }public boolean tom_cmp_fun_sym_JGTreeSet(Object s1, Object s2) { return  false; }public Object tom_get_subterm_JGTreeSet( jtom.adt.set.types.JGTreeSet t,  int  n) { return null; }public boolean tom_terms_equal_JGTreeSet(Object t1, Object t2) { return t1.equals(t2); }public boolean tom_is_fun_sym_emptySet( jtom.adt.set.types.JGTreeSet t) { return  (t!= null) && t.isEmptySet(); }public  jtom.adt.set.types.JGTreeSet tom_make_emptySet() { return  getSetFactory().makeJGTreeSet_EmptySet(); }public boolean tom_is_fun_sym_singleton( jtom.adt.set.types.JGTreeSet t) { return  (t!= null) && t.isSingleton(); }public  jtom.adt.set.types.JGTreeSet tom_make_singleton( aterm.ATerm t0) { return  getSetFactory().makeJGTreeSet_Singleton(t0); }public  aterm.ATerm tom_get_slot_singleton_value( jtom.adt.set.types.JGTreeSet t) { return  t.getValue(); }public boolean tom_is_fun_sym_pair( jtom.adt.set.types.JGTreeSet t) { return  (t!= null) && t.isPair(); }public  jtom.adt.set.types.JGTreeSet tom_make_pair( aterm.ATerm t0,  int  t1) { return  getSetFactory().makeJGTreeSet_Pair(t0, t1); }public  aterm.ATerm tom_get_slot_pair_value( jtom.adt.set.types.JGTreeSet t) { return  t.getValue(); }public  int  tom_get_slot_pair_multiplicity( jtom.adt.set.types.JGTreeSet t) { return  t.getMultiplicity(); }public boolean tom_is_fun_sym_branch( jtom.adt.set.types.JGTreeSet t) { return  (t!= null) && t.isBranch(); }public  jtom.adt.set.types.JGTreeSet tom_make_branch( jtom.adt.set.types.JGTreeSet t0,  jtom.adt.set.types.JGTreeSet t1) { return  getSetFactory().makeJGTreeSet_Branch(t0, t1); }public  jtom.adt.set.types.JGTreeSet tom_get_slot_branch_left( jtom.adt.set.types.JGTreeSet t) { return  t.getLeft(); }public  jtom.adt.set.types.JGTreeSet tom_get_slot_branch_right( jtom.adt.set.types.JGTreeSet t) { return  t.getRight(); }  

  public int hashCode() {
    return tree.getUniqueIdentifier();
  }
  
  protected Factory getSetFactory() { 
    return factory;
  }
  
  protected JGTreeSet makeEmptySet() {
    return emptyTree;
  }
  
  protected JGTreeSet makeBranch(JGTreeSet left, JGTreeSet right) {
    return getSetFactory().makeJGTreeSet_Branch(left, right);
  }
  
  protected JGTreeSet getTreeSet() {
    return tree;
  }

  public String toString() {
    return tree.toString();
  }
  
    // High level interface
  public boolean equals(Object set) {
    if (set instanceof ATermSet) {
      return (tree == ((ATermSet)set).getTreeSet());
    } else {
      return false;
    }
  }

  public void clear() {
    tree = makeEmptySet();
    count = 0;
  }
  
  public boolean isEmpty() {
    return tree.isEmptySet();
  }

  public boolean contains(Object o) {
    if (o instanceof ATerm)
      return containsATerm((ATerm)o);
    else
      throw new RuntimeException("Not an ATerm");
  }

  public boolean containsATerm(ATerm elt) {
    return contains(elt, tree, 0);
  }
  
  public boolean containsAll(Collection c) {
    Iterator it = c.iterator();
    Object o = null;
    while(it.hasNext()) {
      if (!contains((ATerm)it.next())) {
        return false;
      }
    }
    return true;    
  }
  
  public abstract Object[] toArray();
  
  public abstract Object[] toArray(Object[] o);
  
  public boolean add(Object o) {
    if (o instanceof ATerm)
      return addATerm((ATerm)o);
    else
      throw new RuntimeException("Not an ATerm");
  }
  
  private boolean addATerm(ATerm elt) {
    JGTreeSet before = tree;
    tree = override(elt, 1, tree, 0);
    modCount++;
    return tree == before;
  }
  
  public boolean addAll(Collection c) {
    JGTreeSet before = tree;
    Iterator it = c.iterator();
    while(it.hasNext()) {
      add(it.next());
    }
    return tree != before;
  }
  
  public boolean remove(Object o) {
    if (o instanceof ATerm)
      return removeATerm((ATerm)o);
    else
      throw new RuntimeException("Not an ATerm");
  }
  
  private boolean removeATerm(ATerm elt) {
    JGTreeSet c = tree;
    tree = remove(elt, tree, 0);
    modCount++;
    return tree == c;
  }

  public boolean removeAll(Collection c) {
    JGTreeSet before = tree;
    Iterator it = c.iterator();
    while(it.hasNext()) {
      remove(it.next());
    }
    modCount++;
    return tree ==before;
  }
  
  public boolean retainAll(Collection c) {
    return true;
  }

  public int size() {
    return size(tree);
  }
  
    // getHead return the first left inner element found
  public ATerm getHead() {
    return getHead(tree);
  }
  
  public String topRepartition() {
    return topRepartition(tree);
  }

    // Low interface using JGTreeSet classes
  protected boolean isEmpty(JGTreeSet set) {
    return set.isEmptySet();
  }
  
  protected boolean contains(ATerm elt, JGTreeSet t) {
    return contains(elt, t, 0);
  }
  protected JGTreeSet add(ATerm elt, JGTreeSet t) {
    return override(elt, 1, t, 0);
  }
  
  protected JGTreeSet remove(ATerm elt, JGTreeSet t) {
    return remove(elt, t, 0);
  }
  
  protected abstract int size(JGTreeSet t);
 
      // getHead return the first left inner element found
  protected abstract ATerm getHead(JGTreeSet t);

  protected JGTreeSet getTail(JGTreeSet t) {
    return remove(getHead(t), t);
  }
  
  protected JGTreeSet union(JGTreeSet t1, JGTreeSet t2) {
    return union(t1, t2, 0);
  }

  protected JGTreeSet intersection(JGTreeSet t1, JGTreeSet t2) {
    JGTreeSet result = intersection(t1, t2, 0);
    return result;
      //return reworkJGTreeSet(result);
  }
  
  protected String topRepartition(JGTreeSet t) {
     { jtom.adt.set.types.JGTreeSet tom_match1_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_branch(tom_match1_1) ||  false ) { { jtom.adt.set.types.JGTreeSet tom_match1_1_1=tom_get_slot_branch_left(tom_match1_1); { jtom.adt.set.types.JGTreeSet tom_match1_1_2=tom_get_slot_branch_right(tom_match1_1); { jtom.adt.set.types.JGTreeSet l=tom_match1_1_1; { jtom.adt.set.types.JGTreeSet r=tom_match1_1_2;
 return "Left branch: "+size(l)+"\tright branch: "+size(r);}}}} }
return "topRepartition: No a branch";}}

  }

  
  /* Simple binary operation skeleton
 private JGTreeSet f(JGTreeSet m1, JGTreeSet m2) {
   %match(JGTreeSet m1, JGTreeSet m2) {
      emptySet(), x -> {
        return f2(m2);
      }
      x, emptySet() -> {
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

  protected abstract JGTreeSet reworkJGTreeSet(JGTreeSet t);
  
  protected abstract JGTreeSet union(JGTreeSet m1, JGTreeSet m2, int level);
  
  protected abstract JGTreeSet intersection(JGTreeSet m1, JGTreeSet m2, int level);
  
  protected abstract JGTreeSet restriction(JGTreeSet m1, JGTreeSet m2, int level);

  protected abstract JGTreeSet remove(ATerm elt, JGTreeSet t, int level);

  protected abstract boolean contains(ATerm elt, JGTreeSet t, int level);
    
  protected abstract JGTreeSet override(ATerm elt, int multiplicity, JGTreeSet t, int level);
  
  protected abstract JGTreeSet underride(ATerm elt, JGTreeSet t, int level);

  protected boolean isBitZero(ATerm elt, int position) {
    return ( (elt.getUniqueIdentifier() & mask[position]) == 0);
  }
  
  protected boolean isBitOne(ATerm elt, int position) {
    return ( (elt.getUniqueIdentifier() & mask[position]) > 0);
  }



     // Iterator interface
  public Iterator iterator() {
    if(tree.isEmptySet())
      return new EmptySetIterator();
    return new SetIterator();
  }
  
  protected static class EmptySetIterator implements Iterator {
    public boolean hasNext() {
      return false;
    }
    public Object next() {
      throw new NoSuchElementException();
    }
    public void remove() {
      throw new IllegalStateException();
    }
  } // Class EmptySetIterator
  
  protected class SetIterator implements Iterator {
    
    ATerm[] table = createTableFromTree(ATermSet.this.tree);
    int index = table.length;
    ATerm entry = null;
    ATerm lastReturned = null;
    
      /**
       * The modCount value that the iterator believes that the backing
       * List should have.  If this expectation is violated, the iterator
       * has detected concurrent modification.
       */
    private int expectedModCount = modCount;
    
    private ATerm[] createTableFromTree(JGTreeSet tree) {
      final Collection res = new ArrayList();
      Collect1 collect = new Collect1() {
          public boolean apply(ATerm t) {
            if(t instanceof JGTreeSet) {
               { jtom.adt.set.types.JGTreeSet tom_match2_1=(( jtom.adt.set.types.JGTreeSet)t);{ if(tom_is_fun_sym_emptySet(tom_match2_1) ||  false ) {
return false; } if(tom_is_fun_sym_singleton(tom_match2_1) ||  false ) { { aterm.ATerm tom_match2_1_1=tom_get_slot_singleton_value(tom_match2_1); { aterm.ATerm x=tom_match2_1_1;

                  res.add(x);
                  return false;
                }} } if(tom_is_fun_sym_pair(tom_match2_1) ||  false ) { { aterm.ATerm tom_match2_1_1=tom_get_slot_pair_value(tom_match2_1); { aterm.ATerm x=tom_match2_1_1;

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

    public boolean hasNext() {
      ATerm e = entry;
      int i = index;
      ATerm t[] = table;
        /* Use locals for faster loop iteration */
      while (e == null && i > 0)
        e = t[--i];
      entry = e;
      index = i;
      return e != null;
    }
    
    public Object next() {
      if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
      
      ATerm et = entry;
      int i = index;
      ATerm t[] = table;
      
        /* Use locals for faster loop iteration */
      while (et == null && i > 0) 
        et = t[--i];
      
      entry = et;
      index = i;
      if (et != null) {
        ATerm res = lastReturned = entry;
        entry = null;
        return res;
      }
      throw new NoSuchElementException();
    }
    
    public void remove() {
      throw new RuntimeException("not yet implemented!");
    }
  } // Class SetIterator
  
} // Class ATermSet
