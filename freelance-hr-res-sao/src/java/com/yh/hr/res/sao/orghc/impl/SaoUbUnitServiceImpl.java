package com.yh.hr.res.sao.orghc.impl;

import java.util.List;

import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.ub.queryhelper.UbUnitQueryHelper;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.res.sao.orghc.dto.SaoUbUnitDTO;
import com.yh.hr.res.sao.orghc.service.SaoUbHcService;
import com.yh.hr.res.sao.orghc.service.SaoUbLeaderService;
import com.yh.hr.res.sao.orghc.service.SaoUbOrgService;
import com.yh.hr.res.sao.orghc.service.SaoUbUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 机构信息查询、维护接口
 * @author	lenghh
 * @version	1.0,	16/09/05
 */

public class SaoUbUnitServiceImpl implements SaoUbUnitService {
	
	private UbUnitService 		ubUnitService;
	private SaoUbOrgService		saoUbOrgService;
	private SaoUbLeaderService 	saoUbLeaderService;
	private SaoUbHcService 		saoUbHcService;
	
	public void setSaoUbOrgService(SaoUbOrgService saoUbOrgService) {
		this.saoUbOrgService = saoUbOrgService;
	}

	public void setSaoUbLeaderService(SaoUbLeaderService saoUbLeaderService) {
		this.saoUbLeaderService = saoUbLeaderService;
	}

	public void setSaoUbHcService(SaoUbHcService saoUbHcService) {
		this.saoUbHcService = saoUbHcService;
	}

	public void setUbUnitService(UbUnitService ubUnitService) {
		this.ubUnitService = ubUnitService;
	}
	
	public SaoUbUnitDTO getSaoUbUnitDTO(Long unitOid) throws ServiceException {
		SaoUbUnitDTO saoUbUnitDTO = null;
		UbUnitDTO ubUnitDTO = ubUnitService.getUbUnitDTOById(unitOid);
		if(null != ubUnitDTO){
			saoUbUnitDTO = new SaoUbUnitDTO();
			BeanHelper.copyProperties(ubUnitDTO, saoUbUnitDTO);
		}
		return saoUbUnitDTO;
	}
	
	/* (non-Javadoc)
	 * @see SaoUbUnitService#viewUnitInfo(java.lang.Long)
	 */
	public SaoUbUnitDTO viewUnitInfo(Long unitOid) throws ServiceException {
		SaoUbUnitDTO saoUbUnitDTO = null;
		UbUnitDTO ubUnitDTO = ubUnitService.getUbUnitDTOById(unitOid);
		if(null != ubUnitDTO){
			saoUbUnitDTO = new SaoUbUnitDTO();
			BeanHelper.copyProperties(ubUnitDTO, saoUbUnitDTO);
			saoUbUnitDTO.setSaoUbOrgDTOs(saoUbOrgService.listSaoUbOrgDTOByUnitOid(unitOid));
			saoUbUnitDTO.setSaoUbLeaderDTOs(saoUbLeaderService.listSaoUbLeaderDTOByUnitOid(unitOid));
			saoUbUnitDTO.setSaoUbHcDTOs(saoUbHcService.listSaoUbHcDTOByUnitOid(unitOid));
		}
		return saoUbUnitDTO;
	}
	
	/* (non-Javadoc)
	 * @see SaoUbUnitService#updateUnitInfo(SaoUbUnitDTO)
	 */
	public void updateUnitInfo(SaoUbUnitDTO saoUbUnitDTO) throws ServiceException {
		UbUnitDTO ubUnitDTO = new UbUnitDTO();
		BeanHelper.copyProperties(saoUbUnitDTO, ubUnitDTO);
		ubUnitService.update(ubUnitDTO);
	}
	
	public List<Long> findUnitOidsByAdminUnitOid(Long adminUnitOid)
			throws ServiceException {
		
		return UbUnitQueryHelper.findUnitOidsByAdminUnitOid(adminUnitOid);
	}
}
