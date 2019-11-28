package com.reyco.kn.core.utils;

import java.io.Serializable;

/**
 * 
 * 响应工具类
 * 
 * @author reyco
 *
 */
public class R3 implements Serializable {

	private static final long serialVersionUID = 5698278545387629199L;

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

	private R3(RBuilder rBuilder) {
		this.success = rBuilder.success;
		this.code = rBuilder.code;
		this.msg = rBuilder.msg;
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
	}

	/**
	 * 组装器
	 * 
	 * @author Admin
	 *
	 */
	public static class RDirector {

		private RBuilder builder;

		public RDirector(RBuilder builder) {
			this.builder = builder;
		}

		public RBuilder getBuilder() {
			return builder;
		}

		public void setBuilder(RBuilder builder) {
			this.builder = builder;
		}

		public R3 director() {
			return new R3(this.builder);
		}
	}

	public static void main(String[] args) {
		// 构建
		RBuilder builder = new RBuilder().builderSuccess(true).builderCode(200).builderMsg("请求成功");
		// 组装
		R3 r = new RDirector(builder).director();
		//
		System.out.println(r);
	}

}
