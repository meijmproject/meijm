package com.yh.hr.res.gw.util;

import java.util.HashMap;
import java.util.Map;
/**
 * 岗位资源常量类
 * @author liuhw
 * 2016-8-24
 */
public class GwFlowConstants {
	
	/**
	 * 账户类型-单位
	 * 账户类型：1：单位，2区：3内设机构，99其它等
	 */
	public static final String ACCOUNT_TYPE_1 = "1";
	
	/**
	 * 账户类型-区
	 * 账户类型：1：单位，2区：3内设机构，99其它等
	 */
	public static final String ACCOUNT_TYPE_2 = "2";
	
	/**
	 * 账户类型-内设机构
	 * 账户类型：1：单位，2区：3内设机构，99其它等
	 */
	public static final String ACCOUNT_TYPE_3= "3";
	
	/**
	 * 账户类型-其它
	 * 账户类型：1：单位，2区：3内设机构，99其它等
	 */
	public static final String ACCOUNT_TYPE_99 = "99";
	
	
	/**
	 * 业务类型-业务台账（bizmovement）
	 * 1：movement(movementOid),2:biz(taskOid),99：other'
	 */
	public static final String BIZ_TYPE_1 = "1";
	/**
	 * 业务类型-业务流程（biztask）
	 * 1：movement(movementOid),2:biz(taskOid),99：other'
	 */
	public static final String BIZ_TYPE_2 = "2";
	/**
	 * 业务类型-其它业务（other）
	 * 1：movement(movementOid),2:biz(taskOid),99：other'
	 */
	public static final String BIZ_TYPE_99 = "99";
	
	/**
	 * 资源类型-岗位调整
	 * 1，岗位调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_1 = "1";
	
	
	/**
	 * 资源类型-释放岗位
	 * 1，岗位调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_2 = "2";
	
	/**
	 * 资源类型-释放
	 * 1，岗位调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_3 = "3";
	/**
	 * 资源类型-冻结
	 * 1，岗位调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_4 = "4";
	
	/**
	 * 资源类型-解冻
	 * 1，岗位调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_5 = "5";
	
	/**
	 * 资源类型-锁定
	 * 1，岗位调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_6= "6";
	
	/**
	 * 资源类型-解锁
	 * 1，岗位调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_7 = "7";
	
	/**
	 * 资源类型-其它
	 * 1，岗位调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_99 = "99";
	
	/**
	 * 岗位资源信息使用类型 1-冻结解冻
	 */
	private static final String INFO_TYPE_1="1";
	/**
	 * 岗位资源信息使用类型2-锁定解锁
	 */
	private static final String INFO_TYPE_2="2";
	
	/**
	 * 岗位资源信息使用类型 3-占用释放
	 */
	private static final String INFO_TYPE_3="3";
	
	
	/**
	 * 岗位资源使用情况对应表
	 */
	private static Map<String, String> infoTypeMap =new HashMap<String, String>();
	public static Map<String,String> getInfoTypeMap()
	{
		infoTypeMap.put(RES_TYPE_2, INFO_TYPE_3);
		infoTypeMap.put(RES_TYPE_3, INFO_TYPE_3);
		infoTypeMap.put(RES_TYPE_4, INFO_TYPE_1);
		infoTypeMap.put(RES_TYPE_5, INFO_TYPE_1);
		infoTypeMap.put(RES_TYPE_6, INFO_TYPE_2);
		infoTypeMap.put(RES_TYPE_7, INFO_TYPE_2);
		return infoTypeMap;
	}
	
	/**
	 * 岗位资源使用状态对应表-查询使用情况表信息时使用
	 */
	private static Map<String, String> statusMap =new HashMap<String, String>();
	public static Map<String,String> getStatusMap()
	{
		statusMap.put(RES_TYPE_3, RES_TYPE_2);
		statusMap.put(RES_TYPE_5, RES_TYPE_4);
		statusMap.put(RES_TYPE_7, RES_TYPE_6);
		return statusMap;
	}
	
	/**
	 * 使用类型
	 * inGwType:岗位占用情况;freezeGwType:岗位冻结情况；lockGwType:查询岗位锁定情况
	 */
	public static final String USE_TYPE_IN_GW = "inGwType";//查询岗位占用情况
	
	public static final String USE_TYPE_FREEZE_GW = "freezeGwType";//查询岗位冻结情况
	
	public static final String USE_TYPE_LOCK_GW = "lockGwType";//查询岗位锁定情况
	
	/**
	 * 账户类型名称对应表
	 */
	private static Map<String, String> accountTypeNameMap =new HashMap<String, String>();;
	
	public static Map<String, String> getAccountTypeNameMap()
	{
		 accountTypeNameMap.put(ACCOUNT_TYPE_1, "单位");
		 accountTypeNameMap.put(ACCOUNT_TYPE_2, "区");
		 accountTypeNameMap.put(ACCOUNT_TYPE_3, "内设机构");
		 accountTypeNameMap.put(ACCOUNT_TYPE_99, "其它");
		 //TODO add elements
		return bizTypeNameMap;
	}
	
	/**
	 * 业务类型名称对应表
	 */
	private static Map<String, String> bizTypeNameMap =new HashMap<String, String>();;
	
