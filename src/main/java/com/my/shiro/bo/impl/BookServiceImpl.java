package com.my.shiro.bo.impl;

import org.springframework.stereotype.Service;

import com.my.shiro.bo.BookService;

@Service
public class BookServiceImpl implements BookService {

	public void book(String name) {
		System.out.println("=============book=============" + name);
	}

}
