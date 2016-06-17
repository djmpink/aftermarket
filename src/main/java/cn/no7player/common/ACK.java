package cn.no7player.common;
/**
 * 系统ACK 统一规定
 * 系统ACK统一为6位  00 开头表示成功 和   99开头表示系统级别错误       
 * @author lzq
 * created on 2015年10月10日 下午4:10:03
 *
 */
public class ACK {
	/**
	 * 成功
	 */
	public static final String COMMEN_SUCCESS = "0000";
	
	/**
	 * 系统错误
	 */
	public static final String SYSTEM_ERROR = "9999";
	
	/**
	 * 系统异常
	 */
	public static final String SYSTEM_EXCEPTION = "9998";
	
	/**
	 * 业务异常
	 */
	public static final String BUSINESS_ERROR = "999997";

	
	/**
	 * 参数错误
	 */
	public static final String PARAM_ERROR = "1000";
	/**
	 * 传入错误的Id
	 */
	public static final String PARAM_ERROR_ID = "1001";
	

	/**
	 * 用户信息错误  建议 以1开头
	 */
	
	/**
	 * 用户不存在
	 */
	public static final String USER_NOT_EXIST = "1001";
	
	/**
	 * 用户密码错误
	 */
	public static final String USER_PASSWORD_ERROR = "1002";
	
	/**
	 * 用户未登录
	 */
	public static final String USER_NOT_LOGIN = "1003";
	
	/**
	 * 用户无权限
	 */
	public static final String USER_NOT_AUTH = "1004";

	/**
	 * 用户未实名认证
	 */
	public static final String USER_NOT_AUTHENTICATION = "1005";

	/**
	 * 用户已实名认证
	 */
	public static final String USER_IS_AUTHENTICATION = "1006";
	/**
	 * 用户已登录
	 */
	public static final String USER_IS_LOGIN = "1007";
	
	/**
	 * 验证码错误
	 */
	public static final String USER_IDENTIFYING_CODE_ERROR = "1008";

	/**
	 * 用户参数（用户名或密码）为空
	 */
	public static final String USER_INFO_PAMR_EMPTY = "1009";

	/**
	 * 注册用户已存在
	 */
	public static final String USER_EXIST = "1010";


	/**
	 * token验证失败
	 */
	public static final String USER_TOKEN_MISS= "1011";
	//-------------

	/**
	 * 参数校验ACK
	 */
	public static final String USER_PARAM_ERROR = "1100";
	
	/**
	 * 手机参数有误
	 */
	public static final String PARAM_TELEPHONE_ERROR = "1101";
	/**
	 * 邮箱参数有误
	 */
	public static final String PARAM_EAMIL_ERROR = "1102";
	/**
	 * 金额参数有误
	 */
	public static final String PARAM_AMOUNT_ERROR = "1103";
	
	
	
	
	/**
	 * 请求参数异常
	 */
	public static final String PROJECT_PARAM_EXCEPTION = "2002";

	
	/**
	 * 邮箱地址非法
	 */
	public static final  String EMAIL_ILLEGAL="3102";
	/**
	 * 超过发送次数限制
	 */
	public static final  String EMAIL_EXEED_TIMES="3103";

	/**
	 * 图片相关
	 */
	//不支持格式
	public static final String IMAGE_NONSUPPORT="3002";
	//超过图片限制大小
	public static final String IMAGE_EXCEEDED="3003";
	//其他错误，上传失败
	public static final String IMAGE_UPLOAD="3004";


	/**
	 * 重复提交请求
	 */
	public static final String REPEAT_SUBMIT= "5001";

	/**
	 * 重复提交请求
	 */
	public static final String ACTIVITY_CODE_NOT_EXIST= "6001";
}
