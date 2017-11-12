package com.ahtsho.boot.domain;

public class UserInfo {
	private int id;
	private int user_id;
	private byte[] cv;
	private byte[] photo;

	public UserInfo() {}

	public UserInfo(int id, int user_id, byte[] cv, byte[] photo) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.cv = cv;
		this.photo = photo;
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
	public byte[] getCv() {
		return cv;
	}
	public void setCv(byte[] cv) {
		this.cv = cv;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
}
