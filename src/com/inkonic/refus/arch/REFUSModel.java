/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.arch;

import com.inkonic.refus.annotations.RAuthenticator;
import com.inkonic.refus.annotations.RModel;
import com.inkonic.refus.annotations.RProcess;
import com.inkonic.refus.annotations.RRedirect;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benoit.simonis
 */
@RModel(jspName = "")
public interface REFUSModel {
    
    
    
    @RProcess
    public Object process(HttpServletRequest request, HttpServletResponse response);
    
    @RRedirect
    public String redirect(HttpServletRequest request, HttpServletResponse response);
    
  
}
