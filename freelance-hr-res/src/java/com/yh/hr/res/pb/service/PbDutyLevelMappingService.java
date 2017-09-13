package com.yh.hr.res.pb.service;

import com.yh.hr.res.pb.dto.PbDutyLevelMappingDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 职务层次等级映射逻辑层
 * @author luqy
 * @createDate 2016-8-15下午04:30:56
 */
public interface PbDutyLevelMappingService {

	public PbDutyLevelMappingDTO getPbDutyLevelMappingDTOByLevel(String dutyLevelCode) throws ServiceException;

}
