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
    
    
    public Container(){
        this.registeredObjects = new HashMap<>();
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
        }else{
            throw new DependencyException(name + " doesn't exists.");
        }        
    }
    
    @Override
    public void registerFacotry(String name, Factory creator, String... parameters) throws DependencyException{
        
        if (this.registeredObjects.containsKey(name)){
            throw new DependencyException(name + " factory is already registered.");
        }else{
            try{
                if (parameters.length == 1){
                    this.registeredObjects.put(name, creator.create(this.getObject(parameters[0])));
                    System.out.println("Succesfull Factory register '" + name + "' with two items in parameters '" + parameters[0] + "'");
                }
                else{
                    this.registeredObjects.put(name, creator.create(this.getObject(parameters[0]), this.getObject(parameters[1])));
                    System.out.println("Succesfull Factory register with one item in parameters");                    
                }
                
            }catch(DependencyException ex){ System.err.println("Error when triyng to register a factory");
            }
        }
    }
}
