package com.df.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.log4j.Logger;

import com.df.crawl.CrawlResult;
import com.df.crawl.WebTask;
import com.df.fetch.HttpClientSpider;
import com.df.parse.ProxyParse;


public class InitUtil {

	private static final Logger Log = Logger.getLogger(InitUtil.class.getName());
	
	public static void InitProxyPool(){
		Log.info("开始初始化代理池--------------------->");
		WebTask task = new WebTask("http://www.89ip.cn/api/?&tqsl=300&sxa=&sxb=&tta=&ports=&ktip=&cf=1");
		CrawlResult cr = HttpClientSpider.crawl(task);
//		System.out.println(cr.getContent());
		List<HttpHost> list = ProxyParse.parse(cr.getContent());
		List<HttpHost> proxyList = new ArrayList<HttpHost>();
		for(HttpHost host:list){
			if(HttpClientSpider.checkProxy(host))
				proxyList.add(host);
		}
		Log.info("一共抓取"+list.size()+"个代理IP，其中"+proxyList.size()+"可用");
	}
	
	private void mutiCheck(){
		
	}
	
	public static void main(String[] args) {
		InitUtil.InitProxyPool();
//		HttpHost host = new HttpHost("123.157.209.62:8000");
//		HttpClientSpider.checkProxy(host);
	}
	
}
