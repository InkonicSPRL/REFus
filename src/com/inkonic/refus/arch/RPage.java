/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.arch;

import com.inkonic.refus.annotations.RAuthenticator;
import com.inkonic.refus.annotations.RModel;
import com.inkonic.refus.annotations.RRedirect;
import com.inkonic.refus.exceptions.RefusWebException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;



/**
 *
 * @author benoit.simonis
 */

public abstract class RPage implements REFUSModel{
    
    protected String page_name;
    protected String page_title;
    protected String page_uri; 
    protected String page_locale;

    /**
     * @return the page_name
     */
    public String getPage_name() {
        return page_name;
    }

    /**
     * @param page_name the page_name to set
     */
    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    /**
     * @return the page_title
     */
    public String getPage_title() {
        return page_title;
    }

    /**
     * @param page_title the page_title to set
     */
    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    /**
     * @return the page_uri
     */
    public String getPage_uri() {
        return page_uri;
    }

    /**
     * @param page_uri the page_uri to set
     */
    public void setPage_uri(String page_uri) {
        this.page_uri = page_uri;
    }

    /**
     * @return the page_locale
     */
    public String getPage_locale() {
        return page_locale;
    }

    /**
     * @param page_locale the page_locale to set
     */
    public void setPage_locale(String page_locale) {
        this.page_locale = page_locale;
    }
    
    
    @Override
    @RRedirect
    public String redirect(HttpServletRequest request, HttpServletResponse response) {
    
        try {
            Class t = this.getClass();
            RModel rm = (RModel) t.getAnnotation(RModel.class);
            String to_redirect = rm.jspName();
            RequestDispatcher view = request.getRequestDispatcher(to_redirect);        
            view.forward(request, response);
        } catch (Exception ex) {
          
           // rwe.
          throw new RuntimeException("Error", ex);
        }
        return "OK";
    
    }
    
    
  
}
