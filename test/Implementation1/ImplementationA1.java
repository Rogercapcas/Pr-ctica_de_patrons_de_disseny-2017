/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation1;

import Interfaces1.InterfaceA;
import Interfaces1.InterfaceB;
import Interfaces1.InterfaceC;

/**
 *
 * @author roger
 */
public class ImplementationA1 implements InterfaceA{
    
    public InterfaceB b;
    public InterfaceC c;
    
    public ImplementationA1(InterfaceB b, InterfaceC c){
        this.b = b;
        this.c = c;
    }    
}
