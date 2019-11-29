package com.reyco.kn.core.state;
/**
 * 空闲状态
 * @author reyco
 *
 */
public class FreeState2 implements State2{
	
	@Override
	public void handle(HomeContext2 context2) {
		System.out.println("房间空闲中,可预订。。。。。");
		//
		//System.out.println("修改状态。。");
		context2.setState(this);
	}	
	
}