/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachat;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Message {
    public final String sender;
    public final String message;
    public final Date timestamp;
    
    
    public Message(String message,String sender, Date timestamp){
        this.message = message;
        this.timestamp = timestamp;
        this.sender = sender;
    }
    
    @Override
    public String toString(){
        return sender + " : " + message;
    }
}
