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

import com.wch.utils.date.DateUtils;

public class RequestUtils {
	
	private HttpServletRequest request;
	private Map<String, Object> params = new HashMap<String, Object>();

	public RequestUtils(HttpServletRequest request) {
		this.request = request;
	}

	public RequestUtils() {
		
	}

	public Map<String, Object> getRequestParams() {
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = paramNames.nextElement();
			if(!key.equals("sql")&&!key.equals("sdu"))
			this.params.put(key, request.getParameter(key));
		}
		return params;
	}
	
	public static void printRequest(HttpServletRequest request) {
		try {
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String urix = uri.substring(contextPath.length());
			System.out.println("++++++++++++++++( "+DateUtils.formartDate(new Date())+" )++++++++++++++++");
			System.out.println("|--------------访问地址-------------");
			System.out.println("| "+urix);
			System.out.println("+--------------内存地址-------------");
			System.out.println("| "+request.toString().replace("org.apache.catalina.connector.RequestFacade", ""));
			System.out.println("|---------------类型---------------");
			System.out.println("+ "+request.getContentType());
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
			if (baos.size()>0) {
				System.out.println("+流数据" + baos.toString("UTF-8"));
			}
			Enumeration<String> paramName = request.getParameterNames();
			System.out.println("+----------------参数---------------");
			while (paramName.hasMoreElements()) {
				String string = (String) paramName.nextElement();
				System.out.print("|" +string + " : ");
				System.out.println(request.getParameter(string));
			}
			System.out.println("++++++++++++++++( "+DateUtils.formartDate(new Date())+" )++++++++++++++++");
		} catch (IOException e) {
			 request.getRequestDispatcher("/index.jsp");
		}

	}
	
	public static void jsForword(HttpServletResponse response,String url) {
		try {
			response.setContentType("text/html;charset=uitf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.write("<script type=text/javascript > window.location.href =  '"+url+"'; </script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
