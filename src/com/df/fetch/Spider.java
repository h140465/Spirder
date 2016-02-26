package com.df.fetch;

import com.df.crawl.CrawlResult;
import com.df.crawl.WebTask;

public interface Spider {

	public CrawlResult crawl(WebTask task);
	
}
