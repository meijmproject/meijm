/**
 * 
 */
package com.yh.hr.res.pb.service.impl;

import com.yh.hr.res.pb.bo.PbPersonAttach;
import com.yh.hr.res.pb.dto.PbPersonAttachDTO;
import com.yh.hr.res.pb.service.PbPersonAttachService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public class PbPersonAttachServiceImpl implements PbPersonAttachService {

	public PbPersonAttachDTO get(Long personOid) throws ServiceException {
		@SuppressWarnings("unused")
		PbPersonAttach bo = DaoUtil.get(PbPersonAttach.class, personOid);
		return BeanHelper.copyProperties(DaoUtil.get(PbPersonAttach.class, personOid), PbPersonAttachDTO.class);
	}

}
