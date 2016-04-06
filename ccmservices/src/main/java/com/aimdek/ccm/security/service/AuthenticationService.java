package com.aimdek.ccm.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.aimdek.ccm.service.UserService;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class AuthenticationService.
 * 
 * @author aimdek.team
 */
@Component
public class AuthenticationService implements UserDetailsService {

	/** The users service. */
	@Autowired
	private UserService userService;

	/**
	 * Load user by username.
	 *
	 * @param userName the user name
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.aimdek.ccm.document.User user = userService.findUserByEmail(userName);
		User userDetail = null;
		if (CommonUtil.isNotNull(user)) {
			userDetail = new User(String.valueOf(user.getId()), user.getPassword(), true, true, true, true, getAuthorities(user.getRole()));
		}
		return userDetail;
	}

	/**
	 * Gets the authorities.
	 * 
	 * @param role
	 *            the role
	 * @return the authorities
	 */
	public List<GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(role));
		return authList;
	}

}
