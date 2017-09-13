package com.yh.hr.res.dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.platform.core.constant.Constant;

public class WageConstants {
	public static final Map<String, String> DUTYATTRIBUTE_DIC_TO_WAGE = new HashMap<String, String>();
	static{

		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_010110, Constant.YES);
		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_010120, Constant.YES);
		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_030, Constant.NO);
		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_10, Constant.YES);
		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_11, Constant.YES);
		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_21, Constant.YES);
		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_22, Constant.YES);
		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_31, Constant.NO);
		DUTYATTRIBUTE_DIC_TO_WAGE.put(DicConstants.YHRS0028_9, Constant.NO);
	}
	/* ************************* 机关工资项 */
	/**
	 * 工资项编码：级别工资
	 */
	public static String WAGE_ITEM_LEVEL_WAGE = "LEVEL_WAGE";
	/**
	 * 工资项编码：职务工资
	 */
	public static String WAGE_ITEM_DUTY_WAGE = "DUTY_WAGE";
	/**
	 * 工资项编码：技术工人等级工资
	 */
	public static String WAGE_ITEM_TECH_GRADE_WAGE = "TECH_GRADE_WAGE";
	/**
	 * 工资项编码：机关工人岗位工资
	 */
	public static String WAGE_ITEM_WORKER_POSITION_WAGE = "WORKER_POSITION_WAGE";
	/**
	 * 工资项编码：机关公务员见习期工资
	 */
	public static String WAGE_ITEM_PROBATION_WAGE= "PROBATION_WAGE";
	/**
	 * 工资项编码：机关公务员初期工资
	 */        
	public static String WAGE_ITEM_PRIME_WAGE = "PRIME_WAGE";
	/**
	 * 工资项编码：生活性补贴
	 */
	public static String WAGE_ITEM_LIFE_ALLOWANCE = "LIFE_ALLOWANCE";
	/**
	 * 工资项编码：工作性津贴
	 */
	public static String WAGE_ITEM_JOB_ALLOWANCE = "JOB_ALLOWANCE";
	/**
	 * 工资项编码：见习期生活性补贴
	 */
	public static String WAGE_ITEM_PROBATION_LIFE_SUBSIDY= "PROBATION_LIFE_SUBSIDY";
	/**
	 * 工资项编码：见习期工作性津贴
	 */
	public static String WAGE_ITEM_PROBATION_JOB_ALLOWANCE = "PROBATION_JOB_ALLOWANCE";

	/* ****************************机关end */
	/**
	 * 工资项编码：事业单位岗位工资
	 */
	public static String WAGE_ITEM_POSITION_WAGE = "POSITION_WAGE";
	/**
	 * 工资项编码：薪级工资
	 */
	public static String WAGE_ITEM_SALARY_LEVEL_WAGE = "SALARY_LEVEL_WAGE";
	/**
	 * 工资项编码：事业单位见习期工资
	 */
	public static String WAGE_ITEM_INST_PROBATION_WAGE = "INST_PROBATION_WAGE";
	/**
	 * 工资项编码：事业单位初期工资
	 */
	public static String WAGE_ITEM_INST_EARLY_WAGE = "INST_EARLY_WAGE";
	/**
	 * 工资项编码：岗位津贴
	 */
	public static String WAGE_ITEM_POSITION_ALLOWANCE = "POSITION_ALLOWANCE";
	/**
	 * 工资项编码：见习期岗位津贴
	 */
	public static String WAGE_ITEM_PRO_POSITION_ALLOWANCE = "PRO_POSITION_ALLOWANCE";
	/**
	 * 工资项编码：生活补贴
	 */
	public static String WAGE_ITEM_LIVING_SUBSIDY = "LIVING_SUBSIDY";		
	/**
	 * 工资项编码：工龄补贴
	 */
	public static String WAGE_ITEM_WORK_YEAR_WAGE = "WORK_YEAR_WAGE";		
	/**
	 * 工资项编码：浮动薪级工资
	 */
	public static String WAGE_ITEM_FLOATED_SALARY_WAGE = "FLOATED_SALARY_WAGE";		
	/**
	 * 工资项编码：教师护士上浮工资
	 */
	public static String WAGE_ITEM_TEACHER_NURSE_ALLOWANCE = "TEACHER_NURSE_ALLOWANCE";		
	

	/* ************************* 机关工资项 */
	/**
	 * 工资项编码：级别工资
	 */
	public static String WAGE_ITEM_LEVEL_WAGE_NAME = "级别工资";
	/**
	 * 工资项编码：职务工资
	 */
	public static String WAGE_ITEM_DUTY_WAGE_NAME = "职务工资";
	/**
	 * 工资项编码：技术工人等级工资
	 */
	public static String WAGE_ITEM_TECH_GRADE_WAGE_NAME = "技术工人等级工资";
	/**
	 * 工资项编码：机关工人岗位工资
	 */
	public static String WAGE_ITEM_WORKER_POSITION_WAGE_NAME = "岗位工资";
	/**
	 * 工资项编码：机关公务员见习期工资
	 */
	public static String WAGE_ITEM_PROBATION_WAGE_NAME = "见习期工资";
	/**
	 * 工资项编码：机关公务员初期工资
	 */
	public static String WAGE_ITEM_PRIME_WAGE_NAME = "初期工资";
	/**
	 * 工资项编码：生活性补贴
	 */
	public static String WAGE_ITEM_LIFE_ALLOWANCE_NAME = "生活性补贴";
	/**
	 * 工资项编码：工作性津贴
	 */
	public static String WAGE_ITEM_JOB_ALLOWANCE_NAME = "工作性津贴";
	/**
	 * 工资项编码：见习期生活性补贴
	 */
	public static String WAGE_ITEM_PROBATION_LIFE_SUBSIDY_NAME = "生活性补贴";
	/**
	 * 工资项编码：见习期工作性津贴
	 */
	public static String WAGE_ITEM_PROBATION_JOB_ALLOWANCE_NAME = "工作性津贴";

	/* ****************************机关end */
	

	/**
	 * 工资项编码：事业单位岗位工资
	 */
	public static String WAGE_ITEM_POSITION_WAGE_NAME = "岗位工资";
	/**
	 * 工资项编码：薪级工资
	 */
	public static String WAGE_ITEM_SALARY_LEVEL_WAGE_NAME = "薪级工资";
	/**
	 * 工资项编码：事业单位见习期工资
	 */
	public static String WAGE_ITEM_INST_PROBATION_WAGE_NAME = "见习期工资";
	/**
	 * 工资项编码：事业单位初期工资
	 */
	public static String WAGE_ITEM_INST_EARLY_WAGE_NAME = "初期工资";
	/**
	 * 工资项编码：岗位津贴
	 */
	public static String WAGE_ITEM_POSITION_ALLOWANCE_NAME = "岗位津贴";
	/**
	 * 工资项编码：见习期岗位津贴
	 */
	public static String WAGE_ITEM_PRO_POSITION_ALLOWANCE_NAME = "岗位津贴";
	/**
	 * 工资项编码：生活补贴
	 */
	public static String WAGE_ITEM_LIVING_SUBSIDY_NAME = "生活补贴";		
	/**
	 * 工资项编码：工龄补贴
	 */
	public static String WAGE_ITEM_WORK_YEAR_WAGE_NAME = "工龄补贴";
	/**
	 * 工资项编码：浮动薪级工资
	 */
	public static String WAGE_ITEM_FLOATED_SALARY_WAGE_NAME = "浮动薪级工资";		
	/**
	 * 工资项编码：教师护士上浮工资
	 */
	public static String WAGE_ITEM_TEACHER_NURSE_ALLOWANCE_NAME = "教师护士上浮工资";		
	
	
	/**
	 * 逻辑属性Code - 薪级不晋升标识
	 */
	public static String LOGIC_CODE_SALARY_LEVEL_NOT_PROMOTE_FLAG = "SALARY_LEVEL_NOT_PROMOTE_FLAG";
	/**
	 * 逻辑属性Code - 级别晋升累计数
	 */
	public static String LOGIC_CODE_PROMOTE_RANK_COUNT = "PROMOTE_RANK_COUNT";
	/**
	 * 逻辑属性Code - 档次晋升累计数
	 */
	public static String LOGIC_CODE_PROMOTE_GRADE_COUNT = "PROMOTE_GRADE_COUNT";
	/**
	 * 逻辑属性Code - 高定级别
	 */
	public static String LOGIC_CODE_HIGE_SET_RANK = "HIGE_SET_RANK";
	/**
	 * 逻辑属性Code - 高定档次
	 */
	public static String LOGIC_CODE_HIGE_SET_GRADE = "HIGE_SET_GRADE";
	/**
	 * 逻辑属性Code - 岗位级别晋升累计数
	 */
	public static String LOGIC_CODE_PROMOTE_POSITION_COUNT = "PROMOTE_POSITION_COUNT";
	/**
	 * 逻辑属性Code - 浮动日期
	 */
	public static String LOGIC_CODE_FLOAT_SALARY_LEVEL_DATE = "FLOAT_SALARY_LEVEL_DATE";
	
	
	/**
	 * 逻辑属性Name - 薪级不晋升标识
	 */
	public static String LOGIC_CODE_SALARY_LEVEL_NOT_PROMOTE_FLAG_STR = "薪级不晋升标识";
	/**
	 * 逻辑属性Name - 薪级不晋升标识
	 */
	public static String LOGIC_CODE_PROMOTE_RANK_COUNT_STR = "级别晋升累计数";
	/**
	 * 逻辑属性Name - 薪级不晋升标识
	 */
	public static String LOGIC_CODE_PROMOTE_GRADE_COUNT_STR = "档次晋升累计数";
	/**
	 * 逻辑属性Name - 薪级不晋升标识
	 */
	public static String LOGIC_CODE_HIGE_SET_RANK_STR = "高定级别";
	/**
	 * 逻辑属性Name - 薪级不晋升标识
	 */
	public static String LOGIC_CODE_HIGE_SET_GRADE_STR = "高定档次";
	/**
	 * 逻辑属性Name - 岗位级别晋升累计数
	 */
	public static String LOGIC_CODE_PROMOTE_POSITION_COUNT_STR = "岗位级别晋升累计数";
	/**
	 * 逻辑属性Name - 浮动日期
	 */
	public static String LOGIC_CODE_FLOAT_SALARY_LEVEL_DATE_STR = "浮动日期";
	
	
	
	public static Map<String,String> WAGE_ITEM_CODE_TO_NAME = new HashMap<String, String>();
	static{
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_LEVEL_WAGE,WAGE_ITEM_LEVEL_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_DUTY_WAGE,WAGE_ITEM_DUTY_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_TECH_GRADE_WAGE,WAGE_ITEM_TECH_GRADE_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_WORKER_POSITION_WAGE,WAGE_ITEM_WORKER_POSITION_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_PROBATION_WAGE,WAGE_ITEM_PROBATION_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_PRIME_WAGE,WAGE_ITEM_PRIME_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_LIFE_ALLOWANCE,WAGE_ITEM_LIFE_ALLOWANCE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_JOB_ALLOWANCE,WAGE_ITEM_JOB_ALLOWANCE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_PROBATION_LIFE_SUBSIDY,WAGE_ITEM_PROBATION_LIFE_SUBSIDY_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_PROBATION_JOB_ALLOWANCE,WAGE_ITEM_PROBATION_JOB_ALLOWANCE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_POSITION_WAGE,WAGE_ITEM_POSITION_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_SALARY_LEVEL_WAGE,WAGE_ITEM_SALARY_LEVEL_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_INST_PROBATION_WAGE,WAGE_ITEM_INST_PROBATION_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_INST_EARLY_WAGE,WAGE_ITEM_INST_EARLY_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_POSITION_ALLOWANCE,WAGE_ITEM_POSITION_ALLOWANCE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_PRO_POSITION_ALLOWANCE,WAGE_ITEM_PRO_POSITION_ALLOWANCE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_LIVING_SUBSIDY,WAGE_ITEM_LIVING_SUBSIDY_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_WORK_YEAR_WAGE,WAGE_ITEM_WORK_YEAR_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_FLOATED_SALARY_WAGE,WAGE_ITEM_FLOATED_SALARY_WAGE_NAME);
		WAGE_ITEM_CODE_TO_NAME.put(WAGE_ITEM_TEACHER_NURSE_ALLOWANCE,WAGE_ITEM_TEACHER_NURSE_ALLOWANCE_NAME);
	}
	
	/**
	 * 公务员在职工资结构
	 * 	职务工资、级别工资、生活性补贴、工作性津贴
	 */
	public static List<String> WAGE_ITEM_STRUCT_ON_JOB_OFFICIAL = Arrays.asList(new String[]{
			WAGE_ITEM_LEVEL_WAGE,
			WAGE_ITEM_DUTY_WAGE,
			WAGE_ITEM_LIFE_ALLOWANCE,
			WAGE_ITEM_JOB_ALLOWANCE
	});
	/**
	 * 机关技术工人在职工资结构
	 *  技术等级工资、岗位工资、生活性补贴、工作性津贴
	 */
	public static List<String> WAGE_ITEM_STRUCT_ON_JOB_OFF_SKILL_WORKER = Arrays.asList(new String[]{
			WAGE_ITEM_TECH_GRADE_WAGE,
			WAGE_ITEM_WORKER_POSITION_WAGE,
			WAGE_ITEM_LIFE_ALLOWANCE,
			WAGE_ITEM_JOB_ALLOWANCE
	});
	/**
	 * 机关普通工人在职工资结构
	 *  等级工资、生活性补贴、工作性津贴
	 */
	public static List<String> WAGE_ITEM_STRUCT_ON_JOB_OFF_WORKER = Arrays.asList(new String[]{
			//WAGE_ITEM_TECH_GRADE_WAGE,
			WAGE_ITEM_WORKER_POSITION_WAGE,
			WAGE_ITEM_LIFE_ALLOWANCE,
			WAGE_ITEM_JOB_ALLOWANCE
	});
	/**
	 * 机关公务员初级人员工资结构（学历等级为硕士学位的研究生及以上）
	 *  初期工资、见习期工作性津贴、见习期生活性补贴
	 */
	public static List<String> WAGE_ITEM_STRUCT_EARLY_OFFICIAL = Arrays.asList(new String[]{
			WAGE_ITEM_PRIME_WAGE,
			WAGE_ITEM_PROBATION_LIFE_SUBSIDY,
			WAGE_ITEM_JOB_ALLOWANCE
	});
	/**
	 * 机关公务员见习期工资工资结构（起薪时间点的最高学历，学历等级为硕士学位的研究生以下）
	 *  见习期工资、见习期工作性津贴、见习期生活性补贴
	 */
	public static List<String> WAGE_ITEM_STRUCT_PRO_OFFICIAL = Arrays.asList(new String[]{
			WAGE_ITEM_PROBATION_WAGE,
			WAGE_ITEM_PROBATION_LIFE_SUBSIDY,
			WAGE_ITEM_PROBATION_JOB_ALLOWANCE
	});


	/**
	 * 事业单位在职工资结构
	 *  薪级工资、岗位工资、岗位津贴、生活补贴、工龄补贴
	 */
	public static List<String> WAGE_ITEM_STRUCT_ON_JOB_INST = Arrays.asList(new String[]{
			WAGE_ITEM_POSITION_WAGE,
			WAGE_ITEM_SALARY_LEVEL_WAGE,
			WAGE_ITEM_POSITION_ALLOWANCE,
			WAGE_ITEM_LIVING_SUBSIDY,
			WAGE_ITEM_WORK_YEAR_WAGE
	});
	/**
	 * 事业单位见习期工资结构（学历等级为硕士学位的研究生及以下）
	 *  见习期工资、见习期岗位津贴、生活补贴、工龄补贴
	 */
	public static List<String> WAGE_ITEM_STRUCT_PRO_INST = Arrays.asList(new String[]{
			WAGE_ITEM_INST_PROBATION_WAGE,
			WAGE_ITEM_PRO_POSITION_ALLOWANCE,
			WAGE_ITEM_LIVING_SUBSIDY,
			WAGE_ITEM_WORK_YEAR_WAGE
	});
	/**
	 * 事业单位初级工资结构（学历等级为硕士学位的研究生及以上）
	 *  初期工资、见习期岗位津贴、生活补贴、工龄补贴
	 */
	public static List<String> WAGE_ITEM_STRUCT_EARLY_INST = Arrays.asList(new String[]{
			WAGE_ITEM_INST_EARLY_WAGE,
			WAGE_ITEM_PRO_POSITION_ALLOWANCE,
			WAGE_ITEM_LIVING_SUBSIDY,
			WAGE_ITEM_WORK_YEAR_WAGE
	});
	
	public static Map<String, List<String>> WAGE_SERIES_TO_WAGE_ITEM_STRUCT = new HashMap<String, List<String>>();
	static{
		WAGE_SERIES_TO_WAGE_ITEM_STRUCT.put(DicConstants.YHRS0088_010, WAGE_ITEM_STRUCT_ON_JOB_OFFICIAL);
		WAGE_SERIES_TO_WAGE_ITEM_STRUCT.put(DicConstants.YHRS0088_020, WAGE_ITEM_STRUCT_ON_JOB_OFF_SKILL_WORKER);
		WAGE_SERIES_TO_WAGE_ITEM_STRUCT.put(DicConstants.YHRS0088_030, WAGE_ITEM_STRUCT_ON_JOB_OFF_WORKER);
		
		WAGE_SERIES_TO_WAGE_ITEM_STRUCT.put(DicConstants.YHRS0088_040, WAGE_ITEM_STRUCT_ON_JOB_INST);
		WAGE_SERIES_TO_WAGE_ITEM_STRUCT.put(DicConstants.YHRS0088_050, WAGE_ITEM_STRUCT_ON_JOB_INST);
		WAGE_SERIES_TO_WAGE_ITEM_STRUCT.put(DicConstants.YHRS0088_060, WAGE_ITEM_STRUCT_ON_JOB_INST);
		WAGE_SERIES_TO_WAGE_ITEM_STRUCT.put(DicConstants.YHRS0088_070, WAGE_ITEM_STRUCT_ON_JOB_INST);
	}
	
	/* ****************************特岗津贴用操作类型huanglm*/
	/** 原始 */
	public static final String	HANDLE_CODE_01	= "1";	
	public static final String	HANDLE_CODE_001	= "原始";	
	/** 新增 */
	public static final String	HANDLE_CODE_02	= "2";	
	public static final String	HANDLE_CODE_002	= "新增";	
	/** 变更 */
	public static final String	HANDLE_CODE_03	= "3";	
	public static final String	HANDLE_CODE_003	= "变更";	
	/** 撤消 */
	public static final String	HANDLE_CODE_04	= "4";	
	public static final String	HANDLE_CODE_004	= "撤消";	
}

