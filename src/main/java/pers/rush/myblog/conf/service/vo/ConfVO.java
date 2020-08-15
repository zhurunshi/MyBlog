package pers.rush.myblog.conf.service.vo;

public class ConfVO {
	/**
	 * 参数代码
	 */
	private String confKey;
	/**
	 * 参数名称
	 */
	private String confName;
	/**
	 * 参数类型
	 */
	private String type;
	/**
	 * 参数值
	 */
	private String value;
	/**
	 * 备注
	 */
	private String dtal;
	public String getConfKey() {
		return confKey;
	}
	public void setConfKey(String confKey) {
		this.confKey = confKey;
	}
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDtal() {
		return dtal;
	}
	public void setDtal(String dtal) {
		this.dtal = dtal;
	}
	
}
