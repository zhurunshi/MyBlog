package pers.rush.myblog.common.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class JWTUtilsTest {

	@Test
	void testToken() {
		
		Map<String, String> claims = new HashMap<>();
		claims.put("userId", "0001");
		
		String token = JWTUtils.genToken(claims);
		
		System.out.println("token: " + token);
		
		Map<String, String> map = JWTUtils.verifyToken(token);
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		System.out.println(System.currentTimeMillis()/1000);
	}

}
