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

grammar TomJava;

start : (PACKAGE QID ';')?
//.* EOF
      ;

PACKAGE : 'package' ;
QID : ID ('.' ID)* ;
fragment ID : [a-zA-Z]([a-zA-Z0-9])* ;

MLCOMMENT : '/*' .*? '*/' -> skip;
SLCOMMENT : '//' ~[\r\n]* -> skip;
WS : [ \r\t\n]+ -> skip ;
ANY : . -> skip;
