package com.devket.workout.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Spring web application initializer. Replaces web.xml configuration entries.
 *
 * @author Tom Cross
 * @version $Revision: #1 $ submitted $DateTime: 2013/09/16 09:32:00 $ by $Author: CROSTA $
 */
public final class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // Static resource handling using "default" servlet -- for certain file types.
        servletContext.getServletRegistration("default").addMapping("*.js", "*.css", "*.jpg", "*.gif", "*.png");

		//Load application context
		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		rootContext.setDisplayName("Workout-o-Matic 5000");

		//Context loader listener
		servletContext.addListener(new ContextLoaderListener(rootContext));

		//Dispatcher servlet
		final ServletRegistration.Dynamic dispatcher =
				servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
    }
}
