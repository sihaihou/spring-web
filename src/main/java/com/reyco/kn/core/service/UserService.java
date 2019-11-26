package com.reyco.kn.core.service;

import com.reyco.kn.core.domain.UserEntity;

public interface UserService extends BaseService<UserEntity>{
	
	UserEntity getByName(String name);
	
}
