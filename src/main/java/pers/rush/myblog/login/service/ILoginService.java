package pers.rush.myblog.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import pers.rush.myblog.common.pojo.R;
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
	public R login(UserVO userVO, HttpServletRequest request, 
			HttpServletResponse response);
	
	/**
	 * 登出
	 * @param request
	 * @return
	 */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response);
}
