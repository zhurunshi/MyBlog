package pers.rush.myblog.common.pojo;

/**
 * 结果类型
 * @author Rush
 *
 */
public enum ResultEnum {
	SUCCESS(true, "0000", "成功"),
	ERROR(false, "4000", "错误");
	
	 
	// 响应是否成功
	private Boolean success;
	// 响应状态码
	private String code;
	// 响应信息
	private String message;

	ResultEnum(boolean success, String code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

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
	
}
