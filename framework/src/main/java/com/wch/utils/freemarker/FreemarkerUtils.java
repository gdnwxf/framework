
 /**************************************************************************
 * Copyright (c) 2006-2015 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称：区域大通关系统
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.wch.utils.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


 /**
 *  
 * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
 * @version $Id: FreemarkerUtils.java 35 2015-10-25 13:27:57Z wch $   
 * @since 2.0
 */
public class FreemarkerUtils {
   
	
	/** The Constant ENCODEING. */
	private final static String ENCODEING = "UTF-8";

	/**
	 * Process.
	 *
	 * @param object the object
	 * @param templateDir the template dir
	 * @param ftlNameOrPathName the ftl name or path name
	 * @param path the path
	 * @param isPrint the is print
	 */
	public static void process(Object object ,String templateDir , String ftlNameOrPathName ,String path , boolean isPrint)  {
		Writer fileWriter = null;
		try {
			Configuration configuration = new Configuration(Configuration.getVersion());
			File file = new File(ftlNameOrPathName);
			if (!file.exists()) {
				System.out.println("模板不存在!!!");
				return;
			}
			configuration.setDirectoryForTemplateLoading(templateDir == null ? file.getParentFile() : new File(templateDir));
			Template template = configuration.getTemplate(templateDir == null ? file.getName() : ftlNameOrPathName, ENCODEING);
			fileWriter = isPrint ? new  PrintWriter(System.out) : new FileWriter(path);
			template.process(object, fileWriter);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fileWriter!=null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Process.
	 *
	 * @param object the object
	 * @param templatePathName the template path name
	 * @param path the path
	 */
	public static void process(Object object , String templatePathName ,String path)  {
		process(object, null, templatePathName, path ,false);
	}
	
	/**
	 * Prints the.
	 *
	 * @param object the object
	 * @param templatePathName the template path name
	 */
	public static void print(Object object , String templatePathName) {
		process(object, null, templatePathName ,null,true);
	}
}
