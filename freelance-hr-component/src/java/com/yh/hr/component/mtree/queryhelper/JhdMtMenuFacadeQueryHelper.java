package com.yh.hr.component.mtree.queryhelper;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.component.mtree.dto.MtCountCode;
import com.yh.hr.component.task.util.TaskConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.component.mtree.dto.MtMenuDto;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

public class JhdMtMenuFacadeQueryHelper {

	public static List<MtMenuDto> findJhdMtMenu(String menuCode,String menuType) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.MENU_CODE,t.PARENT_MENU_CODE,t.MENU_TITLE,t.MENU_TYPE,t.LOCATION,t.ACTION,t.IMAGE  from yhd_mt_menu t where 1=1");
		if(!StringUtils.isEmpty(menuType)){
			sql.append(" and t.menu_type = " + menuType);
		}
		if(StringUtils.isEmpty(menuCode)){
			sql.append(" and t.parent_menu_code is null");
		}else {
			sql.append(" and t.parent_menu_code = '" + menuCode+"'");
		}
		if(StringUtils.isNotEmpty(UserContext.getLoginUserID())){
			sql.append(" and t.menu_code in (select jrin.menu_code");
			sql.append(" from yhb_user_sp jus, yhb_system_position jsp, yhb_role_menu jrin");
			sql.append(" where jus.system_position_oid = jsp.system_position_oid");
			sql.append(" and jsp.function_role_id = jrin.role_id and jus.user_id = '"+UserContext.getLoginUserID()+"')");
		}
		sql.append(" order by t.order_seq, t.MENU_CODE");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<MtMenuDto> items = new ArrayList<MtMenuDto>();
			
