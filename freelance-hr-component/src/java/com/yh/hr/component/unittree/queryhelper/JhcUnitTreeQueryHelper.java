package com.yh.hr.component.unittree.queryhelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.util.UtConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

public class JhcUnitTreeQueryHelper {


    public static List<UtUnitDTO> findUnitList(String orgOid) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select juu.unit_name,juu.organization_oid from yhc_ut_unit juu ");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<UtUnitDTO> items = new ArrayList<UtUnitDTO>();
			
			for (Object[] objs : list) 
			{
				UtUnitDTO dto = new UtUnitDTO();
				dto.setUnitName(objs[0] == null ? null : objs[0].toString());
				dto.setOrganizationOid(objs[1] == null ? null : Long.valueOf(objs[1].toString()));
				items.add(dto);
			}
			return items;
		}
		return null;
	}
	public static List<UtOrgDTO> findUnit(String organizationOid,String relationFlag) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ju.org_oid,ju.org_name,ju.organization_oid from yhc_ut_relation jur,yhc_ut_org ju where ");
		sql.append(" ju.organization_oid = jur.child_organization_oid and");
		if(UtConstants.UT_RELATION_TYPE_2.equals(relationFlag)){
			sql.append("  jur.relation_type = '"+UtConstants.UT_RELATION_TYPE_2+"'");
		}else if(UtConstants.UT_RELATION_TYPE_3.equals(relationFlag)){
			sql.append("  jur.relation_type = '"+UtConstants.UT_RELATION_TYPE_3+"'");
		}
		sql.append(" and jur.parent_organization_oid="+organizationOid);
		sql.append(" order by ju.org_oid");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<UtOrgDTO> items = new ArrayList<UtOrgDTO>();
			
			for (Object[] objs : list) 
			{
				UtOrgDTO dto = new UtOrgDTO();
				dto.setOrgOid(objs[0] == null ? null : Long.valueOf(objs[0].toString()));
				dto.setOrgName(objs[1] == null ? null : objs[1].toString());
				dto.setOrganizationOid(objs[2] == null ? null : Long.valueOf(objs[2].toString()));
				items.add(dto);
			}
			return items;
		}
		return null;
	}
	public static boolean checkIsChild(String orgOid) throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ju.org_name,ju.org_oid from yhc_ut_org juo,yhc_ut_relation jur,yhc_ut_org ju where juo.organization_oid = jur.parent_organization_oid");
		sql.append(" and ju.organization_oid = jur.child_organization_oid and jur.relation_type = '"+UtConstants.UT_ORGANIZATION_TYPE_2+"'");
		sql.append(" and juo.org_oid="+orgOid);
		sql.append(" order by ju.org_oid");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			return false;
		}
		return true;
	}
}
