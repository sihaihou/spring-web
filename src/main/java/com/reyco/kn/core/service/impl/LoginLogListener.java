package com.reyco.kn.core.service.impl;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.reyco.kn.core.domain.LoginLog;
import com.reyco.kn.core.domain.UserEntity;
import com.reyco.kn.core.event.LoginLogEvent;

@Component
public class LoginLogListener {
		
	/**
	 * 监听器
	 * @param loginLogEvent
	 */
	// @Async：异步执行，需要在入口类添加@EnableAsync注解,开启异步模式，异步执行不支持事物
	@Async   
	@EventListener
	public void save(LoginLogEvent loginLogEvent) {
		String logInfo = loginLogEvent.getLogInfo();
		System.out.println("insert登录日志,logInfo="+logInfo);
	}
	
	@Async
	@EventListener
	public void save(LoginLog loginLog) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("insert登录日志,loginLog="+loginLog+Thread.currentThread().getName());
	}
	@Async
	@EventListener
	public void save(UserEntity userEntity) throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("insert登录日志,userEntity="+userEntity+Thread.currentThread().getName());
	}
}
