package com.yh.hr.info.view.service;

import com.yh.hr.info.ver.unit.person.dto.VerPbPersonInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface ViewPbPersonService {

	VerPbPersonInfoDTO getPbPersonInfoDTOById(Long personOid) throws ServiceException;

}
