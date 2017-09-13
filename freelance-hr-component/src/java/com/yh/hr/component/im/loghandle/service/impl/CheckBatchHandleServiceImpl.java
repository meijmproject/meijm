package com.yh.hr.component.im.loghandle.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import jade.workflow.utils.SpringBeanUtil;

import com.yh.hr.component.im.loghandle.service.CheckBatchHandleService;
import com.yh.hr.component.im.tablehandle.util.SqlDaoUtil;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImDataUnusualLogDTO;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.hr.res.im.service.ImDataUnusualLogService;
import com.yh.hr.res.im.service.ImImportBatchService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 导入批次检查数量更新服务
 * @author wangx
 * @date 2017-07-13
 * @version 1.0
 */
public class CheckBatchHandleServiceImpl implements CheckBatchHandleService {

	private ImDataUnusualLogService imDataUnusualLogService = (ImDataUnusualLogService)SpringBeanUtil.getBean("imDataUnusualLogService");
	private ImImportBatchService imImportBatchService = (ImImportBatchService)SpringBeanUtil.getBean("imImportBatchService");

	/**
	 * 导入前检查更新批次检查数量
	 * @param importBatchOid 当期导入批次OID
	 * @param hasCheckAbnormity 是否检查有异常
	 * @throws ServiceException
	 */
	public void updateImportBeforeBatchAmount(Long importBatchOid,Boolean hasCheckAbnormity) throws ServiceException {
		ImImportBatchDTO imImportBatchDTO = imImportBatchService.get(importBatchOid);
		Integer checkNopassedAmount = imImportBatchDTO.getCheckNopassedAmount()==null?0:imImportBatchDTO.getCheckNopassedAmount();
		Integer checkPassedAmount = imImportBatchDTO.getCheckPassedAmount()==null?0:imImportBatchDTO.getCheckPassedAmount();
		if(hasCheckAbnormity) {
			imImportBatchDTO.setCheckNopassedAmount(++checkNopassedAmount);
		}else {
			imImportBatchDTO.setCheckPassedAmount(++checkPassedAmount);
		}
		imImportBatchDTO.setUpdatedByCode(UserContext.getLoginUserID());
		imImportBatchDTO.setUpdatedByName(UserContext.getLoginUserName());
		imImportBatchDTO.setUpdatedDate(DateUtil.now());
		imImportBatchService.update(imImportBatchDTO);
	}
	
	/**
	 * 导入后校核更新批次检查数量
	 * @param importBatchOid 当期导入批次OID
	 * @param hasCheckAbnormity 是否检查有异常
	 * @throws ServiceException
	 */
	public void updateImportAfterBatchAmount(Long importBatchOid,Boolean hasCheckAbnormity) throws ServiceException {
		ImImportBatchDTO imImportBatchDTO = imImportBatchService.get(importBatchOid);
		Integer checkNopassedAmount = imImportBatchDTO.getCheckNopassedAmount()==null?0:imImportBatchDTO.getCheckNopassedAmount();
		Integer checkPassedAmount = imImportBatchDTO.getCheckPassedAmount()==null?0:imImportBatchDTO.getCheckPassedAmount();
		if(hasCheckAbnormity) {
			imImportBatchDTO.setCheckNopassedAmount(++checkNopassedAmount);
			imImportBatchDTO.setCheckPassedAmount(--checkPassedAmount);
		}else {
			imImportBatchDTO.setCheckPassedAmount(++checkPassedAmount);
			imImportBatchDTO.setCheckNopassedAmount(--checkNopassedAmount);
		}
		imImportBatchDTO.setUpdatedByCode(UserContext.getLoginUserID());
		imImportBatchDTO.setUpdatedByName(UserContext.getLoginUserName());
		imImportBatchDTO.setUpdatedDate(DateUtil.now());
		imImportBatchService.update(imImportBatchDTO);
	}
	
