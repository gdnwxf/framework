package com.wch.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;
import com.wch.core.RequestMap;
import com.wch.service.UserService;
import com.wch.utils.xutils.MapUtilsX;
import com.wch.utils.xutils.bean.PersonBean;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

	static {
		System.out.println("----");
	}

	@Autowired
	private UserService userService;
	
	/**
	 */
	public UserController() {
		System.out.println("--实例化--------");
	}

	@RequestMapping("id")
	@ResponseBody
	public String id() {
		return  "1" ;
	}

	@RequestMapping(value = "/user3"  )
	@ApiOperation(value = "根据用户名获取用户对象", httpMethod = "GET",    notes = "根据用户名获取用户对象")
	public String index() {
		System.out.println("你好啊2222");
		return "/index";
	}


	@RequestMapping(value = "/upload"  )
	public String upload( MultipartHttpServletRequest  request  , @RequestBody 
			RequestMap requestMap  ) throws IOException {
		
		System.out.println(request.getParameterMap());
		System.out.println(request.getFileMap());
		System.out.println(request.getFile("fileName"));
	   org.apache.commons.io.FileUtils.copyToFile(request.getFile("fileName").getInputStream(), new File("d:/2.txt"));
	   System.out.println(request);
		return "/index";
	}
	@RequestMapping(value = "/upload1"  )
	public String upload( HttpServletRequest  request   ) throws IOException {
		
//		System.out.println(request.getParameterMap());
//		System.out.println(request.getFileMap());
//		System.out.println(request.getFile("fileName"));
//		org.apache.commons.io.FileUtils.copyToFile(request.getFile("fileName").getInputStream(), new File("d:/2.txt"));
		System.out.println(request);
		return "/index";
	}
	@RequestMapping(value = "/upload2"  )
	public String upload2( MultipartHttpServletRequest  request) throws IOException {
		JsonObject jObject = new JsonObject();
		 
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		for (String string : keySet) {
			System.out.println(Arrays.toString(parameterMap.get(string)));
		}
		Map<String, MultipartFile> fileMap = request.getFileMap();
		for (String string : keySet) {
			
		}
		System.out.println(fileMap);
		return "/index";
	}
	
	@RequestMapping(value = "/users")
	@ResponseBody
	public String getUser(  PersonBean personBean ,    Integer id ,    String name ,
			HttpServletRequest  request ,    HttpServletResponse response ) {
		System.out.println(MapUtilsX.transBean2Map(personBean));
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " ---- " + cookie.getValue());
		}
		
		Cookie cookie = new Cookie("zansan", "123");
		response.addCookie(cookie);
		System.out.println("你好啊");
		return "/index";
	}
	/**
	 * 事务测试
	 * @return
	 */
	@RequestMapping(value = "/transaction" )
	public String transaction() {
		userService.xiugai();
//		userService.update();
		return "/index";
	}

}
