package com.yh.admin.menu.queryhelper;

import java.util.ArrayList;
import java.util.List;

import com.yh.admin.bo.MenuItem;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.web.UserContext;

public class MenuQueryHelper {

	/**
	 * 根据资源配置获取菜单信息
	 * 
	 * @param resources
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<MenuItem> findAllowed(List<String> resources) throws DataAccessFailureException {
		String systemId = (String) UserContext.getSystemId();

		StringBuffer sqlBuff = new StringBuffer("select distinct m from MenuItem m ");

		List<StringBuffer> listRes = new ArrayList<StringBuffer>();
		StringBuffer res = new StringBuffer();

		int i = 0;
		for (String rsValue : resources) {

			i++;
			if (i == 500) {
				if (res.length() > 1) {
					res.deleteCharAt(0);
				}
				listRes.add(res);
				res = new StringBuffer();
				i = 0;
			}
			res.append(",'").append(rsValue).append("'");

		}

		if (res.length() > 1) {
			res.deleteCharAt(0);
		}
		listRes.add(res);

		sqlBuff.append(" where m.systemId =" + systemId);
		if (listRes.size() > 0) {
			sqlBuff.append(" and ( ");
			for (int m = 0; m < listRes.size(); m++) {
				sqlBuff.append(" m.action in(" + listRes.get(m).toString() + ",'#' ) ");
				if (m + 1 != listRes.size()) {
					sqlBuff.append(" or ");
				}
			}

			sqlBuff.append(" ) ");
		}

		sqlBuff.append(" order by m.orderSeq");

		return DaoUtil.find(sqlBuff.toString());
	}

	/**
	 * 查询所有菜单
	 * 
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<MenuItem> list() throws DataAccessFailureException {
		return DaoUtil.find(" from MenuItem");
	}

	/**
	 * 根据系统id查询所有菜单信息
	 * 
	 * @param systemId
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<MenuItem> findAllTopMenu(String systemId) throws DataAccessFailureException {
		return DaoUtil.findByNamed("findTopMenuItem", systemId);
	}

	/**
	 * 根据系统id和父id查询子菜单
	 * 
	 * @param parentId
	 * @param systemId
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<MenuItem> findMenuByParentId(Long parentId, String systemId) throws DataAccessFailureException {
		return DaoUtil.findByNamed("findSubMenuItem", parentId, systemId);
	}

	/**
	 * 得到下级菜单<br>
	 * xuhj 2009年8月17日 modify 考虑岗位有效期及岗位代理的情况<br>
	 * xuhj 2009年11月4日 modify 本方法不再考虑岗位代理的情况
	 * 
	 * @return
	 * @throws DataAccessFailureException
	 * @throws ServiceException
	 */
	public static List<MenuItem> listMenuByParentOid(List<String> resources, Long parentId) throws DataAccessFailureException {
		List<MenuItem> list = new ArrayList<MenuItem>();
		String systemId = UserContext.getSystemId();
		String userId = UserContext.getLoginUserID();
		String hql = " select m.id,m.PARENT_NAME,m.NAME,m.TITLE,m.DESCRIPTION,m.LOCATION,m.TARGET,m.ORDER_SEQ,m.ACTION,m.PARENT_ID,m.SYSTEM_CODE,m.IMAGE " + " from YHB_MENU_ITEM m where m.parent_id = " + parentId;
		if (ConfigUtil.isSecurityCheckRequired()) {
			hql += " and (m.action = '#' or m.action in (select distinct r.res_value " 
					+ " from yhb_function_res fr,yhb_resources r,yhb_role_function rf,yhb_system_position sp,yhb_user_sp usp,yhb_roles rl " 
					+ " where r.res_id = fr.res_id and fr.func_id = rf.func_id and rf.role_id = rl.role_id "
					+ " and sp.function_role_id = rl.role_id " 
					+ " and sp.system_position_oid = usp.system_position_oid " 
					+ " and usp.user_id = '"+ userId+ "'" 
					+ " and (usp.effective_date is null or usp.effective_date <= now()) " 
					+ " and (usp.expired_date is null or usp.expired_date >= now()) )"
					+ ")";

		}
		hql += " and m.system_code = '" + systemId + "' order by m.order_seq";
		List<Object[]> objectList = DaoUtil.findWithSQL(hql);
		if (!objectList.isEmpty()) {
			for (int i = 0, length = objectList.size(); i < length; i++) {
				Object[] obj = objectList.get(i);
				MenuItem menuItem = new MenuItem();
				menuItem.setId(DataConverUtils.toLong(obj[0]));
				menuItem.setParentName(DataConverUtils.toString(obj[1]));
				menuItem.setName(DataConverUtils.toString(obj[2]));
				menuItem.setTitle(DataConverUtils.toString(obj[3]));
				menuItem.setDescription(DataConverUtils.toString(obj[4]));
				menuItem.setLocation(DataConverUtils.toString(obj[5]));
				menuItem.setTarget(DataConverUtils.toString(obj[6]));
				menuItem.setOrderSeq(DataConverUtils.toLong(obj[7]));
				menuItem.setAction(DataConverUtils.toString(obj[8]));
				menuItem.setParentId(DataConverUtils.toLong(obj[9]));
				menuItem.setSystemCode(DataConverUtils.toString(obj[10]));
				menuItem.setImage(DataConverUtils.toString(obj[11]));
				list.add(menuItem);

			}
		}
		return list;
	}

