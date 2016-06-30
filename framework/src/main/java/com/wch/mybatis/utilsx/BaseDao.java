package com.wch.mybatis.utilsx;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDao {

	/*@Autowired
	private JdbcTemplate jdbcTemplate;*/
	/*
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;*/


	public void query() {
		String sql = null;
	//	Configuration configuration = sqlSessionTemplate.getConfiguration();

	}



}
