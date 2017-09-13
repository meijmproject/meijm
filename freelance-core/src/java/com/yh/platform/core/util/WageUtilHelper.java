package com.yh.platform.core.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class WageUtilHelper {
	public static Date getDateFromStr(String s)
	{
		return DateUtil.parse(s, DateUtil.DATA_PATTERN_YYYYMMDD);
	}

	/**
	 * 根据日期获得年月日字符串值
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date)
	{
		if (date == null)
			return "";

		String str = DateUtil.formatDate(date);

		str = str.replaceAll("\\D", "");

		return str;
	}

	/**
	 * 对两个日期进行比较，如日期1小于日期2则返回-1，相等则返回0，大于则返回1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	private static int compareDate(Date date1, Date date2)
	{
		if (date1 == null || date2 == null)
			return -1;

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(getDateFromStr(dateToStr(date1)));
		cal2.setTime(getDateFromStr(dateToStr(date2)));

		return cal1.compareTo(cal2);
	}

	/**
	 * 日期（orgDate）早于日期(date)
	 * 
	 * @param orgDate
	 * @param date
	 * @return
	 */
	public static boolean orgDateLtDate(Date orgDate, Date date)
	{
		return (compareDate(orgDate, date) == -1);
	}

	/**
	 * 日期（orgDate）晚于日期(date)
	 * 
	 * @param orgDate
	 * @param date
	 * @return
	 */
	public static boolean orgDateGtDate(Date orgDate, Date date)
	{
		return (compareDate(orgDate, date) == 1);
	}

	/**
	 * 日期（orgDate）等于日期(date)
	 * 
	 * @param orgDate
	 * @param date
	 * @return
	 */
	public static boolean orgDateEqDate(Date orgDate, Date date)
	{
		return (compareDate(orgDate, date) == 0);
	}

	/**
	 * 根据指定日期月份最后天,得到个新日期
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getDateForMonthLastDate(java.util.Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// c.add(Calendar.MONTH);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得某日期的下一个月的1号，一般用来获取起薪日期
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getNextMonthFirstDay(Date currentDate)
	{
		Calendar c = Calendar.getInstance();

		c.setTime(currentDate);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);

		return c.getTime();
	}
	
	/**
	 * 取得当前日期1号，一般用来获取起薪日期
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getCurrentMonthFirstDay(Date currentDate)
	{
		Calendar c = Calendar.getInstance();

		c.setTime(currentDate);
		c.set(Calendar.DAY_OF_MONTH, 1);

		return c.getTime();
	}

	public static Double getDouble(Double obj)
	{
		return (obj == null ? 0d : obj);
	}

	public static Long getLong(Long obj)
	{
		return (obj == null ? 0l : obj);
	}

	public static Double getLongToDouble(Long obj)
	{
		return (obj == null ? 0.0 : obj.doubleValue());
	}

	public static Integer getInteger(Integer obj)
	{
		return (obj == null ? 0 : obj);
	}

	public static Double getDifValue(Double sub, Double min)
	{
		return (sub == null ? 0d : sub) - (min == null ? 0d : min);
	}

	public static Double getSumValue(Double d1, Double d2)
	{
		return (d1 == null ? 0d : d1) + (d2 == null ? 0d : d2);
	}

	public static double round(double v, int scale)
	{
		if (scale < 0)
		{
			throw new IllegalArgumentException(
					"scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 根据当前日期及月份间隔获得新日期 n为负值所得日期为当前日期的前n月日期 n为正值所得日期为当前日期的后n月日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date dateAddMonth(Date date, int n)
	{
		if (date == null)
			return DateUtil.now();

		Calendar c = Calendar.getInstance();

		c.setTime(date);
		c.add(Calendar.MONTH, n);

		return c.getTime();
	}
	/**
	 * 比较两个日期相差月份数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differenceOnMonth(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		int difYear = 0;
		int difMonth = 0;

		int year1 = getDateYear(date1);
		int year2 = getDateYear(date2);
		int month1 = getMonthOnDate(date1);
		int month2 = getMonthOnDate(date2);

		difYear = (year2 - year1) * 12;
		difMonth = month2 - month1;

		return Math.abs(difYear + difMonth);
	}

	/**
	 * 获得所在日期的年份
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDateYear(Date date) {
		if (date == null)
			return 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得所在日期的月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthOnDate(Date date) {
		if (date == null)
			return 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.MONTH);
	}

	/**
	 * 获得所在日期日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		if (date == null)
			return 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static boolean isEmpty(Collection<?> o) {
		return (o == null || o.isEmpty());
	}

	public static boolean notEmpty(Collection<?> o) {
		return (o != null && !o.isEmpty());
	}

	public static boolean isEmpty(Map<?, ?> o) {
		return (o == null || o.isEmpty());
	}

	public static boolean notEmpty(Map<?, ?> o) {
		return (o != null && !o.isEmpty());
	}

	/**
	 * 根据日期获得某日期之前（或之后）N天的日期 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getDateAddDays(Date date, int n)
	{
		if (date == null)
			return DateUtil.now();

		Calendar c = Calendar.getInstance();

		c.setTime(date);
		c.add(Calendar.DATE, n);

		return c.getTime();
	}
	
	/**
	 * 根据对像返回字符串值，如为null返回空
	 * 
	 * @param obj
	 * @return
	 */
	public static String getValue(Object obj) {
		return (obj == null ? "" : String.valueOf(obj));
	}
	/**
	 * 将StringList转换为字符串
	 * @param stringList
	 * @param split 分隔符
	 * @param conjSymbol 连接符
	 * @return
	 * 例：stringList元素【1，2，3】 split为"-" conjSymbol为"'" 输出为'1'-'2'-'3'
	 * 例：stringList元素【1，2，3】 split为"," conjSymbol为"'" 输出为'1','2','3'
	 */
	public static String conversionListToString(List<String> stringList, String split, String conjSymbol)
	{
		StringBuffer target = null;
		if(notEmpty(stringList))
		{
			split = (split == null ? "" : split);
			conjSymbol = (conjSymbol == null ? "" : conjSymbol);
			for(String string : stringList)
			{
				String temp = conjSymbol + string + conjSymbol;
				if(target == null)
				{
					target = new StringBuffer(temp);
				}else {
					if(!target.toString().contains(temp))
					{
						target.append(split).append(temp);
					}
				}
			}			
			return target.toString();
		}
		return "";
	}
	public static List<String> conversionStringToList(String inString, String split)
	{
		List<String> outStringList = new ArrayList<String>();
		if(StringUtils.isNotEmpty(inString))
		{
			split = (split == null ? "," : split);
			if(inString.indexOf(split) >= 0)
			{
				for(String app : inString.split(split))
				{
					outStringList.add(app.replaceAll("'", ""));
				}
			}
			else 
			{
				outStringList.add(inString.replaceAll("'", ""));
			}
		}
		return outStringList;
	}
}
