
 
package com.wch.utils.xutils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import sun.misc.BASE64Decoder;


 
public class Decode {
  
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		System.out.println(new String( decoder.decodeBuffer("66qv3jBbt6n2ODZRTS5YTnuhc3t18SyuClnpmz4C:uGowLBo4eiwDWk7BbHX0BIVjkw4="),"gb2312" ));
	    URLDecoder urlDecoder = new URLDecoder() ;
	    String decode = urlDecoder.decode("http://down.icoolxue.com/115991196?attname=Nutch%E7%9B%B8%E5%85%B3%E6%A1%86%E6%9E%B6%E8%A7%86%E9%A2%91%E6%95%99%E7%A8%8B%EF%BC%881%EF%BC%89.mp4&e=1444586163&token=66qv3jBbt6n2ODZRTS5YTnuhc3t18SyuClnpmz4C:uGowLBo4eiwDWk7BbHX0BIVjkw4=");
	    System.out.println(decode);
	    
	    String s = "Nutch相关框架视频教程（2）.mp4";
	    String encoder = URLEncoder.encode(s);
	    System.out.println("http://down.icoolxue.com/115991196?attname="+encoder+"&e=1444586163&token=66qv3jBbt6n2ODZRTS5YTnuhc3t18SyuClnpmz4C:uGowLBo4eiwDWk7BbHX0BIVjkw4=");
	    
	    
	    
		
	}
}
