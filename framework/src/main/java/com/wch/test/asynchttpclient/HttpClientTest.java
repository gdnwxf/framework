package com.wch.test.asynchttpclient;

import java.io.File;
import java.io.IOException;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class HttpClientTest {

	
	public static void main(String[] args) throws IOException {
		
		 
		
		AsyncHttpClient client = new AsyncHttpClient() ;
		String url = "http://localhost:2000/user/upload2";
		client.addHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryDuG59K47RNhgsXdo");
		RequestParams params = new  RequestParams();
//		params.p
		params.put("file1","321321" );
		File file = new File("d:/1.txt");
		params.put("filename1", file);
//		params.setForceMultipartEntityContentType(true);
			
		
/*		HttpPost arg0 = new HttpPost(url);
		arg0.addHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryDuG59K47RNhgsXdo");
		HttpParams params2 = arg0.getParams();
		params2.setParameter("11", file);
		HttpEntity entity = new FileEntity(file)  ;
		
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		BasicNameValuePair nameValuePair = new BasicNameValuePair("abc","abc");
		parameters.add(nameValuePair);
		UrlEncodedFormEntity uEncodedFormEntity = new UrlEncodedFormEntity(parameters);
		arg0.setEntity(uEncodedFormEntity);
		HttpContext arg1 = new BasicHttpContext();
		client.getHttpClient().execute(arg0, arg1 ); */
		client.post( url, params,   new  AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
