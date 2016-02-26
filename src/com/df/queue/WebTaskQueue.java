package com.df.queue;

import java.util.LinkedList;

import com.df.crawl.WebTask;

/**
 * 未访问的url队列
 *
 */
public class WebTaskQueue {
	// 超链接队列
	public static LinkedList<WebTask> urlQueue = new LinkedList<WebTask>();
	// 队列中对应最多的超链接数量
	public static final int MAX_SIZE = 10000;
	
	public synchronized static void addElement(WebTask task){
		urlQueue.add(task);
	}
	
	public synchronized static void addFirstElement(WebTask task){
		urlQueue.addFirst(task);
	}
	
	public synchronized static WebTask outElement(){
		return urlQueue.removeFirst();
	}
	
	public synchronized static boolean isEmpty(){
		return urlQueue.isEmpty();
	}
	
	public static int size(){
		return urlQueue.size();
	}
	
	public static boolean isContains(WebTask task){
		return urlQueue.contains(task);
	}
}
