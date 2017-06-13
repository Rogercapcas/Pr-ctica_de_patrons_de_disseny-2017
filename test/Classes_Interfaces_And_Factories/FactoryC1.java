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
public class FactoryC1 implements Factory{

    @Override
    public ImplementationC1 create(Object... parameters) throws DependencyException {
        String s;
        try{
            s = (String) parameters[0];
        }catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);            
        }
        return new ImplementationC1(s);
    }
        
    
}
