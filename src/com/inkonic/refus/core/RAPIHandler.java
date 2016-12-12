/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.core;

import com.inkonic.refus.annotations.RAuthenticator;
import com.inkonic.refus.annotations.RDelete;
import com.inkonic.refus.annotations.RGet;
import com.inkonic.refus.annotations.RModel;
import com.inkonic.refus.annotations.RPost;
import com.inkonic.refus.annotations.RProcess;
import com.inkonic.refus.annotations.RPut;
import com.inkonic.refus.annotations.RRedirect;
import com.inkonic.refus.annotations.RSecured;
import com.inkonic.refus.annotations.RSerialize;
import com.inkonic.refus.arch.RAuthenticatorImpl;
import com.inkonic.refus.arch.REFUSModel;
import com.inkonic.refus.exceptions.GenericError;
import com.inkonic.refus.exceptions.RefusAPIException;
import com.inkonic.refus.exceptions.RefusHttpException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

/**
 *
 * @author benoit.simonis
 */
public class RAPIHandler {

    private RAPI ra;

    public RAPIHandler(RAPI ra) {

        this.ra = ra;

    }

    public void processPage(String name, HttpServletRequest request, HttpServletResponse response) {

        REFUSModel rp = ra.getModel(name);

        Class<? extends REFUSModel> cm = (Class<? extends REFUSModel>) rp.getClass();
        Method process = null;
        Method redirect = null;
        Method get = null;
        Method post = null;
        Method put = null;
        Method delete = null;
        Method authenticator = null;
        Method serialize = null;
        Object authenticatorinstance = null;

        if (cm.isAnnotationPresent(RModel.class)) {

            /*          
             Pour chaque RAPIMethod, on cherche les annotations @RGet, @RPost, ...     
             /!\ Attention une méthode peut avoir plusieurs anotations, d'ou la non utilisation de "else-if"
             */
            for (Method method : cm.getMethods()) {

                if (method.isAnnotationPresent(RGet.class)) {
                    get = method;
                }
                if (method.isAnnotationPresent(RPost.class)) {
                    post = method;
                }
                if (method.isAnnotationPresent(RPut.class)) {
                    put = method;
                }
                if (method.isAnnotationPresent(RDelete.class)) {
                    delete = method;
                }

                // APT d'ici on peut mettre des else-if car les annotations suivantes ne sont pas cummulables
                // à l'exception du RProcess qui peut aussi avoir les @RGet, @RPost, ... (même si ça ne sert à rien) 
                if (method.isAnnotationPresent(RProcess.class)) {

                    process = method;
                } else if (method.isAnnotationPresent(RRedirect.class)) {

                    redirect = method;
                } else if (method.isAnnotationPresent(RAuthenticator.class)) {
                    authenticator = method;
                    authenticatorinstance = rp;
                } else if (method.isAnnotationPresent(RSerialize.class)) {
                    serialize = method;
                }

            }

        }

        if (cm.isAnnotationPresent(RSecured.class)) {

            if (authenticator == null) {
                try {
                    Method cri = RAuthenticatorImpl.class.getMethod("authenticate", HttpServletRequest.class, HttpServletResponse.class);
                    authenticator = cri;
                    authenticatorinstance = ra.getRauth();
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(RWSHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(RWSHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            authenticator = null;
        }

        Boolean execmethod = true;
        try {
            try {
                if (authenticator != null) {
                    execmethod = (Boolean) authenticator.invoke(authenticatorinstance, request, response);
                }

                /*
                
                 On analyse le type de requête. En fonction du type (GET, POST, PUT, DELETE) on 
                 appelle la méthode de l'instance de RAPIMethod qui est concernée (doGet, doPost, ...) 
                 Pour être appellée, cette méthode doit avoir l'annotation @RGet, @RPöst, ...
                
                 Si aucune méthode avec cette annotation n'est définie, on va appeler la méthode 
                 process (qui doit elle aussi contenir l'annoation @RProcess).
                
                 Si la méthode process n'est pas définie , on déclenche une erreur. 
                
                
                 */
                String method = request.getMethod();
                Object result = null;
                if (execmethod != null && execmethod == true) {

                    if (get != null && method.equals(HttpMethod.GET)) {
                        result = get.invoke(rp, request, response);
                    } else if (post != null && method.equals(HttpMethod.POST)) {
                        result = post.invoke(rp, request, response);
                    } else if (put != null && method.equals(HttpMethod.PUT)) {
                        result = put.invoke(rp, request, response);
                    } else if (delete != null && method.equals(HttpMethod.DELETE)) {
                        result = delete.invoke(rp, request, response);
                    } else {
                        if (process != null) {
                            result = process.invoke(rp, request, response);
                        } else {
                            throw new RefusAPIException(405, "Unauthorized method", null);
                        }
                    }
                    if (redirect != null) {
                        redirect.invoke(rp, request, response);
                    } else if (serialize != null) {
                        serialize.invoke(rp, request, response, result, ra);
                    }
                }

            } catch (InvocationTargetException re) {
                if (re.getCause() instanceof RefusHttpException) {

                    request.setAttribute("error_context", re.getCause());
                    System.out.println("MESSAGE ::" + re.getCause().getMessage());
                    //RequestDispatcher view = request.getRequestDispatcher(rw.getError_page());
                    response.setStatus(((RefusHttpException) re.getCause()).getStatuscode());
                    
                    ra.getRerr().processError(request, response, re.getCause());
                    
                  //  view.forward(request, response);
                } else {
                    throw re;
                }
            }

        } catch (Exception e) {
            
            /* Dans le cas ou même le traitement de l'erreur a échoué */ 
            
            System.out.println("TYPE " + e.getClass().getName());
        
            GenericError.printException(response, e);

        }

    }
}
