package com.wch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wch.dao.mapper.UserInfoMapper;
import com.wch.domain.UserInfo;
 


@Service
public class TransactionTest implements BaseTransactionTest {
	
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
}



interface BaseTransactionTest {

	void update();
	
	void newUpdate();
	
} 