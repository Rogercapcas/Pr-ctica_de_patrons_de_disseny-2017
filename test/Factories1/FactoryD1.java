/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories1;

import Implementation.ImplementationD1;
import common.DependencyException;
import simple.Factory;

/**
 *
 * @author roger
 */
public class FactoryD1 implements Factory {

    @Override
    public ImplementationD1 create(Object... parameters) throws DependencyException {
        int i;
        try{
            i = (int) parameters[0];
        }catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            System.err.println("ERROR: Something went wrong when trying to create a instance of ImplementationD1.");
            throw new DependencyException(ex);
        }
        return new ImplementationD1(i);
    }
    
}
