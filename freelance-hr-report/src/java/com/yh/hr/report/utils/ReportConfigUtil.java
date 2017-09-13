/*
 *  2017-2-25
 */
package com.yh.hr.report.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 *@description
 *加载报表配置文件
 *@author            liuhw
 *@created           2013-9-17
 *@version           1.0
 *
 */

public class ReportConfigUtil
{
	 @SuppressWarnings("rawtypes")
	private static HashMap messages = new HashMap();
	 private static Properties props = new Properties();
	 
	/**
	 * @param args
	 */
	static  
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
		InputStream is = null;
        is = classLoader.getResourceAsStream("report/report-config.properties");
        if (is != null) 
        {
            try
			{
				props.load(is);
			}
			catch (IOException e)
			{				
				e.printStackTrace();
			}
			finally
			{
				try
				{
					is.close();
				}
				catch (IOException e)
				{					
					e.printStackTrace();
				}
			}
        }
        
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getProperty(String key)
	{
		String msg = (String)messages.get(key);
		if (msg == null)
		{
			String pros = props.getProperty(key);
			if( pros != null)
			{
				messages.put(key,pros);
			}
						
			msg = pros;
		}
		
		return msg;
	}

}
