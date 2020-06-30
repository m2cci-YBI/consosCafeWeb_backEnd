package com.example.demo.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Programmeur;

public class MyUserDetails implements UserDetails {

	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public MyUserDetails() {

	}

	public MyUserDetails(Programmeur programmeur) {

		this.username = programmeur.getNom();
		this.password = programmeur.getMotDePasse();
		this.authorities = Arrays.stream(programmeur.getRoles().split(","))
				.map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
