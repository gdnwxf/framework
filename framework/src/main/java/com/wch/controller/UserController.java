package com.wch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "/users" )
	public String getUser(PersonBean personBean ) {
		System.out.println(MapUtilsX.transBean2Map(personBean));
		System.out.println("你好啊");
		return "/index";
	}

}
