/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package definitions;

import java.util.HashSet;

/**
 *
 * @author hubert
 */
class MakeAllStrategy extends Request {

  MakeAllStrategy(int i) {
    super(i);
  }

  MakeAllStrategy() {
    super(0);
  }

  HashSet<Slot> fillATerm(Slot aTerm, int n) {
    HashSet<Slot> res = new HashSet<Slot>();

    //fill the term by choosing one of its constructors
    Slot[] deps = aTerm.chooseConstructor();

    //dispatch fields of the term between two categories: these whose dimension 
    //equals dimension of the term, and the others
    HashSet<Slot> listHigherDim = new HashSet<Slot>();
    for (int i = 0; i < deps.length; i++) {
      Slot dep = deps[i];
      if (dep.getDimension() < aTerm.getDimension()) {
        res.add(dep);
      } else {
        listHigherDim.add(dep);
      }
    }

    //spread number of recursions of the curent term into each fields with the 
    //same dimension
    int[] listSpread = Random.pile(n - 1, listHigherDim.size());

    //re-apply algorithm on same dimension fields in order to eliminate them
    int i = 0;
    for (Slot term : listHigherDim) {
      Request req;
      if (term.getDstToLeaf() < n) {
        req = new MakeAllStrategy(listSpread[i]);
      } else {
        req = new MakeLeafStrategy(listSpread[i]);
      }
      res.addAll(req.fillATerm(term));
      i++;
    }
    return res;
  }

  @Override
  HashSet<Slot> fillATerm(Slot aTerm) {
    HashSet<Slot> res = new HashSet<Slot>();

    //fill the term by choosing one of its constructors
    Slot[] deps = aTerm.chooseConstructor();

    //dispatch fields of the term between two categories: these whose dimension 
    //equals dimension of the term, and the others
    HashSet<Slot> listHigherDim = new HashSet<Slot>();
    for (int i = 0; i < deps.length; i++) {
      Slot dep = deps[i];
      if (dep.getDimension() < aTerm.getDimension()) {
        res.add(dep);
      } else {
        listHigherDim.add(dep);
      }
    }

    //spread number of recursions of the curent term into each fields with the 
    //same dimension
    int[] listSpread = Random.pile(this.getCounter() - 1, listHigherDim.size());

    //re-apply algorithm on same dimension fields in order to eliminate them
    int i = 0;
    for (Slot term : listHigherDim) {
      Request req;
      if (term.getDstToLeaf() < this.getCounter()) {
        req = new MakeAllStrategy(listSpread[i]);
      } else {
        req = new MakeLeafStrategy(listSpread[i]);
      }
      res.addAll(req.fillATerm(term));
      i++;
    }
    return res;
  }
}
