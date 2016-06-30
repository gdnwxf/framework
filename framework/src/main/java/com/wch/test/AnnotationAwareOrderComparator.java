/*package com.wch.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;

public class AnnotationAwareOrderComparator extends OrderComparator {
	public static final AnnotationAwareOrderComparator INSTANCE = new AnnotationAwareOrderComparator();

	public AnnotationAwareOrderComparator() {
	}

	protected int getOrder(Object obj) {
		if ((obj instanceof Ordered)) {
			return ((Ordered) obj).getOrder();
		}
		if (obj != null) {
			Class<?> clazz = (obj instanceof Class) ? (Class) obj : obj.getClass();
			Order order = (Order) AnnotationUtils.findAnnotation(clazz, Order.class);
			if (order != null) {
				return order.value();
			}
		}
		return Integer.MAX_VALUE;
	}

	public static void sort(List<?> list) {
		if (list.size() > 1) {
			Collections.sort(list, INSTANCE);
		}
	}

	public static void sort(Object[] array) {
		if (array.length > 1) {
			Arrays.sort(array, INSTANCE);
		}
	}
}
*/
