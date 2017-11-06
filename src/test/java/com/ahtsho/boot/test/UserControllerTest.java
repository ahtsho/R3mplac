package com.ahtsho.boot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ahtsho.boot.Application;
import com.ahtsho.boot.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class UserControllerTest {

	@Autowired
	UserService service;

	@Test
	public void getAllUsers_test() {
		Assert.assertEquals(1, service.getAllUsers().size());
	}
}
