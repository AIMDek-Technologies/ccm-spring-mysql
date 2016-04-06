package com.aimdek.ccm.web.handler;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.aimdek.ccm.document.User;
import com.aimdek.ccm.service.UserService;

/**
 * The Class CustomAuthenticationSuccessHandler.
 * 
 * @author aimdek.team
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationSuccessHandler.class);
	
	/** The users service. */
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,ServletException {
		 LOGGER.info("----------------Authentication Success handler---------------------------------------");
		 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		User user = userService.findUserById(Long.parseLong(userId));
		request.getSession(true).setAttribute("user", user);
		response.sendRedirect("/ccmspring/home/");
	}

}