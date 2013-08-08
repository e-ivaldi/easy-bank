package com.manuv.test.persistence;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context/root-context.xml" })
public class PasswordGeneratorTest {

	@Test
	public void PasswordGenerator() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		logger.debug("testing password generation");

		String cryptedPassword = null;

		cryptedPassword = encoder.encodePassword("user1", "1resuXuser1");

		Assert.assertNotNull(cryptedPassword);

		Assert.assertEquals(
				"e8e4b17b575a882c3f730b97425a378e9e8234f330a4cdcbd717cd09958cc58275e627ed79ef2b481ff9364e3dac9e6b0c03f25ac2046c352fa6d8054a276aa4",
				cryptedPassword);

		cryptedPassword = encoder.encodePassword("user2", "2resuXuser2");

		Assert.assertNotNull(cryptedPassword);

		Assert.assertEquals(
				"135b2ac24cd514c32b2170032bfcb06a228a5aa999018c21330d1ab40eff72e1ac6a85ab3e5a4cc13a5a51ff6d923acdb0010a5d7e9f6dbc06fdfea8a2ae2c59",
				cryptedPassword);
	}

	private final Logger logger = LoggerFactory.getLogger(PasswordGeneratorTest.class);

	@Autowired
	@Qualifier("sha512PasswordEncoder")
	PasswordEncoder encoder;

}
