package com.reyco.kn.core.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.reyco.kn.core.domain.UserEntity;
import com.reyco.kn.core.utils.R;

@RestController
@RequestMapping("/api")
public class SwaggerController {
	final static List<UserEntity> users = new ArrayList<UserEntity>();

	@GetMapping("/user/list")
	public String list(@RequestParam Map<String, String> map) {
		List<UserEntity> selUsers = new ArrayList<UserEntity>();
		if (!map.containsKey("pageNo")) {
			return R.failToJson("pageNo not is null...");
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
		return R.successToJson(selUsers);
	}

	@GetMapping("/user/info/{id}")
	public String list(@PathVariable(name = "id") Integer id) {
		if (null == id) {
			return R.failToJson("id not is null...");
		}
		if (id > users.size() - 1) {
			return R.failToJson(null);
		}
		UserEntity userEntity = users.get(id - 1);
		return R.successToJson(userEntity);
	}

	@PostMapping("/user/save")
	public String list(@RequestBody UserEntity userEntity) {
		if (null == userEntity || null == userEntity.getId() || StringUtils.isBlank(userEntity.getUsername())) {
			return R.failToJson("参数错误,请联系管理员...");
		}
		users.add(userEntity);
		return R.successToJson();
	}

	@PostMapping("/user/update")
	public String update(@RequestBody UserEntity userEntity) {
		if (null == userEntity || null == userEntity.getId() || StringUtils.isBlank(userEntity.getUsername())) {
			return R.failToJson("参数错误,请联系管理员...");
		}
		if (userEntity.getId() - 1 > users.size()) {
			return R.failToJson("用户不存在");
		}
		users.remove(userEntity.getId() - 1);
		users.add(userEntity);
		return R.successToJson();
	}

	@DeleteMapping("/user/delete/{id}")
	public String update(@PathVariable(name = "id") Integer id) {
		if (null == id) {
			return R.failToJson("id not is null...");
		}
		if (id > users.size()) {
			return R.successToJson(null);
		}
		users.remove(id - 1);
		return R.successToJson();
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
			userEntity.setGmtCreate(new Date());
			users.add(userEntity);
		}
		return users;
	}

}
