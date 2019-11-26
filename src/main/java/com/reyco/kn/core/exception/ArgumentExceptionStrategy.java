package com.reyco.kn.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.reyco.kn.core.domain.Result;

/**
 * 自定义异常处理实现1：
 * 			参数错误异常：
 * 			Component("argument")的value必须和MyException的属性type一致
 * @author reyco
 */
@Component("argument")
public class ArgumentExceptionStrategy implements ExceptionStrategy {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Result getExceptionMsg(Exception ex) {
		Result result = new Result();
		ArgumentException argumentException = (ArgumentException) ex;
		result.setSuccess(false);
		result.setMsg("参数错误异常,"+ "msg=" + argumentException.getMsg());
		logger.error("参数错误异常：" + argumentException.getMsg());
		return result;
	}

}
