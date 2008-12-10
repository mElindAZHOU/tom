/* Generated by TOM (version 2.6): Do not edit this file *//*
 * Gom
 *
 * Copyright (c) 2006-2008, INRIA
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
package tom.gom.backend;

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;
import java.io.*;

public abstract class TemplateClass {
  protected GomClass gomClass;
  protected ClassName className;
  protected GomEnvironment gomEnvironment;

  public TemplateClass(GomClass gomClass, GomEnvironment gomEnvironment) {
    this.gomClass = gomClass;
    this.className = gomClass.getClassName();
    this.gomEnvironment = gomEnvironment;
  }

  public abstract GomEnvironment getGomEnvironment();

  /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */  /* Generated by TOM (version 2.6): Do not edit this file */  

  public abstract void generate(Writer writer) throws java.io.IOException;

  public String className() {
    return className(this.className);
  }

  public String className(ClassName clsName) {
    {{if ( (clsName instanceof tom.gom.adt.objects.types.ClassName) ) {if ( ((( tom.gom.adt.objects.types.ClassName )clsName) instanceof tom.gom.adt.objects.types.classname.ClassName) ) {

        return  (( tom.gom.adt.objects.types.ClassName )clsName).getName() ;
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:className got a strange ClassName "+clsName);
  }

  public String fullClassName() {
    return fullClassName(this.className);
  }

  public static String fullClassName(ClassName clsName) {
    {{if ( (clsName instanceof tom.gom.adt.objects.types.ClassName) ) {if ( ((( tom.gom.adt.objects.types.ClassName )clsName) instanceof tom.gom.adt.objects.types.classname.ClassName) ) { String  tomMatch209NameNumber_freshVar_1= (( tom.gom.adt.objects.types.ClassName )clsName).getPkg() ; String  tomMatch209NameNumber_freshVar_2= (( tom.gom.adt.objects.types.ClassName )clsName).getName() ;

        if(tomMatch209NameNumber_freshVar_1.length()==0) {
          return tomMatch209NameNumber_freshVar_2;
        } else {
          return tomMatch209NameNumber_freshVar_1+"."+tomMatch209NameNumber_freshVar_2;
        }
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:fullClassName got a strange ClassName "+clsName);
  }

  public String getPackage() {
    return getPackage(this.className);
  }

  public String getPackage(ClassName clsName) {
    {{if ( (clsName instanceof tom.gom.adt.objects.types.ClassName) ) {if ( ((( tom.gom.adt.objects.types.ClassName )clsName) instanceof tom.gom.adt.objects.types.classname.ClassName) ) {

        return  (( tom.gom.adt.objects.types.ClassName )clsName).getPkg() ;
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:getPackage got a strange ClassName "+clsName);
  }

  public String hasMethod(SlotField slot) {
    {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {

        return "has"+ (( tom.gom.adt.objects.types.SlotField )slot).getName() ;
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:hasMethod got a strange SlotField "+slot);
  }

  public String getMethod(SlotField slot) {
    {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {

        return "get"+ (( tom.gom.adt.objects.types.SlotField )slot).getName() ;
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:getMethod got a strange SlotField "+slot);
  }

  public String setMethod(SlotField slot) {
    {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {

        return "set"+ (( tom.gom.adt.objects.types.SlotField )slot).getName() ;
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:setMethod got a strange SlotField "+slot);
  }

  public String index(SlotField slot) {
    {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {

        return "index_"+ (( tom.gom.adt.objects.types.SlotField )slot).getName() ;
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:index got a strange SlotField "+slot);
  }

  public String slotDomain(SlotField slot) {
    {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {

        return fullClassName( (( tom.gom.adt.objects.types.SlotField )slot).getDomain() );
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:slotDomain got a strange SlotField "+slot);
  }

  private String fieldName(String fieldName) {
    return "_"+fieldName;
  }

  public String classFieldName(ClassName clsName) {
    {{if ( (clsName instanceof tom.gom.adt.objects.types.ClassName) ) {if ( ((( tom.gom.adt.objects.types.ClassName )clsName) instanceof tom.gom.adt.objects.types.classname.ClassName) ) {

        return  (( tom.gom.adt.objects.types.ClassName )clsName).getName() .toLowerCase();
      }}}}

    throw new GomRuntimeException(
        "TemplateClass:classFieldName got a strange ClassName "+clsName);
  }

  public void toStringSlotField(StringBuilder res, SlotField slot,
                                String element, String buffer) {
    {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) { tom.gom.adt.objects.types.ClassName  tomMatch217NameNumber_freshVar_1= (( tom.gom.adt.objects.types.SlotField )slot).getDomain() ;

        if(!getGomEnvironment().isBuiltinClass(tomMatch217NameNumber_freshVar_1)) {
          res.append(""/* Generated by TOM (version 2.6): Do not edit this file */+element+".toStringBuilder("/* Generated by TOM (version 2.6): Do not edit this file */+buffer+");\n"
);
        } else {
          if (tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "int") )
              || tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "double") )
              || tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "float") )) {
            res.append(""/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append("/* Generated by TOM (version 2.6): Do not edit this file */+element+");\n"
);
          } else if (tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "long") )) {
            res.append(""/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append("/* Generated by TOM (version 2.6): Do not edit this file */+element+");\n            "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append(\"l\");\n"

);
          } else if (tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "char") )) {
            res.append(""/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append(((int)"/* Generated by TOM (version 2.6): Do not edit this file */+element+"-(int)'0'));\n"
);
          } else if (tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "boolean") )) {
            res.append(""/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append("/* Generated by TOM (version 2.6): Do not edit this file */+element+"?1:0);\n"
);
          } else if (tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "String") )) {
            String atchar = "@";
            res.append(""/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\"');\n            for (int i = 0; i < "/* Generated by TOM (version 2.6): Do not edit this file */+element+".length(); i++) {\n              char c = "/* Generated by TOM (version 2.6): Do not edit this file */+element+".charAt(i);\n              switch (c) {\n                case '\\n':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('n');\n                  break;\n                case '\\t':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('t');\n                  break;\n                case '\\b':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('b');\n                  break;\n                case '\\r':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('r');\n                  break;\n                case '\\f':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('f');\n                  break;\n                case '\\\\':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  break;\n                case '\\'':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\'');\n                  break;\n                case '\\\"':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\"');\n                  break;\n                case '!':\n                case '"/* Generated by TOM (version 2.6): Do not edit this file */+atchar+"':\n                case '#':\n                case '$':\n                case '%':\n                case '^':\n                case '&':\n                case '*':\n                case '(':\n                case ')':\n                case '-':\n                case '_':\n                case '+':\n                case '=':\n                case '|':\n                case '~':\n                case '{':\n                case '}':\n                case '[':\n                case ']':\n                case ';':\n                case ':':\n                case '<':\n                case '>':\n                case ',':\n                case '.':\n                case '?':\n                case ' ':\n                case '/':\n                  "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append(c);\n                  break;\n\n                default:\n                  if (java.lang.Character.isLetterOrDigit(c)) {\n                    "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append(c);\n                  } else {\n                    "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\\\\');\n                    "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append((char) ('0' + c / 64));\n                    c = (char) (c % 64);\n                    "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append((char) ('0' + c / 8));\n                    c = (char) (c % 8);\n                    "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append((char) ('0' + c));\n                  }\n              }\n            }\n            "/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append('\"');\n"

















































































);
          } else if (tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATerm") ) ||tomMatch217NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATermList") )) {
            res.append(""/* Generated by TOM (version 2.6): Do not edit this file */+buffer+".append("/* Generated by TOM (version 2.6): Do not edit this file */+element+".toString());\n"
);
          } else {
            throw new GomRuntimeException("Builtin " + tomMatch217NameNumber_freshVar_1+ " not supported");
          }
        }
      }}}}

  }

  public void toATermSlotField(StringBuilder res, SlotField slot) {
    {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) { tom.gom.adt.objects.types.ClassName  tomMatch218NameNumber_freshVar_1= (( tom.gom.adt.objects.types.SlotField )slot).getDomain() ;

        if(!getGomEnvironment().isBuiltinClass(tomMatch218NameNumber_freshVar_1)) {
          res.append(getMethod(slot));
          res.append("().toATerm()");
        } else {
          if (tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "int") )) {
            res.append("(aterm.ATerm) atermFactory.makeInt(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "boolean") )) {
            res.append("(aterm.ATerm) atermFactory.makeInt(");
            res.append(getMethod(slot));
            res.append("()?1:0)");
          } else if (tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "long") )) {
            res.append("(aterm.ATerm) atermFactory.makeLong(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "double") )) {
            res.append("(aterm.ATerm) atermFactory.makeReal(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "float") )) {
            res.append("(aterm.ATerm) atermFactory.makeReal(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "char") )) {
            res.append("(aterm.ATerm) atermFactory.makeInt(((int)");
            res.append(getMethod(slot));
            res.append("()-(int)'0'))");
          } else if (tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "String") )) {
            res.append("(aterm.ATerm) atermFactory.makeAppl(");
            res.append("atermFactory.makeAFun(");
            res.append(getMethod(slot));
            res.append("() ,0 , true))");
          } else if (tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATerm") ) ||tomMatch218NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATermList") )){
            res.append(getMethod(slot));
            res.append("()");
          } else {
            throw new GomRuntimeException("Builtin " + tomMatch218NameNumber_freshVar_1+ " not supported");
          }
        }
      }}}}

  }

  public void fromATermSlotField(StringBuilder buffer, SlotField slot, String appl) {
    {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) { tom.gom.adt.objects.types.ClassName  tomMatch219NameNumber_freshVar_1= (( tom.gom.adt.objects.types.SlotField )slot).getDomain() ;

        if(!getGomEnvironment().isBuiltinClass(tomMatch219NameNumber_freshVar_1)) {
          buffer.append(fullClassName(tomMatch219NameNumber_freshVar_1));
          buffer.append(".fromTerm(");
          buffer.append(appl);
          buffer.append(")");
        } else {
          if (tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "int") )) {
            buffer.append("((aterm.ATermInt)").append(appl).append(").getInt()");
          } else  if (tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "float") )) {
            buffer.append("(float) ((aterm.ATermReal)").append(appl).append(").getReal()");
          } else  if (tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "boolean") )) {
            buffer.append("(((aterm.ATermInt)").append(appl).append(").getInt()==0?false:true)");
          } else  if (tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "long") )) {
            buffer.append("((aterm.ATermLong)").append(appl).append(").getLong()");
          } else  if (tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "double") )) {
            buffer.append("((aterm.ATermReal)").append(appl).append(").getReal()");
          } else  if (tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "char") )) {
            buffer.append("(char) (((aterm.ATermInt)").append(appl).append(").getInt()+(int)'0')");
          } else if (tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "String") )) {
            buffer.append("(String) ((aterm.ATermAppl)").append(appl).append(").getAFun().getName()");
          } else if (tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATerm") ) || tomMatch219NameNumber_freshVar_1.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATermList") ) ){
            buffer.append(appl);
          } else {
            throw new GomRuntimeException("Builtin " + tomMatch219NameNumber_freshVar_1+ " not supported");
          }
        }
      }}}}

  }

  protected String primitiveToReferenceType(String classname) {
    {{if ( true ) {if ( "byte".equals((( String )classname)) ) {
 return "java.lang.Byte"; }}}{if ( true ) {if ( "short".equals((( String )classname)) ) {
 return "java.lang.Short"; }}}{if ( true ) {if ( "int".equals((( String )classname)) ) {
 return "java.lang.Integer"; }}}{if ( true ) {if ( "long".equals((( String )classname)) ) {
 return "java.lang.Long"; }}}{if ( true ) {if ( "float".equals((( String )classname)) ) {
 return "java.lang.Float"; }}}{if ( true ) {if ( "double".equals((( String )classname)) ) {
 return "java.lang.Double"; }}}{if ( true ) {if ( "boolean".equals((( String )classname)) ) {
 return "java.lang.Boolean"; }}}{if ( true ) {if ( "char".equals((( String )classname)) ) {
 return "java.lang.Character"; }}}}

    return classname;
  }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".java";
  }

  protected File fileToGenerate() {
    GomStreamManager stream = getGomEnvironment().getStreamManager();
    File output = new File(stream.getDestDir(),fileName());
    return output;
  }

  public int generateFile() {
    try {
       File output = fileToGenerate();
       // make sure the directory exists
       output.getParentFile().mkdirs();
       Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
       generate(writer);
       writer.flush();
       writer.close();
    } catch(Exception e) {
      e.printStackTrace();
      return 1;
    }
    return 0;
  }

  public String visitMethod(ClassName sortName) {
    return "visit_"+className(sortName);
  }

  public String isOperatorMethod(ClassName opName) {
    return "is"+className(opName);
  }

  public String getCollectionMethod(ClassName opName) {
    return "getCollection"+className(opName);
  }

  protected void slotDecl(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyConcSlotField()) {
      SlotField slot = slotList.getHeadConcSlotField();
      slotList = slotList.getTailConcSlotField();
      if (index>0) { writer.write(", "); }
      {{if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {if ( ((( tom.gom.adt.objects.types.SlotField )slot) instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) { tom.gom.adt.objects.types.ClassName  tomMatch221NameNumber_freshVar_2= (( tom.gom.adt.objects.types.SlotField )slot).getDomain() ;if ( (tomMatch221NameNumber_freshVar_2 instanceof tom.gom.adt.objects.types.classname.ClassName) ) {

          writer.write( (( tom.gom.adt.objects.types.SlotField )slot).getName() );
          writer.write(":");
          writer.write( tomMatch221NameNumber_freshVar_2.getName() );
          index++;
        }}}}}

    }
  }

  protected void slotArgs(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyConcSlotField()) {
      SlotField slot = slotList.getHeadConcSlotField();
      slotList = slotList.getTailConcSlotField();
      if (index>0) { writer.write(", "); }
      /* Warning: do not write the 'index' alone, this is not a valid variable
         name */
      writer.write("t"+index);
      index++;
    }
  }

  protected void slotArgsWithDollar(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyConcSlotField()) {
      SlotField slot = slotList.getHeadConcSlotField();
      slotList = slotList.getTailConcSlotField();
      if (index>0) { writer.write(", "); }
      /* Warning: do not write the 'index' alone, this is not a valid variable
         name */
      writer.write("$t"+index);
      index++;
    }
  }

  public void generateTomMapping(Writer writer)
      throws java.io.IOException {
    return;
  }

}
