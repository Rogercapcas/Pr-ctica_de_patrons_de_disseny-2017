/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes_Interfaces_And_Factories;

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
            System.out.println("Before int cast --> " + parameters[0]);
            i = (int) parameters[0];
            System.out.println("Successful parameters to int cast " + i);
        }catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            System.err.println("Error trying to cast parameters to int");
            throw new DependencyException(ex);
        }
        return new ImplementationD1(i);
    }
    
}
