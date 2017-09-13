/**
 * 
 */
package com.yh.admin.users.queryhelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.yh.admin.bo.SubSystem;
import com.yh.admin.bo.SystemPosition;
import com.yh.admin.bo.UserRelation;
import com.yh.admin.bo.UserSystemPosition;
import com.yh.admin.bo.Users;
import com.yh.admin.dto.SubSystemDTO;
import com.yh.admin.dto.UserPositionInfoDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.util.AdminConstants;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/23
 */

public class UsersQueryHelper {

	/**
	 * 登录检查
	 * 
	 * @param userCode
	 * @param pw
	 * @return
	 * @throws ServiceException
	 */
	public static UsersDTO findByUserId(String userId) throws ServiceException {

		List<Users> list = DaoUtil.find("from Users as u where u.userId= ? ",
				userId);

		if (CollectionUtils.isEmpty(list))
			return null;

		if (list.size() > 1)
			throw new ServiceException(null, "用户ID不唯一");

		return BeanHelper.copyProperties(list.get(0), UsersDTO.class);
	}

	/**
	 * 登录检查
	 * 
	 * @param userCode
	 * @param pw
	 * @return
	 * @throws ServiceException
	 */
	public static UsersDTO checkUserPswd(String userCode, String pw)
			throws ServiceException {

		List<Users> list = DaoUtil.find(
				"from Users as u where u.userId= ? and u.password = ?",
				userCode, pw);

		if (CollectionUtils.isEmpty(list))
			return null;

		if (list.size() > 1)
			throw new ServiceException(null, "用户ID不唯一");

		return BeanHelper.copyProperties(list.get(0), UsersDTO.class);
	}

	/**
	 * 根据userId和systemId查询用户信息<br>
	 * xuhj 2009年8月17日 modify 考虑岗位有效期及岗位代理的情况，如果该用户代理其他人岗位也可以登陆<br>
	 * xuhj 2009年11月4日 modify 方法本身不再考虑用户代理的情况
	 * 
	 * @throws DataAccessFailureException
	 */
	public static Users checkUserRight(String userId, String systemId)
			throws DataAccessFailureException {
		// 查询权限则使用Uc中的agentUid
		if (userId == null || "".equals(userId))
			userId = UserContext.getLoginAgentUserID();

		String sql = " select users.user_oid, users.user_id, users.password, users.user_name, users.user_sex, users.address, "
				+ " users.contact_phone, users.regist_date, users.effective_dt, users.expired_date, users.user_type, "
				+ " users.user_status, users.unit_id, users.unit_name, users.dept_id, users.dept_name,"
				+ " users.default_login_system,users.updated_by_code, users.updated_by_name, users.updated_date "
				+ " from yhb_users users  where users.user_id in("
				+ " select distinct u.user_id "
				+ " from yhb_users u,yhb_system_position sp,yhb_user_sp usp,yhb_roles rl "
				+ " where u.user_id = usp.user_id "
				+ " and usp.system_position_oid = sp.system_position_oid "
				+ " and rl.role_id = sp.function_role_id "
				/* 登陆时用户不在于系统挂钩 + */
				// + "  and rl.system_id = :systemId "
				+ " and usp.user_id = ? "
				+ " and (u.effective_dt is null or u.effective_dt <= now()) "
				+ " and (u.expired_date is null or u.expired_date >= now()) "
				+ " and (usp.effective_date is null or usp.effective_date <= now()) "
				+ " and (usp.expired_date is null or usp.expired_date >= now()) "
				+ ")";

		List<Object[]> list = DaoUtil.findWithSQL(sql, userId);

		if (CollectionUtils.isEmpty(list))
			return null;

		Object[] objs = list.get(0);

		Users users = new Users();

		users.setUserOid(DataConverUtils.toLong(objs[0]));
		users.setUserId(DataConverUtils.toString(objs[1]));
		users.setPassword(null);
		users.setUserName(DataConverUtils.toString(objs[3]));
		users.setUserSex(DataConverUtils.toString(objs[4]));
		users.setAddress(DataConverUtils.toString(objs[5]));
		users.setContactPhone(DataConverUtils.toString(objs[6]));
		users.setRegistDate(DataConverUtils.toDate(objs[7]));
		users.setEffectiveDate(DataConverUtils.toDate(objs[8]));
		users.setExpiredDate(DataConverUtils.toDate(objs[9]));
		users.setUserType(DataConverUtils.toString(objs[10]));
		users.setUserStatus(DataConverUtils.toString(objs[11]));
		users.setUnitId(DataConverUtils.toLong(objs[12]));
		users.setUnitName(DataConverUtils.toString(objs[13]));
		users.setDeptId(DataConverUtils.toLong(objs[14]));
		users.setDeptName(DataConverUtils.toString(objs[15]));
		users.setDefaultLoginSystem(DataConverUtils.toString(objs[16]));

		return users;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param userOid
	 * */
	public static UsersDTO getUserByOid(String userOid) throws ServiceException {
		Users users = DaoUtil.get(Users.class, Long.valueOf(userOid.trim()));
		UsersDTO userDTO = new UsersDTO();
		BeanHelper.copyProperties(users, userDTO);
		return userDTO;
	}

	/**
	 * 查询用户信息
	 * 
	 * @return
	 * @throws ServiceException
	 * */
	public static List<UsersDTO> listUsersInfo(TableTagBean ttb)
			throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" from Users u where 1=1 and u.userId<>'admin'");

