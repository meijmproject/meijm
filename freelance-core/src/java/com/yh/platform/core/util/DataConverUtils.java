/**
 * 
 */
package com.yh.platform.core.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;

/**
 * sql 返回数据类型转换工具类
 * @author	zhangqp
 * @version	1.0,	16/08/20
 */

public class DataConverUtils {
	
	/**
	 * 为null时，返回null，obj 如果为number类型，直接返回，否则toString后转为BigDecimal类型
	 * @param obj
	 * @return
	 */
	public static Number toNumber(Object obj) {
		
		if (obj == null) return null;
		
		if (obj instanceof Number) {
			return (Number)obj;
		}
		
		return new BigDecimal(obj.toString());
	}

	/**
	 * 为null时，返回null
	 * @param obj
	 * @return
	 */
	public static Long toLong(Object obj) {
		return obj == null ? null : toNumber(obj).longValue();
	}
	
	/**
	 * 为null时，返回null
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return ObjectUtils.toString(obj, null);
	}
	
	
	/**
	 * 默认sql查询返回的date类型为java.sql.Date，会丢失时分秒，要保留时分秒，需要用to_char格式化，再转回来
	 * 
	 * sql中需要使用 to_char(ut.created_date,'YYYY-MM-DD hh24:mi:ss') 格式化
	 * 
	 * @param obj
	 * @return
	 */
	public static Date toDate(Object obj) {
		if (obj instanceof Date) {
			return (Date) obj;
		}
		
		if (obj instanceof String) {
			return DateUtil.parseTime((String)obj);
		}
		
		return null;
	}
}
