/*  Generated by TOM: Do not edit this file */  /*
  
    TOM - To One Matching Compiler

    Copyright (C) 2000-2003 INRIA
			    Nancy, France.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA

    Pierre-Etienne Moreau	e-mail: Pierre-Etienne.Moreau@loria.fr

*/

package jtom.compiler;
  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jtom.TomBase;
import jtom.adt.tomsignature.*;
import jtom.adt.tomsignature.types.*;
import jtom.runtime.Replace1;
import jtom.runtime.Replace2;
import aterm.ATerm;
import jtom.exception.TomRuntimeException;

public class TomKernelExpander extends TomBase {

  public TomKernelExpander(jtom.TomEnvironment environment) {
    super(environment);
  }

// ------------------------------------------------------------
  /*  Generated by TOM: Do not edit this file */     
// ------------------------------------------------------------
  
    /*
     * replace Name by Symbol
     * replace Name by Variable
     */
  private Replace2 replaceVariable = new Replace2() { 
      public ATerm apply(ATerm subject, Object arg1) {
        TomTerm contextSubject = (TomTerm)arg1;

        if(contextSubject == null) {
          throw new TomRuntimeException(new Throwable("expandVariable: null contextSubject"));
        }

          //System.out.println("expandVariable:\n\t" + subject );

        if(!(subject instanceof TomTerm)) {
            //debugPrintln("expandVariable not a tomTerm: " );
            //System.out.println("expandVariable not a tomTerm:\n\t" + subject );
          if(subject instanceof TomType) {
             {  TomType tom_match1_1 = null; tom_match1_1 = ( TomType) subject;_match1_pattern1: {  String tomType = null; if(tom_is_fun_sym_TomTypeAlone(tom_match1_1) ||  false ) {  String tom_match1_1_1 = null; tom_match1_1_1 = ( String) tom_get_slot_TomTypeAlone_string(tom_match1_1); tomType = ( String) tom_match1_1_1;
 
                TomType type = getType(tomType);
                if(type != null) {
                  return type;
                } else {
                  return subject; // useful for TomTypeAlone("unknown type")
                }
               }} }
 
          }
          return traversal().genericTraversal(subject,this,contextSubject);
        }

          //System.out.println("expandVariable is a tomTerm:\n\t" + subject );
        
         {  TomTerm tom_match2_1 = null;  TomTerm tom_match2_2 = null; tom_match2_1 = ( TomTerm) contextSubject; tom_match2_2 = ( TomTerm) subject;_match2_pattern1: {  TomList l = null;  TomName tom_absvar1 = null;  OptionList optionList = null;  NameList nameList = null;  TomType type = null; if(tom_is_fun_sym_TomTypeToTomTerm(tom_match2_1) ||  false ) {  TomType tom_match2_1_1 = null; tom_match2_1_1 = ( TomType) tom_get_slot_TomTypeToTomTerm_astType(tom_match2_1); if(tom_is_fun_sym_Type(tom_match2_1_1) ||  false ) { type = ( TomType) tom_match2_1_1; if(tom_is_fun_sym_Appl(tom_match2_2) ||  false ) {  OptionList tom_match2_2_1 = null;  NameList tom_match2_2_2 = null;  TomList tom_match2_2_3 = null; tom_match2_2_1 = ( OptionList) tom_get_slot_Appl_option(tom_match2_2); tom_match2_2_2 = ( NameList) tom_get_slot_Appl_nameList(tom_match2_2); tom_match2_2_3 = ( TomList) tom_get_slot_Appl_args(tom_match2_2); optionList = ( OptionList) tom_match2_2_1; if(tom_is_fun_sym_concTomName(tom_match2_2_2) ||  false ) { nameList = ( NameList) tom_match2_2_2;  NameList tom_match2_2_2_list1 = null; tom_match2_2_2_list1 = ( NameList) tom_match2_2_2; if(!(tom_is_empty_NameList(tom_match2_2_2_list1))) { tom_absvar1 = ( TomName) tom_get_head_NameList(tom_match2_2_2_list1); tom_match2_2_2_list1 = ( NameList) tom_get_tail_NameList(tom_match2_2_2_list1); l = ( TomList) tom_match2_2_3; {  TomName tom_match3_1 = null; tom_match3_1 = ( TomName) tom_absvar1;_match3_pattern1: {  String strName = null; if(tom_is_fun_sym_Name(tom_match3_1) ||  false ) {  String tom_match3_1_1 = null; tom_match3_1_1 = ( String) tom_get_slot_Name_string(tom_match3_1); strName = ( String) tom_match3_1_1;
 
              //debugPrintln("expandVariable.1: Type(" + tomType + "," + glType + ")");
            Option orgTrack = findOriginTracking(optionList);
            OptionList option = replaceAnnotedName(optionList, type, orgTrack) ;
              // create a constant or a variable

              //TomSymbol tomSymbol = getSymbol(strName);
            TomSymbol tomSymbol;
            if(strName.equals("")) {
              tomSymbol = getSymbol(type);
              nameList = tom_make_insert_concTomName(tomSymbol .getAstName(),( NameList) tom_make_empty_concTomName()) ;
            } else {
              tomSymbol = getSymbol(strName);
            }

            if(tomSymbol != null) {
              TomList subterm = expandVariableList(tomSymbol, l);
              return tom_make_Appl(option,nameList,subterm) ;
            } else {
              if(l.isEmpty()  && !hasConstructor(optionList)) {
                return tom_make_Variable(option,nameList .getHead(),type) ;
              } else {
                TomList subterm = expandVariableList(tom_make_emptySymbol() , l);
                return tom_make_Appl(option,nameList,subterm) ;
              }
            }

            
           }} } } } } } }}_match2_pattern2: {  OptionList optionList = null;  NameList nameList = null;  TomName name1 = null;  OptionList option1 = null;  TomName tom_absvar2 = null;  TomList l = null;  TomType type1 = null; if(tom_is_fun_sym_Variable(tom_match2_1) ||  false ) {  OptionList tom_match2_1_1 = null;  TomName tom_match2_1_2 = null;  TomType tom_match2_1_3 = null; tom_match2_1_1 = ( OptionList) tom_get_slot_Variable_option(tom_match2_1); tom_match2_1_2 = ( TomName) tom_get_slot_Variable_astName(tom_match2_1); tom_match2_1_3 = ( TomType) tom_get_slot_Variable_astType(tom_match2_1); option1 = ( OptionList) tom_match2_1_1; name1 = ( TomName) tom_match2_1_2; type1 = ( TomType) tom_match2_1_3; if(tom_is_fun_sym_Appl(tom_match2_2) ||  false ) {  OptionList tom_match2_2_1 = null;  NameList tom_match2_2_2 = null;  TomList tom_match2_2_3 = null; tom_match2_2_1 = ( OptionList) tom_get_slot_Appl_option(tom_match2_2); tom_match2_2_2 = ( NameList) tom_get_slot_Appl_nameList(tom_match2_2); tom_match2_2_3 = ( TomList) tom_get_slot_Appl_args(tom_match2_2); optionList = ( OptionList) tom_match2_2_1; if(tom_is_fun_sym_concTomName(tom_match2_2_2) ||  false ) { nameList = ( NameList) tom_match2_2_2;  NameList tom_match2_2_2_list1 = null; tom_match2_2_2_list1 = ( NameList) tom_match2_2_2; if(!(tom_is_empty_NameList(tom_match2_2_2_list1))) { tom_absvar2 = ( TomName) tom_get_head_NameList(tom_match2_2_2_list1); tom_match2_2_2_list1 = ( NameList) tom_get_tail_NameList(tom_match2_2_2_list1); l = ( TomList) tom_match2_2_3; {  TomName tom_match4_1 = null; tom_match4_1 = ( TomName) tom_absvar2;_match4_pattern1: {  String strName = null; if(tom_is_fun_sym_Name(tom_match4_1) ||  false ) {  String tom_match4_1_1 = null; tom_match4_1_1 = ( String) tom_get_slot_Name_string(tom_match4_1); strName = ( String) tom_match4_1_1;

 
              //System.out.println("expandVariable.3: Variable(" + option1 + "," + name1 + "," + type1 + ")");
            Option orgTrack = findOriginTracking(optionList);
            OptionList option = replaceAnnotedName(optionList, type1, orgTrack) ;
              // under a match construct
              // create a constant or a variable
              //TomSymbol tomSymbol = getSymbol(strName);
            TomSymbol tomSymbol;
            if(strName.equals("")) {
              tomSymbol = getSymbol(type1);
              nameList = tom_make_insert_concTomName(tomSymbol .getAstName(),( NameList) tom_make_empty_concTomName()) ;
            } else {
              tomSymbol = getSymbol(strName);
            }

            if(tomSymbol != null) {
              TomList subterm = expandVariableList(tomSymbol, l);
              return tom_make_Appl(option,nameList,subterm) ;
            } else {
              if(l.isEmpty()  && !hasConstructor(optionList)) {
                return tom_make_Variable(option,nameList .getHead(),type1) ;
              } else {
                TomList subterm = expandVariableList(tom_make_emptySymbol() , l);
                return tom_make_Appl(option,nameList,subterm) ;
              }
            }

              /*
            if(tomSymbol != null) {
            	//System.out.println("----->Symbol:"+tomSymbol+ "\n-->args:"+l);
              TomList subterm = expandVariableList(tomSymbol, l);
              return `Appl(option,nameList,subterm);
            } else if(l.isEmpty()) {
              return `Variable(option,nameList.getHead(),type1);
            }
              */
            
           }} } } } } }}_match2_pattern3: {  TomType tomType = null;  TomTerm p = null;  TomType type = null;  OptionList optionList = null;  TomType glType = null; if(tom_is_fun_sym_TomTypeToTomTerm(tom_match2_1) ||  false ) {  TomType tom_match2_1_1 = null; tom_match2_1_1 = ( TomType) tom_get_slot_TomTypeToTomTerm_astType(tom_match2_1); if(tom_is_fun_sym_Type(tom_match2_1_1) ||  false ) {  TomType tom_match2_1_1_1 = null;  TomType tom_match2_1_1_2 = null; tom_match2_1_1_1 = ( TomType) tom_get_slot_Type_tomType(tom_match2_1_1); tom_match2_1_1_2 = ( TomType) tom_get_slot_Type_tlType(tom_match2_1_1); type = ( TomType) tom_match2_1_1; tomType = ( TomType) tom_match2_1_1_1; glType = ( TomType) tom_match2_1_1_2; if(tom_is_fun_sym_Placeholder(tom_match2_2) ||  false ) {  OptionList tom_match2_2_1 = null; tom_match2_2_1 = ( OptionList) tom_get_slot_Placeholder_option(tom_match2_2); p = ( TomTerm) tom_match2_2; optionList = ( OptionList) tom_match2_2_1;

 
            Option orgTrack = findOriginTracking(optionList);
            OptionList option = replaceAnnotedName(optionList, type, orgTrack) ;
              // create an unamed variable
            return tom_make_UnamedVariable(option,type) ;
           } } }}_match2_pattern4: {  TomName name1 = null;  OptionList option1 = null;  TomType type1 = null;  OptionList optionList = null;  TomTerm p = null; if(tom_is_fun_sym_Variable(tom_match2_1) ||  false ) {  OptionList tom_match2_1_1 = null;  TomName tom_match2_1_2 = null;  TomType tom_match2_1_3 = null; tom_match2_1_1 = ( OptionList) tom_get_slot_Variable_option(tom_match2_1); tom_match2_1_2 = ( TomName) tom_get_slot_Variable_astName(tom_match2_1); tom_match2_1_3 = ( TomType) tom_get_slot_Variable_astType(tom_match2_1); option1 = ( OptionList) tom_match2_1_1; name1 = ( TomName) tom_match2_1_2; type1 = ( TomType) tom_match2_1_3; if(tom_is_fun_sym_Placeholder(tom_match2_2) ||  false ) {  OptionList tom_match2_2_1 = null; tom_match2_2_1 = ( OptionList) tom_get_slot_Placeholder_option(tom_match2_2); p = ( TomTerm) tom_match2_2; optionList = ( OptionList) tom_match2_2_1;

 
            Option orgTrack = findOriginTracking(optionList);
            OptionList option = replaceAnnotedName(optionList, type1, orgTrack) ;
              // create an unamed variable
            return tom_make_UnamedVariable(option,type1) ;
           } }}_match2_pattern5: {  OptionList optionList = null;  TomList l = null;  NameList nameList = null;  TomTerm appl = null;  TomName tom_absvar3 = null;  TomTerm context = null; context = ( TomTerm) tom_match2_1; if(tom_is_fun_sym_Appl(tom_match2_2) ||  false ) {  OptionList tom_match2_2_1 = null;  NameList tom_match2_2_2 = null;  TomList tom_match2_2_3 = null; tom_match2_2_1 = ( OptionList) tom_get_slot_Appl_option(tom_match2_2); tom_match2_2_2 = ( NameList) tom_get_slot_Appl_nameList(tom_match2_2); tom_match2_2_3 = ( TomList) tom_get_slot_Appl_args(tom_match2_2); appl = ( TomTerm) tom_match2_2; optionList = ( OptionList) tom_match2_2_1; if(tom_is_fun_sym_concTomName(tom_match2_2_2) ||  false ) { nameList = ( NameList) tom_match2_2_2;  NameList tom_match2_2_2_list1 = null; tom_match2_2_2_list1 = ( NameList) tom_match2_2_2; if(!(tom_is_empty_NameList(tom_match2_2_2_list1))) { tom_absvar3 = ( TomName) tom_get_head_NameList(tom_match2_2_2_list1); tom_match2_2_2_list1 = ( NameList) tom_get_tail_NameList(tom_match2_2_2_list1); l = ( TomList) tom_match2_2_3; {  TomName tom_match5_1 = null; tom_match5_1 = ( TomName) tom_absvar3;_match5_pattern1: {  String tomName = null; if(tom_is_fun_sym_Name(tom_match5_1) ||  false ) {  String tom_match5_1_1 = null; tom_match5_1_1 = ( String) tom_get_slot_Name_string(tom_match5_1); tomName = ( String) tom_match5_1_1;

 
               //System.out.println("expandVariable.6: Appl(Name(" + tomName + ")," + l + ")");
              // create a  symbol
            TomSymbol tomSymbol = getSymbol(tomName);
            if(tomSymbol != null) {
              TomList subterm = expandVariableList(tomSymbol, l);
                //System.out.println("***** expandVariable.6: expandVariableList = " + subterm);
              Option orgTrack = findOriginTracking(optionList);
              OptionList option = replaceAnnotedName(optionList, getSymbolCodomain(tomSymbol), orgTrack) ;
              return tom_make_Appl(option,nameList,subterm) ;
            } else {
                // do nothing
            }
           }} } } } }}_match2_pattern6: {  OptionList option = null;  String strName = null;  TomTerm context = null;  String tomType = null; context = ( TomTerm) tom_match2_1; if(tom_is_fun_sym_Variable(tom_match2_2) ||  false ) {  OptionList tom_match2_2_1 = null;  TomName tom_match2_2_2 = null;  TomType tom_match2_2_3 = null; tom_match2_2_1 = ( OptionList) tom_get_slot_Variable_option(tom_match2_2); tom_match2_2_2 = ( TomName) tom_get_slot_Variable_astName(tom_match2_2); tom_match2_2_3 = ( TomType) tom_get_slot_Variable_astType(tom_match2_2); option = ( OptionList) tom_match2_2_1; if(tom_is_fun_sym_Name(tom_match2_2_2) ||  false ) {  String tom_match2_2_2_1 = null; tom_match2_2_2_1 = ( String) tom_get_slot_Name_string(tom_match2_2_2); strName = ( String) tom_match2_2_2_1; if(tom_is_fun_sym_TomTypeAlone(tom_match2_2_3) ||  false ) {  String tom_match2_2_3_1 = null; tom_match2_2_3_1 = ( String) tom_get_slot_TomTypeAlone_string(tom_match2_2_3); tomType = ( String) tom_match2_2_3_1;

 
              // create a variable
            TomType localType = getType(tomType);
            return tom_make_Variable(option,tom_make_Name(strName),localType) ;
           } } }}_match2_pattern7: {  String strName = null;  String tomType = null;  TomTerm context = null; context = ( TomTerm) tom_match2_1; if(tom_is_fun_sym_TLVar(tom_match2_2) ||  false ) {  String tom_match2_2_1 = null;  TomType tom_match2_2_2 = null; tom_match2_2_1 = ( String) tom_get_slot_TLVar_strName(tom_match2_2); tom_match2_2_2 = ( TomType) tom_get_slot_TLVar_astType(tom_match2_2); strName = ( String) tom_match2_2_1; if(tom_is_fun_sym_TomTypeAlone(tom_match2_2_2) ||  false ) {  String tom_match2_2_2_1 = null; tom_match2_2_2_1 = ( String) tom_get_slot_TomTypeAlone_string(tom_match2_2_2); tomType = ( String) tom_match2_2_2_1;

 
              //debugPrintln("expandVariable.8: TLVar(" + strName + "," + tomType + ")");
              // create a variable: its type is ensured by checker
            TomType localType = getType(tomType);
            OptionList option = ast().makeOption();
            return tom_make_Variable(option,tom_make_Name(strName),localType) ;
           } }}_match2_pattern8: {  TomList l1 = null;  TomList subjectList = null; if(tom_is_fun_sym_SubjectList(tom_match2_1) ||  false ) {  TomList tom_match2_1_1 = null; tom_match2_1_1 = ( TomList) tom_get_slot_SubjectList_tomList(tom_match2_1); l1 = ( TomList) tom_match2_1_1; if(tom_is_fun_sym_TermList(tom_match2_2) ||  false ) {  TomList tom_match2_2_1 = null; tom_match2_2_1 = ( TomList) tom_get_slot_TermList_tomList(tom_match2_2); subjectList = ( TomList) tom_match2_2_1;

 
            //System.out.println("expandVariable.9: "+l1+"(" + subjectList + ")");
                
              // process a list of subterms
            ArrayList list = new ArrayList();
            while(!subjectList.isEmpty()) {
              list.add(expandVariable(l1.getHead(), subjectList.getHead()));
              subjectList = subjectList.getTail();
              l1 = l1.getTail();
            }
            return tom_make_TermList(ast() .makeList(list)) ;
           } }}_match2_pattern9: {  TomTerm tomSubjectList = null;  TomTerm context = null;  OptionList option = null;  TomTerm patternList = null; context = ( TomTerm) tom_match2_1; if(tom_is_fun_sym_Match(tom_match2_2) ||  false ) {  TomTerm tom_match2_2_1 = null;  TomTerm tom_match2_2_2 = null;  OptionList tom_match2_2_3 = null; tom_match2_2_1 = ( TomTerm) tom_get_slot_Match_subjectList(tom_match2_2); tom_match2_2_2 = ( TomTerm) tom_get_slot_Match_patternList(tom_match2_2); tom_match2_2_3 = ( OptionList) tom_get_slot_Match_option(tom_match2_2); tomSubjectList = ( TomTerm) tom_match2_2_1; patternList = ( TomTerm) tom_match2_2_2; option = ( OptionList) tom_match2_2_3;

 
            //debugPrintln("expandVariable.10: Match(" + tomSubjectList + "," + patternList + ")");
            TomTerm newSubjectList = expandVariable(context,tomSubjectList);
            TomTerm newPatternList = expandVariable(newSubjectList,patternList);
            return tom_make_Match(newSubjectList,newPatternList,option) ;
           }}_match2_pattern10: {  TomTerm context = null;  TomTerm t = null; context = ( TomTerm) tom_match2_1; t = ( TomTerm) tom_match2_2;


 
            //debugPrintln("expandVariable.11 default: " );
            //System.out.println("expandVariable default:\n\t" + subject );
            return traversal().genericTraversal(subject,this,contextSubject);
          } }
  // end match
      } // end apply
    }; // end new

