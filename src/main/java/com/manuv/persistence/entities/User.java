package com.manuv.persistence.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manuv.security.AuthorizableUser;

/**
 * @author emanuele.ivaldi@gmail.com
 * 
 */
@Entity
@Table(name = "users")
public class User extends AuthorizableUser {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", nullable = false, length = 64, unique = true)
	private long userId;

	@Column(name = "username", nullable = false, length = 16, unique = true)
	private String username;

	@Column(name = "password", nullable = false, length = 64)
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Account> accounts;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// this method is called by the authentication-provider and it is used in
	// the password encoder to
	// salt the form-input user password
	public String getSalt() {

		StringBuilder sb = new StringBuilder();
		sb.append(username);
		sb.reverse();
		sb.append("X");
		sb.append(username);

		String salt = sb.toString();

		logger.debug("using salt: " + salt + "for user " + username);

		return salt;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	private final static Logger logger = LoggerFactory.getLogger(User.class);

}
