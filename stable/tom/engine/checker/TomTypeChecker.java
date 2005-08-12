/* Generated by TOM (version 2.3rc0): Do not edit this file *//*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2005, INRIA
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
 * Julien Guyon
 *
 **/

package jtom.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import jtom.TomMessage;
import jtom.adt.tomsignature.types.*;
import tom.library.traversal.Collect1;
import tom.platform.OptionParser;
import tom.platform.adt.platformoption.types.PlatformOptionList;
import aterm.ATerm;

/**
 * The TomTypeChecker plugin.
 */
public class TomTypeChecker extends TomChecker {

  /* Generated by TOM (version 2.3rc0): Do not edit this file *//* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2005, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2005, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  * Copyright (c) 2004-2005, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */   /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2005, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2005, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2005, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     

  /** the declared options string */
  public static final String DECLARED_OPTIONS = "<options><boolean name='noTypeCheck' altName='' description='Do not perform type checking' value='false'/></options>";

  /**
   * inherited from OptionOwner interface (plugin) 
   */
  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(TomTypeChecker.DECLARED_OPTIONS);
  }

  /** Constructor */
  public TomTypeChecker() {
    super("TomTypeChecker");
  }
  
  public void run() {
    if(isActivated()) {
      strictType = !getOptionBooleanValue("lazyType");
      long startChrono = System.currentTimeMillis();
      try {
        // clean up internals
        reinit();
        // perform analyse
        checkTypeInference( (TomTerm)getWorkingTerm() );
        // verbose
        getLogger().log( Level.INFO, TomMessage.tomTypeCheckingPhase.getMessage(),
                         new Integer((int)(System.currentTimeMillis()-startChrono)) );
      } catch (Exception e) {
        getLogger().log( Level.SEVERE, TomMessage.exceptionMessage.getMessage(),
                         new Object[]{getClass().getName(), getStreamManager().getInputFile().getName(),e.getMessage()} );
        e.printStackTrace();
      }
    } else {
      // type checker desactivated    
      getLogger().log(Level.INFO, TomMessage.typeCheckerInactivated.getMessage());
    }
  }
  
  private boolean isActivated() {
    return !getOptionBooleanValue("noTypeCheck");
  }
  
  /**
   * Main type checking entry point:
   * We check all Match and RuleSet instructions
   */
  private void checkTypeInference(TomTerm expandedTerm) {
    Collect1 collectAndVerify = new Collect1() {  
        public boolean apply(ATerm term) {
          if(term instanceof Instruction) {
             { jtom.adt.tomsignature.types.Instruction tom_match1_1=(( jtom.adt.tomsignature.types.Instruction)term); if(tom_is_fun_sym_Match(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.PatternInstructionList tom_match1_1_astPatternInstructionList=tom_get_slot_Match_astPatternInstructionList(tom_match1_1); { jtom.adt.tomsignature.types.OptionList tom_match1_1_option=tom_get_slot_Match_option(tom_match1_1);
  
                currentTomStructureOrgTrack = findOriginTracking(tom_match1_1_option);
                verifyMatchVariable(tom_match1_1_astPatternInstructionList);
                return false;
              }} } if(tom_is_fun_sym_RuleSet(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomRuleList tom_match1_1_ruleList=tom_get_slot_RuleSet_ruleList(tom_match1_1); { jtom.adt.tomsignature.types.Option tom_match1_1_orgTrack=tom_get_slot_RuleSet_orgTrack(tom_match1_1);

                currentTomStructureOrgTrack = tom_match1_1_orgTrack;
                verifyRuleVariable(tom_match1_1_ruleList);
                return false;
              }} }}

          } 
          return true;
        }// end apply
      }; // end new
    traversal().genericCollect(expandedTerm, collectAndVerify);
  } //checkTypeInference

  /* 
   * Collect unknown (not in symbol table) appls without ()
   */
  private void collectUnknownsAppls(ArrayList unknownsApplsInWhen, TomList guards) {
    Collect1 collectAndVerify = new Collect1() {  
        public boolean apply(ATerm term) {
          if(term instanceof TomTerm) {
             { jtom.adt.tomsignature.types.TomTerm tom_match2_1=(( jtom.adt.tomsignature.types.TomTerm)term); if(tom_is_fun_sym_TermAppl(tom_match2_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_app=tom_match2_1; { jtom.adt.tomsignature.types.OptionList tom_match2_1_option=tom_get_slot_TermAppl_option(tom_match2_1); { jtom.adt.tomsignature.types.TomList tom_match2_1_args=tom_get_slot_TermAppl_args(tom_match2_1); if(tom_is_fun_sym_concTomTerm(tom_match2_1_args) ||  false ) { { jtom.adt.tomsignature.types.TomList tom_match2_1_args_list1=tom_match2_1_args; if(tom_is_empty_concTomTerm_TomList(tom_match2_1_args_list1)) {

                boolean isConstructor = false;
                 { jtom.adt.tomsignature.types.OptionList tom_match3_1=(( jtom.adt.tomsignature.types.OptionList)tom_match2_1_option); if(tom_is_fun_sym_concOption(tom_match3_1) ||  false ) { { jtom.adt.tomsignature.types.OptionList tom_match3_1_list1=tom_match3_1; { jtom.adt.tomsignature.types.OptionList tom_match3_1_begin1=tom_match3_1_list1; { jtom.adt.tomsignature.types.OptionList tom_match3_1_end1=tom_match3_1_list1;{ while (!(tom_is_empty_concOption_OptionList(tom_match3_1_end1))) {tom_match3_1_list1=tom_match3_1_end1;{ { jtom.adt.tomsignature.types.Option tom_match3_1_2=tom_get_head_concOption_OptionList(tom_match3_1_list1);tom_match3_1_list1=tom_get_tail_concOption_OptionList(tom_match3_1_list1); if(tom_is_fun_sym_Constructor(tom_match3_1_2) ||  false ) {

                    isConstructor = true;
                   }}tom_match3_1_end1=tom_get_tail_concOption_OptionList(tom_match3_1_end1);} }tom_match3_1_list1=tom_match3_1_begin1;}}}} }}

                if(!isConstructor) {
                  if((symbolTable().getSymbolFromName(getName(tom_app)))==null) {
                    messageError(findOriginTrackingLine(tom_app.getOption()),
                                 TomMessage.unknownVariableInWhen.getMessage(),
                                 new Object[]{getName(tom_app)});
                  }
                  // else, it's actually app()
                } // else, it's a unknown (ie : java) function
                return true;
               }} }}}} }}

          } 
          return true;
        }// end apply
      }; // end new
    traversal().genericCollect(guards, collectAndVerify);
  }
  
  private void verifyMatchVariable(PatternInstructionList patternInstructionList) {
    while(!patternInstructionList.isEmpty()) {
      PatternInstruction pa = patternInstructionList.getHead();
      Pattern pattern = pa.getPattern();
        // collect variables
      ArrayList variableList = new ArrayList();
      collectVariable(variableList, pattern);
      verifyVariableTypeListCoherence(variableList);
      // verify variables in WHEN instruction
      ArrayList unknownsApplsInWhen = new ArrayList();
      // collect unknown variables
      collectUnknownsAppls(unknownsApplsInWhen, pattern.getGuards());
      //System.out.println("vars in guard "+unknownsApplsInWhen);

      patternInstructionList = patternInstructionList.getTail();
    }
  } //verifyMatchVariable
  
  /**
   * The notion of conditional rewrite rule can be generalised with a sequence of conditions
   * as in lhs -> rhs where P1:=C1 ... where Pn:=Cn if Qj==Dj 
   * (i) Var(Pi) inter (var(lhs) U var(P1) U ... U var(Pi-1)) = empty
   * => introduced variables in Pi are "fresh"
   * (ii) var(ci) included in (var(lhs) U var(P1) U ... U var(Pi-1))
   * no new fresh variables in Ci
   * (iii) Var(rhs) included in (var(lhs) U U(var(Pi)))
   * => no new fresh variables in r
   * (iv) the condition Qj==Dj shall never lead to the declaration of a new variable
   */
  private void verifyRuleVariable(TomRuleList list) {
    while(!list.isEmpty()) {
      TomRule rewriteRule = list.getHead();
      TomTerm ruleLhs = rewriteRule.getLhs();
      TomTerm ruleRhs = rewriteRule.getRhs();
      InstructionList condList = rewriteRule.getCondList();
      Option orgTrack = findOriginTracking(rewriteRule.getOption());
           
      // the accumulator for defined variables
      Hashtable variableTable = new Hashtable();
      // collect lhs variable 
      ArrayList freshLhsVariableList = new ArrayList();
      collectVariable(freshLhsVariableList, ruleLhs);

      // fill the table with found variables in lhs
      if(!appendToTable(variableTable, freshLhsVariableList)) {
        // there are already some coherence issues: same name but not same type
        break;
      }
      
       { jtom.adt.tomsignature.types.InstructionList tom_match4_1=(( jtom.adt.tomsignature.types.InstructionList)condList); if(tom_is_fun_sym_concInstruction(tom_match4_1) ||  false ) { { jtom.adt.tomsignature.types.InstructionList tom_match4_1_list1=tom_match4_1; { jtom.adt.tomsignature.types.InstructionList tom_match4_1_begin1=tom_match4_1_list1; { jtom.adt.tomsignature.types.InstructionList tom_match4_1_end1=tom_match4_1_list1;{ while (!(tom_is_empty_concInstruction_InstructionList(tom_match4_1_end1))) {tom_match4_1_list1=tom_match4_1_end1;{ { jtom.adt.tomsignature.types.Instruction tom_cond=tom_get_head_concInstruction_InstructionList(tom_match4_1_list1);tom_match4_1_list1=tom_get_tail_concInstruction_InstructionList(tom_match4_1_list1);

          Instruction condition = tom_cond;
           { jtom.adt.tomsignature.types.Instruction tom_match5_1=(( jtom.adt.tomsignature.types.Instruction)condition); if(tom_is_fun_sym_MatchingCondition(tom_match5_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match5_1_lhs=tom_get_slot_MatchingCondition_lhs(tom_match5_1); { jtom.adt.tomsignature.types.TomTerm tom_match5_1_rhs=tom_get_slot_MatchingCondition_rhs(tom_match5_1);

              // (i)
              ArrayList pVar = new ArrayList();
              collectVariable(pVar, tom_match5_1_lhs);
              if(!areAllFreshVariableTest(pVar, variableTable)) {
                // at least one no fresh variable
                break;
              }
              // (ii)
              ArrayList cVar = new ArrayList();
              collectVariable(cVar, tom_match5_1_rhs);
              if(!areAllExistingVariableTest(cVar, variableTable, TomMessage.declaredVariableIssueInWhere)) {
                // there is a fresh variable
                break;
              }
              
              // fill the table
              if(!appendToTable(variableTable, pVar)) {
                // there are some coherence issues: same name but not same type
                break;
              }
            }} } if(tom_is_fun_sym_TypedEqualityCondition(tom_match5_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match5_1_lhs=tom_get_slot_TypedEqualityCondition_lhs(tom_match5_1); { jtom.adt.tomsignature.types.TomTerm tom_match5_1_rhs=tom_get_slot_TypedEqualityCondition_rhs(tom_match5_1);

               // (iv)
              ArrayList pVar = new ArrayList();
              collectVariable(pVar, tom_match5_1_lhs);
              if(!areAllExistingVariableTest(pVar, variableTable, TomMessage.declaredVariableIssueInIf)) {
                // there is a fresh variable
                break;
              }
              // (iv)
              ArrayList cVar = new ArrayList();
              collectVariable(cVar, tom_match5_1_rhs);
              if(!areAllExistingVariableTest(cVar, variableTable, TomMessage.declaredVariableIssueInIf)) {
                // there is a fresh variable
                break;
              }
              
              // fill the table
              if(!appendToTable(variableTable, pVar)) {
                // there are some coherence issues: same name but not same type
                break;
              }
            }} }}

        }tom_match4_1_end1=tom_get_tail_concInstruction_InstructionList(tom_match4_1_end1);} }tom_match4_1_list1=tom_match4_1_begin1;}}}} }}

      
      // (iii)
      ArrayList variableRhs = new ArrayList();
      collectVariable(variableRhs, ruleRhs);
      areAllExistingVariableTest(variableRhs, variableTable, TomMessage.unknownRuleRhsVariable);
      
      // next rewrite rule
      list = list.getTail();
    }
  } //verifyRuleVariable
  
  private void verifyVariableTypeListCoherence(ArrayList list) {
    // compute multiplicities
    HashSet set = new HashSet();
    HashMap map = new HashMap();
    Iterator it = list.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName name = variable.getAstName();
      
      if(set.contains(name.getString())) {
        TomTerm var = (TomTerm)map.get(name);
        TomType type = var.getAstType();
        TomType type2 = variable.getAstType();
        if(!(type==type2)) {
          messageError(findOriginTrackingLine(variable.getOption()),
                       TomMessage.incoherentVariable.getMessage(),
                       new Object[]{name.getString(), type.getTomType().getString(), type2.getTomType().getString()});
        }
      } else {
        map.put(name, variable);
        set.add(name.getString());
      }
    }
  }  //verifyVariableTypeListCoherence  

  /**
   * Append variables to table 
   * if variable name already exists, it ensures it is coherent with the previous found type
   * return true if OK else false
   */
  private boolean appendToTable(Hashtable variableTable, List freshLhsVariableList) {
    Iterator it = freshLhsVariableList.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName nameVar = variable.getAstName();
      TomType typeVar = variable.getAstType();
        
      if(variableTable.containsKey(nameVar)) {
        // this is an issue
        //TomTerm var = (TomTerm)variableTable.get(nameVar);
        //TomType type = var.getAstType();
        TomType type = (TomType)variableTable.get(nameVar);
        if(!(type==typeVar)) {
          messageError(findOriginTrackingLine(variable.getOption()),
                       TomMessage.incoherentVariable.getMessage(),
                       new Object[]{nameVar.getString(), type.getTomType().getString(), typeVar.getTomType().getString()});
          return false;
        }
      } else {
        // append to table
        variableTable.put(nameVar, typeVar);
        //System.out.println("Adding "+nameVar+" "+typeVar);
      }
    }
    return true;
  }
  
  /** (i) condition */
  private boolean areAllFreshVariableTest(ArrayList pVar, Hashtable variableTable) {
    Iterator it = pVar.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName name = variable.getAstName();
      if(variableTable.containsKey(name)) {
        messageError(findOriginTrackingLine(variable.getOption()),
                     TomMessage.freshVariableIssue.getMessage(),
                     new Object[]{name.getString()});
         
        return false;
      }
    }
    return true;
  }

  /** (ii) condition and (iii) at the end when varaibleTable is full */
  private boolean areAllExistingVariableTest(ArrayList cVar, Hashtable variableTable, TomMessage message) {
    Iterator it = cVar.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName name = variable.getAstName();
      if(!variableTable.containsKey(name)) {
        messageError(findOriginTrackingLine(variable.getOption()),
                     message.getMessage(),
                     new Object[]{name.getString()});             
        return false;
      }
    }
    return true;
  }
  
} // class TomTypeChecker
