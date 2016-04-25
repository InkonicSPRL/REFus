/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.core;



import com.inkonic.refus.arch.RAuthenticatorImpl;
import com.inkonic.refus.arch.RPage;
import java.util.HashMap;

/**
 *
 * @author benoit.simonis
 */
public class RWebsite {
    
    private HashMap<String, RPage> pages_map;
    protected HashMap<String, Object> site_metadata;
    protected String site_name;
    protected String site_copyright;
    private String error_page;
    private RAuthenticatorImpl rauth;
    
    
    public RWebsite(String site_name, String site_copyright){
        pages_map = new HashMap<>();
        site_metadata = new HashMap<>();
        this.site_name = site_name;
        this.site_copyright = site_copyright;
    }
    
    public void addData(String name, Object object){
    
        site_metadata.put(name, object);
    }
    
    public Object getData(String name){
        return site_metadata.get(name);
    }
    
    public void addRModel(String title, RPage rm){
        
        pages_map.put(title, rm);
    
    }
    
    public RPage getModel(String title){
    
        return pages_map.get(title);
    }

    /**
     * @return the site_name
     */
    public String getSite_name() {
        return site_name;
    }

    /**
     * @param site_name the site_name to set
     */
    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    /**
     * @return the site_copyright
     */
    public String getSite_copyright() {
        return site_copyright;
    }

    /**
     * @param site_copyright the site_copyright to set
     */
    public void setSite_copyright(String site_copyright) {
        this.site_copyright = site_copyright;
    }

    /**
     * @return the rauth
     */
    public RAuthenticatorImpl getRauth() {
        return rauth;
    }

    /**
     * @param rauth the rauth to set
     */
    public void setRauth(RAuthenticatorImpl rauth) {
        this.rauth = rauth;
    }

    /**
     * @return the error_page
     */
    public String getError_page() {
        return error_page;
    }

    /**
     * @param error_page the error_page to set
     */
    public void setError_page(String error_page) {
        this.error_page = error_page;
    }
    
    
}
