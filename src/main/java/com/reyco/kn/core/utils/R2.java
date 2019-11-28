package com.reyco.kn.core.utils;

import java.io.Serializable;

/**
 * 
 * 响应工具类
 * 
 * @author reyco
 *
 */
public class R2 implements Serializable {
	private static final long serialVersionUID = -7809317658713239470L;
	/**
	 * 成功或失败
	 */
	private Boolean success;
	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 失败原因/后端看的
	 */
	private String msg;

	public R2() {
	}

	private R2(RBuilder rBuilder) {
		this.success = rBuilder.success;
		this.code = rBuilder.code;
		this.msg = rBuilder.msg;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object clone = super.clone();
		return clone;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
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

	@Override
	public String toString() {
		return "R2 [success=" + success + ", code=" + code + ", msg=" + msg + "]";
	}

	/**
	 * R 构建器
	 * 
	 * @author reyco
	 *
	 */
	public static class RBuilder {
		/**
		 * 成功或失败
		 */
		private Boolean success;
		/**
		 * 状态码
		 */
		private Integer code;
		/**
		 * 失败原因/后端看的
		 */
		private String msg;

		public RBuilder builderSuccess(Boolean success) {
			this.success = success;
			return this;
		}

		public RBuilder builderCode(Integer code) {
			this.code = code;
			return this;
		}

		public RBuilder builderMsg(String msg) {
			this.msg = msg;
			return this;
		}

		/**
		 * 构建其对象
		 * 
		 * @return
		 */
		public R2 builder() {
			return new R2(this);
		}
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		R2 r = new RBuilder().builderSuccess(true).builderCode(200).builderMsg("请求成功").builder();
		System.out.println(r);
	}

}
