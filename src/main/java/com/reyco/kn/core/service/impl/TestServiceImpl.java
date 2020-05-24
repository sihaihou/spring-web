package com.reyco.kn.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reyco.kn.core.dao.TestDao;
import com.reyco.kn.core.domain.TestDO;
import com.reyco.kn.core.domain.TestDTO;
import com.reyco.kn.core.domain.TestQuery;
import com.reyco.kn.core.service.TestService;

import ma.glasnost.orika.MapperFacade;


@Service("testService")
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDao testDao;
	@Autowired
	private MapperFacade mapper;
	
	@Override
	public TestDTO get(TestQuery testQuery) {
		TestDO testDO = testDao.get(testQuery);
		TestDTO testDTO = mapper.map(testDO, TestDTO.class);
		return testDTO;
	}

	@Override
	public List<TestDTO> list(TestQuery testQuery) {
		List<TestDO> listTestDO = testDao.list(testQuery);
		List<TestDTO> listTestDTO = mapper.mapAsList(listTestDO, TestDTO.class);
		return listTestDTO;
	}

}
