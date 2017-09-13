package com.yh.hr.res.gb.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.gb.dto.GbPostPercentDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.gb.bo.GbPostPercent;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 岗位信息 查询工具类
 * @author zhengdr
 *
 * 时间:2016-12-22下午01:39:04
 */
public class GbPostPercentQueryHelper {

	/**
	 * 得到岗位信息列表
	 * @param unitOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<GbPostPercentDTO> getGbPostPercentDTO(Long unitOid)throws ServiceException{
		//hql语句
		StringBuffer hql = new StringBuffer();
		hql.append(" from GbPostPercent gpp")
		.append(" where gpp.unitOid=").append(unitOid);
	    //得到列表
		List<GbPostPercent> gbPostPercentList = DaoUtil.find(hql.toString());

		return BeanHelper.copyProperties(gbPostPercentList,GbPostPercentDTO.class);
	
	}

	/**
	 * 得到岗位信息的记录数
	 * @param unitOid
	 * @return
	 * @throws DataAccessFailureException
	 * */
	public static int countGbPostPercentByUnitOid (Long unitOid)throws ServiceException{
		
		String hql = "select count(*) from GbPostPercent gpp where gpp.unitOid= :unitOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitOid", unitOid);
		
		return DaoUtil.countByCondition(hql, params);
	}
	
	/**
	 * 根据岗位类别、岗位级别获取岗位百分比信息
	 * @param unitOid
	 * @param postType
	 * @param postLevel
	 * @return
	 * @throws ServiceException
	 */
	public static List<GbPostPercentDTO> listGbPostPercentDTOByPostInfo(Long unitOid, String postType, String postLevel)throws ServiceException {
		
		StringBuffer hql = new StringBuffer("from GbPostPercent gpp where 1 = 1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != unitOid)
		{
			hql.append("and gpp.unitOid = :unitOid ");
			params.put("unitOid", unitOid);
		}
		if(StringUtils.isNotBlank(postType))
		{
			hql.append("and gpp.postType = :postType ");
			params.put("postType", postType);
		}
		if(StringUtils.isNotBlank(postLevel))
		{
			hql.append("and gpp.postLevel = :postLevel ");
			params.put("postLevel", postLevel);
		}
		List<GbPostPercent> boList = DaoUtil.find(hql.toString(), params);
		if(CollectionUtils.isNotEmpty(boList))
		{
			return BeanHelper.copyProperties(boList, GbPostPercentDTO.class);
		}
		return null;
	}
}
