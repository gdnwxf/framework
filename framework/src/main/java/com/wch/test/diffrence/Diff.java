package com.wch.test.diffrence;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.wch.utils.file.FileUtils;
import com.wch.utils.string.StringFilter;

public class Diff {

	public static void main(String[] args) throws IOException {

		final Set<String> dataSet = new HashSet<String>(); 
 		FileUtils.readFileToString(new File("D:\\mygithub\\github\\project\\framework\\framework\\src\\main\\java\\test"), new StringFilter() {
			
			@Override
			public boolean accept(StringBuilder stringBuilder, String temp) {
				// TODO Auto-generated method stub
				dataSet.add(StringUtils.trim(temp));
				return true;
			}
		});
		
 		
 		final Set<String> dataSet2 = new HashSet<String>(); 
 		FileUtils.readFileToString(new File("D:\\mygithub\\github\\project\\framework\\framework\\src\\main\\java\\test1"), new StringFilter() {
			
			@Override
			public boolean accept(StringBuilder stringBuilder, String temp) {
				// TODO Auto-generated method stub
				dataSet2.add(StringUtils.trim(temp));
				return true;
			}
		});
		
 		System.out.println(dataSet);
 		System.out.println(dataSet2);
 		for (String string : dataSet2) {
			if(!dataSet.contains(string)) {
				System.out.println(string);
			}
		}
	}
}
