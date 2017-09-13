package com.yh.hr.info.dataimport.person.facade;

import java.util.List;

import com.yh.hr.info.dataimport.person.service.VerPersonDataService;
import com.yh.hr.res.im.bo.ImCheckPersonInfo;
import com.yh.platform.core.exception.ServiceException;

public class VerPersonDataFacade {

	private VerPersonDataService verPersonDataService;



	public void setVerPersonDataService(VerPersonDataService verPersonDataService) {
		this.verPersonDataService = verPersonDataService;
	}



	public List<ImCheckPersonInfo> listImCheckPersonInfo() throws Exception{
		return verPersonDataService.listImCheckPersonInfo();
	}


	public void checkDataDic() throws Exception{
		verPersonDataService.checkDataDic();
	}


	public int countExceptionNum(String checkType) throws Exception{
		return verPersonDataService.countExceptionNum(checkType);
	}


	public int countExportPerson() throws Exception{
		return verPersonDataService.countExportPerson();
	}


	public void checkDataRelation() throws Exception{
		verPersonDataService.checkDataRelation();
	}


	public void checkDataComplete() throws Exception{
		verPersonDataService.checkDataComplete();
	}


	public void updateBatchPersonCheckTypeForCheck() throws ServiceException {
		verPersonDataService.updateBatchPersonCheckTypeForCheck();
	}

	public void updateBatchUnusualLogs() throws ServiceException {
		verPersonDataService.updateBatchUnusualLogs();
	}

	public void updateBatchAmountForCheck() throws ServiceException {
		verPersonDataService.updateBatchAmountForCheck(); 
	}
}
