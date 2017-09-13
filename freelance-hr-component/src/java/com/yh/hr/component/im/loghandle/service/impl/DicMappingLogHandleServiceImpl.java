package com.yh.hr.component.im.loghandle.service.impl;

import jade.workflow.utils.DaoUtil;
import jade.workflow.utils.SpringBeanUtil;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.im.loghandle.service.DicMappingLogHandleService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.dto.ImDataUnusualLogDTO;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.im.service.ImCheckPersonUnusualService;
import com.yh.hr.res.im.service.ImDataUnusualLogService;
import com.yh.hr.res.im.service.ImImportBatchService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 字典映射日志批量更新服务
 * @author wangx
 * @date 2017-07-13
 * @version 1.0
 */
public class DicMappingLogHandleServiceImpl implements DicMappingLogHandleService {

	private ImCheckPersonUnusualService imCheckPersonUnusualService = (ImCheckPersonUnusualService)SpringBeanUtil.getBean("imCheckPersonUnusualService");
	private ImDataUnusualLogService ImDataUnusualLogService = (ImDataUnusualLogService)SpringBeanUtil.getBean("imDataUnusualLogService");
	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService)SpringBeanUtil.getBean("imCheckPersonInfoService");
	private ImImportBatchService imImportBatchService = (ImImportBatchService)SpringBeanUtil.getBean("imImportBatchService");

	/**
	 * 字典映射人员检查异常明细处理
	 * @param importBatchOid 当期导入批次OID
	 * @param databaseColumnCode 数据库字段代码
	 * @param importCollValue 该采集项导入值
	 * @throws ServiceException
	 */
	public void handlePersonLog(Long importBatchOid, String databaseColumnCode, String importCollValue) throws ServiceException {
		if(databaseColumnCode==null||importBatchOid==null) {
			throw new ServiceException(null,"参数不能为空！");
		}
		List<Long> personOids = imCheckPersonUnusualService.getDicNoPassedPersonOids(importBatchOid, databaseColumnCode, importCollValue);
		
		//更新人员异常日志状态
		updatePersonUnusualLog(importBatchOid, databaseColumnCode, importCollValue);
		
		if(CollectionUtils.isNotEmpty(personOids)) {
			ImDataUnusualLogDTO imDataUnusualLogDTO = ImDataUnusualLogService.findImDataUnusualLogDTOByBatchOidAndType(importBatchOid, DicConstants.YHRS0140_4);
			for(Long checkPersonInfoOid : personOids) {
				Boolean noPassedExist = imCheckPersonUnusualService.checkNoPassedDatas(importBatchOid, checkPersonInfoOid);
				if(!noPassedExist) {
					//更新校核人员检查状态
					ImCheckPersonInfoDTO personDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
					if(personDTO!=null) {
						personDTO.setCheckStatus(DicConstants.YHRS0003_1);
						imCheckPersonInfoService.update(personDTO);
					}
					//更新导入批次数据量
					ImImportBatchDTO imImportBatchDTO = imImportBatchService.get(importBatchOid);
					Integer checkNopassedAmount = imImportBatchDTO.getCheckNopassedAmount()==null?0:imImportBatchDTO.getCheckNopassedAmount();
					Integer checkPassedAmount = imImportBatchDTO.getCheckPassedAmount()==null?0:imImportBatchDTO.getCheckPassedAmount();
					imImportBatchDTO.setCheckPassedAmount(++checkPassedAmount);
					imImportBatchDTO.setCheckNopassedAmount(--checkNopassedAmount);
					imImportBatchService.update(imImportBatchDTO);
				}
				//更新导入数据异常日志信息
				if(imDataUnusualLogDTO!=null) {
					Integer checkNopassedAmount = imDataUnusualLogDTO.getCheckNopassedAmount()==null?0:imDataUnusualLogDTO.getCheckNopassedAmount();
					if(--checkNopassedAmount<=0) {
						checkNopassedAmount = 0;
						imDataUnusualLogDTO.setCheckStatus(DicConstants.YHRS0003_1);
					}
					imDataUnusualLogDTO.setCheckNopassedAmount(checkNopassedAmount);
					ImDataUnusualLogService.update(imDataUnusualLogDTO);
				}
			}
		}
	}
	
	/**
	 * 更新指定被字典已映射的字典检查日志状态为通过
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @param importCollValue
	 * @throws ServiceException
	 */
	private void updatePersonUnusualLog(Long importBatchOid, String databaseColumnCode, String importCollValue) throws ServiceException {
		String hql = "update ImCheckPersonUnusual cpu set cpu.checkStatus = '1' where cpu.effectiveFlag='1' and cpu.checkStatus = '0' and cpu.checkType = '4'" +
				" and cpu.importBatchOid = "+importBatchOid+" and cpu.databaseColumnCode='"+databaseColumnCode+"' and cpu.databaseColumnValue='"+importCollValue+"'";
		DaoUtil.executeUpdate(hql);
	}
}
