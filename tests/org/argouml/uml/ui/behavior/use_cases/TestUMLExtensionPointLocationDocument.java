// $Id$
// Copyright (c) 1996-2004 The Regents of the University of California. All
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

package org.argouml.uml.ui.behavior.use_cases;

import javax.swing.text.BadLocationException;

import junit.framework.TestCase;

import org.argouml.model.Model;

import ru.novosoft.uml.MFactoryImpl;
import ru.novosoft.uml.behavior.use_cases.MExtensionPoint;

/**
 * @since Nov 3, 2002
 * @author jaap.branderhorst@xs4all.nl
 */
public class TestUMLExtensionPointLocationDocument extends TestCase {

    private MExtensionPoint elem = null;
    private int oldEventPolicy;
    private UMLExtensionPointLocationDocument model;
    
    /**
     * Constructor for TestUMLExtensionPointLocationDocument.
     * @param arg0 is the name of the test case.
     */
    public TestUMLExtensionPointLocationDocument(String arg0) {
        super(arg0);
    }

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        elem = Model.getUseCasesFactory().createExtensionPoint();
        oldEventPolicy = MFactoryImpl.getEventPolicy();
        MFactoryImpl.setEventPolicy(MFactoryImpl.EVENT_POLICY_IMMEDIATE); 
        model = new UMLExtensionPointLocationDocument();
        model.setTarget(elem);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        Model.getUmlFactory().delete(elem);
        elem = null;
        MFactoryImpl.setEventPolicy(oldEventPolicy);
        model = null;
    }
    
    /**
     * Test setLocation().
     * 
     * @throws BadLocationException when the lacation is refused
     */
    public void testSetName()
	throws BadLocationException
    {
        elem.setLocation("test");
	assertEquals("test", model.getText(0, model.getLength()));
    }
    
    /**
     * Test setLocation() with null argument.
     * 
     * @throws BadLocationException when the lacation is refused
     */
    public void testRemoveName()
	throws BadLocationException
    {
        elem.setLocation("test");
        elem.setLocation(null);
	assertEquals("", model.getText(0, model.getLength()));
    }
    
    /**
     * Test insertString().
     * 
     * @throws BadLocationException when the lacation is refused
     */
    public void testInsertString()
	throws BadLocationException
    {
	model.insertString(0, "test", null);
        assertEquals("test", elem.getLocation());
    }
    
    /**
     * Test remove().
     * 
     * @throws BadLocationException when the lacation is refused
     */
    public void testRemoveString()
	throws BadLocationException
    {
	model.insertString(0, "test", null);
	model.remove(0, model.getLength());
        assertEquals("", elem.getLocation());
    }
    
    /**
     * Test insertString().
     * 
     * @throws BadLocationException when the lacation is refused
     */
    public void testAppendString()
	throws BadLocationException
    {
        elem.setLocation("test");
	model.insertString(model.getLength(), "test", null);
        assertEquals("testtest", elem.getLocation());
    }
    
    /**
     * Test inserting a string in the middle.
     * 
     * @throws BadLocationException when the lacation is refused
     */
    public void testInsertStringHalfway()
	throws BadLocationException
    {
        elem.setLocation("test");
	model.insertString(1, "test", null);
        assertEquals("ttestest", elem.getLocation());
    }
    
    /**
     * Test remove a string from the middle.
     * 
     * @throws BadLocationException when the lacation is refused
     */
    public void testRemoveStringHalfway() 
	throws BadLocationException
    {
        elem.setLocation("test");
	model.remove(1, model.getLength() - 2);
        assertEquals("tt", elem.getLocation());
    }

}
