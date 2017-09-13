package com.yh.hr.component.im.check.service.impl;

import com.yh.hr.component.im.dto.CheckColumnDTO;
import com.yh.hr.component.im.dto.CheckResultDTO;
import jade.workflow.utils.SpringBeanUtil;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.im.check.service.CollItemCheckService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 科室是否存在检查实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class OrgExistCheckServiceImpl implements CollItemCheckService {
	
	private UtOrgService utOrgService = (UtOrgService) SpringBeanUtil.getBean("utOrgService");

	/**
	 * 科室检查
	 * @param column
	 * @param collList 已映射的采集项模板
	 * @throws ServiceException
	 */
	public CheckResultDTO check(CheckColumnDTO column, List<ImCollectTemplateDTO> collList) throws ServiceException {
		String importCollName = column.getImportCollName();
		if(importCollName==null) {
			throw new ServiceException(null, "导入采集项名称不能为空！");
		}
		ImCollectTemplateDTO imCollectTemplateDTO=null;
		if(CollectionUtils.isNotEmpty(collList)) {
			for(ImCollectTemplateDTO collDTO:collList) {
				if(DicConstants.YHRS0003_1.equals(collDTO.getEffectiveFlag())&&importCollName.equals(collDTO.getImportCollName())) {
					imCollectTemplateDTO = collDTO;
				}
			}
		}
		String importCollValue = column.getImportCollValue();
		if(imCollectTemplateDTO!=null) {
			String dataBaseColumnCode = imCollectTemplateDTO.getDatabaseColumnCode();
			if("HIRE_DEPT_OID".equals(dataBaseColumnCode)&&(importCollValue!=null&&!"".equals(importCollValue))) {
				UtOrgDTO orgDTO = utOrgService.findUtOrgDTOByOrgName(importCollValue);
				if(orgDTO==null) {
					CheckResultDTO result = new CheckResultDTO();
					result.setDatabaseColumnCode(imCollectTemplateDTO.getDatabaseColumnCode());
					result.setCheckType(column.getCheckType());
					result.setCheckStatus(DicConstants.YHRS0003_0);
					result.setCheckMessage("科室“"+importCollValue+"”不存在");
					return result;
				}
			}
		}
		return null;
	}

}
