package com.yh.hr.info.view.service.impl;


import org.springframework.beans.BeanUtils;

import com.yh.hr.info.ver.unit.person.dto.VerPbPersonInfoDTO;
import com.yh.hr.info.view.service.ViewPbPersonService;
import com.yh.hr.res.pb.dto.PbPersonAttachDTO;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.service.PbPersonAttachService;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.platform.core.exception.ServiceException;

public class ViewPbPersonServiceImpl implements ViewPbPersonService{
	private PbPersonInfoService	pbPersonInfoService;
	private PbPersonAttachService	pbPersonAttachService;
	
	public void setPbPersonInfoService(PbPersonInfoService pbPersonInfoService) {
		this.pbPersonInfoService = pbPersonInfoService;
	}
    
	public void setPbPersonAttachService(PbPersonAttachService pbPersonAttachService) {
		this.pbPersonAttachService = pbPersonAttachService;
	}

	public VerPbPersonInfoDTO getPbPersonInfoDTOById(Long personOid) throws ServiceException {
		VerPbPersonInfoDTO dto = new VerPbPersonInfoDTO();
		PbPersonInfoDTO pbPersonInfoDTO = pbPersonInfoService.getPbPersonInfoDTOById(personOid);
		if(null!=pbPersonInfoDTO)
		{ 
			BeanUtils.copyProperties(pbPersonInfoDTO, dto);
			PbPersonAttachDTO pbPersonAttachDTO = pbPersonAttachService.get(personOid);
		    if(null!=pbPersonAttachDTO)
		    {
		    BeanUtils.copyProperties(pbPersonAttachDTO, dto);
		    }
		}
		return dto;
	}

}
