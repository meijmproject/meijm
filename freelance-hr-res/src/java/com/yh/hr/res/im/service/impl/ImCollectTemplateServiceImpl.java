package com.yh.hr.res.im.service.impl;

import java.util.List;

import com.yh.hr.res.im.bo.ImCollectTemplate;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.hr.res.im.queryhelper.ImCollectTemplateQueryHelper;
import com.yh.hr.res.im.service.ImCollectTemplateService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 采集项映射操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImCollectTemplateServiceImpl implements ImCollectTemplateService {
	
	/**
	 * 获取所有的采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCollectTemplateDTO> findAllCollTemplates() throws ServiceException {
		return ImCollectTemplateQueryHelper.findAllCollTemplates();
	}
	
	/**
	 * 获取所有有效的的采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCollectTemplateDTO> findEffectiveCollTemplates() throws ServiceException {
		return ImCollectTemplateQueryHelper.findEffectiveCollTemplates();
	}
	
	/**
	 * 通过主键获取采集项映射模板信息
	 * @param templateOid
	 * @return
	 * @throws ServiceException
	 */
	public ImCollectTemplateDTO get(Long templateOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImCollectTemplate.class, templateOid), ImCollectTemplateDTO.class);
	}
	
	/**
	 * 获取所有已被映射的采集项映射模板
	 * @return
	 * @throws ServiceExceptin
	 */
	public List<ImCollectTemplateDTO> findCollTemplateForMapped() throws ServiceException {
		return ImCollectTemplateQueryHelper.findCollTemplateForMapped();
	}
	
	/**
	 * 通过数据库字段代码获取模板信息
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public ImCollectTemplateDTO findCollTemplateByColumnCode(String databaseColumnCode) throws ServiceException {
		return ImCollectTemplateQueryHelper.findCollTemplateByColumnCode(databaseColumnCode);
	}
	
	/**
	 * 通过数据库字段代码获取模板采集项名称
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public String findTemplateCollNameByColumnCode(String databaseColumnCode) throws ServiceException {
		return ImCollectTemplateQueryHelper.findTemplateCollNameByColumnCode(databaseColumnCode);
	}

	/**
	 * 更新采集项映射模板信息
	 * @param imCollectTemplateDTO
	 * @throws ServiceException
	 */
	public void update(ImCollectTemplateDTO imCollectTemplateDTO) throws ServiceException {
		if(imCollectTemplateDTO!=null) {
			ImCollectTemplate imCollectTemplate = DaoUtil.get(ImCollectTemplate.class, imCollectTemplateDTO.getTemplateOid());
			if(imCollectTemplate!=null) {
				BeanHelper.copyProperties(imCollectTemplateDTO, imCollectTemplate, BeanHelper.getNullPropertyNames(imCollectTemplateDTO));
				imCollectTemplate.setUpdatedByCode(UserContext.getLoginUserID());
				imCollectTemplate.setUpdatedByName(UserContext.getLoginUserName());
				imCollectTemplate.setUpdatedDate(DateUtil.now());
				imCollectTemplate.update();
			}
		}
	}
	
	/**
	 * 通过导入采集项名称获取模板信息
	 * @param importCollName
	 * @return
	 * @throws ServiceException
	 */
	public ImCollectTemplateDTO findCollTemplateByImportCollName(String importCollName) throws ServiceException {
		return ImCollectTemplateQueryHelper.findCollTemplateByImportCollName(importCollName);
	}
	
	/**
	 * 通过字段名获取字段名描述字段
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public String getColumnCodeNameByColumnCode(String databaseColumnCode) throws ServiceException {
		return ImCollectTemplateQueryHelper.getColumnCodeNameByColumnCode(databaseColumnCode);
	}
}
