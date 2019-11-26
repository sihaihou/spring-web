package com.reyco.kn.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCacheable {
	/**
	 * 缓存名称
	 * @return
	 */
	String name() default "";
	/**
	 * 缓存的keyGenerator
	 * @return
	 */
	String keyGenerator() default "";
	
	/**
	 * 缓存的过期时间,默认30分钟
	 * @return
	 */
	long expireTime() default 60*30L;
	
}
