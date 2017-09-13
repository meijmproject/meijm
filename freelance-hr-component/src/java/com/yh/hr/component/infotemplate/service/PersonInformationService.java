package com.yh.hr.component.infotemplate.service;

import java.util.List;

import com.yh.hr.component.infotemplate.dto.ItLibraryGroupDetailDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PersonInformationService {
	public List<ItLibraryGroupDetailDTO> findInforList(String functionCode) throws ServiceException;

	public String findUnitType(String unitOid) throws ServiceException;

	public String findUnitTypeForUpdate(String unitOid) throws ServiceException;


}
