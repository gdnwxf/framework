
 /**************************************************************************
 * Copyright (c) 2006-2015 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称：区域大通关系统
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.wch.utils.xutils;

import java.util.LinkedList;
import java.util.List;


 /**
 *  
 * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
 * @version $Id: ArraysHelp.java 33 2015-10-11 12:36:48Z wch $   
 * @since 2.0
 */
public class ArraysHelp {

	
	@SuppressWarnings("unchecked")
	public static <T> T[]  removeSame(T[] t1 , T[] t2) {
		List<T> list = new LinkedList<T>();
		for (T t : t1) {
			list.add(t) ;
		}
		
		for (T t : t2) {
			if(!list.contains(t)) {
				list.add(t) ;
			}
		}
		return (T[]) list.toArray();
	}
}