  public TomTerm expandVariable(TomTerm contextSubject, TomTerm subject) {
    return (TomTerm) replaceVariable.apply(subject,contextSubject); 
  }

  public TomList expandVariableList(TomSymbol subject, TomList subjectList) {
    //%variable
    if(subject == null) {
      throw new TomRuntimeException(new Throwable("expandVariableList: null subject"));
    }
    
     {  TomSymbol tom_match6_1 = null; tom_match6_1 = ( TomSymbol) subject;_match6_pattern1: { if(tom_is_fun_sym_emptySymbol(tom_match6_1) ||  false ) {

 
          /*
           * If the top symbol is unknown, the subterms
           * are expanded in an empty context
           */
        ArrayList list = new ArrayList();
        while(!subjectList.isEmpty()) {
          list.add(expandVariable(tom_make_emptyTerm() , subjectList.getHead()));
          subjectList = subjectList.getTail();
        }
        return ast().makeList(list);
       }}_match6_pattern2: {  TomSymbol symb = null;  TomTypeList typeList = null;  TomType codomainType = null; if(tom_is_fun_sym_Symbol(tom_match6_1) ||  false ) {  TomType tom_match6_1_2 = null; tom_match6_1_2 = ( TomType) tom_get_slot_Symbol_typesToType(tom_match6_1); symb = ( TomSymbol) tom_match6_1; if(tom_is_fun_sym_TypesToType(tom_match6_1_2) ||  false ) {  TomTypeList tom_match6_1_2_1 = null;  TomType tom_match6_1_2_2 = null; tom_match6_1_2_1 = ( TomTypeList) tom_get_slot_TypesToType_domain(tom_match6_1_2); tom_match6_1_2_2 = ( TomType) tom_get_slot_TypesToType_codomain(tom_match6_1_2); typeList = ( TomTypeList) tom_match6_1_2_1; codomainType = ( TomType) tom_match6_1_2_2;

 
          
          // process a list of subterms and a list of types
        TomList result = null;
        ArrayList list = new ArrayList();
        if(isListOperator(symb) || isArrayOperator(symb)) {
          /*
           * TODO:
           * when the symbol is an associative operator,
           * the signature has the form: List conc( Element* )
           * the list of types is reduced to the singleton { Element }
           *
           * consider a pattern: conc(E1*,x,E2*,y,E3*)
           *  assign the type "Element" to each subterm: x and y
           *  assign the type "List" to each subtermList: E1*,E2* and E3*
           */

          //System.out.println("listOperator: " + symb);
          //System.out.println("subjectList: " + subjectList);
          
          TomType domainType = typeList.getHead();
          while(!subjectList.isEmpty()) {
            TomTerm subterm = subjectList.getHead();
              //System.out.println("subterm:\n" + subterm);
            matchBlock: {
               {  TomTerm tom_match7_1 = null; tom_match7_1 = ( TomTerm) subterm;_match7_pattern1: {  OptionList optionList = null;  TomName name = null; if(tom_is_fun_sym_VariableStar(tom_match7_1) ||  false ) {  OptionList tom_match7_1_1 = null;  TomName tom_match7_1_2 = null; tom_match7_1_1 = ( OptionList) tom_get_slot_VariableStar_option(tom_match7_1); tom_match7_1_2 = ( TomName) tom_get_slot_VariableStar_astName(tom_match7_1); optionList = ( OptionList) tom_match7_1_1; name = ( TomName) tom_match7_1_2;
 
                  Option orgTrack = findOriginTracking(optionList);
                  OptionList option = replaceAnnotedName(optionList, codomainType, orgTrack) ;
                  list.add(tom_make_VariableStar(option,name,codomainType) );
                    //System.out.println("*** break: " + subterm);
                  break matchBlock;
                 }}_match7_pattern2: {  OptionList optionList = null; if(tom_is_fun_sym_UnamedVariableStar(tom_match7_1) ||  false ) {  OptionList tom_match7_1_1 = null; tom_match7_1_1 = ( OptionList) tom_get_slot_UnamedVariableStar_option(tom_match7_1); optionList = ( OptionList) tom_match7_1_1;

 
                  Option orgTrack = findOriginTracking(optionList);
                  OptionList option = replaceAnnotedName(optionList, codomainType, orgTrack) ;
                  list.add(tom_make_UnamedVariableStar(option,codomainType) );
                  break matchBlock;
                 }}_match7_pattern3: {

 
                  list.add(expandVariable(tom_make_TomTypeToTomTerm(domainType) , subterm));
                  break matchBlock;
                } }
 
            }
            subjectList = subjectList.getTail();
          }
        } else {
          while(!subjectList.isEmpty()) {
            list.add(expandVariable(tom_make_TomTypeToTomTerm(typeList .getHead()) , subjectList.getHead()));
            subjectList = subjectList.getTail();
            typeList    = typeList.getTail();
          }
        }
       
        result = ast().makeList(list);
        return result;
       } }}_match6_pattern3: {

 
        System.out.println("expandVariableList: strange case: '" + subject + "'");
        throw new TomRuntimeException(new Throwable("expandVariableList: strange case: '" + subject + "'"));
      } }
 
  }

