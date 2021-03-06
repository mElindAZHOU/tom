/*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (C) 2000-2004 Inria Nancy, France.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Pierre-Etienne Moreau e-mail: Pierre-Etienne.Moreau@loria.fr Guillaume
 * Darmont e-mail: Guillaume.Darmont@free.fr
 *  
 */
package fr.loria.eclipse.jtom.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.ui.actions.AbstractOpenWizardAction;
import org.eclipse.ui.INewWizard;

public class OpenTomProjectWizardAction extends AbstractOpenWizardAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.wizards.AbstractOpenWizardAction#createWizard()
	 */
	protected INewWizard createWizard() throws CoreException {
		return new TomProjectCreationWizard();
	}

	protected boolean checkWorkspaceNotEmpty() {
		return true;
	}

}