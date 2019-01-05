package com.ahtsho.boot.domain;

public class Role {
	private String role_name;
	private String description;

	public Role(String name, String description) {
		super();
		this.role_name = name;
		this.description = description;
	}

	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
