/**
 * 
 */
package com.aimdek.ccm.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * The class WebAppInitializer provides the implements the {@link WebApplicationInitializer} order to configure the ServletContext programmatically.
 * 
 * @author aimdek.team
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {
	
	/** The Constant MAPPING_URL. */
	private static final String MAPPING_URL = "/";

	/**
	 *  Configure the given ServletContext with any servlets, filters, listeners context-params and attributes necessary for initializing this web application.
	 *  @param servletContext
	 *  @throws ServletException
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
		
		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.addListener(new RequestContextListener());
		servletContext.addListener(new HttpSessionEventPublisher());
		context.setServletContext(servletContext);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(MAPPING_URL);
	}

}
