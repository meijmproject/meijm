package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtAllocationInfo;
import jade.workflow.utils.DaoUtil;

import com.yh.platform.core.exception.ServiceException;

/**
 * 安置信息业务 查询工具类
 * @author zhengdr
 *
 * 时间:2016-11-16下午02:37:16
 */
public class PtAllocationInfoQueryHelper {

	/**
	 * 根据bizPersonOid得到安置信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtAllocationInfo getByBizPersonOid(Long bizPersonOid)throws ServiceException{

		String hql = "from PtAllocationInfo fai where fai.bizPersonOid ="+bizPersonOid;
		@SuppressWarnings("unchecked")
		List<PtAllocationInfo> ptAllocationInfoList = DaoUtil.find(hql);
		if(ptAllocationInfoList!=null&&ptAllocationInfoList.size()!=0){
			return ptAllocationInfoList.get(0);
		}
		
		return null;
    }
	
	/**
	 * 删除通过bizPersonOid
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		
		DaoUtil.executeUpdate("delete from PtAllocationInfo pai where pai.bizPersonOid = " + bizPersonOid);
	
	}
}
