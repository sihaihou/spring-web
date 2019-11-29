package com.reyco.kn.core.state;
/**
 * 入住状态
 * @author Administrator
 */
class CheckedInState implements State{
	@Override
	public void handle() {
		System.out.println("房间已入住，请勿打扰。。。。。");
	}	
}