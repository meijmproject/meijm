package com.yh.hr.component.im.loghandle.service.impl;

import com.yh.hr.component.im.dto.CheckResultDTO;
import jade.workflow.utils.SpringBeanUtil;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.im.loghandle.service.CheckUnusualHandleService;
import com.yh.hr.component.im.tablehandle.util.SqlDaoUtil;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.dto.ImCheckPersonUnusualDTO;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.hr.res.im.dto.ImDataUnusualLogDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.im.service.ImCheckPersonUnusualService;
import com.yh.hr.res.im.service.ImCollectTemplateService;
import com.yh.hr.res.im.service.ImDataUnusualLogService;
import com.yh.hr.res.im.service.ImImportBatchService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员导入第一次数据校核更新日志服务
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public class CheckUnusualHandleServiceImpl implements CheckUnusualHandleService {
	
	private ImDataUnusualLogService ImDataUnusualLogService = (ImDataUnusualLogService)SpringBeanUtil.getBean("imDataUnusualLogService");
	private ImCollectTemplateService imCollectTemplateService = (ImCollectTemplateService)SpringBeanUtil.getBean("imCollectTemplateService");
	private ImImportBatchService imImportBatchService = (ImImportBatchService)SpringBeanUtil.getBean("imImportBatchService");
	private ImCheckPersonUnusualService imCheckPersonUnusualService = (ImCheckPersonUnusualService)SpringBeanUtil.getBean("imCheckPersonUnusualService");
	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService)SpringBeanUtil.getBean("imCheckPersonInfoService");

	/**
	 * 导入异常日志处理
	 * @param resultList
	 * @throws ServiceException
	 */
	public void handleLog(List<CheckResultDTO> resultList) throws ServiceException {
		if(CollectionUtils.isNotEmpty(resultList)) {
			//清空对应检查类型的异常日志
//			emptyAllLogsByType(resultList);
			int checkTypeNum_1 = 0;
			int checkTypeNum_2 = 0;
			int checkTypeNum_3 = 0;
			int checkTypeNum_4 = 0;
			int checkTypeNum_5 = 0;
			int checkTypeNum_6 = 0;
			int checkTypeNum_7 = 0;
			for(CheckResultDTO result :resultList) {
				String checkType = result.getCheckType();
				if(checkType==null) {
					throw new ServiceException(null,"检查类型不能为空！");
				}else {
					//导入前数据检查
					if(DicConstants.YHRS0140_1.equals(checkType)||DicConstants.YHRS0140_2.equals(checkType)||DicConstants.YHRS0140_3.equals(checkType)||DicConstants.YHRS0140_7.equals(checkType)) {
						if((checkTypeNum_1==1&&DicConstants.YHRS0140_1.equals(checkType))||(checkTypeNum_2==1&&DicConstants.YHRS0140_2.equals(checkType))
								||(checkTypeNum_3==1&&DicConstants.YHRS0140_3.equals(checkType))||(checkTypeNum_7==1&&DicConstants.YHRS0140_7.equals(checkType))) {
							continue;
						}
						//导入数据异常日志处理
						handleDataLog(result);
						if(DicConstants.YHRS0140_1.equals(checkType)) {
							checkTypeNum_1 = 1;
						}else if(DicConstants.YHRS0140_2.equals(checkType)) {
							checkTypeNum_2 = 1;
						}else if(DicConstants.YHRS0140_3.equals(checkType)) {
							checkTypeNum_3 = 1;
						}else if(DicConstants.YHRS0140_7.equals(checkType)) {
							checkTypeNum_7 = 1;
						}
					}else if(DicConstants.YHRS0140_4.equals(checkType)||DicConstants.YHRS0140_5.equals(checkType)||DicConstants.YHRS0140_6.equals(checkType)) {//导入后数据校核
						//人员检查异常明细处理
						handlePersonLog(result);
						if((checkTypeNum_4==1&&DicConstants.YHRS0140_4.equals(checkType))||(checkTypeNum_5==1&&DicConstants.YHRS0140_5.equals(checkType))||(checkTypeNum_6==1&&DicConstants.YHRS0140_6.equals(checkType))) {
							continue;
						}
						//校核数据异常日志处理
						handlePersonDataLog(result);
						if(DicConstants.YHRS0140_4.equals(checkType)) {
							checkTypeNum_4 = 1;
						}else if(DicConstants.YHRS0140_5.equals(checkType)) {
							checkTypeNum_5 = 1;
						}else if(DicConstants.YHRS0140_6.equals(checkType)) {
							checkTypeNum_6 = 1;
						}
					}else {
						throw new ServiceException(null,"检查类型暂未定义！");
					}
				}
			}
		}
	}
	
	/**
	 * 插入前先清空对应检查类型的异常日志
	 * @param resultList
	 * @throws ServiceException
	 */
