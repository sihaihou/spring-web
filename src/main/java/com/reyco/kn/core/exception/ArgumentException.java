package com.reyco.kn.core.exception;

/**
 * 参数异常
 * @author reyco
 *
 */
public class ArgumentException extends MyException{
	
	private static final long serialVersionUID = 7718169525499572946L;
	private String msg;
	
	public ArgumentException() {
		this("参数错误,请联系管理员...");
	}
	public ArgumentException(String msg) {
		this.type = "argument";
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
