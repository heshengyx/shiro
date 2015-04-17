package com.my.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.shiro.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(User user) {
		System.out.println("==============list=" + user.getUserName() + "," + user.getPassword());
		return "user/list";
	}
}
