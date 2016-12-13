/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.arch;

import com.inkonic.refus.annotations.RRedirect;
import com.inkonic.refus.annotations.RSerialize;
import com.inkonic.refus.core.RAPI;
import com.inkonic.refus.exceptions.RefusAPIException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benoit.simonis
 */
public abstract class RAPIMethod implements REFUSModel {

    /* @TODO Ajouter les variables de définition d'une méthode d'API */

    

    public Object doGet(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object doPut(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object doDelete(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String redirect(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @RSerialize
    public Object serialize(HttpServletRequest request, HttpServletResponse response, Object data, RAPI rapi) {
        if (rapi.getRser() != null) {
            return rapi.getRser().serialize(request, response, data);
        } else {
            throw new RefusAPIException(500, "No serializer defined for this method", null);

        }
    }

}
