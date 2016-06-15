package cn.no7player.common.bean;

import cn.no7player.common.em.ResultCode;

import java.io.Serializable;





/**
 * 返回统一接口
 * @author LZQ
 *
 */
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 结果码
	 */
	private String code;

	/**
	 * 成功标志
	 */
	protected boolean success;

	/**
	 * 业务处理返回数据
	 */
	protected Object data;

	/**
	 * 返回消息
	 */
	protected String message;

	/**
	 * 是否将日期转换为
	 */
	private transient boolean useDateFormat = true;
	
	/**
	 * 日期格式默认值
	 */
	private transient String dateFormat = "yyyy-MM-dd HH:mm:ss";
	public Result() {

	}

	public Result(ResultCode resultCode) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.success = resultCode.isSuccess();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean isSuccess) {
		this.success = isSuccess;
	}

	public void setCode(ResultCode resultCode) {
		setCode(resultCode.getCode());
		setMessage(resultCode.getMessage());
	}

	public boolean isUseDateFormat() {
		return useDateFormat;
	}

	public void setUseDateFormat(boolean useDateFormat) {
		this.useDateFormat = useDateFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
