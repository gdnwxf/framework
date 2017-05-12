package com.wch.velocity;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ClassLoaderTest {

	public static void main(String[] args) throws URISyntaxException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("vm");
		File file = new File(resource.toURI());
		System.out.println(file.getAbsolutePath());
	}
}
