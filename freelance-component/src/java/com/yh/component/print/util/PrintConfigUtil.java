/*
 * @(#) PropertyResourceUtil.java        1.00         2006-7-26
 * 
 * Copyright (c) 2006 JADE EXPRESS Corporation. All Rights Reserved.
 *
 *
 */
package com.yh.component.print.util;

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

public class PrintConfigUtil
{
	 @SuppressWarnings("rawtypes")
	private static HashMap print_messages = new HashMap();
	 private static Properties print_props = new Properties();
	 
	 @SuppressWarnings("rawtypes")
	private static HashMap common_messages = new HashMap();
	 private static Properties common_props = new Properties();
	 
	/**
	 * @param args
	 */
	static  
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
		InputStream common_is = null;
		common_is = classLoader.getResourceAsStream("common-config.properties");
        if (common_is != null) 
        {
            try
			{
            	common_props.load(common_is);
			}
			catch (IOException e)
			{				
				e.printStackTrace();
			}
			finally
			{
				try
				{
					common_is.close();
				}
				catch (IOException e)
				{					
					e.printStackTrace();
				}
			}
        }
        
        InputStream print_is = null;
        print_is = classLoader.getResourceAsStream("gdpos/print/printutil/print-config.properties");
        if (print_is != null) 
        {
            try
			{
            	print_props.load(print_is);
			}
			catch (IOException e)
			{				
				e.printStackTrace();
			}
			finally
			{
				try
				{
					print_is.close();
				}
				catch (IOException e)
				{					
					e.printStackTrace();
				}
			}
        }
        
	}
	
	@SuppressWarnings("unchecked")
	public static String getCommonProperty(String key)
	{
		String msg = (String)common_messages.get(key);
		if (msg == null)
		{
			String pros = common_props.getProperty(key);
			if( pros != null)
			{
				common_messages.put(key,pros);
			}
						
			msg = pros;
		}
		
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	public static String getPrintProperty(String key)
	{
		String msg = (String)print_messages.get(key);
		if (msg == null)
		{
			String pros = print_props.getProperty(key);
			if( pros != null)
			{
				print_messages.put(key,pros);
			}
						
			msg = pros;
		}
		
		return msg;
	}

}
