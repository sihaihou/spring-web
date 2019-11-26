package com.reyco.kn.core.domain;

public class Result {
	
	
	private Boolean success;
	private Integer code;
	private Object data;
	private String msg;
	
	public Result() {
		
	}
	public Result(Boolean success) {
		super();
		this.success = success;
	}
	public Result(Integer code) {
		super();
		this.code = code;
	}
	public Result(Boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}
	public Result(Integer code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}
	public Result(Boolean success, Integer code) {
		super();
		this.success = success;
		this.code = code;
	}
	public Result(Boolean success, Integer code, Object data) {
		super();
		this.success = success;
		this.code = code;
		this.data = data;
	}
	public Result(Boolean success, Integer code, String msg) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
	}
	public Result(Boolean success,Integer code, Object data, String msg) {
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public static Result success() {
		return success("成功");
	}
	public static Result success(Object data) {
		return success(200,data);
	}
	public static Result success(Integer code,Object data) {
		return new Result(true,code,data,null);
	}
	public static Result fail() {
		return fail("未知失败，请联系管理员");
	}
	public static Result fail(String msg) {
		return fail(201,msg);
	}
	public static Result fail(Integer code,String msg) {
		return new Result(false,code,null,msg);
	}
	public static Result error() {
		return error("未知异常，请联系管理员");
	}
	public static Result error(String msg) {
		return error(500,msg);
	}
	public static Result error(Integer code,String msg) {
		return new Result(false,code,null,msg);
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "Result [success=" + success + ", code=" + code + ", data=" + data + ", msg=" + msg + "]";
	}
}
