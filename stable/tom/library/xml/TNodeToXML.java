/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
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

public class TNodeToXML {

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_TNodeList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TNode(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_EmptyNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.EmptyNode  ;}private static boolean tom_is_fun_sym_CommentNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.CommentNode  ;}private static  String  tom_get_slot_CommentNode_Data( tom.library.adt.tnode.types.TNode  t) {  return  t.getData()  ;}private static boolean tom_is_fun_sym_ProcessingInstructionNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.ProcessingInstructionNode  ;}private static  String  tom_get_slot_ProcessingInstructionNode_Target( tom.library.adt.tnode.types.TNode  t) {  return  t.getTarget()  ;}private static  String  tom_get_slot_ProcessingInstructionNode_Data( tom.library.adt.tnode.types.TNode  t) {  return  t.getData()  ;}private static boolean tom_is_fun_sym_TextNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.TextNode  ;}private static  String  tom_get_slot_TextNode_Data( tom.library.adt.tnode.types.TNode  t) {  return  t.getData()  ;}private static boolean tom_is_fun_sym_CDATASectionNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.CDATASectionNode  ;}private static  String  tom_get_slot_CDATASectionNode_Data( tom.library.adt.tnode.types.TNode  t) {  return  t.getData()  ;}private static boolean tom_is_fun_sym_DocumentNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.DocumentNode  ;}private static  tom.library.adt.tnode.types.TNode  tom_get_slot_DocumentNode_DocType( tom.library.adt.tnode.types.TNode  t) {  return  t.getDocType()  ;}private static  tom.library.adt.tnode.types.TNode  tom_get_slot_DocumentNode_DocElem( tom.library.adt.tnode.types.TNode  t) {  return  t.getDocElem()  ;}private static boolean tom_is_fun_sym_ElementNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.ElementNode  ;}private static  String  tom_get_slot_ElementNode_Name( tom.library.adt.tnode.types.TNode  t) {  return  t.getName()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_AttrList( tom.library.adt.tnode.types.TNode  t) {  return  t.getAttrList()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_ChildList( tom.library.adt.tnode.types.TNode  t) {  return  t.getChildList()  ;}private static boolean tom_is_fun_sym_AttributeNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.AttributeNode  ;}private static  String  tom_get_slot_AttributeNode_Name( tom.library.adt.tnode.types.TNode  t) {  return  t.getName()  ;}private static  String  tom_get_slot_AttributeNode_Specified( tom.library.adt.tnode.types.TNode  t) {  return  t.getSpecified()  ;}private static  String  tom_get_slot_AttributeNode_Value( tom.library.adt.tnode.types.TNode  t) {  return  t.getValue()  ;}private static boolean tom_is_fun_sym_DocumentTypeNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.DocumentTypeNode  ;}private static  String  tom_get_slot_DocumentTypeNode_Name( tom.library.adt.tnode.types.TNode  t) {  return  t.getName()  ;}private static  String  tom_get_slot_DocumentTypeNode_PublicId( tom.library.adt.tnode.types.TNode  t) {  return  t.getPublicId()  ;}private static  String  tom_get_slot_DocumentTypeNode_SystemId( tom.library.adt.tnode.types.TNode  t) {  return  t.getSystemId()  ;}private static  String  tom_get_slot_DocumentTypeNode_InternalSubset( tom.library.adt.tnode.types.TNode  t) {  return  t.getInternalSubset()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_DocumentTypeNode_Entities( tom.library.adt.tnode.types.TNode  t) {  return  t.getEntities()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_DocumentTypeNode_Notations( tom.library.adt.tnode.types.TNode  t) {  return  t.getNotations()  ;}private static boolean tom_is_fun_sym_EntityReferenceNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.EntityReferenceNode  ;}private static  String  tom_get_slot_EntityReferenceNode_Name( tom.library.adt.tnode.types.TNode  t) {  return  t.getName()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_EntityReferenceNode_ChildList( tom.library.adt.tnode.types.TNode  t) {  return  t.getChildList()  ;}private static boolean tom_is_fun_sym_concTNode( tom.library.adt.tnode.types.TNodeList  t) {  return  t instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode || t instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_empty_list_concTNode() { return  tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ; }private static  tom.library.adt.tnode.types.TNodeList  tom_cons_list_concTNode( tom.library.adt.tnode.types.TNode  e,  tom.library.adt.tnode.types.TNodeList  l) { return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make(e,l) ; }private static  tom.library.adt.tnode.types.TNode  tom_get_head_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.getHeadconcTNode()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_tail_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.getTailconcTNode()  ;}private static boolean tom_is_empty_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.isEmptyconcTNode()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList  l1,  tom.library.adt.tnode.types.TNodeList  l2) {    if(tom_is_empty_concTNode_TNodeList(l1)) {     return l2;    } else if(tom_is_empty_concTNode_TNodeList(l2)) {     return l1;    } else if(tom_is_empty_concTNode_TNodeList(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(l1))) {     return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(l1),l2);    } else {      return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(l1),tom_append_list_concTNode(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(l1),l2));    }   }  private static  tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end) {    if(tom_terms_equal_TNodeList(begin,end)) {      return ( tom.library.adt.tnode.types.TNodeList )tom_empty_list_concTNode();    } else {      return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(begin),( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(begin),end));    }   }   

  private OutputStream out = System.out;
  private Writer writer = null;

  public void setOutputStream(OutputStream out){
    this.out = out;
  }

  public void setWriter(Writer writer) {
    this.writer = writer;
  }

  /*
public void convert(String filename) {
    try {
      convert(SingletonFactory.getInstance().readFromFile(filename));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
*/

  public String xml(TNode n) {
		StringWriter str_res = new StringWriter();
		setWriter(str_res);
		tnodeToXML(n);
		return str_res.toString();
  }

  public void tnodeToXML(TNode n) {
     if(n instanceof  tom.library.adt.tnode.types.TNode ) { { tom.library.adt.tnode.types.TNode  tom_match1_1=(( tom.library.adt.tnode.types.TNode )n); if ( ( tom_is_fun_sym_DocumentNode(tom_match1_1) ||  false  ) ) { { tom.library.adt.tnode.types.TNode  tom_match1_1_DocType=tom_get_slot_DocumentNode_DocType(tom_match1_1); { tom.library.adt.tnode.types.TNode  tom_match1_1_DocElem=tom_get_slot_DocumentNode_DocElem(tom_match1_1); { tom.library.adt.tnode.types.TNode  tom_docType=tom_match1_1_DocType; { tom.library.adt.tnode.types.TNode  tom_docElem=tom_match1_1_DocElem; if ( true ) {

        write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        tnodeToXML(tom_docType);
        tnodeToXML(tom_docElem);
        write("\n");
        return;
       } } } } } } if ( ( tom_is_fun_sym_DocumentTypeNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Name=tom_get_slot_DocumentTypeNode_Name(tom_match1_1); { String  tom_match1_1_PublicId=tom_get_slot_DocumentTypeNode_PublicId(tom_match1_1); { String  tom_match1_1_SystemId=tom_get_slot_DocumentTypeNode_SystemId(tom_match1_1); { String  tom_match1_1_InternalSubset=tom_get_slot_DocumentTypeNode_InternalSubset(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_Entities=tom_get_slot_DocumentTypeNode_Entities(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_Notations=tom_get_slot_DocumentTypeNode_Notations(tom_match1_1); { String  tom_name=tom_match1_1_Name; { String  tom_publicId=tom_match1_1_PublicId; { String  tom_systemId=tom_match1_1_SystemId; { String  tom_internalSubset=tom_match1_1_InternalSubset; if ( true ) {

        write("<!DOCTYPE "+tom_name);
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
       } } } } } } } } } } } } if ( ( tom_is_fun_sym_ElementNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Name=tom_get_slot_ElementNode_Name(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_AttrList=tom_get_slot_ElementNode_AttrList(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match1_1); { String  tom_name=tom_match1_1_Name; { tom.library.adt.tnode.types.TNodeList  tom_attrList=tom_match1_1_AttrList; if ( ( tom_is_fun_sym_concTNode(tom_match1_1_ChildList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList_list1=tom_match1_1_ChildList; if (tom_is_empty_concTNode_TNodeList(tom_match1_1_ChildList_list1)) { if ( true ) {

        write("<"+tom_name);
        tnodeListToXML(tom_attrList);
        write("/>");
        return;
       } } } } } } } } } } if ( ( tom_is_fun_sym_ElementNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Name=tom_get_slot_ElementNode_Name(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_AttrList=tom_get_slot_ElementNode_AttrList(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match1_1); { String  tom_name=tom_match1_1_Name; { tom.library.adt.tnode.types.TNodeList  tom_attrList=tom_match1_1_AttrList; { tom.library.adt.tnode.types.TNodeList  tom_childList=tom_match1_1_ChildList; if ( true ) {

        write("<"+tom_name);
        tnodeListToXML(tom_attrList);
        write(">");
        tnodeListToXML(tom_childList);
        write("</"+tom_name+">");
        return;
       } } } } } } } } if ( ( tom_is_fun_sym_AttributeNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Name=tom_get_slot_AttributeNode_Name(tom_match1_1); { String  tom_match1_1_Specified=tom_get_slot_AttributeNode_Specified(tom_match1_1); { String  tom_match1_1_Value=tom_get_slot_AttributeNode_Value(tom_match1_1); { String  tom_name=tom_match1_1_Name; { String  tom_specified=tom_match1_1_Specified; { String  tom_child=tom_match1_1_Value; if ( true ) {

        if (tom_specified.equals("true")) {
          write(" " + tom_name+ "=\"" + tom_child+ "\"");
        }
        return;
       } } } } } } } } if ( ( tom_is_fun_sym_TextNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Data=tom_get_slot_TextNode_Data(tom_match1_1); { String  tom_data=tom_match1_1_Data; if ( true ) {

        write(tom_data);
        return;
       } } } } if ( ( tom_is_fun_sym_CommentNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Data=tom_get_slot_CommentNode_Data(tom_match1_1); { String  tom_data=tom_match1_1_Data; if ( true ) {

        write("<!-- "+tom_data+" -->");
        return;
       } } } } if ( ( tom_is_fun_sym_CDATASectionNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Data=tom_get_slot_CDATASectionNode_Data(tom_match1_1); { String  tom_data=tom_match1_1_Data; if ( true ) {

        write("<![CDATA["+tom_data+"]]>");
        return;
       } } } } if ( ( tom_is_fun_sym_ProcessingInstructionNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Target=tom_get_slot_ProcessingInstructionNode_Target(tom_match1_1); { String  tom_match1_1_Data=tom_get_slot_ProcessingInstructionNode_Data(tom_match1_1); { String  tom_target=tom_match1_1_Target; { String  tom_data=tom_match1_1_Data; if ( true ) {

        write("<?"+tom_target+" "+tom_data+"?>");
        return;
       } } } } } } if ( ( tom_is_fun_sym_EntityReferenceNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Name=tom_get_slot_EntityReferenceNode_Name(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList=tom_get_slot_EntityReferenceNode_ChildList(tom_match1_1); { String  tom_name=tom_match1_1_Name; if ( true ) {

        write("&"+tom_name+";");
        return;
       } } } } } if ( ( tom_is_fun_sym_EmptyNode(tom_match1_1) ||  false  ) ) { if ( true ) {

        return;
       } } if ( true ) {

        System.out.println("Unknown type of TNode : "+n);
       } } }

    write("\n");
  }

  private void tnodeListToXML(TNodeList list) {
    if(list.isEmptyconcTNode()) {
      return;
    }
    TNode t = list.getHeadconcTNode();
    TNodeList l = list.getTailconcTNode();
    tnodeToXML(t);
    tnodeListToXML(l);
  }

  private void write(String s) {
    try {
      if (out != null) {
    out.write(s.getBytes("UTF-8"));
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
