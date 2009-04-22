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

import org.objectweb.asm.*;

import tom.library.adt.bytecode.*;
import tom.library.adt.bytecode.types.*;
import java.io.IOException;

import tom.library.bytecode.*;
import tom.library.adt.bytecode.*;
import tom.library.adt.bytecode.types.*;


public class BytecodeReader implements ClassVisitor {
           private static   tom.library.adt.bytecode.types.TMethodList  tom_append_list_MethodList( tom.library.adt.bytecode.types.TMethodList l1,  tom.library.adt.bytecode.types.TMethodList  l2) {     if( l1.isEmptyMethodList() ) {       return l2;     } else if( l2.isEmptyMethodList() ) {       return l1;     } else if(  l1.getTailMethodList() .isEmptyMethodList() ) {       return  tom.library.adt.bytecode.types.tmethodlist.ConsMethodList.make( l1.getHeadMethodList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tmethodlist.ConsMethodList.make( l1.getHeadMethodList() ,tom_append_list_MethodList( l1.getTailMethodList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TMethodList  tom_get_slice_MethodList( tom.library.adt.bytecode.types.TMethodList  begin,  tom.library.adt.bytecode.types.TMethodList  end, tom.library.adt.bytecode.types.TMethodList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyMethodList()  ||  (end== tom.library.adt.bytecode.types.tmethodlist.EmptyMethodList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.tmethodlist.ConsMethodList.make( begin.getHeadMethodList() ,( tom.library.adt.bytecode.types.TMethodList )tom_get_slice_MethodList( begin.getTailMethodList() ,end,tail)) ;   }      private static   tom.library.adt.bytecode.types.TInnerClassInfoList  tom_append_list_InnerClassInfoList( tom.library.adt.bytecode.types.TInnerClassInfoList l1,  tom.library.adt.bytecode.types.TInnerClassInfoList  l2) {     if( l1.isEmptyInnerClassInfoList() ) {       return l2;     } else if( l2.isEmptyInnerClassInfoList() ) {       return l1;     } else if(  l1.getTailInnerClassInfoList() .isEmptyInnerClassInfoList() ) {       return  tom.library.adt.bytecode.types.tinnerclassinfolist.ConsInnerClassInfoList.make( l1.getHeadInnerClassInfoList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tinnerclassinfolist.ConsInnerClassInfoList.make( l1.getHeadInnerClassInfoList() ,tom_append_list_InnerClassInfoList( l1.getTailInnerClassInfoList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TInnerClassInfoList  tom_get_slice_InnerClassInfoList( tom.library.adt.bytecode.types.TInnerClassInfoList  begin,  tom.library.adt.bytecode.types.TInnerClassInfoList  end, tom.library.adt.bytecode.types.TInnerClassInfoList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyInnerClassInfoList()  ||  (end== tom.library.adt.bytecode.types.tinnerclassinfolist.EmptyInnerClassInfoList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.tinnerclassinfolist.ConsInnerClassInfoList.make( begin.getHeadInnerClassInfoList() ,( tom.library.adt.bytecode.types.TInnerClassInfoList )tom_get_slice_InnerClassInfoList( begin.getTailInnerClassInfoList() ,end,tail)) ;   }      private static   tom.library.adt.bytecode.types.TFieldList  tom_append_list_FieldList( tom.library.adt.bytecode.types.TFieldList l1,  tom.library.adt.bytecode.types.TFieldList  l2) {     if( l1.isEmptyFieldList() ) {       return l2;     } else if( l2.isEmptyFieldList() ) {       return l1;     } else if(  l1.getTailFieldList() .isEmptyFieldList() ) {       return  tom.library.adt.bytecode.types.tfieldlist.ConsFieldList.make( l1.getHeadFieldList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tfieldlist.ConsFieldList.make( l1.getHeadFieldList() ,tom_append_list_FieldList( l1.getTailFieldList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TFieldList  tom_get_slice_FieldList( tom.library.adt.bytecode.types.TFieldList  begin,  tom.library.adt.bytecode.types.TFieldList  end, tom.library.adt.bytecode.types.TFieldList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyFieldList()  ||  (end== tom.library.adt.bytecode.types.tfieldlist.EmptyFieldList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.bytecode.types.tfieldlist.ConsFieldList.make( begin.getHeadFieldList() ,( tom.library.adt.bytecode.types.TFieldList )tom_get_slice_FieldList( begin.getTailFieldList() ,end,tail)) ;   }    


  public BytecodeReader(String className){
    super();
    try {
      ClassAdapter ca = new ClassAdapter(this);
      ClassReader cr = new ClassReader(className);
      cr.accept(ca, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
    } catch(IOException io) {
      System.err.println("Class not found : " + className);
    }
  }

  private TClass clazz;

  public TClass getTClass() {
    return clazz;
  }

  public void appendMethod(TMethod method){
    TMethodList l = clazz.getmethods();
    clazz = clazz.setmethods(tom_append_list_MethodList(l, tom.library.adt.bytecode.types.tmethodlist.ConsMethodList.make(method, tom.library.adt.bytecode.types.tmethodlist.EmptyMethodList.make() ) ));
  }

  public void appendField(TField field) {
    TFieldList l = clazz.getfields();
    clazz = clazz.setfields(tom_append_list_FieldList(l, tom.library.adt.bytecode.types.tfieldlist.ConsFieldList.make(field, tom.library.adt.bytecode.types.tfieldlist.EmptyFieldList.make() ) ));
  }

  public void appendInnerClass(TInnerClassInfo info) {
    TClassInfo i = clazz.getinfo();
    TInnerClassInfoList l = i.getinnerClasses();
    clazz = clazz.setinfo(i.setinnerClasses(tom_append_list_InnerClassInfoList(l, tom.library.adt.bytecode.types.tinnerclassinfolist.ConsInnerClassInfoList.make(info, tom.library.adt.bytecode.types.tinnerclassinfolist.EmptyInnerClassInfoList.make() ) )));
  }

  public void visit(
      int version,
      int access,
      String name,
      String signature,
      String superName,
      String[] interfaces) {
    TClassInfo info =  tom.library.adt.bytecode.types.tclassinfo.ClassInfo.make(name,  tom.library.adt.bytecode.types.tsignature.Signature.make(signature) , ToolBox.buildTAccess(access), superName, ToolBox.buildTStringList(interfaces),  tom.library.adt.bytecode.types.tinnerclassinfolist.EmptyInnerClassInfoList.make() ,  tom.library.adt.bytecode.types.touterclassinfo.EmptyOuterClassInfo.make() ) ;
    clazz =  tom.library.adt.bytecode.types.tclass.Class.make(info,  tom.library.adt.bytecode.types.tfieldlist.EmptyFieldList.make() ,  tom.library.adt.bytecode.types.tmethodlist.EmptyMethodList.make() ) ;
  }

  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    // TODO
    return null;
  }

  public void visitAttribute(Attribute attr) {
    // TODO
  }

  public void visitEnd() {
    // do nothing
  }

  public FieldVisitor visitField(
      int access,
      String name,
      String desc,
      String signature,
      Object value) {
    TField field =  tom.library.adt.bytecode.types.tfield.Field.make(ToolBox.buildTAccess(access), name, ToolBox.buildTFieldDescriptor(desc),  tom.library.adt.bytecode.types.tsignature.Signature.make(signature) , ToolBox.buildTValue(value)) ;
    appendField(field);

    return null;
  }

  public void visitInnerClass(
      String name,
      String outerName,
      String innerName,
      int access) {
    TInnerClassInfo info =  tom.library.adt.bytecode.types.tinnerclassinfo.InnerClassInfo.make(name, outerName, innerName, ToolBox.buildTAccess(access)) ;
    appendInnerClass(info);
  }

  public MethodVisitor visitMethod(
      int access,
      String name,
      String desc,
      String signature,
      String[] exceptions) {
    return new TMethodGenerator(this, ToolBox.buildTAccess(access), name, ToolBox.buildTMethodDescriptor(desc),  tom.library.adt.bytecode.types.tsignature.Signature.make(signature) , ToolBox.buildTStringList(exceptions));
  }

  public void visitOuterClass(String owner, String name, String desc) {
    TOuterClassInfo info =  tom.library.adt.bytecode.types.touterclassinfo.OuterClassInfo.make(owner, name, ToolBox.buildTMethodDescriptor(desc)) ;
    TClassInfo i = clazz.getinfo();
    clazz = clazz.setinfo(i.setouterClass(info));
  }

  public void visitSource(String source, String debug) {
    // do nothing
  }
}

