package com.wch.velocity;

import java.io.File;

public class ClassLoaderTest {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("") +"vm");
	}
}
