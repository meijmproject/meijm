package com.yh.hr.res.hc.queryhelper;

import java.util.List;

import com.yh.hr.res.hc.bo.HcInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 编制使用情况查询服务类
 * @author liuhw
 * 2016-8-23
 */
public class HcInfoQueryHelper 
{
	/**
	 * 根据条件查询编制使用情况信息
	 * @param unitOid  单位OID
	 * @param refOid 来源OID
	 * @param refType 来源类型
	 * @param hcCode 编制类型
	 * @param budgetFromCode 经费形式
	 * @param status 状态
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static HcInfo findHcInfoByCondition(Long unitOid, Long refOid, String refType, String hcCode, String budgetFromCode, String status)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("from HcInfo j where 1 = 1");
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
		if(StringUtils.isNotEmpty(hcCode))
		{
			sb.append(" and j.hcCode = '").append(hcCode).append("'");
		}
		if(StringUtils.isNotEmpty(budgetFromCode))
		{
			sb.append(" and j.budgetFromCode = '").append(budgetFromCode).append("'");
		}
		if(StringUtils.isNotEmpty(status))
		{
			sb.append(" and j.status = '").append(status).append("'");
		}
		List<HcInfo> list = DaoUtil.find(sb.toString());
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		return list.get(0);
	}
	
	
	/**
	 * 根据条件统计 编制使用情况数量信息
	 * @param unitOid  单位OID
	 * @param refOid 来源OID
	 * @param refType 来源类型
	 * @param hcCode 编制类型
	 * @param budgetFromCode 经费形式
	 * @param status 状态
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static int countHcInfoByCondition(Long unitOid,Long refOid,String refType,String hcCode,String budgetFromCode,String status)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("select sum(j.count) from HcInfo j where 1 = 1");
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
		if(StringUtils.isNotEmpty(hcCode))
		{
			sb.append(" and j.hcCode = '").append(hcCode).append("'");
		}
		if(StringUtils.isNotEmpty(budgetFromCode))
		{
			sb.append(" and j.budgetFromCode = '").append(budgetFromCode).append("'");
		}
		if(StringUtils.isNotEmpty(status))
		{
			sb.append(" and j.status = '").append(status).append("'");
		}
		
		return DaoUtil.countByCondition(sb.toString(), null);
		
	}
	
	
	
	/**
	 * 根据条件查询编制使用情况信息（未释放 or 未解冻 or 未解锁）
	 * @param unitOid  单位OID
	 * @param refOid 来源OID
	 * @param refType 来源类型
	 * @param status 状态
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<HcInfo> findHcInfoByCondition(Long unitOid,Long refOid,String refType,String status)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("from HcInfo j where j.count > 0");
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
		List<HcInfo> list = DaoUtil.find(sb.toString());
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		return list;
	}
}
