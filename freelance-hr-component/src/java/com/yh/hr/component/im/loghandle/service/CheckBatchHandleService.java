package com.yh.hr.component.im.loghandle.service;

import com.yh.platform.core.exception.ServiceException;

/**
 * 导入批次检查数量更新服务接口
 * @author wangx
 * @date 2017-07-13
 * @version 1.0
 */
public interface CheckBatchHandleService {

	/**
	 * 导入前检查更新批次检查数量
	 * @param importBatchOid 当期导入批次OID
	 * @param hasCheckAbnormity 是否检查有异常
	 * @throws ServiceException
	 */
	public void updateImportBeforeBatchAmount(Long importBatchOid,Boolean hasCheckAbnormity) throws ServiceException;

	/**
	 * 导入后校核更新批次检查数量
	 * @param importBatchOid 当期导入批次OID
	 * @param hasCheckAbnormity 是否检查有异常
	 * @throws ServiceException
	 */
	public void updateImportAfterBatchAmount(Long importBatchOid,Boolean hasCheckAbnormity) throws ServiceException;
	
	/**
	 * 批次导入流程状态推动
	 * @param importBatchOid 当期导入批次OID
	 * @param importFlowStatus 导入流程当前状态
	 * @throws ServiceException
	 */
	public void updateBatchStatus(Long importBatchOid, String importFlowStatus) throws ServiceException;
	
	/**
	 * 数据校核更新导入批次检查数
	 * @param importBatchOid
	 * @throws ServiceException
	 */
	public void updateBatchAmountForCheck(Long importBatchOid) throws ServiceException;
}
