package lemu;

import lemu.sequents.*;
import lemu.sequents.types.*;
import lemu.sequents.types.proprulelist.*;

import lemu.urban.*;
import lemu.urban.types.*;

import tom.library.sl.*;

import java.util.WeakHashMap;
import java.util.Vector;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Observable;
import java.util.Collection;

import java.io.*;
import antlr.*;
import antlr.collections.*;

public class ProofBuilder extends Observable {

  %include { sequents/_sequents.tom }
  %include { urban/urban.tom }
  %include { sl.tom }
  %include { string.tom }
  %include { util/LinkedList.tom }

  // methode remplacant System.out.println
  private void writeToOutputln (String text) {
    writeToOutput(text+"\n");
  }

  // methode remplacant System.out.print
  private void writeToOutput (String text) {
    if (countObservers() != 0) {
      setChanged();
      notifyObservers(text);
    }
    else System.out.print(text);
  }

  %strategy AddInContexts(ctxt:Context) extends `Identity() {
    visit Sequent {
      sequent(hyp,concl) -> { return `sequent(context(hyp*,ctxt*),concl);}
    }
  }

  %strategy PutInConclusion(ctxt:Context) extends `Identity() {
    visit Sequent {
      sequent(hyp,concl) -> { return `sequent(hyp,context(concl*,ctxt*));}
    }
  }

  %typeterm TermMap { implement { Map<Term,Term> } }

  %typeterm Substitution { implement { HashMap<String,Term> } }


  %typeterm ProofBuilder { implement { ProofBuilder } }


  %strategy ReplaceTree(t: Tree) extends Fail() {
    visit Tree {
      _ -> { return t; }
    }
  }

  public static class InfoNarrow {
    private Position pos;
    private sequentsAbstractType rule;
    private HashMap<String,Term> tds;
    private int numRule;

    private static int ruleIndex = 0;

    public InfoNarrow(HashMap<String, Term> tds) {
      this.tds = tds;
    }

    public InfoNarrow(Position pos, TermRule rule,
        HashMap<String, Term> tds) {
      this.pos = pos;
      this.rule = rule;
      this.numRule = ruleIndex++;
      this.tds = tds;
    }

    public InfoNarrow(Position pos, PropRule rule,
		      HashMap<String, Term> tds) {
      this.pos = pos;
      this.rule = rule;
      this.numRule = ruleIndex++;
      this.tds = tds;
    }

    public static void initNumRules() { ruleIndex = 0; }

    public Position getPos() { return pos; }
    public sequentsAbstractType getRule() { return rule; }
    public int getNumRule() { return numRule; }
    public HashMap<String,Term> getTds() { return tds; }

    public void setTds(HashMap<String,Term> t) { tds = t; }

    public boolean equals(Object o) {
      if (o == this) return true;
      else if (o instanceof InfoNarrow) {
	InfoNarrow i = (InfoNarrow) o;
	return i.getPos().equals(getPos()) &&
	  i.getRule().equals(getRule());
      }
      else return false;
    }
  }

  %typeterm InfoNarrow { implement { InfoNarrow } }
  %typeterm InfoNarrowVector { implement { java.util.Vector<InfoNarrow> } }

  %strategy CollectNarrow(l: InfoNarrowVector, tl:TermRuleList, pl:PropRuleList)
    extends `Identity() {
    visit Term {
      t@!Var[] -> {
        %match (TermRuleList tl) {
          termrulelist(_*, tr@termrule[], _*) -> {
            TermRule tr = (TermRule) Utils.refresh(`tr, `t.getVars());
            HashMap<String,Term> tds = Unification.unify(`t, tr.getlhs());
            if (tds != null) {
              InfoNarrow in = new InfoNarrow(getEnvironment().getPosition(),
                  tr, tds);
              l.add(in);
            };
          }
        }
      }
    }

    visit Prop {
      forall[] -> { throw new VisitFailure("do not collect under quantifier"); }

      exists[] -> { throw new VisitFailure("do not collect under quantifier"); }

      t -> {
        %match (PropRuleList pl) {
          proprulelist(_*, tr@proprule[], _*) -> {
            PropRule tr = (PropRule) Utils.refresh(`tr, `t.getFreeVars());
            HashMap<String,Term> tds = Unification.unify(`t, tr.getlhs());
            if (tds != null) {
              InfoNarrow in = new InfoNarrow(getEnvironment().getPosition(),
                  tr, tds);
              l.add(in);
            };
          }
        }
      }
    }
  }

  public static Tree applyNarrow(InfoNarrow i, Tree t) throws VisitFailure{
    sequentsAbstractType rule = i.getRule();
    %match (TermRule rule) {
      termrule(_,rhs) -> {
	t = (Tree) i.getPos().getReplace(`rhs).visit(t);
	return (Tree) Unification.substitute(i.getTds(), t);
      }
    }
    %match (PropRule rule) {
      proprule(_,rhs) -> {
	t = (Tree) i.getPos().getReplace(`rhs).visit(t);
	return (Tree) Unification.substitute(i.getTds(), t);
      }
    }
    return t;
  }


  %strategy ApplyRule(rule: Rule, active: Prop, args: TermMap) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        SeqList res = rule.getprem();
        Prop conclusion = rule.getconcl();
        Tree expanded = rule.gettree();

        // recuperage de la table des symboles
        HashMap<String,Term> tds = Unification.match(conclusion, active);
        if (tds == null)  throw new VisitFailure("active formula and rule conclusion don't match");

        /*
        //  -- building the original axiom with quantifiers --

        // building p => phi /\ phi => p
        Prop conj = null;
        if (rule.geths() == 1) { // right rule
          %match (expanded) {
            rule[c=sequent(context(),context(phi))] -> {
              conj = `and(implies(conclusion,phi),implies(phi,conclusion));
            }
          }
        } else {
          %match (expanded) {
            rule[c=sequent(context(phi),context())] -> {
              conj = `and(implies(conclusion,phi),implies(phi,conclusion));
            }
          }
        }

        // adding quantifiers
        Prop newconcl = conj;
        Set<String> vars = tds.keySet();
        for (String var: vars) {
          newconcl = `forall(var,newconcl);
        }

        // --
        */

        // renommage des variables
        Set<Map.Entry<String,Term>> entries= tds.entrySet();
        for (Map.Entry<String,Term> ent: entries) {
          Term old_term = `Var(ent.getKey());
          Term new_term = ent.getValue();
          res = (SeqList) Utils.replaceFreeVars(res, old_term, new_term);
          // also replacing in the expanded tree
          expanded = (Tree) Utils.replaceFreeVars(expanded, old_term, new_term);
        }

        // creation des variables fraiches (forall right et exists left)
        Set<Term> fresh = Utils.getSideConstraints(rule.getprem());
        for (Term fvar : fresh) {
          String bname = fvar.getbase_name();
          Term new_var = Utils.freshEigenVar(bname, `seq);
          res = (SeqList) Utils.replaceTerm(res,fvar,new_var);
          // also replacing in the expanded tree
          expanded = (Tree) Utils.replaceFreeVars(expanded, fvar, new_var);
        }

        // remplacement des nouvelles variables (forall left et exists right)
        Set<Term> new_vars = Utils.getNewVars(rule.getprem());
        if (new_vars.size() != args.size())
          throw new VisitFailure("Wrong variables number");
        Set<Map.Entry<Term,Term>> entries2 = args.entrySet();
        for (Map.Entry<Term,Term> ent: entries2) {
          Term old_term = ent.getKey();
          if (! new_vars.contains(old_term))
            throw new VisitFailure("Variable " + old_term.getname() +" not present in the rule");
          Term new_term = ent.getValue();
          res = (SeqList) Utils.replaceFreeVars(res, old_term, new_term);
          // also replacing in the expanded tree
          expanded = (Tree) Utils.replaceFreeVars(expanded, old_term, new_term);
        }

        // ajout des contextes dans les premisses
b: {
        %match (rule, seq, Prop active) {

          // si c'est une regle gauche
          ruledesc(0,_,_,_), sequent(ctxt@context(u*,act,v*),c), act -> {
            // also in expanded tree
            /*
            expanded = (Tree) `TopDown(AddInContexts(ctxt)).fire(expanded);
            expanded = (Tree) `TopDown(PutInConclusion(c)).fire(expanded);
            */
            Context gamma = args.size() <= 0 ? `context(u*,v*) : `ctxt;
            //Context gamma = `context(u*,v*);
            try {
              res = (SeqList) `TopDown(AddInContexts(gamma)).visit(res);
              res = (SeqList) `TopDown(PutInConclusion(c)).visit(res);
            } catch(VisitFailure e) { e.printStackTrace(); throw new RuntimeException(); }
            break b;
          }

          // si c'est une regle droite
          ruledesc(1,_,_,_), sequent(ctxt,c@context(u*,act,v*)), act -> {
            // also in expanded tree
            /*
            expanded = (Tree) `TopDown(AddInContexts(ctxt)).fire(expanded);
            expanded = (Tree) `TopDown(PutInConclusion(c)).fire(expanded);
            */
            Context delta = args.size() <= 0 ? `context(u*,v*) : `c;
            //Context delta = `context(u*,v*);
            try {
              res = (SeqList) `TopDown(AddInContexts(ctxt)).visit(res);
              res = (SeqList) `TopDown(PutInConclusion(delta)).visit(res);
            } catch(VisitFailure e) { e.printStackTrace(); throw new RuntimeException(); }
            break b;
          }

          // probleme
          _,_,_ -> { throw new VisitFailure("wrong hand side rule application");  }
        }
   }

        // closing the expanding tree with the axiom
        //expanded = (Tree) ((MuStrategy) next.getOmega(`ApplyAxiom())).apply(expanded);

        // creating open leaves
        Premisses newprems = `premisses();
        %match(SeqList res) {
          concSeq(_*,x,_*) -> {
            newprems = `premisses(newprems*,createOpenLeaf(x));
          }
        }

        //try { PrettyPrinter.display(expanded); } catch (Exception e) {}

        return `rule(customRuleInfo("{\\sc super}",expanded),newprems,seq,active);
      }
    }
  }

    %strategy NarrowApplyRule(rule: Rule, active: Prop, args: TermMap, tds: Substitution) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        SeqList res = rule.getprem();
        Tree expanded = rule.gettree();

        // recuperage de la table des symboles
        if (tds == null)  throw new VisitFailure("active formula and rule conclusion don't match");

        // renommage des variables
        Set<Map.Entry<String,Term>> entries= tds.entrySet();
        for (Map.Entry<String,Term> ent: entries) {
          Term old_term = `Var(ent.getKey());
          Term new_term = ent.getValue();
          res = (SeqList) Utils.replaceFreeVars(res, old_term, new_term);
          // also replacing in the expanded tree
          expanded = (Tree) Utils.replaceFreeVars(expanded, old_term, new_term);
        }

        // creation des variables fraiches (forall right et exists left)
        Set<Term> fresh = Utils.getSideConstraints(rule.getprem());
        for (Term fvar : fresh) {
          String bname = fvar.getbase_name();
          Term new_var = Utils.freshEigenVar(bname, `seq);
          res = (SeqList) Utils.replaceTerm(res,fvar,new_var);
          // also replacing in the expanded tree
          expanded = (Tree) Utils.replaceFreeVars(expanded, fvar, new_var);
        }

        // remplacement des nouvelles variables (forall left et exists right)
        Set<Term> new_vars = Utils.getNewVars(rule.getprem());
        if (new_vars.size() != args.size())
          throw new VisitFailure("Wrong variables number");
        Set<Map.Entry<Term,Term>> entries2 = args.entrySet();
        for (Map.Entry<Term,Term> ent: entries2) {
          Term old_term = ent.getKey();
          if (! new_vars.contains(old_term))
            throw new VisitFailure("Variable " + old_term.getname() +" not present in the rule");
          Term new_term = ent.getValue();
          res = (SeqList) Utils.replaceFreeVars(res, old_term, new_term);
          // also replacing in the expanded tree
          expanded = (Tree) Utils.replaceFreeVars(expanded, old_term, new_term);
        }

        // ajout des contextes dans les premisses
b: {
        %match (rule, seq, Prop active) {

          // si c'est une regle gauche
          ruledesc(0,_,_,_), sequent(ctxt@context(u*,act,v*),c), act -> {
            // also in expanded tree
            /*
            expanded = (Tree) `TopDown(AddInContexts(ctxt)).fire(expanded);
            expanded = (Tree) `TopDown(PutInConclusion(c)).fire(expanded);
            */
            Context gamma = args.size() <= 0 ? `context(u*,v*) : `ctxt;
            //Context gamma = `context(u*,v*);
            try {
              res = (SeqList) `TopDown(AddInContexts(gamma)).visit(res);
              res = (SeqList) `TopDown(PutInConclusion(c)).visit(res);
            } catch(VisitFailure e) { e.printStackTrace(); throw new RuntimeException(); }
            break b;
          }

          // si c'est une regle droite
          ruledesc(1,_,_,_), sequent(ctxt,c@context(u*,act,v*)), act -> {
            // also in expanded tree
            /*
            expanded = (Tree) `TopDown(AddInContexts(ctxt)).fire(expanded);
            expanded = (Tree) `TopDown(PutInConclusion(c)).fire(expanded);
            */
            Context delta = args.size() <= 0 ? `context(u*,v*) : `c;
            //Context delta = `context(u*,v*);
            try {
              res = (SeqList) `TopDown(AddInContexts(ctxt)).visit(res);
              res = (SeqList) `TopDown(PutInConclusion(delta)).visit(res);
            } catch(VisitFailure e) { e.printStackTrace(); throw new RuntimeException(); }
            break b;
          }

          // probleme
          _,_,_ -> { throw new VisitFailure("wrong hand side rule application");  }
        }
   }

        // creating open leaves
        Premisses newprems = `premisses();
        %match(SeqList res) {
          concSeq(_*,x,_*) -> {
            newprems = `premisses(newprems*,createOpenLeaf(x));
          }
        }

        return `rule(customRuleInfo("{\\sc super}",expanded),newprems,seq,active);
      }
    }
  }

  %strategy ApplyFoldR(rulelist: PropRuleList, active: Prop) extends Fail() {
    visit Tree {
     rule[c=seq@sequent(left,context(C1*,act,C2*))] -> {
        if (`act==active) {
          int i = 0;
          PropRule rule = null;
          for (PropRule r: (proprulelist) rulelist) {
            HashMap<String,Term> tds = Unification.match(r.getlhs(), active);
            if (tds != null) rule=r;
            i++;
          }

          Prop newprop = Unification.reduceProp(active,rule);
          Tree prem = createOpenLeaf(`sequent(left,context(C1*,newprop,C2*)));
          return `rule(foldRightInfo(i,newprop),premisses(prem),seq,active);
        }
      }
    }
  }

  %strategy ApplyFoldL(rulelist: PropRuleList, active: Prop) extends Fail() {
    visit Tree {
     rule[c=seq@sequent(context(C1*,act,C2*),right)] -> {
        if (`act==active) {
          int i = 0;
          PropRule rule = null;
          for (PropRule r: (proprulelist) rulelist) {
            HashMap<String,Term> tds = Unification.match(r.getlhs(), active);
            if (tds != null) rule=r;
            i++;
          }

          Prop newprop = Unification.reduceProp(active,rule);
          Tree prem = createOpenLeaf(`sequent(context(C1*,newprop,C2*),right));
          return `rule(foldLeftInfo(i,newprop),premisses(prem),seq,active);
        }
      }
    }
  }

  %strategy ApplyAssume(n:String,active:Prop) extends Fail() {
    visit Tree {
      rule[c=concl] -> {
        return `rule(metaVariableInfo(mvar(n)),premisses(),concl,active);
      }
    }
  }



  /**
   * classical rules
   **/

  public static Tree createOpenLeaf(Sequent concl) {
    return `rule(openInfo(),premisses(),concl,nullProp());
  }

  %strategy ApplyUnifAxiom(in: InfoNarrow) extends Fail() {
    visit Tree {
      rule[c=seq@sequent(context(_*,act1,_*),context(_*,act2,_*))] ->
      {
        HashMap<String,Term> tds = Unification.unify(`act1, `act2);
        if (tds != null) {
          in.setTds(tds);
          return `rule(axiomInfo(),premisses(),seq,act1);
        }
      }
    }
  }

  %strategy ApplySubst(in: InfoNarrow) extends Identity() {
    visit Tree {
      t -> { return (Tree) Unification.substitute(in.getTds(), `t); }
    }
  }

  // should do better
  %strategy ToRoot(s: Strategy) extends Identity() {
    visit Object {
      _ -> {
        while (getEnvironment().depth() > 1)
          getEnvironment().up();
        s.visit(getEnvironment());
      }
    }
  }

  %strategy ApplyAxiom() extends Fail() {
    visit Tree {
      rule[c=seq@sequent(context(_*,act,_*),context(_*,act,_*))] ->
        rule(axiomInfo(),premisses(),seq,act)
    }
  }

  %strategy ApplyImpliesR(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(d,context(X*,act@implies(p1,p2),Y*)), act -> {
            Tree t1 = createOpenLeaf(`sequent(context(d*,p1),context(X*,p2,Y*)));
            return `rule(impliesRightInfo(),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyImpliesL(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(X*,act@implies(p1,p2),Y*),g), act -> {
            Tree t1 = createOpenLeaf(`sequent(context(X*,Y*),context(p1,g*)));
            Tree t2 = createOpenLeaf(`sequent(context(X*,p2,Y*),g));
            return `rule(impliesLeftInfo(),premisses(t1,t2),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyAndR(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(d,context(X*,act@and(p1,p2),Y*)), act -> {
            Tree t1 = createOpenLeaf(`sequent(d,context(X*,p1,Y*)));
            Tree t2 = createOpenLeaf(`sequent(d,context(X*,p2,Y*)));
            return `rule(andRightInfo(),premisses(t1,t2),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyAndL(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(X*,act@and(p1,p2),Y*),g), act -> {
            Tree t1 = createOpenLeaf(`sequent(context(X*,p1,p2,Y*),g));
            return `rule(andLeftInfo(),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyOrR(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(d,context(X*,act@or(p1,p2),Y*)), act -> {
            Tree t1 = createOpenLeaf(`sequent(d,context(X*,p1,p2,Y*)));
            return `rule(orRightInfo(),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyOrL(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(X*,act@or(p1,p2),Y*),g), act -> {
            Tree t1 = createOpenLeaf(`sequent(context(X*,p1,Y*),g));
            Tree t2 = createOpenLeaf(`sequent(context(X*,p2,Y*),g));
            return `rule(orLeftInfo(),premisses(t1,t2),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyContractionL(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(X*,act,Y*),g), act -> {
            Tree t1 = createOpenLeaf(`sequent(context(X*,act,act,Y*),g));
            return `rule(contractionLeftInfo(),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyContractionR(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(d,context(X*,act,Y*)), act -> {
            Tree t1 = createOpenLeaf(`sequent(d,context(X*,act,act,Y*)));
            return `rule(contractionRightInfo(),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyWeakL(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(X*,act,Y*),g), act -> {
            Tree t1 = createOpenLeaf(`sequent(context(X*,Y*),g));
            return `rule(weakLeftInfo(),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyWeakR(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(d,context(X*,act,Y*)), act -> {
            Tree t1 = createOpenLeaf(`sequent(d,context(X*,Y*)));
            return `rule(weakRightInfo(),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyBottom() extends Fail() {
    visit Tree {
      rule[c=seq@sequent(context(_*,act@bottom(),_*),_)] -> {
        return `rule(bottomInfo(),premisses(),seq,act);
      }
    }
  }

  %strategy ApplyTop() extends Fail() {
    visit Tree {
      rule[c=seq@sequent(_,context(_*,act@top(),_*))] -> {
        return `rule(topInfo(),premisses(),seq,act);
      }
    }
  }

 %strategy ApplyCut(prop:Prop) extends Fail() {
    visit Tree {
      rule[c=seq@sequent(l,r)] -> {
        Tree t1 = createOpenLeaf(`sequent(l*,context(r*,prop)));
        Tree t2 = createOpenLeaf(`sequent(context(l*,prop),r));
        return `rule(cutInfo(prop),premisses(t1,t2),seq,nullProp());
      }
    }
  }

  %strategy ApplyForAllR(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(d,context(X*,act@forall(n,p),Y*)), act -> {
            Term nvar = Utils.freshEigenVar(`n,`seq);
            Prop res = (Prop) Utils.replaceFreeVars(`p, `Var(n), nvar);
            Tree t1 = createOpenLeaf(`sequent(d,context(X*,res,Y*)));
            return `rule(forallRightInfo(nvar),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyForAllL(active:Prop, term:Term) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(X*,act@forall(n,p),Y*),g), act -> {
            Prop res = (Prop) Utils.replaceFreeVars(`p, `Var(n), term);
            Tree t1 = createOpenLeaf(`sequent(context(X*,res,Y*),g));
            return `rule(forallLeftInfo(term),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyForAllLInteractive(active:Prop, o:ProofBuilder) extends Fail() {
    visit Tree {
      r@rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(_*,act@forall(n,_),_*),_), act -> {
            o.writeToOutput("instance of " + `n + " > ");
            Term term = null;
            try { term = IO.getTerm(); } catch (Exception e) { throw new VisitFailure(); }
            return (Tree) `ApplyForAllL(active,term).visit(`r);
          }
        }
      }
    }
  }

  %strategy ApplyExistsR(active:Prop, term:Term) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(d,context(X*,act@exists(n,p),Y*)), act -> {
            Prop res = (Prop) Utils.replaceFreeVars(`p, `Var(n), term);
            Tree t1 = createOpenLeaf(`sequent(d,context(X*,res,Y*)));
            return `rule(existsRightInfo(term),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy ApplyExistsRInteractive(active:Prop, o:ProofBuilder) extends Fail() {
    visit Tree {
      r@rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(_,context(_*,act@exists(n,_),_*)), act -> {
            o.writeToOutput("instance of " + `n + " > ");
            Term term = null;
            try { term = IO.getTerm(); } catch (Exception e) { throw new VisitFailure(); }
            return (Tree) `ApplyExistsR(active,term).visit(`r);
          }
        }
      }
    }
  }

  %strategy ApplyExistsL(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(X*,act@exists(n,p),Y*),g), act -> {
            Term nvar = Utils.freshEigenVar(`n,`seq);
            Prop res = (Prop) Utils.replaceFreeVars(`p, `Var(n), nvar);
            Tree t1 = createOpenLeaf(`sequent(context(X*,res,Y*),g));
            return `rule(existsLeftInfo(nvar),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  /* --- User interaction  --- */

  private String prettyGoal(ArrayList<Prop> hyp, ArrayList<Prop> concl, boolean left, int focus) {
    String res = "";
    for (int i=0; i < hyp.size(); i++) {
      res +=  (left && focus == i+1) ? "*" : " ";
      res += "h"+ (i+1) + ":  ";
      res += PrettyPrinter.prettyPrint(hyp.get(i)) + "\n";
    }
    res += "-----------\n";
    for (int i=0; i < concl.size(); i++) {
      res +=  (!left && focus == i+1) ? "*" : " ";
      res += "c"+ (i+1) + ":  ";
      res += PrettyPrinter.prettyPrint(concl.get(i)) + "\n";
    }
    return res;
  }

  private ArrayList<Prop> getHypothesis(Sequent seq) {
    ArrayList<Prop> res = new ArrayList<Prop>();
    Context ctxt = seq.geth();
    %match (Context ctxt) {
      context(_*, p, _*) -> {
        res.add(`p);
      }
    }
    return res;
  }

  private ArrayList<Prop> getConclusions(Sequent seq) {
    ArrayList<Prop> res = new ArrayList<Prop>();
    Context ctxt = seq.getc();
    %match (Context ctxt) {
      context(_*, p, _*) -> {
        res.add(`p);
      }
    }
    return res;
  }

  %strategy getOpenPosition(list:LinkedList) extends `Fail() {
    visit RuleType {
      // do not look into custom rules expanded trees
      rt -> { return `rt; }
    }
    visit Tree {
      r@rule[type=openInfo[]] -> {
        list.add(getEnvironment().getPosition());
        return `r;
      }
    }
  }

  private static void getOpenPositions(Tree tree, LinkedList pl) {
    Strategy s = `mu(MuVar("x"),Choice(getOpenPosition(pl),All(MuVar("x"))));
    try { s.visit(tree); }
    catch(VisitFailure e) { e.printStackTrace(); throw new RuntimeException(); }
  }

  private static void getOpenPositions(Tree tree, Position pos, LinkedList pl) {
    Strategy s = pos.getOmega(`mu(MuVar("x"),Choice(getOpenPosition(pl),All(MuVar("x")))));
    try { s.visit(tree); }
    catch(VisitFailure e) { e.printStackTrace(); throw new RuntimeException(); }
  }

  private Sequent getSequentByPosition(Tree tree, Position pos) {
    Tree res = null;
    try {res = (Tree) pos.getSubterm().visit(tree); }
    // FIME : why not exit ?
    catch (VisitFailure e) { e.printStackTrace(); }
    return res.getc();
  }

  /* ----------- auxiliary functions for buildProofTree ---------------*/

    private Tree ruleCommand(Tree tree, Position pos,
      Prop active, boolean focus_left, int n) throws Exception {
    HashMap<Term,Term> args = new HashMap<Term,Term>();

    if (n == -1) { // trying to apply one unique rule
      for(int i=0; i<newRules.size(); i++) {
        Rule rule = newRules.get(i);
        Prop conclusion = rule.getconcl();
        HashMap<String,Term> tds = Unification.match(conclusion, active);
        if (tds != null && ((focus_left && rule.geths() == 0) || (!focus_left && rule.geths() == 1))) {
          if (n != -1)  throw new Exception("more than one matching rule, give a number"); // more than one rule
          else n = i;
        }
      }
      if (n == -1) throw new Exception("No applicable rule.");
    }

    if (n < newRules.size()) {
      // TODO verify hand side
      Rule rule = newRules.get(n);

      // asking for new vars
      Set<Term> new_vars = Utils.getNewVars(rule.getprem());
      for (Term t : new_vars) {
        String varname = t.getname();
        writeToOutput("new term for variable " + varname + " in rule " + n + " > ");
        Term new_var = IO.getTerm();
        args.put(t, new_var);
      }

      return (Tree) pos.getOmega(`ApplyRule(rule,active,args)).visit(tree);
    }

    else throw new Exception("rule " + n + " doesn't exist.");
  }

    private Tree narrowRuleCommand(Tree tree, Position pos,
      Prop active, boolean focus_left, int n) throws Exception {
    HashMap<Term,Term> args = new HashMap<Term,Term>();
    HashMap<String,Term> tds = null;

    if (n == -1) { // trying to apply one unique rule
      for(int i=0; i<newRules.size(); i++) {
        Rule rule = newRules.get(i);
        rule = (Rule) Utils.refresh(rule, active.getFreeVars());
        Prop conclusion = rule.getconcl();
        tds = Unification.unify(conclusion, active);
        if (tds != null && ((focus_left && rule.geths() == 0) || (!focus_left && rule.geths() == 1))) {
          if (n != -1)  throw new Exception("more than one matching rule, give a number"); // more than one rule
          else n = i;
        }
      }
      if (n == -1) throw new Exception("No applicable rule.");
    }

    if (n < newRules.size()) {
      // TODO verify hand side
      Rule rule = newRules.get(n);

      // asking for new vars
      Set<Term> new_vars = Utils.getNewVars(rule.getprem());
      for (Term t : new_vars) {
        String varname = t.getname();
        writeToOutput("new term for variable " + varname + " in rule " + n + " > ");
        Term new_var = IO.getTerm();
        args.put(t, new_var);
      }

      rule = (Rule) Utils.refresh(rule, active.getFreeVars());
      Prop conclusion = rule.getconcl();
      tds = Unification.unify(conclusion, active);

      tree = (Tree) pos.getOmega(`NarrowApplyRule(rule,active,args,tds)).visit(tree);
      return (Tree) Unification.substitute(tds, tree);
    }

    else throw new Exception("rule " + n + " doesn't exist.");
    }

  // FIXME uses positions ... may be easily broken
  private Tree theoremCommand(Tree tree, Position pos, String name) throws Exception {

    Tree thtree = theorems.get(`name);
    Prop conclusion = null;

    %match(Tree thtree) {
      rule(_,_,sequent(context(),context(prop)),_) -> {
        tree = (Tree) pos.getOmega(`ApplyCut(prop)).visit(tree);
        pos = pos.down(2);
        pos = pos.down(1);
        conclusion = `prop;
      }
    }

    while(true) {
b :{
      Sequent goal = getSequentByPosition(tree, pos);
      %match(Prop conclusion, goal) {
        // sequent of the form " |- concl, concl ..."
        concl, sequent(context(),l@!context(_*,!concl,_*)) -> {
          %match (Context l) {
            // more than one occurence
            context(x,x,_*) -> {
              tree = (Tree) pos.getOmega(`ApplyWeakR(x)).visit(tree);
              pos = pos.down(2);
              pos = pos.down(1);
              break b;
            }
            // one left
            context(_) -> { return (Tree) pos.getReplace(thtree).visit(tree); }
          }
        }
        // weak lhs of sequent
        _, sequent(context(p,_*),_) -> {
          tree = (Tree) pos.getOmega(`ApplyWeakL(p)).visit(tree);
          pos = pos.down(2);
          pos = pos.down(1);
          break b;
        }
        // remove non-conclusion props at the right of the sequent
        tokeep, sequent(context(),context(_*,p@!tokeep,_*)) -> {
            tree = (Tree) pos.getOmega(`ApplyWeakR(p)).visit(tree);
            pos = pos.down(2);
            pos = pos.down(1);
            break b;
        }
      }
      throw new Exception("theorem not proved");
   }
    }
  }

  private Tree reduceCommand(Tree tree, Position pos,
      Prop active, boolean focus_left) throws Exception {
    Sequent goal = getSequentByPosition(tree, pos);
    Sequent s = (Sequent) Unification.reduce(goal,newTermRules,newPropRules);
    Premisses prems = `premisses(rule(openInfo(), premisses(), s, s.getc().getHeadcontext()));

    // get new tree
    Tree newrule = `rule(reductionInfo(), prems, goal, active);
    try { return (Tree) pos.getReplace(newrule).visit(tree); }
    catch(VisitFailure e) { e.printStackTrace(); throw new RuntimeException(); }
  }


  static private WeakHashMap<Rule,Boolean> noNewVars = new WeakHashMap(); // to memoize

  private static Rule applicableInAuto(ArrayList<Rule> newRules, Prop prop, boolean left) {
    Rule res = null;

    for(int i=0; i<newRules.size(); i++) {
      Rule rule = newRules.get(i);
      boolean ok = false;
      if (noNewVars.containsKey(rule)) {
        ok = noNewVars.get(rule);
      } else {
        ok = (Utils.getNewVars(rule).size() == 0);
        noNewVars.put(rule,ok);
      }
      if( ok ) {
        Prop conclusion = rule.getconcl();
        HashMap<String,Term> tds = Unification.match(conclusion, prop);
        if (tds != null && ((left && rule.geths() == 0) || (!left && rule.geths() == 1))) {
          if (res != null)  return null; // more than one rule
          else res = rule;
        }
      }
    }
    return res;
  }

  %typeterm RuleArrayList { implement { ArrayList<Rule> } }

  %strategy ApplyReduce(newTermRules: TermRuleList, newPropRules: PropRuleList) extends Identity() {
    visit Tree {
      rule[c=goal,active=a] -> {
        Sequent s = (Sequent) Unification.reduce(`goal,newTermRules,newPropRules);
        if(s.equals(`goal)) throw new VisitFailure();
        Premisses prems = `premisses(rule(openInfo(), premisses(), s, s.getc().getHeadcontext()));
        // get new tree
        return `rule(reductionInfo(), prems, goal, a);
      }
    }
  }

  %strategy ApplyAuto(newRules: RuleArrayList) extends Fail() {
    visit Tree {
      // right hand side (+ axiom)
      t@rule[c=sequent(_,context(_*,p,_*))] -> {
        Strategy strat;
        Rule r = applicableInAuto(newRules, `p, false);
        HashMap<Term,Term> hm = new HashMap<Term,Term>();
        if(r != null) strat = `ApplyRule(r,p,hm);
        else strat = `Choice(ApplyAxiom(),ApplyTop(),ApplyAndR(p),ApplyOrR(p),ApplyImpliesR(p),ApplyForAllR(p));
        try {
          Tree res = (Tree) strat.visit(`t);
          return res;
        } catch (VisitFailure e) {}
      }

      // left hand side
      t@rule[c=sequent(context(_*,p,_*),_)] -> {
        Strategy strat;
        Rule r = applicableInAuto(newRules, `p, true);
        HashMap<Term,Term> hm = new HashMap<Term,Term>();
        if(r != null) strat = `ApplyRule(r,p,hm);
        else strat = `Choice(ApplyBottom(),ApplyAndL(p),ApplyOrL(p),ApplyImpliesL(p),ApplyExistsL(p));
        try{
          Tree res = (Tree) strat.visit(`t);
          return res;
        } catch (VisitFailure e) {}
      }
    }
  }

/* ---- LAMBDA PI -----*/

  %strategy Brackets(s: Strategy) extends Identity() {
    visit Tree {
      t -> {
        LinkedList<Position> open = new LinkedList<Position>();
        getOpenPositions(`t,open);
        Tree res = `t;
        for(Position p: open) {
          res = (Tree) p.getOmega(s).visit(res);
        }
        return res;
      }
    }
  }

  private static Term isAppliedNestedTo(Term funsymb, Term t) {
    %match(t) {
      funAppl("lappl",concTerm(f,x)) -> {
        if (`f==funsymb) return `x;
        else return isAppliedNestedTo(funsymb,`f);
      }
    }
    return null;
  }


  /*
  private static int indexOf(Prop prop, Context c) {
    int i = 0;
    for(Prop p: (lemu.sequents.types.context.context) c) {
      if (p == prop) return i;
      i++;
    }
    throw new RuntimeException();
  }
  */

    %strategy PTSApply(newRules: RuleArrayList, dest: Prop, active: Prop) extends Fail() {
      visit Tree {
        r@rule[c=seq] -> {
          %match(seq, Prop dest, Prop active) {
            sequent(context(_*,d@relationAppl("in",concTerm(f,_)),_*),
                context(_*,a@relationAppl("in",concTerm(t1@funAppl("lappl",_),_))
                  ,_*)), d, a -> {
              Term appliedTo = `isAppliedNestedTo(f,t1);
              if(appliedTo != null) {

                int n = -1;
                for(int i=0; i<newRules.size(); i++) {
                  Rule rule = newRules.get(i);
                  Prop conclusion = rule.getconcl();
                  HashMap<String,Term> tds = Unification.match(conclusion, dest);
                  if (tds != null &&  rule.geths() == 0) {
                    if (n != -1)  throw new VisitFailure("more than one matching rule"); // more than one rule
                    else n = i;
                  }
                }
                if (n == -1) throw new VisitFailure("No applicable rule.");
                Rule rule = newRules.get(n);
                // setting the new var
                HashMap<Term,Term> args = new HashMap<Term,Term>();
                Set<Term> new_vars = Utils.getNewVars(rule.getprem());
                for (Term t : new_vars) {
                  String varname = t.getname();
                  args.put(t, appliedTo);
                };
                try {
                  Tree res = (Tree) (`ApplyRule(rule,dest,args)).visit(`r);
                  return res;
                } catch (Exception e) {
                  throw new VisitFailure("cannot apply the rule ; "
                      + e.getMessage());}
              }
              else throw new
                VisitFailure(PrettyPrinter.prettyPrint(`f) +
                    " is not applied in " +
                    PrettyPrinter.prettyPrint(`t1));
            }
          }
        }
      }
    }



  %strategy LamdaPiTypeCheck(pistarleft:Rule, cont: Strategy) extends Identity() {
    visit Tree {
      r@rule[type=openInfo(),
             c=sequent(hyps@context(_*,h@relationAppl("in",concTerm(f,_)),_*),
                       context(_*,relationAppl("in",concTerm(a@funAppl("lappl",_),_))))] -> {
               Term appliedTo = `isAppliedNestedTo(f,a);
               if(appliedTo != null) {
                 System.out.println("found " + PrettyPrinter.prettyPrint(`f) +
                     " applied to " + PrettyPrinter.prettyPrint(`appliedTo));

                 // if not already done
                 %match(hyps) {
                   //!context(_*,relationAppl("in",(x,_)),_*) && x << funAppl("lappl",concTerm(f,appliedTo)) -> {
                   context(_*,relationAppl("in",concTerm(x,_)),_*) -> {
                     System.out.println(`x);
                   }
                 }
                   {
                     HashMap<Term,Term> termmap = new HashMap<Term,Term>();
                     termmap.put(`NewVar("z0","z"),appliedTo);
                     return (Tree) `Sequence(ApplyRule(pistarleft,h,termmap),cont).visit(`r);
                   }
                 }
               }
    }
  }

/* ---- LAMBDA PI -----*/

    %op Strategy toPremisses(s: Strategy) {
      make(s) {
        `_rule(Identity(),_premisses(s),Identity(),Identity())
      }
    }

  %op Strategy SafeTopDown(s:Strategy) {
    make(s) {
	`mu(MuVar("y"),Try(Sequence(s,toPremisses(MuVar("y")))))
    }
  }

  private static Strategy AutoReduce(TermRuleList tr, PropRuleList pr, ArrayList<Rule> dr) {
    return `SafeTopDown(Try(Choice(ApplyAuto(dr),ApplyReduce(tr,pr))));
  }

  /* ------------------------------------------------------------------ */

  private class ProofEnv implements Cloneable {
    public Tree tree = null;
    LinkedList<Position> openGoals = null;
    public boolean focus_left = false;  // true if focus on one hypothesis, false if on a conclusion
    public int focus = 1;
    public int currentGoal = 0;

    public Object clone() {
      ProofEnv res = new ProofEnv();
      res.tree = tree;
      res.openGoals = (LinkedList<Position>) openGoals.clone();
      res.focus_left = focus_left;
      res.focus = focus;
      res.currentGoal = currentGoal;
      return res;
    }
  }

  // builds the proof by manipulating a tree
  private Stack buildProofTree(Sequent goal) throws ReInitException {

    // environnement stack to allow the "undo" command and incomplete proof save
    Stack<ProofEnv> envStack = new Stack<ProofEnv>();
    ProofEnv env = new ProofEnv();

    // initialisations
    env.focus_left = false;
    env.focus = 1;
    env.currentGoal = 0;
    env.openGoals = new LinkedList<Position>();
    env.tree = `rule(openInfo(), premisses(), goal, goal.getc().getHeadcontext());
    getOpenPositions(env.tree, env.openGoals);
    envStack.push(env);
    return buildProofTreeFromStack(envStack);

  }

  private Stack<ProofEnv> buildProofTreeFromStack(Stack<ProofEnv> envStack)  throws ReInitException {

    roofEnv env = envStack.pop();
    ProofEnv orig = env;
    Sequent goal = null;

    // main loop
    while(env.openGoals.size() > 0) {
      Tree tree = null;

      // printing open goals
      LinkedList<Position> og = env.openGoals;
      writeToOutputln(og.size() + " open goals : ");
      %match(LinkedList og) {
        concLinkedList(_*,p,_*) -> {
          Position pos = (Position) `p;
          writeToOutputln("\t"+PrettyPrinter.prettyPrint(getSequentByPosition(env.tree, pos)));
        }
      }

      // " + openGoals.size() + ", currentGoal = " + currentGoalgets current goal
      Position currentPos = env.openGoals.get(env.currentGoal);
      goal = getSequentByPosition(env.tree, currentPos);

      // pritty prints the current goal
      ArrayList<Prop> hyp = getHypothesis(goal);
      ArrayList<Prop> concl = getConclusions(goal);
      Prop active = env.focus_left ?  hyp.get(env.focus-1) : concl.get(env.focus-1);  // for conveniance
      writeToOutputln("\n" + prettyGoal(hyp, concl, env.focus_left, env.focus) + "\n");

      writeToOutput("proof> ");
      ProofCommand pcommand;
      try {pcommand = IO.getProofCommand(); }
      catch (Exception e) { writeToOutputln("Unknown command : " + e); continue; }

      /* begin of the big switch */
      %match(ProofCommand pcommand) {

        proofCommand("undo") -> {
          if (envStack.size() > 0) {
            env = envStack.pop();
          }
        }

        proofCommand("next") -> {
          env.currentGoal = (env.currentGoal+1 ) % env.openGoals.size();
        }

        proofCommand("display") -> {
          try { PrettyPrinter.display(env.tree, newTermRules, newPropRules); }
          catch (Exception e) { writeToOutputln("display failed : " + e); }
        }


        focusCommand(arg) -> {
          int n;
          char hs = `arg.charAt(0);
          try { n = Integer.parseInt(`arg.substring(1)); }
          catch (NumberFormatException e) { continue; }

          if (hs == 'h' && n <= hyp.size()) {
            env.focus_left = true;
            env.focus = n;
          }
          else if (hs == 'c' && n <= concl.size()) {
            env.focus_left = false;
            env.focus = n;
          }
        }

        /* ask for possible rules */
        askrulesCommand()-> {
          for(int i=0; i<newRules.size(); i++) {
            Rule rule = newRules.get(i);
	    rule = (Rule) Utils.refresh(rule, active.getFreeVars());
            Prop conclusion = rule.getconcl();
            HashMap<String,Term> utds = Unification.unify(conclusion, active);
            if (utds != null) {
              HashMap<String,Term> tds = Unification.match(conclusion, active);
              int rule_hs = rule.geths();

              // same side condition
              if (((rule_hs==0 && env.focus_left) || (rule_hs==1 && !env.focus_left)))
              {
                writeToOutput("\n- rule " + i);
                if (tds == null) writeToOutput(" (narrow)");
                writeToOutputln(" :\n");
                writeToOutputln(PrettyPrinter.prettyRule(rule));
              }
            }
          } // for
          writeToOutputln("");
        }


        /* applying one of the custom rules */
        ruleCommand(n) -> {
          try {
	      tree = ruleCommand(env.tree, currentPos, active, env.focus_left, `n);
          } catch (Exception e) {
            writeToOutputln("Can't apply custom rule "+ `n + ": " + e.getMessage());
            e.printStackTrace();
          }
        }

        /* applying one of the custom rules with narrowing*/
        narrowRuleCommand(n) -> {
          try {
	      tree = narrowRuleCommand(env.tree, currentPos, active, env.focus_left, `n);
          } catch (Exception e) {
            writeToOutputln("Can't narrow with custom rule "+ `n + ": " + e.getMessage());
            e.printStackTrace();
          }
        }

        /* intro case */
        proofCommand("intro") -> {
          try {
            Strategy strat;

            if (env.focus_left)
              strat = `Choice(ApplyImpliesL(active),
                  ApplyAndL(active),
                  ApplyOrL(active),
                  ApplyForAllLInteractive(active,this),
                  ApplyExistsL(active),ApplyBottom());
            else
              strat = `Choice(ApplyImpliesR(active),
                  ApplyAndR(active),
                  ApplyOrR(active),
                  ApplyForAllR(active),
                  ApplyExistsRInteractive(active, this),
                  ApplyTop());

            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply intro " + e.getMessage());
          }
        }

        /* duplicate case */
        proofCommand("duplicate") -> {
          try {
            Strategy strat;
            if (env.focus_left) strat = `ApplyContractionL(active);
            else strat = `ApplyContractionR(active);
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply duplicate " + e.getMessage());
          }
        }

        /* remove case */
        proofCommand("remove") -> {
          try {
            Strategy strat;
            if (env.focus_left) strat = `ApplyWeakL(active);
            else strat = `ApplyWeakR(active);
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply duplicate " + e.getMessage());
          }
        }

        /* intros case */
        proofCommand("intros") -> {
          try {
            ArrayList<Rule> emptylist = new ArrayList<Rule>();
            Strategy strat = `SafeTopDown(Try(ApplyAuto(emptylist)));
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply intros " + e.getMessage());
            e.printStackTrace();
          }
        }

        /* auto case */
        proofCommand("auto") -> {
          try {
            Strategy strat = `SafeTopDown(Try(ApplyAuto(newRules)));
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply auto : " + e.getMessage());
            e.printStackTrace();
          }
        }

	/* experimental apply in PTS case */
	applyCommand(arg) -> {
	    try {
		int n;
	      char hs = `arg.charAt(0);
	      try { n = Integer.parseInt(`arg.substring(1)); }
	      catch (NumberFormatException e) {
		  throw new VisitFailure("Apply needs a correct hypothesis as argument.");
	      };
	      if (hs == 'c')
		  throw new VisitFailure("Apply works only for hypotheses");
	      else if (hs == 'h' && n <= hyp.size()) {
		  Strategy strat =
		      `Sequence(PTSApply(newRules,hyp.get(n-1),active),
				toPremisses(Choice(Sequence(ApplyReduce(newTermRules, newPropRules),
							    toPremisses(Try(ApplyAxiom()))),Try(ApplyAxiom()))));
		  tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
	      }
	      else throw new VisitFailure("Invalid hypothesis number");
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply apply : " + e.getMessage());
            e.printStackTrace();
          }
        }

       /* experimental autoreduce case */
        proofCommand("autoreduce") -> {
          try {
            Strategy strat = AutoReduce(newTermRules,newPropRules,newRules);
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply autoreduce : " + e + ", " + e.getMessage());
            e.printStackTrace();
          }
        }

        /* experimental typecheck case */
        proofCommand("typecheck") -> {
          try {
            Strategy auto = AutoReduce(newTermRules,newPropRules,newRules);
            Strategy strat = `mu(MuVar("x"),LamdaPiTypeCheck(newRules.get(7),Brackets(Sequence(auto,MuVar("x")))));
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply typecheck : " + e + ", " + e.getMessage());
            e.printStackTrace();
          }
        }


        /* Axiom case */
        proofCommand("axiom") -> {
          try {
            Strategy strat = `ApplyAxiom();
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply rule axiom " + e.getMessage());
          }
        }

        /* Axiom case */
        proofCommand("axiomunif") -> {
          try {
            InfoNarrow in = new InfoNarrow(null);
	    Strategy strat = `Sequence(ApplyUnifAxiom(in),
				       ToRoot(ApplySubst(in)));
	    tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
	    //tree = (Tree) Unification.substitute(in.getTds(), tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply rule axiomunif " + e.getMessage());
          }
        }

        /* reduce with narrowing case */
        proofCommand("narrowreduce") -> {
          try {
	    Vector<InfoNarrow> c = new Vector();
	    InfoNarrow.initNumRules();
            Strategy strat = `mu(MuVar("x"), Try(Sequence(CollectNarrow(c,
							    newTermRules,
							    newPropRules),
						     All(MuVar("x")))));
	    strat = `_rule(Identity(), Identity(), strat, Identity());
	    currentPos.getOmega(strat).visit(env.tree);

	    if (c.size() == 0)
	      { throw new VisitFailure("no possible choices");};
	    InfoNarrow i;
	    if (c.size() == 1) {
	      i = c.firstElement();
	    } else {
	      writeToOutputln("Possible choices:");
	      HashMap h = new HashMap();
	      int n = 0;
	      for (InfoNarrow in: c) {
		h.put(in.getNumRule(), n);
		writeToOutputln( in.getNumRule() + ":");
		writeToOutputln("  " +
				PrettyPrinter.prettyPrint((sequentsAbstractType)in.getPos().getSubterm().visit(env.tree)) +
				" by " + PrettyPrinter.prettyPrint(in.getRule())
				+"\n");
		n++;
	      }
	      n = -1;
	      while (!h.containsKey(n)) {
		writeToOutput("your choice > ");
		n = IO.getInt();
	      }
	      i = (InfoNarrow) c.get(((Integer)h.get(n)).intValue());
	    }
	    tree = applyNarrow(i, env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply narrowing: " + e.getMessage());
          } catch (Exception e) {
	    writeToOutputln("Incorrect input: " + e.getMessage());
	  }

        }

        /* cut case */
        cutCommand(prop) -> {
          try {
            Strategy strat = `ApplyCut(prop);
            tree = (Tree) ((Strategy) currentPos.getOmega(strat)).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply cut rule : " + e.getMessage());
          }
        }

        /* experimental theorem case */
        theoremCommand(name) -> {
          try {
            tree = theoremCommand(env.tree, currentPos, `name);
            //System.out.println(tree);
          } catch(Exception e) {
            writeToOutputln("Can't apply theorem " + `name + " : " + e.getMessage());
          }
        }

        /* experimental fold case */
        foldCommand() -> {
          try {
            Strategy strat =  env.focus_left ?
                                `ApplyFoldL(newPropRules,active)
                              : `ApplyFoldR(newPropRules,active);
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply fold rule : " + e.getMessage());
          }
        }

        /* metavariables */
        assumeCommand(s) -> {
          try {
            Strategy strat = `ApplyAssume(s,active);
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply assume rule : " + e.getMessage());
          }
        }

        /* experimental reduce case */
        normalizeSequent() -> {
          try {
            Strategy strat = `Try(ApplyReduce(newTermRules, newPropRules));
            tree = (Tree) currentPos.getOmega(strat).visit(env.tree);
            //old: tree = reduceCommand(env.tree, currentPos, active, env.focus_left);
          } catch (VisitFailure e) {
            writeToOutputln("Can't apply cut rule : " + e.getMessage());
          }
        }

        /* quit */
        proofquit() -> {
          System.exit(0);
        }

        // reinit
        proofreinit() -> {
          throw new ReInitException();
        }

        /* abort */
        abort() -> {
          envStack.push(env);
          return envStack;
        }

        /* proof end of file */
        proofendoffile() -> {
          writeToOutputln("Warning : The file ended while the theorem was not proved !\nProve it manually or abort.");
          IO.setStream(new DataInputStream(System.in));
          inputStreams.clear();
        }

        /* debug commands */
        proofCommand("debugprop") -> {
          writeToOutputln(active.toString());
        }
        proofCommand("debuggoal") -> {
          writeToOutputln(goal.toString());
        }
        proofCommand("debugtree") -> {
          writeToOutputln(env.tree.toString());
        }

      } /* end of the big command switch */

      if(tree != null && tree != env.tree) {
        // get open positions
        envStack.push(env);
        env = (ProofEnv) env.clone();

        env.tree = tree;
        env.openGoals.remove(currentPos);
        getOpenPositions(env.tree, currentPos, env.openGoals);
        env.currentGoal = env.openGoals.size()-1;

        // reset focus
        env.focus_left = false;
        env.focus = 1;
      }


    } // while still open goals

    envStack.push(env);
    return envStack;
  }

  class ReInitException extends Exception {}

  public void reInit() {
    newRules = new ArrayList<Rule>();
    newTermRules = `termrulelist();
    newPropRules = `proprulelist();
    theorems = new HashMap<String,Tree>();
    unprovedTheorems = new HashMap<String,Stack<ProofEnv>>();
    inputStreams = new Stack<InputStream>();
    pttheorems = new HashMap<String, ProofTerm>();
    writeToOutput("SESSIONRESTARTED"); // mot cle pour dire qu'on a redemarre la session
  }

  private ArrayList<Rule> newRules = new ArrayList<Rule>();
  private TermRuleList newTermRules = `termrulelist();
  private PropRuleList newPropRules = `proprulelist();
  private HashMap<String,Tree> theorems = new HashMap<String,Tree>();
  private HashMap<String,Stack<ProofEnv>> unprovedTheorems = new HashMap<String,Stack<ProofEnv>>();
  private Stack<InputStream> inputStreams = new Stack<InputStream>();
  private HashMap<String,ProofTerm> pttheorems = new HashMap<String, ProofTerm>();

  // called when leaving proof mode
  private void store(Stack<ProofEnv> envStack, String name) {
	  if(envStack.peek().openGoals.size() == 0) {
      Tree tree = envStack.peek().tree;
      theorems.put(name,tree);
      unprovedTheorems.remove(name);
      writeToOutputln(name + " proved.");
      //ted.VisitableViewer.toTreeStdout(tree);
    } else {
      unprovedTheorems.put(name, envStack);
      writeToOutputln(name + " remains unproved.");
    }
  }

  private RuleList cleanRules(Prop atom, Prop p) {
    RuleCalc rc = new RuleCalc(atom,p);
    Prop permut_problem = rc.getProblem();
    int i = 1;
    while((permut_problem = rc.getProblem()) != null) {
      try {
        writeToOutputln("name the proposition \"" +
            PrettyPrinter.prettyPrint(permut_problem) + "\" > ");
        String name = IO.getIdent();
        // ask user for a name
        rc.run(name);
      } catch (Exception e) {
        writeToOutputln("not a valid id : " + e.toString());
      }
    }
    return rc.getResult();
  }

  private void addSuperRule(Prop atom, Prop phi) {
    RuleList rl = `cleanRules(atom,phi);
    %match(RuleList rl) {
      rlist(_*,r,_*) -> {
        `r = (Rule) Unification.substPreTreatment(`r);
        newRules.add(`r);
      }
    }
    writeToOutputln("The new deduction rules are : \n");
    writeToOutputln(PrettyPrinter.prettyRule(rl));
  }

  private void addSuperRule(Tree theorem) {
    try {
      %match(theorem) {
        rule[c=sequent(context(),context(p))] -> {
          PropRule pr = Utils.theoremToPropRewriteRule(`p);
          %match(pr) { proprule(lhs,rhs) -> { `addSuperRule(lhs,rhs); } }
        }
      }
    } catch (Exception e) {
      writeToOutputln("Can't convert theorem: " + e.getMessage());
    }
  }

  private void addPropRule(Prop atom, Prop phi) {
    `atom = (Prop) Unification.substPreTreatment(`atom);
    `phi = (Prop) Unification.substPreTreatment(`phi);
    newPropRules = `proprulelist(newPropRules*,proprule(atom,phi));
  }

  private void addPropRule(Tree theorem) {
    try {
      %match(theorem) {
        rule[c=sequent(context(),context(p))] -> {
          PropRule pr = Utils.theoremToPropRewriteRule(`p);
          %match(pr) { proprule(lhs,rhs) -> { `addPropRule(lhs,rhs); } }
        }
      }
    } catch (Exception e) {
      writeToOutputln("Can't convert theorem :" + e.getMessage());
    }
  }

  private void addTermRule(Term lhs, Term rhs) {
    `lhs = (Term) Unification.substPreTreatment(`lhs);
    `rhs = (Term) Unification.substPreTreatment(`rhs);
    newTermRules = `termrulelist(newTermRules*,termrule(lhs,rhs));
  }

  private void addTermRule(Tree t) {
    try {
      %match(t) {
        rule[c=sequent(context(),context(p))] -> {
          TermRule pr = Utils.theoremToTermRewriteRule(`p);
          %match(pr) { termrule(lhs,rhs) -> { `addTermRule(lhs,rhs); } }
        }
      }
    } catch (Exception e) {
      writeToOutputln("Can't convert theorem :" + e.getMessage());
    }
  }

  public void mainLoop() throws Exception {
    Command command = null;

    while(true) {

      writeToOutput("> ");
      try { command = IO.getCommand(); }
      catch (Exception e) {
        writeToOutputln("Unknow command : " + e);
        continue;
      }

      %match(Command command) {

        /* declarations */

        rewritesuper(p1,p2) -> { `addSuperRule(p1,p2); }

        rewritesuperFromTheorem(th) -> {
          Tree t = theorems.get(`th);
          if (t == null) writeToOutputln(`th + " not found");
          else addSuperRule(t);
        }

        rewriteterm(lhs,rhs) -> { `addTermRule(lhs,rhs); }

        rewritetermFromTheorem(th) -> {
          Tree t = theorems.get(`th);
          if (t == null) writeToOutputln(`th + " not found");
          else addTermRule(t);
        }

        rewriteprop(lhs,rhs) -> { `addPropRule(lhs,rhs); }

        rewritepropFromTheorem(th) -> {
          Tree t = theorems.get(`th);
          if (t == null) writeToOutputln(`th + " not found");
          else addPropRule(t);
        }

        inductive(sig) -> {
          `addSuperRule(Inductive.getLhs(sig),Inductive.getRhs(sig,false));
        }

        inductiver(sig) -> {
          `addSuperRule(Inductive.getLhs(sig),Inductive.getRhs(sig,true));
        }

        /* reduce command */

        normalizeProp(p) -> {
          Prop res = (Prop) Unification.reduce(`p,newTermRules,newPropRules);
          writeToOutputln(PrettyPrinter.prettyPrint(res));
        }

        normalizeTerm(t) -> {
          Term res = (Term) Unification.reduce(`t,newTermRules,newPropRules);
          writeToOutputln(PrettyPrinter.prettyPrint(res));
        }

        /* proof handling */

        proof(name,p) -> {
          try {
            Stack<ProofEnv> envStack = buildProofTree(`sequent(context(),context(p)));
            store(envStack, `name);
          }
          catch (ReInitException e) { reInit(); }
        }

        resume(name) -> {
          Stack<ProofEnv> envStack = unprovedTheorems.get(`name);
          if(envStack==null) { writeToOutputln(`name + " not found"); } else {
            try {
              envStack = buildProofTreeFromStack(envStack);
              store(envStack, `name);
            }
            catch (ReInitException e) {reInit(); }
          }
        }

        proofcheck(name) -> {
          Tree tree = theorems.get(`name);
          if(tree==null) writeToOutputln(`name + " not found"); else {
            tree = ProofExpander.expand(tree);
            if(ProofChecker.proofcheck(tree)) writeToOutputln("Proof check passed !");
            else writeToOutputln("Proof check failed :S");
          }
        }

        /* pretty print */

        display(name) -> {
          Tree tree = theorems.get(`name);
          if(tree==null) writeToOutputln(`name + " not found");
          else PrettyPrinter.display(tree,newTermRules,newPropRules);
          //tree = ProofExpander.expand(tree);
          //PrettyPrinter.display(tree,newTermRules,newPropRules);
        }

        proofterm(name) -> {
          /*
             ProofTerm pi = pttheorems.get(`name);
             if (pi==null) {
             Tree tree = theorems.get(`name);
             if(tree==null) writeToOutputln(`name + " not found");
             else {
             pi = Proofterms.getProofterm(tree);
             pttheorems.put(`name,pi);
             }
             }
             if (pi!=null) {
             writeToOutputln(PrettyPrinter.prettyPrint(pi));
          //writeToOutput("IMG"+PrettyPrinter.show(pi));
          PrettyPrinter.display(pi);
          }*/
          Tree tree = theorems.get(`name);
          if(tree==null) writeToOutputln(`name + " not found");
          else {
            TypableProofTerm tpt = Proofterms.getTypableProofterm(tree);
            ProofTerm pt = Proofterms.typableProofterm2Proofterm(tpt);
            NSequent nseq = Proofterms.typableProofterm2NSequent(tpt);
            writeToOutputln(PrettyPrinter.prettyPrint(pt));
            //PrettyPrinter.display(Proofterms.typeTypableProofterm(tpt));
            Collection c = Proofterms.computeNormalForms(pt, nseq);
            writeToOutputln("Number of normal forms found : "+c.size());

            HashSet set = new HashSet();
            set.addAll(c);
            writeToOutputln("Number of different normal forms : "+set.size());
            set = new HashSet();
            /* modulo alpha
            for (Object o:c) {
              ProofTerm prt = (ProofTerm) o;
              set.add(DBProofterms.translate(prt));
            }
            writeToOutputln("Number of different normal forms modulo alpha : "+set.size());
            for(Object o: set) {
              writeToOutputln(PrettyPrinter.prettyPrint((DBProofTerm) o));
            }
            */

            for (Object o:c) {
              writeToOutputln(PrettyPrinter.prettyPrint((ProofTerm) o));
              //PrettyPrinter.display(Proofterms.typeTypableProofterm(`typablePT((ProofTerm) o, nseq)));
            }
          }
        }

        gibber() -> {
          writeToOutputln("        .=\"=.\n      _/.-.-.\\_\n     ( ( 0 0 ) )\n      |/  \"  \\|\n       \\'---'/\n        `\"\"\"`");
          writeToOutputln("Have you gibbered today ?");
        }

        print(name) -> {
          Tree tree = theorems.get(`name);
          if(tree==null) writeToOutputln(`name + " not found");
          else
            %match(Tree tree) {
              rule[c=concl] -> {
                String str = `name + " : " + PrettyPrinter.prettyPrint(`concl);
                writeToOutputln(str);
              }
            }
        }

        /* file/stream management */

        importfile(name) -> {
          String newname = `name.substring(1, `name.length()-1);
          try {
            InputStream stream = new FileInputStream(newname);
            IO.setStream(stream);
            inputStreams.push(stream);
          }
          catch (Exception FileNotFoundException) {
            writeToOutputln(newname + " : File not found");
          }
        }

        endoffile() -> {
          writeToOutputln("End of file");
          InputStream stream = new DataInputStream(System.in);
          if (! inputStreams.empty()) stream = inputStreams.pop();
          IO.setStream(stream);
        }

        quit() -> {
          System.exit(0);
        }

        reinit() -> {
          reInit();
        }

      }  // %match(Command)
    } // while true
  }

  public final static void main(String[] args) throws Exception {
    ProofBuilder test = new ProofBuilder();
    //test.run();
    test.mainLoop();
  }
}
