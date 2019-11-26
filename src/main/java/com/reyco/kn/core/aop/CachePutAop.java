package com.reyco.kn.core.aop;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.reyco.kn.core.annotation.MyCacheEvict;
import com.reyco.kn.core.annotation.MyCacheable;
import com.reyco.kn.core.cache.KeyGenerator;
import com.reyco.kn.core.utils.CacheUtils;

@SuppressWarnings("all")
@Component
@Aspect
public class CachePutAop {
	
	@Pointcut("@annotation(com.reyco.kn.core.annotation.MyCacheable)")
	public void cachePointcutPut(){
		
	}
	/**
	 * 如果缓存存在直接获取缓存信息，否则获取执行方法后,将结果添加到缓存中
	 * @param point		
	 * @throws Throwable
	 */
	@Around("cachePointcutPut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		// 1.获取相关数据
		MethodSignature methodSignature = (MethodSignature)point.getSignature();
		// 目标对象
		Class<? extends MethodSignature> clazz = methodSignature.getClass();
		// 目标方法
		Method method = methodSignature.getMethod();
		// 目标参数名
		String[] paramNames = methodSignature.getParameterNames();
		// 目标参数值
		Object[] paramValues = point.getArgs();
		
		// 获取注解
		MyCacheable myCacheable = method.getAnnotation(MyCacheable.class);
		// key
		String key = "";
		// 3.是否有cacheablePut注解，如果缓存存在直接获取缓存信息，否则获取执行方法后,将结果添加到缓存中
		if(null != myCacheable) {
			String cacheName = getCacheName(myCacheable);
			KeyGenerator keyGenerator = getKeyGenerator(myCacheable,clazz,method,paramNames,paramValues);
			// 3.1有注解
			key = getKey(cacheName,keyGenerator);
			// 通过key获取缓存value
			Object object = CacheUtils.get(key);
			if(null == object) {
				// 缓存中不存在当前缓存,执行目标方法
				object = point.proceed();
				long expireTime = myCacheable.expireTime();
				if(expireTime < 1) {
					CacheUtils.put(key, object);
				}else {
					CacheUtils.put(key, object,expireTime);
				}
			}
			return object;
		}
		return point.proceed();
	}
	/**
	 * 获取key
	 * @param cacheName	                       缓存名称
	 * @param keyGeneratorObj   keyGeneratorObj对象
	 * @return
	 */
	private String getKey(String cacheName,KeyGenerator keyGeneratorObj) {
		// String key = cacheName + keyGenerator;
		String key = "";
		// 缓存
		String keyGenerator = keyGeneratorObj.getKeyGenerator();
		key = cacheName + keyGenerator;
		return key;
	}
	/**
	 * 获取缓存名称
	 * @param myCacheable
	 * @return
	 */
	private String getCacheName(MyCacheable myCacheable) {
		// 缓存名称
		String cacheName = "";
		String name = myCacheable.name();
		if(!StringUtils.isBlank(name)) {
			cacheName = name+"::";
		}
		return cacheName;
	}
	/**
	 * 获取KeyGenerator
	 * @param myCacheEvict
	 * @param clazz
	 * @param method
	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	private KeyGenerator getKeyGenerator(MyCacheable myCacheable,Class<? extends MethodSignature> clazz,Method method, String[] paramNames, Object[] paramValues) {
		// KeyGenerator
		KeyGenerator keyGeneratorObj = null;
		// 2.1有注解
		String keyGenerator = myCacheable.keyGenerator();
		if(StringUtils.isBlank(keyGenerator)) {
			keyGenerator = createKeyGenerator(clazz,method,paramValues);
		}else {
			// 3.2key是否包含#字符
			if (keyGenerator.contains("#")) {
				// 3.3 获取KeyGenerator的名称
				String keyGeneratorName = keyGenerator.substring(1);
				// 3.4 获取KeyGenerator值
				for (Object paramValue : paramValues) {
					for (int j = 0; j < paramNames.length; j++) {
						if (keyGeneratorName.equals(paramNames[j])) {
							// 如果注解的keyGenerator和参数的名称一直，取对应参数的值作为keyGenerator
							keyGenerator = paramValues[j].toString();
						}
					}
				}
			}
		}
		keyGeneratorObj = new KeyGenerator();
		keyGeneratorObj.setKeyGenerator(keyGenerator);
		return keyGeneratorObj;
	}
	/**
	 * key的默认生成器
	 * @param target	目标对象
	 * @param method	目标方法
	 * @param params	目标方法参数
	 * @return
	 */
	private String createKeyGenerator(Object target, Method method, Object... params) {
		// 缓存的key
		StringBuilder sb = new StringBuilder();
		sb.append(target.getClass());
		sb.append(method.getName());
		for (Object obj : params) {
			sb.append(obj.toString());
		}
		return sb.toString();
	}	
}
