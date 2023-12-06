package com.nephew.ss.ntsecurityservice.controllers;

import com.nephew.ss.ntsecurityservice.entities.Role;

public class Action {
	private Role role;
	
	public Action() {
		super();
	}

	public Action(Role role) {
		super();
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Action [role=" + role + "]";
	}

}
