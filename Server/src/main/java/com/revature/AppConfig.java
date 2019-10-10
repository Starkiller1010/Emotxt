package com.revature;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan						// Enable Component scanning
@EnableTransactionManagement		// Enable Spring's transaction management
@PropertySource("app.properties")	// Specify properties file

public class AppConfig implements WebMvcConfigurer, WebApplicationInitializer {
	
	// Interpolate database information and credentials from the properties file.
	
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	/**
	 * 
	 * Configures a BasicDataSource (an implementation of the DataSource) interface
	 * that is an alternative to DriverManager and supports connection pooling.
	 * @return
	 */
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	/**
	 * 
	 * Creates a a Hibernate sessionFactory and configures it with
	 * the data source, annotated classes, and Hibernate's properties.
	 * 
	 */
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.revature");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	/**
	 * 
	 * Configures Hibernate's transaction manager that will be used by Spring
	 * for handling transactions.
	 * 
	 */
	
	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	/**
	 * 
	 * Reads from the properties file and assigns properties to
	 * Hibernate.
	 * 
	 */
	
	@Bean
	private Properties hibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.connection.driver_class", driver);
		props.setProperty("hibernate.connection.url", url);
		props.setProperty("hibernate.connection.username", username);
		props.setProperty("hibernate.connection.password", password);
		return props;
	}
	
	/**
	 * 
	 * Defines actions to be taken when the application starts.
	 * 	- Sets up a container that registers classes
	 *  - Creates and registers a servlet
	 *  - Sets instantiation of dispatcher to eager
	 *  - Adds a mapping to the servlet.
	 */
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
		container.register(AppConfig.class);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(container));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}
