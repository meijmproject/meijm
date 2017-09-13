package com.yh.hr.res.ld.service;

import java.util.List;

import com.yh.hr.res.ld.dto.LdCashDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 领导职数资源使用信息查询接口
 * @author	zhangqp
 * @version	1.0,	16/10/31
 */
public interface LdCashService {
	
	/**
	 * 领导职数资源使用信息查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LdCashDTO> findLdCash(LdCashDTO dto) throws ServiceException;
}
