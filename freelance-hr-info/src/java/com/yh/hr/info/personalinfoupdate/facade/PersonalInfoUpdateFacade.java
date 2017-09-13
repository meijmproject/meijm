package com.yh.hr.info.personalinfoupdate.facade;

import com.yh.hr.info.personalinfoupdate.service.PersonalInfoUpdateService;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public class PersonalInfoUpdateFacade {
	private PersonalInfoUpdateService personalInfoUpdateService;
	public void setPersonalInfoUpdateService(
			PersonalInfoUpdateService personalInfoUpdateService) {
		this.personalInfoUpdateService = personalInfoUpdateService;
	}
	public PbPersonInfoDTO getPbPersonInfoDTO() throws ServiceException {
		return personalInfoUpdateService.getPbPersonInfoDTO();
	}
	
	

}
