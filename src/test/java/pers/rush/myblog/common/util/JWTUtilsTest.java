package pers.rush.myblog.common.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import pers.rush.myblog.common.data.Const;

class JWTUtilsTest {

	@Test
	void testToken() {
		
		Map<String, String> claims = new HashMap<>();
		claims.put("userId", "0001");
		
		String token = JWTUtils.genToken(claims, Const.SECRET, Const.EXPIRE_30_MINS);
		
		System.out.println("token: " + token);
		
		Map<String, String> map = JWTUtils.verifyToken(token, Const.SECRET);
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		System.out.println(System.currentTimeMillis()/1000);
	}

}
