package com.df.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpHost;

public class ProxyPool {
	private static List<HttpHost> proxyList = new ArrayList<HttpHost>();  
	
//	static{
//		try {
//			HttpHost proxy1 = new HttpHost("117.177.250.147", 8080, "http");
//			HttpHost proxy2 = new HttpHost("200.113.19.21", 8080, "http");
//			HttpHost proxy3 = new HttpHost("61.153.198.178", 3128, "http");
//			HttpHost proxy4 = new HttpHost("115.159.193.158", 80, "http");
//			HttpHost proxy5 = new HttpHost("202.108.50.67", 80, "http");
//			HttpHost proxy6 = new HttpHost("199.119.137.72", 3128, "http");
//			HttpHost proxy7 = new HttpHost("218.106.96.194", 80, "http");
//			HttpHost proxy8 = new HttpHost("119.76.0.168", 8080, "http");
//			HttpHost proxy9 = new HttpHost("101.204.131.143", 8090, "http");
//			HttpHost proxy10 = new HttpHost("218.7.170.190", 3128, "http");
//			HttpHost proxy11 = new HttpHost("123.56.228.224", 8080, "http");
//			HttpHost proxy12 = new HttpHost("89.218.38.234", 8080, "http");
//			HttpHost proxy13 = new HttpHost("71.144.122.234", 8008, "http");
//			HttpHost proxy14 = new HttpHost("120.52.72.34", 80, "http");
//			HttpHost proxy15 = new HttpHost("221.130.17.137", 80, "http");
//			HttpHost proxy16 = new HttpHost("37.187.125.39", 8080, "http");
//			HttpHost proxy17 = new HttpHost("106.37.177.251", 3128, "http");
//			HttpHost proxy18 = new HttpHost("120.52.72.75", 80, "http");
//			HttpHost proxy19 = new HttpHost("121.201.18.74", 3128, "http");
//			HttpHost proxy20 = new HttpHost("121.69.24.22", 8118, "http");
//
//			proxyList.add(proxy1 );
//			proxyList.add(proxy2 );
//			proxyList.add(proxy3 );
//			proxyList.add(proxy4 );
//			proxyList.add(proxy5 );
//			proxyList.add(proxy6 );
//			proxyList.add(proxy7 );
//			proxyList.add(proxy8 );
//			proxyList.add(proxy9 );
//			proxyList.add(proxy10);
//			proxyList.add(proxy11);
//			proxyList.add(proxy12);
//			proxyList.add(proxy13);
//			proxyList.add(proxy14);
//			proxyList.add(proxy15);
//			proxyList.add(proxy16);
//			proxyList.add(proxy17);
//			proxyList.add(proxy18);
//			proxyList.add(proxy19);
//			proxyList.add(proxy20);
//		} catch (Exception e) {
//			System.out.println("初始化参数失败");
//		}
//		
//		
//	}
	
	public static HttpHost getProxy(){
		Random r = new Random();
		return proxyList.get(r.nextInt(proxyList.size()-1));
	}
	
	public static void setProxy(List<HttpHost> list){
		proxyList = list;
	}
	
	public static void main(String[] args) {
		ProxyPool.getProxy();
	}
}