    /*
     * updateSymbol is called after a first syntax expansion phase
     * this phase updates the symbolTable according to the typeTable
     * this is performed by recursively traversing each symbol
     * each TomTypeAlone is replace by the corresponding TomType
     */
  public void updateSymbolTable() {
    Iterator it = symbolTable().keySymbolIterator();
    while(it.hasNext()) {
      String tomName = (String)it.next();
      TomTerm emptyContext = tom_make_emptyTerm() ;
      TomSymbol tomSymbol = getSymbol(tomName);
      tomSymbol = expandVariable(emptyContext,tom_make_TomSymbolToTomTerm(tomSymbol) ).getAstSymbol();
      symbolTable().putSymbol(tomName,tomSymbol);
    }
  }

  private TomType getType(String tomName) {
    TomType tomType = symbolTable().getType(tomName);
    return tomType;
  }


  private OptionList replaceAnnotedName(OptionList subjectList, TomType type, Option orgTrack) {
    //%variable
     {  OptionList tom_match8_1 = null; tom_match8_1 = ( OptionList) subjectList;_match8_pattern1: { if(tom_is_fun_sym_emptyOptionList(tom_match8_1) ||  false ) {
  return subjectList;  }}_match8_pattern2: {  OptionList l = null;  TomName name = null; if(tom_is_fun_sym_manyOptionList(tom_match8_1) ||  false ) {  Option tom_match8_1_1 = null;  OptionList tom_match8_1_2 = null; tom_match8_1_1 = ( Option) tom_get_slot_manyOptionList_head(tom_match8_1); tom_match8_1_2 = ( OptionList) tom_get_slot_manyOptionList_tail(tom_match8_1); if(tom_is_fun_sym_TomNameToOption(tom_match8_1_1) ||  false ) {  TomName tom_match8_1_1_1 = null; tom_match8_1_1_1 = ( TomName) tom_get_slot_TomNameToOption_astName(tom_match8_1_1); if(tom_is_fun_sym_Name(tom_match8_1_1_1) ||  false ) { name = ( TomName) tom_match8_1_1_1; l = ( OptionList) tom_match8_1_2;
 
        return tom_make_manyOptionList(tom_make_TomTermToOption(tom_make_Variable(ast() .makeOption(orgTrack),name,type)),replaceAnnotedName(l, type, orgTrack))

 ;
       } } }}_match8_pattern3: {  OptionList l = null;  Option t = null; if(tom_is_fun_sym_manyOptionList(tom_match8_1) ||  false ) {  Option tom_match8_1_1 = null;  OptionList tom_match8_1_2 = null; tom_match8_1_1 = ( Option) tom_get_slot_manyOptionList_head(tom_match8_1); tom_match8_1_2 = ( OptionList) tom_get_slot_manyOptionList_tail(tom_match8_1); t = ( Option) tom_match8_1_1; l = ( OptionList) tom_match8_1_2;
 
        return tom_make_manyOptionList(t,replaceAnnotedName(l, type, orgTrack)) ;
       }} }
 
    return null;
  }
  
