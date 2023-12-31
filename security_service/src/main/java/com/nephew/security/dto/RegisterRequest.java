package com.nephew.security.dto;

import com.nephew.security.entities.Role;

public class RegisterRequest {
	private String firstName;
	private String lastName;
	private String email; // user name
	private String password;
	private String companyName;
	private Role role;

	public RegisterRequest(String firstName, String lastName, String email, String password, String companyName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.companyName = companyName;
	}

	public RegisterRequest() {
		super();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "RegisterRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", companyName=" + companyName + "]";
	}

}
