package com.ll.demo.wechat;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public static String getResponseBody(String uri){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try {
			HttpGet httpGet = new HttpGet(uri);
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			
			try {
				HttpEntity httpEntity = httpResponse.getEntity();
				return EntityUtils.toString(httpEntity);
			} finally {
				if(httpResponse != null){
					httpResponse.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(httpClient != null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
}
