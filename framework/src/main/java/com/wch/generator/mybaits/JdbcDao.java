
 
package com.wch.generator.mybaits;

import java.util.List;

import com.wch.pojo.parser.Filter;

/**
 * @author wch
 *
 */
public interface JdbcDao  {

	int update(Object object);
		
	int delete(Object object);
	 
	int create(Object object);

	<T> int count(Filter<T> clazz);

	<T> T get(Filter<T> filter);
	 
	<T> List<T> list(Filter<T> filter);

	<T> List<T> findFirst(Filter<T> filter);

}
