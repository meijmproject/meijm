package com.yh.hr.info.dataimport.person.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.hr.res.im.dto.ImDicItemMappingDTO;
import com.yh.hr.res.im.dto.ImDicTypeMappingDTO;
import com.yh.platform.core.exception.ServiceException;


public interface DicMappedService {
   
	public List<JSONObject> listNoDicMapped() throws Exception;
	
	public List<ImDicItemMappingDTO> listImDicItemMappingDTOIsCreateMapping(Long dicTypeMappingOid,String isCreateMapping) throws Exception;

	public ImDicTypeMappingDTO get(Long dicTypeMappingOid) throws ServiceException;

	public void saveDicMapped(JSONObject json,String dicTypeCode) throws  Exception;

	public void updateImPerson(List<String> importItemNames,String dicTypeCode) throws  Exception;

	public Boolean checkDicRepeat() throws  Exception;

	void updateBatchPersonCheckTypeForCheck() throws ServiceException;
	void updateBatchUnusualLogs() throws ServiceException;
	void updateBatchAmountForCheck() throws ServiceException;

	public void checkData(String dicTypeCode) throws  Exception;
}
