/**
 * 
 */
package com.yh.hr.res.bt.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.unit.bo.UtUnit;
import com.yh.hr.res.unit.queryhelper.UtUnitQueryHelper;
import com.yh.hr.res.bt.bo.BtTask;
import org.apache.commons.collections.CollectionUtils;

import net.sf.json.JSONObject;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.res.bt.bo.BtLog;
import com.yh.hr.res.bt.dto.BtLogDTO;
import com.yh.hr.res.bt.queryhelper.BtLogQueryHelper;
import com.yh.hr.res.bt.queryhelper.BtUnitQueryHelper;
import com.yh.hr.res.bt.service.BtTaskService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.web.UserContext;

/**
 * Task Service
 * @author	zhangqp
 * @version	1.0,	16/10/19
 */

public class BtTaskServiceImpl implements BtTaskService {
	private UtUnitService utUnitService;
	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService;

	public void setSaoUserUnitAuthorizationService(
			SaoUserUnitAuthorizationService saoUserUnitAuthorizationService) {
		this.saoUserUnitAuthorizationService = saoUserUnitAuthorizationService;
	}

	public void updateTaskStatus(Long taskOid, String processDeptCode, String bizStatusCode, String auditStatusCode, String remark) throws ServiceException {
		BtTask task = DaoUtil.get(BtTask.class, taskOid);
		
		if (task != null) {
			if (processDeptCode != null) {
				task.setProcessDeptCode(processDeptCode);
				task.setProcessDeptName(DicHelper.viewName(DicConstants.YHRS2002, processDeptCode));
			}

			if (bizStatusCode != null) {
				task.setPreBizStatusCode(task.getBizStatusCode());
				task.setPreBizStatusName(task.getBizStatusName());
				
				task.setBizStatusCode(bizStatusCode);
				task.setBizStatusName(DicHelper.viewName(DicConstants.YHRS2001, bizStatusCode));
			}

			if (auditStatusCode != null) {
				task.setPreAuditStatusCode(task.getAuditStatusCode());
				task.setPreAuditStatusName(task.getAuditStatusName());
				
				task.setAuditStatusCode(auditStatusCode);//审核代码
				task.setAuditStatusName(DicHelper.viewName(DicConstants.YHRS2001, auditStatusCode));
			}

			task.update();
			
			BtLog taskLog = new BtLog();
			
			taskLog.setTaskOid(taskOid);
			
			taskLog.setBizStatusCode(bizStatusCode);
			taskLog.setBizStatusName(DicHelper.viewName(DicConstants.YHRS2001, bizStatusCode));
			
			taskLog.setAuditStatusCode(auditStatusCode);
			taskLog.setAuditStatusName(DicHelper.viewName(DicConstants.YHRS2001, auditStatusCode));
			
			taskLog.setProcessDeptCode(task.getProcessDeptCode());
			taskLog.setProcessDeptName(task.getProcessDeptName());
			
			taskLog.setRemark(remark);//一般是审核意见
			
			taskLog.save();
		}
	}

	public List<BtLogDTO> findBizWorkProcess(Long taskOid) throws ServiceException {
		List<BtLog> l=BtLogQueryHelper.findBizWorkProcess(taskOid);
		return BeanHelper.copyProperties(l, BtLogDTO.class);
	}

	public List<BtLogDTO> findAuditWorkProcess(Long taskOid)
			throws ServiceException {
		return BeanHelper.copyProperties(BtLogQueryHelper.findAuditWorkProcess(taskOid), BtLogDTO.class);
	}

	public List<JSONObject> findSelectUnit(String menuCode)
			throws ServiceException {
		List<String> unit = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		if(CollectionUtils.isEmpty(unit)) return null;
		return BtUnitQueryHelper.findSelectUnit(menuCode, unit);
	}

	public List<UtUnitDTO> findAllSelectUnit() throws ServiceException {
		List<String> unit = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		if(CollectionUtils.isEmpty(unit)) return null;
		StringMap params = new StringMap();
		params.put("unitStatus", DicConstants.YHRS0101_1+","+DicConstants.YHRS0101_2+","+DicConstants.YHRS0101_3);
		return utUnitService.findUnitListByAuth(unit, params);
	}

	/**
	 * 修改单位信息
	 * @param taskOid
	 * @param unitOid
	 */
	public void updateStartUnit(Long taskOid,Long unitOid) throws ServiceException
	{
		if(null==taskOid||null==unitOid)
			return;
		BtTask btTast = DaoUtil.get(BtTask.class,taskOid);
		btTast.setStartUnitOid(unitOid);
		UtUnit unit = UtUnitQueryHelper.get(unitOid);
		if (unit != null) {
			btTast.setStartUnitName(unit.getUnitName());
		}
		btTast.setUpdatedByCode(UserContext.getLoginUserID());
		btTast.setUpdatedByName(UserContext.getLoginUserName());
		btTast.setUpdatedDate(new Date());
		btTast.update();
	}
}
