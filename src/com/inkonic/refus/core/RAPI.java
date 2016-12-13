/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.core;

import com.inkonic.refus.annotations.RDelete;
import com.inkonic.refus.annotations.RGet;
import com.inkonic.refus.annotations.RModel;
import com.inkonic.refus.annotations.RPost;
import com.inkonic.refus.annotations.RPut;
import com.inkonic.refus.arch.RAPIMethod;
import com.inkonic.refus.arch.RAuthenticatorImpl;
import com.inkonic.refus.arch.REFUSModel;
import com.inkonic.refus.arch.RErrorImpl;
import com.inkonic.refus.arch.RPage;
import com.inkonic.refus.arch.RSerializeImpl;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *
 * @author benoit.simonis
 */
public class RAPI {

    private HashMap<String, RAPIMethod> methods_map;

    protected String api_name;
    protected String api_copyright;
    private RAuthenticatorImpl rauthenticator;
    private RSerializeImpl rserializer;
    private RErrorImpl rerrorhandler;

    public RAPI(String api_name, String api_copyright) {
        methods_map = new HashMap<>();

        this.api_name = api_name;
        this.api_copyright = api_copyright;
    }

    public HashMap<String, RAPIMethod> getMethodsMap(){
        return methods_map;
    }

    

    public void addRModel(String title, RAPIMethod rm) {

        methods_map.put(title, rm);

    }

    public RAPIMethod getModel(String title) {

        return methods_map.get(title);
    }

    /**
     * @return the site_name
     */
    public String getSite_name() {
        return api_name;
    }

    /**
     * @param site_name the site_name to set
     */
    public void setSite_name(String site_name) {
        this.api_name = site_name;
    }

    /**
     * @return the site_copyright
     */
    public String getSite_copyright() {
        return api_copyright;
    }

    /**
     * @param site_copyright the site_copyright to set
     */
    public void setSite_copyright(String site_copyright) {
        this.api_copyright = site_copyright;
    }

    /**
     * @return the rauth
     */
    public RAuthenticatorImpl getRauth() {
        return rauthenticator;
    }

    /**
     * @param rauth the rauth to set
     */
    public void setRauthenticator(RAuthenticatorImpl rauth) {
        this.rauthenticator = rauth;
    }

    /**
     * @return the rser
     */
    public RSerializeImpl getRser() {
        return rserializer;
    }

    /**
     * @param rser the rser to set
     */
    public void setRserializer(RSerializeImpl rser) {
        this.rserializer = rser;
    }

    /**
     * @return the rerr
     */
    public RErrorImpl getRerr() {
        return rerrorhandler;
    }

    /**
     * @param rerr the rerr to set
     */
    public void setRerrorhandler(RErrorImpl rerr) {
        this.rerrorhandler = rerr;
    }

}
