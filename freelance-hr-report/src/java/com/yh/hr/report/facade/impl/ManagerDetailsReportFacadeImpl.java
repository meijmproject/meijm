package com.yh.hr.report.facade.impl;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.ManagerDetailsReportDTO;
import com.yh.hr.report.service.ManagerDetailsReportService;
import com.yh.platform.core.exception.ServiceException;

public class ManagerDetailsReportFacadeImpl{
	
	private ManagerDetailsReportService managerDetailsReportService;

	public void setManagerDetailsReportService(ManagerDetailsReportService managerDetailsReportService) {
		this.managerDetailsReportService = managerDetailsReportService;
	}

	/**
	 *  获取管理人员明细
	 * @param ttb
	 * @return Map<String 管理人员类别,List<ManagerDetailsReportDTO> 管理人员明细>
	 * @throws ServiceException
	 */
	public Map<String, List<ManagerDetailsReportDTO>> getManagerDetails(TableTagBean ttb) throws ServiceException {
		return managerDetailsReportService.getManagerDetails(ttb);
	}
	
	
}