	public static Map<String, String> getBizTypeNameMap()
	{
		 bizTypeNameMap.put(BIZ_TYPE_1, "业务台账");
		 bizTypeNameMap.put(BIZ_TYPE_2, "业务流程");
		 bizTypeNameMap.put(BIZ_TYPE_99, "其它业务");
		 //TODO add elements
		return bizTypeNameMap;
	}
	 
	 /**
	 * 资源类型名称对应表
	 */
	private static Map<String, String> resTypeNameMap =new HashMap<String, String>();;
	
	public static Map<String, String> getResTypeNameMap()
	{
		 resTypeNameMap.put(RES_TYPE_1, "岗位调整");
		 resTypeNameMap.put(RES_TYPE_2, "占用");
		 resTypeNameMap.put(RES_TYPE_3, "释放");
		 resTypeNameMap.put(RES_TYPE_4, "冻结");
		 resTypeNameMap.put(RES_TYPE_5, "解冻");
		 resTypeNameMap.put(RES_TYPE_6, "锁定");
		 resTypeNameMap.put(RES_TYPE_7, "解锁");
		 resTypeNameMap.put(RES_TYPE_99, "其它");
		 //TODO add elements
		return resTypeNameMap;
	}
	
	
	/**
	 * 岗位类型 - 1
	 * 1：正常（单岗或双肩挑主岗）、2：双肩挑（专技岗）、3：是否保留待遇
	 */
	public static final String POSITION_TYPE_1 = "1";
	
	/**
	 * 岗位类型 - 2
	 * 1：正常（单岗或双肩挑主岗）、2：双肩挑（专技岗）、3：是否保留待遇
	 */
	public static final String POSITION_TYPE_2 = "2";
	
	/**
	 * 岗位类型 - 3
	 * 1：正常（单岗或双肩挑主岗）、2：双肩挑（专技岗）、3：是否保留待遇
	 */
	public static final String POSITION_TYPE_3 = "3";
		
	/**
	 * 专业技术等级
	 * 注意：value 头尾不需加''号
	 */
	private static HashMap<String, String> profTechGrade = new HashMap<String, String>();
	static 
	{
		profTechGrade.put("A1020010010010", "A1020010010010','A1020010010020','A1020010010030','A1020010010040");//正高级(一级)
		profTechGrade.put("A1020010010020", "A1020010010010','A1020010010020','A1020010010030','A1020010010040");//正高级(二级)
		profTechGrade.put("A1020010010030", "A1020010010010','A1020010010020','A1020010010030','A1020010010040");//正高级(三级)
		profTechGrade.put("A1020010010040", "A1020010010010','A1020010010020','A1020010010030','A1020010010040");//正高级(四级)
		profTechGrade.put("A1020010020010", "A1020010020010','A1020010020020','A1020010020030");//副高级(五级)
		profTechGrade.put("A1020010020020", "A1020010020010','A1020010020020','A1020010020030");//副高级(六级)
		profTechGrade.put("A1020010020030", "A1020010020010','A1020010020020','A1020010020030");//副高级(七级)
		profTechGrade.put("A1020020010", "A1020020010','A1020020020','A1020020030");//中级(八级)
		profTechGrade.put("A1020020020", "A1020020010','A1020020020','A1020020030");//中级(九级)
		profTechGrade.put("A1020020030", "A1020020010','A1020020020','A1020020030");//中级(十级)
//		profTechGrade.put("A1020030010", "A1020010010010','A1020010010020','A1020010010030','A1020010010040','A1020010020010','A1020010020020','A1020010020030','A1020020010','A1020020020','A1020020030','A1020030010','A1020030020','A1020030030");//初级(十一级)
//		profTechGrade.put("A1020030020", "A1020010010010','A1020010010020','A1020010010030','A1020010010040','A1020010020010','A1020010020020','A1020010020030','A1020020010','A1020020020','A1020020030','A1020030010','A1020030020','A1020030030");//初级(十二级)
//		profTechGrade.put("A1020030030", "A1020010010010','A1020010010020','A1020010010030','A1020010010040','A1020010020010','A1020010020020','A1020010020030','A1020020010','A1020020020','A1020020030','A1020030010','A1020030020','A1020030030");//初级(十三级)
	}
	public static HashMap<String, String> getProfTechGrade()
	{
		return profTechGrade;
	}
	
	/**
	 * 是否检查专业技术级别
	 */
	private static HashMap<String, String> profTechLevel = new HashMap<String, String>();
	static 
	{
		profTechLevel.put("A1020010010040", "A1020010010040");//正高级(四级)
		profTechLevel.put("A1020010020030", "A1020010020030");//副高级(七级)
		profTechLevel.put("A1020020030", "A1020020030");//中级(十级)
		profTechLevel.put("A1020030010", "A1020030010");//初级(十一级)
		profTechLevel.put("A1020030020", "A1020030020");//初级(十二级)
		profTechLevel.put("A1020030030", "A1020030030");//初级(十三级)
	}
	public static HashMap<String, String> isCheckProfTechLevel()
	{
		return profTechLevel;
	}
}
