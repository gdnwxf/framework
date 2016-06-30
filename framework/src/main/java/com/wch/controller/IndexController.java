package com.wch.controller;

import com.wch.annotation.compent.Controller;
import com.wch.annotation.web.RequestMapping;

  
/**
 * The Class IndexController.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	 
	/**
	 * Index.
	 *
	 * @return the string
	 */
	@RequestMapping("/index2")
	public String index() {
		System.out.println("-------------------- ");
		return "index";
	}
	

}
