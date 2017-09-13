package com.yh.hr.orghc.unit.common.facade;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.orghc.unit.common.dto.BizUtLeaderInfoDTO;
import com.yh.hr.orghc.ut.dto.BizUtLeaderDTO;
import com.yh.hr.orghc.ut.service.BizLeaderInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

public class BizUtLeaderInfoFacade {
	private BizLeaderInfoService bizLeaderInfoService = (BizLeaderInfoService)SpringBeanUtil.getBean("bizLeaderInfoService");
	
	public List<BizUtLeaderInfoDTO> listByUnitOid(java.lang.Long unitOid) throws ServiceException{
		List<BizUtLeaderInfoDTO> verUbLeaderInfoDTOs = new ArrayList<BizUtLeaderInfoDTO>();
		List<BizUtLeaderDTO> leaderList = bizLeaderInfoService.list(unitOid);
		if(null != leaderList){
			for (BizUtLeaderDTO saoUbLeaderDTO : leaderList) {
				BizUtLeaderInfoDTO verUbLeaderInfoDTO = new BizUtLeaderInfoDTO();
				BeanHelper.copyProperties(saoUbLeaderDTO, verUbLeaderInfoDTO);
				verUbLeaderInfoDTOs.add(verUbLeaderInfoDTO);
			}
		}
		return verUbLeaderInfoDTOs;
	}
	
	public void create(BizUtLeaderInfoDTO verUbLeaderInfoDTO) throws ServiceException{
		BizUtLeaderDTO saoUbLeaderDTO = new BizUtLeaderDTO();
		BeanHelper.copyProperties(verUbLeaderInfoDTO, saoUbLeaderDTO);
		bizLeaderInfoService.create(saoUbLeaderDTO);
	}
	
	public void delete(java.lang.Long leaderOid) throws ServiceException{
		bizLeaderInfoService.delete(leaderOid);
	}
	
	public void update(BizUtLeaderInfoDTO verUbLeaderInfoDTO) throws ServiceException{
		BizUtLeaderDTO saoUbLeaderDTO = new BizUtLeaderDTO();
		BeanHelper.copyProperties(verUbLeaderInfoDTO, saoUbLeaderDTO);
		bizLeaderInfoService.update(saoUbLeaderDTO);
	}
	
	public BizUtLeaderInfoDTO get(java.lang.Long leaderOid) throws ServiceException{
		BizUtLeaderDTO saoUbLeaderDTO = bizLeaderInfoService.get(leaderOid);
		BizUtLeaderInfoDTO verUbLeaderInfoDTO = new BizUtLeaderInfoDTO();
		BeanHelper.copyProperties(saoUbLeaderDTO, verUbLeaderInfoDTO);
		return verUbLeaderInfoDTO;
	}
}
