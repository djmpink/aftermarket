package cn.no7player.common;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.no7player.common.bean.PageForm;
import cn.no7player.common.bean.PageRequst;
import cn.no7player.common.bean.Result;
import cn.no7player.common.em.ResultCode;
import cn.no7player.common.exception.BusinessException;
import cn.no7player.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;




/**
 * Controller 基础服务类
 * 
 * @author LZQ
 *
 */
public class BaseController {

	/**
	 * 日志
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpServletRequest getHttpServletRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	protected String getStringParameter(String name) {
		return getStringParameter(name, null);
	}

	protected String getStringParameter(String name, String defaultValue) {
		String value = getHttpServletRequest().getParameter(name);

		return StringUtil.isEmpty(value) ? defaultValue : value.trim();
	}

	protected BigDecimal getBigDecimalParameter(String name) {
		String value = getHttpServletRequest().getParameter(name);
		return StringUtil.isEmpty(value) ? null : new BigDecimal(value);
	}

	protected BigDecimal getBigDecimalParameter(String name, String defaultValue) {
		String value = getHttpServletRequest().getParameter(name);
		return StringUtil.isEmpty(value) ? new BigDecimal(defaultValue) : new BigDecimal(value);
	}

	protected Boolean getBooleanParameter(String name) {
		return getBooleanParameter(name, null);
	}

	protected Boolean getBooleanParameter(String name, Boolean defaultValue) {
		String value = getHttpServletRequest().getParameter(name);

		return StringUtil.isEmpty(value) ? defaultValue : Boolean.valueOf(value);
	}

	protected Integer getIntegerParameter(String name) {
		return getIntegerParameter(name, null);
	}

	protected Integer getIntegerParameter(String name, Integer defaultValue) {
		String value = getHttpServletRequest().getParameter(name);

		return StringUtil.isEmpty(value) ? defaultValue : Integer.valueOf(value);
	}

	protected Long getLongParameter(String name) {
		return getLongParameter(name, null);
	}

	protected Long getLongParameter(String name, Long defaultValue) {
		String value = getHttpServletRequest().getParameter(name);

		return StringUtil.isEmpty(value) ? defaultValue : Long.valueOf(value);
	}

	protected Float getFloatParameter(String name) {
		return getFloatParameter(name, null);
	}

	protected Float getFloatParameter(String name, Float defaultValue) {
		String value = getHttpServletRequest().getParameter(name);

		return StringUtil.isEmpty(value) ? defaultValue : Float.valueOf(value);
	}

	protected Double getDoubleParameter(String name) {
		return getDoubleParameter(name, null);
	}

	protected Double getDoubleParameter(String name, Double defaultValue) {
		String value = getHttpServletRequest().getParameter(name);
		return StringUtil.isEmpty(value) ? defaultValue : Double.valueOf(value);
	}
	
	/**
	 * 获取对象参数
	 * @param name
	 * @param clazz
	 * @return
	 */
	public<T> T getObjetParameter(String name,Class<T> clazz){
		String value = getStringParameter(name);
		T obj = JSONObject.parseObject(value,clazz);
		return obj;
	}
	/**
	 * 获取集合参数
	 * @param name
	 * @param clazz
	 * @return
	 */
	public<T> List<T> getArrayParameter(String name,Class<T> clazz){
		String value = getStringParameter(name);
		List<T> list = JSONArray.parseArray(value, clazz);
		return list;
	}
	
	/**
	 * 获取分页参数
	 * @return
	 */
	public PageRequst getPageRequest(){
		PageRequst pageRequst;
		try {
			pageRequst = new PageRequst();
			int page = getIntegerParameter("page",1);
			int pageSize = getIntegerParameter("pageSize",10);
			int start = getIntegerParameter("start", 0);
			pageRequst.setPage(page);
			pageRequst.setPageSize(pageSize);
			pageRequst.setStart(start);
		} catch (Exception e) {
			logger.info("获取分页参数失败",e);
			throw new BusinessException(ACK.PARAM_ERROR, "缺少分页参数");
		}
		return pageRequst;
	}
	/**
	 * 获取分页参数
	 * @return
	 */
	public PageForm getPageForm(){
		PageForm pageRequst;
		try {
			pageRequst = new PageForm();
			int page = getIntegerParameter("page",1);
			int pageSize = getIntegerParameter("pageSize",10);
			int start = getIntegerParameter("start", 0);
			pageRequst.setPage(page);
			pageRequst.setLimit(pageSize);
			pageRequst.setStart(start);
		} catch (Exception e) {
			logger.info("获取分页参数失败",e);
			throw new BusinessException(ACK.PARAM_ERROR, "缺少分页参数");
		}
		return pageRequst;
	}
	
	/**
	 * 获取用户
	 * @param token
	 * @return
	 */
	protected Integer getUserId(String token){
		if(StringUtil.isEmpty(token)){
			throw new BusinessException(ACK.USER_NOT_LOGIN,"请登录");
		}
		return Integer.valueOf(token);
	}
	/**
	 * 返回成功结果（单个数据对象）
	 * 
	 * @param object
	 * @return
	 */
	protected Result resultOK(Object object) {
		Result result = new Result(ResultCode.COMMON_SUCCESS);
		result.setData(object);
		return result;
	}

	/**
	 * 返回成功结果（不需返回数据）
	 * 
	 * @return
	 */
	protected Result resultOK() {
		return resultOK(null);
	}

	/**
	 * 返回错误信息
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	protected Result resultError(String code, String msg) {
		return resultError(code, msg, null);
	}

	/**
	 * 返回错误信息
	 * 
	 * @param code
	 * @param msg
	 * @param object
	 * @return
	 */
	protected Result resultError(String code, String msg, Object object) {
		Result result = new Result();
		result.setCode(code);
		result.setSuccess(false);
		result.setMessage(msg);
		return result;
	}
}
