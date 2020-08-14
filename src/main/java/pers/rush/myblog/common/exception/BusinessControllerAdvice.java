package pers.rush.myblog.common.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.rush.myblog.common.pojo.R;

/**
 * 拦截BusinessException
 * @author Rush
 *
 */
@ControllerAdvice(annotations = Controller.class)
public class BusinessControllerAdvice {
	
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public R handleMyblogException(BusinessException e) {
		return R.error().success(false)
				.message(e.getMessage())
				.code(e.getErrCode());
	}
}
