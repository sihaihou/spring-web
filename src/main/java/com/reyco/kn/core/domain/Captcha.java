package com.reyco.kn.core.domain;

/**
 * 验证码对象
 * @author reyco
 */
public class Captcha {
	/**
	 *  cookie的value值,通过key从缓存对象中取对应的value值
	 */
	private String key;
	/**
	 *  缓存的value值
	 */
	private String value;

	private Captcha(CaptchaBuilder builder) {
		this.key = builder.key;
		this.value = builder.value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Captcha 构建器
	 * @author Admin
	 *
	 */
	public static class CaptchaBuilder {
		private String key;
		private String value;

		public CaptchaBuilder builderKey(String key) {
			this.key = key;
			return this;
		}

		public CaptchaBuilder builderValue(String value) {
			this.value = value;
			return this;
		}

		public Captcha builder() {
			return new Captcha(this);
		}
	}
}
