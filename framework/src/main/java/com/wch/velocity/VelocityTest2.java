package com.wch.velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityTest2 {

	
	public static void main(String[] args) throws IOException {
		
		  
		 
		//单例模式
		 Properties p = new Properties();
		 p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		 p.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, Thread.currentThread().getContextClassLoader().getResource("").getPath());
//		 p.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "D:/mygithub/github/project/framework/framework/target/classes/vm");
		 Velocity.init(p);
		 
		 
		 Template t = Velocity.getTemplate("vm/Hellovelocity.vm");
		 Map<String	, Object> ctx = new HashMap<String, Object>();
		 
		 ctx.put("name", "velocity");
		 ctx.put("date", (new Date()).toString());
		 
		 List temp = new ArrayList();
		 temp.add("1");
		 temp.add("2");
		 ctx.put("list", temp);
		 
		 StringWriter sw = new StringWriter();
		 
//		 t.merge(ctx, sw);
		 VelocityUtils.singleInit();
		 VelocityUtils.process(ctx, "d:\\a\\hello.txt", "vm/Hellovelocity.vm");
		 System.out.println(sw.toString());
	}
}
