/**
 * gdnwxf Glog 公共类的
 */
package com.wch.utils.log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Glog {

	/** The Constant GLOG_PROPERTIES. */
	private static final String GLOG_PROPERTIES = "Glog.properties";
	/** The Constant DEBUG. */
	private final static int DEBUG = 50;
	/** The Constant WARN. */
	private final static int WARN = 40;
	/** The Constant ERROR. */
	private final static int ERROR = 30;
	/** The Constant FATAL. */
	private final static int FATAL = 20;
	/** The Constant INFO. */
	private final static int INFO = 10;
	/** The level. */
	private static Integer LEVEL = INFO;
	/** The Constant pStream. */
	private final static PrintStream pStream = System.out;

	/** The properties. */
	private static Properties properties = new Properties();

	/** The Constant LEVELS. */
	private final static Map<String, Integer> LEVELS = new HashMap<String, Integer>();

	static {
		try {
			// 实例化值
			LEVELS.put("DEBUG", DEBUG);
			LEVELS.put("WARN", WARN);
			LEVELS.put("FATAL", FATAL);
			LEVELS.put("ERROR", ERROR);
			LEVELS.put("INFO", INFO);
			InputStream resourceAsStream = Glog.class.getClassLoader().getResourceAsStream(GLOG_PROPERTIES);
			properties.load(resourceAsStream);
			LEVEL = LEVELS.get(properties.getProperty("log").toString().toUpperCase());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("warn the  glog  config can't find ");
		}
	}

	/**
	 * Debug.
	 *
	 * @param object the object
	 */
	public static void debug(Object object) {
		if (LEVEL >= DEBUG)
			pStream.println(object);
	}

	/**
	 * Info.
	 *
	 * @param object the object
	 */
	public static void info(Object object) {
		if (LEVEL >= INFO)
			pStream.println(object);
	}

	/**
	 * Error.
	 *
	 * @param object the object
	 */
	public static void error(Object object) {
		if (LEVEL >= ERROR)
			pStream.println(object);
	}

	/**
	 * Fatal.
	 *
	 * @param object the object
	 */
	public static void fatal(Object object) {
		if (LEVEL >= FATAL)
			pStream.println(object);
	}

	/**
	 * Warn.
	 *
	 * @param object the object
	 */
	public static void warn(Object object) {
		if (LEVEL >= FATAL)
			pStream.println(object);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Glog.info("321");
	}

}
