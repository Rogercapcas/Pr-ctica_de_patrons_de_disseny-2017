/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleTests;

import common.*;
import simple.*;
import Factories1.*;
import Implementation.*;
import Interfaces.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author roger
 */
public class ContainerTest {
    
    private Injector injector;
    
    @Before
    public void FirstTest(){
        injector = new Container();
    }
        
    @Test    
    public void getInstanceOfFactoryD1() throws DependencyException{
        System.out.println("************ Starting Test0 ************");
        System.out.println("Testing FactoryD1 is correctly registered and get a instance of it.");
        injector.registerConstant("I", 42);
        injector.registerFactory("D", new FactoryD1(), "I");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        ImplementationD1 d1 = (ImplementationD1) d;
        assertThat(d1.i, is(42));
        System.out.println("************ End of Test0 **************");
        System.out.println("");
    }
    
    @Test    
    public void getInstanceOfFactoryC1() throws DependencyException{
        System.out.println("************ Starting Test1 ************");
        System.out.println("Testing FactoryC1 is correctly registered and get a instance of it.");
        injector.registerConstant("S", "patata");
        injector.registerFactory("C", new FactoryC1(), "S");
        InterfaceC c = (InterfaceC) injector.getObject("C");
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        ImplementationC1 c1 = (ImplementationC1) c;
        assertThat(c1.s, is("patata"));
        System.out.println("************ End of Test1 **************");
        System.out.println("");
    }
    
    @Test    
    public void getInstaceOfFactoryB1() throws DependencyException{
        System.out.println("************ Starting Test2 ************");
        System.out.println("Testing FactoryB1 is correctly registered and get a instance of it.");
        injector.registerConstant("I", 42);
        injector.registerFactory("D", new FactoryD1(), "I");
        injector.registerFactory("B", new FactoryB1(), "D");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        InterfaceB b = (InterfaceB) injector.getObject("B");
        assertThat(b, is(instanceOf(ImplementationB1.class)));
        ImplementationB1 b1 = (ImplementationB1) b;
        assertThat(b1.d, is(instanceOf(ImplementationD1.class)));
        System.out.println("************ End of Test2 **************");
        System.out.println("");
    }
    
    @Test  
    public void getInstanceOfFactoryA1() throws DependencyException{
        System.out.println("************ Starting Test3 ************");
        System.out.println("Testing FactoryA1 is correctly registered and get a instance of it.");
        registConstances();
        injector.registerFactory("D", new FactoryD1(), "I");
        injector.registerFactory("C", new FactoryC1(), "S");
        injector.registerFactory("B", new FactoryB1(), "D");
        injector.registerFactory("A", new FactoryA1(), "B","C");
        InterfaceA a = (InterfaceA) injector.getObject("A");
        assertThat(a, is(instanceOf(ImplementationA1.class)));
        ImplementationA1 a1 = (ImplementationA1) a;
        assertThat(a1.b, is(instanceOf(ImplementationB1.class)));
        assertThat(a1.c, is(instanceOf(ImplementationC1.class)));
        System.out.println("************ End of Test3 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void creationFactoryA1WithoutConstant() throws DependencyException{
        System.out.println("************ Starting Test4 ************");
        System.out.println("Testing cration a FactoryA1 instance without a constant needed.");
        injector.registerConstant("S", "patata");
        injector.registerFactory("C", new FactoryC1(), "S");
        injector.registerFactory("B", new FactoryB1(), "D");
        injector.registerFactory("A", new FactoryA1(), "B","C");
        InterfaceA a = (InterfaceA) injector.getObject("A");
        System.out.println("************ End of Test4 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void creationFactoryB1WithBadArguments() throws DependencyException{
        System.out.println("************ Starting Test5 ************");
        System.out.println("Testing cration a FactoryB1 instance with bad arguments.");
        registConstances();
        injector.registerFactory("B", new FactoryB1(), "S");
        InterfaceB b = (InterfaceB) injector.getObject("B");
        System.out.println("************ End of Test5 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void creationFactoryA1withBadArguments() throws DependencyException{
        System.out.println("************ Starting Test6 ************");
        System.out.println("Testing cration a FactoryA1 instance with bad arguments.");
        registConstances();
        injector.registerFactory("D", new FactoryD1(), "I");
        injector.registerFactory("A", new FactoryA1(), "I", "S");
        InterfaceA a = (InterfaceA) injector.getObject("A");
        System.out.println("************ End of Test6 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void registerTwoTimesSameConstant() throws DependencyException{
        System.out.println("************ Starting Test7 ************");
        System.out.println("Testing to register two times the same constant.");
        registConstances();
        registConstances();
        System.out.println("************ End of Test7 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void registerTwoTimesSameFactory() throws DependencyException{
        System.out.println("************ Starting Test8 ************");
        System.out.println("Testing to register two times the same factory.");
        registConstances();
        injector.registerFactory("D", new FactoryD1(), "I");
        injector.registerFactory("B", new FactoryB1(), "D");
        injector.registerFactory("B", new FactoryB1(), "D");
        System.out.println("************ End of Test8 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void getFactoryD1WithBadArguments() throws DependencyException{
        System.out.println("************ Starting Test9 ************");
        System.out.println("Testing FactoryD1 is correctly registeret but not with correct arguments and trying to get a instance of it.");
        String[] str = {"patata", "poma", "GEIADE"};
        injector.registerConstant("I", str);
        injector.registerFactory("D", new FactoryD1(), "I");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        System.out.println("************ End of Test9 **************");
        System.out.println("");
    }
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
///////////////////////////     Auxiliar method    /////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
  
    private void registConstances()throws DependencyException{
        injector.registerConstant("I", 42);
        injector.registerConstant("S", "patata");
    }
}
