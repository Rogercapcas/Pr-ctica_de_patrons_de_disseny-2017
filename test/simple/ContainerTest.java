/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roger
 */
public class ContainerTest {
    
    public ContainerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerConstant method, of class Container.
     */
    @Test
    public void testRegisterConstant() throws Exception {
        System.out.println("registerConstant");
        String name = "";
        Object value = null;
        Container instance = new Container();
        instance.registerConstant(name, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObject method, of class Container.
     */
    @Test
    public void testGetObject() throws Exception {
        System.out.println("getObject");
        String name = "";
        Container instance = new Container();
        Object expResult = null;
        Object result = instance.getObject(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerFacotry method, of class Container.
     */
    @Test
    public void testRegisterFacotry() throws Exception {
        System.out.println("registerFacotry");
        String name = "";
        Factory creator = null;
        String[] parameters = null;
        Container instance = new Container();
        instance.registerFacotry(name, creator, parameters);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
