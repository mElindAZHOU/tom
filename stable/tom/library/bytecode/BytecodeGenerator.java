/*
 * Copyright (c) 2000-2009, INRIA
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 *	- Redistributions of source code must retain the above copyright
 *	notice, this list of conditions and the following disclaimer.  
 *	- Redistributions in binary form must reproduce the above copyright
 *	notice, this list of conditions and the following disclaimer in the
 *	documentation and/or other materials provided with the distribution.
 *	- Neither the name of the INRIA nor the names of its
 *	contributors may be used to endorse or promote products derived from
 *	this software without specific prior written permission.
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
 */

package tom.library.bytecode;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;

import tom.library.adt.bytecode.*;
import tom.library.adt.bytecode.types.*;
import tom.library.adt.bytecode.types.tstringlist.*;
import tom.library.adt.bytecode.types.tlabellist.*;
import tom.library.adt.bytecode.types.tintlist.*;

import java.util.HashMap;

public class BytecodeGenerator extends ToolBox implements Opcodes {
           private static   tom.library.adt.bytecode.types.TMethodList  tom_append_list_MethodList( tom.library.adt.bytecode.types.TMethodList l1,  tom.library.adt.bytecode.types.TMethodList  l2) {     if( l1.isEmptyMethodList() ) {       return l2;     } else if( l2.isEmptyMethodList() ) {       return l1;     } else if(  l1.getTailMethodList() .isEmptyMethodList() ) {       return  tom.library.adt.bytecode.types.tmethodlist.ConsMethodList.make( l1.getHeadMethodList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tmethodlist.ConsMethodList.make( l1.getHeadMethodList() ,tom_append_list_MethodList( l1.getTailMethodList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TMethodList  tom_get_slice_MethodList( tom.library.adt.bytecode.types.TMethodList  begin,  tom.library.adt.bytecode.types.TMethodList  end, tom.library.adt.bytecode.types.TMethodList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyMethodList()  ||  (end== tom.library.adt.bytecode.types.tmethodlist.EmptyMethodList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.tmethodlist.ConsMethodList.make( begin.getHeadMethodList() ,( tom.library.adt.bytecode.types.TMethodList )tom_get_slice_MethodList( begin.getTailMethodList() ,end,tail)) ;   }      private static   tom.library.adt.bytecode.types.TInnerClassInfoList  tom_append_list_InnerClassInfoList( tom.library.adt.bytecode.types.TInnerClassInfoList l1,  tom.library.adt.bytecode.types.TInnerClassInfoList  l2) {     if( l1.isEmptyInnerClassInfoList() ) {       return l2;     } else if( l2.isEmptyInnerClassInfoList() ) {       return l1;     } else if(  l1.getTailInnerClassInfoList() .isEmptyInnerClassInfoList() ) {       return  tom.library.adt.bytecode.types.tinnerclassinfolist.ConsInnerClassInfoList.make( l1.getHeadInnerClassInfoList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tinnerclassinfolist.ConsInnerClassInfoList.make( l1.getHeadInnerClassInfoList() ,tom_append_list_InnerClassInfoList( l1.getTailInnerClassInfoList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TInnerClassInfoList  tom_get_slice_InnerClassInfoList( tom.library.adt.bytecode.types.TInnerClassInfoList  begin,  tom.library.adt.bytecode.types.TInnerClassInfoList  end, tom.library.adt.bytecode.types.TInnerClassInfoList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyInnerClassInfoList()  ||  (end== tom.library.adt.bytecode.types.tinnerclassinfolist.EmptyInnerClassInfoList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.tinnerclassinfolist.ConsInnerClassInfoList.make( begin.getHeadInnerClassInfoList() ,( tom.library.adt.bytecode.types.TInnerClassInfoList )tom_get_slice_InnerClassInfoList( begin.getTailInnerClassInfoList() ,end,tail)) ;   }      private static   tom.library.adt.bytecode.types.TTryCatchBlockList  tom_append_list_TryCatchBlockList( tom.library.adt.bytecode.types.TTryCatchBlockList l1,  tom.library.adt.bytecode.types.TTryCatchBlockList  l2) {     if( l1.isEmptyTryCatchBlockList() ) {       return l2;     } else if( l2.isEmptyTryCatchBlockList() ) {       return l1;     } else if(  l1.getTailTryCatchBlockList() .isEmptyTryCatchBlockList() ) {       return  tom.library.adt.bytecode.types.ttrycatchblocklist.ConsTryCatchBlockList.make( l1.getHeadTryCatchBlockList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.ttrycatchblocklist.ConsTryCatchBlockList.make( l1.getHeadTryCatchBlockList() ,tom_append_list_TryCatchBlockList( l1.getTailTryCatchBlockList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TTryCatchBlockList  tom_get_slice_TryCatchBlockList( tom.library.adt.bytecode.types.TTryCatchBlockList  begin,  tom.library.adt.bytecode.types.TTryCatchBlockList  end, tom.library.adt.bytecode.types.TTryCatchBlockList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyTryCatchBlockList()  ||  (end== tom.library.adt.bytecode.types.ttrycatchblocklist.EmptyTryCatchBlockList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.ttrycatchblocklist.ConsTryCatchBlockList.make( begin.getHeadTryCatchBlockList() ,( tom.library.adt.bytecode.types.TTryCatchBlockList )tom_get_slice_TryCatchBlockList( begin.getTailTryCatchBlockList() ,end,tail)) ;   }      private static   tom.library.adt.bytecode.types.TFieldList  tom_append_list_FieldList( tom.library.adt.bytecode.types.TFieldList l1,  tom.library.adt.bytecode.types.TFieldList  l2) {     if( l1.isEmptyFieldList() ) {       return l2;     } else if( l2.isEmptyFieldList() ) {       return l1;     } else if(  l1.getTailFieldList() .isEmptyFieldList() ) {       return  tom.library.adt.bytecode.types.tfieldlist.ConsFieldList.make( l1.getHeadFieldList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tfieldlist.ConsFieldList.make( l1.getHeadFieldList() ,tom_append_list_FieldList( l1.getTailFieldList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TFieldList  tom_get_slice_FieldList( tom.library.adt.bytecode.types.TFieldList  begin,  tom.library.adt.bytecode.types.TFieldList  end, tom.library.adt.bytecode.types.TFieldList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyFieldList()  ||  (end== tom.library.adt.bytecode.types.tfieldlist.EmptyFieldList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.tfieldlist.ConsFieldList.make( begin.getHeadFieldList() ,( tom.library.adt.bytecode.types.TFieldList )tom_get_slice_FieldList( begin.getTailFieldList() ,end,tail)) ;   }      private static   tom.library.adt.bytecode.types.TInstructionList  tom_append_list_InstructionList( tom.library.adt.bytecode.types.TInstructionList l1,  tom.library.adt.bytecode.types.TInstructionList  l2) {     if( l1.isEmptyInstructionList() ) {       return l2;     } else if( l2.isEmptyInstructionList() ) {       return l1;     } else if(  l1.getTailInstructionList() .isEmptyInstructionList() ) {       return  tom.library.adt.bytecode.types.tinstructionlist.ConsInstructionList.make( l1.getHeadInstructionList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tinstructionlist.ConsInstructionList.make( l1.getHeadInstructionList() ,tom_append_list_InstructionList( l1.getTailInstructionList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TInstructionList  tom_get_slice_InstructionList( tom.library.adt.bytecode.types.TInstructionList  begin,  tom.library.adt.bytecode.types.TInstructionList  end, tom.library.adt.bytecode.types.TInstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyInstructionList()  ||  (end== tom.library.adt.bytecode.types.tinstructionlist.EmptyInstructionList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.tinstructionlist.ConsInstructionList.make( begin.getHeadInstructionList() ,( tom.library.adt.bytecode.types.TInstructionList )tom_get_slice_InstructionList( begin.getTailInstructionList() ,end,tail)) ;   }      private static   tom.library.adt.bytecode.types.TLocalVariableList  tom_append_list_LocalVariableList( tom.library.adt.bytecode.types.TLocalVariableList l1,  tom.library.adt.bytecode.types.TLocalVariableList  l2) {     if( l1.isEmptyLocalVariableList() ) {       return l2;     } else if( l2.isEmptyLocalVariableList() ) {       return l1;     } else if(  l1.getTailLocalVariableList() .isEmptyLocalVariableList() ) {       return  tom.library.adt.bytecode.types.tlocalvariablelist.ConsLocalVariableList.make( l1.getHeadLocalVariableList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tlocalvariablelist.ConsLocalVariableList.make( l1.getHeadLocalVariableList() ,tom_append_list_LocalVariableList( l1.getTailLocalVariableList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TLocalVariableList  tom_get_slice_LocalVariableList( tom.library.adt.bytecode.types.TLocalVariableList  begin,  tom.library.adt.bytecode.types.TLocalVariableList  end, tom.library.adt.bytecode.types.TLocalVariableList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyLocalVariableList()  ||  (end== tom.library.adt.bytecode.types.tlocalvariablelist.EmptyLocalVariableList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.tlocalvariablelist.ConsLocalVariableList.make( begin.getHeadLocalVariableList() ,( tom.library.adt.bytecode.types.TLocalVariableList )tom_get_slice_LocalVariableList( begin.getTailLocalVariableList() ,end,tail)) ;   }    

  public byte[] toBytecode(TClass clazz){

    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

    {{if ( (clazz instanceof tom.library.adt.bytecode.types.TClass) ) {if ( ((( tom.library.adt.bytecode.types.TClass )clazz) instanceof tom.library.adt.bytecode.types.tclass.Class) ) { tom.library.adt.bytecode.types.TClassInfo  tomMatch588NameNumber_freshVar_1= (( tom.library.adt.bytecode.types.TClass )clazz).getinfo() ;if ( (tomMatch588NameNumber_freshVar_1 instanceof tom.library.adt.bytecode.types.tclassinfo.ClassInfo) ) { tom.library.adt.bytecode.types.TInnerClassInfoList  tom_innerClasses= tomMatch588NameNumber_freshVar_1.getinnerClasses() ; tom.library.adt.bytecode.types.TOuterClassInfo  tom_outerClass= tomMatch588NameNumber_freshVar_1.getouterClass() ; tom.library.adt.bytecode.types.TFieldList  tom_fields= (( tom.library.adt.bytecode.types.TClass )clazz).getfields() ; tom.library.adt.bytecode.types.TMethodList  tom_methods= (( tom.library.adt.bytecode.types.TClass )clazz).getmethods() ;



        // bytecode for the header
        cw.visit(V1_1, buildAccessValue( tomMatch588NameNumber_freshVar_1.getaccess() ),  tomMatch588NameNumber_freshVar_1.getname() , buildSignature( tomMatch588NameNumber_freshVar_1.getsignature() ), tomMatch588NameNumber_freshVar_1.getsuperName() , ((StringList) tomMatch588NameNumber_freshVar_1.getinterfaces() ).toArray(new String[0]));


        //bytecode for the inner classes

        {{if ( (tom_innerClasses instanceof tom.library.adt.bytecode.types.TInnerClassInfoList) ) {if ( (((( tom.library.adt.bytecode.types.TInnerClassInfoList )tom_innerClasses) instanceof tom.library.adt.bytecode.types.tinnerclassinfolist.ConsInnerClassInfoList) || ((( tom.library.adt.bytecode.types.TInnerClassInfoList )tom_innerClasses) instanceof tom.library.adt.bytecode.types.tinnerclassinfolist.EmptyInnerClassInfoList)) ) { tom.library.adt.bytecode.types.TInnerClassInfoList  tomMatch589NameNumber_end_4=(( tom.library.adt.bytecode.types.TInnerClassInfoList )tom_innerClasses);do {{if (!( tomMatch589NameNumber_end_4.isEmptyInnerClassInfoList() )) { tom.library.adt.bytecode.types.TInnerClassInfo  tom_innerClass= tomMatch589NameNumber_end_4.getHeadInnerClassInfoList() ;{{if ( (tom_innerClass instanceof tom.library.adt.bytecode.types.TInnerClassInfo) ) {if ( ((( tom.library.adt.bytecode.types.TInnerClassInfo )tom_innerClass) instanceof tom.library.adt.bytecode.types.tinnerclassinfo.InnerClassInfo) ) {



                cw.visitInnerClass( (( tom.library.adt.bytecode.types.TInnerClassInfo )tom_innerClass).getname() , (( tom.library.adt.bytecode.types.TInnerClassInfo )tom_innerClass).getouterName() , (( tom.library.adt.bytecode.types.TInnerClassInfo )tom_innerClass).getinnerName() ,buildAccessValue( (( tom.library.adt.bytecode.types.TInnerClassInfo )tom_innerClass).getaccess() ));
              }}}}

          }if ( tomMatch589NameNumber_end_4.isEmptyInnerClassInfoList() ) {tomMatch589NameNumber_end_4=(( tom.library.adt.bytecode.types.TInnerClassInfoList )tom_innerClasses);} else {tomMatch589NameNumber_end_4= tomMatch589NameNumber_end_4.getTailInnerClassInfoList() ;}}} while(!( (tomMatch589NameNumber_end_4==(( tom.library.adt.bytecode.types.TInnerClassInfoList )tom_innerClasses)) ));}}}}


        //bytecode for the outer class

        {{if ( (tom_outerClass instanceof tom.library.adt.bytecode.types.TOuterClassInfo) ) {if ( ((( tom.library.adt.bytecode.types.TOuterClassInfo )tom_outerClass) instanceof tom.library.adt.bytecode.types.touterclassinfo.OuterClassInfo) ) {

            cw.visitOuterClass( (( tom.library.adt.bytecode.types.TOuterClassInfo )tom_outerClass).getowner() , (( tom.library.adt.bytecode.types.TOuterClassInfo )tom_outerClass).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TOuterClassInfo )tom_outerClass).getdesc() ));
          }}}}


        //bytecode for the fields 

        {{if ( (tom_fields instanceof tom.library.adt.bytecode.types.TFieldList) ) {if ( (((( tom.library.adt.bytecode.types.TFieldList )tom_fields) instanceof tom.library.adt.bytecode.types.tfieldlist.ConsFieldList) || ((( tom.library.adt.bytecode.types.TFieldList )tom_fields) instanceof tom.library.adt.bytecode.types.tfieldlist.EmptyFieldList)) ) { tom.library.adt.bytecode.types.TFieldList  tomMatch592NameNumber_end_4=(( tom.library.adt.bytecode.types.TFieldList )tom_fields);do {{if (!( tomMatch592NameNumber_end_4.isEmptyFieldList() )) { tom.library.adt.bytecode.types.TField  tom_field= tomMatch592NameNumber_end_4.getHeadFieldList() ;{{if ( (tom_field instanceof tom.library.adt.bytecode.types.TField) ) {if ( ((( tom.library.adt.bytecode.types.TField )tom_field) instanceof tom.library.adt.bytecode.types.tfield.Field) ) {





               FieldVisitor fw = cw.visitField(buildAccessValue( (( tom.library.adt.bytecode.types.TField )tom_field).getaccess() ), (( tom.library.adt.bytecode.types.TField )tom_field).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TField )tom_field).getdesc() ),buildSignature( (( tom.library.adt.bytecode.types.TField )tom_field).getsignature() ),buildConstant( (( tom.library.adt.bytecode.types.TField )tom_field).getvalue() ));
               // we do not visit the annotations and attributes
               fw.visitEnd(); 
             }}}}

          }if ( tomMatch592NameNumber_end_4.isEmptyFieldList() ) {tomMatch592NameNumber_end_4=(( tom.library.adt.bytecode.types.TFieldList )tom_fields);} else {tomMatch592NameNumber_end_4= tomMatch592NameNumber_end_4.getTailFieldList() ;}}} while(!( (tomMatch592NameNumber_end_4==(( tom.library.adt.bytecode.types.TFieldList )tom_fields)) ));}}}}

        //bytecode for the methods

        MethodVisitor mw;
        {{if ( (tom_methods instanceof tom.library.adt.bytecode.types.TMethodList) ) {if ( (((( tom.library.adt.bytecode.types.TMethodList )tom_methods) instanceof tom.library.adt.bytecode.types.tmethodlist.ConsMethodList) || ((( tom.library.adt.bytecode.types.TMethodList )tom_methods) instanceof tom.library.adt.bytecode.types.tmethodlist.EmptyMethodList)) ) { tom.library.adt.bytecode.types.TMethodList  tomMatch594NameNumber_end_4=(( tom.library.adt.bytecode.types.TMethodList )tom_methods);do {{if (!( tomMatch594NameNumber_end_4.isEmptyMethodList() )) { tom.library.adt.bytecode.types.TMethod  tom_method= tomMatch594NameNumber_end_4.getHeadMethodList() ;{{if ( (tom_method instanceof tom.library.adt.bytecode.types.TMethod) ) {if ( ((( tom.library.adt.bytecode.types.TMethod )tom_method) instanceof tom.library.adt.bytecode.types.tmethod.Method) ) { tom.library.adt.bytecode.types.TMethodInfo  tomMatch595NameNumber_freshVar_1= (( tom.library.adt.bytecode.types.TMethod )tom_method).getinfo() ; tom.library.adt.bytecode.types.TMethodCode  tomMatch595NameNumber_freshVar_2= (( tom.library.adt.bytecode.types.TMethod )tom_method).getcode() ;if ( (tomMatch595NameNumber_freshVar_1 instanceof tom.library.adt.bytecode.types.tmethodinfo.MethodInfo) ) {if ( (tomMatch595NameNumber_freshVar_2 instanceof tom.library.adt.bytecode.types.tmethodcode.MethodCode) ) { tom.library.adt.bytecode.types.TInstructionList  tom_code= tomMatch595NameNumber_freshVar_2.getinstructions() ; tom.library.adt.bytecode.types.TLocalVariableList  tom_localVariables= tomMatch595NameNumber_freshVar_2.getlocalVariables() ; tom.library.adt.bytecode.types.TTryCatchBlockList  tom_tryCatchBlockLists= tomMatch595NameNumber_freshVar_2.gettryCatchBlocks() ;



                mw = cw.visitMethod(buildAccessValue( tomMatch595NameNumber_freshVar_1.getaccess() ),
                     tomMatch595NameNumber_freshVar_1.getname() ,
                    buildDescriptor( tomMatch595NameNumber_freshVar_1.getdesc() ),
                    buildSignature( tomMatch595NameNumber_freshVar_1.getsignature() ),
                    ((StringList) tomMatch595NameNumber_freshVar_1.getexceptions() ).toArray(new String[0]));

                mw.visitCode();

                HashMap labelMap = new HashMap();
                //bytecode for the method code 
                {{if ( (tom_code instanceof tom.library.adt.bytecode.types.TInstructionList) ) {if ( (((( tom.library.adt.bytecode.types.TInstructionList )tom_code) instanceof tom.library.adt.bytecode.types.tinstructionlist.ConsInstructionList) || ((( tom.library.adt.bytecode.types.TInstructionList )tom_code) instanceof tom.library.adt.bytecode.types.tinstructionlist.EmptyInstructionList)) ) { tom.library.adt.bytecode.types.TInstructionList  tomMatch596NameNumber_end_4=(( tom.library.adt.bytecode.types.TInstructionList )tom_code);do {{if (!( tomMatch596NameNumber_end_4.isEmptyInstructionList() )) { tom.library.adt.bytecode.types.TInstruction  tomMatch596NameNumber_freshVar_8= tomMatch596NameNumber_end_4.getHeadInstructionList() ;if ( (tomMatch596NameNumber_freshVar_8 instanceof tom.library.adt.bytecode.types.tinstruction.Anchor) ) {

                    labelMap.put( tomMatch596NameNumber_freshVar_8.getlabel() ,new Label());
                  }}if ( tomMatch596NameNumber_end_4.isEmptyInstructionList() ) {tomMatch596NameNumber_end_4=(( tom.library.adt.bytecode.types.TInstructionList )tom_code);} else {tomMatch596NameNumber_end_4= tomMatch596NameNumber_end_4.getTailInstructionList() ;}}} while(!( (tomMatch596NameNumber_end_4==(( tom.library.adt.bytecode.types.TInstructionList )tom_code)) ));}}}{if ( (tom_code instanceof tom.library.adt.bytecode.types.TInstructionList) ) {if ( (((( tom.library.adt.bytecode.types.TInstructionList )tom_code) instanceof tom.library.adt.bytecode.types.tinstructionlist.ConsInstructionList) || ((( tom.library.adt.bytecode.types.TInstructionList )tom_code) instanceof tom.library.adt.bytecode.types.tinstructionlist.EmptyInstructionList)) ) { tom.library.adt.bytecode.types.TInstructionList  tomMatch596NameNumber_end_13=(( tom.library.adt.bytecode.types.TInstructionList )tom_code);do {{if (!( tomMatch596NameNumber_end_13.isEmptyInstructionList() )) { tom.library.adt.bytecode.types.TInstruction  tom_inst= tomMatch596NameNumber_end_13.getHeadInstructionList() ;{{if ( (tom_inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )tom_inst) instanceof tom.library.adt.bytecode.types.tinstruction.Anchor) ) {



                        mw.visitLabel((Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )tom_inst).getlabel() ));
                      }}}{if ( (tom_inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {
addInstruction(mw,(( tom.library.adt.bytecode.types.TInstruction )tom_inst),labelMap);}}}

                  }if ( tomMatch596NameNumber_end_13.isEmptyInstructionList() ) {tomMatch596NameNumber_end_13=(( tom.library.adt.bytecode.types.TInstructionList )tom_code);} else {tomMatch596NameNumber_end_13= tomMatch596NameNumber_end_13.getTailInstructionList() ;}}} while(!( (tomMatch596NameNumber_end_13==(( tom.library.adt.bytecode.types.TInstructionList )tom_code)) ));}}}}{{if ( (tom_tryCatchBlockLists instanceof tom.library.adt.bytecode.types.TTryCatchBlockList) ) {if ( (((( tom.library.adt.bytecode.types.TTryCatchBlockList )tom_tryCatchBlockLists) instanceof tom.library.adt.bytecode.types.ttrycatchblocklist.ConsTryCatchBlockList) || ((( tom.library.adt.bytecode.types.TTryCatchBlockList )tom_tryCatchBlockLists) instanceof tom.library.adt.bytecode.types.ttrycatchblocklist.EmptyTryCatchBlockList)) ) { tom.library.adt.bytecode.types.TTryCatchBlockList  tomMatch598NameNumber_end_4=(( tom.library.adt.bytecode.types.TTryCatchBlockList )tom_tryCatchBlockLists);do {{if (!( tomMatch598NameNumber_end_4.isEmptyTryCatchBlockList() )) { tom.library.adt.bytecode.types.TTryCatchBlock  tomMatch598NameNumber_freshVar_10= tomMatch598NameNumber_end_4.getHeadTryCatchBlockList() ;if ( (tomMatch598NameNumber_freshVar_10 instanceof tom.library.adt.bytecode.types.ttrycatchblock.TryCatchBlock) ) { tom.library.adt.bytecode.types.TLabel  tom_start= tomMatch598NameNumber_freshVar_10.getstart() ; tom.library.adt.bytecode.types.TLabel  tom_end= tomMatch598NameNumber_freshVar_10.getend() ; tom.library.adt.bytecode.types.THandler  tom_handler= tomMatch598NameNumber_freshVar_10.gethandler() ;{{if ( (tom_handler instanceof tom.library.adt.bytecode.types.THandler) ) {if ( ((( tom.library.adt.bytecode.types.THandler )tom_handler) instanceof tom.library.adt.bytecode.types.thandler.CatchHandler) ) {






                        mw.visitTryCatchBlock((Label)labelMap.get(tom_start),(Label)labelMap.get(tom_end),(Label)labelMap.get( (( tom.library.adt.bytecode.types.THandler )tom_handler).gethandler() ), (( tom.library.adt.bytecode.types.THandler )tom_handler).gettype() );
                      }}}{if ( (tom_handler instanceof tom.library.adt.bytecode.types.THandler) ) {if ( ((( tom.library.adt.bytecode.types.THandler )tom_handler) instanceof tom.library.adt.bytecode.types.thandler.FinallyHandler) ) {

                        mw.visitTryCatchBlock((Label)labelMap.get(tom_start),(Label)labelMap.get(tom_end),(Label)labelMap.get( (( tom.library.adt.bytecode.types.THandler )tom_handler).gethandler() ),null);
                      }}}}

                  }}if ( tomMatch598NameNumber_end_4.isEmptyTryCatchBlockList() ) {tomMatch598NameNumber_end_4=(( tom.library.adt.bytecode.types.TTryCatchBlockList )tom_tryCatchBlockLists);} else {tomMatch598NameNumber_end_4= tomMatch598NameNumber_end_4.getTailTryCatchBlockList() ;}}} while(!( (tomMatch598NameNumber_end_4==(( tom.library.adt.bytecode.types.TTryCatchBlockList )tom_tryCatchBlockLists)) ));}}}}{{if ( (tom_localVariables instanceof tom.library.adt.bytecode.types.TLocalVariableList) ) {if ( (((( tom.library.adt.bytecode.types.TLocalVariableList )tom_localVariables) instanceof tom.library.adt.bytecode.types.tlocalvariablelist.ConsLocalVariableList) || ((( tom.library.adt.bytecode.types.TLocalVariableList )tom_localVariables) instanceof tom.library.adt.bytecode.types.tlocalvariablelist.EmptyLocalVariableList)) ) { tom.library.adt.bytecode.types.TLocalVariableList  tomMatch600NameNumber_end_4=(( tom.library.adt.bytecode.types.TLocalVariableList )tom_localVariables);do {{if (!( tomMatch600NameNumber_end_4.isEmptyLocalVariableList() )) { tom.library.adt.bytecode.types.TLocalVariable  tomMatch600NameNumber_freshVar_13= tomMatch600NameNumber_end_4.getHeadLocalVariableList() ;if ( (tomMatch600NameNumber_freshVar_13 instanceof tom.library.adt.bytecode.types.tlocalvariable.LocalVariable) ) {




                    mw.visitLocalVariable( tomMatch600NameNumber_freshVar_13.getname() ,  tomMatch600NameNumber_freshVar_13.gettypeDesc() , buildSignature( tomMatch600NameNumber_freshVar_13.getsignature() ), (Label)labelMap.get( tomMatch600NameNumber_freshVar_13.getstart() ), (Label)labelMap.get( tomMatch600NameNumber_freshVar_13.getend() ),  tomMatch600NameNumber_freshVar_13.getindex() );
                  }}if ( tomMatch600NameNumber_end_4.isEmptyLocalVariableList() ) {tomMatch600NameNumber_end_4=(( tom.library.adt.bytecode.types.TLocalVariableList )tom_localVariables);} else {tomMatch600NameNumber_end_4= tomMatch600NameNumber_end_4.getTailLocalVariableList() ;}}} while(!( (tomMatch600NameNumber_end_4==(( tom.library.adt.bytecode.types.TLocalVariableList )tom_localVariables)) ));}}}}


                mw.visitMaxs(0, 0);
                mw.visitEnd();
              }}}}}}

          }if ( tomMatch594NameNumber_end_4.isEmptyMethodList() ) {tomMatch594NameNumber_end_4=(( tom.library.adt.bytecode.types.TMethodList )tom_methods);} else {tomMatch594NameNumber_end_4= tomMatch594NameNumber_end_4.getTailMethodList() ;}}} while(!( (tomMatch594NameNumber_end_4==(( tom.library.adt.bytecode.types.TMethodList )tom_methods)) ));}}}}

      }}}}}



    // gets the bytecode
    return cw.toByteArray();
  }

  public void addInstruction(MethodVisitor mw,TInstruction inst,HashMap labelMap){
    {{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Nop) ) {

        mw.visitInsn(NOP);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Aconst_null) ) {

        mw.visitInsn(ACONST_NULL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iconst_m1) ) {

        mw.visitInsn(ICONST_M1);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iconst_0) ) {

        mw.visitInsn(ICONST_0);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iconst_1) ) {

        mw.visitInsn(ICONST_1);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iconst_2) ) {

        mw.visitInsn(ICONST_2);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iconst_3) ) {

        mw.visitInsn(ICONST_3);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iconst_4) ) {

        mw.visitInsn(ICONST_4);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iconst_5) ) {

        mw.visitInsn(ICONST_5);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lconst_0) ) {

        mw.visitInsn(LCONST_0);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lconst_1) ) {

        mw.visitInsn(LCONST_1);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fconst_0) ) {

        mw.visitInsn(FCONST_0);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fconst_1) ) {

        mw.visitInsn(FCONST_1);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fconst_2) ) {

        mw.visitInsn(FCONST_2);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dconst_0) ) {

        mw.visitInsn(DCONST_0);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dconst_1) ) {

        mw.visitInsn(DCONST_1);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iaload) ) {

        mw.visitInsn(IALOAD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Laload) ) {

        mw.visitInsn(LALOAD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Faload) ) {

        mw.visitInsn(FALOAD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Daload) ) {

        mw.visitInsn(DALOAD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Aaload) ) {

        mw.visitInsn(AALOAD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Baload) ) {

        mw.visitInsn(BALOAD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Caload) ) {

        mw.visitInsn(CALOAD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Saload) ) {

        mw.visitInsn(SALOAD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iastore) ) {

        mw.visitInsn(IASTORE);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lastore) ) {

        mw.visitInsn(LASTORE);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fastore) ) {

        mw.visitInsn(FASTORE);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dastore) ) {

        mw.visitInsn(DASTORE);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Aastore) ) {

        mw.visitInsn(AASTORE);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Bastore) ) {

        mw.visitInsn(BASTORE);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Castore) ) {

        mw.visitInsn(CASTORE);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Sastore) ) {

        mw.visitInsn(SASTORE);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Pop) ) {

        mw.visitInsn(POP);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Pop2) ) {

        mw.visitInsn(POP2);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dup) ) {

        mw.visitInsn(DUP);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dup_x1) ) {

        mw.visitInsn(DUP_X1);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dup_x2) ) {

        mw.visitInsn(DUP_X2);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dup2) ) {

        mw.visitInsn(DUP2);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dup2_x1) ) {

        mw.visitInsn(DUP2_X1);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dup2_x2) ) {

        mw.visitInsn(DUP2_X2);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Swap) ) {

        mw.visitInsn(SWAP);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iadd) ) {

        mw.visitInsn(IADD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ladd) ) {

        mw.visitInsn(LADD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fadd) ) {

        mw.visitInsn(FADD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dadd) ) {

        mw.visitInsn(DADD);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Isub) ) {

        mw.visitInsn(ISUB);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lsub) ) {

        mw.visitInsn(LSUB);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fsub) ) {

        mw.visitInsn(FSUB);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dsub) ) {

        mw.visitInsn(DSUB);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Imul) ) {

        mw.visitInsn(IMUL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lmul) ) {

        mw.visitInsn(LMUL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fmul) ) {

        mw.visitInsn(FMUL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dmul) ) {

        mw.visitInsn(DMUL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Idiv) ) {

        mw.visitInsn(IDIV);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ldiv) ) {

        mw.visitInsn(LDIV);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fdiv) ) {

        mw.visitInsn(FDIV);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ddiv) ) {

        mw.visitInsn(DDIV);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Irem) ) {

        mw.visitInsn(IREM);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lrem) ) {

        mw.visitInsn(LREM);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Frem) ) {

        mw.visitInsn(FREM);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Drem) ) {

        mw.visitInsn(DREM);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ineg) ) {

        mw.visitInsn(INEG);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lneg) ) {

        mw.visitInsn(LNEG);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fneg) ) {

        mw.visitInsn(FNEG);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dneg) ) {

        mw.visitInsn(DNEG);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ishl) ) {

        mw.visitInsn(ISHL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lshl) ) {

        mw.visitInsn(LSHL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ishr) ) {

        mw.visitInsn(ISHR);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lshr) ) {

        mw.visitInsn(LSHR);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iushr) ) {

        mw.visitInsn(IUSHR);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lushr) ) {

        mw.visitInsn(LUSHR);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iand) ) {

        mw.visitInsn(IAND);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Land) ) {

        mw.visitInsn(LAND);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ior) ) {

        mw.visitInsn(IOR);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lor) ) {

        mw.visitInsn(LOR);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ixor) ) {

        mw.visitInsn(IXOR);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lxor) ) {

        mw.visitInsn(LXOR);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.I2l) ) {

        mw.visitInsn(I2L);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.I2f) ) {

        mw.visitInsn(I2F);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.I2d) ) {

        mw.visitInsn(I2D);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.L2i) ) {

        mw.visitInsn(L2I);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.L2f) ) {

        mw.visitInsn(L2F);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.L2d) ) {

        mw.visitInsn(L2D);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.F2i) ) {

        mw.visitInsn(F2I);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.F2l) ) {

        mw.visitInsn(F2L);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.F2d) ) {

        mw.visitInsn(F2D);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.D2i) ) {

        mw.visitInsn(D2I);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.D2l) ) {

        mw.visitInsn(D2L);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.D2f) ) {

        mw.visitInsn(D2F);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.I2b) ) {

        mw.visitInsn(I2B);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.I2c) ) {

        mw.visitInsn(I2C);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.I2s) ) {

        mw.visitInsn(I2S);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lcmp) ) {

        mw.visitInsn(LCMP);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fcmpl) ) {

        mw.visitInsn(FCMPL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fcmpg) ) {

        mw.visitInsn(FCMPG);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dcmpl) ) {

        mw.visitInsn(DCMPL);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dcmpg) ) {

        mw.visitInsn(DCMPG);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ireturn) ) {

        mw.visitInsn(IRETURN);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lreturn) ) {

        mw.visitInsn(LRETURN);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Freturn) ) {

        mw.visitInsn(FRETURN);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dreturn) ) {

        mw.visitInsn(DRETURN);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Areturn) ) {

        mw.visitInsn(ARETURN);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Return) ) {

        mw.visitInsn(RETURN);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Arraylength) ) {

        mw.visitInsn(ARRAYLENGTH);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Athrow) ) {

        mw.visitInsn(ATHROW);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Monitorenter) ) {

        mw.visitInsn(MONITORENTER);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Monitorexit) ) {

        mw.visitInsn(MONITOREXIT);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Getstatic) ) {

        mw.visitFieldInsn(GETSTATIC, (( tom.library.adt.bytecode.types.TInstruction )inst).getowner() , (( tom.library.adt.bytecode.types.TInstruction )inst).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TInstruction )inst).getfieldDesc() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Putstatic) ) {

        mw.visitFieldInsn(PUTSTATIC, (( tom.library.adt.bytecode.types.TInstruction )inst).getowner() , (( tom.library.adt.bytecode.types.TInstruction )inst).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TInstruction )inst).getfieldDesc() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Getfield) ) {

        mw.visitFieldInsn(GETFIELD, (( tom.library.adt.bytecode.types.TInstruction )inst).getowner() , (( tom.library.adt.bytecode.types.TInstruction )inst).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TInstruction )inst).getfieldDesc() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Putfield) ) {

        mw.visitFieldInsn(PUTFIELD, (( tom.library.adt.bytecode.types.TInstruction )inst).getowner() , (( tom.library.adt.bytecode.types.TInstruction )inst).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TInstruction )inst).getfieldDesc() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Bipush) ) {

        mw.visitIntInsn(BIPUSH, (( tom.library.adt.bytecode.types.TInstruction )inst).getoperand() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Sipush) ) {

        mw.visitIntInsn(SIPUSH, (( tom.library.adt.bytecode.types.TInstruction )inst).getoperand() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Newarray) ) {

        mw.visitIntInsn(NEWARRAY, (( tom.library.adt.bytecode.types.TInstruction )inst).getoperand() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ifeq) ) {

        mw.visitJumpInsn(IFEQ,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ifne) ) {

        mw.visitJumpInsn(IFNE,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iflt) ) {

        mw.visitJumpInsn(IFLT,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ifge) ) {

        mw.visitJumpInsn(IFGE,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ifgt) ) {

        mw.visitJumpInsn(IFGT,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ifle) ) {

        mw.visitJumpInsn(IFLE,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.If_icmpeq) ) {

        mw.visitJumpInsn(IF_ICMPEQ,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.If_icmpne) ) {

        mw.visitJumpInsn(IF_ICMPNE,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.If_icmplt) ) {

        mw.visitJumpInsn(IF_ICMPLT,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.If_icmpge) ) {

        mw.visitJumpInsn(IF_ICMPGE,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.If_icmpgt) ) {

        mw.visitJumpInsn(IF_ICMPGT,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.If_icmple) ) {

        mw.visitJumpInsn(IF_ICMPLE,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.If_acmpeq) ) {

        mw.visitJumpInsn(IF_ACMPEQ,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.If_acmpne) ) {

        mw.visitJumpInsn(IF_ACMPNE,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Goto) ) {

        mw.visitJumpInsn(GOTO,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Jsr) ) {

        mw.visitJumpInsn(JSR,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ifnull) ) {

        mw.visitJumpInsn(IFNULL,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ifnonnull) ) {

        mw.visitJumpInsn(IFNONNULL,(Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getlabel() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Invokevirtual) ) {

        mw.visitMethodInsn(INVOKEVIRTUAL, (( tom.library.adt.bytecode.types.TInstruction )inst).getowner() , (( tom.library.adt.bytecode.types.TInstruction )inst).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TInstruction )inst).getmethodDesc() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Invokespecial) ) {

        mw.visitMethodInsn(INVOKESPECIAL, (( tom.library.adt.bytecode.types.TInstruction )inst).getowner() , (( tom.library.adt.bytecode.types.TInstruction )inst).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TInstruction )inst).getmethodDesc() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Invokestatic) ) {

        mw.visitMethodInsn(INVOKESTATIC, (( tom.library.adt.bytecode.types.TInstruction )inst).getowner() , (( tom.library.adt.bytecode.types.TInstruction )inst).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TInstruction )inst).getmethodDesc() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Invokeinterface) ) {

        mw.visitMethodInsn(INVOKEINTERFACE, (( tom.library.adt.bytecode.types.TInstruction )inst).getowner() , (( tom.library.adt.bytecode.types.TInstruction )inst).getname() ,buildDescriptor( (( tom.library.adt.bytecode.types.TInstruction )inst).getmethodDesc() ));
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.New) ) {


        mw.visitTypeInsn(NEW, (( tom.library.adt.bytecode.types.TInstruction )inst).gettypeDesc() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Anewarray) ) {

        mw.visitTypeInsn(ANEWARRAY, (( tom.library.adt.bytecode.types.TInstruction )inst).gettypeDesc() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Checkcast) ) {

        mw.visitTypeInsn(CHECKCAST, (( tom.library.adt.bytecode.types.TInstruction )inst).gettypeDesc() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Instanceof) ) {

        mw.visitTypeInsn(INSTANCEOF, (( tom.library.adt.bytecode.types.TInstruction )inst).gettypeDesc() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iload) ) {

        mw.visitVarInsn(ILOAD, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lload) ) {

        mw.visitVarInsn(LLOAD, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fload) ) {

        mw.visitVarInsn(FLOAD, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dload) ) {

        mw.visitVarInsn(DLOAD, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Aload) ) {

        mw.visitVarInsn(ALOAD, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Istore) ) {

        mw.visitVarInsn(ISTORE, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lstore) ) {

        mw.visitVarInsn(LSTORE, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Fstore) ) {

        mw.visitVarInsn(FSTORE, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Dstore) ) {

        mw.visitVarInsn(DSTORE, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Astore) ) {

        mw.visitVarInsn(ASTORE, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ret) ) {

        mw.visitVarInsn(RET, (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Iinc) ) {

        mw.visitIincInsn( (( tom.library.adt.bytecode.types.TInstruction )inst).getvar() , (( tom.library.adt.bytecode.types.TInstruction )inst).getincr() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Ldc) ) {

        mw.visitLdcInsn(buildConstant( (( tom.library.adt.bytecode.types.TInstruction )inst).getcst() )); 
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Multianewarray) ) {


        mw.visitMultiANewArrayInsn( (( tom.library.adt.bytecode.types.TInstruction )inst).gettypeDesc() , (( tom.library.adt.bytecode.types.TInstruction )inst).getdims() );
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Tableswitch) ) {


        TLabel[] tlabelTab = ((LabelList) (( tom.library.adt.bytecode.types.TInstruction )inst).getlabels() ).toArray(new TLabel[0]);
        Label[] labelTab = null;
        if(tlabelTab != null){
          labelTab = new Label[tlabelTab.length];
          for(int i=0;i<labelTab.length;i++){
            labelTab[i]=(Label)labelMap.get(tlabelTab[i]);
          }
        }
        mw.visitTableSwitchInsn( (( tom.library.adt.bytecode.types.TInstruction )inst).getmin() ,  (( tom.library.adt.bytecode.types.TInstruction )inst).getmax() , (Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getdflt() ), labelTab);
      }}}{if ( (inst instanceof tom.library.adt.bytecode.types.TInstruction) ) {if ( ((( tom.library.adt.bytecode.types.TInstruction )inst) instanceof tom.library.adt.bytecode.types.tinstruction.Lookupswitch) ) { tom.library.adt.bytecode.types.TintList  tom_keys= (( tom.library.adt.bytecode.types.TInstruction )inst).getkeys() ;


        TLabel[] tlabelTab = ((LabelList) (( tom.library.adt.bytecode.types.TInstruction )inst).getlabels() ).toArray(new TLabel[0]);
        Label[] labelTab = null;
        if(tlabelTab != null){
          labelTab = new Label[tlabelTab.length];
          for(int i=0;i<labelTab.length;i++){
            labelTab[i]=(Label)labelMap.get(tlabelTab[i]);
          }
        }
        int[] array = new int[((intList)tom_keys).length()];
        java.util.Iterator<Integer> it = ((intList)tom_keys).iterator();
        for(int i=0 ; it.hasNext() ; i++) {
          array[i] = it.next();
        }
        mw.visitLookupSwitchInsn((Label)labelMap.get( (( tom.library.adt.bytecode.types.TInstruction )inst).getdflt() ),array,labelTab);
      }}}}

  }
}
