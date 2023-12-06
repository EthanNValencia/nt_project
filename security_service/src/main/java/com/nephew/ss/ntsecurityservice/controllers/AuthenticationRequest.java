package com.nephew.ss.ntsecurityservice.controllers;

public class AuthenticationRequest {
	private String email;
	private String password;

	public AuthenticationRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public AuthenticationRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthenticationRequest [email=" + email + ", password=" + password + "]";
	}
	
}
