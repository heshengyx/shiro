package com.my.shiro.bo;

import com.my.shiro.entity.User;

public interface UserService {

	User getByUserName(String userName);
}
