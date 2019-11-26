package com.reyco.kn.core.cache;

/**
 * 
 * 缓存顶级接口
 * 
 * @author reyco
 *
 */
public interface Cache {
	/**
	 * 添加缓存对象
	 * @param key 		缓存的key,通过该key可以获取对应的缓存对象
	 * @param obj		缓存的对象...  
	 * @return
	 */
	public Boolean put(String key, Object obj);
	/**
	 * 添加缓存对象
	 * @param key 			缓存的key,通过该key可以获取对应的缓存对象
	 * @param obj			缓存的对象...  
	 * @param duration		存活时长...
	 * @return
	 */
	public Boolean put(String key, Object obj,Long duration);
	/**
	 * 获取缓存对象
	 * @param key	缓存的key,通过该key可以获取对应的缓存对象
	 * @return
	 */
	public Object get(String key);
	/**
	 * 缓存的数量大小
	 * @return
	 */
	public Integer getSize();
	/**
	 * 移除缓存
	 * @param key   缓存的key,通过该key可以获取对应的缓存对象
	 * @return
	 */
	public Boolean remove(String key);
	/**
	 * 清空缓存
	 * @return
	 */
	public Boolean clear();
	/**
	 * 缓存删除策略
	 */
	public void removeStrategy();
}
