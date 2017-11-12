package com.ahtsho.boot.dao;

import java.util.List;

import com.ahtsho.boot.domain.Contact;
import com.ahtsho.boot.domain.User;
import com.ahtsho.boot.domain.UserInfo;

public class UserDetailsDAO {

	private User user;
	private List<UserInfo> info;
	private List<Contact> contacts;

	public UserDetailsDAO(User user, List<UserInfo> info, List<Contact> contacts) {
		this.user = user;
		this.info = info;
		this.contacts = contacts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserInfo> getInfo() {
		return info;
	}
	public void setInfo(List<UserInfo> info) {
		this.info = info;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}
