package com.wch.utils.xutils;

import java.util.LinkedList;
import java.util.List;

import com.wch.utils.Assert;


 /**
 *  
 * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
 * @version $Id: ArraysHelp.java 33 2015-10-11 12:36:48Z wch $   
 * @since 2.0
 */
public class ArraysHelp {

	
	@SuppressWarnings("unchecked")
	public static <T> T[]  removeSame(T[] t1 , T[] t2) {
		List<T> list = new LinkedList<T>();
		for (T t : t1) {
			list.add(t) ;
		}
		
		for (T t : t2) {
			if(!list.contains(t)) {
				list.add(t) ;
			}
		}
		return (T[]) list.toArray();
	}
	
	
	public Object[] arrays (Object object )
	{
		Assert.notNull(object);
		Class<?> clazz = object.getClass();
		if(clazz.isArray()) {
		 
		}
		return null;
	}
}
