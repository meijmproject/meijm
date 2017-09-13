package com.yh.hr.res.pb.queryhelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbVacation;
import com.yh.hr.res.pb.dto.PbVacationDto;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 人员请休假基础信息查询工具类
 * @author renp
 *
 */
public class PbVacationInfoQueryHelper {

	/**
	 * 根据personOid查询人员外出信息
	 * @param personOid
	 * @return
	 * @throws com.jade.platform.core.exception.ServiceException
	 */
	public static List<PbVacationDto> list(Long personOid,String vacationType) throws ServiceException {
		
		StringBuilder hql = new StringBuilder("from PbVacation fi where fi.personOid = :personOid ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		if(null!=vacationType){
			hql.append(" fi.vacationType =:vacationType");
			params.put("personOid", personOid);
		}
		hql.append(" order by fi.createDate asc");
		List<PbVacation> boList = DaoUtil.find(hql.toString(), params);
		List<PbVacationDto> dtoList = new ArrayList<PbVacationDto>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbVacation bo : boList)
			{
				PbVacationDto dto = new PbVacationDto();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	public static List<JSONObject> listPbVacation(TableTagBean ttb) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();
		buildSQL(ttb,sql,params);

		StringBuilder lsql = new StringBuilder();
		lsql.append("select jpp.person_oid,");
		lsql.append("       jpp.person_code,");
		lsql.append("       jpp.name, ");
		lsql.append(" (select uo.org_name from yhc_ut_org uo where uo.org_oid = jpp.hire_dept_oid) as hire_dept_name ,");
		lsql.append("       jpvi.VACATION_Oid," );
		lsql.append("       jpvi.VACATION_TYPE," );
		lsql.append("       date_format(jpvi.START_DATE,'%Y-%m-%d')," );
		lsql.append("       date_format(jpvi.END_DATE,'%Y-%m-%d'),");
		lsql.append("       jpvi.VACATION_DAYS," );
		lsql.append("       jpvi.REASON," );
		lsql.append("       jpvi.CREATE_DATE" );

		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) from (").append(lsql).append(sql).append(") t").toString(), params));
		}
		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder().append("select * from (").append(lsql).append(sql).append(") t order by CREATE_DATE").toString(), params, ttb.getPage(), ttb.getPageSize());

		return buildJSON(list);
	}
	
	public static void buildSQL(TableTagBean ttb, StringBuilder sql, Map<String, Object> params) throws ServiceException {
		sql.append(" from yhc_pb_vacation_info jpvi, yhc_pb_person_info jpp" );
        sql.append("  where jpp.person_oid = jpvi.person_oid ");
        sql.append("	and jpvi.vacation_oid not in (");
		sql.append("		select jpvi.BASE_VACATION_OID from ");
		sql.append("		yhc_pt_vacation_info jpvi,");
		sql.append("		yhc_pt_person jpp,");
		sql.append("		yhc_bt_task jbt");
		sql.append("		where jpvi.VACATION_STATUS = 2");
		sql.append("		and jpvi.BIZ_PERSON_OID = jpp.BIZ_PERSON_OID");
		sql.append("		and jpp.TASK_OID = jbt.TASK_OID");
		sql.append("		and (jbt.PROCESS_RESULT = 0 or jbt.PROCESS_RESULT is null)");
		sql.append("	)");
//		sql.append("	and jpvi.vacation_oid not in (");
//		sql.append("		select jpvi.BASE_VACATION_OID from ");
//		sql.append("		yhc_pt_vacation_info jpvi,"); 
//		sql.append("		yhc_pt_person jpp, ");
//		sql.append("		yhc_bt_task jbt ");
//		sql.append("		where vacation_type = '"+ DicConstants.YHRS0130_13+"'");
//		sql.append("		and jpvi.BIZ_PERSON_OID = jpp.BIZ_PERSON_OID ");
//		sql.append("		and jpp.TASK_OID = jbt.TASK_OID");
//		sql.append("		and jbt.PROCESS_RESULT != 0)");
		if (StringUtils.isNotEmpty(ttb.getCondition().get("orgOid"))) {
			String orgOidString = getOrgOidListByOid(ttb.getCondition().get("orgOid"));
			sql.append(" and jpp.HIRE_DEPT_OID in(").append(orgOidString).append(")");
		}
        if (StringUtils.isNotEmpty(ttb.getCondition().get("personCode"))) {
			sql.append(" and jpp.person_code like '%").append(ttb.getCondition().get("personCode")).append("%'");
		}
        if (StringUtils.isNotEmpty(ttb.getCondition().get("name"))) {
			sql.append(" and jpp.name like '%").append(ttb.getCondition().get("name")).append("%'");
		}
        if (StringUtils.isNotEmpty(ttb.getCondition().get("vacationType"))) {
        	String vacationType = ttb.getCondition().get("vacationType");
        	sql.append(" and jpvi.vacation_Type = '" +vacationType+"'");
		}
        sql.append(" and jpp.HIRE_DEPT_OID in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("')");
	}
	
	private static List<JSONObject> buildJSON(List<Object[]> list) throws ServiceException
	{
		List<JSONObject> dtoList = new ArrayList<JSONObject>();
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)){return dtoList;}
		for (int i = 0; i < list.size(); i++)
		{
			Object[] task = list.get(i);
			String personOid = task[0] == null ? null:task[0].toString();
			String personCode = task[1] == null ? null:task[1].toString();
			String name = task[2] == null ? null: task[2].toString();
			String hirDeptName = task[3] == null ? null:task[3].toString();
			String vacationOid = task[4] == null ? null:task[4].toString();
			String vacationType = task[5] == null ? null:task[5].toString();
			Date startDate = task[6] == null ? null:DateUtil.parseDate(task[6].toString());
			Date endDate = task[7] == null ? null:DateUtil.parseDate(task[7].toString());
			String vacationDays = task[8] == null ? null:task[8].toString();
			String reason = task[9] == null ? null:task[9].toString();
			
			JSONObject obj = new JSONObject();

			obj.put("personOid",personOid);
			obj.put("name",name);
			obj.put("vacationOid", vacationOid);
			obj.put("vacationType", vacationType);
			obj.put("vacationTypeDesc", DicHelper.viewName(DicConstants.YHRS0130,vacationType));
			obj.put("startDate", startDate);
			obj.put("startDateStr", DateUtil.formatDate(startDate));
			obj.put("endDate", endDate);
			obj.put("endDateStr", DateUtil.formatDate(endDate));
			obj.put("vacationDays", vacationDays);
			obj.put("reason", reason);
			obj.put("personCode", personCode);
			obj.put("hirDeptName", hirDeptName);

			dtoList.add(obj);
		}
		return dtoList;
	}

	/**
	 * 根据人员ID、开始时间、结束时间查询请休假信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @param flag 
	 * @return
	 */
	public static List<PbVacationDto> findVacationInfoByPeriodStartDateAndEndDate1(
			Long personOid, Date startDate, Date endDate,String flag) throws DataAccessFailureException
	{		
		StringBuffer sb = new StringBuffer("select p.vacation_oid,p.person_oid,p.vacation_type,p.start_date,p.end_date,p.vacation_days")
		.append(",statutory_holiday_days,p.start_date_days,p.end_date_days from yhc_pb_vacation_info p where 1=1 ");
		if("1".equals(flag))
		{
			sb.append(" and person_oid = ").append(personOid).append(" and start_date >= str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date <= str_to_date('")
			.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		}
		if("2".equals(flag))
		{
			sb.append(" and person_oid = ").append(personOid).append(" and start_date < str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date <= str_to_date('")
			.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')").append(" and end_date > str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		}
		if("3".equals(flag))
		{
			sb.append(" and person_oid = ").append(personOid).append(" and start_date >= str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date > str_to_date('")
			.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')").append(" and start_date <= str_to_date('").append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		}
		if("4".equals(flag))
		{
			sb.append(" and person_oid = ").append(personOid).append(" and start_date < str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date > str_to_date('")
			.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		}
		List<Object[]> list = DaoUtil.findWithSQL(sb.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			List<PbVacationDto> dtoList = new ArrayList<PbVacationDto>();
			for(Object[] obj : list)
			{
				PbVacationDto dto = new PbVacationDto();
				dto.setVacationOid(obj[0] == null ? null:Long.valueOf(obj[0].toString()));
				dto.setPersonOid(personOid);
				dto.setVacationType(obj[2] == null ? null:obj[2].toString());
				dto.setStartDate(obj[3] == null ? null:(Date)obj[3]);
				dto.setEndDate(obj[4] == null ? null:(Date)obj[4]);
				dto.setVacationDays(obj[5] == null ? null:Double.valueOf(obj[5].toString()));
				dto.setStatutoryHolidayDays(obj[6] == null ? null:Double.valueOf(obj[6].toString()));
				dto.setStartDateDays(obj[7] == null ? null:Double.valueOf(obj[7].toString()));
				dto.setEndDateDays(obj[8] == null ? null:Double.valueOf(obj[8].toString()));
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}
	/**
	 * 根据人员ID查询请休假信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @param flag 
	 * @return
	 */
	public static List<PbVacationDto> findVacationInfoBypersonOid(Long personOid, Date startDate, Date endDate) throws DataAccessFailureException
	{		
		StringBuffer sb = new StringBuffer("select p.vacation_oid,p.person_oid,p.vacation_type,p.start_date,p.end_date,p.vacation_days")
		.append(",statutory_holiday_days,p.start_date_days,p.end_date_days from yhc_pb_vacation_info p where 1=1 ");
		sb.append(" and person_oid = ").append(personOid);
		sb.append(" and start_date <= str_to_date('").append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date >= str_to_date('")
		.append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		List<Object[]> list = DaoUtil.findWithSQL(sb.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			List<PbVacationDto> dtoList = new ArrayList<PbVacationDto>();
			for(Object[] obj : list)
			{
				PbVacationDto dto = new PbVacationDto();
				dto.setVacationOid(obj[0] == null ? null:Long.valueOf(obj[0].toString()));
				dto.setPersonOid(personOid);
				dto.setVacationType(obj[2] == null ? null:obj[2].toString());
				dto.setStartDate(obj[3] == null ? null:(Date)obj[3]);
				dto.setEndDate(obj[4] == null ? null:(Date)obj[4]);
				dto.setVacationDays(obj[5] == null ? null:Double.valueOf(obj[5].toString()));
				dto.setStatutoryHolidayDays(obj[6] == null ? null:Double.valueOf(obj[6].toString()));
				dto.setStartDateDays(obj[7] == null ? null:Double.valueOf(obj[7].toString()));
				dto.setEndDateDays(obj[8] == null ? null:Double.valueOf(obj[8].toString()));
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}
	/**
	 * 获取科室OID拼接字符串
	 * @param orgOid
	 * @throws DataAccessFailureException 
	 */
	private static String getOrgOidListByOid(String orgOid) throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			String proc = "{?=call getChildOrgList(?)}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, orgOid);
			cstmt.execute();
			String orgOidString = cstmt.getString(1);
			cstmt.close();
			return orgOidString;
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程获取科室OID拼接字符串失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
	}
	
	private static void close(Connection conn) throws DataAccessFailureException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DataAccessFailureException("关闭Connection失败", e);
		}
	}
	
	private static void close(Statement stmt) throws DataAccessFailureException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new DataAccessFailureException("关闭Statement失败", e);
		}
	}
}
