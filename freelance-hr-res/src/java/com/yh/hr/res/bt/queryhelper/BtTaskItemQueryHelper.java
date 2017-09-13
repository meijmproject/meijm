package com.yh.hr.res.bt.queryhelper;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.yh.hr.res.bt.bo.BtTaskItem;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

/**
 * 查询待办事项服务类
 * @author Administrator
 *
 */
public class BtTaskItemQueryHelper 
{
	/**
	 * 根据业务id查询待办事项信息
	 * @param bizTaskOid
	 * @return
	 * @throws DataAccessException
	 * @throws DataAccessFailureException 
	 */
	public static List<BtTaskItem> findBizWaitItemInfoByBizTaskOid(Long bizTaskOid) throws DataAccessFailureException
	{
		List<BtTaskItem> list = DaoUtil.findByNamed(" from BtTaskItem b where b.taskOid=:bizTaskOid", "bizTaskOid",bizTaskOid);
		return list;
	}
	
	/**
	 * 根据业务id、环节代码和状态查询当前环节待办事项信息
	 * @param bizTaskOid
	 * @param itemNodeCode
	 * @return
	 */
	public static BtTaskItem findWaitItemByCondition(Long bizTaskOid,String status,String itemNodeCode)  throws DataAccessFailureException
	{
		StringBuffer hql = new StringBuffer(" from BtTaskItem where 1 = 1");
		HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		if(null != bizTaskOid)
		{
			hql.append(" and taskOid=:bizTaskOid");
			hqlParams.put("bizTaskOid", bizTaskOid);
		}
		if(StringUtils.isNotEmpty(status))
		{
			hql.append(" and taskItemStatus=:status");
			hqlParams.put("status", status);
		}
		if(StringUtils.isNotEmpty(itemNodeCode))
		{
			hqlParams.put("itemNodeCode", itemNodeCode);
			hql.append(" and taskItemCode=:itemNodeCode");
		}
		List<BtTaskItem> list = DaoUtil.find(hql.toString(), hqlParams);
		if(CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

	/**
	 * 根据业务id、环节代码和状态查询下一环节待办事项信息
	 * @param bizTaskOid
	 * @param itemNodeCode
	 * @param status
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static BtTaskItem findWaitItemByCond(Long bizTaskOid,String status, String itemNodeCode)throws DataAccessFailureException
	{
		StringBuffer hql = new StringBuffer(" from BtTaskItem where 1 = 1");
		HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		if(null != bizTaskOid)
		{
			hql.append(" and taskOid=:bizTaskOid");
			hqlParams.put("bizTaskOid", bizTaskOid);
		}
		if(StringUtils.isNotEmpty(status))
		{
			hql.append(" and taskItemStatus=:status");
			hqlParams.put("status", status);
		}
		if(StringUtils.isNotEmpty(itemNodeCode))
		{
			hql.append(" and preTaskItemCode=:itemNodeCode");
			hqlParams.put("itemNodeCode", itemNodeCode);
		}
		List<BtTaskItem> list = DaoUtil.find(hql.toString(), hqlParams);
		if(CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}
	
	/**
	 * 根据业务id和节点状态查询业务环节信息(流程id)
	 * @param bizTaskOid
	 * @param nodeStatus
	 * @return
	 * @throws DataAccessException
	 */
	public static Long getBizWaititemOid(Long bizTaskOid, String itemNodeStatus)throws DataAccessFailureException
	{
		StringBuffer hql = new StringBuffer(" SELECT bwi.taskItemOid FROM BtTaskItem bwi WHERE 1 = 1");
		HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		
		if(null != bizTaskOid)
		{
			hql.append(" AND bwi.taskOid = :bizTaskOid");
			hqlParams.put("bizTaskOid", bizTaskOid);
		}
		if(StringUtils.isNotEmpty(itemNodeStatus))
		{
			hql.append(" AND bwi.taskItemStatus= :itemNodeStatus");
			hqlParams.put("itemNodeStatus", itemNodeStatus);
		}
		hql.append(" ORDER BY bwi.taskItemOid desc");
		
		List<Object> list = DaoUtil.find(hql.toString(), hqlParams);
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		else
		{
			return Long.valueOf(list.get(0).toString());
		}
	}
	
	/**
	 *	更新公示基本信息办理状态
	 *	@param	bizTaskOid		业务事项OID
	 * @throws DataAccessFailureException 
	 *	@throws ServiceException
	 */
	public static void deleteWaitItemInfos(Long bizTaskOid) throws DataAccessException, DataAccessFailureException
	{
		StringBuffer doSQL = new StringBuffer();
		
		doSQL.append(" delete BtTaskItem bwi");
		doSQL.append(" where bwi.taskOid = " + bizTaskOid);
		
		DaoUtil.executeUpdate(doSQL.toString());
	}
}
