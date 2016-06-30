/**
 *
 */
package com.wch.pojo.parser;

import com.wch.pojo.UserInfo;
import com.wch.pojo.parser.Parser;

import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;

/**
 * @author wch
 *
 */
public class IdParser {

	/**
	 *
	 */
	public static  <T> String [] parser(Class<T>  clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			 

		}
		return null;
	}

	public static void main(String[] args) {
		Table annotation = UserInfo.class.getAnnotation(Table.class);
		Id annotation1 = UserInfo.class.getAnnotation(Id.class);
		System.out.println(annotation1);
		System.out.println(annotation);
	}


}
