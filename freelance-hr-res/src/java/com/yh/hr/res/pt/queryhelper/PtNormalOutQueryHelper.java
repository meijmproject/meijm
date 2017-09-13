package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtNormalOut;
import com.yh.hr.res.pt.bo.PtPostInfoDis;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 免职信息查询工具类
 * @author chenjl
 *
 * 时间:2016-11-10
 */
public class PtNormalOutQueryHelper
{
	/**根据bizPersonOid查询
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtNormalOut listPtNormalOutByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		final StringBuffer hBuffer = new StringBuffer("from PtNormalOut pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);// 必须是按人来查询
		List<PtNormalOut> list = BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtNormalOut.class);
		return list==null?null:list.get(0);
	}
	
	/**
	 * 删除
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
    public static void deletePtNormalOutByBizPersonOid(Long bizPersonOid)throws ServiceException {
		
		DaoUtil.executeUpdate("delete from PtNormalOut pod where pod.bizPersonOid = " + bizPersonOid);
	}
    
    /**
     * 得到免职信息列表BO
     * @param bizPersonOid
     * @throws ServiceException
     */
    public static List<PtPostInfoDis> listPtPostInfoDisBoByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtPostInfoDis fi where fi.bizPersonOid = :bizPersonOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtPostInfoDis> boList = DaoUtil.find(hql, params);
		
		return boList;
	}
    /**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtNormalOutDTOByBizPersonId(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtNormalOut pt where  pt.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		
		return DaoUtil.countByCondition(hql, params);
	}
}
