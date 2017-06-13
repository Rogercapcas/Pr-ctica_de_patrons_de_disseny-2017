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
            throw new DependencyException(name + " is already registered.");
        } else{
            this.registeredObjects.put(name, value);
        }
    }

    @Override
    public Object getObject(String name) throws DependencyException {
        if (this.registeredObjects.containsKey(name)){
            return this.registeredObjects.get(name);
        }
        throw new DependencyException(name + " doesn't exists.");
        
    }
    
    @Override
    public void registerFacotry(String name, Factory creator, String... parameters) throws DependencyException{
        
        if (this.registeredObjects.containsKey(name)){
            throw new DependencyException(name + " factory is already registered.");
        }else{
            for( String parameter:parameters ){
                
            }
            try{
                this.registeredObjects.put(name, creator.create(parameters));
            }catch(DependencyException ex){
            }
        }
    }
}
