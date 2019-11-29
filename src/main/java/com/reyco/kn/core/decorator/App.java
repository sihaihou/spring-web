package com.reyco.kn.core.decorator;
/**
 * 装饰模式
 * @author reyco
 *
 */
public class App {

	public static void main(String[] args) {
		IVoice voice = new Voice(10);
		System.out.println("普通音量："+voice.voiceSize());
		
		IVoice voice2 = new Voice2(voice);
		System.out.println("2倍音量："+voice2.voiceSize());
		
		IVoice voice5 = new Voice5(new Voice(10));
		System.out.println("5倍音量："+voice5.voiceSize());
		
		voice5 = new Voice5(new Voice2(new Voice(10)));
		System.out.println("5*2倍音量："+voice5.voiceSize());
	}
	
}
