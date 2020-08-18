package pers.rush.myblog.login.logic;

import org.springframework.stereotype.Component;

import pers.rush.myblog.common.data.ErrConst;
import pers.rush.myblog.common.exception.BusinessException;
import pers.rush.myblog.user.dao.entity.UserEntity;
import pers.rush.myblog.user.service.vo.UserVO;

/**
 * 登录逻辑
 * 将复杂逻辑写入此处
 * @author Rush
 *
 */
@Component
public class LoginLogic {
	
	/**
	 * 登录鉴权
	 * @param userVO
	 * @return
	 */
	public UserEntity auth(UserVO userVO) {
		if (userVO == null) {
			String msg = "登录信息为空！";
			throw new BusinessException("0001", msg);
		}
		UserEntity userEntity = null;
		/*
		 * 正常情况是查询数据库，校验密码，
		 *  或是调用某些服务，校验密码。
		 */
		if ("admin".equals(userVO.getUname()) &&
				"111111".equals(userVO.getPwd())) {
			userEntity = new UserEntity();
			userEntity.setUserId("U0001");
			userEntity.setUserName("admin");
			userEntity.setRoleId("R0001");
			userEntity.setRealName("朱润石");
		} else {
			String msg = "用户名或密码错误。";
			throw new BusinessException(ErrConst.LOGIN_FAILED, msg);
		}
		return userEntity;
	}
}
