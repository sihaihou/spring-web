package com.reyco.kn.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.exception.ArgumentException;
import com.reyco.kn.core.exception.BusinessException;

@RestController
@RequestMapping("/api")
public class ErrorController {

	@RequestMapping("error1")
	public String error1() {
		int a = 1/0;
		return "error1";
	}
	
	@RequestMapping("error2")
	public String error2() {
		String a = null;
		a.length();
		return "error2";
	}
	
	@RequestMapping("error3")
	public String error3() {
		String[] a = {"1"};
		String b = a[1];
		return "error3";
	}
	@RequestMapping("error4")
	public String error4() throws BusinessException {
		throw new BusinessException("xx异常");
	}
	@RequestMapping("error5")
	public String error5() throws ArgumentException {
		throw new ArgumentException();
	}
	@RequestMapping("error6")
	public String error6(Integer a) {
		return "error6";
	}
	@RequestMapping("error7")
	public String error7() {
		String a = "adwad ";
		Integer.parseInt(a);
		return "error7";
	}
}
