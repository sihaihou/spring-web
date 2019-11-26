package com.reyco.kn.core.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.kn.core.cache.ConcurrentHashMapCache;


//@Component
public class CacheTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Scheduled(cron = "0 * * * * ?")
	public void clear() {
		logger.debug("缓存定时任务执行开始："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		ConcurrentHashMapCache instance = ConcurrentHashMapCache.getInstance();
		instance.removeStrategy();
		logger.debug("缓存定时任务执行结束："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
