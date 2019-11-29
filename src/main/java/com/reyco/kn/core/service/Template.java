package com.reyco.kn.core.service;

import java.util.Map;

public interface Template {
	/**
	 * 执行
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String handler(Map<String,String> map ) throws Exception;
}