//	private void emptyAllLogsByType(List<CheckResultDTO> resultList) throws ServiceException {
//		for(CheckResultDTO result :resultList) {
//			String checkType = result.getCheckType();
//			if(checkType==null) {
//				throw new ServiceException(null,"检查类型不能为空！");
//			}
//			//导入批次OID
//			Long importBatchOid = result.getImportBatchOid();
//			if(importBatchOid==null) {//如果导入批次为空，则默认取当前最新批次的OID
//				importBatchOid = imImportBatchService.getCurrentImportBatchDTO().getImportBatchOid();
//			}
//			if(DicConstants.YHRS0140_1.equals(checkType)||DicConstants.YHRS0140_2.equals(checkType)||DicConstants.YHRS0140_3.equals(checkType)) {
//				//查找该批次检查类型的数据异常日志
//				ImDataUnusualLogDTO logDTO = ImDataUnusualLogService.findImDataUnusualLogDTOByBatchOidAndType(importBatchOid, checkType);
//				if(logDTO!=null) {
//					ImDataUnusualLogService.delete(logDTO.getDataUnusualOid());
//				}
//			}else if(DicConstants.YHRS0140_4.equals(checkType)||DicConstants.YHRS0140_5.equals(checkType)||DicConstants.YHRS0140_6.equals(checkType)) {
//				//校核人员OID
//				Long checkPersonInfoOid = result.getCheckPersonInfoOid();
//				if(checkPersonInfoOid==null) {
//					throw new ServiceException(null,"校核人员OID不能为空！");
//				}
//				//查找该批次检查类型的数据异常日志
//				ImDataUnusualLogDTO logDTO = ImDataUnusualLogService.findImDataUnusualLogDTOByBatchOidAndType(importBatchOid, checkType);
//				if(logDTO!=null) {
//					ImDataUnusualLogService.delete(logDTO.getDataUnusualOid());
//				}
//				List<ImCheckPersonUnusualDTO> personLogs = imCheckPersonUnusualService.findImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid, checkPersonInfoOid, checkType);
//				if(CollectionUtils.isNotEmpty(personLogs)) {
//					for(ImCheckPersonUnusualDTO dto : personLogs) {
//						imCheckPersonUnusualService.delete(dto.getCheckPersonUnusualOid());
//					}
//				}
//			}else {
//				throw new ServiceException(null,"检查类型暂未定义！");
//			}
//		}
//	}
	
	/**
	 * 导入数据异常日志处理
	 * @param result
	 * @throws ServiceException
	 */
	private void handleDataLog(CheckResultDTO result) throws ServiceException {
		//检查类型
		String checkType = result.getCheckType();
		//导入批次OID
		Long importBatchOid = result.getImportBatchOid();
		if(importBatchOid==null) {//如果导入批次为空，则默认取当前最新批次的OID
			importBatchOid = imImportBatchService.getCurrentImportBatchDTO().getImportBatchOid();
		}
		//查找该批次检查类型的数据异常日志
		ImDataUnusualLogDTO logDTO = ImDataUnusualLogService.findImDataUnusualLogDTOByBatchOidAndType(importBatchOid, checkType);
		if(logDTO!=null) {
			int checkNopassedAmount = logDTO.getCheckNopassedAmount()==null?0:logDTO.getCheckNopassedAmount();
			logDTO.setCheckStatus(DicConstants.YHRS0003_0);
			logDTO.setCheckNopassedAmount(++checkNopassedAmount);
			ImDataUnusualLogService.update(logDTO);
		}else {
			ImDataUnusualLogDTO imDataUnusualLogDTO = new ImDataUnusualLogDTO();
			imDataUnusualLogDTO.setImportBatchOid(importBatchOid);
			imDataUnusualLogDTO.setCheckType(checkType);
			imDataUnusualLogDTO.setCheckStatus(DicConstants.YHRS0003_0);
			imDataUnusualLogDTO.setCheckNopassedAmount(1);
			ImDataUnusualLogService.create(imDataUnusualLogDTO);
		}
	}
	
	/**
	 * 校核数据异常日志处理
	 * @param result
	 * @throws ServiceException
	 */
	private void handlePersonDataLog(CheckResultDTO result) throws ServiceException {
		//检查类型
		String checkType = result.getCheckType();
		//导入批次OID
		Long importBatchOid = result.getImportBatchOid();
		if(importBatchOid==null) {//如果导入批次为空，则默认取当前最新批次的OID
			importBatchOid = imImportBatchService.getCurrentImportBatchDTO().getImportBatchOid();
		}
		//校核人员OID
		Long checkPersonInfoOid = result.getCheckPersonInfoOid();
		if(checkPersonInfoOid==null) {
			throw new ServiceException(null,"校核人员OID不能为空！");
		}
		Boolean hasNoPassData = imCheckPersonUnusualService.checkNoPassedDatasByCheckType(importBatchOid, checkPersonInfoOid, checkType);
		//查找该批次检查类型的数据异常日志
		ImDataUnusualLogDTO logDTO = ImDataUnusualLogService.findImDataUnusualLogDTOByBatchOidAndType(importBatchOid, checkType);
		if(logDTO!=null) {
			int checkNopassedAmount = logDTO.getCheckNopassedAmount()==null?0:logDTO.getCheckNopassedAmount();
			if(!hasNoPassData) {
				if(--checkNopassedAmount<=0) {
					checkNopassedAmount = 0;
					logDTO.setCheckStatus(DicConstants.YHRS0003_1);
				}
			}else {
				checkNopassedAmount++;
				logDTO.setCheckStatus(DicConstants.YHRS0003_0);
			}
			logDTO.setCheckNopassedAmount(checkNopassedAmount);
			ImDataUnusualLogService.update(logDTO);
		}else {
			ImDataUnusualLogDTO imDataUnusualLogDTO = new ImDataUnusualLogDTO();
			imDataUnusualLogDTO.setImportBatchOid(importBatchOid);
			imDataUnusualLogDTO.setCheckType(checkType);
			if(!hasNoPassData) {
				imDataUnusualLogDTO.setCheckStatus(DicConstants.YHRS0003_1);
				imDataUnusualLogDTO.setCheckNopassedAmount(0);
			}else {
				imDataUnusualLogDTO.setCheckStatus(DicConstants.YHRS0003_0);
				imDataUnusualLogDTO.setCheckNopassedAmount(1);
			}
			ImDataUnusualLogService.create(imDataUnusualLogDTO);
		}
	}

	/**
	 * 人员检查异常明细处理
	 * @param result
	 * @throws ServiceException
	 */
	private void handlePersonLog(CheckResultDTO result) throws ServiceException {
		//校核人员OID
		Long checkPersonInfoOid = result.getCheckPersonInfoOid();
		if(checkPersonInfoOid==null) {
			throw new ServiceException(null,"校核人员OID不能为空！");
		}
		//导入批次OID
		Long importBatchOid = result.getImportBatchOid();
		if(importBatchOid==null) {//如果导入批次为空，则默认取当前最新批次的OID
			importBatchOid = imImportBatchService.getCurrentImportBatchDTO().getImportBatchOid();
		}
		//检查类型
		String checkType = result.getCheckType();
		if(!DicConstants.YHRS0140_5.equals(checkType)) {
			String databaseColumnCode = result.getDatabaseColumnCode();
			if(databaseColumnCode==null) {
				throw new ServiceException(null,"字段名不能为空！");
			}
			ImCollectTemplateDTO templateDTO = imCollectTemplateService.findCollTemplateByColumnCode(databaseColumnCode);
			if(templateDTO==null) {
				throw new ServiceException(null,"字段名"+databaseColumnCode+"采集项映射模板中未定义！");
			}
			String databaseColumnName = templateDTO.getTemplateCollName();
			ImCheckPersonUnusualDTO personLogDTO = imCheckPersonUnusualService.findImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid, checkPersonInfoOid, checkType, databaseColumnCode);
			if(personLogDTO!=null) {
				personLogDTO.setDatabaseColumnValue(result.getImportCollValue());
				personLogDTO.setCheckStatus(result.getCheckStatus()==null?DicConstants.YHRS0003_0:result.getCheckStatus());
				personLogDTO.setRemark(result.getCheckMessage());
				imCheckPersonUnusualService.update(personLogDTO);
			}else {
				personLogDTO = new ImCheckPersonUnusualDTO();
				personLogDTO.setCheckPersonInfoOid(checkPersonInfoOid);
				personLogDTO.setImportBatchOid(importBatchOid);
				personLogDTO.setCheckType(result.getCheckType());
				personLogDTO.setDatabaseColumnCode(result.getDatabaseColumnCode());
				personLogDTO.setDatabaseColumnName(databaseColumnName);
				personLogDTO.setDatabaseColumnValue(result.getImportCollValue());
				personLogDTO.setCheckStatus(result.getCheckStatus()==null?DicConstants.YHRS0003_0:result.getCheckStatus());
				personLogDTO.setRemark(result.getCheckMessage());
				imCheckPersonUnusualService.create(personLogDTO);
			}
		}else {
			ImCheckPersonUnusualDTO personLogDTO = imCheckPersonUnusualService.findRelationImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid, checkPersonInfoOid);
			if(personLogDTO!=null) {
				personLogDTO.setCheckStatus(result.getCheckStatus()==null?DicConstants.YHRS0003_0:result.getCheckStatus());
				personLogDTO.setRemark(result.getCheckMessage());
				imCheckPersonUnusualService.update(personLogDTO);
			}else {
				personLogDTO = new ImCheckPersonUnusualDTO();
				personLogDTO.setCheckPersonInfoOid(checkPersonInfoOid);
				personLogDTO.setImportBatchOid(importBatchOid);
				personLogDTO.setCheckType(result.getCheckType());
				personLogDTO.setCheckStatus(result.getCheckStatus()==null?DicConstants.YHRS0003_0:result.getCheckStatus());
				personLogDTO.setRemark(result.getCheckMessage());
				imCheckPersonUnusualService.create(personLogDTO);
			}
		}
	}
	
	/**
	 * 更新校核人员检查状态
	 * @param resultList
	 * @throws ServiceException
	 */
	public void updatePersonCheckType(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException {
		Boolean noPassedExist = imCheckPersonUnusualService.checkNoPassedDatas(importBatchOid, checkPersonInfoOid);
		if(!noPassedExist) {
			//更新校核人员检查状态
			ImCheckPersonInfoDTO personDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
			if(personDTO!=null) {
				personDTO.setCheckStatus(DicConstants.YHRS0003_1);
				imCheckPersonInfoService.update(personDTO);
			}
		}
	}

	/**
	 * 数据校核批量更新校核人员检查状态
	 * @param importBatchOid
	 * @throws ServiceException
	 */
	public void updateBatchPersonCheckTypeForCheck(Long importBatchOid) throws ServiceException {
		//先批量更新检查状态为已通过
		String updateHql = "update yhc_im_check_person_info cpi set cpi.CHECK_STATUS='1' where cpi.IMPORT_BATCH_OID="+importBatchOid;
		SqlDaoUtil.executeSqlUpdate(updateHql);
		
		//再通过关联人员异常日志批量更新检查状态为不通过
		String updateBatchHql = "update yhc_im_check_person_info cpi set cpi.CHECK_STATUS='0' where cpi.IMPORT_BATCH_OID="+importBatchOid
				+" and cpi.CHECK_PERSON_INFO_OID in(select cpu.CHECK_PERSON_INFO_OID from yhc_im_check_person_unusual cpu where cpu.IMPORT_BATCH_OID="+importBatchOid+" and cpu.CHECK_STATUS='0' and cpu.EFFECTIVE_FLAG='1')";
		SqlDaoUtil.executeSqlUpdate(updateBatchHql);
	}
	
	/**
	 * 数据校核批量更新导入数据异常日志
	 * @param importBatchOid
	 * @throws ServiceException
	 */
	public void updateBatchUnusualLogs(Long importBatchOid) throws ServiceException {
		//先清除所有数据校核检查异常日志
		String delHql = "delete from yhc_im_data_unusual_log where check_Type in('4','5','6') and IMPORT_BATCH_OID="+importBatchOid;
		SqlDaoUtil.executeSqlUpdate(delHql);
		
		//字典项检查
		String dicCheckHql = "insert into yhc_im_data_unusual_log(IMPORT_BATCH_OID,CHECK_TYPE,CHECK_STATUS,CHECK_NOPASSED_AMOUNT) " +
				"(select " +importBatchOid+
				",'4'" +
				",IF(count(1)=0,'1','0')" +
				",count(1)" +
				" FROM yhc_im_check_person_unusual cpu where cpu.CHECK_TYPE='4' and cpu.CHECK_STATUS='0' and cpu.EFFECTIVE_FLAG='1' and cpu.IMPORT_BATCH_OID="+importBatchOid+")";
		SqlDaoUtil.executeSqlUpdate(dicCheckHql);
		
		//关联性检查
		String relationCheckHql = "insert into yhc_im_data_unusual_log(IMPORT_BATCH_OID,CHECK_TYPE,CHECK_STATUS,CHECK_NOPASSED_AMOUNT) " +
				"(select " +importBatchOid+
				",'5'" +
				",IF(count(1)=0,'1','0')" +
				",count(1)" +
				" FROM yhc_im_check_person_unusual cpu where cpu.CHECK_TYPE='5' and cpu.CHECK_STATUS='0' and cpu.EFFECTIVE_FLAG='1' and cpu.IMPORT_BATCH_OID="+importBatchOid+")";
		SqlDaoUtil.executeSqlUpdate(relationCheckHql);
		
		//完整性检查
		String integrityCheckHql = "insert into yhc_im_data_unusual_log(IMPORT_BATCH_OID,CHECK_TYPE,CHECK_STATUS,CHECK_NOPASSED_AMOUNT) " +
				"(select " +importBatchOid+
				",'6'" +
				",IF(count(1)=0,'1','0')" +
				",count(1)" +
				" FROM yhc_im_check_person_unusual cpu where cpu.CHECK_TYPE='6' and cpu.CHECK_STATUS='0' and cpu.EFFECTIVE_FLAG='1' and cpu.IMPORT_BATCH_OID="+importBatchOid+")";
		SqlDaoUtil.executeSqlUpdate(integrityCheckHql);
	}
}
