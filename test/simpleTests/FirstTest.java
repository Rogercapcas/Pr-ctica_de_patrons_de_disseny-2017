/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleTests;

import Classes_Interfaces_And_Factories.*;
import common.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import simple.*;
/**
 *
 * @author roger
 */
public class FirstTest {
    
    public void pdfTest() throws DependencyException{
        Injector injector = new Container() ;
        injector.registerConstant("I", 42);
        injector.registerFacotry("D", new FactoryD1(), "I");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        ImplementationD1 d1 = (ImplementationD1) d;
        assertThat(d1.i, is(42));
    }
    
    
    
    
    
}
