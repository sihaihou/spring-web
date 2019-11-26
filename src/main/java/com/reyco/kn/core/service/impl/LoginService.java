package com.reyco.kn.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.reyco.kn.core.event.LoginLogEvent;

@Service
public class LoginService {
	
	@Autowired
	private ApplicationContext applicationContext; 
	 
	public void login(){
		LoginLogEvent loginLogEvent = new LoginLogEvent(this, "登录日志信息");
		applicationContext.publishEvent(loginLogEvent);
	}
}
