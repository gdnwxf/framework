
 
package com.wch.utils.xutils;

import java.util.LinkedList;
import java.util.List;


 
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
}
