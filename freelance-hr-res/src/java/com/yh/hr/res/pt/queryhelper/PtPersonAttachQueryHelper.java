package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.bo.PtPersonAttach;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 人员附属信息查询工具类
 * @author wujj
 * @createDate 2016-11-05
 */
public class PtPersonAttachQueryHelper {
	
	/**
	 * 通过ID获取
	 */
	public static PtPersonAttach getPtPersonAttachDTOById(Long bizPersonOid) throws ServiceException {
		return DaoUtil.get(PtPersonAttach.class, bizPersonOid);
	}

	/**
	 * 通过personOid删除任职附属表信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid)throws ServiceException {
		
		DaoUtil.executeUpdate("delete from PtPersonAttach pa where pa.bizPersonOid = " + bizPersonOid);
	}
}
