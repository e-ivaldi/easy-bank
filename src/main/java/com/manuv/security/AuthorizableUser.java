package com.manuv.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author emanuele.ivaldi@gmail.com
 *
 */
public abstract class AuthorizableUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Collection<? extends GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public abstract String getUsername();
	
	@Override
	public abstract String getPassword();

	//TODO: atm all these getters return true
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
