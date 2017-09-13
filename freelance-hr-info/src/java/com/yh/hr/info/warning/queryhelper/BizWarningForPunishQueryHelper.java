package com.yh.hr.info.warning.queryhelper;


public class BizWarningForPunishQueryHelper
{
//	/**
//	 * 查询惩处业务预警人员列表
//	 * @param ttb
//	 * @return
//	 * @throws DataAccessException
//	 */
//	public static List<JSONObject> listBizWarningInfo(TableTagBean ttb) throws DataAccessFailureException
//	{
//		StringBuffer sql = new StringBuffer("select p.person_oid,p.name,p.sex_code," +
//				" p.person_type,p.birthday,bpi.punishment_date,bpi.punishment_end_date, bw.estimate_end_date," +
//				" bw.estimate_end_days,(select u.unit_name from V_BASE_UNIT u where u.unit_oid = p.unit_Oid ) unit_name ," +
//				" (select o.org_name from base_unit_org o where o.org_oid = p.dept_Oid ) dept_name, bpi.punishment_info_oid");
//		sql.append(", (select order_of_all from base_unit where unit_oid = p.unit_oid) as order_of_view_u,");
//		sql.append("(select order_of_all from base_unit_org where org_oid = p.dept_oid) as order_of_view_d")
//		.append(" from biz_warning_person bw,person p, base_Punishment_Info bpi" +
//				" where p.person_oid = bpi.person_oid and bpi.punishment_info_oid = bw.person_oid ");
//		sql.append(" and p.unit_oid in ("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")");
//		final HashMap<String, Object> sqlParams = new HashMap<String, Object>();
//		sql.append(" and bw.system_id =:systemId");
//		sqlParams.put("systemId", UserContext.getSystemId());
//
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
//		sql.append(" order by order_of_view_u,order_of_view_d,p.person_order_view ");
//		List<Object[]> list =  DaoUtil.listWithSQLByCondition(sql.toString(), sqlParams, ttb.getPage(), ttb.getPageSize());
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
//			String personOid = obj[0]==null ? "" : obj[0].toString();
//			String punishmentInfoOid = obj[11]==null ? "" : obj[11].toString();
//			jsonObject.put("p_personOid", personOid+punishmentInfoOid);
//			jsonObject.put("p_name", obj[1]==null ? "" : obj[1].toString());
//			jsonObject.put("p_sexCode", obj[2]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS0001, obj[2].toString()));
//			jsonObject.put("p_personType", obj[3]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS0014, obj[3].toString()));
//			jsonObject.put("p_birthday", obj[4]==null ? "" : obj[4].toString());
//			jsonObject.put("bec_contractBegin", obj[5]==null ? "" : obj[5].toString());
//			jsonObject.put("bpi_endPunishmentDateActual", obj[6]==null ? "" : obj[6].toString());
//			jsonObject.put("p_estimateEndDate", obj[7]==null ? "" : obj[7].toString());
//			jsonObject.put("p_estimateEndDays", obj[8]==null ? "" : obj[8].toString());
//			jsonObject.put("p_unitOid", obj[9]==null ? "" : obj[9].toString());
//			jsonObject.put("p_deptOid", obj[10]==null ? "" : obj[10].toString());
//			jsonList.add(jsonObject);
//		}
//		return jsonList;
//	}
//	/**
//	 * 根据系统id和事项代码查询预警人员数量
//	 * @param itemCode
//	 * @param systemId
//	 * @return
//	 * @throws DataAccessException
//	 */
//	public static int getWarningCountByItemCodeAndSystemId(String itemCode,String systemId) throws DataAccessFailureException
//	{
//		//因为设计时没考虑到多条惩处信息的情况，所以现在将预警人员表中的person_oid存惩处信息的Oid用来确定具体的惩处信息
//		String sql = "select count(b.biz_warning_person_oid) from biz_warning_person b ,person p,base_Punishment_Info bpi where p.person_oid = bpi.person_oid and bpi.punishment_info_oid = b.person_oid " +
//				" and p.unit_oid in("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+") " +
//						"and b.warning_item_code = '"+itemCode+"' and b.system_id = '"+systemId+"'";
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
