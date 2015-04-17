package com.my.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.shiro.entity.User;

@Controller
@RequestMapping("/security")
public class LoginController {

	@RequestMapping("/login")
	public String login(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUserName(), user.getPassword());
		//token.setRememberMe(true);
		subject.login(token);
		System.out.println("==============login=" + user.getUserName() + ","
				+ user.getPassword());
		return "redirect:/security/success";
	}

	@RequestMapping("/success")
	public String success() {
		System.out.println("==============success=");
		return "success";
	}
}
