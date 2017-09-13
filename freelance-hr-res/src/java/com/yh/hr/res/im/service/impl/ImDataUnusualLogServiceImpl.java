package com.yh.hr.res.im.service.impl;

import java.util.List;

import com.yh.hr.res.im.bo.ImDataUnusualLog;
import com.yh.hr.res.im.dto.ImDataUnusualLogDTO;
import com.yh.hr.res.im.queryhelper.ImDataUnusualLogQueryHelper;
import com.yh.hr.res.im.service.ImDataUnusualLogService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 导入数据异常日志操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImDataUnusualLogServiceImpl implements ImDataUnusualLogService {

	/**
	 * 通过主键获取导入数据异常日志信息
	 * @param dataUnusualOid
	 * @return
	 * @throws ServiceException
	 */
	public ImDataUnusualLogDTO get(Long dataUnusualOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImDataUnusualLog.class, dataUnusualOid), ImDataUnusualLogDTO.class);
	}
	
	/**
	 * 创建导入数据异常日志信息
	 * @param imDataUnusualLogDTO
	 * @throws ServiceException
	 */
	public void create(ImDataUnusualLogDTO imDataUnusualLogDTO) throws ServiceException {
		if(imDataUnusualLogDTO!=null) {
			ImDataUnusualLog imDataUnusualLog = new ImDataUnusualLog();
			BeanHelper.copyProperties(imDataUnusualLogDTO, imDataUnusualLog);
			imDataUnusualLog.save();
		}
	}
	
	/**
	 * 修改导入数据异常日志信息
	 * @param imDataUnusualLogDTO
	 * @throws ServiceException
	 */
	public void update(ImDataUnusualLogDTO imDataUnusualLogDTO) throws ServiceException {
		if(imDataUnusualLogDTO!=null) {
			ImDataUnusualLog imDataUnusualLog = DaoUtil.get(ImDataUnusualLog.class, imDataUnusualLogDTO.getDataUnusualOid());
			if(imDataUnusualLog!=null) {
				BeanHelper.copyProperties(imDataUnusualLogDTO, imDataUnusualLog, BeanHelper.getNullPropertyNames(imDataUnusualLogDTO));
				imDataUnusualLog.update();
			}
		}
	}
	
	/**
	 * 删除导入数据异常日志信息
	 * @param dataUnusualOid
	 * @throws ServiceException
	 */
	public void delete(Long dataUnusualOid) throws ServiceException {
		if(dataUnusualOid!=null) {
			ImDataUnusualLog imDataUnusualLog = DaoUtil.get(ImDataUnusualLog.class, dataUnusualOid);
			if(imDataUnusualLog!=null) {
				imDataUnusualLog.delete();
			}
		}
	}
	
	/**
	 * 获取该导入批次的所有数据异常日志信息
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDataUnusualLogDTO> findImDataUnusualLogDTOListByBatchOid(Long importBatchOid) throws ServiceException {
		return ImDataUnusualLogQueryHelper.findImDataUnusualLogDTOListByBatchOid(importBatchOid);
	}
	
	/**
	 * 获取该导入批次的某个检查类型的数据异常日志信息
	 * @param importBatchOid
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public ImDataUnusualLogDTO findImDataUnusualLogDTOByBatchOidAndType(Long importBatchOid, String checkType) throws ServiceException {
		return ImDataUnusualLogQueryHelper.findImDataUnusualLogDTOByBatchOidAndType(importBatchOid, checkType);
	}
	
	/**
	 * 获取该导入批次的指定检查状态的数据异常日志信息
	 * @param importBatchOid
	 * @param checkStatus
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDataUnusualLogDTO> findImDataUnusualLogDTOByBatchOidAndStatus(Long importBatchOid, String checkStatus) throws ServiceException {
		return ImDataUnusualLogQueryHelper.findImDataUnusualLogDTOByBatchOidAndStatus(importBatchOid, checkStatus);
	}
	
	/**
	 * 检查当前批次是否还有检查未通过的异常日志
	 * @return
	 * @throws ServiceException
	 */
	public Boolean checkNopassedLogs(Long importBatchOid) throws ServiceException {
		return ImDataUnusualLogQueryHelper.checkNopassedLogs(importBatchOid);
	}
}
