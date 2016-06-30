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
 * 
 * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
 * @version $Id: GException.java 25 2015-10-08 09:28:08Z wch $
 * @since 2.0
 */
public class GException extends RuntimeException {

	/**    */
	private static final long serialVersionUID = 1L;

	/**
	  */
	public GException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public GException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public GException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
