package com.reyco.kn.core.memento;

/**
 * 备忘录对象
 * @author reyco
 *
 */
public class UsersMemento {

	private Integer id;

	private String username;

	private String password;

	public UsersMemento(Users users) {
		this.id = users.getId();
		this.username = users.getUsername();
		this.password = users.getPassword();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "memento [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}
