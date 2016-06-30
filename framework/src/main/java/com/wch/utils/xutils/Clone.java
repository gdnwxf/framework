
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.wch.utils.clazz.ClassUtils;


 /**
 *  
 * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
 * @version $Id: Clone.java 33 2015-10-11 12:36:48Z wch $   
 * @since 2.0
 */
public class Clone {

	/**
	 * 深克隆对象
	 * @param t
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public  static <T> T deepClone(T t) throws ClassNotFoundException, IOException {
		//判断其是否实现了Serializable 
		if (ClassUtils.isImSerializable(t.getClass())) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(30);
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(t);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		}
		throw  new IOException("你要实现克隆的类未实现Serializable 无法克隆 ");
	}
}
