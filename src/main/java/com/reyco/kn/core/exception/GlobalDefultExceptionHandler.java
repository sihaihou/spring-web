package com.reyco.kn.core.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.reyco.kn.core.utils.R;

@RestControllerAdvice
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
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public String defultExcepitonHandler(HttpServletRequest reuqest, HttpServletResponse response, Exception ex) {
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
	private String getIllegalArgumentException(Exception ex) {
		logger.error("非法参数异常：" + ex.getMessage());
		IllegalArgumentException illegalArgumentException = (IllegalArgumentException) ex;
		return R.errorToJson("未知异常,请联系管理员...", "非法参数异常：" + "msg=" + illegalArgumentException.getMessage());
	}
	/**
	 * 空指针异常
	 * @param ex
	 * @return
	 */
	private String getNullPointerException(Exception ex) {
		logger.error("空指针异常：" + ex.getMessage());
		NullPointerException nullPointerException = (NullPointerException) ex;
		return R.errorToJson("未知异常,请联系管理员...", "空指针异常：" + "msg=" + nullPointerException.getMessage());
	}
	/**
	 * SQL语句异常
	 * @param ex
	 * @return
	 */
	private String getSQLException(Exception ex) {
		logger.error("SQL语句异常：" + ex.getMessage());
		SQLException qQLException = (SQLException) ex;
		return R.errorToJson("未知异常,请联系管理员...", "SQL语句异常：" + "msg=" + qQLException.getMessage());
	}
	/**
	 * 数字转换异常
	 * @param ex
	 * @return
	 */
	private String getNumberFormatException(Exception ex) {
		logger.error("数字转换异常：" + ex.getMessage());
		NumberFormatException numberFormatException = (NumberFormatException) ex;
		return R.errorToJson("未知异常,请联系管理员...","数字转换异常：" + "msg=" + numberFormatException.getMessage());
	}
	/**
	 * 数组溢出异常
	 * @param ex
	 * @return
	 */
	private String getArrayIndexOutOfBoundsException(Exception ex) {
		logger.error("数组溢出异常：" + ex.getMessage());
		ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = (ArrayIndexOutOfBoundsException) ex;
		return R.errorToJson("未知异常,请联系管理员...", "数组溢出异常：" + "msg=" + arrayIndexOutOfBoundsException.getMessage());
	}
	/**
	 * IO输入输出异常
	 * @param ex
	 * @return
	 */
	private String getIOException(Exception ex) {
		logger.error("IO输入输出异常：" + ex.getMessage());
		IOException iOException = (IOException) ex;
		return R.errorToJson("未知异常,请联系管理员...", "IO输入输出异常：" + "msg=" + iOException.getMessage());
	}
	/**
	 * 系统异常
	 * @param ex
	 * @return
	 */
	private String getException(Exception ex) {
		logger.error("系统异常：" + ex);
		return R.errorToJson("未知异常,请联系管理员...", "系统异常：msg=" + ex.getMessage());
	}
	
}
