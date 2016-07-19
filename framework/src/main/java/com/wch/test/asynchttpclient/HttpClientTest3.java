package com.wch.test.asynchttpclient;

import java.io.File;
import java.io.IOException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;
import cz.msebera.android.httpclient.entity.mime.content.StringBody;
import cz.msebera.android.httpclient.impl.client.BasicCookieStore;
import cz.msebera.android.httpclient.impl.client.BasicCredentialsProvider;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
import cz.msebera.android.httpclient.util.EntityUtils;
 


public class HttpClientTest3 {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope("httpbin.org", 80),
				new UsernamePasswordCredentials("user", "passwd"));
		CookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie var1 = new BasicClientCookie("111a0b4a781ef7689f688ebb4a46f102", "e5eb7339aed9d49f662b2fec7c8513cca443632758368e6057bf6d3a40d2123932d09ead0bf60ade8b797078f8d695e3b7dc6dbd605529e3c60e5199d858af9ad799d106c15d63ebad15d09b19ec0f74");
		var1.setDomain("10.1.53.148");
		cookieStore.addCookie(var1);
	  CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider)
			  .setDefaultCookieStore(cookieStore)
              .build();
	 
	    try {
	    	
	    	
	    	
	        HttpPost httppost = new HttpPost("http://10.1.53.148:8080/serviceCenter/api/" +
	                "microStyle/saveInfoImageList");
	
//	        String cookie = ""  ;
//			httppost.addHeader(new BasicHeader("Cookie",cookie ));
	        FileBody bin = new FileBody(new File("d:/1.txt"));
	        StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
	
	        HttpEntity reqEntity = MultipartEntityBuilder.create()
	                .addPart("bin", bin)
	                .addPart("comment", comment)
	                .addPart("sessionId", new StringBody("e09c6aa399b44a51b7d4b6d0c44dc3a9", ContentType.TEXT_PLAIN))
	                .addPart("version",  new StringBody("RMB_1.0", ContentType.TEXT_PLAIN))
	                .addPart("appKey",  new StringBody("54b1304dd75c4d89a97a4241563ab4bc", ContentType.TEXT_PLAIN))
	                .addPart("timestamp",  new StringBody(""+System.currentTimeMillis(), ContentType.TEXT_PLAIN))
	                .addPart("sign", new StringBody( APIUtils.getSign(""+System.currentTimeMillis()), ContentType.TEXT_PLAIN))
	                .build();
	
	
	        httppost.setEntity(reqEntity);
	    
	        System.out.println("executing request " + httppost.getRequestLine());
	        
	        CloseableHttpResponse response = httpclient.execute(httppost);
	        try {
	            System.out.println("----------------------------------------");
	            System.out.println(response.getStatusLine());
	            HttpEntity resEntity = response.getEntity();
	            if (resEntity != null) {
	                System.out.println("Response content length: " + resEntity.getContentLength());
	                System.out.println(resEntity.toString());
	            }
	            EntityUtils.consume(resEntity);
	        } finally {
	            response.close();
	        }
	    } finally {
	        httpclient.close();
	    }

	}
}

