package com.manuv.test.controller;

/*
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
 import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
*/

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import com.manuv.constant.UserConstants;
import com.manuv.controller.MainController;
import com.manuv.persistence.entities.User;

@WebAppConfiguration
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(value = { "classpath:context/test-context.xml", "classpath:context/servlet/servlet-context.xml" })
public class MvcTest {	
	
	@Before
	public void before() throws SQLException {
		
		this.mockMvc = webAppContextSetup(this.webappContext).addFilter(this.springSecurityFilter, "/*").build();
	}		

	/**
	 * simple main route test
	 * check if the forwarded url is the "tiles" one
	 * and if the view and the controller / method used are the good ones
	 * 
	 * @throws Exception
	 */
	@Test
	public void indexTest() throws Exception {
		
		mockMvc.perform(get("/")).andDo(print())
			.andExpect(handler().handlerType(MainController.class))
			.andExpect(handler().methodName("index"))
			.andExpect(view().name("index"))
			.andExpect(forwardedUrl("/WEB-INF/tiles/template.jsp"))
			.andExpect(status().isOk());
	}

	/**
	 * testing the login using user1/user1 credentials (good ones)
	 * check if after the login the client is redirected to the home page
	 * and if the model contains the correct user (user_id = 1)
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginUser1Ok() throws Exception {		
		
		HttpSession session = mockMvc.perform(post("/login-process").param("j_username", "user1").param("j_password", "user1"))
			.andExpect(status().is(HttpStatus.FOUND.value()))
			.andExpect(redirectedUrl("/"))
			.andReturn()
			.getRequest()
			.getSession();				
	
		Assert.assertNotNull(session);		

		Map<String,Object> model = mockMvc.perform(get("/").session((MockHttpSession)session).locale(Locale.ENGLISH))
			.andDo(print())
			.andExpect(status().isOk())	
			.andExpect(view().name("logged_in"))
			.andExpect(model().attributeExists(UserConstants.USER_MODEL_ATTR_NAME))
			.andReturn()
			.getModelAndView()
			.getModel();
		
		User userFromModel = (User) model.get(UserConstants.USER_MODEL_ATTR_NAME);
		
		Assert.assertEquals(1,userFromModel.getUserId());

	}

	/**
	 * testing the login using user1/wrong_password credentials (wrong ones)
	 * check if after the login the client is redirected to the home page with the
	 * loginError query string parameter set to true
	 * and if the model does not contain the user
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginUser1Ko() throws Exception {

		HttpSession session = mockMvc.perform(post("/login-process").param("j_username", "user1").param("j_password", "wrong_pass"))
			.andDo(print())
			.andExpect(status().is(HttpStatus.FOUND.value()))
			.andExpect(redirectedUrl("/?loginError=true"))
			.andReturn()
			.getRequest()
			.getSession();	
		
		mockMvc.perform(get("/").session((MockHttpSession)session).locale(Locale.ENGLISH))
			.andDo(print())
			.andExpect(status().isOk())	
			.andExpect(view().name("index"))
			.andExpect(model().attributeDoesNotExist(UserConstants.USER_MODEL_ATTR_NAME));				
	}
	
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(MvcTest.class);
	
	private MockMvc mockMvc;

	@Autowired
	private FilterChainProxy springSecurityFilter;

	@Autowired
	private WebApplicationContext webappContext;

}