		if (StringUtils.isNotEmpty(ttb.getCondition().get("userId"))) {
			sql.append(" and u.userId like " + "'%"
					+ ttb.getCondition().get("userId") + "%'");
		}

		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitName"))) {
			sql.append(" and u.unitName like  " + "'%"
					+ ttb.getCondition().get("unitName") + "%'");
		}

		if (StringUtils.isNotEmpty(ttb.getCondition().get("userName"))) {
			sql.append(" and u.userName like '%")
					.append(ttb.getCondition().get("userName")).append("%' ");
		}

		if ((StringUtils.isNotEmpty(ttb.getCondition().get("userStatus")))
				&& (ttb.getCondition().get("userStatus")) != "0") {
			sql.append(" and u.userStatus = ").append(
					ttb.getCondition().get("userStatus"));
		}

		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countByCondition(
					new StringBuilder().append("select count(*) ").append(sql)
							.toString(), params));
		}
		sql.append(" and not exists(select 1 from UserRelation ur where ur.userId=u.userId and ur.refType='").append(AdminConstants.YHRS9001_01).append("')");
		return BeanHelper.copyProperties(DaoUtil.listByCondition(
				new StringBuilder().append(sql)
						.append(" order by u.userOid desc").toString(), params,
				ttb.getPage(), ttb.getPageSize()), UsersDTO.class);
	}

	/**
	 * 系统信息查询
	 * 
	 * @throws ServiceException
	 * */
	public static List<SubSystemDTO> findSubSystemsInfo()
			throws ServiceException {
		List<SubSystem> list = DaoUtil.find("from SubSystem ");
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return BeanHelper.copyProperties(list, SubSystemDTO.class);
		}
	}

	/**
	 * 用户ID查询（增加用户时的ID校验）
	 * 
	 * @param usersId
	 * @throws ServiceException
	 * */
	public static boolean checkUserByUsersID(String usersId)
			throws ServiceException {
		List<Users> usersBo = DaoUtil.find("from Users u where u.userId = ? ",
				usersId);
		if (CollectionUtils.isEmpty(usersBo)) {
			return true;
		}
		return false;
	}

	/**
	 * 查询当前登录用户信息
	 * 
	 * */
	public static UsersDTO findLoginUserByUserId(String usersId)
			throws ServiceException {
		List<Users> usersList = DaoUtil.find(
				" from Users u where u.userId = ?", usersId.trim());
		if (!CollectionUtils.isEmpty(usersList)) {
			UsersDTO usersDTO = new UsersDTO();
			BeanHelper.copyProperties(usersList.get(0), usersDTO);
			return usersDTO;
		}
		return null;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param usersId
	 * */
	public static UsersDTO findUsersByUserId(String usersId)
			throws ServiceException {
		List<Users> usersList = DaoUtil.find(
				" from Users u where u.userId = ?", usersId.trim());
		if (!CollectionUtils.isEmpty(usersList)) {
			UsersDTO usersDTO = new UsersDTO();
			BeanHelper.copyProperties(usersList.get(0), usersDTO);
			return usersDTO;
		}
		return null;
	}

	/**
	 * 删除用户信息 deleteUsers
	 * */
	public static void deleteUsers(String userOid) throws ServiceException {
		Users usersBo = DaoUtil.get(Users.class, Long.valueOf(userOid));
		if (usersBo != null) {
			if (usersBo.getUserId() != null) {
				String userId = usersBo.getUserId().toString();
				List<UserSystemPosition> userSystemPositionlist = DaoUtil.find(
						" from  UserSystemPosition usp"
								+ " where usp.userId = ?", userId);
				if (!CollectionUtils.isEmpty(userSystemPositionlist)) {
					for (UserSystemPosition usp : userSystemPositionlist) {
						usp.delete();
					}
				}
			}

			usersBo.delete();
		}
	}

	/**
	 * 用户岗位信息查询
	 * 
	 * @param userId
	 * */
	public static List<UserPositionInfoDTO> findUsersPosition(String userId,String systemPositionOid) throws ServiceException {
		
		StringBuilder sql = new StringBuilder();
		if (StringUtils.isNotEmpty(userId)) {
			sql.append(sql)
					.append("select sp.system_position_oid, ")
					.append("sp.system_position_name , ")
					.append("sp.system_position_desc, ")
					.append(" trim(r.role_name) as functionName, ")
					// 数据角色
					.append(" trim(rr.role_name) as dataName, ")
					// 功能角色
					.append("   us.effective_date, ")
					.append("  us.expired_date, ")
					.append(" us.user_id")
					.append("  from YHB_USER_SP us, YHB_SYSTEM_POSITION sp ")
					.append(" left join YHB_ROLES r on r.role_id = sp.data_role_id  ")
					.append(" 	left join YHB_ROLES rr on rr.role_id = sp.function_role_id ")
					.append(" where us.system_position_oid = sp.system_position_oid and ")
					.append(" sp.system_position_oid in")
					.append(" (select us.system_position_oid from YHB_USER_SP us where us.user_id = ")
					.append("'").append(userId.trim()).append("')")
					.append("and us.user_id ='").append(userId.trim())
					.append("'");
			if (StringUtils.isNotEmpty(systemPositionOid)) {
				sql.append(" and sp.system_position_oid = "
						+ Long.valueOf(systemPositionOid.trim()));
			}
		}
		List<Object[]> listObj = DaoUtil.findWithSQL(sql.toString());
		if (!CollectionUtils.isEmpty(listObj)) {
			return BeanHelper
					.copyProperties(
							listObj,
							new BeanHelper.PropertiesHandler<Object[], UserPositionInfoDTO>() {
								public UserPositionInfoDTO getValue(Object[] src)
										throws ServiceException {
									UserPositionInfoDTO dto = new UserPositionInfoDTO();
									dto.setSystemPositionOid(DataConverUtils
											.toLong(src[0]));
									dto.setSystemPositionName(DataConverUtils
											.toString(src[1]));
									dto.setSystemPositionDesc(DataConverUtils
											.toString(src[2]));
									dto.setDataRoleName(DataConverUtils
											.toString(src[3]));
									dto.setFunctionRoleName(DataConverUtils
											.toString(src[4]));
									dto.setEffectiveDt(DataConverUtils
											.toDate(src[5]));
									dto.setExpiredDt(DataConverUtils
											.toDate(src[6]));
									dto.setUserId(DataConverUtils
											.toString(src[7]));
									return dto;
								}
							});
		}
		return null;
	}

	/**
	 * 用户的岗位信息查询
	 * */
	public static UserSystemPositionDTO findUSPositionBy(String usPositionOid,
			String userId) throws ServiceException {

		UserSystemPositionDTO usPosition = new UserSystemPositionDTO();
		UserSystemPosition usPositionBo = (UserSystemPosition) DaoUtil.find(
				"from UserSystemPosition usp where "
						+ "usp.systemPositionOid = ? and usp.userId = ? ",
				Long.valueOf(usPositionOid), userId).get(0);
		BeanHelper.copyProperties(usPositionBo, usPosition);
		return usPosition;
	}

	/**
	 * 
	 * 用户岗位添加
	 * */
	public static void addUsersPosition(String systemPositionOids, String userId)
			throws ServiceException {
		List<Users> usersList = DaoUtil.find(
				" from Users u where u.userId = ?", userId);
		if (!CollectionUtils.isEmpty(usersList)) {
			String[] positionOids = systemPositionOids.split(",");
			for (String positionOid : positionOids) {
				if (DaoUtil
						.get(SystemPosition.class, Long.valueOf(positionOid)) != null) {
					UserSystemPosition userSystemPosition = new UserSystemPosition();
					userSystemPosition.setCreatedByCode(UserContext
							.getLoginUserID());
					userSystemPosition.setCreatedByName(UserContext
							.getLoginUserName());
					userSystemPosition.setCreatedDate(DateUtil.now());
					userSystemPosition.setSystemPositionOid(Long
							.valueOf(positionOid));
					userSystemPosition.setUserId(userId);
					
					Date now = new Date();
					userSystemPosition.setUpdatedByCode(userId);
					userSystemPosition.setUpdatedByName(UserContext.getLoginUserName());
					userSystemPosition.setUpdatedDate(now);
					userSystemPosition.setEffectiveDate(now);
					userSystemPosition.setExpiredDate(DateUtil.addYear(now, 90));
					userSystemPosition.save();
				}
			}
		}
	}

	/**
	 * 用户岗位移除
	 * */
	public static void deleteUsersPosition(String systemPositionOid,
			String userId) throws ServiceException {
		UserSystemPosition userSystemPosition = (UserSystemPosition) DaoUtil
				.find(" from UserSystemPosition usp "
						+ "where usp.systemPositionOid = ? and usp.userId = ?  ",
						Long.valueOf(systemPositionOid), userId).get(0);
		if (userSystemPosition != null) {
			userSystemPosition.delete();
		}
	}

	/**
	 * 用户岗位添加的岗位信息查询(用户已经拥有的岗位不做查询)
	 * 
	 * */
	public static List<UserPositionInfoDTO> findAllUsersPosition(
			TableTagBean ttb) throws ServiceException {

		String userId = ttb.getCondition().get("userId");
		StringBuilder sql = new StringBuilder();
		if (StringUtils.isNotEmpty(userId)) {
			sql.append("select sp.system_position_oid, ")
					.append("sp.system_position_name, ")
					.append(" sp.system_position_desc, ")
					.append(" r.role_name as function_role , ")
					.append(" rr.role_name as data_role")
					.append(" from YHB_SYSTEM_POSITION sp ")
					.append(" left join YHB_ROLES r on r.role_id = sp.data_role_id ")
					.append("  left join YHB_ROLES rr  on rr.role_id = sp.function_role_id  where sp.system_position_oid not in ")
					.append(" (select jus.system_position_oid from YHB_USER_SP jus where jus.user_id = ")
					.append(" '").append(userId.trim()).append("')");
			if (StringUtils.isNotEmpty(ttb.getCondition().get(
					"systemPositionName"))) {
				sql.append(" and sp.system_position_name like  " + "'%"
						+ ttb.getCondition().get("systemPositionName") + "%'");
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countWithSQLByCondition(new StringBuilder()
					.append("select count(*) from (").append(sql).append("  ) t")
					.toString(), params));
		}
		StringBuilder lsql = new StringBuilder();
		lsql.append(" select  t.system_position_oid, ")
				.append(" t.system_position_name, ")
				.append("  t.system_position_desc, ")
				.append(" t.function_role, ").append("  t.data_role from (");

		// 分页查询
		List<Object[]> list = DaoUtil.listWithSQLByCondition(
				new StringBuilder(lsql).append(sql)
						.append(" order by sp.system_position_oid asc ) t")
						.toString(), params, ttb.getPage(), ttb.getPageSize());
		if (!CollectionUtils.isEmpty(list)) {
			return BeanHelper
					.copyProperties(
							list,
							new BeanHelper.PropertiesHandler<Object[], UserPositionInfoDTO>() {
								public UserPositionInfoDTO getValue(Object[] src)
										throws ServiceException {
									UserPositionInfoDTO dto = new UserPositionInfoDTO();
									dto.setSystemPositionOid(DataConverUtils
											.toLong(src[0]));
									dto.setSystemPositionName(DataConverUtils
											.toString(src[1]));
									dto.setSystemPositionDesc(DataConverUtils
											.toString(src[2]));
									dto.setDataRoleName(DataConverUtils
											.toString(src[3]));
									dto.setFunctionRoleName(DataConverUtils
											.toString(src[4]));
									return dto;
								}
							});
		}
		return null;
	}
	
	/**
	 * 通过来源OID和来源类型查询用户信息关系
	 * @param refOid
	 * @param refType
	 * @return
	 * @throws ServiceException
	 */
	public static UserRelation getUserRelationByRefOidAndRefType(Long refOid,String refType) throws ServiceException {
		String hql = "from UserRelation ur where ur.refOid=? and ur.refType=?";
		return DaoUtil.uniqueResult(hql, refOid, refType);
	}
	
	/**
	 * 通过用户ID和来源类型查询用户信息关系表
	 * @param userId
	 * @param refType
	 * @return
	 * @throws ServiceException
	 */
	public static UserRelation getUserRelationByUserIdAndRefType(String userId,String refType) throws ServiceException {
		String hql = "from UserRelation ur where ur.userId=? and ur.refType=?";
		return DaoUtil.uniqueResult(hql, userId, refType);
	}
	
	/**
	 * 通过用户ID查询用户信息关系
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public static UserRelation getUserRelationByUserId(String userId) throws ServiceException {
		String hql = "from UserRelation ur where ur.userId=?";
		return DaoUtil.uniqueResult(hql, userId);
	}
}
