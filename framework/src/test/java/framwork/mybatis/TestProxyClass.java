
 /**************************************************************************
 * Copyright (c) 2006-2015 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称：区域大通关系统
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package framwork.mybatis;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wch.service.UserService;

/**
  *  
  * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
  * @version $Id$   
  * @since 2.0
  */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/beans.xml")
public class TestProxyClass {
  
	@Autowired
	UserService userService;
	
	@Autowired
	ApplicationContext atx ;
	@Test
	public void testClassName() throws Exception {
		
		System.out.println(userService.getClass());
		System.out.println(atx.getBean("userService").getClass());
		System.out.println(atx.getBean("userController").getClass());
	}
}
