// START OF M4 MACROS

import lib.sl.Visitor;

changequote(`[[', `]]')

// END OF M4 MACROS


import lib.Fun;
import lib.P;
import lib.Ref;
import lib.Zip;
import lib.sl.*;
import tom.library.sl.*;

import miniml.miniml.types.*;

public class MiniML {
    %include { string.tom }

    public static void showresult(Object x) { System.out.println("<result>" + x.toString() + "</result>"); }

    %gom {
        module MiniML

        imports String int boolean

        abstract syntax

        EnvAssoc = EnvAssoc( name:String , val:Val )
        MemAssoc = MemAssoc( addr:int    , val:Val )

        Env      = Env( EnvAssoc* )
        Mem      = Mem( MemAssoc* )
        Names    = Names( String* )

        Val      = Unit()
                 | Int( i:int )
                 | Bool( b:boolean )
                 | Closure( env:Env , recs:Names , arg:String , body:Code )
                 | Ptr( addr:int )
                 | Obj( members:Env )

        BinOp  = Add()
               | Sub()
               | Mult()
               | Div()

               | And()
               | Or()

               | Eq()

               | LT()
               | GT()
               | LE()
               | GE()

        UniOp  = Minus()
               | Neg()

        Code  = Val( val:Val )
              | UniOp( uniop:UniOp , code:Code )
              | BinOp( fst:Code , binop:BinOp, snd:Code)

              | Var( name:String )

              | If( cond:Code , then:Code, else:Code )
              | While( cond:Code , body:Code )
              | Seq ( fst:Code, snd:Code )

              | Ref( code:Code )
              | Bang( code:Code )
              | Assign( var:Code, body:Code )

              | Member(obj: Code , member:String )

              | Let( name:String, def:Code, body:Code )
              | Fun( name:String, body:Code )
              | App( fun:Code   , arg:Code )

              | Read()
              | Write( body:Code )

              | LetRec( name:String, def:Code, body:Code )

        Inputs   = Inputs(Val*)
        Outputs  = Outputs(Val*)

        module MiniML:rules() {
            Env(x*, EnvAssoc(n,_), y*, EnvAssoc(n,v), z*) -> Env(x*, y*, EnvAssoc(n,v), z*)
            Mem(x*, MemAssoc(n,_), y*, MemAssoc(n,v), z*) -> Mem(x*, y*, MemAssoc(n,v), z*)
            Names(x*, s , y*, s, z*)                      -> Names(x*, y*, s, z*)
        }
    }

    %include{sl.tom}

    define([[VISITORMAP]], [[public static Visitor<$2,$3> $1 = Visitor.map( new Fun<$2,$3>() {
        public $3 apply($2 $4) throws VisitFailure {
            $5
        }});]])

    define([[VISITORLIFT]], [[public static Visitor<$2,$3> $1 = Visitor.lift( new Fun<$2,Zip<$2,$3>>() {
        public Zip<$2,$3> apply(final $2 $4) throws VisitFailure {
            $5
        }});]])

    define([[FUNCTION]], [[new Fun<$1,$2>() {
        public $2 apply(final $1 $3) throws VisitFailure {
            $4
        }}]])

    define([[CODE_TYPE]],[[Visitable]])
    define([[ENVASSOC_TYPE]],[[Visitable]])
    define([[ENV_TYPE]],[[Visitable]])
    define([[CODE_ENV]],[[P<CODE_TYPE,ENV_TYPE>]])

    define([[MEMASSOC_TYPE]],[[Visitable]])
    define([[MEM_TYPE]],[[Visitable]])
    define([[MEMORY_TYPE]],[[P<Integer,MEM_TYPE>]])

    define([[INPUTS_TYPE]],[[Visitable]])
    define([[OUTPUTS_TYPE]],[[Visitable]])
    define([[INPUTS_OUTPUTS]],[[P<INPUTS_TYPE,OUTPUTS_TYPE>]])

    define([[WORLD_TYPE]],[[P<MEMORY_TYPE,INPUTS_OUTPUTS>]])

