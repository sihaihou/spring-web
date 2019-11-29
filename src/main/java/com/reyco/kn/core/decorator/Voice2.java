package com.reyco.kn.core.decorator;

/**
 * 二倍音量声音
 * @author Admin
 *
 */
public class Voice2 extends SuperVoice {

	public Voice2(IVoice ivoice) {
		super(ivoice);
	}
	
	@Override
	public Integer voiceSize() {
		return super.voiceSize()*2;
	}
	
}
