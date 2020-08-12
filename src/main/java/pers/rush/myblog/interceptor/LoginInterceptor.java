package pers.rush.myblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import pers.rush.myblog.user.dao.entity.UserEntity;

/**
 * 登录拦截器
 * 适用于：非ajax形式，可以登录跳转
 * @author Rush
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		// 若session中用户为空，跳转登录页面
		if (userEntity != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
		return false;
	}
}
