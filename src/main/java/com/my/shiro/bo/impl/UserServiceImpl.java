package com.my.shiro.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.my.shiro.bo.UserService;
import com.my.shiro.entity.Permission;
import com.my.shiro.entity.Role;
import com.my.shiro.entity.User;

@Component
public class UserServiceImpl implements UserService {

	public User getByUserName(String userName) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword("123456");
		user.setNickName("jack");
		
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setName("admin");
		
		List<Permission> permissions = new ArrayList<Permission>();
		Permission permission = new Permission();
		permission.setUrl("admin:user");
		permissions.add(permission);
		role.setPermissions(permissions);
		roles.add(role);
		user.setRoles(roles);
		return user;
	}

}
