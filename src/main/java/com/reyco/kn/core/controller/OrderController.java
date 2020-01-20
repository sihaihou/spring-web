package com.reyco.kn.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.domain.OrderEntity;
import com.reyco.kn.core.service.OrderService;

@RequestMapping("/api/order")
@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("save")
	public String save(@RequestBody OrderEntity orderEntity) {
		orderService.save(orderEntity);
		return "ok";
	}
	
}
