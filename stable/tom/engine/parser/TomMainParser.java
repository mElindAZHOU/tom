/* Generated by TOM (version 3.0alpha): Do not edit this file *//*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (C) 2000-2004 INRIA
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

package jtom.parser;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import antlr.*;

import aterm.*;
import aterm.pure.*;

import jtom.*;
import jtom.tools.*;
import jtom.adt.tomsignature.*;
import jtom.adt.tomsignature.types.*;
import tom.platform.adt.platformoption.types.*;
import jtom.exception.*;

public class TomMainParser extends TomGenericPlugin {

    /* Generated by TOM (version 3.0alpha): Do not edit this file *//* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     /*  * old definition of String %typeterm String {   implement           { String }   get_fun_sym(t)      { t }   cmp_fun_sym(s1,s2)  { s1.equals(s2) }   get_subterm(t, n)   { null }   equals(t1,t2)       { t1.equals(t2) } } */ /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */   /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     /* Generated by TOM (version 3.0alpha): Do not edit this file */ 

    
    public static final String PARSED_SUFFIX = ".tfix.parsed"; // was previously in TomTaskInput
    public static final String PARSED_TABLE_SUFFIX = ".tfix.parsed.table"; // was previously in TomTaskInput
    public static final String DEBUG_TABLE_SUFFIX = ".tfix.debug.table"; // was previously in TomTaskInput

    private String currentFile;
    
    private NewTargetParser parser = null;
    
    public TomMainParser(){
      super("TomMainParser");
    }

    public PlatformOptionList declaredOptions() {
    return tom_cons_list_concPlatformOption(tom_make_OptionBoolean("parse","","",tom_make_True()) // activation flag
			  ,tom_empty_list_concPlatformOption())
;
    }

    private void p(String s){
	System.out.println(s);
    }

    
    protected static NewTargetParser newParser(String fileName) throws FileNotFoundException,IOException{
	//	try{
	HashSet includedFiles = new HashSet();
	HashSet alreadyParsedFiles = new HashSet();
	
	return newParser(fileName,includedFiles,alreadyParsedFiles);
    }

    //create new parsers
    protected static NewTargetParser newParser(String fileName,HashSet includedFiles,HashSet alreadyParsedFiles) 
	throws FileNotFoundException,IOException {
	
	// The input Stream
	DataInputStream input = new DataInputStream(new FileInputStream(new File(fileName)));
	    
	// a selector to choose the lexer to use
	TokenStreamSelector selector = new TokenStreamSelector();
	
	// create a lexer for target mode
	NewTargetLexer targetlexer = new NewTargetLexer(input);
	// create a lexer for tom mode
	NewTomLexer tomlexer = new NewTomLexer(targetlexer.getInputState());
	// create a lexer for backquote mode
	NewBQLexer bqlexer = new NewBQLexer(targetlexer.getInputState());
	
	// notify selector about various lexers
	selector.addInputStream(targetlexer,"targetlexer");
	selector.addInputStream(tomlexer, "tomlexer");
	selector.addInputStream(bqlexer, "bqlexer");
	selector.select("targetlexer");
	
	// create the parser for target mode
	// also create tom parser and backquote parser
	return new NewTargetParser(selector,fileName,includedFiles,alreadyParsedFiles);
    }
    
  public void run() {
    try {
	
      p("-- NEW PARSER --");

      int errorsAtStart = getPluginPlatform().getStatusHandler().nbOfErrors();
      int warningsAtStart = getPluginPlatform().getStatusHandler().nbOfWarnings();

      long startChrono = System.currentTimeMillis();
	    
      boolean verbose      = ((Boolean)getPluginPlatform().getOptionValue("verbose")).booleanValue();
      boolean intermediate = ((Boolean)getPluginPlatform().getOptionValue("intermediate")).booleanValue();
      boolean java         = ((Boolean)getPluginPlatform().getOptionValue("jCode")).booleanValue();
      boolean debug        = ((Boolean)getPluginPlatform().getOptionValue("debug")).booleanValue();
	    
      if(java){
	  TomJavaParser javaParser = TomJavaParser.createParser(currentFile);
	  String packageName = javaParser.JavaPackageDeclaration();
	  // Update taskInput
	  environment().setPackagePath(packageName);
	  environment().updateOutputFile();
      }
      else{
	  environment().setPackagePath("");
      }
      //createParser();
      parser = newParser(currentFile);
      
      super.setTerm( parser.input() );
      
      if(verbose) 
	  System.out.println("TOM parsing phase (" + (System.currentTimeMillis()-startChrono)+ " ms)");
      
      if(environment().isEclipseMode()){
	  String outputFileName = environment().getInputFile().getParent()+ File.separator + "."
	      + environment().getRawFileName()+ PARSED_TABLE_SUFFIX;
	  
	  Tools.generateOutput(outputFileName, symbolTable().toTerm());
      }
      
      if(intermediate){
	  System.out.println("intermediate");
	  Tools.generateOutput(environment().getOutputFileNameWithoutSuffix() 
			       + PARSED_SUFFIX, getTerm());
	  Tools.generateOutput(environment().getOutputFileNameWithoutSuffix() 
			       + PARSED_TABLE_SUFFIX, symbolTable().toTerm());
      }
      
      if(debug)
	  Tools.generateOutput(environment().getOutputFileNameWithoutSuffix() 
			       + DEBUG_TABLE_SUFFIX, parser.getStructTable());
      
      printAlertMessage(errorsAtStart, warningsAtStart);
    }
    catch (TokenStreamException e){
	e.printStackTrace();
	getLogger().log( Level.SEVERE,
			 "TokenStreamException",
			 new Object[]{currentFile, new Integer( getLineFromTomParser() ), e.getMessage()} );
    }
    catch (RecognitionException e){
	e.printStackTrace();
	getLogger().log( Level.SEVERE,
			 "RecognitionException",
			 new Object[]{currentFile, new Integer( getLineFromTomParser() ), e.getMessage()} );
    } catch (TomIncludeException e) {
	getLogger().log( Level.SEVERE,
			 "SimpleMessage",
			 new Object[]{currentFile, new Integer( getLineFromTomParser() ), e.getMessage()} );
    } catch (TomException e) {
	getLogger().log( Level.SEVERE,
			 "SimpleMessage",
			 new Object[]{currentFile, new Integer( getLineFromTomParser() ), e.getMessage()} );
    } catch (FileNotFoundException e) {
	getLogger().log( Level.SEVERE,
			 "FileNotFound",
			 new Object[]{currentFile, new Integer( getLineFromTomParser() ), currentFile} ); 
    } catch (Exception e) {
	e.printStackTrace();
	getLogger().log( Level.SEVERE,
			 "UnhandledException", 
			 new Object[]{currentFile, e.getMessage()} );
    }
  }

    private int getLineFromTomParser() {
	if(parser == null) {
	    return TomMessage.DEFAULT_ERROR_LINE_NUMBER;
	} 
	return parser.getLine();
    }

    public void setTerm(ATerm term){
	this.currentFile = ((AFun) term).getName();
    }

    
}
