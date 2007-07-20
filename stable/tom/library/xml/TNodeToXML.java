/* Generated by TOM (version 2.5): Do not edit this file *//*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2007, INRIA
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

  /* Generated by TOM (version 2.5): Do not edit this file *//* Generated by TOM (version 2.5): Do not edit this file *//* Generated by TOM (version 2.5): Do not edit this file */ private static boolean tom_equal_term_String(String t1, String t2) { return  (t1.equals(t2)) ;}private static boolean tom_is_sort_String(String t) { return  t instanceof String ;}  /* Generated by TOM (version 2.5): Do not edit this file */ /* Generated by TOM (version 2.5): Do not edit this file */ /* Generated by TOM (version 2.5): Do not edit this file */ /* Generated by TOM (version 2.5): Do not edit this file */ private static boolean tom_equal_term_TNodeList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TNodeList(Object t) { return  t instanceof tom.library.adt.tnode.types.TNodeList ;}private static boolean tom_equal_term_TNode(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TNode(Object t) { return  t instanceof tom.library.adt.tnode.types.TNode ;}private static boolean tom_is_fun_sym_EmptyNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.EmptyNode ;}private static boolean tom_is_fun_sym_CommentNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.CommentNode ;}private static  String  tom_get_slot_CommentNode_Data( tom.library.adt.tnode.types.TNode  t) { return  t.getData() ;}private static boolean tom_is_fun_sym_ProcessingInstructionNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.ProcessingInstructionNode ;}private static  String  tom_get_slot_ProcessingInstructionNode_Target( tom.library.adt.tnode.types.TNode  t) { return  t.getTarget() ;}private static  String  tom_get_slot_ProcessingInstructionNode_Data( tom.library.adt.tnode.types.TNode  t) { return  t.getData() ;}private static boolean tom_is_fun_sym_TextNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.TextNode ;}private static  String  tom_get_slot_TextNode_Data( tom.library.adt.tnode.types.TNode  t) { return  t.getData() ;}private static boolean tom_is_fun_sym_CDATASectionNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.CDATASectionNode ;}private static  String  tom_get_slot_CDATASectionNode_Data( tom.library.adt.tnode.types.TNode  t) { return  t.getData() ;}private static boolean tom_is_fun_sym_DocumentNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.DocumentNode ;}private static  tom.library.adt.tnode.types.TNode  tom_get_slot_DocumentNode_DocType( tom.library.adt.tnode.types.TNode  t) { return  t.getDocType() ;}private static  tom.library.adt.tnode.types.TNode  tom_get_slot_DocumentNode_DocElem( tom.library.adt.tnode.types.TNode  t) { return  t.getDocElem() ;}private static boolean tom_is_fun_sym_ElementNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.ElementNode ;}private static  String  tom_get_slot_ElementNode_Name( tom.library.adt.tnode.types.TNode  t) { return  t.getName() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_AttrList( tom.library.adt.tnode.types.TNode  t) { return  t.getAttrList() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_ChildList( tom.library.adt.tnode.types.TNode  t) { return  t.getChildList() ;}private static boolean tom_is_fun_sym_AttributeNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.AttributeNode ;}private static  String  tom_get_slot_AttributeNode_Name( tom.library.adt.tnode.types.TNode  t) { return  t.getName() ;}private static  String  tom_get_slot_AttributeNode_Specified( tom.library.adt.tnode.types.TNode  t) { return  t.getSpecified() ;}private static  String  tom_get_slot_AttributeNode_Value( tom.library.adt.tnode.types.TNode  t) { return  t.getValue() ;}private static boolean tom_is_fun_sym_DocumentTypeNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.DocumentTypeNode ;}private static  String  tom_get_slot_DocumentTypeNode_Name( tom.library.adt.tnode.types.TNode  t) { return  t.getName() ;}private static  String  tom_get_slot_DocumentTypeNode_PublicId( tom.library.adt.tnode.types.TNode  t) { return  t.getPublicId() ;}private static  String  tom_get_slot_DocumentTypeNode_SystemId( tom.library.adt.tnode.types.TNode  t) { return  t.getSystemId() ;}private static  String  tom_get_slot_DocumentTypeNode_InternalSubset( tom.library.adt.tnode.types.TNode  t) { return  t.getInternalSubset() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_DocumentTypeNode_Entities( tom.library.adt.tnode.types.TNode  t) { return  t.getEntities() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_DocumentTypeNode_Notations( tom.library.adt.tnode.types.TNode  t) { return  t.getNotations() ;}private static boolean tom_is_fun_sym_EntityReferenceNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.EntityReferenceNode ;}private static  String  tom_get_slot_EntityReferenceNode_Name( tom.library.adt.tnode.types.TNode  t) { return  t.getName() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_EntityReferenceNode_ChildList( tom.library.adt.tnode.types.TNode  t) { return  t.getChildList() ;}private static boolean tom_is_fun_sym_concTNode( tom.library.adt.tnode.types.TNodeList  t) { return  t instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode || t instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode ;}private static  tom.library.adt.tnode.types.TNodeList  tom_empty_list_concTNode() { return  tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ; }private static  tom.library.adt.tnode.types.TNodeList  tom_cons_list_concTNode( tom.library.adt.tnode.types.TNode  e,  tom.library.adt.tnode.types.TNodeList  l) { return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make(e,l) ; }private static  tom.library.adt.tnode.types.TNode  tom_get_head_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) { return  l.getHeadconcTNode() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_tail_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) { return  l.getTailconcTNode() ;}private static boolean tom_is_empty_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) { return  l.isEmptyconcTNode() ;}   private static   tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList l1,  tom.library.adt.tnode.types.TNodeList  l2) {     if(tom_is_empty_concTNode_TNodeList(l1)) {       return l2;     } else if(tom_is_empty_concTNode_TNodeList(l2)) {       return l1;     } else if(tom_is_empty_concTNode_TNodeList(tom_get_tail_concTNode_TNodeList(l1))) {       return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(tom_get_head_concTNode_TNodeList(l1),l2);     } else {       return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(tom_get_head_concTNode_TNodeList(l1),tom_append_list_concTNode(tom_get_tail_concTNode_TNodeList(l1),l2));     }   }   private static   tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end, tom.library.adt.tnode.types.TNodeList  tail) {     if(tom_equal_term_TNodeList(begin,end)) {       return tail;     } else {       return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(tom_get_head_concTNode_TNodeList(begin),( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode(tom_get_tail_concTNode_TNodeList(begin),end,tail));     }   }    

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
    if (tom_is_sort_TNode(n)) {{  tom.library.adt.tnode.types.TNode  tomMatch525NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )n);if (tom_is_fun_sym_DocumentNode(tomMatch525NameNumberfreshSubject_1)) {{  tom.library.adt.tnode.types.TNode  tomMatch525NameNumber_freshVar_0=tom_get_slot_DocumentNode_DocType(tomMatch525NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNode  tomMatch525NameNumber_freshVar_1=tom_get_slot_DocumentNode_DocElem(tomMatch525NameNumberfreshSubject_1);if ( true ) {

        write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        tnodeToXML(tomMatch525NameNumber_freshVar_0);
        tnodeToXML(tomMatch525NameNumber_freshVar_1);
        write("\n");
        return;
      }}}}if (tom_is_fun_sym_DocumentTypeNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_2=tom_get_slot_DocumentTypeNode_Name(tomMatch525NameNumberfreshSubject_1);{  String  tomMatch525NameNumber_freshVar_3=tom_get_slot_DocumentTypeNode_PublicId(tomMatch525NameNumberfreshSubject_1);{  String  tomMatch525NameNumber_freshVar_4=tom_get_slot_DocumentTypeNode_SystemId(tomMatch525NameNumberfreshSubject_1);{  String  tomMatch525NameNumber_freshVar_5=tom_get_slot_DocumentTypeNode_InternalSubset(tomMatch525NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch525NameNumber_freshVar_6=tom_get_slot_DocumentTypeNode_Entities(tomMatch525NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch525NameNumber_freshVar_7=tom_get_slot_DocumentTypeNode_Notations(tomMatch525NameNumberfreshSubject_1);{  String  tom_publicId=tomMatch525NameNumber_freshVar_3;{  String  tom_systemId=tomMatch525NameNumber_freshVar_4;{  String  tom_internalSubset=tomMatch525NameNumber_freshVar_5;if ( true ) {

        write("<!DOCTYPE "+tomMatch525NameNumber_freshVar_2);
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
      }}}}}}}}}}}if (tom_is_fun_sym_ElementNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_8=tom_get_slot_ElementNode_Name(tomMatch525NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch525NameNumber_freshVar_9=tom_get_slot_ElementNode_AttrList(tomMatch525NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch525NameNumber_freshVar_10=tom_get_slot_ElementNode_ChildList(tomMatch525NameNumberfreshSubject_1);if (tom_is_fun_sym_concTNode(tomMatch525NameNumber_freshVar_10)) {{  tom.library.adt.tnode.types.TNodeList  tomMatch525NameNumber_freshVar_11=tomMatch525NameNumber_freshVar_10;if (tom_is_empty_concTNode_TNodeList(tomMatch525NameNumber_freshVar_11)) {if ( true ) {

        write("<"+tomMatch525NameNumber_freshVar_8);
        tnodeListToXML(tomMatch525NameNumber_freshVar_9);
        write("/>");
        return;
      }}}}}}}}if (tom_is_fun_sym_ElementNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_12=tom_get_slot_ElementNode_Name(tomMatch525NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch525NameNumber_freshVar_13=tom_get_slot_ElementNode_AttrList(tomMatch525NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch525NameNumber_freshVar_14=tom_get_slot_ElementNode_ChildList(tomMatch525NameNumberfreshSubject_1);{  String  tom_name=tomMatch525NameNumber_freshVar_12;if ( true ) {

        write("<"+tom_name);
        tnodeListToXML(tomMatch525NameNumber_freshVar_13);
        write(">");
        tnodeListToXML(tomMatch525NameNumber_freshVar_14);
        write("</"+tom_name+">");
        return;
      }}}}}}if (tom_is_fun_sym_AttributeNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_15=tom_get_slot_AttributeNode_Name(tomMatch525NameNumberfreshSubject_1);{  String  tomMatch525NameNumber_freshVar_16=tom_get_slot_AttributeNode_Specified(tomMatch525NameNumberfreshSubject_1);{  String  tomMatch525NameNumber_freshVar_17=tom_get_slot_AttributeNode_Value(tomMatch525NameNumberfreshSubject_1);if ( true ) {

        if (tomMatch525NameNumber_freshVar_16.equals("true")) {
          write(" " + tomMatch525NameNumber_freshVar_15+ "=\"" + tomMatch525NameNumber_freshVar_17+ "\"");
        }
        return;
      }}}}}if (tom_is_fun_sym_TextNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_18=tom_get_slot_TextNode_Data(tomMatch525NameNumberfreshSubject_1);if ( true ) {

        write(tomMatch525NameNumber_freshVar_18);
        return;
      }}}if (tom_is_fun_sym_CommentNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_19=tom_get_slot_CommentNode_Data(tomMatch525NameNumberfreshSubject_1);if ( true ) {

        write("<!-- "+tomMatch525NameNumber_freshVar_19+" -->");
        return;
      }}}if (tom_is_fun_sym_CDATASectionNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_20=tom_get_slot_CDATASectionNode_Data(tomMatch525NameNumberfreshSubject_1);if ( true ) {

        write("<![CDATA["+tomMatch525NameNumber_freshVar_20+"]]>");
        return;
      }}}if (tom_is_fun_sym_ProcessingInstructionNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_21=tom_get_slot_ProcessingInstructionNode_Target(tomMatch525NameNumberfreshSubject_1);{  String  tomMatch525NameNumber_freshVar_22=tom_get_slot_ProcessingInstructionNode_Data(tomMatch525NameNumberfreshSubject_1);if ( true ) {

        write("<?"+tomMatch525NameNumber_freshVar_21+" "+tomMatch525NameNumber_freshVar_22+"?>");
        return;
      }}}}if (tom_is_fun_sym_EntityReferenceNode(tomMatch525NameNumberfreshSubject_1)) {{  String  tomMatch525NameNumber_freshVar_23=tom_get_slot_EntityReferenceNode_Name(tomMatch525NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch525NameNumber_freshVar_24=tom_get_slot_EntityReferenceNode_ChildList(tomMatch525NameNumberfreshSubject_1);if ( true ) {

        write("&"+tomMatch525NameNumber_freshVar_23+";");
        return;
      }}}}if (tom_is_fun_sym_EmptyNode(tomMatch525NameNumberfreshSubject_1)) {if ( true ) {

        return;
      }}if ( true ) {

        System.out.println("Unknown type of TNode : "+n);
      }}}

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
