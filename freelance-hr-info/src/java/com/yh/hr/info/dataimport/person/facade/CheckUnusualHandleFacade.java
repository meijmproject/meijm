package com.yh.hr.info.dataimport.person.facade;

import java.util.List;

import com.yh.hr.component.im.dto.CheckResultDTO;
import com.yh.hr.component.im.loghandle.service.CheckUnusualHandleService;
import com.yh.hr.res.im.dto.ImDataUnusualLogDTO;
import com.yh.hr.res.im.service.ImDataUnusualLogService;
import com.yh.platform.core.exception.ServiceException;

public class CheckUnusualHandleFacade {

	private CheckUnusualHandleService checkUnusualHandleService;
	private ImDataUnusualLogService imDataUnusualLogService;

	public void setCheckUnusualHandleService(
			CheckUnusualHandleService checkUnusualHandleService) {
		this.checkUnusualHandleService = checkUnusualHandleService;
	}

	public void setImDataUnusualLogService(
			ImDataUnusualLogService imDataUnusualLogService) {
		this.imDataUnusualLogService = imDataUnusualLogService;
	}

	/**
	 * 导入异常日志处理
	 * @param resultList
	 * @throws ServiceException
	 */
	public void updateUnusualLog(List<CheckResultDTO> resultList) throws ServiceException {
		checkUnusualHandleService.handleLog(resultList);
	}
	
	/**
	 * 检查当前批次是否还有检查未通过的异常日志
	 * @return
	 * @throws ServiceException
	 */
	public Boolean checkNopassedLogs(Long importBatchOid) throws ServiceException {
		return imDataUnusualLogService.checkNopassedLogs(importBatchOid);
	}
	
	/**
	 * 获取该导入批次的指定检查状态的数据异常日志信息
	 * @param importBatchOid
	 * @param checkStatus
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDataUnusualLogDTO> findImDataUnusualLogDTOByBatchOidAndStatus(Long importBatchOid, String checkType) throws ServiceException {
		return imDataUnusualLogService.findImDataUnusualLogDTOByBatchOidAndStatus(importBatchOid, checkType);
	}
}
