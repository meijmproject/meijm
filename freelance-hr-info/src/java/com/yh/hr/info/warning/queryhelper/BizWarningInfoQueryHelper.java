package com.yh.hr.info.warning.queryhelper;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.dto.BizWarConfigInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

import java.util.HashMap;
import java.util.List;


public class BizWarningInfoQueryHelper
{

	public static List<BizWarConfigInfoDTO> listBizWarningInfo(TableTagBean ttb) throws ServiceException {
		StringBuilder hql = new StringBuilder();
        HashMap<String, Object> hqlParams = new HashMap<String, Object>();
        buildHQL(ttb.getCondition(), hql, hqlParams);
        List<BizWarConfigInfoDTO> list = BeanHelper.copyProperties(DaoUtil.listByCondition("select mb "+hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize()),BizWarConfigInfoDTO.class);
        ttb.setTotal(DaoUtil.countByCondition("select count(*)"+hql+"", hqlParams));
        return list;
	}
	private static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) {
		hql.append(" from BizWarConfigInfo mb where 1=1 ");
	}

	public static String getBizWarningDays(String itemCode) throws ServiceException{
		StringBuffer sql = new StringBuffer("select wci.warning_days");
		sql.append(" from jhi_war_config_info wci " +
				" where wci.item_code = '"+itemCode+"'");
		System.out.println(sql);
		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(),new HashMap<String, Object>(),0,30);
		return String.valueOf(list.get(0));
	}
