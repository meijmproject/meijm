package com.yh.hr.component.im.check.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.im.check.service.CollItemCheckService;
import com.yh.hr.component.im.dto.CheckColumnDTO;
import com.yh.hr.component.im.dto.CheckResultDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 数据项长度检查实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class DataLengthCheckServiceImpl implements CollItemCheckService {

	/**
	 * 数据项长度检查
	 * @param column
	 * @param collList 已映射的采集项模板
	 * @throws ServiceException
	 */
	public CheckResultDTO check(CheckColumnDTO column,List<ImCollectTemplateDTO> collList) throws ServiceException {
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
		if(importCollValue!=null&&!"".equals(importCollValue)) {
			if(imCollectTemplateDTO!=null) {
				Integer importCollMaxlength = imCollectTemplateDTO.getImportCollMaxlength();
				Integer valueLength = importCollValue.length();
				if(valueLength>importCollMaxlength) {
					CheckResultDTO result = new CheckResultDTO();
					result.setDatabaseColumnCode(imCollectTemplateDTO.getDatabaseColumnCode());
					result.setImportCollValue(importCollValue);
					result.setCheckType(column.getCheckType());
					result.setCheckStatus(DicConstants.YHRS0003_0);
					result.setCheckMessage("“"+imCollectTemplateDTO.getTemplateCollName()+"”长度超长");
					return result;
				}
			}
		}
		return null;
	}
}
