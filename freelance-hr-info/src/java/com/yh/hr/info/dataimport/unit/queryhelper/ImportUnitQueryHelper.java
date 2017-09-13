package com.yh.hr.info.dataimport.unit.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.res.unit.bo.UtOrg;
import com.yh.hr.res.unit.bo.UtOrganization;
import com.yh.hr.res.unit.bo.UtRelation;
import com.yh.hr.res.unit.bo.UtUnit;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 单位、科室导入queryHelper
 * @author chenjl
 * @date 2017-03-23
 * @version 1.0
 */
public class ImportUnitQueryHelper {
	
	/**
	 * 查询导入的组织机构历史信息
	 * @return
	 * @throws ServiceException
	 */
	public static List<UtOrganization> getUtOrganization() throws ServiceException {
		
		StringBuffer hql = new StringBuffer("from UtOrganization org where org.organizationType ='2'");
		List<UtOrganization> list= DaoUtil.find(hql.toString());
		if(CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}

	/**
	 * 查询导入的单位业务历史信息
	 * @return
	 * @throws ServiceException
	 */
	public static List<UtUnit> getUtUnit() throws ServiceException {
		
		String hql = "from UtUnit ut";
		List<UtUnit> list= DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}
	
	
	/**
	 * 查询上次导入的单位基础历史信息
	 * @return
	 * @throws ServiceException
	 */
	public static List<UbUnit> getUbUnit() throws ServiceException {
		String hql = "from UbUnit ub ";
		List<UbUnit> list= DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}
	
	/**
	 * 查询上次导入的科室基础历史信息
	 * @return
	 * @throws ServiceException
	 */
	public static List<UbOrg> getUbOrg() throws ServiceException {
		String hql = "from UbOrg ub ";
		List<UbOrg> list= DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}
	
	/**
	 * 查询导入的科室业务历史信息
	 * @return
	 * @throws ServiceException
	 */
	public static List<UtOrg> getUtOrg() throws ServiceException {
		
		String hql = "from UtOrg ut ";
		List<UtOrg> list= DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}
	
	/**
	 * 查询上次导入的组织关系历史信息
	 * @return
	 * @throws ServiceException
	 */
	public static List<UtRelation> getUtRelation() throws ServiceException {
		StringBuffer hql =new StringBuffer("from UtRelation ut where ut.relationType in ('2','3')");
		List<UtRelation> list= DaoUtil.find(hql.toString());
		if(CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}

	/**
	 * 根据单位名称查询单位信息
	 * @param unitName
	 * @return
	 * @throws ServiceException
	 */
	public static UbUnit getUnitByName(String unitName) throws ServiceException{
		String hql = "from UbUnit ub where ub.unitName ='"+unitName+"'";
		List<UbUnit> list= DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据科室名称查询科室信息
	 * @param orgName
	 * @return
	 * @throws ServiceException
	 */
	public static UbOrg getOrgByName(String orgName) throws ServiceException{
		String hql = "from UbOrg ub where ub.orgName ='"+orgName+"'";
		List<UbOrg> list= DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	

}
