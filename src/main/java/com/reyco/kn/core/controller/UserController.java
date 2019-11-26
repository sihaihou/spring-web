package com.reyco.kn.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.domain.Result;
import com.reyco.kn.core.domain.UserEntity;
import com.reyco.kn.core.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("get/{id}")
	public Result get(@PathVariable(name="id")Integer id) {
		Result result = new Result();
		UserEntity userEntity = userService.get(id);
		if(null==userEntity) {
			result.setSuccess(true);
			result.setMsg("暂无数据");
			result.setData(null);
			return result;
		}
		result.setSuccess(true);
		result.setData(userEntity);
		return result;
	}
	
	@RequestMapping("update")
	public UserEntity update(@RequestBody UserEntity userEntity) {
		UserEntity UpdateUserEntity = userService.update(userEntity);
		return UpdateUserEntity;
	}
	
	@RequestMapping("list")
	public List<UserEntity> list(@RequestParam Map<String,Object> map) {
		List<UserEntity> userEntitys = userService.list(map);
		return userEntitys;
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable(name="id")Integer id) {
		userService.delete(id);
		return "删除成功";
	}
	
	@RequestMapping("save")
	public Result save(@RequestBody UserEntity userEntity) {
		Result result = new Result();
		UserEntity save = userService.save(userEntity);
		if(null==userEntity) {
			result.setSuccess(true);
			result.setMsg("暂无数据");
			result.setData(null);
			return result;
		}
		result.setSuccess(true);
		result.setData(userEntity);
		return result;
	}
	
	
	
}
