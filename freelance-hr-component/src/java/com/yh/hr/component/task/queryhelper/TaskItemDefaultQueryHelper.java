package com.yh.hr.component.task.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.util.UtConstants;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

import net.sf.json.JSONObject;


/**
 * 默认待办/已办公共查询
 * @author	zhangqp
 * @version	1.0,	16/11/03
 */
public class TaskItemDefaultQueryHelper {
	/**
	 * 获取总页数
	 * 
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public static int count(TableTagBean ttb) throws ServiceException {
		StringBuffer countSql = new StringBuffer("select count(distinct jbt.task_oid) ");
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		buildSQL(ttb.getCondition(), countSql, hqlParams);
		// 获取总页数
		int total = DaoUtil.countWithSQLByCondition(countSql.toString(), hqlParams);
		return total;
	}

	public static List<JSONObject> list(TableTagBean ttb) throws DataAccessFailureException,ServiceException {
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();

		hql.append("select distinct jpp.biz_person_oid,");
		hql.append("       jpp.task_oid,");
		hql.append("       jpp.name,");//人员名称
		hql.append("       jpp.id_no,");//证件号码
		hql.append("       jpp.person_status,");//人员状态YHRS0009
		hql.append("       jpp.person_type,");//人员类别YHRS0010
		hql.append("       obti.task_item_code,");//业务当前环节（流程当前所在环节）
		hql.append("       obti.task_item_name,");//
		hql.append("       obti.pre_task_item_code,");//业务当前环节的前一环节（流程当前所在环节）
		hql.append("       obti.pre_task_item_name,");//
		hql.append("       jbti.task_item_status,");//当前事项环节状态(待办/已办)
		hql.append("       to_char(jbt.CREATED_DATE, 'yyyy-mm-dd hh24:mi:ss') CREATED_DATE,");//业务创建时间
		hql.append("       to_char(jbt.UPDATED_DATE, 'yyyy-mm-dd hh24:mi:ss') UPDATED_DATE,");//业务修改时间（业务暂存时间）
		hql.append("       jbt.PROCESS_RESULT,");//业务办理结果
		hql.append("       to_char(jbt.start_time, 'yyyy-mm-dd hh24:mi:ss') start_time,");//业务申报时间
		hql.append("       to_char(jbt.complete_time, 'yyyy-mm-dd hh24:mi:ss') complete_time,");//业务办结时间
		hql.append("       jbt.item_code,");//业务事项代码
		hql.append("       jbt.BIZ_STATUS_CODE,");//业务岗状态代码
		hql.append("       jbt.BIZ_STATUS_NAME,");//业务岗状态名称
		hql.append("       jbt.PRE_BIZ_STATUS_CODE,");//业务岗前一状态代码
		hql.append("       jbt.PRE_BIZ_STATUS_NAME,");//业务岗前一状态名称
		hql.append("       jbt.AUDIT_STATUS_CODE,");//审核岗状态代码
		hql.append("       jbt.AUDIT_STATUS_NAME,");//审核岗状态名称
		hql.append("       jbt.PRE_AUDIT_STATUS_CODE,");//审核岗前一状态代码
		hql.append("       jbt.PRE_AUDIT_STATUS_NAME,");//审核岗前一状态名称
		hql.append("       jbt.PROCESS_DEPT_CODE");//在办部门代码
		hql.append("  ,    jbt.PROCESS_DEPT_NAME ");//在办部门名称
		hql.append("  ,   jpea.ADMINISTRATIVE_DUTY   ");//任职(聘任)职务名称
		hql.append("  ,   jpea.administrative_duty_level  ");//任职(聘任)职务级别YHRS0015
		hql.append("  ,   jpp.unit_oid  ");//人员所在单位OID
		hql.append("  ,   jpp.unit_name  ");//人员所在单位名称
		hql.append("  ,   jpea.M_POSITION_TYPE  ");//主岗位类别YHRS0022
		hql.append("  ,   jpea.M_POSITION_LEVEL   ");//主岗位级别YHRS0023
		hql.append("  ,   jbti.task_item_code as taskItemCode");//数据产生的当前环节
		hql.append("  ,   jbti.pre_task_item_code as preTaskItemCode");//数据数据产生的上一环节
//		hql.append("  ,   (select pre.item_code");
//		hql.append("          from YHC_BT_TASK pre");
//		hql.append("         where pre.task_oid = jbt.REF_BIZ_TASK_OID) pre_item_code");
		//hql.append("  ,   uu.unit_oid as unitoid");//主管单位id
		//hql.append("  ,   uu.unit_name as unitname");//主管单位名称
		hql.append(" , 	  jpp.ADMIN_UNIT_NAME as unitname");
		buildSQL(ttb.getCondition(), hql, hqlParams);

		hql.append(" order by UPDATED_DATE desc");

		List<Object[]> list = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
		return build(list);
	}

	private static void buildSQL(StringMap params, StringBuffer hql, HashMap<String, Object> hqlParams) throws ServiceException {
		String itemCode = params.get("itemCode");// 业务类型
//		String taskItemCode = params.get("taskItemCode");// 业务环节
		String taskItemStatus = params.get("taskItemStatus");// 待办/已办
		String startTimeStart = params.get("startTimeStart");// 业务申报时间（起）
		String startTimeEnd = params.get("startTimeEnd");// 业务申报时间（止）
		
		String name =params.get("name");//人员名称
		
//		String completeTime = params.get("completeTime");// 业务办结时间
		String createdDateStart = params.get("createdDateStart");//业务创建时间(起)
		String createdDateEnd = params.get("createdDateEnd");//业务创建时间(止)
		
		String taskOid = params.get("taskOid");// 业务OID
		
		String menuCode = params.get("menuCode"); //菜单编码
		//String authUnits =params.get("authUnits");//资源权限
		
		String isEnd =params.get("isEnd");//业务是否办结
		
		hql.append("  from YHC_BT_TASK          jbt,");
		hql.append("     YHC_BT_TASK_ITEM     jbti,");
		hql.append("     YHC_BT_TASK_ITEM     obti,");//流程当前环节
		hql.append("     YHC_PT_PERSON        jpp,");
		hql.append("     YHC_PT_PERSON_ATTACH  jpea ");//业务信息附属表，需在业务修改时同步
		//hql.append("      ,yhc_ut_unit          uu ");
		hql.append("  where jbt.task_oid = jpp.task_oid");
		hql.append("  and jpp.biz_person_oid = jpea.biz_person_oid(+)");
		hql.append("  and jbt.task_oid = jbti.task_oid ");
		
		//
		hql.append("  and jbti.task_item_oid in ");
		hql.append("      (select max(bti.task_item_oid) ");
		hql.append("         from yhc_bt_task_item bti ");
		hql.append("        where bti.task_oid = jbt.task_oid ");
		hql.append("        group by bti.task_item_code) ");
		
		hql.append("  and obti.task_item_oid = ");
		hql.append("      (select max(bti2.task_item_oid) ");
		hql.append("         from yhc_bt_task_item bti2 ");
		hql.append("        where bti2.task_oid = jbt.task_oid) ");

		/*hql.append(" and uu.unit_oid = (select distinct uu.unit_oid from yhc_ut_relation ur, yhc_ut_unit uu");
		hql.append(" where ur.parent_organization_oid = uu.organization_oid and not exists  (select 1 from yhc_ut_relation u");
		hql.append("  where u.child_organization_oid =ur.parent_organization_oid) start with uu.unit_oid = jpp.unit_oid");
		hql.append(" connect by prior ur.parent_organization_oid = ur.child_organization_oid)");*/
		 
