/**
 * 
 */
package com.aimdek.ccm.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.aimdek.ccm.amazons3.service.AmazonRepositoryService;
import com.aimdek.ccm.scheduler.MonthlyStatementScheduler;
import com.aimdek.ccm.service.StatementService;
import com.aimdek.ccm.util.CCMConstant;

/**
 * The class AppConfig provides the bean definitions. 
 * 
 * @author aimdek.team
 *
 */
@Configuration
@ComponentScan(basePackages = "com.aimdek.ccm")
@Import({ WebSecurityConfig.class })
@EnableWebMvc
@PropertySource({"classpath:amazon-config/aws-credential.properties", "classpath:mail-config/mail.properties" })
public class AppConfig extends WebMvcConfigurerAdapter {

	/** The env. */
	@Autowired
	private Environment env;
	
	/** The statement service. */
	@Autowired
	private StatementService statementService;

	/**
	 * Ammazon repository arg service.
	 *
	 * @return the amazon repository service
	 */
	public @Bean AmazonRepositoryService ammazonRepositoryArgService() {
		return new AmazonRepositoryService(env.getProperty("secretKey"), env.getProperty("accessKey"), env.getProperty("bucketName"));
	}
	
	/**
	 * Run me job.
	 *
	 * @return the job detail factory bean
	 */
	@Bean
	public JobDetailFactoryBean runMeJob() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(MonthlyStatementScheduler.class);
		jobDetailFactory.setDurability(CCMConstant.TRUE);
		return jobDetailFactory;
	}
	
	/**
	 * Process trigger.
	 *
	 * @return the cron trigger factory bean
	 */
	@Bean
	public CronTriggerFactoryBean processTrigger() {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(runMeJob().getObject());
		// Cron Trigger, run everyday 4:00 AM
		cronTriggerFactoryBean.setCronExpression("0 0 4 1/1 * ? *");
		return cronTriggerFactoryBean;
	}
	
	/**
	 * Scheduler factory bean.
	 *
	 * @return the scheduler factory bean
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		Map<String, Object> schedulerContextAsMap = new HashMap<String, Object>();
		schedulerContextAsMap.put("statementService", statementService);
		scheduler.setSchedulerContextAsMap(schedulerContextAsMap);
		scheduler.setJobDetails(runMeJob().getObject());
		Trigger[] triggers = { processTrigger().getObject() };
		scheduler.setTriggers(triggers);
		return scheduler;
	}
	
	
	/**
	 * Java mail service.
	 *
	 * @return the java mail sender
	 */
	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(env.getProperty("mail.sender.host"));
		javaMailSender.setPort(Integer.parseInt(env.getProperty("mail.sender.port")));
		javaMailSender.setUsername(env.getProperty("mail.username"));
		javaMailSender.setPassword(env.getProperty("mail.password"));
		javaMailSender.setJavaMailProperties(getMailProperties());
		return javaMailSender;
	}

	/**
	 * Gets the mail properties.
	 *
	 * @return the mail properties
	 */
	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
		properties.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		properties.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
		properties.setProperty("mail.debug", env.getProperty("mail.debug"));
		return properties;
	}
	
	/**
	 * Velocity engine.
	 *
	 * @return the velocity engine
	 * @throws VelocityException the velocity exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException {
		VelocityEngineFactory factory = new VelocityEngineFactory();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		factory.setVelocityProperties(props);
		return factory.createVelocityEngine();
	}
	
	/**
	 * Message source.
	 *
	 * @return the resource bundle message source
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("language.properties.language_en");
		return source;
	}

	/**
	 * Creates the multipart resolver.
	 *
	 * @return the commons multipart resolver
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
		super.configureMessageConverters(converters);
	}
	
	/**
	 * Converter.
	 *
	 * @return the mapping jackson2 http message converter
	 */
	@Bean
	MappingJackson2HttpMessageConverter converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		return converter;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * View resolver.
	 *
	 * @return the internal resource view resolver
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setContentType("text/html; charset=UTF-8");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