    /*
     * Replace pattern with only variables or underscore (UnamedVariables)
     * By DefaultPattern
     */
  public TomTerm expandMatchPattern(TomTerm subject) {
    Replace1 replace = new Replace1() { 
        public ATerm apply(ATerm subject) {
          if(subject instanceof TomTerm) {
             {  TomTerm tom_match9_1 = null; tom_match9_1 = ( TomTerm) subject;_match9_pattern1: {  TomTerm subjectList = null;  TomTerm m = null;  OptionList option = null;  TomTerm patternList = null; if(tom_is_fun_sym_Match(tom_match9_1) ||  false ) {  TomTerm tom_match9_1_1 = null;  TomTerm tom_match9_1_2 = null;  OptionList tom_match9_1_3 = null; tom_match9_1_1 = ( TomTerm) tom_get_slot_Match_subjectList(tom_match9_1); tom_match9_1_2 = ( TomTerm) tom_get_slot_Match_patternList(tom_match9_1); tom_match9_1_3 = ( OptionList) tom_get_slot_Match_option(tom_match9_1); m = ( TomTerm) tom_match9_1; subjectList = ( TomTerm) tom_match9_1_1; patternList = ( TomTerm) tom_match9_1_2; option = ( OptionList) tom_match9_1_3;
 
                  // find other match in PA list
                TomTerm newPatternList = expandMatchPattern(patternList);
                return expandPattern(tom_make_Match(subjectList,newPatternList,option) ); 
               }}_match9_pattern2: {
 
                return traversal().genericTraversal(subject,this);
              } }
  // end match
          } else {
            return traversal().genericTraversal(subject,this);
          }
        } // end apply
      }; // end new
    
    return (TomTerm) replace.apply(subject); 
  }

