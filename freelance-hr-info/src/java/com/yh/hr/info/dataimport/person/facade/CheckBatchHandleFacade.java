package com.yh.hr.info.dataimport.person.facade;

import com.yh.hr.component.im.loghandle.service.CheckBatchHandleService;
import com.yh.platform.core.exception.ServiceException;

public class CheckBatchHandleFacade {

	private CheckBatchHandleService checkBatchHandleService;

	public void setCheckBatchHandleService(
			CheckBatchHandleService checkBatchHandleService) {
		this.checkBatchHandleService = checkBatchHandleService;
	}
	
	/**
	 * 更新批次检查数量
	 * @param importBatchOid 当期导入批次OID
	 * @param hasCheckAbnormity 是否检查有异常
	 * @throws ServiceException
	 */
	public void updateImportBeforeBatchAmount(Long importBatchOid,Boolean hasCheckAbnormity) throws ServiceException {
		checkBatchHandleService.updateImportBeforeBatchAmount(importBatchOid, hasCheckAbnormity);
	}

	/**
	 * 批次导入流程状态推动
	 * @param importBatchOid 当期导入批次OID
	 * @param importFlowStatus 导入流程当前状态
	 * @throws ServiceException
	 */
	public void updateBatchStatus(Long importBatchOid, String importFlowStatus) throws ServiceException {
		checkBatchHandleService.updateBatchStatus(importBatchOid, importFlowStatus);
	}
}
