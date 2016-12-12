/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.exceptions;

/**
 *
 * @author benoit.simonis
 */
public class RefusAPIException extends RuntimeException {

    /**
     * Creates a new instance of <code>RefusWebException</code> without detail
     * message.
     */
    
    private int statuscode;
    private String message; 
    private String message_key;
    
    
    
    public RefusAPIException() {
    }

    public RefusAPIException(int statuscode, String message, String messagekey){
        this.statuscode = statuscode;
        this.message = message;
        this.message_key = messagekey;
    
    }
    
    /**
     * Constructs an instance of <code>RefusWebException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RefusAPIException(String msg) {
        super(msg);
    }

    /**
     * @return the statuscode
     */
    public int getStatuscode() {
        return statuscode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the message_key
     */
    public String getMessage_key() {
        return message_key;
    }
}
