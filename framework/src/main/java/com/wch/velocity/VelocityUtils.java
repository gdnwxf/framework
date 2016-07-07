package com.wch.velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.directive.Macro;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityUtils {
	
	static {
		Properties ve = new Properties();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		Velocity.init(ve);
	}
	
	public static void  Single(VelocityContext ctx, Writer writer ,String templateName) {
		 Single(ctx, writer, templateName, null );
	}
	public static void  Single(VelocityContext ctx, Writer writer ,String templateName,List<Macro> list) {
		 Template t = Velocity.getTemplate(templateName);
		 t.merge(ctx, writer, list);
	}
	public static void  Single(VelocityContext ctx, String fileName ,String templateName) throws IOException {
		 Single(ctx, new FileWriter(fileName), null );
	}
}
