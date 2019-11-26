package com.reyco.kn.core.filter;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Bean
	public FilterRegistrationBean<Filter> configFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new LogFilter());
		filterRegistrationBean.addUrlPatterns("/api/*");
		filterRegistrationBean.setName("logFilter");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
}
