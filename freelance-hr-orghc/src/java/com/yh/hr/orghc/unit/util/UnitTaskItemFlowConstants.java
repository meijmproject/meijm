package com.yh.hr.orghc.unit.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 单位 事项节点常量类
 * @author zhengdr
 *
 * 时间:2016-12-21下午01:41:48
 */
public class UnitTaskItemFlowConstants {

	/**
	 * 主管单位创建
	 */
	public static String ITEM_CODE_99995100 = "99995100";
	public static String ITEM_NODE_CODE_999951001110 = "999951001110";//主管单位申报
	
	/**
	 * 下设单位创建
	 */
	public static String ITEM_CODE_99995200 = "99995200";
	public static String ITEM_NODE_CODE_999952001110 = "999952001110";//主管单位申报
	
	/**
	 * 主管单位撤销
	 */
	public static String ITEM_CODE_99995110 = "99995110";
	public static String ITEM_NODE_CODE_999951101110 = "999951101110";//主管单位申报
	
	/**
	 * 下级单位撤销
	 */
	public static String ITEM_CODE_99995210 = "99995210";
	public static String ITEM_NODE_CODE_999952101110 = "999952101110";//主管单位申报
	
	/*
	 * 单位信息管理
	 */
	public static String ITEM_CODE_99995300 = "99995300";
	public static String ITEM_NODE_CODE_999953001010 = "999953001010";//单位申报
	
	
	
	/**
	 * 内设机构级别集合
	 */
	private static Map<String, String> levelCodeMap =new HashMap<String, String>();
	public static Map<String,String> getLevelCodeMap()
	{
		//降半级
		levelCodeMap.put("101","102");
		levelCodeMap.put("102", "103");
		levelCodeMap.put("103", "104");
		levelCodeMap.put("104", "105");
		levelCodeMap.put("105", "106");
		levelCodeMap.put("106", "107");
		levelCodeMap.put("107", "108");
		levelCodeMap.put("108", "109");
		levelCodeMap.put("109", "113");
		levelCodeMap.put("113", "113");
		
		return levelCodeMap;
	}
}
