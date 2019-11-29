package com.reyco.kn.core.state;
/**
 * 空闲状态
 * @author reyco
 *
 */
public class FreeState implements State{
	
	@Override
	public void handle() {
		System.out.println("房间空闲中,可预订。。。。。");
	}	
	
}