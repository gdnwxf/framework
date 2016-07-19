package com.wch.btrace;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

public class HelloBtrace {
	
	private Logger logger = LoggerFactory.getLogger(HelloBtrace.class);
	
	public String doWork(String str,Integer str2) {
		logger.debug("logger {}", "test");
		if(str2 == null)
		throw new NullPointerException("你好啊");
		return str + "wch" + str2;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		HelloBtrace HelloBtrace = new HelloBtrace();
		System.out.println(HelloBtrace.logger +"  HelloBtrace.logger ");
		while (true) {
		TimeUnit.SECONDS.sleep(5);
			try {
				System.out.println(HelloBtrace.doWork("321" ,  null));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

}
