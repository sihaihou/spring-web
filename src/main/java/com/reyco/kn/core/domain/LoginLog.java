package com.reyco.kn.core.domain;

public class LoginLog {

	private String name;
	
	public LoginLog(LoginLigBuilder builder) {
		this.name = builder.name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 构建器
	 * @author reyco
	 *
	 */
	public static class LoginLigBuilder{
		private String name;
		
		public LoginLigBuilder builderName(String name) {
			this.name = name;
			return this;
		}
		public LoginLog builder() {
			return new LoginLog(this);
		}
	}
	
}
