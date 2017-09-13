package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.bo.PtReviewDetailInfo;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.dto.PtReviewDetailInfoDTO;
import com.yh.hr.res.unit.bo.UtUnit;
import com.yh.hr.res.unit.queryhelper.UtUnitQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;

/**
 * 年度考核结果 详细信息 查询
 * @author zhengdr
 *
 * 时间:2017-1-4上午09:55:42
 */
public class PtReviewDetailInfoQueryHelper {
	
	/**
	 * 查询
	 */	
	public static PtReviewDetailInfoDTO get(Long reviewDetailInfoOid) throws ServiceException{
		//查询数据
		PtReviewDetailInfo ptReviewDetailInfo = DaoUtil.get(PtReviewDetailInfo.class, reviewDetailInfoOid);
		//po转换为dto
		PtReviewDetailInfoDTO serdto = new PtReviewDetailInfoDTO();
		return BeanHelper.copyRtnProperties(ptReviewDetailInfo, serdto);
		
	}

	/**
	 * 删除
	 */
	public static void delete(Long reviewDetailInfoOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtReviewDetailInfo rebi where rebi.reviewDetailInfoOid = " + reviewDetailInfoOid);
	}
	
	/**
	 * 得到业务 人员列表
	 * @param reviewBizInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtReviewDetailInfo> listBoByReviewBizInfoOid(Long reviewBizInfoOid)throws ServiceException {
		
		StringBuffer hql = new StringBuffer();
		hql.append(" from PtReviewDetailInfo ptdi where ptdi.reviewBizInfoOid=").append(reviewBizInfoOid);
		
		return DaoUtil.find(hql.toString());
	}

	/**
	 * 根据条件得到列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public static List<JSONObject> listPtReviewDetailInfo(TableTagBean ttb) throws ServiceException {
		
		//hql语句
		final StringBuffer hql =  new StringBuffer();
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		
		hql.append(" select ");
		hql.append("   jprdi.REVIEW_DETAIL_INFO_OID ");//主键
		hql.append(" , jppi.NAME ");//人员名称
		hql.append(" , jppi.UNIT_OID ");//单位oid
		hql.append(" , juu.UNIT_NAME ");//单位名称
		hql.append(" , jprdi.DUTY_LEVEL_CODE ");//职务级别
		hql.append(" , jprdi.DUTY_ATTRIBUTE ");//职务属性
		hql.append(" , jprdi.REVIEW_RESULT_TYPE ");//考核结论类别
		hql.append(" , jprdi.REVIEW_TYPE_DESC ");//考核结论类别描述
		hql.append(" , jprdi.POSITION_TYPE ");//岗位类别YHRS0022
		hql.append(" , jprdi.POSITION_LEVEL ");//岗位级别YHRS0023
		hql.append(" , jprdi.POSITION_NAME ");//岗位名称
		
		
		hql.append(" from ");
		hql.append(" YHC_PB_PERSON_INFO jppi ");//人员信息表
		hql.append(",YHC_PT_REVIEW_DETAIL_INFO jprdi  ");//年度考核信息表
		hql.append(", yhc_ut_unit juu ");//单位信息表
		
		hql.append(" where jppi.person_oid=jprdi.person_oid ");
		hql.append(" and jppi.unit_oid=juu.unit_oid ");
		
		buildSQL(ttb.getCondition(), hql, hqlParams);
		
		hql.append(" order by jppi.unit_oid,jprdi.DUTY_LEVEL_CODE,jppi.person_oid");
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition((new StringBuilder().append("select count(*) from (").append(hql).append(")").toString()), hqlParams));
		}
		
		List<Object[]> list =DaoUtil.listWithSQLByCondition(hql.toString(), 				
 				hqlParams, ttb.getPage(), ttb.getPageSize());
		
		return build(list);
	}
	
	/**
	 * 构造查询条件	
	 * @param params
	 * @param hql
	 * @param hqlParams
	 * @throws ServiceException
	 */
    private static void buildSQL(StringMap params, StringBuffer hql, HashMap<String, Object> hqlParams)  throws ServiceException 
    {
    	//业务oid
    	String reviewBizInfoOid = params.get("reviewBizInfoOid");
    	//单位名称
		String unitName = params.get("unitName");
		//人员名称
		String personName = params.get("personName");
		//职务级别
		String dutyLevel = params.get("dutyLevel");
		//考核结论类别
		String reviewResultType = params.get("reviewResultType");
		//岗位类别
		String positionType = params.get("positionType");
		
		if (StringUtils.isNotEmpty(reviewBizInfoOid)) {
			//业务oid
			hql.append("  and jprdi.REVIEW_BIZ_INFO_OID =")
					.append(reviewBizInfoOid);
		}
		
		if (StringUtils.isNotEmpty(unitName)) {
			//单位名称
			hql.append("  and juu.unit_name like '")
					.append(StringUtil.wrapPercent(unitName)).append("'");
		}

		if (StringUtils.isNotEmpty(personName)) {
			//人员名称
			hql.append("  and jppi.name like '")
					.append(StringUtil.wrapPercent(personName)).append("'");

		}
		
		if (StringUtils.isNotEmpty(dutyLevel)) {
			//职务级别
			hql.append("  and jprdi.DUTY_LEVEL_CODE in(").append(dutyLevel)
					.append(")");

		}
		if (StringUtils.isNotEmpty(reviewResultType)) {

			hql.append("  and jprdi.REVIEW_RESULT_TYPE in(").append(reviewResultType)
					.append(")");

		}
		if (StringUtils.isNotEmpty(positionType)) {
			
			hql.append("  and jprdi.POSITION_TYPE in(").append(positionType)
			.append(")");
			
		}
		
	}
    
