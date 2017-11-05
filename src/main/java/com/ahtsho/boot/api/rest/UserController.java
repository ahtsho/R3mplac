package com.ahtsho.boot.api.rest;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ahtsho.boot.domain.User;
import com.ahtsho.boot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/users")
@Api(tags = { "users" })
public class UserController extends AbstractRestHandler {

	@Autowired
	private UserController userService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a list of all users.", notes="None")
	public @ResponseBody List<User> getAllUsers(HttpServletRequest request, HttpServletResponse response) {
		return this.userService.getAllUsers();
	}
}
