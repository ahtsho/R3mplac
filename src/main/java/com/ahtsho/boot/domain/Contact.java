package com.ahtsho.boot.domain;

public class Contact {
	private int id;
	private int user_id;
	private String phone_no;
	private String email;
	private String facebook;

	public Contact() {}

	public Contact(int id, int user_id, String phone_no, String email, String facebook) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.phone_no = phone_no;
		this.email = email;
		this.facebook = facebook;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
}