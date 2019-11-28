package com.reyco.kn.core.aop;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.reyco.kn.core.annotation.MyCacheEvict;
import com.reyco.kn.core.annotation.MyCacheable;
import com.reyco.kn.core.cache.KeyGenerator;
import com.reyco.kn.core.utils.CacheUtils;
import com.reyco.kn.core.utils.R;

@SuppressWarnings("all")
@Component
@Scope
@Aspect
@Order(1001)
public class RateLimitAop{
	
	private RateLimiter rateLimiter = RateLimiter.create(5.0,5,TimeUnit.SECONDS);
	
	@Autowired
	private HttpServletResponse response;
	
	@Pointcut("@annotation(com.reyco.kn.core.annotation.RateLimit)")
	public void rateLimitPut(){
		
	}
	@Around("rateLimitPut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        try {
            if(rateLimiter.tryAcquire(1,200,TimeUnit.MILLISECONDS)) {
                obj = joinPoint.proceed();
            }else{
            	String result = R.failToJson("操作太频繁，请稍后再试", "系统繁忙");
                output(response, result);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
	}
	public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
	
}
