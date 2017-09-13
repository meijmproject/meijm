package com.yh.hr.component.yngw.util;

import java.util.HashMap;
import java.util.Map;

public class GwYnInfoConstants {
	
	/** 工作类别 */
	public static final String	YHRS0112	=	"YHRS0112";
	/** 临床 */
	public static final String	YHRS0112_1	=	"1";
	/** 行政与后勤 */
	public static final String	YHRS0112_2	=	"2";
	/** 研究人员 */
	public static final String	YHRS0112_3	=	"3";
	/** 工勤 */
	public static final String	YHRS0112_4	=	"4";
	
	
	
	/** 岗位类别 */
	public static final String	YHRS0113	=	"YHRS0113";
	/** 医生 */
	public static final String	YHRS0113_1	=	"1";
	/** 技师 */
	public static final String	YHRS0113_2	=	"2";
	/** 药师 */
	public static final String	YHRS0113_3	=	"3";
	/** 护理 */
	public static final String	YHRS0113_4	=	"4";
	/** 管理类 */
	public static final String	YHRS0113_5	=	"5";
	/** 工勤 */
	public static final String	YHRS0113_6	=	"6";
	/** 研究 */
	public static final String	YHRS0113_7	=	"7";

	
	private static Map<String, String> hisWorkTypeMap = new HashMap<String, String>();
	public static Map<String, String> getHisWorkTypeMap()
	{
		hisWorkTypeMap.put(YHRS0112_1, YHRS0113_1+","+YHRS0113_2+","+YHRS0113_3+","+YHRS0113_4);
		hisWorkTypeMap.put(YHRS0112_2, YHRS0113_5);
		hisWorkTypeMap.put(YHRS0112_4, YHRS0113_6);
		hisWorkTypeMap.put(YHRS0112_3, YHRS0113_7);
		return hisWorkTypeMap;
	}
}
