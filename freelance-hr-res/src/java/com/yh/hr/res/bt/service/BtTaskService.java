/**
 * 
 */
package com.yh.hr.res.bt.service;

import java.util.List;

import com.yh.hr.res.bt.dto.BtLogDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import net.sf.json.JSONObject;

import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/10/19
 */

public interface BtTaskService {
	
	/**
	 * 更新在办部门、业务状态、备注信息（同时，生成 BtLog）
	 * @param taskOid
	 * @param processDeptCode null表示不更新
	 * @param bizStatusCode	null表示不更新
	 * @param auditStatusCode	null表示不更新
	 * @param opinion
	 * @throws ServiceException
	 */
	public void updateTaskStatus(Long taskOid, String processDeptCode, String bizStatusCode, String auditStatusCode, String opinion) throws ServiceException;

	/**
	 * 更新业务状态（同时，生成 BtLog）
	 * @param taskOid
	 * @param bizStatusWsb
	 * @param auditStatusWsb
	 */
//	public void updateTaskStatus(Long taskOid, String bizStatusCode, String auditStatusCode) throws ServiceException;
	public List<BtLogDTO> findBizWorkProcess(Long taskOid) throws ServiceException;
	public List<BtLogDTO> findAuditWorkProcess(Long taskOid) throws ServiceException;

	public List<JSONObject> findSelectUnit(String menuCode) throws ServiceException;

	public List<UtUnitDTO> findAllSelectUnit() throws ServiceException;
	/**
	 * 修改单位信息
	 * @param taskOid
	 * @param unitOid
	 */
	public void updateStartUnit(Long taskOid,Long unitOid) throws ServiceException;
}
