/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories2;

import Implementation1.ImplementationB1;
import Implementation1.ImplementationD1;
import Interfaces1.InterfaceD;
import common.DependencyException;
import complex.Factory;

/**
 *
 * @author roger
 */
public class FactoryB1 implements Factory <ImplementationB1> {

    @Override
    public ImplementationB1 create(Object... parameters) throws DependencyException {
        InterfaceD d;
        try{
            d = (ImplementationD1) parameters[0];
        }catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            System.err.println("ERROR: Something went wrong when trying to create a instance of complex ImplementationB1.");
            throw new DependencyException(ex);
        }
        return new ImplementationB1(d);
    }
    
}
