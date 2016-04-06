package com.aimdek.ccm;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The Class JPATestConfig provides JPA configuration for the jUnit test cases.
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:database-config/testcase.database.properties" })
@EnableJpaRepositories(basePackages = { "com.aimdek.ccm" }, considerNestedRepositories = true)
public class JPATestConfig {

	/** The env. */
	@Autowired
	private Environment env;

	/**
	 * Data source.
	 *
	 * @return the data source
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	/**
	 * Entity manager factory.
	 *
	 * @return the entity manager factory
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setDatabasePlatform(env.getProperty("hibernate.dialect"));
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.aimdek.ccm");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		factory.setBeanName("emf");

		return factory.getObject();
	}

	/**
	 * Transaction manager.
	 *
	 * @return the jpa transaction manager
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}
	
	/**
	 * Exception translation.
	 *
	 * @return the persistence exception translation post processor
	 */
	@Bean  
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {  
        return new PersistenceExceptionTranslationPostProcessor();  
    }  
}