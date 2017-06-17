/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author roger
 */
public class Container implements Injector {
    
    private HashMap<String,Object> registeredObjects;
    private HashMap<String,Factory> FactoriesMap;
    private HashMap<String,String[]> dependencesMap;
    private boolean DEBUG = false;
    
    public Container(){
        this.registeredObjects = new HashMap<>();
        this.FactoriesMap = new HashMap<>();
        this.dependencesMap = new HashMap<>();
    }

    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        if (this.registeredObjects.containsKey(name)){
            if (DEBUG) System.err.println("ERROR: '" + name + "' constant is already registered.");
            throw new DependencyException(name + " constant is already registered.");
        } else{
            this.registeredObjects.put(name, value);
            if (DEBUG) System.out.println("Key '" + name + "' registered with value '" + value + "'");
        }
    }

    @Override
    public void registerFactory(String name, Factory creator, String... parameters) throws DependencyException{
        
        if (this.FactoriesMap.containsKey(name)){
            if (DEBUG) System.err.println("ERROR: '" + name + "' factory is already registered.");
            throw new DependencyException(name + " factory is already registered.");
        }else{
            if (DEBUG) System.out.println("Trying to register a factory with name: '" + name + "'");
            this.FactoriesMap.put(name, creator);
            if (DEBUG) System.out.println("Successfull factory register with FactoryName: '" + name + "'");
            if (DEBUG) System.out.println("Trying to register a factory dependences with FactoryName: '" + name + "'");
            this.dependencesMap.put(name, parameters);
            if (DEBUG) System.out.println("Successfull dependences register for Factory: '" + name + "'");
        }
    }
    
    @Override
    public Object getObject(String name) throws DependencyException {
        if (this.registeredObjects.containsKey(name)){
            return this.registeredObjects.get(name);
        }
        else if(this.FactoriesMap.containsKey(name)){
            return this.makeFactory(name);            
        }
        else{
            throw new DependencyException(name + " has not been registered.");
        }        
    }
    
    private Object makeFactory(String name) throws DependencyException{
        try{
            Factory creator;
            creator = this.FactoriesMap.get(name);
            Object[] str1 = new Object[this.dependencesMap.get(name).length];
            for (int i=0; i<this.dependencesMap.get(name).length; i++){
                str1[i] = this.getObject(this.dependencesMap.get(name)[i]);
            }
            return creator.create(str1);
        }catch(DependencyException ex){
                if (DEBUG) System.err.println("ERROR: Something whent wrong trying to make '" + name + "' factory");
                throw new DependencyException(ex);
        }
    }
}
