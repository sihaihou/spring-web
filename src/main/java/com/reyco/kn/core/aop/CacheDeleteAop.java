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
import com.reyco.kn.core.cache.KeyGenerator;
import com.reyco.kn.core.utils.CacheUtils;

@SuppressWarnings("all")
@Component
@Aspect
public class CacheDeleteAop {
	
	@Pointcut("@annotation(com.reyco.kn.core.annotation.MyCacheEvict)")
	public void cachePointcutDelete() {

	}

	/**
	 * 如果缓存存在直接获取缓存信息，否则获取执行方法后,将结果添加到缓存中
	 * 
	 * @param point
	 * @throws Throwable
	 */
	@Around("cachePointcutDelete()")
	public void around(ProceedingJoinPoint point) throws Throwable {
		// 1.获取相关数据
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		// 目标对象
		Class<? extends MethodSignature> clazz = methodSignature.getClass();
		// 目标方法
		Method method = methodSignature.getMethod();
		// 获取注解
		MyCacheEvict myCacheEvict = method.getAnnotation(MyCacheEvict.class);
		// key
		String key = "";
		// 2 是否有cacheableDelete注解,如果有走删除缓存,否则向下执行
		if (null != myCacheEvict) {
			// 目标参数名
			String[] paramNames = methodSignature.getParameterNames();
			// 目标参数值
			Object[] paramValues = point.getArgs();
			// 缓存名称
			String cacheName = getCacheName(myCacheEvict);
			// 2.1 获取key
			KeyGenerator keyGenerator = getKeyGenerator(myCacheEvict,clazz,method,paramNames,paramValues);
			key = getKey(cacheName,keyGenerator);
			// 2.2移除key的缓存
			CacheUtils.remove(key);
		}
		// 3 执行目标方法
		point.proceed();
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
	 * 获取KeyGenerator
	 * @param myCacheEvict
	 * @param clazz
	 * @param method
	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	private KeyGenerator getKeyGenerator(MyCacheEvict myCacheEvict,Class<? extends MethodSignature> clazz,Method method, String[] paramNames, Object[] paramValues) {
		// KeyGenerator
		KeyGenerator keyGeneratorObj = null;
		// 2.1有注解
		String keyGenerator = myCacheEvict.keyGenerator();
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
	 * 获取缓存名称
	 * @param myCacheEvict
	 * @return
	 */
	private String getCacheName(MyCacheEvict myCacheEvict) {
		// 缓存名称
		String cacheName = "";
		String name = myCacheEvict.name();
		if(!StringUtils.isBlank(name)) {
			cacheName = name+"::";
		}
		return cacheName;
	}
	/**
	 * key的默认生成器
	 * 
	 * @param target
	 *            目标对象
	 * @param method
	 *            目标方法
	 * @param params
	 *            目标方法参数
	 * @return
	 */
	private String createKeyGenerator(Object target, Method method, Object... params) {
		// 缓存的KeyGenerator
		StringBuilder sb = new StringBuilder();
		sb.append(target.getClass());
		sb.append(method.getName());
		for (Object obj : params) {
			sb.append(obj.toString());
		}
		return sb.toString();
	}
}
