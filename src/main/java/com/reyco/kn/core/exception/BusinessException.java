package com.reyco.kn.core.exception;

/**
 * 业务异常类
 * @author reyco
 */
public class BusinessException extends MyException{
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;  //错误码
	private String msg;    //错误信息  
 
	public BusinessException() {
		this("业务异常,请联系管理员...");
	}
	public BusinessException(String msg) {
		this(500,msg);
	}
	public BusinessException(Integer code, String msg) {
		this.type = "business";
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
