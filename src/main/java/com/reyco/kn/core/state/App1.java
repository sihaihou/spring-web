package com.reyco.kn.core.state;

/**
 * 状态模式
 * @author Admin
 *
 */
public class App1 {

	public static void main(String[] args) {
		HomeContext hc=new HomeContext();
		hc.setState(new FreeState());
		hc.setState(new CheckedInState());
	}
}
