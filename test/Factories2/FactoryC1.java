/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories2;

import Implementation.ImplementationC1;
import common.DependencyException;
import complex.Factory;

/**
 *
 * @author roger
 */
public class FactoryC1 implements Factory <ImplementationC1> {
    @Override
    public ImplementationC1 create(Object... parameters) throws DependencyException{
        String s;
        try{
            s = (String) parameters[0];
        }catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            System.err.println("ERROR: Something went wrong when trying to create a instance of complex ImplementationC1.");
            throw new DependencyException(ex);
        }
        return new ImplementationC1(s);
    }
}
