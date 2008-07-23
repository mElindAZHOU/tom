/*
 * Gom
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
 * Antoine Reilles    e-mail: Antoine.Reilles@loria.fr
 * 
 **/

package tom.gom.expander;

import java.util.logging.Level;
import tom.gom.GomStreamManager;
import tom.gom.SymbolTable;
import tom.gom.adt.gom.types.*;
import tom.library.sl.*;
import java.util.ArrayList;
import java.util.Set;
import tom.gom.SymbolTable.*;

public class FreshExpander {

  %include { ../adt/gom/Gom.tom}
  %include { util/ArrayList.tom }
  %include { sl.tom }  

  %typeterm SymbolTable { implement { tom.gom.SymbolTable } }

  private SymbolTable st = new SymbolTable();

  public GomModuleList expand(GomModuleList m) {
    try {
      ArrayList list = new ArrayList();
      GomModuleList res = (GomModuleList) 
        `Sequence(
            TopDown(ExpandAtoms(list)),
            TopDown(UpdateSpecialization(list))).visitLight(m);
      st.fill(res);
      //System.out.println(st);
      res = addRawSortsAndConstructors(res);
      res = addAtomHooks(res);
      res = addSortHooks(res);
      res = addRawSortHooks(res);
      res = addConstructorHooks(res);
      res = addRawConstructorHooks(res);
      return res;
    } catch (VisitFailure e) {
      throw new RuntimeException("should not happen");
    } catch (SymbolTable.SortException e) {
      e.printStackTrace();
      throw new RuntimeException("should not happen");
    } catch (SymbolTable.ConstructorException e) {
      e.printStackTrace();
      throw new RuntimeException("should not happen");
    }
  }
  

  /* -- export, convert and alpha maps argument lists -- */

  private String alphamapArgList(String sort) {
    StringBuffer buf = new StringBuffer();
    for(String a: st.getAccessibleAtoms(sort)) {
      buf.append(", ");
      String aid = st.qualifiedSortId(a);
      buf.append(%[tom.library.freshgom.AlphaMap<@aid@> @a@Map]%);
    }
    return buf.toString();
  }

  private String newAlphamapList(String sort) {
    StringBuffer buf = new StringBuffer();
    for(String a: st.getAccessibleAtoms(sort)) {
      buf.append(", ");
      String aid = st.qualifiedSortId(a);
      buf.append(%[new tom.library.freshgom.AlphaMap<@aid@>()]%);
    }
    return buf.toString();
  }

  private String exportmapArgList(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true; 
    for(String a: st.getAccessibleAtoms(sort)) {
      if(!first) buf.append(", ");
      else first = false;
      String aid = st.qualifiedSortId(a);
      if(st.isPatternType(sort)) {
        if (st.getBoundAtoms(sort).contains(a)) {
          buf.append(%[tom.library.freshgom.ExportMap<@aid@> @a@OuterMap]%);
          buf.append(%[,tom.library.freshgom.ExportMap<@aid@> @a@InnerMap]%);
        } else 
          buf.append(%[tom.library.freshgom.ExportMap<@aid@> @a@Map]%);
      } else {
        buf.append(%[tom.library.freshgom.ExportMap<@aid@> @a@Map]%);
      }
    }
    return buf.toString();
  }

  private String newExportmapList(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true; 
    for(String a: st.getAccessibleAtoms(sort)) {
      if(!first) buf.append(", ");
      else first = false;
      String aid = st.qualifiedSortId(a);
      buf.append(%[new tom.library.freshgom.ExportMap<@aid@>()]%);
      if(st.isPatternType(sort) && st.getBoundAtoms(sort).contains(a)) 
        buf.append(%[,new tom.library.freshgom.ExportMap<@aid@>()]%);
    }
    return buf.toString();
  }

  /* call to expression field inside expression type */
  private String exportRecCall1(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getAccessibleAtoms(sort)) {
      if(!first) buf.append(",");
      else first = false;
      buf.append(%[@a@Map]%);
    }
    return buf.toString();
  }

  /* call to pattern field inside expression type */
  private String exportRecCall2(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getAccessibleAtoms(sort)) {
      if(!first) buf.append(",");
      else first = false;
      buf.append(%[@a@Map]%);
      if(st.getBoundAtoms(sort).contains(a))
        buf.append(%[,@a@InnerMap]%);
    }
    return buf.toString();
  }

  /* call to inner field inside pattern type */
  private String exportRecCall3(String tosort, String fromsort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getAccessibleAtoms(tosort)) {
      if(!first) buf.append(",");
      else first = false;
      if(st.getBoundAtoms(fromsort).contains(a)) 
        buf.append(%[@a@InnerMap]%);
      else
        buf.append(%[@a@Map]%);
    }
    return buf.toString();
  }

  /* call to outer field inside pattern type */
  private String exportRecCall4(String tosort, String fromsort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getAccessibleAtoms(tosort)) {
      if(!first) buf.append(",");
      else first = false;
      if(st.getBoundAtoms(fromsort).contains(a)) 
        buf.append(%[@a@OuterMap]%);
      else
        buf.append(%[@a@Map]%);
    }
    return buf.toString();
  }

  /* call to neutral field inside pattern type */
  private String exportRecCall5(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getAccessibleAtoms(sort)) {
      if(!first) buf.append(",");
      else first = false;
      buf.append(%[@a@Map]%);
    }
    return buf.toString();
  }

  /* call to pattern field inside pattern type */
  private String exportRecCall6(String tosort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getAccessibleAtoms(tosort)) {
      if(!first) buf.append(",");
      else first = false;
      // bound atoms have to be the same
      if(st.getBoundAtoms(tosort).contains(a)) {
        buf.append(%[@a@OuterMap]%);
        buf.append(%[,@a@InnerMap]%);
      } else buf.append(%[@a@Map]%);
    }
    return buf.toString();
  }

  private String convertmapArgList(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true; 
    for(String a: st.getAccessibleAtoms(sort)) {
      if(!first) buf.append(", ");
      else first = false;
      String aid = st.qualifiedSortId(a);
      buf.append(%[tom.library.freshgom.ConvertMap<@aid@> @a@Map]%);
    }
    return buf.toString();
  }

  private String newConvertmapList(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true; 
    for(String a: st.getAccessibleAtoms(sort)) {
      if(!first) buf.append(", ");
      else first = false;
      String aid = st.qualifiedSortId(a);
      buf.append(%[new tom.library.freshgom.ConvertMap<@aid@>()]%);
    }
    return buf.toString();
  }

  /* hashTables for 'refresh' methods" */
  private String hashtableArgList(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getBoundAtoms(sort)) {
      if(!first) buf.append(", ");
      else first = false;
      String aid = st.qualifiedSortId(a);
      buf.append( %[java.util.Hashtable<@aid@,@aid@> @a@Map]% );
    }
    return buf.toString();
  }

  /* hashTables for 'refresh' methods" */
  private String newHashtableList(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getBoundAtoms(sort)) {
      if(!first) buf.append(", ");
      else first = false;
      String aid = st.qualifiedSortId(a);
      buf.append(%[new java.util.Hashtable<@aid@,@aid@>()]%);
    }
    return buf.toString();
  }

  /* generates "A1Map,A2Map,...,AnMap" */
  private String hashtableRecursiveCall(String sort) {
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String a: st.getBoundAtoms(sort)) {
      if(!first) buf.append(", ");
      else first = false;
      buf.append(%[@a@Map]%);
    }
    return buf.toString();
  }

  /* generates "raw_f1,raw_f2,...,raw_fn" for fields of cons
   except for builtins where no new is added */
  private String rawArgList(String cons) {
    String sort = st.getSort(cons);
    StringBuffer buf = new StringBuffer();
    boolean first = true;
    for(String f: st.getFields(cons)) {
      if(!first) buf.append(",");
      else first = false;
      buf.append(st.isBuiltin(st.getSort(cons,f))? %[get@f@()]%:%[raw_@f@]%);
    }
    return buf.toString();
  }


  /* -- hooks utilities -- */

  %strategy AddHook(sort:String,hook:Production) extends Fail() {
    visit ProductionList {
      (p@SortType[Type=GomType[Name=n]],ps*) -> {
        if(`n.equals(sort)) 
          return `ConcProduction(p,hook,ps*);
      }
    }
  }

  /* add the hook next to sort declaration */
  private static GomModuleList addSortBlockHook
    (GomModuleList ml, String sort, String code) {
      Production hook = 
        `Hook(KindSort(),sort,HookKind("block"),
            ConcArg(),code,OptionList());
      try { 
        return (GomModuleList)
          `OnceTopDown(AddHook(sort,hook)).visitLight(ml); 
      }
      catch (VisitFailure e) { 
        throw new RuntimeException("Should never happen"); 
      }
    }

  /* add the hook next to the declaration of the constructor's sort */
  private GomModuleList addConstructorBlockHook
    (GomModuleList ml, String cons, String code) {
      String sort = st.getSort(cons);
      Production hook = 
        `Hook(KindOperator(),cons,HookKind("block"),
            ConcArg(),code,OptionList());
      try { 
        return (GomModuleList)
          `OnceTopDown(AddHook(sort,hook)).visitLight(ml); 
      }
      catch (VisitFailure e) { 
        throw new RuntimeException("Should never happen"); 
      }
    }

  /* add the hook next to the declaration of the constructor's sort */
  private GomModuleList addRawConstructorBlockHook
    (GomModuleList ml, String cons, String code) {
      String sort = st.getSort(cons);
      Production hook = 
        `Hook(KindOperator(),st.raw(cons),HookKind("block"),
            ConcArg(),code,OptionList());
      try { 
        return (GomModuleList)
          `OnceTopDown(AddHook(st.raw(sort),hook)).visitLight(ml); 
      }
      catch (VisitFailure e) { 
        throw new RuntimeException("Should never happen"); 
      }
    }

  /* add the hook next to the sort declaration */
  private GomModuleList addSortInterfaceHook
    (GomModuleList ml, String sort, String code) {
      Production hook = 
        `Hook(KindSort(),sort,HookKind("interface"),
            ConcArg(),code,OptionList());
      try { 
        return (GomModuleList)
          `OnceTopDown(AddHook(sort,hook)).visitLight(ml); 
      }
      catch (VisitFailure e) { 
        throw new RuntimeException("Should never happen"); 
      }
    }

  /* -- sort hooks -- */

  private GomModuleList addSortHooks(GomModuleList ml) {
    for(String s: st.getSorts()) 
      if(st.isExpressionType(s) || st.isPatternType(s))
        ml = addSortBlockHook(ml,s,sortBlockHookString(s));
    return ml;
  }


  private String sortBlockHookString(String sort) {
    String alphamapargs = alphamapArgList(sort);
    String newalphamaps = newAlphamapList(sort);
    String exportmapargs = exportmapArgList(sort);
    String newexportmaps = newExportmapList(sort);
    String rawsortid = st.qualifiedRawSortId(sort);
    String sortid = st.qualifiedSortId(sort);

    String res = "{";

    for(String a: st.getAccessibleAtoms(sort)) {
      String aid = st.qualifiedSortId(a);
      res += %[ 
        public abstract @sortid@ 
          rename@a@(java.util.Hashtable<@aid@,@aid@> map); 
      ]%;
    }

    res += %[
     /**
      * alpha equivalence 
      */
      public boolean equals(@sortid@ o) {
        return alpha(o @newalphamaps@);
      }

      public abstract boolean alpha (@sortid@ o @alphamapargs@);

     /**
      * exportation (term -> raw term) 
      */
      public @rawsortid@ export() {
        return _export(@newexportmaps@);
      }

      public abstract @rawsortid@ _export(@exportmapargs@);
    ]%;

    if(st.isPatternType(sort)) {
      String newhashtables = newHashtableList(sort);
      String hashtableargs = hashtableArgList(sort);
      res += %[ 
        public @sortid@ refresh() {
          return _refresh(@newhashtables@);
        }

        public abstract @sortid@ _refresh(@hashtableargs@); 
      ]%;

      for(String a: st.getBoundAtoms(sort)) {
        String aid = st.qualifiedSortId(a);
        res += %[
          public java.util.Set<@aid@> getBound@a@() {
            java.util.HashSet<@aid@> res = new java.util.HashSet<@aid@>();
            getBound@a@(res);
            return res;
          }

          public abstract void getBound@a@(java.util.Set<@aid@> atoms); 
        ]%;
      }
    }

    return res + "}";
  }

  /* -- raw sort hooks -- */

  private GomModuleList addRawSortHooks(GomModuleList ml) {
    for(String s: st.getSorts()) 
      if(st.isExpressionType(s) || st.isPatternType(s))
        ml = addSortBlockHook(ml,st.raw(s),rawSortBlockHookString(s));
    return ml;
  }


  private String rawSortBlockHookString(String sort) {
    String convertmapargs = convertmapArgList(sort);
    String newconvertmaps = newConvertmapList(sort);
    String sortid = st.qualifiedSortId(sort);

    return %[{
      /**
       * importation (raw term -> term) 
       */
      public @sortid@ convert() {
        return _convert(@newconvertmaps@);
      }

      public abstract @sortid@ _convert(@convertmapargs@);
    }]%;
  }

  /* -- non variadic constructor hooks -- */

  private GomModuleList addConstructorHooks(GomModuleList ml) {
    for(String s: st.getSorts()) 
      if(st.isExpressionType(s) || st.isPatternType(s))
        for(String c: st.getConstructors(s))
          if(! st.isVariadic(c))
            ml = addConstructorBlockHook(ml,c,constructorBlockHookString(c));
    return ml;
  }

  private String renameRecursiveCalls(String c, String atomSort) {
    String sortid = st.qualifiedSortId(st.getSort(c));
    String res = %[@sortid@ res = this;]%; 
    String atomSortId = st.qualifiedSortId(atomSort);
    for(String f: st.getFields(c)) {
      String fsort = st.getSort(c,f);
      if (st.isBuiltin(fsort)) continue;
      if (st.isAtomType(fsort)) {
        if (fsort.equals(atomSort))
          res += %[ 
            @atomSortId@ n_@f@ = map.get(get@f@());
        if (n_@f@ != null) res = res.set@f@(n_@f@); 
        ]%;
      } else {
        if (!st.getAccessibleAtoms(fsort).contains(atomSort)) continue;
        res += %[ res = res.set@f@(get@f@().rename@atomSort@(map)); ]%;
      }
    }
    return res + "return res;";
  }

  private String refreshRecursiveCalls(String c) {
    String sort = st.getSort(c);
    String sortid = st.qualifiedSortId(sort);
    String res = %[@sortid@ res = this;]%; 
    for(String f: st.getPatternFields(c)) {
      String fsort = st.getSort(c,f);
      if (st.isBuiltin(fsort)) continue;
      if (st.isAtomType(fsort)) {
        String fsortid = st.qualifiedSortId(fsort);
        res += %[
          @fsortid@ @f@ = get@f@();
        if (@fsort@Map.containsKey(@f@))
          res = res.set@f@(@fsort@Map.get(@f@));
        else {
          @fsortid@ fresh_@f@ = @fsortid@.fresh@fsort@(@f@);
          @fsort@Map.put(@f@,fresh_@f@);
          res = res.set@f@(fresh_@f@);
        }
        ]%;
      } else {
        String arglist = hashtableRecursiveCall(sort); 
        res += %[res = res.set@f@(get@f@()._refresh(@arglist@));]%;
      }
    }
    for(String f: st.getInnerFields(c)) {
      String fsort = st.getSort(c,f);
      if (st.isBuiltin(fsort)) continue;
      for(String a: st.getBoundAtoms(sort)) {
        res += %[res = res.set@f@(res.get@f@().rename@a@(@a@Map));]%;
      }
    }
    return res + "return res;";
  }

  private String exportRecursiveCalls(String c) {
    String sort = st.getSort(c);
    String sortid = st.qualifiedSortId(sort);
    String res = ""; 
    /* if c is a constructor in expression position
       the args are of the form atomsort1Map, atomsort2Map .. */
    if(st.isExpressionType(sort)) {
      for(String f: st.getFields(c)) {
        String fsort = st.getSort(c,f);
        if (st.isBuiltin(fsort)) continue;
        String fsortid = st.qualifiedSortId(fsort);
        String rawfsortid = st.qualifiedRawSortId(fsort);
        if (st.isAtomType(fsort)) {
          res += %[String raw_@f@ = @fsort@Map.get(get@f@());]%;
        } else if (st.isExpressionType(fsort)) {
          res += %[@rawfsortid@ raw_@f@ 
            = get@f@()._export(@exportRecCall1(fsort)@);]%;
        } else if (st.isPatternType(fsort)) {
          res += %[@fsortid@ @f@ = get@f@();]%;
          for(String a: st.getBoundAtoms(fsort)) {
            String aid = st.qualifiedSortId(a);
            res += %[
              java.util.Set<@aid@> @f@_bound@a@ = @f@.getBound@a@();
              tom.library.freshgom.ExportMap<@aid@> @a@InnerMap 
                = @a@Map.addSet(@f@_bound@a@);
            ]%;
          }
          res += %[@rawfsortid@ raw_@f@ 
            = @f@._export(@exportRecCall2(fsort)@);]%;
        }
      }
    /* if c is a constructor in pattern position --
       the args are of the form 
        as1OuterMap, as1InnerMap, as2OuterMap, as2InnerMap .. */
    } else if(st.isPatternType(sort)) {
      for(String f: st.getFields(c)) {
        String fsort = st.getSort(c,f);
        if (st.isBuiltin(fsort)) continue;
        String fsortid = st.qualifiedSortId(fsort);
        String rawfsortid = st.qualifiedRawSortId(fsort);
        if (st.isAtomType(fsort)) {
          if (st.isBound(c,f))
            res += %[String raw_@f@ = @fsort@InnerMap.get(get@f@());]%;
          else
            res += %[String raw_@f@ = @fsort@Map.get(get@f@());]%;
        } else if (st.isInner(c,f)) /* must be expression type */ {
          res += %[@rawfsortid@ raw_@f@ 
            = get@f@()._export(@exportRecCall3(fsort,sort)@);]%;
        } else if (st.isOuter(c,f)) /* must be expression type */ {
          res += %[@rawfsortid@ raw_@f@ 
            = get@f@()._export(@exportRecCall4(fsort,sort)@);]%;
        } else if (st.isNeutral(c,f)) /* must be expression type */ {
          res += %[@rawfsortid@ raw_@f@ 
            = get@f@()._export(@exportRecCall5(fsort)@);]%;
        } else /* must be pattern type */ {
          res += %[@rawfsortid@ raw_@f@ 
            = get@f@()._export(@exportRecCall6(fsort)@);]%;
        }
      }
    }
    return res + %[return `@st.raw(c)@(@rawArgList(c)@);]%;
  }

  private String getBoundRecursiveCalls(String c, String atomSort) {
    String sort = st.getSort(c);
    String sortid = st.qualifiedSortId(sort);
    String res = ""; 
    for(String f: st.getPatternFields(c)) {
      String fsort = st.getSort(c,f);
      if (st.isBuiltin(fsort)) continue;
      if (st.isAtomType(fsort)) {
        if (fsort.equals(atomSort)) {
          res += %[atoms.add(get@f@());]%;
        }
      } else if (st.getBoundAtoms(fsort).contains(atomSort)) {
        res += %[get@f@().getBound@atomSort@(atoms);]%;
      }
    }
    return res;
  }

  private String constructorBlockHookString(String c) {
    String sort = st.getSort(c);
    String alphamapargs = alphamapArgList(sort);
    String exportmapargs = exportmapArgList(sort);
    String rawsortid = st.qualifiedRawSortId(sort);
    String sortid = st.qualifiedSortId(sort);

    String res = "{";

    for(String a: st.getAccessibleAtoms(sort)) {
      String aid = st.qualifiedSortId(a);
      res += %[
        public @sortid@ rename@a@(java.util.Hashtable<@aid@,@aid@> map) {
          @renameRecursiveCalls(c,a)@
        }]%;
    }

    res += %[
      /**
       * alpha equivalence 
       */
      public boolean alpha (@sortid@ o @alphamapargs@) {
        return false;
      };

    /**
     * exportation (term -> raw term) 
     */
    public @rawsortid@ _export(@exportmapargs@) {
      @exportRecursiveCalls(c)@
    }
    ]%;

    if(st.isPatternType(sort)) {
      res += %[ 
        public @sortid@ _refresh(@hashtableArgList(sort)@) {
          @refreshRecursiveCalls(c)@
        }
      ]%;

      for(String a: st.getBoundAtoms(sort)) {
        String aid = st.qualifiedSortId(a);
        res += %[ 
          public void getBound@a@(java.util.Set<@aid@> atoms) {
            @getBoundRecursiveCalls(c,a)@
          }
        ]%;
      }
    }

    return res + "}";
  }

  /* -- non variadic raw constructor hooks -- */

  private GomModuleList addRawConstructorHooks(GomModuleList ml) {
    for(String s: st.getSorts()) 
      if(st.isExpressionType(s) || st.isPatternType(s))
        for(String c: st.getConstructors(s))
          if(! st.isVariadic(c))
            ml = addRawConstructorBlockHook(
                ml,c,rawConstructorBlockHookString(c));
    return ml;
  }


  private String rawConstructorBlockHookString(String c) {
    String sort = st.getSort(c);
    String convertmapargs = convertmapArgList(sort);
    String sortid = st.qualifiedSortId(sort);

    return %[{
      /**
       * importation (raw term -> term) 
       */
      public @sortid@ _convert(@convertmapargs@) {
        return null;
      }
    }]%;
  }

  /* -- atom hooks -- */

  private GomModuleList addAtomHooks(GomModuleList ml) {
    for(String s: st.getAtoms()) {
      ml = addSortBlockHook(ml,s,atomBlockHookString(s));
      ml = addSortInterfaceHook(ml,s,atomInterfaceHookString);
    }
    return ml;
  }

  private String atomBlockHookString(String sort) {
    String sortid = st.qualifiedSortId(sort);
    return %[{
      private static int counter = 0;

      public static @sortid@ 
        fresh@sort@(@sortid@ hint) { 
          return fresh@sort@(hint.gethint()); 
        }

      public static @sortid@ 
        fresh@sort@(String hint) { 
          return `@sort@(++counter,hint.split("[0-9]")[0]); 
        }

      public boolean equals(@sort@ o) {
        return this.getn() == o.getn();
      }

      public String getRepresentation(int n) {
        return gethint() + (n==0 ? "" : n);
      }
    }]%;
  }

  private static final String atomInterfaceHookString 
    = "{ tom.library.freshgom.Atom }";

  /* -- rawification -- */

  private GomModuleList addRawSortsAndConstructors(GomModuleList res) {
    try { return (GomModuleList) `TopDown(AddRaw(st)).visitLight(res); }
    catch(VisitFailure e) { 
      throw new RuntimeException("should never happen"); 
    }
  }

  %strategy AddRaw(st:SymbolTable) extends Identity() {
    visit Grammar {
      g@Grammar[ProductionList=pl] -> { 
        return `g.setProductionList(addRaw(st,`pl)); 
      }
    }
  }

  /**
   * match pl with
   *  | p::ps -> if fresh p && (not atom p) 
   *             then p::(rawify p)::(addraw pl) 
   *             else p::(addraw pl)
   *  | [] -> []
   **/
  private static ProductionList addRaw(SymbolTable st, ProductionList pl) {
    %match(pl) {
      ConsConcProduction(p,ps) -> {
        ProductionList nps = addRaw(st,`ps);
        %match(p) {
          SortType[Type=GomType[Name=n]] -> {
            if (st.isFreshType(`n) && !st.isAtomType(`n)) 
              return `ConcProduction(p,rawify(p,st),nps*);
          }
        }
        return `ConcProduction(p,nps*);
      }
      e@EmptyConcProduction() -> { return `e; }
    }
    throw new RuntimeException("non exhaustive patterns");
  }

  %strategy Rawify(st:SymbolTable) extends Identity() {
    visit GomType { 
      gt@GomType[Name=n] -> { 
        if (st.isAtomType(`n)) return `gt.setName("String");
        else if (!st.isBuiltin(`n)) return `gt.setName(SymbolTable.raw(`n));
      }
    }
    visit Production { 
      p@Production[Name=n] -> { return `p.setName(SymbolTable.raw(`n)); }
    }
  }

  private static Production rawify(Production p, SymbolTable st) {
    try { return (Production) `TopDown(Rawify(st)).visitLight(p); }
    catch(VisitFailure f) { 
      throw new RuntimeException("should never happen"); 
    }
  }

  /* -- atom expansion --**/

  %strategy ExpandAtoms(list:ArrayList) extends Identity() {
    visit Production {
      AtomDecl[ Name=name ] -> {
        list.add(`name);
        return `SortType(
            GomType(AtomType(),name),
            ConcAtom(),
            ConcProduction(Production(
                name,
                ConcField(
                  NamedField(None(),"n",GomType(ExpressionType(),"int")),
                  NamedField(None(),"hint",GomType(ExpressionType(),"String"))),
                GomType(AtomType(),name),Origin(-1)))); 
      }
    }
  }

  /* -- update expressiontype -> atomtype for atom sorts --**/

  %strategy UpdateSpecialization(list:ArrayList) extends Identity() {
    visit GomType {
      type@GomType[Name=name] -> {
        if (list.contains(`name)) {
          return `type.setSpecialization(`AtomType());
        }
      }
    }
  }

}
