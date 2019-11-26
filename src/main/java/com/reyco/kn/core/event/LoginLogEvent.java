package com.reyco.kn.core.event;

import org.springframework.context.ApplicationEvent;

/**
 * 登录记录日志自定义事件
 * @author reyco
 *
 */
public class LoginLogEvent extends ApplicationEvent{
	
	/**
	 * 模拟登录信息
	 */
	private String logInfo;
	
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	public LoginLogEvent(Object source,String logInfo) {
		super(source);
		this.logInfo = logInfo;
	}
	
}
