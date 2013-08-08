package com.manuv.persistence.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manuv.persistence.entities.User;

public interface UserService {

	@Transactional(propagation = Propagation.REQUIRED)
	void saveUser(User obj);

	@Transactional(propagation = Propagation.REQUIRED)
	User getUserByUsername(String username);

	@Transactional(propagation = Propagation.REQUIRED)
	User getUser(long userId);

	@Transactional(propagation = Propagation.REQUIRED)
	void mergeUser(User user);

}
