/**
 * 
 */
package com.wch.generator.mybaits.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.sql.SqlMapper;
import com.wch.generator.mybaits.JdbcDao;
import com.wch.pojo.parser.Filter;

/**
 * @author wch
 *
 */
public class JdbcDaoImpl implements JdbcDao {

	@Autowired
	SqlMapper mapper;

	public int update(Object object) {
		return 0;
	}

	public int delete(Object object) {
		return 0;
	}

	public int create(Object object) {
		return 0;
	}

	public <T> int count(Filter<T> clazz) {
		return 0;
	}

	public <T> T get(Filter<T> filter) {
		return null;
	}

	public <T> List<T> list(Filter<T> filter) {

		return null;
	}

	public <T> List<T> findFirst(Filter<T> filter) {
		return null;
	}
}
