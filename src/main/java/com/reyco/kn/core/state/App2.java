package com.reyco.kn.core.state;

public class App2 {

	public static void main(String[] args) {
		HomeContext2 context2=new HomeContext2();
		State2 state2 = new FreeState2();
		state2.handle(context2);
		state2 = new CheckedInState2();
		state2.handle(context2);
	}
}
