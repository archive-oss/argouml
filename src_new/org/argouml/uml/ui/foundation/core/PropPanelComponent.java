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


// File: PropPanelComponent.java
// Classes: PropPanelComponent
// Original Author: 5eichler@informatik.uni-hamburg.de
// $Id$

// 21 Mar 2002: Jeremy Bennett (mail@jeremybennett.com). Changed to use the
// labels "Generalizes:" and "Specializes:" for inheritance.

// 4 Apr 2002: Jeremy Bennett (mail@jeremybennett.com). Labels corrected to
// "Generalizations:" and "Specializations". Layout of prop panel balanced
// better.


package org.argouml.uml.ui.foundation.core;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.argouml.application.api.Argo;
import org.argouml.uml.ui.PropPanelButton;
import org.argouml.uml.ui.UMLCheckBox;
import org.argouml.uml.ui.UMLReflectionBooleanProperty;

import ru.novosoft.uml.foundation.core.MComponent;


public class PropPanelComponent extends PropPanelClassifier {

  ////////////////////////////////////////////////////////////////
  // contructors
  public PropPanelComponent() {
    super("Component", _componentIcon, 2);

    Class mclass = MComponent.class;

    addCaption(Argo.localize("UMLMenu", "label.name"),1,0,0);
    addField(getNameTextField(),1,0,0);

    addCaption(Argo.localize("UMLMenu", "label.stereotype"),2,0,0);
    addField(getStereotypeBox(),2,0,0);

    addCaption(Argo.localize("UMLMenu", "label.namespace"),3,0,0);
    addField(getNamespaceComboBox(),3,0,0);

    addCaption(Argo.localize("UMLMenu", "label.modifiers"),4,0,1);
    JPanel modifiersPanel = new JPanel(new GridLayout(0,3));
    modifiersPanel.add(new UMLCheckBox(Argo.localize("UMLMenu", "checkbox.abstract-lc"),this,new UMLReflectionBooleanProperty("isAbstract",mclass,"isAbstract","setAbstract")));
    modifiersPanel.add(new UMLCheckBox(Argo.localize("UMLMenu", "checkbox.final-lc"),this,new UMLReflectionBooleanProperty("isLeaf",mclass,"isLeaf","setLeaf")));
    modifiersPanel.add(new UMLCheckBox(localize("root"),this,new UMLReflectionBooleanProperty("isRoot",mclass,"isRoot","setRoot")));
    addField(modifiersPanel,4,0,0);

    addCaption("Generalizations:",0,1,1);
    addField(getGeneralizationScroll(),0,1,1);

    addCaption("Specializations:",1,1,1);
    addField(getSpecializationScroll(),1,1,1);

    new PropPanelButton(this,buttonPanel,_navUpIcon, Argo.localize("UMLMenu", "button.go-up"),"navigateUp",null);   
    new PropPanelButton(this,buttonPanel,_deleteIcon,localize("Delete component"),"removeElement",null);
  }

    
} /* end class PropPanelComponent */



