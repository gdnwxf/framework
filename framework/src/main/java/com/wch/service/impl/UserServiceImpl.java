package com.wch.service.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wch.service.UserService;

@Service("userService")
public class UserServiceImpl implements  UserService {


	
	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public  void update() {
		// TODO Auto-generated method stub

		System.out.println(template);
	}

}
