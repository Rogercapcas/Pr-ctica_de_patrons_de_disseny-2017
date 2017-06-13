/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;

/**
 *
 * @author roger
 */
public interface Injector {
    
    void registerConstant(String name, Object value) throws DependencyException;
    
    void registerFacotry(String name, Factory creator, String... parameters) throws DependencyException;
    
    Object getObject(String name) throws DependencyException;
}
