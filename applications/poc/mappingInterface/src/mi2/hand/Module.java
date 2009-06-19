package mi2.hand;

import base.hand.types.T1;
import base.hand.types.T2;
import base.hand.types.t1.a;
import base.hand.types.t1.f;
import base.hand.types.t2.b;
import base.hand.types.t2.g;
import base.hand.types.t2.h;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author nvintila
 * @date 2:32:06 PM Jun 13, 2009
 */
public class Module {

    public static final Module instance = new Module();

    /**
     * for each constructor f, the interface f_MappingI and the abstract class
     * f_Introspector can be generated by Tom from the Module specification. If
     * the user do not need introspector, he just has to implement f_MappingI.
     */

    public static interface a_MappingI {
        // should be generated automatically
        boolean isSym(Object t);

        a make();
    }

    public static abstract class a_Introspector extends mi2.mapping.IntrospectorMapping<a> implements a_MappingI {
        // should be generated automatically
        public abstract a make();

        public a setChildren(Object o, Object[] children) {
            return make();
        }

        public Object[] getChildren(Object o) {
            return new Object[]{};
        }

        public <T> T setChildAt(T o, int i, Object child) {
            assert false : "Unexpected call.";
            return null;
        }

        public Object getChildAt(Object o, int i) {
            return null;
        }

        public int getChildCount(Object o) {
            return 0;
        }

        public Class forType() {
            return a.class;
        }
    }


    public static class a_Mapping extends a_Introspector implements a_MappingI {
        // written by hand or generated by Gom
        public static a_Mapping instance = new a_Mapping();

        public boolean isSym(Object t) {
            return t instanceof a;
        }

        public a make() {
            return new a();
        }
    }

    /**
     * ------------------------------
     */
    public static interface b_MappingI {
        boolean isSym(Object t);

        b make();
    }

    public static abstract class b_Introspector extends mi2.mapping.IntrospectorMapping<b> implements b_MappingI {
        public abstract b make();

        public b setChildren(Object o, Object[] children) {
            return make();
        }

        public Object[] getChildren(Object o) {
            return new Object[]{};
        }

        public <T> T setChildAt(T o, int i, Object child) {
            assert false : "Unexpected call.";
            return null;
        }

        public Object getChildAt(Object o, int i) {
            return null;
        }

        public int getChildCount(Object o) {
            return 0;
        }

        public Class forType() {
            return b.class;
        }
    }


    public static class b_Mapping extends b_Introspector implements b_MappingI {
        public static b_Mapping instance = new b_Mapping();

        public boolean isSym(Object t) {
            return t instanceof b;
        }

        public b make() {
            return new b();
        }
    }

    /**
     * ------------------------------
     */
    public static interface f_MappingI {
        // Test
        boolean isSym(Object t);

        f make(Object s1, Object s2);

        // Slot getters
        T1 getS1(Object t);

        T2 getS2(Object t);

    }

    public static abstract class f_Introspector extends mi2.mapping.IntrospectorMapping<f> implements f_MappingI {

        // LocalIntrospector
        public f setChildren(Object o, Object[] children) {
            return make(children[0], children[1]);
        }

        public Object[] getChildren(Object o) {
            return new Object[]{((f) o).getS1(), ((f) o).getS2()};
        }

        public Object setChildAt(Object o, int i, Object child) {
            switch (i) {
                case 0:
                    return make(child, ((f) o).getS2());
                case 1:
                    return make(((f) o).getS1(), (T2) child);
                //todo: or ((f)o).setS2((T2)child); ?
            }
            assert false : "Unexpected call.";
            return null;
        }

        public Object getChildAt(Object o, int i) {
            switch (i) {
                case 0:
                    return getS1(o);
                case 1:
                    return getS2(o);
            }
            assert false : "Unexpected call.";
            return null;
        }

        public int getChildCount(Object o) {
            return 2;
        }

        public Class forType() {
            return f.class;
        }

    }


    public static class f_Mapping extends f_Introspector {
        public static f_Mapping instance = new f_Mapping();

        // Test
        public boolean isSym(Object t) {
            return t instanceof f;
        }

        // Make
        public f make(Object s1, Object s2) {
            return new f((T1) s1, (T2) s2);
        }

        public T1 getS1(Object t) {
            return ((f) t).getS1();
        }

        public T2 getS2(Object t) {
            return ((f) t).getS2();
        }

    }


    /**
     * ------------------------------
     */
    public static interface g_MappingI {
        // Test
        boolean isSym(Object t);

        g make(Object s2);

        // Slot getters
        T2 getS2(Object t);

    }

    public static abstract class g_Introspector extends mi2.mapping.IntrospectorMapping<g> implements g_MappingI {
        public g setChildren(Object o, Object[] children) {
            return make(children[0]);
        }

        public Object[] getChildren(Object o) {
            return new Object[]{(((g) o).getS2())};
        }

        public /*<T> T*/ Object setChildAt(/*T*/Object o, int i, Object child) {
            switch (i) {
                case 0:
                    return make(child);
                //todo : or ((g)o).setS2((T2)child); ?
            }
            assert false : "Unexpected call.";
            return null;
        }

        public Object getChildAt(Object o, int i) {
            switch (i) {
                case 0:
                    return getS2(o);
            }
            assert false : "Unexpected call.";
            return null;
        }

        public int getChildCount(Object o) {
            return 1;
        }

        public Class forType() {
            return g.class;
        }
    }

    public static class g_Mapping extends g_Introspector {
        public static g_Mapping instance = new g_Mapping();

        // Test
        public boolean isSym(Object t) {
            return t instanceof g;
        }

        // Make
        public g make(Object s2) {
            return new g((T2) s2);
        }

        public T2 getS2(Object t) {
            return ((g) t).getS2();
        }

    }

    /**
     * ------------------------------
     */
    public static interface h_MappingI {
        // Test
        boolean isSym(Object t);

        h make(List<T1> ts);

        // Slot getters
        List<T1> getTs(Object t);

    }

    public static abstract class h_Introspector extends mi2.mapping.IntrospectorMapping<h> implements h_MappingI {

        public h setChildren(Object o, Object[] children) {
            // todo: this cast might blow..
            return make(Arrays.asList((T1[]) children[0]));
        }

        public Object[] getChildren(Object o) {
            return ((h) o).getTs().toArray();
        }

        public /*<T> T*/ Object setChildAt(/*T*/Object o, int i, Object child) {
            switch (i) {
                case 0:
                    //todo: improve this dummy way
                    final List<T1> t1s = getTs(this);
                    final List<T1> t1s_ = new ArrayList<T1>(t1s);
                    t1s_.set(i, (T1) child);
                    return make(t1s_);
            }
            assert false : "Unexpected call.";
            return null;
        }

        public Object getChildAt(Object o, int i) {
            switch (i) {
                case 0:
                    return getTs(o);
            }
            assert false : "Unexpected call.";
            return null;
        }

        public int getChildCount(Object o) {
            return 1;
        }

        public Class forType() {
            return h.class;
        }
    }

    public static class h_Mapping extends h_Introspector {
        public static h_Mapping instance = new h_Mapping();

        // Test
        public boolean isSym(Object t) {
            return t instanceof h;
        }

        public h make(List<T1> ts) {
            return new h(ts);
        }

        public List<T1> getTs(Object t) {
            return ((h) t).getTs();
        }

    }

}
