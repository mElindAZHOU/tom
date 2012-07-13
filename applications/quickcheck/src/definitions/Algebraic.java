package definitions;

import java.lang.reflect.Method;
import java.util.*;

/**
 *
 * @author hubert
 */
public class Algebraic implements Typable {

    private String name;
//    private Set<Constructor> constructors;
    private List<Constructor> constructors;
    private Set<Typable> dependences;
    private int dstLeaf;
    private Scope scope;

    public Algebraic(Scope scope, String name) {
        this.name = name;
//    constructors = new HashSet<Constructor>();
        constructors = new ArrayList<Constructor>();
        dependences = new HashSet<Typable>();
        dstLeaf = -1;
        this.scope = scope;
        scope.addType(this);
    }

    @Deprecated
    public Algebraic(Scope scope, Class type) {
        dstLeaf = -1;
        this.scope = scope;
        this.name = type.getName();
//    constructors = new HashSet<Constructor>();
        constructors = new ArrayList<Constructor>();
        dependences = new HashSet<Typable>();
        scope.addType(this);
    }
    
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<Typable> getDependences() {
        return dependences;
    }
    
    @Override
    public int getDimension() {
        int dim = 0;
        int add = 0;
        if (isRec()) {
            add = 1;
        }
        for (Typable typable : dependences) {
            boolean dependsOn = typable.getDependences().contains(this);
            if (!dependsOn) {
                dim = Math.max(dim, typable.getDimension());
            }
        }
        return dim + add;
    }

    @Override
    public int dstToLeaf() {
        System.out.println(getName() + " : " + dstLeaf);
        if (dstLeaf != -1) {
            return dstLeaf;
        }
        int res = Integer.MAX_VALUE;
        for (Constructor constructor : constructors) {
            if (constructor.isLocked()) {
                //this case should not directly happen if dstToLeaf() is called by user:
                //only during recursive call.
                //the returned value is sensless
                return -1;
            }
            res = Math.min(res, constructor.distanceToReachLeaf());
        }
        this.dstLeaf = res;
        System.out.println(getName() + " -> " + dstLeaf);
        return dstLeaf;
    }

    
    @Override
    public Slot generate(int n) {
        // TODO empecher les cas de non terminaison
        Slot res = new Slot(this);
        HashSet<Slot> listHoles = new HashSet<Slot>();
        listHoles.add(res);
        while (!listHoles.isEmpty()) {

            //retrieve set of maximal dimension terms
            int dimMax = 0;
            HashSet<Slot> toVisit = new HashSet<Slot>();
            for (Slot term : listHoles) {
                int d = term.getDimension();
                if (d > dimMax) {
                    dimMax = d;
                    toVisit = new HashSet<Slot>();
                }
                if (d == dimMax) {
                    toVisit.add(term);
                }
            }

            //spread n across maximal dimention terms
            int[] listSpread = Random.pile(n, toVisit.size());

            //fill each maximal dimension term
            int i = 0;
            for (Slot term : toVisit) {
                Request req;
                if (term.getDstToLeaf() < listSpread[i]) {
                    req = new MakeAllStrategy(listSpread[i]);
                } else {
                    req = new MakeLeafStrategy(listSpread[i]);
                }
                listHoles.addAll(req.fillATerm(term));
                i++;
            }

            //remove newly filled terms
            listHoles.removeAll(toVisit);
        }
        return res;
    }

    @Override
    public boolean updateDependences() {
        Set<Typable> depsClone = new HashSet<Typable>(dependences);
        for (Typable deps : depsClone) {
            dependences.addAll(deps.getDependences());
        }
        //return true if there were changes
        return dependences.size() != depsClone.size();
    }
    
    
    public Algebraic addConstructor(String name, Typable... listTypes) {
        constructors.add(new Constructor(this, listTypes, name));
        dependences.addAll(Arrays.asList(listTypes));
        return this;
    }