//	/**
//	 * 预警业务事项-记录
//	 * @param itemCode				业务事项
//	 * @return bizWarningInfo		预警事项-记录
//	 * @throws DataAccessException
//	 */
//	public static BizWarningInfo getSystemBizWarningInfo(String itemCode) throws DataAccessFailureException
//	{
//		StringBuffer hql = new StringBuffer();
//		String systemId = UserContext.getSystemId();
//		hql.append("FROM BizWarningInfo bwi WHERE bwi.itemCode =:itemCode AND bwi.systemId =:systemId");
//		List<BizWarningInfo> list = DaoUtil.findByNamedParam(hql.toString(), new String[]{"itemCode","systemId"}, new Object[]{itemCode,systemId});
//		if(CollectionUtils.isEmpty(list)) return null;
//		return list.get(0);
//	}
//	/**
//	 * 系统预警业务事项-列表
//	 * @param systemId				系统ID
//	 * @return bizWarningInfos		预警事项-集合
//	 * @throws DataAccessException
//	 */
//	public static List<BizWarningInfo> listSystemBizWarningInfos(String systemId) throws DataAccessFailureException
//	{
//		StringBuffer hql = new StringBuffer();
//		hql.append("FROM BizWarningInfo bwi WHERE bwi.systemId =:systemId AND bwi.warningFlag =:warningFlag order by bizWarningInfoOid");
//		return DaoUtil.findByNamedParam(hql.toString(), new String[]{"systemId","warningFlag"}, new Object[]{systemId,Constants.IS_TRUE});
//	}
//	/**
//	 * 组装DTO
//	 * @param list
//	 * @return
//	 */
//	private static List<BizWarningPersonDTO> buildDto(List<Object[]> list,BizWarningInfo bizWarningInfo)throws DataAccessFailureException
//	{
//		List<BizWarningPersonDTO> dtoList = new ArrayList<BizWarningPersonDTO>();
//		for(int i = 0 ; i<list.size() ;i++ )
//		{
//			Object[] obj = list.get(i);
//			BizWarningPersonDTO dto = new BizWarningPersonDTO();
//			dto.setPersonOid(obj[0]==null?null:Long.valueOf(obj[0].toString()));
//			dto.setEstimateEndDate(obj[1]==null?null:(Date)obj[1]);
//			dto.setEstimateEndDays(obj[1]==null?0:DateUtil.daysBetween(DateUtil.parseDate(DateUtil.nowString()),DateUtil.parseDate(obj[1].toString())));
//			dto.setWarningItemCode(bizWarningInfo.getItemCode());
//			dto.setSystemId(bizWarningInfo.getSystemId());
//			dtoList.add(dto);
//		}
//		return dtoList;
//	}
//	/**
//	 * 聘任制试用期到期预警-人员列表集合
//	 * @param bizWarningParameterForProbationAffirmDTO		查询DTO
//	 * @return bizWarningResultForProbationAffirmDTOs		人员列表-集合
//	 * @throws DataAccessException
//	 */
//	public static List<BizWarningPersonDTO> listBizWarningResultForEngageProbationAffirmDTOs(BizWarningInfo bizWarningInfo) throws DataAccessFailureException
//	{
//		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
//		List<BizFullProbationRule> listRule = listBizFullProbationRuleByItemCode(bizWarningInfo.getItemCode());
//
//		final StringBuffer hql = new StringBuffer();//执行SQL
//		final StringBuffer hql1 = new StringBuffer();//存放默认规则sql
//		final StringBuffer hql2 = new StringBuffer();//存放设置的预警规则
//
//		hql.append(" select * from ( ");
//
//		hql1.append(" SELECT p.person_Oid");
//		hql1.append(",(add_months(bec.probation_Begin, 6)) as startWorkDate ");
//		buildEngageProbationAffirmHQL(bizWarningInfo, hql1, hqlParams,null);
//
//		if(CollectionUtils.isNotEmpty(listRule))
//		{
//			StringBuffer sb = new StringBuffer();
//			hql2.append("SELECT p.person_Oid");
//			hql2.append(",case ");
//			for(BizFullProbationRule bo : listRule)
//			{
//				sb.append("'").append(bo.getPersonType()).append("',");
//				hql2.append(" when p.person_Type='"+bo.getPersonType()+"' then add_months(bec.probation_Begin, "+bo.getProbationDays()+")");
//			}
//			hql2.append(" end startWorkDate ");
//
//			buildEngageProbationAffirmHQL(bizWarningInfo, hql2, hqlParams,"Y");
//			String personType = "";
//			if(sb.length()>1)
//			{
//				personType = sb.delete(sb.length()-1, sb.length()).toString();
//			}
//			System.out.println(hql1.toString());
//			hql1.append(" and p.person_type not in(").append(personType).append(")").append(" union ").append(hql2);
//		}
//		hql.append(hql1);
//		hql.append(")");
//		List<Object[]> list =  DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams,0,0);
//		if(CollectionUtils.isEmpty(list)) return null;
//		return buildDto(list,bizWarningInfo);
//	}
//	/**
//	 * 聘任制试用期到期预警-语句构建
//	 * @param queryDTO	查询DTO
//	 * @param hql		SELECT语句
//	 * @param hqlParams 构造query查询参数
//	 * @param str    构建查询条件
//	 */
//	private static void buildEngageProbationAffirmHQL(BizWarningInfo bizWarningInfo, StringBuffer hql, HashMap<String, Object> hqlParams,String ruleFlag)throws DataAccessFailureException
//	{
//		hql.append(" FROM Person p, BASE_Engage_Contract bec WHERE bec.person_Oid = p.person_Oid");
//		hql.append(" AND p.person_Status ='" + DicParameter.SZRS0010_120 + "'");
//		hql.append(" AND p.appoint_Mode ='" + DicParameter.SZRS0148_2 + "'");
//		hql.append(" AND bec.probation_Begin IS NOT NULL");
//		hql.append(" and bec.status = '"+DicParameter.SZRS6001_001+"'");
//		//判断是否是执行日终任务
//		if(null != bizWarningInfo && StringUtils.isNotEmpty(bizWarningInfo.getSchedulerFlag()))
//		{
//			hql.append(" and p.hire_Unit in ("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")");
//		}
//		List<BizFullProbationRule> listRule = listBizFullProbationRuleByItemCode(bizWarningInfo.getItemCode());
//		if(CollectionUtils.isEmpty(listRule) || StringUtils.isEmpty(ruleFlag))
//		{
//			hql.append(" AND ((sysdate between (add_months(bec.probation_Begin, "+BizWarningConstants.ENGAGE_PROBATION_MONTHS+")-" +bizWarningInfo.getWarningDays()+")");
//			hql.append(" and (add_months(bec.probation_Begin, "+BizWarningConstants.ENGAGE_PROBATION_MONTHS+")+1))" +
//					" or sysdate > (add_months(bec.probation_Begin, "+BizWarningConstants.ENGAGE_PROBATION_MONTHS+")+1))");
//		}
//		else
//		{
//			hql.append(" AND (");
//
//			for(BizFullProbationRule bo : listRule)
//			{
//				hql.append(" ((sysdate between (add_months(bec.probation_Begin, decode(p.person_Type,'"+bo.getPersonType()+"',"+bo.getProbationDays()+"))-"
//						+bizWarningInfo.getWarningDays()+")");
//				hql.append(" and (add_months(bec.probation_Begin, decode(p.person_Type,'"+bo.getPersonType()+"',"+bo.getProbationDays()+"))+1))) " +
//						"or sysdate > (add_months(bec.probation_Begin, decode(p.person_Type,'"+bo.getPersonType()+"',"+bo.getProbationDays()+"))+1)");
//				hql.append("or");
//			}
//			if(hql.length() > 1)
//			{
//				hql.delete(hql.length()-2, hql.length());
//			}
//			hql.append(")");
//		}
//	}
//	/**
//	 * 合同到期预警-人员列表集合
//	 * @param bizWarningInfo
//	 * @return bizWarningResultForContractExtendDTOs		人员列表-集合
//	 * @throws ServiceException
//	 */
//	public static List<BizWarningPersonDTO> listBizWarningResultForContractExtendDTOs(BizWarningInfo bizWarningInfo) throws DataAccessFailureException
//	{
//		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
//		final StringBuffer hql = new StringBuffer();
//		hql.append("SELECT p.personOid, "+
//							 "bec.contractEnd ");
//
//		buildContractExtendHQL(bizWarningInfo, hql, hqlParams);
//		List<Object[]> list = DaoUtil.listByCondition(hql.toString(), hqlParams,0,0);
//		if(CollectionUtils.isEmpty(list)) return null;
//		return buildDto(list,bizWarningInfo);
//	}
//	/**
//	 * 合同到期预警-语句构建
//	 * @param queryDTO	查询DTO
//	 * @param hql		SELECT语句
//	 * @param hqlParams 构造query查询参数
//	 */
//	private static void buildContractExtendHQL(BizWarningInfo bizWarningInfo, StringBuffer hql, HashMap<String, Object> hqlParams)
//	{
//		hql.append(" FROM Person p, EngageContract bec WHERE bec.personOid = p.personOid");
//		hql.append(" AND p.personStatus in (" + DicParameter.getInWorkingStatus() + ")");
//		hql.append(" AND bec.contractEnd IS NOT NULL");
//		hql.append(" AND bec.contractType ='" + DicParameter.SZRS6002_001 + "'");
//		hql.append(" and bec.status = '"+DicParameter.SZRS6001_001+"'");
//		//判断是否是执行日终任务
//		if(null != bizWarningInfo && StringUtils.isNotEmpty(bizWarningInfo.getSchedulerFlag()))
//		{
//			hql.append(" and p.hireUnit in ("+BizDataAuthSearchServiceUtil.getUserUnitAuthHql(UserContext.getCurUid())+")");
//		}
//		hql.append(" and ((sysdate between (bec.contractEnd - "+bizWarningInfo.getWarningDays()+") and bec.contractEnd) " +
//				" or sysdate > bec.contractEnd)");
//	}
//	/**
//	 * 处分到期预警-人员列表集合
//	 * @param bizWarningParameterForPunishDTO		查询DTO
//	 * @return bizWarningResultForPunishDTOs		人员列表-集合
//	 * @throws ServiceException
//	 */
//	public static List<BizWarningPersonDTO> listBizWarningResultForPunishDTOs(BizWarningInfo bizWarningInfo) throws DataAccessFailureException
//	{
//		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
//		final StringBuffer hql = new StringBuffer();
//		hql.append("SELECT bpi.punishmentInfoOid, "+
//							"bpi.punishmentEndDate");
//
//		buildPunishHQL(bizWarningInfo, hql, hqlParams);
//		List<Object[]> list = DaoUtil.listByCondition(hql.toString(), hqlParams,0,0);
//		if(CollectionUtils.isEmpty(list)) return null;
//		return buildDto(list,bizWarningInfo);
//	}
//	/**
//	 * 处分到期预警-语句构建
//	 * @param queryDTO	查询DTO
//	 * @param hql		SELECT语句
//	 * @param hqlParams 构造query查询参数
//	 */
//	private static void buildPunishHQL(BizWarningInfo bizWarningInfo, StringBuffer hql, HashMap<String, Object> hqlParams)throws DataAccessFailureException
//	{
//		hql.append(" FROM Person p, PunishmentInfo bpi WHERE bpi.personOid = p.personOid");
//		hql.append(" AND p.personStatus in (" + DicParameter.getInWorkingStatus() + ")");
//		hql.append(" AND bpi.punishmentEndDate IS NOT NULL");
//		hql.append(" AND (bpi.isCancalPunishment = UPPER('N') or bpi.isCancalPunishment is null )"); // 有效标识，暂未获取常量定义
//		//判断是否是执行日终任务
//		if(null != bizWarningInfo && StringUtils.isNotEmpty(bizWarningInfo.getSchedulerFlag()))
//		{
//			hql.append(" and p.unitOid in ("+BizDataAuthSearchServiceUtil.getUserUnitAuthHql(UserContext.getCurUid())+")");
//		}
//		hql.append(" AND ((SYSDATE between (bpi.punishmentEndDate -"+bizWarningInfo.getWarningDays()+")");
//		hql.append(" AND bpi.punishmentEndDate ) or SYSDATE > bpi.punishmentEndDate)");
//	}
//	/**
//	 * 离退休预警-人员列表集合
//	 * @param bizWarningParameterForRetireDTO		查询DTO
//	 * @return bizWarningResultForRetireDTOs		人员列表-集合
//	 * @throws ServiceException
//	 */
//	public static List<BizWarningPersonDTO> listBizWarningResultForRetireDTOs(BizWarningInfo bizWarningInfo) throws DataAccessFailureException
//	{
//		List<BizOutRetireRule> ruleList = listOutRetireRuleByItemCode(bizWarningInfo.getItemCode());
//
//		final StringBuffer sql = new StringBuffer("select * from (");//执行SQL
//		final StringBuffer sql1 = new StringBuffer(" select distinct ");//存放默认规则sql
//		final StringBuffer sql2 = new StringBuffer(" select distinct ");//存放设置的预警规则
//
//		sql1.append("a.person_oid");
//		sql1.append(",add_months(a.birthday, decode(a.sex_code,'1',60*12 , '2', 55*12)) retire_date ");
//
//		buildRetireHQL(bizWarningInfo, sql1,null);
//		if(CollectionUtils.isNotEmpty(ruleList))
//		{
//			StringBuffer personType = new StringBuffer();
//			StringBuffer sexCode = new StringBuffer();
//			String personTypeStr = "";
//			String sexCodeStr = "";
//			sql2.append("a.person_oid");
//			sql2.append(",case") ;
//			for(BizOutRetireRule bo : ruleList)
//			{
//				personType.append("'").append(bo.getPersonType()).append("',");
//				sexCode.append("'").append(bo.getSexCode()).append("',");
//				sql2.append(" when a.sex_code = '"+bo.getSexCode()+"' and a.person_type='"+bo.getPersonType()+"' then add_months(a.birthday, ("+bo.getRetrieDays()+" * 12))");
//			}
//	        sql2.append(" end retire_date");
//
//	     // 构造查询条件
//	        buildRetireHQL(bizWarningInfo, sql2,"Y");
//
//			if(personType.length()>1)
//			{
//				personTypeStr = personType.delete(personType.length()-1, personType.length()).toString();
//			}
//			if(sexCode.length()>1)
//			{
//				sexCodeStr = sexCode.delete(sexCode.length()-1, sexCode.length()).toString();
//			}
//
//			sql1.append(" and a.person_oid not in ( select person_oid from person where person_type in(")
//			    .append(personTypeStr).append(") and sex_code in(").append(sexCodeStr).append("))")
//			    .append(" union ").append(sql2);
//		}
//		sql.append(sql1);
//		sql.append(")");
//		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
//		if(CollectionUtils.isEmpty(list)) return null;
//		return buildDto(list,bizWarningInfo);
//	}
//	/**
//	 * 离退休预警-语句构建（拷贝于原离退休预警逻辑代码）
//	 * @param queryDTO	查询DTO
//	 * @param hql		SELECT语句
//	 * @param hqlParams 构造query查询参数
//	 * @throws DataAccessFailureException
//	 */
//	private static void buildRetireHQL(BizWarningInfo bizWarningInfo, StringBuffer sql,String ruleFlag) throws DataAccessFailureException
//	{
//		sql.append(" from PERSON a where 1 = 1 "
//		   +" and a.person_Status in ('"+DicParameter.SZRS0010_110+"','"+DicParameter.SZRS0010_130+"','"+DicParameter.SZRS0010_208+"')");
//		sql.append(" and a.person_type in('"+DicParameter.SZRS0014_0101+"','"+DicParameter.SZRS0014_0102+"','"+DicParameter.SZRS0014_0107+"','"+DicParameter.SZRS0014_0201+"','"+DicParameter.SZRS0014_0202+"')");
//		//判断是否是执行日终任务
//		if(null != bizWarningInfo && StringUtils.isNotEmpty(bizWarningInfo.getSchedulerFlag()))
//		{
//			sql.append(" and a.unit_oid in ("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")");
//		}
//		List<BizOutRetireRule> ruleList = listOutRetireRuleByItemCode(bizWarningInfo.getItemCode());
//
//		if(CollectionUtils.isEmpty(ruleList) || StringUtils.isEmpty(ruleFlag))
//		{
//			sql.append(" and ((sysdate between (add_months(a.birthday, decode(a.sex_code,'"+DicParameter.SZRS0001_MAN+"',60*12 " +
//					", '"+DicParameter.SZRS0001_WOMAN+"', 55*12))-"+bizWarningInfo.getWarningDays() +" ) " +
//							" and (add_months(a.birthday, decode(a.sex_code,'1',60*12 , '2', 55*12))+1)) " +
//							" or sysdate > (add_months(a.birthday, decode(a.sex_code,'1',60*12 , '2', 55*12))+1))");
//		}
//		else
//		{
//			sql.append(" AND (");
//			for(BizOutRetireRule bo : ruleList)
//			{
//				sql.append(" (a.sex_code = '"+bo.getSexCode()+"' and ((sysdate between (add_months(a.birthday," +
//						"decode(a.person_type,'"+bo.getPersonType()+"',"+bo.getRetrieDays()+"*12)) - "
//						+bizWarningInfo.getWarningDays()+") and (add_months(a.birthday,decode(a.person_type,'"+bo.getPersonType()+"',"+bo.getRetrieDays()+"*12)) + 1)) " +
//								" or sysdate > (add_months(a.birthday,decode(a.person_type,'"+bo.getPersonType()+"',"+bo.getRetrieDays()+"*12)) + 1)))");
//				sql.append("or");
//
//			}
//			if(sql.length() > 1)
//			{
//				sql.delete(sql.length()-2, sql.length());
//			}
//			sql.append(")");
//		}
//
//	}
//	/**
//	 * 合同到期预警-人员列表集合
//	 * @param ttb
//	 * @return List<JSONObject>
//	 * @throws ServiceException
//	 */
//	public static List<JSONObject> listBizWarningResultForContractExtendDTOs(TableTagBean ttb) throws DataAccessFailureException
//	{
//		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
//		final StringBuffer hql = new StringBuffer();
//		hql.append("SELECT p.person_Oid, "+
//				 "p.name, "+
//				 "p.sex_Code, "+
//				 "p.person_Type, "+
//				 "(SELECT um.unit_Name FROM V_Base_Unit um WHERE um.unit_Oid=p.hire_Unit) AS unitName,"+
//				 "p.birthday,"+
//				 "bec.contract_Begin,"+
//				 "bec.contract_End," +
//				 "(select o.org_Name from Base_Unit_Org o where o.org_Oid = p.hire_Dept_Oid ) AS deptName");
//		hql.append(",bu.order_Of_All");
//		hql.append(",(select o.order_Of_All from Base_Unit_Org o where o.org_Oid = p.hire_Dept_Oid ) AS orderOfAll ");
//
//		buildContractExtendHQL(ttb, hql, hqlParams);
//		final StringBuffer countHql = new StringBuffer("select count(*) ");
//		buildContractExtendHQL(ttb, countHql, hqlParams);
//		hql.append(" order by bu.order_Of_All,orderOfAll,p.person_Order_View ");
//		List<Object[]> list = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
//		ttb.setTotal(DaoUtil.countWithSQLByCondition(countHql.toString(), hqlParams));
//		return buildJSON(list);
//	}
//
//	/**
//	 * 组装JSON对象
//	 * @param list
//	 * @return
//	 * @throws DataAccessException
//	 */
//	private static List<JSONObject> buildJSON(List<Object[]> list) throws DataAccessException
//	{
//		if(CollectionUtils.isEmpty(list))
//		{
//			return null;
//		}
//		else
//		{
//			List<JSONObject> jsonList = new ArrayList<JSONObject>();
//			for(int i = 0 ; i <list.size(); i++)
//			{
//				Object [] obj = list.get(i);
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("p_personOid", obj[0]==null ? "" : obj[0].toString());
//				jsonObject.put("p_name", obj[1]==null ? "" : obj[1].toString());
//				jsonObject.put("p_sexCode", obj[2]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS0001, obj[2].toString()));
//				jsonObject.put("p_personType", obj[3]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS0014, obj[3].toString()));
//				jsonObject.put("p_unitOid", obj[4]==null ? "" : obj[4].toString());
//				jsonObject.put("p_birthday", obj[5]==null ? "" : DateUtil.format((Date)obj[5], DateUtil.DATE_PATTERN_DEFAULT));
//				jsonObject.put("beoi_probationStartDate", obj[6]==null ? "" : DateUtil.format((Date)obj[6], DateUtil.DATE_PATTERN_DEFAULT));
//				jsonObject.put("bec_contractBegin", obj[6]==null ? "" : DateUtil.format((Date)obj[6], DateUtil.DATE_PATTERN_DEFAULT));
//				jsonObject.put("p_startWorkDate", obj[7]==null ? "" : DateUtil.format((Date)obj[7], DateUtil.DATE_PATTERN_DEFAULT));
//				jsonObject.put("p_estimateEndDate", obj[7]==null ? "" : DateUtil.format((Date)obj[7], DateUtil.DATE_PATTERN_DEFAULT));
//				jsonObject.put("p_estimateEndDays",obj[7]==null?0:DateUtil.daysBetween(DateUtil.parseDate(DateUtil.nowString()),DateUtil.parseDate(obj[7].toString())));
//				jsonObject.put("p_deptOid",obj[8]==null ? "" : obj[8].toString());
//				jsonList.add(jsonObject);
//			}
//			return jsonList;
//		}
//	}
//	/**
//	 * 合同到期预警-语句构建
//	 * @param queryDTO	查询DTO
//	 * @param hql		SELECT语句
//	 * @param hqlParams 构造query查询参数
//	 */
//	private static void buildContractExtendHQL(TableTagBean ttb, StringBuffer hql, HashMap<String, Object> hqlParams)throws DataAccessFailureException
//	{
//		hql.append(" FROM Person p, base_Engage_Contract bec,Base_Unit bu WHERE bec.person_Oid = p.person_Oid");
//		hql.append(" AND p.person_Status in (" + DicParameter.getInWorkingStatus() + ")");
//		hql.append(" AND bec.contract_End IS NOT NULL");
//		hql.append(" AND bec.contract_Type ='" + DicParameter.SZRS6002_001 + "'");
//		hql.append(" and bec.status = '"+DicParameter.SZRS6001_001+"'");
//		// 过滤单位数据权限
//		hql.append(" and p.hire_Unit in("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")");
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("warningDays")))
//		{
//			hql.append(" and ((sysdate between (bec.contract_End - "+ttb.getCondition().get("warningDays")+") and bec.contract_End) " +
//					" or sysdate > bec.contract_End)");
//		}
//		// 人员姓名
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("name")))
//		{
//			hql.append(" AND p.name like '%" +ttb.getCondition().get("name")+ "%'");
//		}
//		hql.append(" and bu.unit_Oid = p.hire_Unit");
//	}
//
//	public static List<JSONObject> listBizWarningResultForRetireDTOs(TableTagBean ttb)throws DataAccessFailureException
//	{
//		List<BizOutRetireRule> ruleList = listOutRetireRuleByItemCode(ttb.getCondition().get("itemCode"));
//		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
//		// 语句查询部分
//		StringBuffer sql = new StringBuffer("select * from ( ");
//		StringBuffer sql1 = new StringBuffer("select distinct ");
//		StringBuffer sql2 = new StringBuffer("select distinct ");
//
//		final StringBuffer countSql = new StringBuffer("select sum (aa) as bb from ( ");
//		final StringBuffer countSql1 = new StringBuffer(" select count(*) as aa ");//查询默认规则数量
//		final StringBuffer countSql2 = new StringBuffer(" select count(*) as aa ");//查询设置规则的数量
//
//		sql1.append("a.person_oid ,");
//		sql1.append("a.name,");
//		sql1.append("a.sex_Code,");
//		sql1.append("a.person_Type,");
//		sql1.append("a.administrative_duty_level,");
//		sql1.append("(select u.unit_name from V_BASE_UNIT u where u.unit_oid = a.unit_Oid and rownum <= 1) unit_name, ");
//		sql1.append("a.birthday,");
//		sql1.append("a.start_Work_Date,");
//		sql1.append("a.stop_Wage_Flag");
//		sql1.append(",add_months(a.birthday, decode(a.sex_code,'1',60*12 , '2', 55*12)) retire_date ");
//		sql1.append(",(select o.org_name from base_unit_org o where o.org_oid = a.dept_Oid ) dept_name");
//		sql1.append(", (select order_of_all from base_unit where unit_oid = a.unit_oid) order_of_view_u,");
//		sql1.append("(select order_of_all from base_unit_org where org_oid = a.dept_oid) order_of_view_d ");
//		sql1.append(",person_Order_View");
//
//		// 构造查询条件
//		buildRetireHQL(ttb, sql1,null);
//		// 构造查询条件
//		buildRetireHQL(ttb, countSql1,null);
//
//		if(CollectionUtils.isNotEmpty(ruleList))
//		{
//			StringBuffer personType = new StringBuffer();
//			StringBuffer sexCode = new StringBuffer();
//			String personTypeStr = "";
//			String sexCodeStr = "";
//			sql2.append("a.person_oid ,");
//			sql2.append("a.name,");
//			sql2.append("a.sex_Code,");
//			sql2.append("a.person_Type,");
//			sql2.append("a.administrative_duty_level,");
//			sql2.append("(select u.unit_name from V_BASE_UNIT u where u.unit_oid = a.unit_Oid and rownum <= 1) unit_name, ");
//			sql2.append("a.birthday,");
//			sql2.append("a.start_Work_Date,");
//			sql2.append("a.stop_Wage_Flag");
//			sql2.append(",case") ;
//			for(BizOutRetireRule bo : ruleList)
//			{
//				personType.append("'").append(bo.getPersonType()).append("',");
//				sexCode.append("'").append(bo.getSexCode()).append("',");
//				sql2.append(" when a.sex_code = '"+bo.getSexCode()+"' and a.person_type='"+bo.getPersonType()+"' then add_months(a.birthday, ("+bo.getRetrieDays()+" * 12))");
//			}
//	        sql2.append(" end retire_date");
//	        sql2.append(",(select o.org_name from base_unit_org o where o.org_oid = a.dept_Oid ) dept_name");
//			sql2.append(", (select order_of_all from base_unit where unit_oid = a.unit_oid) order_of_view_u,");
//			sql2.append("(select order_of_all from base_unit_org where org_oid = a.dept_oid) order_of_view_d ");
//			sql2.append(",person_Order_View");
//
//			// 构造查询条件
//			buildRetireHQL(ttb, sql2,"Y");
//			buildRetireHQL(ttb, countSql2,"Y");
//
//			if(personType.length()>1)
//			{
//				personTypeStr = personType.delete(personType.length()-1, personType.length()).toString();
//			}
//			if(sexCode.length()>1)
//			{
//				sexCodeStr = sexCode.delete(sexCode.length()-1, sexCode.length()).toString();
//			}
//
//			sql1.append(" and a.person_oid not in ( select person_oid from person where person_type in(")
//			    .append(personTypeStr).append(") and sex_code in(").append(sexCodeStr).append("))")
//			    .append(" union ").append(sql2);
//
//			countSql1.append(" and a.person_oid not in ( select person_oid from person where person_type in(")
//		    .append(personTypeStr).append(") and sex_code in(").append(sexCodeStr).append("))")
//		    .append(" union ").append(countSql2);
//		}
//		sql.append(sql1);
//		sql.append(")");
//		sql.append(" order by order_of_view_u,order_of_view_d,person_Order_View");
//		countSql.append(countSql1).append(")");
//
//		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
//		ttb.setTotal(DaoUtil.countWithSQLByCondition(countSql.toString(), hqlParams));
//		if(CollectionUtils.isEmpty(list)) return null;
//		return buildRetireJSON(list);
//	}
//
//	/**
//	 * 组装JSON对象
//	 * @param list
//	 * @return
//	 * @throws DataAccessException
//	 */
//	private static List<JSONObject> buildRetireJSON(List<Object[]> list)throws DataAccessFailureException
//	{
//		List<JSONObject> jsonList = new ArrayList<JSONObject>();
//		for(int i = 0 ; i <list.size(); i++)
//		{
//			Object [] obj = list.get(i);
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("person_oid", obj[0]==null ? "" : obj[0].toString());
//			jsonObject.put("name", obj[1]==null ? "" : obj[1].toString());
//			jsonObject.put("sex_code", obj[2]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS0001, obj[2].toString()));
//			jsonObject.put("person_type", obj[3]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS0014, obj[3].toString()));
//			jsonObject.put("administrative_duty_level", obj[4]==null ? "" : DicParameter.getDicItemNameByDicTypeOidAndDicItemCode(DicParameter.SZRS_S0021,obj[4].toString()));
//			jsonObject.put("unit_name", obj[5]==null ? "" : obj[5].toString());
//			jsonObject.put("birthday", obj[6]==null ? "" : obj[6].toString());
//			jsonObject.put("start_work_date", obj[7]==null ? "" : obj[7].toString());
//			jsonObject.put("stop_wage_flag",obj[8]==null ? "" : obj[8].toString().equals("Y")?"是":"否");
//			jsonObject.put("p_estimateEndDate", obj[9]==null ? "" : obj[9].toString());
//			jsonObject.put("p_estimateEndDays",obj[9]==null?0:DateUtil.daysBetween(DateUtil.parseDate(DateUtil.nowString()),DateUtil.parseDate(obj[9].toString())));
//			jsonObject.put("dept_name",obj[10]==null ? "" : obj[10].toString());
//			jsonList.add(jsonObject);
//		}
//		return jsonList;
//	}
//	/**
//	 * 离退休预警-语句构建（拷贝于原离退休预警逻辑代码）
//	 * @param queryDTO	查询DTO
//	 * @param hql		SELECT语句
//	 * @param hqlParams 构造query查询参数
//	 */
//	private static void buildRetireHQL(TableTagBean ttb, StringBuffer sql,String ruleFlag)throws DataAccessFailureException
//	{
//		sql.append(" from PERSON a  where a.person_Status in ('"+DicParameter.SZRS0010_110+"','"+DicParameter.SZRS0010_130+"','"+DicParameter.SZRS0010_208+"')");
//		sql.append(" and a.person_type in('"+DicParameter.SZRS0014_0101+"','"+DicParameter.SZRS0014_0102+"','"+DicParameter.SZRS0014_0107+"','"+DicParameter.SZRS0014_0201+"','"+DicParameter.SZRS0014_0202+"')");
//		sql.append(" and a.unit_oid in ("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")");
//		List<BizOutRetireRule> ruleList = listOutRetireRuleByItemCode(ttb.getCondition().get("itemCode"));
//
//		if(CollectionUtils.isEmpty(ruleList) || StringUtils.isEmpty(ruleFlag))
//		{
//			sql.append(" and ((sysdate between (add_months(a.birthday, decode(a.sex_code,'"+DicParameter.SZRS0001_MAN+"',60*12 , '"+DicParameter.SZRS0001_WOMAN+"', 55*12))-"+ttb.getCondition().get("warningDays") +" ) and (add_months(a.birthday, decode(a.sex_code,'1',60*12 , '2', 55*12))+1)) " +
//					" or sysdate > (add_months(a.birthday, decode(a.sex_code,'1',60*12 , '2', 55*12))+1))");
//		}
//		else
//		{
//			sql.append(" AND (");
//			for(BizOutRetireRule bo : ruleList)
//			{
//				sql.append(" (a.sex_code = '"+bo.getSexCode()+"' and ((sysdate between (add_months(a.birthday," +
//						"decode(a.person_type,'"+bo.getPersonType()+"',"+bo.getRetrieDays()+"*12)) - "
//						+ttb.getCondition().get("warningDays")+") and (add_months(a.birthday,decode(a.person_type,'"+bo.getPersonType()+"',"+bo.getRetrieDays()+"*12)) + 1)) or sysdate > (add_months(a.birthday,decode(a.person_type,'"+bo.getPersonType()+"',"+bo.getRetrieDays()+"*12)) + 1)))");
//				sql.append("or");
//
//			}
//			if(sql.length() > 1)
//			{
//				sql.delete(sql.length()-2, sql.length());
//			}
//			sql.append(")");
//		}
//
//		// 过滤人员姓名
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("name")))
//		{
//			sql.append(" and a.name like '%" + ttb.getCondition().get("name") + "%'");
//		}
//	}
//	/**
//	 * 聘任制试用期到期预警
//	 */
//	public static List<JSONObject> listBizWarningResultForEngageProbationAffirmDTOs(TableTagBean ttb) throws DataAccessFailureException
//	{
//		List<BizFullProbationRule> ruleList = listBizFullProbationRuleByItemCode(ttb.getCondition().get("itemCode"));
//
//		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
//		final StringBuffer hql = new StringBuffer();//执行SQL
//		final StringBuffer hql1 = new StringBuffer();//存放默认规则sql
//		final StringBuffer hql2 = new StringBuffer();//存放设置的预警规则
//
//		final StringBuffer countHql = new StringBuffer("select sum (aa) as bb from ( ");
//		final StringBuffer countHql1 = new StringBuffer(" select count(*) as aa ");//查询默认规则数量
//		final StringBuffer countHql2 = new StringBuffer(" select count(*) as aa ");//查询设置规则的数量
//
//		hql.append("select * from (");
//
//
//		hql1.append("SELECT p.person_Oid, "+
//				 "p.name, "+
//				 "p.sex_Code, "+
//				 "p.person_Type, "+
//				 "(SELECT um.unit_Name FROM V_Base_Unit um WHERE um.unit_Oid=p.hire_unit) AS unitName,"+
//				 "p.birthday,"+
//				 "bec.probation_Begin,(add_months(bec.probation_Begin, 6)) as startWorkDate");
//		hql1.append(",(select o.org_name from base_unit_org o where o.org_oid = p.hire_dept_Oid ) dept_name");
//		hql1.append(",(select order_of_all from base_unit where unit_oid = p.hire_unit) as h_order_of_view_u,");
//		hql1.append("(select order_of_all from base_unit_org	where org_oid = p.hire_dept_oid) as h_order_of_view_d");
//		hql1.append(",p.person_Order_View");
//
//		buildEngageProbationAffirmHQL(ttb, hql1, hqlParams,null);
//		buildEngageProbationAffirmHQL(ttb, countHql1, hqlParams,null);
//
//		if(CollectionUtils.isNotEmpty(ruleList))
//		{
//			StringBuffer sb = new StringBuffer();
//			hql2.append("SELECT p.person_Oid, "+
//					 "p.name, "+
//					 "p.sex_Code, "+
//					 "p.person_Type, "+
//					 "(SELECT um.unit_Name FROM V_Base_Unit um WHERE um.unit_Oid=p.hire_unit) AS unitName,"+
//					 "p.birthday,"+
//					 "bec.probation_Begin");
//			hql2.append(",case ");
//			for(BizFullProbationRule bo : ruleList)
//			{
//				sb.append("'").append(bo.getPersonType()).append("',");
//				hql2.append(" when p.person_Type='"+bo.getPersonType()+"' then add_months(bec.probation_Begin, "+bo.getProbationDays()+")");
//			}
//			hql2.append(" end startWorkDate ");
//			hql2.append(",(select o.org_name from base_unit_org o where o.org_oid = p.hire_dept_Oid ) dept_name");
//			hql2.append(",(select order_of_all from base_unit where unit_oid = p.hire_unit) as h_order_of_view_u,");
//			hql2.append("(select order_of_all from base_unit_org	where org_oid = p.hire_dept_oid) as h_order_of_view_d");
//			hql2.append(",p.person_Order_View");
//
//			buildEngageProbationAffirmHQL(ttb, hql2, hqlParams,"Y");
//			buildEngageProbationAffirmHQL(ttb, countHql2, hqlParams,"Y");
//			String personType = "";
//			if(sb.length()>1)
//			{
//				personType = sb.delete(sb.length()-1, sb.length()).toString();
//			}
//			hql1.append(" and p.person_type not in(").append(personType).append(")").append(" union ").append(hql2);
//
//			countHql1.append(" and p.person_type not in(").append(personType).append(")").append(" union ").append(countHql2);
//
//		}
//		hql.append(hql1);
//		hql.append(")");
//		hql.append(" order by h_order_of_view_u,h_order_of_view_d,person_Order_View");
//		countHql.append(countHql1).append(")");
//		List<Object[]> list = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
//		ttb.setTotal(DaoUtil.countWithSQLByCondition(countHql.toString(), hqlParams));
//		if(CollectionUtils.isEmpty(list)) return null;
//		return buildJSON(list);
//	}
//
//
//
//	/**
//	 * 聘任制试用期到期预警-语句构建
//	 * @param queryDTO	查询DTO
//	 * @param hql		SELECT语句
//	 * @param hqlParams 构造query查询参数
//	 */
//	private static void buildEngageProbationAffirmHQL(TableTagBean ttb, StringBuffer hql, HashMap<String, Object> hqlParams,String ruleFlag)throws DataAccessFailureException
//	{
//		hql.append(" FROM Person p, BASE_Engage_Contract bec WHERE bec.person_Oid = p.person_Oid");
//		hql.append(" AND p.person_Status ='" + DicParameter.SZRS0010_120 + "'");
//		hql.append(" AND p.appoint_Mode ='" + DicParameter.SZRS0148_2 + "'");
//		hql.append(" AND bec.probation_Begin IS NOT NULL");
//		hql.append(" and bec.status = '"+DicParameter.SZRS6001_001+"'");
//		hql.append(" and p.hire_Unit in("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")");
//		List<BizFullProbationRule> ruleList = listBizFullProbationRuleByItemCode(ttb.getCondition().get("itemCode"));
//
//		if(CollectionUtils.isEmpty(ruleList) || StringUtils.isEmpty(ruleFlag))
//		{
//			if(StringUtils.isNotEmpty(ttb.getCondition().get("warningDays")))
//			{
//				hql.append(" AND ((sysdate between (add_months(bec.probation_Begin, "+BizWarningConstants.ENGAGE_PROBATION_MONTHS+")-"
//						+ttb.getCondition().get("warningDays")+")");
//			}
//			hql.append(" and (add_months(bec.probation_Begin, "+BizWarningConstants.ENGAGE_PROBATION_MONTHS+")+1)) " +
//					"or sysdate > (add_months(bec.probation_Begin, "+BizWarningConstants.ENGAGE_PROBATION_MONTHS+")+1))");
//		}
//		else
//		{
//			hql.append(" AND (");
//			for(BizFullProbationRule bo : ruleList)
//			{
//				if(StringUtils.isNotEmpty(ttb.getCondition().get("warningDays")))
//				{
//					hql.append(" ((sysdate between (add_months(bec.probation_Begin, decode(p.person_Type,'"+bo.getPersonType()+"',"+bo.getProbationDays()+"))-"
//							+ttb.getCondition().get("warningDays")+")");
//				}
//				hql.append(" and (add_months(bec.probation_Begin, decode(p.person_Type,'"+bo.getPersonType()+"',"+bo.getProbationDays()+"))+1)) " +
//						" or sysdate > (add_months(bec.probation_Begin, decode(p.person_Type,'"+bo.getPersonType()+"',"+bo.getProbationDays()+"))+1))");
//				hql.append("or");
//			}
//			if(hql.length() > 1)
//			{
//				hql.delete(hql.length()-2, hql.length());
//			}
//			hql.append(")");
//		}
//		// 人员姓名
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("name")))
//		{
//			hql.append(" AND p.name like :name");
//			hqlParams.put("name", "%" +ttb.getCondition().get("name")+ "%");
//		}
//	}
//
//	public static List<JSONObject> listBizWarningResultForPunishDTOs(TableTagBean ttb)throws DataAccessFailureException
//	{
//		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
//		final StringBuffer hql = new StringBuffer();
//		hql.append("SELECT bpi.PUNISHMENT_INFO_OID, "+
//					        "p.name, "+
//							"p.sex_Code, "+
//							"p.person_Type, "+
//						    "(SELECT um.unit_Name FROM V_Base_Unit um WHERE um.unit_Oid=p.unit_Oid) AS unitName,"+
//						    "p.birthday,"+
//						    "bpi.punishment_Date,"+
//							"bpi.punishment_End_Date," +
//							"(select o.org_Name from Base_Unit_Org o where o.org_Oid = p.dept_Oid ) AS deptName");
//
//		hql.append(",bu.order_Of_All,(select bo.order_Of_All from Base_Unit_Org bo where bo.org_Oid = p.dept_Oid) AS orderOfAll ");
//		buildPunishHQL(ttb, hql, hqlParams);
//		final StringBuffer countHql = new StringBuffer("select count(*) ");
//		buildPunishHQL(ttb, countHql, hqlParams);
//		hql.append(" order by bu.order_Of_All,orderOfAll,p.person_Order_View");
//		List<Object[]> list = DaoUtil.listWithSQLByCondition(hql.toString(), hqlParams,ttb.getPage(),ttb.getPageSize());
//		ttb.setTotal(DaoUtil.countWithSQLByCondition(countHql.toString(), hqlParams));
//		if(CollectionUtils.isEmpty(list)) return null;
//		return buildJSON(list);
//	}
//	/**
//	 * 处分到期预警-语句构建
//	 * @param queryDTO	查询DTO
//	 * @param hql		SELECT语句
//	 * @param hqlParams 构造query查询参数
//	 */
//	private static void buildPunishHQL(TableTagBean ttb, StringBuffer hql, HashMap<String, Object> hqlParams)throws DataAccessFailureException
//	{
//		hql.append(" FROM Person p, BASE_PUNISHMENT_INFO bpi,Base_Unit bu WHERE bpi.person_Oid = p.person_Oid");
//		hql.append(" AND p.person_Status in (" + DicParameter.getInWorkingStatus() + ")");
//		hql.append(" AND bpi.punishment_End_Date IS NOT NULL");
//		hql.append(" AND (bpi.is_Cancal_Punishment = UPPER('N') or bpi.is_Cancal_Punishment is null )"); // 有效标识，暂未获取常量定义
//		hql.append(" and p.unit_Oid in("+BizDataAuthSearchServiceUtil.getUserUnitAuthSql(UserContext.getCurUid())+")");
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("warningDays")))
//		{
//			hql.append(" AND ((SYSDATE between (bpi.punishment_End_Date -"+ttb.getCondition().get("warningDays")+")");
//		}
//		hql.append(" AND bpi.punishment_End_Date ) or SYSDATE > bpi.punishment_End_Date)");
//		// 人员姓名
//		if(StringUtils.isNotEmpty(ttb.getCondition().get("name")))
//		{
//			hql.append(" AND p.name like :name");
//			hqlParams.put("name", "%" +ttb.getCondition().get("name")+ "%");
//		}
//		hql.append(" and bu.unit_Oid = p.unit_Oid ");
//	}
//
//	/**
//	 * 根据预警事项代码查询聘任制试用期预警规则
//	 * @param itemCode
//	 * @return
//	 * @throws DataAccessException
//	 */
//	private static List<BizFullProbationRule> listBizFullProbationRuleByItemCode(String itemCode) throws DataAccessFailureException
//	{
//		return DaoUtil.findByNamedParam("from BizFullProbationRule where itemCode=:itemCode", "itemCode", itemCode);
//	}
//
//	/**
//	 * 根据预警事项代码查询离退休预警规则
//	 * @param itemCode
//	 * @return
//	 * @throws DataAccessException
//	 */
//	private static List<BizOutRetireRule> listOutRetireRuleByItemCode(String itemCode)throws DataAccessFailureException
//	{
//		return DaoUtil.findByNamedParam("from BizOutRetireRule where itemCode=:itemCode","itemCode",itemCode);
//	}
//
//	/**
//	 * 查询所有预警信息
//	 * @return
//	 * @throws DataAccessFailureException
//	 */
//	public static List<BizWarningInfo> list() throws DataAccessFailureException
//	{
//		return DaoUtil.find("from BizWarningInfo");
//	}
}
