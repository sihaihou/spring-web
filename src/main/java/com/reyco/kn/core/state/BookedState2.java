package com.reyco.kn.core.state;
/**
 * 预订状态
 * @author reyco
 */
class BookedState2 implements State2{
	
	@Override
	public void handle(HomeContext2 context2) {
		System.out.println("房间已预订。。。。。");
		//
		//System.out.println("修改状态。。");
		context2.setState(this);
	}
	
}