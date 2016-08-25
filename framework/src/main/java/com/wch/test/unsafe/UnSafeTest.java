package com.wch.test.unsafe;


import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.*;

public class UnSafeTest {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = Unsafe.class.getDeclaredField("theUnsafe");
		field.setAccessible(true);
		Unsafe unsafe = (sun.misc.Unsafe) field.get(null);
		Object paramObject = new Object();
//		unsafe.compareAndSwapInt(paramObject , paramLong, paramInt1, paramInt2);
		System.out.println(unsafe);
		UnSafeTest unSafeTest = new UnSafeTest();
		AtomicInteger atomicInteger = new AtomicInteger(2);
		atomicInteger.compareAndSet(2, 4);
		System.out.println(atomicInteger.get());
	}
}
