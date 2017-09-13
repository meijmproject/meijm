package com.yh.hr.info.dataimport.person.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.hr.component.im.check.manage.RelationCheckServiceManage;
import com.yh.hr.component.im.check.service.dic.DictionaryCheckService;
import com.yh.hr.component.im.check.service.integrity.IntegrityCheckService;
import com.yh.hr.component.im.loghandle.service.CheckBatchHandleService;
import com.yh.hr.component.im.loghandle.service.CheckUnusualHandleService;
import com.yh.hr.component.im.loghandle.service.DicMappingLogHandleService;
import com.yh.hr.info.dataimport.person.queryhelper.VerPersonDataQueryHelper;
import com.yh.hr.info.dataimport.person.service.VerPersonDataService;
import com.yh.hr.info.dataimport.person.tool.Singleton;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.bo.ImCheckDefinition;
import com.yh.hr.res.im.bo.ImCheckPersonInfo;
import com.yh.hr.res.im.bo.ImDicItemMapping;
import com.yh.hr.res.im.dto.ImDicItemMappingDTO;
import com.yh.hr.res.im.dto.ImDicTypeMappingDTO;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.hr.res.im.service.ImCollectTemplateService;
import com.yh.hr.res.im.service.ImDicItemMappingService;
import com.yh.hr.res.im.service.ImDicTypeMappingService;
import com.yh.hr.res.im.service.ImImportBatchService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

public class VerPersonDataServiceImpl implements VerPersonDataService {
	private DictionaryCheckService dictionaryCheckService = (DictionaryCheckService) SpringBeanUtil.getBean("dictionaryCheckService");
	private IntegrityCheckService integrityCheckService = (IntegrityCheckService) SpringBeanUtil.getBean("integrityCheckService");
    private ImDicItemMappingService imDicItemMappingService;
    private ImDicTypeMappingService imDicTypeMappingService;
    private ImImportBatchService imImportBatchService;
    private CheckUnusualHandleService checkUnusualHandleService;
    private DicMappingLogHandleService dicMappingLogHandleService;
    private CheckBatchHandleService checkBatchHandleService;
    private ImCollectTemplateService imCollectTemplateService;
	public void setImCollectTemplateService(
			ImCollectTemplateService imCollectTemplateService) {
		this.imCollectTemplateService = imCollectTemplateService;
	}

	public void setDicMappingLogHandleService(
			DicMappingLogHandleService dicMappingLogHandleService) {
		this.dicMappingLogHandleService = dicMappingLogHandleService;
	}

	public void setCheckUnusualHandleService(
			CheckUnusualHandleService checkUnusualHandleService) {
		this.checkUnusualHandleService = checkUnusualHandleService;
	}

	public void setImDicTypeMappingService(
			ImDicTypeMappingService imDicTypeMappingService) {
		this.imDicTypeMappingService = imDicTypeMappingService;
	}

	public void setImDicItemMappingService(
			ImDicItemMappingService imDicItemMappingService) {
		this.imDicItemMappingService = imDicItemMappingService;
	}
	public void setImImportBatchService(ImImportBatchService imImportBatchService) {
		this.imImportBatchService = imImportBatchService;
	}

	public void setCheckBatchHandleService(
			CheckBatchHandleService checkBatchHandleService) {
		this.checkBatchHandleService = checkBatchHandleService;
	}
	@Override
	//importItemName:本次未映射的用户导入值
	public void updateImPerson(String dicTypeCode, List<String> importItemNames)throws ServiceException {
			ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
			ImDicTypeMappingDTO ImDicTypeMappingDTO =imDicTypeMappingService.findImDicTypeMappingDTOBydicTypeCode(dicTypeCode);
			String databaseColumnCodeName =imCollectTemplateService.getColumnCodeNameByColumnCode(ImDicTypeMappingDTO.getDatabaseColumnCode());
			for(String importItemName : importItemNames){
				VerPersonDataQueryHelper.updateImPerson(ImDicTypeMappingDTO.getDatabaseColumnCode(),databaseColumnCodeName,importItemName);
				//更新人员字典异常日志
				dicMappingLogHandleService.handlePersonLog(imImportBatchDTO.getImportBatchOid(), ImDicTypeMappingDTO.getDatabaseColumnCode(), importItemName);
			}
	}


