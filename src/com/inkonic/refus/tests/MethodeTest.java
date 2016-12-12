/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.tests;

import com.inkonic.refus.annotations.RGet;
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
    @RGet
    public Object doGet(HttpServletRequest request, HttpServletResponse response) {
        Integer a = 2;
        a = a + 2;
        return a;

//return super.doGet(request, response); //To change body of generated methods, choose Tools | Templates.
    }

    @RSerialize
    @Override
    public Object serialize(HttpServletRequest request, HttpServletResponse response, Object data, RAPI rapi) {

        return null;
    }

}
