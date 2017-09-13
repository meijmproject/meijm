package com.yh.hr.info.warning.queryhelper;




public class BizWarningForPrzProbationAffirmQueryHelper 
{
//	/**
//	 * 查询聘任制试用期到期预警人员列表
//	 * @param ttb
//	 * @return
//	 * @throws DataAccessException
//	 */
//	public static List<JSONObject> listBizWarningInfo(TableTagBean ttb) throws DataAccessFailureException
//	{
//		StringBuffer sql = new StringBuffer("select p.person_oid,p.name,p.sex_code,p.person_type,p.birthday," +
//				" bec.probation_begin,(add_months(bec.probation_begin , 6)) as startWorkDate , bw.estimate_end_date," +
//				" bw.estimate_end_days,(select u.unit_name from V_BASE_UNIT u where u.unit_oid = p.hire_unit) unit_name," +
//				" (select o.org_name from base_unit_org o where o.org_oid = p.hire_dept_Oid ) dept_name ");
//		/*sql.append(", (select order_of_all from base_unit where unit_oid = p.unit_oid) as order_of_view_u,");
//		sql.append("(select order_of_all from base_unit_org where org_oid = p.dept_oid) as order_of_view_d,");*/
//		sql.append(",(select order_of_all from base_unit	where unit_oid = p.hire_unit) as h_order_of_view_u,");
//		sql.append("(select order_of_all from base_unit_org	where org_oid = p.hire_dept_oid) as h_order_of_view_d")
//		.append(" from biz_warning_person bw,person p,base_engage_contract bec " +
//				" where p.person_oid = bw.person_oid and p.person_oid = bec.person_oid ");
//		sql.append(" and bec.status = '"+DicParameter.SZRS6001_001+"'");
//		sql.append(" and p.hire_unit in ("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")");
//		final HashMap<String, Object> sqlParams = new HashMap<String, Object>();
//		sql.append("and bw.system_id =:systemId");
//		sqlParams.put("systemId", UserContext.getSystemId());
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("itemCode")))
//		{
//			sql.append(" and bw.warning_item_code =:itemCode ");
//			sqlParams.put("itemCode", ttb.getCondition().get("itemCode"));
//		}
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("name")))
//		{
//			sql.append(" and p.name like :name");
//			sqlParams.put("name", "%"+ttb.getCondition().get("name")+"%");
//		}
//		sql.append(" order by h_order_of_view_u,h_order_of_view_d,p.person_order_view ");
//		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(), sqlParams, ttb.getPage(), ttb.getPageSize());
//		ttb.setTotal(getWarningCountByItemCodeAndSystemId(ttb.getCondition().get("itemCode"), UserContext.getSystemId()));
//		if(CollectionUtils.isEmpty(list))return null;
//		return buildJSON(list);
//	}
//	/**
//	 * 组装JSON对象
//	 * @param list
//	 * @return
//	 * @throws DataAccessException
//	 */
//	private static List<JSONObject> buildJSON(List<Object[]> list)throws DataAccessException
//	{
//		List<JSONObject> jsonList = new ArrayList<JSONObject>();
//		for(int i = 0 ; i <list.size(); i++)
//		{
//			Object [] obj = list.get(i);
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("p_personOid", obj[0]==null ? "" : obj[0].toString());
//			jsonObject.put("p_name", obj[1]==null ? "" : obj[1].toString());
//			jsonObject.put("p_sexCode", obj[2]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS0001, obj[2].toString()));
//			jsonObject.put("p_personType", obj[3]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS0014, obj[3].toString()));
//			jsonObject.put("p_birthday", obj[4]==null ? "" : obj[4].toString());
//			jsonObject.put("bec_contractBegin", obj[5]==null ? "" : obj[5].toString());
//			jsonObject.put("beoi_probationStartDate", obj[5]==null ? "" : obj[5].toString());
//			jsonObject.put("p_startWorkDate", obj[6]==null ? "" : obj[6].toString());
//			jsonObject.put("p_estimateEndDate", obj[7]==null ? "" : obj[7].toString());
//			jsonObject.put("p_estimateEndDays", obj[8]==null ? "" : obj[8].toString());
//			jsonObject.put("p_unitOid", obj[9]==null ? "" : obj[9].toString());
//			jsonObject.put("p_deptOid", obj[10]==null ? "" : obj[10].toString());
//			jsonList.add(jsonObject);
//		}
//		return jsonList;
//	}
//
//	/**
//	 * 根据系统id和事项代码查询预警人员数量
//	 * @param itemCode
//	 * @param systemId
//	 * @param authStr 权限SQL
//	 * @return
//	 * @throws DataAccessException
//	 */
//	public static int getWarningCountByItemCodeAndSystemId(String itemCode,String systemId) throws DataAccessFailureException
//	{
//		String sql = "select count(b.biz_warning_person_oid) from biz_warning_person b ,person p where p.person_oid = b.person_oid " +
//				" and p.hire_unit in("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+") " +
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
