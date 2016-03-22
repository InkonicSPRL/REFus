/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkonic.refus.tests;

import com.inkonic.refus.annotations.RModel;
import com.inkonic.refus.annotations.RProcess;
import com.inkonic.refus.core.RWebsite;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

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
