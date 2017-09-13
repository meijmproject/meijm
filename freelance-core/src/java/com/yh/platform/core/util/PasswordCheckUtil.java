package com.yh.platform.core.util;

/**
 * @description 密码格式验证工具类
 * @author	zhangqp
 * @version	1.0,	16/09/19
 */
public class PasswordCheckUtil {
	
	/**
	 * 验证密码有效性
	 * 
	 * @param password
	 * @return 如果密码不符合要求则返回异常信息
	 */
	public static String checkPassword(String password) {
		if (password == null) {
			return "密码不能为空！";
		}

		int length = password.length();
		if (length > 12 || length < 8) {
			return "密码不符合规范！（要求长度为8~12位）";
		}

		boolean numf = false, lowcharf = false, upcharf = false;
		boolean ccf = true;
		for (char x : password.toCharArray()) {
			if (x >= '0' && x <= '9') {
				numf = true;
			} else if (x >= 'a' && x <= 'z') {
				lowcharf = true;
			} else if (x >= 'A' && x <= 'Z') {
				upcharf = true;
			} else {
				ccf = false;
			}
		}

		if (!ccf) {
			return "密码不符合规范！（要求以数字和英文字母组成）";
		} 
		
		if (!numf) {
			return "密码不符合规范！（要求至少包含一位以上数字）";
		}

		if (!lowcharf) {
			return "密码不符合规范！（要求至少包含一位以上小写英文字母）";
		}

		if (!upcharf) {
			return "密码不符合规范！（要求至少包含一位以上大写英文字母）";
		}

		return null;
	}

	public static void main(String[] arg) {
		System.out.println(checkPassword("         "));
	}

}
