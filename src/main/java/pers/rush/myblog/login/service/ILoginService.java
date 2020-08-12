package pers.rush.myblog.login.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import pers.rush.myblog.user.service.vo.UserVO;

/**
 * 登录控制器
 * @author Rush
 *
 */
public interface ILoginService {
	
	/**
	 * 登录
	 * @param userVO
	 * @param request
	 */
	public void login(UserVO userVO, HttpServletRequest request);
	
	/**
	 * 登出
	 * @param request
	 * @return
	 */
	public ModelAndView logout(HttpServletRequest request);
}
