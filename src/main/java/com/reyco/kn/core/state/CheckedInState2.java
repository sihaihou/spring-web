package com.reyco.kn.core.state;
/**
 * 入住状态
 * @author Administrator
 */
class CheckedInState2 implements State2{
	@Override
	public void handle(HomeContext2 context2) {
		System.out.println("房间已入住，请勿打扰。。。。。");
		//
		//System.out.println("修改状态。。");
		context2.setState(this);
	}	
}