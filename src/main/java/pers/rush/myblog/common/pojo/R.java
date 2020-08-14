package pers.rush.myblog.common.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一结果类
 * @author Rush
 *
 */
public class R {
	private Boolean success;
	private String code;
	private String message;
	private Map <String, Object> data = new HashMap<>();
	
	private R() {}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	/* 链式编程 */
	public R data(Map<String, Object> map) {
		this.setData(map);
		return this;
	}
	public R data(String key, String value) {
		this.data.put(key, value);
		return this;
	}
	public R message(String message) {
		this.setMessage(message);
		return this;
	}
	public R code(String code) {
		this.setCode(code);
		return this;
	}
	public R success(Boolean success) {
		this.setSuccess(success);
		return this;
	}

	public static R ok() {
		R r = new R();
		r.setSuccess(ResultEnum.SUCCESS.getSuccess());
		r.setCode(ResultEnum.SUCCESS.getCode());
		r.setMessage(ResultEnum.SUCCESS.getMessage());
		return r;
	}
	
	public static R error() {
		R r = new R();
		r.setSuccess(ResultEnum.ERROR.getSuccess());
		r.setCode(ResultEnum.ERROR.getCode());
		r.setMessage(ResultEnum.ERROR.getMessage());
		return r;
	}
}
