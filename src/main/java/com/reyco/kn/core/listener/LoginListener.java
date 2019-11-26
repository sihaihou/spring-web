package com.reyco.kn.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LoginListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("监听器初始化。。。。。。。。。。");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听器销毁。。。。。。。。。。。");
	}
	
}
