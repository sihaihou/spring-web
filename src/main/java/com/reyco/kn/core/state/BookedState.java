package com.reyco.kn.core.state;
/**
 * 预订状态
 * @author reyco
 */
class BookedState implements State{
	
	@Override
	public void handle() {
		System.out.println("房间已预订。。。。。");

	}
	
}