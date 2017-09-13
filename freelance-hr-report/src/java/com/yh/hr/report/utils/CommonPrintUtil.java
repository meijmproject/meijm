package com.yh.hr.report.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;

public class CommonPrintUtil
{
	
	public static final String CONSTANTS_ONE = "1";
	public static final String CONSTANTS_TWO = "2";
	public static final String CONSTANTS_THREE = "3";

	/**
	 * 简历信息按时间排序
     * <br>(如果没有起始时间,则过滤掉)
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> sortList(List<String> list)
	{
		List<String> sortList = new ArrayList<String>();
		String maxInfo = "";
		String curInfo = "";
		int max = 0;
		int cur = 0;
		int maxindex = 0;
		for (int i = 0; i < list.size(); i++)
		{
			maxindex = i;
            maxInfo = list.get(i);
            if(maxInfo.length() < 10 || !maxInfo.substring(4,5).equals("-")){
                continue;
            }
            max = Integer.parseInt((String) list.get(i).substring(0, 10).replace("-", ""));
			for (int j = i + 1; j < list.size(); j++)
			{
                curInfo = list.get(j);
                if(curInfo.length() < 10 || !curInfo.substring(4,5).equals("-")){
                    continue;
                }
                cur = Integer.parseInt((String) list.get(j).substring(0, 10).replace("-", ""));
				if (max < cur)
				{
					maxindex = j;
					max = cur;
					maxInfo = curInfo;
				}
			}
			sortList.add(maxInfo);
			if (maxindex != i)
			{
				list.set(maxindex, list.get(i));
			}
		}

		return sortList;
	}
	
	/**
	 * 根据生日计算年龄
	 * 
	 * @param date
	 * @return
	 */
	public static String caculateAge(Date birthDate, Date baseDate)
	{
		if (birthDate == null) { return ""; }

		Calendar birthday = Calendar.getInstance();
		birthday.setTime(birthDate);
		Calendar now = Calendar.getInstance();
		if(baseDate!=null)
		{
			now.setTime(baseDate);
		}

		int birthYear = birthday.get(Calendar.YEAR);
		int nowYear = now.get(Calendar.YEAR);

		int birthMonth = birthday.get(Calendar.MONTH);
		int nowMonth = now.get(Calendar.MONTH);

		
		int years = nowYear-birthYear;
		if(years<1) return "";
		
		int months = nowMonth-birthMonth;
		if(months<0)
		{
			years = years -1;
			//months = months + 12;
		}
		String age = years + "岁";
//		if(months != 0)
//		{
//			age = age + months +"月";
//		}
		return age;
	}
	
    /**
	 * 判断对象是否为空
	 * @param Object
	 * @param type : 日期类型转换指定格式需要填写、后面需要转换的形式,ItemCode需要转换成ItemName、后面需要传ItemCode
	 * @return String
     * @throws ServiceException 
	 */
	public static String checkObjIsNull(Object obj,String type) throws ServiceException
	{
		if(null == obj || "".equals(obj))
		{
			return "/";
		}
		else if(obj instanceof java.util.Date && null != type)
		{
			return DateUtil.format((Date) obj, type);
		}
		else if(obj instanceof java.util.Date && null == type)
		{	
			return DateUtil.formatDate((Date) obj);
		}
		else if((obj instanceof String || obj instanceof Long || obj instanceof Integer) && null != type)
		{	
			/*return HrBizConstants.handleDutyLevelName(DicHelper.viewName(type, (String) obj));*/
			return DicHelper.viewName(type, (String) obj);
		}
		else 
		{
			return obj.toString();
		}
	}
	
	
	
}
