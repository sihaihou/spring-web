package com.reyco.kn.core.service;

import java.util.List;

import com.reyco.kn.core.domain.OrderEntity;

public interface OrderService{
	
	OrderEntity get(Integer id);
	
	List<OrderEntity> listOrderNoExpire();
	
	void updateExpire();
	
	void save(OrderEntity orderEntity);	
	
}
