package com.yh.component.datahandler.handler;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.platform.core.exception.ServiceException;


/**
 *@description 数据托盘线程
 * 
 *@author      chencr	
 *@created     2013-04-10
 *@version     1.0
 * 
 */
public class BaseHandler 
{
	private static ThreadLocal<BaseHandleData> threadLocalVar = new ThreadLocal<BaseHandleData>();
    
	/**
     * 
     * @param operationData
     */
	public static void put(BaseHandleData operationData) 
	{
		if (threadLocalVar == null) 
		{
			threadLocalVar = new ThreadLocal<BaseHandleData>();
		}
		threadLocalVar.set(operationData);
	}
	
	/**
	 * 获取托盘数据
	 * @return operationData
	 */
	
	public static BaseHandleData get() throws ServiceException
	{ 
		try
		{
			BaseHandleData operationData  = null;
			if(threadLocalVar.get() != null)
			{
				operationData = threadLocalVar.get();
			}
			return operationData;
			
		}catch(Exception e)
		{
			throw new ServiceException("get BaseOperationDataService data failed",e);
		}
	}
	
	/**
	 * 移除托盘数据
	 */
	public static void remove() 
	{
		if (threadLocalVar != null) 
		{
			threadLocalVar.remove();
		}
    }
}
