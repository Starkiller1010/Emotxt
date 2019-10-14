package com.revature;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebApplicationInitializer
      extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
   protected Class<?>[] getRootConfigClasses() {
     return new Class[] { AppConfig.class } ;
    //  return null;
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] { WebSocketConfig.class };
   }

   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }

}