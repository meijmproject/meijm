package com.yh.admin.users.service;

import java.util.List;

import com.yh.admin.bo.UserRelation;
import com.yh.admin.bo.UserSystemPosition;
import com.yh.admin.bo.Users;
import com.yh.admin.dto.SubSystemDTO;
import com.yh.admin.dto.UserPositionInfoDTO;
import com.yh.admin.dto.UserRelationDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.users.queryhelper.UserSystemPositionQueryHelper;
import com.yh.admin.users.queryhelper.UsersQueryHelper;
import com.yh.admin.util.AdminConstants;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.CryptoUtil;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */
public class UsersService {

	public UsersDTO checkUserPswd(String userCode, String pw) throws ServiceException {
		
		return UsersQueryHelper.checkUserPswd(userCode, pw);
	}

	public Users checkUserRight(String userId, String systemCode) throws ServiceException {
		return UsersQueryHelper.checkUserRight(userId, systemCode);
	}

	public UsersDTO findUsers(String userCode) throws ServiceException {
		return UsersQueryHelper.findByUserId(userCode);
	}
	public List<UsersDTO> listUsersInfo(TableTagBean ttb)throws ServiceException{
		return UsersQueryHelper.listUsersInfo(ttb);
	}
	public UsersDTO getUserByOid(String userOid)throws ServiceException{
		return UsersQueryHelper.getUserByOid(userOid);
	}
	
	public UsersDTO findLoginUserByUserId()throws ServiceException{
		return UsersQueryHelper.findLoginUserByUserId(UserContext.getLoginUserID());
	}
	
	public UsersDTO findUsersByUserId(String userId)throws ServiceException{
		return UsersQueryHelper.findUsersByUserId(userId);
	}
	public  void updateUsersInfo(UsersDTO usersDto) throws ServiceException {
		Users users = DaoUtil.get(Users.class, usersDto.getUserOid());
		BeanHelper.copyProperties(usersDto,users);
		users.setUpdatedByCode(UserContext.getLoginUserID());
		users.setUpdatedByName(UserContext.getLoginUserName());
		users.setUpdatedDate(DateUtil.now());
		users.update();
	}
	public  void createUsersInfo(UsersDTO usersDto) throws ServiceException {
		Users users = new Users();
		BeanHelper.copyProperties(usersDto,users);
		
		users.setRegistDate(DateUtil.now());
		users.setUpdatedByCode(UserContext.getLoginUserID());
		users.setUpdatedByName(UserContext.getLoginUserName());
		users.save();
	}
	public  void addUsersPosition(String systemPositionOids,String userId) throws ServiceException {
		UsersQueryHelper.addUsersPosition(systemPositionOids,userId);
	}
	
	public  void deleteUsersPosition(String systemPositionOid,String userId) throws ServiceException {
		UsersQueryHelper.deleteUsersPosition(systemPositionOid,userId);
	}
	
	public boolean checkUserByUsersID(String usersId) throws ServiceException {
		return UsersQueryHelper.checkUserByUsersID(usersId);
	}
	public  List<SubSystemDTO> findSubSystemsInfo() throws ServiceException {
		return UsersQueryHelper.findSubSystemsInfo();
	}
	public  void deleteUsers(String userOid,String refType) throws ServiceException {
		Users users = DaoUtil.get(Users.class,Long.valueOf(userOid));
		deleteUserRelation(users.getUserId(),refType);
		
		UsersQueryHelper.deleteUsers(userOid);
	}
	public  List<UserPositionInfoDTO> findUsersPosition(String userId, String systemPositionOid) throws ServiceException {
		return UsersQueryHelper.findUsersPosition(userId,systemPositionOid);
	}
	
	public  List<UserPositionInfoDTO> findAllUsersPosition(TableTagBean ttb) throws ServiceException {
		return UsersQueryHelper.findAllUsersPosition(ttb);
	}
	public  void updateUsersPosition(UserSystemPositionDTO userSystemPositionDTO) throws ServiceException {
		UserSystemPosition userSystemPosition = UserSystemPositionQueryHelper.get(userSystemPositionDTO.getUserId(), userSystemPositionDTO.getSystemPositionOid());
		if (userSystemPosition != null) {
			BeanHelper.copyProperties(userSystemPositionDTO,userSystemPosition);
			userSystemPosition.setUpdatedByCode(UserContext.getLoginUserID());
			userSystemPosition.setUpdatedByName(UserContext.getLoginUserName());
			userSystemPosition.setUpdatedDate(DateUtil.now());
			userSystemPosition.update();
		}
	}
	
