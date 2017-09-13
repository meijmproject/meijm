package com.yh.hr.res.hc.util;

import java.util.HashMap;
import java.util.Map;

public class HcFlowConstants {
	
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
	 * 资源类型-编制调整
	 * 1，编制调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_1 = "1";
	
	
	/**
	 * 资源类型-占用
	 * 1，编制调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_2 = "2";
	
	/**
	 * 资源类型-释放
	 * 1，编制调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_3 = "3";
	/**
	 * 资源类型-冻结
	 * 1，编制调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_4 = "4";
	
	/**
	 * 资源类型-解冻
	 * 1，编制调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_5 = "5";
	
	/**
	 * 资源类型-锁定
	 * 1，编制调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_6= "6";
	
	/**
	 * 资源类型-解锁
	 * 1，编制调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_7 = "7";
	
	/**
	 * 资源类型-其它
	 * 1，编制调整，2:占用，3释放，4冻结，5解冻，6锁定，7解锁，99其它
	 */
	public static final String RES_TYPE_99 = "99";
	
	/**
	 * 编制资源信息使用类型 1-冻结解冻
	 */
	private static final String INFO_TYPE_1="1";
	/**
	 * 编制资源信息使用类型2-锁定解锁
	 */
	private static final String INFO_TYPE_2="2";
	
	/**
	 * 编制资源信息使用类型 3-占用释放
	 */
	private static final String INFO_TYPE_3="3";
	
	
	/**
	 * 编制资源使用情况对应表
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
	 * 编制资源使用状态对应表-查询使用情况表信息时使用
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
		 resTypeNameMap.put(RES_TYPE_1, "编制调整");
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
}
