/*
 * @(#) CommonReportDataSource.java        1.00         2009-2-5
 * 
 * Copyright (c) 2006  EXPRESS Corporation. All Rights Reserved.
 *
 */
package com.yh.component.print.util;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.beanutils.BeanUtils;

/**
 *@description �������Դ(����ֶε�ֵΪnull��"",������"/")
 *
 *@author            luzw
 *@created           2015-2-7
 *@version           1.0
 *
 */

public class CommonReportDataSource implements JRDataSource
{
	private int index = -1;
	private List<? extends Object> data;
	
	public CommonReportDataSource(List<? extends Object> data){
		this.data = data;
	}
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
	 */
	public Object getFieldValue(JRField field) throws JRException
	{
        Object obj = getValue(field);
        if(null == obj || "".equals(obj))
		{
			return "/";
		}
        return obj;
	}

	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDataSource#next()
	 */
	public boolean next() throws JRException
	{
		return data != null && ++index < data.size();
	}

    private Object getValue(JRField field) throws JRException{
        Object obj = data.get(index);
		try
		{
			if(obj != null){
				return BeanUtils.getProperty(obj, field.getName());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
        return null;
    }
}