		// 待办中存在的业务 查询已办时不显示
		if (StringUtils.isNotEmpty(taskItemStatus) && "2".equals(taskItemStatus)) {
			hql.append("  and jbti.task_oid  not in (select j.task_oid from YHC_BT_TASK_ITEM j where j.TASK_ITEM_CODE = jbti.TASK_ITEM_CODE and j.TASK_ITEM_STATUS='1' )");
		}

		// 业务类型
		if (StringUtils.isNotEmpty(itemCode)) {
			hql.append(" and jbt.item_code =:itemCode");
			hqlParams.put("itemCode", itemCode);
		}
		// 业务环节
//		if (StringUtils.isNotEmpty(taskItemCode)) {
//			hql.append(" and jbti.TASK_ITEM_CODE in ( " + taskItemCode + " )");
//		}
		if (StringUtils.isNotEmpty(taskItemStatus)) {
			// 节点状态(可用来查待办、已办事项)
			hql.append(" and jbti.TASK_ITEM_STATUS =:taskItemStatus");
			hqlParams.put("taskItemStatus", taskItemStatus);
		}
		// 业务申报时间（工作台查询参数）
		if (StringUtils.isNotEmpty(startTimeStart)) {
			hql.append(" and jbt.START_TIME >=:startTimeStart ");
			hqlParams.put("startTimeStart", DateUtil.parse(startTimeStart, DateUtil.DATE_PATTERN_DEFAULT));
		}
		// 业务申报时间（工作台查询参数）
		if (StringUtils.isNotEmpty(startTimeEnd)) {
			hql.append(" and jbt.START_TIME <:startTimeEnd ");
			hqlParams.put("startTimeEnd", DateUtil.parse(startTimeEnd, DateUtil.DATE_PATTERN_DEFAULT));
		}
		// 业务创建时间（工作台查询参数）
		if (StringUtils.isNotEmpty(createdDateStart)) {
			hql.append(" and jbt.CREATED_DATE >=:createdDateStart ");
			hqlParams.put("createdDateStart", DateUtil.parse(createdDateStart, DateUtil.DATE_PATTERN_DEFAULT));
		}
		// 业务创建时间（工作台查询参数）
		if (StringUtils.isNotEmpty(createdDateEnd)) {
			hql.append(" and jbt.CREATED_DATE <:createdDateEnd ");
			hqlParams.put("createdDateEnd", DateUtil.parse(createdDateEnd, DateUtil.DATE_PATTERN_DEFAULT));
		}
		
