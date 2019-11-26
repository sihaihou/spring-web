package com.reyco.kn.core.service;

import java.util.List;
import java.util.Map;

import com.reyco.kn.core.domain.BaseEntity;

public interface BaseService<T extends BaseEntity> {

	T get(Integer id);
	
	List<T> list(Map<String,Object> map); 
	
	T update(T t);
	
	void delete(Integer id);
	
	T save(T t);
}
