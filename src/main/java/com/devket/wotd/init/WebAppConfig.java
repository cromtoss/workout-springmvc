package com.devket.wotd.init;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * tcTODO
 * <p/>
 * Created: 8/3/13 3:16 PM
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.devket.wotd")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.devket.wotd.repository")
public final class WebAppConfig {

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
    @Bean
   	public DataSource dataSource() {
   		ComboPooledDataSource dataSource = new ComboPooledDataSource();    //tcTODO destroy-method == close() ???

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
   		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
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
   		Properties properties = new Properties();
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
   		JpaTransactionManager transactionManager = new JpaTransactionManager();
   		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
   		return transactionManager;
   	}

   	@Bean
   	public UrlBasedViewResolver setupViewResolver() {
   		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
   		resolver.setPrefix("/WEB-INF/pages/");
   		resolver.setSuffix(".jsp");
   		resolver.setViewClass(JstlView.class);
   		return resolver;
   	}

   	@Bean
   	public ResourceBundleMessageSource messageSource() {
   		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
   		source.setBasename(env.getRequiredProperty("message.source.basename"));
   		source.setUseCodeAsDefaultMessage(true);
   		return source;
   	}
}
