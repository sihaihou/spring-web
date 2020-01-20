package com.reyco.kn.core.domain;

import java.io.Serializable;

public class TestVO implements Serializable {
	private static final long serialVersionUID = 962730078846814811L;

	private Integer id;
	
	private String name;
	
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
	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + "]";
	}
}
