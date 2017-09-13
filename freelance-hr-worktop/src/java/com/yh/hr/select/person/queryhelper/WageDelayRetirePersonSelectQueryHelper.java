package com.yh.hr.select.person.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yh.hr.select.person.dto.PersonSelectDTO;
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

public class WageDelayRetirePersonSelectQueryHelper {
	
	public static List<PersonSelectDTO> listPbpersonInfo(TableTagBean ttb) throws ServiceException {
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
	
		hql.append(" select ");
		hql.append("  jppi.person_oid ");
		hql.append(" , jppi.name ");
		hql.append(" , jppi.id_no ");
		hql.append(" , jppi.person_status ");
		hql.append(" , jppi.updated_date ");
		hql.append(" , juu.unit_name ");
		
		hql.append(" , jppa.s_position_type ");
		hql.append(" , jppa.s_position_level ");
		hql.append(" , jppa.m_position_type ");
		hql.append(" , jppa.m_position_level ");
		hql.append(" , jppa.ADMINISTRATIVE_DUTY_LEVEL ");
		hql.append(" , jppa.ADMINISTRATIVE_DUTY_ATTRIBUTE ");
		hql.append(" , jppi.UNIT_OID ");
		
		hql.append(" from ");
		hql.append(" yhc_pb_person_info jppi ");
		hql.append(",YHC_PB_PERSON_ATTACH jppa  ");
		hql.append(", yhc_ut_unit juu ");
		
		
		hql.append(" where jppi.person_oid=jppa.person_oid ");
		hql.append(" and jppi.unit_oid=juu.unit_oid ");
		hql.append(" and (jppi.is_cadre is null or jppi.is_cadre= '").append(DicConstants.YHRS0003_0).append("')");//是否干部身份为“否”或为空，即“工人”身份
		hql.append(" and (jppa.m_position_type  = '").append(DicConstants.YHRS0022_1).append("'");//事业单位岗位类别-管理类
		hql.append("  or  jppa.m_position_type  = '").append(DicConstants.YHRS0022_2).append("')");//事业单位岗位类别-专业技术类
		hql.append(" and  jppi.sex_code = '2' ");//女职员
		
		buildSQL(ttb.getCondition(), hql, hqlParams);
		hql.append(" order by jppi.updated_date");
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
		String personName =params.get("personName");
		String personType =params.get("personType");//人员类别
		String personStatus =params.get("personStatus");//人员状态
		String authUnits = params.get("authUnits");// 资源权限
		if(StringUtils.isNotEmpty(unitName)){
			
			hql.append("  and juu.unit_name like '").append(StringUtil.wrapPercent(unitName)).append("'");
		}
		
		if(StringUtils.isNotEmpty(personName)){
			
			hql.append("  and jppi.name like '").append(StringUtil.wrapPercent(personName)).append("'");
			
		}
		if(StringUtils.isNotEmpty(personType)){
			
			hql.append("  and jppi.person_type in(").append(personType).append(")");
			
		}
		if(StringUtils.isNotEmpty(personStatus)){
			
			hql.append("  and jppi.person_status in(").append(personStatus).append(")");
			
		}
		//权限控制
		hql.append(" and jppi.UNIT_OID in(").append(authUnits).append(") ");
	}
	
	private static List<PersonSelectDTO> build(List<Object[]> list) throws DataAccessFailureException,ServiceException
	{
		List<PersonSelectDTO> personSelectDTOList = new ArrayList<PersonSelectDTO>();		 
		if (CollectionUtils.isEmpty(list))
		{
			return personSelectDTOList;
		}
	    for (int i = 0; i < list.size(); i++)
		{
	    	Object[] task = list.get(i);
	    	PersonSelectDTO personSelectDTO  = new PersonSelectDTO();
	    	Long personOid = task[0] == null ? null:new Long(task[0].toString());
	    	String personName = DataConverUtils.toString(task[1]);
	    	String idNo = DataConverUtils.toString(task[2]);
	    	String personStatus = DataConverUtils.toString(task[3]);
	    	/*String updatedDate = DataConverUtils.toString(task[4]);*/
	    	String unitName = DataConverUtils.toString(task[5]);
	    	String sPositionType = DataConverUtils.toString(task[6]);
	    	String sPositionLevel = DataConverUtils.toString(task[7]);
	    	String mPositionType = DataConverUtils.toString(task[8]);
	    	String mPositionLevel = DataConverUtils.toString(task[9]);
	    	String administrativeDutyLevel = DataConverUtils.toString(task[10]);
	    	String administrativeDutyAttribute = DataConverUtils.toString(task[11]);
	    	String unitOid = DataConverUtils.toString(task[12]);
	    	
	    	personSelectDTO.setPersonOid(personOid);
	    	personSelectDTO.setName(personName);
	    	personSelectDTO.setIdNo(idNo);
	    	personSelectDTO.setPersonStatus(personStatus);
	    	personSelectDTO.setUnitName(unitName);
	    	personSelectDTO.setsPositionType(sPositionType);
	    	personSelectDTO.setsPositionLevel(sPositionLevel);
	    	personSelectDTO.setmPositionType(mPositionType);
	    	personSelectDTO.setmPositionLevel(mPositionLevel);
	    	personSelectDTO.setAdministrativeDutyLevel(administrativeDutyLevel);
	    	personSelectDTO.setUnitOid(Long.valueOf(unitOid));
	    	personSelectDTO.setAdministrativeDutyAttribute(administrativeDutyAttribute);
	    	personSelectDTOList.add(personSelectDTO);
	    	
		}
	    return personSelectDTOList;
	}   
	public static List<String> check( Long personOid) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select bf.flow_name from yhc_pt_person pp , yhc_bt_task t , yhd_bt_flow bf");
		sql.append(" where t.task_oid = pp.task_oid");
		sql.append(" and t.item_code = bf.flow_code(+)");
		sql.append(" and t.complete_time is  null");
		sql.append(" and pp.person_oid = "+personOid);
		List<String> list=DaoUtil.findWithSQL(sql.toString());
		return list;
	}
}
