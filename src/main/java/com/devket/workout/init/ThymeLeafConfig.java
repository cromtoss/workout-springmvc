/*	$Header: $
 *
 *	Copyright © 2013 Pearson VUE. All rights reserved.
 */

package com.devket.workout.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 *  It is an important implementation detail here that the individual template engine, template resolver,
 *  and view resolver live in singleton scope.
 *
 *  @author Tom Cross
 *  @version $Revision: #1 $ submitted $DateTime: 2013/09/17 19:35:00 $ by $Author: CROSTA $	
 */

@Configuration
public class ThymeleafConfig {

	@Bean
	public ServletContextTemplateResolver templateResolver() {
		final ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("XHTML");
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		final SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		return engine;
	}

	@Bean
	public ViewResolver thymeleafViewResolver() {
		final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}
}
