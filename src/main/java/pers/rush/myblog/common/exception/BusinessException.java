package pers.rush.myblog.common.exception;

/**
 * 业务异常
 * @author Rush
 *
 */
public class BusinessException extends RuntimeException {
	/**
	 * 错误码
	 */
	private String errCode;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
}
