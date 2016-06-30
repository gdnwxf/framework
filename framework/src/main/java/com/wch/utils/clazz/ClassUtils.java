package com.wch.utils.clazz;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class ClassUtils.
 * 
 * @version $Id: ClassUtils.java 32 2015-10-11 12:34:21Z wch $
 * @since 2.0
 */
public class ClassUtils {

	/**
	 * Scan class.
	 * 
	 * @param pkgName the pkg name
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the URI syntax exception
	 */
	public static List<String> scanClass(String pkgName) throws IOException, URISyntaxException {
		List<String> classNames = new ArrayList<String>();
		scanFile(getClassPathFile(pkgName), getClassPath("").getFile().length() - 1, classNames);
		return classNames;
	}

	/**
	 * Scan class.
	 * 
	 * @param pkgName the pkg name
	 * @param classHandler the class handler
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the URI syntax exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static void scanClass(String pkgName, ClassHandler classHandler) throws IOException, URISyntaxException, ClassNotFoundException {
		scanFile(getClassPathFile(pkgName), getClassPath("").getFile().length() - 1, classHandler);
	}

	// 加载所有的class文件
	/**
	 * Scan file.
	 * 
	 * @param file the file
	 * @param classPathLen the class path len
	 * @param classNames the class names
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void scanFile(File file, int classPathLen, List<String> classNames) throws IOException {
		File[] listFiles = file.listFiles();
		if (file.isFile()) {
			String className = file.getAbsolutePath().substring(classPathLen).replace("\\", ".");
			classNames.add(className.substring(0, className.lastIndexOf('.')));
			return;
		}
		if (listFiles == null) {
			return;
		}
		for (File file2 : listFiles) {
			scanFile(file2, classPathLen, classNames);
		}

	}

	// 加载所有的class文件
	/**
	 * Scan file.
	 * 
	 * @param file the file
	 * @param classPathLen the class path len
	 * @param classHandler the class handler
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	private static void scanFile(File file, int classPathLen, ClassHandler classHandler) throws IOException, ClassNotFoundException {
		File[] listFiles = file.listFiles();
		if (file.isFile()) {
			String className = file.getAbsolutePath().substring(classPathLen).replace("\\", ".");
			className = className.substring(0, className.lastIndexOf('.'));
			classHandler.headleClass(Class.forName(className));
			return;
		}
		if (listFiles == null) {
			return;
		}
		for (File file2 : listFiles) {
			scanFile(file2, classPathLen, classHandler);
		}

	}

	/**
	 * 获取classpath的文件.
	 * 
	 * @param packageName the package name
	 * @return the class path file
	 * @throws URISyntaxException the URI syntax exception
	 */
	private static File getClassPathFile(String packageName) throws URISyntaxException {
		return new File(getClassPath(packageName).getFile());
	}

	/**
	 * 获取classpath的url.
	 * 
	 * @param packageName the package name
	 * @return the class path
	 * @throws URISyntaxException the URI syntax exception
	 */
	private static URL getClassPath(String packageName) throws URISyntaxException {
		String prefixPath = "";
		if (packageName != null) {
			prefixPath = packageName.replace(".", "/"); // 统一资源定位的 不是file.s
		}
		URL systemResource = ClassLoader.getSystemResource(prefixPath);
		return systemResource;
	}

	/**
	 * Load class.
	 * 
	 * @param className the class name
	 * @return the class
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static Class<?> loadClass(String className) throws ClassNotFoundException {
		return Class.forName(className);
	}

	/**
	 * Gets the inner methods.
	 * 
	 * @param clazz the clazz
	 * @return the inner methods
	 */
	public static Method[] getAllMethods(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		return methods;
	}

	/**
	 * Gets the public methods.
	 * 
	 * @param clazz the clazz
	 * @return the public methods
	 */
	public static Method[] getSelfMethods(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		return methods;
	}

	/**
	 * 判断两个方发是否相等.
	 * 
	 * @param bridgeMethod the bridge method
	 * @param bridgedMethod the bridged method
	 * @return true, if is visibility bridge method pair
	 */
	public static boolean isVisibilityBridgeMethodPair(Method bridgeMethod, Method bridgedMethod) {
		if (bridgeMethod == bridgedMethod) {
			return true;
		}
		return Arrays.equals(bridgeMethod.getParameterTypes(), bridgedMethod.getParameterTypes()) && bridgeMethod.getReturnType().equals(bridgedMethod.getReturnType());
	}

	/**
	 * Checks if is im serializable.
	 *
	 * @param clazz the clazz
	 * @return true, if is im serializable
	 */
	public static boolean isImSerializable(Class<?> clazz) {
		// 判断其是否实现了Serializable
		Class<?>[] interfaces = clazz.getInterfaces();
		if (interfaces != null) {
			for (int i = 0; i < interfaces.length; i++) {
				if (interfaces[i].equals(Serializable.class)) {
					return true;
				}
			}

		}
		return false;
	}
	
	/**
	 * Gets the all fields.
	 *
	 * @param clazz the clazz
	 * @return the all fields
	 */
	public static  Field[] getAllFields(Class<?> clazz) {
		return clazz.getFields();
	}
	
	/**
	 * Gets the public element.
	 *
	 * @param clazz the clazz
	 * @return the public element
	 */
	public static  Field[] getPublicField(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}
  
	
	public static <T> T instanceObj(Class<T> clazz,int length) {
		if(clazz.isArray())
		{
			return (T) Array.newInstance(clazz, length);
		}
		
		return null;
	} 
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		scanClass("com.wch.x", new ClassHandler() {
			public void headleClass(Class<?> clazz) {
				System.out.println(clazz.getSimpleName());
			}
		});
	}
}
