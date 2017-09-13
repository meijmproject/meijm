/**
 * 
 */
package com.yh.admin.oplog.service;

import java.util.List;

import com.yh.admin.oplog.bo.OpLog;
import com.yh.admin.oplog.dto.OpLogDTO;
import com.yh.admin.oplog.queryhelper.OpLogQueryHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/26
 */

public class OpLogService {
	
	public void saveLog(String functionCode, String functionName) throws ServiceException {
		OpLog log = new OpLog();
		
		log.setFunctionCode(functionCode);
		log.setFunctionName(functionName);
		
		UserContext uc = UserContext.getInstance();
		
		log.setIpAddress(uc.getAttributeAsString("ipAddr", null));
		
		log.setLogDate(DateUtil.now());
		
		log.setUserId(uc.getUid());
		log.setUserName(uc.getDisplayName());
		
		log.setSystemCode(uc.getAttributeAsString(UserContext.WEB_KEY_SYSTEMID, null));
		log.setSystemName(UserContext.getInstance().getAttributeAsString("systemName", null));
		
		log.save();
	}

	public OpLogDTO getLastOpLog(String userId, String systemCode, String functionCode) throws ServiceException {
		return OpLogQueryHelper.getLastOpLog(userId, systemCode, functionCode);
	}
	
	public List<OpLogDTO> listOpLog(TableTagBean ttb) throws ServiceException {
		return OpLogQueryHelper.listOpLog(ttb);
	}
}
