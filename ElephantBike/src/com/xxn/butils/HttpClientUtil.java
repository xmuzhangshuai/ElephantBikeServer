package com.xxn.butils;

import ytx.org.apache.http.HttpEntity;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.client.HttpClient;
import ytx.org.apache.http.client.methods.HttpPost;
import ytx.org.apache.http.entity.StringEntity;
import ytx.org.apache.http.util.EntityUtils;
/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
	public String doPost(String url, String param){
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try{
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			//设置参数
			StringEntity entity = new StringEntity(param, "utf-8");
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,"utf-8");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}