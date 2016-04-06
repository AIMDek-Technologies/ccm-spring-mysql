package com.aimdek.ccm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aimdek.ccm.dto.LoginDto;

/**
 * The Class WebApplicationController.
 *
 * @author aimdek.team
 */
@Controller
public class WebApplicationController {

	/** The dto. */
	private LoginDto dto = new LoginDto();

	/**
	 * Default render.
	 *
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String defaultRender() {
		return "redirect:/login";
	}

	/**
	 * Gets the login page.
	 *
	 * @param model
	 *            the model
	 * @return the login page
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(ModelMap model) {
		model.put("user", dto);
		return new ModelAndView("index");
	}

	/**
	 * Render home page.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String renderHomePage(ModelMap model) {
		model.put("page", "home");
		return "home";
	}

	/**
	 * Render error page.
	 *
	 * @param model
	 *            the model
	 * @param error
	 *            the error
	 * @return the string
	 */
	@RequestMapping(value = "login/{error}", method = RequestMethod.GET)
	public String renderErrorPage(ModelMap model, @PathVariable String error) {
		model.put("error", true);
		model.put("user", dto);
		return "index";
	}

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public LoginDto getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto
	 *            the dto to set
	 */
	public void setDto(LoginDto dto) {
		this.dto = dto;
	}
}
