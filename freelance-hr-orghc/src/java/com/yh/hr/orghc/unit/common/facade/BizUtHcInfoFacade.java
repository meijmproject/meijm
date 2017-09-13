package com.yh.hr.orghc.unit.common.facade;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.orghc.unit.common.dto.BizUtHcInfoDTO;
import com.yh.hr.orghc.ut.dto.BizUtHcDTO;
import com.yh.hr.orghc.ut.service.BizHcInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

public class BizUtHcInfoFacade {
	
	private BizHcInfoService bizHcInfoService = (BizHcInfoService)SpringBeanUtil.getBean("bizHcInfoService");
	
	public void update(BizUtHcInfoDTO bizUbHcInfoDTO) throws ServiceException{
		BizUtHcDTO saoUbHcDTO = new BizUtHcDTO();
		BeanHelper.copyProperties(bizUbHcInfoDTO, saoUbHcDTO);
		bizHcInfoService.update(saoUbHcDTO);
	}
	
	public void create(BizUtHcInfoDTO bizUbHcInfoDTO) throws ServiceException{
		BizUtHcDTO saoUbHcDTO = new BizUtHcDTO();
		BeanHelper.copyProperties(bizUbHcInfoDTO, saoUbHcDTO);
		bizHcInfoService.create(saoUbHcDTO);
	}
	
	public List<BizUtHcInfoDTO> listByUnitOid(java.lang.Long unitOid) throws ServiceException{
		List<BizUtHcInfoDTO> bizUbHcInfoDTOs = new ArrayList<BizUtHcInfoDTO>();
		List<BizUtHcDTO> saoUbHcDTOs = bizHcInfoService.list(unitOid);
		if(null != saoUbHcDTOs){
			for (BizUtHcDTO saoUbHcDTO : saoUbHcDTOs) {
				BizUtHcInfoDTO bizUbHcInfoDTO = new BizUtHcInfoDTO();
				BeanHelper.copyProperties(saoUbHcDTO, bizUbHcInfoDTO);
				bizUbHcInfoDTOs.add(bizUbHcInfoDTO);
			}
		}
		return bizUbHcInfoDTOs;
	}
	
	public void delete(java.lang.Long hcId) throws ServiceException{
		bizHcInfoService.delete(hcId);
	}
	
	public BizUtHcInfoDTO getBizUbHcInfoDTO(java.lang.Long hcId) throws ServiceException{
		BizUtHcDTO saoUbHcDTO = bizHcInfoService.get(hcId);
		BizUtHcInfoDTO bizUbHcInfoDTO = new BizUtHcInfoDTO();
		BeanHelper.copyProperties(saoUbHcDTO, bizUbHcInfoDTO);
		return bizUbHcInfoDTO;
	}
}
