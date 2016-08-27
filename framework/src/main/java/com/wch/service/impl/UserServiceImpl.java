package com.wch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wch.dao.mapper.UserInfoMapper;
import com.wch.domain.UserInfo;
import com.wch.service.UserService;

@Service("userService")
public class UserServiceImpl implements  UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	 
	
	public  void update() {
		UserInfo record = new UserInfo();
		record.setId(1l);
		record.setDa(6l);
		userInfoMapper.updateByPrimaryKey(record);
	}
	
	
	@Override
	public void xiugai() {
		this.update();
	}

}
