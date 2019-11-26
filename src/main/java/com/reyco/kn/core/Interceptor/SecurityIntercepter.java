package com.reyco.kn.core.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.reyco.kn.core.domain.Result;
import com.reyco.kn.core.utils.CacheUtils;
import com.reyco.kn.core.utils.CookieUtil;
import com.reyco.kn.core.utils.JsonUtils;

@Component("securityIntercepter")
public class SecurityIntercepter implements HandlerInterceptor{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setContentType("application/json;charset=utf-8");
		//logger.info("在请求处理的方法之前执行");
		//logger.info("demoInterceptor...拦截器实现方式一");
		String cookie = CookieUtil.getCookieByName(request, "kn_token");
		if(null == cookie) {
			Result result = Result.fail("你未登陆...");
			response.getWriter().print(JsonUtils.objToJson(result));
			return false;
		}
		Object object = CacheUtils.get(cookie);
		if(null== object) {
			Result result = Result.fail("登陆失效...");
			response.getWriter().print(JsonUtils.objToJson(result));
			return false;
		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//System.out.println("在请求处理的方法执行后执行");
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("在DispatcherServlet处理后执行---清理工作");
	}
}
