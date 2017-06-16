/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleTests;

import Classes_Interfaces_And_Factories.*;
import common.*;
import simple.Container;
import simple.Injector;

import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
/**
 *
 * @author roger
 */
public class FirstTest {
    @Test    
    public void Test0() throws DependencyException{
        System.out.println("************ Starting Test0 ************");
        System.out.println("Testing FactoryD1 is correctly registeret and get a instance of it.");
        Injector injector = new Container() ;
        injector.registerConstant("I", 42);
        injector.registerFacotry("D", new FactoryD1(), "I");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        ImplementationD1 d1 = (ImplementationD1) d;
        assertThat(d1.i, is(42));
        System.out.println("************ End of Test0 **************");
        System.out.println("");
    }
    
    @Test    
    public void Test1() throws DependencyException{
        System.out.println("************ Starting Test1 ************");
        System.out.println("Testing FactoryC1 is correctly registeret and get a instance of it.");
        Injector injector = new Container();
        injector.registerConstant("S", "patata");
        injector.registerFacotry("C", new FactoryC1(), "S");
        InterfaceC c = (InterfaceC) injector.getObject("C");
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        ImplementationC1 c1 = (ImplementationC1) c;
        assertThat(c1.s, is("patata"));
        System.out.println("************ End of Test1 **************");
        System.out.println("");
    }
    
    @Test    
    public void Test2() throws DependencyException{
        System.out.println("************ Starting Test2 ************");
        System.out.println("Testing FactoryB1 is correctly registeret and get a instance of it.");
        Injector injector = new Container();
        injector.registerConstant("I", 42);
        injector.registerFacotry("D", new FactoryD1(), "I");
        injector.registerFacotry("B", new FactoryB1(), "D");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        InterfaceB b = (InterfaceB) injector.getObject("B");
        assertThat(b, is(instanceOf(ImplementationB1.class)));
        ImplementationB1 b1 = (ImplementationB1) b;
        assertThat(b1.d, is(instanceOf(ImplementationD1.class)));
        System.out.println("************ End of Test2 **************");
        System.out.println("");
    }
    
    @Test    
    public void Test3() throws DependencyException{
        System.out.println("************ Starting Test3 ************");
        System.out.println("Testing FactoryA1 is correctly registeret and get a instance of it.");
        Injector injector = new Container();
        injector.registerConstant("I", 42);
        injector.registerConstant("S", "patata");
        injector.registerFacotry("D", new FactoryD1(), "I");
        injector.registerFacotry("C", new FactoryC1(), "S");
        injector.registerFacotry("B", new FactoryB1(), "D");
        injector.registerFacotry("A", new FactoryA1(), "B","C");
        InterfaceA a = (InterfaceA) injector.getObject("A");
        assertThat(a, is(instanceOf(ImplementationA1.class)));
        ImplementationA1 a1 = (ImplementationA1) a;
        assertThat(a1.b, is(instanceOf(ImplementationB1.class)));
        assertThat(a1.c, is(instanceOf(ImplementationC1.class)));
        System.out.println("************ End of Test3 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void Test4() throws DependencyException{
        System.out.println("************ Starting Test4 ************");
        System.out.println("Testing cration a FactoryA1 instance without a constant needed.");
        Injector injector = new Container();
        injector.registerConstant("S", "patata");
        injector.registerFacotry("C", new FactoryC1(), "S");
        injector.registerFacotry("B", new FactoryB1(), "D");
        injector.registerFacotry("A", new FactoryA1(), "B","C");
        InterfaceA a = (InterfaceA) injector.getObject("A");
        System.out.println("************ End of Test4 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void Test5() throws DependencyException{
        System.out.println("************ Starting Test5 ************");
        System.out.println("Testing cration a FactoryB1 instance with bad arguments.");
        Injector injector = new Container();
        injector.registerConstant("I", 42);
        injector.registerConstant("S", "patata");
        injector.registerFacotry("D", new FactoryD1(), "I");
        injector.registerFacotry("B", new FactoryB1(), "S");
        injector.registerFacotry("A", new FactoryA1(), "I", "S");
        InterfaceB b = (InterfaceB) injector.getObject("B");
        System.out.println("************ End of Test5 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void Test6() throws DependencyException{
        System.out.println("************ Starting Test6 ************");
        System.out.println("Testing cration a FactoryA1 instance with bad arguments.");
        Injector injector = new Container();
        injector.registerConstant("I", 42);
        injector.registerConstant("S", "patata");
        injector.registerFacotry("D", new FactoryD1(), "I");
        injector.registerFacotry("A", new FactoryA1(), "I", "S");
        InterfaceA a = (InterfaceA) injector.getObject("A");
        System.out.println("************ End of Test6 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void Test7() throws DependencyException{
        System.out.println("************ Starting Test7 ************");
        System.out.println("Testing to register two times the same constant.");
        Injector injector = new Container();
        injector.registerConstant("I", 42);
        injector.registerConstant("I", 4);
        System.out.println("************ End of Test7 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void Test8() throws DependencyException{
        System.out.println("************ Starting Test8 ************");
        System.out.println("Testing to register two times the same factory.");
        Injector injector = new Container();
        injector.registerConstant("I", 42);
        injector.registerConstant("S", "patata");
        injector.registerFacotry("D", new FactoryD1(), "I");
        injector.registerFacotry("B", new FactoryB1(), "D");
        injector.registerFacotry("B", new FactoryB1(), "I");
        System.out.println("************ End of Test8 **************");
        System.out.println("");
    }
    
    @Test(expected = DependencyException.class)    
    public void Test9() throws DependencyException{
        System.out.println("************ Starting Test9 ************");
        System.out.println("Testing FactoryD1 is correctly registeret but not with correct arguments and trying to get a instance of it.");
        Injector injector = new Container();
        String[] str = {"patata", "poma", "GEIADE"};
        injector.registerConstant("I", str);
        injector.registerFacotry("D", new FactoryD1(), "I");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        System.out.println("************ End of Test9 **************");
        System.out.println("");
    }
    
}
