package com.reyco.kn.core.dao;

import java.util.List;

import com.reyco.kn.core.domain.OrderEntity;

public interface OrderDao{
	
	OrderEntity get(Integer id);
	
	List<OrderEntity> listOrderNoExpire();
	
	void update(OrderEntity orderEntity);
	
	Integer updateExpire();
	
	void save(OrderEntity orderEntity);	
	
	
	
}
