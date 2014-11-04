package com.manuv.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manuv.persistence.entities.User;

/**
 * @author emanuele.ivaldi@gmail.com
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class UserDao extends BaseDaoImpl<User> {

	public UserDao() {

		super(User.class);
	}

	public User getUserByUsername(String username) {

		List<User> auths = this.entityManager.createQuery("select a from " + User.class.getName() + " a where a.username = :username")
				.setParameter("username", username).getResultList();

		if (!auths.isEmpty()) {
			return auths.get(0);
		}

		return null;
	}

}
