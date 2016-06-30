package com.wch.test;

import java.util.ArrayList;
import java.util.List;

import com.wch.utils.string.StringUtils;

public class ObjectTest {

	 public static void main(String[] args) {
		
		List<String>  dataList = new ArrayList<String>(5);
		List<String>  dataList2 = new ArrayList<String>(5);
		
		dataList.add("321321");
		
		
		dataList2.addAll(dataList);
		dataList.add("sssss");
		
		System.out.println( StringUtils.join(dataList2 , ","));
		 
	}
}
