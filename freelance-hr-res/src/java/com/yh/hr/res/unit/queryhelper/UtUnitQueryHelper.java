/**
 * 
 */
package com.yh.hr.res.unit.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.unit.bo.UtUnit;
import com.yh.hr.res.unit.util.UtConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public class UtUnitQueryHelper {

	/**
	 * 查找已授权单位的详细信息， 如果为null，则查询全部单位，如果是没有权限，请传一个空集合（如java.util.Collections.emptyList();）
	 * @param authorizationOids
	 * @return
	 * @throws ServiceException
	 */
	public static List<UtUnitDTO> findUnitListByAuth(List<String> authorizationOids, StringMap params) throws ServiceException {
		
		if(CollectionUtils.isEmpty(authorizationOids)) return null;
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("from UtUnit u where 1=1 ");
		
		if (MapUtils.isNotEmpty(params)) {
			if (StringUtils.isNotEmpty(params.get("unitKind"))) {
				hql.append(" and u.unitKind in(").append(params.get("unitKind")).append(")");
			}
			
			if (StringUtils.isNotEmpty(params.get("unitStatus"))) {
				hql.append(" and u.unitStatus in(").append(params.get("unitStatus")).append(")");
			}else{
				//没有传单位状态---默认为正常
				hql.append(" and u.unitStatus in(").append(DicConstants.YHRS0101_2).append(")");
			}
		}
		
			
		hql.append(" and u.unitOid in(").append(StringUtil.join(authorizationOids)).append(")");
		
		return BeanHelper.copyProperties(DaoUtil.listByCondition(hql.toString(), null, 0, 0), UtUnitDTO.class);
	}
	
	
	
	public static List<UtUnitDTO> findUnitList(StringMap params) throws ServiceException {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("from UtUnit u where 1=1 ");
		
		if (MapUtils.isNotEmpty(params)) {
			if (StringUtils.isNotEmpty(params.get("unitKind"))) {
				hql.append(" and u.unitKind in(").append(params.get("unitKind")).append(")");
			}
			
			if (StringUtils.isNotEmpty(params.get("unitStatus"))) {
				hql.append(" and u.unitStatus in(").append(params.get("unitStatus")).append(")");
			}else{
				//没有传单位状态---默认为正常
				hql.append(" and u.unitStatus in(").append(DicConstants.YHRS0101_2).append(")");
			}
		}
		
		return BeanHelper.copyProperties(DaoUtil.listByCondition(hql.toString(), null, 0, 0), UtUnitDTO.class);
	}
	public static List<UtUnitDTO> listByCondition(TableTagBean ttb) throws ServiceException {
		
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		
		hql.append(" from yhc_ut_unit u left join (select ur.child_organization_oid,ur.parent_organization_oid,ut2.unit_oid,");
		hql.append(" ut2.unit_name,ut2.unit_kind from yhc_ut_relation ur, yhc_ut_unit ut2 where ur.parent_organization_oid = ut2.organization_oid");
		hql.append("  and ur.relation_type='"+ UtConstants.UT_RELATION_TYPE_1+"') t on (u.organization_oid =t.child_organization_oid) ");
		hql.append("  where 1=1");
		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitName"))) {
			hql.append(" and u.unit_name like '"+StringUtil.wrapPercent(ttb.getCondition().get("unitName"))+"'");
		}
		if(StringUtils.isNotEmpty(ttb.getCondition().get("isMainUnit"))&&Constant.YES.equals(ttb.getCondition().get("isMainUnit"))){
			hql.append(" and t.parent_organization_oid is null");
		}
		//单位性质
		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitKind"))) {
			hql.append(" and u.unit_kind in (").append(ttb.getCondition().get("unitKind")).append(") ");
		}
		//地区
		if (StringUtils.isNotEmpty(ttb.getCondition().get("areaCode"))) {
			hql.append(" and u.unit_area_code in (").append(ttb.getCondition().get("areaCode")).append(") ");
		}
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("authUnits"))) {
			hql.append(" and u.unit_oid in (").append(ttb.getCondition().get("authUnits")).append(") ");
		}
		//授权 已选
		if (StringUtils.isNotEmpty(ttb.getCondition().get("exUnitOids"))) {
			hql.append(" and u.unit_oid not in (").append(ttb.getCondition().get("exUnitOids")).append(") ");
		}
		//系统类别
		if (StringUtils.isNotEmpty(ttb.getCondition().get("systemCode"))) {
			hql.append(" and u.unit_category_code  in (").append(ttb.getCondition().get("systemCode")).append(") ");
		}
		if(ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder("select count(*) ").append(hql).toString(), params));
		}
		StringBuilder lsql = new StringBuilder();
		lsql.append("select u.unit_oid,");
		lsql.append("       trim(u.unit_name) as un," );
		lsql.append("       u.unit_kind," );
		lsql.append("       u.unit_category_code," );
		lsql.append("       u.unit_area_code," );
		lsql.append("       trim(t.unit_name) as unp," );
		lsql.append("       u.unit_code" );
		/*return BeanHelper.copyProperties(DaoUtil.listByCondition(
				new StringBuilder().append(hql).append(" order by u.orderOfAll, u.unitOid").toString(), params, ttb.getPage(), ttb.getPageSize()), UtUnitDTO.class);*/
		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder(lsql).append(hql).append(" order by u.order_of_all, u.unit_oid").toString(), params, ttb.getPage(), ttb.getPageSize());
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], UtUnitDTO>() {

			public UtUnitDTO getValue(Object[] src) throws ServiceException {
				UtUnitDTO dto = new UtUnitDTO();
				dto.setUnitOid(DataConverUtils.toLong(src[0])==null?null:DataConverUtils.toLong(src[0]));
				dto.setUnitName(DataConverUtils.toString(src[1])==null?null:DataConverUtils.toString(src[1]));
				dto.setUnitKind(DataConverUtils.toString(src[2])==null?null:DataConverUtils.toString(src[2]));
				dto.setUnitCategoryCode(DataConverUtils.toString(src[3])==null?null:DataConverUtils.toString(src[3]));
				dto.setUnitAreaCode(DataConverUtils.toString(src[4])==null?null:DataConverUtils.toString(src[4]));
				dto.setParentUnitName(DataConverUtils.toString(src[5])==null?null:DataConverUtils.toString(src[5]));
				dto.setUnitCode(DataConverUtils.toString(src[6])==null?null:DataConverUtils.toString(src[6]));
				return dto;
			}});
	}
	
	public static UtUnit get(Long unitOid) throws ServiceException {
		return DaoUtil.uniqueResult("from UtUnit u where u.unitOid = ? " , unitOid);
	}

	public static UtUnit getByOrganizationOid(Long organizationOid) throws ServiceException {
		return DaoUtil.uniqueResult("from UtUnit u where u.organizationOid = ? " , organizationOid);
	}
	
	public static UtUnitDTO getAdminUnit(Long unitOid) throws ServiceException {
		UtUnit utUnit = get(unitOid);
		if(null != utUnit){
			UtUnit adminUtUnit = getByOrganizationOid(UtRelationQueryHelper.findAdminByChildOrganizationOid(utUnit.getOrganizationOid()));
			return BeanHelper.copyProperties(adminUtUnit, UtUnitDTO.class);
		}
		return null;
	}



	public static List<Long> findAdminUnitOid(List<Long> unitOids) throws ServiceException {
		StringBuffer sql = new StringBuffer("select uu.unit_oid from yhc_ut_relation ur,yhc_ut_unit uu")
			.append(" where ur.parent_organization_oid = uu.organization_oid")
			.append(" and not exists(select 1 from yhc_ut_relation u where u.child_organization_oid = ur.parent_organization_oid)")
			.append(" start with uu.unit_oid in(").append(StringUtil.arrayToSql(unitOids, StringUtil.BLANK)).append(")")
			.append(" connect by prior ur.parent_organization_oid = ur.child_organization_oid");
        return DaoUtil.findWithSQL(sql.toString());
	}


    /**
     * 查询单位信息
     * @return
     * @throws ServiceException 
     * @throws DataAccessFailureException 
     */
	public static List<UtUnitDTO> findUnitInfo() throws DataAccessFailureException, ServiceException {
        StringBuilder hql = new StringBuilder();
		hql.append("from UtUnit u where 1=1 ");
		return BeanHelper.copyProperties(DaoUtil.find(hql.toString()), UtUnitDTO.class);
	}



	/**
	 * 根据orgOid获取UtUnitDTO
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public static UtUnitDTO getUnitInfoByOrgOid(Long orgOid) throws ServiceException{
		String sql="select ut from UtUnit ut, UbOrg uo where ut.unitOid=uo.unitOid and uo.orgOid ="+orgOid+")";
		List<UtUnit> list=DaoUtil.find(sql);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return BeanHelper.copyProperties(list.get(0), UtUnitDTO.class);
	}
}
