/*
 * Created on 13 ao�t 2004
 *
 * Copyright (c) 2004-2007, Michael Moossen
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 *  - Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.  
 *  - Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  - Neither the name of the INRIA nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package fr.loria.protheo.psgenerator.representation;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author mmoossen
 */
public class UnorderedFactType extends FactType {
	private Set slotNames = Collections.checkedSet(new TreeSet(), String.class);
	
	public UnorderedFactType(String name, String comment, List slotNames) {
		super(name, comment, slotNames.size(), UNORDERED_TYPE);
		for (Iterator it = slotNames.iterator(); it.hasNext();) {
			String slotName = (String) it.next();
			if (slotName.indexOf(' ')>=0) {
				throw new IllegalArgumentException("for fact type " + name + ": " + slotName + " is an invalid slot name, should not contain spaces");
			}			
		}
		this.slotNames.addAll(slotNames);
	}
	
	public boolean hasSlot(String name) {
		return slotNames.contains(name);
	}
	
	public Set getSlotNames() {
		return Collections.unmodifiableSet(slotNames);
	}

	public String toString() {
		return getName()+slotNames;
	}
}
