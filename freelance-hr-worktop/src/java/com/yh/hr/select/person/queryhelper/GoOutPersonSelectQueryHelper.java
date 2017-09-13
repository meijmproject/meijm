package com.yh.hr.select.person.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.select.person.dto.PersonSelectDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.web.UserContext;


public class GoOutPersonSelectQueryHelper {
	
	public static List<PersonSelectDTO> listPbpersonInfo(TableTagBean ttb) throws ServiceException {
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select ");
		hql.append("  jppi.PERSON_OID, ");
		hql.append(" jppi.PERSON_CODE,");
		hql.append(" jppi.`NAME`, ");
		hql.append(" (SELECT juo.ORG_NAME FROM yhc_ut_org juo WHERE juo.ORG_OID =jppi.HIRE_DEPT_OID ) as orgName, ");
		hql.append(" jppi.HIRE_DEPT_OID, ");
		hql.append(" jpgoi.GO_OUT_ADDRESS,");
		
		hql.append(" date_format(jpgoi.START_DATE, '%Y-%m-%d ') START_DATE,");
		hql.append(" date_format(jpgoi.END_DATE, '%Y-%m-%d ') END_DATE,");
		hql.append(" jpgoi.DAY_COUNT,");
		hql.append(" jpgoi.BUDGET_FROM, ");
		hql.append(" jpgoi.go_out_oid ");
		
		hql.append(" from ");
		hql.append("  yhc_pb_go_out_info jpgoi,");
		hql.append(" yhc_pb_person_info jppi ");
		
		hql.append(" where  jpgoi.PERSON_OID = jppi.PERSON_OID ");
		//hql.append(" and EXISTS (select 1 FROM yhb_user_org_auth juoa WHERE juoa.org_oid = jppi.HIRE_DEPT_OID and juoa.user_id ='"+UserContext.getLoginUserID()+"') ");
		//已办
		hql.append(" AND NOT EXISTS ( SELECT 1 FROM YHC_BT_TASK jbt, YHC_BT_TASK_ITEM jbti, YHC_PT_PERSON jpp, YHC_PT_go_out_INFO jpgoi1 	WHERE");
		hql.append(" jbt.task_oid = jbti.task_oid AND jbt.task_oid = jpp.task_oid AND jpp.biz_person_oid = jpgoi1.biz_person_oid AND jpgoi1.BASE_GO_OUT_OID = jpgoi.GO_OUT_OID");
		hql.append(" AND jbti.task_item_oid IN ( SELECT max(bti.task_item_oid) FROM yhc_bt_task_item bti WHERE bti.task_oid = jbt.task_oid GROUP BY bti.task_item_code) AND jbti.TASK_ITEM_STATUS = 1)");
		//已销
		hql.append("	AND NOT EXISTS (SELECT 1 FROM yhc_pb_revoke_go_out jpr WHERE jpr.pb_go_out_oid = jpgoi.GO_OUT_OID)");
		//过滤掉已销或已申请销假的和整个外出日期范围内的考勤数据都已经生成了的
		hql.append("	AND(( NOT EXISTS ( SELECT 1 FROM yhc_at_generate_status jags,yhc_at_parameter_set japs WHERE jags.HIS_DEPT_OID = jppi.HIRE_DEPT_OID AND jags.ATTENDANCE_YEAR = japs.ATTENDANCE_YEAR ");
		hql.append(" AND jags.ATTENDANCE_PERIOD = japs.ATTENDANCE_PERIOD AND  jpgoi.END_DATE BETWEEN japs.START_DATE and japs.END_DATE)");
		hql.append(" 	AND NOT EXISTS ( 	SELECT 1 FROM yhc_at_no_person janp WHERE janp.person_oid = jpgoi.PERSON_OID)) 	OR (");
		hql.append(" EXISTS ( SELECT 	1 FROM yhc_at_no_person janp WHERE janp.person_oid = jpgoi.PERSON_OID )))");
		buildSQL(ttb.getCondition(), hql, hqlParams);
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition((new StringBuilder().append("select count(*) from (").append(hql).append(") t").toString()), hqlParams));
		}
		List<Object[]> list =DaoUtil.listWithSQLByCondition(hql.toString(), 				
 				hqlParams, ttb.getPage(), ttb.getPageSize());
		return build(list,ttb.getCondition());
	}
	private static void buildSQL(StringMap params, StringBuffer hql, HashMap<String, Object> hqlParams)  throws ServiceException 
	{
		String goOutStartDate = params.get("goOutStartDate");
		String goOutEndDate = params.get("goOutEndDate");
		String taskItemCode = params.get("itemCodeNode").replace("'", "");;
		if(StringUtils.isNotEmpty(taskItemCode)) {
			//业务环节为个人申报环节
			if(taskItemCode.endsWith("1211")) {
				hql.append(" and exists (select 1 from yhb_user_relation ur where ((ur.ref_oid=jppi.person_oid and ur.ref_type='02') ) and ur.user_id= '").append(UserContext.getLoginUserID()).append("') ");;
			}else {
				//业务环节为科室申报环节
				if(taskItemCode.endsWith("1111")) {
					hql.append(" and jbt.created_by_code = '").append(UserContext.getLoginUserID()).append("' ");
				}
				//过滤用户权限
				hql.append(" and jppi.hire_dept_oid in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("') ");
			}
		}else {
			//过滤用户权限
			hql.append(" and jppi.hire_dept_oid in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("') ");
		}
		if(StringUtils.isNotEmpty(goOutStartDate)){
			hql.append("  and jpgoi.START_DATE >=str_to_date('"+goOutStartDate+"','%Y-%m-%d')");
		}
		if(StringUtils.isNotEmpty(goOutEndDate)){
			hql.append("  and jpgoi.END_DATE <=str_to_date('"+goOutEndDate+"','%Y-%m-%d')");
			
		}
	}
	private static List<PersonSelectDTO> build(List<Object[]> list,StringMap params) throws DataAccessFailureException,ServiceException
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
	    	String personCode = DataConverUtils.toString(task[1]);
	    	String name = DataConverUtils.toString(task[2]);
	    	String orgName = DataConverUtils.toString(task[3]);
	    	Long hireDeptOid = DataConverUtils.toLong(task[4]);
	    	String goOutAddress = DataConverUtils.toString(task[5]);
	    	String startDate = DataConverUtils.toString(task[6]);
	    	String endDate = DataConverUtils.toString(task[7]);
	    	String dayCount = DataConverUtils.toString(task[8]);
	    	String budgetFrom = DataConverUtils.toString(task[9]);
	    	Long goOutOid = task[10] == null ? null:new Long(task[10].toString());
	    	
	    	personSelectDTO.setPersonCode(personCode);
	    	personSelectDTO.setPersonOid(personOid);
	    	personSelectDTO.setName(name);
	    	personSelectDTO.setOrgName(orgName);
	    	personSelectDTO.setHireDeptOid(hireDeptOid);
	    	personSelectDTO.setGoOutAddress(goOutAddress);
	    	personSelectDTO.setStartDate(startDate);
	    	personSelectDTO.setEndDate(endDate);
	    	personSelectDTO.setBudgetFrom(DicHelper.viewName("YHRS0134", budgetFrom));
	    	personSelectDTO.setDayCount(Float.valueOf(dayCount==null?"0":dayCount));
	    	personSelectDTO.setGoOutOid(goOutOid);
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
