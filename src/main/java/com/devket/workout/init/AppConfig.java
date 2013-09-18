package com.devket.workout.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The main Spring configuration file for the web application.
 *
 * AppConfig implementation patterns courtesy:
 * https://github.com/krams915/spring-thymeleaf-javaconfig/tree/master/src/main/java/org/krams/config
 *
 * @author Tom Cross
 * @version $Revision: #1 $ submitted $DateTime: 2013/09/16 09:32:00 $ by $Author: CROSTA $
 */

@Configuration
@ComponentScan("com.devket.workout")
@EnableWebMvc
@Import({SpringDataConfig.class, ThymeleafConfig.class})
public class AppConfig extends WebMvcConfigurerAdapter {

	// Provides internationalization of messages
	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("Messages");
		return source;
	}
}
