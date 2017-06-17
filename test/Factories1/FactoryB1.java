/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories1;

import Implementation.ImplementationB1;
import Implementation.ImplementationD1;
import Interfaces.InterfaceD;
import common.DependencyException;
import simple.Factory;

/**
 *
 * @author roger
 */
public class FactoryB1 implements Factory {

    @Override
    public ImplementationB1 create(Object... parameters) throws DependencyException {
        InterfaceD d;
        try{
            d = (ImplementationD1) parameters[0];
        }catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            System.err.println("ERROR: Something went wrong when trying to create a instance of ImplementationB1.");
            throw new DependencyException(ex);
        }
        return new ImplementationB1(d);
    }
}
