package com.yh.hr.res.im.service.impl;

import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.bo.ImImportBatch;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.hr.res.im.queryhelper.ImImportBatchQueryHelper;
import com.yh.hr.res.im.service.ImImportBatchService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

/**
 * 导入批次操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImImportBatchServiceImpl implements ImImportBatchService {

	/**
	 * 通过主键获取导入批次信息
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public ImImportBatchDTO get(Long importBatchOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImImportBatch.class, importBatchOid), ImImportBatchDTO.class);
	}
	
	/**
	 * 创建导入批次信息
	 * @param imImportBatchDTO
	 * @throws ServiceException
	 */
	public Long create(ImImportBatchDTO imImportBatchDTO) throws ServiceException {
		if(imImportBatchDTO!=null) {
			ImImportBatchDTO batchDTO = ImImportBatchQueryHelper.getCurrentImportBatchDTO();
			if(batchDTO!=null) {
				ImImportBatch batch = DaoUtil.get(ImImportBatch.class, batchDTO.getImportBatchOid());
				batch.setEffectiveFlag(DicConstants.YHRS0003_0);
				batch.setUpdatedByCode(UserContext.getLoginUserID());
				batch.setUpdatedByName(UserContext.getLoginUserName());
				batch.setUpdatedDate(DateUtil.now());
				batch.update();
			}
			
			ImImportBatch imImportBatch = new ImImportBatch();
			BeanHelper.copyProperties(imImportBatchDTO, imImportBatch);
			imImportBatch.setEffectiveFlag(DicConstants.YHRS0003_1);
			imImportBatch.setCreatedByCode(UserContext.getLoginUserID());
			imImportBatch.setCreatedByName(UserContext.getLoginUserName());
			imImportBatch.setCreatedDate(DateUtil.now());
			imImportBatch.setUpdatedByCode(UserContext.getLoginUserID());
			imImportBatch.setUpdatedByName(UserContext.getLoginUserName());
			imImportBatch.setUpdatedDate(DateUtil.now());
			imImportBatch.save();
			return imImportBatch.getImportBatchOid();
		}
		return null;
	}
	
	/**
	 * 修改导入批次信息
	 * @param imImportBatchDTO
	 * @throws ServiceException
	 */
	public void update(ImImportBatchDTO imImportBatchDTO) throws ServiceException {
		if(imImportBatchDTO!=null) {
			ImImportBatch imImportBatch = DaoUtil.get(ImImportBatch.class, imImportBatchDTO.getImportBatchOid());
			if(imImportBatch!=null) {
				BeanHelper.copyProperties(imImportBatchDTO, imImportBatch, BeanHelper.getNullPropertyNames(imImportBatchDTO));
				imImportBatch.setUpdatedByCode(UserContext.getLoginUserID());
				imImportBatch.setUpdatedByName(UserContext.getLoginUserName());
				imImportBatch.setUpdatedDate(DateUtil.now());
				imImportBatch.update();
			}
		}
	}
	
	/**
	 * 删除导入批次信息
	 * @param importBatchOid
	 * @throws ServiceException
	 */
	public void delete(Long importBatchOid) throws ServiceException {
		if(importBatchOid!=null) {
			ImImportBatch imImportBatch = DaoUtil.get(ImImportBatch.class, importBatchOid);
			if(imImportBatch!=null) {
				imImportBatch.delete();
			}
		}
	}
	
	/**
	 * 查询所有的导入批次
	 * @return
	 * @throws ServiceException
	 */
	public List<ImImportBatchDTO> findAllImImportBatchDTO() throws ServiceException {
		return ImImportBatchQueryHelper.findAllImImportBatchDTO();
	}
	
	/**
	 * 获取当前最新的导入批次
	 * @return
	 * @throws ServiceException
	 */
	public ImImportBatchDTO getCurrentImportBatchDTO() throws ServiceException {
		return ImImportBatchQueryHelper.getCurrentImportBatchDTO();
	}
}
