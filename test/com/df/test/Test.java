package com.df.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.df.crawl.WebTask;
import com.df.queue.WebTaskQueue;
import com.df.worker.FetchWorker;

public class Test {

	public static void main(String[] args) throws Exception {
		for(int i =0;i<40;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pageNos", i+1);
			map.put("pageNo", i);
			WebTask task = new WebTask("http://qyxy.baic.gov.cn/dito/ditoAction!ycmlFrame.dhtml",map);
			WebTaskQueue.addFirstElement(task);
		}
		Date now = new Date();
		List<Thread> l = new ArrayList<Thread>();
		for(int x=0;x<20;x++){
			Thread t = new Thread(new FetchWorker());
//			t.start();
			l.add(t);
		}
		
		for(Thread t:l)
			t.start();
		
		for(Thread d:l)
			d.join();
		System.out.println("一共耗时:"+(new Date().getTime() - now.getTime()));
	}

}
