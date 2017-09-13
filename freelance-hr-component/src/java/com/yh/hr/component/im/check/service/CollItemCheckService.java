package com.yh.hr.component.im.check.service;

import java.util.List;

import com.yh.hr.component.im.dto.CheckColumnDTO;
import com.yh.hr.component.im.dto.CheckResultDTO;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 数据检查接口
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public interface CollItemCheckService {

	/**
	 * 检查
	 * @param dto
	 * @param collList 已映射的采集项模板
	 * @throws ServiceException
	 */
	public CheckResultDTO check(CheckColumnDTO dto, List<ImCollectTemplateDTO> collList) throws ServiceException;
}
