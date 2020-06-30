package com.example.demo.auth.model;

public class AuthenticationRequest {

	private String Username;
	private String Password;

	public AuthenticationRequest() {

	}

	public AuthenticationRequest(String username, String password) {

		Username = username;
		Password = password;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