	public UserSystemPositionDTO findUSPositionBy(String systemPositionOid,String userId) throws ServiceException {
		
		return UsersQueryHelper.findUSPositionBy(systemPositionOid, userId);
	}

	public void reSetPs(String userOid)  throws ServiceException{
		Users users = DaoUtil.get(Users.class,Long.valueOf(userOid));
		users.setPassword(CryptoUtil.md5(AdminConstants.DEFAULT_PASSWORD));
		users.setUpdatedByCode(UserContext.getLoginUserID());
		users.setUpdatedByName(UserContext.getLoginUserName());
		users.setUpdatedDate(DateUtil.now());
		users.update();
		
	}
	
	/**
	 * 创建用户关系信息
	 * @param userRelationDTO
	 * @throws ServiceException
	 */
	public void createUserRelation(UserRelationDTO userRelationDTO) throws ServiceException {
		UserRelation userRelation = new UserRelation();
		BeanHelper.copyProperties(userRelationDTO, userRelation);
		userRelation.save();
	}

	/**
	 * 修改用户关系信息
	 * @param userRelationDTO
	 * @throws ServiceException
	 */
	public void updateUserRelation(UserRelationDTO userRelationDTO) throws ServiceException {
		UserRelation userRelation = UsersQueryHelper.getUserRelationByUserIdAndRefType(userRelationDTO.getUserId(), userRelationDTO.getRefType());
		if(userRelation!=null) {
			userRelation.setRefOid(userRelationDTO.getRefOid());
			userRelation.update();
		}
	}

	/**
	 * 创建或者用户关系信息
	 * @param userRelationDTO
	 * @throws ServiceException
	 */
	public void createOrUpdateUserRelation(UserRelationDTO userRelationDTO) throws ServiceException {
		UserRelation userRelation = UsersQueryHelper.getUserRelationByUserId(userRelationDTO.getUserId());
		if(userRelation!=null) {
			UserRelation boRelation = new UserRelation();
			BeanHelper.copyProperties(userRelation, boRelation);
			userRelation.delete();
			
			boRelation.setRefOid(userRelationDTO.getRefOid());
			boRelation.setRefType(userRelationDTO.getRefType());
			boRelation.save();
		}else {
			userRelation = new UserRelation();
			BeanHelper.copyProperties(userRelationDTO, userRelation);
			userRelation.save();
		}
	}

	/**
	 * 通过用户ID和来源类型删除用户信息关系表
	 * @param userId
	 * @param refType
	 * @throws ServiceException
	 */
	public void deleteUserRelation(String userId,String refType) throws ServiceException {
		UserRelation userRelation = UsersQueryHelper.getUserRelationByUserIdAndRefType(userId,refType);
		if(userRelation!=null) {
			userRelation.delete();
		}
	}
	
	/**
	 * 通过来源OID和来源类型查询用户信息关系表
	 * @param refOid
	 * @param refType
	 * @return
	 * @throws ServiceException
	 */
	public UserRelationDTO getUserRelationDTOByRefOidAndRefType(Long refOid,String refType) throws ServiceException {
		return BeanHelper.copyProperties(UsersQueryHelper.getUserRelationByRefOidAndRefType(refOid, refType), UserRelationDTO.class);
	}
	
	/**
	 * 通过用户ID和来源类型查询用户信息关系
	 * @param userId
	 * @param refType
	 * @return
	 * @throws ServiceException
	 */
	public UserRelationDTO getUserRelationDTOByUserIdAndRefType(String userId,String refType) throws ServiceException {
		return BeanHelper.copyProperties(UsersQueryHelper.getUserRelationByUserIdAndRefType(userId,refType), UserRelationDTO.class);
	}
	
	/**
	 * 通过用户ID查询用户信息关系
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public UserRelationDTO getUserRelationDTOByUserId(String userId) throws ServiceException {
		return BeanHelper.copyProperties(UsersQueryHelper.getUserRelationByUserId(userId), UserRelationDTO.class);
	}
}
