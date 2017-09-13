/**
 * 
 */
package com.yh.admin.oplog;

import com.yh.admin.oplog.dto.OpLogDTO;
import com.yh.admin.oplog.facade.OpLogFacade;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/26
 */

public class OpLogHelper {
	private static OpLogFacade opLogFacade = (OpLogFacade) SpringBeanUtil.getBean("opLogFacade");
	
	public static void saveLog(String functionCode, String functionName) throws ServiceException {
		opLogFacade.saveLog(functionCode, functionName);
	}
	
	public static OpLogDTO getLastOpLog(String userId, String systemCode, String functionCode) throws ServiceException {
		return opLogFacade.getLastOpLog(userId, systemCode, functionCode);
	}
	
}
