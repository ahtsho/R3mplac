package com.ahtsho.boot.domain;

public class User {

	private int id;
	private String username;
	private String mister;
	private String name;
	private String surname;
	private String role;

	public User() {}

	public User(int id, String username, String mister, String name, String surname, String role) {
		super();
		this.id = id;
		this.username = username;
		this.mister = mister;
		this.name = name;
		this.surname = surname;
		this.role = role;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMister() {
		return mister;
	}
	public void setMister(String mister) {
		this.mister = mister;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


}
