/**
 * 
 */
package com.yh.hr.leader.validate.service;

import java.util.List;

import com.yh.hr.leader.validate.dto.LdCashCheckDTO;
import com.yh.hr.leader.validate.dto.LdCashCheckResultDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 领导职数检查接口
 * @author	zhangqp
 * @version	1.0,	16/10/31
 */

public interface LdCheckService {

	/**
	 * 
	 * @param dto
	 * @throws ServiceException
	 */
	public List<LdCashCheckResultDTO> check(List<LdCashCheckDTO> ldCashCheckDTOs) throws ServiceException;
	
}
