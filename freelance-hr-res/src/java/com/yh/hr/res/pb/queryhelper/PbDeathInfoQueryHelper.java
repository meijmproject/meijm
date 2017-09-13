package com.yh.hr.res.pb.queryhelper;

import java.util.List;

import com.yh.hr.res.pb.bo.PbDeathInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 人员自然减员查询帮助服务类
 * @author liuhw
 * 2017-4-6
 */
public class PbDeathInfoQueryHelper 
{
	/**
	 * 根据人员ID查询人员自然减员信息
	 * @param personOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<PbDeathInfo> findByPersonOid(Long personOid) throws DataAccessFailureException
	{
		return DaoUtil.findByNamed("from PbDeathInfo where personOid=:personOid", "personOid", personOid);
	}

}
