package com.yh.hr.info.dataimport.person.service;

import java.util.List;

import com.yh.hr.res.im.bo.ImCheckPersonInfo;
import com.yh.platform.core.exception.ServiceException;


public interface VerPersonDataService {

    void updateImPerson(String dicTypeCode,List<String> importItemNames) throws ServiceException;
    Boolean checkDicRepeat () throws ServiceException;
	List<ImCheckPersonInfo> listImCheckPersonInfo() throws ServiceException;
	void checkDataDic() throws ServiceException;
	int countExceptionNum(String checkType) throws ServiceException;
	int countExportPerson( ) throws ServiceException;
	void checkDataRelation() throws ServiceException;
	void checkDataComplete() throws ServiceException;
	void updateBatchPersonCheckTypeForCheck() throws ServiceException;
	void updateBatchUnusualLogs() throws ServiceException;
	void updateBatchAmountForCheck() throws ServiceException;
	
	void checkDataDicRepeat(String databaseColumnCode) throws ServiceException;
	void checkDataRelationRepeat(String databaseColumnCode) throws ServiceException;
	void checkDataCompleteRepeat(String databaseColumnCode) throws ServiceException;


}
