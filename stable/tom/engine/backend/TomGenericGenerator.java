/* Generated by TOM (version 2.3rc0): Do not edit this file *//*
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

package tom.engine.backend;

import java.io.IOException;
import java.util.HashMap;

import tom.engine.adt.tomsignature.types.*;
import tom.engine.tools.OutputCode;
import tom.engine.tools.SymbolTable;
import tom.platform.OptionManager;

public abstract class TomGenericGenerator extends TomAbstractGenerator {
  
  protected HashMap isFsymMap = new HashMap();
  protected boolean lazyMode;

  public TomGenericGenerator(OutputCode output, OptionManager optionManager,
                             SymbolTable symbolTable) {
    super(output, optionManager, symbolTable);
    lazyMode = ((Boolean)optionManager.getOptionValue("lazyType")).booleanValue();
  }

  // ------------------------------------------------------------
  /* Generated by TOM (version 2.3rc0): Do not edit this file *//* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */   /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 2.3rc0): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     
  // ------------------------------------------------------------

  protected abstract void genDecl(String returnType,
                                  String declName,
                                  String suffix,
                                  String args[],
                                  TargetLanguage tlCode) throws IOException;
  
  protected abstract void genDeclMake(String funName, TomType returnType, 
                                      TomList argList, Instruction instr) throws IOException;
  
  protected abstract void genDeclList(String name) throws IOException;

  protected abstract void genDeclArray(String name) throws IOException;

  //------------------------------------------------------------
 
  protected abstract void buildRef(int deep, TomTerm term) throws IOException;
  protected abstract void buildInstructionSequence(int deep, InstructionList instructionList) throws IOException;
  protected abstract void buildComment(int deep, String text) throws IOException;
  protected abstract void buildFunctionCall(int deep, String name, TomList argList)  throws IOException;
  protected abstract void buildFunctionBegin(int deep, String tomName, TomList varList) throws IOException; 
  protected abstract void buildFunctionEnd(int deep) throws IOException;
  protected abstract void buildFunctionDef(int deep, String tomName, TomList argList, TomType codomain, TomType throwsType, Instruction instruction) throws IOException; 
  protected abstract void buildClass(int deep, String tomName, TomList varList, TomType extendsType,Instruction instruction) throws IOException; 
  protected abstract void buildExpNegation(int deep, Expression exp) throws IOException;
  protected abstract void buildExpGetHead(int deep, TomName opName, TomType domain, TomType codomain, TomTerm var) throws IOException;
  protected abstract void buildExpGetElement(int deep, TomName opNameAST,TomType domain, TomType codomain, TomTerm varName, TomTerm varIndex) throws IOException;

  protected abstract void buildReturn(int deep, TomTerm exp) throws IOException ;
  protected abstract void buildExpTrue(int deep) throws IOException;
  protected abstract void buildExpFalse(int deep) throws IOException;
  protected abstract void buildAssignVar(int deep, TomTerm var, OptionList list, Expression exp) throws IOException ;
  protected abstract void buildLetAssign(int deep, TomTerm var, OptionList list, Expression exp, Instruction body) throws IOException ;
  protected abstract void buildExpCast(int deep, TomType type, Expression exp) throws IOException;
  protected abstract void buildLet(int deep, TomTerm var, OptionList list, TomType tlType, Expression exp, Instruction body) throws IOException ;
  protected abstract void buildLetRef(int deep, TomTerm var, OptionList list, TomType tlType, Expression exp, Instruction body) throws IOException ;
  protected abstract void buildNamedBlock(int deep, String blockName, InstructionList instList) throws IOException ;
  protected abstract void buildUnamedBlock(int deep, InstructionList instList) throws IOException ;
  protected abstract void buildIf(int deep, Expression exp, Instruction succes) throws IOException ;
  protected abstract void buildIfWithFailure(int deep, Expression exp, Instruction succes, Instruction failure) throws IOException ;
  protected abstract void buildDoWhile(int deep, Instruction succes, Expression exp) throws IOException;
  protected abstract void buildWhileDo(int deep, Expression exp, Instruction succes) throws IOException;

  /*
   * Implementation of functions whose definition is
   * independant of the target language
   */
 
  protected void buildTerm(int deep, String name, TomList argList) throws IOException {
    buildFunctionCall(deep, "tom_make_"+name, argList);
  }

  protected void buildCheckStamp(int deep, TomType type, TomTerm variable) throws IOException {
    if(((Boolean)optionManager.getOptionValue("stamp")).booleanValue()) {
      output.write("tom_check_stamp_" + getTomType(type) + "(");
      generate(deep,variable);
      output.write(");");
    }
  }

  protected void buildSymbolDecl(int deep, String tomName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable().getSymbolFromName(tomName);
    OptionList optionList = tomSymbol.getOption();
    PairNameDeclList pairNameDeclList = tomSymbol.getPairNameDeclList();
    // inspect the optionList
    generateOptionList(deep, optionList);
    // inspect the slotlist
    generatePairNameDeclList(deep, pairNameDeclList);
  }

  protected void buildExpAnd(int deep, Expression exp1, Expression exp2) throws IOException {
    generateExpression(deep,exp1);
    output.write(" && ");
    generateExpression(deep,exp2);
  }
  protected void buildExpOr(int deep, Expression exp1, Expression exp2) throws IOException {
    generateExpression(deep,exp1);
    output.write(" || ");
    generateExpression(deep,exp2);
  }

  protected void buildExpGreaterThan(int deep, Expression exp1, Expression exp2) throws IOException {
    generateExpression(deep,exp1);
    output.write(" > ");
    generateExpression(deep,exp2);
  }

  protected void buildExpIsEmptyList(int deep, TomName opNameAST, TomType type, TomTerm expList) throws IOException {
     { tom.engine.adt.tomsignature.types.TomName tom_match1_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match1_1) ||  false ) {
 output.write("tom_is_empty_" + getTomType(type) + "(");  } if(tom_is_fun_sym_Name(tom_match1_1) ||  false ) { { String  tom_match1_1_string=tom_get_slot_Name_string(tom_match1_1);
 output.write("tom_is_empty_" + tom_match1_1_string+ "_" + getTomType(type) + "("); } }}

    generate(deep,expList);
    output.write(")");
  }

  protected void buildExpIsEmptyArray(int deep, TomName opNameAST, TomType type, TomTerm expIndex, TomTerm expArray) throws IOException {
    generate(deep,expIndex);
    output.write(" >= ");
     { tom.engine.adt.tomsignature.types.TomName tom_match2_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match2_1) ||  false ) {
 output.write("tom_get_size_" + getTomType(type) + "(");  } if(tom_is_fun_sym_Name(tom_match2_1) ||  false ) { { String  tom_match2_1_string=tom_get_slot_Name_string(tom_match2_1);
 output.write("tom_get_size_" + tom_match2_1_string+ "_" + getTomType(type) + "("); } }}

    generate(deep,expArray);
    output.write(")");
  }

  protected void buildExpEqualTerm(int deep, TomType type, TomTerm exp1,TomTerm exp2) throws IOException {
    output.write("tom_terms_equal_" + getTomType(type) + "(");
    generate(deep,exp1);
    output.write(", ");
    generate(deep,exp2);
    output.write(")");
  }

  protected void buildExpIsFsym(int deep, String opname, TomTerm exp) throws IOException {
    String s = (String)isFsymMap.get(opname);
    if(s == null) {
      s = "tom_is_fun_sym_" + opname + "(";
      isFsymMap.put(opname,s);
    } 
    output.write(s);
    generate(deep,exp);
    output.write(")");
  }

  protected void buildExpGetSlot(int deep, String opname, String slotName, TomTerm var) throws IOException {
    //output.write("tom_get_slot_" + opname + "_" + slotName + "(");
    //generate(deep,var);
    //output.write(")");
    output.write("tom_get_slot_");
    output.write(opname);
    output.writeUnderscore();
    output.write(slotName);
    output.writeOpenBrace();
    generate(deep,var);
    output.writeCloseBrace();
  }

  protected void buildExpGetTail(int deep, TomName opNameAST, TomType type, TomTerm var) throws IOException {
     { tom.engine.adt.tomsignature.types.TomName tom_match3_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match3_1) ||  false ) {
 output.write("tom_get_tail_" + getTomType(type) + "(");  } if(tom_is_fun_sym_Name(tom_match3_1) ||  false ) { { String  tom_match3_1_string=tom_get_slot_Name_string(tom_match3_1);
 output.write("tom_get_tail_" + tom_match3_1_string+ "_" + getTomType(type) + "("); } }}

    generate(deep,var); 
    output.write(")");
  }

  protected void buildExpGetSize(int deep, TomName opNameAST, TomType type, TomTerm var) throws IOException {
     { tom.engine.adt.tomsignature.types.TomName tom_match4_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match4_1) ||  false ) {
 output.write("tom_get_size_" + getTomType(type) + "(");  } if(tom_is_fun_sym_Name(tom_match4_1) ||  false ) { { String  tom_match4_1_string=tom_get_slot_Name_string(tom_match4_1);
 output.write("tom_get_size_" + tom_match4_1_string+ "_" + getTomType(type) + "("); } }}

    generate(deep,var);
    output.write(")");
  }

  protected void buildExpGetSliceList(int deep, String name, TomTerm varBegin, TomTerm varEnd) throws IOException {
    output.write("tom_get_slice_" + name + "(");
    generate(deep,varBegin);
    output.write(",");
    generate(deep,varEnd);
    output.write(")");
  }

  protected void buildExpGetSliceArray(int deep, String name, TomTerm varArray, TomTerm varBegin, TomTerm expEnd) throws IOException {
    output.write("tom_get_slice_" + name + "(");
    generate(deep,varArray);
    output.write(",");
    generate(deep,varBegin);
    output.write(",");
    generate(deep,expEnd);
    output.write(")");
  }

  protected void buildAddOne(int deep, TomTerm var) throws IOException {
    generate(deep,var);
    output.write(" + 1");
  }

  protected void buildGetFunctionSymbolDecl(int deep, String type, String name,
                                            TomType tlType, TargetLanguage tlCode) throws IOException {
    String args[];
    if(lazyMode) {
      TomType argType = getUniversalType();
      if(getSymbolTable().isBuiltinType(type)) {
        argType = getSymbolTable().getBuiltinType(type);
      }
      args = new String[] { getTLType(argType), name };
    } else {
      args = new String[] { getTLCode(tlType), name };
    }
    
    TomType returnType = getUniversalType();
    if(getSymbolTable().isBuiltinType(type)) {
      returnType = getSymbolTable().getBuiltinType(type);
    }
    genDecl(getTLType(returnType),"tom_get_fun_sym", type,args,tlCode);
  }

  protected void buildCheckStampDecl(int deep, String type, String name,
                                     TomType tlType, TargetLanguage tlCode) throws IOException {
    TomType returnType = getSymbolTable().getVoidType();
    String argType;
    if(!lazyMode) {
      argType = getTLCode(tlType);
    } else {
      argType = getTLType(getUniversalType());
    }
    
    genDecl(getTLType(returnType),"tom_check_stamp", type, new String[] { argType, name }, tlCode);
  }

  protected void buildSetStampDecl(int deep, String type, String name,
                                   TomType tlType, TargetLanguage tlCode) throws IOException {
    String argType;
    if(!lazyMode) {
      argType = getTLCode(tlType);
    } else {
      argType = getTLType(getUniversalType());
    }
    String returnType = argType; /* TODO: use stamp type */
    
    genDecl(returnType,
            "tom_set_stamp", type,
            new String[] { argType, name },
            tlCode);
  }

  protected void buildGetImplementationDecl(int deep, String type, String name,
                                            TomType tlType, TargetLanguage tlCode) throws IOException {
    String argType;
    if(!lazyMode) {
      argType = getTLCode(tlType);
    } else {
      argType = getTLType(getUniversalType());
    }
    String returnType = argType; /* TODO: use stamp type */
    
    genDecl(returnType,
            "tom_get_implementation", type,
            new String[] { argType, name },
            tlCode);
  }

  protected void buildIsFsymDecl(int deep, String tomName, String name1,
                                 TomType tlType, TargetLanguage tlCode) throws IOException {
    TomSymbol tomSymbol = getSymbolTable().getSymbolFromName(tomName);
    String opname = tomSymbol.getAstName().getString();
    
    TomType returnType = getSymbolTable().getBooleanType();
    String argType;
    if(!lazyMode) {
      argType = getTLCode(tlType);
    } else {
      argType = getTLType(getUniversalType());
    }
    
    genDecl(getTLType(returnType),
            "tom_is_fun_sym", opname,
            new String[] { argType, name1 },
            tlCode);
  }

  protected void buildGetSlotDecl(int deep, String tomName, String name1,
                                  TomType tlType, TargetLanguage tlCode, TomName slotName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable().getSymbolFromName(tomName);
    String opname = tomSymbol.getAstName().getString();
    TomTypeList typesList = tomSymbol.getTypesToType().getDomain();
    
    int slotIndex = getSlotIndex(tomSymbol,slotName);
    TomTypeList l = typesList;
    for(int index = 0; !l.isEmpty() && index<slotIndex ; index++) {
      l = l.getTail();
    }
    TomType returnType = l.getHead();
    
    String argType;
    if(!lazyMode) {
      argType = getTLCode(tlType);
    } else {
      argType = getTLType(getUniversalType());
    }
    genDecl(getTLType(returnType),
            "tom_get_slot", opname  + "_" + slotName.getString(),
            new String[] { argType, name1 },
            tlCode);
  }

  protected void  buildCompareFunctionSymbolDecl(int deep, String name1,
                                                 String name2, String type1, String type2, TargetLanguage tlCode) throws IOException {
    TomType argType1 = getUniversalType();
    if(getSymbolTable().isBuiltinType(type1)) {
      argType1 = getSymbolTable().getBuiltinType(type1);
    }
    TomType argType2 = getUniversalType();
    if(getSymbolTable().isBuiltinType(type2)) {
      argType2 = getSymbolTable().getBuiltinType(type2);
    } 
    
    genDecl(getTLType(getSymbolTable().getBooleanType()), "tom_cmp_fun_sym", type1,
            new String[] {
              getTLType(argType1), name1,
              getTLType(argType2), name2
            },
            tlCode);
  }

  protected void buildTermsEqualDecl(int deep, String name1, String name2,
                                     String type1, String type2, TargetLanguage tlCode) throws IOException {
    TomType argType1 = getUniversalType();
    if(getSymbolTable().isBuiltinType(type1)) {
      argType1 = getSymbolTable().getBuiltinType(type1);
    } 
    TomType argType2 = getUniversalType();
    if(getSymbolTable().isBuiltinType(type2)) {
      argType2 = getSymbolTable().getBuiltinType(type2);
    } 
    
    genDecl(getTLType(getSymbolTable().getBooleanType()), "tom_terms_equal", type1,
            new String[] {
              getTLType(argType1), name1,
              getTLType(argType2), name2
            },
            tlCode);
  }

  protected void buildGetHeadDecl(int deep, TomName opNameAST, String varName, String suffix, TomType domain, TomType codomain, TargetLanguage tlCode) 
    throws IOException {
    String returnType = null;
    String argType = null;
    String functionName = "tom_get_head";

     { tom.engine.adt.tomsignature.types.TomName tom_match5_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_Name(tom_match5_1) ||  false ) { { String  tom_match5_1_string=tom_get_slot_Name_string(tom_match5_1);
 functionName = functionName + "_" + tom_match5_1_string; } }}


    if(lazyMode) {
      returnType = getTLType(getUniversalType());
      argType = getTLType(getUniversalType());
    } else {
       { tom.engine.adt.tomsignature.types.TomName tom_match6_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match6_1) ||  false ) {

          returnType = getTLCode(codomain);
          argType = getTLCode(domain);
         } if(tom_is_fun_sym_Name(tom_match6_1) ||  false ) { { String  tom_match6_1_string=tom_get_slot_Name_string(tom_match6_1);


          TomSymbol tomSymbol = getSymbolFromName(tom_match6_1_string);
          argType = getTLType(getSymbolCodomain(tomSymbol));
          returnType = getTLType(getSymbolDomain(tomSymbol).getHead());
        } }}

    }
    genDecl(returnType, functionName, suffix,
            new String[] { argType, varName },
            tlCode);
  }

  protected void buildGetTailDecl(int deep, TomName opNameAST, String varName, String type, TomType tlType, TargetLanguage tlCode) 
    throws IOException {
    String returnType = null;
    String argType = null;
    String functionName = "tom_get_tail";

     { tom.engine.adt.tomsignature.types.TomName tom_match7_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_Name(tom_match7_1) ||  false ) { { String  tom_match7_1_string=tom_get_slot_Name_string(tom_match7_1);
 functionName = functionName + "_" + tom_match7_1_string; } }}


    if(lazyMode) {
      returnType = getTLType(getUniversalType());
      argType = getTLType(getUniversalType());
    } else {
       { tom.engine.adt.tomsignature.types.TomName tom_match8_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match8_1) ||  false ) {

          returnType = getTLCode(tlType);
          argType = returnType;
         } if(tom_is_fun_sym_Name(tom_match8_1) ||  false ) { { String  tom_match8_1_string=tom_get_slot_Name_string(tom_match8_1);


          TomSymbol tomSymbol = getSymbolFromName(tom_match8_1_string);
          returnType = getTLType(getSymbolCodomain(tomSymbol));
          argType = returnType;
        } }}

    }

    genDecl(returnType, functionName, type,
            new String[] { argType, varName },
            tlCode);
  }

  protected void buildIsEmptyDecl(int deep, TomName opNameAST, String varName, String type,
                                  TomType tlType, TargetLanguage tlCode) throws IOException {
    String argType = null;
    String functionName = "tom_is_empty";

     { tom.engine.adt.tomsignature.types.TomName tom_match9_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_Name(tom_match9_1) ||  false ) { { String  tom_match9_1_string=tom_get_slot_Name_string(tom_match9_1);
 functionName = functionName + "_" + tom_match9_1_string; } }}

    if(lazyMode) {
      argType = getTLType(getUniversalType());
    } else {
       { tom.engine.adt.tomsignature.types.TomName tom_match10_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match10_1) ||  false ) {

          argType = getTLCode(tlType);
         } if(tom_is_fun_sym_Name(tom_match10_1) ||  false ) { { String  tom_match10_1_string=tom_get_slot_Name_string(tom_match10_1);


          TomSymbol tomSymbol = getSymbolFromName(tom_match10_1_string);
          argType = getTLType(getSymbolCodomain(tomSymbol));
        } }}

    }

    genDecl(getTLType(getSymbolTable().getBooleanType()),
            functionName, type,
            new String[] { argType, varName },
            tlCode);
  }

  protected void buildGetElementDecl(int deep, TomName opNameAST, String name1, String name2,
                                     String type1, TomType domain, TargetLanguage tlCode) throws IOException {
    String returnType = null;
    String argType = null;
    String functionName = "tom_get_element";

     { tom.engine.adt.tomsignature.types.TomName tom_match11_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_Name(tom_match11_1) ||  false ) { { String  tom_match11_1_string=tom_get_slot_Name_string(tom_match11_1);
 functionName = functionName + "_" + tom_match11_1_string; } }}


    if(lazyMode) {
      returnType = getTLType(getUniversalType());
      argType = getTLType(getUniversalType());
    } else {
       { tom.engine.adt.tomsignature.types.TomName tom_match12_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match12_1) ||  false ) {

          returnType = getTLType(getUniversalType());
          argType = getTLCode(domain);
         } if(tom_is_fun_sym_Name(tom_match12_1) ||  false ) { { String  tom_match12_1_string=tom_get_slot_Name_string(tom_match12_1);


          TomSymbol tomSymbol = getSymbolFromName(tom_match12_1_string);
          argType = getTLType(getSymbolCodomain(tomSymbol));
          returnType = getTLType(getSymbolDomain(tomSymbol).getHead());
        } }}

    }
    
    genDecl(returnType,
            functionName, type1,
            new String[] {
              argType, name1,
              getTLType(getSymbolTable().getIntType()), name2
            },
            tlCode);
  }

  protected void buildGetSizeDecl(int deep, TomName opNameAST, String name1, String type,
                                  TomType domain, TargetLanguage tlCode) throws IOException {
    String argType = null;
    String functionName = "tom_get_size";

     { tom.engine.adt.tomsignature.types.TomName tom_match13_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_Name(tom_match13_1) ||  false ) { { String  tom_match13_1_string=tom_get_slot_Name_string(tom_match13_1);
 functionName = functionName + "_" + tom_match13_1_string; } }}


    if(lazyMode) {
      argType = getTLType(getUniversalType());
    } else {
       { tom.engine.adt.tomsignature.types.TomName tom_match14_1=(( tom.engine.adt.tomsignature.types.TomName)opNameAST); if(tom_is_fun_sym_EmptyName(tom_match14_1) ||  false ) {

          argType = getTLCode(domain);
         } if(tom_is_fun_sym_Name(tom_match14_1) ||  false ) { { String  tom_match14_1_string=tom_get_slot_Name_string(tom_match14_1);


          TomSymbol tomSymbol = getSymbolFromName(tom_match14_1_string);
          argType = getTLType(getSymbolCodomain(tomSymbol));
        } }}

    }
    
    genDecl(getTLType(getSymbolTable().getIntType()),
            functionName, type,
            new String[] { argType, name1 },
            tlCode);
  }

 
} // class TomGenericGenerator
