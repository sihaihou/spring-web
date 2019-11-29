package com.reyco.kn.core.decorator;

/**
 * 装饰器
 * @author Admin
 *
 */
public class SuperVoice implements IVoice{
	
	private IVoice ivoice;
	
	public SuperVoice(IVoice ivoice) {
		this.ivoice = ivoice;
	}
	public IVoice getIvoice() {
		return ivoice;
	}
	public void setIvoice(IVoice ivoice) {
		this.ivoice = ivoice;
	}
	@Override
	public Integer voiceSize() {
		return ivoice.voiceSize();
	}
	
}
