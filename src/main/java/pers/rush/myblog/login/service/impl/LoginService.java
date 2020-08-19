package pers.rush.myblog.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import pers.rush.myblog.common.data.Const;
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
		// 1.验证通过后，将用户放到session中
		request.getSession().setAttribute("user", userEntity);
		// 2.根据用户信息生成token
		Map<String, String> claims = new HashMap<>();
		claims.put("userId", userEntity.getUserId());
		// 可以使用该用户的密码作为秘钥
		String token = JWTUtils.genToken(claims, Const.SECRET, Const.EXPIRE_1_MIN);
		// 3.将token放到cookie中
		Cookie tokenCookie = new Cookie("token", token);
		tokenCookie.setMaxAge(60*60);
		tokenCookie.setPath("/");
		response.addCookie(tokenCookie);
		// 4.根据token生成refresh-token
		String refreshToken = JWTUtils.genToken(claims, token, 2 * Const.EXPIRE_1_MIN);
		// 5.将refresh-token放到cookie中
		Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
		refreshCookie.setMaxAge(60*60);
		refreshCookie.setPath("/");
		response.addCookie(refreshCookie);
		return R.ok().data("token", token)
					.data("refresh_token", refreshToken);
	}

	@Override
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		// 删除session中的用户
		request.getSession().removeAttribute("user");
		// 删除cookie
		Cookie tokenCookie = new Cookie("token" , null);
		tokenCookie.setMaxAge(0);
		tokenCookie.setPath("/");
		response.addCookie(tokenCookie);
		Cookie refreshCookie = new Cookie("refresh_token" , null);
		refreshCookie.setMaxAge(0);
		refreshCookie.setPath("/");
		response.addCookie(refreshCookie);
		return new ModelAndView("redirect:/");
	}

}
