package com.reyco.kn.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reyco.kn.core.annotation.MyCacheEvict;
import com.reyco.kn.core.annotation.MyCacheable;
import com.reyco.kn.core.dao.UserDao;
import com.reyco.kn.core.domain.UserEntity;
import com.reyco.kn.core.domain.UserEntity.UserEnitiyBuilder;
import com.reyco.kn.core.service.UserService;

@Service("userService")
@CacheConfig(cacheNames="user-key",keyGenerator="redisKeyGenerator")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	//@Cacheable(/*key="#id",*/ sync=true)
	@MyCacheable(keyGenerator="#id",name="user")
	public UserEntity get(Integer id) {
		return userDao.get(id);
	}
	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	//@CachePut(key="#userEntity.id")
	public UserEntity update(UserEntity userEntity) {
		userDao.update(userEntity);
		return userEntity;
	}
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	//@CacheEvict(key="#id")
	@MyCacheEvict(keyGenerator="#id",name="user")
	public void delete(Integer id) {
		UserEnitiyBuilder builder = new UserEnitiyBuilder()
				.builderId(id)
				.builderState(1);
		UserEntity userEntity = builder.builder();
		//userDao.update(userEntity);
	}
	
	@Override
	//@Cacheable
	public List<UserEntity> list(Map<String, Object> map) {
		return userDao.list(map);
	}
	
	@Override
	//@Transactional(propagation=Propagation.REQUIRED)
	public UserEntity save(UserEntity userEntity) {
		 userDao.save(userEntity);
		 return userEntity;
	}
	@MyCacheable(keyGenerator="#name",name="user")
	@Override
	public UserEntity getByName(String name) {
		return userDao.getByName(name);
	}
}
