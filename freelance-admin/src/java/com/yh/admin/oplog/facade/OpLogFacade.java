/**
 * 
 */
package com.yh.admin.oplog.facade;

import java.util.List;

import com.yh.admin.oplog.dto.OpLogDTO;
import com.yh.admin.oplog.service.OpLogService;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/26
 */

public class OpLogFacade {
	private OpLogService opLogService;

	public void setOpLogService(OpLogService opLogService) {
		this.opLogService = opLogService;
	}
	
	public void saveLog(String functionCode, String functionName) throws ServiceException {
		opLogService.saveLog(functionCode, functionName);
	}

	public OpLogDTO getLastOpLog(String userId, String systemCode, String functionCode) throws ServiceException {
		return opLogService.getLastOpLog(userId, systemCode, functionCode);
	}
	
	
	public List<OpLogDTO> listOpLog(TableTagBean ttb) throws ServiceException {
		return opLogService.listOpLog(ttb);
	}
}
