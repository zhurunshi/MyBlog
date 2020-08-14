package pers.rush.myblog.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import pers.rush.myblog.common.pojo.R;
import pers.rush.myblog.common.util.JWTUtils;
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
	public R login(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
		UserEntity userEntity = loginLogic.auth(userVO);
		request.getSession().setAttribute("user", userEntity);
		// 根据用户信息生成token
		Map<String, String> claims = new HashMap<>();
		claims.put("userId", userEntity.getUserId());
		String token = JWTUtils.genToken(claims);
		response.setHeader("Token", token);
		return R.ok().data("token", token);
	}

	@Override
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return new ModelAndView("redirect:/");
	}

}