	//检查item表是否存在未映射的字典项
	public Boolean checkDicRepeat () throws ServiceException {
		//查询需要进行字典检查的字段
		List<ImDicItemMapping> imDicItemMappingList = VerPersonDataQueryHelper.listCheckDicData();
		if(CollectionUtils.isNotEmpty(imDicItemMappingList)){
			return true;
		}
		return false;
	}

	@Override
	public List<ImCheckPersonInfo> listImCheckPersonInfo() throws ServiceException {
		return VerPersonDataQueryHelper.listImCheckPersonInfos(DicConstants.YHRS0003_0);
	}

	@Override
	public void checkDataDic() throws ServiceException {
		//查询需要进行字典检查的字段
		List<ImCheckDefinition> listTemplate = VerPersonDataQueryHelper.listCheckData(DicConstants.YHRS0140_4);
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		if(CollectionUtils.isNotEmpty(listTemplate)){
			for (int i=0;i<listTemplate.size();i++){
				ImCheckDefinition imCheckDefinition = listTemplate.get(i);
				String message = "字典校核中，共"+listTemplate.size()+"项，"+"正在校核第"+i+"项";
				Singleton.getInstance().setMessage(message);
				Boolean flag = false;//判断是否需要调字典检查日志接口
				String databaseColumnCode=imCheckDefinition.getDatabaseColumnCode();
				String databaseColumnCodeName =imCollectTemplateService.getColumnCodeNameByColumnCode(databaseColumnCode);
				String templateCollName = imCollectTemplateService.findTemplateCollNameByColumnCode(databaseColumnCode);
				ImDicTypeMappingDTO imDicTypeMappingDTO =imDicTypeMappingService.findImDicTypeMappingDTOByColumnCode(databaseColumnCode);
				//importItemNames 用户导入某个字典所有值
				List<String> importItemNames =VerPersonDataQueryHelper.listColumnValueByName(databaseColumnCodeName);
				if(CollectionUtils.isNotEmpty(importItemNames)){
					for(String importItemName : importItemNames){
						//把用户导入的值拿到字典表去比较，如果跟我们的标准一样，那我们则默认更新导入字典表（没有就新增），并且更新人员信息
						//dicItem不为空，证明与标准的字典值一样(是拿name比较的)
						DicItem dicItem =VerPersonDataQueryHelper.findDicItemByTypeIdAndDicItemName(imDicTypeMappingDTO.getDicTypeCode(),importItemName);
						ImDicItemMappingDTO  imDicItemMapping= imDicItemMappingService.getDicMappingDTOByColumnAndName(databaseColumnCode, importItemName);
						if(dicItem!=null){
							if(imDicItemMapping==null){
								ImDicItemMappingDTO imDicItemMappingDTO = new ImDicItemMappingDTO();
								imDicItemMappingDTO.setDicTypeMappingOid(imDicTypeMappingDTO.getDicTypeMappingOid());
								imDicItemMappingDTO.setImportItemName(importItemName);
								imDicItemMappingDTO.setEffectiveFlag(DicConstants.YHRS0003_1);
								imDicItemMappingDTO.setIsCreateMapping(DicConstants.YHRS0003_1);
								imDicItemMappingDTO.setDicItemCode(dicItem.getDicItemCode());
								imDicItemMappingDTO.setDicItemName(dicItem.getDicItemName());
								imDicItemMappingService.create(imDicItemMappingDTO);
							}
							VerPersonDataQueryHelper.updateImPersonBefore(imDicTypeMappingDTO.getDatabaseColumnCode(),databaseColumnCodeName,dicItem.getDicItemCode(),importItemName);
						}else{
							flag = true;
							Boolean isChecked = imDicItemMappingService.checkDicMappingForColumnAndName(databaseColumnCode,importItemName);
							//如果isChecked为true,代表改字典值已经映射，如果为false并且item表不存在importItemName的数据，然后才往item表增加一条数据
							if(!isChecked&&imDicItemMapping==null){
								ImDicItemMappingDTO imDicItemMappingDTO = new ImDicItemMappingDTO();
								//根据字典的字段去type表查询数据，唯一的，例如sexCode
								imDicItemMappingDTO.setDicTypeMappingOid(imDicTypeMappingDTO.getDicTypeMappingOid());
								imDicItemMappingDTO.setImportItemName(importItemName);
								imDicItemMappingDTO.setEffectiveFlag(DicConstants.YHRS0003_1);
								imDicItemMappingDTO.setIsCreateMapping(DicConstants.YHRS0003_0);
								imDicItemMappingService.create(imDicItemMappingDTO);
							}
							
							//20170721-zhangyx,已映射字典项，也需要更新人员的字典值，否则第二次导入时这类人员字典值会为空
							if (isChecked){
								flag = false;
								VerPersonDataQueryHelper.updateImPersonBefore(imDicTypeMappingDTO.getDatabaseColumnCode(),databaseColumnCodeName,imDicItemMapping.getDicItemCode(),importItemName);
							}
								
						}
					}
				}
				if(flag){
					dictionaryCheckService.check(imImportBatchDTO.getImportBatchOid(), databaseColumnCode, templateCollName);
				}
			}
		}
	}