  private TomTerm expandPattern(TomTerm match) {
     {  TomTerm tom_match10_1 = null; tom_match10_1 = ( TomTerm) match;_match10_pattern1: {  TomTerm subjectList = null;  TomList list = null;  OptionList option = null; if(tom_is_fun_sym_Match(tom_match10_1) ||  false ) {  TomTerm tom_match10_1_1 = null;  TomTerm tom_match10_1_2 = null;  OptionList tom_match10_1_3 = null; tom_match10_1_1 = ( TomTerm) tom_get_slot_Match_subjectList(tom_match10_1); tom_match10_1_2 = ( TomTerm) tom_get_slot_Match_patternList(tom_match10_1); tom_match10_1_3 = ( OptionList) tom_get_slot_Match_option(tom_match10_1); subjectList = ( TomTerm) tom_match10_1_1; if(tom_is_fun_sym_PatternList(tom_match10_1_2) ||  false ) {  TomList tom_match10_1_2_1 = null; tom_match10_1_2_1 = ( TomList) tom_get_slot_PatternList_tomList(tom_match10_1_2); list = ( TomList) tom_match10_1_2_1; option = ( OptionList) tom_match10_1_3;
 
        boolean needModification = false;
        TomList newPatternList = empty();
        while(!list.isEmpty()) {
          TomTerm pa = list.getHead();
          if( isDefaultPattern(pa.getTermList().getTomList()) ) {
            OptionList newPatternActionOption =  tom_make_manyOptionList(tom_make_DefaultCase(),pa .getOption()) ;
            newPatternList = cons(tom_make_PatternAction(pa .getTermList(),pa .getTom(),newPatternActionOption) , newPatternList);
            needModification = true;
            if(!list.getTail().isEmpty()) {
                // the default pattern is not the latest one!!
              System.out.println("Default pattern issue"+pa.getOption());
            }
          } else {
              // we keep the PA
            newPatternList = cons(list.getHead(), newPatternList);
          }
          list = list.getTail();
        }
        if(needModification) {
          newPatternList = reverse(newPatternList);
          OptionList newMatchOption =tom_make_manyOptionList(tom_make_DefaultCase(),option) ;
          return tom_make_Match(subjectList,tom_make_PatternList(newPatternList),newMatchOption) ;
        } else {
          return match;
        }
       } }}_match10_pattern2: {
 
        System.out.println("Strange Match in expandMatchPattern"+match);
		throw new TomRuntimeException(new Throwable("Strange Match in expandMatchPattern"+match));
      } }
 
  }

  private boolean isDefaultPattern(TomList pList) {
    TomTerm term;
    while(!pList.isEmpty()) {
      term = pList.getHead();
       {  TomTerm tom_match11_1 = null; tom_match11_1 = ( TomTerm) term;_match11_pattern1: { if(tom_is_fun_sym_Appl(tom_match11_1) ||  false ) {
 
          return false;
         }} }
 
      pList = pList.getTail();
    }
    
    ArrayList variableList = new ArrayList();
    collectVariable(variableList,tom_make_PatternList(pList) );
    
      // compute multiplicities
    HashMap multiplicityMap = new HashMap();
    Iterator it = variableList.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName name = variable.getAstName();
      if(multiplicityMap.containsKey(name)) {
        Integer value = (Integer)multiplicityMap.get(name);
        return false;
      } else {
        multiplicityMap.put(name, new Integer(1));
      }
    }
    return true;
  }
  
} // Class TomKernelExpander
