package com.df.fetch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.df.crawl.CrawlResult;
import com.df.crawl.WebTask;
import com.df.util.AccessType;
import com.df.util.ProxyPool;


public class HttpClientSpider {

	
	public static CrawlResult crawl(WebTask task) {
		CrawlResult cr = new CrawlResult();
		cr.setTask(task);
		if(null==task||task.getUrl().isEmpty()){
			cr.setSuccess(false);
			return cr;
		}
		
		if(task.getAccessType().equals(AccessType.POST))
			 crawlByPost(cr);
		else
			crawlByGet(cr);
		return cr;
	}
	
	private static void crawlByGet(CrawlResult cr){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet hg = new HttpGet(cr.getTask().getUrl());
			hg.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.103 Safari/537.36");
			CloseableHttpResponse response = httpclient.execute(hg);
			cr.setContent(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static void crawlByPost(CrawlResult cr){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpHost target = new HttpHost(cr.getTask().getHost(), 80, "http");
			HttpHost proxy = ProxyPool.getProxy();
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
			// 请求地址  
	        HttpPost httpPost = new HttpPost(cr.getTask().getPath());  
	        httpPost.setConfig(config); 
	        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.103 Safari/537.36");
			
	        RequestBuilder rb = RequestBuilder.post(cr.getTask().getUrl());
	        
	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	        for(Entry<String, Object> ent :cr.getTask().getParam().entrySet()){
				nvps.add(new BasicNameValuePair(ent.getKey(), ent.getValue().toString()));
			}
	        
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			System.out.println("Executing request " + httpPost.getRequestLine() + " to " + target + " via " + proxy);
			CloseableHttpResponse response = httpclient.execute(target,httpPost);
			
			HttpEntity entity = response.getEntity();
//			String charset = EntityUtils.getContentCharSet(entity);
			
			cr.setContent(EntityUtils.toString(entity));
			cr.setCode(response.getStatusLine().getStatusCode());
			cr.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void crawlByPostProxy(CrawlResult cr,HttpHost proxy){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		WebTask task = cr.getTask();
		try {
			HttpHost target = new HttpHost(task.getHost(), task.getPort(), task.getProtocol());
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
			// 请求地址  
	        HttpPost httpPost = new HttpPost(cr.getTask().getPath());  
	        httpPost.setConfig(config); 
	        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.103 Safari/537.36");
			
	        RequestBuilder rb = RequestBuilder.post(cr.getTask().getUrl());
	        
	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	        for(Entry<String, Object> ent :cr.getTask().getParam().entrySet()){
				nvps.add(new BasicNameValuePair(ent.getKey(), ent.getValue().toString()));
			}
	        
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			System.out.println("Executing request " + httpPost.getRequestLine() + " to " + target + " via " + proxy);
			CloseableHttpResponse response = httpclient.execute(target,httpPost);
			
			HttpEntity entity = response.getEntity();
//			String charset = EntityUtils.getContentCharSet(entity);
			
			cr.setContent(EntityUtils.toString(entity));
			cr.setCode(response.getStatusLine().getStatusCode());
			cr.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean checkProxy(HttpHost proxy){
		if(null== proxy)
			return false;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
            HttpHost target = new HttpHost("www.baidu.com", 80, "http");

            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)  
                    .setSocketTimeout(5000).build(); 
            
            HttpGet request = new HttpGet("/");
            request.setConfig(config);

            System.out.println("Executing request " + request.getRequestLine() + " to " + target + " via " + proxy);

            CloseableHttpResponse response = httpclient.execute(target, request);
            System.out.println("返回码:"+response.getStatusLine().getStatusCode());
            if(200==response.getStatusLine().getStatusCode())
            	return true;
        }catch (Exception e){
        	return false;
        }		
		finally {
            try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return false;
	}
	
	public static void main(String[] args) {
//		Map<String,Object> map = new HashMap<String,Object>();
//////		map.put("querystr", "请输入企业名称或注册�?");
//		map.put("pageNos", "2");
//		map.put("pageNo", "1");
//		map.put("pageSize", "20");
//		map.put("clear", "");
//		
//		WebTask task = new WebTask("http://qyxy.baic.gov.cn/dito/ditoAction!ycmlFrame.dhtml",map);
////		QyxyParser qp = new QyxyParser();
////		for(int i=9352;i<9355;i++){
////			map.put("pageNos", i+1);
////			map.put("pageNo", i);
////			task.setParam(map);
////			CrawlResult cr = spider.crawl(task);
////			List<QiYe> list = qp.parse(cr);
////			for(int x=0;x<list.size();x++)
////				System.out.println(list.get(x));
////		}
//		
////		WebTask task = new WebTask("http://www.qq.com");
////		CrawlResult cr = spider.crawl(task);
////		System.out.println(cr.getContent());
//		HttpClientSpider.crawl(task);
		
//		for(int i=0;i<20;i++){
			HttpClientSpider.checkProxy(new HttpHost("200.123.246.145",8080,"http"));
//		}
	}
}
