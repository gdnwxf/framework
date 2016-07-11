package com.wch.btrace;

import java.util.concurrent.TimeUnit;

public class HelloBtrace {
	
	public String doWork(String str,Integer str2) {
		return str + "wch" + str2;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		HelloBtrace HelloBtrace = new HelloBtrace();
		while (true) {
		TimeUnit.SECONDS.sleep(5);
		System.out.println(HelloBtrace.doWork("321" ,  8888));
		}
	}

}
