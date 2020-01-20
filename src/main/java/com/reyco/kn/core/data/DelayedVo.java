package com.reyco.kn.core.data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
/**
 * 
 *  延时队列实体Delayed
 * @author housihai
 *
 */
public class DelayedVo<T> implements Delayed{
	/**
	 * 过期时长/单位毫秒
	 */
	private Long expireTime;
	/**
	 * 目标对象
	 */
	private T target;
	
	public DelayedVo(Long expireTime, T target) {
		super();
		this.expireTime = expireTime+System.currentTimeMillis();
		this.target = target;
	}

	@Override
	public int compareTo(Delayed o) {
		return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(expireTime - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
	}

	public T getTarget() {
		return this.target;
	}

}
