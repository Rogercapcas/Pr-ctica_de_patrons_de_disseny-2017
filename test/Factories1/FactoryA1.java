/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories1;

import Implementation1.ImplementationA1;
import Implementation1.ImplementationB1;
import Implementation1.ImplementationC1;
import Interfaces1.InterfaceB;
import Interfaces1.InterfaceC;
import common.DependencyException;
import simple.Factory;

/**
 *
 * @author roger
 */
public class FactoryA1 implements Factory {

    @Override
    public ImplementationA1 create(Object... parameters) throws DependencyException {
        InterfaceB b;
        InterfaceC c;
        try{
            b = (ImplementationB1) parameters[0];
            c = (ImplementationC1) parameters[1];
        }catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            System.err.println("ERROR: Something went wrong when trying to create a instance of ImplementationA1.");
            throw new DependencyException(ex);
        }
        return new ImplementationA1(b, c);
    }
    
    
}