
























package tom.engine.backend;



import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;



import tom.engine.TomBase;
import tom.engine.tools.OutputCode;



import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.code.types.*;



import tom.engine.tools.SymbolTable;
import tom.platform.OptionManager;
import tom.engine.exception.TomRuntimeException;
import tom.engine.transformer.TransformerPlugin;



import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.BasicEList;



public class JavaGenerator extends CFamilyGenerator {

  
  protected String stratmodifier = "";

  public JavaGenerator(OutputCode output, OptionManager optionManager,
                       SymbolTable symbolTable) {
    super(output, optionManager, symbolTable);
    
    this.modifier += "private " ;
    if( ((Boolean)optionManager.getOptionValue("protected")).booleanValue() ) {
      this.stratmodifier += "protected " ;
    } else {
      this.stratmodifier += "public " ; 
    }

    if(!((Boolean)optionManager.getOptionValue("noStatic")).booleanValue()) {
      this.modifier += "static " ;
      this.stratmodifier += "static " ;
    }
  }


     private static   tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  tom_append_list_concRefClassTracelinkInstruction( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList l1,  tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  l2) {     if( l1.isEmptyconcRefClassTracelinkInstruction() ) {       return l2;     } else if( l2.isEmptyconcRefClassTracelinkInstruction() ) {       return l1;     } else if(  l1.getTailconcRefClassTracelinkInstruction() .isEmptyconcRefClassTracelinkInstruction() ) {       return  tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.ConsconcRefClassTracelinkInstruction.make( l1.getHeadconcRefClassTracelinkInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.ConsconcRefClassTracelinkInstruction.make( l1.getHeadconcRefClassTracelinkInstruction() ,tom_append_list_concRefClassTracelinkInstruction( l1.getTailconcRefClassTracelinkInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  tom_get_slice_concRefClassTracelinkInstruction( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  begin,  tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  end, tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcRefClassTracelinkInstruction()  ||  (end== tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.EmptyconcRefClassTracelinkInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.ConsconcRefClassTracelinkInstruction.make( begin.getHeadconcRefClassTracelinkInstruction() ,( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList )tom_get_slice_concRefClassTracelinkInstruction( begin.getTailconcRefClassTracelinkInstruction() ,end,tail)) ;   }     private static <O> org.eclipse.emf.common.util.EList<O> appendEAnnotationEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEStringToStringMapEntry(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEntryEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEObject(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEObjectEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEAnnotation(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEGenericTypeEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEGenericType(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructETypeParameter(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendETypeParameterEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEAttribute(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEClassEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEParameter(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEParameterEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEClassifierEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEOperation(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEOperationEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEStructuralFeatureEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEClass(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEDataType(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEEnumLiteral(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEEnumLiteralEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEEnum(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEPackageEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEPackage(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEFactory(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }    private static <O> org.eclipse.emf.common.util.EList<O> appendEAttributeEList(O e,org.eclipse.emf.common.util.EList<O> l) {   l.add(e);   return l; }    public static <O extends org.eclipse.emf.ecore.EObject> O constructEReference(O o, Object[] objs) {   int i=0;   EList<EStructuralFeature> sfes = o.eClass().getEAllStructuralFeatures();   for(EStructuralFeature esf : sfes) {     if(esf.isChangeable()) {       o.eSet(esf, objs[i]);       i++;     }   }   return o; }  



  protected void buildExpBottom(int deep, TomType type, String moduleName) throws IOException {
    String typeName = TomBase.getTomType(type);
    if(getSymbolTable(moduleName).isIntType(typeName)
        || getSymbolTable(moduleName).isCharType(typeName)
        || getSymbolTable(moduleName).isLongType(typeName)
        || getSymbolTable(moduleName).isFloatType(typeName)
        || getSymbolTable(moduleName).isDoubleType(typeName)
        ) {
      output.write(" 0 ");
    } else if(getSymbolTable(moduleName).isBooleanType(typeName)) {
      output.write(" false ");
    } else if(getSymbolTable(moduleName).isStringType(typeName)) {
      output.write(" \"\" ");
    } else {
      output.write(" null ");
    }
  }

  protected void buildExpTrue(int deep) throws IOException {
    output.write(" true ");
  }

  protected void buildExpFalse(int deep) throws IOException {
    output.write(" false ");
  }

  protected void buildNamedBlock(int deep, String blockName, InstructionList instList, String moduleName) throws IOException {
    output.writeln(blockName + ": {");
    generateInstructionList(deep+1,instList,moduleName);
    output.writeln("}");
  }
  
  protected void buildIntrospectorClass(int deep, String tomName, Declaration declaration, String moduleName) throws IOException {
    output.write("public static class " + tomName+ " implements tom.library.sl.Introspector {");
    generateDeclaration(deep,declaration,moduleName);
    output.write(deep,"}");
  }

  protected String genResolveMakeCode(String funName,
                                      BQTermList argList) throws IOException {
    String args = "";
    while(!argList.isEmptyconcBQTerm()) {
      BQTerm arg = argList.getHeadconcBQTerm();
matchBlock: {
              { /* unamed block */{ /* unamed block */if ( (arg instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )arg) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) { tom.engine.adt.tomname.types.TomName  tomMatch107_1= (( tom.engine.adt.code.types.BQTerm )arg).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch107_1) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

                  args = args+ tomMatch107_1.getString() ;
                  break matchBlock;
                }}}}{ /* unamed block */if ( (arg instanceof tom.engine.adt.code.types.BQTerm) ) {


                  System.out.println("genResolveMakeCode: strange term: " + arg);
                  throw new TomRuntimeException("genResolveMakeCode: strange term: " + arg);
                }}}

            }
            argList = argList.getTailconcBQTerm();
            if(!argList.isEmptyconcBQTerm()) {
              args = args + ", ";
            }
    }
    return "new "+funName+"("+args+")";
  }

  protected void genResolveDeclMake(String prefix, String funName, TomType
      returnType, BQTermList argList, String moduleName) throws IOException {
    if(nodeclMode) {
      return;
    }
    Instruction instr =  tom.engine.adt.tominstruction.types.instruction.ExpressionToInstruction.make( tom.engine.adt.tomexpression.types.expression.Code.make(genResolveMakeCode(funName,argList)) ) ;
    if(!inline) {
      StringBuilder s = new StringBuilder();
      s.append(modifier + TomBase.getTLType(returnType) + " " + prefix + funName + "(");
      while(!argList.isEmptyconcBQTerm()) {
        BQTerm arg = argList.getHeadconcBQTerm();
matchBlock: {
              { /* unamed block */{ /* unamed block */if ( (arg instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )arg) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) { tom.engine.adt.tomname.types.TomName  tomMatch108_1= (( tom.engine.adt.code.types.BQTerm )arg).getAstName() ; tom.engine.adt.tomtype.types.TomType  tomMatch108_2= (( tom.engine.adt.code.types.BQTerm )arg).getAstType() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch108_1) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )tomMatch108_2) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) { tom.engine.adt.tomtype.types.TargetLanguageType  tomMatch108_8= tomMatch108_2.getTlType() ;if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType )tomMatch108_8) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.TLType) ) {

                  s.append(TomBase.getTLCode(tomMatch108_8) + " " +  tomMatch108_1.getString() );
                  break matchBlock;
                }}}}}}{ /* unamed block */if ( (arg instanceof tom.engine.adt.code.types.BQTerm) ) {


                  System.out.println("genResolveDeclMake: strange term: " + arg);
                  throw new TomRuntimeException("genResolveDeclMake: strange term: " + arg);
                }}}

            }
            argList = argList.getTailconcBQTerm();
            if(!argList.isEmptyconcBQTerm()) {
              s.append(", ");
            }
      }
      s.append(") { ");
      output.writeln(s);
      output.write("return ");
      generateInstruction(0,instr,moduleName);
      output.writeln(";");
      output.writeln("}");
    }
  }

  
  

  protected String genResolveIsFsymCode(String resolveStringName,
                                        String varName) throws IOException {
    return " ( "+varName+" instanceof "+resolveStringName+" ) ";
  }

  protected String genResolveGetSlotCode(String tomName,
                                         String varName,
                                         String slotName) throws IOException {
    return " (("+tomName+")"+varName+")."+slotName+" ";
  }

  protected String getFullQualifiedNameFromTypeName(String name, String moduleName) {
    String result = null;
    TomType type = getSymbolTable(moduleName).getType(name);
    return getFullQualifiedNameFromType(type);
  }

  protected String getFullQualifiedNameFromType(TomType type) {
    String result = null;
    { /* unamed block */{ /* unamed block */if ( (type instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )type) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) { tom.engine.adt.tomtype.types.TargetLanguageType  tomMatch109_1= (( tom.engine.adt.tomtype.types.TomType )type).getTlType() ;if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType )tomMatch109_1) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.TLType) ) {
 return result =  tomMatch109_1.getString() ; }}}}}

    throw new RuntimeException("Should not be there: full qualified name of "+type+" is null");
  }

  
  protected void buildResolveClass(String wName, String tName, String extendsName, String moduleName) throws
    IOException {
      String resolveStringName = "Resolve"+wName+tName;
      
      
      
      String fqnwName = getFullQualifiedNameFromTypeName(wName, moduleName);
      
      
      output.write("private static class "+resolveStringName+" extends "+extendsName+" {\n  public String "+TransformerPlugin.RESOLVE_ELEMENT_ATTRIBUTE_NAME+";\n  public "+fqnwName+" "+TransformerPlugin.RESOLVE_ELEMENT_ATTRIBUTE_O+";\n\n  public "+resolveStringName+"("+fqnwName+" o, String name) {\n    this."+TransformerPlugin.RESOLVE_ELEMENT_ATTRIBUTE_NAME+" = name;\n    this."+TransformerPlugin.RESOLVE_ELEMENT_ATTRIBUTE_O+" = o;\n  }\n}\n"








);
    }

  
  
  
  protected void buildReferenceClass(int deep, String refname, RefClassTracelinkInstructionList refclassTInstructions, String moduleName)
  throws IOException {
    output.write("\npublic static class "+refname+" implements tom.library.utils.ReferenceClass {\n  "

);
    
    
    
    String getfunctionbody = "";
    { /* unamed block */{ /* unamed block */if ( (refclassTInstructions instanceof tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList) ) {if ( (((( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList )refclassTInstructions) instanceof tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.ConsconcRefClassTracelinkInstruction) || ((( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList )refclassTInstructions) instanceof tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.EmptyconcRefClassTracelinkInstruction)) ) { tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  tomMatch110_end_4=(( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList )refclassTInstructions);do {{ /* unamed block */if (!( tomMatch110_end_4.isEmptyconcRefClassTracelinkInstruction() )) { tom.engine.adt.tominstruction.types.RefClassTracelinkInstruction  tomMatch110_9= tomMatch110_end_4.getHeadconcRefClassTracelinkInstruction() ;if ( ((( tom.engine.adt.tominstruction.types.RefClassTracelinkInstruction )tomMatch110_9) instanceof tom.engine.adt.tominstruction.types.refclasstracelinkinstruction.RefClassTracelinkInstruction) ) { tom.engine.adt.tomname.types.TomName  tomMatch110_7= tomMatch110_9.getType() ; tom.engine.adt.tomname.types.TomName  tomMatch110_8= tomMatch110_9.getName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch110_7) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom___type= tomMatch110_7.getString() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch110_8) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom___name= tomMatch110_8.getString() ;

      output.write("\n  private "+tom___type+" "+tom___name+";\n  public "+tom___type+" get"+tom___name+"() { return "+tom___name+"; }\n  public void set"+tom___name+"("+tom___type+" value) { this."+tom___name+" = value; }\n"



);
      getfunctionbody = getfunctionbody+"if(name.equals(\""+tom___name+"\")) {\n        return get"+tom___name+"();\n    } else ";
      }}}}if ( tomMatch110_end_4.isEmptyconcRefClassTracelinkInstruction() ) {tomMatch110_end_4=(( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList )refclassTInstructions);} else {tomMatch110_end_4= tomMatch110_end_4.getTailconcRefClassTracelinkInstruction() ;}}} while(!( (tomMatch110_end_4==(( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList )refclassTInstructions)) ));}}}}


    output.write("\n  public Object get(String name) {\n    "+getfunctionbody+" {\n      throw new RuntimeException(\"This field does not exist:\" + name);\n    }\n  }\n\n}\n\n"








);

  }

  protected void buildTracelink(int deep, String type, String name, Expression expr, String moduleName) throws IOException {
   output.write(type+" "+name+" = ");
   generateExpression(deep,expr,moduleName);
   output.writeln(";");
  }

  
  protected void buildTracelinkPopulateResolve(int deep, String refClassName, TomNameList tracedLinks, BQTerm current, BQTerm link, String moduleName) throws IOException {
    String refVar = "var_"+refClassName.toLowerCase();
    
    output.write(refClassName+" "+refVar+" = new "+refClassName+"();");
    
    for(TomName name : tracedLinks.getCollectionconcTomName()){
      String namestr = name.getString();
      output.write(refVar+".set"+namestr+"("+namestr+");");
    }
    
    
    
    
    generateBQTerm(deep, link, moduleName);
    output.write(".put(");
    generateBQTerm(deep, current, moduleName);
    output.write(","+refVar+");");
  }

  protected void buildResolve(int deep, BQTerm bqterm, String moduleName) throws IOException {
    generateBQTerm(deep, bqterm, moduleName);
  }

  
  
  protected void buildResolveInverseLinks(int deep, String fileFrom, String fileTo, TomNameList resolveNameList, String moduleName) throws IOException {
    
    

    

    
    
    
    output.write("\n  public static void resolveInverseLinks(EObject resolveNode, EObject newNode, EObject acc) {\n    //Work in progress: optimization of generated resolve phase\n    //trying to hook the EMF adapter\n    ECrossReferenceAdapter adapter = new ECrossReferenceAdapter();\n    //MyECrossReferenceAdapter adapter = new tom.library.utils.MyECrossReferenceAdapter();\n\n    acc.eAdapters().add(adapter);\n    Collection<EStructuralFeature.Setting> references = adapter.getInverseReferences(resolveNode);\n\n        boolean toSet = (false\n        "+genToSetFromResolveNameList(resolveNameList)+"\n        );\n\n    for (EStructuralFeature.Setting setting:references) {\n      EObject current = setting.getEObject();\n"















);
    genTargetElementBlock(fileTo,moduleName);
    output.write("\n    }\n  }\n"


);
  }

  
  
  
  protected void genTargetElementBlock(String fileTo, String moduleName) throws IOException  {
    XMIResourceImpl resource = new XMIResourceImpl();
    Map opts = new HashMap();
    opts.put(XMIResource.OPTION_SCHEMA_LOCATION, java.lang.Boolean.TRUE);
    File input = new File(fileTo);
    try {
      resource.load(new FileInputStream(input),opts);
    } catch (Exception e) {
      e.printStackTrace();
    }

    
    EPackage tmp = (EPackage)resource.getContents().get(0);
    EList<EPackage> epkgs = tmp.getESubpackages();
    if(epkgs.size()<0) {
      epkgs = getEPackages(epkgs);
    }
    epkgs.add(tmp);
    EList<EClassifier> ecls = new BasicEList<EClassifier>();
    
    for(EPackage epk : epkgs) {
      ecls.addAll(epk.getEClassifiers());
    }
    
    for(EClassifier ecl : ecls) {
      if(ecl instanceof EClass ) {
        if(!((EClass)ecl).isAbstract()) {
          
          String eclFQName = getFullQualifiedNameFromTypeName(ecl.getName(),moduleName);
          output.write("if (current instanceof "+eclFQName+") {\n        "+eclFQName+" newCurrent = ("+eclFQName+")current;\n      "

);

          EList<EStructuralFeature> sfs = ((EClass)ecl).getEAllStructuralFeatures();
          int sfCounter = 0; 
          for(EStructuralFeature sf : sfs) {
            if(sf.isChangeable() && !sf.isMany() && !isPrimitiveEMFType(sf) && !isEEnumType(sf)) {
              if(sfCounter>0) {
                output.write(" else ");
              }
              output.write("if(newCurrent.get"+firstToUpperCase(sf.getName())+"().equals(resolveNode) && toSet) {\n          newCurrent.set"+firstToUpperCase(sf.getName())+"(("+getTypeFromEStructuralFeature(sf)+")newNode); \n        } "

);
              sfCounter++;
            }
          }
          if(sfCounter>0) {
            output.write("else { throw new RuntimeException(\"should not be there\"); }");
          }
          output.write("} else ");
        }
      }
    }
    output.write(" { throw new RuntimeException(\"should not be there\"); }");
  }

  
  private final static Set<String> primitiveEMFTypes = new HashSet<String>();
  static {
    primitiveEMFTypes.add("EInt");
    primitiveEMFTypes.add("EBoolean");
    primitiveEMFTypes.add("EString");
    primitiveEMFTypes.add("EDouble");
    primitiveEMFTypes.add("EDate");
    primitiveEMFTypes.add("BigInteger");
    primitiveEMFTypes.add("BigDecimal");
    primitiveEMFTypes.add("Date");
    primitiveEMFTypes.add("java.lang.String");
    primitiveEMFTypes.add("byte");
    primitiveEMFTypes.add("double");
    primitiveEMFTypes.add("int");
    primitiveEMFTypes.add("float");
    primitiveEMFTypes.add("double");
    primitiveEMFTypes.add("boolean");
  }
  protected boolean isPrimitiveEMFType(EStructuralFeature sf) {
    return (primitiveEMFTypes.contains(sf.getName()) || primitiveEMFTypes.contains(sf.getEType().getInstanceClassName()));
  }

  protected boolean isEEnumType(EStructuralFeature sf) {
    return (sf.getEType() instanceof EEnum);
  }

  protected String getTypeFromEStructuralFeature(EStructuralFeature sf) {
    String result = sf.getEType().getInstanceClassName();
    return ((result==null)?sf.getEType().getName():result);
  }

  protected String firstToUpperCase(String s) {
    if(s.length()>1) {
      return (s.substring(0,1).toUpperCase()+s.substring(1,s.length()));
    } else {
      return s.toUpperCase();
    }
  }

  protected EList<org.eclipse.emf.ecore.EPackage> getEPackages(EList<org.eclipse.emf.ecore.EPackage> epkgs) {
    for(EPackage epkg : epkgs ) {
      EList<EPackage> sub = epkg.getESubpackages();
      if(sub.size()>0) {
        epkgs.addAll(getEPackages(sub));
      }
    }
    return epkgs;
  }


  protected String genToSetFromResolveNameList(TomNameList resolveNameList) {
    
    String result = "";
    for(TomName tname : resolveNameList.getCollectionconcTomName()) {
      result = result+" | resolveNode instanceof "+tname.getString();
    }
    return result;
  }

  protected void buildClass(int deep, String tomName, TomType extendsType, BQTerm superTerm, Declaration declaration, String moduleName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(tomName);
    TomTypeList tomTypes = TomBase.getSymbolDomain(tomSymbol);
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<Integer> stratChild = new ArrayList<Integer>(); 

    
    int index = 0;
    while(!tomTypes.isEmptyconcTomType()) {
	    TomType type = tomTypes.getHeadconcTomType();
	    types.add(TomBase.getTLType(type));
      String name = TomBase.getSlotName(tomSymbol, index).getString();
      names.add(name);

      
      { /* unamed block */{ /* unamed block */if ( (type instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )type) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {if ( "Strategy".equals( (( tom.engine.adt.tomtype.types.TomType )type).getTomType() ) ) {

          stratChild.add(Integer.valueOf(index));
        }}}}}


	    tomTypes = tomTypes.getTailconcTomType();
	    index++;
    }
    output.write(deep, stratmodifier + "class " + tomName);
    

