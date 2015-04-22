package com.my.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.shiro.annotation.ControllerLog;
import com.my.shiro.bo.BookService;
import com.my.shiro.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ControllerLog(description = "列表用户")
	public String list(User user) {
		bookService.book(user.getUserName());
		System.out.println("==============list=" + user.getUserName() + "," + user.getPassword());
		return "user/list";
	}
}
