package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtMilitaryInfo;
import com.yh.hr.res.pt.dto.PtMilitaryInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 军队任职信息查询工具类（业务） 
 * @author zhengdr
 *
 * 时间:2016-10-9下午03:05:32
 */
public class PtMilitaryInfoQueryHelper {

	/**
	 * 得到军队任职信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<PtMilitaryInfoDTO> getPtMilitaryInfoDTO(Long bizPersonOid)throws ServiceException{
		//hql语句
		StringBuffer hql = new StringBuffer();
		hql.append(" from PtMilitaryInfo pm")
		.append(" where pm.bizPersonOid=").append(bizPersonOid)
		.append(" order by pm.seviceBeginDate desc");
	    //得到列表
		List<PtMilitaryInfo> PtMilitaryInfoList = DaoUtil.find(hql.toString());

		return BeanHelper.copyProperties(PtMilitaryInfoList,PtMilitaryInfoDTO.class);
	}

	/**
	 * 得到军队任职信息的记录数
	 * @param bizPersonOid
	 * @return
	 * @throws DataAccessFailureException
	 * */
	public static int countPtMilitaryInfoByBizPersonOid (Long bizPersonOid)throws ServiceException{
		
		String hql = "select count(*) from PtMilitaryInfo pm where pm.bizPersonOid= :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		
		return DaoUtil.countByCondition(hql, params);
	}
	
	/**
	 * 根据bizPersonOid删除军队任职信息信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtMilitaryInfo mi where mi.bizPersonOid = " + bizPersonOid);
	}
}
