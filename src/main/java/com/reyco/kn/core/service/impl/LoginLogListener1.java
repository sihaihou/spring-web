package com.reyco.kn.core.service.impl;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.reyco.kn.core.event.LoginLogEvent;

/**
 * 自定义监听器
 * @author reyco
 *
 */
@Component
public class LoginLogListener1 implements ApplicationListener<LoginLogEvent>{

	@Override
	public void onApplicationEvent(LoginLogEvent loginLogEvent) {
		String logInfo = loginLogEvent.getLogInfo();
		System.out.println("insert登录日志1,logInfo="+logInfo);
	}

}
