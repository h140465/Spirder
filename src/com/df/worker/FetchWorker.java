package com.df.worker;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.df.crawl.WebTask;
import com.df.fetch.HttpClientSpider;
import com.df.handler.NextUrlHandler;
import com.df.queue.WebTaskQueue;
import com.df.util.Constants;

public class FetchWorker implements Runnable {
	
	public static final Logger Log = Logger.getLogger(FetchWorker.class.getName());
	
	/**
	 * 下载对应页面并分析出页面对应URL，放置在未访问队列中
	 * @param url
	 * 
	 * 返回值：被封账号/系统繁忙/OK
	 * 
	 */
	protected String dataHandler(WebTask task){
		return NextUrlHandler.addNextWeiboUrl(HttpClientSpider.crawl(task));
	}
	
	@Override
	public void run() {
		Log.info("抓取任务启动："+WebTaskQueue.size()+"个任务");
		
		String result = null;
		try {
			while(!WebTaskQueue.isEmpty()){
				result = dataHandler(WebTaskQueue.outElement());
				process(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 根据处理结果进行分析需要执行的动作，并返回合法的gsid
	 * @param result
	 * @param gsid
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	protected String process(String result) throws InterruptedException, IOException{
		// 处理成功，未被冻结账号，停顿200ms，防止频繁访问被快速冻结账号
		if(result.equals(Constants.OK)){	
			Thread.sleep(2000);
		}
		// 系统繁忙
		else if(result.equals(Constants.SYSTEM_BUSY)){
			Thread.sleep(5 * 1000);
		}
		
		return "";
	}
}
