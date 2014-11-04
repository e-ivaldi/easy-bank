package com.manuv.test.persistence;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manuv.persistence.entities.Account;
import com.manuv.persistence.entities.User;
import com.manuv.persistence.service.UserService;

@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(locations = { "classpath:context/root-context.xml" })
public class EntityTest {

	// private final Logger logger = LoggerFactory.getLogger(EntityTest.class);

	@Autowired
	private UserService userService;

	/**
	 * Testing the User Service (only reading atm)
	 */
	@Test
	public void testUser1() {		

		User user = null;
		Account userAccount = null;

		user = userService.getUser(1);

		Assert.assertEquals(1, user.getUserId());
		Assert.assertEquals("user1", user.getUsername());
		Assert.assertEquals("e8e4b17b575a882c3f730b97425a378e9e8234f330a4cdcbd717cd09958cc58275e627ed79ef2b481ff9364e3dac9e6b0c03f25ac2046c352fa6d8054a276aa4", user.getPassword());
		Assert.assertNotNull(user.getAccounts());
		Assert.assertEquals(user.getAccounts().size(), 2);
		
		userAccount = user.getAccounts().get(0);
		
		Assert.assertEquals(new BigDecimal("100.2500"),userAccount.getAmount());

		userAccount = user.getAccounts().get(1);

		Assert.assertEquals(new BigDecimal("100.0000"),userAccount.getAmount());
		
	}



}
