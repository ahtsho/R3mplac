package com.ahtsho.boot.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;
import com.ahtsho.boot.dao.jpa.UserRepository;
import com.ahtsho.boot.domain.User;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	CounterService counterService;

	@Autowired
	GaugeService gaugeService;

	public UserService() {
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
