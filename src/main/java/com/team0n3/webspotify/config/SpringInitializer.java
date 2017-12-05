
package com.team0n3.webspotify.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
 
public class SpringInitializer implements WebApplicationInitializer {
  

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(ApplicationContextConfig.class);
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
      "SpringDispatcher", new DispatcherServlet(appContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
    dispatcher.setMultipartConfig(new MultipartConfigElement("C:\\Users\\JSCHA\\Pictures\\Camera Roll", 1024*1024*5, 1024*1024*5*5, 1024*1024));
//C:\\Users\\JSCHA\\Pictures\\Camera Roll
  }
}