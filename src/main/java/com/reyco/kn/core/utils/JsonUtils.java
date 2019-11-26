package com.reyco.kn.core.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtils {
	/**
	 * json字符串转对象
	 * @param json
	 * @param obj
	 * @return
	 */
	public static Object jsonToObj(String json,Object obj) {
		return JSON.parseObject(json, obj.getClass());
	}
	/**
	 * 对象转json字符串
	 * @param json
	 * @param obj
	 * @return
	 */
	public static String objToJson(Object obj) {
		return JSON.toJSONString(obj);
	}
}
