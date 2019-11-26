package com.reyco.kn.core.domain;

public class Permission {
	
	private Integer userId;
	
	private String percode;
	
	private String name;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPercode() {
		return percode;
	}
	public void setPercode(String percode) {
		this.percode = percode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Permission [userId=" + userId + ", percode=" + percode + ", name=" + name + "]";
	}
	
}
