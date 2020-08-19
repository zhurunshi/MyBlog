package pers.rush.myblog.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import pers.rush.myblog.common.data.Const;
import pers.rush.myblog.common.data.ErrConst;
import pers.rush.myblog.common.exception.BusinessException;

/**
 * JWT工具类
 * @author RUSH
 *
 */
@Component
public class JWTUtils {
	private static final String ISSUER = "myblog";
	
	/**
	 * 生成token
	 * @param claims
	 * @param expireDatePoint
	 * @return
	 */
	public static String genToken(Map<String, String> claims, String secret, long during) {
		try {
			// 使用HMAC256进行加密
			Algorithm algorithm = Algorithm.HMAC256(secret);
			long nowTime = System.currentTimeMillis();
			// 创建jwt
			JWTCreator.Builder builder = JWT.create()
					.withIssuer(ISSUER) // 发行者
					.withIssuedAt(new Date(nowTime)) // 当前时间
					.withExpiresAt(new Date(nowTime + during)); // 过期时间
			// 传入参数
			for (Map.Entry<String, String> entry : claims.entrySet()) {
				builder.withClaim(entry.getKey(), entry.getValue());
			}
			// 签名加密
			return builder.sign(algorithm);
		} catch (Exception e) {
            throw new BusinessException(ErrConst.GEN_TOKEN_ERROR, "生成token时出现异常，原因为" + e.getMessage());
        }
	}
	
	/**
	 * 解析token
	 * @param token
	 * @return
	 */
	public static Map<String, String> verifyToken(String token, String secret) {
		Algorithm algorithm = null;
		try {
			// 使用HMAC256加密
			algorithm = Algorithm.HMAC256(secret);
		} catch (Exception e) {
			throw new BusinessException(ErrConst.ANY_TOKEN_ERROR, "解析token时出现异常，原因为：" + e.getMessage());
		}
		// 解析
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
		} catch (TokenExpiredException e) { // token过期异常
			throw e;
		} catch (Exception e) {
			throw new BusinessException(ErrConst.ANY_TOKEN_ERROR, "解析token时出现异常，原因为：" + e.getMessage());
		}
		Map<String, Claim> map = jwt.getClaims();
		Map<String, String> resultMap = new HashMap<>();
		for (Map.Entry<String, Claim> entry : map.entrySet()) {
			if ("exp".equals(entry.getKey())) {
				resultMap.put(entry.getKey(), entry.getValue().asLong().toString());
			} else {
				resultMap.put(entry.getKey(), entry.getValue().asString());
			}
		}
		return resultMap;
	}
}
