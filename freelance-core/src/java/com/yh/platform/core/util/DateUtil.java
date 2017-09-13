/*
 * @(#) DateUtil.java        1.00         2006-7-26
 * 
 * Copyright (c) 2006 JADE EXPRESS Corporation. All Rights Reserved.
 *
 *
 */
package com.yh.platform.core.util;

import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.exception.ServiceException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
    public static final String DATE_DIVISION = "-";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
    /**
     * yyyy-MM
     */
    public static final String DATE_PATTERN = "yyyy-MM";
    /**
     * yyyyMMdd
     */
    public static final String DATA_PATTERN_YYYYMMDD = "yyyyMMdd";
    /**
     * yyyyMdd
     */
    public static final String DATA_PATTERN_MD = "M-d";
    /**
     * yyyyMM
     */
    public static final String DATA_PATTERN_YYYYMM = "yyyyMM";

    public static final String DATA_PATTERN_YYMM = "yyyy年MM月";
    
    public static final String DATA_PATTERN_YYMMDD = "yyyy年MM月DD日";

    public static final String DATE_TO_STRING = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * HH:mm:ss
     */
    public static final String TIME_PATTERN_HHMMSS = "HH:mm:ss";

    public static final int SECOND = 1000;
    public static final int MINUTE = 60 * SECOND;
    public static final int HOUR = 60 * MINUTE;
    public static final long DAY = 24l * HOUR;

    public static Date now() {
        Calendar cal = Calendar.getInstance();

        return cal.getTime();
    }

    public static Date nowYearEnd() {
        Calendar cal = Calendar.getInstance();
        //cal.set(Calendar.YEAR, 2099);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }

    public static Timestamp nowTimestamp() {
        Calendar cal = Calendar.getInstance();
        return new Timestamp(cal.getTimeInMillis());
    }

    public static String nowString() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();

        return formatDate(currDate);
    }

    /**
     * Return the current date in the specified format
     *
     * @param pattern
     * @return
     */
    public static String nowString(String pattern) {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();

        return format(currDate, pattern);
    }

    /**
     * Parse a string and return a date value
     *
     * @param dateValue
     * @return
     * @throws Exception
     */
    public static Date parseDate(String dateValue) {
        return parse(dateValue, DATE_PATTERN_DEFAULT);
    }

    public static void main(String[] args) {
        System.out.print(format(addMonths(parseDate("2010-08-31"), 6), "yyyy-MM-dd"));

    }

    /**
     * Parse a strign and return a datetime value
     *
     * @param dateValue
     * @return
     */
    public static Date parseTime(String dateValue) {
        return parse(dateValue, TIME_PATTERN_DEFAULT);
    }

    /**
     * Parse a string and return the date value in the specified format
     *
     * @param dateValue
     * @param pattern
     * @return
     * @throws java.text.ParseException
     * @throws Exception
     */
    public static Date parse(String dateValue, String pattern) {
        if (StringUtils.isEmpty(dateValue))
            return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            return dateFormat.parse(dateValue);
        } catch (ParseException pe) {
            return null;
        }
    }

    public static String formatDate(Date d) {
        return format(d, DATE_PATTERN_DEFAULT);
    }
    public static String formatDate2(Date d) {
        return format(d, DATA_PATTERN_MD);
    }
    public static String formatDate1(Date d) {
        return format(d, DATE_PATTERN);
    }

    public static String formatTime(Date t) {
        return format(t, TIME_PATTERN_DEFAULT);
    }

    public static String format(Date d, String pattern) {
        if (d == null)
            return null;

        SimpleDateFormat dateFromat = new SimpleDateFormat(pattern);
        return dateFromat.format(d);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, days);

        return cal.getTime();
    }

    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MONTH, months);

        return cal.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.YEAR, year);

        return cal.getTime();
    }

    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static Date getDateBeforTwelveMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MONTH, -12);

        return cal.getTime();

    }

    public static Date addDate(String date) {
        if (date == null) return null;

        Date tmpDate = parse(date, DATE_PATTERN_DEFAULT);

        Calendar cal = Calendar.getInstance();
        cal.setTime(tmpDate);
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;

        return w;
    }

    public static String getCurrentMouDateStr() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return format(cal.getTime(), DATE_PATTERN_DEFAULT);
    }

    public static Date getDateBegin(Date dt) {
        Calendar cal = Calendar.getInstance();
        if (dt != null)
            cal.setTime(dt);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    public static Date getDateEnd(Date dt) {
        Calendar cal = Calendar.getInstance();
        if (dt != null)
            cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }

    public static Date getDateForMonthAddOne(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1);
        Date nextDate = c.getTime();
        return nextDate;

    }

    public static Date getDateForMonthAddNew(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + month, c.get(Calendar.DATE));
        Date nextDate = c.getTime();
        return nextDate;

    }

    public static Date getDateForMonthRemoveOne(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static int yearsBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH) + 1;

        cal.setTime(date2);
        int year2 = cal.get(Calendar.YEAR);
        int month2 = cal.get(Calendar.MONTH) + 1;

        int yearBetween = year2 - year1;
        int monthBetween = month2 - month1;

        int betweenMonths = monthBetween + yearBetween * 12;

        return betweenMonths / 12;
    }

    public static int yearsBetweenExactDate(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int year1 = cal.get(Calendar.YEAR);

        cal.setTime(date2);
        int year2 = cal.get(Calendar.YEAR);
        int yearBetween = year2 - year1;
        Date date3 = addYear(date1, yearBetween);
        if (yearBetween > 0) {
            if (date3.after(date2)) {
                return yearBetween - 1;
            } else {
                return yearBetween;
            }
        } else if (yearBetween < 0) {
            if (date3.before(date2)) {
                return yearBetween + 1;
            } else {
                return yearBetween;
            }
        } else {
            return yearBetween;
        }
    }

    public static Date getDateForYearAddOne(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR) + 1, c.get(Calendar.MONTH), c.get(Calendar.DATE));
        Date nextYear = c.getTime();
        return nextYear;

    }


    public static int getMonthsBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH) + 1;

        cal.setTime(date2);
        int year2 = cal.get(Calendar.YEAR);
        int month2 = cal.get(Calendar.MONTH) + 1;

        int yearBetween = year2 - year1;
        int monthBetween = month2 - month1;
        return monthBetween + yearBetween * 12;
    }

    public static int getYear(Date date) {
        return getFromDate(date, Calendar.YEAR);
    }

    public static int getFromDate(Date date, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }
    
    //将数字转化为大写
    public static String numToUpper(int num) {
        String u[] = {"○", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        char[] str = String.valueOf(num).toCharArray();
        String rstr = "";
        for (int i = 0; i < str.length; i++) {
            rstr = rstr + u[Integer.parseInt(str[i] + "")];
        }
        return rstr;
    }
    
    //将月转化为大写
    public static String monthToUppder(int month) {
        if (month < 10) {
            return numToUpper(month);
        } else if (month == 10) {
            return "十";
        } else {
            return "十" + numToUpper(month - 10);
        }
    }
 
    //将日转化为大写
    public static String dayToUppder(int day) {
        if (day < 20) {
            return monthToUppder(day);
        } else {
            char[] str = String.valueOf(day).toCharArray();
            if (str[1] == '0') {
                return numToUpper(Integer.parseInt(str[0] + "")) + "十";
            } else {
                return numToUpper(Integer.parseInt(str[0] + "")) + "十" + numToUpper(Integer.parseInt(str[1] + ""));
            }
        }
    }
    
    //将日期转化为大小写
    public static String dataToUpper(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return numToUpper(year) + "年" + monthToUppder(month) + "月" + dayToUppder(day) + "日";
    }
    public static List<String> getAllMonths(String start, String end){
        String splitSign="-";
        String regex="\\d{4}"+splitSign+"(([0][1-9])|([1][012]))"; //判断YYYY-MM时间格式的正则表达式
        if(!start.matches(regex) || !end.matches(regex)) return new ArrayList<String>();
         
        List<String> list=new ArrayList<String>();
        if(start.compareTo(end)>0){
            //start大于end日期时，互换
            String temp=start;
            start=end;
            end=temp;
        }
         
        String temp=start; //从最小月份开始
        while(temp.compareTo(start)>=0 && temp.compareTo(end)<1){
            list.add(temp); //首先加上最小月份,接着计算下一个月份
            String[] arr=temp.split(splitSign);
            int year=Integer.valueOf(arr[0]);
            int month=Integer.valueOf(arr[1])+1;
            if(month>12){
                month=1;
                year++;
            }
            if(month<10){//补0操作
                temp=year+splitSign+"0"+month;
            }else{
                temp=year+splitSign+month;
            }
        }
        return list;
    }
	
	/**
	 * 判断是否为日期格式（yyyy-MM-dd或者yyyy/MM/dd）
	 * @param dateStr
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean checkDateFormat(String dateStr) throws ServiceException {
		String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])" +
				"|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])" +
				"|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])" +
				"|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		Pattern pat = Pattern.compile(rexp);    
        Matcher mat = pat.matcher(dateStr);    
        return mat.matches();  
	}
	
	/**
	 * 判断是否为日期时间格式（yyyy-MM-dd HH:mm:ss或者yyyy/mm/dd HH:mm:ss）
	 * @param dateStr
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean checkDateTimeFormat(String dateStr) throws ServiceException {
		String rexp =  "^((//d{2}(([02468][048])|([13579][26]))[//-/////s]?((((0?[13578])|(1[02]))[//-/////s]?((0?[1-9])" +
				"|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-/////s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-/////s]?((0?[1-9])" +
				"|([1-2][0-9])))))|(//d{2}(([02468][1235679])|([13579][01345789]))[//-/////s]?((((0?[13578])|(1[02]))[//-/////s]?((0?[1-9])" +
				"|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-/////s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-/////s]?((0?[1-9])" +
				"|(1[0-9])|(2[0-8]))))))(//s(((0?[0-9])|([1][0-9])|([2][0-3]))//:([0-5]?[0-9])((//s)|(//:([0-5]?[0-9])))))?$";
		Pattern pat = Pattern.compile(rexp);    
        Matcher mat = pat.matcher(dateStr);    
        return mat.matches();  
	}
}