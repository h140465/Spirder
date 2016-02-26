package com.df.crawl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.df.util.AccessType;

public class WebTask {

private String url;
	
	private AccessType accessType;
	
	private Map<String,Object> param;
	
	private URL url2 = null;
	
	public WebTask(String url){
		this(url,AccessType.GET,null);
	}
	
	public WebTask(String url,Map<String,Object> param){
		this(url,AccessType.POST,param);
	}
	
	public WebTask(String url,AccessType accessType,Map<String,Object> param){
		this.url = url;
		this.accessType = accessType;
		this.param = param;
		try {
			url2 = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	
	public String getHost(){
		return url2.getHost();
	}
	
	public int getPort(){
		return url2.getPort();
	}
	
	public String getProtocol(){
		return url2.getProtocol();
	}
	
	public String getPath(){
		return url2.getPath();
	}

	public AccessType getAccessType() {
		return accessType;
	}

	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}
	
	
}
