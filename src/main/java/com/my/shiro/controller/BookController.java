package com.my.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.shiro.annotation.ControllerLog;

@Controller
@RequestMapping("/book")
public class BookController {

	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ControllerLog(description = "É¾³ýÓÃ»§")
	public String list() {
		System.out.println("==============book-list==============");
		return "book/list";
	}
}
