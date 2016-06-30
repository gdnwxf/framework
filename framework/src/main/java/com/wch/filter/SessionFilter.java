package com.wch.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wch.utils.BeanUtils;

// TODO: Auto-generated Javadoc
/**
 * 处理字符的拦截器.
 *
 * @author GDNWXF
 * @date 2014年7月6日  下午5:07:34
 */
public class SessionFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		System.out.println(request2.getRequestURI());
		
		Cookie[] cookies = request2.getCookies();
		if(cookies !=null) {
			int a = cookies.length;
			if(a>0) {
				Cookie x = cookies[0];
				BeanUtils.print_Min(x);
			}
		}else {
			
			 Cookie arg0 = new Cookie("userid", "3213213");
			response2.addCookie(arg0);;
			
		}
		System.out.println(cookies);
		
		request2.setCharacterEncoding("utf-8");
		response2.setCharacterEncoding("utf-8");
		chain.doFilter(request2, response2);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
	   
	}

}
