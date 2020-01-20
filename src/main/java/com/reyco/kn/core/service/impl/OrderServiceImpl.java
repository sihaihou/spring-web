package com.reyco.kn.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reyco.kn.core.dao.OrderDao;
import com.reyco.kn.core.domain.OrderEntity;
import com.reyco.kn.core.domain.OrderEntity.OrderEnitiyBuilder;
import com.reyco.kn.core.service.OrderService;
import com.reyco.kn.core.utils.SnowFlake;
/**
 * 订单Service
 * @author reyco
 *
 */
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderedDao;
	
	@Autowired
	private DelayOrderService delayOrderService;
	
	@Override
	public OrderEntity get(Integer id) {
		return orderedDao.get(id);
	}

	@Override
	public List<OrderEntity> listOrderNoExpire() {
		return orderedDao.listOrderNoExpire();
	}

	@Override
	public void updateExpire() {
		orderedDao.updateExpire();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(OrderEntity orderEntity) {
		//订单号
		Long no = new SnowFlake(2,3).nextId();
		//超时时长
		long expireTime = 1000 * 60 * 1;
		Date gmtExpire = new Date();
		gmtExpire.setTime(System.currentTimeMillis() + expireTime);
		
		orderEntity = new OrderEnitiyBuilder()
				.builderNo(no.toString())
				.builderContent("500块钱的羽绒服。。。")
				.builderState(1)
				.builderGmtExpire(gmtExpire)
				.builderGmtDesc("备注")
				.builder();
		// 保存到数据库
		orderedDao.save(orderEntity);
		// 
		delayOrderService.save(orderEntity, expireTime);
	}

}
