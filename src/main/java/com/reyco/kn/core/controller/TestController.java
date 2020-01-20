package com.reyco.kn.core.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.domain.TestDTO;
import com.reyco.kn.core.domain.TestQuery;
import com.reyco.kn.core.domain.TestVO;
import com.reyco.kn.core.domain.UserEntity;
import com.reyco.kn.core.service.Template;
import com.reyco.kn.core.service.TestService;
import com.reyco.kn.core.service.UserService;
import com.reyco.kn.core.service.impl.LoginService;
import com.reyco.kn.core.utils.CusAccessObjectUtil;
import com.reyco.kn.core.utils.DeleteTemplate;
import com.reyco.kn.core.utils.GetInfoTemplate;
import com.reyco.kn.core.utils.IPDataUtils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

@RestController
@RequestMapping("/api")
public class TestController {

	//@Autowired
	//private TestService testService;
	
	@Autowired
	private LoginService loginService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private MapperFacade mapper;
	
	
	@RequestMapping(value = "/test/event", method = RequestMethod.GET)
	public String event() throws InterruptedException {
		loginService.login();
		Thread.sleep(5000);
		return "aaa";
	}
		
	//@RequestMapping(value = "test", method = RequestMethod.GET)
	//public String test() {
		//String test = testService.test("-----");
		//return test;
	//}

	@RequestMapping(value = "/test/test1", method = RequestMethod.GET)
	public String test1(@RequestBody UserEntity userEntity) {
		System.out.println("------userEntity-------" + userEntity);
		return "" + userEntity;
	}
	
	@RequestMapping("/test/getIp")
	public String getIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String ipAddress = CusAccessObjectUtil.getIpAddress(request);
		//ipAddress="182.61.13.51";
		String city = IPDataUtils.getCity(ipAddress);
		return ipAddress+"\t"+city;
	}
	
	@GetMapping("/test/get")
	public Object r(@RequestParam Map<String,String> map) throws Exception {
		Template temp =  new GetInfoTemplate() {
			@Override
			protected Object get() {
				String idStr = map.get("userId");
				return userService.get(Integer.parseInt(idStr));
			}
		};
		return temp.handler(map);
	}
	
	@GetMapping("/test/delete")
	public Object delete(@RequestParam Map<String,String> map) throws Exception {
		Template  temp= new DeleteTemplate() {
			@Override
			protected void delete() {
				String idStr = map.get("userId");
				userService.delete(Integer.parseInt(idStr));
			}
		};
		return temp.handler(map);
	
	}
	
	
	@GetMapping("/test/orika/get/{testId}")
	public TestVO get(@PathVariable("testId") Integer id) {
		TestQuery testQuery = new TestQuery();
		testQuery.setId(id);
		TestDTO testDTO = testService.get(testQuery);
		TestVO testVO = mapper.map(testDTO, TestVO.class);
		return testVO;
	}
	
	@GetMapping("/test/orika/list")
	public List<TestVO> list(TestQuery testQuery) {
		List<TestDTO> listTestDTO = testService.list(testQuery);
		List<TestVO> listTestVO = mapper.mapAsList(listTestDTO, TestVO.class);
		return listTestVO;
	}
	
}
