package com.yh.hr.info.dataimport.person.facade;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.hr.info.dataimport.person.service.DicMappedService;
import com.yh.hr.res.im.dto.ImDicItemMappingDTO;
import com.yh.hr.res.im.dto.ImDicTypeMappingDTO;
import com.yh.platform.core.exception.ServiceException;

public class DicMappedFacade {

	private DicMappedService dicMappedService;

	public void setDicMappedService(DicMappedService dicMappedService) {
		this.dicMappedService = dicMappedService;
	}

	public List<JSONObject> listNoDicMapped() throws Exception{
		return dicMappedService.listNoDicMapped();
	};
	
	public List<ImDicItemMappingDTO> listImDicItemMappingDTOIsCreateMapping(Long dicTypeMappingOid,String isCreateMapping) throws Exception {
		return dicMappedService.listImDicItemMappingDTOIsCreateMapping(dicTypeMappingOid, isCreateMapping);
	}

	public ImDicTypeMappingDTO getImDicTypeMappingDTO(Long dicTypeMappingOid) throws Exception{
		return dicMappedService.get(dicTypeMappingOid);
	}

	public void  saveDicMapped(JSONObject json,String dicTypeCode) throws Exception{
		 dicMappedService.saveDicMapped(json,dicTypeCode);
	}

	public void updateImPerson(List<String> importItemNames,
			String dicTypeCode) throws Exception{
		 dicMappedService.updateImPerson(importItemNames,dicTypeCode);
	}

	public Boolean checkDicRepeat() throws Exception{
		return dicMappedService.checkDicRepeat();
	}

	public void updateBatchPersonCheckTypeForCheck() throws ServiceException {
		dicMappedService.updateBatchPersonCheckTypeForCheck();
	}

	public void updateBatchUnusualLogs() throws ServiceException {
		dicMappedService.updateBatchUnusualLogs();
	}

	public void updateBatchAmountForCheck() throws ServiceException {
		dicMappedService.updateBatchAmountForCheck(); 
	}

	public void checkData(String dicTypeCode) throws Exception{
		dicMappedService.checkData(dicTypeCode);		
	}
}
