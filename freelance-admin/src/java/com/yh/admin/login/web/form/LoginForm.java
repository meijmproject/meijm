package com.yh.admin.login.web.form;

import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {
	/**
	 * sid
	 */
	private static final long	serialVersionUID	= -4076373247406142615L;

	private String				userCode;

	private String				password;

	private String				localeStr;

	private String				systemCode;

	private String				screenSize;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null)
			this.password = password.trim();
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String username) {
		if (username != null)
			this.userCode = username.trim();
	}

	public String getLocaleStr() {
		return localeStr;
	}

	public void setLocaleStr(String localeStr) {
		this.localeStr = localeStr;
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("userName=" + userCode);
		str.append(",local=" + localeStr);
		return str.toString();
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

}
