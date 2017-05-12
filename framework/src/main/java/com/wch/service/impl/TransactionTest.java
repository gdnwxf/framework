package com.wch.service.impl;

import com.wch.service.BaseTransactionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wch.dao.mapper.UserInfoMapper;
import com.wch.domain.UserInfo;
 


@Service("BaseTransactionTestService")
public class TransactionTest implements BaseTransactionTest {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Override
	public void update() {
		UserInfo record = new UserInfo();
		record.setId(1l);
		record.setPart("wubiiiiii");
		record.setDa(8l);
		userInfoMapper.updateByPrimaryKey(record);
	}
	@Override
	public void newUpdate() {
		// TODO Auto-generated method stub
		System.out.println("new update");
		UserInfo record = new UserInfo();
		record.setId(1l);
		record.setPart("new");
		record.setDa(8l);
		userInfoMapper.updateByPrimaryKey(record);
		System.out.println("new update");
	}
	
	@Override
	public void selectById() {
		// TODO Auto-generated method stub
		UserInfo selectByPrimaryKey = userInfoMapper.selectByPrimaryKey(1l);
		System.out.println(selectByPrimaryKey);
	}
}


