package com.yh.hr.component.unittree.facade;

import com.yh.hr.component.unittree.service.JhcUnitTreeService;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;

public class JhcUnitTreeFacade {
	private JhcUnitTreeService jhcUnitTreeService;

	

	public void setJhcUnitTreeService(JhcUnitTreeService jhcUnitTreeService) {
		this.jhcUnitTreeService = jhcUnitTreeService;
	}

	/**
	 * 根据单位Oid查询单位信息
	 * @param unitOid
	 * @return
	 */
	public UtUnitDTO findUnitInfoByUnitOid(Long unitOid) throws ServiceException
	{
		return jhcUnitTreeService.findUnitInfoByUnitOid(unitOid);
	}

}
