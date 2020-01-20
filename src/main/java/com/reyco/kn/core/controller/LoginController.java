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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.annotation.RateLimit;
import com.reyco.kn.core.domain.Captcha;
import com.reyco.kn.core.domain.Captcha.CaptchaBuilder;
import com.reyco.kn.core.domain.LoginLog;
import com.reyco.kn.core.domain.LoginLog.LoginLigBuilder;
import com.reyco.kn.core.domain.User;
import com.reyco.kn.core.domain.UserEntity;
import com.reyco.kn.core.service.UserService;
import com.reyco.kn.core.utils.CacheUtils;
import com.reyco.kn.core.utils.CaptchaUtils;
import com.reyco.kn.core.utils.CookieUtil;
import com.reyco.kn.core.utils.R;

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
	public String getCaptcha(HttpServletRequest request,HttpServletResponse response) {
		// 1.生成验证码
		logger.info("生成验证码");
		String code = CaptchaUtils.createCode();
		String key = UUID.randomUUID().toString().replace("-", "");
		// 2.验证码放入缓存
		CacheUtils.put(key, code,60L);
		// 3.验证码设置cookie
		CookieUtil.setCookie(request, response, "Kn_captcha", key, -1);
		// 4.验证码返回给前端
		Captcha captcha = new CaptchaBuilder().builderKey(key).builderValue(code).builder();
		return R.successToJson(captcha);
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
	//@RateLimit
	@RequestMapping("/login")
	public String login(String username,String password,String captcha,HttpServletRequest request,HttpServletResponse response)throws Exception {
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)|| StringUtils.isBlank(captcha) ) {
			logger.info("参数错误");
			return R.failToJson("参数错误...","username"+username+",password"+password+",captcha"+captcha);
		}
		UserEntity userEntity = userService.getByName(username);
		if(null==userEntity) {
			logger.info("用户名或密码错误。。。");
			return R.failToJson("用户名或密码错误...","用户名不存在");
		}
		if(!username.equals(userEntity.getName())) {
			logger.info("用户名或密码错误。。。");
			return R.failToJson("用户名或密码错误...","用户名错误");
		}
		if(!password.equals(userEntity.getPassword())) {
			logger.info("用户名或密码错误。。。");
			return R.failToJson("用户名或密码错误...","密码错误");
		}
		String captchaCookie = CookieUtil.getCookieByName(request, "Kn_captcha");
		if(StringUtils.isBlank(captchaCookie)) {
			logger.info("没有验证码");
			return R.failToJson("登录失败","没有验证码...");
		}
		Object selCaptcha = CacheUtils.get(captchaCookie);
		CacheUtils.remove(captchaCookie);
		if(null == selCaptcha ) {
			logger.info("验证码失效");
			return R.failToJson("验证码失效...","缓存验证码过期...");
		}
		if(!captcha.equalsIgnoreCase(selCaptcha.toString())) {
			logger.info("验证码错误");
			return R.failToJson("验证码错误...","验证码错误...");
		}
		User user = new User.UserBuilder()
				.builderId(userEntity.getId())
				.builderUsername(userEntity.getName())
				.builder();
		// 创建 token
		String key = UUID.randomUUID().toString().replace("-", "");
		CacheUtils.put(key, user);
		// 设置cookie
		CookieUtil.setCookie(request, response, "kn_token", key, -1);
		// 设置cookie
		LoginLog loginLog = new LoginLigBuilder().builder();
		CookieUtil.setCookie(request, response, "kn_token", key, -1);
		applicationContext.publishEvent(loginLog);
		//返回数据
		return R.successToJson(user,"登录成功");
	}
	
	@GetMapping("/checkUser")
	public String checkLogin(HttpServletRequest request) {
		String cookie = CookieUtil.getCookieByName(request, "kn_token");
		Object object = CacheUtils.get(cookie);
		return R.successToJson(object);
	}
	
}
