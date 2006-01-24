/* Generated by TOM (version 2.3rc0): Do not edit this file *//*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2006, Pierre-Etienne Moreau
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

package tom.library.xml;

import java.io.*;
import tom.library.adt.tnode.*;
import tom.library.adt.tnode.types.*;
import aterm.*;
import aterm.pure.*;

public class ATermToXML {
  
  /* Generated by TOM (version 2.3rc0): Do not edit this file *//* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private  char  tom_set_stamp_char( char  c) { return  c ; }private void tom_check_stamp_char( char  c) { ; }private boolean tom_terms_equal_char( char  t1,  char  t2) { return  (t1==t2) ; }private  Character  tom_set_stamp_Character( Character  c) { return  c ; }private void tom_check_stamp_Character( Character  c) { ; }private boolean tom_terms_equal_Character(Object t1, Object t2) { return  (t1.equals(t2)) ; }private boolean tom_is_fun_sym_Char( Character  t) { return  (t!= null) && (t instanceof Character) ; }private  Character  tom_make_Char( char  c) { return  new Character(c) ; }private  char  tom_get_slot_Char_c( Character  t) { return  t.charValue() ; } private  String  tom_set_stamp_String( String  s) { return  s ; }private void tom_check_stamp_String( String  s) { ; }private boolean tom_terms_equal_String( String  t1,  String  t2) { return  (t1.equals(t2)) ; }private boolean tom_is_fun_sym_concString( String  t) { return  (t!= null) && (t instanceof String) ; }private  String  tom_empty_list_concString() { return  "" ; }private  String  tom_cons_list_concString( char  c,  String  s) { return  (c+s) ; }private  char  tom_get_head_concString_String( String  s) { return  s.charAt(0) ; }private  String  tom_get_tail_concString_String( String  s) { return  s.substring(1) ; }private boolean tom_is_empty_concString_String( String  s) { return  (s.length()==0) ; }private  String  tom_append_list_concString( String  l1,  String  l2) {    if(tom_is_empty_concString_String(l1)) {     return l2;    } else if(tom_is_empty_concString_String(l2)) {     return l1;    } else if(tom_is_empty_concString_String(( String )tom_get_tail_concString_String(l1))) {     return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(l1),l2);    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(l1),tom_append_list_concString(( String )tom_get_tail_concString_String(l1),l2));    }   }  private  String  tom_get_slice_concString( String  begin,  String  end) {    if(tom_terms_equal_String(begin,end)) {      return ( String )tom_empty_list_concString();    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(begin),( String )tom_get_slice_concString(( String )tom_get_tail_concString_String(begin),end));    }   }    /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */  private boolean tom_terms_equal_int( int  t1,  int  t2) { return  (t1==t2) ; } /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private boolean tom_terms_equal_double( double  t1,  double  t2) { return  (t1==t2) ; } /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private boolean tom_terms_equal_ATerm(Object t1, Object t2) { return  t1 == t2; } /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private boolean tom_terms_equal_ATermList(Object l1, Object l2) { return  l1==l2 ; } private  tom.library.adt.tnode.types.TNodeList tom_get_implementation_TNodeList( tom.library.adt.tnode.types.TNodeList t) { return t; }private  tom.library.adt.tnode.types.TNodeList tom_set_stamp_TNodeList( tom.library.adt.tnode.types.TNodeList t) { return (tom.library.adt.tnode.types.TNodeList)t.setAnnotation(getTNodeFactory().getPureFactory().makeList(),getTNodeFactory().getPureFactory().makeList()); }private void tom_check_stamp_TNodeList( tom.library.adt.tnode.types.TNodeList t) { if(t.getAnnotation(getTNodeFactory().getPureFactory().makeList()) == getTNodeFactory().getPureFactory().makeList())  return; else throw new RuntimeException("bad stamp"); }private boolean tom_terms_equal_TNodeList(Object t1, Object t2) { return t1.equals(t2); }private boolean tom_is_fun_sym_concTNode( tom.library.adt.tnode.types.TNodeList t) { return (t!= null) && t.isSortTNodeList(); }private  tom.library.adt.tnode.types.TNodeList tom_empty_list_concTNode() { return getTNodeFactory().makeTNodeList(); }private  tom.library.adt.tnode.types.TNodeList tom_cons_list_concTNode( tom.library.adt.tnode.types.TNode e,  tom.library.adt.tnode.types.TNodeList l) { return getTNodeFactory().makeTNodeList(e,l); }private  tom.library.adt.tnode.types.TNode tom_get_head_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList l) { return l.getHead(); }private  tom.library.adt.tnode.types.TNodeList tom_get_tail_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList l) { return l.getTail(); }private boolean tom_is_empty_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList l) { return l.isEmpty(); }private  tom.library.adt.tnode.types.TNodeList tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList l1,  tom.library.adt.tnode.types.TNodeList l2) {    if(tom_is_empty_concTNode_TNodeList(l1)) {     return l2;    } else if(tom_is_empty_concTNode_TNodeList(l2)) {     return l1;    } else if(tom_is_empty_concTNode_TNodeList(( tom.library.adt.tnode.types.TNodeList)tom_get_tail_concTNode_TNodeList(l1))) {     return ( tom.library.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode)tom_get_head_concTNode_TNodeList(l1),l2);    } else {      return ( tom.library.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode)tom_get_head_concTNode_TNodeList(l1),tom_append_list_concTNode(( tom.library.adt.tnode.types.TNodeList)tom_get_tail_concTNode_TNodeList(l1),l2));    }   }  private  tom.library.adt.tnode.types.TNodeList tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList begin,  tom.library.adt.tnode.types.TNodeList end) {    if(tom_terms_equal_TNodeList(begin,end)) {      return ( tom.library.adt.tnode.types.TNodeList)tom_empty_list_concTNode();    } else {      return ( tom.library.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode)tom_get_head_concTNode_TNodeList(begin),( tom.library.adt.tnode.types.TNodeList)tom_get_slice_concTNode(( tom.library.adt.tnode.types.TNodeList)tom_get_tail_concTNode_TNodeList(begin),end));    }   }  private boolean tom_is_fun_sym_emptyTNodeList( tom.library.adt.tnode.types.TNodeList t) { return  (t!= null) && t.isEmpty(); }private  tom.library.adt.tnode.types.TNodeList tom_make_emptyTNodeList() { return getTNodeFactory().makeTNodeList(); }private boolean tom_is_fun_sym_manyTNodeList( tom.library.adt.tnode.types.TNodeList t) { return  (t!= null) && t.isMany(); }private  tom.library.adt.tnode.types.TNodeList tom_make_manyTNodeList( tom.library.adt.tnode.types.TNode e,  tom.library.adt.tnode.types.TNodeList l) { return getTNodeFactory().makeTNodeList(e,l); }private  tom.library.adt.tnode.types.TNode tom_get_slot_manyTNodeList_head( tom.library.adt.tnode.types.TNodeList t) { return  t.getHead(); }private  tom.library.adt.tnode.types.TNodeList tom_get_slot_manyTNodeList_tail( tom.library.adt.tnode.types.TNodeList t) { return  t.getTail(); }private  tom.library.adt.tnode.types.TNode tom_get_implementation_TNode( tom.library.adt.tnode.types.TNode t) { return t; }private  tom.library.adt.tnode.types.TNode tom_set_stamp_TNode( tom.library.adt.tnode.types.TNode t) { return (tom.library.adt.tnode.types.TNode)t.setAnnotation(getTNodeFactory().getPureFactory().makeList(),getTNodeFactory().getPureFactory().makeList()); }private void tom_check_stamp_TNode( tom.library.adt.tnode.types.TNode t) { if(t.getAnnotation(getTNodeFactory().getPureFactory().makeList()) == getTNodeFactory().getPureFactory().makeList())  return; else throw new RuntimeException("bad stamp"); }private boolean tom_terms_equal_TNode(Object t1, Object t2) { return t1.equals(t2); }private boolean tom_is_fun_sym_NotationNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isNotationNode(); }private  tom.library.adt.tnode.types.TNode tom_make_NotationNode( String  t0,  String  t1) { return  getTNodeFactory().makeTNode_NotationNode(t0, t1); }private  String  tom_get_slot_NotationNode_publicId( tom.library.adt.tnode.types.TNode t) { return  t.getPublicId(); }private  String  tom_get_slot_NotationNode_systemId( tom.library.adt.tnode.types.TNode t) { return  t.getSystemId(); }private boolean tom_is_fun_sym_EntityNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isEntityNode(); }private  tom.library.adt.tnode.types.TNode tom_make_EntityNode( String  t0,  String  t1,  String  t2) { return  getTNodeFactory().makeTNode_EntityNode(t0, t1, t2); }private  String  tom_get_slot_EntityNode_notationName( tom.library.adt.tnode.types.TNode t) { return  t.getNotationName(); }private  String  tom_get_slot_EntityNode_publicId( tom.library.adt.tnode.types.TNode t) { return  t.getPublicId(); }private  String  tom_get_slot_EntityNode_systemId( tom.library.adt.tnode.types.TNode t) { return  t.getSystemId(); }private boolean tom_is_fun_sym_EntityReferenceNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isEntityReferenceNode(); }private  tom.library.adt.tnode.types.TNode tom_make_EntityReferenceNode( String  t0,  tom.library.adt.tnode.types.TNodeList t1) { return  getTNodeFactory().makeTNode_EntityReferenceNode(t0, t1); }private  String  tom_get_slot_EntityReferenceNode_name( tom.library.adt.tnode.types.TNode t) { return  t.getName(); }private  tom.library.adt.tnode.types.TNodeList tom_get_slot_EntityReferenceNode_childList( tom.library.adt.tnode.types.TNode t) { return  t.getChildList(); }private boolean tom_is_fun_sym_DocumentTypeNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isDocumentTypeNode(); }private  tom.library.adt.tnode.types.TNode tom_make_DocumentTypeNode( String  t0,  String  t1,  String  t2,  String  t3,  tom.library.adt.tnode.types.TNodeList t4,  tom.library.adt.tnode.types.TNodeList t5) { return  getTNodeFactory().makeTNode_DocumentTypeNode(t0, t1, t2, t3, t4, t5); }private  String  tom_get_slot_DocumentTypeNode_name( tom.library.adt.tnode.types.TNode t) { return  t.getName(); }private  String  tom_get_slot_DocumentTypeNode_publicId( tom.library.adt.tnode.types.TNode t) { return  t.getPublicId(); }private  String  tom_get_slot_DocumentTypeNode_systemId( tom.library.adt.tnode.types.TNode t) { return  t.getSystemId(); }private  String  tom_get_slot_DocumentTypeNode_internalSubset( tom.library.adt.tnode.types.TNode t) { return  t.getInternalSubset(); }private  tom.library.adt.tnode.types.TNodeList tom_get_slot_DocumentTypeNode_entities( tom.library.adt.tnode.types.TNode t) { return  t.getEntities(); }private  tom.library.adt.tnode.types.TNodeList tom_get_slot_DocumentTypeNode_notations( tom.library.adt.tnode.types.TNode t) { return  t.getNotations(); }private boolean tom_is_fun_sym_AttributeNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isAttributeNode(); }private  tom.library.adt.tnode.types.TNode tom_make_AttributeNode( String  t0,  String  t1,  String  t2) { return  getTNodeFactory().makeTNode_AttributeNode(t0, t1, t2); }private  String  tom_get_slot_AttributeNode_name( tom.library.adt.tnode.types.TNode t) { return  t.getName(); }private  String  tom_get_slot_AttributeNode_specified( tom.library.adt.tnode.types.TNode t) { return  t.getSpecified(); }private  String  tom_get_slot_AttributeNode_value( tom.library.adt.tnode.types.TNode t) { return  t.getValue(); }private boolean tom_is_fun_sym_ElementNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isElementNode(); }private  tom.library.adt.tnode.types.TNode tom_make_ElementNode( String  t0,  tom.library.adt.tnode.types.TNodeList t1,  tom.library.adt.tnode.types.TNodeList t2) { return  getTNodeFactory().makeTNode_ElementNode(t0, t1, t2); }private  String  tom_get_slot_ElementNode_name( tom.library.adt.tnode.types.TNode t) { return  t.getName(); }private  tom.library.adt.tnode.types.TNodeList tom_get_slot_ElementNode_attrList( tom.library.adt.tnode.types.TNode t) { return  t.getAttrList(); }private  tom.library.adt.tnode.types.TNodeList tom_get_slot_ElementNode_childList( tom.library.adt.tnode.types.TNode t) { return  t.getChildList(); }private boolean tom_is_fun_sym_DocumentNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isDocumentNode(); }private  tom.library.adt.tnode.types.TNode tom_make_DocumentNode( tom.library.adt.tnode.types.TNode t0,  tom.library.adt.tnode.types.TNode t1) { return  getTNodeFactory().makeTNode_DocumentNode(t0, t1); }private  tom.library.adt.tnode.types.TNode tom_get_slot_DocumentNode_docType( tom.library.adt.tnode.types.TNode t) { return  t.getDocType(); }private  tom.library.adt.tnode.types.TNode tom_get_slot_DocumentNode_docElem( tom.library.adt.tnode.types.TNode t) { return  t.getDocElem(); }private boolean tom_is_fun_sym_CDATASectionNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isCDATASectionNode(); }private  tom.library.adt.tnode.types.TNode tom_make_CDATASectionNode( String  t0) { return  getTNodeFactory().makeTNode_CDATASectionNode(t0); }private  String  tom_get_slot_CDATASectionNode_data( tom.library.adt.tnode.types.TNode t) { return  t.getData(); }private boolean tom_is_fun_sym_TextNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isTextNode(); }private  tom.library.adt.tnode.types.TNode tom_make_TextNode( String  t0) { return  getTNodeFactory().makeTNode_TextNode(t0); }private  String  tom_get_slot_TextNode_data( tom.library.adt.tnode.types.TNode t) { return  t.getData(); }private boolean tom_is_fun_sym_ProcessingInstructionNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isProcessingInstructionNode(); }private  tom.library.adt.tnode.types.TNode tom_make_ProcessingInstructionNode( String  t0,  String  t1) { return  getTNodeFactory().makeTNode_ProcessingInstructionNode(t0, t1); }private  String  tom_get_slot_ProcessingInstructionNode_target( tom.library.adt.tnode.types.TNode t) { return  t.getTarget(); }private  String  tom_get_slot_ProcessingInstructionNode_data( tom.library.adt.tnode.types.TNode t) { return  t.getData(); }private boolean tom_is_fun_sym_CommentNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isCommentNode(); }private  tom.library.adt.tnode.types.TNode tom_make_CommentNode( String  t0) { return  getTNodeFactory().makeTNode_CommentNode(t0); }private  String  tom_get_slot_CommentNode_data( tom.library.adt.tnode.types.TNode t) { return  t.getData(); }private boolean tom_is_fun_sym_EmptyNode( tom.library.adt.tnode.types.TNode t) { return  (t!= null) && t.isEmptyNode(); }private  tom.library.adt.tnode.types.TNode tom_make_EmptyNode() { return  getTNodeFactory().makeTNode_EmptyNode(); }  

  private TNodeFactory factory = null;
  private OutputStream out = System.out;
  private Writer writer = null;

  private TNodeFactory getTNodeFactory() {
    return factory;
  }

  public ATermToXML () {
    factory = TNodeFactory.getInstance(SingletonFactory.getInstance());
  }

  public ATermToXML (TNodeFactory factory) {
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
    } else if (term instanceof TNodeList) {
      atermToXMLList((TNodeList) term);
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
     { tom.library.adt.tnode.types.TNode tom_match1_1=(( tom.library.adt.tnode.types.TNode)n); if(tom_is_fun_sym_DocumentNode(tom_match1_1) ||  false ) { { tom.library.adt.tnode.types.TNode tom_match1_1_docType=tom_get_slot_DocumentNode_docType(tom_match1_1); { tom.library.adt.tnode.types.TNode tom_match1_1_docElem=tom_get_slot_DocumentNode_docElem(tom_match1_1);

        write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        atermToXML(tom_match1_1_docType);
        atermToXML(tom_match1_1_docElem);
        write("\n");
        return;
      }} } if(tom_is_fun_sym_DocumentTypeNode(tom_match1_1) ||  false ) { { String  tom_match1_1_name=tom_get_slot_DocumentTypeNode_name(tom_match1_1); { String  tom_match1_1_publicId=tom_get_slot_DocumentTypeNode_publicId(tom_match1_1); { String  tom_match1_1_systemId=tom_get_slot_DocumentTypeNode_systemId(tom_match1_1); { String  tom_match1_1_internalSubset=tom_get_slot_DocumentTypeNode_internalSubset(tom_match1_1); { String  tom_publicId=tom_match1_1_publicId; { String  tom_systemId=tom_match1_1_systemId; { String  tom_internalSubset=tom_match1_1_internalSubset;

        write("<!DOCTYPE "+tom_match1_1_name);
        if (!tom_publicId.equals("UNDEF") && !tom_systemId.equals("UNDEF"))
          write(" PUBLIC \""+tom_publicId+"\" \"");
        else if (!tom_systemId.equals("UNDEF") && tom_publicId.equals("UNDEF"))
          write(" SYSTEM \"");
        else {
          System.out.println("Problem in DocumentTypeNode");
          throw new RuntimeException("Problem in DocumentTypeNode");
        }
        write(tom_systemId+"\"");
        if (!tom_internalSubset.equals("UNDEF")) 
          write(" ["+tom_internalSubset+"]");
          write(">\n");
          return;
      }}}}}}} } if(tom_is_fun_sym_ElementNode(tom_match1_1) ||  false ) { { String  tom_match1_1_name=tom_get_slot_ElementNode_name(tom_match1_1); { tom.library.adt.tnode.types.TNodeList tom_match1_1_attrList=tom_get_slot_ElementNode_attrList(tom_match1_1); { tom.library.adt.tnode.types.TNodeList tom_match1_1_childList=tom_get_slot_ElementNode_childList(tom_match1_1); if(tom_is_fun_sym_concTNode(tom_match1_1_childList) ||  false ) { { tom.library.adt.tnode.types.TNodeList tom_match1_1_childList_list1=tom_match1_1_childList; if(tom_is_empty_concTNode_TNodeList(tom_match1_1_childList_list1)) {

        write("<"+tom_match1_1_name);
        atermToXMLList(tom_match1_1_attrList);
        write("/>");
        return;
       }} }}}} } if(tom_is_fun_sym_ElementNode(tom_match1_1) ||  false ) { { String  tom_match1_1_name=tom_get_slot_ElementNode_name(tom_match1_1); { tom.library.adt.tnode.types.TNodeList tom_match1_1_attrList=tom_get_slot_ElementNode_attrList(tom_match1_1); { tom.library.adt.tnode.types.TNodeList tom_match1_1_childList=tom_get_slot_ElementNode_childList(tom_match1_1); { String  tom_name=tom_match1_1_name;

        write("<"+tom_name);
        atermToXMLList(tom_match1_1_attrList);
        write(">");
        atermToXMLList(tom_match1_1_childList);
        write("</"+tom_name+">");
        return;
      }}}} } if(tom_is_fun_sym_AttributeNode(tom_match1_1) ||  false ) { { String  tom_match1_1_name=tom_get_slot_AttributeNode_name(tom_match1_1); { String  tom_match1_1_specified=tom_get_slot_AttributeNode_specified(tom_match1_1); { String  tom_match1_1_value=tom_get_slot_AttributeNode_value(tom_match1_1);

        if (tom_match1_1_specified.equals("true")) {
          write(" " + tom_match1_1_name+ "=\"" + tom_match1_1_value+ "\"");
        }
        return;
      }}} } if(tom_is_fun_sym_TextNode(tom_match1_1) ||  false ) { { String  tom_match1_1_data=tom_get_slot_TextNode_data(tom_match1_1);

        write(tom_match1_1_data);
        return;
      } } if(tom_is_fun_sym_CommentNode(tom_match1_1) ||  false ) { { String  tom_match1_1_data=tom_get_slot_CommentNode_data(tom_match1_1);

        write("<!-- "+tom_match1_1_data+" -->");
        return;
      } } if(tom_is_fun_sym_CDATASectionNode(tom_match1_1) ||  false ) { { String  tom_match1_1_data=tom_get_slot_CDATASectionNode_data(tom_match1_1);

        write("<![CDATA["+tom_match1_1_data+"]]>");
        return;
      } } if(tom_is_fun_sym_ProcessingInstructionNode(tom_match1_1) ||  false ) { { String  tom_match1_1_target=tom_get_slot_ProcessingInstructionNode_target(tom_match1_1); { String  tom_match1_1_data=tom_get_slot_ProcessingInstructionNode_data(tom_match1_1);

        write("<?"+tom_match1_1_target+" "+tom_match1_1_data+"?>");
        return;
      }} } if(tom_is_fun_sym_EntityReferenceNode(tom_match1_1) ||  false ) { { String  tom_match1_1_name=tom_get_slot_EntityReferenceNode_name(tom_match1_1);

        write("&"+tom_match1_1_name+";");
        return;
      } } if(tom_is_fun_sym_EmptyNode(tom_match1_1) ||  false ) {

        return;
       }

        System.out.println("Unknown type of TNode : "+n);
      }

    write("\n");
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
      throw new RuntimeException(e.getMessage());
    }
  }

}
