package com.yh.hr.res.pt.queryhelper;

import jade.workflow.utils.DaoUtil;

import java.util.List;
import com.yh.hr.res.pt.bo.PtProbationInfo;
import com.yh.platform.core.exception.ServiceException;

/**
 * 试用转正业务 查询工具类
 * @author zhengdr
 *
 * 时间:2016-11-17下午05:16:10
 */
public class PtProbationInfoQueryHelper {

	/**
	 * 根据bizPersonOid得到试用信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtProbationInfo getByBizPersonOid(Long bizPersonOid)throws ServiceException{

		String hql = "from PtProbationInfo ppi where ppi.bizPersonOid ="+bizPersonOid;
		@SuppressWarnings("unchecked")
		List<PtProbationInfo> ptProbationInfoList = DaoUtil.find(hql);
		if(ptProbationInfoList!=null&&ptProbationInfoList.size()!=0){
			return ptProbationInfoList.get(0);
		}
		
		return null;
    }
	
	/**
	 * 删除通过bizPersonOid
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		
		DaoUtil.executeUpdate("delete from PtProbationInfo ppi where ppi.bizPersonOid = " + bizPersonOid);
	
	}
}
