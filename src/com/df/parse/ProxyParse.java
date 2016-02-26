package com.df.parse;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ProxyParse {
	
	public static List<HttpHost> parse(String content){
		if(null==content||content.isEmpty())
			return null;
		List<HttpHost> list = new ArrayList<HttpHost>();
		Document doc = Jsoup.parse(content);
		Element tbody = doc.select("body").first();
		String body = tbody.text();
		System.out.println("-:"+body);
		for(String tr:body.split(" ")){
			try {
				String[] strs = tr.trim().split(":");
				HttpHost host = new HttpHost(strs[0], Integer.valueOf(strs[1]),"http");
				list.add(host);
			} catch (Exception e) {
			}
			
		}
		return list;
	}
}
