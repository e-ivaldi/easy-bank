package com.manuv.test.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manuv.persistence.entities.User;
import com.manuv.persistence.service.UserService;

@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context/root-context.xml" })
public class EntityTest {

	private final Logger logger = LoggerFactory.getLogger(EntityTest.class);

	@Autowired
	private UserService userService;

	/**
	 * Testing the User Service (only reading atm)
	 */
	@Test
	public void userServiceTest() {

		logger.debug("Testing UserService");

		User user = null;

		user = userService.getUser(1);

		checkUser(user, 1);

		user = userService.getUserByUsername("user1");

		checkUser(user, 1);

		user = userService.getUser(2);

		checkUser(user, 2);

		user = userService.getUserByUsername("user2");

		checkUser(user, 2);
	}

	private void checkUser(User user, int userId) {

		String username = "user" + Integer.toString(userId);

		Assert.assertEquals(userId, user.getUserId());
		Assert.assertEquals(username, user.getUsername());
		Assert.assertEquals(username, user.getPassword());
	}

}
