// Copyright (c) 1996-99 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

// $header$
package org.argouml.uml.ui.behavior.collaborations;

import java.util.Collection;
import java.util.Iterator;

import org.argouml.model.uml.behavioralelements.collaborations.CollaborationsHelper;
import org.argouml.uml.ui.UMLModelElementListModel2;
import org.argouml.uml.ui.UMLUserInterfaceContainer;
import ru.novosoft.uml.MElementEvent;
import ru.novosoft.uml.behavior.collaborations.MClassifierRole;
import ru.novosoft.uml.foundation.core.MClassifier;
import ru.novosoft.uml.foundation.core.MFeature;
import ru.novosoft.uml.foundation.core.MModelElement;

/**
 * @since Oct 4, 2002
 * @author jaap.branderhorst@xs4all.nl
 */
public class UMLClassifierRoleAvailableFeaturesListModel
    extends UMLModelElementListModel2 {

    /**
     * Constructor for UMLClassifierRoleAvailableFeaturesListModel.
     * @param container
     */
    public UMLClassifierRoleAvailableFeaturesListModel(UMLUserInterfaceContainer container) {
        super(container);
    }

    /**
     * @see org.argouml.uml.ui.UMLModelElementListModel2#buildModelList()
     */
    protected void buildModelList() {
        setAllElements(CollaborationsHelper.getHelper().allAvailableFeatures((MClassifierRole)getTarget()));
    }
    
    /**
     * @see ru.novosoft.uml.MElementListener#roleAdded(ru.novosoft.uml.MElementEvent)
     */
    public void roleAdded(MElementEvent e) {
        if (isValidRoleAdded(e)) {
            MClassifier clazz = (MClassifier)e.getAddedValue();
            Iterator it = clazz.getFeatures().iterator();
            while (it.hasNext()) {
                addElement(it.next());
            }
        }
    }

    /**
     * @see ru.novosoft.uml.MElementListener#roleRemoved(ru.novosoft.uml.MElementEvent)
     */
    public void roleRemoved(MElementEvent e) {
        if (isValidRoleRemoved(e)) {
            MClassifier clazz = (MClassifier)e.getRemovedValue();
            Iterator it = clazz.getFeatures().iterator();
            while (it.hasNext()) {
                removeElement(it.next());
            }
        }
    }

    /**
     * @see org.argouml.uml.ui.UMLModelElementListModel2#isValidRoleAdded(ru.novosoft.uml.MElementEvent)
     */
    protected boolean isValidRoleAdded(MElementEvent e) {
        Object elem = getChangedElement(e);
        if (elem instanceof MClassifier) {
            Collection availableFeatures = CollaborationsHelper.getHelper().allAvailableFeatures((MClassifierRole)getTarget());
            Iterator it = ((MClassifier)elem).getFeatures().iterator();
            while (it.hasNext()) {
                MFeature feature = (MFeature)it.next();
                if (availableFeatures.contains(feature)) return true;
            }
        }
        return false;
    }

}
