package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtRevokeGoOutDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 普通外出销假业务信息查询类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PtRevokeGoOutQueryHelper {

	/**
	 * 根据ptGoOutOid查询普通外出销假业务信息
	 * @param ptGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtRevokeGoOutDTO> getPtRevokeGoOutDTOListByPtGoOutOid(Long ptGoOutOid) throws ServiceException {
		
		String hql = "from PtRevokeGoOut rg where rg.ptGoOutOid = ? order by rg.updateDate desc";
		return BeanHelper.copyProperties(DaoUtil.find(hql, ptGoOutOid), PtRevokeGoOutDTO.class);
	}
}
