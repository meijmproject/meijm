package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.dto.PtOfficialInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtOfficialInfoQueryHelper;
import com.yh.hr.res.pt.service.PtOfficialInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 公务员登记 信息
 * @author luqy
 * @createDate 2016-11-5下午09:29:14
 */
public class PtOfficialInfoServiceImpl implements PtOfficialInfoService {

	public PtOfficialInfoDTO getPtOfficialInfoDTO(Long bizPersonOid) throws ServiceException {
		return BeanHelper.copyProperties(PtOfficialInfoQueryHelper.getPtOfficialInfoDTOByBizPersonOid(bizPersonOid), PtOfficialInfoDTO.class);
	}
}
