package com.manuv.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuv.persistence.dao.UserDao;
import com.manuv.persistence.entities.User;

/**
 * @author emanuele.ivaldi@gmail.com
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public void saveUser(User obj) {

		authDao.save(obj);
	}

	@Override
	public void mergeUser(User user) {

		authDao.merge(user);
	}

	@Override
	public User getUserByUsername(String username) {

		return authDao.getUserByUsername(username);
	}

	@Override
	public User getUser(long userId) {

		return authDao.get(userId);
	}

	@Autowired
	UserDao authDao;

}
