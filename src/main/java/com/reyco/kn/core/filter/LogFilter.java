package com.reyco.kn.core.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *   日志过滤器
 * @author Admin
 */
public class LogFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		//logger.info("LogFilter....过滤器实现方式二");
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		try {
			// 输出参数日志
			StringBuilder sb = new StringBuilder();
			sb.append("Parameter : ");
			Enumeration paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues != null) {
					String value = "";
					for (String s : paramValues) {
						if (StringUtils.isBlank(value)) {
							value += s;
						} else {
							value += "," + s;
						}
					}
					sb.append("[" + paramName + ":" + URLDecoder.decode(value) + "]");
				}
			}
			sb.append("cookies : ");
			if(request.getCookies() != null) {
				for (Cookie c : request.getCookies()) {
					sb.append("[" + c.getName() + ":" + URLDecoder.decode(c.getValue()) + "]");
				}
			}
			String to = request.getRequestURI();
			logger.info("request to " + to + "  | " + sb.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	
}
