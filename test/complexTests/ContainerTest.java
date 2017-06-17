/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexTests;

import Factories2.*;
import Implementation1.*;
import Interfaces1.*;
import common.DependencyException;
import complex.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
    
    private Injector injector;
    
    @Before
    public void ContainerTest(){
        
        injector = new complex.Container();
    }
    
    
    @Test    
    public void getInstanceOfFactoryD1() throws DependencyException{
        System.out.println("************ Starting Test0 ************");
        System.out.println("Testing FactoryD1 is correctly registered and get a instance of it.");
        injector.registerConstant(Integer.class, 42);
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        InterfaceD d = (InterfaceD) injector.getObject(InterfaceD.class);
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
        injector.registerConstant(String.class, "patata");
        injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        InterfaceC c = (InterfaceC) injector.getObject(InterfaceC.class);
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
        injector.registerConstant(Integer.class, 42);
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), InterfaceD.class);
        InterfaceD d = (InterfaceD) injector.getObject(InterfaceD.class);
        InterfaceB b = (InterfaceB) injector.getObject(InterfaceB.class);
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
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), InterfaceD.class);
        injector.registerFactory(InterfaceA.class, new FactoryA1(), InterfaceB.class,InterfaceC.class);
        InterfaceA a = (InterfaceA) injector.getObject(InterfaceA.class);
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
        injector.registerConstant(String.class, "patata");
        injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), InterfaceD.class);
        injector.registerFactory(InterfaceA.class, new FactoryA1(), InterfaceB.class,InterfaceC.class);
        InterfaceA a = (InterfaceA) injector.getObject(InterfaceA.class);
        System.out.println("************ End of Test4 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void creationFactoryB1WithBadArguments() throws DependencyException{
        System.out.println("************ Starting Test5 ************");
        System.out.println("Testing cration a FactoryB1 instance with bad arguments.");
        registConstances();
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), String.class);
        injector.registerFactory(InterfaceA.class, new FactoryA1(), Integer.class, String.class);
        InterfaceB b = (InterfaceB) injector.getObject(InterfaceB.class);
        System.out.println("************ End of Test5 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void creationFactoryA1withBadArguments() throws DependencyException{
        System.out.println("************ Starting Test6 ************");
        System.out.println("Testing cration a FactoryA1 instance with bad arguments.");
        registConstances();
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        injector.registerFactory(InterfaceA.class, new FactoryA1(), Integer.class, String.class);
        InterfaceA a = (InterfaceA) injector.getObject(InterfaceA.class);
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
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), InterfaceD.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), Integer.class);
        System.out.println("************ End of Test8 **************");
        System.out.println("");
    }
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
///////////////////////////    Auxiliar methods    /////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
  
    private void registConstances()throws DependencyException{
        injector.registerConstant(Integer.class, 42);
        injector.registerConstant(String.class, "patata");
    }
    
}