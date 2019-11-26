package com.reyco.kn.core.exception;

/**
 * 自定义异常顶级父类
 * @author reyco
 *
 */
public class MyException extends Exception {
	
	private static final long serialVersionUID = 8302278103288977774L;
	/**
	 * 异常类型
	 */
	public String type;

	public String getType() {
		return type;
	}
}
