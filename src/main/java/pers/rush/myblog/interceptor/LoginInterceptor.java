package pers.rush.myblog.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import pers.rush.myblog.common.data.ErrConst;
import pers.rush.myblog.common.exception.BusinessException;
import pers.rush.myblog.common.util.JWTUtils;
import pers.rush.myblog.user.dao.entity.UserEntity;

/**
 * 判断Token是否存在
 * @author Rush
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 验证session
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		// 若session中用户为空，跳转登录页面
		if (userEntity == null) {
			response.sendRedirect(request.getContextPath() + "/");
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			boolean ifValidate = false;
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					String token = cookie.getValue();
					// 验证 token
					Map<String, String> map = JWTUtils.verifyToken(token);
					String userId = map.get("userId");
					if (userId == null || !userId.equals(userEntity.getUserId())) {
						throw new BusinessException(ErrConst.TOKEN_ERROR, "Token信息无效。");
					}
					ifValidate = true;
					// 更新token时间
					Map<String, String> claims = new HashMap<>();
					claims.put("userId", userEntity.getUserId());
					String newToken = JWTUtils.genToken(claims);
					// 将token放到cookie中
					Cookie newCookie = new Cookie("token", newToken);
					newCookie.setMaxAge(60*30); // 30mins
					newCookie.setPath("/");
					response.addCookie(newCookie);
					break;
				}
			}
			if (!ifValidate) {
				throw new BusinessException(ErrConst.TOKEN_NULL, "无Token，请重新登录。");
			}
		} else {
			throw new BusinessException(ErrConst.TOKEN_NULL, "无Token，请重新登录。");
		}
        return true;
	}
}
