package com.reyco.kn.core.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.annotation.RateLimit;
import com.reyco.kn.core.domain.Captcha;
import com.reyco.kn.core.domain.LoginLog;
import com.reyco.kn.core.domain.Result;
import com.reyco.kn.core.domain.User;
import com.reyco.kn.core.domain.UserEntity;
import com.reyco.kn.core.service.UserService;
import com.reyco.kn.core.utils.CacheUtils;
import com.reyco.kn.core.utils.CaptchaUtils;
import com.reyco.kn.core.utils.CookieUtil;

@RestController
@RequestMapping("/api")
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @return
	 */
	@RateLimit
	@GetMapping("/captcha")
	public Result getCaptcha(HttpServletRequest request,HttpServletResponse response) {
		// 1.生成验证码
		logger.info("生成验证码");
		String code = CaptchaUtils.createCode();
		String key = UUID.randomUUID().toString().replace("-", "");
		// 2.验证码放入缓存
		CacheUtils.put(key, code,60L);
		// 3.验证码设置cookie
		CookieUtil.setCookie(request, response, "Kn_captcha", key, -1);
		// 4.验证码返回给前端
		Captcha captcha = new Captcha(key,code);
		return Result.success(captcha);
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param captcha
	 * @param request
	 * @param response
	 * @return
	 */
	@RateLimit
	@PostMapping("/login")
	public Result Login(String username,String password,String captcha,HttpServletRequest request,HttpServletResponse response) {
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)|| StringUtils.isBlank(captcha) ) {
			logger.info("参数错误");
			return Result.fail("参数错误...");
		}
		UserEntity userEntity = userService.getByName(username);
		if(!username.equals(userEntity.getName()) || !password.equals(userEntity.getPassword())) {
			logger.info("用户名或密码错误。。。");
			return Result.fail("用户名或密码错误...");
		}
		String captchaCookie = CookieUtil.getCookieByName(request, "Kn_captcha");
		if(StringUtils.isBlank(captchaCookie)) {
			logger.info("没有验证码");
			return Result.fail("没有验证码...");
		}
		Object selCaptcha = CacheUtils.get(captchaCookie);
		CacheUtils.remove(captchaCookie);
		if(null == selCaptcha ) {
			logger.info("验证码失效");
			return Result.fail("验证码失效...");
		}
		if(!captcha.equalsIgnoreCase(selCaptcha.toString())) {
			logger.info("验证码错误");
			return Result.fail("验证码错误...");
		}
		User user = new User();
		user.setId(userEntity.getId());
		user.setUsername(userEntity.getName());
		// 创建 token
		String key = UUID.randomUUID().toString().replace("-", "");
		CacheUtils.put(key, user);
		// 设置cookie
		CookieUtil.setCookie(request, response, "kn_token", key, -1);
		// 设置cookie
		LoginLog loginLog = new LoginLog();
		applicationContext.publishEvent(loginLog);
		CookieUtil.setCookie(request, response, "kn_token", key, -1);
		applicationContext.publishEvent(userEntity);
		//返回数据
		return Result.success(user);
	}
	
	@GetMapping("/checkUser")
	public Result checkLogin(HttpServletRequest request) {
		String cookie = CookieUtil.getCookieByName(request, "kn_token");
		Object object = CacheUtils.get(cookie);
		return Result.success(object);
	}
	
}
