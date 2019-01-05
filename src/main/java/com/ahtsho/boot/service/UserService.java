package com.ahtsho.boot.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;
import com.ahtsho.boot.dao.UserDetailsDAO;
import com.ahtsho.boot.dao.jpa.RoleRepository;
import com.ahtsho.boot.dao.jpa.UserRepository;
import com.ahtsho.boot.domain.Contact;
import com.ahtsho.boot.domain.Role;
import com.ahtsho.boot.domain.User;
import com.ahtsho.boot.domain.UserInfo;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository rolesRepository;

	@Autowired
	CounterService counterService;

	@Autowired
	GaugeService gaugeService;

	public UserService() {
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public void create(User user) {
		userRepository.create(user);
	}

	public List<User> search(User user) {
		return userRepository.find(user);
	}

	public UserDetailsDAO getUserDetails(int id) {
		return new UserDetailsDAO(
				userRepository.findUser(id), 
				userRepository.findUserInfo(id), 
				userRepository.findContacts(id));
	}

	public void createContact(Contact contact) {
		userRepository.insertContact(contact);
	}

	public void createUserinfo(UserInfo info) {
		userRepository.insertUserInfo(info);
	}

	public void update(User user) {
		userRepository.update(user);
	}

	public List<Role> getAllRoles() {
		return rolesRepository.findAll();
	}
}
