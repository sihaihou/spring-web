package com.reyco.kn.core.memento;
/**
 * 备忘录模式
 * @author reyco
 */
public class App {

	public static void main(String[] args) {
		CareTaker t=new CareTaker();
		Users users = null;
		for (int i = 1; i < 5; i++) {
			users = new Users();
			users.setId(i);
			users.setUsername("user"+i);
			users.setPassword("password"+i);
			t.setMementos(users.getMemento());
		}
		// 
		users.recovery(t.getMemento());
		System.out.println(users);
		users.recovery(t.getMemento());
		System.out.println(users);
		users.recovery(t.getMemento());
		System.out.println(users);
		users.recovery(t.getMemento());
		System.out.println(users);
		System.out.println("-------------------");
		users.recovery1(t.getMemento1());
		System.out.println(users);
		users.recovery1(t.getMemento1());
		System.out.println(users);
		users.recovery1(t.getMemento1());
		System.out.println(users);
		users.recovery1(t.getMemento1());
		System.out.println(users);
	}
	
	
}
