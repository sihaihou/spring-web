package com.reyco.kn.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.reyco.kn.core.utils.R;
/**
 * 自定义异常处理实现2：
 * 			业务异常：
 * 			Component("business")的value必须和MyException的属性type一致
 * @author reyco
 *
 */
@Component("business")
public class BusinessExceptionStrategy implements ExceptionStrategy {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public String getExceptionMsg(Exception ex) {
		BusinessException businessException = (BusinessException) ex;
		logger.error("业务异常：" + businessException.getMsg());
		return R.errorToJson("业务异常："  + "msg=" + businessException.getMsg(), "业务异常,code=" + businessException.getCode() + ",msg=" + businessException.getMsg());
	}

}
