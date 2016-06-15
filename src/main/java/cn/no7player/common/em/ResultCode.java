package cn.no7player.common.em;


import cn.no7player.common.ACK;

/**
 * 返回数据枚举
 * @author LZQ
 *
 */
public enum ResultCode {
	/**
	 * 通用的返回码
	 */
	COMMON_SUCCESS(ACK.COMMEN_SUCCESS,"成功",true),
	COMMON_BUSINESS_EXCEPTION(ACK.BUSINESS_ERROR,"业务异常",false),
	COMMON_SYSTEM_EXCEPTION(ACK.SYSTEM_EXCEPTION,"系统异常",false),
	COMMON_SYSTEM_ERROR(ACK.SYSTEM_ERROR,"系统错误",false);

	/**
	 * 结果码
	 */
	private String code;

	/**
	 * 描述
	 */
	private String message;
	
	/**
	 * 是否成功
	 */
	private boolean success;

	private ResultCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	private ResultCode(String code, String message,boolean success) {
		this.code = code;
		this.message = message;
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	public boolean isSuccess() {
		return success;
	}

}
