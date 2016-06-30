package org.springframework.core.utils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CoreDispatcher
 */
public class CoreDispatcher extends FrameworkDispatcher {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 实例化的处理
	 */
	public void processInit(ServletConfig config) {
		ServletContext servletContext = config.getServletContext();
		System.out.println(servletContext.getInitParameter("contextClass"));
	}


	public void processDoGet(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	public void processDoPost(HttpServletRequest req, HttpServletResponse resp) {
		
	}



}
