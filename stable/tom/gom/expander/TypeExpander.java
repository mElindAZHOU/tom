/*
 *
 * GOM
 *
 * Copyright (c) 2006-2009, INRIA
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

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.gom.*;
import tom.gom.adt.gom.types.*;
import tom.gom.tools.error.GomRuntimeException;

public class TypeExpander {

         private static   tom.gom.adt.gom.types.ModuleList  tom_append_list_ConcModule( tom.gom.adt.gom.types.ModuleList l1,  tom.gom.adt.gom.types.ModuleList  l2) {     if( l1.isEmptyConcModule() ) {       return l2;     } else if( l2.isEmptyConcModule() ) {       return l1;     } else if(  l1.getTailConcModule() .isEmptyConcModule() ) {       return  tom.gom.adt.gom.types.modulelist.ConsConcModule.make( l1.getHeadConcModule() ,l2) ;     } else {       return  tom.gom.adt.gom.types.modulelist.ConsConcModule.make( l1.getHeadConcModule() ,tom_append_list_ConcModule( l1.getTailConcModule() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.ModuleList  tom_get_slice_ConcModule( tom.gom.adt.gom.types.ModuleList  begin,  tom.gom.adt.gom.types.ModuleList  end, tom.gom.adt.gom.types.ModuleList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcModule()  ||  (end== tom.gom.adt.gom.types.modulelist.EmptyConcModule.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.modulelist.ConsConcModule.make( begin.getHeadConcModule() ,( tom.gom.adt.gom.types.ModuleList )tom_get_slice_ConcModule( begin.getTailConcModule() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.ImportList  tom_append_list_ConcImportedModule( tom.gom.adt.gom.types.ImportList l1,  tom.gom.adt.gom.types.ImportList  l2) {     if( l1.isEmptyConcImportedModule() ) {       return l2;     } else if( l2.isEmptyConcImportedModule() ) {       return l1;     } else if(  l1.getTailConcImportedModule() .isEmptyConcImportedModule() ) {       return  tom.gom.adt.gom.types.importlist.ConsConcImportedModule.make( l1.getHeadConcImportedModule() ,l2) ;     } else {       return  tom.gom.adt.gom.types.importlist.ConsConcImportedModule.make( l1.getHeadConcImportedModule() ,tom_append_list_ConcImportedModule( l1.getTailConcImportedModule() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.ImportList  tom_get_slice_ConcImportedModule( tom.gom.adt.gom.types.ImportList  begin,  tom.gom.adt.gom.types.ImportList  end, tom.gom.adt.gom.types.ImportList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcImportedModule()  ||  (end== tom.gom.adt.gom.types.importlist.EmptyConcImportedModule.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.importlist.ConsConcImportedModule.make( begin.getHeadConcImportedModule() ,( tom.gom.adt.gom.types.ImportList )tom_get_slice_ConcImportedModule( begin.getTailConcImportedModule() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.GomTypeList  tom_append_list_ConcGomType( tom.gom.adt.gom.types.GomTypeList l1,  tom.gom.adt.gom.types.GomTypeList  l2) {     if( l1.isEmptyConcGomType() ) {       return l2;     } else if( l2.isEmptyConcGomType() ) {       return l1;     } else if(  l1.getTailConcGomType() .isEmptyConcGomType() ) {       return  tom.gom.adt.gom.types.gomtypelist.ConsConcGomType.make( l1.getHeadConcGomType() ,l2) ;     } else {       return  tom.gom.adt.gom.types.gomtypelist.ConsConcGomType.make( l1.getHeadConcGomType() ,tom_append_list_ConcGomType( l1.getTailConcGomType() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.GomTypeList  tom_get_slice_ConcGomType( tom.gom.adt.gom.types.GomTypeList  begin,  tom.gom.adt.gom.types.GomTypeList  end, tom.gom.adt.gom.types.GomTypeList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcGomType()  ||  (end== tom.gom.adt.gom.types.gomtypelist.EmptyConcGomType.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.gomtypelist.ConsConcGomType.make( begin.getHeadConcGomType() ,( tom.gom.adt.gom.types.GomTypeList )tom_get_slice_ConcGomType( begin.getTailConcGomType() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.ProductionList  tom_append_list_ConcProduction( tom.gom.adt.gom.types.ProductionList l1,  tom.gom.adt.gom.types.ProductionList  l2) {     if( l1.isEmptyConcProduction() ) {       return l2;     } else if( l2.isEmptyConcProduction() ) {       return l1;     } else if(  l1.getTailConcProduction() .isEmptyConcProduction() ) {       return  tom.gom.adt.gom.types.productionlist.ConsConcProduction.make( l1.getHeadConcProduction() ,l2) ;     } else {       return  tom.gom.adt.gom.types.productionlist.ConsConcProduction.make( l1.getHeadConcProduction() ,tom_append_list_ConcProduction( l1.getTailConcProduction() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.ProductionList  tom_get_slice_ConcProduction( tom.gom.adt.gom.types.ProductionList  begin,  tom.gom.adt.gom.types.ProductionList  end, tom.gom.adt.gom.types.ProductionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcProduction()  ||  (end== tom.gom.adt.gom.types.productionlist.EmptyConcProduction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.productionlist.ConsConcProduction.make( begin.getHeadConcProduction() ,( tom.gom.adt.gom.types.ProductionList )tom_get_slice_ConcProduction( begin.getTailConcProduction() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.GomModuleList  tom_append_list_ConcGomModule( tom.gom.adt.gom.types.GomModuleList l1,  tom.gom.adt.gom.types.GomModuleList  l2) {     if( l1.isEmptyConcGomModule() ) {       return l2;     } else if( l2.isEmptyConcGomModule() ) {       return l1;     } else if(  l1.getTailConcGomModule() .isEmptyConcGomModule() ) {       return  tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule.make( l1.getHeadConcGomModule() ,l2) ;     } else {       return  tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule.make( l1.getHeadConcGomModule() ,tom_append_list_ConcGomModule( l1.getTailConcGomModule() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.GomModuleList  tom_get_slice_ConcGomModule( tom.gom.adt.gom.types.GomModuleList  begin,  tom.gom.adt.gom.types.GomModuleList  end, tom.gom.adt.gom.types.GomModuleList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcGomModule()  ||  (end== tom.gom.adt.gom.types.gommodulelist.EmptyConcGomModule.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule.make( begin.getHeadConcGomModule() ,( tom.gom.adt.gom.types.GomModuleList )tom_get_slice_ConcGomModule( begin.getTailConcGomModule() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.SectionList  tom_append_list_ConcSection( tom.gom.adt.gom.types.SectionList l1,  tom.gom.adt.gom.types.SectionList  l2) {     if( l1.isEmptyConcSection() ) {       return l2;     } else if( l2.isEmptyConcSection() ) {       return l1;     } else if(  l1.getTailConcSection() .isEmptyConcSection() ) {       return  tom.gom.adt.gom.types.sectionlist.ConsConcSection.make( l1.getHeadConcSection() ,l2) ;     } else {       return  tom.gom.adt.gom.types.sectionlist.ConsConcSection.make( l1.getHeadConcSection() ,tom_append_list_ConcSection( l1.getTailConcSection() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.SectionList  tom_get_slice_ConcSection( tom.gom.adt.gom.types.SectionList  begin,  tom.gom.adt.gom.types.SectionList  end, tom.gom.adt.gom.types.SectionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcSection()  ||  (end== tom.gom.adt.gom.types.sectionlist.EmptyConcSection.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.sectionlist.ConsConcSection.make( begin.getHeadConcSection() ,( tom.gom.adt.gom.types.SectionList )tom_get_slice_ConcSection( begin.getTailConcSection() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.SlotList  tom_append_list_ConcSlot( tom.gom.adt.gom.types.SlotList l1,  tom.gom.adt.gom.types.SlotList  l2) {     if( l1.isEmptyConcSlot() ) {       return l2;     } else if( l2.isEmptyConcSlot() ) {       return l1;     } else if(  l1.getTailConcSlot() .isEmptyConcSlot() ) {       return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( l1.getHeadConcSlot() ,l2) ;     } else {       return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( l1.getHeadConcSlot() ,tom_append_list_ConcSlot( l1.getTailConcSlot() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.SlotList  tom_get_slice_ConcSlot( tom.gom.adt.gom.types.SlotList  begin,  tom.gom.adt.gom.types.SlotList  end, tom.gom.adt.gom.types.SlotList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcSlot()  ||  (end== tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( begin.getHeadConcSlot() ,( tom.gom.adt.gom.types.SlotList )tom_get_slice_ConcSlot( begin.getTailConcSlot() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.SortDeclList  tom_append_list_ConcSortDecl( tom.gom.adt.gom.types.SortDeclList l1,  tom.gom.adt.gom.types.SortDeclList  l2) {     if( l1.isEmptyConcSortDecl() ) {       return l2;     } else if( l2.isEmptyConcSortDecl() ) {       return l1;     } else if(  l1.getTailConcSortDecl() .isEmptyConcSortDecl() ) {       return  tom.gom.adt.gom.types.sortdecllist.ConsConcSortDecl.make( l1.getHeadConcSortDecl() ,l2) ;     } else {       return  tom.gom.adt.gom.types.sortdecllist.ConsConcSortDecl.make( l1.getHeadConcSortDecl() ,tom_append_list_ConcSortDecl( l1.getTailConcSortDecl() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.SortDeclList  tom_get_slice_ConcSortDecl( tom.gom.adt.gom.types.SortDeclList  begin,  tom.gom.adt.gom.types.SortDeclList  end, tom.gom.adt.gom.types.SortDeclList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcSortDecl()  ||  (end== tom.gom.adt.gom.types.sortdecllist.EmptyConcSortDecl.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.sortdecllist.ConsConcSortDecl.make( begin.getHeadConcSortDecl() ,( tom.gom.adt.gom.types.SortDeclList )tom_get_slice_ConcSortDecl( begin.getTailConcSortDecl() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.ModuleDeclList  tom_append_list_ConcModuleDecl( tom.gom.adt.gom.types.ModuleDeclList l1,  tom.gom.adt.gom.types.ModuleDeclList  l2) {     if( l1.isEmptyConcModuleDecl() ) {       return l2;     } else if( l2.isEmptyConcModuleDecl() ) {       return l1;     } else if(  l1.getTailConcModuleDecl() .isEmptyConcModuleDecl() ) {       return  tom.gom.adt.gom.types.moduledecllist.ConsConcModuleDecl.make( l1.getHeadConcModuleDecl() ,l2) ;     } else {       return  tom.gom.adt.gom.types.moduledecllist.ConsConcModuleDecl.make( l1.getHeadConcModuleDecl() ,tom_append_list_ConcModuleDecl( l1.getTailConcModuleDecl() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.ModuleDeclList  tom_get_slice_ConcModuleDecl( tom.gom.adt.gom.types.ModuleDeclList  begin,  tom.gom.adt.gom.types.ModuleDeclList  end, tom.gom.adt.gom.types.ModuleDeclList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcModuleDecl()  ||  (end== tom.gom.adt.gom.types.moduledecllist.EmptyConcModuleDecl.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.moduledecllist.ConsConcModuleDecl.make( begin.getHeadConcModuleDecl() ,( tom.gom.adt.gom.types.ModuleDeclList )tom_get_slice_ConcModuleDecl( begin.getTailConcModuleDecl() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.OperatorDeclList  tom_append_list_ConcOperator( tom.gom.adt.gom.types.OperatorDeclList l1,  tom.gom.adt.gom.types.OperatorDeclList  l2) {     if( l1.isEmptyConcOperator() ) {       return l2;     } else if( l2.isEmptyConcOperator() ) {       return l1;     } else if(  l1.getTailConcOperator() .isEmptyConcOperator() ) {       return  tom.gom.adt.gom.types.operatordecllist.ConsConcOperator.make( l1.getHeadConcOperator() ,l2) ;     } else {       return  tom.gom.adt.gom.types.operatordecllist.ConsConcOperator.make( l1.getHeadConcOperator() ,tom_append_list_ConcOperator( l1.getTailConcOperator() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.OperatorDeclList  tom_get_slice_ConcOperator( tom.gom.adt.gom.types.OperatorDeclList  begin,  tom.gom.adt.gom.types.OperatorDeclList  end, tom.gom.adt.gom.types.OperatorDeclList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcOperator()  ||  (end== tom.gom.adt.gom.types.operatordecllist.EmptyConcOperator.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.operatordecllist.ConsConcOperator.make( begin.getHeadConcOperator() ,( tom.gom.adt.gom.types.OperatorDeclList )tom_get_slice_ConcOperator( begin.getTailConcOperator() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.FieldList  tom_append_list_ConcField( tom.gom.adt.gom.types.FieldList l1,  tom.gom.adt.gom.types.FieldList  l2) {     if( l1.isEmptyConcField() ) {       return l2;     } else if( l2.isEmptyConcField() ) {       return l1;     } else if(  l1.getTailConcField() .isEmptyConcField() ) {       return  tom.gom.adt.gom.types.fieldlist.ConsConcField.make( l1.getHeadConcField() ,l2) ;     } else {       return  tom.gom.adt.gom.types.fieldlist.ConsConcField.make( l1.getHeadConcField() ,tom_append_list_ConcField( l1.getTailConcField() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.FieldList  tom_get_slice_ConcField( tom.gom.adt.gom.types.FieldList  begin,  tom.gom.adt.gom.types.FieldList  end, tom.gom.adt.gom.types.FieldList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcField()  ||  (end== tom.gom.adt.gom.types.fieldlist.EmptyConcField.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.fieldlist.ConsConcField.make( begin.getHeadConcField() ,( tom.gom.adt.gom.types.FieldList )tom_get_slice_ConcField( begin.getTailConcField() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.GrammarList  tom_append_list_ConcGrammar( tom.gom.adt.gom.types.GrammarList l1,  tom.gom.adt.gom.types.GrammarList  l2) {     if( l1.isEmptyConcGrammar() ) {       return l2;     } else if( l2.isEmptyConcGrammar() ) {       return l1;     } else if(  l1.getTailConcGrammar() .isEmptyConcGrammar() ) {       return  tom.gom.adt.gom.types.grammarlist.ConsConcGrammar.make( l1.getHeadConcGrammar() ,l2) ;     } else {       return  tom.gom.adt.gom.types.grammarlist.ConsConcGrammar.make( l1.getHeadConcGrammar() ,tom_append_list_ConcGrammar( l1.getTailConcGrammar() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.GrammarList  tom_get_slice_ConcGrammar( tom.gom.adt.gom.types.GrammarList  begin,  tom.gom.adt.gom.types.GrammarList  end, tom.gom.adt.gom.types.GrammarList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcGrammar()  ||  (end== tom.gom.adt.gom.types.grammarlist.EmptyConcGrammar.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.grammarlist.ConsConcGrammar.make( begin.getHeadConcGrammar() ,( tom.gom.adt.gom.types.GrammarList )tom_get_slice_ConcGrammar( begin.getTailConcGrammar() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.SortList  tom_append_list_ConcSort( tom.gom.adt.gom.types.SortList l1,  tom.gom.adt.gom.types.SortList  l2) {     if( l1.isEmptyConcSort() ) {       return l2;     } else if( l2.isEmptyConcSort() ) {       return l1;     } else if(  l1.getTailConcSort() .isEmptyConcSort() ) {       return  tom.gom.adt.gom.types.sortlist.ConsConcSort.make( l1.getHeadConcSort() ,l2) ;     } else {       return  tom.gom.adt.gom.types.sortlist.ConsConcSort.make( l1.getHeadConcSort() ,tom_append_list_ConcSort( l1.getTailConcSort() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.SortList  tom_get_slice_ConcSort( tom.gom.adt.gom.types.SortList  begin,  tom.gom.adt.gom.types.SortList  end, tom.gom.adt.gom.types.SortList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcSort()  ||  (end== tom.gom.adt.gom.types.sortlist.EmptyConcSort.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.sortlist.ConsConcSort.make( begin.getHeadConcSort() ,( tom.gom.adt.gom.types.SortList )tom_get_slice_ConcSort( begin.getTailConcSort() ,end,tail)) ;   }    

  private GomEnvironment gomEnvironment;

  public TypeExpander(GomStreamManager streamManager) {
    this.gomEnvironment.setStreamManager(streamManager);
  }
  
  public TypeExpander(GomEnvironment gomEnvironment) {
    this.gomEnvironment = gomEnvironment;
  }

  public TypeExpander(GomStreamManager streamManager, GomEnvironment gomEnvironment) {
    this.gomEnvironment = gomEnvironment;
  }

  public GomEnvironment getGomEnvironment() {
    return this.gomEnvironment;
  }

  public GomStreamManager getStreamManager() {
    return this.gomEnvironment.getStreamManager();
  }

  /**
    * We try here to get full sort definitions for each constructs
    * Once the structure is correctly build, we can attach the hooks
    */
  public ModuleList expand(GomModuleList moduleList) {

    /* put a map giving all imported modules for each module in the path */
    buildDependencyMap(moduleList);

    /* collect all sort declarations */
    SortDeclList sortDeclList =  tom.gom.adt.gom.types.sortdecllist.EmptyConcSortDecl.make() ;
    /* The sorts declared in each module */
    Map<ModuleDecl,SortDeclList> sortsForModule =
      new HashMap<ModuleDecl,SortDeclList>();
    GomModuleList consum = moduleList;
    while(!consum.isEmptyConcGomModule()) {
      GomModule module = consum.getHeadConcGomModule();
      consum = consum.getTailConcGomModule();

      Collection<SortDecl> decls = getSortDeclarations(module);

      Collection<SortDecl> implicitdecls = getSortDeclarationInCodomain(module);

      /* Check that there are no implicit sort declarations
       * Also, check that declared sorts have at least an operator
       */
      if(!decls.containsAll(implicitdecls)) {
        // whine about non declared sorts
        Collection<SortDecl> undeclaredSorts = new HashSet<SortDecl>();
        undeclaredSorts.addAll(implicitdecls);
        undeclaredSorts.removeAll(decls);
        getLogger().log(Level.WARNING, GomMessage.undeclaredSorts.getMessage(),
            new Object[]{showSortList(undeclaredSorts)});
      }
      if(!implicitdecls.containsAll(decls)) {
        // whine about sorts without operators: this is a real error
        Collection<SortDecl> emptySorts = new HashSet<SortDecl>();
        emptySorts.addAll(decls);
        emptySorts.removeAll(implicitdecls);
        getLogger().log(Level.SEVERE, GomMessage.emptySorts.getMessage(),
            new Object[]{showSortList(emptySorts)});
        return  tom.gom.adt.gom.types.modulelist.EmptyConcModule.make() ;
      }
      for (SortDecl decl : implicitdecls) {
        sortDeclList =  tom.gom.adt.gom.types.sortdecllist.ConsConcSortDecl.make(decl,tom_append_list_ConcSortDecl(sortDeclList, tom.gom.adt.gom.types.sortdecllist.EmptyConcSortDecl.make() )) ;
      }
      /* Fills sortsForModule */
      SortDeclList declaredSorts =  tom.gom.adt.gom.types.sortdecllist.EmptyConcSortDecl.make() ;
      for (SortDecl decl : decls) {
        declaredSorts =  tom.gom.adt.gom.types.sortdecllist.ConsConcSortDecl.make(decl,tom_append_list_ConcSortDecl(declaredSorts, tom.gom.adt.gom.types.sortdecllist.EmptyConcSortDecl.make() )) ;
      }
      GomModuleName moduleName = module.getModuleName();
      ModuleDecl mdecl =  tom.gom.adt.gom.types.moduledecl.ModuleDecl.make(moduleName, getStreamManager().getPackagePath(moduleName.getName())) ;
      sortsForModule.put(mdecl,declaredSorts);
    }

    /* now get all operators for each sort */
    Map<SortDecl,OperatorDeclList> operatorsForSort =
      new HashMap<SortDecl,OperatorDeclList>();
    Map hooksForSort = new HashMap();
    consum = moduleList;
    while(!consum.isEmptyConcGomModule()) {
      GomModule module = consum.getHeadConcGomModule();
      consum = consum.getTailConcGomModule();

      // iterate through the productions
      {{if ( (module instanceof tom.gom.adt.gom.types.GomModule) ) {if ( ((( tom.gom.adt.gom.types.GomModule )module) instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) { tom.gom.adt.gom.types.SectionList  tomMatch483NameNumber_freshVar_2= (( tom.gom.adt.gom.types.GomModule )module).getSectionList() ;if ( ((tomMatch483NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.ConsConcSection) || (tomMatch483NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.EmptyConcSection)) ) { tom.gom.adt.gom.types.SectionList  tomMatch483NameNumber_end_7=tomMatch483NameNumber_freshVar_2;do {{if (!( tomMatch483NameNumber_end_7.isEmptyConcSection() )) { tom.gom.adt.gom.types.Section  tomMatch483NameNumber_freshVar_11= tomMatch483NameNumber_end_7.getHeadConcSection() ;if ( (tomMatch483NameNumber_freshVar_11 instanceof tom.gom.adt.gom.types.section.Public) ) { tom.gom.adt.gom.types.GrammarList  tomMatch483NameNumber_freshVar_10= tomMatch483NameNumber_freshVar_11.getGrammarList() ;if ( ((tomMatch483NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.ConsConcGrammar) || (tomMatch483NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.EmptyConcGrammar)) ) { tom.gom.adt.gom.types.GrammarList  tomMatch483NameNumber_end_15=tomMatch483NameNumber_freshVar_10;do {{if (!( tomMatch483NameNumber_end_15.isEmptyConcGrammar() )) { tom.gom.adt.gom.types.Grammar  tomMatch483NameNumber_freshVar_19= tomMatch483NameNumber_end_15.getHeadConcGrammar() ;if ( (tomMatch483NameNumber_freshVar_19 instanceof tom.gom.adt.gom.types.grammar.Grammar) ) { tom.gom.adt.gom.types.ProductionList  tomMatch483NameNumber_freshVar_18= tomMatch483NameNumber_freshVar_19.getProductionList() ;if ( ((tomMatch483NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.ConsConcProduction) || (tomMatch483NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.EmptyConcProduction)) ) { tom.gom.adt.gom.types.ProductionList  tomMatch483NameNumber_end_23=tomMatch483NameNumber_freshVar_18;do {{if (!( tomMatch483NameNumber_end_23.isEmptyConcProduction() )) {if ( ( tomMatch483NameNumber_end_23.getHeadConcProduction()  instanceof tom.gom.adt.gom.types.production.Production) ) {



          // we may want to pass moduleName to help resolve ambiguities with modules
          getOperatorDecl( tomMatch483NameNumber_end_23.getHeadConcProduction() ,sortDeclList,operatorsForSort);

        }}if ( tomMatch483NameNumber_end_23.isEmptyConcProduction() ) {tomMatch483NameNumber_end_23=tomMatch483NameNumber_freshVar_18;} else {tomMatch483NameNumber_end_23= tomMatch483NameNumber_end_23.getTailConcProduction() ;}}} while(!( (tomMatch483NameNumber_end_23==tomMatch483NameNumber_freshVar_18) ));}}}if ( tomMatch483NameNumber_end_15.isEmptyConcGrammar() ) {tomMatch483NameNumber_end_15=tomMatch483NameNumber_freshVar_10;} else {tomMatch483NameNumber_end_15= tomMatch483NameNumber_end_15.getTailConcGrammar() ;}}} while(!( (tomMatch483NameNumber_end_15==tomMatch483NameNumber_freshVar_10) ));}}}if ( tomMatch483NameNumber_end_7.isEmptyConcSection() ) {tomMatch483NameNumber_end_7=tomMatch483NameNumber_freshVar_2;} else {tomMatch483NameNumber_end_7= tomMatch483NameNumber_end_7.getTailConcSection() ;}}} while(!( (tomMatch483NameNumber_end_7==tomMatch483NameNumber_freshVar_2) ));}}}}}{{if ( (module instanceof tom.gom.adt.gom.types.GomModule) ) {if ( ((( tom.gom.adt.gom.types.GomModule )module) instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) { tom.gom.adt.gom.types.SectionList  tomMatch484NameNumber_freshVar_2= (( tom.gom.adt.gom.types.GomModule )module).getSectionList() ;if ( ((tomMatch484NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.ConsConcSection) || (tomMatch484NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.EmptyConcSection)) ) { tom.gom.adt.gom.types.SectionList  tomMatch484NameNumber_end_7=tomMatch484NameNumber_freshVar_2;do {{if (!( tomMatch484NameNumber_end_7.isEmptyConcSection() )) { tom.gom.adt.gom.types.Section  tomMatch484NameNumber_freshVar_11= tomMatch484NameNumber_end_7.getHeadConcSection() ;if ( (tomMatch484NameNumber_freshVar_11 instanceof tom.gom.adt.gom.types.section.Public) ) { tom.gom.adt.gom.types.GrammarList  tomMatch484NameNumber_freshVar_10= tomMatch484NameNumber_freshVar_11.getGrammarList() ;if ( ((tomMatch484NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.ConsConcGrammar) || (tomMatch484NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.EmptyConcGrammar)) ) { tom.gom.adt.gom.types.GrammarList  tomMatch484NameNumber_end_15=tomMatch484NameNumber_freshVar_10;do {{if (!( tomMatch484NameNumber_end_15.isEmptyConcGrammar() )) { tom.gom.adt.gom.types.Grammar  tomMatch484NameNumber_freshVar_19= tomMatch484NameNumber_end_15.getHeadConcGrammar() ;if ( (tomMatch484NameNumber_freshVar_19 instanceof tom.gom.adt.gom.types.grammar.Grammar) ) { tom.gom.adt.gom.types.ProductionList  tomMatch484NameNumber_freshVar_18= tomMatch484NameNumber_freshVar_19.getProductionList() ;if ( ((tomMatch484NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.ConsConcProduction) || (tomMatch484NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.EmptyConcProduction)) ) { tom.gom.adt.gom.types.ProductionList  tomMatch484NameNumber_end_23=tomMatch484NameNumber_freshVar_18;do {{if (!( tomMatch484NameNumber_end_23.isEmptyConcProduction() )) { tom.gom.adt.gom.types.Production  tomMatch484NameNumber_freshVar_27= tomMatch484NameNumber_end_23.getHeadConcProduction() ;if ( (tomMatch484NameNumber_freshVar_27 instanceof tom.gom.adt.gom.types.production.SortType) ) { tom.gom.adt.gom.types.ProductionList  tomMatch484NameNumber_freshVar_26= tomMatch484NameNumber_freshVar_27.getProductionList() ;if ( ((tomMatch484NameNumber_freshVar_26 instanceof tom.gom.adt.gom.types.productionlist.ConsConcProduction) || (tomMatch484NameNumber_freshVar_26 instanceof tom.gom.adt.gom.types.productionlist.EmptyConcProduction)) ) { tom.gom.adt.gom.types.ProductionList  tomMatch484NameNumber_end_31=tomMatch484NameNumber_freshVar_26;do {{if (!( tomMatch484NameNumber_end_31.isEmptyConcProduction() )) {if ( ( tomMatch484NameNumber_end_31.getHeadConcProduction()  instanceof tom.gom.adt.gom.types.production.Production) ) {








          // we may want to pass moduleName to help resolve ambiguities with modules
          getOperatorDecl( tomMatch484NameNumber_end_31.getHeadConcProduction() ,sortDeclList,operatorsForSort);

        }}if ( tomMatch484NameNumber_end_31.isEmptyConcProduction() ) {tomMatch484NameNumber_end_31=tomMatch484NameNumber_freshVar_26;} else {tomMatch484NameNumber_end_31= tomMatch484NameNumber_end_31.getTailConcProduction() ;}}} while(!( (tomMatch484NameNumber_end_31==tomMatch484NameNumber_freshVar_26) ));}}}if ( tomMatch484NameNumber_end_23.isEmptyConcProduction() ) {tomMatch484NameNumber_end_23=tomMatch484NameNumber_freshVar_18;} else {tomMatch484NameNumber_end_23= tomMatch484NameNumber_end_23.getTailConcProduction() ;}}} while(!( (tomMatch484NameNumber_end_23==tomMatch484NameNumber_freshVar_18) ));}}}if ( tomMatch484NameNumber_end_15.isEmptyConcGrammar() ) {tomMatch484NameNumber_end_15=tomMatch484NameNumber_freshVar_10;} else {tomMatch484NameNumber_end_15= tomMatch484NameNumber_end_15.getTailConcGrammar() ;}}} while(!( (tomMatch484NameNumber_end_15==tomMatch484NameNumber_freshVar_10) ));}}}if ( tomMatch484NameNumber_end_7.isEmptyConcSection() ) {tomMatch484NameNumber_end_7=tomMatch484NameNumber_freshVar_2;} else {tomMatch484NameNumber_end_7= tomMatch484NameNumber_end_7.getTailConcSection() ;}}} while(!( (tomMatch484NameNumber_end_7==tomMatch484NameNumber_freshVar_2) ));}}}}}

    }

    /*
     * build the module list using the map
     * since we already checked that the declared and used sorts do match, we
     * can use the map alone
     */
    ModuleList resultModuleList =  tom.gom.adt.gom.types.modulelist.EmptyConcModule.make() ;
    for (Map.Entry<ModuleDecl,SortDeclList> entry : sortsForModule.entrySet()) {
      ModuleDecl mdecl = entry.getKey();
      SortDeclList sdeclList = entry.getValue();
      SortList sortList =  tom.gom.adt.gom.types.sortlist.EmptyConcSort.make() ;
      {{if ( (sdeclList instanceof tom.gom.adt.gom.types.SortDeclList) ) {if ( (((( tom.gom.adt.gom.types.SortDeclList )sdeclList) instanceof tom.gom.adt.gom.types.sortdecllist.ConsConcSortDecl) || ((( tom.gom.adt.gom.types.SortDeclList )sdeclList) instanceof tom.gom.adt.gom.types.sortdecllist.EmptyConcSortDecl)) ) { tom.gom.adt.gom.types.SortDeclList  tomMatch485NameNumber_end_4=(( tom.gom.adt.gom.types.SortDeclList )sdeclList);do {{if (!( tomMatch485NameNumber_end_4.isEmptyConcSortDecl() )) { tom.gom.adt.gom.types.SortDecl  tom_sdecl= tomMatch485NameNumber_end_4.getHeadConcSortDecl() ;

          OperatorDeclList opdecl = operatorsForSort.get(tom_sdecl);
          Sort fullSort =  tom.gom.adt.gom.types.sort.Sort.make(tom_sdecl, opdecl) ;
          if(checkSortValidity(fullSort)) {
            sortList =  tom.gom.adt.gom.types.sortlist.ConsConcSort.make(fullSort,tom_append_list_ConcSort(sortList, tom.gom.adt.gom.types.sortlist.EmptyConcSort.make() )) ;
          }
        }if ( tomMatch485NameNumber_end_4.isEmptyConcSortDecl() ) {tomMatch485NameNumber_end_4=(( tom.gom.adt.gom.types.SortDeclList )sdeclList);} else {tomMatch485NameNumber_end_4= tomMatch485NameNumber_end_4.getTailConcSortDecl() ;}}} while(!( (tomMatch485NameNumber_end_4==(( tom.gom.adt.gom.types.SortDeclList )sdeclList)) ));}}}}

      resultModuleList =  tom.gom.adt.gom.types.modulelist.ConsConcModule.make( tom.gom.adt.gom.types.module.Module.make(mdecl, sortList) ,tom_append_list_ConcModule(resultModuleList, tom.gom.adt.gom.types.modulelist.EmptyConcModule.make() )) 

;
    }
    return resultModuleList;
  }

  /*
   * Get an OperatorDecl from a Production, using the list of sort declarations
   * XXX: There is huge room for efficiency improvement, as we could use a map
   * sortName -> sortDeclList instead of a simple list
   */
  private OperatorDecl getOperatorDecl(Production prod,
      SortDeclList sortDeclList,
      Map<SortDecl,OperatorDeclList> operatorsForSort) {

    {{if ( (prod instanceof tom.gom.adt.gom.types.Production) ) {if ( ((( tom.gom.adt.gom.types.Production )prod) instanceof tom.gom.adt.gom.types.production.Production) ) { tom.gom.adt.gom.types.GomType  tomMatch486NameNumber_freshVar_3= (( tom.gom.adt.gom.types.Production )prod).getCodomain() ; String  tom_name= (( tom.gom.adt.gom.types.Production )prod).getName() ;if ( (tomMatch486NameNumber_freshVar_3 instanceof tom.gom.adt.gom.types.gomtype.GomType) ) { tom.gom.adt.gom.types.Option  tom_options= (( tom.gom.adt.gom.types.Production )prod).getOption() ;



        SortDecl codomainSort = declFromTypename( tomMatch486NameNumber_freshVar_3.getName() ,sortDeclList);
        TypedProduction domainSorts = typedProduction( (( tom.gom.adt.gom.types.Production )prod).getDomainList() ,sortDeclList);
///
        OperatorDecl decl =  tom.gom.adt.gom.types.operatordecl.OperatorDecl.make(tom_name, codomainSort, domainSorts,  tom.gom.adt.gom.types.option.Details.make("") ) ; //default case, when no comment is present
        if (tom_options.isConsOptionList()) { // usual case : 
          Object[] opts = ((tom.gom.adt.gom.types.option.OptionList)tom_options).toArray();
          for (int i=0;i<tom_options.length();i++) {
            if (opts[i] instanceof tom.gom.adt.gom.types.option.Details) {
              decl =  tom.gom.adt.gom.types.operatordecl.OperatorDecl.make(tom_name, codomainSort, domainSorts, (tom.gom.adt.gom.types.option.Details)opts[i]) ;
              break;
            }
          }
        } else if (tom_options.isDetails()) { // just in case, but for moment, it shouldn't be possible to have it
          decl =  tom.gom.adt.gom.types.operatordecl.OperatorDecl.make(tom_name, codomainSort, domainSorts, tom_options) ;
        }
///
        if (operatorsForSort.containsKey(codomainSort)) {
          OperatorDeclList list = operatorsForSort.get(codomainSort);
          operatorsForSort.put(codomainSort, tom.gom.adt.gom.types.operatordecllist.ConsConcOperator.make(decl,tom_append_list_ConcOperator(list, tom.gom.adt.gom.types.operatordecllist.EmptyConcOperator.make() )) );
        } else {
          operatorsForSort.put(codomainSort, tom.gom.adt.gom.types.operatordecllist.ConsConcOperator.make(decl, tom.gom.adt.gom.types.operatordecllist.EmptyConcOperator.make() ) );
        }
        return decl;
      }}}}}


    throw new GomRuntimeException(
        "TypeExpander::getOperatorDecl: wrong Production?");
  }

  private SortDecl declFromTypename(String typename,
                                    SortDeclList sortDeclList) {
    if (getGomEnvironment().isBuiltinSort(typename)) {
      return getGomEnvironment().builtinSort(typename);
    }
    {{if ( (sortDeclList instanceof tom.gom.adt.gom.types.SortDeclList) ) {if ( (((( tom.gom.adt.gom.types.SortDeclList )sortDeclList) instanceof tom.gom.adt.gom.types.sortdecllist.ConsConcSortDecl) || ((( tom.gom.adt.gom.types.SortDeclList )sortDeclList) instanceof tom.gom.adt.gom.types.sortdecllist.EmptyConcSortDecl)) ) { tom.gom.adt.gom.types.SortDeclList  tomMatch487NameNumber_end_4=(( tom.gom.adt.gom.types.SortDeclList )sortDeclList);do {{if (!( tomMatch487NameNumber_end_4.isEmptyConcSortDecl() )) { tom.gom.adt.gom.types.SortDecl  tomMatch487NameNumber_freshVar_8= tomMatch487NameNumber_end_4.getHeadConcSortDecl() ;if ( (tomMatch487NameNumber_freshVar_8 instanceof tom.gom.adt.gom.types.sortdecl.SortDecl) ) {

        if (typename.equals( tomMatch487NameNumber_freshVar_8.getName() )) {
          return  tomMatch487NameNumber_end_4.getHeadConcSortDecl() ;
        }
      }}if ( tomMatch487NameNumber_end_4.isEmptyConcSortDecl() ) {tomMatch487NameNumber_end_4=(( tom.gom.adt.gom.types.SortDeclList )sortDeclList);} else {tomMatch487NameNumber_end_4= tomMatch487NameNumber_end_4.getTailConcSortDecl() ;}}} while(!( (tomMatch487NameNumber_end_4==(( tom.gom.adt.gom.types.SortDeclList )sortDeclList)) ));}}}}

    
    getLogger().log(Level.SEVERE, GomMessage.unknownSort.getMessage(),
        new Object[]{typename});
    /* If the sort is not known, assume it is a builtin */
    return  tom.gom.adt.gom.types.sortdecl.BuiltinSortDecl.make(typename) ;
  }

  private TypedProduction typedProduction(FieldList domain, SortDeclList sortDeclList) {
    {{if ( (domain instanceof tom.gom.adt.gom.types.FieldList) ) {if ( (((( tom.gom.adt.gom.types.FieldList )domain) instanceof tom.gom.adt.gom.types.fieldlist.ConsConcField) || ((( tom.gom.adt.gom.types.FieldList )domain) instanceof tom.gom.adt.gom.types.fieldlist.EmptyConcField)) ) {if (!( (( tom.gom.adt.gom.types.FieldList )domain).isEmptyConcField() )) { tom.gom.adt.gom.types.Field  tomMatch488NameNumber_freshVar_5= (( tom.gom.adt.gom.types.FieldList )domain).getHeadConcField() ;if ( (tomMatch488NameNumber_freshVar_5 instanceof tom.gom.adt.gom.types.field.StarredField) ) { tom.gom.adt.gom.types.GomType  tomMatch488NameNumber_freshVar_3= tomMatch488NameNumber_freshVar_5.getFieldType() ;if ( (tomMatch488NameNumber_freshVar_3 instanceof tom.gom.adt.gom.types.gomtype.GomType) ) {if (  (( tom.gom.adt.gom.types.FieldList )domain).getTailConcField() .isEmptyConcField() ) {

        return  tom.gom.adt.gom.types.typedproduction.Variadic.make(declFromTypename( tomMatch488NameNumber_freshVar_3.getName() ,sortDeclList)) ;
      }}}}}}}{if ( (domain instanceof tom.gom.adt.gom.types.FieldList) ) {if ( (((( tom.gom.adt.gom.types.FieldList )domain) instanceof tom.gom.adt.gom.types.fieldlist.ConsConcField) || ((( tom.gom.adt.gom.types.FieldList )domain) instanceof tom.gom.adt.gom.types.fieldlist.EmptyConcField)) ) {

        return  tom.gom.adt.gom.types.typedproduction.Slots.make(typedSlotList((( tom.gom.adt.gom.types.FieldList )domain),sortDeclList)) ;
      }}}}

    // the error message could be more refined
    throw new GomRuntimeException("TypeExpander::typedProduction: illformed Production");
  }

  private SlotList typedSlotList(FieldList fields, SortDeclList sortDeclList) {
    {{if ( (fields instanceof tom.gom.adt.gom.types.FieldList) ) {if ( (((( tom.gom.adt.gom.types.FieldList )fields) instanceof tom.gom.adt.gom.types.fieldlist.ConsConcField) || ((( tom.gom.adt.gom.types.FieldList )fields) instanceof tom.gom.adt.gom.types.fieldlist.EmptyConcField)) ) {if ( (( tom.gom.adt.gom.types.FieldList )fields).isEmptyConcField() ) {

        return  tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ;
      }}}}{if ( (fields instanceof tom.gom.adt.gom.types.FieldList) ) {if ( (((( tom.gom.adt.gom.types.FieldList )fields) instanceof tom.gom.adt.gom.types.fieldlist.ConsConcField) || ((( tom.gom.adt.gom.types.FieldList )fields) instanceof tom.gom.adt.gom.types.fieldlist.EmptyConcField)) ) {if (!( (( tom.gom.adt.gom.types.FieldList )fields).isEmptyConcField() )) { tom.gom.adt.gom.types.Field  tomMatch489NameNumber_freshVar_9= (( tom.gom.adt.gom.types.FieldList )fields).getHeadConcField() ;if ( (tomMatch489NameNumber_freshVar_9 instanceof tom.gom.adt.gom.types.field.NamedField) ) { tom.gom.adt.gom.types.GomType  tomMatch489NameNumber_freshVar_8= tomMatch489NameNumber_freshVar_9.getFieldType() ;if ( (tomMatch489NameNumber_freshVar_8 instanceof tom.gom.adt.gom.types.gomtype.GomType) ) {

        SlotList newtail = typedSlotList( (( tom.gom.adt.gom.types.FieldList )fields).getTailConcField() ,sortDeclList);
        return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( tom.gom.adt.gom.types.slot.Slot.make( tomMatch489NameNumber_freshVar_9.getName() , declFromTypename( tomMatch489NameNumber_freshVar_8.getName() ,sortDeclList)) ,tom_append_list_ConcSlot(newtail, tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() )) ;
      }}}}}}}

    getLogger().log(Level.SEVERE, GomMessage.malformedProduction.getMessage(),
        new Object[]{fields.toString()});
    return  tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ;
  }

  /*
   * Get all sort declarations in a module
   */
  private Collection<SortDecl> getSortDeclarations(GomModule module) {
    Collection<SortDecl> result = new HashSet<SortDecl>();
    {{if ( (module instanceof tom.gom.adt.gom.types.GomModule) ) {if ( ((( tom.gom.adt.gom.types.GomModule )module) instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) { tom.gom.adt.gom.types.SectionList  tomMatch490NameNumber_freshVar_2= (( tom.gom.adt.gom.types.GomModule )module).getSectionList() ; tom.gom.adt.gom.types.GomModuleName  tom_moduleName= (( tom.gom.adt.gom.types.GomModule )module).getModuleName() ;if ( ((tomMatch490NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.ConsConcSection) || (tomMatch490NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.EmptyConcSection)) ) { tom.gom.adt.gom.types.SectionList  tomMatch490NameNumber_end_7=tomMatch490NameNumber_freshVar_2;do {{if (!( tomMatch490NameNumber_end_7.isEmptyConcSection() )) { tom.gom.adt.gom.types.Section  tomMatch490NameNumber_freshVar_11= tomMatch490NameNumber_end_7.getHeadConcSection() ;if ( (tomMatch490NameNumber_freshVar_11 instanceof tom.gom.adt.gom.types.section.Public) ) { tom.gom.adt.gom.types.GrammarList  tomMatch490NameNumber_freshVar_10= tomMatch490NameNumber_freshVar_11.getGrammarList() ;if ( ((tomMatch490NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.ConsConcGrammar) || (tomMatch490NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.EmptyConcGrammar)) ) { tom.gom.adt.gom.types.GrammarList  tomMatch490NameNumber_end_15=tomMatch490NameNumber_freshVar_10;do {{if (!( tomMatch490NameNumber_end_15.isEmptyConcGrammar() )) { tom.gom.adt.gom.types.Grammar  tomMatch490NameNumber_freshVar_19= tomMatch490NameNumber_end_15.getHeadConcGrammar() ;if ( (tomMatch490NameNumber_freshVar_19 instanceof tom.gom.adt.gom.types.grammar.Sorts) ) { tom.gom.adt.gom.types.GomTypeList  tomMatch490NameNumber_freshVar_18= tomMatch490NameNumber_freshVar_19.getTypeList() ;if ( ((tomMatch490NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.gomtypelist.ConsConcGomType) || (tomMatch490NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.gomtypelist.EmptyConcGomType)) ) { tom.gom.adt.gom.types.GomTypeList  tomMatch490NameNumber_end_23=tomMatch490NameNumber_freshVar_18;do {{if (!( tomMatch490NameNumber_end_23.isEmptyConcGomType() )) { tom.gom.adt.gom.types.GomType  tomMatch490NameNumber_freshVar_28= tomMatch490NameNumber_end_23.getHeadConcGomType() ;if ( (tomMatch490NameNumber_freshVar_28 instanceof tom.gom.adt.gom.types.gomtype.GomType) ) { String  tom_typeName= tomMatch490NameNumber_freshVar_28.getName() ;



        if (getGomEnvironment().isBuiltinSort(tom_typeName)) {
          getLogger().log(Level.SEVERE, GomMessage.operatorOnBuiltin.getMessage(),
            new Object[]{(tom_typeName)});
          result.add(getGomEnvironment().builtinSort(tom_typeName));
        } else {
          result.add( tom.gom.adt.gom.types.sortdecl.SortDecl.make(tom_typeName,  tom.gom.adt.gom.types.moduledecl.ModuleDecl.make(tom_moduleName, getStreamManager().getPackagePath(tom_moduleName.getName())) ) );
        }
      }}if ( tomMatch490NameNumber_end_23.isEmptyConcGomType() ) {tomMatch490NameNumber_end_23=tomMatch490NameNumber_freshVar_18;} else {tomMatch490NameNumber_end_23= tomMatch490NameNumber_end_23.getTailConcGomType() ;}}} while(!( (tomMatch490NameNumber_end_23==tomMatch490NameNumber_freshVar_18) ));}}}if ( tomMatch490NameNumber_end_15.isEmptyConcGrammar() ) {tomMatch490NameNumber_end_15=tomMatch490NameNumber_freshVar_10;} else {tomMatch490NameNumber_end_15= tomMatch490NameNumber_end_15.getTailConcGrammar() ;}}} while(!( (tomMatch490NameNumber_end_15==tomMatch490NameNumber_freshVar_10) ));}}}if ( tomMatch490NameNumber_end_7.isEmptyConcSection() ) {tomMatch490NameNumber_end_7=tomMatch490NameNumber_freshVar_2;} else {tomMatch490NameNumber_end_7= tomMatch490NameNumber_end_7.getTailConcSection() ;}}} while(!( (tomMatch490NameNumber_end_7==tomMatch490NameNumber_freshVar_2) ));}}}}}{{if ( (module instanceof tom.gom.adt.gom.types.GomModule) ) {if ( ((( tom.gom.adt.gom.types.GomModule )module) instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) { tom.gom.adt.gom.types.SectionList  tomMatch491NameNumber_freshVar_2= (( tom.gom.adt.gom.types.GomModule )module).getSectionList() ; tom.gom.adt.gom.types.GomModuleName  tom_moduleName= (( tom.gom.adt.gom.types.GomModule )module).getModuleName() ;if ( ((tomMatch491NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.ConsConcSection) || (tomMatch491NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.EmptyConcSection)) ) { tom.gom.adt.gom.types.SectionList  tomMatch491NameNumber_end_7=tomMatch491NameNumber_freshVar_2;do {{if (!( tomMatch491NameNumber_end_7.isEmptyConcSection() )) { tom.gom.adt.gom.types.Section  tomMatch491NameNumber_freshVar_11= tomMatch491NameNumber_end_7.getHeadConcSection() ;if ( (tomMatch491NameNumber_freshVar_11 instanceof tom.gom.adt.gom.types.section.Public) ) { tom.gom.adt.gom.types.GrammarList  tomMatch491NameNumber_freshVar_10= tomMatch491NameNumber_freshVar_11.getGrammarList() ;if ( ((tomMatch491NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.ConsConcGrammar) || (tomMatch491NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.EmptyConcGrammar)) ) { tom.gom.adt.gom.types.GrammarList  tomMatch491NameNumber_end_15=tomMatch491NameNumber_freshVar_10;do {{if (!( tomMatch491NameNumber_end_15.isEmptyConcGrammar() )) { tom.gom.adt.gom.types.Grammar  tomMatch491NameNumber_freshVar_19= tomMatch491NameNumber_end_15.getHeadConcGrammar() ;if ( (tomMatch491NameNumber_freshVar_19 instanceof tom.gom.adt.gom.types.grammar.Grammar) ) { tom.gom.adt.gom.types.ProductionList  tomMatch491NameNumber_freshVar_18= tomMatch491NameNumber_freshVar_19.getProductionList() ;if ( ((tomMatch491NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.ConsConcProduction) || (tomMatch491NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.EmptyConcProduction)) ) { tom.gom.adt.gom.types.ProductionList  tomMatch491NameNumber_end_23=tomMatch491NameNumber_freshVar_18;do {{if (!( tomMatch491NameNumber_end_23.isEmptyConcProduction() )) { tom.gom.adt.gom.types.Production  tomMatch491NameNumber_freshVar_27= tomMatch491NameNumber_end_23.getHeadConcProduction() ;if ( (tomMatch491NameNumber_freshVar_27 instanceof tom.gom.adt.gom.types.production.SortType) ) { tom.gom.adt.gom.types.GomType  tomMatch491NameNumber_freshVar_26= tomMatch491NameNumber_freshVar_27.getType() ;if ( (tomMatch491NameNumber_freshVar_26 instanceof tom.gom.adt.gom.types.gomtype.GomType) ) { String  tom_typeName= tomMatch491NameNumber_freshVar_26.getName() ;







        if (getGomEnvironment().isBuiltinSort(tom_typeName)) {
          getLogger().log(Level.SEVERE, GomMessage.operatorOnBuiltin.getMessage(),
            new Object[]{(tom_typeName)});
          result.add(getGomEnvironment().builtinSort(tom_typeName));
        } else {
          result.add( tom.gom.adt.gom.types.sortdecl.SortDecl.make(tom_typeName,  tom.gom.adt.gom.types.moduledecl.ModuleDecl.make(tom_moduleName, getStreamManager().getPackagePath(tom_moduleName.getName())) ) );
        }
      }}}if ( tomMatch491NameNumber_end_23.isEmptyConcProduction() ) {tomMatch491NameNumber_end_23=tomMatch491NameNumber_freshVar_18;} else {tomMatch491NameNumber_end_23= tomMatch491NameNumber_end_23.getTailConcProduction() ;}}} while(!( (tomMatch491NameNumber_end_23==tomMatch491NameNumber_freshVar_18) ));}}}if ( tomMatch491NameNumber_end_15.isEmptyConcGrammar() ) {tomMatch491NameNumber_end_15=tomMatch491NameNumber_freshVar_10;} else {tomMatch491NameNumber_end_15= tomMatch491NameNumber_end_15.getTailConcGrammar() ;}}} while(!( (tomMatch491NameNumber_end_15==tomMatch491NameNumber_freshVar_10) ));}}}if ( tomMatch491NameNumber_end_7.isEmptyConcSection() ) {tomMatch491NameNumber_end_7=tomMatch491NameNumber_freshVar_2;} else {tomMatch491NameNumber_end_7= tomMatch491NameNumber_end_7.getTailConcSection() ;}}} while(!( (tomMatch491NameNumber_end_7==tomMatch491NameNumber_freshVar_2) ));}}}}}

    return result;
  }

  /*
   * Get all sort uses in a module (as codomain of an operator)
   */
  private Collection<SortDecl> getSortDeclarationInCodomain(GomModule module) {
    Collection<SortDecl> result = new HashSet<SortDecl>();
    {{if ( (module instanceof tom.gom.adt.gom.types.GomModule) ) {if ( ((( tom.gom.adt.gom.types.GomModule )module) instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) { tom.gom.adt.gom.types.SectionList  tomMatch492NameNumber_freshVar_2= (( tom.gom.adt.gom.types.GomModule )module).getSectionList() ; tom.gom.adt.gom.types.GomModuleName  tom_moduleName= (( tom.gom.adt.gom.types.GomModule )module).getModuleName() ;if ( ((tomMatch492NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.ConsConcSection) || (tomMatch492NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.EmptyConcSection)) ) { tom.gom.adt.gom.types.SectionList  tomMatch492NameNumber_end_7=tomMatch492NameNumber_freshVar_2;do {{if (!( tomMatch492NameNumber_end_7.isEmptyConcSection() )) { tom.gom.adt.gom.types.Section  tomMatch492NameNumber_freshVar_11= tomMatch492NameNumber_end_7.getHeadConcSection() ;if ( (tomMatch492NameNumber_freshVar_11 instanceof tom.gom.adt.gom.types.section.Public) ) { tom.gom.adt.gom.types.GrammarList  tomMatch492NameNumber_freshVar_10= tomMatch492NameNumber_freshVar_11.getGrammarList() ;if ( ((tomMatch492NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.ConsConcGrammar) || (tomMatch492NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.EmptyConcGrammar)) ) { tom.gom.adt.gom.types.GrammarList  tomMatch492NameNumber_end_15=tomMatch492NameNumber_freshVar_10;do {{if (!( tomMatch492NameNumber_end_15.isEmptyConcGrammar() )) { tom.gom.adt.gom.types.Grammar  tomMatch492NameNumber_freshVar_19= tomMatch492NameNumber_end_15.getHeadConcGrammar() ;if ( (tomMatch492NameNumber_freshVar_19 instanceof tom.gom.adt.gom.types.grammar.Grammar) ) { tom.gom.adt.gom.types.ProductionList  tomMatch492NameNumber_freshVar_18= tomMatch492NameNumber_freshVar_19.getProductionList() ;if ( ((tomMatch492NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.ConsConcProduction) || (tomMatch492NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.EmptyConcProduction)) ) { tom.gom.adt.gom.types.ProductionList  tomMatch492NameNumber_end_23=tomMatch492NameNumber_freshVar_18;do {{if (!( tomMatch492NameNumber_end_23.isEmptyConcProduction() )) { tom.gom.adt.gom.types.Production  tomMatch492NameNumber_freshVar_30= tomMatch492NameNumber_end_23.getHeadConcProduction() ;if ( (tomMatch492NameNumber_freshVar_30 instanceof tom.gom.adt.gom.types.production.Production) ) { tom.gom.adt.gom.types.GomType  tomMatch492NameNumber_freshVar_28= tomMatch492NameNumber_freshVar_30.getCodomain() ;if ( (tomMatch492NameNumber_freshVar_28 instanceof tom.gom.adt.gom.types.gomtype.GomType) ) { String  tom_typeName= tomMatch492NameNumber_freshVar_28.getName() ;











        if (getGomEnvironment().isBuiltinSort(tom_typeName)) {
          result.add(getGomEnvironment().builtinSort(tom_typeName));
        } else {
          result.add( tom.gom.adt.gom.types.sortdecl.SortDecl.make(tom_typeName,  tom.gom.adt.gom.types.moduledecl.ModuleDecl.make(tom_moduleName, getStreamManager().getPackagePath(tom_moduleName.getName())) ) );
        }
      }}}if ( tomMatch492NameNumber_end_23.isEmptyConcProduction() ) {tomMatch492NameNumber_end_23=tomMatch492NameNumber_freshVar_18;} else {tomMatch492NameNumber_end_23= tomMatch492NameNumber_end_23.getTailConcProduction() ;}}} while(!( (tomMatch492NameNumber_end_23==tomMatch492NameNumber_freshVar_18) ));}}}if ( tomMatch492NameNumber_end_15.isEmptyConcGrammar() ) {tomMatch492NameNumber_end_15=tomMatch492NameNumber_freshVar_10;} else {tomMatch492NameNumber_end_15= tomMatch492NameNumber_end_15.getTailConcGrammar() ;}}} while(!( (tomMatch492NameNumber_end_15==tomMatch492NameNumber_freshVar_10) ));}}}if ( tomMatch492NameNumber_end_7.isEmptyConcSection() ) {tomMatch492NameNumber_end_7=tomMatch492NameNumber_freshVar_2;} else {tomMatch492NameNumber_end_7= tomMatch492NameNumber_end_7.getTailConcSection() ;}}} while(!( (tomMatch492NameNumber_end_7==tomMatch492NameNumber_freshVar_2) ));}}}}}{{if ( (module instanceof tom.gom.adt.gom.types.GomModule) ) {if ( ((( tom.gom.adt.gom.types.GomModule )module) instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) { tom.gom.adt.gom.types.SectionList  tomMatch493NameNumber_freshVar_2= (( tom.gom.adt.gom.types.GomModule )module).getSectionList() ; tom.gom.adt.gom.types.GomModuleName  tom_moduleName= (( tom.gom.adt.gom.types.GomModule )module).getModuleName() ;if ( ((tomMatch493NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.ConsConcSection) || (tomMatch493NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.sectionlist.EmptyConcSection)) ) { tom.gom.adt.gom.types.SectionList  tomMatch493NameNumber_end_7=tomMatch493NameNumber_freshVar_2;do {{if (!( tomMatch493NameNumber_end_7.isEmptyConcSection() )) { tom.gom.adt.gom.types.Section  tomMatch493NameNumber_freshVar_11= tomMatch493NameNumber_end_7.getHeadConcSection() ;if ( (tomMatch493NameNumber_freshVar_11 instanceof tom.gom.adt.gom.types.section.Public) ) { tom.gom.adt.gom.types.GrammarList  tomMatch493NameNumber_freshVar_10= tomMatch493NameNumber_freshVar_11.getGrammarList() ;if ( ((tomMatch493NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.ConsConcGrammar) || (tomMatch493NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.grammarlist.EmptyConcGrammar)) ) { tom.gom.adt.gom.types.GrammarList  tomMatch493NameNumber_end_15=tomMatch493NameNumber_freshVar_10;do {{if (!( tomMatch493NameNumber_end_15.isEmptyConcGrammar() )) { tom.gom.adt.gom.types.Grammar  tomMatch493NameNumber_freshVar_19= tomMatch493NameNumber_end_15.getHeadConcGrammar() ;if ( (tomMatch493NameNumber_freshVar_19 instanceof tom.gom.adt.gom.types.grammar.Grammar) ) { tom.gom.adt.gom.types.ProductionList  tomMatch493NameNumber_freshVar_18= tomMatch493NameNumber_freshVar_19.getProductionList() ;if ( ((tomMatch493NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.ConsConcProduction) || (tomMatch493NameNumber_freshVar_18 instanceof tom.gom.adt.gom.types.productionlist.EmptyConcProduction)) ) { tom.gom.adt.gom.types.ProductionList  tomMatch493NameNumber_end_23=tomMatch493NameNumber_freshVar_18;do {{if (!( tomMatch493NameNumber_end_23.isEmptyConcProduction() )) { tom.gom.adt.gom.types.Production  tomMatch493NameNumber_freshVar_27= tomMatch493NameNumber_end_23.getHeadConcProduction() ;if ( (tomMatch493NameNumber_freshVar_27 instanceof tom.gom.adt.gom.types.production.SortType) ) { tom.gom.adt.gom.types.ProductionList  tomMatch493NameNumber_freshVar_26= tomMatch493NameNumber_freshVar_27.getProductionList() ;if ( ((tomMatch493NameNumber_freshVar_26 instanceof tom.gom.adt.gom.types.productionlist.ConsConcProduction) || (tomMatch493NameNumber_freshVar_26 instanceof tom.gom.adt.gom.types.productionlist.EmptyConcProduction)) ) { tom.gom.adt.gom.types.ProductionList  tomMatch493NameNumber_end_31=tomMatch493NameNumber_freshVar_26;do {{if (!( tomMatch493NameNumber_end_31.isEmptyConcProduction() )) { tom.gom.adt.gom.types.Production  tomMatch493NameNumber_freshVar_38= tomMatch493NameNumber_end_31.getHeadConcProduction() ;if ( (tomMatch493NameNumber_freshVar_38 instanceof tom.gom.adt.gom.types.production.Production) ) { tom.gom.adt.gom.types.GomType  tomMatch493NameNumber_freshVar_36= tomMatch493NameNumber_freshVar_38.getCodomain() ;if ( (tomMatch493NameNumber_freshVar_36 instanceof tom.gom.adt.gom.types.gomtype.GomType) ) { String  tom_typeName= tomMatch493NameNumber_freshVar_36.getName() ;













        if (getGomEnvironment().isBuiltinSort(tom_typeName)) {
          result.add(getGomEnvironment().builtinSort(tom_typeName));
        } else {
          result.add( tom.gom.adt.gom.types.sortdecl.SortDecl.make(tom_typeName,  tom.gom.adt.gom.types.moduledecl.ModuleDecl.make(tom_moduleName, getStreamManager().getPackagePath(tom_moduleName.getName())) ) );
        }
      }}}if ( tomMatch493NameNumber_end_31.isEmptyConcProduction() ) {tomMatch493NameNumber_end_31=tomMatch493NameNumber_freshVar_26;} else {tomMatch493NameNumber_end_31= tomMatch493NameNumber_end_31.getTailConcProduction() ;}}} while(!( (tomMatch493NameNumber_end_31==tomMatch493NameNumber_freshVar_26) ));}}}if ( tomMatch493NameNumber_end_23.isEmptyConcProduction() ) {tomMatch493NameNumber_end_23=tomMatch493NameNumber_freshVar_18;} else {tomMatch493NameNumber_end_23= tomMatch493NameNumber_end_23.getTailConcProduction() ;}}} while(!( (tomMatch493NameNumber_end_23==tomMatch493NameNumber_freshVar_18) ));}}}if ( tomMatch493NameNumber_end_15.isEmptyConcGrammar() ) {tomMatch493NameNumber_end_15=tomMatch493NameNumber_freshVar_10;} else {tomMatch493NameNumber_end_15= tomMatch493NameNumber_end_15.getTailConcGrammar() ;}}} while(!( (tomMatch493NameNumber_end_15==tomMatch493NameNumber_freshVar_10) ));}}}if ( tomMatch493NameNumber_end_7.isEmptyConcSection() ) {tomMatch493NameNumber_end_7=tomMatch493NameNumber_freshVar_2;} else {tomMatch493NameNumber_end_7= tomMatch493NameNumber_end_7.getTailConcSection() ;}}} while(!( (tomMatch493NameNumber_end_7==tomMatch493NameNumber_freshVar_2) ));}}}}}

    return result;
  }

  /**
   * Get directly imported modules. Skip builtins
   *
   * @param module the main module with imports
   * @return the Collection of imported GomModuleName
   */
  private Collection<GomModuleName> getImportedModules(GomModule module) {
    Set<GomModuleName> imports = new HashSet<GomModuleName>();
    {{if ( (module instanceof tom.gom.adt.gom.types.GomModule) ) {if ( ((( tom.gom.adt.gom.types.GomModule )module) instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) { tom.gom.adt.gom.types.SectionList  tom_sectionList= (( tom.gom.adt.gom.types.GomModule )module).getSectionList() ;

        imports.add( (( tom.gom.adt.gom.types.GomModule )module).getModuleName() );
        {{if ( (tom_sectionList instanceof tom.gom.adt.gom.types.SectionList) ) {if ( (((( tom.gom.adt.gom.types.SectionList )tom_sectionList) instanceof tom.gom.adt.gom.types.sectionlist.ConsConcSection) || ((( tom.gom.adt.gom.types.SectionList )tom_sectionList) instanceof tom.gom.adt.gom.types.sectionlist.EmptyConcSection)) ) { tom.gom.adt.gom.types.SectionList  tomMatch495NameNumber_end_4=(( tom.gom.adt.gom.types.SectionList )tom_sectionList);do {{if (!( tomMatch495NameNumber_end_4.isEmptyConcSection() )) { tom.gom.adt.gom.types.Section  tomMatch495NameNumber_freshVar_8= tomMatch495NameNumber_end_4.getHeadConcSection() ;if ( (tomMatch495NameNumber_freshVar_8 instanceof tom.gom.adt.gom.types.section.Imports) ) { tom.gom.adt.gom.types.ImportList  tomMatch495NameNumber_freshVar_7= tomMatch495NameNumber_freshVar_8.getImportList() ;if ( ((tomMatch495NameNumber_freshVar_7 instanceof tom.gom.adt.gom.types.importlist.ConsConcImportedModule) || (tomMatch495NameNumber_freshVar_7 instanceof tom.gom.adt.gom.types.importlist.EmptyConcImportedModule)) ) { tom.gom.adt.gom.types.ImportList  tomMatch495NameNumber_end_12=tomMatch495NameNumber_freshVar_7;do {{if (!( tomMatch495NameNumber_end_12.isEmptyConcImportedModule() )) { tom.gom.adt.gom.types.ImportedModule  tomMatch495NameNumber_freshVar_16= tomMatch495NameNumber_end_12.getHeadConcImportedModule() ;if ( (tomMatch495NameNumber_freshVar_16 instanceof tom.gom.adt.gom.types.importedmodule.Import) ) { tom.gom.adt.gom.types.GomModuleName  tomMatch495NameNumber_freshVar_15= tomMatch495NameNumber_freshVar_16.getModuleName() ;if ( (tomMatch495NameNumber_freshVar_15 instanceof tom.gom.adt.gom.types.gommodulename.GomModuleName) ) {





            if (!getGomEnvironment().isBuiltin( tomMatch495NameNumber_freshVar_15.getName() )) {
              imports.add(tomMatch495NameNumber_freshVar_15);
            }
          }}}if ( tomMatch495NameNumber_end_12.isEmptyConcImportedModule() ) {tomMatch495NameNumber_end_12=tomMatch495NameNumber_freshVar_7;} else {tomMatch495NameNumber_end_12= tomMatch495NameNumber_end_12.getTailConcImportedModule() ;}}} while(!( (tomMatch495NameNumber_end_12==tomMatch495NameNumber_freshVar_7) ));}}}if ( tomMatch495NameNumber_end_4.isEmptyConcSection() ) {tomMatch495NameNumber_end_4=(( tom.gom.adt.gom.types.SectionList )tom_sectionList);} else {tomMatch495NameNumber_end_4= tomMatch495NameNumber_end_4.getTailConcSection() ;}}} while(!( (tomMatch495NameNumber_end_4==(( tom.gom.adt.gom.types.SectionList )tom_sectionList)) ));}}}}

      }}}}

    return imports;
  }

  private GomModule getModule(GomModuleName modname, GomModuleList list) {
    {{if ( (list instanceof tom.gom.adt.gom.types.GomModuleList) ) {if ( (((( tom.gom.adt.gom.types.GomModuleList )list) instanceof tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule) || ((( tom.gom.adt.gom.types.GomModuleList )list) instanceof tom.gom.adt.gom.types.gommodulelist.EmptyConcGomModule)) ) { tom.gom.adt.gom.types.GomModuleList  tomMatch496NameNumber_end_4=(( tom.gom.adt.gom.types.GomModuleList )list);do {{if (!( tomMatch496NameNumber_end_4.isEmptyConcGomModule() )) { tom.gom.adt.gom.types.GomModule  tomMatch496NameNumber_freshVar_8= tomMatch496NameNumber_end_4.getHeadConcGomModule() ;if ( (tomMatch496NameNumber_freshVar_8 instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) {

        if ( tomMatch496NameNumber_freshVar_8.getModuleName() .equals(modname)) {
          return  tomMatch496NameNumber_end_4.getHeadConcGomModule() ;
        }
      }}if ( tomMatch496NameNumber_end_4.isEmptyConcGomModule() ) {tomMatch496NameNumber_end_4=(( tom.gom.adt.gom.types.GomModuleList )list);} else {tomMatch496NameNumber_end_4= tomMatch496NameNumber_end_4.getTailConcGomModule() ;}}} while(!( (tomMatch496NameNumber_end_4==(( tom.gom.adt.gom.types.GomModuleList )list)) ));}}}}

    throw new GomRuntimeException("Module "+ modname +" not present");
  }

  private Collection<GomModuleName> getTransitiveClosureImports(
      GomModule module,
      GomModuleList moduleList) {
    Set<GomModuleName> imported = new HashSet<GomModuleName>();
    imported.addAll(getImportedModules(module));

    Set<GomModuleName> newSet = new HashSet<GomModuleName>();
    while(!newSet.equals(imported)) {
      newSet.addAll(imported);
      imported.addAll(newSet);
      for (GomModuleName modname : imported) {
        newSet.addAll(getImportedModules(getModule(modname,moduleList)));
      }
    }
    return newSet;
  }

  private void buildDependencyMap(GomModuleList moduleList) {
    {{if ( (moduleList instanceof tom.gom.adt.gom.types.GomModuleList) ) {if ( (((( tom.gom.adt.gom.types.GomModuleList )moduleList) instanceof tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule) || ((( tom.gom.adt.gom.types.GomModuleList )moduleList) instanceof tom.gom.adt.gom.types.gommodulelist.EmptyConcGomModule)) ) { tom.gom.adt.gom.types.GomModuleList  tomMatch497NameNumber_end_4=(( tom.gom.adt.gom.types.GomModuleList )moduleList);do {{if (!( tomMatch497NameNumber_end_4.isEmptyConcGomModule() )) { tom.gom.adt.gom.types.GomModule  tomMatch497NameNumber_freshVar_8= tomMatch497NameNumber_end_4.getHeadConcGomModule() ;if ( (tomMatch497NameNumber_freshVar_8 instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) { tom.gom.adt.gom.types.GomModuleName  tom_moduleName= tomMatch497NameNumber_freshVar_8.getModuleName() ;

        ModuleDeclList importsModuleDeclList =  tom.gom.adt.gom.types.moduledecllist.EmptyConcModuleDecl.make() ;
        Iterator it = getTransitiveClosureImports( tomMatch497NameNumber_end_4.getHeadConcGomModule() ,moduleList).iterator();
        while(it.hasNext()) {
          GomModuleName importedModuleName = (GomModuleName) it.next();
          importsModuleDeclList = 
             tom.gom.adt.gom.types.moduledecllist.ConsConcModuleDecl.make( tom.gom.adt.gom.types.moduledecl.ModuleDecl.make(importedModuleName, getStreamManager().getPackagePath(importedModuleName.getName())) ,tom_append_list_ConcModuleDecl(importsModuleDeclList, tom.gom.adt.gom.types.moduledecllist.EmptyConcModuleDecl.make() )) 
;
        }
        getGomEnvironment().addModuleDependency(
             tom.gom.adt.gom.types.moduledecl.ModuleDecl.make(tom_moduleName, getStreamManager().getPackagePath(tom_moduleName.getName())) ,importsModuleDeclList);
      }}if ( tomMatch497NameNumber_end_4.isEmptyConcGomModule() ) {tomMatch497NameNumber_end_4=(( tom.gom.adt.gom.types.GomModuleList )moduleList);} else {tomMatch497NameNumber_end_4= tomMatch497NameNumber_end_4.getTailConcGomModule() ;}}} while(!( (tomMatch497NameNumber_end_4==(( tom.gom.adt.gom.types.GomModuleList )moduleList)) ));}}}}

  }

  private boolean checkSortValidity(Sort sort) {
    boolean valid = true;
    // check if the same slot name is used with different types
    Map<String,SortDecl> mapNameType = new HashMap<String,SortDecl>();
    {{if ( (sort instanceof tom.gom.adt.gom.types.Sort) ) {if ( ((( tom.gom.adt.gom.types.Sort )sort) instanceof tom.gom.adt.gom.types.sort.Sort) ) { tom.gom.adt.gom.types.SortDecl  tomMatch498NameNumber_freshVar_1= (( tom.gom.adt.gom.types.Sort )sort).getDecl() ; tom.gom.adt.gom.types.OperatorDeclList  tomMatch498NameNumber_freshVar_2= (( tom.gom.adt.gom.types.Sort )sort).getOperatorDecls() ;boolean tomMatch498NameNumber_freshVar_25= false ; String  tomMatch498NameNumber_freshVar_4= "" ;if ( (tomMatch498NameNumber_freshVar_1 instanceof tom.gom.adt.gom.types.sortdecl.SortDecl) ) {{tomMatch498NameNumber_freshVar_25= true ;tomMatch498NameNumber_freshVar_4= tomMatch498NameNumber_freshVar_1.getName() ;}} else {if ( (tomMatch498NameNumber_freshVar_1 instanceof tom.gom.adt.gom.types.sortdecl.BuiltinSortDecl) ) {{tomMatch498NameNumber_freshVar_25= true ;tomMatch498NameNumber_freshVar_4= tomMatch498NameNumber_freshVar_1.getName() ;}}}if ( tomMatch498NameNumber_freshVar_25== true  ) {if ( ((tomMatch498NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.operatordecllist.ConsConcOperator) || (tomMatch498NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.operatordecllist.EmptyConcOperator)) ) { tom.gom.adt.gom.types.OperatorDeclList  tomMatch498NameNumber_end_9=tomMatch498NameNumber_freshVar_2;do {{if (!( tomMatch498NameNumber_end_9.isEmptyConcOperator() )) { tom.gom.adt.gom.types.OperatorDecl  tomMatch498NameNumber_freshVar_13= tomMatch498NameNumber_end_9.getHeadConcOperator() ;if ( (tomMatch498NameNumber_freshVar_13 instanceof tom.gom.adt.gom.types.operatordecl.OperatorDecl) ) { tom.gom.adt.gom.types.TypedProduction  tomMatch498NameNumber_freshVar_12= tomMatch498NameNumber_freshVar_13.getProd() ;if ( (tomMatch498NameNumber_freshVar_12 instanceof tom.gom.adt.gom.types.typedproduction.Slots) ) { tom.gom.adt.gom.types.SlotList  tomMatch498NameNumber_freshVar_14= tomMatch498NameNumber_freshVar_12.getSlots() ;if ( ((tomMatch498NameNumber_freshVar_14 instanceof tom.gom.adt.gom.types.slotlist.ConsConcSlot) || (tomMatch498NameNumber_freshVar_14 instanceof tom.gom.adt.gom.types.slotlist.EmptyConcSlot)) ) { tom.gom.adt.gom.types.SlotList  tomMatch498NameNumber_end_19=tomMatch498NameNumber_freshVar_14;do {{if (!( tomMatch498NameNumber_end_19.isEmptyConcSlot() )) { tom.gom.adt.gom.types.Slot  tomMatch498NameNumber_freshVar_24= tomMatch498NameNumber_end_19.getHeadConcSlot() ;if ( (tomMatch498NameNumber_freshVar_24 instanceof tom.gom.adt.gom.types.slot.Slot) ) { String  tom_slotName= tomMatch498NameNumber_freshVar_24.getName() ; tom.gom.adt.gom.types.SortDecl  tom_slotSort= tomMatch498NameNumber_freshVar_24.getSort() ;






        if(!mapNameType.containsKey(tom_slotName)) {
          mapNameType.put(tom_slotName,tom_slotSort);
        } else {
          SortDecl prevSort = mapNameType.get(tom_slotName);
          if (!prevSort.equals(tom_slotSort)) {
            getLogger().log(Level.SEVERE,
                GomMessage.slotIncompatibleTypes.getMessage(),
                new Object[]{tomMatch498NameNumber_freshVar_4,tom_slotName,prevSort.getName(),
                             (tom_slotSort).getName()});
            valid = false;
          }
        }
      }}if ( tomMatch498NameNumber_end_19.isEmptyConcSlot() ) {tomMatch498NameNumber_end_19=tomMatch498NameNumber_freshVar_14;} else {tomMatch498NameNumber_end_19= tomMatch498NameNumber_end_19.getTailConcSlot() ;}}} while(!( (tomMatch498NameNumber_end_19==tomMatch498NameNumber_freshVar_14) ));}}}}if ( tomMatch498NameNumber_end_9.isEmptyConcOperator() ) {tomMatch498NameNumber_end_9=tomMatch498NameNumber_freshVar_2;} else {tomMatch498NameNumber_end_9= tomMatch498NameNumber_end_9.getTailConcOperator() ;}}} while(!( (tomMatch498NameNumber_end_9==tomMatch498NameNumber_freshVar_2) ));}}}}}}

    return valid;
  }

  private String showSortList(Collection<SortDecl> decls) {
    String sorts = "";
    Iterator<SortDecl> it = decls.iterator();
    if(it.hasNext()) {
      SortDecl decl = it.next();
      sorts += decl.getName();
    }
    while(it.hasNext()) {
      SortDecl decl = it.next();
      sorts += ", "+decl.getName();
    }
    return sorts;
  }

  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
}