    public Scope getScope() {
        return scope;
    }

    Constructor chooseConstructor() {
        if(constructors.isEmpty()){
                    throw new UnsupportedOperationException("No constructors");
        }
        int choice = (int) (Math.random() * constructors.size());
        return constructors.get(choice);
//        int i = 0;
//        for (Constructor constructor : constructors) {
//            if (i == choice) {
//                return constructor;
//            }
//            i++;
//        }
//        throw new UnsupportedOperationException("ERROR");
    }

    Constructor chooseMinimalConstructor() {
        //TODO improve choice randomly
        for (Constructor constructor : constructors) {
            int m = constructor.distanceToReachLeaf();
            if (m == dstToLeaf()) {
                return constructor;
            }
        }
        throw new UnsupportedOperationException("Internal error happends when backtracking (" + getName() + " : " + dstToLeaf() + ").");
    }


    public boolean isRec() {
        return dependences.contains(this);
    }

    public Slot generate2(int n) {
        Slot res = new Slot(this);
        HashSet<Slot> listHoles = new HashSet<Slot>();
        listHoles.add(res);
        while (!listHoles.isEmpty()) {

            //retrieve set of maximal dimension terms
            int dimMax = 0;
            HashSet<Slot> toVisit = new HashSet<Slot>();
            for (Slot term : listHoles) {
                int d = term.getDimension();
                if (d > dimMax) {
                    dimMax = d;
                    toVisit = new HashSet<Slot>();
                }
                if (d == dimMax) {
                    toVisit.add(term);
                }
            }

            //spread n across maximal dimention terms
            int[] listSpread = Random.pile(n, toVisit.size());

            //fill each maximal dimension term
            int i = 0;
            for (Slot term : toVisit) {
                Request req;
                if (term.getDstToLeaf() < listSpread[i]) {
                    req = new MakeAllStrategy(listSpread[i]);
                } else {
                    req = new MakeLeafStrategy(listSpread[i]);
                }
                listHoles.addAll(req.fillATerm(term));
                i++;
            }

            //remove newly filled terms
            listHoles.removeAll(toVisit);
        }
        return res;
    }

    @Override
    public String toString() {
        String res = this.getName() + " : \n";
        for (Constructor constructor : constructors) {
            res += "\t" + constructor + "\n";
        }
        return res;
    }

    /*
     * =========================== USING META-TYPAGE
     * ============================
     */
    /**
     * This methode only work with Gom pattern classes. Indeed, method make()
     * constructed by using Gom is searched in order to build Constructor
     *
     * @param classe class following Gom pattern definition
     * @return
     */
    @Deprecated
    public Algebraic addConstructor(String name, Class classe) {
        String pattern = "make";
        Method[] listMethods = classe.getDeclaredMethods();
        Method make = null;
        for (int i = 0; i < listMethods.length; i++) {
            Method method = listMethods[i];
            if (method.getName().equals(pattern)) {
                make = method;
                break;
            }
            if (i == listMethods.length - 1) {
                throw new UnsupportedOperationException("Method " + pattern + "() was not found in " + classe);
            }
        }
        Constructor cons = new Constructor(this, make, name);
        constructors.add(cons);
        dependences.addAll(Arrays.asList(cons.getFields()));
        return this;
    }

    @Deprecated
    public Algebraic addConstructor(String name, Class classe, String pattern) {
        Method[] listMethods = classe.getDeclaredMethods();
        Method make = null;
        for (int i = 0; i < listMethods.length; i++) {
            Method method = listMethods[i];
            if (method.getName().equals(pattern)) {
                make = method;
                break;
            }
            if (i == listMethods.length - 1) {
                throw new UnsupportedOperationException("Method " + pattern + "() was not found in " + classe);
            }
        }
        Constructor cons = new Constructor(this, make, name);
        constructors.add(cons);
        dependences.addAll(Arrays.asList(cons.getFields()));
        return this;
    }

}