/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (C) 2006 INRIA
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
 * Antoine Reilles  e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.gom.expander;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.gom.*;
import tom.gom.adt.gom.types.*;
import tom.gom.parser.GomLexer;
import tom.gom.parser.GomParser;
import tom.platform.PlatformLogRecord;
import antlr.RecognitionException;
import antlr.TokenStreamException;

public class GomExpander {
  private GomStreamManager streamManager;

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_char( char  t1,  char  t2) {  return  (t1==t2)  ;}private static boolean tom_terms_equal_Character(Object t1, Object t2) {  return  (t1.equals(t2))  ;} private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */ private static boolean tom_terms_equal_int( int  t1,  int  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_double( double  t1,  double  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_ATerm(Object t1, Object t2) {  return  t1 == t2 ;} /* Generated by TOM (version 2.4alpha): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_ATermList(Object l1, Object l2) {  return  l1==l2  ;} private static boolean tom_terms_equal_Slot(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Hookkind(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ArgList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomModuleList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GrammarList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TypedProduction(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookDeclList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OperatorDeclList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SectionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OperatorDecl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SortDeclList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomModule(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Section(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomTypeList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ProductionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ImportList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Sort(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookDecl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SortDecl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ModuleDeclList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Grammar(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_FieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SortList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Arg(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Production(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Field(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomModuleName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ModuleDecl(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ImportedModule(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_GomModule( tom.gom.adt.gom.types.GomModule  t) {  return  (t!=null) && t.isGomModule()  ;}private static  tom.gom.adt.gom.types.GomModuleName  tom_get_slot_GomModule_moduleName( tom.gom.adt.gom.types.GomModule  t) {  return  t.getmoduleName()  ;}private static  tom.gom.adt.gom.types.SectionList  tom_get_slot_GomModule_sectionList( tom.gom.adt.gom.types.GomModule  t) {  return  t.getsectionList()  ;}private static boolean tom_is_fun_sym_Imports( tom.gom.adt.gom.types.Section  t) {  return  (t!=null) && t.isImports()  ;}private static  tom.gom.adt.gom.types.ImportList  tom_get_slot_Imports_importList( tom.gom.adt.gom.types.Section  t) {  return  t.getimportList()  ;}private static boolean tom_is_fun_sym_concGomModule( tom.gom.adt.gom.types.GomModuleList  t) {  return  t instanceof tom.gom.adt.gom.types.gommodulelist.ConsconcGomModule || t instanceof tom.gom.adt.gom.types.gommodulelist.EmptyconcGomModule  ;}private static  tom.gom.adt.gom.types.GomModuleList  tom_empty_list_concGomModule() { return  tom.gom.adt.gom.types.gommodulelist.EmptyconcGomModule.make() ; }private static  tom.gom.adt.gom.types.GomModuleList  tom_cons_list_concGomModule( tom.gom.adt.gom.types.GomModule  e,  tom.gom.adt.gom.types.GomModuleList  l) { return  tom.gom.adt.gom.types.gommodulelist.ConsconcGomModule.make(e,l) ; }private static  tom.gom.adt.gom.types.GomModule  tom_get_head_concGomModule_GomModuleList( tom.gom.adt.gom.types.GomModuleList  l) {  return  l.getHeadconcGomModule()  ;}private static  tom.gom.adt.gom.types.GomModuleList  tom_get_tail_concGomModule_GomModuleList( tom.gom.adt.gom.types.GomModuleList  l) {  return  l.getTailconcGomModule()  ;}private static boolean tom_is_empty_concGomModule_GomModuleList( tom.gom.adt.gom.types.GomModuleList  l) {  return  l.isEmptyconcGomModule()  ;}private static  tom.gom.adt.gom.types.GomModuleList  tom_append_list_concGomModule( tom.gom.adt.gom.types.GomModuleList  l1,  tom.gom.adt.gom.types.GomModuleList  l2) {    if(tom_is_empty_concGomModule_GomModuleList(l1)) {     return l2;    } else if(tom_is_empty_concGomModule_GomModuleList(l2)) {     return l1;    } else if(tom_is_empty_concGomModule_GomModuleList(( tom.gom.adt.gom.types.GomModuleList )tom_get_tail_concGomModule_GomModuleList(l1))) {     return ( tom.gom.adt.gom.types.GomModuleList )tom_cons_list_concGomModule(( tom.gom.adt.gom.types.GomModule )tom_get_head_concGomModule_GomModuleList(l1),l2);    } else {      return ( tom.gom.adt.gom.types.GomModuleList )tom_cons_list_concGomModule(( tom.gom.adt.gom.types.GomModule )tom_get_head_concGomModule_GomModuleList(l1),tom_append_list_concGomModule(( tom.gom.adt.gom.types.GomModuleList )tom_get_tail_concGomModule_GomModuleList(l1),l2));    }   }  private static  tom.gom.adt.gom.types.GomModuleList  tom_get_slice_concGomModule( tom.gom.adt.gom.types.GomModuleList  begin,  tom.gom.adt.gom.types.GomModuleList  end) {    if(tom_terms_equal_GomModuleList(begin,end)) {      return ( tom.gom.adt.gom.types.GomModuleList )tom_empty_list_concGomModule();    } else {      return ( tom.gom.adt.gom.types.GomModuleList )tom_cons_list_concGomModule(( tom.gom.adt.gom.types.GomModule )tom_get_head_concGomModule_GomModuleList(begin),( tom.gom.adt.gom.types.GomModuleList )tom_get_slice_concGomModule(( tom.gom.adt.gom.types.GomModuleList )tom_get_tail_concGomModule_GomModuleList(begin),end));    }   }  private static boolean tom_is_fun_sym_concSection( tom.gom.adt.gom.types.SectionList  t) {  return  t instanceof tom.gom.adt.gom.types.sectionlist.ConsconcSection || t instanceof tom.gom.adt.gom.types.sectionlist.EmptyconcSection  ;}private static  tom.gom.adt.gom.types.SectionList  tom_empty_list_concSection() { return  tom.gom.adt.gom.types.sectionlist.EmptyconcSection.make() ; }private static  tom.gom.adt.gom.types.SectionList  tom_cons_list_concSection( tom.gom.adt.gom.types.Section  e,  tom.gom.adt.gom.types.SectionList  l) { return  tom.gom.adt.gom.types.sectionlist.ConsconcSection.make(e,l) ; }private static  tom.gom.adt.gom.types.Section  tom_get_head_concSection_SectionList( tom.gom.adt.gom.types.SectionList  l) {  return  l.getHeadconcSection()  ;}private static  tom.gom.adt.gom.types.SectionList  tom_get_tail_concSection_SectionList( tom.gom.adt.gom.types.SectionList  l) {  return  l.getTailconcSection()  ;}private static boolean tom_is_empty_concSection_SectionList( tom.gom.adt.gom.types.SectionList  l) {  return  l.isEmptyconcSection()  ;}private static  tom.gom.adt.gom.types.SectionList  tom_append_list_concSection( tom.gom.adt.gom.types.SectionList  l1,  tom.gom.adt.gom.types.SectionList  l2) {    if(tom_is_empty_concSection_SectionList(l1)) {     return l2;    } else if(tom_is_empty_concSection_SectionList(l2)) {     return l1;    } else if(tom_is_empty_concSection_SectionList(( tom.gom.adt.gom.types.SectionList )tom_get_tail_concSection_SectionList(l1))) {     return ( tom.gom.adt.gom.types.SectionList )tom_cons_list_concSection(( tom.gom.adt.gom.types.Section )tom_get_head_concSection_SectionList(l1),l2);    } else {      return ( tom.gom.adt.gom.types.SectionList )tom_cons_list_concSection(( tom.gom.adt.gom.types.Section )tom_get_head_concSection_SectionList(l1),tom_append_list_concSection(( tom.gom.adt.gom.types.SectionList )tom_get_tail_concSection_SectionList(l1),l2));    }   }  private static  tom.gom.adt.gom.types.SectionList  tom_get_slice_concSection( tom.gom.adt.gom.types.SectionList  begin,  tom.gom.adt.gom.types.SectionList  end) {    if(tom_terms_equal_SectionList(begin,end)) {      return ( tom.gom.adt.gom.types.SectionList )tom_empty_list_concSection();    } else {      return ( tom.gom.adt.gom.types.SectionList )tom_cons_list_concSection(( tom.gom.adt.gom.types.Section )tom_get_head_concSection_SectionList(begin),( tom.gom.adt.gom.types.SectionList )tom_get_slice_concSection(( tom.gom.adt.gom.types.SectionList )tom_get_tail_concSection_SectionList(begin),end));    }   }  private static boolean tom_is_fun_sym_concImportedModule( tom.gom.adt.gom.types.ImportList  t) {  return  t instanceof tom.gom.adt.gom.types.importlist.ConsconcImportedModule || t instanceof tom.gom.adt.gom.types.importlist.EmptyconcImportedModule  ;}private static  tom.gom.adt.gom.types.ImportList  tom_empty_list_concImportedModule() { return  tom.gom.adt.gom.types.importlist.EmptyconcImportedModule.make() ; }private static  tom.gom.adt.gom.types.ImportList  tom_cons_list_concImportedModule( tom.gom.adt.gom.types.ImportedModule  e,  tom.gom.adt.gom.types.ImportList  l) { return  tom.gom.adt.gom.types.importlist.ConsconcImportedModule.make(e,l) ; }private static  tom.gom.adt.gom.types.ImportedModule  tom_get_head_concImportedModule_ImportList( tom.gom.adt.gom.types.ImportList  l) {  return  l.getHeadconcImportedModule()  ;}private static  tom.gom.adt.gom.types.ImportList  tom_get_tail_concImportedModule_ImportList( tom.gom.adt.gom.types.ImportList  l) {  return  l.getTailconcImportedModule()  ;}private static boolean tom_is_empty_concImportedModule_ImportList( tom.gom.adt.gom.types.ImportList  l) {  return  l.isEmptyconcImportedModule()  ;}private static  tom.gom.adt.gom.types.ImportList  tom_append_list_concImportedModule( tom.gom.adt.gom.types.ImportList  l1,  tom.gom.adt.gom.types.ImportList  l2) {    if(tom_is_empty_concImportedModule_ImportList(l1)) {     return l2;    } else if(tom_is_empty_concImportedModule_ImportList(l2)) {     return l1;    } else if(tom_is_empty_concImportedModule_ImportList(( tom.gom.adt.gom.types.ImportList )tom_get_tail_concImportedModule_ImportList(l1))) {     return ( tom.gom.adt.gom.types.ImportList )tom_cons_list_concImportedModule(( tom.gom.adt.gom.types.ImportedModule )tom_get_head_concImportedModule_ImportList(l1),l2);    } else {      return ( tom.gom.adt.gom.types.ImportList )tom_cons_list_concImportedModule(( tom.gom.adt.gom.types.ImportedModule )tom_get_head_concImportedModule_ImportList(l1),tom_append_list_concImportedModule(( tom.gom.adt.gom.types.ImportList )tom_get_tail_concImportedModule_ImportList(l1),l2));    }   }  private static  tom.gom.adt.gom.types.ImportList  tom_get_slice_concImportedModule( tom.gom.adt.gom.types.ImportList  begin,  tom.gom.adt.gom.types.ImportList  end) {    if(tom_terms_equal_ImportList(begin,end)) {      return ( tom.gom.adt.gom.types.ImportList )tom_empty_list_concImportedModule();    } else {      return ( tom.gom.adt.gom.types.ImportList )tom_cons_list_concImportedModule(( tom.gom.adt.gom.types.ImportedModule )tom_get_head_concImportedModule_ImportList(begin),( tom.gom.adt.gom.types.ImportList )tom_get_slice_concImportedModule(( tom.gom.adt.gom.types.ImportList )tom_get_tail_concImportedModule_ImportList(begin),end));    }   }   

  public GomExpander(GomStreamManager streamManager) {
    this.streamManager = streamManager;
  }

  private GomEnvironment environment() {
    return GomEnvironment.getInstance();
  }

  /*
   * Compute the transitive closure of imported modules
   */
  public GomModuleList expand(GomModule module) {
    GomModuleList result = tom_cons_list_concGomModule(module,tom_empty_list_concGomModule());
    Set alreadyParsedModule = new HashSet();
    alreadyParsedModule.add(module.getmoduleName());
    Set moduleToAnalyse = generateModuleToAnalyseSet(module, alreadyParsedModule);
    getLogger().log(Level.FINER, "GomExpander:moduleToAnalyse {0}",
        new Object[]{moduleToAnalyse});

    while (!moduleToAnalyse.isEmpty()) {
      HashSet newModuleToAnalyse = new HashSet();
      Iterator it = moduleToAnalyse.iterator();

      while(it.hasNext()) {
        GomModuleName moduleNameName = (GomModuleName)it.next();
        String moduleName = moduleNameName.getname();

        if(!environment().isBuiltin(moduleName)
            && !alreadyParsedModule.contains(moduleNameName)) {
          GomModule importedModule = parse(moduleName);
          if(importedModule == null) {
            return null;
          }
          result = tom_append_list_concGomModule(result,tom_cons_list_concGomModule(importedModule,tom_empty_list_concGomModule()));
          alreadyParsedModule.add(moduleNameName);
          newModuleToAnalyse.addAll(generateModuleToAnalyseSet(importedModule,alreadyParsedModule));
        } else {
          environment().markUsedBuiltin(moduleName); 
        }
      }
      moduleToAnalyse = newModuleToAnalyse;
    }
    return result;
  }

  /*
   * Compute immediate imported modules where already parsed modules are removed
   */
  private Set generateModuleToAnalyseSet(GomModule module, Set alreadyParsedModule) {
    HashSet moduleToAnalyse = new HashSet();
    ImportList importedModules = getImportList(module);
    while(!importedModules.isEmptyconcImportedModule()) {
      GomModuleName name = importedModules.getHeadconcImportedModule().getmoduleName();
      if(!alreadyParsedModule.contains(name)) {
        moduleToAnalyse.add(name);
      }
      importedModules = importedModules.getTailconcImportedModule();
    }
    //System.out.println("*** generateModuleToAnalyseSet = " + moduleToAnalyse);
    return moduleToAnalyse;
  }

  private GomModule parse(String moduleName) {
    getLogger().log(Level.FINE, "Seeking for file {0}",
        new Object[]{moduleName});
    GomModule result = null;
    File importedModuleFile = findModuleFile(moduleName);
    if(importedModuleFile == null) {
      getLogger().log(Level.SEVERE,
          GomMessage.moduleNotFound.getMessage(),
          new Object[]{moduleName});
      return null;
    }
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(importedModuleFile);
    } catch (FileNotFoundException e) {
      getLogger().log(Level.SEVERE,
          GomMessage.fileNotFound.getMessage(),
          new Object[]{moduleName+".gom"});
      return null;
    }
    GomLexer lexer = new GomLexer(inputStream);
    GomParser parser = new GomParser(lexer,"GomIncludeParser");
    try {
      result = parser.module();
    } catch (RecognitionException re) {
      getLogger().log(new PlatformLogRecord(Level.SEVERE,
            GomMessage.detailedParseException,
            re.getMessage(),moduleName+".gom", lexer.getLine()));
      return null;
    } catch(TokenStreamException tse) {
      getLogger().log(new PlatformLogRecord(Level.SEVERE,
            GomMessage.detailedParseException,
            tse.getMessage(),moduleName+".gom", lexer.getLine()));
      return null;
    }
    return result;
  }

  /**
   * find a module locally or thanks to the stream manager import list
   */
  private File findModuleFile(String moduleName) {
    String extendedModuleName = moduleName+".gom";
    File f = new File(extendedModuleName);
    if(f.exists()) {
      return f;
    }
    return streamManager.findModuleFile(extendedModuleName);
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

  public ImportList getImportList(GomModule module) {
    ImportList imports = tom_empty_list_concImportedModule();
     if(module instanceof  tom.gom.adt.gom.types.GomModule ) { { tom.gom.adt.gom.types.GomModule  tom_match1_1=(( tom.gom.adt.gom.types.GomModule )module); if (tom_is_fun_sym_GomModule(tom_match1_1) ||  false ) { { tom.gom.adt.gom.types.GomModuleName  tom_match1_1_moduleName=tom_get_slot_GomModule_moduleName(tom_match1_1); { tom.gom.adt.gom.types.SectionList  tom_match1_1_sectionList=tom_get_slot_GomModule_sectionList(tom_match1_1); if (tom_is_fun_sym_concSection(tom_match1_1_sectionList) ||  false ) { { tom.gom.adt.gom.types.SectionList  tom_match1_1_sectionList_list1=tom_match1_1_sectionList; { tom.gom.adt.gom.types.SectionList  tom_match1_1_sectionList_begin1=tom_match1_1_sectionList_list1; { tom.gom.adt.gom.types.SectionList  tom_match1_1_sectionList_end1=tom_match1_1_sectionList_list1; { while (!(tom_is_empty_concSection_SectionList(tom_match1_1_sectionList_end1))) {tom_match1_1_sectionList_list1=tom_match1_1_sectionList_end1; { { tom.gom.adt.gom.types.Section  tom_match1_1_sectionList_2=tom_get_head_concSection_SectionList(tom_match1_1_sectionList_list1);tom_match1_1_sectionList_list1=tom_get_tail_concSection_SectionList(tom_match1_1_sectionList_list1); if (tom_is_fun_sym_Imports(tom_match1_1_sectionList_2) ||  false ) { { tom.gom.adt.gom.types.ImportList  tom_match1_1_sectionList_2_importList=tom_get_slot_Imports_importList(tom_match1_1_sectionList_2); { tom.gom.adt.gom.types.ImportList  tom_importList=tom_match1_1_sectionList_2_importList; if ( true ) {

        imports = tom_append_list_concImportedModule(tom_importList,tom_append_list_concImportedModule(imports,tom_empty_list_concImportedModule()));
       } } } } }tom_match1_1_sectionList_end1=tom_get_tail_concSection_SectionList(tom_match1_1_sectionList_end1); } }tom_match1_1_sectionList_list1=tom_match1_1_sectionList_begin1; } } } } } } } } } }

    return imports;
  }
}
