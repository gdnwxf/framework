package com.wch.test.velocitytest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wch.velocity.VelocityUtils;

public class VelocityTest {  
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		VelocityUtils.singleInit("vm");
		List<String>  dataList = new ArrayList<String>(5);
		
		dataList.add("123");
		dataList.add("wch");
		dataList.add("gdnwxf");
		Map<String ,Object> data = new HashMap<String ,Object>();
		data.put("names", dataList);
		data.put("vals", new ArrayList<String>());
		data.put("valsa",  123);
		VelocityUtils.print(data,"test.vm");
	}

}
