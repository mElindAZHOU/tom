/* Generated by TOM (version 2.4rc1): Do not edit this file *//*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2006, INRIA
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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
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

  /* Generated by TOM (version 2.4rc1): Do not edit this file *//* Generated by TOM (version 2.4rc1): Do not edit this file *//* Generated by TOM (version 2.4rc1): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4rc1): Do not edit this file */ /* Generated by TOM (version 2.4rc1): Do not edit this file */ /* Generated by TOM (version 2.4rc1): Do not edit this file */ /* Generated by TOM (version 2.4rc1): Do not edit this file */ private static boolean tom_terms_equal_TNode(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TNodeList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_ElementNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.ElementNode  ;}private static  String  tom_get_slot_ElementNode_Name( tom.library.adt.tnode.types.TNode  t) {  return  t.getName()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_AttrList( tom.library.adt.tnode.types.TNode  t) {  return  t.getAttrList()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_ChildList( tom.library.adt.tnode.types.TNode  t) {  return  t.getChildList()  ;}private static boolean tom_is_fun_sym_AttributeNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.AttributeNode  ;}private static  String  tom_get_slot_AttributeNode_Name( tom.library.adt.tnode.types.TNode  t) {  return  t.getName()  ;}private static  String  tom_get_slot_AttributeNode_Specified( tom.library.adt.tnode.types.TNode  t) {  return  t.getSpecified()  ;}private static  String  tom_get_slot_AttributeNode_Value( tom.library.adt.tnode.types.TNode  t) {  return  t.getValue()  ;}private static boolean tom_is_fun_sym_concTNode( tom.library.adt.tnode.types.TNodeList  t) {  return  t instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode || t instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_empty_list_concTNode() { return  tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ; }private static  tom.library.adt.tnode.types.TNodeList  tom_cons_list_concTNode( tom.library.adt.tnode.types.TNode  e,  tom.library.adt.tnode.types.TNodeList  l) { return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make(e,l) ; }private static  tom.library.adt.tnode.types.TNode  tom_get_head_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.getHeadconcTNode()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_tail_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.getTailconcTNode()  ;}private static boolean tom_is_empty_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.isEmptyconcTNode()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList  l1,  tom.library.adt.tnode.types.TNodeList  l2) {    if(tom_is_empty_concTNode_TNodeList(l1)) {     return l2;    } else if(tom_is_empty_concTNode_TNodeList(l2)) {     return l1;    } else if(tom_is_empty_concTNode_TNodeList(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(l1))) {     return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(l1),l2);    } else {      return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(l1),tom_append_list_concTNode(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(l1),l2));    }   }  private static  tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end) {    if(tom_terms_equal_TNodeList(begin,end)) {      return ( tom.library.adt.tnode.types.TNodeList )tom_empty_list_concTNode();    } else {      return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(begin),( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(begin),end));    }   }   /* Generated by TOM (version 2.4rc1): Do not edit this file */private static boolean tom_terms_equal_PlatformValue(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformOptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformBoolean(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformOption(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static  tom.platform.adt.platformoption.types.PlatformValue  tom_make_BooleanValue( tom.platform.adt.platformoption.types.PlatformBoolean  t0) { return  tom.platform.adt.platformoption.types.platformvalue.BooleanValue.make(t0); }private static  tom.platform.adt.platformoption.types.PlatformBoolean  tom_make_False() { return  tom.platform.adt.platformoption.types.platformboolean.False.make(); }private static  tom.platform.adt.platformoption.types.PlatformOption  tom_make_PluginOption( String  t0,  String  t1,  String  t2,  tom.platform.adt.platformoption.types.PlatformValue  t3,  String  t4) { return  tom.platform.adt.platformoption.types.platformoption.PluginOption.make(t0, t1, t2, t3, t4); }private static boolean tom_is_fun_sym_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption || t instanceof tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_empty_list_concPlatformOption() { return  tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ; }private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_cons_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOption  e,  tom.platform.adt.platformoption.types.PlatformOptionList  l) { return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make(e,l) ; }private static  tom.platform.adt.platformoption.types.PlatformOption  tom_get_head_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.getHeadconcPlatformOption()  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_tail_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.getTailconcPlatformOption()  ;}private static boolean tom_is_empty_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.isEmptyconcPlatformOption()  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {    if(tom_is_empty_concPlatformOption_PlatformOptionList(l1)) {     return l2;    } else if(tom_is_empty_concPlatformOption_PlatformOptionList(l2)) {     return l1;    } else if(tom_is_empty_concPlatformOption_PlatformOptionList(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(l1))) {     return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(l1),l2);    } else {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(l1),tom_append_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(l1),l2));    }   }  private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end) {    if(tom_terms_equal_PlatformOptionList(begin,end)) {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_empty_list_concPlatformOption();    } else {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(begin),( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(begin),end));    }   }   


  private PlatformOptionList allDeclaredOptions;
  private PlatformOptionList allRequiredOptions;
  private Map flagOwners;
  private Object[] argToRelay;
  private OptionManager optionManager; // it is never written!

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
  public void setOptionManager(OptionManager optionManager) {}

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

     if(docNode instanceof  tom.library.adt.tnode.types.TNode ) { { tom.library.adt.tnode.types.TNode  tom_match1_1=(( tom.library.adt.tnode.types.TNode )docNode); if ( ( tom_is_fun_sym_ElementNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Name=tom_get_slot_ElementNode_Name(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_AttrList=tom_get_slot_ElementNode_AttrList(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match1_1); if ( ( tom_terms_equal_String("factory", tom_match1_1_Name) ||  false  ) ) { if ( ( tom_is_fun_sym_concTNode(tom_match1_1_AttrList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match1_1_AttrList_list1=tom_match1_1_AttrList; if ( ( tom_is_fun_sym_concTNode(tom_match1_1_ChildList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList_list1=tom_match1_1_ChildList; {boolean tom_match1_tom_anti_constraints_status= true ; { tom.library.adt.tnode.types.TNode  tom_fact=tom_match1_1; if ((tom_match1_tom_anti_constraints_status ==  true )) { if ( true ) { if(tom_fact instanceof  tom.library.adt.tnode.types.TNode ) { { tom.library.adt.tnode.types.TNode  tom_match2_1=(( tom.library.adt.tnode.types.TNode )tom_fact); if ( ( tom_is_fun_sym_ElementNode(tom_match2_1) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match2_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match2_1); { tom.library.adt.tnode.types.TNodeList  tom_cl=tom_match2_1_ChildList; {boolean tom_match2_tom_anti_constraints_status= true ; if ((tom_match2_tom_anti_constraints_status ==  true )) { if ( true ) {




            while(!(tom_cl.isEmptyconcTNode())) {
              TNode pluginNode = tom_cl.getHeadconcTNode();

               if(pluginNode instanceof  tom.library.adt.tnode.types.TNode ) { { tom.library.adt.tnode.types.TNode  tom_match3_1=(( tom.library.adt.tnode.types.TNode )pluginNode); if ( ( tom_is_fun_sym_ElementNode(tom_match3_1) ||  false  ) ) { { String  tom_match3_1_Name=tom_get_slot_ElementNode_Name(tom_match3_1); { tom.library.adt.tnode.types.TNodeList  tom_match3_1_AttrList=tom_get_slot_ElementNode_AttrList(tom_match3_1); { tom.library.adt.tnode.types.TNodeList  tom_match3_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match3_1); if ( ( tom_terms_equal_String("plugin", tom_match3_1_Name) ||  false  ) ) { if ( ( tom_is_fun_sym_concTNode(tom_match3_1_AttrList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match3_1_AttrList_list1=tom_match3_1_AttrList; { tom.library.adt.tnode.types.TNodeList  tom_match3_1_AttrList_begin1=tom_match3_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match3_1_AttrList_end1=tom_match3_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match3_1_AttrList_end1))) {tom_match3_1_AttrList_list1=tom_match3_1_AttrList_end1; { { tom.library.adt.tnode.types.TNode  tom_match3_1_AttrList_2=tom_get_head_concTNode_TNodeList(tom_match3_1_AttrList_list1);tom_match3_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match3_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match3_1_AttrList_2) ||  false  ) ) { { String  tom_match3_1_AttrList_2_Name=tom_get_slot_AttributeNode_Name(tom_match3_1_AttrList_2); { String  tom_match3_1_AttrList_2_Specified=tom_get_slot_AttributeNode_Specified(tom_match3_1_AttrList_2); { String  tom_match3_1_AttrList_2_Value=tom_get_slot_AttributeNode_Value(tom_match3_1_AttrList_2); if ( ( tom_terms_equal_String("classpath", tom_match3_1_AttrList_2_Name) ||  false  ) ) { { String  tom_cp=tom_match3_1_AttrList_2_Value; if ( ( tom_is_fun_sym_concTNode(tom_match3_1_ChildList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match3_1_ChildList_list1=tom_match3_1_ChildList; if (tom_is_empty_concTNode_TNodeList(tom_match3_1_ChildList_list1)) { {boolean tom_match3_tom_anti_constraints_status= true ; if ((tom_match3_tom_anti_constraints_status ==  true )) { if ( true ) {
 classPaths.add(tom_cp);/*System.out.println(cp);*/  } } } } } } } } } } } } }tom_match3_1_AttrList_end1=tom_get_tail_concTNode_TNodeList(tom_match3_1_AttrList_end1); } }tom_match3_1_AttrList_list1=tom_match3_1_AttrList_begin1; } } } } } } } } } } } }tom_cl

= tom_cl.getTailconcTNode();
            }
           } } } } } } } }

       } } } } } } } } } } } } } } }

  }

}
