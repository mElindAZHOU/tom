/* Generated by TOM (version 2.6): Do not edit this file *//*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2008, INRIA
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
 * Antoine Reilles        e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.engine.verifier;

import tom.engine.*;
import aterm.*;
import aterm.pure.*;
import java.util.*;
import tom.engine.adt.zenon.*;
import tom.engine.adt.zenon.types.*;

public class ZenonBackend {

  // ------------------------------------------------------------
  /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */  /* Generated by TOM (version 2.6): Do not edit this file */    public static   tom.engine.adt.zenon.types.ZAxiomList  tom_append_list_zby( tom.engine.adt.zenon.types.ZAxiomList l1,  tom.engine.adt.zenon.types.ZAxiomList  l2) {     if( l1.isEmptyzby() ) {       return l2;     } else if( l2.isEmptyzby() ) {       return l1;     } else if(  l1.getTailzby() .isEmptyzby() ) {       return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( l1.getHeadzby() ,l2) ;     } else {       return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( l1.getHeadzby() ,tom_append_list_zby( l1.getTailzby() ,l2)) ;     }   }   public static   tom.engine.adt.zenon.types.ZAxiomList  tom_get_slice_zby( tom.engine.adt.zenon.types.ZAxiomList  begin,  tom.engine.adt.zenon.types.ZAxiomList  end, tom.engine.adt.zenon.types.ZAxiomList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyzby()  ||  (end== tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( begin.getHeadzby() ,( tom.engine.adt.zenon.types.ZAxiomList )tom_get_slice_zby( begin.getTailzby() ,end,tail)) ;   }    
  // ------------------------------------------------------------

  private Verifier verifier; // is it useful ?
  private TomIlTools tomiltools;

  public ZenonBackend(Verifier verifier) {
    this.verifier = verifier;
    this.tomiltools = new TomIlTools(verifier);
  }

  public String genZSymbol(ZSymbol symbol) {
    {{ Object tomMatch323NameNumber_freshVar_0=symbol;if ( (tomMatch323NameNumber_freshVar_0 instanceof tom.engine.adt.zenon.types.ZSymbol) ) {{  tom.engine.adt.zenon.types.ZSymbol  tomMatch323NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZSymbol )tomMatch323NameNumber_freshVar_0);{  tom.engine.adt.zenon.types.ZSymbol  tomMatch323NameNumber_freshVar_2=tomMatch323NameNumber_freshSubject_1;if ( (tomMatch323NameNumber_freshVar_2 instanceof tom.engine.adt.zenon.types.zsymbol.zsymbol) ) {{  String  tomMatch323NameNumber_freshVar_1= tomMatch323NameNumber_freshVar_2.getName() ;

        // manage builtins
        String symbolName = tomiltools.replaceNumbersByString(tomMatch323NameNumber_freshVar_1);
        return symbolName+ "_";
      }}}}}}}

    return "errorZSymbol";
  }

  public String genZType(ZType type) {
    {{ Object tomMatch324NameNumber_freshVar_0=type;if ( (tomMatch324NameNumber_freshVar_0 instanceof tom.engine.adt.zenon.types.ZType) ) {{  tom.engine.adt.zenon.types.ZType  tomMatch324NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZType )tomMatch324NameNumber_freshVar_0);{  tom.engine.adt.zenon.types.ZType  tomMatch324NameNumber_freshVar_2=tomMatch324NameNumber_freshSubject_1;if ( (tomMatch324NameNumber_freshVar_2 instanceof tom.engine.adt.zenon.types.ztype.ztype) ) {{  String  tomMatch324NameNumber_freshVar_1= tomMatch324NameNumber_freshVar_2.getTname() ;

        return tomMatch324NameNumber_freshVar_1;
      }}}}}}}

    return "errorZType";
  }

  public String genZTerm(ZTerm term) {
    {{ Object tomMatch325NameNumber_freshVar_0=term;if ( (tomMatch325NameNumber_freshVar_0 instanceof tom.engine.adt.zenon.types.ZTerm) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZTerm )tomMatch325NameNumber_freshVar_0);{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshVar_2=tomMatch325NameNumber_freshSubject_1;if ( (tomMatch325NameNumber_freshVar_2 instanceof tom.engine.adt.zenon.types.zterm.zvar) ) {{  String  tomMatch325NameNumber_freshVar_1= tomMatch325NameNumber_freshVar_2.getVarname() ;
 return tomMatch325NameNumber_freshVar_1; }}}}}}{ Object tomMatch325NameNumber_freshVar_3=term;if ( (tomMatch325NameNumber_freshVar_3 instanceof tom.engine.adt.zenon.types.ZTerm) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZTerm )tomMatch325NameNumber_freshVar_3);{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshVar_6=tomMatch325NameNumber_freshSubject_1;if ( (tomMatch325NameNumber_freshVar_6 instanceof tom.engine.adt.zenon.types.zterm.zappl) ) {{  tom.engine.adt.zenon.types.ZSymbol  tomMatch325NameNumber_freshVar_4= tomMatch325NameNumber_freshVar_6.getZsymb() ;{  tom.engine.adt.zenon.types.ZTermList  tomMatch325NameNumber_freshVar_5= tomMatch325NameNumber_freshVar_6.getTermlist() ;{  tom.engine.adt.zenon.types.ZSymbol  tomMatch325NameNumber_freshVar_8=tomMatch325NameNumber_freshVar_4;if ( (tomMatch325NameNumber_freshVar_8 instanceof tom.engine.adt.zenon.types.zsymbol.zsymbol) ) {{  String  tomMatch325NameNumber_freshVar_7= tomMatch325NameNumber_freshVar_8.getName() ;
 
        // manage builtins
        String realName = tomiltools.replaceNumbersByString(tomMatch325NameNumber_freshVar_7);
        return "(" + realName +" "+genZTermList(tomMatch325NameNumber_freshVar_5)+")"; 
      }}}}}}}}}}{ Object tomMatch325NameNumber_freshVar_9=term;if ( (tomMatch325NameNumber_freshVar_9 instanceof tom.engine.adt.zenon.types.ZTerm) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZTerm )tomMatch325NameNumber_freshVar_9);{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshVar_12=tomMatch325NameNumber_freshSubject_1;if ( (tomMatch325NameNumber_freshVar_12 instanceof tom.engine.adt.zenon.types.zterm.zst) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshVar_10= tomMatch325NameNumber_freshVar_12.getAbst() ;{  int  tomMatch325NameNumber_freshVar_11= tomMatch325NameNumber_freshVar_12.getIndex() ;
 
        return "(_"+tomMatch325NameNumber_freshVar_11+" "+genZTerm(tomMatch325NameNumber_freshVar_10)+")";
      }}}}}}}{ Object tomMatch325NameNumber_freshVar_13=term;if ( (tomMatch325NameNumber_freshVar_13 instanceof tom.engine.adt.zenon.types.ZTerm) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZTerm )tomMatch325NameNumber_freshVar_13);{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshVar_16=tomMatch325NameNumber_freshSubject_1;if ( (tomMatch325NameNumber_freshVar_16 instanceof tom.engine.adt.zenon.types.zterm.zsl) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch325NameNumber_freshVar_14= tomMatch325NameNumber_freshVar_16.getAbst() ;{  String  tomMatch325NameNumber_freshVar_15= tomMatch325NameNumber_freshVar_16.getName() ;
 
        return "(_"+tomMatch325NameNumber_freshVar_15+" "+genZTerm(tomMatch325NameNumber_freshVar_14)+")";
      }}}}}}}}

    return "errorZTerm";
  }

  public String genZTermList(ZTermList tl) {
    StringBuilder res = new StringBuilder();
    while (!tl.isEmptyconcZTerm()) {
      res.append(" "+genZTerm(tl.getHeadconcZTerm()));
      tl = tl.getTailconcZTerm();
    }
    return res.toString();
  }

  public String genZExpr(ZExpr expr) {
    {{ Object tomMatch326NameNumber_freshVar_0=expr;if ( (tomMatch326NameNumber_freshVar_0 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_0);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_1=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_1 instanceof tom.engine.adt.zenon.types.zexpr.ztrue) ) {
 return "True";}}}}}{ Object tomMatch326NameNumber_freshVar_2=expr;if ( (tomMatch326NameNumber_freshVar_2 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_2);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_3=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_3 instanceof tom.engine.adt.zenon.types.zexpr.zfalse) ) {
 return "False";}}}}}{ Object tomMatch326NameNumber_freshVar_4=expr;if ( (tomMatch326NameNumber_freshVar_4 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_4);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_7=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_7 instanceof tom.engine.adt.zenon.types.zexpr.zisfsym) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch326NameNumber_freshVar_5= tomMatch326NameNumber_freshVar_7.getT() ;{  tom.engine.adt.zenon.types.ZSymbol  tomMatch326NameNumber_freshVar_6= tomMatch326NameNumber_freshVar_7.getSymbol() ;

        return "((symb "+genZTerm(tomMatch326NameNumber_freshVar_5)+") = "+genZSymbol(tomMatch326NameNumber_freshVar_6)+")";
      }}}}}}}{ Object tomMatch326NameNumber_freshVar_8=expr;if ( (tomMatch326NameNumber_freshVar_8 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_8);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_11=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_11 instanceof tom.engine.adt.zenon.types.zexpr.zeq) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch326NameNumber_freshVar_9= tomMatch326NameNumber_freshVar_11.getLt() ;{  tom.engine.adt.zenon.types.ZTerm  tomMatch326NameNumber_freshVar_10= tomMatch326NameNumber_freshVar_11.getRt() ;

        return "("+genZTerm(tomMatch326NameNumber_freshVar_9)+" = "+genZTerm(tomMatch326NameNumber_freshVar_10)+")";
      }}}}}}}{ Object tomMatch326NameNumber_freshVar_12=expr;if ( (tomMatch326NameNumber_freshVar_12 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_12);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_16=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_16 instanceof tom.engine.adt.zenon.types.zexpr.zforall) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch326NameNumber_freshVar_13= tomMatch326NameNumber_freshVar_16.getVar() ;{  tom.engine.adt.zenon.types.ZType  tomMatch326NameNumber_freshVar_14= tomMatch326NameNumber_freshVar_16.getAztype() ;{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_15= tomMatch326NameNumber_freshVar_16.getExpr() ;

        return "forall "+genZTerm(tomMatch326NameNumber_freshVar_13)+" : "+genZType(tomMatch326NameNumber_freshVar_14)+",\n "+genZExpr(tomMatch326NameNumber_freshVar_15);
      }}}}}}}}{ Object tomMatch326NameNumber_freshVar_17=expr;if ( (tomMatch326NameNumber_freshVar_17 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_17);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_21=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_21 instanceof tom.engine.adt.zenon.types.zexpr.zexists) ) {{  tom.engine.adt.zenon.types.ZTerm  tomMatch326NameNumber_freshVar_18= tomMatch326NameNumber_freshVar_21.getVar() ;{  tom.engine.adt.zenon.types.ZType  tomMatch326NameNumber_freshVar_19= tomMatch326NameNumber_freshVar_21.getAztype() ;{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_20= tomMatch326NameNumber_freshVar_21.getExpr() ;

        return "exists "+genZTerm(tomMatch326NameNumber_freshVar_18)+" : "+genZType(tomMatch326NameNumber_freshVar_19)+", "+genZExpr(tomMatch326NameNumber_freshVar_20);
      }}}}}}}}{ Object tomMatch326NameNumber_freshVar_22=expr;if ( (tomMatch326NameNumber_freshVar_22 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_22);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_25=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_25 instanceof tom.engine.adt.zenon.types.zexpr.zand) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_23= tomMatch326NameNumber_freshVar_25.getLte() ;{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_24= tomMatch326NameNumber_freshVar_25.getRte() ;{  tom.engine.adt.zenon.types.ZExpr  tom_l=tomMatch326NameNumber_freshVar_23;{  tom.engine.adt.zenon.types.ZExpr  tom_r=tomMatch326NameNumber_freshVar_24;

        if(tom_l==  tom.engine.adt.zenon.types.zexpr.ztrue.make() ) {
          return "("+ genZExpr(tom_r) +")";
        }
        else if (tom_r==  tom.engine.adt.zenon.types.zexpr.ztrue.make() ) {
          return "("+ genZExpr(tom_l) +")";
        }
        return "("+genZExpr(tom_l)+") /\\ ("+genZExpr(tom_r)+")";
      }}}}}}}}}{ Object tomMatch326NameNumber_freshVar_26=expr;if ( (tomMatch326NameNumber_freshVar_26 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_26);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_29=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_29 instanceof tom.engine.adt.zenon.types.zexpr.zor) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_27= tomMatch326NameNumber_freshVar_29.getLte() ;{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_28= tomMatch326NameNumber_freshVar_29.getRte() ;{  tom.engine.adt.zenon.types.ZExpr  tom_l=tomMatch326NameNumber_freshVar_27;{  tom.engine.adt.zenon.types.ZExpr  tom_r=tomMatch326NameNumber_freshVar_28;

        if(tom_l==  tom.engine.adt.zenon.types.zexpr.zfalse.make() ) {
          return "("+ genZExpr(tom_r) +")";
        }
        else if (tom_r==  tom.engine.adt.zenon.types.zexpr.zfalse.make() ) {
          return "("+ genZExpr(tom_l) +")";
        }
        return "("+genZExpr(tom_l)+") \\/ ("+genZExpr(tom_r)+")";
      }}}}}}}}}{ Object tomMatch326NameNumber_freshVar_30=expr;if ( (tomMatch326NameNumber_freshVar_30 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_30);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_32=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_32 instanceof tom.engine.adt.zenon.types.zexpr.znot) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_31= tomMatch326NameNumber_freshVar_32.getNex() ;
 return "~("+genZExpr(tomMatch326NameNumber_freshVar_31)+")"; }}}}}}{ Object tomMatch326NameNumber_freshVar_33=expr;if ( (tomMatch326NameNumber_freshVar_33 instanceof tom.engine.adt.zenon.types.ZExpr) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZExpr )tomMatch326NameNumber_freshVar_33);{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_36=tomMatch326NameNumber_freshSubject_1;if ( (tomMatch326NameNumber_freshVar_36 instanceof tom.engine.adt.zenon.types.zexpr.zequiv) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_34= tomMatch326NameNumber_freshVar_36.getLte() ;{  tom.engine.adt.zenon.types.ZExpr  tomMatch326NameNumber_freshVar_35= tomMatch326NameNumber_freshVar_36.getRte() ;

        return "("+genZExpr(tomMatch326NameNumber_freshVar_34)+") <-> ("+genZExpr(tomMatch326NameNumber_freshVar_35)+")";
      }}}}}}}}

    return "errorZExpr";
  }

  public String genZAxiom(ZAxiom axiom) {
    {{ Object tomMatch327NameNumber_freshVar_0=axiom;if ( (tomMatch327NameNumber_freshVar_0 instanceof tom.engine.adt.zenon.types.ZAxiom) ) {{  tom.engine.adt.zenon.types.ZAxiom  tomMatch327NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZAxiom )tomMatch327NameNumber_freshVar_0);{  tom.engine.adt.zenon.types.ZAxiom  tomMatch327NameNumber_freshVar_3=tomMatch327NameNumber_freshSubject_1;if ( (tomMatch327NameNumber_freshVar_3 instanceof tom.engine.adt.zenon.types.zaxiom.zaxiom) ) {{  String  tomMatch327NameNumber_freshVar_1= tomMatch327NameNumber_freshVar_3.getName() ;{  tom.engine.adt.zenon.types.ZExpr  tomMatch327NameNumber_freshVar_2= tomMatch327NameNumber_freshVar_3.getAx() ;

        // manage builtins
        String realName = tomiltools.replaceNumbersByString(tomMatch327NameNumber_freshVar_1);
        return "Parameter " + realName +" :\n    " + genZExpr(tomMatch327NameNumber_freshVar_2) + ".\n";
      }}}}}}}}

    return "errorZAxiom";
  }

  public String genZAxiomList(ZAxiomList axlist) {
    StringBuilder res = new StringBuilder();
    while (!axlist.isEmptyzby()) {
      res.append(genZAxiom(axlist.getHeadzby()));
      axlist = axlist.getTailzby();
    }
    return res.toString();
  }

  public String genZSpec(ZSpec spec) {
    {{ Object tomMatch328NameNumber_freshVar_0=spec;if ( (tomMatch328NameNumber_freshVar_0 instanceof tom.engine.adt.zenon.types.ZSpec) ) {{  tom.engine.adt.zenon.types.ZSpec  tomMatch328NameNumber_freshSubject_1=(( tom.engine.adt.zenon.types.ZSpec )tomMatch328NameNumber_freshVar_0);{  tom.engine.adt.zenon.types.ZSpec  tomMatch328NameNumber_freshVar_3=tomMatch328NameNumber_freshSubject_1;if ( (tomMatch328NameNumber_freshVar_3 instanceof tom.engine.adt.zenon.types.zspec.zthm) ) {{  tom.engine.adt.zenon.types.ZExpr  tomMatch328NameNumber_freshVar_1= tomMatch328NameNumber_freshVar_3.getThm() ;{  tom.engine.adt.zenon.types.ZAxiomList  tomMatch328NameNumber_freshVar_2= tomMatch328NameNumber_freshVar_3.getBy() ;

        return "\n" 
          + genZExpr(tomMatch328NameNumber_freshVar_1) 
          + "\n" 
          + genZAxiomList(tomMatch328NameNumber_freshVar_2)+"\n";
      }}}}}}}}

    return "errorZSpec";
  }

  public String genFunctionSymbolDeclaration(String symbolName) {
    StringBuilder res = new StringBuilder();
    res.append("Parameter ");
    res.append(tomiltools.replaceNumbersByString(symbolName)+" :");
    // take care of the arity
    List names = tomiltools.subtermList(symbolName);
    for(int i = 0; i<names.size();i++) {
      res.append(" T ->");
    }
    res.append(" T.\n");
    return res.toString();
  }

  public String genZSpecCollection(Collection collection) {
    StringBuilder out = new StringBuilder();

    out.append("\nRequire Import zenon.\n");
    out.append("\nParameter T S : Set.\n");

    // collects all used symbols
    Collection symbols = new HashSet();
    Iterator it = collection.iterator();
    while(it.hasNext()) {
      ZSpec local = (ZSpec) it.next();
      symbols.addAll(tomiltools.collectSymbolsFromZSpec(local));
    }

    // Generates types for symbol functions
    it = symbols.iterator();
    while(it.hasNext()) {
      String symbolName = (String) it.next();
      out.append(genFunctionSymbolDeclaration(symbolName));
      // declares the subterm functions if necessary
      List names = tomiltools.subtermList(symbolName);
      if(names.size() != 0) {
        out.append("Parameter ");
        Iterator nameIt = names.iterator();
        while(nameIt.hasNext()) {
          String localName = (String) nameIt.next();
          out.append("_" + localName + " ");
        }
        out.append(": T -> T.\n");
      }
    }


    out.append("Parameter symb : T -> S.\n");
    // XXX: define True
    out.append("Parameter true_is_true : True.\n");
    // Generates types for symbols
    it = symbols.iterator();
    out.append("Parameter ");
    while(it.hasNext()) {
      String symbolName = (String) it.next();
      out.append(genZSymbol( tom.engine.adt.zenon.types.zsymbol.zsymbol.make(symbolName) ) +" ");
    }
    out.append(": S.\n");


    // Generates the axioms for coq
    ZAxiomList axiomsDef = tomiltools.symbolsDefinition(symbols);
    ZAxiomList axiomsSub = tomiltools.subtermsDefinition(symbols);
    ZAxiomList axioms = tom_append_list_zby(axiomsDef,tom_append_list_zby(axiomsSub, tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ));
    while (!axioms.isEmptyzby()) {
      out.append(genZAxiom(axioms.getHeadzby()));
      axioms = axioms.getTailzby();
    }

    // Generates the different proof obligations
    int number=1;
    it = collection.iterator();
    while (it.hasNext()) {
      out.append("\n%%begin-auto-proof\n");
      //out.append("%%location: []\n");
      out.append("%%name: theorem"+number+"\n");
      //out.append("%%syntax: tom\n");
      //out.append("%%statement\n");
      out.append(genZSpec((ZSpec)it.next()));

      // XXX: Outputs the axiom for True (Newer versions of zenon may remove this need)
      out.append("Parameter true_is_true : True.\n");

      // Generates types for symbol functions
      // (otherwise, zenon can not know T is not empty)
      // also adds a Parameter fake : T. to make sure zenon knows T is
      // not empty
      Iterator symbIt = symbols.iterator();
      while(symbIt.hasNext()) {
        String symbolName = (String) symbIt.next();
        out.append(genFunctionSymbolDeclaration(symbolName));
      }
      out.append("Parameter tom_fake : T.\n");
    
      out.append("%%end-auto-proof\n");
      number++;
    }
    return out.toString();
  }
}
