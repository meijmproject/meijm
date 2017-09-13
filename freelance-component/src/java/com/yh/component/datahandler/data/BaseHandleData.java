package com.yh.component.datahandler.data;

import java.util.HashMap;

/**
 * @description 托盘数据
 * 
 * @author chencr
 * @created 2013-04-10
 * @version 1.0
 * 
 */
public class BaseHandleData {

	private HashMap<String, Object> dataMap = new HashMap<String, Object>(); // 数据存储对象

	public Object get(String key) {
		return dataMap.get(key);
	}

	public void put(String key, Object value) {
		dataMap.put(key, value);
	}

	public String toString() {
		return String.valueOf(dataMap);
	}
}
