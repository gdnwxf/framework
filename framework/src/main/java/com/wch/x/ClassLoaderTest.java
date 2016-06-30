
 /**************************************************************************
 * Copyright (c) 2006-2015 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称：区域大通关系统
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.wch.x;



 /**
 *  
 * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
 * @version $Id: ClassLoaderTest.java 36 2015-11-28 13:32:31Z wch $   
 * @since 2.0
 */
public class ClassLoaderTest    {
	  
	/**
	 */
	public ClassLoaderTest() {
		System.out.println("构造方法");
	}
	  
	  static
	  {
	    try
	    {
	    	MapContainer.registerDriver("32132132131");
	    }
	    catch (Exception E)
	    {
	      throw new RuntimeException("Can't register driver!");
	    }
	  }

}
