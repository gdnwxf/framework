package com.wch.core;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher4 extends HttpServlet {
	
	private static ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");

	private static final String APPLICATIONCONTEXT = "APPLICATIONCONTEXT";
	
	/**    */
	private static final long serialVersionUID = 1L;

	/**
	 * @return
	 * @see HttpServlet#HttpServlet()
	 */
	public Dispatcher4() {
		initRequestMapping();
	}

	/**
	 */
	private void initRequestMapping() {
		String basePackagePath = "com.wch.controller";
		ApplicationContext context = new ApplicationContext();
		this.getServletContext().setAttribute(APPLICATIONCONTEXT, context);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	public void service(ServletRequest request2, ServletResponse response2) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) request2;
		HttpServletResponse response = (HttpServletResponse) response2;
		// 找到方法
		// 给方法组装参数
		// 执行方法
		// 处理方法的返回值
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equals("GET")) {
			long lastModified = getLastModified(req);
			if (lastModified == -1L) {
				doGet(req, resp);
			} else {
				long ifModifiedSince = req.getDateHeader("If-Modified-Since");
				if (ifModifiedSince < lastModified) {
					maybeSetLastModified(resp, lastModified);
					doGet(req, resp);
				} else {
					resp.setStatus(304);
				}
			}
		} else if (method.equals("HEAD")) {
			long lastModified = getLastModified(req);
			maybeSetLastModified(resp, lastModified);
			doHead(req, resp);
		} else if (method.equals("POST")) {
			doPost(req, resp);
		} else if (method.equals("PUT")) {
			doPut(req, resp);
		} else if (method.equals("DELETE")) {
			doDelete(req, resp);
		} else if (method.equals("OPTIONS")) {
			doOptions(req, resp);
		} else if (method.equals("TRACE")) {
			doTrace(req, resp);
		} else {
			String errMsg = lStrings.getString("http.method_not_implemented");
			Object[] errArgs = new Object[1];
			errArgs[0] = method;
			errMsg = MessageFormat.format(errMsg, errArgs);

			resp.sendError(501, errMsg);
		}
	}
	
	
	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	
	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	private void maybeSetLastModified(HttpServletResponse resp, long lastModified) {
		if (resp.containsHeader("Last-Modified")) {
			return;
		}
		if (lastModified >= 0L) {
			resp.setDateHeader("Last-Modified", lastModified);
		}
	}
	
	 

}
