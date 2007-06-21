/* Generated by TOM (version 2.5rc1): Do not edit this file *//*
 *
 * Copyright (c) 2000-2007, Pierre-Etienne Moreau
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 * 	- Redistributions of source code must retain the above copyright
 * 	notice, this list of conditions and the following disclaimer.  
 * 	- Redistributions in binary form must reproduce the above copyright
 * 	notice, this list of conditions and the following disclaimer in the
 * 	documentation and/or other materials provided with the distribution.
 * 	- Neither the name of the INRIA nor the names of its
 * 	contributors may be used to endorse or promote products derived from
 * 	this software without specific prior written permission.
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
 **/

package tom.library.plugin;

import java.util.*;
import java.util.logging.*;

import tom.library.xml.*;
import tom.library.adt.tnode.*;
import tom.library.adt.tnode.types.*;

import tom.platform.*;
import tom.platform.adt.platformoption.*;
import tom.platform.adt.platformoption.types.*;


/**
 * This Factory, when given a proper XML file, can manage a set of plugins.
 * A plugin managed by this Factory MUST respect two very important conditions:
 * <ul>
 * <li>It MUST have an activation flag, which MUST be the first element in the
 * option list it declares;</li>
 * <li>In case it has other options, it MUST precise in its getRequiredOptions
 * () method that each of these options needs the activation flag to be set to
 * true to be set to another value than the default value.</li>
 * </ul>
 * Of course, the plugin must also implement the Plugin interface,
 * and the XML file must respect this DTD :
 * <!DOCTYPE factory [
 *
 * <!ELEMENT factory (plugin*)>
 *
 * <!ELEMENT plugin EMPTY>
 * <!ATTLIST plugin
 *   name CDATA #IMPLIED
 *   version CDATA #IMPLIED
 *   description CDATA #IMPLIED
 *   classpath CDATA #REQUIRED>
 * ]>
 * Please note that if you put two or more plugins in the set that
 * have their activation flag set to true by default, it will lead to
 * an error if the user doesn't activate manually (i.e. with the
 * command line) one of the plugins.  So all the benefit of having an
 * activation flag set to true by default (that is avoiding to set one
 * manually) will be gone.
 *
 * @author Gr&eacute;gory ANDRIEN
 */

public class PluginFactory implements Plugin {

