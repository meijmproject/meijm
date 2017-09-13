package com.yh.hr.res.ld.queryhelper;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.ld.bo.LdInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 领导职数使用情况查询服务类
 * @author liuhw
 * 2016-8-23
 */
public class LdInfoQueryHelper 
{
	/**
	 * 根据条件查询领导职数使用情况信息
	 * @param unitOid  单位OID
	 * @param refOid 来源OID
	 * @param refType 来源类型
	 * @param dutyAttribute 职务属性
	 * @param dutyLevel 职务层次
	 * @param status 状态
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static LdInfo findLdInfoByCondition(Long unitOid,Long refOid,String refType,String dutyAttribute,String dutyLevel,String status)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("from LdInfo j where 1 = 1");
		if(null != unitOid)
		{
			sb.append(" and j.unitOid=").append(unitOid);
		}
		if(null != refOid)
		{
			sb.append(" and j.refOid =").append(refOid);
		}
		if(StringUtils.isNotEmpty(refType))
		{
			sb.append(" and j.refType = '").append(refType).append("'");
		}
		if(StringUtils.isNotEmpty(dutyAttribute))
		{
			sb.append(" and j.dutyAttribute = '").append(dutyAttribute).append("'");
		}
		if(StringUtils.isNotEmpty(dutyLevel))
		{
			sb.append(" and j.dutyLevel = '").append(dutyLevel).append("'");
		}
		if(StringUtils.isNotEmpty(status))
		{
			sb.append(" and j.status = '").append(status).append("'");
		}
		List<LdInfo> list = DaoUtil.find(sb.toString());
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 根据条件查询领导职数使用情况信息（未释放 or 未解冻 or 未解锁）
	 * @param unitOid  单位OID
	 * @param refOid 来源OID
	 * @param refType 来源类型
	 * @param status 状态
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<LdInfo> findLdInfoByCondition(Long unitOid,Long refOid,String refType,String status)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("from LdInfo j where j.count > 0");
		if(null != unitOid)
		{
			sb.append(" and j.unitOid=").append(unitOid);
		}
		if(null != refOid)
		{
			sb.append(" and j.refOid =").append(refOid);
		}
		if(StringUtils.isNotEmpty(refType))
		{
			sb.append(" and j.refType = '").append(refType).append("'");
		}
		if(StringUtils.isNotEmpty(status))
		{
			sb.append(" and j.status = '").append(status).append("'");
		}
		List<LdInfo> list = DaoUtil.find(sb.toString());
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		return list;
	}
	
	/**
	 * 根据条件查询领导职数使用情况信息
	 * @param unitOid  单位OID
	 * @param refOid 来源OID
	 * @param refType 来源类型
	 * @param dutyAttribute 职务属性
	 * @param dutyLevel 职务层次
	 * @param status 状态
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static int getLdInfoCountSum(Long unitOid,String dutyAttribute,String dutyLevel,String status)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("select sum(j.count) from LdInfo j where 1 = 1");
		if(null != unitOid)
		{
			sb.append(" and j.unitOid=").append(unitOid);
		}
		if(StringUtils.isNotEmpty(dutyAttribute))
		{
			sb.append(" and j.dutyAttribute = '").append(dutyAttribute).append("'");
		}
		if(StringUtils.isNotEmpty(dutyLevel))
		{
			sb.append(" and j.dutyLevel = '").append(dutyLevel).append("'");
		}
		if(StringUtils.isNotEmpty(status))
		{
			sb.append(" and j.status = '").append(status).append("'");
		}
		HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		int num = DaoUtil.countByCondition(sb.toString(),hqlParams);
		return num;
	}
}
