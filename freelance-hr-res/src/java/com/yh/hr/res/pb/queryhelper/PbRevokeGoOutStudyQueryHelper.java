package com.yh.hr.res.pb.queryhelper;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbRevokeGoOutStudyDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 外出进修销假基础信息查询类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PbRevokeGoOutStudyQueryHelper {

	/**
	 * 根据pbGoOutStudyOid查询外出进修销假基础信息
	 * @param pbGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbRevokeGoOutStudyDTO> getPbRevokeGoOutStudyDTOListByPbGoOutStudyOid(Long pbGoOutStudyOid) throws ServiceException {
		
		String hql = "from PbRevokeGoOutStudy rgs where rgs.pbGoOutStudyOid = ? order by rgs.updateDate desc";
		return BeanHelper.copyProperties(DaoUtil.find(hql, pbGoOutStudyOid), PbRevokeGoOutStudyDTO.class);
	}

	public static List<PbRevokeGoOutStudyDTO> getPbRevokeGoOutStudyDTOList(
			Long pbGoOutStudyOid, Date startDate, Date endDate) throws ServiceException {
		String hql = "from PbRevokeGoOutStudy rgs where rgs.pbGoOutStudyOid = ? and revokeStartDate<=? and revokeEndDate>=? order by rgs.updateDate desc";
		return BeanHelper.copyProperties(DaoUtil.find(hql, pbGoOutStudyOid,endDate,startDate), PbRevokeGoOutStudyDTO.class);
	}
}
