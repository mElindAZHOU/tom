
























package tom.gom.expander.rule;



import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.tree.Tree;
import tom.gom.adt.rule.RuleAdaptor;
import java.util.logging.Level;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
import tom.gom.GomMessage;
import tom.gom.adt.gom.types.*;
import tom.gom.adt.rule.types.*;
import tom.gom.tools.error.GomRuntimeException;



public class RuleExpander {

     private static   tom.gom.adt.gom.types.HookDeclList  tom_append_list_ConcHookDecl( tom.gom.adt.gom.types.HookDeclList l1,  tom.gom.adt.gom.types.HookDeclList  l2) {     if( l1.isEmptyConcHookDecl() ) {       return l2;     } else if( l2.isEmptyConcHookDecl() ) {       return l1;     } else if(  l1.getTailConcHookDecl() .isEmptyConcHookDecl() ) {       return  tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( l1.getHeadConcHookDecl() ,l2) ;     } else {       return  tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( l1.getHeadConcHookDecl() ,tom_append_list_ConcHookDecl( l1.getTailConcHookDecl() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.HookDeclList  tom_get_slice_ConcHookDecl( tom.gom.adt.gom.types.HookDeclList  begin,  tom.gom.adt.gom.types.HookDeclList  end, tom.gom.adt.gom.types.HookDeclList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcHookDecl()  ||  (end== tom.gom.adt.gom.types.hookdecllist.EmptyConcHookDecl.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( begin.getHeadConcHookDecl() ,( tom.gom.adt.gom.types.HookDeclList )tom_get_slice_ConcHookDecl( begin.getTailConcHookDecl() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.SlotList  tom_append_list_ConcSlot( tom.gom.adt.gom.types.SlotList l1,  tom.gom.adt.gom.types.SlotList  l2) {     if( l1.isEmptyConcSlot() ) {       return l2;     } else if( l2.isEmptyConcSlot() ) {       return l1;     } else if(  l1.getTailConcSlot() .isEmptyConcSlot() ) {       return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( l1.getHeadConcSlot() ,l2) ;     } else {       return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( l1.getHeadConcSlot() ,tom_append_list_ConcSlot( l1.getTailConcSlot() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.SlotList  tom_get_slice_ConcSlot( tom.gom.adt.gom.types.SlotList  begin,  tom.gom.adt.gom.types.SlotList  end, tom.gom.adt.gom.types.SlotList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcSlot()  ||  (end== tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( begin.getHeadConcSlot() ,( tom.gom.adt.gom.types.SlotList )tom_get_slice_ConcSlot( begin.getTailConcSlot() ,end,tail)) ;   }      private static   tom.gom.adt.rule.types.Condition  tom_append_list_CondAnd( tom.gom.adt.rule.types.Condition  l1,  tom.gom.adt.rule.types.Condition  l2) {     if( l1.isEmptyCondAnd() ) {       return l2;     } else if( l2.isEmptyCondAnd() ) {       return l1;     } else if( ((l1 instanceof tom.gom.adt.rule.types.condition.ConsCondAnd) || (l1 instanceof tom.gom.adt.rule.types.condition.EmptyCondAnd)) ) {       if(  l1.getTailCondAnd() .isEmptyCondAnd() ) {         return  tom.gom.adt.rule.types.condition.ConsCondAnd.make( l1.getHeadCondAnd() ,l2) ;       } else {         return  tom.gom.adt.rule.types.condition.ConsCondAnd.make( l1.getHeadCondAnd() ,tom_append_list_CondAnd( l1.getTailCondAnd() ,l2)) ;       }     } else {       return  tom.gom.adt.rule.types.condition.ConsCondAnd.make(l1,l2) ;     }   }   private static   tom.gom.adt.rule.types.Condition  tom_get_slice_CondAnd( tom.gom.adt.rule.types.Condition  begin,  tom.gom.adt.rule.types.Condition  end, tom.gom.adt.rule.types.Condition  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyCondAnd()  ||  (end== tom.gom.adt.rule.types.condition.EmptyCondAnd.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.rule.types.condition.ConsCondAnd.make((( ((begin instanceof tom.gom.adt.rule.types.condition.ConsCondAnd) || (begin instanceof tom.gom.adt.rule.types.condition.EmptyCondAnd)) )? begin.getHeadCondAnd() :begin),( tom.gom.adt.rule.types.Condition )tom_get_slice_CondAnd((( ((begin instanceof tom.gom.adt.rule.types.condition.ConsCondAnd) || (begin instanceof tom.gom.adt.rule.types.condition.EmptyCondAnd)) )? begin.getTailCondAnd() : tom.gom.adt.rule.types.condition.EmptyCondAnd.make() ),end,tail)) ;   }      private static   tom.gom.adt.rule.types.Condition  tom_append_list_CondOr( tom.gom.adt.rule.types.Condition  l1,  tom.gom.adt.rule.types.Condition  l2) {     if( l1.isEmptyCondOr() ) {       return l2;     } else if( l2.isEmptyCondOr() ) {       return l1;     } else if( ((l1 instanceof tom.gom.adt.rule.types.condition.ConsCondOr) || (l1 instanceof tom.gom.adt.rule.types.condition.EmptyCondOr)) ) {       if(  l1.getTailCondOr() .isEmptyCondOr() ) {         return  tom.gom.adt.rule.types.condition.ConsCondOr.make( l1.getHeadCondOr() ,l2) ;       } else {         return  tom.gom.adt.rule.types.condition.ConsCondOr.make( l1.getHeadCondOr() ,tom_append_list_CondOr( l1.getTailCondOr() ,l2)) ;       }     } else {       return  tom.gom.adt.rule.types.condition.ConsCondOr.make(l1,l2) ;     }   }   private static   tom.gom.adt.rule.types.Condition  tom_get_slice_CondOr( tom.gom.adt.rule.types.Condition  begin,  tom.gom.adt.rule.types.Condition  end, tom.gom.adt.rule.types.Condition  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyCondOr()  ||  (end== tom.gom.adt.rule.types.condition.EmptyCondOr.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.rule.types.condition.ConsCondOr.make((( ((begin instanceof tom.gom.adt.rule.types.condition.ConsCondOr) || (begin instanceof tom.gom.adt.rule.types.condition.EmptyCondOr)) )? begin.getHeadCondOr() :begin),( tom.gom.adt.rule.types.Condition )tom_get_slice_CondOr((( ((begin instanceof tom.gom.adt.rule.types.condition.ConsCondOr) || (begin instanceof tom.gom.adt.rule.types.condition.EmptyCondOr)) )? begin.getTailCondOr() : tom.gom.adt.rule.types.condition.EmptyCondOr.make() ),end,tail)) ;   }      private static   tom.gom.adt.rule.types.RuleList  tom_append_list_RuleList( tom.gom.adt.rule.types.RuleList l1,  tom.gom.adt.rule.types.RuleList  l2) {     if( l1.isEmptyRuleList() ) {       return l2;     } else if( l2.isEmptyRuleList() ) {       return l1;     } else if(  l1.getTailRuleList() .isEmptyRuleList() ) {       return  tom.gom.adt.rule.types.rulelist.ConsRuleList.make( l1.getHeadRuleList() ,l2) ;     } else {       return  tom.gom.adt.rule.types.rulelist.ConsRuleList.make( l1.getHeadRuleList() ,tom_append_list_RuleList( l1.getTailRuleList() ,l2)) ;     }   }   private static   tom.gom.adt.rule.types.RuleList  tom_get_slice_RuleList( tom.gom.adt.rule.types.RuleList  begin,  tom.gom.adt.rule.types.RuleList  end, tom.gom.adt.rule.types.RuleList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyRuleList()  ||  (end== tom.gom.adt.rule.types.rulelist.EmptyRuleList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.rule.types.rulelist.ConsRuleList.make( begin.getHeadRuleList() ,( tom.gom.adt.rule.types.RuleList )tom_get_slice_RuleList( begin.getTailRuleList() ,end,tail)) ;   }      private static   tom.gom.adt.rule.types.TermList  tom_append_list_TermList( tom.gom.adt.rule.types.TermList l1,  tom.gom.adt.rule.types.TermList  l2) {     if( l1.isEmptyTermList() ) {       return l2;     } else if( l2.isEmptyTermList() ) {       return l1;     } else if(  l1.getTailTermList() .isEmptyTermList() ) {       return  tom.gom.adt.rule.types.termlist.ConsTermList.make( l1.getHeadTermList() ,l2) ;     } else {       return  tom.gom.adt.rule.types.termlist.ConsTermList.make( l1.getHeadTermList() ,tom_append_list_TermList( l1.getTailTermList() ,l2)) ;     }   }   private static   tom.gom.adt.rule.types.TermList  tom_get_slice_TermList( tom.gom.adt.rule.types.TermList  begin,  tom.gom.adt.rule.types.TermList  end, tom.gom.adt.rule.types.TermList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyTermList()  ||  (end== tom.gom.adt.rule.types.termlist.EmptyTermList.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.rule.types.termlist.ConsTermList.make( begin.getHeadTermList() ,( tom.gom.adt.rule.types.TermList )tom_get_slice_TermList( begin.getTailTermList() ,end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Sequence )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) ;       } else {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Sequence.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Sequence.make(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Choice )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) ;       } else {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Choice.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Choice.make(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.SequenceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.SequenceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.SequenceId.make(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.ChoiceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.ChoiceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.ChoiceId.make(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ): null ),end,tail)) ;   }   private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ), null ) ) , tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ), null ) ) , null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ), null ) ) , null ) ) ) ));}private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { return (  tom.library.sl.Choice.make(s, tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) )  );}private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s, tom.library.sl.Sequence.make(( new tom.library.sl.MuVar("_x") ), null ) ) , tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make(v, tom.library.sl.Choice.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.SequenceId.make(v, tom.library.sl.SequenceId.make(( new tom.library.sl.MuVar("_x") ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.ChoiceId.make(v, tom.library.sl.ChoiceId.make(( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}



  private ModuleList moduleList;

  public RuleExpander(ModuleList data) {
    this.moduleList = data;
  }

  public HookDeclList expandRules(String ruleCode) {
    RuleLexer lexer = new RuleLexer(new ANTLRStringStream(ruleCode));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    RuleParser parser = new RuleParser(tokens);
    RuleList rulelist =  tom.gom.adt.rule.types.rulelist.EmptyRuleList.make() ;
    try {
      Tree ast = (Tree) parser.ruleset().getTree();
      rulelist = (RuleList) RuleAdaptor.getTerm(ast);
    } catch (org.antlr.runtime.RecognitionException e) {
      GomMessage.error(getLogger(), null, 0, GomMessage.rulesParsingFailure);
      return  tom.gom.adt.gom.types.hookdecllist.EmptyConcHookDecl.make() ;
    }
    return expand(rulelist);
  }

  protected HookDeclList expand(RuleList rulelist) {
    HookDeclList hookList =  tom.gom.adt.gom.types.hookdecllist.EmptyConcHookDecl.make() ;
    
    Map<OperatorDecl,RuleList> rulesForOperator =
      new HashMap<OperatorDecl,RuleList>();
    { /* unamed block */{ /* unamed block */if ( (rulelist instanceof tom.gom.adt.rule.types.RuleList) ) {if ( (((( tom.gom.adt.rule.types.RuleList )rulelist) instanceof tom.gom.adt.rule.types.rulelist.ConsRuleList) || ((( tom.gom.adt.rule.types.RuleList )rulelist) instanceof tom.gom.adt.rule.types.rulelist.EmptyRuleList)) ) { tom.gom.adt.rule.types.RuleList  tomMatch780_end_4=(( tom.gom.adt.rule.types.RuleList )rulelist);do {{ /* unamed block */if (!( tomMatch780_end_4.isEmptyRuleList() )) { tom.gom.adt.rule.types.Rule  tomMatch780_8= tomMatch780_end_4.getHeadRuleList() ;boolean tomMatch780_14= false ; tom.gom.adt.rule.types.Rule  tomMatch780_10= null ; tom.gom.adt.rule.types.Rule  tomMatch780_9= null ; tom.gom.adt.rule.types.Term  tomMatch780_7= null ;if ( ((( tom.gom.adt.rule.types.Rule )tomMatch780_8) instanceof tom.gom.adt.rule.types.rule.Rule) ) {{ /* unamed block */tomMatch780_14= true ;tomMatch780_9=tomMatch780_8;tomMatch780_7= tomMatch780_9.getlhs() ;}} else {if ( ((( tom.gom.adt.rule.types.Rule )tomMatch780_8) instanceof tom.gom.adt.rule.types.rule.ConditionalRule) ) {{ /* unamed block */tomMatch780_14= true ;tomMatch780_10=tomMatch780_8;tomMatch780_7= tomMatch780_10.getlhs() ;}}}if (tomMatch780_14) {if ( ((( tom.gom.adt.rule.types.Term )tomMatch780_7) instanceof tom.gom.adt.rule.types.term.Appl) ) { tom.gom.adt.rule.types.Rule  tom___rl= tomMatch780_end_4.getHeadRuleList() ;

        OperatorDecl decl = getOperatorDecl( tomMatch780_7.getsymbol() );
        if (null != decl) { /* unamed block */
          RuleList rules = rulesForOperator.get(decl);
          if (null == rules) { /* unamed block */
            rulesForOperator.put(decl, tom.gom.adt.rule.types.rulelist.ConsRuleList.make(tom___rl, tom.gom.adt.rule.types.rulelist.EmptyRuleList.make() ) );
          } else { /* unamed block */
            rulesForOperator.put(decl,tom_append_list_RuleList(rules, tom.gom.adt.rule.types.rulelist.ConsRuleList.make(tom___rl, tom.gom.adt.rule.types.rulelist.EmptyRuleList.make() ) ));
          }}
 else { /* unamed block */
          GomMessage.warning(getLogger(), null, 0, 
              GomMessage.discardRuleWarning, (tom___rl));
        }}}}if ( tomMatch780_end_4.isEmptyRuleList() ) {tomMatch780_end_4=(( tom.gom.adt.rule.types.RuleList )rulelist);} else {tomMatch780_end_4= tomMatch780_end_4.getTailRuleList() ;}}} while(!( (tomMatch780_end_4==(( tom.gom.adt.rule.types.RuleList )rulelist)) ));}}}}


    
    for (OperatorDecl opDecl : rulesForOperator.keySet()) {
      TypedProduction prod = opDecl.getProd();
      { /* unamed block */{ /* unamed block */if ( (prod instanceof tom.gom.adt.gom.types.TypedProduction) ) {if ( ((( tom.gom.adt.gom.types.TypedProduction )prod) instanceof tom.gom.adt.gom.types.typedproduction.Slots) ) {


          SlotList args = opArgs( (( tom.gom.adt.gom.types.TypedProduction )prod).getSlots() ,1);
          String hookCode =
            generateHookCode(args, rulesForOperator.get(opDecl));
          hookList =
            tom_append_list_ConcHookDecl(hookList, tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( tom.gom.adt.gom.types.hookdecl.MakeHookDecl.make( tom.gom.adt.gom.types.decl.CutOperator.make(opDecl) , args,  tom.gom.adt.code.types.code.Code.make(hookCode) ,  tom.gom.adt.gom.types.hookkind.HookKind.make("rules") ,  true ) , tom.gom.adt.gom.types.hookdecllist.EmptyConcHookDecl.make() ) )
;
        }}}{ /* unamed block */if ( (prod instanceof tom.gom.adt.gom.types.TypedProduction) ) {if ( ((( tom.gom.adt.gom.types.TypedProduction )prod) instanceof tom.gom.adt.gom.types.typedproduction.Variadic) ) {


          RuleList rules = rulesForOperator.get(opDecl);
          
          int count = 0;
          RuleList nonEmptyRules = rules;
          { /* unamed block */{ /* unamed block */if ( (rules instanceof tom.gom.adt.rule.types.RuleList) ) {if ( (((( tom.gom.adt.rule.types.RuleList )rules) instanceof tom.gom.adt.rule.types.rulelist.ConsRuleList) || ((( tom.gom.adt.rule.types.RuleList )rules) instanceof tom.gom.adt.rule.types.rulelist.EmptyRuleList)) ) { tom.gom.adt.rule.types.RuleList  tomMatch782_end_4=(( tom.gom.adt.rule.types.RuleList )rules);do {{ /* unamed block */if (!( tomMatch782_end_4.isEmptyRuleList() )) { tom.gom.adt.rule.types.Rule  tomMatch782_8= tomMatch782_end_4.getHeadRuleList() ;boolean tomMatch782_15= false ; tom.gom.adt.rule.types.Rule  tomMatch782_10= null ; tom.gom.adt.rule.types.Rule  tomMatch782_9= null ; tom.gom.adt.rule.types.Term  tomMatch782_7= null ;if ( ((( tom.gom.adt.rule.types.Rule )tomMatch782_8) instanceof tom.gom.adt.rule.types.rule.Rule) ) {{ /* unamed block */tomMatch782_15= true ;tomMatch782_9=tomMatch782_8;tomMatch782_7= tomMatch782_9.getlhs() ;}} else {if ( ((( tom.gom.adt.rule.types.Rule )tomMatch782_8) instanceof tom.gom.adt.rule.types.rule.ConditionalRule) ) {{ /* unamed block */tomMatch782_15= true ;tomMatch782_10=tomMatch782_8;tomMatch782_7= tomMatch782_10.getlhs() ;}}}if (tomMatch782_15) {if ( ((( tom.gom.adt.rule.types.Term )tomMatch782_7) instanceof tom.gom.adt.rule.types.term.Appl) ) { tom.gom.adt.rule.types.TermList  tomMatch782_11= tomMatch782_7.getargs() ;if ( (((( tom.gom.adt.rule.types.TermList )tomMatch782_11) instanceof tom.gom.adt.rule.types.termlist.ConsTermList) || ((( tom.gom.adt.rule.types.TermList )tomMatch782_11) instanceof tom.gom.adt.rule.types.termlist.EmptyTermList)) ) {if ( tomMatch782_11.isEmptyTermList() ) {



              count++;
              nonEmptyRules = tom_append_list_RuleList(tom_get_slice_RuleList((( tom.gom.adt.rule.types.RuleList )rules),tomMatch782_end_4, tom.gom.adt.rule.types.rulelist.EmptyRuleList.make() ),tom_append_list_RuleList( tomMatch782_end_4.getTailRuleList() , tom.gom.adt.rule.types.rulelist.EmptyRuleList.make() ));
              String hookCode =
                generateHookCode( tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() , tom.gom.adt.rule.types.rulelist.ConsRuleList.make( tomMatch782_end_4.getHeadRuleList() , tom.gom.adt.rule.types.rulelist.EmptyRuleList.make() ) );
              hookList =
                tom_append_list_ConcHookDecl(hookList, tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( tom.gom.adt.gom.types.hookdecl.MakeHookDecl.make( tom.gom.adt.gom.types.decl.CutOperator.make(opDecl) ,  tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ,  tom.gom.adt.code.types.code.Code.make(hookCode) ,  tom.gom.adt.gom.types.hookkind.HookKind.make("rules") ,  true ) , tom.gom.adt.gom.types.hookdecllist.EmptyConcHookDecl.make() ) )
;
            }}}}}if ( tomMatch782_end_4.isEmptyRuleList() ) {tomMatch782_end_4=(( tom.gom.adt.rule.types.RuleList )rules);} else {tomMatch782_end_4= tomMatch782_end_4.getTailRuleList() ;}}} while(!( (tomMatch782_end_4==(( tom.gom.adt.rule.types.RuleList )rules)) ));}}}}

          if (count>1) { /* unamed block */
            GomMessage.warning(getLogger(), null, 0, 
                GomMessage.multipleRulesForEmpty, opDecl.getName());
          }
          
          if (!nonEmptyRules.isEmptyRuleList()) { /* unamed block */
            SlotList args =  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( tom.gom.adt.gom.types.slot.Slot.make("head",  (( tom.gom.adt.gom.types.TypedProduction )prod).getSort() ) , tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( tom.gom.adt.gom.types.slot.Slot.make("tail", opDecl.getSort()) , tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ) ) ;
            String hookCode =
              generateVariadicHookCode(args, nonEmptyRules);
            hookList =
              tom_append_list_ConcHookDecl(hookList, tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( tom.gom.adt.gom.types.hookdecl.MakeHookDecl.make( tom.gom.adt.gom.types.decl.CutOperator.make(opDecl) , args,  tom.gom.adt.code.types.code.Code.make(hookCode) ,  tom.gom.adt.gom.types.hookkind.HookKind.make("rules") ,  true ) , tom.gom.adt.gom.types.hookdecllist.EmptyConcHookDecl.make() ) )
;
          }}}}}


    }
    return hookList;
  }

  private String generateHookCode(SlotList slotList, RuleList ruleList) {
    StringBuilder output = new StringBuilder();
    if(slotList.isEmptyConcSlot()) {
      while(!ruleList.isEmptyRuleList()) {
        Rule rule = ruleList.getHeadRuleList();
        ruleList = ruleList.getTailRuleList();
        { /* unamed block */{ /* unamed block */if ( (rule instanceof tom.gom.adt.rule.types.Rule) ) {if ( ((( tom.gom.adt.rule.types.Rule )rule) instanceof tom.gom.adt.rule.types.rule.Rule) ) {if ( ((( tom.gom.adt.rule.types.Term ) (( tom.gom.adt.rule.types.Rule )rule).getlhs() ) instanceof tom.gom.adt.rule.types.term.Appl) ) {

            output.append("    return `");
            genTerm( (( tom.gom.adt.rule.types.Rule )rule).getrhs() ,output);
            output.append(";\n");
          }}}}{ /* unamed block */if ( (rule instanceof tom.gom.adt.rule.types.Rule) ) {if ( ((( tom.gom.adt.rule.types.Rule )rule) instanceof tom.gom.adt.rule.types.rule.ConditionalRule) ) {if ( ((( tom.gom.adt.rule.types.Term ) (( tom.gom.adt.rule.types.Rule )rule).getlhs() ) instanceof tom.gom.adt.rule.types.term.Appl) ) {

            output.append("    %match{\n");
            genCondition( (( tom.gom.adt.rule.types.Rule )rule).getcond() ,output);
            output.append(" -> { return `");
            genTerm( (( tom.gom.adt.rule.types.Rule )rule).getrhs() ,output);
            output.append("; }\n");
            output.append("}\n");
          }}}}}

      }

    } else {
      output.append("    %match(");
      matchArgs(slotList,output,1);
      output.append(") {\n");
      while(!ruleList.isEmptyRuleList()) {
        Rule rule = ruleList.getHeadRuleList();
        ruleList = ruleList.getTailRuleList();
        { /* unamed block */{ /* unamed block */if ( (rule instanceof tom.gom.adt.rule.types.Rule) ) {if ( ((( tom.gom.adt.rule.types.Rule )rule) instanceof tom.gom.adt.rule.types.rule.Rule) ) { tom.gom.adt.rule.types.Term  tomMatch784_1= (( tom.gom.adt.rule.types.Rule )rule).getlhs() ;if ( ((( tom.gom.adt.rule.types.Term )tomMatch784_1) instanceof tom.gom.adt.rule.types.term.Appl) ) {

            genTermList( tomMatch784_1.getargs() ,output);
            output.append(" -> { return `");
            genTerm( (( tom.gom.adt.rule.types.Rule )rule).getrhs() ,output);
            output.append("; }\n");
          }}}}{ /* unamed block */if ( (rule instanceof tom.gom.adt.rule.types.Rule) ) {if ( ((( tom.gom.adt.rule.types.Rule )rule) instanceof tom.gom.adt.rule.types.rule.ConditionalRule) ) { tom.gom.adt.rule.types.Term  tomMatch784_9= (( tom.gom.adt.rule.types.Rule )rule).getlhs() ;if ( ((( tom.gom.adt.rule.types.Term )tomMatch784_9) instanceof tom.gom.adt.rule.types.term.Appl) ) {

            genTermList( tomMatch784_9.getargs() ,output);
            output.append(" && ");
            genCondition( (( tom.gom.adt.rule.types.Rule )rule).getcond() ,output);
            output.append(" -> { return `");
            genTerm( (( tom.gom.adt.rule.types.Rule )rule).getrhs() ,output);
            output.append("; }\n");
          }}}}}

      }
      output.append("    }\n");
    }
    return output.toString();
  }

  private String generateVariadicHookCode(SlotList slotList, RuleList ruleList) {
    StringBuilder output = new StringBuilder();
    output.append("    %match(realMake(head,tail)) {\n");
    while(!ruleList.isEmptyRuleList()) {
      Rule rule = ruleList.getHeadRuleList();
      ruleList = ruleList.getTailRuleList();
      { /* unamed block */{ /* unamed block */if ( (rule instanceof tom.gom.adt.rule.types.Rule) ) {boolean tomMatch785_16= false ; tom.gom.adt.rule.types.Rule  tomMatch785_4= null ; tom.gom.adt.rule.types.Term  tomMatch785_1= null ; tom.gom.adt.rule.types.Rule  tomMatch785_3= null ;if ( ((( tom.gom.adt.rule.types.Rule )rule) instanceof tom.gom.adt.rule.types.rule.Rule) ) {{ /* unamed block */tomMatch785_16= true ;tomMatch785_3=(( tom.gom.adt.rule.types.Rule )rule);tomMatch785_1= tomMatch785_3.getlhs() ;}} else {if ( ((( tom.gom.adt.rule.types.Rule )rule) instanceof tom.gom.adt.rule.types.rule.ConditionalRule) ) {{ /* unamed block */tomMatch785_16= true ;tomMatch785_4=(( tom.gom.adt.rule.types.Rule )rule);tomMatch785_1= tomMatch785_4.getlhs() ;}}}if (tomMatch785_16) {if ( ((( tom.gom.adt.rule.types.Term )tomMatch785_1) instanceof tom.gom.adt.rule.types.term.Appl) ) { tom.gom.adt.rule.types.TermList  tomMatch785_6= tomMatch785_1.getargs() ;if ( (((( tom.gom.adt.rule.types.TermList )tomMatch785_6) instanceof tom.gom.adt.rule.types.termlist.ConsTermList) || ((( tom.gom.adt.rule.types.TermList )tomMatch785_6) instanceof tom.gom.adt.rule.types.termlist.EmptyTermList)) ) {if (!( tomMatch785_6.isEmptyTermList() )) { tom.gom.adt.rule.types.Term  tomMatch785_12= tomMatch785_6.getHeadTermList() ;boolean tomMatch785_15= false ; tom.gom.adt.rule.types.Term  tomMatch785_14= null ; tom.gom.adt.rule.types.Term  tomMatch785_13= null ;if ( ((( tom.gom.adt.rule.types.Term )tomMatch785_12) instanceof tom.gom.adt.rule.types.term.UnnamedVarStar) ) {{ /* unamed block */tomMatch785_15= true ;tomMatch785_13=tomMatch785_12;}} else {if ( ((( tom.gom.adt.rule.types.Term )tomMatch785_12) instanceof tom.gom.adt.rule.types.term.VarStar) ) {{ /* unamed block */tomMatch785_15= true ;tomMatch785_14=tomMatch785_12;}}}if (tomMatch785_15) { tom.gom.adt.rule.types.Term  tom___var= tomMatch785_6.getHeadTermList() ;

            String varname = "_";
            { /* unamed block */{ /* unamed block */if ( (tom___var instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )tom___var) instanceof tom.gom.adt.rule.types.term.VarStar) ) {
 varname =  (( tom.gom.adt.rule.types.Term )tom___var).getname() ; }}}}

            GomMessage.warning(getLogger(),null,0,
                GomMessage.variadicRuleStartingWithStar,
                new Object[]{ /* unamed block */( tomMatch785_1.getsymbol() ),varname});
        }}}}}}}}{ /* unamed block */{ /* unamed block */if ( (rule instanceof tom.gom.adt.rule.types.Rule) ) {if ( ((( tom.gom.adt.rule.types.Rule )rule) instanceof tom.gom.adt.rule.types.rule.Rule) ) {



          genTerm( (( tom.gom.adt.rule.types.Rule )rule).getlhs() ,output);
          output.append(" -> { return `");
          genTerm( (( tom.gom.adt.rule.types.Rule )rule).getrhs() ,output);
          output.append("; }\n");
        }}}{ /* unamed block */if ( (rule instanceof tom.gom.adt.rule.types.Rule) ) {if ( ((( tom.gom.adt.rule.types.Rule )rule) instanceof tom.gom.adt.rule.types.rule.ConditionalRule) ) {

          genTerm( (( tom.gom.adt.rule.types.Rule )rule).getlhs() ,output);
          output.append(" && ");
          genCondition( (( tom.gom.adt.rule.types.Rule )rule).getcond() ,output);
          output.append(" -> { return `");
          genTerm( (( tom.gom.adt.rule.types.Rule )rule).getrhs() ,output);
          output.append("; }\n");
        }}}}

    }
    output.append("    }\n");
    return output.toString();
  }
  private void genTermList(TermList list, StringBuilder output) {
    { /* unamed block */{ /* unamed block */if ( (list instanceof tom.gom.adt.rule.types.TermList) ) {if ( (((( tom.gom.adt.rule.types.TermList )list) instanceof tom.gom.adt.rule.types.termlist.ConsTermList) || ((( tom.gom.adt.rule.types.TermList )list) instanceof tom.gom.adt.rule.types.termlist.EmptyTermList)) ) {if ( (( tom.gom.adt.rule.types.TermList )list).isEmptyTermList() ) {
 return; }}}}{ /* unamed block */if ( (list instanceof tom.gom.adt.rule.types.TermList) ) {if ( (((( tom.gom.adt.rule.types.TermList )list) instanceof tom.gom.adt.rule.types.termlist.ConsTermList) || ((( tom.gom.adt.rule.types.TermList )list) instanceof tom.gom.adt.rule.types.termlist.EmptyTermList)) ) {if (!( (( tom.gom.adt.rule.types.TermList )list).isEmptyTermList() )) { tom.gom.adt.rule.types.TermList  tom___t= (( tom.gom.adt.rule.types.TermList )list).getTailTermList() ;

        genTerm( (( tom.gom.adt.rule.types.TermList )list).getHeadTermList() ,output);
        if (!tom___t.isEmptyTermList()) { /* unamed block */
          output.append(", ");
        }
        genTermList(tom___t,output);
      }}}}}

  }

  private void genTerm(Term termArg, StringBuilder output) {
    { /* unamed block */{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.Appl) ) {

        output.append( (( tom.gom.adt.rule.types.Term )termArg).getsymbol() );
        output.append("(");
        genTermList( (( tom.gom.adt.rule.types.Term )termArg).getargs() , output);
        output.append(")");
      }}}{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.At) ) {

        output.append( (( tom.gom.adt.rule.types.Term )termArg).getname() );
        output.append("@");
        genTerm( (( tom.gom.adt.rule.types.Term )termArg).getterm() ,output);
      }}}{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.Anti) ) {

        output.append("!");
        genTerm( (( tom.gom.adt.rule.types.Term )termArg).getterm() ,output);
      }}}{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.UnnamedVar) ) {

        output.append("_");
      }}}{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.UnnamedVarStar) ) {

        output.append("_*");
      }}}{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.Var) ) {

        output.append( (( tom.gom.adt.rule.types.Term )termArg).getname() );
      }}}{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.VarStar) ) {

        output.append( (( tom.gom.adt.rule.types.Term )termArg).getname() );
        output.append("*");
      }}}{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.BuiltinInt) ) {

        output.append( (( tom.gom.adt.rule.types.Term )termArg).geti() );
      }}}{ /* unamed block */if ( (termArg instanceof tom.gom.adt.rule.types.Term) ) {if ( ((( tom.gom.adt.rule.types.Term )termArg) instanceof tom.gom.adt.rule.types.term.BuiltinString) ) {

        output.append( (( tom.gom.adt.rule.types.Term )termArg).gets() );
      }}}}

  }

  private void genCondition(Condition cond, StringBuilder output) {
    { /* unamed block */{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.CondEquals) ) {

        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett1() ,output);
        output.append(" == ");
        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett2() ,output);
      }}}{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.CondNotEquals) ) {

        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett1() ,output);
        output.append(" != ");
        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett2() ,output);
      }}}{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.CondLessEquals) ) {

        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett1() ,output);
        output.append(" <= ");
        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett2() ,output);
      }}}{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.CondLessThan) ) {

        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett1() ,output);
        output.append(" < ");
        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett2() ,output);
      }}}{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.CondGreaterEquals) ) {

        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett1() ,output);
        output.append(" >= ");
        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett2() ,output);
      }}}{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.CondGreaterThan) ) {

        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett1() ,output);
        output.append(" > ");
        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett2() ,output);
      }}}{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.CondMatch) ) {

        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett1() ,output);
        output.append(" << ");
        genTerm( (( tom.gom.adt.rule.types.Condition )cond).gett2() ,output);
      }}}{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( (((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.ConsCondAnd) || ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.EmptyCondAnd)) ) {if (!( (  (( tom.gom.adt.rule.types.Condition )cond).isEmptyCondAnd()  ||  ((( tom.gom.adt.rule.types.Condition )cond)== tom.gom.adt.rule.types.condition.EmptyCondAnd.make() )  ) )) { tom.gom.adt.rule.types.Condition  tom___head=(( (((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.ConsCondAnd) || ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.EmptyCondAnd)) )?( (( tom.gom.adt.rule.types.Condition )cond).getHeadCondAnd() ):((( tom.gom.adt.rule.types.Condition )cond))); tom.gom.adt.rule.types.Condition  tom___tail=(( (((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.ConsCondAnd) || ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.EmptyCondAnd)) )?( (( tom.gom.adt.rule.types.Condition )cond).getTailCondAnd() ):( tom.gom.adt.rule.types.condition.EmptyCondAnd.make() ));

        if(tom___tail !=  tom.gom.adt.rule.types.condition.EmptyCondAnd.make() ) { /* unamed block */
          output.append("(");
          genCondition(tom___head,output);
          output.append(" && ");
          genCondition(tom___tail,output);
          output.append(")");
        } else { /* unamed block */
          genCondition(tom___head,output);
        }}}}}{ /* unamed block */if ( (cond instanceof tom.gom.adt.rule.types.Condition) ) {if ( (((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.ConsCondOr) || ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.EmptyCondOr)) ) {if (!( (  (( tom.gom.adt.rule.types.Condition )cond).isEmptyCondOr()  ||  ((( tom.gom.adt.rule.types.Condition )cond)== tom.gom.adt.rule.types.condition.EmptyCondOr.make() )  ) )) { tom.gom.adt.rule.types.Condition  tom___head=(( (((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.ConsCondOr) || ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.EmptyCondOr)) )?( (( tom.gom.adt.rule.types.Condition )cond).getHeadCondOr() ):((( tom.gom.adt.rule.types.Condition )cond))); tom.gom.adt.rule.types.Condition  tom___tail=(( (((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.ConsCondOr) || ((( tom.gom.adt.rule.types.Condition )cond) instanceof tom.gom.adt.rule.types.condition.EmptyCondOr)) )?( (( tom.gom.adt.rule.types.Condition )cond).getTailCondOr() ):( tom.gom.adt.rule.types.condition.EmptyCondOr.make() ));


        if(tom___tail !=  tom.gom.adt.rule.types.condition.EmptyCondOr.make() ) { /* unamed block */
          output.append("(");
          genCondition(tom___head,output);
          output.append(" || ");
          genCondition(tom___tail,output);
          output.append(")");
        } else { /* unamed block */
          genCondition(tom___head,output);
        }}}}}}


  }

  private void matchArgs(SlotList sl, StringBuilder output, int count) {
    { /* unamed block */{ /* unamed block */if ( (sl instanceof tom.gom.adt.gom.types.SlotList) ) {if ( (((( tom.gom.adt.gom.types.SlotList )sl) instanceof tom.gom.adt.gom.types.slotlist.ConsConcSlot) || ((( tom.gom.adt.gom.types.SlotList )sl) instanceof tom.gom.adt.gom.types.slotlist.EmptyConcSlot)) ) {if ( (( tom.gom.adt.gom.types.SlotList )sl).isEmptyConcSlot() ) {
 return; }}}}{ /* unamed block */if ( (sl instanceof tom.gom.adt.gom.types.SlotList) ) {if ( (((( tom.gom.adt.gom.types.SlotList )sl) instanceof tom.gom.adt.gom.types.slotlist.ConsConcSlot) || ((( tom.gom.adt.gom.types.SlotList )sl) instanceof tom.gom.adt.gom.types.slotlist.EmptyConcSlot)) ) {if (!( (( tom.gom.adt.gom.types.SlotList )sl).isEmptyConcSlot() )) { tom.gom.adt.gom.types.Slot  tomMatch791_7= (( tom.gom.adt.gom.types.SlotList )sl).getHeadConcSlot() ;if ( ((( tom.gom.adt.gom.types.Slot )tomMatch791_7) instanceof tom.gom.adt.gom.types.slot.Slot) ) { tom.gom.adt.gom.types.SortDecl  tom___sort= tomMatch791_7.getSort() ; tom.gom.adt.gom.types.SlotList  tom___t= (( tom.gom.adt.gom.types.SlotList )sl).getTailConcSlot() ;{ /* unamed block */{ /* unamed block */if ( (tom___sort instanceof tom.gom.adt.gom.types.SortDecl) ) {boolean tomMatch792_5= false ; String  tomMatch792_1= "" ; tom.gom.adt.gom.types.SortDecl  tomMatch792_4= null ; tom.gom.adt.gom.types.SortDecl  tomMatch792_3= null ;if ( ((( tom.gom.adt.gom.types.SortDecl )tom___sort) instanceof tom.gom.adt.gom.types.sortdecl.SortDecl) ) {{ /* unamed block */tomMatch792_5= true ;tomMatch792_3=(( tom.gom.adt.gom.types.SortDecl )tom___sort);tomMatch792_1= tomMatch792_3.getName() ;}} else {if ( ((( tom.gom.adt.gom.types.SortDecl )tom___sort) instanceof tom.gom.adt.gom.types.sortdecl.BuiltinSortDecl) ) {{ /* unamed block */tomMatch792_5= true ;tomMatch792_4=(( tom.gom.adt.gom.types.SortDecl )tom___sort);tomMatch792_1= tomMatch792_4.getName() ;}}}if (tomMatch792_5) {



            output.append(tomMatch792_1);
            output.append(" arg_"+count);
          }}}}

        if (!tom___t.isEmptyConcSlot()) { /* unamed block */
          output.append(", ");
        }
        matchArgs(tom___t,output,count+1);
      }}}}}}

  }

  private SlotList opArgs(SlotList slots, int count) {
    { /* unamed block */{ /* unamed block */if ( (slots instanceof tom.gom.adt.gom.types.SlotList) ) {if ( (((( tom.gom.adt.gom.types.SlotList )slots) instanceof tom.gom.adt.gom.types.slotlist.ConsConcSlot) || ((( tom.gom.adt.gom.types.SlotList )slots) instanceof tom.gom.adt.gom.types.slotlist.EmptyConcSlot)) ) {if ( (( tom.gom.adt.gom.types.SlotList )slots).isEmptyConcSlot() ) {

        return  tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ;
      }}}}{ /* unamed block */if ( (slots instanceof tom.gom.adt.gom.types.SlotList) ) {if ( (((( tom.gom.adt.gom.types.SlotList )slots) instanceof tom.gom.adt.gom.types.slotlist.ConsConcSlot) || ((( tom.gom.adt.gom.types.SlotList )slots) instanceof tom.gom.adt.gom.types.slotlist.EmptyConcSlot)) ) {if (!( (( tom.gom.adt.gom.types.SlotList )slots).isEmptyConcSlot() )) { tom.gom.adt.gom.types.Slot  tomMatch793_7= (( tom.gom.adt.gom.types.SlotList )slots).getHeadConcSlot() ;if ( ((( tom.gom.adt.gom.types.Slot )tomMatch793_7) instanceof tom.gom.adt.gom.types.slot.Slot) ) {

        SlotList tail = opArgs( (( tom.gom.adt.gom.types.SlotList )slots).getTailConcSlot() ,count+1);
        return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( tom.gom.adt.gom.types.slot.Slot.make("arg_"+count,  tomMatch793_7.getSort() ) ,tom_append_list_ConcSlot(tail, tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() )) ;
      }}}}}}

    throw new GomRuntimeException("RuleExpander:opArgs failed "+slots);
  }
  protected OperatorDecl getOperatorDecl(String name) {
    OperatorDecl decl = null;
    OpRef ref = new OpRef();
    try {
      tom_make_TopDown( new GetOperatorDecl(ref,name) ).visit(moduleList);
    } catch (tom.library.sl.VisitFailure e) {
      throw new GomRuntimeException("Unexpected strategy failure!");
    }
    if (ref.val == null) {
      GomMessage.error(getLogger(), null, 0, GomMessage.unknownConstructor, name);
    }
    return ref.val;
  }
  static class OpRef { OperatorDecl val; }
  public static class GetOperatorDecl extends tom.library.sl.AbstractStrategyBasic {private  OpRef  opref;private  String  opName;public GetOperatorDecl( OpRef  opref,  String  opName) {super(( new tom.library.sl.Identity() ));this.opref=opref;this.opName=opName;}public  OpRef  getopref() {return opref;}public  String  getopName() {return opName;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChildren = new tom.library.sl.Visitable[getChildCount()];stratChildren[0] = super.getChildAt(0);return stratChildren;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.gom.adt.gom.types.OperatorDecl) ) {return ((T)visit_OperatorDecl((( tom.gom.adt.gom.types.OperatorDecl )v),introspector));}if (!(  null ==environment )) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.gom.adt.gom.types.OperatorDecl  _visit_OperatorDecl( tom.gom.adt.gom.types.OperatorDecl  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(  null ==environment )) {return (( tom.gom.adt.gom.types.OperatorDecl )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.gom.adt.gom.types.OperatorDecl  visit_OperatorDecl( tom.gom.adt.gom.types.OperatorDecl  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{ /* unamed block */{ /* unamed block */if ( (tom__arg instanceof tom.gom.adt.gom.types.OperatorDecl) ) {if ( ((( tom.gom.adt.gom.types.OperatorDecl )tom__arg) instanceof tom.gom.adt.gom.types.operatordecl.OperatorDecl) ) {






        if ( (( tom.gom.adt.gom.types.OperatorDecl )tom__arg).getName() .equals(opName)) { /* unamed block */
          opref.val = (( tom.gom.adt.gom.types.OperatorDecl )tom__arg);
        }}}}}return _visit_OperatorDecl(tom__arg,introspector);}}




  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
}
