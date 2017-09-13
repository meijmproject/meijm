package com.yh.hr.res.bt.queryhelper;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.res.unit.dto.UtUnitDTO;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 业务日志查询
 * 
 * @author zhangqp
 * @version 1.0, 16/10/19
 */
public class BtUnitQueryHelper {

	public static List<JSONObject> findSelectUnit(String menuCode, List<String> utUnitlist)
			throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer();
		if (menuCode.startsWith("B426")||menuCode.equals("A301327")||menuCode.equals("A401327")||menuCode.equals("A901601")||menuCode.equals("A902601")) {
			sql.append("select distinct jpp.MANAGE_UNIT_OID,juu.unit_name from YHC_BT_TASK jbt,YHC_BT_TASK_ITEM jbti,");
		}else{
			sql.append("select distinct jpp.unit_oid,juu.unit_name from YHC_BT_TASK jbt,YHC_BT_TASK_ITEM jbti,");
		}
		if (menuCode.startsWith("B302")||menuCode.equals("A301202")||menuCode.equals("A401202")||menuCode.equals("A901102")||menuCode.equals("A902102")
			||menuCode.startsWith("B301")||menuCode.equals("A301201")||menuCode.equals("A401201")||menuCode.equals("A901101")||menuCode.equals("A902101")) {
			sql.append(" YHG_GT_SET_PLAN jpp,");
		} else if(menuCode.startsWith("B303")||menuCode.equals("A301203")||menuCode.equals("A401203")||menuCode.equals("A903101")||menuCode.equals("A904101")) {
			sql.append(" YHG_GT_PLAN_DETAIL jpp,");
		} else if (menuCode.startsWith("B426")||menuCode.equals("A301327")||menuCode.equals("A401327")||menuCode.equals("A901601")||menuCode.equals("A902601")) {
			sql.append(" YHC_PT_REVIEW_BIZ_INFO jpp, ");
		} else {
			sql.append(" YHC_PT_PERSON jpp,");
		}
		sql.append(" YHC_UT_UNIT juu where jbt.task_oid = jbti.task_oid and jbt.task_oid = jpp.task_oid");
		if (menuCode.startsWith("B426")||menuCode.equals("A301327")||menuCode.equals("A401327")||menuCode.equals("A901601")||menuCode.equals("A902601")) {
			sql.append(" and juu.unit_oid = jpp.MANAGE_UNIT_OID and jbti.TASK_ITEM_CODE in (select jmn.FLOW_NODE_CODE");
		}else{
			sql.append(" and juu.unit_oid = jpp.unit_oid and jbti.TASK_ITEM_CODE in (select jmn.FLOW_NODE_CODE");
		}
		sql.append(" from YHD_MT_NODE jmn where");
		if(StringUtils.isNotEmpty(menuCode)){
			sql.append(" jmn.MENU_CODE = '"+menuCode+"') and");
		}
		sql.append(" jbti.TASK_ITEM_STATUS = '1'");
		if (menuCode.startsWith("B426")||menuCode.equals("A301327")||menuCode.equals("A401327")||menuCode.equals("A901601")||menuCode.equals("A902601")) {
			sql.append(" and jpp.MANAGE_UNIT_OID in(").append(StringUtil.join(utUnitlist)).append(")");
		}else{
			sql.append(" and jpp.UNIT_OID in(").append(StringUtil.join(utUnitlist)).append(")");
		}
		List<Object> list=DaoUtil.findWithSQL(sql.toString());
		
		List<JSONObject> jsonArray = new ArrayList<JSONObject>();
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				JSONObject json=new JSONObject();
				Object[] obj=(Object[]) list.get(i);
				json.put("unitOid",obj[0]==null?null:new Long(obj[0].toString()));
				json.put("unitName",obj[1]==null?null:obj[1].toString());
				jsonArray.add(json);
			}
		}
		return jsonArray;
	}

	public static List<JSONObject> findAllSelectUnit(List<UtUnitDTO> utUnitlist) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct juu.unit_oid,juu.unit_name from YHC_UT_UNIT juu");
		sql.append("  where 1=1");
		sql.append(" and juu.UNIT_OID in(").append(StringUtil.join(utUnitlist)).append(")");
		List<Object> list=DaoUtil.findWithSQL(sql.toString());
		
		List<JSONObject> jsonArray = new ArrayList<JSONObject>();
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				JSONObject json=new JSONObject();
				Object[] obj=(Object[]) list.get(i);
				json.put("unitOid",obj[0]==null?null:new Long(obj[0].toString()));
				json.put("unitName",obj[1]==null?null:obj[1].toString());
				jsonArray.add(json);
			}
		}
		return jsonArray;
	}

}
