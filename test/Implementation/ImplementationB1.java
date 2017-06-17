/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import Interfaces.InterfaceB;
import Interfaces.InterfaceD;

/**
 *
 * @author roger
 */
public class ImplementationB1 implements InterfaceB{
    
    public InterfaceD d;
    
    public ImplementationB1(InterfaceD d){
        this.d = d;
    }    
}
