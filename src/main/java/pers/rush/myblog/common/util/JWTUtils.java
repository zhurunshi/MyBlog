package pers.rush.myblog.common.util;

import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import pers.rush.myblog.common.exception.BusinessException;

/**
 * JWT工具类
 * @author RUSH
 *
 */
public class JWTUtils {
	private static final String SECRET = "RUSH";
	
	/**
	 * 生成token
	 * @param claims
	 * @param expireDatePoint
	 * @return
	 */
	public static String genToken(Map<String, String> claims, Date expireDatePoint) {
		try {
			// 使用HMAC256进行加密
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			
			// 创建jwt
			JWTCreator.Builder builder = JWT.create()
					.withIssuer("faxingren") // 发行人
					.withExpiresAt(expireDatePoint); // 过期时间点
			
			// 传入参数
			for (Map.Entry<String, String> entry : claims.entrySet()) {
				builder.withClaim(entry.getKey(), entry.getValue());
			}
			
			// 签名加密
			return builder.sign(algorithm);
		} catch (Exception e) {
            throw new BusinessException("0002", "生成token时出现异常，原因为" + e.getMessage());
        }
	}
}