    define([[CONFIGURATION_TYPE]],[[P<CODE_ENV,WORLD_TYPE>]])


    /*
     * Simple reduction with only one focus and no need of control
     */

    VISITORMAP(code_reduction_single, CODE_TYPE , CODE_TYPE , u , [[

            if (!(u instanceof Code)) throw new VisitFailure();

            /*
             Now that we are on Code, we can match.
             */
            %match(u) {
                UniOp(Minus() , Val(Int( i)))                    -> { return `Val(Int( -i));                                 }
                UniOp(Neg()   , Val(Bool(b)))                    -> { return `Val(Bool(!b));                                 }

                BinOp(Val(Int(i))  , Add()  , Val(Int(j)))       -> { return `Val(Int(i + j));                               }
                BinOp(Val(Int(i))  , Sub()  , Val(Int(j)))       -> { return `Val(Int(i - j));                               }
                BinOp(Val(Int(i))  , Mult() , Val(Int(j)))       -> { return `Val(Int(i * j));                               }
                BinOp(Val(Int(i))  , Div()  , Val(Int(j)))       -> { return `Val(Int(i / j));                               }

                BinOp(Val(Int(i))  , LT()   , Val(Int(j)))       -> { return `Val(Bool(i <  j));                             }
                BinOp(Val(Int(i))  , GT()   , Val(Int(j)))       -> { return `Val(Bool(i >  j));                             }
                BinOp(Val(Int(i))  , LE()   , Val(Int(j)))       -> { return `Val(Bool(i <= j));                             }
                BinOp(Val(Int(i))  , GE()   , Val(Int(j)))       -> { return `Val(Bool(i >= j));                             }

                BinOp(Val(Bool(i)) , And()  , Val(Bool(j)))      -> { return `Val(Bool(i & j));                              }
                BinOp(Val(Bool(i)) , Or()   , Val(Bool(j)))      -> { return `Val(Bool(i | j));                              }

                BinOp(x            , Eq()   , y           )      -> { return `Val(Bool(true()));                              }
                BinOp(_            , Eq()   , _           )      -> { return `Val(Bool(false()));                             }


                If(Val(Bool(true()))  , t , _ )                  -> { return `t;                                              }
                If(Val(Bool(false())) , _ , e )                  -> { return `e;                                              }

                Seq(Val(_), c)                                   -> { return `c;                                              }

                Let(n , d , b)                                   -> { return `App(Fun(n,b),d);                                }
                While(c,b)                                       -> { return `If(c, Seq(b,While(c,b)), Val(Unit()));          }

                Member(Val(Obj(Env(_*, EnvAssoc(n, v), _*))), n) -> { return `v;                                              }
            };
            throw new VisitFailure();
    ]])


    public static Visitor<CODE_TYPE,CODE_TYPE> code_reduction = code_reduction_single.trace("code_reduction_single");

