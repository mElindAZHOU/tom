/*
 *
 * Gomantlr
 *
 * Copyright (c) 2006-2015, Universite de Lorraine, Inria
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
 * Eric Deplagne <Eric.Deplagne@loria.fr>
 *
 **/

package aterm2antlrgrammar.exceptions;

import antlrgrammar.AntlrGrammarAbstractType;

import antlrgrammar.types.AntlrWrongGrammar;
import antlrgrammar.types.AntlrGrammar;

public class AntlrWrongGrammarException extends RecognitionException {
    public AntlrWrongGrammarException(AntlrWrongGrammar t) {
        super(t);
    }

    public AntlrGrammarAbstractType getAntlrGrammarAbstractType() {
        return getAntlrGrammar();
    }

    public AntlrWrongGrammar getAntlrWrongGrammar() {
        return (AntlrWrongGrammar)getAntlrGrammarWrongAbstractType();
    }

    %include { ../../antlrgrammar/AntlrGrammar.tom }

    public AntlrGrammar getAntlrGrammar() {
        return `AntlrWrongGrammar(getAntlrWrongGrammar());
    }
}        