  /* Generated by TOM (version 2.5rc1): Do not edit this file *//* Generated by TOM (version 2.5rc1): Do not edit this file *//* Generated by TOM (version 2.5rc1): Do not edit this file */ private static boolean tom_equal_term_String(String t1, String t2) { return  (t1.equals(t2)) ;}private static boolean tom_is_sort_String(String t) { return  t instanceof String ;}  /* Generated by TOM (version 2.5rc1): Do not edit this file */ /* Generated by TOM (version 2.5rc1): Do not edit this file */ /* Generated by TOM (version 2.5rc1): Do not edit this file */ /* Generated by TOM (version 2.5rc1): Do not edit this file */ private static boolean tom_equal_term_TNodeList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TNodeList(Object t) { return  t instanceof tom.library.adt.tnode.types.TNodeList ;}private static boolean tom_equal_term_TNode(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TNode(Object t) { return  t instanceof tom.library.adt.tnode.types.TNode ;}private static boolean tom_is_fun_sym_ElementNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.ElementNode ;}private static  String  tom_get_slot_ElementNode_Name( tom.library.adt.tnode.types.TNode  t) { return  t.getName() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_AttrList( tom.library.adt.tnode.types.TNode  t) { return  t.getAttrList() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_ChildList( tom.library.adt.tnode.types.TNode  t) { return  t.getChildList() ;}private static boolean tom_is_fun_sym_AttributeNode( tom.library.adt.tnode.types.TNode  t) { return  t instanceof tom.library.adt.tnode.types.tnode.AttributeNode ;}private static  String  tom_get_slot_AttributeNode_Name( tom.library.adt.tnode.types.TNode  t) { return  t.getName() ;}private static  String  tom_get_slot_AttributeNode_Specified( tom.library.adt.tnode.types.TNode  t) { return  t.getSpecified() ;}private static  String  tom_get_slot_AttributeNode_Value( tom.library.adt.tnode.types.TNode  t) { return  t.getValue() ;}private static boolean tom_is_fun_sym_concTNode( tom.library.adt.tnode.types.TNodeList  t) { return  t instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode || t instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode ;}private static  tom.library.adt.tnode.types.TNodeList  tom_empty_list_concTNode() { return  tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ; }private static  tom.library.adt.tnode.types.TNodeList  tom_cons_list_concTNode( tom.library.adt.tnode.types.TNode  e,  tom.library.adt.tnode.types.TNodeList  l) { return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make(e,l) ; }private static  tom.library.adt.tnode.types.TNode  tom_get_head_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) { return  l.getHeadconcTNode() ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_tail_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) { return  l.getTailconcTNode() ;}private static boolean tom_is_empty_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) { return  l.isEmptyconcTNode() ;}   private static   tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList l1,  tom.library.adt.tnode.types.TNodeList  l2) {     if(tom_is_empty_concTNode_TNodeList(l1)) {       return l2;     } else if(tom_is_empty_concTNode_TNodeList(l2)) {       return l1;     } else if(tom_is_empty_concTNode_TNodeList(tom_get_tail_concTNode_TNodeList(l1))) {       return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(tom_get_head_concTNode_TNodeList(l1),l2);     } else {       return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(tom_get_head_concTNode_TNodeList(l1),tom_append_list_concTNode(tom_get_tail_concTNode_TNodeList(l1),l2));     }   }   private static   tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end, tom.library.adt.tnode.types.TNodeList  tail) {     if(tom_equal_term_TNodeList(begin,end)) {       return tail;     } else {       return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(tom_get_head_concTNode_TNodeList(begin),( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode(tom_get_tail_concTNode_TNodeList(begin),end,tail));     }   }    /* Generated by TOM (version 2.5rc1): Do not edit this file */private static boolean tom_equal_term_PlatformOption(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_PlatformOption(Object t) { return  t instanceof tom.platform.adt.platformoption.types.PlatformOption ;}private static boolean tom_equal_term_PlatformOptionList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_PlatformOptionList(Object t) { return  t instanceof tom.platform.adt.platformoption.types.PlatformOptionList ;}private static boolean tom_equal_term_PlatformBoolean(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_PlatformBoolean(Object t) { return  t instanceof tom.platform.adt.platformoption.types.PlatformBoolean ;}private static boolean tom_equal_term_PlatformValue(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_PlatformValue(Object t) { return  t instanceof tom.platform.adt.platformoption.types.PlatformValue ;}private static  tom.platform.adt.platformoption.types.PlatformOption  tom_make_PluginOption( String  t0,  String  t1,  String  t2,  tom.platform.adt.platformoption.types.PlatformValue  t3,  String  t4) { return  tom.platform.adt.platformoption.types.platformoption.PluginOption.make(t0, t1, t2, t3, t4) ; }private static  tom.platform.adt.platformoption.types.PlatformBoolean  tom_make_False() { return  tom.platform.adt.platformoption.types.platformboolean.False.make() ; }private static  tom.platform.adt.platformoption.types.PlatformValue  tom_make_BooleanValue( tom.platform.adt.platformoption.types.PlatformBoolean  t0) { return  tom.platform.adt.platformoption.types.platformvalue.BooleanValue.make(t0) ; }private static boolean tom_is_fun_sym_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  t) { return  t instanceof tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption || t instanceof tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_empty_list_concPlatformOption() { return  tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ; }private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_cons_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOption  e,  tom.platform.adt.platformoption.types.PlatformOptionList  l) { return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make(e,l) ; }private static  tom.platform.adt.platformoption.types.PlatformOption  tom_get_head_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) { return  l.getHeadconcPlatformOption() ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_tail_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) { return  l.getTailconcPlatformOption() ;}private static boolean tom_is_empty_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) { return  l.isEmptyconcPlatformOption() ;}   private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {     if(tom_is_empty_concPlatformOption_PlatformOptionList(l1)) {       return l2;     } else if(tom_is_empty_concPlatformOption_PlatformOptionList(l2)) {       return l1;     } else if(tom_is_empty_concPlatformOption_PlatformOptionList(tom_get_tail_concPlatformOption_PlatformOptionList(l1))) {       return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(tom_get_head_concPlatformOption_PlatformOptionList(l1),l2);     } else {       return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(tom_get_head_concPlatformOption_PlatformOptionList(l1),tom_append_list_concPlatformOption(tom_get_tail_concPlatformOption_PlatformOptionList(l1),l2));     }   }   private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end, tom.platform.adt.platformoption.types.PlatformOptionList  tail) {     if(tom_equal_term_PlatformOptionList(begin,end)) {       return tail;     } else {       return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(tom_get_head_concPlatformOption_PlatformOptionList(begin),( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption(tom_get_tail_concPlatformOption_PlatformOptionList(begin),end,tail));     }   }    


  private PlatformOptionList allDeclaredOptions;
  private PlatformOptionList allRequiredOptions;
  private Map flagOwners;
  private Object[] argToRelay;
  private OptionManager optionManager;

  private String pluginName;
  private Logger logger;

  protected Logger getLogger() { return logger; }

  private OptionManager getOM() {
    return optionManager;
  }

  public PluginFactory(String name, String xmlFile) {
    allDeclaredOptions = tom_empty_list_concPlatformOption();
    allRequiredOptions = tom_empty_list_concPlatformOption();
    flagOwners = new HashMap();

    pluginName = name;
    logger = Logger.getLogger(getClass().getName());

    List classPaths = new ArrayList();
    List plugins = new ArrayList();

    fillClassPathsList(classPaths, xmlFile);

    // creates an instance of each plugin
    Iterator it = classPaths.iterator();
    while( it.hasNext() ) {
      Object instance;
      String path = (String)it.next();
      try {
        instance = Class.forName(path).newInstance();
        if(instance instanceof Plugin) {
          plugins.add(instance);
        } else {
          logger.log(Level.SEVERE, "ClassNotAPlugin",
                     new Object[]{pluginName, path});
        }
      } catch(ClassNotFoundException cnfe) {
        logger.log(Level.WARNING, "ClassNotFound",
                   new Object[]{pluginName, path});
      } catch(Exception e) {
        logger.log(Level.SEVERE, "InstantiationError",
                   new Object[]{pluginName, path});
      }
    }

    it = plugins.iterator();
    while( it.hasNext() ) {
      Plugin plugin = (Plugin)it.next();

      PlatformOptionList declaredList = plugin.getDeclaredOptionList();
      allDeclaredOptions = tom_append_list_concPlatformOption(allDeclaredOptions,tom_append_list_concPlatformOption(declaredList,tom_empty_list_concPlatformOption()));
      String flagName = declaredList.getHeadconcPlatformOption().getName();
      flagOwners.put(flagName, plugin);

      PlatformOptionList requiredList = plugin.getRequiredOptionList();
      allRequiredOptions = tom_append_list_concPlatformOption(allRequiredOptions,tom_append_list_concPlatformOption(requiredList,tom_empty_list_concPlatformOption()));
    }
  }

  /**
   * From Plugin interface
   */
  public void setArgs(Object arg[]) {
    argToRelay = (Object[]) arg.clone();
  }

  /**
   * From Plugin interface
   */
  public Object[] getArgs() {
    return (Object[]) argToRelay.clone();
  }

  /**
   * From Plugin interface
   */
  public void run() {
    Plugin activatedPlugin = null;
    Iterator it = flagOwners.keySet().iterator();
    while(it.hasNext()) {
      String flagName = (String)it.next();
      if( ((Boolean)getOM().getOptionValue(flagName)).booleanValue() ) {
        activatedPlugin = (Plugin)flagOwners.get(flagName);
      }
    }
    try{
      activatedPlugin.setArgs(argToRelay);
      activatedPlugin.run();
      argToRelay = activatedPlugin.getArgs();
    } catch(NullPointerException npe) {
      System.out.println("Error : No plugin was activated.");
      // TODO: when error management has changed, change this
    }
  }

  /**
   * From OptionOwner interface inherited from Plugin interface
   */
  public void setOptionManager(OptionManager optionManager) {
    this.optionManager = optionManager;
  }

  /**
   * From OptionOwner interface inherited from Plugin interface
   */
  public PlatformOptionList getDeclaredOptionList() {
    return allDeclaredOptions;
  }

  /**
   * From OptionOwner interface inherited from Plugin interface
   */
  public PlatformOptionList getRequiredOptionList() {
    Iterator it = flagOwners.keySet().iterator();
    while(it.hasNext()) { // for all plugins
      String flagName = (String)it.next();
      if(((Boolean)getOM().getOptionValue(flagName)).booleanValue()) {
        // if this plugin is activated
        it = flagOwners.keySet().iterator();

        while( it.hasNext() ) {
          String name = (String)it.next();
          if( !name.equals(flagName) ) // require that the other aren't
            allRequiredOptions = tom_cons_list_concPlatformOption(tom_make_PluginOption(name,"","",tom_make_BooleanValue(tom_make_False()),""),tom_append_list_concPlatformOption(allRequiredOptions,tom_empty_list_concPlatformOption()));
        }
      }
    }

    return allRequiredOptions;
  }

  /**
   * From OptionOwner interface inherited from Plugin interface
   */
  public void optionChanged(String optionName, Object optionValue) {
    getOM().setOptionValue(optionName, optionValue);

    if(optionValue.equals(Boolean.TRUE)) {
      // no more than 1 plugin can be activated at a time
      if( flagOwners.keySet().contains(optionName) ) {
        // if the flag just set is an activation flag...
        Iterator it = flagOwners.keySet().iterator();
        while( it.hasNext() ) {
          String flagName = (String)it.next();
          if( !flagName.equals(optionName) ) {
            getOM().setOptionValue(flagName, Boolean.FALSE);
            // ...desactivate the other flags
            //System.out.println(flagName + " desactivated");
          }
        }
        //System.out.println(optionName + " activated");
      }
    }
  }

  private void fillClassPathsList(List classPaths, String xmlFile) {
    XmlTools xtools = new XmlTools();
    TNode docNode = ( xtools.convertXMLToTNode(xmlFile) ).getDocElem();

    if (tom_is_sort_TNode(docNode)) {{  tom.library.adt.tnode.types.TNode  tomMatch508NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )docNode);{  tom.library.adt.tnode.types.TNode  tomMatch508NameNumber_freshVar_0=tomMatch508NameNumberfreshSubject_1;if (tom_is_fun_sym_ElementNode(tomMatch508NameNumber_freshVar_0)) {{  String  tomMatch508NameNumber_freshVar_1=tom_get_slot_ElementNode_Name(tomMatch508NameNumber_freshVar_0);{  tom.library.adt.tnode.types.TNodeList  tomMatch508NameNumber_freshVar_2=tom_get_slot_ElementNode_AttrList(tomMatch508NameNumber_freshVar_0);{  tom.library.adt.tnode.types.TNodeList  tomMatch508NameNumber_freshVar_3=tom_get_slot_ElementNode_ChildList(tomMatch508NameNumber_freshVar_0);if (tom_equal_term_String("factory", tomMatch508NameNumber_freshVar_1)) {if (tom_is_fun_sym_concTNode(tomMatch508NameNumber_freshVar_2)) {{  tom.library.adt.tnode.types.TNodeList  tomMatch508NameNumber_freshVar_4=tomMatch508NameNumber_freshVar_2;if (tom_is_fun_sym_concTNode(tomMatch508NameNumber_freshVar_3)) {{  tom.library.adt.tnode.types.TNodeList  tomMatch508NameNumber_freshVar_6=tomMatch508NameNumber_freshVar_3;{  tom.library.adt.tnode.types.TNode  tom_fact=tomMatch508NameNumber_freshVar_0;if ( true ) {if (tom_is_sort_TNode(tom_fact)) {{  tom.library.adt.tnode.types.TNode  tomMatch507NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )tom_fact);if (tom_is_fun_sym_ElementNode(tomMatch507NameNumberfreshSubject_1)) {{  tom.library.adt.tnode.types.TNodeList  tomMatch507NameNumber_freshVar_0=tom_get_slot_ElementNode_ChildList(tomMatch507NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tom_cl=tomMatch507NameNumber_freshVar_0;if ( true ) {




            while(!(tom_cl.isEmptyconcTNode())) {
              TNode pluginNode = tom_cl.getHeadconcTNode();

              if (tom_is_sort_TNode(pluginNode)) {{  tom.library.adt.tnode.types.TNode  tomMatch506NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )pluginNode);if (tom_is_fun_sym_ElementNode(tomMatch506NameNumberfreshSubject_1)) {{  String  tomMatch506NameNumber_freshVar_0=tom_get_slot_ElementNode_Name(tomMatch506NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch506NameNumber_freshVar_1=tom_get_slot_ElementNode_AttrList(tomMatch506NameNumberfreshSubject_1);{  tom.library.adt.tnode.types.TNodeList  tomMatch506NameNumber_freshVar_2=tom_get_slot_ElementNode_ChildList(tomMatch506NameNumberfreshSubject_1);if (tom_equal_term_String("plugin", tomMatch506NameNumber_freshVar_0)) {if (tom_is_fun_sym_concTNode(tomMatch506NameNumber_freshVar_1)) {{  tom.library.adt.tnode.types.TNodeList  tomMatch506NameNumber_freshVar_3=tomMatch506NameNumber_freshVar_1;{  tom.library.adt.tnode.types.TNodeList  tomMatch506NameNumber_begin_5=tomMatch506NameNumber_freshVar_3;{  tom.library.adt.tnode.types.TNodeList  tomMatch506NameNumber_end_6=tomMatch506NameNumber_freshVar_3;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch506NameNumber_freshVar_4=tomMatch506NameNumber_end_6;if (!(tom_is_empty_concTNode_TNodeList(tomMatch506NameNumber_freshVar_4))) {if (tom_is_fun_sym_AttributeNode(tom_get_head_concTNode_TNodeList(tomMatch506NameNumber_freshVar_4))) {{  String  tomMatch506NameNumber_freshVar_10=tom_get_slot_AttributeNode_Name(tom_get_head_concTNode_TNodeList(tomMatch506NameNumber_freshVar_4));{  String  tomMatch506NameNumber_freshVar_11=tom_get_slot_AttributeNode_Specified(tom_get_head_concTNode_TNodeList(tomMatch506NameNumber_freshVar_4));{  String  tomMatch506NameNumber_freshVar_12=tom_get_slot_AttributeNode_Value(tom_get_head_concTNode_TNodeList(tomMatch506NameNumber_freshVar_4));if (tom_equal_term_String("classpath", tomMatch506NameNumber_freshVar_10)) {{  tom.library.adt.tnode.types.TNodeList  tomMatch506NameNumber_freshVar_7=tom_get_tail_concTNode_TNodeList(tomMatch506NameNumber_freshVar_4);if (tom_is_fun_sym_concTNode(tomMatch506NameNumber_freshVar_2)) {{  tom.library.adt.tnode.types.TNodeList  tomMatch506NameNumber_freshVar_9=tomMatch506NameNumber_freshVar_2;if (tom_is_empty_concTNode_TNodeList(tomMatch506NameNumber_freshVar_9)) {if ( true ) {
 classPaths.add(tomMatch506NameNumber_freshVar_12);/*System.out.println(cp);*/ }}}}}}}}}}}}if (tom_is_empty_concTNode_TNodeList(tomMatch506NameNumber_end_6)) {tomMatch506NameNumber_end_6=tomMatch506NameNumber_begin_5;} else {tomMatch506NameNumber_end_6=tom_get_tail_concTNode_TNodeList(tomMatch506NameNumber_end_6);}}} while(!(tom_equal_term_TNodeList(tomMatch506NameNumber_end_6, tomMatch506NameNumber_begin_5)));}}}}}}}}}}}tom_cl

= tom_cl.getTailconcTNode();
            }
          }}}}}}

      }}}}}}}}}}}}}}

  }

}
