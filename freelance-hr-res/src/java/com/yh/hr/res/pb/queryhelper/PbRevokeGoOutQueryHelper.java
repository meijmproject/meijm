package com.yh.hr.res.pb.queryhelper;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbRevokeGoOutDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 普通外出销假基础信息查询类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PbRevokeGoOutQueryHelper {

	/**
	 * 根据pbGoOutOid查询普通外出销假基础信息
	 * @param pbGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbRevokeGoOutDTO> getPbRevokeGoOutDTOListByPbGoOutOid(Long pbGoOutOid) throws ServiceException {
		
		String hql = "from PbRevokeGoOut rg where rg.pbGoOutOid = ? order by rg.updateDate desc";
		return BeanHelper.copyProperties(DaoUtil.find(hql, pbGoOutOid), PbRevokeGoOutDTO.class);
	}

	public static List<PbRevokeGoOutDTO> getPbRevokeGoOutDTOList(Long pbGoOutOid,
			Date startDate, Date endDate) throws  ServiceException {
		String hql = "from PbRevokeGoOut rg where rg.pbGoOutOid = ? and revokeStartDate<=? and revokeEndDate >= ? order by rg.updateDate desc";
		return BeanHelper.copyProperties(DaoUtil.find(hql, pbGoOutOid,endDate,startDate), PbRevokeGoOutDTO.class);
	}
}
