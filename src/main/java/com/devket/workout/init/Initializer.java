package com.devket.workout.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * tcTODO
 * <p/>
 * Created: 8/11/13 12:37 PM
 */
public final class Initializer implements WebApplicationInitializer {

    // see https://github.com/Fruzenshtein/spr-mvc-hib/blob/master/src/main/java/com/sprhib/init/Initializer.java
    // see http://stackoverflow.com/questions/7271801/spring-3-1-java-configuration-autowired-configuration-and-profile-challen
    // see http://www.rockhoppertech.com/blog/spring-mvc-configuration-without-xml/

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(servletContext);

        final ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
