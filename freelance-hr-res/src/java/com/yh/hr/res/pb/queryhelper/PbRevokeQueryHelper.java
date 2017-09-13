package com.yh.hr.res.pb.queryhelper;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.bo.PbRevokeVacation;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbRevokeVacationDto;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 销假信息查询工具类
 * @author chenjl
 * @createDate 2015-4-26
 */
public class PbRevokeQueryHelper {
	
	
	/**
	 * 根据vacationOid查询销假业务信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public static PbRevokeVacationDto getPbRevokeByVacationOid(Long vacationOid) throws ServiceException
	{
		String sql = "from PbRevokeVacation prv where prv.vacationOid="+vacationOid;
		
		List<PbRevokeVacation> list = DaoUtil.find(sql);
		if(CollectionUtils.isNotEmpty(list))
		{
			return BeanHelper.copyProperties(list.get(0),PbRevokeVacationDto.class);
		}
		return null;
	}

	public static PbRevokeVacationDto getPbRevokeVacation(Long vacationOid,
			Date startDate, Date endDate) throws ServiceException {
		String hql = "from PbRevokeVacation prv where prv.vacationOid = ? and revokeStartDate<=? and revokeEndDate >= ? ";
		List<PbRevokeVacation> list= DaoUtil.find(hql, vacationOid,endDate,startDate);
		if(CollectionUtils.isNotEmpty(list)){
			return BeanHelper.copyProperties(list.get(0),PbRevokeVacationDto.class);
		}
		return null;
	}
	
}