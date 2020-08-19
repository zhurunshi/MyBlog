package pers.rush.myblog.common.data;

/**
 * 常量类
 * @author RUSH
 *
 */
public class Const {
	/**
	 * 成功
	 */
	public static final String SUCCESS = "0000";
	/**
	 * 秘钥
	 */
	public static final String SECRET = "RUSH";
	
	public static long EXPIRE_1_MIN = 1 * 60 * 1000;
	public static long EXPIRE_30_MINS = 30 * 60 * 1000;
	
	/**
	 * header和cookie中的token的键名
	 */
	public static final String TOKEN_KEY = "token";
	/**
	 * header和cookie中的refresh_token的键名
	 */
	public static final String REFRESH_TOKEN_KEY = "refresh_token";
}
