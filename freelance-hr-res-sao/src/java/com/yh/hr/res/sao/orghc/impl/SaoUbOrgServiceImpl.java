package com.yh.hr.res.sao.orghc.impl;

import java.util.List;

import com.yh.hr.res.sao.orghc.dto.SaoUbOrgDTO;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.service.UbOrgService;
import com.yh.hr.res.sao.orghc.service.SaoUbOrgService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 内设机构信息查询、维护接口
 * @author	lenghh
 * @version	1.0,	16/09/05
 */

public class SaoUbOrgServiceImpl implements SaoUbOrgService {
	
	private UbOrgService  	ubOrgService;
	
	public void setUbOrgService(UbOrgService ubOrgService) {
		this.ubOrgService = ubOrgService;
	}
	
	/* (non-Javadoc)
	 * @see SaoUbOrgService#getSaoUbOrgDTO(java.lang.Long)
	 */
	public SaoUbOrgDTO getSaoUbOrgDTO(Long orgOid) throws ServiceException {
		UbOrgDTO ubOrgDTO = ubOrgService.getUbOrgDTOById(orgOid);
		if(null != ubOrgDTO){
			SaoUbOrgDTO saoUbOrgDTO = new SaoUbOrgDTO();
			BeanHelper.copyProperties(ubOrgDTO, saoUbOrgDTO);
			return saoUbOrgDTO;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see SaoUbOrgService#listSaoUbOrgDTOByUnitOid(java.lang.Long)
	 */
	public List<SaoUbOrgDTO> listSaoUbOrgDTOByUnitOid(Long unitOid) throws ServiceException {
		List<SaoUbOrgDTO> saoUbOrgDTOs = null;
		List<UbOrgDTO> ubOrgs = ubOrgService.listByUnitOid(unitOid);
		if(CollectionUtils.isNotEmpty(ubOrgs)){
			saoUbOrgDTOs = BeanHelper.copyProperties(ubOrgs,SaoUbOrgDTO.class);
		}
		return saoUbOrgDTOs;
	}

	/* (non-Javadoc)
	 * @see SaoUbOrgService#createOrgInfo(SaoUbOrgDTO)
	 */
	public void createOrgInfo(SaoUbOrgDTO saoUbOrgDTO) throws ServiceException {
		UbOrgDTO ubOrgDTO = new UbOrgDTO();
		BeanHelper.copyProperties(saoUbOrgDTO, ubOrgDTO);
		ubOrgService.createOrgInfo(ubOrgDTO);
	}

	/* (non-Javadoc)
	 * @see SaoUbOrgService#updateOrgInfo(SaoUbOrgDTO)
	 */
	public void updateOrgInfo(SaoUbOrgDTO saoUbOrgDTO) throws ServiceException {
		UbOrgDTO ubOrgDTO = new UbOrgDTO();
		BeanHelper.copyProperties(saoUbOrgDTO, ubOrgDTO);
		ubOrgService.updateOrgInfo(ubOrgDTO);
	}

	/* (non-Javadoc)
	 * @see SaoUbOrgService#deleteOrgInfo(java.lang.Long)
	 */
	public void deleteOrgInfo(Long orgOid) throws ServiceException {
		ubOrgService.deleteOrgInfo(orgOid);
	}
	
	
}
