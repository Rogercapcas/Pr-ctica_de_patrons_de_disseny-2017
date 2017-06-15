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
    private HashMap<String,Object> FactoriesMap;
    private HashMap<String,ArrayList<String>> dependencesMap;
    
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
            this.registeredObjects.put(name, value);
            System.out.println("Key '" + name + "' registered with value '" + value + "' ");
        }
    }

    @Override
    public void registerFacotry(String name, Factory creator, String... parameters) throws DependencyException{
        
        if (this.registeredObjects.containsKey(name)){
            throw new DependencyException(name + " factory is already registered.");
        }else{
            System.out.println("Trying to register a factory with name: " + name + " and value/s " + creator);
            this.FactoriesMap.put(name, creator);
            System.out.println("Successfull factory register with FactoryName: '" + name + "'");
            System.out.println("Trying to register a factory dependences with FactoryName: " + name + " and value/s " + parameters[0]);
            ArrayList<String> params = new ArrayList<>();
            for (String item:parameters){
                params.add(item);
            }
            this.dependencesMap.put(name, params);
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
            System.out.println("Getting '" + name + "' from dependencesMap. Result is: '" + this.dependencesMap.get(name).get(0) + "'");
            ArrayList<String> dependences = this.dependencesMap.get(name);
            if (dependences.size() == 1){
                return creator.create(this.getObject(dependences.get(0)));
            }
            else if (dependences.size() == 2){
                return creator.create(this.getObject(dependences.get(0)), this.getObject(dependences.get(1)));
            }
            else{
                throw new DependencyException(name + " Factory has some problem in its dependences list.");
            }
            
        }
        else{
            throw new DependencyException(name + " has not been registered. ");
        }        
    }
}
