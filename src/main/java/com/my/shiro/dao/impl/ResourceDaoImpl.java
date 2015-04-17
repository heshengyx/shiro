package com.my.shiro.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.my.shiro.dao.ResourceDao;
import com.my.shiro.security.Resource;

@Component
public class ResourceDaoImpl implements ResourceDao {

	public List<Resource> getResources() {
		List<Resource> resources = new ArrayList<Resource>();
		Resource resource = new Resource();
		resource.setValue("/user/**");
		resource.setPermission("admin:user");
		
		resource = new Resource();
		resource.setValue("/book/**");
		resource.setPermission("admin:book");
		resources.add(resource);
		return resources;
	}

}
