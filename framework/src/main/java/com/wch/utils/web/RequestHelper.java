/**************************************************************************
 * Copyright (c) 2006-2010 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称：融易通-数据交换中心
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.wch.utils.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wch.utils.date.DateUtils;

/**
 * The Class RequestHelper.
 */
public class RequestHelper {

	/** The request. */
	private HttpServletRequest request;

	/** The params. */
	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * Instantiates a new request helper.
	 * 
	 * @param request the request
	 */
	public RequestHelper(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Instantiates a new request helper.
	 */
	public RequestHelper() {

	}

	/**
	 * Gets the request params.
	 * 
	 * @return the request params
	 */
	public Map<String, Object> getRequestParams() {
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = paramNames.nextElement();
			if (!key.equals("sql") && !key.equals("sdu"))
				this.params.put(key, request.getParameter(key));
		}
		return params;
	}
 

	/**
	 * Prints the request.
	 * 
	 * @param request the request
	 */
	public static void printRequestParameters(HttpServletRequest request) {
		try {
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String urix = uri.substring(contextPath.length());
			System.out.println("*****requestS( " + DateUtils.formartDate(new Date()) + " )**************");
			System.out.println("* "+ request.getCharacterEncoding());
			System.out.println("* --------------访问方法-------------");
			System.out.println("* "+ request.getMethod() );
			System.out.println("* --------------访问地址-------------");
			System.out.println("* " + urix);
			System.out.println("* --------------内存地址-------------");
			System.out.println("* " + request.toString().replace("org.apache.catalina.connector.RequestFacade", ""));
			System.out.println("* ----------------类型---------------");
			System.out.println("* " + request.getContentType());
			InputStream is = request.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffers = new byte[1024];
			for (;;) {
				int len = is.read(buffers);
				if (len == -1) {
					break;
				}
				if (len > 0) {
					baos.write(buffers, 0, len);
				}
			}
			if (baos.size() > 0) {
				System.out.println("* 流数据" + baos.toString("UTF-8"));
			}
			Enumeration<String> paramName = request.getParameterNames();
			System.out.println("* ----------------参数---------------");
			while (paramName.hasMoreElements()) {
				String string = (String) paramName.nextElement();
				System.out.print("* " + string + " : ");
				System.out.println(request.getParameter(string));
			}
			System.out.println("******requestE( " + DateUtils.formartDate(new Date()) + " )**************");
		} catch (IOException e) {
			request.getRequestDispatcher("/index.jsp");
		}

	}

	/**
	 * Js forword.
	 * 
	 * @param response the response
	 * @param url the url
	 */
	public static void jsForword(HttpServletResponse response, String url) {
		try {
			response.setContentType("text/html;charset=uitf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.write("<script type=text/javascript > window.location.href =  '" + url + "'; </script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints the session.
	 * 
	 * @param session a HttpSession
	 */
	public static void printSession(HttpSession session) {
		Enumeration<String> enumeration = session.getAttributeNames();
		System.out.println("*****************session中的信息****************");
		while (enumeration.hasMoreElements()) {
			String object = enumeration.nextElement();
			System.out.print("* " + object);
			System.out.print(" : ");
			System.out.println(session.getAttribute(object));
		}
		System.out.println("*****************session中的信息****************");
	}

}
