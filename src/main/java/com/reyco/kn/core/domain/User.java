package com.reyco.kn.core.domain;

public class User {
	
	private Integer id;
	private String username;
	public User() {
		// TODO Auto-generated constructor stub
	}
	private User(UserBuilder builder) {
		this.id = builder.id;
		this.username = builder.username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 构建器
	 * @author reyco
	 *
	 */
	public static class UserBuilder {
		private Integer id;
		private String username;
		
		public UserBuilder builderId(Integer id) {
			this.id = id;
			return this;
		}
		public UserBuilder builderUsername(String username) {
			this.username = username;
			return this;
		}
		public User builder() {
			return new User(this);
		}
	}
	
}
