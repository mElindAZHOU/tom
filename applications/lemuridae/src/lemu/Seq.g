
grammar Seq;

options {
    output=AST;
    ASTLabelType=CommonTree; 
}

tokens { FAPPL; APPL; VARLIST; RRULEALONE; NRULEALONE; RULEINDUCT; RULECTOR; 
    TYPELIST; RULECTORARITYZERO; VOIDRULEINDUCT; CTORLIST; LABELLEDTYPE; }

@parser::header {
  package lemu;
}

@lexer::header {
  package lemu;
}

pred: implpred (EQUIV^ pred)*;

implpred: orpred (IMPL^ pred)*;

orpred: andpred (OR^ pred)*;

andpred: negpred (AND^ pred)*;

negpred: NOT^ forallpred | forallpred;

forallpred: FORALL^ varlist COMMA! pred
          | EXISTS^ varlist COMMA! pred
          | LPAREN! pred RPAREN!
          | atom
          ;

atom: appl
    | BOTTOM
    | TOP
    ;

appl : ID LPAREN term_list  RPAREN -> ^(APPL ID term_list)
       | ID -> ^(APPL ID)
       ;

varlist: ID+  -> ^(VARLIST ID)+;
term_list: term (COMMA^ term)*; 
term: minterm (PLUS^ minterm)*;
minterm: mterm (MINUS^ mterm)* ;
mterm: divterm (TIMES^ divterm)*;

divterm: fterm (DIV^ fterm)*
       ;
      
fterm : LPAREN! term RPAREN!
      | funappl
      | ID
      | NUMBER
      ;

funappl : ID LPAREN term_list RPAREN -> ^(FAPPL ID term_list)
        | ID LPAREN RPAREN -> ^(FAPPL ID)
        ;


// points d'entree pour les programmes

command: PROOF^ ID COLUMN! pred DOT!
       | RRULE^ atom ARROW! pred DOT!
       | RRULE^ ID DOT!
       | TRULE^ term ARROW! term DOT!
       | TRULE^ ID DOT!
       | PRULE^ atom ARROW! pred DOT!
       | PRULE^ ID DOT!
       | NORMALIZE! TERM^ term DOT!
       | NORMALIZE! PROP^ pred DOT!
       | DISPLAY^ ID DOT!
       | PROOFTERM^ ID DOT!
       | QUIT DOT!
       | REINIT DOT!
       | PROOFCHECK^ ID DOT!
       | PRINT^ ID DOT!
       | RESUME^ ID DOT!
       | GIBBER DOT!
       | IMPORT^ PATH DOT!
       | INDUCTIVER^ rule1 DOT!
       | INDUCTIVE^ rule1 DOT!
       | EOF
       ;

proofcommand: FOCUS^ ID DOT!
            | APPLY^ ID DOT!
            | RRULE^ NUMBER DOT!
            | NRULE^ NUMBER DOT!
            | FOLD^ DOT!
            | ASSUME^ ID DOT!
            | RRULE DOT -> ^(RRULEALONE) 
            | NRULE DOT -> ^(NRULEALONE) 
            | CUT^ pred DOT!
            | THEOREM^ ID DOT!
            | NORMALIZE^ DOT!
            | ASKRULES DOT!
            | DISPLAY DOT!
            | ID DOT!
            | QUIT DOT!
            | REINIT DOT!
            | ABORT DOT!
            | EOF
            ;

start1: pred DOT! ;
start2: term DOT! ;
ident: ID DOT! ;

/* --- stagiaires -- */
rule1: ID AFFECT ctor_list -> ^(RULEINDUCT ID ctor_list)
     | ID AFFECT -> ^(VOIDRULEINDUCT ID) ;

ctor_list: ctor (PIPE ctor)* -> ^(CTORLIST ctor)+;

ctor: ID LPAREN type_list RPAREN -> ^(RULECTOR ID type_list)
    | ID LPAREN RPAREN -> ^(RULECTORARITYZERO ID) ;

type_list: type (COMMA type)* -> ^(TYPELIST type)+;

type: ID COLUMN ID -> ^(LABELLEDTYPE ID ID);
/* ----------------- */

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;

COMMENT
    :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

LPAREN : '(' ;
RPAREN : ')' ;
SEQ : '|-';
IMPL : '=>';
EQUIV : '<=>';
OR : '\\/' ;
AND :  '/\\' ;
NOT : '!'
    | '^'
    | '~' ;
DOT : '.' ;
COMMA : ',' ;
FORALL  : '\\A' | 'forall';
EXISTS : '\\E' | 'exists';
BOTTOM : '\\B' | 'False';
TOP: '\\T' | 'True';
ARROW : '->';

/* -- stagiaires -- */
PIPE : '|';
AFFECT : '=';
/* ----------------*/

COLUMN: ':';
TIMES: '*';
PLUS: '+';
DIV: '/';
MINUS: '-';
GT: '>';
LT: '<';

PROOF: 'proof';
RRULE: 'rule';
NRULE: 'narrow';
TRULE: 'termrule';
PRULE: 'proprule';
FOCUS: 'focus';
ASKRULES: 'showrules';
CUT: 'cut';
DISPLAY: 'display';
PROOFTERM: 'proofterm';
QUIT: 'quit';
REINIT: 'init';
THEOREM: 'theorem';
PRINT: 'print';
PROOFCHECK: 'proofcheck';
ABORT: 'abort';
RESUME: 'resume';
GIBBER: 'gibber';
IMPORT: 'import';
NORMALIZE: 'reduce';
FOLD: 'fold';
ASSUME: 'assume';
TERM: 'term';
PROP: 'proposition';
INDUCTIVER: 'recinductive';
INDUCTIVE: 'inductive';
APPLY: 'apply';

ID : ('_'|'A'..'Z'|'a'..'z')('_'|'A'..'Z'|'a'..'z'|'0'..'9')*;
NUMBER: ('0'..'9')+;
PATH:  '\"'((~'\"')+)'\"';