	@Override
	public int countExceptionNum(String checkType) throws ServiceException {
		return VerPersonDataQueryHelper.countExceptionNum(checkType);
	}

	@Override
	public int countExportPerson() throws ServiceException {
		return VerPersonDataQueryHelper.countExportPerson();
	}

	@Override
	public void checkDataRelation() throws ServiceException {
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		List<ImCheckDefinition> listTemplate = VerPersonDataQueryHelper.listCheckData(DicConstants.YHRS0140_5);
		if(CollectionUtils.isNotEmpty(listTemplate)){
			for (int i=0;i<listTemplate.size();i++){
				ImCheckDefinition imCheckDefinition = listTemplate.get(i);
				String message = "关联性校核中，共"+listTemplate.size()+"项，"+"正在校核第"+i+"项";
				Singleton.getInstance().setMessage(message);
				String databaseColumnCode=imCheckDefinition.getDatabaseColumnCode();
				RelationCheckServiceManage.check(imImportBatchDTO.getImportBatchOid(), databaseColumnCode);
			}
		}
	}

	@Override
	public void checkDataComplete() throws ServiceException {
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		List<ImCheckDefinition> listTemplate = VerPersonDataQueryHelper.listCheckData(DicConstants.YHRS0140_6);
		if(CollectionUtils.isNotEmpty(listTemplate)){
			for (int i=0;i<listTemplate.size();i++){
				ImCheckDefinition imCheckDefinition = listTemplate.get(i);
				String message = "完整性校核中，共"+listTemplate.size()+"项，"+"正在校核第"+i+"项";
				Singleton.getInstance().setMessage(message);
				String databaseColumnCode=imCheckDefinition.getDatabaseColumnCode();
				String templateCollName =imCollectTemplateService.findTemplateCollNameByColumnCode(databaseColumnCode);
				integrityCheckService.check(imImportBatchDTO.getImportBatchOid(), databaseColumnCode, templateCollName);
			}
		}
	}

	@Override
	public void updateBatchPersonCheckTypeForCheck() throws ServiceException {
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		Long importBatchOid = imImportBatchDTO.getImportBatchOid();
		checkUnusualHandleService.updateBatchPersonCheckTypeForCheck(importBatchOid);  //数据校核批量更新校核人员检查状态
	}

	@Override
	public void updateBatchUnusualLogs() throws ServiceException {
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		Long importBatchOid = imImportBatchDTO.getImportBatchOid();
		checkUnusualHandleService.updateBatchUnusualLogs(importBatchOid);  // 数据校核批量更新导入数据异常日志
	}

	@Override
	public void updateBatchAmountForCheck() throws ServiceException {
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		Long importBatchOid = imImportBatchDTO.getImportBatchOid();
		checkBatchHandleService.updateBatchAmountForCheck(importBatchOid);   //数据校核更新导入批次检查数
	}
	@Override
	public void checkDataDicRepeat(String databaseColumnCode) throws ServiceException {
		//查询需要进行字典检查的字段
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		String templateCollName =imCollectTemplateService.findTemplateCollNameByColumnCode(databaseColumnCode);
		dictionaryCheckService.check(imImportBatchDTO.getImportBatchOid(), databaseColumnCode, templateCollName);
	}

	@Override
	public void checkDataRelationRepeat(String databaseColumnCode) throws ServiceException {
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		RelationCheckServiceManage.check(imImportBatchDTO.getImportBatchOid(), databaseColumnCode);
		
	}

	@Override
	public void checkDataCompleteRepeat(String databaseColumnCode) throws ServiceException {
		ImImportBatchDTO imImportBatchDTO =imImportBatchService.getCurrentImportBatchDTO();
		String templateCollName =imCollectTemplateService.findTemplateCollNameByColumnCode(databaseColumnCode);
		integrityCheckService.check(imImportBatchDTO.getImportBatchOid(), databaseColumnCode, templateCollName);
	}
		
}
