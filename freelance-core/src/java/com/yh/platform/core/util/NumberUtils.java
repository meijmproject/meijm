/*
 * @(#) AssertUtils.java        1.00         14/03/21
 * 
 * Copyright (c) 2006  EXPRESS Corporation. All Rights Reserved.
 *
 */
package com.yh.platform.core.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 *@description	工具类：数字对象工具
 *
 *@author		zhangqp
 *@created		14/03/21
 *@version		1.0
 */

public class NumberUtils {
	
	/**
	 * 为0时，返回空，否则返回本身
	 * @param num
	 * @return
	 */
	public static Long asNull(Long num) {
		if (num == null || num.longValue() == 0) return null;
		return num;
	}
	
	/**
	 * Long 对象是否为空或0
	 * @param num
	 * @return
	 */
	public static boolean isNullOrZero(Long num) {
		return num == null || num.longValue() == 0;
	}

	/**
	 * Long 对象不为空且不为0
	 * @param num
	 * @return
	 */
	public static boolean isNotNullOrZero(Long num) {
		return !isNullOrZero(num);
	}
	
	/**
	 * String 转为为 Long 对象， 不为空且不为0
	 * @param num
	 * @return
	 */
	public static boolean isNotNullOrZero(String num) {
		return !isNullOrZero(longValue(num));
	}
	
	/**
	 * 转换为Long
	 * @param s
	 * @return
	 */
	public static Long longValue(String s) {
		if (StringUtils.isEmpty(s)) return null;
		return Long.valueOf(StringUtils.trim(s));
	}

	/**
	 * 转换为Integer
	 * @param s
	 * @return
	 */
	public static Integer intValue(String s) {
		if (StringUtils.isEmpty(s)) return null;
		return Integer.valueOf(StringUtils.trim(s));
	}

	/**
	 * Long.valueOf(object.toString())， 为空时，返回null
	 * @param object
	 * @return
	 */
	public static Long createLong(Object object) {
		return object == null ? null : Long.valueOf(object.toString());
	}

	public static Integer createInteger(Object object) {
		return object == null ? null : Integer.valueOf(object.toString());
	}
	
	/**
	 * 数字转string
	 * @param num
	 * @return
	 */
	public static String toString(Number num) {
		return ObjectUtils.toString(num, null);
	}
	/**
	 * 转换为Long
	 * @param s
	 * @return
	 */
	public static Long objToLong(Object s) {
		if (null==s) return 0L;
		return Long.valueOf(s.toString());
	}
}
