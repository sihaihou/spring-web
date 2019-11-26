package com.reyco.kn.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.domain.Result;
import com.reyco.kn.core.domain.UserEntity;

@RestController
@RequestMapping("/api")
public class SwaggerController {
	final static List<UserEntity> users = new ArrayList<UserEntity>();

	@GetMapping("/user/list")
	public Result list(@RequestParam Map<String, String> map) {
		List<UserEntity> selUsers = new ArrayList<UserEntity>();
		if (!map.containsKey("pageNo")) {
			return Result.fail("pageNo not is null...");
		}
		String pageNoStr = map.get("pageNo");
		Integer pageNo = Integer.parseInt(pageNoStr);
		Integer pageSize = 0;
		if (!map.containsKey("pageSize")) {
			pageSize = 5;
		} else {
			String pageSizeStr = map.get("pageSize");
			pageSize = Integer.parseInt(pageSizeStr);
		}
		for (int i = (pageNo - 1) * pageSize; i < pageNo * pageSize; i++) {
			if (i < users.size()) {
				selUsers.add(users.get(i));
			}
		}
		Result result = Result.success(selUsers);
		return result;
	}

	@GetMapping("/user/info/{id}")
	public Result list(@PathVariable(name = "id") Integer id) {
		if (null == id) {
			return Result.fail("id not is null...");
		}
		if (id > users.size() - 1) {
			return Result.success(null);
		}
		UserEntity userEntity = users.get(id - 1);
		return Result.success(userEntity);
	}

	@PostMapping("/user/save")
	public Result list(@RequestBody UserEntity userEntity) {
		if (null == userEntity || null == userEntity.getId() || StringUtils.isBlank(userEntity.getUsername())) {
			return Result.fail("参数错误,请联系管理员...");
		}
		users.add(userEntity);
		return Result.success();
	}

	@PostMapping("/user/update")
	public Result update(@RequestBody UserEntity userEntity) {
		if (null == userEntity || null == userEntity.getId() || StringUtils.isBlank(userEntity.getUsername())) {
			return Result.fail("参数错误,请联系管理员...");
		}
		if (userEntity.getId() - 1 > users.size()) {
			return Result.fail("用户不存在");
		}
		users.remove(userEntity.getId() - 1);
		users.add(userEntity);
		return Result.success();
	}

	@DeleteMapping("/user/delete/{id}")
	public Result update(@PathVariable(name = "id") Integer id) {
		if (null == id) {
			return Result.fail("id not is null...");
		}
		if (id > users.size()) {
			return Result.success(null);
		}
		users.remove(id - 1);
		return Result.success();
	}

	@PostConstruct
	private static List<UserEntity> getUsers() {
		UserEntity userEntity = null;
		for (int i = 1; i < 11; i++) {
			String username = "user" + i;
			userEntity = new UserEntity();
			userEntity.setId(i);
			userEntity.setUsername(username);
			userEntity.setPassword("123456");
			userEntity.setGmtCreate("2019-11+1" + i + " 14:00:00");
			users.add(userEntity);
		}
		return users;
	}

}
