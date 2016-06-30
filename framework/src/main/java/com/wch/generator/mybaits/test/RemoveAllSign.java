package com.wch.generator.mybaits.test;

import java.io.File;
import java.io.IOException;
import java.security.spec.DSAGenParameterSpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wch.utils.file.FileHandle;
import com.wch.utils.file.FileUtils;
import com.wch.utils.file.FileUtilsX;

public class RemoveAllSign {
   
	public static void main(String[] args) throws IOException {
		String filePathString = "D:\\WorkSpace\\2015-4-2_project\\framework\\src";
	    FileUtilsX.cycleFilePath(new File(filePathString), new FileHandle() {
	    	/* (non-Javadoc)
	    	 * @see com.wch.utils.file.FileHandle#handleFile(java.io.File)
	    	 */
	    	@Override
	    	public void handleFile(File file) {
	    	    try {
					String readFileToStr = FileUtilsX.readFileToStr(file);
					String regExp = "\\/\\*\\*.*?区域大通关.*?\\*\\/";
					Matcher matcher = Pattern.compile(regExp, Pattern.DOTALL).matcher(readFileToStr);
					String regExp2 = "\\/\\*\\*.*?@zjport.gov.cn.*?\\*\\/";
					Matcher matcher2 = Pattern.compile(regExp2, Pattern.DOTALL).matcher(readFileToStr);
					if(matcher.find()) {
						  readFileToStr = readFileToStr.replace(matcher.group(), "");
					}
					if(matcher2.find()) {
						  readFileToStr = readFileToStr.replace(matcher2.group(), "");
					}
					FileUtils.writeToFile(readFileToStr, file, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		});
	}
}
