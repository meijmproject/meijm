package com.yh.hr.info.personalinfoupdate.service;

import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PersonalInfoUpdateService {

	PbPersonInfoDTO getPbPersonInfoDTO() throws ServiceException;

}
