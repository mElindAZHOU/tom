/*
 [The "BSD licence"]
 Copyright (c) 2013 Terence Parr, Sam Harwell
 Copyright (c) 2017 Ivan Kochurkin (upgrade to Java 8)
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2016-2017, Universite de Lorraine
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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/

lexer grammar TomJavaLexer;

// Keywords

ABSTRACT:           'abstract';
ASSERT:             'assert';
BOOLEAN:            'boolean';
BREAK:              'break';
BYTE:               'byte';
CASE:               'case';
CATCH:              'catch';
CHAR:               'char';
CLASS:              'class';
CONST:              'const';
CONTINUE:           'continue';
DEFAULT:            'default';
DO:                 'do';
DOUBLE:             'double';
ELSE:               'else';
ENUM:               'enum';
EXTENDS:            'extends';
FINAL:              'final';
FINALLY:            'finally';
FLOAT:              'float';
FOR:                'for';
IF:                 'if';
GOTO:               'goto';
IMPLEMENTS:         'implements';
IMPORT:             'import';
INSTANCEOF:         'instanceof';
INT:                'int';
INTERFACE:          'interface';
LONG:               'long';
NATIVE:             'native';
NEW:                'new';
PACKAGE:            'package';
PRIVATE:            'private';
PROTECTED:          'protected';
PUBLIC:             'public';
RETURN:             'return';
SHORT:              'short';
STATIC:             'static';
STRICTFP:           'strictfp';
SUPER:              'super';
SWITCH:             'switch';
SYNCHRONIZED:       'synchronized';
THIS:               'this';
THROW:              'throw';
THROWS:             'throws';
TRANSIENT:          'transient';
TRY:                'try';
VOID:               'void';
VOLATILE:           'volatile';
WHILE:              'while';

//TOM Keywords

MATCH : '%match' ;
STRATEGY : '%strategy' ;
INCLUDE : '%include' ;
GOM : '%gom' -> pushMode(UNKNOWNBLOCK) ;
OP : '%op' ;
OPARRAY : '%oparray' ;
OPLIST : '%oplist' ;
TYPETERM : '%typeterm' ;
RULE : '%rule' -> pushMode(UNKNOWNBLOCK) ;

VISIT : 'visit' ;
IS_FSYM : 'is_fsym' ;
IS_SORT : 'is_sort' ;
MAKE : 'make' ;
MAKE_EMPTY : 'make_empty' ;
MAKE_APPEND : 'make_append' ;
MAKE_INSERT : 'make_insert' ;
GET_SLOT : 'get_slot' ;
GET_DEFAULT : 'get_default' ;
GET_ELEMENT : 'get_element' ;
GET_HEAD : 'get_head' ;
GET_TAIL : 'get_tail' ;
GET_SIZE : 'get_size' ;
IS_EMPTY : 'is_empty' ;
IMPLEMENT : 'implement' ;
EQUALS : 'equals' ;

WHEN : 'when' ;

// Literals

DECIMAL_LITERAL:    ('0' | [1-9] (Digits? | '_'+ Digits)) [lL]?;
HEX_LITERAL:        '0' [xX] [0-9a-fA-F] ([0-9a-fA-F_]* [0-9a-fA-F])? [lL]?;
OCT_LITERAL:        '0' '_'* [0-7] ([0-7_]* [0-7])? [lL]?;
BINARY_LITERAL:     '0' [bB] [01] ([01_]* [01])? [lL]?;
                    
FLOAT_LITERAL:      (Digits '.' Digits? | '.' Digits) ExponentPart? [fFdD]?
             |       Digits (ExponentPart [fFdD]? | [fFdD])
             ;

HEX_FLOAT_LITERAL:  '0' [xX] (HexDigits '.'? | HexDigits? '.' HexDigits) [pP] [+-]? Digits [fFdD]?;

BOOL_LITERAL:       'true'
            |       'false'
            ;

CHAR_LITERAL:       '\'' (~['\\\r\n] | EscapeSequence) '\'';

EXTENDED_CHAR_LITERAL: '\'' (~['\\\r\n] | EscapeSequence) (~['\\\r\n] | EscapeSequence)+ '\'' ;

STRING_LITERAL:     '"' (~["\\\r\n] | EscapeSequence)* '"';

NULL_LITERAL:       'null';

// Separators

LPAREN:             '(';
RPAREN:             ')';
LBRACE:             '{' -> pushMode(DEFAULT_MODE) ;
RBRACE:             '}' -> popMode ;
LBRACK:             '[';
RBRACK:             ']';
SEMI:               ';';
COMMA:              ',';
DOT:                '.';

// Operators

ASSIGN:             '=';
GT:                 '>';
LT:                 '<';
//BANG:               '!';
TILDE:              '~';
//QUESTION:           '?';
COLON:              ':';
EQUAL:              '==';
LE:                 '<=';
GE:                 '>=';
NOTEQUAL:           '!=';
AND:                '&&';
OR:                 '||';
INC:                '++';
DEC:                '--';
ADD:                '+';
SUB:                '-';
//MUL:                '*';
//DIV:                '/';
BITAND:             '&';
//BITOR:              '|';
CARET:              '^';
MOD:                '%';

ADD_ASSIGN:         '+=';
SUB_ASSIGN:         '-=';
MUL_ASSIGN:         '*=';
DIV_ASSIGN:         '/=';
AND_ASSIGN:         '&=';
OR_ASSIGN:          '|=';
XOR_ASSIGN:         '^=';
MOD_ASSIGN:         '%=';
LSHIFT_ASSIGN:      '<<=';
RSHIFT_ASSIGN:      '>>=';
URSHIFT_ASSIGN:     '>>>=';

//TOM Operators

LSHIFT : '<<' ;
STAR : '*' ; //JAVA->MUL
UNDERSCORE : '_' ;
ANTI : '!' ; //JAVA->BANG
//LMETAQUOTE : '%[' ;
//RMETAQUOTE : ']%' ;
//ATAT : '@@' ;

PIPE : '|'; //JAVA->BITOR
QMARK : '?'; //JAVA->QUESTION
DQMARK : '??';
SLASH : '/'; //JAVA->DIV
BACKSLASH : '\\';
BQUOTE : '`' ;

METAQUOTE : '%[' .*? ']%';

// Java 8 tokens

ARROW:              '->';
COLONCOLON:         '::';

// Additional symbols not defined in the lexical specification

AT:                 '@';
ELLIPSIS:           '...';

// Whitespace and comments

WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

// Identifiers

IDENTIFIER:         Letter LetterOrDigit*;

// Fragment rules

fragment ExponentPart
    : [eE] [+-]? Digits
    ;

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

fragment HexDigits
    : HexDigit ((HexDigit | '_')* HexDigit)?
    ;

fragment HexDigit
    : [0-9a-fA-F]
    ;

fragment Digits
    : [0-9] ([0-9_]* [0-9])?
    ;

fragment LetterOrDigit
    : Letter
    | [0-9]
    ;

fragment Letter
    : [a-zA-Z$_] // these are the "java letters" below 0x7F
    | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;
    
mode GOM_MODE;

GOMSTART : '{' -> pushMode(GOM_INSIDE) ;

GOM_WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
GOM_COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
GOM_LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

mode GOM_INSIDE;

HOOKSTART : '{' -> pushMode(SUBBLOCK) ;
GOMEND : '}' -> popMode, popMode ;

MODULE   : 'module';
IMPORTS  : 'imports';
GOM_PUBLIC   : 'public';
GOM_ABSTRACT : 'abstract';
SYNTAX   : 'syntax';
SORT     : 'sort';
OPERATOR : 'operator';
ATOM     : 'atom';
INNER    : 'inner';
OUTER    : 'outer';
NEUTRAL  : 'neutral';
BINDS    : 'binds';

GOM_COLON    : ':';
GOM_COMMA    : ',';
GOM_DOT      : '.';
GOM_LPAREN   : '(';
GOM_RPAREN   : ')';
GOM_STAR     : '*';
GOM_EQUAL   : '=';
ALT      : '|';
GOM_SEMI     : ';;';
LDIPLE   : '<';
RDIPLE   : '>';

JAVADOC :
  '/**' .*? '*/'
  ;

