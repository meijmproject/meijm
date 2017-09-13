package com.yh.hr.component.unittree.service;



import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;

public interface JhcUnitTreeService {

	/**
	 * 根据单位Oid查询单位信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public UtUnitDTO findUnitInfoByUnitOid(Long unitOid)throws ServiceException;



}
