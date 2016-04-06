/**
 * 
 */
package com.aimdek.ccm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aimdek.ccm.security.provider.CustomAuthenticationProvider;
import com.aimdek.ccm.web.handler.CustomAuthenticationSuccessHandler;
import com.aimdek.ccm.web.handler.CustomLogoutSuccessHandler;

/**
 * The Class WebSecurityConfig provides a configuration for the spring security.
 *
 * @author aimdek.team
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Configure http security.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/home/**").authenticated()
			.antMatchers("/customer/**").access("hasRole('HELPDESK')")
			.antMatchers("/statement/**").authenticated()
			.antMatchers("/transactions/**").authenticated()
			.antMatchers("/bulkupload/**").access("hasRole('HELPDESK')")
			.antMatchers("/**").permitAll()
		.and()	
			.formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.loginPage("/login")
			.usernameParameter("email")
			.passwordParameter("password")
			.defaultSuccessUrl("/home")
			.successHandler(customAuthenticationSuccessHandler())
			.failureUrl("/login/error")
			.permitAll()
		.and()
			.logout()
			.permitAll()
			.logoutSuccessUrl("/login?logout=logout")
			.invalidateHttpSession(true)
			.logoutUrl("/j_spring_security_logout")
			.logoutSuccessHandler(customLogoutSuccessHandler())
			.permitAll()
		.and()
			.csrf().disable();
	}
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(6);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler(){
		return new CustomAuthenticationSuccessHandler();
	}
	
	@Bean
	CustomLogoutSuccessHandler customLogoutSuccessHandler(){
		return new CustomLogoutSuccessHandler();
	}
}
