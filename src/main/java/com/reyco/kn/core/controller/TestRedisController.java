package com.reyco.kn.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.annotation.RateLimit;

@RestController
@RequestMapping("/api/redis")
public class TestRedisController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private Integer count = 0;
	
	@RequestMapping("/{phone}")
	public String redis(@PathVariable(name = "phone") String phone) {
		ValueOperations opsForValue = redisTemplate.opsForValue();
		opsForValue.set("name", "admin");
		Object object = opsForValue.get("name");
		System.out.println("object=" + object);
		return "" + object;
	}
	
	@RateLimit
	@GetMapping("/test")
	public String test() {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set("name", "admin");
		String name = opsForValue.get("name");
		this.count += 1;
		System.out.println("name=" + name+",count="+count);
		return "name=" + name;
	}
	@RateLimit
	@GetMapping("test1")
	public String test1() {
		this.count += 1;
		System.out.println("count="+count);
		return "count=" + count;
	}
}
