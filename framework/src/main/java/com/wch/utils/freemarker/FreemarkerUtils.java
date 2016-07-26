
 
package com.wch.utils.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


 
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
			templateDir = templateDir == null? null : Thread.currentThread().getContextClassLoader().getResource("").getPath() + templateDir; 
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
	 * @param templateDir the template dir
	 * @param ftlNameOrPathName the ftl name or path name
	 * @param path the path
	 * @param isPrint the is print
	 */
	public static void process(Object object ,String templateDir , String ftlNameOrPathName , Writer writer)  {
		Writer fileWriter = null;
		try {
			Configuration configuration = new Configuration(Configuration.getVersion());
			templateDir = templateDir == null? null : Thread.currentThread().getContextClassLoader().getResource("").getPath() + templateDir; 
			File file = new File(templateDir +File.separator+ ftlNameOrPathName);
			if (!file.exists()) {
				System.out.println("模板不存在!!!");
				return;
			}
			configuration.setDirectoryForTemplateLoading(templateDir == null ? file.getParentFile() : new File(templateDir));
			Template template = configuration.getTemplate(templateDir == null ? file.getName() : ftlNameOrPathName, ENCODEING);
			fileWriter = writer; 
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
