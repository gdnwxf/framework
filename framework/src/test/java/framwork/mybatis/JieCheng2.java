
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


 /**
  *  
  * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
  * @version $Id$   
  * @since 2.0
  */
public class JieCheng2 {

	
	public static void main(String[] args) {
		System.out.println(jc(5));
	}
	
	public static Integer jc(int  a) {
		if(a==1) {
			return a ; 
		}
		
		return a * jc(a-1);
	}
}
