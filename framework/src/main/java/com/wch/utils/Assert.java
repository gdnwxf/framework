
 /**************************************************************************
 * Copyright (c) 2006-2015 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称：区域大通关系统
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.wch.utils;



 /**
 * TODO 请在此处添加注释
 * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
 * @version $Id: Assert.java 25 2015-10-08 09:28:08Z wch $   
 * @since 2.0
 */
public class Assert {

	/**
	 * 判断是否是空
	 * TODO 请在此处添加注释
	 * @param object
	 */
	public static void notNull(Object object){
		if (object == null) {
			throw new NullPointerException(object  + " the Object is null");
		}
	}
	
	/**
	 * 判断是否是空
	 * TODO 请在此处添加注释
	 * @param object
	 */
	public static void notNull(Object object ,String msg){
		if (object == null) {
			throw new NullPointerException(  object + ( msg == null? "": msg)  + " the Object is null");
		}
	}
	
	public static void isNull(Object  object) {
		if (object != null) {
			throw new NullPointerException(object  + " the Object is not null");
		}
	}
	
	/**
	 * 判断是否是空
	 * TODO 请在此处添加注释
	 * @param object
	 */
	public static void isNull(Object object ,String msg){
		if (object != null) {
			throw new NullPointerException(object  + (msg == null? "" : msg) + " the Object is null");
		}
	}
}
