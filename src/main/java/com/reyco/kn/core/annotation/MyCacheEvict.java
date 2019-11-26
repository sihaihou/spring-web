package com.reyco.kn.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCacheEvict {
	
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
	
}
