package com.wch.velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.directive.Macro;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityUtils {
	
	public static void singleInit(Properties p) {
		Properties ve = new Properties();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, Thread.currentThread().getContextClassLoader().getResource("").getPath());
		if(p != null) {
			Set<Object> keySet = p.keySet();
			for (Object object : keySet) {
				ve.setProperty((String) object, p.getProperty((String) object));
			}
		}
		Velocity.init(ve);
	}
	
	public static  void singleInit() {
		singleInit( (Properties) null) ;
	}
	
	public static  void singleInit(String baseDir) {
		Properties properties = null;
		if(baseDir != null) {
		    properties = new Properties();
			properties.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, Thread.currentThread().getContextClassLoader().getResource("").getPath()+ baseDir);
		}
		singleInit(properties) ;
	}
	
	
	public static void  process(Map<String, Object> ctx, Writer writer ,String templateName) throws IOException {
		 process(ctx, writer, templateName, null );
	}
	public static void  process(Map<String, Object> ctx, Writer writer ,String templateName,List<Macro> list) throws IOException {
		 try {
			 VelocityContext vContext = null;
			 if(ctx == null) {
				 vContext = new VelocityContext();
			 }else {
				 vContext = new VelocityContext(ctx);
			}
			Template t = Velocity.getTemplate(templateName);
			t.merge(vContext, writer, list);
		} finally {
			writer.flush();
			writer.close();
		}
	}
	public static void  process(Map<String, Object> ctx, String fileName ,String templateName) throws IOException {
		File file = new File(fileName);
		if(!file.exists()) {
			file.getParentFile().mkdirs();
		}
		process(ctx, new FileWriter(fileName), templateName ,null );
	}
}
