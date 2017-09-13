package com.yh.hr.info.warning.queryhelper;


import java.util.HashMap;
import java.util.List;

import com.yh.hr.info.warning.bo.BizFullProbationRule;
import org.springframework.dao.DataAccessException;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;


/**
 * 
 *@description		聘任制试用期到期预警规则QueryHelper
 *
 *@author            liuhw
 *@created           2014-03-31
 *@version           1.0
 *
 */
public class BizFullProbationRuleQueryHelper 
{
	/**
	 * 查找聘任制试用期到期预警规则
	 * @return
	 * @throws DataAccessException
	 * @throws DataAccessFailureException 
	 */
	public static List<BizFullProbationRule> listProbationRule(TableTagBean ttb ) throws DataAccessFailureException
	{
		//查询语句
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BizFullProbationRule order by personType");
		List<BizFullProbationRule> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
		return list;
	}

	/**
	 * 根据条件查找聘任制试用期到期预警规则
	 * @param personType,bizFullProbationOid
	 * @return
	 * @throws DataAccessException
	 */
	public static List<BizFullProbationRule> findPersonType(String personType,Long bizFullProbationOid)throws DataAccessFailureException
	{
		//查询语句
		StringBuffer hql = new StringBuffer("from BizFullProbationRule where personType='"+personType+"'");
		if(null!=bizFullProbationOid){
			hql.append(" and bizFullProbationOid !='"+bizFullProbationOid+"'");
		}
		List<BizFullProbationRule> list = DaoUtil.find(hql.toString());
		return list;
	}


}
