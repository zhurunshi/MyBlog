package pers.rush.myblog.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;

import pers.rush.myblog.common.data.Const;
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
			String token = null;
			String refreshToken = null;
			for (Cookie cookie : cookies) {
				if (Const.TOKEN_KEY.equals(cookie.getName())) {
					token = cookie.getValue();
				} else if (Const.REFRESH_TOKEN_KEY.equals(cookie.getName())) {
					refreshToken = cookie.getValue();
				}
			}
			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(refreshToken)) {
				throw new BusinessException(ErrConst.TOKEN_NULL, "无Token，请重新登录。");
			}
			// 验证 token
			Map<String, String> map = null;
			try {
				map = JWTUtils.verifyToken(token, Const.SECRET);
				String userId = map.get("userId");
				if (userId == null || !userId.equals(userEntity.getUserId())) {
					throw new BusinessException(ErrConst.TOKEN_ERROR, "Token信息无效。");
				}
			} catch (TokenExpiredException e) {
				// 如果token过期，则验证refresh_token是否过期
				try {
					map = JWTUtils.verifyToken(refreshToken, token);
				} catch (TokenExpiredException ei) {
					// 如果refresh_token也过期，抛出异常。
					throw new BusinessException(ErrConst.TOKEN_ERROR, "Token信息过期，请重新登录。");
				}
				// 如果refresh_token未过期，则给token续期
				Map<String, String> claims = new HashMap<>();
				claims.put("userId", userEntity.getUserId());
				String newToken = JWTUtils.genToken(claims, Const.SECRET, Const.EXPIRE_1_MIN);
				String newRefreshToken = JWTUtils.genToken(claims, token, 2 * Const.EXPIRE_1_MIN);
				// 将token和refresh_token放到cookie中
				Cookie newTokenCookie = new Cookie("token", newToken);
				newTokenCookie.setMaxAge(60*60);
				newTokenCookie.setPath("/");
				Cookie newRefreshTokenCookie = new Cookie("refresh_token", newRefreshToken);
				newRefreshTokenCookie.setMaxAge(60*60);
				newRefreshTokenCookie.setPath("/");
				response.addCookie(newTokenCookie);
				response.addCookie(newRefreshTokenCookie);
			}
		} else {
			throw new BusinessException(ErrConst.TOKEN_NULL, "无Token，请重新登录。");
		}
        return true;
	}
}
