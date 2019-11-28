package com.reyco.kn.core.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * 响应工具类
 * @author reyco
 *
 */
public class R implements Serializable{
	
	private static final long serialVersionUID = 5698278545387629199L;
	
	public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE = "yyyy-MM-dd";
	public final static String TIME = "HH:mm:ss";
	
	private final static Gson GSON_DATE_TIME = new GsonBuilder().setDateFormat(DATE_TIME).create();
	
	private final static Gson GSON_DATE = new GsonBuilder().setDateFormat(DATE).create();
	
	private final static Gson GSON_TIME = new GsonBuilder().setDateFormat(TIME).create();

	
	public final static String SAVE_SUCCESS = "提交成功";
	public final static String SAVE_FAIL = "提交失败";
	
	public final static String REQUEST_SUCCESS = "请求成功";
	public final static String REQUEST_FAIL = "请求失败";
	
	public final static String PARAM_ERROR = "参数错误";
	
	public final static String ERROR_MSG = "未知异常,请联系管理员...";
	/**
	 * 成功或失败
	 */
	private Boolean success;
	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 数据
	 */
	private Map<String,Object> data;
	/**
	 * 失败原因/后端看的
	 */
	private String msg;
	
	public R() {
		this.data = new HashMap<>();
	}
	/**
	 *  如果有时间
	 * @return
	 */
	public String toJSON() {
		return toJSON(DATE_TIME);
	}
	public String toJSON(String dateFormat) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", this.success);
		jsonObject.addProperty("code",this.code);
		Gson gson = new GsonBuilder().setDateFormat(dateFormat).create();
		jsonObject.add("data", new JsonParser().parse(gson.toJson(this.data)));
		jsonObject.add("msg", new JsonParser().parse(GSON_DATE_TIME.toJson(this.msg)));
		return jsonObject.toString();
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @return
	 */
	public static String successToJson() {
		return successToJson(new Object(),REQUEST_SUCCESS);
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @return
	 */
	public static String successToJson(Object data) {
		return successToJson(data,REQUEST_SUCCESS);
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @param dataMsg  前端提示信息
	 * @return
	 */
	public static String successToJson(Object data,String dataMsg) {
		return successToJson(data,dataMsg,REQUEST_SUCCESS);
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @param dataMsg  前端提示信息
	 * @param msg      后端错误信息
	 * @return
	 */
	public static String successToJson(Object data,String dataMsg,String msg) {
		return success(200,data,dataMsg,msg).toJSON();
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @param dataMsg  前端提示信息
	 * @return
	 */
	public static String successDateFormatToJson(Object data,String dateFormat) {
		return successDateFormatToJson(data,REQUEST_SUCCESS,dateFormat);
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @param dataMsg  前端提示信息
	 * @return
	 */
	public static String successDateFormatToJson(Object data,String dataMsg,String dateFormat) {
		return successDateFormatToJson(data,dataMsg,REQUEST_SUCCESS,dateFormat);
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @param dataMsg  前端提示信息
	 * @param msg      后端错误信息
	 * @return
	 */
	public static String successDateFormatToJson(Object data,String dataMsg,String msg,String dateFormat) {
		return success(200,data,dataMsg,msg).toJSON(dateFormat);
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @return
	 */
	public static R success(Object data) {
		return success(data,REQUEST_SUCCESS);
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @param dataMsg  前端提示信息
	 * @return
	 */
	public static R success(Object data,String dataMsg) {
		return success(data,dataMsg,REQUEST_SUCCESS);
	}
	/**
	 * 请求成功
	 * @param data     数据 
	 * @param dataMsg  前端提示信息
	 * @param msg      后端错误信息
	 * @return
	 */
	public static R success(Object data,String dataMsg,String msg) {
		return success(200,data,dataMsg,msg);
	}
	/**
	 * 请求成功
	 * @param code       成功状态码  
	 * @param data       数据    
	 * @param dataMsg    前端提示信息
	 * @param msg        后端错误信息
	 * @return
	 */
	public static R success(Integer code,Object data,String dataMsg,String msg) {
		R r = new R();
		r.setSuccess(true);
		r.setCode(code);
		r.setMsg(msg);
		r.getData().put("info", data);
		r.getData().put("msg", dataMsg);
		return r;
	}
	/**
	 * 没有数据
	 * @return
	 */
	public static String noDataToJson() {
		return noData().toJSON();
	}
	/**
	 * 没有数据
	 * @return
	 */
	public static R noData() {
		R r = new R();
		r.setSuccess(true);
		r.setCode(200);
		r.setMsg(REQUEST_SUCCESS);
		r.getData().put("info",new Object());
		r.getData().put("msg", "暂无数据");
		return r;
	}
	/**
	 * 请求失败
	 * @param data     数据 
	 * @return
	 */
	public static String failToJson() {
		return failToJson(ERROR_MSG);
	}
	/**
	 * 请求失败
	 * @param dataMsg  前端提示信息
	 * @return
	 */
	public static String failToJson(String dataMsg) {
		return failToJson(dataMsg,ERROR_MSG);
	}
	/**
	 * 请求失败
	 * @param dataMsg  前端提示信息
	 * @param msg      后端错误信息
	 * @return
	 */
	public static String failToJson(String dataMsg,String msg) {
		return fail(201,dataMsg,msg).toJSON();
	}
	/**
	 * 请求失败
	 * @param data     数据 
	 * @return
	 */
	public static R fail() {
		return fail(ERROR_MSG);
	}
	/**
	 * 请求失败
	 * @param dataMsg  前端提示信息
	 * @return
	 */
	public static R fail(String dataMsg) {
		return fail(dataMsg,ERROR_MSG);
	}
	/**
	 * 请求失败
	 * @param dataMsg  前端提示信息
	 * @param msg      后端错误信息
	 * @return
	 */
	public static R fail(String dataMsg,String msg) {
		return fail(201,dataMsg,msg);
	}
	/**
	 * 请求失败
	 * @param code       状态码  
	 * @param dataMsg    前端提示信息
	 * @param msg        后端错误信息
	 * @return
	 */
	public static R fail(Integer code,String dataMsg,String msg) {
		R r = new R();
		r.setSuccess(false);
		r.setCode(code);
		r.setMsg(msg);
		r.getData().put("info", new Object());
		r.getData().put("msg", dataMsg);
		return r;
	}
	/**
	 * 请求错误
	 * @return 
	 */
	public static String errorToJson() {
		return errorToJson(ERROR_MSG);
	}
	/**
	 * 请求错误
	 * @param dataMsg  前端提示信息
	 * @return
	 */
	public static String errorToJson(String dataMsg) {
		return errorToJson(dataMsg,ERROR_MSG);
	}
	/**
	 * 请求错误
	 * @param dataMsg  前端提示信息
	 * @param msg      后端错误信息
	 * @return
	 */
	public static String errorToJson(String dataMsg,String msg) {
		return error(500,dataMsg,msg).toJSON();
	}
	/**
	 * 请求错误
	 * @param data     数据 
	 * @return
	 */
	public static R error() {
		return error(ERROR_MSG);
	}
	/**
	 * 请求错误
	 * @param dataMsg  前端提示信息
	 * @return
	 */
	public static R error(String dataMsg) {
		return error(dataMsg,ERROR_MSG);
	}
	/**
	 * 请求错误
	 * @param dataMsg  前端提示信息
	 * @param msg      后端错误信息
	 * @return
	 */
	public static R error(String dataMsg,String msg) {
		return error(500,dataMsg,msg);
	}
	/**
	 * 请求错误
	 * @param code       状态码  
	 * @param dataMsg    前端提示信息
	 * @param msg        后端错误信息
	 * @return
	 */
	public static R error(Integer code,String dataMsg,String msg) {
		R r = new R();
		r.setSuccess(false);
		r.setCode(code);
		r.setMsg(msg);
		r.getData().put("info", new Object());
		r.getData().put("msg", dataMsg);
		return r;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
