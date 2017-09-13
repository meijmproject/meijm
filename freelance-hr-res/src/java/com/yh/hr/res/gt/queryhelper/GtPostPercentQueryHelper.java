package com.yh.hr.res.gt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gt.bo.GtPostPercent;
import com.yh.hr.res.gt.dto.GtPostPercentDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 岗位信息业务  查询工具类
 * @author zhengdr
 *
 * 时间:2016-12-21下午03:58:29
 */
public class GtPostPercentQueryHelper {

	/**
	 * 得到岗位信息列表
	 * @param utUnitOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<GtPostPercentDTO> getGtPostPercentDTO(Long utUnitOid)throws ServiceException{
		//hql语句
		StringBuffer hql = new StringBuffer();
		hql.append(" from GtPostPercent gpp")
		.append(" where gpp.utUnitOid=").append(utUnitOid);
	    //得到列表
		List<GtPostPercent> gtPostPercentList = DaoUtil.find(hql.toString());

		return BeanHelper.copyProperties(gtPostPercentList,GtPostPercentDTO.class);
	}

	/**
	 * 得到岗位信息的记录数
	 * @param utUnitOid
	 * @return
	 * @throws DataAccessFailureException
	 * */
	public static int countGtPostPercentByUtUnitOid (Long utUnitOid)throws ServiceException{
		
		String hql = "select count(*) from GtPostPercent gpp where gpp.utUnitOid= :utUnitOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("utUnitOid", utUnitOid);
		
		return DaoUtil.countByCondition(hql, params);
	}
	
	/**
	 * 根据utUnitOid删除岗位信息
	 * @param utUnitOid
	 * @throws ServiceException
	 */
	public static void deleteByUtUnitOid(Long utUnitOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from GtPostPercent gpp where gpp.utUnitOid = " + utUnitOid);
	}
}
