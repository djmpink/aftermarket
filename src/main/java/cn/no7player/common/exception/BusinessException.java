package cn.no7player.common.exception;

import java.util.HashMap;
import java.util.Map;
/**
 * 业务异常
 * @author LZQ
 *
 */
public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 自定义属性
	 */
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	private String code;
	private String msg;
	public BusinessException(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BusinessException(String message, Map<String, Object> resultMap) {
		super(message);
		this.resultMap = resultMap;
	}
	
	public BusinessException(String message, Map<String, Object> resultMap, Throwable cause) {
		super(message, cause);
		this.resultMap = resultMap;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