    /**
	 * 封装列表
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
			Object[] object = list.get(i);
			
			Long reviewDetailInfoOid = object[0] == null ? null : new Long(object[0].toString());
			String name = DataConverUtils.toString(object[1]);//人员姓名
			Long unitOid = object[2] == null ? null : new Long(object[2].toString());//单位oid
			String unitName = DataConverUtils.toString(object[3]);//单位名称
			String dutyLevelName = DataConverUtils.toString(object[4]);//职务级别
			String dutyAttributeName = DataConverUtils.toString(object[5]);//职务属性
			String reviewResultTypeName = DataConverUtils.toString(object[6]);// 考核类别
			String reviewTypeDesc = DataConverUtils.toString(object[7]);// 考核结论类别描述
			String positionType = DataConverUtils.toString(object[8]);// 岗位类别YHRS0022
			String positionLevel = DataConverUtils.toString(object[9]);// 岗位级别YHRS0023
			String positionName = DataConverUtils.toString(object[10]);// 岗位名称
			
			
			JSONObject obj = new JSONObject();
			
			obj.put("reviewDetailInfoOid",reviewDetailInfoOid);
			obj.put("name",name);
			obj.put("unitOid", unitOid);
			obj.put("unitName",unitName );
			obj.put("dutyLevelName",DicHelper.viewName(DicConstants.YHRS0015,dutyLevelName));
			obj.put("dutyAttributeName",DicHelper.viewName(DicConstants.YHRS0028,dutyAttributeName));
			UtUnit utUnit = UtUnitQueryHelper.get(unitOid);
			if (utUnit.getUnitKind() != null && utUnit.getUnitKind().equals(DicConstants.YHRS0090_104)) {
				obj.put("reviewResultTypeName",DicHelper.viewName(DicConstants.YHRS0123,reviewResultTypeName));
			}else{
				obj.put("reviewResultTypeName",DicHelper.viewName(DicConstants.YHRS0070,reviewResultTypeName));
			}
			obj.put("reviewTypeDesc",reviewTypeDesc);
			obj.put("positionType",DicHelper.viewName(DicConstants.YHRS0022,positionType));
			obj.put("positionLevel",DicHelper.viewName(DicConstants.YHRS0023,positionLevel));
			obj.put("positionName",positionName);
			
			dtoList.add(obj);
		}
		return dtoList;
	}
	
	
	/**
	 * 根据不同条件查询不同数量
	 * @param reviewBizInfoOid
	 * @param reviewResultType
	 *         考核结论类别YHRS0070
	 * @param isPromote
	 *         是否因特殊原因不晋升标识YHRS0003
	 * @param dutyLevel  职级
	 * @return
	 * @throws ServiceException
	 */
	public static int countByCondition(Long reviewBizInfoOid,String reviewResultType, String isPromote,String dutyLevel)throws ServiceException {
	
		//查询
		StringBuffer hql =  new StringBuffer("select count(*) from PtReviewDetailInfo prdi ");
		//业务主键
		hql.append(" where prdi.reviewBizInfoOid="+reviewBizInfoOid);
		Map<String, Object> params = new HashMap<String, Object>();
		if(reviewResultType!=null){
			//考核结论类别YHRS0070
			hql.append(" and prdi.reviewResultType = '").append(reviewResultType).append("'");
			
		}
		
		if(isPromote!=null){
			//是否因特殊原因不晋升标识YHRS0003----否 --就会参加工资核算
			if(DicConstants.YHRS0003_0.equals(isPromote)){
				
				hql.append(" and prdi.isPromote = '").append(isPromote).append("'");
			}
		}
		
		if(dutyLevel !=null){
			//职务级别
			hql.append(" and prdi.dutyLevelCode in (").append(dutyLevel).append(")");
		}
		
	    //计数
		return DaoUtil.countByCondition(hql.toString(), params);
	}
	
}
