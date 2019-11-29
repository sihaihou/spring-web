package com.reyco.kn.core.decorator;

/**
 * 普通音量声音
 * @author reyco
 *
 */
public class Voice implements IVoice {
	
	private Integer voiceSize;
	public Voice() {
		// TODO Auto-generated constructor stub
	}
	public Voice(Integer voiceSize) {
		this.voiceSize = voiceSize;
	}
	@Override
	public Integer voiceSize() {
		return 10;
	}

}
