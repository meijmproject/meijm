package com.yh.hr.info.dataimport.person.service.impl;

import java.util.List;

import com.yh.hr.info.dataimport.person.queryhelper.VerPersonDataQueryHelper;
import com.yh.hr.info.dataimport.person.service.DicMappedService;
import com.yh.hr.info.dataimport.person.service.VerPersonDataService;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.info.dataimport.person.queryhelper.DicMappedQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.bo.ImCheckDefinition;
import com.yh.hr.res.im.dto.ImDicItemMappingDTO;
import com.yh.hr.res.im.dto.ImDicTypeMappingDTO;
import com.yh.hr.res.im.service.ImDicItemMappingService;
import com.yh.hr.res.im.service.ImDicTypeMappingService;
import com.yh.platform.core.exception.ServiceException;

public class DicMappedServiceImpl implements DicMappedService {
	private ImDicItemMappingService imDicItemMappingService;
	private ImDicTypeMappingService imDicTypeMappingService;
	private VerPersonDataService verPersonDataService;
	public void setVerPersonDataService(VerPersonDataService verPersonDataService) {
		this.verPersonDataService = verPersonDataService;
	}
	public void setImDicTypeMappingService(
			ImDicTypeMappingService imDicTypeMappingService) {
		this.imDicTypeMappingService = imDicTypeMappingService;
	}
	public void setImDicItemMappingService(
			ImDicItemMappingService imDicItemMappingService) {
		this.imDicItemMappingService = imDicItemMappingService;
	}
	public List<JSONObject> listNoDicMapped() throws Exception {
		return DicMappedQueryHelper.listNoDicMapped();
	}
	public List<ImDicItemMappingDTO> listImDicItemMappingDTOIsCreateMapping(Long dicTypeMappingOid,String isCreateMapping) throws Exception {
		return imDicItemMappingService.findImDicItemMappingListByTypeOid(dicTypeMappingOid, isCreateMapping);
	}
	@Override
	public ImDicTypeMappingDTO get(Long dicTypeMappingOid)
			throws ServiceException {
		return imDicTypeMappingService.get(dicTypeMappingOid);
	}
	@Override
	public void saveDicMapped(JSONObject json,String dicTypeCode) throws Exception {
			String dicItemMappingOid = json.getString("dicItemMappingOid");
			String dicItemCode = json.getString("dicItemCode");
			String dicItemName = json.getString("dicItemName");
			ImDicItemMappingDTO imDicItemMappingDTO =imDicItemMappingService.get(Long.valueOf(dicItemMappingOid));
			imDicItemMappingDTO.setDicItemCode(dicItemCode);
			imDicItemMappingDTO.setDicItemName(dicItemName);
			imDicItemMappingDTO.setIsCreateMapping(DicConstants.YHRS0003_1);
			imDicItemMappingService.update(imDicItemMappingDTO);
		
	}
	@Override
	public void updateImPerson(List<String> importItemNames,
			String dicTypeCode) throws Exception {
		//更新临时表的字典值
		verPersonDataService.updateImPerson(dicTypeCode, importItemNames);
	}
	@Override
	public Boolean checkDicRepeat() throws Exception {
		//重新检查字典项
		return verPersonDataService.checkDicRepeat();
	}
	@Override
	public void updateBatchPersonCheckTypeForCheck() throws ServiceException {
		verPersonDataService.updateBatchPersonCheckTypeForCheck();  //数据校核批量更新校核人员检查状态
	}

	@Override
	public void updateBatchUnusualLogs() throws ServiceException {
		verPersonDataService.updateBatchUnusualLogs();  // 数据校核批量更新导入数据异常日志
	}

	@Override
	public void updateBatchAmountForCheck() throws ServiceException {
		verPersonDataService.updateBatchAmountForCheck();   //数据校核更新导入批次检查数
	}
	@Override
	public void checkData(String dicTypeCode) throws Exception {
		ImDicTypeMappingDTO ImDicTypeMappingDTO =imDicTypeMappingService.findImDicTypeMappingDTOBydicTypeCode(dicTypeCode);
		String databaseColumnCode = ImDicTypeMappingDTO.getDatabaseColumnCode();
		verPersonDataService.checkDataDicRepeat(databaseColumnCode);
		verPersonDataService.checkDataRelation();
		List<ImCheckDefinition> listTemplate = VerPersonDataQueryHelper.listCheckData(DicConstants.YHRS0140_6);
		if(CollectionUtils.isNotEmpty(listTemplate)){
			for (ImCheckDefinition def:listTemplate){
				if(databaseColumnCode.equals(def.getDatabaseColumnCode())) {
					verPersonDataService.checkDataCompleteRepeat(databaseColumnCode);
				}
			}
		}
		
	}

}
