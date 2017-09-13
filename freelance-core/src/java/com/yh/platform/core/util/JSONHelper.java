
package com.yh.platform.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @description
 *
 * @author zhangqp
 * @created 16/01/09
 * @version 1.0
 */

public class JSONHelper {
	
	/**
	 * 默认成功标志+提示信息格式(messageHeader + e.getMessage())
	 * @param success
	 * @param e
	 * @param messageHeader messageHeader + e.getMessage()
	 * @return
	 */
	public static JSONObject fromObject(boolean success, Exception e, String messageHeader) {
		return fromObject(success, StringUtils.isEmpty(e.getMessage()) ? messageHeader : new StringBuilder(messageHeader).append(" ").append(e.getMessage()).toString());
	}
	
	/**
	 * 默认成功标志+提示信息格式，其中，<font color=red>成功情况下，如果不是必要的特殊提示，则不必设置message，置为null即可；失败情况下，必须写message（或使用 fromObject(boolean success, Exception e, String messageHeader)方法）</font>
	 * @param object
	 * @return
	 */
	public static JSONObject fromObject(boolean success, String message) {
		JSONObject json = new JSONObject();
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	/**
	 * 默认时间转为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param object
	 * @return
	 */
	public static JSONObject fromObject(Object object) {
		return fromObject(object, DateProcessor.DEFAULT_TIME_PROCESSOR, null);
	}
	
	/**
	 * 默认时间转为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param object
	 * @param ignoreFields 不需要的字段
	 * @return
	 */
	public static JSONObject fromObject(Object object, String[] ignoreFields) {
		return fromObject(object, DateProcessor.DEFAULT_TIME_PROCESSOR, ignoreFields);
	}
	
	public static JSONObject fromObject(Object object, JsonConfig jsonConfig) {
		return JSONObject.fromObject(object, jsonConfig);
	}
	
	public static JSONObject fromObject(Object object, String dateFormat) {
		return fromObject(object, DateProcessor.getDateProcessor(dateFormat), null);
	}
	
	public static JSONObject fromObject(Object object, String dateFormat, String[] ignoreFields) {
		return fromObject(object, new DateProcessor(dateFormat), ignoreFields);
	}
	
	/**
	 * 指定时间处理类
	 * @param object
	 * @param dateProcessor 时间处理类  yyyy-MM-dd格式-> JSONHelper.DateProcessor.DEFAULT_DATE_PROCESSOR
	 * @param ignoreFields 不需要的字段
	 * @return
	 */
	public static JSONObject fromObject(Object object, DateProcessor dateProcessor, String[] ignoreFields) {
		JsonConfig jsonConfig = new JsonConfig();
		if (dateProcessor != null) {
			jsonConfig.registerJsonValueProcessor(Date.class, dateProcessor);
		}
		if (!ArrayUtils.isEmpty(ignoreFields)) {
			jsonConfig.setExcludes(ignoreFields);
		}
		
		return fromObject(object, jsonConfig);
	}
	
	/**
	 *@description	date 转 string 格式
	 *
	 *@author		zhangqp
	 *@created		16/01/09
	 *@version		1.0
	 */
	public static class DateProcessor implements JsonValueProcessor {
		
		/**
		 * yyyy-MM-dd HH:mm:ss  日期格式   DateUtil.TIME_PATTERN_DEFAULT
		 */
		public static DateProcessor DEFAULT_TIME_PROCESSOR = new DateProcessor(DateUtil.TIME_PATTERN_DEFAULT);
		
		/**
		 * yyyy-MM-dd   日期格式   DateUtil.DATE_PATTERN_DEFAULT
		 */
		public static DateProcessor DEFAULT_DATE_PROCESSOR = new DateProcessor(DateUtil.DATE_PATTERN_DEFAULT);
		
		public static Map<String, DateProcessor> DATE_PROCESSORS = new HashMap<String, DateProcessor>(2);
		static {
			DATE_PROCESSORS.put(DateUtil.TIME_PATTERN_DEFAULT, DEFAULT_TIME_PROCESSOR);//yyyy-MM-dd HH:mm:ss
			DATE_PROCESSORS.put(DateUtil.DATE_PATTERN_DEFAULT, DEFAULT_DATE_PROCESSOR);//yyyy-MM-dd
		}

		private DateFormat			dateFormat;

	    private DateProcessor(String formater) {
	    	this.dateFormat = new SimpleDateFormat(formater);
	    }
	    
	    public static DateProcessor getDateProcessor(String formater) {
	    	DateProcessor dp = DATE_PROCESSORS.get(formater);
	    	
	    	return dp == null ? new DateProcessor(formater) : dp;
	    }
	    
		/**
		 * 转换数组？.
		 *
		 * @param value
		 *            Object
		 * @param jsonConfig
		 *            配置
		 * @return Object
		 */
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		/**
		 * 转换对象.
		 *
		 * @param key
		 *            String
		 * @param value
		 *            Object
		 * @param jsonConfig
		 *            配置
		 * @return Object
		 */
		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		/**
		 * 格式化日期.
		 *
		 * @param value
		 *            Object
		 * @return Object
		 */
		private Object process(Object value) {
			try {
				return dateFormat.format((Date) value);
			} catch (Exception ex) {
				return null;
			}
		}
	}
}
