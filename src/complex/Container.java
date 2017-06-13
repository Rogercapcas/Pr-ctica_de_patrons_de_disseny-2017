/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complex;

import common.DependencyException;
import java.util.HashMap;
import simple.Factory;

/**
 *
 * @author roger
 */
public class Container implements Injector {
    
    @Override
    public <E> void registerConstant(Class<E> name, E value) throws DependencyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <E> E getObject(Class<E> name) throws DependencyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <E> void registerFactory(Class<E> name, Object creator, Class<?>... parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
