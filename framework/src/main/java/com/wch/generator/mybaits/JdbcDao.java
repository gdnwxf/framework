
 /**************************************************************************
 * Copyright (c) 2006-2015 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称：区域大通关系统
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
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
