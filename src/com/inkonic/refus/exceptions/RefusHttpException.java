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
public class RefusHttpException extends RuntimeException {

    private int statuscode = 500;
    //private String message;
    private String message_key;

    /**
     * Creates a new instance of <code>RefusHttpException</code> without detail
     * message.
     */
    public RefusHttpException() {
    }

    /**
     * Constructs an instance of <code>RefusHttpException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RefusHttpException(String msg) {
        super(msg);
    }
    
    public RefusHttpException(String msg, String msg_key) {
        super(msg);
        setMessage_key(msg_key);
    }
    
    public RefusHttpException(String msg, String msg_key, int statuscode, Exception rootcause) {
        super(msg, rootcause);
        setMessage_key(msg_key);
        setStatuscode(statuscode);
        
    }

    /**
     * @return the statuscode
     */
    public int getStatuscode() {
        return statuscode;
    }

    /**
     * @param statuscode the statuscode to set
     */
    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    /**
     * @return the message
     */
  

    /**
     * @return the message_key
     */
    public String getMessage_key() {
        return message_key;
    }

    /**
     * @param message_key the message_key to set
     */
    public void setMessage_key(String message_key) {
        this.message_key = message_key;
    }
}
