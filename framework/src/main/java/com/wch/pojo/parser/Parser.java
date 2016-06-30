/**
 *
 */
package com.wch.pojo.parser;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wch
 *
 */
public abstract class Parser<T> {


	private  SqlBuilder sqlBuilder = new SqlBuilder();

	static Map<Annotation ,Object> annotationClazz  = new HashMap<Annotation, Object>();

	public static void register(Annotation annotation ,Parser parser ) {
		annotationClazz.put(annotation, parser);
	}


	public void parser() {
		parser();
	}

	public abstract String[] parser(Class<T> clazz);

	public <T> void toSql(Filter filter){

	}


}