		if (StringUtils.isNotEmpty(menuCode))
		{
			hql.append(" and jbti.TASK_ITEM_CODE in ( select jmn.FLOW_NODE_CODE from YHD_MT_NODE jmn where jmn.MENU_CODE ='"+menuCode+"')");
		}
		
		// 业务OID
		if (StringUtils.isNotEmpty(taskOid)) {
			hql.append(" and jbt.task_oid =:taskOid");
			hqlParams.put("taskOid", taskOid);
		}
		
		if(Constant.YES.equals(params.get("isContainCell")) && StringUtils.isNotEmpty(params.get("unitOid"))){
			hql.append("and jpp.unit_oid in(");
			hql.append("select uu.unit_oid from yhc_ut_unit uu where uu.organization_oid in(");
			hql.append("select organization_oid"); 
			hql.append("  from (select u.organization_oid, ur.parent_organization_oid"); 
			hql.append("          from yhc_ut_unit u"); 
			hql.append("          left join"); 
			hql.append("          YHC_UT_RELATION ur"); 
			hql.append("            on (u.organization_oid = ur.child_organization_oid and"); 
			hql.append("               ur.relation_type = '").append(UtConstants.UT_ORGANIZATION_TYPE_1).append("')) ju"); 
			hql.append(" START WITH ju.organization_oid ="); 
			hql.append("            (select juu.organization_oid"); 
			hql.append("               from yhc_ut_unit juu"); 
			hql.append("              where juu.unit_oid = ");
			hql.append(params.get("unitOid"));
			hql.append("              )");
			hql.append("CONNECT BY ju.parent_organization_oid = PRIOR ju.organization_oid"); 
			hql.append(")");
			hql.append(")");
		}else{
			if(StringUtils.isNotEmpty(params.get("unitOid")))
			{
				hql.append("  and jpp.unit_oid =").append(params.get("unitOid"));
			}
		}
		
