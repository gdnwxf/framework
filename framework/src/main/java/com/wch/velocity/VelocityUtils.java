package com.wch.velocity;

import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityUtils {
	
	static {
		Properties ve = new Properties();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		Velocity.init(ve);
	}
	
	public static void  Single(VelocityContext ctx, ) {
		Velocity.
	}

}
