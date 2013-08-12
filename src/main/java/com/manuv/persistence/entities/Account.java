package com.manuv.persistence.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author emanuele.ivaldi@gmail.com
 * 
 */
@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@Column(name = "account_id", nullable = false, length = 11, unique = true)
	private long account_id;

	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	User user;

	@Column(name = "amount", nullable = false, length = 64)
	private BigDecimal amount;

	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(Account.class);

}
