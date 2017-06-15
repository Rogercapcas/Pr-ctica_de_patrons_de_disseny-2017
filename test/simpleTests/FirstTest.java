/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleTests;

import Classes_Interfaces_And_Factories.*;
import common.*;
import simple.*;
import simple.Container;
import simple.Injector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
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
        Injector injector = new Container();
        injector.registerConstant("I", 42);
        injector.registerConstant("S", "patata");
        injector.registerFacotry("D", new FactoryD1(), "I");
        injector.registerFacotry("C", new FactoryC1(), "S");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        InterfaceC c = (InterfaceC) injector.getObject("C");
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        ImplementationD1 d1 = (ImplementationD1) d;
        ImplementationC1 c1 = (ImplementationC1) c;
        assertThat(d1.i, is(42));
        assertThat(c1.s, is("patata"));
        System.out.println("************ End of Test1 **************");
        System.out.println("");
    }
    
    @Test    
    public void Test2() throws DependencyException{
        System.out.println("************ Starting Test2 ************");
        Injector injector = new Container();
        injector.registerConstant("I", 42);
        injector.registerConstant("S", "patata");
        injector.registerFacotry("D", new FactoryD1(), "I");
        injector.registerFacotry("C", new FactoryC1(), "S");
        injector.registerFacotry("B", new FactoryB1(), "D");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        InterfaceC c = (InterfaceC) injector.getObject("C");
        InterfaceB b = (InterfaceB) injector.getObject("B");
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        assertThat(b, is(instanceOf(ImplementationB1.class)));
        ImplementationD1 d1 = (ImplementationD1) d;
        ImplementationC1 c1 = (ImplementationC1) c;
        ImplementationB1 b1 = (ImplementationB1) b;
        assertThat(d1.i, is(42));
        assertThat(c1.s, is("patata"));
        //assertThat(b1.d, is(d1));
        System.out.println("************ End of Test2 **************");
        System.out.println(" ");
    }
    
    
    
    
    
}
