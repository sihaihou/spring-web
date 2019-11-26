package com.reyco.kn.core.utils;

import com.reyco.kn.core.cache.ConcurrentHashMapCache;

public class CacheUtils {

	private static ConcurrentHashMapCache getConcurrentHashMapCache() {
		return ConcurrentHashMapCache.getInstance();
	}
	/**
	 * 添加元素
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @param duration
	 *            有效时间/单位秒
	 * @return
	 */
	public static void put(String key,Object value,Long expireTime) {
		ConcurrentHashMapCache concurrentHashMapCache = getConcurrentHashMapCache();
		concurrentHashMapCache.put(key,value,expireTime);
	}
	/**
	 * 添加元素
	 * @param key
	 * @param value
	 * @return
	 */
	public static void put(String key,Object value) {
		ConcurrentHashMapCache concurrentHashMapCache = getConcurrentHashMapCache();
		concurrentHashMapCache.put(key, value);
	}
	/**
	 * 获取缓存对象
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		ConcurrentHashMapCache concurrentHashMapCache = getConcurrentHashMapCache();
		return concurrentHashMapCache.get(key);
	}
	/**
	 * 移除缓存对象
	 * @param key
	 * @return
	 */
	public static Object remove(String key) {
		ConcurrentHashMapCache concurrentHashMapCache = getConcurrentHashMapCache();
		return concurrentHashMapCache.remove(key);
	}
	
}
