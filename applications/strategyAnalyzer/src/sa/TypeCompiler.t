package sa;

import sa.rule.types.*;
import java.util.*;
import tom.library.sl.*;
import aterm.*;
import aterm.pure.*;

public class TypeCompiler {
  %include { rule/Rule.tom }
  %include { sl.tom }
  %include { java/util/types/Map.tom }
  %include { java/util/types/List.tom }
  %include { java/util/types/ArrayList.tom }

  %typeterm Signature { implement { Signature } }

  // The extracted (concrete) signature
  private Signature extractedSignature;
  // The typed signature
  private Signature typedSignature;
  // The generated (ordered) TRS
  private List<Rule> untypedRules;
  private List<Rule> generatedRules;

  public TypeCompiler(Signature extractedSignature, List<Rule> untypedRules) {
    this.extractedSignature = extractedSignature;
    this.typedSignature = extractedSignature.cloneSignature();
    this.untypedRules = untypedRules;
    this.generatedRules = new ArrayList<Rule>();
  }

  public Signature getExtractedSignature() {
    return this.extractedSignature;
  }

  public Signature getTypedSignature() {
    return this.typedSignature;
  }

  public List<Rule> getGeneratedRules() {
    return this.generatedRules;
  }

  /**
   * Transform each rewrite rule to a set of well-typed rules with the same behaviour. 
   **/
  public void typeRules() {
    Map<String,GomType> env = new HashMap<String,GomType>();

    for(Rule rule: untypedRules) {
      %match(rule) {
        Rule(lhs@Appl(stratOp,TermList(arg,_*)), rhs) -> {
          // get the possible codomain(s) for the head operator of the LHS
          // - boolean operators: Bool
          // - operators of the form ALL-f... : codomain of f
          // - operators of the form CHOICE... (no ...-f) : codomain of its first argument
          Collection<GomType> types = new HashSet<GomType>();
          if(getExtractedSignature().isBooleanOperator(`stratOp)) { // if head opearator of the rule is EQ or AND than the codomain should be BOOL 
            types.add(`GomType(Signature.BOOLEAN));
          } else { 
            if(Tools.getOperatorName(`stratOp) != null){ // if symbol of the form ALL-f... the codomain is given by the codomain of f
              types = this.getTypes(env,`lhs);
            }else{ // otherwise the codomain is given by the codomain of its argument
              types = this.getTypes(env,`arg);
            }
          }
          // normally shouldn't happen 
          if(types.size() == 0){
            throw new UntypableTermException("RULE OMITTED for " + `stratOp + "  because no possible codomain");
          }

          // for each potential codomain generate a new rule
          // (the potential codomain is unique for all operators but for BOTTOM
          //  which can be of any type of the declared (ie extracted) signature
          for(GomType type: types) {
            try {
              env = new HashMap<String,GomType>(); // start with fresh env for each rule generation
              Term typedLhs = this.propagateType(env,`lhs,type); // type of all variables is inferred in env
                                                                 // head symbol of LHS is added to the typed signature
              Term typedRhs = this.propagateType(env,`rhs,type);
              Rule newRule = `Rule(typedLhs, typedRhs);
              this.generatedRules.add(newRule);
            } catch(TypeMismatchException typeExc) {
              System.err.println("RULE OMITTED for " + `stratOp + "  because of " + typeExc.getMessage());
            }
          }
        }
      }
    }

    for(Rule rule: generatedRules) {
      System.out.println(" RULE "+ Pretty.toString(rule));
    }

  }

