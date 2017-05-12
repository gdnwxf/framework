package framwork.mybatis;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.wch.utils.file.FileUtils;

public class CleanComment {

	
	public static void main(String[] args) {
		FileUtils.scanFile(new File("D:\\mygithub\\github\\project\\framework\\framework\\src\\main\\java"), new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				 if(pathname .isFile()) {
					 try {
						String readFileToString = FileUtils.readFileToString(pathname);
					 	Pattern pattern = Pattern.compile("(/\\*\\*.*?\\*/)", Pattern.DOTALL);
						Matcher matches = pattern.matcher(readFileToString);
						while (matches.find()) {
							String group = matches.group();
							Pattern compile = Pattern.compile ("/\\*\\*.*?(浙江电子口岸|@zjport).*?\\*/" ,pattern.DOTALL);
							Matcher matcher = compile.matcher(group);
							if(matcher.find()) {
								System.out.println(pathname);
								readFileToString = readFileToString.replace(matcher.group(), "");
							}
						}
						FileUtils.writeToFile(readFileToString, pathname, true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 
				 }
				return true;
			}
		});
	}
	
	
	@Test
	public void testReg() throws Exception {
		
		String readFileToString = FileUtils.readFileToString(new File("D:\\mygithub\\github\\project\\framework\\framework\\src\\main\\java\\test"));
		Pattern pattern = Pattern.compile("/\\*\\*.*?(浙江电子口岸|@zjport).*?\\*/", Pattern.DOTALL);
		Matcher matches = pattern.matcher(readFileToString);
		if(matches.find()) {
			String group = matches.group();
			System.out.println(group);
			System.out.println("---------------------------");
		}
		
	}


	@Test
	public void testReg2() throws Exception {
		
		String readFileToString = FileUtils.readFileToString(new File("D:\\mygithub\\github\\project\\framework\\framework\\src\\main\\java\\test2"));
		Pattern pattern = Pattern.compile("(/\\*\\*.*?\\*/)", Pattern.DOTALL);
		Matcher matches = pattern.matcher(readFileToString);
		while (matches.find()) {
			String group = matches.group();
			Pattern compile = Pattern.compile ("/\\*\\*.*?(浙江电子口岸|@zjport).*?\\*/" ,pattern.DOTALL);
			Matcher matcher = compile.matcher(group);
			if(matcher.find()) {
				System.out.println(matcher.group());
			}
		}
		
	}


	@Test
	public void testReg3() throws Exception {
		
		String readFileToString = FileUtils.readFileToString(new File("D:\\mygithub\\github\\project\\framework\\framework\\src\\main\\java\\test3"));
		Pattern pattern = Pattern.compile("(/\\*\\*).*?\\*/");
		Matcher matches = pattern.matcher(readFileToString);
		while (matches.find()) {
			String group = matches.group();
			System.out.println(group);
			System.out.println("---------------------------");
		}
		
	}
}
