package com.wch.core;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * 实现类SpringMVC的核心类
 * @author GDNWXF
 * @date 2014年6月22日  下午6:25:39
 */
public class Dispatcher implements Servlet {
	
	private ServletConfig config;
	private Map<String, Method> getRequest ;
	private Map<String, Method> postRequest ;
	
	private final static String GET_REQUEST  = "getRequest" ;
	private final static String POST_REQUEST  = "postRequest" ;
	private static final String APPLICATIONCONTEXT = "applicationContext";
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}

	/**
	 * 容器
	 */
	public ServletConfig getServletConfig() {
		return config;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(ServletConfig config) throws ServletException {
		//启动实例化时进行的操作
	    ServletContext servletContext = config.getServletContext();
		this.getRequest = (Map<String, Method>) servletContext.getAttribute(GET_REQUEST);
	    this.postRequest = (Map<String, Method>) servletContext.getAttribute(POST_REQUEST);
		this.config = config;
	}

	@Override
	public void service(ServletRequest request , ServletResponse response) throws ServletException, IOException {
	     
	}
	

	/**
	 */
	private void initRequestMapping() {
		String basePackagePath = "com.wch.controller";
		ApplicationContext context = new ApplicationContext();
		this.config.getServletContext().setAttribute(APPLICATIONCONTEXT, context);
	}
	
	
	
	
/*	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   System.out.println("doget方法");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   System.out.println("dopost方法");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
		System.out.println("protected的service方法");
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
		System.out.println("service 方法");
	}

	public void init(ServletConfig config) throws ServletException {
		System.out.println("调用servlet的开始");
		Enumeration<?> enumeration = config.getInitParameterNames();
		while (enumeration.hasMoreElements()) {
			Object object = (Object) enumeration.nextElement();
			System.out.println(object);
		}
		super.init(config);
		System.out.println();
	}
	
	public void destroy() {
		super.destroy();
	}
*/
}