		//权限控制
		hql.append(" and exists (select 1 from YHB_USER_UNIT_AUTH uua where user_id = '").append(UserContext.getLoginAgentUserID()).append("' and uua.unit_oid=jpp.UNIT_OID) ");
		
		if(StringUtils.isNotEmpty(name))
		{
			hql.append("  and jpp.name like '").append(StringUtil.wrapPercent(name)).append("'");
		}
		if(StringUtils.isEmpty(params.get("unitOid"))&&StringUtils.isNotEmpty(params.get("unitName")))
		{
			hql.append("  and jpp.unit_name like'").append(StringUtil.wrapPercent(params.get("unitName"))).append("'");
		}
		if (StringUtils.isNotEmpty(isEnd)) {
			if(Constant.NO.equals(isEnd)){
				hql.append(" and jbt.complete_time is null ");
			}
			else if(Constant.YES.equals(isEnd)) {
				hql.append(" and jbt.complete_time is not null ");
			}
		}
	}

	/**
	 * 查询待办事项列表
	 * 
	 * @param list
	 * @return
	 * @throws DataAccessFailureException
	 * @throws ServiceException
	 */
	private static List<JSONObject> build(List<Object[]> list) throws DataAccessFailureException,ServiceException {
		List<JSONObject> dtoList = new ArrayList<JSONObject>();

		if (CollectionUtils.isEmpty(list)) {
			return dtoList;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] task = list.get(i);
			
			Long bizPersonOid = task[0] == null ? null : new Long(task[0].toString());
			Long taskOid = task[1] == null ? null : new Long(task[1].toString());
			String name = DataConverUtils.toString(task[2]);//人员名称
			String idNo = DataConverUtils.toString(task[3]);//证件号码
			String personStatus = DataConverUtils.toString(task[4]);//人员状态YHRS0009
			String personType = DataConverUtils.toString(task[5]);//人员类别YHRS0010
			String onTaskItemCode = DataConverUtils.toString(task[6]);// 业务当前环节（流程当前所在环节）
			String onTaskItemName = DataConverUtils.toString(task[7]);// 业务当前环节名称（流程当前所在环节）
			String onPreTaskItemCode = DataConverUtils.toString(task[8]);// 业务当前环节的前一环节（流程当前所在环节）
			String onPreTaskItemName = DataConverUtils.toString(task[9]);// 业务当前环节的前一环节名称（流程当前所在环节）
			String taskItemStatus = DataConverUtils.toString(task[10]);//当前事项环节状态(待办/已办)
			String createdDate = DataConverUtils.toString(task[11]);//业务创建时间
			String updatedDate = DataConverUtils.toString(task[12]);//业务修改时间（业务暂存时间）
			String processResult = DataConverUtils.toString(task[13]);//业务办理结果
			String startTime = DataConverUtils.toString(task[14]);//业务申报时间
			String completeTime = DataConverUtils.toString(task[15]);//业务办结时间
			String itemCode = DataConverUtils.toString(task[16]);//业务事项代码
			String bizStatusCode = DataConverUtils.toString(task[17]);//业务岗状态代码
			String bizStatusName = DataConverUtils.toString(task[18]);//业务岗状态名称
			String preBizStatusCode = DataConverUtils.toString(task[19]);//业务岗前一状态代码
			String preBizStatusName = DataConverUtils.toString(task[20]);//业务岗前一状态名称
			String auditStatusCode = DataConverUtils.toString(task[21]);//审核岗状态代码
			String auditStatusName = DataConverUtils.toString(task[22]);//审核岗状态名称
			String preAuditStatusCode = DataConverUtils.toString(task[23]);//审核岗前一状态代码
			String preAuditStatusName = DataConverUtils.toString(task[24]);//审核岗前一状态名称
			String processDeptCode = DataConverUtils.toString(task[25]);//在办部门代码
			String processDeptName = DataConverUtils.toString(task[26]);//在办部门名称
			String dutName = DataConverUtils.toString(task[27]);//任职(聘任)职务名称
			String dutyLevel = DataConverUtils.toString(task[28]);//任职(聘任)职务级别YHRS0015
			
//			String preUnitName = DataConverUtils.toString(task[29]);//原工作单位
//			String preUnitOid = DataConverUtils.toString(task[30]);
//			String postUnitName = DataConverUtils.toString(task[31]);//工作单位
//			String postUnitOid = DataConverUtils.toString(task[32]);
//			String postDeptName  = DataConverUtils.toString(task[33]);//调入单位内设机构(或部门)名称
//			String posDeptOid  = DataConverUtils.toString(task[34]);
			
			String unitOid  = DataConverUtils.toString(task[29]);
			String unitName  = DataConverUtils.toString(task[30]);//人员所在单位名称
			String mPositionType  = DataConverUtils.toString(task[31]);//主岗位类别YHRS0022
			String mPositionLevel  = DataConverUtils.toString(task[32]);//主岗位级别YHRS0023
			
			String taskItemCode  = DataConverUtils.toString(task[33]);//数据产生的当前环节
			String preTaskItemCode  = DataConverUtils.toString(task[34]);//数据数据产生的上一环节
			
			//String adminUnitOid = DataConverUtils.toString(task[35]);//人员所在主管单位名称
			String adminUnit = DataConverUtils.toString(task[35]);
			
			
			JSONObject obj = new JSONObject();
			
			obj.put("bizPersonOid",bizPersonOid );
			obj.put("taskOid", taskOid);
			obj.put("name",name );
			obj.put("idNo", idNo);
			obj.put("personStatus", personStatus);
			obj.put("personType",personType );
			obj.put("onTaskItemCode",onTaskItemCode );
			obj.put("onTaskItemName",onTaskItemName );
			obj.put("onPreTaskItemCode",onPreTaskItemCode );
			obj.put("onPreTaskItemName", onPreTaskItemName);
			obj.put("taskItemStatus",taskItemStatus );
			obj.put("createdDate",createdDate );
			obj.put("updatedDate",updatedDate );
			obj.put("processResult",processResult );
			obj.put("startTime",startTime);
			obj.put("completeTime", completeTime);
			obj.put("itemCode", itemCode);
			obj.put("bizStatusCode", bizStatusCode);
			obj.put("bizStatusName",bizStatusName );
			obj.put("preBizStatusCode", preBizStatusCode);
			obj.put("preBizStatusName",preBizStatusName );
			obj.put("auditStatusCode",auditStatusCode );
			obj.put("auditStatusName",auditStatusName );
			obj.put("preAuditStatusCode", preAuditStatusCode);
			obj.put("preAuditStatusName",preAuditStatusName );
			obj.put("processDeptCode", processDeptCode);
			obj.put("processDeptName",processDeptName );
			obj.put("dutName",dutName );
			obj.put("dutyLevel",dutyLevel );
//			obj.put("preUnitName",preUnitName );
//			obj.put("postUnitName",postUnitName );
//			obj.put("preUnitOid",preUnitOid );
//			obj.put("postUnitOid",postUnitOid );
//			obj.put("postDeptName",postDeptName );
//			obj.put("posDeptOid",posDeptOid );
			obj.put("unitOid",unitOid );
			obj.put("unitName",unitName );
			obj.put("mPositionType",mPositionType );
			obj.put("mPositionLevel",mPositionLevel );
			obj.put("taskItemCode",taskItemCode );
			obj.put("preTaskItemCode",preTaskItemCode );
			
			//obj.put("adminUnitOid",adminUnitOid );
			obj.put("adminUnit",adminUnit );
			
			dtoList.add(obj);
		}
		return dtoList;
	}
	
}