  /** Get the potential types of a term by looking at its head symbol
   *  The head symbol can be a symbol from the extracted signature or Bottom or a boolean operator
   *  (symbol can't be a generated)
   */  
  private List<GomType> getTypes(Map<String,GomType> env, Term term) {
    List<GomType> types = new ArrayList<GomType>();
    Signature eSig = this.getExtractedSignature();
    %match(term) {
        Appl(symbol,_) -> {
          if(`symbol == Signature.BOTTOM) { // for BOTTOM add all possible types
            types.addAll(eSig.getTypes());
          } else if(eSig.isBooleanOperator(`symbol)) {  // for boolean ops (in fact only TRUE or FALSE can occur) add BOOLEAN
            types.add(`GomType(Signature.BOOLEAN));
          } else if(Tools.getOperatorName(`symbol) != null) {   // for symbol of the form ALL-f add the type of f
            types.add(eSig.getCodomainType(Tools.getOperatorName(`symbol)));
          } else if(eSig.getCodomainType(`symbol) != null) {  // for symbols of the original signature add their type
            types.add(eSig.getCodomainType(`symbol));
          }
        }

        Var(name) -> { // for VAR get the type from the environment
          if(env.get(`name) != null){
            types.add(env.get(`name));
          }else{ // if we don't know it's type than it could be any type (eg for mainstrat)
            types.addAll(eSig.getTypes());
          }
        }
    }
    return types;
  }
   
  /**
   * Propagate the type information in the names of the symbols. Term t of the form:
   * - all_39(s1)
   * - all_39-f(s1,...,sn,s) with n=ar(f)
   * - one_42(s1)
   * - one_42-f(s1,...,sn) with n=ar(f)
   * - ...
   */
  private Term propagateType(Map<String,GomType> env, Term t, GomType type) {
    Term typedTerm = t;
    Signature eSig = getExtractedSignature();

    %match(t) {
      Appl(name,args) -> {
        if(eSig.isBooleanOperatorExceptEQ(`name)){ // for AND, TRUE, FALSE we override the "type" imposed by propagateType: always BOOLEAN
          type = `GomType(Signature.BOOLEAN);
        }        
        if(eSig.isBooleanOperatorEQ(`name)) { // for EQ we override the "type" imposed by propagateType: the type of its first argument
            %match(args) {
              TermList(arg,_*) -> {
                if(this.getTypes(env,`arg).size() == 1){ // at least one type (ie well-typed) and only one (ie no Bottom)
                  type = this.getTypes(env,`arg).get(0);
                }else{  // normally shouldn't happen 
                  throw new UntypableTermException("No possible type for "+`arg+" in term "+t);
                }
              }
          }
        }

        List<GomType> domain = null;
        String fun = null;
        String typedName = null;

        // build the symbol name by adding the type information
        if(eSig.getCodomainType(`name) != null){ // if term in the extracted signature then first check if it's well-typed
          fun = `name;
          // if type mismatch than the rule should be eventually removed
          if(eSig.getCodomainType(`name) != type) {
            throw new TypeMismatchException("BAD ARG: " + `name + " TRY TYPE " + type + " IN TERM "+t);
          }
          // don't change its name if symbol from original (extracted) signature
          typedName = `name;
        }else{
          // retrieve fun from name of the form symbolName-fun_typeName
          fun = Tools.getOperatorName(`name);
          typedName = Tools.addTypeName(`name,type.getName()); // add type information to symbol name
        }

        // build the domain of the typed symbol
        if(fun != null){ // if a composite symbol (e.g. all-f_...)  or symbol from original signature (eg  f, g, ...)
          domain = eSig.getProfileType(fun);
          if(domain == null){  // normally should'n happen (unless wrongly built symbol name)
            throw new UntypableTermException("Type of symbol "+ fun +"cannot be determined");
          }
          // for all_f add the type of f(...) at the end
          if(StrategyOperator.getStrategyOperator(Tools.getSymbolNameMain(`name))==StrategyOperator.ALL){
            domain.add(type); 
          }
        }else{  // if Boolean or Generated symbol
          domain = new ArrayList<GomType>();
          if(!Tools.getSymbolNameMain(`name).equals(Signature.TRUE) && !Tools.getSymbolNameMain(`name).equals(Signature.FALSE)){
            domain.add(type); // first argument has always the same type as the term
            if(eSig.isBooleanOperator(`name) ||
               (Tools.isSymbolNameAux(`name) && StrategyOperator.getStrategyOperator(Tools.getSymbolNameMain(`name))==StrategyOperator.SEQ)){
              // if boolean operator (EQ or AND) or if aux symbol for SEQ (ie seqAux_...) then there is a second parameter of the same type
              domain.add(type);
            }else if(Tools.isSymbolNameAux(`name) && StrategyOperator.getStrategyOperator(Tools.getSymbolNameMain(`name))==StrategyOperator.RULE){
              // if aux symbol for RULE (originating from a non-linear rule compilation) then there is a second parameter of type Bool
              domain.add(`GomType(Signature.BOOLEAN));
            }
          }
        }

        // propagate the type to the subterms
        int i = 0;
        TermList args = `args;
        TermList newArgs = `TermList();
        while(!args.isEmptyTermList()) {
          Term arg = args.getHeadTermList();
          Term typedArg = propagateType(env, arg, domain.get(i));
          if(typedArg != null) {
            newArgs = `TermList(newArgs*, typedArg);
          }else{
            throw new UntypableTermException("Can't type argument " + arg +" in "+t);
          }
          i++;
          args = args.getTailTermList();
        }
        typedTerm = `Appl(typedName,newArgs);

        // add info into typed signature
        if(`name == Signature.EQ) {
//           typedSignature.addSymbolType(typedName,domain.subList(0,i),`GomType(Signature.BOOLEAN));
          typedSignature.addSymbolType(typedName,domain.subList(0,i),`GomType(Signature.BOOLEAN));
        } else {
//           typedSignature.addSymbolType(typedName,domain.subList(0,i),type);
          typedSignature.addSymbolType(typedName,domain.subList(0,i),type);
        }
        // TODO: build domain exactly as it should be (problem for TRUE, ruleAUX, ...?)
      }

      s@Var(name) -> {
        // set the type in the environment
        GomType varType = env.get(`name);
        if(varType != null){ // if type already set
          if(varType != type){ // if try to assigne a different type
            throw new RuntimeException("Attemp to type variable with different type");
          }
        }else{ // set type if not already done
          env.put(`name,type);
        }
        // dont't change it
        typedTerm = `s;
      }
    }
    return typedTerm;
  }

}
 
  
  /********************************************************************************
   *     END
   ********************************************************************************/

//   /*
//    * Specializer
//    * @param symbol the symbol whose type is enforce
//    * @param the type of the symbol
//    * @param trs the TRS to transform
//    */
//   public List<Rule> specialize(String symbol, String typeName, List<Rule> trs) {
//     Signature gSig = getGeneratedSignature();
//     String codomainName = gSig.getCodomain(symbol);

//     for(Rule r: trs) {
//       %match(r) {
//         Rule(lhs@Appl(name,args),rhs) -> {
//           if(`name.equals(symbol)) {
//             Term newLhs = propagateType(`EmptyEnvironment(), `lhs, `GomType(typeName));
//             System.out.println("newLhs = " + newLhs);
//           }
//         }
//       }

//     }
//     return null;
//   }

//   private Term propagateType(TypeEnvironment env, Term t, GomType type) {
//     Signature gSig = getGeneratedSignature();
//     %match(t) {
//       Appl(name,args) -> {
//         String codomainName = gSig.getCodomain(`name);
//         if(codomainName == Signature.TERM) {
//           List<String> domain = gSig.getProfile(`name);
//           String dname = gSig.disambiguateSymbol(`name, domain);
//           gSig.addSymbol(dname,domain,codomainName);
//           int i = 0;
//           TermList args = `args;
//           TermList newArgs = `TermList();
//           while(!args.isEmptyTermList()) {
//             Term arg = args.getHeadTermList();
//             // compute GOOD type here!
//             Term arg2 = propagateType(env, arg, `GomType(domain.get(i)));
//             if(arg2!=null) {
//               newArgs = `TermList(newArgs*, arg2);
//             } else {
//               // throw exception
//               System.out.println("bad arg2: " + arg2);
//               return null;
//             }
//             i++;
//             args = args.getTailTermList();
//           }
//           return `Appl(dname,newArgs);
//         } else if(type != `GomType(codomainName)) {
//           // throw exception
//           System.out.println("bad type: " + t + " : " + type);
//           return null;
//         }

//       }

//       Anti(t1) -> {
//         return propagateType(env, `t1,type);
//       }

//       At(t1,t2) -> {
//         env = checkType(env,`t1, type);
//         if(env == null) {
//           // throw exception
//           System.out.println("bad type: " + `t1 + " : " + type);
//           return null;
//         }
//         return propagateType(env, `t2,type);
//       }

//       s@BuiltinInt(i) -> {
//         if(type != `GomType("int")) {
//           // throw exception
//           System.out.println("bad type: " + `s + " : " + type);
//           return null;
//         }
//         return `s;
//       }

//       s@Var(name) -> {
//         env = checkType(env,`s, type);
//         if(env == null) {
//           // throw exception
//           System.out.println("bad type: " + `s + " : " + type);
//           return null;
//         } 
//         return `s;
//       }

//     }
//     System.out.println("propagateType should not be there: " + t);
//     return null;
//   }

//   /*
//    * check that t is a variable with a type compatible with the enviroment
//    * @param env the environment
//    * @param t the term to type check
//    * @param type the type
//    * @return null is t is not correctly typed, or the enviroment enriched with t:type
//    */
//   private TypeEnvironment checkType(TypeEnvironment env, Term t, GomType type) {
//     %match(env, t) {
//       EmptyEnvironment(), Var(x) -> {
//         return `PushEnvironment(x, type, EmptyEnvironment());
//       }
      
//       PushEnvironment(x,xtype,tail), Var(x) -> {
//         if(`xtype == type) {
//           return env;
//         } else {
//           // type mismatch
//           return null;
//         }
//       }
      
//       PushEnvironment(y,ytype,tail), Var(x) -> {
//         TypeEnvironment tmp = checkType(`tail, t, type);
//         if(tmp == null) {
//           return null;
//         } else {
//           return `PushEnvironment(y,type, tmp);
//         }
//       }

//     }

//     System.out.println("checkType should not be there: " + t);
//     return null;
//   }


//   /*
//    * term t of the form:
//    * - all_39(s1)
//    * - all_39-f(s1,...,sn,s) with n=ar(f)
//    * - one_42(s1)
//    * - one_42-f(s1,...,sn) with n=ar(f)
//    */
//   private Term propagateType(TypeEnvironment env, Term t, GomType type) {
//     Term typedTerm = t;
//     Signature eSig = getExtractedSignature();

// //           this.AddVarToEnv();

//     System.out.println("PROPAGATE " + t);

//     %match(t) {
//       Appl(name,args) -> {
//         // if term in the extracted signature then don't change it
// //         if(eSig.getCodomainType(`name) != null) {
// //           // if type mismatch than the rule should be eventually removed
// //           if(eSig.getCodomainType(`name) != type) {
// //             throw new TypeMismatchException("BAD ARG: " + `name + " TRY TYPE " + type);
// //           }
// //         }else{ //change symbol names otherwise
//           if(eSig.isBooleanOperatorExceptEQ(`name)) {
//             type = `GomType(Signature.BOOLEAN);
//             //typedSignature.addSymbol(Signature.TRUE,new ArrayList<String>(),Signature.BOOLEAN);
//             //typedSignature.addSymbol(Signature.FALSE,new ArrayList<String>(),Signature.BOOLEAN);
//             //typedSignature.addSymbol(Signature.AND,Arrays.asList(Signature.BOOLEAN,Signature.BOOLEAN),Signature.BOOLEAN);
//           }
//           //} else {
//         {
//             List<GomType> domain = null;

//             String fun = null;
//             String typedName = null;

//             if(eSig.getCodomainType(`name) != null){ // if term in the extracted signature check if it's well-typed
//               fun = `name;
//               // if type mismatch than the rule should be eventually removed
//               if(eSig.getCodomainType(`name) != type) {
//                 throw new TypeMismatchException("BAD ARG: " + `name + " TRY TYPE " + type);
//               }
//               // don't change its name
//               typedName = `name;
//             }else{
//               // retrieve fun from name of the form symbolName-fun_typeName
//               fun = Tools.getOperatorName(`name);
//               typedName = Tools.addTypeName(`name,type.getName());
//             }

//             if(fun != null) { // if a composite symbol (e.g. all-f_...)  or symbol from original signature (eg  f, g, ...)
//               domain = eSig.getProfileType(fun);
//               // TODO : domain == null
//               // pem why not using add as below? domain.add(domain.size(),type); // for all_f add the type of f(...) at the end; will be ignored for one_f
//               domain.add(type); // THIS IS HACK. ONLY needed for ALL and ignored for ONE and f,g,...
//             } else {
//               // at most 2 arguments; propagate the type
//               // TODO: be more general ?
//               domain = new ArrayList<GomType>();
//               //pem: why arity 2 ? THIS IS HACK !!!
//               domain.add(type);
//               domain.add(type);
//             }

//             int i = 0;
//             TermList args = `args;
//             TermList newArgs = `TermList();
//             while(!args.isEmptyTermList()) {
//               Term arg = args.getHeadTermList();
//               Term typedArg = propagateType(env, arg, domain.get(i));
//               if(typedArg != null) {
//                 newArgs = `TermList(newArgs*, typedArg);
//               } else {
//                 // throw exception
//                 System.out.println("bad typedArg: " + typedArg);
//                 return null;
//               }
//               i++;
//               args = args.getTailTermList();
//             }
//             System.out.println("APPP " + typedName + "  -- " + newArgs);
//             typedTerm = `Appl(typedName,newArgs);

//             // add info into typed signature
//             if(`name == Signature.EQ) {
//               typedSignature.addSymbolType(typedName,domain.subList(0,i),`GomType(Signature.BOOLEAN));
//             } else {
//               typedSignature.addSymbolType(typedName,domain.subList(0,i),type);
//             }
//           }
// //         }
//       }

//       s@Var(name) -> {
//         System.out.println("VAR " + `s);
//         typedTerm = `s;
//       }
//     }
//     return typedTerm;
//   }