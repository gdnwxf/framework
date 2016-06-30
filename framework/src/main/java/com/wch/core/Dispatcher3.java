package com.wch.core;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Dispatcher3 extends HttpServlet {
	private static ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");
	// 从配置文件中读取前缀 后缀
	private String prefix = "";
	// 后缀
	private String suffix = "";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			ServletContext servletContext = config.getServletContext(); // tomcat环境的全局上下文
			// 获取配置 //解析xml中hibernate的配置 ibatis的配置 实例化bean的配置(单例 多例 等)
			// 读取spring.properties文件中的配置
			String path = servletContext.getContextPath();
			Properties properties = new Properties();
			properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("spring.properties"));
			ApplicationContext applicationContext = new ApplicationContext();
			applicationContext.setPrefix("/");
			applicationContext.setSuffix(".jsp");
			servletContext.setAttribute("applicationContext", applicationContext);

			// 实例化controller
			// 实例化pojo
			// 实例化services
			// 实例化resposity
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 菱形表示protected
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equals("GET")) {
			long lastModified = getLastModified(req);
			if (lastModified == -1L) {
				doGet(req, resp);
			} else {
				long ifModifiedSince;
				try {
					ifModifiedSince = req.getDateHeader("If-Modified-Since");
				} catch (IllegalArgumentException iae) {
					ifModifiedSince = -1L;
				}
				if (ifModifiedSince < lastModified / 1000L * 1000L) {
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

	private void maybeSetLastModified(HttpServletResponse resp, long lastModified) {
		if (resp.containsHeader("Last-Modified"))
			return;
		if (lastModified >= 0L) {
			resp.setDateHeader("Last-Modified", lastModified);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String middleReturn = "";
		// 返回中间处理要到达的页面
		if (middleReturn.indexOf("redirect:") >= 0) {
			middleReturn = middleReturn.substring(8);
			req.getRequestDispatcher(prefix + middleReturn + suffix).forward(req, resp);
		} else {
			resp.sendRedirect(prefix + middleReturn + suffix);
		}
	}

}
