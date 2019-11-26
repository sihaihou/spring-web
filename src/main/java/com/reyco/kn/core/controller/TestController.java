package com.reyco.kn.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.domain.Account;
import com.reyco.kn.core.service.impl.LoginService;
import com.reyco.kn.core.utils.CusAccessObjectUtil;
import com.reyco.kn.core.utils.IPDataUtils;

@RestController
@RequestMapping("/api")
public class TestController {

	//@Autowired
	//private TestService testService;
	
	@Autowired
	private LoginService loginService;
	
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
	public String test1(@RequestBody Account account) {
		System.out.println("------account-------" + account);
		return "" + account;
	}
	
	@RequestMapping("/test/getIp")
	public String getIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String ipAddress = CusAccessObjectUtil.getIpAddress(request);
		//ipAddress="182.61.13.51";
		String city = IPDataUtils.getCity(ipAddress);
		return ipAddress+"\t"+city;
	}
	
}
