/**
 * 
 */
package com.aimdek.ccm.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;

/**
 * The Class LoginDto.
 *
 * @author aimdek.team
 */
@Scope("session")
public class LoginDto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6265565117937539517L;

	/** The email. */
	@NotNull
	@Email
	@NotEmpty(message = "Please enter your email addresss.")
	private String email;

	/** The password. */
	@NotEmpty(message = "Please enter your password.")
	private String password;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
