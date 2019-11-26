package com.reyco.kn.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.core.annotation.Order;

/**
 * 注解方式无法对某一类型请求处理，只能全局过滤
 * @author Admin
 *
 */
//规定多个Filter的执行顺序,按照@Order()的值从小到大执行。
@Order(2) 
//urlPatterns：配置过滤的请求路径  filterName：过滤器名称
//@Component
//@WebFilter(filterName = "demoFilter",urlPatterns="/*") 
public class DemoFilter implements Filter{
	
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("demoFilter...过滤器实现方式一");
        filterChain.doFilter(servletRequest,servletResponse);
	}

}
