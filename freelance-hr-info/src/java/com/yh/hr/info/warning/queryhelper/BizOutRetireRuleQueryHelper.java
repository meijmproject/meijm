package com.yh.hr.info.warning.queryhelper;


import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.bo.BizOutRetireRule;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;


/**
 * 
 *@description		离退休预警规则QueryHelper
 *
 *@author            liuhw
 *@created           2014-03-31
 *@version           1.0
 *
 */
public class BizOutRetireRuleQueryHelper 
{
	/**
	 * 查找离退休预警规则
	 * @return
	 * @throws DataAccessException
	 */
	public static List<BizOutRetireRule> listRetireRule(TableTagBean ttb ) throws DataAccessFailureException
	{
		//查询语句
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BizOutRetireRule order by personType");
		List<BizOutRetireRule> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
		return list;
	}

	/**
	 * 根据条件查找离退休预警规则
	 * @param personType,bizFullProbationOid
	 * @return
	 * @throws DataAccessException
	 */
	public static List<BizOutRetireRule> findPersonType(String personType,Long bizOutRetireOid,String sexCode)throws DataAccessFailureException
	{
		//查询语句
		StringBuffer hql = new StringBuffer("from BizOutRetireRule where personType='"+personType+"' and sexCode='"+sexCode+"' ");
		if(null!=bizOutRetireOid){
			hql.append(" and bizOutRetireOid !='"+bizOutRetireOid+"'");
		}
		List<BizOutRetireRule> list = DaoUtil.find(hql.toString());
		return list;
	}


}
