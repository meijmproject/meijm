package com.yh.hr.component.im.loghandle.service;

import java.util.List;

import com.yh.hr.component.im.dto.CheckResultDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员导入第一次数据校核更新日志服务接口
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public interface CheckUnusualHandleService {

	/**
	 * 导入异常日志处理
	 * @param resultList
	 * @throws ServiceException
	 */
	public void handleLog(List<CheckResultDTO> resultList) throws ServiceException;

	/**
	 * 更新校核人员检查状态
	 * @param resultList
	 * @throws ServiceException
	 */
	public void updatePersonCheckType(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException;
	
	/**
	 * 数据校核批量更新校核人员检查状态
	 * @param importBatchOid
	 * @throws ServiceException
	 */
	public void updateBatchPersonCheckTypeForCheck(Long importBatchOid) throws ServiceException;
	
	/**
	 * 数据校核批量更新导入数据异常日志
	 * @param importBatchOid
	 * @throws ServiceException
	 */
	public void updateBatchUnusualLogs(Long importBatchOid) throws ServiceException;
}
