package com.ahtsho.boot.api.rest;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ahtsho.boot.domain.Activity;
import com.ahtsho.boot.service.CalendarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/calendar")
@Api(tags = { "calendar" })
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalendarController {

	@Autowired
	private CalendarService calendarService;

	@GET
	@RequestMapping(value = "/list")
	public List<Activity> getAllEntries() {
		return this.calendarService.getAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create an activity", notes = "Returns the URL of the new resource in the Location header.")
	public void createActivityForUser(@RequestBody Activity activity, HttpServletRequest request, HttpServletResponse response) {
		this.calendarService.create(activity);
	}
}
