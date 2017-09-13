package com.yh.hr.info.warning.queryhelper;


import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.util.BizWarningConstants;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BizWarningForContractExtendQueryHelper 
{
//	/**
//	 * 查询合同续聘业务预警人员数
//	 * @param ttb
//	 * @return
//	 * @throws DataAccessException
//	 */
//	public static List<JSONObject> countBizWarningInfo(TableTagBean ttb) throws ServiceException
//	{
//		StringBuffer sql = new StringBuffer(" select count(1) ");
//		sql.append(" from yhc_pb_engage_contract_info eci,yhc_pb_person_info ppi,yhc_pb_person_attach ppa,yhc_ut_org o " +
//				" where eci.person_oid = ppi.person_oid and ppi.person_oid = ppa.person_oid and ppi.hire_dept_oid = o.org_oid");
//		sql.append(" and eci.status='1' and eci.contract_type <> '2'");
//		sql.append(" and (eci.contract_end-to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd'))<=(select wci.warning_days from jhi_war_config_info wci where wci.item_code='"+ BizWarningConstants.BIZ_WARNING_YJHTDQ+"') ");
//		final HashMap<String, Object> sqlParams = new HashMap<String, Object>();
//		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(), sqlParams, ttb.getPage(), ttb.getPageSize());
//		ttb.setTotal(DaoUtil.countByCondition(sql.toString(), sqlParams));
//		if(CollectionUtils.isEmpty(list))return null;
//		return buildJSON(list);
//	}
	/**
	 * 查询合同续聘业务预警人员列表
	 * @param ttb
	 * @return
	 * @throws DataAccessException
	 */
	public static List<JSONObject> listBizWarningInfo(TableTagBean ttb) throws ServiceException
	{
		StringBuffer sql = new StringBuffer("select eci.person_oid,eci.contract_no,eci.contract_begin," +
				" eci.contract_end,(eci.contract_end-60) as warning_date,datediff(eci.contract_end,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) as differenceDays,ppi.name,ppi.id_code,ppi.id_no,ppi.sex_code,ppi.birthday,ppi.hire_dept_oid," +
				" ppi.person_type,ppi.person_code,ppa.his_position_name,o.org_name");
		sql.append(" from yhc_pb_engage_contract_info eci,yhc_pb_person_info ppi,yhc_pb_person_attach ppa,yhc_ut_org o " +
				" where eci.person_oid = ppi.person_oid and ppi.person_oid = ppa.person_oid and ppi.hire_dept_oid = o.org_oid");
		sql.append(" and eci.status='1' and eci.contract_type <> '2'");
		sql.append(" and datediff(eci.contract_end,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d'))<=(select wci.warning_days from jhi_war_config_info wci where wci.item_code='"+ BizWarningConstants.BIZ_WARNING_YJHTDQ+"') ");
		final HashMap<String, Object> sqlParams = new HashMap<String, Object>();
//		sql.append(" and bw.system_id =:systemId");
//		sqlParams.put("systemId", UserContext.getSystemId());
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("itemCode")))
//		{
//			sql.append(" and bw.warning_item_code =:itemCode ");
//			sqlParams.put("itemCode", ttb.getCondition().get("itemCode"));
//		}
		if(StringUtils.isNotEmpty(ttb.getCondition().get("warningDays")))
		{
			sql.append(" and (eci.contract_end-str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) <=:warningDays ");
			sqlParams.put("warningDays", ttb.getCondition().get("warningDays"));
		}
		if(StringUtils.isNotEmpty(ttb.getCondition().get("name")))
		{
			sql.append(" and ppi.name like :name ");
			sqlParams.put("name","%"+ttb.getCondition().get("name").trim()+"%");
		}
		sql.append("and exists (select 1 from yhb_user_org_auth juoa where juoa.org_oid = ppi.hire_dept_oid and juoa.user_id = '"+UserContext.getLoginUserID()+"') ");
		sql.append(" order by differenceDays asc ");
		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(), sqlParams, ttb.getPage(), ttb.getPageSize());
		ttb.setTotal(DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+") t", sqlParams));
		if(CollectionUtils.isEmpty(list))return null;
		return buildJSON(list);
	}
	/**
	 * 查询合同续聘业务预警人员数
	 * @param ttb
	 * @return
	 * @throws DataAccessException
	 */
	public static long[] countBizWarningInfo(TableTagBean ttb) throws ServiceException
	{
		StringBuffer sql = new StringBuffer("select eci.person_oid,eci.contract_no,eci.contract_begin," +
				" eci.contract_end,(eci.contract_end-60) as warning_date,datediff(eci.contract_end,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) as difference,ppi.name,ppi.id_code,ppi.id_no,ppi.sex_code,ppi.birthday,ppi.hire_dept_oid," +
				" ppi.person_type,ppi.person_code,ppa.his_position_name,o.org_name");
		sql.append(" from yhc_pb_engage_contract_info eci,yhc_pb_person_info ppi,yhc_pb_person_attach ppa,yhc_ut_org o " +
				" where eci.person_oid = ppi.person_oid and ppi.person_oid = ppa.person_oid and ppi.hire_dept_oid = o.org_oid");
		sql.append(" and eci.status='1' and eci.contract_type <> '2'");
		sql.append(" and datediff(eci.contract_end,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d'))<=(select wci.warning_days from jhi_war_config_info wci where wci.item_code='"+ BizWarningConstants.BIZ_WARNING_YJHTDQ+"') ");
		sql.append(" and ppi.HIRE_DEPT_OID in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("') ");
		final HashMap<String, Object> sqlParams = new HashMap<String, Object>();
//		sql.append(" and bw.system_id =:systemId");
//		sqlParams.put("systemId", UserContext.getSystemId());
		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(), sqlParams, ttb.getPage(), ttb.getPageSize());
		long[] countList = new long[4];
		countList[0]=DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+") t", sqlParams);
		countList[1]=DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+" and datediff(eci.contract_end,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) <=10 ) t", sqlParams);
		countList[2]=DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+" and datediff(eci.contract_end,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) >10 and (eci.contract_end-str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) <=30 ) t", sqlParams);
		countList[3]=DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+" and datediff(eci.contract_end,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) >30 ) t", sqlParams);
		if(CollectionUtils.isEmpty(list))return null;
		return countList;
	}
	/**
	 * 组装JSON对象
	 * @param list
	 * @return
	 * @throws DataAccessException
	 */
	private static List<JSONObject> buildJSON(List<Object[]> list)throws ServiceException
	{
		List<JSONObject> jsonList = new ArrayList<JSONObject>(); 
		for(int i = 0 ; i <list.size(); i++)
		{
			Object [] obj = list.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("personOid", obj[0]==null ? "" : obj[0].toString());
			jsonObject.put("contractNo", obj[1]==null ? "" : obj[1].toString());
			jsonObject.put("contractBegin", obj[2]==null ? "" : DateUtil.parseDate(obj[2].toString()));
			jsonObject.put("contractBeginStr", obj[2]==null ? "" : obj[2].toString());
			jsonObject.put("contractEnd", obj[3] == null ? "" : DateUtil.parseDate(obj[3].toString()));
			jsonObject.put("contractEndStr", obj[3] == null ? "" : obj[3].toString());
			jsonObject.put("warningDate", obj[4] == null ? "" : DateUtil.parseDate(obj[4].toString()));
			jsonObject.put("warningDateStr", obj[4] == null ? "" : obj[4].toString());
			jsonObject.put("difference", obj[5] == null ? "" : obj[5].toString());
			jsonObject.put("name", obj[6]==null ? "" : obj[6].toString());
			jsonObject.put("idCode", obj[7]==null ? "" : obj[7].toString());
			jsonObject.put("idNo", obj[8]==null ? "" : obj[8].toString());
			jsonObject.put("sexCode", obj[9]==null ? "" : obj[9].toString());
			jsonObject.put("sexCodeDesc", obj[9]==null ? "" : DicHelper.viewName(DicConstants.YHRS0001, obj[9].toString()));
			jsonObject.put("birthday", obj[10]==null ? "" : obj[10].toString());
			jsonObject.put("hireDeptOid", obj[11]==null ? "" : obj[11].toString());
			jsonObject.put("personType", obj[12]==null ? "" : obj[12].toString());
			jsonObject.put("personTypeDesc", obj[12]==null ? "" : DicHelper.viewName(DicConstants.YHRS0010, obj[12].toString()));
			jsonObject.put("personCode", obj[13]==null ? "" : obj[13].toString());
			jsonObject.put("hisPositionName", obj[14]==null ? "" : obj[14].toString());
			jsonObject.put("hireDeptName", obj[15]==null ? "" : obj[15].toString());
			jsonList.add(jsonObject);
		}
		return jsonList;
	}
	
//	/**
//	 * 根据系统id和事项代码查询预警人员数量
//	 * @param itemCode
//	 * @param systemId
//	 * @return
//	 * @throws DataAccessException
//	 */
//	public static int getWarningCountByItemCodeAndSystemId(String itemCode,String systemId) throws DataAccessFailureException
//	{
//		String sql = "select count(b.biz_warning_person_oid) from biz_warning_person b ,person p where p.person_oid = b.person_oid " +
//				" and p.hire_unit in("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+") " +
//				" and b.warning_item_code = '"+itemCode+"' and b.system_id = '"+systemId+"'";
//		List<BigDecimal> list = DaoUtil.findWithSQL(sql);
//		if(CollectionUtils.isEmpty(list))return 0;
//		BigDecimal bigDecimal = list.get(0);
//		if(null != bigDecimal)
//		{
//			return Integer.valueOf(bigDecimal.toString());
//		}
//		else
//		{
//			return 0;
//		}
//	}
}
