package com.wch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wch.dao.mapper.UserInfoMapper;
import com.wch.domain.UserInfo;
import com.wch.service.UserService;

@Service("userService")
public class UserServiceImpl implements  UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	 
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private BaseTransactionTest baseTransactionTest;
	
	@Autowired
	private InnerService innerService;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public  void update() {
		UserInfo record = new UserInfo();
		record.setId(1l);
		record.setDa(6l);
		userInfoMapper.updateByPrimaryKey(record);
		baseTransactionTest.newUpdate();
//		this.xiugai();
		System.out.println("this is xiugai ");
	}
	
	
	@Override
	public void xiugai() {
		baseTransactionTest.update();
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1l);
		System.out.println(userInfo);
		baseTransactionTest.newUpdate();
		UserInfo userInfo2 = innerService.selectInfo(1l);
		System.out.println(userInfo2);
		
	}

}
@Service
class InnerServiceImpl implements  InnerService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	@Override
	public void update() {
		UserInfo record = new UserInfo();
		record.setId(1l);
		record.setDa(8l);
		userInfoMapper.updateByPrimaryKey(record);
	}
	@Override
	public UserInfo selectInfo(Long id) {
		return userInfoMapper.selectByPrimaryKey(1l);
	}
	
}
interface  InnerService {
	void update();
	UserInfo selectInfo(Long id);
}

