package com.wch.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wch.core.RequestMap;
import com.wch.utils.file.FileUtils;
import com.wch.utils.xutils.MapUtilsX;
import com.wch.utils.xutils.bean.PersonBean;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

	static {
		System.out.println("----");
	}

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
	@RequestMapping(value = "/upload2"  )
	public String upload2( MultipartHttpServletRequest  request) throws IOException {

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
	
	@RequestMapping(value = "/users" )
	public String getUser(PersonBean personBean ) {
		System.out.println(MapUtilsX.transBean2Map(personBean));
		System.out.println("你好啊");
		return "/index";
	}

}
