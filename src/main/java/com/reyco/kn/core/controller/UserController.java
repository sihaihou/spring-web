package com.reyco.kn.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.domain.UserEntity;
import com.reyco.kn.core.service.UserService;
import com.reyco.kn.core.utils.R;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("get/{id}")
	public String get(@PathVariable(name="id")Integer id) {
		UserEntity userEntity = userService.get(id);
		if(null==userEntity) {
			return R.noData().toJSON();
		}
		return R.successToJson(userEntity);
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
	public String save(@RequestBody UserEntity userEntity) {
		UserEntity save = userService.save(userEntity);
		if(null==save) {
			return R.noData().toJSON();
		}
		return R.successToJson(userEntity);
	}
	
	
	
}