	/**
	 * 批次导入流程状态推动
	 * @param importBatchOid 当期导入批次OID
	 * @param importFlowStatus 导入流程当前状态
	 * @throws ServiceException
	 */
	public void updateBatchStatus(Long importBatchOid, String importFlowStatus) throws ServiceException {
		if(importFlowStatus==null) {
			throw new ServiceException(null,"导入流程当前状态不能为空");
		}
		ImImportBatchDTO imImportBatchDTO = imImportBatchService.get(importBatchOid);
		Boolean hasNoPassedLogs = imDataUnusualLogService.checkNopassedLogs(importBatchOid);
		if((DicConstants.YHRS0142_3.equals(importFlowStatus))||(DicConstants.YHRS0142_4.equals(importFlowStatus))
				||(DicConstants.YHRS0142_7.equals(importFlowStatus))||(DicConstants.YHRS0142_8.equals(importFlowStatus))
				||(DicConstants.YHRS0142_9.equals(importFlowStatus))||(DicConstants.YHRS0142_10.equals(importFlowStatus))
				||(DicConstants.YHRS0142_11.equals(importFlowStatus))) {
			if(!hasNoPassedLogs) {
				imImportBatchDTO.setCheckNopassedReason(null);
			}else {
				String checkNopassedReason = getCheckMessage(importBatchOid);
				imImportBatchDTO.setCheckNopassedReason(checkNopassedReason);
			}
		}
		imImportBatchDTO.setImportFlowStatus(importFlowStatus);
		imImportBatchDTO.setUpdatedByCode(UserContext.getLoginUserID());
		imImportBatchDTO.setUpdatedByName(UserContext.getLoginUserName());
		imImportBatchDTO.setUpdatedDate(DateUtil.now());
		imImportBatchService.update(imImportBatchDTO);
	}
	
	/**
	 * 生成当期导入批次检查未通过的说明
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	private String getCheckMessage(Long importBatchOid) throws ServiceException {
		List<ImDataUnusualLogDTO> logList = imDataUnusualLogService.findImDataUnusualLogDTOByBatchOidAndStatus(importBatchOid, DicConstants.YHRS0003_0);
		if(CollectionUtils.isNotEmpty(logList)) {
			StringBuffer mess = new StringBuffer();
			for(ImDataUnusualLogDTO log:logList) {
				if(DicConstants.YHRS0140_1.equals(log.getCheckType())) {
					mess.append("必填项检查异常；");
				}
				if(DicConstants.YHRS0140_2.equals(log.getCheckType())) {
					mess.append("数据项长度检查异常；");
				}
				if(DicConstants.YHRS0140_3.equals(log.getCheckType())) {
					mess.append("数据格式检查异常；");
				}
				if(DicConstants.YHRS0140_4.equals(log.getCheckType())) {
					mess.append("字典项检查异常；");
				}
				if(DicConstants.YHRS0140_5.equals(log.getCheckType())) {
					mess.append("关联性检查异常；");
				}
				if(DicConstants.YHRS0140_6.equals(log.getCheckType())) {
					mess.append("完整性检查异常；");
				}
				if(DicConstants.YHRS0140_7.equals(log.getCheckType())) {
					mess.append("科室检查异常；");
				}
			}
			return mess.toString();
		}
		return null;
	}
	
	/**
	 * 数据校核更新导入批次检查数
	 * @param importBatchOid
	 * @throws ServiceException
	 */
	public void updateBatchAmountForCheck(Long importBatchOid) throws ServiceException {
		//查询校核人员检查未通过的人数
		Integer noCheckedCounts = countNoCheckedPersons(importBatchOid);
		//更新导入批次检查数
		executeBatchUpdate(importBatchOid, noCheckedCounts);
	}
	
	/**
	 * 查询校核人员检查未通过的人数
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	private Integer countNoCheckedPersons(Long importBatchOid) throws ServiceException {
		String countHql = "select count(cpi) from ImCheckPersonInfo cpi where cpi.checkStatus = '0' and cpi.importBatchOid="+importBatchOid;
		List<Integer> list = DaoUtil.find(countHql);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return 0;
	}
	
	/**
	 * 更新导入批次检查数
	 * @param importBatchOid
	 * @param noCheckedCounts
	 * @throws ServiceException
	 */
	private void executeBatchUpdate(Long importBatchOid, Integer noCheckedCounts) throws ServiceException {
		String updateHql = "update yhc_im_import_batch iib set iib.CHECK_PASSED_AMOUNT=iib.IMPORT_AMOUNT-"+noCheckedCounts+",iib.CHECK_NOPASSED_AMOUNT="+noCheckedCounts+" where iib.IMPORT_BATCH_OID="+importBatchOid;
		SqlDaoUtil.executeSqlUpdate(updateHql);
	}
}
