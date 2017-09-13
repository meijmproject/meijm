/**
 * 
 */
package com.yh.hr.res.pb.service;

import com.yh.hr.res.pb.dto.PbPersonAttachDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员附属信息
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public interface PbPersonAttachService {

	public PbPersonAttachDTO get(Long personOid) throws ServiceException;
	
}