// hookTypes:
RULES : 'rules' -> pushMode(RULE_MODE);
GRAPHRULES : 'graphrules' -> pushMode(RULE_MODE);
AC : 'AC';
ACU : 'ACU';
AU : 'AU';
FL : 'FL';
FREE : 'Free';
HOOK_MAKE : 'make';
HOOK_MAKE_INSERT : 'make_insert';
HOOK_MAKE_EMPTY : 'make_empty';
HOOK_IMPORT : 'import';
HOOK_INTERFACE : 'interface';
BLOCK : 'block';
MAPPING : 'mapping';

ID : ('a'..'z' | 'A'..'Z')
     ('a'..'z' | 'A'..'Z' | '0'..'9' | '_' | '-')* ;
     
GOM_INSIDE_WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
GOM_INSIDE_COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
GOM_INSIDE_LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

mode RULE_MODE;

IDENTITY : 'Identity';
FAIL : 'Fail';

RULE_ARG : ('a'..'z' | 'A'..'Z')
           ('a'..'z' | 'A'..'Z' | '0'..'9' | '_' | '-')* ;
          
ARG_COMMA    : ',';
RULE_LPAREN   : '(';
RULE_RPAREN   : ')';

RULESTART : '{' -> pushMode(RULE_INSIDE) ;

RULE_WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
RULE_COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
RULE_LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

mode RULE_INSIDE;

RULEEND : '}' -> popMode, popMode ;

RULE_ARROW : '->';
AMPERSAND : '&';
RULE_UNDERSCORE : '_';
RULE_STAR : '*';
RULE_AT : '@';
RULE_COLON : ':';
LPAR : '(';
RPAR : ')';
RULE_COMMA : ',';
RULE_AND : '&&';
RULE_OR : '||';
NOT : '!';
RULE_EQUALS : '==';
NOTEQUALS : '!=';
LEQ : '<=';
MATCH_SYMBOL : '<<';
RULE_LT : '<';
GEQ : '>=';
RULE_GT : '>';
RULE_IF : 'if' ;

INTEGER:    ('0' | [1-9] (Digits? | '_'+ Digits)) [lL]?;
STRING : '"' (~["\\\r\n] | EscapeSequence)* '"';

RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INSIDE_WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
RULE_INSIDE_COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
RULE_INSIDE_LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);


mode UNKNOWNBLOCK;

BLOCKSTART : '{' -> pushMode(INSIDE) ;

OPTIONSTART : '(' ;
OPTIONEND : ')';

DMINUSID : '--' Letter* ;

UNKNOWNBLOCK_WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
UNKNOWNBLOCK_COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
UNKNOWNBLOCK_LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

mode INSIDE;

SUBBLOCKSTART : '{' -> pushMode(SUBBLOCK) ;
BLOCKEND : '}' -> popMode, popMode ;

ANY : . ; 

mode SUBBLOCK;

SUBSUBBLOCKSTART : '{' -> pushMode(SUBBLOCK) ;
SUBBLOCKEND : '}' -> popMode ;

SUB_ANY : . ; 
