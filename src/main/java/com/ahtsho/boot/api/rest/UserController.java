package com.ahtsho.boot.api.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ahtsho.boot.dao.UserDetailsDAO;
import com.ahtsho.boot.domain.Contact;
import com.ahtsho.boot.domain.User;
import com.ahtsho.boot.domain.UserInfo;
import com.ahtsho.boot.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/users")
@Api(tags = { "users" })
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController  {

	@Autowired
	private UserService userService;

	@GET
	@RequestMapping(value = "/list")
	public List<User> getAllUsers() {
		return this.userService.getAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create a user resource.", notes = "Returns the URL of the new resource in the Location header.")
	public void createUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		this.userService.create(user);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create a user resource.", notes = "Returns the URL of the new resource in the Location header.")
	public void editUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		this.userService.update(user);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search a user resource.", notes = "Returns the list of users matching the combination of available user fields.")
	public @ResponseBody List<User> searchUser(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		return this.userService.search(user);
	}
/*
	@RequestMapping(value = "/detail", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get user details.")
	public @ResponseBody UserDetailsDAO getUserDetails(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		return this.userService.getUserDetails(user);
	}
*/
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a single hotel.", notes = "You have to provide a valid User ID.")
	public @ResponseBody UserDetailsDAO getUserDetails(
			@ApiParam(value = "The ID of the user.", required = true) @PathVariable("id") int id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return this.userService.getUserDetails(id);
	}

	@RequestMapping(value = "/contact/add", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create a user contact.")
	public void createContact(@RequestBody Contact contact, HttpServletRequest request, HttpServletResponse response) {
		this.userService.createContact(contact);
	}

	@RequestMapping(value = "/userinfo/add", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create a user info.")
	public void createUserinfo(@RequestBody UserInfo info, HttpServletRequest request, HttpServletResponse response) {
		this.userService.createUserinfo(info);
	}
}
