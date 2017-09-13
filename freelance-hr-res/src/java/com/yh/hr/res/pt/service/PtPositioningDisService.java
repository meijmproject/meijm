package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPositioningDisDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 免职信息服务层
 * @author luqy
 * @createDate 2016-11-4下午06:29:39
 */
public interface PtPositioningDisService {

	/**
	 * 根据主键bizPersonOid获取业务基础信息DTO
	 * 
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPositioningDisDTO> listPtPositioningDisByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	
}