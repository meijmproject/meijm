/*
 * @(#) StringUtil.java        1.00         2007-11-21
 * 
 * Copyright (c) 2006  EXPRESS Corporation. All Rights Reserved.
 *
 */
package com.yh.platform.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 * 
 * @author chenkb
 * @created 2007-11-21
 * @version 1.0
 * 
 */

public class StringUtil {
	
	public static String toString(Object o) {
		return toString(o, null);
	}
	
	public static String toString(Object o, String defaultValue) {
		return o == null ? defaultValue : o.toString();
	}
	
//	public static List<String> splitStr2List(String str, String splitFlag) {
//		List<String> itemCodeLst = new ArrayList<String>();
//		if (str != null && str.length() > 0) {
//			String[] itemCodeArry = StringUtils.split(str, splitFlag);
//			Collections.addAll(itemCodeLst, itemCodeArry);
//		}
//		return itemCodeLst;
//	}

	// ===========================================================

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param s
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 */
	public static boolean isBlank(String s) {
		return StringUtils.isBlank(s);
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace only.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.isNotBlank(null)      = false
	 * StringUtil.isNotBlank("")        = false
	 * StringUtil.isNotBlank(" ")       = false
	 * StringUtil.isNotBlank("bob")     = true
	 * StringUtil.isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @param s
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not
	 *         whitespace
	 */
	public static boolean isNotBlank(String s) {
		return StringUtils.isNotBlank(s);
	}

	public final static String	BLANK	= "";

	public final static String	COMMA	= ",";

	public final static String	QUOT	= "'";

	public final static String	PERCENT	= "%";

	public static String arrayToSql(Collection<? extends Object> coll) {
		return arrayToSql(coll, null);
	}

	public static String arrayToSql(Collection<? extends Object> coll, String surround) {
		return arrayToSql(coll == null ? null : coll.toArray(new Object[coll.size()]), surround);
	}

	/**
	 * [1,2,3] => '1','2','3'
	 * @param strs， 为空时，返回 ''
	 * @return
	 */
	public static String arrayToSql(String[] strs) {
		return arrayToSql(strs, QUOT);
	}
	
	public static String arrayToSql(Object[] objs, String surround) {
		return connect(objs, COMMA, surround, true);
	}
	
	public static String arrayToSql(Object[] objs, String surround, boolean hasBlank) {
		return connect(objs, COMMA, surround, hasBlank);
	}

	public static String connect(Object[] objs, String link, String surround) {
		return connect(objs, link, surround, false);
	}

	public static String connect(Object[] objs, String link, String surround, boolean hasBlank) {
		if (link == null)
			link = COMMA;
		if (objs != null && objs.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (Object s : objs) {
				if (sb.length() > 0)
					sb.append(link);
				sb.append(getSurrounded(s, surround));
			}
			return sb.toString();
		}
		return hasBlank ? getSurrounded(null, surround) : BLANK;
	}

	private static String getSurrounded(Object v, String surround) {
		boolean needSrd = surround != null;
		StringBuilder sb = new StringBuilder();
		if (needSrd)
			sb.append(surround);
		if (v != null)
			sb.append(v);
		if (needSrd)
			sb.append(surround);
		return sb.toString();
	}

	public static boolean getBoolean(String b) {
		if (StringUtils.isBlank(b))
			return false;
		b = b.trim().toLowerCase();
		return b.equals("y") || b.equals("true") || b.equals("yes");
	}

	@SuppressWarnings("unchecked")
	public static <T> Collection<T> strToObj(String[] strs, Class<T> type) {
		if (strs == null || strs.length == 0) {
			return Collections.emptyList();
		}
		List<T> list = new ArrayList<T>(strs.length);
		for (String s : strs) {
			if (type == String.class) {
				list.add((T) s);
			}
			if (type == Integer.class) {
				list.add((T) Integer.valueOf(s));
			}
			if (type == Long.class) {
				list.add((T) Long.valueOf(s));
			}
		}
		return list;
	}

	public static boolean isNull(Long v) {
		return v == null || v == 0;
	}

	public static boolean isNotNull(Long v) {
		return !isNull(v);
	}
	
	/**
	 * 字符串的SQL IN 查询拼接  [1,2,3] => '1','2','3'
	 * @param objs
	 * @return
	 */
	public static String joinWrap(String... objs) {
		return connect(objs, COMMA, QUOT, false);
	}
	
	/**
	 * 字符串的SQL IN 查询拼接  [1,2,3] => '1','2','3'
	 * @param objs
	 * @return
	 */
	public static String joinWrap(List<String> authCodes) {
		return connect(authCodes.toArray(new String[authCodes.size()]), COMMA, QUOT, false);
	}
	
	/**
	 * 拼接 <br> [1,2,3][;] => 1;2;3  <br>  [1,2,3][,] => 1,2,3
	 * @param coll
	 * @param link
	 * @return
	 */
	public static String join(Collection<? extends Object> coll, String link) {
		return connect(coll == null ? null : coll.toArray(new Object[coll.size()]), link, null, true);
	}
	
	/**
	 * 拼接 [1,2,3][;] => 1;2;3
	 * @param objs
	 * @param link
	 * @return
	 */
	public static String join(Object[] objs, String link) {
		return connect(objs, link, null, true);
	}
	
	/**
	 * 逗号拼接 [1,2,3] => 1,2,3
	 * @param objs
	 * @return
	 */
	public static String join(Object[] objs) {
		return connect(objs, COMMA, null, true);
	}

	/**
	 * 逗号拼接 [1,2,3] => 1,2,3
	 * @param coll
	 * @return
	 */
	public static String join(Collection<? extends Object> coll) {
		return connect(coll == null ? null : coll.toArray(new Object[coll.size()]), COMMA, null, true);
	}

	/**
	 * 前后加上百分号，用于like name => %name%
	 * @param str
	 * @return
	 */
	public static String wrapPercent(String str) {
		return new StringBuilder().append(PERCENT).append(str).append(PERCENT).toString();
	}
	
	/**
	 * 前后加上指定字符 name,(,) => (name)
	 * @param str
	 * @return
	 */
	public static String wrap(String str, String prefix, String suffix) {
		return StringUtils.isEmpty(str) ? BLANK :new StringBuilder().append(prefix==null?BLANK:prefix).append(str).append(suffix==null?BLANK:suffix).toString();
	}
	
}
