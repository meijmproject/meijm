package com.yh.hr.orghc.unit.unitmanger.queryhelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.orgtree.queryhelper.JhcOrgTreeQueryHelper;
import com.yh.hr.orghc.unit.unitmanger.dto.OrgDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.NumberUtils;

public class UnitMangerQueryHelper {
	/**
	 * 获取系统单位数量
	 * @throws ServiceException
	 */
	public static int listUnitCounts() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from yhc_ut_unit");
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return list.size();
	}

	/**
	 * 查询科室列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public static List<JSONObject> listOrg(TableTagBean ttb) throws ServiceException{
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("select uo.org_oid,uo.org_name,uo.order_of_view,uo.parent_org_oid,uo.org_type,uo.established_date,uo.contacter,uo.mobile_phone,uo.email,uo.phone,uo.fax,uo.address,uo.remark,uo.org_status,uo.created_date,uo.hierarchy_code,uo.org_category ");
		sql.append(" from YHE_UB_ORG uo where uo.org_oid not in (select org_oid from YHE_UB_ORG where org_type not in ('1','2','3')) " );
		//单位id
		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitOid"))) {
			sql.append(" and uo.unit_oid= ").append(ttb.getCondition().get("unitOid"));
		}
		//科室名称
		if (StringUtils.isNotEmpty(ttb.getCondition().get("deptName"))) {
			sql.append(" and uo.org_name like '%").append(ttb.getCondition().get("deptName")+"%'");
		}
		//科室类型
		if (StringUtils.isNotEmpty(ttb.getCondition().get("deptType"))) {
			String orgOidString = JhcOrgTreeQueryHelper.getOrgOidListByType(ttb.getCondition().get("deptType"));
			sql.append(" and uo.org_oid in(").append(orgOidString).append(")");
		}
		//科室状态
		if (StringUtils.isNotEmpty(ttb.getCondition().get("orgStatus"))) {
			sql.append(" and uo.org_Status= ").append(ttb.getCondition().get("orgStatus"));
		}
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder().append("select count(*) from(").append(sql).append(") t").toString(), params));
		}

		List<Object[]> list = DaoUtil.listWithSQLByCondition(new StringBuilder().append("select * from(").append(sql).append(") t order by hierarchy_code,org_oid ").toString(), params, ttb.getPage(), ttb.getPageSize());

		return buildOrgJSON(list);
	}
	//构建json对象
	private static List<JSONObject> buildOrgJSON(List<Object[]> list) throws ServiceException{
		List<JSONObject> dtoList = new ArrayList<JSONObject>();
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)){return dtoList;}
		for (int i = 0; i < list.size(); i++)
		{
			Object[] task = list.get(i);

			String orgOid = task[0] == null ? null:task[0].toString();
			String orgName = task[1] == null ? null:task[1].toString();
			String orderOfView = task[2] == null ? null: task[2].toString();
			String parentOrgOid = task[3] == null ? null:task[3].toString();
			String orgType = task[4] == null ? null:task[4].toString();
			Date establishedDate = task[5] == null ? null:DateUtil.parseDate(task[5].toString());
			String contacter = task[6] == null ? null:task[6].toString();
			String mobilePhone = task[7] == null ? null:task[7].toString();
			String email = task[8] == null ? null:task[8].toString();
			String phone = task[9] == null ? null:task[9].toString();
			String fax = task[10] == null ? null:task[10].toString();
			String address = task[11] == null ? null:task[11].toString();
			String remark = task[12] == null ? null:task[12].toString();
			String orgStatus = task[13] == null ? null:task[13].toString();
			String orgCategory = task[16] == null ? null:task[16].toString();
			
			JSONObject obj = new JSONObject();

			obj.put("orgOid",orgOid);
			obj.put("orgName",orgName);
			obj.put("orderOfView",orderOfView);
			obj.put("parentOrgOid",parentOrgOid);
			obj.put("orgType", orgType);
			obj.put("establishedDateStr",DateUtil.formatDate(establishedDate));
			obj.put("contacter",contacter);
			obj.put("mobilePhone",mobilePhone);
			obj.put("email", email);
			obj.put("phone",phone);
			obj.put("fax",fax);
			obj.put("address",address);
			obj.put("remark",remark);
			obj.put("orgStatus",orgStatus);
			obj.put("orgStatusName",DicHelper.viewName(DicConstants.YHRS0101,orgStatus));
			obj.put("orgCategory",orgCategory);
			obj.put("orgCategoryDesc",DicHelper.viewName(DicConstants.YHRS0102,orgCategory));
			dtoList.add(obj);
		}
		return dtoList;
	}
	
	/**
	 * 根据科室名称查询科室信息
	 * @param orgName
	 * @return
	 * @throws ServiceException
	 */
	public static UbOrg getOrgByName(String orgName) throws ServiceException{
		String hql = "from UbOrg ub where ub.orgName ='"+orgName+"'";
		return DaoUtil.uniqueResult(hql);
	}

	/**
	 * 查询科室子节点
	 * @param unitOid
	 * @param orgOidStr
	 * @return
	 * @throws ServiceException
	 */
	public static List<UbOrgDTO> getOrg(Long unitOid, Long orgOid) throws ServiceException{
		StringBuffer hql = new StringBuffer("from UbOrg ub where 1 = 1");
		if(NumberUtils.isNotNullOrZero(unitOid)){
			hql.append(" and ub.unitOid ="+unitOid);
			if(NumberUtils.isNotNullOrZero(orgOid)){
				hql.append(" and ub.parentOrgOid ="+orgOid);
				hql.append(" and ub.parentOrgOid in (select orgOid from UbOrg where orgType in ('4','5','6','7'))");
				hql.append(" order by ub.orgOid");
			}else{
				hql.append(" and ub.parentOrgOid is null");
			}
		}
		return DaoUtil.find(hql.toString());
	}

	/**
	 * 根据科室类型查询上级科室
	 * @param unitOid
	 * @param orgType
	 * @return
	 * @throws ServiceException
	 */
	public static List<OrgDTO> findByOrgType(String unitOid, String orgType) throws ServiceException{
		StringBuffer hql = new StringBuffer("from UbOrg ub where ub.parentOrgOid is not null ");
		if(StringUtils.isNotEmpty(unitOid)){
			hql.append(" and ub.unitOid ="+unitOid);
			if(null!=orgType){
				hql.append(" and ub.parentOrgOid in (select orgOid from UbOrg where orgType ='"+orgType+"')");
				hql.append(" order by ub.orgOid");
			}
		}
		return BeanHelper.copyProperties(DaoUtil.find(hql.toString()),OrgDTO.class);
	}

	/**
	 * 查询科室下的在职人员
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbPersonInfo> listPersonByOrgOid(Long orgOid) throws ServiceException{
		StringBuffer hql = new StringBuffer("from PbPersonInfo ub where ub.personStatus ="+DicConstants.YHRS0009_110);
		if(NumberUtils.isNotNullOrZero(orgOid)){
			hql.append(" and ub.hireDeptOid ="+orgOid);
		}
		return DaoUtil.find(hql.toString());
	}
	
	public static String getMaxOrderOfView(Long unitOid) throws ServiceException{
		String hql = "SELECT max(ORDER_OF_VIEW) from yhe_ub_org where ORG_TYPE not in ('1','2','3') and unit_Oid ="+unitOid;
		List<Object> list=DaoUtil.findWithSQL(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return DataConverUtils.toString(list.get(0));
		}
		return null;
	}
}
