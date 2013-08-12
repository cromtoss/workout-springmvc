package com.devket.workout.init;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * The main Spring configuration file for the web application.
 * <p/>
 * Created: 8/3/13 3:16 PM
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.devket.workout")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.devket.workout.repository")
public class WebAppConfiguration {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
   	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
   	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
   	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

   	private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_AUTOCREATE = "hibernate.hbm2ddl.auto";
   	private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

   	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

   	@Resource
   	private Environment env;

    /**
     * Gets a Java built-in paradigmatic data source, using the c3p0 connection pooler.
     *
     * @return
     *  the data source
     */
    @Bean(destroyMethod="close")
   	public DataSource dataSource() {
   		ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
   		    dataSource.setDriverClass(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        } catch (PropertyVetoException ex) {
            throw new IllegalStateException(ex);
        }

   		dataSource.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
   		dataSource.setUser(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
   		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

   		return dataSource;
   	}

    /**
     * Provides a concrete JPA {@link javax.persistence.EntityManagerFactory}, by way of Spring ORM & Hibernate.
     *
     * @return
     *  an entity manager factory
     */
    @Bean
   	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
   		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
   		entityManagerFactoryBean.setDataSource(dataSource());
   		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
   		entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

   		entityManagerFactoryBean.setJpaProperties(hibProperties());

   		return entityManagerFactoryBean;
   	}

    /**
     * Gets the Hibernate properties from the all-purpose properties file placed on the classpath.
     *
     * @return
     *  the Hibernate properties, as {@link Properties}.
     */
   	private Properties hibProperties() {
   		final Properties properties = new Properties();
   		properties.put(PROPERTY_HIBERNATE_DIALECT,	env.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
   		properties.put(PROPERTY_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_HIBERNATE_AUTOCREATE, env.getRequiredProperty(PROPERTY_HIBERNATE_AUTOCREATE));
   		return properties;
   	}


    /**
     * Provide a {@link org.springframework.transaction.PlatformTransactionManager} to the Spring framework.
     * Provides transactional proxies (using Spring AOP) for classes annotated with {@code @Transactional}.
     *
     * Spring will automatically recognize this bean as the transaction manager based on its name, by convention.
     */
    @Bean
   	public JpaTransactionManager transactionManager() {
   		final JpaTransactionManager transactionManager = new JpaTransactionManager();
   		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
   		return transactionManager;
   	}

   	@Bean
   	public ViewResolver viewResolver() {
        final TemplateResolver templateResolver = new ClassLoaderTemplateResolver(); //thymeleaf template resolver
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        final SpringTemplateEngine templateEngine = new SpringTemplateEngine(); //thymeleaf template engine for Spring MVC
        templateEngine.setTemplateResolver(templateResolver);

        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); //thymeleaf implementation for Spring ViewResolver.
        viewResolver.setTemplateEngine(templateEngine);

   		return viewResolver;
   	}

    //tcTODO i18n
//   	@Bean
//   	public ResourceBundleMessageSource messageSource() {
//   		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//   		source.setBasename(env.getRequiredProperty("message.source.basename"));
//   		source.setUseCodeAsDefaultMessage(true);
//   		return source;
//   	}
}
