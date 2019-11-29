package com.reyco.kn.core.decorator;

/**
 * 5倍音量声音
 * @author Admin
 *
 */
public class Voice5 extends SuperVoice {

	public Voice5(IVoice ivoice) {
		super(ivoice);
	}
	
	@Override
	public Integer voiceSize() {
		return super.voiceSize()*5;
	}
	
}
