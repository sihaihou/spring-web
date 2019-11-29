package com.reyco.kn.core.memento;

/**
 * 用户------需要备忘的对象
 * @author reyco
 *
 */
public class Users {
	private Integer id;

	private String username;

	private String password;
	
	/**
	 * 
	 * @return
	 */
	public UsersMemento getMemento() {
		return new UsersMemento(this);
	}
	/**
	 * 恢复到上一个操作
	 * @param memento
	 */
	public void recovery(UsersMemento memento) {
		this.id=memento.getId();
		this.username=memento.getUsername();
		this.password=memento.getPassword();
	}
	/**
	 * 恢复到上一个操作
	 * @param memento
	 */
	public void recovery1(UsersMemento memento) {
		this.id=memento.getId();
		this.username=memento.getUsername();
		this.password=memento.getPassword();
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
		return "Users [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}
