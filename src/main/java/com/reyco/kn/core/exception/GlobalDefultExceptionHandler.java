package com.reyco.kn.core.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reyco.kn.core.domain.Result;

@ControllerAdvice
public class GlobalDefultExceptionHandler {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExceptionStrategyContext exceptionStrategyContext;
	/**
	 * 声明要捕获的异常
	 * 
	 * @param reuqest
	 * @param response
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result defultExcepitonHandler(HttpServletRequest reuqest, HttpServletResponse response, Exception ex) {
		ex.printStackTrace();
		try {
			// 自定义异常...
			if(ex instanceof MyException) {
				MyException myException = (MyException)ex; 
				return exceptionStrategyContext.getExceptionMsg(myException.getType(), ex);
			}
			// 数组溢出异常
			if(ex instanceof ArrayIndexOutOfBoundsException) {
				return getArrayIndexOutOfBoundsException(ex);
			}
			// 数字转换异常
			if(ex instanceof NumberFormatException) {
				return getNumberFormatException(ex);
			}
			// 非法参数
			if(ex instanceof IllegalArgumentException) {
				return getIllegalArgumentException(ex);
			}
			// 空指针异常
			if(ex instanceof NullPointerException) {
				return getNullPointerException(ex);
			}
			// SQL语句异常
			if(ex instanceof SQLException) {
				return getSQLException(ex);
			}
			// IO输入输出异常
			if(ex instanceof IOException) {
				return getIOException(ex);
			}
			return getException(ex);
		} catch (Exception e) {
			return getException(e);
		}
	}
	/**
	 * 非法参数异常
	 * @param ex
	 * @return
	 */
	private Result getIllegalArgumentException(Exception ex) {
		Result result = new Result();
		logger.error("非法参数异常：" + ex.getMessage());
		IllegalArgumentException illegalArgumentException = (IllegalArgumentException) ex;
		result.setSuccess(false);
		result.setMsg("非法参数异常：" + "msg=" + illegalArgumentException.getMessage());
		return result;
	}
	/**
	 * 空指针异常
	 * @param ex
	 * @return
	 */
	private Result getNullPointerException(Exception ex) {
		Result result = new Result();
		logger.error("空指针异常：" + ex.getMessage());
		NullPointerException nullPointerException = (NullPointerException) ex;
		result.setSuccess(false);
		result.setMsg("空指针异常：" + "msg=" + nullPointerException.getMessage());
		return result;
	}
	/**
	 * SQL语句异常
	 * @param ex
	 * @return
	 */
	private Result getSQLException(Exception ex) {
		Result result = new Result();
		logger.error("SQL语句异常：" + ex.getMessage());
		SQLException qQLException = (SQLException) ex;
		result.setSuccess(false);
		result.setMsg("SQL语句异常：" + "msg=" + qQLException.getMessage());
		return result;
	}
	/**
	 * 数字转换异常
	 * @param ex
	 * @return
	 */
	private Result getNumberFormatException(Exception ex) {
		Result result = new Result();
		logger.error("数字转换异常：" + ex.getMessage());
		NumberFormatException numberFormatException = (NumberFormatException) ex;
		result.setSuccess(false);
		result.setMsg("数字转换异常：" + "msg=" + numberFormatException.getMessage());
		return result;
	}
	/**
	 * 数组溢出异常
	 * @param ex
	 * @return
	 */
	private Result getArrayIndexOutOfBoundsException(Exception ex) {
		Result result = new Result();
		logger.error("数组溢出异常：" + ex.getMessage());
		ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = (ArrayIndexOutOfBoundsException) ex;
		result.setSuccess(false);
		result.setMsg("数组溢出异常：" + "msg=" + arrayIndexOutOfBoundsException.getMessage());
		return result;
	}
	/**
	 * IO输入输出异常
	 * @param ex
	 * @return
	 */
	private Result getIOException(Exception ex) {
		Result result = new Result();
		logger.error("IO输入输出异常：" + ex.getMessage());
		IOException iOException = (IOException) ex;
		result.setSuccess(false);
		result.setMsg("IO输入输出异常：" + "msg=" + iOException.getMessage());
		return result;
	}
	/**
	 * 系统异常
	 * @param ex
	 * @return
	 */
	private Result getException(Exception ex) {
		Result result = new Result();
		logger.error("系统异常：" + ex);
		result.setSuccess(false);
		result.setMsg("系统异常：msg=" + ex.getMessage());
		return result;
	}
	
}