matchblock: {
              { /* unamed block */{ /* unamed block */if ( (extendsType instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )extendsType) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) { tom.engine.adt.tomtype.types.TargetLanguageType  tomMatch112_1= (( tom.engine.adt.tomtype.types.TomType )extendsType).getTlType() ;if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType )tomMatch112_1) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.TLType) ) {

				output.write(deep," extends " +  tomMatch112_1.getString() );
        break matchblock;
			}}}}{ /* unamed block */if ( (extendsType instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )extendsType) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType ) (( tom.engine.adt.tomtype.types.TomType )extendsType).getTlType() ) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.EmptyTargetLanguageType) ) {


				output.write(deep," extends " +  (( tom.engine.adt.tomtype.types.TomType )extendsType).getTomType() );
        break matchblock;
			}}}}}

            }
    output.writeln(deep," {");
    int args = names.size();
    
    for(int i = 0 ; i < args ; i++) {
      output.writeln(deep, "private " + types.get(i) + " " + names.get(i) + ";");
    }

    
    output.write(deep, "public " + tomName + "(");
    
    for(int i = 0 ; i < args ; i++) {
	    output.write(deep,types.get(i) + " " + names.get(i));
	    if(i+1<args) {
		    output.write(deep,", ");
	    }
    }

    
    output.writeln(deep,") {");
    output.write(deep+1,"super(");
    generateBQTerm(deep,superTerm,moduleName);
    output.writeln(");");

    
    for(int i = 0 ; i < args ; i++) {
	    String param = names.get(i);
	    output.writeln(deep+1, "this." + param + "=" + param + ";");
    }
    output.writeln(deep,"}");

    
    for(int i = 0 ; i < args ; i++) {
      output.writeln(deep, "public " + types.get(i) + " get" + names.get(i) + "() {");
      output.writeln(deep+1,"return " + names.get(i) + ";");
      output.writeln(deep,"}");
    }

    
    int stratChildCount = stratChild.size();

    output.writeln(deep, "public tom.library.sl.Visitable[] getChildren() {");
    output.writeln(deep, "tom.library.sl.Visitable[] stratChildren = new tom.library.sl.Visitable[getChildCount()];");
    output.writeln(deep, "stratChildren[0] = super.getChildAt(0);");
    for(int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep, "stratChildren[" + (i+1) + "] = get" + names.get(j) + "();");
    }
    output.writeln(deep, "return stratChildren;}");

    output.writeln(deep, "public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {");
    output.writeln(deep,"super.setChildAt(0, children[0]);");
    for(int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep, names.get(j) + " = (" + types.get(j) + ") children[" + (i+1) + "];");
    }
    output.writeln(deep, "return this;");
    output.writeln(deep, "}");

    output.writeln(deep, "public int getChildCount() {");
    output.writeln(deep, "return " + (stratChildCount + 1) + ";");
    output.writeln(deep, "}");

    
    output.writeln(deep, "public tom.library.sl.Visitable getChildAt(int index) {");
    output.writeln(deep, "switch (index) {");
    output.writeln(deep, "case 0: return super.getChildAt(0);");
    for (int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep, "case " + (i+1) + ": return get" + names.get(j) + "();");
    }
    output.writeln(deep, "default: throw new IndexOutOfBoundsException();");
    output.writeln(deep, "}");
    output.writeln(deep, "}");

    
    output.writeln(deep, "public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {");
    output.writeln(deep, "switch (index) {");
    output.writeln(deep, "case 0: return super.setChildAt(0, child);");
    for (int i = 0; i < stratChildCount; i++) {
      int j = (stratChild.get(i)).intValue();
      output.writeln(deep, "case " + (i+1) + ": " + names.get(j) + " = (" + types.get(j) + ")child; return this;");
    }
    output.writeln(deep, "default: throw new IndexOutOfBoundsException();");
    output.writeln(deep, "}");
    output.writeln(deep, "}");

    generateDeclaration(deep,declaration,moduleName);
    output.writeln(deep,"}");
  }

  protected void buildFunctionDef(int deep, String tomName, BQTermList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,this.modifier);
  }

  protected void buildMethodDef(int deep, String tomName, BQTermList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,"public ");
  }

  private void buildMethod(int deep, String tomName, BQTermList varList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName, String methodModifier) throws IOException {
    output.writeln(deep, "@SuppressWarnings(\"unchecked\")");
    output.write(deep, methodModifier + TomBase.getTLType(codomain) + " " + tomName + "(");
    while(!varList.isEmptyconcBQTerm()) {
      BQTerm localVar = varList.getHeadconcBQTerm();
      matchBlock: {
        { /* unamed block */{ /* unamed block */if ( (localVar instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )localVar) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {

            output.write(deep,TomBase.getTLType( (( tom.engine.adt.code.types.BQTerm )localVar).getAstType() ) + " ");
            generateBQTerm(deep,(( tom.engine.adt.code.types.BQTerm )localVar),moduleName);
            break matchBlock;
          }}}{ /* unamed block */if ( (localVar instanceof tom.engine.adt.code.types.BQTerm) ) {

            System.out.println("MakeFunction: strange term: " + localVar);
            throw new TomRuntimeException("MakeFunction: strange term: " + localVar);
          }}}

      }
      varList = varList.getTailconcBQTerm();
      if(!varList.isEmptyconcBQTerm()) {
        output.write(deep,", ");
      }
    }
    output.writeln(deep,")");
matchblock: {
    { /* unamed block */{ /* unamed block */if ( (throwsType instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )throwsType) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) { tom.engine.adt.tomtype.types.TargetLanguageType  tomMatch114_1= (( tom.engine.adt.tomtype.types.TomType )throwsType).getTlType() ;if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType )tomMatch114_1) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.TLType) ) {

				output.write(deep," throws " +  tomMatch114_1.getString() );
        break matchblock;
			}}}}{ /* unamed block */if ( (throwsType instanceof tom.engine.adt.tomtype.types.TomType) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )throwsType) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType ) (( tom.engine.adt.tomtype.types.TomType )throwsType).getTlType() ) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.EmptyTargetLanguageType) ) {


				output.write(deep," throws " +  (( tom.engine.adt.tomtype.types.TomType )throwsType).getTomType() );
        break matchblock;
			}}}}}

  }

    output.writeln(" {");
    generateInstruction(deep,instruction,moduleName);
    output.writeln(deep,"}");
  }



}
