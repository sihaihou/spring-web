package com.reyco.kn.core.service.impl;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.reyco.kn.core.domain.LoginLog;

@Component
public class LoginLogListener {
		
	@Async
	@EventListener
	public void save(LoginLog loginLog) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("insert登录日志,loginLog="+loginLog+Thread.currentThread().getName());
	}
}
