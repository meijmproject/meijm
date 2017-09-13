package com.yh.hr.bt.facade;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.hr.res.bt.dto.BtLogDTO;
import com.yh.hr.res.bt.service.BtTaskService;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;

public class TaskWorkProcessFacade {
	private BtTaskService btTaskService;
	public BtTaskService getBtTaskService() {
		return btTaskService;
	}

	public void setBtTaskService(BtTaskService btTaskService) {
		this.btTaskService = btTaskService;
	}

	public List<BtLogDTO> findBizWorkProcess(Long taskOid) throws ServiceException {
		return btTaskService.findBizWorkProcess(taskOid);
	}

	public List<BtLogDTO> findAuditWorkProcess(Long taskOid)
			throws ServiceException {
		return btTaskService.findAuditWorkProcess(taskOid);
	}

	public List<JSONObject> findSelectUnit(String menuCode) throws ServiceException{
		return btTaskService.findSelectUnit(menuCode);
	}

	public List<UtUnitDTO> findAllSelectUnit() throws ServiceException{
		return btTaskService.findAllSelectUnit();
	}

}
