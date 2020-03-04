package com.harvey.work.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harvey.work.service.UserService;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/init", method = RequestMethod.GET, produces = "application/json")
	public Object initRedis() {
		return userService.init();
	}
	
	@RequestMapping(value = "/putRedis/{model}", method = RequestMethod.GET, produces = "application/json")
	public Object putRedis(@PathVariable String model) {
		String userId = UUID.randomUUID().toString().toUpperCase().replace("-", "").toLowerCase();
		return userService.secondKill(userId,model);
	}
	
	@RequestMapping(value = "/putSset", method = RequestMethod.POST)
	public Object putSset(String value,int score) {
		return userService.sortSet(value,score);
	}
	
	@RequestMapping(value = "/getId", method = RequestMethod.GET)
	public void getId() {
		userService.getId();
	}

}
