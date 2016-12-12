/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.tests;

import com.inkonic.refus.annotations.RModel;
import com.inkonic.refus.annotations.RProcess;
import com.inkonic.refus.arch.RAuthenticatorImpl;
import com.inkonic.refus.arch.RErrorImpl;
import com.inkonic.refus.arch.RSerializeImpl;
import com.inkonic.refus.core.RAPI;
import com.inkonic.refus.core.RWebsite;
import com.inkonic.refus.exceptions.RefusHttpException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benoit.simonis
 */
public class Tests {

    // REFus Reflective wEb Framework
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("LAUNCHING");
        
        
        RAPI the_api = new RAPI("api test", "nous");
        
        the_api.addRModel("test", new MethodeTest());
        the_api.addRModel("recuprandos", new MethodeTest() );
        
        
        
        
        
        the_api.setRauth(new RAuthenticatorImpl() {

            @Override
            public Boolean authenticate(HttpServletRequest request, HttpServletResponse response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                
        the_api.setRser(new RSerializeImpl() {

            @Override
            public Object serialize(HttpServletRequest request, HttpServletResponse response, Object data) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        the_api.setRerr(new RErrorImpl() {

            @Override
            public Object processError(HttpServletRequest request, HttpServletResponse response, Object data) {
               
                try {
                    
                    RefusHttpException the_error = (RefusHttpException) data;
                    
                    
                    
                    response.getWriter().print("UNE ERREUR S'EST PRODUITE :" + the_error.getStatuscode());
                } catch (IOException ex) {
                    Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        });
        
        
        
       //RWebsite rw = new RWebsite("mon_site", "moi");
      //  rw.addRModel("page_home", new TestClass());
        
        
        
        
        
        
        
//        Class<TestClass> tc = TestClass.class;
//        if(tc.isAnnotationPresent(RModel.class)){
//            RModel annotation = tc.getAnnotation(RModel.class);
//            System.out.println("A : "+ annotation.jspName());
//            
//            for (Method method : tc.getDeclaredMethods()) {
//
//		// if method is annotated with @Test
//		if (method.isAnnotationPresent(RProcess.class)) {
//
//			RProcess test = method.getAnnotation(RProcess.class);
//			
//                    try {
//                        // if enabled = true (default)
//
//                      String result = (String)  method.invoke(tc.newInstance(), null, null);
//                        System.out.println("RESULT "+result);
//                        
//                    } catch (InstantiationException ex) {
//                        Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (IllegalAccessException ex) {
//                        Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (IllegalArgumentException ex) {
//                        Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (InvocationTargetException ex) {
//                        Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//				
//			
//
//		}
//
//	}
//        }
//        
    }
    
}
