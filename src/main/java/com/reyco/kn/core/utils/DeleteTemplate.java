package com.reyco.kn.core.utils;

import java.util.Map;
import java.util.Map.Entry;

import com.reyco.kn.core.service.Template;

import com.reyco.kn.core.exception.ArgumentException;

public abstract class DeleteTemplate implements Template{
	
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
	 * 2. 删除数据
	 * @param obj
	 * @return
	 */
	protected abstract void delete();
	/**
	 * 3. 删除缓存
	 * @param map
	 * @return
	 */
	private void deleteCache(Map<String,String> map) {
		String key = getKey(map);
		CacheUtils.remove(key);
	}
	/**	
	 * 4. 响应
	 * @return
	 */
	private String response() {
		return R.successToJson();
	}
	/**
	 * 执行程序
	 * @param map
	 * @return
	 * @throws ArgumentException
	 */
	public String handler(Map<String,String> map ) throws ArgumentException {
		validator(map);
		delete();
		deleteCache(map);
		return response();
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
