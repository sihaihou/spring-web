package com.reyco.kn.core.state;
/**
 * 房间对象
 * 		持有状态的引用
 * @author Administrator
 */
public class HomeContext2 {
	
	private State2 state2;
	/**
	 * 设置状态。。
	 * @param state
	 */
	public void setState(State2 state2){
		this.state2=state2;
	}
	
}