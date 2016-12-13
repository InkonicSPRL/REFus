/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.tests;

import com.inkonic.refus.annotations.RGet;
import com.inkonic.refus.annotations.RParameter;
import com.inkonic.refus.annotations.RReturns;
import com.inkonic.refus.annotations.RSerialize;
import com.inkonic.refus.arch.RAPIMethod;
import com.inkonic.refus.core.RAPI;
import com.inkonic.refus.exceptions.RefusAPIException;
import java.io.ByteArrayOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benoit.simonis
 */
public class MethodeTest extends RAPIMethod {

    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RGet(parameters = {
        @RParameter(desc = "la description", name = "client_name", type = Integer.class),
        @RParameter(desc = "blivli", name = "blabla", type = String.class)},
        returns = @RReturns(desc = "La valeur de retour", type = Integer.class))
    public Object doGet(HttpServletRequest request, HttpServletResponse response) {

        Integer a = 2;
        a = a + 2;
        return a;

//return super.doGet(request, response); //To change body of generated methods, choose Tools | Templates.
    }

//    @RGet({})
//    public Object doGfdsfdsfdet(HttpServletRequest request, HttpServletResponse response, RArguments[] arguments) {
//
//        Integer a = 2;
//        a = a + 2;
//        return a;
//
////return super.doGet(request, response); //To change body of generated methods, choose Tools | Templates.
//    }

 

   

}
