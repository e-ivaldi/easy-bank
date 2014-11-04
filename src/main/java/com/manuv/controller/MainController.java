package com.manuv.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author emanuele.ivaldi@gmail.com
 * 
 */
@Controller
public class MainController extends BaseController {

	/**
	 * index & loggedIn are used to remove logic from the jsp
	 * @param model
	 * @param locale
	 * @return the view "index.jsp"
	 */
	@RequestMapping(value = INDEX_ROUTE)
	public String index(Model model, Locale locale) {

		if (getCurrentUser() != null) {
			
			return USER_LOGGED_IN_VIEW;
		}

		return INDEX_VIEW;
	}

	/**
	 * Check if the user is logged, if so add the username to the model
	 * 
	 * @param model
	 * @param locale
	 * @return the view "index.jsp"
	 */
	@RequestMapping(value = PAGE_NOT_FOUND_ROUTE)
	public String pageNotFound(Model model, Locale locale) {

		return PAGE_NOT_FOUND_VIEW;
	}

	/**
	 * Throws an exception, used to check if the exception handler is working
	 * well
	 * 
	 * @param model
	 * @param locale
	 * @return nothing, just throws an exception
	 */
	@RequestMapping(value = EXCEPTION_HANDLED_ROUTE)
	public String exceptionTest(Model model, Locale locale) {

		logger.debug("throws a nullpointer exception..");
		throw new NullPointerException();
	}

	private final static Logger logger = LoggerFactory.getLogger(MainController.class);

}
