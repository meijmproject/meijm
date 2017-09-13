package com.yh.hr.select.unit.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yh.hr.select.unit.dto.UnitSelectDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;


public class UnitSelectQueryHelper {
	
	public static List<UnitSelectDTO> listUbUnitInfo(TableTagBean ttb) throws ServiceException {
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
	
		hql.append(" select ");
		hql.append(" juu.unit_oid ");
		hql.append(" , juu.unit_name ");
		hql.append(" , juu.unit_kind ");
		hql.append(" , juu.unit_category_code ");
		hql.append(" , juu.level_code ");
		
		//排序
//		hql.append(", rank() over(partition by (");
//		hql.append(" select jdi.display_order  from yha_dic_item jdi  where jdi.dic_item_code =jppa.Administrative_Duty_Level  and jdi.dic_type_oid = 1015 ");
//		hql.append(" ) order by (");
//		hql.append("  select jdi.display_order  from yha_dic_item jdi where jdi.dic_item_code = jppa.ADMINISTRATIVE_DUTY_ATTRIBUTE and jdi.dic_type_oid = 1028 ");
//		hql.append(" ) asc) rk ");
		
		hql.append(" from ");
		hql.append("   yhe_ub_unit    juu ");
//		hql.append(" , yhc_pb_person_info   jppi ");
//		hql.append(" , jhi_ver_person       jvp ");
//		hql.append(" , YHC_PB_PERSON_ATTACH jppa ");
		
		hql.append(" where 1=1 ");
		
		buildSQL(ttb.getCondition(), hql, hqlParams);
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition((new StringBuilder().append("select count(*) from (").append(hql).append(")").toString()), hqlParams));
		}
		List<Object[]> list =DaoUtil.listWithSQLByCondition(hql.toString(), 				
 				hqlParams, ttb.getPage(), ttb.getPageSize());
		return build(list);
	}
	private static void buildSQL(StringMap params, StringBuffer hql, HashMap<String, Object> hqlParams)  throws ServiceException 
	{
		String unitName = params.get("unitName");
		String unitStatus =params.get("unitStatus");
		String authUnits = params.get("authUnits");// 资源权限
		//是主管单位还是下级单位标识
		String isParentUnit = params.get("isParentUnit");
		//单位性质
		String unitKind = params.get("unitKind");
		if(StringUtils.isNotEmpty(unitName)){
			
			hql.append("  and juu.unit_name like '").append(StringUtil.wrapPercent(unitName)).append("'");
		}
		
		if(StringUtils.isNotEmpty(unitStatus)){
			
			hql.append("  and juu.unit_status in(").append(unitStatus).append(")");
			
		}
		//是主管单位还是下级单位标识
		if(StringUtils.isNotEmpty(isParentUnit)){
			//1代表是主管单位
			if(DicConstants.YHRS0003_1.equals(isParentUnit)){
				hql.append("  and juu.PARENT_UNIT_OID is null ");
			}else if(DicConstants.YHRS0003_0.equals(isParentUnit)){
				//0代表是下级单位
				hql.append("  and juu.PARENT_UNIT_OID is not null ");
			}	
		}
		
		//单位性质
		if(StringUtils.isNotEmpty(unitKind)){
			
			hql.append("  and juu.unit_kind in(").append(unitKind).append(")");
			
		}
		//权限控制
		hql.append(" and juu.UNIT_OID in(").append(authUnits).append(") ");
	}
	
	private static List<UnitSelectDTO> build(List<Object[]> list) throws DataAccessFailureException,ServiceException
	{
		List<UnitSelectDTO> unitSelectDTOList = new ArrayList<UnitSelectDTO>();		 
		if (CollectionUtils.isEmpty(list))
		{
			return unitSelectDTOList;
		}
	    for (int i = 0; i < list.size(); i++)
		{
	    	Object[] task = list.get(i);
	    	UnitSelectDTO unitSelectDTO  = new UnitSelectDTO();
	    	Long unitOid = task[0] == null ? null:new Long(task[0].toString());
	    	String unitName = DataConverUtils.toString(task[1]);
	    	String unitKind = DataConverUtils.toString(task[2]);
	    	String unitCategoryCode = DataConverUtils.toString(task[3]);
	    	String unitLevelCode = DataConverUtils.toString(task[4]);
	    	
	    	unitSelectDTO.setUnitOid(unitOid);
	    	unitSelectDTO.setUnitName(unitName);
	    	unitSelectDTO.setUnitKind(unitKind);
	    	unitSelectDTO.setUnitCategoryCode(unitCategoryCode);
	    	unitSelectDTO.setUnitLevelCode(unitLevelCode);

	    	unitSelectDTOList.add(unitSelectDTO);
	    	
		}
	    return unitSelectDTOList;
	}    
	public static List<String> check( Long unitOid) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select bf.flow_name from yhe_ut_unit juu , yhc_bt_task t , yhd_bt_flow bf");	
		sql.append(" where t.task_oid = juu.task_oid");
		sql.append(" and t.item_code = bf.flow_code(+)");
		sql.append(" and t.complete_time is  null");
		sql.append(" and juu.unit_oid = "+unitOid);
		List<String> list=DaoUtil.findWithSQL(sql.toString());
		return list;
	}
	
	public static List<String> yearCheck( Long unitOid,String itemCode) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select bf.flow_name from yhe_ut_unit juu , yhc_bt_task t , yhd_bt_flow bf");	
		sql.append(" where t.task_oid = juu.task_oid");
		sql.append(" and t.item_code = bf.flow_code(+)");
		//单位性质
		if(StringUtils.isNotEmpty(itemCode)){	
			sql.append(" and t.item_code = "+itemCode);		
		}
		sql.append(" and t.complete_time is  null");
		sql.append(" and juu.unit_oid = "+unitOid);
		List<String> list=DaoUtil.findWithSQL(sql.toString());
		return list;
	}
}
