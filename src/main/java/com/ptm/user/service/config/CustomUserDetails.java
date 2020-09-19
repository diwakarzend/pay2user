package com.ptm.user.service.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ptm.user.service.enitity.Privilege;
import com.ptm.user.service.enitity.Role;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final String username;
	private final String password;
	private final String mobileNumber;
	private final String roles;
	private Set<Role> authorities;

	public CustomUserDetails(String username, String password, String mobileNumber, Set<Role> authorities,
			String roles) {
		super();
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.authorities = authorities;
		this.roles = roles;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getGrantedAuthorities(getPrivileges(authorities));
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

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

	public String getMobileNumber() {
		return mobileNumber;
	}

	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private Set<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	public String getRoles() {
		return roles;
	}

}
