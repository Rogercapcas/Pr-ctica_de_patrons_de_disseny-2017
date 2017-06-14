/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;
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
public class FactoryTest {
    
    public FactoryTest() {
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
     * Test of create method, of class Factory.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Object[] parameters = null;
        Factory instance = new FactoryImpl();
        Object expResult = null;
        Object result = instance.create(parameters);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class FactoryImpl implements Factory {

        public Object create(Object[] parameters) throws DependencyException {
            return null;
        }
    }
    
}
