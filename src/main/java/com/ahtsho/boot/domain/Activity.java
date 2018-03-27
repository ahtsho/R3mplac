package com.ahtsho.boot.domain;

import java.util.Date;

public class Activity {
	private String activity;
	private int user_id;
	private Date start;
	private Date end;

	public Activity() {
		super();
	}

	public Activity(int user_id, String activity, Date from, Date to) {
		super();
		this.activity = activity;
		this.user_id = user_id;
		this.start = from;
		this.end = to;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
