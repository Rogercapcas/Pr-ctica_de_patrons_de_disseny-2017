/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complex;

import common.DependencyException;
import java.util.HashMap;

/**
 *
 * @author roger
 */
public class Container implements Injector {
   
    private HashMap<Class,Object> registeredObjects;
    private HashMap<Class,complex.Factory> FactoriesMap;
    private HashMap<Class,Class[]> dependencesMap;
    private boolean DEBUG = false;
    
    public Container(){
        this.registeredObjects = new HashMap<>();
        this.FactoriesMap = new HashMap<>();
        this.dependencesMap = new HashMap<>();
    }

    @Override
    public <E> void registerConstant(Class<E> name, E value) throws DependencyException {
        if (this.registeredObjects.containsKey(name)){
            if (DEBUG) System.err.println("ERROR: '" + name + "' constant is already registered.");
            throw new DependencyException(name + " constant is already registered.");
        } else{
            this.registeredObjects.put(name, value);
            if (DEBUG) System.out.println("Key '" + name + "' registered with value '" + value + "'");
        }
    }

    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependencyException {
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
    public <E> E getObject(Class<E> name) throws DependencyException {
        if (this.registeredObjects.containsKey(name)){
            return (E) this.registeredObjects.get(name);
        }
        else if(this.FactoriesMap.containsKey(name)){
            return (E) this.makeFactory(name);            
        }
        else{
            throw new DependencyException(name + " has not been registered.");
        }    
    }

    private <E> Object makeFactory(Class<E> name) throws DependencyException{
        try{
            complex.Factory creator;
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
