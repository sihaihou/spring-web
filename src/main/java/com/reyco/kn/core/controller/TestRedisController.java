package com.reyco.kn.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRedisController {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@RequestMapping(value = "redis/{phone}")
	public String redis(@PathVariable(name="phone") String phone) {
		ValueOperations opsForValue = redisTemplate.opsForValue();
		opsForValue.set("name", "admin");
		Object object = opsForValue.get("name");
		System.out.println(""+object);
		return ""+object;
	}
	
	@RequestMapping(value = "/test/redis")
	public String redis() {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set("name", "admin");
		String name = opsForValue.get("name");
		System.out.println("name="+name);
		return "name="+name;
	}
}
