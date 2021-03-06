package lemu;

import lemu.sequents.*;
import lemu.sequents.types.*;
import tom.library.sl.*;

import java.util.*;

public class ProofExpander {
  %include { sequents/sequents.tom }
  %include { sl.tom }

  static class Proposition {
    public Prop prop = null;
    public Position pos = null;
  }

  %typeterm Proposition {
    implement { Proposition }
    is_sort(t) { t instanceof Proposition }
  }

  %strategy ApplyWeakL(active:Prop) extends Fail() {
    visit Tree {
      rule[c=seq] -> {
        %match(seq, Prop active) {
          sequent(context(X*,act,Y*),g), act -> {
            Tree t1 = ProofBuilder.createOpenLeaf(`sequent(context(X*,Y*),g));
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
            Tree t1 = ProofBuilder.createOpenLeaf(`sequent(d,context(X*,Y*)));
            return `rule(weakRightInfo(),premisses(t1),seq,act);
          }
        }
      }
    }
  }

  %strategy Plug(t: Tree) extends Fail() {
    visit Tree {
      r@rule[type=openInfo(),c=currentconcl] -> {
        %match(t) {
          rule[c=wantedconcl] -> {
            %match(currentconcl,wantedconcl) {
              sequent(context(_*,p,_*),_), sequent(!context(_*,p,_*),_) -> {
                return (Tree) `ApplyWeakL(p).visit(`r);
              }
              sequent(_,context(_*,p,_*)), sequent(_,!context(_*,p,_*)) -> {
                return (Tree) `ApplyWeakR(p).visit(`r);
              }
              x,x -> {
                return `t; 
              }
              _,_ -> {
                return `t;
              }
            }
          }
        }
      }
    }
  } 

  %strategy Debug() extends Identity() {
    visit Tree {
      _ -> { System.out.println(getEnvironment()); }
    }
  }
  
  %op Strategy PlugTree(t: Tree) {
    make(t) { `InnermostId(Sequence(Plug(t))) }
  }

  %strategy ExpandLocally(p: Proposition) extends Fail() {
    visit Tree {
      rule(customRuleInfo[expanded=tree@rule[c=sequent(context(prop,_*),_)]],prems,_,_) -> {
        p.prop = `prop;
        p.pos = getEnvironment().getPosition();
        // plug premises into open leaves of the expanded tree
        Tree result = `tree;
        %match(Premisses prems) {
          premisses(_*,pr,_*) -> {
            Proposition axiom = new Proposition();
            axiom.prop = `prop;
            `pr = (Tree) `SafeAddInGamma(axiom).visit(`pr);
            result = (Tree) `OnceTopDown(PlugTree(pr)).visit(result);
          }
        }
        return result;
      }
    }
  }

  // do not go into custom rules description and under position
  %strategy AddInGamma(p: Proposition) extends Fail() {
    visit Tree {
      t -> { if(getEnvironment().getPosition().equals(p.pos)) return `t; }
    }
    visit RuleType {
      x@customRuleInfo[] -> { return `x; }
    }
    visit Sequent {
      sequent(g,d) -> {
        return `sequent(context(p.prop,g*),d);
      }
    }
  }

  %op Strategy SafeAddInGamma(p: Proposition) {
    make(p) { `mu(MuVar("x"),Choice(AddInGamma(p),All(MuVar("x")))) }
  }

  // translates LK+ tree into LK tree
  public static Tree expand(Tree tree) {
    Proposition axiom = new Proposition();
    Strategy expand = `Repeat(Sequence(OnceTopDown(ExpandLocally(axiom)),SafeAddInGamma(axiom)));
    try { return (Tree) expand.visit(tree); }
    catch (VisitFailure e) { e.printStackTrace(); throw new RuntimeException(); }
  }
}
