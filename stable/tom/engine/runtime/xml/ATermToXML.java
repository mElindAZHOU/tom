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

package jtom.runtime.xml;

import java.io.*;
import jtom.adt.tnode.*;
import jtom.adt.tnode.types.*;
import aterm.*;
import aterm.pure.*;
import jtom.exception.TomRuntimeException;

public class ATermToXML {
  
  /* Generated by TOM (version 2.0rc4): Do not edit this file *//* Generated by TOM (version 2.0rc4): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.0rc4): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  public  char  tom_get_fun_sym_char( char  t) { return  t ; }public boolean tom_cmp_fun_sym_char( char  s1,  char  s2) { return  (s1==s2) ; }public Object tom_get_subterm_char( char  t,  int  n) { return  null ; }public boolean tom_terms_equal_char( char  t1,  char  t2) { return  (t1==t2) ; }public Object tom_get_fun_sym_Character( Character  t) { return  t ; }public boolean tom_cmp_fun_sym_Character(Object s1, Object s2) { return  (s1.equals(s2)) ; }public Object tom_get_subterm_Character( Character  t,  int  n) { return  null ; }public boolean tom_terms_equal_Character(Object t1, Object t2) { return  (t1.equals(t2)) ; }public boolean tom_is_fun_sym_Char( Character  t) { return  (t!= null) && (t instanceof Character) ; }public  Character  tom_make_Char( char  c) { return  new Character(c) ; }public  char  tom_get_slot_Char_c( Character  t) { return  t.charValue() ; } public  String  tom_get_fun_sym_String( String  t) { return  t ; }public boolean tom_cmp_fun_sym_String( String  s1,  String  s2) { return  (s1.equals(s2)) ; }public boolean tom_terms_equal_String( String  t1,  String  t2) { return  (t1.equals(t2)) ; }public  char  tom_get_head_String( String  s) { return  s.charAt(0) ; }public  String  tom_get_tail_String( String  s) { return  s.substring(1) ; }public boolean tom_is_empty_String( String  s) { return  (s.length()==0) ; }public boolean tom_is_fun_sym_concString( String  t) { return  (t!= null) && (t instanceof String) ; }public  String  tom_empty_list_concString() { return  "" ; }public  String  tom_cons_list_concString( char  c,  String  s) { return  (c+s) ; }public  String  tom_append_list_concString( String  l1,  String  l2) {    if(tom_is_empty_String(l1)) {     return l2;    } else if(tom_is_empty_String(l2)) {     return l1;    } else if(tom_is_empty_String(( String )tom_get_tail_String(l1))) {     return ( String )tom_cons_list_concString(( char )tom_get_head_String(l1),l2);    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_String(l1),tom_append_list_concString(( String )tom_get_tail_String(l1),l2));    }   }  public  String  tom_get_slice_concString( String  begin,  String  end) {    if(tom_terms_equal_String(begin,end)) {      return ( String )tom_empty_list_concString();    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_String(begin),( String )tom_get_slice_concString(( String )tom_get_tail_String(begin),end));    }   }    /*  * old definition of String %typeterm String {   implement           { String }   get_fun_sym(t)      { t }   cmp_fun_sym(s1,s2)  { s1.equals(s2) }   get_subterm(t, n)   { null }   equals(t1,t2)       { t1.equals(t2) } } */ /* Generated by TOM (version 2.0rc4): Do not edit this file *//*  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */  public  int  tom_get_fun_sym_int( int  t) { return  t ; }public boolean tom_cmp_fun_sym_int( int  s1,  int  s2) { return  (s1==s2) ; }public Object tom_get_subterm_int( int  t,  int  n) { return  null ; }public boolean tom_terms_equal_int( int  t1,  int  t2) { return  (t1==t2) ; } /* Generated by TOM (version 2.0rc4): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  public  double  tom_get_fun_sym_double( double  t) { return  t ; }public boolean tom_cmp_fun_sym_double( double  s1,  double  s2) { return  (s1==s2) ; }public Object tom_get_subterm_double( double  t,  int  n) { return  null ; }public boolean tom_terms_equal_double( double  t1,  double  t2) { return  (t1==t2) ; } public Object tom_get_fun_sym_ATerm( aterm.ATerm t) { return ((t instanceof ATermAppl)?((ATermAppl)t).getAFun():null); }public boolean tom_cmp_fun_sym_ATerm(Object s1, Object s2) { return  s1==s2; }public Object tom_get_subterm_ATerm( aterm.ATerm t,  int  n) { return (((ATermAppl)t).getArgument(n)); }public boolean tom_terms_equal_ATerm(Object t1, Object t2) { return t1.equals(t2); }public Object tom_get_fun_sym_ATermList( aterm.ATermList t) { return ((t instanceof ATermList)?getTNodeFactory().getPureFactory().makeAFun("conc",1,false):null); }public boolean tom_cmp_fun_sym_ATermList(Object s1, Object s2) { return  s1==s2; }public boolean tom_terms_equal_ATermList(Object t1, Object t2) { return t1.equals(t2); }public Object tom_get_head_ATermList( aterm.ATermList l) { return l.getFirst(); }public  aterm.ATermList tom_get_tail_ATermList( aterm.ATermList l) { return l.getNext(); }public boolean tom_is_empty_ATermList( aterm.ATermList l) { return l.isEmpty(); }public Object tom_get_fun_sym_TNode( jtom.adt.tnode.types.TNode t) { return null; }public boolean tom_cmp_fun_sym_TNode(Object s1, Object s2) { return  false; }public Object tom_get_subterm_TNode( jtom.adt.tnode.types.TNode t,  int  n) { return null; }public boolean tom_terms_equal_TNode(Object t1, Object t2) { return t1.equals(t2); }public boolean tom_is_fun_sym_CommentNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isCommentNode(); }public  jtom.adt.tnode.types.TNode tom_make_CommentNode( String  t0) { return  getTNodeFactory().makeTNode_CommentNode(t0); }public  String  tom_get_slot_CommentNode_data( jtom.adt.tnode.types.TNode t) { return  t.getData(); }public boolean tom_is_fun_sym_ProcessingInstructionNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isProcessingInstructionNode(); }public  jtom.adt.tnode.types.TNode tom_make_ProcessingInstructionNode( String  t0,  String  t1) { return  getTNodeFactory().makeTNode_ProcessingInstructionNode(t0, t1); }public  String  tom_get_slot_ProcessingInstructionNode_target( jtom.adt.tnode.types.TNode t) { return  t.getTarget(); }public  String  tom_get_slot_ProcessingInstructionNode_data( jtom.adt.tnode.types.TNode t) { return  t.getData(); }public boolean tom_is_fun_sym_TextNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isTextNode(); }public  jtom.adt.tnode.types.TNode tom_make_TextNode( String  t0) { return  getTNodeFactory().makeTNode_TextNode(t0); }public  String  tom_get_slot_TextNode_data( jtom.adt.tnode.types.TNode t) { return  t.getData(); }public boolean tom_is_fun_sym_CDATASectionNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isCDATASectionNode(); }public  jtom.adt.tnode.types.TNode tom_make_CDATASectionNode( String  t0) { return  getTNodeFactory().makeTNode_CDATASectionNode(t0); }public  String  tom_get_slot_CDATASectionNode_data( jtom.adt.tnode.types.TNode t) { return  t.getData(); }public boolean tom_is_fun_sym_DocumentNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isDocumentNode(); }public  jtom.adt.tnode.types.TNode tom_make_DocumentNode( jtom.adt.tnode.types.TNode t0,  jtom.adt.tnode.types.TNode t1) { return  getTNodeFactory().makeTNode_DocumentNode(t0, t1); }public  jtom.adt.tnode.types.TNode tom_get_slot_DocumentNode_docType( jtom.adt.tnode.types.TNode t) { return  t.getDocType(); }public  jtom.adt.tnode.types.TNode tom_get_slot_DocumentNode_docElem( jtom.adt.tnode.types.TNode t) { return  t.getDocElem(); }public boolean tom_is_fun_sym_ElementNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isElementNode(); }public  jtom.adt.tnode.types.TNode tom_make_ElementNode( String  t0,  jtom.adt.tnode.types.TNodeList t1,  jtom.adt.tnode.types.TNodeList t2) { return  getTNodeFactory().makeTNode_ElementNode(t0, t1, t2); }public  String  tom_get_slot_ElementNode_name( jtom.adt.tnode.types.TNode t) { return  t.getName(); }public  jtom.adt.tnode.types.TNodeList tom_get_slot_ElementNode_attrList( jtom.adt.tnode.types.TNode t) { return  t.getAttrList(); }public  jtom.adt.tnode.types.TNodeList tom_get_slot_ElementNode_childList( jtom.adt.tnode.types.TNode t) { return  t.getChildList(); }public boolean tom_is_fun_sym_AttributeNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isAttributeNode(); }public  jtom.adt.tnode.types.TNode tom_make_AttributeNode( String  t0,  String  t1,  String  t2) { return  getTNodeFactory().makeTNode_AttributeNode(t0, t1, t2); }public  String  tom_get_slot_AttributeNode_name( jtom.adt.tnode.types.TNode t) { return  t.getName(); }public  String  tom_get_slot_AttributeNode_specified( jtom.adt.tnode.types.TNode t) { return  t.getSpecified(); }public  String  tom_get_slot_AttributeNode_value( jtom.adt.tnode.types.TNode t) { return  t.getValue(); }public boolean tom_is_fun_sym_DocumentTypeNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isDocumentTypeNode(); }public  jtom.adt.tnode.types.TNode tom_make_DocumentTypeNode( String  t0,  String  t1,  String  t2,  String  t3,  jtom.adt.tnode.types.TNodeList t4,  jtom.adt.tnode.types.TNodeList t5) { return  getTNodeFactory().makeTNode_DocumentTypeNode(t0, t1, t2, t3, t4, t5); }public  String  tom_get_slot_DocumentTypeNode_name( jtom.adt.tnode.types.TNode t) { return  t.getName(); }public  String  tom_get_slot_DocumentTypeNode_publicId( jtom.adt.tnode.types.TNode t) { return  t.getPublicId(); }public  String  tom_get_slot_DocumentTypeNode_systemId( jtom.adt.tnode.types.TNode t) { return  t.getSystemId(); }public  String  tom_get_slot_DocumentTypeNode_internalSubset( jtom.adt.tnode.types.TNode t) { return  t.getInternalSubset(); }public  jtom.adt.tnode.types.TNodeList tom_get_slot_DocumentTypeNode_entities( jtom.adt.tnode.types.TNode t) { return  t.getEntities(); }public  jtom.adt.tnode.types.TNodeList tom_get_slot_DocumentTypeNode_notations( jtom.adt.tnode.types.TNode t) { return  t.getNotations(); }public boolean tom_is_fun_sym_EntityReferenceNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isEntityReferenceNode(); }public  jtom.adt.tnode.types.TNode tom_make_EntityReferenceNode( String  t0,  jtom.adt.tnode.types.TNodeList t1) { return  getTNodeFactory().makeTNode_EntityReferenceNode(t0, t1); }public  String  tom_get_slot_EntityReferenceNode_name( jtom.adt.tnode.types.TNode t) { return  t.getName(); }public  jtom.adt.tnode.types.TNodeList tom_get_slot_EntityReferenceNode_childList( jtom.adt.tnode.types.TNode t) { return  t.getChildList(); }public boolean tom_is_fun_sym_EntityNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isEntityNode(); }public  jtom.adt.tnode.types.TNode tom_make_EntityNode( String  t0,  String  t1,  String  t2) { return  getTNodeFactory().makeTNode_EntityNode(t0, t1, t2); }public  String  tom_get_slot_EntityNode_notationName( jtom.adt.tnode.types.TNode t) { return  t.getNotationName(); }public  String  tom_get_slot_EntityNode_publicId( jtom.adt.tnode.types.TNode t) { return  t.getPublicId(); }public  String  tom_get_slot_EntityNode_systemId( jtom.adt.tnode.types.TNode t) { return  t.getSystemId(); }public boolean tom_is_fun_sym_NotationNode( jtom.adt.tnode.types.TNode t) { return  (t!= null) && t.isNotationNode(); }public  jtom.adt.tnode.types.TNode tom_make_NotationNode( String  t0,  String  t1) { return  getTNodeFactory().makeTNode_NotationNode(t0, t1); }public  String  tom_get_slot_NotationNode_publicId( jtom.adt.tnode.types.TNode t) { return  t.getPublicId(); }public  String  tom_get_slot_NotationNode_systemId( jtom.adt.tnode.types.TNode t) { return  t.getSystemId(); }public Object tom_get_fun_sym_TNodeList( jtom.adt.tnode.types.TNodeList t) { return null; }public boolean tom_cmp_fun_sym_TNodeList(Object s1, Object s2) { return  false; }public boolean tom_terms_equal_TNodeList(Object t1, Object t2) { return t1.equals(t2); }public  jtom.adt.tnode.types.TNode tom_get_head_TNodeList( jtom.adt.tnode.types.TNodeList l) { return l.getHead(); }public  jtom.adt.tnode.types.TNodeList tom_get_tail_TNodeList( jtom.adt.tnode.types.TNodeList l) { return l.getTail(); }public boolean tom_is_empty_TNodeList( jtom.adt.tnode.types.TNodeList l) { return l.isEmpty(); }public boolean tom_is_fun_sym_concTNode( jtom.adt.tnode.types.TNodeList t) { return (t!= null) && t.isSortTNodeList(); }public  jtom.adt.tnode.types.TNodeList tom_empty_list_concTNode() { return getTNodeFactory().makeTNodeList(); }public  jtom.adt.tnode.types.TNodeList tom_cons_list_concTNode( jtom.adt.tnode.types.TNode e,  jtom.adt.tnode.types.TNodeList l) { return getTNodeFactory().makeTNodeList(e,l); }public  jtom.adt.tnode.types.TNodeList tom_append_list_concTNode( jtom.adt.tnode.types.TNodeList l1,  jtom.adt.tnode.types.TNodeList l2) {    if(tom_is_empty_TNodeList(l1)) {     return l2;    } else if(tom_is_empty_TNodeList(l2)) {     return l1;    } else if(tom_is_empty_TNodeList(( jtom.adt.tnode.types.TNodeList)tom_get_tail_TNodeList(l1))) {     return ( jtom.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( jtom.adt.tnode.types.TNode)tom_get_head_TNodeList(l1),l2);    } else {      return ( jtom.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( jtom.adt.tnode.types.TNode)tom_get_head_TNodeList(l1),tom_append_list_concTNode(( jtom.adt.tnode.types.TNodeList)tom_get_tail_TNodeList(l1),l2));    }   }  public  jtom.adt.tnode.types.TNodeList tom_get_slice_concTNode( jtom.adt.tnode.types.TNodeList begin,  jtom.adt.tnode.types.TNodeList end) {    if(tom_terms_equal_TNodeList(begin,end)) {      return ( jtom.adt.tnode.types.TNodeList)tom_empty_list_concTNode();    } else {      return ( jtom.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( jtom.adt.tnode.types.TNode)tom_get_head_TNodeList(begin),( jtom.adt.tnode.types.TNodeList)tom_get_slice_concTNode(( jtom.adt.tnode.types.TNodeList)tom_get_tail_TNodeList(begin),end));    }   }  public boolean tom_is_fun_sym_emptyTNodeList( jtom.adt.tnode.types.TNodeList t) { return  (t!= null) && t.isEmpty(); }public  jtom.adt.tnode.types.TNodeList tom_make_emptyTNodeList() { return getTNodeFactory().makeTNodeList(); }public boolean tom_is_fun_sym_manyTNodeList( jtom.adt.tnode.types.TNodeList t) { return  (t!= null) && t.isMany(); }public  jtom.adt.tnode.types.TNodeList tom_make_manyTNodeList( jtom.adt.tnode.types.TNode e,  jtom.adt.tnode.types.TNodeList l) { return getTNodeFactory().makeTNodeList(e,l); }public  jtom.adt.tnode.types.TNode tom_get_slot_manyTNodeList_head( jtom.adt.tnode.types.TNodeList t) { return  t.getHead(); }public  jtom.adt.tnode.types.TNodeList tom_get_slot_manyTNodeList_tail( jtom.adt.tnode.types.TNodeList t) { return  t.getTail(); } 

  private Factory factory = null;
  private OutputStream out = System.out;
  private Writer writer = null;

  private Factory getTNodeFactory() {
    return factory;
  }

  public ATermToXML () {
    factory = new Factory(new PureFactory());
  }

  public ATermToXML (Factory factory) {
    this.factory = factory;
  }

  public void setOutputStream(OutputStream out){
    this.out = out;
  }

  public void setWriter(Writer writer) {
    this.writer = writer;
  }

  public void convert(String filename) {
    try {
      convert(factory.getPureFactory().readFromFile(filename));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public void convert(ATerm term) {
    if (term instanceof TNode) {
      atermToXML((TNode) term);
    } else {
      System.out.println("ATermToXML can only convert TNode to XML");
    }
  }

  public String xml(TNode n) {
  StringWriter str_res = new StringWriter();
  setWriter(str_res);
  atermToXML(n);
  return str_res.toString();
  }

  public void atermToXML(TNode n) {
     { jtom.adt.tnode.types.TNode tom_match1_1=(( jtom.adt.tnode.types.TNode)n);{ if(tom_is_fun_sym_DocumentNode(tom_match1_1) ||  false ) { { jtom.adt.tnode.types.TNode tom_match1_1_1=tom_get_slot_DocumentNode_docType(tom_match1_1); { jtom.adt.tnode.types.TNode tom_match1_1_2=tom_get_slot_DocumentNode_docElem(tom_match1_1); { jtom.adt.tnode.types.TNode docType=tom_match1_1_1; { jtom.adt.tnode.types.TNode docElem=tom_match1_1_2;

        write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        atermToXML(docType);
        atermToXML(docElem);
        write("\n");
        return;
      }}}} } if(tom_is_fun_sym_DocumentTypeNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_DocumentTypeNode_name(tom_match1_1); { String  tom_match1_1_2=tom_get_slot_DocumentTypeNode_publicId(tom_match1_1); { String  tom_match1_1_3=tom_get_slot_DocumentTypeNode_systemId(tom_match1_1); { String  tom_match1_1_4=tom_get_slot_DocumentTypeNode_internalSubset(tom_match1_1); { String  name=tom_match1_1_1; { String  publicId=tom_match1_1_2; { String  systemId=tom_match1_1_3; { String  internalSubset=tom_match1_1_4;

        write("<!DOCTYPE "+name);
        if (!publicId.equals("UNDEF") && !systemId.equals("UNDEF"))
          write(" PUBLIC \""+publicId+"\" \"");
        else if (!systemId.equals("UNDEF") && publicId.equals("UNDEF"))
          write(" SYSTEM \"");
        else {
          System.out.println("Problem in DocumentTypeNode");
          throw new TomRuntimeException("Problem in DocumentTypeNode");
        }
        write(systemId+"\"");
        if (!internalSubset.equals("UNDEF")) 
          write(" ["+internalSubset+"]");
          write(">\n");
          return;
      }}}}}}}} } if(tom_is_fun_sym_ElementNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_ElementNode_name(tom_match1_1); { jtom.adt.tnode.types.TNodeList tom_match1_1_2=tom_get_slot_ElementNode_attrList(tom_match1_1); { jtom.adt.tnode.types.TNodeList tom_match1_1_3=tom_get_slot_ElementNode_childList(tom_match1_1); { String  name=tom_match1_1_1; { jtom.adt.tnode.types.TNodeList attrList=tom_match1_1_2; if(tom_is_fun_sym_concTNode(tom_match1_1_3) ||  false ) { { jtom.adt.tnode.types.TNodeList tom_match1_1_3_list1=tom_match1_1_3; if(tom_is_empty_TNodeList(tom_match1_1_3_list1)) {

        write("<"+name);
        atermToXMLList(attrList);
        write("/>");
        return;
       }} }}}}}} } if(tom_is_fun_sym_ElementNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_ElementNode_name(tom_match1_1); { jtom.adt.tnode.types.TNodeList tom_match1_1_2=tom_get_slot_ElementNode_attrList(tom_match1_1); { jtom.adt.tnode.types.TNodeList tom_match1_1_3=tom_get_slot_ElementNode_childList(tom_match1_1); { String  name=tom_match1_1_1; { jtom.adt.tnode.types.TNodeList attrList=tom_match1_1_2; { jtom.adt.tnode.types.TNodeList childList=tom_match1_1_3;

        write("<"+name);
        atermToXMLList(attrList);
        write(">");
        atermToXMLList(childList);
        write("</"+name+">");
        return;
      }}}}}} } if(tom_is_fun_sym_AttributeNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_AttributeNode_name(tom_match1_1); { String  tom_match1_1_2=tom_get_slot_AttributeNode_specified(tom_match1_1); { String  tom_match1_1_3=tom_get_slot_AttributeNode_value(tom_match1_1); { String  name=tom_match1_1_1; { String  specified=tom_match1_1_2; { String  child=tom_match1_1_3;

        if (specified.equals("true")) {
          write(" " + name + "=\"" + child + "\"");
        }
        return;
      }}}}}} } if(tom_is_fun_sym_TextNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_TextNode_data(tom_match1_1); { String  data=tom_match1_1_1;

        write(data);
        return;
      }} } if(tom_is_fun_sym_CommentNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_CommentNode_data(tom_match1_1); { String  data=tom_match1_1_1;

        write("<!-- "+data+" -->");
        return;
      }} } if(tom_is_fun_sym_CDATASectionNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_CDATASectionNode_data(tom_match1_1); { String  data=tom_match1_1_1;

        write("<![CDATA["+data+"]]>");
        return;
      }} } if(tom_is_fun_sym_ProcessingInstructionNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_ProcessingInstructionNode_target(tom_match1_1); { String  tom_match1_1_2=tom_get_slot_ProcessingInstructionNode_data(tom_match1_1); { String  target=tom_match1_1_1; { String  data=tom_match1_1_2;

        write("<?"+target+" "+data+"?>");
        return;
      }}}} } if(tom_is_fun_sym_EntityReferenceNode(tom_match1_1) ||  false ) { { String  tom_match1_1_1=tom_get_slot_EntityReferenceNode_name(tom_match1_1); { jtom.adt.tnode.types.TNodeList tom_match1_1_2=tom_get_slot_EntityReferenceNode_childList(tom_match1_1); { String  name=tom_match1_1_1; { jtom.adt.tnode.types.TNodeList childList=tom_match1_1_2;

        write("&"+name+";");
        return;
      }}}} }

        System.out.println("Unknown type of TNode : "+n);
      }}

  }

  private void atermToXMLList(TNodeList list) {
    if(list.isEmpty()) {
      return;
    }
    TNode t = (TNode) list.getFirst();
    TNodeList l = list.getTail(); 
    atermToXML(t);
    atermToXMLList(l);
  }
   
  private void write(String s) {
    try {
      if (out != null) {
    out.write(s.getBytes());
    }
      if (writer != null) {
    writer.write(s);
    }
    } catch (IOException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      throw new TomRuntimeException(e.getMessage());
    }
  }

}