	/**
	 * 得到顶层菜单<br>
	 * xuhj 2009年8月17日 modify 考虑岗位有效期及岗位代理的情况<br>
	 * xuhj 2009年11月4日 modify 本方法不再考虑岗位代理的情况
	 * 
	 * @return
	 * @throws DataAccessFailureException
	 * @throws ServiceException
	 */
	public static List<MenuItem> listTopMenu(List<String> resources) throws DataAccessFailureException {
		List<MenuItem> list = new ArrayList<MenuItem>();
		String systemId = UserContext.getSystemId();
		String userId = UserContext.getLoginUserID();
		String hql = " select m.id,m.PARENT_NAME,m.NAME,m.TITLE,m.DESCRIPTION,m.LOCATION,m.TARGET,m.ORDER_SEQ,m.ACTION,m.PARENT_ID,m.SYSTEM_CODE,m.IMAGE " + " from YHB_MENU_ITEM m where m.parent_name = 'Top' ";
		if (ConfigUtil.isSecurityCheckRequired()) {
			hql += " and (m.action = '#' or m.action in (select distinct r.res_value " 
					+ " from yhb_function_res fr,yhb_resources r,yhb_role_function rf,yhb_system_position sp,yhb_user_sp usp,yhb_roles rl " 
					+ " where r.res_id = fr.res_id and fr.func_id = rf.func_id and rf.role_id = rl.role_id "
					+ " and sp.function_role_id = rl.role_id "
					+ " and sp.system_position_oid = usp.system_position_oid " 
					+ " and usp.user_id = '"+ userId+ "'" 
					+ " and (usp.effective_date is null or usp.effective_date <= now()) " 
					+ " and (usp.expired_date is null or usp.expired_date >= now()) ))";

		}
		hql += " and m.system_code = '" + systemId + "' order by m.order_seq";

		List<Object[]> objectList = DaoUtil.findWithSQL(hql);
		if (!objectList.isEmpty()) {
			for (int i = 0, length = objectList.size(); i < length; i++) {
				Object[] obj = objectList.get(i);
				MenuItem menuItem = new MenuItem();
				menuItem.setId(DataConverUtils.toLong(obj[0]));
				menuItem.setParentName(DataConverUtils.toString(obj[1]));
				menuItem.setName(DataConverUtils.toString(obj[2]));
				menuItem.setTitle(DataConverUtils.toString(obj[3]));
				menuItem.setDescription(DataConverUtils.toString(obj[4]));
				menuItem.setLocation(DataConverUtils.toString(obj[5]));
				menuItem.setTarget(DataConverUtils.toString(obj[6]));
				menuItem.setOrderSeq(DataConverUtils.toLong(obj[7]));
				menuItem.setAction(DataConverUtils.toString(obj[8]));
				menuItem.setParentId(DataConverUtils.toLong(obj[9]));
				menuItem.setSystemCode(DataConverUtils.toString(obj[10]));
				menuItem.setImage(DataConverUtils.toString(obj[11]));
				list.add(menuItem);
			}
		}
		return list;
	}
}
