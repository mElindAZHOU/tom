package lemu2;

import tom.library.sl.*;

import lemu2.kernel.proofterms.types.*;
import lemu2.kernel.proofterms.types.namelist.nameList;
import lemu2.kernel.proofterms.types.conamelist.conameList;
import lemu2.kernel.typecheckers.*;
import lemu2.kernel.converters.*;
import lemu2.kernel.evaluation.*;
import lemu2.kernel.*;
import lemu2.util.*;

import org.antlr.runtime.*;
import java.util.Collection;

import fj.data.List;

public class Main {

  %include { kernel/proofterms/proofterms.tom } 
  %include { sl.tom } 

  private static String 
    prettyNormalForms(Collection<ProofTerm> c,TermRewriteRules tr, PropRewriteRules pr, PropRewriteRules pfr) {
    String res = "";
    for (ProofTerm pt: c) {
      res += "- " + Pretty.pretty(pt.export());
      res += "\n\n";
      res += "  typechecks LKFM          : " + TypeChecker.typecheck(pt,tr,pr,pfr);
      res += "\n";
      res += "  typechecks LKM1          : " + LKM1TypeChecker.typecheck(pt,pr);
      res += "\n";
      res += "  typechecks LKM1 eta-long : " + LKM1EtaLongTypeChecker.typecheck(pt,pr);
      res += "\n";
      res += "  typechecks LKF           : " + LKFTypeChecker.typecheck(pt,pfr);
      res += "\n";
    }
    return res;
  }

  public static void main(String[] args) {
    try{
      LemuLexer lexer = new LemuLexer(new ANTLRInputStream(System.in));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      LemuParser parser = new LemuParser(tokens);
      TermRewriteRules rrules = parser.termmodulo().convert();
      //System.out.println(Pretty.pretty(rrules.export()));
      PropRewriteRules prules = parser.propmodulo().convert();
      //System.out.println(Pretty.pretty(prules.export()));
      PropRewriteRules pfrules = parser.propfold().convert();
      //System.out.println(Pretty.pretty(pfrules.export()));
      //PropRewriteRules psrules = parser.propsuper().convert();
      //System.out.println(Pretty.pretty(psrules.export()));
      ProofTerm pt = parser.proofterm().convert();
      
      
      //System.out.println(Pretty.toDoc(pt.export()));
      //System.exit(0);
      
      System.out.println(Pretty.pretty(pt.export()));
      //System.out.println(Pretty.lpretty(pt.export()));
      System.out.println();
			//Latex.display(pt.export());
      Pretty.setChurchStyle(false);
      //LTerm lmupt = LKtoLambdaMu.convert(pt);
      //System.out.println("lambda-mu conversion : " + Pretty.pretty(lmupt.export()) + " : " + Pretty.pretty(LambdaMuTypeChecker.typeof(lmupt).export()));
      //System.out.println("to coq               : " + Pretty.pretty(LambdaMutoCoq.convert(lmupt).export()));
      //LTerm lmupt_eta = LambdaMu.mueta(lmupt);
      //System.out.println("after eta-reduction  : " + Pretty.pretty(lmupt_eta.export()) + " : " + Pretty.pretty(LambdaMuTypeChecker.typeof(lmupt_eta).export()));
      //LTerm lmupt_norm = LambdaMu.norm(lmupt);
      //System.out.println("after normalisation  : " + Pretty.pretty(lmupt_norm.export()) + " : " + Pretty.pretty(LambdaMuTypeChecker.typeof(lmupt_norm).export()));
      //LTerm lmupt_norm_eta = LambdaMu.mueta(lmupt_norm);
      //System.out.println("after norm+eta       : " + Pretty.pretty(lmupt_norm_eta.export()) + " : " + Pretty.pretty(LambdaMuTypeChecker.typeof(lmupt_norm_eta).export()));
      //System.out.println("to coq               : " + Pretty.pretty(LambdaMutoCoq.convert(LambdaMu.mueta(LambdaMu.norm(lmupt))).export()));
      Pretty.setChurchStyle(true);
      System.out.println();
      System.out.println("typechecks LKFM          : " + TypeChecker.typecheck(pt,rrules,prules,pfrules));
      System.out.println("typechecks LKM1          : " + LKM1TypeChecker.typecheck(pt,prules));
      System.out.println("typechecks LKM1 eta-long : " + LKM1EtaLongTypeChecker.typecheck(pt,prules));
      System.out.println("typechecks LKF           : " + LKFTypeChecker.typecheck(pt,pfrules));
      System.out.println();
      PropRewriteRules rulesunion = `proprrules(prules*,pfrules*);



      ProofTerm pt_eta = LKMtoLKF.convert(pt,rulesunion);
      System.out.println("conversion: " + Pretty.pretty(pt_eta.export()));
			//Latex.display(pt_eta.export());
      //System.out.println("conversion typechecks in LKFM  : " + TypeChecker.typecheck(pt_eta,rrules,`proprrules(),rulesunion));
      //System.out.println("conversion typechecks in LKF  : " + LKFTypeChecker.typecheck(pt_eta,rulesunion));
			//System.out.println(Pretty.pretty(LKFtoLK.convert(pt_eta,prules)));


      ProofTerm pure = LKFtoLK.convert(pt_eta,rulesunion);
      System.out.println("pure LK typechecks          : " + TypeChecker.`typecheck(pure,termrrules(),proprrules(),proprrules()));
			//Latex.display(pure.export());
      LTerm pure_lmu = LKtoLambdaMu.convert(pure);
      LTerm pure_lmu_eta = LambdaMu.mueta(pure_lmu);
      System.out.println("before eta-reduction : " + Pretty.pretty(pure_lmu.export()));
      System.out.println("after eta-reduction  : " + Pretty.pretty(pure_lmu_eta.export()));
      System.out.println(" : " + Pretty.pretty(LambdaMuTypeChecker.typeof(pure_lmu_eta).export()));
      System.out.println("to coq               : " + Pretty.pretty(LambdaMutoCoq.convert(pure_lmu_eta).export()));


      // lambda-bar mu mu~ reduction of the translation
      /*
      LMMTerm pure_lmm = LKFtoLambdaBarMuMuTF.convert(pt);
      System.out.println("before reduction : " + Pretty.pretty(pure_lmm.export()));
      System.out.println("reducts : ");
      Iterable<LMMTerm> red = LambdaBarMuMuT.eta(LambdaBarMuMuT.eval(pure_lmm));
      List<LMMTerm> buf = List.<LMMTerm>nil();
      for (LMMTerm t: red) {
        if (!buf.toCollection().contains(t)) {
          System.out.println("- " + Pretty.pretty(t.export()));
          buf = buf.cons(t);
        }
      }
      System.out.println("(" + buf.length() + " normal forms)");
      */

      /* urban reduction */
      System.out.println();
      System.out.println("normal forms :");
      System.out.println(prettyNormalForms(Urban.reduce(pt),rrules,prules,pfrules));
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}

