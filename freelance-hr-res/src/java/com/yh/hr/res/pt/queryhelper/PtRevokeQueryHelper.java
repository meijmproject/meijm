package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.dto.PtRevokeVacationDto;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 销假信息查询工具类
 * @author chenjl
 * @createDate 2015-4-26
 */
public class PtRevokeQueryHelper {
	
	
	/**
	 * 根据vacationOid查询销假业务信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtRevokeVacationDto getPtRevokeByVacationOid(Long vacationOid) throws ServiceException
	{
		String sql = "from PtRevokeVacation prv where prv.vacationOid = " + vacationOid;
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(sql),PtRevokeVacationDto.class);
	}
	
}