    VISITORLIFT(code_inner_eval , CODE_TYPE , CODE_TYPE , u , [[

           if (!(u instanceof Code)) throw new VisitFailure();

            /*
             Now that we are on Code, we can match.
             */

           define([[ZIPPER]], [[Zip.mkZip( FUNCTION( CODE_TYPE , CODE_TYPE , hole ,  [[ return (CODE_TYPE)$1 ; ]] ) ,  (CODE_TYPE)$2 )]] )

           %match(u) {
               UniOp(op , y)                     -> { return ZIPPER( [[ `UniOp(op, (Code)hole )        ]] , `y ); }

               BinOp(Val(v) , op  , y)           -> { final Val w = `v;
                                                      return ZIPPER( [[ `BinOp(Val(w), op, (Code)hole) ]] , `y  );
                                                    }
               BinOp(x      , op  , y)           -> { return ZIPPER( [[ `BinOp((Code)hole    , op, y)  ]] , `x ); }

               If(x , t , e)                     -> { return ZIPPER( [[ `If((Code)hole, t, e)          ]] , `x ); }

               Seq(x, y)                         -> { return ZIPPER( [[ `Seq((Code)hole, y)            ]] , `x ); }

               Member(x , n)                     -> { return ZIPPER( [[ `Member((Code)hole, n)         ]] , `x ); }


               Bang(x @ ! Val(_))                -> { return ZIPPER( [[ `Bang((Code)hole)              ]] , `x ); }

               Assign(Val(_), Val(_))            -> { throw new VisitFailure(); }
               Assign(Val(v), y )                -> { final Val w = `v;
                                                      return ZIPPER( [[ `Assign(Val(w), (Code)hole)    ]] , `y );
                                                    }
               Assign(x , y )                    -> { return ZIPPER( [[ `Assign((Code)hole, y)         ]] , `x ); }

               App(Val(_), Val(_))               -> { throw new VisitFailure(); }
               App(Val(v), y )                   -> { final Val w = `v;
                                                      return ZIPPER( [[ `App(Val(w), (Code)hole)       ]] , `y );
                                                    }
               App(x , y )                       -> { return ZIPPER( [[ `App((Code)hole , y)           ]] , `x ); }

               LetRec(n, x @ ! Val(_) , y)       -> { return ZIPPER( [[ `LetRec(n, (Code)hole,     y)  ]] , `x ); }

               Write(x @ ! Val(_))               -> { return ZIPPER( [[ `Write((Code)hole)             ]] , `x ); }

           };
           throw new VisitFailure();
    ]])


    public static Visitor<CODE_TYPE,CODE_TYPE> code_inner_up = Visitor.[[shift]](
       FUNCTION( [[Visitor<CODE_TYPE,CODE_TYPE>]] , [[Visitor<CODE_TYPE,CODE_TYPE>]] , v , [[return code_inner_eval.trace("code_inner_eval").seq(v).reset().seq(v); ]])
    );


    // Code reduction or inner eval
    public static Visitor<CODE_TYPE,CODE_TYPE> code_all = code_reduction.or(code_inner_up.trace("code_inner_up"));


    // Its global version
    public static Visitor<CONFIGURATION_TYPE,CONFIGURATION_TYPE> code_all_global = code_all.times(new Id<ENV_TYPE>()).times(new Id<WORLD_TYPE>());



    /*
     * Reduction of Var: two focuses, one on Code and one on a EnvAssoc
     */
    VISITORMAP(var, [[ P<CODE_TYPE,ENVASSOC_TYPE> ]] , [[ P<CODE_TYPE,ENVASSOC_TYPE> ]] , p , [[

            CODE_TYPE     code  = p.left;
            ENVASSOC_TYPE assoc = p.right;

            if (!(code  instanceof Code    )) throw new VisitFailure();
            if (!(assoc instanceof EnvAssoc)) throw new VisitFailure();

            %match {
                Var(x) << code && EnvAssoc(x, v) << assoc -> { return P.mkP((Visitable)`Val(v), assoc);                               }
            };
            throw new VisitFailure();
    ]])

    // Its iteration on every EnvAssoc
    public static Visitor<P<CODE_TYPE,ENV_TYPE>,P<CODE_TYPE,ENV_TYPE>> var_iter = (new Id<CODE_TYPE>()).times(Visitor.forSome).seq(var).reset();


    /*
     * Reduction of Fun
     */

    VISITORMAP(fun, [[ P<CODE_TYPE,ENV_TYPE> ]] , [[ P<CODE_TYPE,ENV_TYPE> ]] , p , [[

            CODE_TYPE code  = p.left;
            ENV_TYPE  env   = p.right;

            if (!(code  instanceof Code    )) throw new VisitFailure();
            if (!(env   instanceof Env     )) throw new VisitFailure();

            %match {
              Fun(n,c) << code -> { return P.mkP((CODE_TYPE)`Val(Closure((Env)env, Names(), n,c)), env                        ); }
            };
            throw new VisitFailure();
    ]])

    public static Env addRecs(Names names, ENV_TYPE env) throws VisitFailure {
        if (!(env   instanceof Env     )) throw new VisitFailure();

        //System.out.println("<addRecs><input><names>" + names.toString() + "</names>\n<env>" + env.toString() + "</env></input>\n");

        %match {
          Names(a*, x, b*) << names && Env(c*, EnvAssoc(x, v), d*) << env -> { //System.out.println("<branch>1</branch>\n");
                                                                               Env e2 = addRecs(`Names(a*,b*) , `Env(c*,d*));
                                                                               //System.out.println("<branch>1.1</branch>\n");
                                                                               Env r = `Env(EnvAssoc(x,v) , e2*);
                                                                               //System.out.println("<output>"+ r.toString() + "</output>\n</addRecs>\n");
                                                                               return r;
                                                                             }
          Names(_*)          << names && Env(_*)                     << env -> {//  System.out.println("<branch>2</branch>");
                                                                                Env r = `Env();
                                                                                //System.out.println("<branch>2.1</branch><output>" + r.toString() + "</output></addRecs>\n");
                                                                                return r; }
        };
        throw new RuntimeException("addRecs: Reached dead code ... this should not be happening!");
    }

    VISITORMAP(app_eval_inner, [[ P<CODE_TYPE,ENV_TYPE> ]] , [[ P<CODE_TYPE,ENV_TYPE> ]] , p , [[

            CODE_TYPE code  = p.left;
            ENV_TYPE  env   = p.right;

            if (!(code  instanceof Code    )) throw new VisitFailure();
            if (!(env   instanceof Env     )) throw new VisitFailure();

            Env envCasted = (Env)env;

            %match {
              App(Val(Closure(Env(x*), names, arg, body)) , Val(v))  << code -> { Env e2 = addRecs(`names, `env);
                                                                                  return P.mkP((CODE_TYPE)`body , (ENV_TYPE)`Env(x*, EnvAssoc(arg, v), e2*));
                                                                                }
              LetRec(x,Val(Closure(Env(e*), Names(n*), y, b)), body) << code -> { return P.mkP((CODE_TYPE)`body , (ENV_TYPE)`Env(envCasted*, EnvAssoc(x , Closure(Env(e*), Names(n*,x), y , b)))); }
            };
            throw new VisitFailure();
    ]])


    public static Visitor<ENV_TYPE,ENV_TYPE> getEnv(final Ref<ENV_TYPE> ref) {
        return Visitor.map ( FUNCTION( ENV_TYPE , ENV_TYPE , v , [[ ref.set(v) ; return v; ]] ) );
    }

    public static Visitor<ENV_TYPE,ENV_TYPE> setEnv(final Ref<ENV_TYPE> ref) {
        return Visitor.map ( FUNCTION( ENV_TYPE , ENV_TYPE , v , [[ return ref.value; ]] ) );
    }


    public static Visitor<P<CODE_TYPE,ENV_TYPE>,P<CODE_TYPE,ENV_TYPE>> app_eval = Visitor.[[shift]](
            FUNCTION( [[Visitor<P<CODE_TYPE,ENV_TYPE>,P<CODE_TYPE,ENV_TYPE>>]] , [[Visitor<P<CODE_TYPE,ENV_TYPE>,P<CODE_TYPE,ENV_TYPE>>]] , v , [[
                               Ref<ENV_TYPE> ref = new Ref<ENV_TYPE>();
                               Visitor<CODE_TYPE,CODE_TYPE> idc = new Id<CODE_TYPE>();
                               return idc.times(getEnv(ref)).seq(app_eval_inner).seq(v).seq(idc.times(setEnv(ref))).reset();
                          ]]));


    // Choice of Var or Fun
    public static Visitor<P<CODE_TYPE,ENV_TYPE>,P<CODE_TYPE,ENV_TYPE>> var_fun_app = var_iter.trace("var_iter").or(fun.trace("fun")).or(app_eval.trace("app_eval"));


    // Their global version
    public static Visitor<CONFIGURATION_TYPE,CONFIGURATION_TYPE> var_fun_app_global = var_fun_app.times(new Id<WORLD_TYPE>());


    /*
     * Memory management
     */

    VISITORMAP(assign , [[ P<CODE_TYPE,MEMASSOC_TYPE> ]], [[ P<CODE_TYPE,MEMASSOC_TYPE> ]] , p , [[

            CODE_TYPE     code  = p.left;
            MEMASSOC_TYPE assoc = p.right;

            if (!(code  instanceof Code    )) throw new VisitFailure();
            if (!(assoc instanceof MemAssoc)) throw new VisitFailure();

            /*
             Now that we are on Code * EnvAssoc, we can match.
             */
            %match {
                Bang(  Val(Ptr(i))        ) << code && MemAssoc(i, v) << assoc -> { return P.mkP((CODE_TYPE)`Val(v)     , assoc                     );  }
                Assign(Val(Ptr(i)), Val(v)) << code && MemAssoc(i, _) << assoc -> { return P.mkP((CODE_TYPE)`Val(Unit()), (MEMASSOC_TYPE)`MemAssoc(i, v));  }
            };
            throw new VisitFailure();
    ]])

    // Iteration over MemAssoc
    public static Visitor<P<CODE_TYPE,MEMORY_TYPE>,P<CODE_TYPE,MEMORY_TYPE>> assign_iter =
            (new Id<CODE_TYPE>()).times((new Right<Integer,MEM_TYPE>()).seq(Visitor.forSome)).seq(assign).reset();

    /*
     * Handling of Ref
     */

    VISITORMAP(ref, [[ P<CODE_TYPE,MEMORY_TYPE> ]] , [[ P<CODE_TYPE,MEMORY_TYPE> ]] , arg , [[

            CODE_TYPE   code      = arg.left ;
            MEMORY_TYPE memory    = arg.right ;
            Integer     next_addr = memory.left ;
            MEM_TYPE    mem       = memory.right ;

            if (!(code instanceof Code )) throw new VisitFailure();
            if (!(mem  instanceof Mem  )) throw new VisitFailure();

            %match {
                Ref(Val(v)) << code && Mem(x*) << mem -> { return P.mkP( (CODE_TYPE)`Val(Ptr(next_addr))
                                                                       , P.mkP( next_addr + 1
                                                                              , (MEM_TYPE)`Mem(x*, MemAssoc(next_addr, v))
                                                                              )
                                                                       ) ;
                                                         }
            };
            throw new VisitFailure();
    ]])


    // Choice of Ref or Assign
    public static Visitor<P<CODE_TYPE,MEMORY_TYPE>,P<CODE_TYPE,MEMORY_TYPE>> ref_or_assign = ref.trace("ref").or(assign_iter.trace("assign_iter"));

    // Their global version
    public static Visitor<CONFIGURATION_TYPE,CONFIGURATION_TYPE> ref_or_assign_global =
            (new Left<CODE_TYPE,ENV_TYPE>()).times(new Left<MEMORY_TYPE,INPUTS_OUTPUTS>()).seq(ref_or_assign).reset();


    /*
     * Inputs and Outputs
     */


    VISITORMAP(inputs, [[ P<CODE_TYPE,INPUTS_TYPE> ]] , [[ P<CODE_TYPE,INPUTS_TYPE> ]] , p , [[

            CODE_TYPE    code  = p.left;
            INPUTS_TYPE inputs = p.right;

            if (!(code   instanceof Code    )) throw new VisitFailure();
            if (!(inputs instanceof Inputs  )) throw new VisitFailure();

            /*
             Now that we are on Code * EnvAssoc, we can match.
             */
            %match {
                Read() << code && Inputs(v,x*) << inputs -> { return P.mkP((CODE_TYPE)`Val(v) , (INPUTS_TYPE)`Inputs(x*) );  }
            };
            throw new VisitFailure();
    ]])

    // Its version on INPUTS_OUTPUTS
    public static Visitor<P<CODE_TYPE,INPUTS_OUTPUTS>,P<CODE_TYPE,INPUTS_OUTPUTS>> inputs_up =
       (new Id<CODE_TYPE>()).times(new Left<INPUTS_TYPE,OUTPUTS_TYPE>()).seq(inputs.trace("inputs")).reset();


    VISITORMAP(outputs , [[ P<CODE_TYPE,OUTPUTS_TYPE> ]] , [[ P<CODE_TYPE,OUTPUTS_TYPE> ]] , p , [[

            CODE_TYPE    code    = p.left;
            OUTPUTS_TYPE outputs = p.right;

            if (!(code    instanceof Code     )) throw new VisitFailure();
            if (!(outputs instanceof Outputs  )) throw new VisitFailure();

            /*
             Now that we are on Code * EnvAssoc, we can match.
             */
            %match {
                Write(Val(v)) << code && Outputs(x*) << outputs -> { return P.mkP((CODE_TYPE)`Val(Unit()) , (OUTPUTS_TYPE)`Outputs(x*,v) );  }
            };
            throw new VisitFailure();
    ]])

    // Its version on INPUTS_OUTPUTS
    public static Visitor<P<CODE_TYPE,INPUTS_OUTPUTS>,P<CODE_TYPE,INPUTS_OUTPUTS>> outputs_up =
            (new Id<CODE_TYPE>()).times(new Right<INPUTS_TYPE,OUTPUTS_TYPE>()).seq(outputs.trace("outputs")).reset();


    // Inputs or Outputs
    public static Visitor<P<CODE_TYPE,INPUTS_OUTPUTS>,P<CODE_TYPE,INPUTS_OUTPUTS>> inputs_or_outputs = inputs_up.trace("inputs_up").or(outputs_up.trace("outputs_up"));

    // Their global version
    public static Visitor<CONFIGURATION_TYPE,CONFIGURATION_TYPE> inputs_or_outputs_global =
            (new Left<CODE_TYPE,ENV_TYPE>()).times(new Right<MEMORY_TYPE,INPUTS_OUTPUTS>()).seq(inputs_or_outputs).reset();


    // All together, one step
    public static Visitor<CONFIGURATION_TYPE,CONFIGURATION_TYPE> all_once =
            code_all_global.or(var_fun_app_global).or(ref_or_assign_global).or(inputs_or_outputs_global);

    // All together, nornal form
    public static Visitor<CONFIGURATION_TYPE,CONFIGURATION_TYPE> all_normal =
            Visitor.repeat(all_once);


    public static CONFIGURATION_TYPE programToConfiguration(Code program, Inputs inputs) {
        ENV_TYPE            empty_env     = `Env();
        CODE_ENV            code_env      = P.mkP( (CODE_TYPE)program , empty_env);

        MEM_TYPE            empty_mem     = `Mem();
        MEMORY_TYPE         empty_memory  = P.mkP(0, empty_mem);

        OUTPUTS_TYPE        empty_outputs = `Outputs();
        INPUTS_OUTPUTS      empty_io      = P.mkP((INPUTS_TYPE)inputs,empty_outputs);

        WORLD_TYPE          empty_world   = P.mkP(empty_memory, empty_io);

        return P.mkP(code_env, empty_world);
    }

    public static void run() throws VisitFailure {
        System.out.println("<MiniML>\n\n");

        Code  zero       = `Val(Int(0));
        Code  one        = `Val(Int(1));
        Code  n          = `Var("n");
        Code  fact       = `Var("fact");

        Code  valcounter = `Bang(Var("counter"));

        Code  factfun  = `Fun("n", Seq( Assign( Var( "counter" )
                                              , BinOp( valcounter , Add() , one)
                                              )
                                      , If( BinOp( n , LE() , one )
                                          , one
                                          , BinOp( App( Var("fact") , BinOp( n , Sub() , one) )
                                                 , Mult()
                                                 , n
                                                 )
                                          )
                                      )
                             );

        Code   in      = `Write(App(fact, Read()));

        Code   factDcl = `LetRec("fact", factfun , Seq( Seq(in,in) , Write(valcounter)));
        Code   program = `Let( "counter" , Ref(zero) , factDcl);
        Inputs inputs  = `Inputs(Int(5),Int(7));

        showresult(all_normal.visit(programToConfiguration(program,inputs)));
        System.out.println("</MiniML>\n\n");
    }

}
