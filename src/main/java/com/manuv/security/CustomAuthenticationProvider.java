package com.manuv.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.manuv.persistence.entities.User;
import com.manuv.persistence.service.UserService;

public class CustomAuthenticationProvider implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.debug("load by username...");

		User user = userService.getUserByUsername(username);

		if (user == null) {

			throw new UsernameNotFoundException("unable to find the user");
		}

		// just a temporany workaround, the authorities will be load from the db together with the user
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		user.setAuthorities(grantedAuthorities);

		return user;

	}

}
