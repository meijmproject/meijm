package com.yh.hr.component.infotemplate.facade;

import java.util.List;

import com.yh.hr.component.infotemplate.dto.ItLibraryGroupDetailDTO;
import com.yh.hr.component.infotemplate.service.PersonInformationService;
import com.yh.platform.core.exception.ServiceException;

public class PersonInformationFacade {
	private PersonInformationService personInformationService;
	public void setPersonInformationService(
			PersonInformationService personInformationService) {
		this.personInformationService = personInformationService;
	}
	public List<ItLibraryGroupDetailDTO> findInforList(String functionCode) throws ServiceException {

		return personInformationService.findInforList(functionCode) ;

	}
	public String findUnitType(String unitOid) throws ServiceException{
		return personInformationService.findUnitType(unitOid) ;
	}
	public String findUnitTypeForUpdate(String unitOid) throws ServiceException{
		return personInformationService.findUnitTypeForUpdate(unitOid);
	}
}
