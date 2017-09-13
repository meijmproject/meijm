package com.yh.hr.res.pb.queryhelper;

import java.util.List;

import com.yh.hr.res.pb.bo.PbPersonIn;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 人员进入信息查询服务类
 * @author liuhw
 * 2017-5-11
 */
public class PbPersonInQueryHelper 
{
	/**
	 * 根据personOid查询人员进入信息
	 * @param personOid
	 * @return
	 */
	public static List<PbPersonIn> findByPersonOid(Long personOid)throws DataAccessFailureException
	{
		return DaoUtil.findByNamed("from PbPersonIn where personOid=:personOid", "personOid", personOid);
	}
	
}
