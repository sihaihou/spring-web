package com.reyco.kn.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.reyco.kn.core.KNApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=KNApplication.class)
public class ReycoApplicationTests {
	
	@Autowired
	ApplicationContext a;
	
	@Test
	public void test() {
		Object bean = a.getBean("intStansA");
	}
	
}
