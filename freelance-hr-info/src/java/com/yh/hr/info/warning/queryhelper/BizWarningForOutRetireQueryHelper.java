package com.yh.hr.info.warning.queryhelper;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.util.BizWarningConstants;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BizWarningForOutRetireQueryHelper
{

	/**
	 * 查询提前退休业务预警人员列表
	 * @param ttb
	 * @return
	 * @throws ServiceException 
	 * @throws DataAccessException
	 */
	public static List<JSONObject> listBizWarningInfo(TableTagBean ttb) throws ServiceException
	{
		StringBuffer sql = new StringBuffer("select ppi.person_oid," +
				" datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month) ,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) as differenceDays," +
				" ppi.name,ppi.id_code, ppi.id_no,ppi.sex_code,ppi.birthday,ppi.hire_dept_oid,ppi.person_type,ppi.person_code,ppa.his_position_name, o.org_name,DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month) as retireTime");
		sql.append(" from yhc_pb_person_info ppi,yhc_pb_person_attach ppa,yhc_ut_org o " +
				" where  ppi.person_oid = ppa.person_oid and ppi.hire_dept_oid = o.org_oid");
		sql.append("  and datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month),str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) <="+
			 "(select wci.warning_days from jhi_war_config_info wci where wci.item_code = '"+ BizWarningConstants.BIZ_WARNING_YJLTX+"') ");
		final HashMap<String, Object> sqlParams = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(ttb.getCondition().get("warningDays")))
		{
			sql.append(" and datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month),str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) <=:warningDays ");
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
		ttb.setTotal(DaoUtil.countWithSQLByCondition("select count(*) from(" + sql + ") t", sqlParams));
		if(CollectionUtils.isEmpty(list))return null;
		return buildJSON(list);
	}
	/**
	 * 查询提前退休业务预警人员数
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 * @throws DataAccessException
	 */
	public static long[] countBizWarningInfo(TableTagBean ttb) throws ServiceException
	{
		StringBuffer sql = new StringBuffer("select ppi.person_oid," +
				" datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month) ,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) as difference," +
				" ppi.name,ppi.id_code, ppi.id_no,ppi.sex_code,ppi.birthday,ppi.hire_dept_oid,ppi.person_type,ppi.person_code,ppa.his_position_name, o.org_name,DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month) as retireTime");
		sql.append(" from yhc_pb_person_info ppi,yhc_pb_person_attach ppa,yhc_ut_org o " +
				" where  ppi.person_oid = ppa.person_oid and ppi.hire_dept_oid = o.org_oid");
		sql.append("  and datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month),str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) <="+
			 "(select wci.warning_days from jhi_war_config_info wci where wci.item_code = '"+ BizWarningConstants.BIZ_WARNING_YJLTX+"') ");
		sql.append(" and ppi.HIRE_DEPT_OID in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("') ");
		final HashMap<String, Object> sqlParams = new HashMap<String, Object>();
		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(), sqlParams, ttb.getPage(), ttb.getPageSize());
		long[] countList = new long[4];
		countList[0]=DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+") t", sqlParams);
		countList[1]=DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+" and datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month) ,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) <=10 ) t", sqlParams);
		countList[2]=DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+" and datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month) ,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) >10 and datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month) ,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) <=30 ) t", sqlParams);
		countList[3]=DaoUtil.countWithSQLByCondition("select count(*) from(" + sql+" and datediff(DATE_ADD(ppi.birthday, interval case ppi.sex_code when '1' then 60*12 when '2' then 55*12 end month) ,str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d')) >30 ) t", sqlParams);
		if(CollectionUtils.isEmpty(list))return null;
		return countList;
	}
	/**
	 * 组装JSON对象
	 * @param list
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceException 
	 */
	private static List<JSONObject> buildJSON(List<Object[]> list) throws  ServiceException
	{
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for(int i = 0 ; i <list.size(); i++)
		{
			Object [] obj = list.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("personOid", obj[0]==null ? "" :Long.valueOf(obj[0].toString()));
			jsonObject.put("difference", obj[1]==null ? "" : obj[1].toString());
			jsonObject.put("name", obj[2]==null ? "" : obj[2].toString());
			jsonObject.put("idCode", obj[3]==null ? "" : obj[3].toString());
			jsonObject.put("idNo", obj[4]==null ? "" : obj[4].toString());
			jsonObject.put("sexCode", obj[5]==null ? "" : obj[5].toString());
			jsonObject.put("sexCodeDesc", obj[5]==null ? "" : DicHelper.viewName(DicConstants.YHRS0001, obj[5].toString()));
			jsonObject.put("birthday", obj[6]==null ? "" : obj[6].toString());
			jsonObject.put("hireDeptOid", obj[7]==null ? "" : obj[7].toString());
			jsonObject.put("personType", obj[8]==null ? "" : obj[8].toString());
			jsonObject.put("personTypeDesc", obj[8]==null ? "" : DicHelper.viewName(DicConstants.YHRS0010, obj[8].toString()));
			jsonObject.put("personCode", obj[9]==null ? "" : obj[9].toString());
			jsonObject.put("hisPositionName", obj[10]==null ? "" : obj[10].toString());
			jsonObject.put("hireDeptName", obj[11]==null ? "" : obj[11].toString());
			jsonObject.put("retireTime", obj[12] == null ? "" : obj[12].toString());
			jsonList.add(jsonObject);
		}
		return jsonList;
	}
//
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
//				" and p.unit_oid in("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")" +
//						" and b.warning_item_code = '"+itemCode+"' and b.system_id = '"+systemId+"'";
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
