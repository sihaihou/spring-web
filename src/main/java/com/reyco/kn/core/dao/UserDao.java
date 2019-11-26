package com.reyco.kn.core.dao;

import com.reyco.kn.core.domain.UserEntity;

public interface UserDao extends BaseDao<UserEntity>{

	UserEntity getByName(String name);
}
