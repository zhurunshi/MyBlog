package pers.rush.myblog.common.data;

/**
 * 错误码
 * @author RUSH
 *
 */
public class ErrConst {
	/**
	 * 常规错误
	 */
	public static final String ERROR = "4000";
	/**
	 * 登录失败
	 */
	public static final String LOGIN_FAILED = "4001";
	/**
	 * 登录信息为空
	 */
	public static final String LOGIN_NULL = "4002";
	/**
	 * 生成Token失败
	 */
	public static final String GEN_TOKEN_ERROR = "4003";
	/**
	 * 无Token
	 */
	public static final String TOKEN_NULL = "4004";
	/**
	 * 解析Token失败
	 */
	public static final String ANY_TOKEN_ERROR = "4005";
	/**
	 * Token无效
	 */
	public static final String TOKEN_ERROR = "4006";
}
