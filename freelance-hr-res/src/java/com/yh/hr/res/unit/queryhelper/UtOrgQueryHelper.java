package com.yh.hr.res.unit.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.unit.bo.UtOrg;
import com.yh.hr.res.unit.util.UtConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.StringUtil;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/09/02
 */
public class UtOrgQueryHelper {

	/**
	 * 获取内设机构信息
	 * @param orgOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static UtOrg get(Long orgOid) throws DataAccessFailureException {
		return DaoUtil.uniqueResult("from UtOrg uo where uo.orgOid = ?", orgOid);
	}

	/**
	 * 查找单位下的所有内设机构
	 * @param unitOid
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<UtOrg> findUnitOrg(Long unitOid) throws DataAccessFailureException {
		StringBuilder hql = new StringBuilder();
		
		hql.append("select uo from UtOrg uo, UtRelation ur, UtUnit uu ");
		hql.append(" where uu.unitOid = ? ");
		hql.append("   and ur.relationType = '").append(UtConstants.UT_RELATION_TYPE_2).append("' ");
		hql.append("   and uo.organizationOid = ur.childOrganizationOid ");
		hql.append("   and ur.parentOrganizationOid = uu.organizationOid ");
		
		return DaoUtil.find(hql.toString(), unitOid);
	}

    public static List<UtOrgDTO> listByCondition(TableTagBean ttb) throws ServiceException {
		
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		
		hql.append(" from yhc_ut_org u left join (select ur.child_organization_oid,ur.parent_organization_oid,juo.org_oid,");
		hql.append(" juo.org_name from yhc_ut_relation ur, yhc_ut_org juo where ur.parent_organization_oid = juo.organization_oid");
		hql.append("  and ur.relation_type='"+UtConstants.UT_RELATION_TYPE_2+"') t on (u.organization_oid =t.child_organization_oid) ");
		hql.append("  where 1=1");
		if (StringUtils.isNotEmpty(ttb.getCondition().get("orgName"))) {
			hql.append(" and u.org_name like '"+StringUtil.wrapPercent(ttb.getCondition().get("orgName"))+"'");
		}
		if(StringUtils.isNotEmpty(ttb.getCondition().get("isMainUnit"))&&Constant.YES.equals(ttb.getCondition().get("isMainUnit"))){
			hql.append(" and t.parent_organization_oid is null");
		}
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("authUnits"))) {
			hql.append(" and u.org_oid in (").append(ttb.getCondition().get("authUnits")).append(") ");
		}
		//授权 已选
		if (StringUtils.isNotEmpty(ttb.getCondition().get("exOrgOids"))) {
			hql.append(" and u.org_oid not in (").append(ttb.getCondition().get("exOrgOids")).append(") ");
		}
		//人员类别  待开发
		/*if (StringUtils.isNotEmpty(ttb.getCondition().get("personTypeCode"))) {
			hql.append(" and u.unit_category_code  in (").append(ttb.getCondition().get("personTypeCode")).append(") ");
		}*/
		if(ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder("select count(*) ").append(hql).toString(), params));
		}
		StringBuilder lsql = new StringBuilder();
		lsql.append("select u.org_oid,");
		lsql.append("       trim(u.org_name) as un," );
		lsql.append("       trim(t.org_name) as unp" );
		
		List<Object[]> list = null;
		if(StringUtils.isNotEmpty(ttb.getCondition().get("pageNo")) && StringUtils.isNotEmpty(ttb.getCondition().get("pageSize")))
		{
			list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(hql).append(" order by  u.org_oid").toString(), params, (Integer.parseInt(ttb.getCondition().get("pageNo"))-1)*Integer.parseInt(ttb.getCondition().get("pageSize")), Integer.parseInt(ttb.getCondition().get("pageSize")));
		}else
		{
			list = DaoUtil.findWithSQL(new StringBuilder(lsql).append(hql).append(" order by  u.org_oid").toString(), params);
		}
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], UtOrgDTO>() {

			public UtOrgDTO getValue(Object[] src) throws ServiceException {
				UtOrgDTO dto = new UtOrgDTO();
				dto.setOrgOid(DataConverUtils.toLong(src[0])==null?null:DataConverUtils.toLong(src[0]));
				dto.setOrgName(DataConverUtils.toString(src[1])==null?null:DataConverUtils.toString(src[1]));
				dto.setParentOrgName(DataConverUtils.toString(src[2])==null?null:DataConverUtils.toString(src[2]));
				return dto;
			}});
	}

    /**
     * 通过科室名称查询科室信息
     * @param orgName
     * @return
     * @throws DataAccessFailureException
     */
    public static UtOrgDTO findUtOrgDTOByOrgName(String orgName) throws ServiceException {
    	String hql = "from UtOrg uo where uo.orgStatus = '2' and uo.orgName='"+orgName+"'";
    	List<UtOrg> list = DaoUtil.find(hql);
    	if(CollectionUtils.isNotEmpty(list)) {
    		return BeanHelper.copyProperties(list.get(0), UtOrgDTO.class);
    	}
    	return null;
    }
}
