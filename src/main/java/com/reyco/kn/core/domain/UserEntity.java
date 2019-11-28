package com.reyco.kn.core.domain;

import java.util.Date;
/**
 * 用户实体
 * @author reyco
 *
 */
public class UserEntity extends BaseEntity{
	
	private static final long serialVersionUID = -7876820087892486934L;
	
	private String password;
	
	private String username;
	
	private String salt;
	
	public UserEntity() {
		
	}
	
	private UserEntity(UserEnitiyBuilder builder) {
		this.id = builder.id;
		this.password = builder.password;
		this.username = builder.username;
		this.salt = builder.salt;
		this.gmtCreate = builder.gmtCreate;
		this.state = builder.state;
	}
	
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
	public static class UserEnitiyBuilder {
		private Integer id;
		private String password;
		private String username;
		private String salt;
		private Date gmtCreate;
		private Integer state;
		
		public UserEnitiyBuilder builderId(Integer id) {
			this.id = id;
			return this;
		}
		public UserEnitiyBuilder builderPassword(String password) {
			this.password = password;
			return this;
		}
		public UserEnitiyBuilder builderUsername(String username) {
			this.username = username;
			return this;
		}
		public UserEnitiyBuilder builderSalt(String salt) {
			this.salt = salt;
			return this;
		}
		public UserEnitiyBuilder builderGmtCreate(Date gmtCreate) {
			this.gmtCreate = gmtCreate;
			return this;
		}
		public UserEnitiyBuilder builderState(Integer state) {
			this.state = state;
			return this;
		}
		public UserEntity builder() {
			return new UserEntity(this);
		}
	}
}
