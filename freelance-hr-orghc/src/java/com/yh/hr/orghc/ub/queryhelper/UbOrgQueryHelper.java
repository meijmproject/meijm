package com.yh.hr.orghc.ub.queryhelper;

import java.util.List;

import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.component.orgtree.queryhelper.JhcOrgTreeQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 内设机构信息查询工具类
 */
public class UbOrgQueryHelper {
	
	public static UbOrgDTO getUbOrgDTOById(Long orgOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(UbOrg.class, orgOid), UbOrgDTO.class);
	}
	
	public static List<UbOrgDTO> listByUnitOid(Long unitOid) throws ServiceException{
		final StringBuffer hBuffer =  new StringBuffer("from UbOrg uo where 1 = 1 ");
		if(StringUtil.isNotNull(unitOid)){
			hBuffer.append(" and uo.unitOid =" +unitOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), UbOrgDTO.class);
	}

	public static List<UbOrgDTO> listByUnitOidAndStatus(Long unitOid,String orgStatus) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from UbOrg uo where 1 = 1 ");
		if(StringUtil.isNotNull(unitOid)){
			hBuffer.append(" and uo.unitOid =" +unitOid);
		}
		if(StringUtils.isNotEmpty(orgStatus)){
			hBuffer.append(" and uo.orgStatus = '" +orgStatus + "'");
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), UbOrgDTO.class);
	}
	
	/**
	 * 获取当前科室及其下级所有科室
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<UbOrgDTO> listAllChildOrgByOid(Long orgOid) throws ServiceException {
		String orgOidString = JhcOrgTreeQueryHelper.getOrgOidListByOid(DataConverUtils.toString(orgOid));
		String hql = "from UbOrg uo where uo.orgOid in("+orgOidString+")";
		return BeanHelper.copyProperties(DaoUtil.find(hql), UbOrgDTO.class);
	}
	
	/**
	 * 列出单位下所有科室类型信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<UbOrgDTO> listOrgType(Long unitOid,String orgType) throws ServiceException{
		final StringBuffer hBuffer =  new StringBuffer("from UbOrg uo where  1 = 1 ");
		if(StringUtil.isNotNull(unitOid)){
			hBuffer.append(" and uo.unitOid =" +unitOid);
		}
		if(StringUtils.isNotEmpty(orgType)){
			hBuffer.append(" and uo.orgType ='" +orgType+"'");
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), UbOrgDTO.class);
	}
}