			for (Object[] objs : list) 
			{
				MtMenuDto dto = new MtMenuDto();
				dto.setMenuCode(objs[0] == null ? null : objs[0].toString());
				dto.setMenuTitle(objs[2] == null ? null : objs[2].toString());
				dto.setParentMenuCode(objs[1] == null ? null : objs[1].toString());
				dto.setLocation(objs[4] == null ? null : objs[4].toString());
				dto.setAction(objs[5] == null ? null : objs[5].toString());
				dto.setImage(objs[6] == null ? null : objs[6].toString());
				dto.setMenuType(objs[3] == null ? null : objs[3].toString());
				items.add(dto);
			}
			return items;
		}
		return null;
	}

	public static List<String> getLeafNodeCode(String parentNodeCode) throws ServiceException {
		StringBuffer sql = new StringBuffer("select jtm.menu_code from yhd_mt_menu jtm where 1 = 1  ");
		
		if (StringUtils.isNotEmpty(parentNodeCode)) 
		{
			sql.append(" and jtm.parent_menu_code = '" + parentNodeCode+"'");
		}
		return DaoUtil.findWithSQL(sql.toString());
	}

	public static boolean checkByRoleId(String roleId, String menuCode) throws ServiceException {
		List<MtMenuDto> list=DaoUtil.find(" from RoleMenu r where r.roleId=? and r.MenuCode=?",Long.valueOf(roleId),menuCode);
		return CollectionUtils.isNotEmpty(list);
	}
	

	public static List<MtMenuDto> findItem(String parentMenuCode,String menuType) throws ServiceException {
		StringBuilder hql=new StringBuilder();
		hql.append(" select jmm.menu_code,jmm.menu_title  from yhd_mt_menu jmm where 1=1 ");
		if(!StringUtils.isEmpty(menuType)){
			hql.append("  and jmm.menu_type ='" + menuType+"'");
		}
		if(StringUtils.isEmpty(parentMenuCode)){
			hql.append(" and jmm.parent_menu_code is null");
		}else{
			hql.append(" and jmm.parent_menu_code = '"+parentMenuCode+"'");
		}
		hql.append(" and jmm.menu_code in (select jrin.menu_code from yhb_user_sp jus,yhb_system_position jsp,");
		hql.append(" yhb_role_menu jrin  where jus.system_position_oid = jsp.system_position_oid");
		hql.append(" and jsp.function_role_id = jrin.role_id ");
		hql.append(" and jus.user_id = '"+UserContext.getLoginUserID()+"')"); 
		hql.append(" order by jmm.menu_code asc");
		List<Object[]> list= DaoUtil.findWithSQL(hql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<MtMenuDto> items = new ArrayList<MtMenuDto>();
			
			for (Object[] objs : list) 
			{
				MtMenuDto dto = new MtMenuDto();
				dto.setMenuCode(objs[0] == null ? null : objs[0].toString());
				dto.setMenuTitle(objs[1] == null ? null : objs[1].toString());
				dto.setParentMenuCode(parentMenuCode);
				items.add(dto);
			}
			return items;
		}
		return null;
	}
    public static String findMtNodeByMenuCode(String menuCode) throws ServiceException{
    	if(!StringUtils.isEmpty(menuCode)){
    		StringBuffer sql = new StringBuffer();
    		sql.append("select flow_node_code,menu_code from yhd_mt_node where menu_code = '" + menuCode+"'");
    		//List<MtNode> list =DaoUtil.find(" from MtNode where menuCode='"+menuCode+"'");
    		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
    		if (CollectionUtils.isNotEmpty(list)) 
    		{
    			for (Object[] objs : list) 
    			{
        			return objs[0].toString();
    			}
    		}
    	}
		return null;
    	
    }

	public static List<MtCountCode> getCountByCodeDefault(String menuType) throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.menu, sum(t1.c1) from (select jbti.task_item_code,count(1) as c1,");
		sql.append(" (select jmm.menu_code from yhd_mt_menu jmm, yhd_mt_node jmn where jmm.menu_code = jmn.menu_code");
		sql.append(" and jmm.menu_code in (select jrin.menu_code from yhb_user_sp jus,yhb_system_position jsp,");
		sql.append(" yhb_role_menu jrin  where jus.system_position_oid = jsp.system_position_oid");
		sql.append(" and jsp.function_role_id = jrin.role_id and jmm.menu_type = '"+menuType+"'");
		sql.append(" and jus.user_id = '"+UserContext.getLoginUserID()+"')");
		sql.append("  and jbti.task_item_code = jmn.flow_node_code) as menu from YHC_BT_TASK_ITEM jbti,YHC_BT_TASK_ITEM obti,");
		sql.append(" YHC_BT_TASK jbt LEFT JOIN yhc_pt_person jpp ON jpp.task_oid = jbt.task_oid");
		sql.append(" LEFT JOIN yhc_at_department jad ON jad.task_oid = jbt.task_oid");
		sql.append("  where jbt.task_oid = jbti.task_oid and jbti.TASK_ITEM_STATUS = '"+ TaskConstants.QUERY_TYPE_1+"'");
		sql.append(" and obti.task_item_oid = (select max(bti2.task_item_oid) from yhc_bt_task_item bti2 where bti2.task_oid = jbt.task_oid)");
		sql.append(" and jbti.task_item_oid in (select max(bti.task_item_oid)");
		sql.append(" from yhc_bt_task_item bti  where bti.task_oid = jbt.task_oid  group by bti.task_item_code)");
		sql.append(" AND CASE substr(jbti.task_item_code, 6, 9)  WHEN '1211' THEN");
		sql.append("  exists (select 1 from yhb_user_relation ur where ((ur.ref_oid=jpp.person_oid and ur.ref_type='02') or (ur.ref_oid=jpp.biz_person_oid and ur.ref_type='01')) and ur.user_id= '").append(UserContext.getLoginUserID()).append("') ");;
		sql.append(" WHEN '1111' THEN");
		sql.append(" CASE jbti.task_item_code WHEN '211801111' THEN ");
		sql.append("  jbt.created_by_code = '").append(UserContext.getLoginUserID()).append("' ");
		sql.append(" ELSE");
		sql.append("  jbt.created_by_code = '").append(UserContext.getLoginUserID()).append("' ");
		sql.append(" and (jpp.hire_dept_oid in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("') ");
		sql.append(" or jad.hire_dept_oid in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("'))");
		sql.append(" END ELSE");
		sql.append("  (jpp.hire_dept_oid in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("') ");
		sql.append(" or  jad.hire_dept_oid in (select oa.org_oid from yhb_user_org_auth oa where oa.user_id = '").append(UserContext.getLoginUserID()).append("')) end");
		sql.append("  group by jbti.task_item_code) t1 group by t1.menu");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<MtCountCode> items = new ArrayList<MtCountCode>();
			
			for (Object[] objs : list) 
			{
				MtCountCode dto = new MtCountCode();
				dto.setMenuCode(objs[0] == null ? null : objs[0].toString());
				dto.setCount(objs[1] == null ? null : Integer.valueOf(objs[1].toString()));
				items.add(dto);
			}
			return items;
		}
		return null;
	}
	public static List<MtCountCode> getAllChildCode (String menuCode) throws DataAccessFailureException {
		StringBuffer sql = new StringBuffer();
		sql.append("select jmm.menu_code,jmm.action from yhd_mt_menu jmm where FIND_IN_SET(jmm.menu_code,getAllChildMenuCode('"+menuCode+"'))");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<MtCountCode> items = new ArrayList<MtCountCode>();
			
			for (Object[] objs : list) 
			{
				MtCountCode dto = new MtCountCode();
				dto.setMenuCode(objs[0] == null ? null : objs[0].toString());
				dto.setAction(objs[1] == null ? null : objs[1].toString());
				items.add(dto);
			}
			return items;
		}
		return null;
		
	}
}
