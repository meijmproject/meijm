package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtRevokeGoOutStudyDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 外出进修销假业务信息查询类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PtRevokeGoOutStudyQueryHelper {

	/**
	 * 根据ptGoOutStudyOid查询外出进修销假业务信息
	 * @param ptGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtRevokeGoOutStudyDTO> getPtRevokeGoOutStudyDTOListByPtGoOutStudyOid(Long ptGoOutStudyOid) throws ServiceException {
		
		String hql = "from PtRevokeGoOutStudy rgs where rgs.ptGoOutStudyOid = ? order by rgs.updateDate desc";
		return BeanHelper.copyProperties(DaoUtil.find(hql, ptGoOutStudyOid), PtRevokeGoOutStudyDTO.class);
	}

	/**
	 * 获取进修销假信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtRevokeGoOutStudyDTO getPtGoOutCancelDTOByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String sql=" from PtRevokeGoOutStudy where ptGoOutStudyOid in (select gos.ptGoOutStudyOid from PtGoOutStudy gos where gos.bizPersonOid ="+bizPersonOid+")";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(sql),PtRevokeGoOutStudyDTO.class);
	}
}
