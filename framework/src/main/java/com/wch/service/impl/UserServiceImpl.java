package com.wch.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.annotation.Resource;

import com.wch.service.BaseTransactionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.wch.dao.mapper.UserInfoMapper;
import com.wch.domain.UserInfo;
import com.wch.service.UserService;

@Service("userService")
public class UserServiceImpl implements  UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	 
	@Autowired
	private ApplicationContext applicationContext;
	@Resource(name="BaseTransactionTestService")
	private BaseTransactionTest baseTransactionTest;
	
	@Autowired
	private InnerService innerService;
	
	public  void update() {
		UserInfo record = new UserInfo();
		record.setId(1l);
		record.setDa(6l);
//		baseTransactionTest.newUpdate();
		System.out.println("--1--");
		baseTransactionTest.selectById();
		baseTransactionTest.update();
//		Assert.notNull(null);
		userInfoMapper.updateByPrimaryKey(record);
		System.out.println("--2--");
//		UserInfo selectByPrimaryKey = userInfoMapper.selectByPrimaryKey(1l);
//		System.out.println(selectByPrimaryKey);
//		this.xiugai();
		System.out.println("this is xiugai ");
	}
	
	
	@Override
	public void xiugai() {
//		baseTransactionTest.update();
//		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1l);
//		System.out.println(userInfo);
		System.out.println("--first--");
		UserInfo userInfo2 = innerService.selectInfo(1l);
		System.out.println(userInfo2);
		baseTransactionTest.newUpdate();
//		(applicationContext.getBean(UserService.class)).update();
		this.update();
		
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

