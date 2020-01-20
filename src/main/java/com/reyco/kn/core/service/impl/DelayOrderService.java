package com.reyco.kn.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.DelayQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reyco.kn.core.dao.OrderDao;
import com.reyco.kn.core.data.DelayedVo;
import com.reyco.kn.core.domain.OrderEntity;

/**
 * 延时订单....
 * @author reyco
 *
 */
@Service
public class DelayOrderService{
	
	/**
	 * 订单状态   1未支付 2已付款 3订单关闭 4订单完成
	 */
	/**
	 * 未支付
	 */
	private final static Integer UNPAID = 1;
	/**
	 * 订单关闭
	 */
	private final static Integer CLOSE = 3;
	
	@Autowired
	private OrderDao orderDao;
	
	private static DelayQueue<DelayedVo<OrderEntity>> delayQueue = new DelayQueue<DelayedVo<OrderEntity>>();
	/**
	 * 添加订单到DelayQueue
	 * @param orderEntity
	 * @param expireTime
	 */
	public void save(OrderEntity orderEntity,Long expireTime) {
		DelayedVo<OrderEntity> delayedVo = new DelayedVo<>(expireTime, orderEntity);
		delayQueue.put(delayedVo);
		System.out.println("订单【超时时间:"+expireTime+"毫秒】被推入延时队列,订单详情："+orderEntity);
	}
	/**
	 * 异步线程处理DelayQueue
	 * @author reyco
	 *
	 */
	class OrderTask implements Runnable{
		@Override
		public void run() {
			try {
				while(true) {
					DelayedVo<OrderEntity> delayedVo = delayQueue.take();
					OrderEntity orderEntity = (OrderEntity)delayedVo.getTarget();
					OrderEntity selOrderEntity = orderDao.get(orderEntity.getId());
					//判断数据库中订单是否未支付
					if(selOrderEntity.getState()==UNPAID) {
						selOrderEntity.setState(CLOSE);
						System.out.println("订单关闭:order="+selOrderEntity);
						orderDao.update(selOrderEntity);
					}else {
						System.out.println("订单已处理:orderEntity="+selOrderEntity);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 启动异步线程
	 */
	@PostConstruct
	public void init() {
		new Thread(new OrderTask() ).start();
	}
	/**
	 * 启动修改过期未支付订单为已关闭状态
	 * 启动扫描数据库中的订单未过期未支付到DelayQueue
	 */
	@PostConstruct
	public void initDelayOrder() {
		//1. 处理过期未支付的订单...
		Integer count = orderDao.updateExpire();
		System.out.println("系统启动，扫描处理【"+count+"】个过期未支付的订单...");
		
		//2. 获取未过期未支付的订单
		List<OrderEntity> orders = orderDao.listOrderNoExpire();
		System.out.println("系统启动,发现【"+orders.size()+"】个未过期未支付的订单...");
		//3. 未过期未支付的订单推入延时队列
		if(null!=orders && orders.size()>0) {
			for (OrderEntity order : orders) {
				long expireTime = order.getGmtExpire().getTime()-(new Date().getTime());
				DelayedVo<OrderEntity> delayedVo = new DelayedVo<>(expireTime, order);
				delayQueue.put(delayedVo);
				System.out.println("订单【超时时间:"+expireTime+"毫秒】被推入延时队列,订单详情："+order);
			}
		}
	}
}
