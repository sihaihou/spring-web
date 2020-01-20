package com.reyco.kn.core.domain;

import java.io.Serializable;

public class TestDO implements Serializable {
	private static final long serialVersionUID = 962730078846814811L;

	private Integer id;
	
	private String name;
	
	private String password;
	
	private Byte state;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Byte getState() {
		return state;
	}
	public void setState(Byte state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "TestDO [id=" + id + ", name=" + name + ", password=" + password + ", state=" + state + "]";
	}
}
