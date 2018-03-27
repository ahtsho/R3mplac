package com.ahtsho.boot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;

import com.ahtsho.boot.dao.jpa.CalendarRepository;
import com.ahtsho.boot.domain.Activity;

@Service
public class CalendarService {

	private static final Logger log = LoggerFactory.getLogger(CalendarService.class);

	@Autowired
	private CalendarRepository calendarRepository;

	@Autowired
	CounterService counterService;

	@Autowired
	GaugeService gaugeService;

	public CalendarService() {
	}

	public List<Activity> getAll() {
		return calendarRepository.findAll();
	}

	public void create(Activity activity) {
		calendarRepository.create(activity);
	}
/*
	public List<User> search(User user) {
		return calendarRepository.find(user);
	}

	public UserDetailsDAO getUserDetails(int id) {
		return new UserDetailsDAO(
				calendarRepository.findUser(id), 
				calendarRepository.findUserInfo(id), 
				calendarRepository.findContacts(id));
	}

	public void createContact(Contact contact) {
		calendarRepository.insertContact(contact);
	}

	public void createUserinfo(UserInfo info) {
		calendarRepository.insertUserInfo(info);
	}

	public void update(User user) {
		calendarRepository.update(user);
	}
	*/
}
