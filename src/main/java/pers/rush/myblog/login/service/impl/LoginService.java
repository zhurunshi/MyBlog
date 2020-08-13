package pers.rush.myblog.login.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import pers.rush.myblog.login.logic.LoginLogic;
import pers.rush.myblog.login.service.ILoginService;
import pers.rush.myblog.user.dao.entity.UserEntity;
import pers.rush.myblog.user.service.vo.UserVO;

/**
 * 登录控制器
 * @author Rush
 *
 */
@RestController
@RequestMapping(value = "/login/login")
public class LoginService implements ILoginService {

	@Autowired
	private LoginLogic loginLogic;
	
	@Override
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(UserVO userVO, HttpServletRequest request) {
		UserEntity userEntity = loginLogic.auth(userVO);
		request.getSession().setAttribute("user", userEntity);
	}

	@Override
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return new ModelAndView("redirect:/");
	}

}
