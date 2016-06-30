package com.wch.core;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wch.utils.file.FileHandle;
import com.wch.utils.file.FileUtilsX;
import com.wch.utils.string.StringUtils;

import javax.persistence.Table;

/**
 * The Class ClassUtils.
 */
public class ClassUtils {

	/**
	 * Gets the class path.
	 *
	 * @return the class path
	 */
	public static URL getClassPath() {
		return ClassUtils.class.getClassLoader().getResource("");
	}

	/**
	 * Gets the class name.
	 *
	 * @param prefixPath the prefix path
	 * @return the class name
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the URI syntax exception
	 */
	public static List<String> getClassName(String prefixPath) throws IOException, URISyntaxException {
		final List<String> classNames = new ArrayList<String>(2);
		prefixPath = prefixPath.replace('.', '/');
		FileUtilsX.cycleFilePath(new File(getClassPath().toURI().resolve(prefixPath)), new FileHandle() {
			/**
			 * @param file
			 * @see com.wch.utils.file.FileHandle#handleFile(java.io.File)
			 */
			public void handleFile(File file) {
				String name = file.getName();
				if (file.isFile() && name.endsWith(".class")) {
					try {
						String classNamePath = file.toURI().getPath().substring(getClassPath().toURI().getPath().length()).replace('/', '.');
						classNames.add(StringUtils.subString(classNamePath, classNamePath.lastIndexOf('.')));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return classNames;
	}

	/**
	 * Gets the classes.
	 *
	 * @param prefixPath the prefix path
	 * @return the classes
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the URI syntax exception
	 */
	public static List<Class<?>> getClasses(String prefixPath) throws IOException, URISyntaxException {
		final List<Class<?>> classNames = new ArrayList<Class<?>>(2);
		prefixPath = prefixPath.replace('.', '/');
		FileUtilsX.cycleFilePath(new File(getClassPath().toURI().resolve(prefixPath)), new FileHandle() {
			/**
			 * @param file
			 * @see com.wch.utils.file.FileHandle#handleFile(java.io.File)
			 */
			public void handleFile(File file) {
				String name = file.getName();
				if (file.isFile() && name.endsWith(".class")) {
					try {
						String classNamePath = file.toURI().getPath().substring(getClassPath().toURI().getPath().length()).replace('/', '.');
						classNames.add(Class.forName(StringUtils.subString(classNamePath, classNamePath.lastIndexOf('.'))));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return classNames;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the URI syntax exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
		List<Class<?>> className = getClasses("cn.org.rapid_framework.generator.util.paranamer");
		for (Class<?> class1 : className) {
			System.out.println(class1);
		}

	}

	/**
	 * Class path to file path.
	 *
	 * @param classPath the class path
	 * @return the string
	 */
	@SuppressWarnings("static-access")
	public static String classPathToFilePath(String classPath) {
		URL systemResource = ClassLoader.getSystemClassLoader().getSystemResource("");
		return systemResource.toString()  + classPath.replace(".", "/");

	}

	/**
	 * Test name.
	 *
	 * @throws Exception the exception
	 */
	@org.junit.Test
	public void testName() throws Exception {
		System.out.println(classPathToFilePath("com.wch.springframework"));

	}

	public static <T> Table getClassAnnotation(Class<T> clazz , Annotation annotation) {
		Table table = clazz.getAnnotation(Table.class);
		return table;
	}
}
