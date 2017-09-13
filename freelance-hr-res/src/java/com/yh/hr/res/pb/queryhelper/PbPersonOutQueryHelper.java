package com.yh.hr.res.pb.queryhelper;

import java.util.List;

import com.yh.hr.res.pb.bo.PbPersonOut;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 人员离开信息查询服务类
 * @author liuhw
 * 2017-5-11
 */
public class PbPersonOutQueryHelper 
{
	/**
	 * 根据人员ID查询人员离开信息
	 * @param personOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<PbPersonOut> findByPersonOid(Long personOid) throws DataAccessFailureException
	{
		return DaoUtil.findByNamed("FROM PbPersonOut WHERE personOid=:personOid ", "personOid", personOid);
	}

}
