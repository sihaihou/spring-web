package com.reyco.kn.core.dao;

import java.util.List;

import com.reyco.kn.core.domain.TestDO;
import com.reyco.kn.core.domain.TestQuery;

public interface TestDao {
	
	TestDO get(TestQuery testQuery) ;

	List<TestDO> list(TestQuery testQuery);
	
	void update(TestDO testDO);

	void save(TestDO testDO);
}
