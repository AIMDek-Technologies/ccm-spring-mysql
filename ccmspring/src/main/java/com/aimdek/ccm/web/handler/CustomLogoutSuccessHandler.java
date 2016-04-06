/**
 * 
 */
package com.aimdek.ccm.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * The Class CustomLogoutSuccessHandler.
 *
 * @author aimdek.team
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CustomLogoutSuccessHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		LOGGER.info("------------------Custom Logout Success Handler------------------");
		request.getSession().removeAttribute("user");
		response.sendRedirect("/ccmspring/logout");
	}

}
