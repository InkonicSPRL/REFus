/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.core;

import com.inkonic.refus.annotations.RAuthenticator;
import com.inkonic.refus.annotations.RModel;
import com.inkonic.refus.annotations.RProcess;
import com.inkonic.refus.annotations.RRedirect;
import com.inkonic.refus.annotations.RSecured;
import com.inkonic.refus.arch.RAuthenticatorImpl;
import com.inkonic.refus.arch.REFUSModel;
import com.inkonic.refus.arch.RPage;
import com.inkonic.refus.exceptions.GenericError;
import com.inkonic.refus.exceptions.RefusHttpException;
import com.inkonic.refus.exceptions.RefusWebException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benoit.simonis
 */
public class RWSHandler {

    private RWebsite rw;

    public RWSHandler(RWebsite rw) {

        this.rw = rw;

    }

    public void processPage(String name, HttpServletRequest request, HttpServletResponse response) {
        REFUSModel rp = rw.getModel(name);

        Class<? extends REFUSModel> cm = (Class<? extends REFUSModel>) rp.getClass();
        Method process = null;
        Method redirect = null;
        Method authenticator = null;
        Object authenticatorinstance = null;

        if (cm.isAnnotationPresent(RModel.class)) {
            Annotation annotation = cm.getAnnotation(RModel.class);
            for (Method method : cm.getMethods()) {
                if (method.isAnnotationPresent(RProcess.class)) {
                    //System.out.println("PROCESS FOUND");
                    process = method;
                } else if (method.isAnnotationPresent(RRedirect.class)) {
                    //System.out.println("REDIRECT FOUND");
                    redirect = method;
                } else if (method.isAnnotationPresent(RAuthenticator.class)) {
                    authenticator = method;
                    authenticatorinstance = rp;
                }

            }

        }

        if (cm.isAnnotationPresent(RSecured.class)) {

            if (authenticator == null) {
                try {
                    Method cri = RAuthenticatorImpl.class.getMethod("authenticate", HttpServletRequest.class, HttpServletResponse.class);
                    authenticator = cri;
                    authenticatorinstance = rw.getRauth();
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(RWSHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(RWSHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            authenticator = null;
        }

        request.setAttribute("website", rw);
        System.out.println("RWEBSITE ::" + rw.getSite_name());
        request.setAttribute("page", rp);
        Boolean execpage = true;
        try {
            try {
                if (authenticator != null) {
                    execpage = (Boolean) authenticator.invoke(authenticatorinstance, request, response);
                }
                if (execpage != null && execpage == true) {
                    process.invoke(rp, request, response);
                    redirect.invoke(rp, request, response);
                }

            } catch (InvocationTargetException re) {
                if (re.getCause() instanceof RefusHttpException) {
                    
                    request.setAttribute("error_context", re.getCause());
                    System.out.println("MESSAGE ::"+re.getCause().getMessage());
                    RequestDispatcher view = request.getRequestDispatcher(rw.getError_page());
                    view.forward(request, response);
                } else {
                    throw re;
                }
            }

        } catch (Exception e) {
            System.out.println("TYPE " + e.getClass().getName());
            System.out.println("HEJEPAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSE");
            GenericError.printException(response, e);

        }

    }

}
