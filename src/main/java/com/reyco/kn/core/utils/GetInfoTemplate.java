package com.reyco.kn.core.utils;

import java.util.Map;
import java.util.Map.Entry;

import com.reyco.kn.core.exception.ArgumentException;
import com.reyco.kn.core.service.Template;

public abstract class GetInfoTemplate implements Template {
	
	/**
	 * 1. 验证参数
	 * @param map
	 * @throws ArgumentException
	 */
	private void validator(Map<String,String> map) throws ArgumentException {
		if(!map.isEmpty()) {
			System.out.println("参数验证通过");
		}else {
			throw new ArgumentException("参数验证不通过");
		}
	}
	/**
	 * 2. 获取缓存
	 * @param map
	 * @return
	 */
	private Object getCache(Map<String,String> map) {
		if(map.isEmpty()) {
			return null;
		}else {
			String key = getKey(map);
			return CacheUtils.get(key);
		}
	}
	/**
	 * 3. 具体的获取对象的方法
	 * @param obj
	 * @return
	 */
	protected abstract Object get();
	/**
	 * 4. 放入缓存
	 * @param map
	 * @param value
	 * @param expireTime
	 */
	private void putCache(Map<String,String> map,Object value,Long expireTime) {
		String key = getKey(map);
		new Thread(new Runnable() {
			@Override
			public void run() {
				CacheUtils.existsPut(key, value,expireTime);;				
			}
		}).start();
	}
	/**	
	 * 6. 响应
	 * @return
	 */
	private String response(Object obj) {
		if(null == obj) {
			return "暂无数据";
		}
		return R.successToJson(obj);
	}
	/**
	 * 执行程序
	 * @param map
	 * @return
	 * @throws ArgumentException
	 */
	public String handler(Map<String,String> map ) throws ArgumentException {
		validator(map);
		Object data = getCache(map);
		if(null == data) {
			data = get();
		}
		putCache(map,data,60*30L);
		return response(data);
	}
	
	/**
	 * 获取缓存key
	 * @param map
	 * @return
	 */
	private String getKey(Map<String,String> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key+"::"+value);
		}
		return sb.toString();
	}
}
