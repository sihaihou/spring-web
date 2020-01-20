package com.reyco.kn.core.service;

import java.util.List;

import com.reyco.kn.core.domain.TestDTO;
import com.reyco.kn.core.domain.TestQuery;

public interface TestService {

	TestDTO get(TestQuery testQuery);
	
	List<TestDTO> list(TestQuery testQuery);
	
	
}
