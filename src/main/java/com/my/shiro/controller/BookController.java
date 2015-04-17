package com.my.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/book")
public class BookController {

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list() {
		System.out.println("==============book-list==============");
		return "book/list";
	}
}
