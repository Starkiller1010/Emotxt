package com.revature;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebMvc
@ComponentScan // Enable Component scanning
@Configuration // XML equivalent to <bean>
@EnableTransactionManagement // Enable Spring's transaction management
@PropertySource("classpath:app.properties") // Specify properties file
public class AppConfig implements WebMvcConfigurer {

	private Logger log = LogManager.getLogger(AppConfig.class);
	// Interpolate database information and credentials from the properties file.
	
//	@Value("${db.driverOracle}")
	@Value("${db.driverH2}")
	private String driver;
	
//	@Value("${db.urlOracle}")
	@Value("${db.urlH2}")
	private String url;
	
//	@Value("${db.usernameOracle}")
	@Value("${db.usernameH2}")
	private String username;
	
//	@Value("${db.passwordOracle}")
	@Value("${db.passwordH2}")
	private String password;

	//private MyWebSocketHandler myWebSocketHandler;

	// @Autowired
	// public AppConfig(MyWebSocketHandler socketHandler) {
	// 	super();
	// 	this.myWebSocketHandler = socketHandler;
	// }

	/**
	 * 
	 * Configures a BasicDataSource (an implementation of the DataSource) interface
	 * that is an alternative to DriverManager and supports connection pooling.
	 * 
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
	 * Creates a a Hibernate sessionFactory and configures it with the data source,
	 * annotated classes, and Hibernate's properties.
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
	 * Configures Hibernate's transaction manager that will be used by Spring for
	 * handling transactions.
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
	 * Configures Hibernate properties.
	 * 
	 */

	private Properties hibernateProperties() {
		Properties props = new Properties();
//		props.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		return props;
	}

	/**
	 * 
	 * Defines actions to be taken when the application starts. - Sets up a
	 * container that registers classes - Creates and registers a servlet - Sets
	 * instantiation of dispatcher to eager - Adds a mapping to the servlet.
	 */

	// @Override
	// public void onStartup(ServletContext servletContext) throws ServletException {
	// 	AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
	// 	container.register(AppConfig.class);
	// 	servletContext.addListener(new ContextLoaderListener(container));
	// 	log.info("Setting up dispatcher.");
	// 	ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
	// 			new DispatcherServlet(container));
	// 	dispatcher.setLoadOnStartup(1);
	// 	dispatcher.addMapping("/");
	// 	log.info("Mapping successful.");
	// }
	
}
