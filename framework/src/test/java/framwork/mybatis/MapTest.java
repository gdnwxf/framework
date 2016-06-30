
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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
  *  
  * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
  * @version $Id$   
  * @since 2.0
  */
public class MapTest {

	public static void main(String[] args) {
		Map<String ,String> map2 = new HashMap<String ,String>(2);
		Map<String ,String> map = new LinkedHashMap<String ,String>(2);
		map.put("dsa", "add");
		map.put("a321", "add");
		map.put("b321", "add");
		
		map2.put("dsa", "add");
		map2.put("a321", "add");
		map2.put("b321", "add");
		map.get("dsa");
		System.out.println( Arrays.toString(map.keySet().toArray(new String[0])));
		System.out.println( Arrays.toString(map2.keySet().toArray(new String[0])));
		
	}
}
