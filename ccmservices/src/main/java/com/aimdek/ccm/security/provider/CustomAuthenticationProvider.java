package com.aimdek.ccm.security.provider;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.aimdek.ccm.security.service.AuthenticationService;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class CustomAuthenticationProvider.
 * @author aimdek.team 
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	/** The authentication service. */
	@Autowired
	private AuthenticationService authenticationService;

	/** The password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Authenticate.
	 *
	 * @param authentication the authentication
	 * @return the authentication
	 * @throws AuthenticationException the authentication exception
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserDetails user = authenticationService.loadUserByUsername(username);

		if (CommonUtil.isNull(user)) {
			throw new UsernameNotFoundException("login.invalid.message");
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("login.invalid.password.message");
		}

		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

		return new UsernamePasswordAuthenticationToken(user, password, authorities);

	}

	/**
	 * Supports.
	 *
	 * @param authentication the authentication
	 * @return true, if successful
	 */
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}