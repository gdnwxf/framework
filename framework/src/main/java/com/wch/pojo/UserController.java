package com.wch.pojo;

import com.wch.annotation.compent.Controller;
import com.wch.annotation.web.PathVariable;
import com.wch.annotation.web.RequestMapping;


@Controller
public class UserController {

	@RequestMapping(value="121",params="heheh" )
	public void updateString(@PathVariable String str){
		
	}
}
