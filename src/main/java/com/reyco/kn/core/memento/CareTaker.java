package com.reyco.kn.core.memento;

import java.util.Stack;

/**
 * 负责人对象
 * 负责管理备忘录对象
 * @author reyco
 *
 */
public class CareTaker {
	/**
	 *  1.   push( num) //入栈
		2.   pop() //栈顶元素出栈
		3.   empty() //判定栈是否为空
		4.   peek() //获取栈顶元素
		5.   search(num) //判端元素num是否在栈中，如果在返回1，不在返回-1。
	 */
	/**
	 * 备忘
	 */
	private Stack<UsersMemento> mementos = new Stack<>();
	/**
	 * 恢复
	 */
	private Stack<UsersMemento> mementos1 = new Stack<>();
	
	public UsersMemento getMemento() {
		UsersMemento memento = mementos.pop();
		setMementos1(memento);
		return memento;
	}
	public UsersMemento getMemento1() {
		UsersMemento memento = mementos1.pop();
		setMementos(memento);
		return memento;
	}
	public void setMementos(UsersMemento memento) {
		this.mementos.push(memento);
	}
	public void setMementos1(UsersMemento memento) {
		this.mementos1.push(memento);
	}
	
}
