package com.yh.hr.info.personalinfoupdate.service.impl;



import com.yh.hr.info.personalinfoupdate.queryhelper.PbPersonInfoQueryHelper;
import com.yh.hr.info.personalinfoupdate.service.PersonalInfoUpdateService;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public class PersonalInfoUpdateServiceImpl implements PersonalInfoUpdateService{

	public PbPersonInfoDTO getPbPersonInfoDTO() throws ServiceException {
		return PbPersonInfoQueryHelper.getPbPersonInfoDTO();
	}
	
}
