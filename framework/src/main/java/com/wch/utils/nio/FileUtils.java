package com.wch.utils.nio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;

import org.junit.Test;

import com.wch.utils.Assert;


 /**
 *  用nio 实现的文件帮助类
 * @author  <a href="mailto:gdnwxf@qq.com">gdnwxf</a>
 * @2015年8月16日 下午10:27:31
 */
public class FileUtils {

	
	public String readFileToStr(File file) throws IOException {
		FileReader reader = null;
		try {
			Assert.notNull(file);
			reader = new FileReader(file);
 			char[] array = new char[1024];
 			StringBuilder stringBuilder = new StringBuilder();
			CharBuffer cbuf = CharBuffer.wrap(array);
			while ( reader.read(cbuf) != -1 ) {
				stringBuilder.append(cbuf.array());
			}
			return  stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(reader != null) {
				reader.close();
			}
		}
		return null;
	}
	

	public void writeToFile(String str,File file ,boolean  forceWrite ,boolean append) throws IOException {
		FileWriter writer = null;
		try {
			Assert.notNull(file);
			writer = new FileWriter(file ,append);
			if(file.exists()) {
				if(forceWrite) {
					writer.write(str);
				}else {
					throw new IOException(" file is already exists");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(writer != null) {
				writer.close();
			}
		}
	}
	
	@Test
	public  void  test01() {
		try {
			System.out.println(readFileToStr(new File("D:\\我的电脑安装软件.txt")));
			System.out.println("1111");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
