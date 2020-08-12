package pers.rush.myblog.user.dao.entity;

/**
 * 用户实体
 * 对应数据库中的结构
 * @author Rush
 *
 */
public class UserEntity {
	/**
	 * 用户编号
	 */
	private String userId;
	/**
	 * 用户名（登录用）
	 */
	private String userName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 角色编号
	 */
	private String roleId;
	/**
	 * 备注
	 */
	private String info;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
