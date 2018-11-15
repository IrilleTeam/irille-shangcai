package com.shangcai.common.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public String doGet(String url) throws ClientProtocolException, IOException {
		logger.info("发起httpGet请求:{}",url);
		CloseableHttpClient httpClient = null;
		BufferedReader reader = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			InputStream input = HttpClients.createDefault().execute(httpGet).getEntity().getContent();
			reader = new BufferedReader(new InputStreamReader(input, Charset.forName("utf-8")));
			StringBuilder sb = new StringBuilder();
			String buffer = null;
			while ((buffer = reader.readLine()) != null) {
				sb.append(buffer);
			}
			logger.info("回传内容:{}", sb.toString());
			return sb.toString();
		} finally {
			if (httpClient != null)
				httpClient.close();
			if (reader != null)
				reader.close();
		}
	}

	public String doPost(String url, HttpEntity entity) throws UnsupportedOperationException, ClientProtocolException, IOException {
		logger.info("发起httpPost请求:{}",url);
		CloseableHttpClient httpClient = null;
		BufferedReader reader = null;
		
		try {
			HttpPost httpPost = new HttpPost(url);
			if(entity != null)
				httpPost.setEntity(entity);
			CloseableHttpResponse response = HttpClients.createDefault().execute(httpPost);
			logger.info("content-type:{}", response.getFirstHeader("content-type"));
			InputStream input = response.getEntity().getContent();
			reader = new BufferedReader(new InputStreamReader(input, Charset.forName("utf-8")));
			StringBuilder sb = new StringBuilder();
			String buffer = null;
			while ((buffer = reader.readLine()) != null) {
				sb.append(buffer);
			}
			logger.info("回传内容:{}", sb.toString());
			return sb.toString();
		} finally {
			if (httpClient != null)
				httpClient.close();
			if (reader != null)
				reader.close();
		}
	}
}
