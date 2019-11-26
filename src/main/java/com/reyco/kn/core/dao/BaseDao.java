package com.reyco.kn.core.dao;

import java.util.List;
import java.util.Map;

import com.reyco.kn.core.domain.BaseEntity;
import com.reyco.kn.core.domain.UserEntity;

public interface BaseDao<T extends BaseEntity> {
	
	T get(Integer id);
	
	List<T> list(Map<String,Object> map);
	
	void update(UserEntity userEntity);
	
	void save(UserEntity userEntity);
}
