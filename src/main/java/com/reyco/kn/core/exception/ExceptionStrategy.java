package com.reyco.kn.core.exception;

/**
 * 自定义异常处理顶级接口
 * @author reyco
 *
 */
public interface ExceptionStrategy{
	
	/**
	 * 获取异常信息
	 * @param ex  异常对象
	 * @return
	 */
	String getExceptionMsg(Exception ex);
	
}
