/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;
import java.util.HashMap;

/**
 *
 * @author roger
 */
public class Container implements Injector {
    
    private HashMap<String,Object> registeredObjects;
    private HashMap<String,Object> FactoriesMap;
    private HashMap<String,Object> dependencesMap;
    
    public Container(){
        this.registeredObjects = new HashMap<>();
        this.FactoriesMap = new HashMap<>();
        this.dependencesMap = new HashMap<>();
    }

    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        if (this.registeredObjects.containsKey(name)){
            throw new DependencyException(name + " constant is already registered.");
        } else{
            this.registeredObjects.put(name, value); System.out.println("Key '" + name + "' registered with value '" + value + "' ");
        }
    }

    @Override
    public Object getObject(String name) throws DependencyException {
        if (this.registeredObjects.containsKey(name)){
            return this.registeredObjects.get(name);
        }
        else if(this.FactoriesMap.containsKey(name)){
            Factory creator;
            creator = (Factory) this.FactoriesMap.get(name);
            return creator.create(this.dependencesMap.get(name));
        }
        else{
            throw new DependencyException(name + " doesn't exists.");
        }        
    }
    
    @Override
    public void registerFacotry(String name, Factory creator, String... parameters) throws DependencyException{
        
        if (this.registeredObjects.containsKey(name)){
            throw new DependencyException(name + " factory is already registered.");
        }else{
            this.FactoriesMap.put(name, creator);
            this.dependencesMap.put(name, parameters);
        }
    }
}
