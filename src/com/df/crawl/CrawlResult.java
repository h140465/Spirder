package com.df.crawl;

import java.util.Date;

public class CrawlResult {
	
	private boolean isSuccess;

	private int code;
	
	private String content;
	
	private Date beginTime;
	
	private Date endTime;
	
	private WebTask task;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public WebTask getTask() {
		return task;
	}

	public void setTask(WebTask task) {
		this.task = task;
	}
	
}
