package pers.rush.myblog.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import pers.rush.myblog.common.util.JWTUtils;
import pers.rush.myblog.user.dao.entity.UserEntity;

/**
 * 判断Token是否存在
 * @author Rush
 *
 */
public class TokenInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("Token");// 从 http请求头中取出 token
		if (token == null) {
            throw new RuntimeException("无Token，请重新登录。");
        }
		
		// 验证session
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		// 若session中用户为空，跳转登录页面
		if (userEntity != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
		
		// 验证 token
		Map<String, String> map = JWTUtils.verifyToken(token);
		String userId = map.get("userId");
		if (userId == null || !userId.equals(userEntity.getUserId())) {
			throw new RuntimeException("Token信息无效。");
		}
		String expireTimeStr = map.get("exp");
		if (System.currentTimeMillis() / 1000 >=
				Long.valueOf(expireTimeStr)) {
			// 判断过期
			throw new RuntimeException("Token信息无效，已过期。");
		}
        return true;
	}
}
