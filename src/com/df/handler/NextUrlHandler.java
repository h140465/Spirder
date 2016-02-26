package com.df.handler;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

import com.df.bean.QiYe;
import com.df.crawl.CrawlResult;
import com.df.parse.QyxyParser;
import com.df.queue.WebTaskQueue;
import com.df.util.Constants;


public class NextUrlHandler {
	
	public static final Logger Log = Logger.getLogger(NextUrlHandler.class.getName());

	/**
	 * 从抓取页面的content HTML中解析出下一页的URL，并添加至UrlQueue
	 * 
	 * @param content
	 * @return
	 */
	public static String addNextWeiboUrl(CrawlResult page){
		String content = page.getContent();
//		Document doc = page.getContentDoc();
		
		// 请求weibo.cn返回entity为null
		if(content == null){
			return Constants.ACCOUNT_FORBIDDEN;
		}
//		// 系统繁忙或账号被封
		if(content.equals(Constants.ACCOUNT_FORBIDDEN) || content.equals(Constants.SYSTEM_BUSY)){
			return content;
		}
		
		List<QiYe> list = QyxyParser.parse(page);
		for(int x=0;x<list.size();x++)
			System.out.println((list.get(x).toString()));
		
//		// 微博页面异常的显示“没有发布微博”，跳转至下一页继续处理
//		else if(content.startsWith(Constants.SYSTEM_EMPTY)){
//			Log.info(">> 当前页面显示“没有发布微博”：" + content);
//		}
		// 正常
//		else{
			//    WeiboFetcher L68: WeiboParser.getGoalContent 
			// -> WeiboParser L27：已经对content进行过一次Jsoup.parse了
			// Jsoup.parse是一个非常耗时的操作，因此通过传参的方式将这里的Jsoup.parse避免掉
			// Document doc = Jsoup.parse(content);	
			
//			Element pageEl = doc.getElementById("pagelist");
//			
//			if(pageEl != null){
//				List<Element> hrefEls = pageEl.getElementsByTag("a");
//				for(Element el: hrefEls){
//					if(el.toString().contains("下页")){
//						WeiboUrlQueue.addElement("http://weibo.cn" + el.attr("href").split("&gsid=")[0]); 
//						break;
//					}
//				}
//				Log.info(">> progress of current user: " + pageEl.text());
//			}
//		}
		Log.info("-------------------");
//		Log.info("抓取到：" + WebTaskQueue.size());
//		Log.info("已处理：" + VisitedWeiboUrlQueue.size());
//		Log.info("异常数：" + AbnormalAccountUrlQueue.size());
//		Log.info("-------------------");
		
		return Constants.OK;
	}
	

}
