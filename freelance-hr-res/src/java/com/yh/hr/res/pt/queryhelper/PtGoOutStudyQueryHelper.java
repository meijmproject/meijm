package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.dto.PtGoOutStudyDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 外出进修业务信息查询类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PtGoOutStudyQueryHelper {

	/**
	 * 根据bizPersonOid查询外出进修业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtGoOutStudyDTO getPtGoOutStudyDTOByPersonOid(Long bizPersonOid) throws ServiceException {
		
		String hql = "from PtGoOutStudy gs where gs.bizPersonOid = ? order by gs.updateDate desc";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql,bizPersonOid), PtGoOutStudyDTO.class);
	}
}
