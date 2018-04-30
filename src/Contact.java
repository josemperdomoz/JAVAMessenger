/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachat;

/**
 *
 * @author Usuario
 */
public class Contact {
    public final String name;
    public final String number;
    public String tag;
    public String description;
    
    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }

@Override
public String toString(){
    return name;
}
    
}
