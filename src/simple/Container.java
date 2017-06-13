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
    
    private HashMap<String,Object> objectNames;
    
    public Container(){
        this.objectNames = new HashMap<>();
    }

    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        if (this.objectNames.containsKey(name)){
            throw new DependencyException(name + " is already registered.");
        } else{
            this.objectNames.put(name, value);
        }
    }

    @Override
    public Object getObject(String name) throws DependencyException {
        if (this.objectNames.containsKey(name)){
            return this.objectNames.get(name);
        }
        throw new DependencyException(name + " doesn't exists.");
    }
    
    @Override
    public void registerFacotry(String name, Factory creator, String... parameters) throws DependencyException{
        
        if (this.objectNames.containsKey(name)){
            throw new DependencyException(name + " factory is already registered.");
        }else{
            try{
                this.objectNames.put(name, creator.create(parameters));
            }catch(DependencyException ex){
            }
        }
    }
}
