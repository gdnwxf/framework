package com.wch.test;

import java.util.regex.Pattern;

public class RegTest {

	
	public static void main(String[] args) {
		System.out.println(Pattern.compile("^\\d+$").matcher("321321321321").matches());
	}
}
