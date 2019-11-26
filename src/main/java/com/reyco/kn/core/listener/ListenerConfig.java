package com.reyco.kn.core.listener;

import javax.servlet.ServletContextListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {
	
	@Bean
    public ServletListenerRegistrationBean<ServletContextListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<ServletContextListener> slrBean = new ServletListenerRegistrationBean<ServletContextListener>();
        slrBean.setListener(new LoginListener());
        return slrBean;
    }
	
}
