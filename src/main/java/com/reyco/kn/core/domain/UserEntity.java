package com.reyco.kn.core.domain;

public class UserEntity extends BaseEntity{
	
	private static final long serialVersionUID = -7876820087892486934L;
	
	private String password;
	private String username;
	private String salt;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id +",name=" + name + ",password=" + password +  ",desc=" + desc
				+",state=" + state + ",gmtCreate=" + gmtCreate + ",gmtModified=" + gmtModified + "]";
	}
	
}
