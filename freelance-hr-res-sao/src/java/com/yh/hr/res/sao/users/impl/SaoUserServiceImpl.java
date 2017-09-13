package com.yh.hr.res.sao.users.impl;

import com.yh.admin.dto.UserRelationDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.position.service.SystemPositionService;
import com.yh.admin.users.service.UsersService;
import com.yh.hr.res.sao.users.SaoUserService;
import com.yh.hr.res.sao.users.dto.SaoUserRelationDTO;
import com.yh.hr.res.sao.users.dto.SaoUsersDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
/**
 * 账号信息操作 service接口
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public class SaoUserServiceImpl implements SaoUserService {

	private UsersService usersService;
	private SystemPositionService systemPositionService;
	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public void setSystemPositionService(SystemPositionService systemPositionService) {
		this.systemPositionService = systemPositionService;
	}

	/**
	 * 通过账号ID查找该账号是否已存在
	 * @param usersId
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkUserByUsersID(String usersId) throws ServiceException {
		return usersService.checkUserByUsersID(usersId);
	}

	/**
	 * 通过账号ID获取账号信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public SaoUsersDTO findUsersByUserId(String userId) throws ServiceException {
		return BeanHelper.copyProperties(usersService.findUsersByUserId(userId), SaoUsersDTO.class);
	}

	/**
	 * 创建账号信息
	 * @param saoUsersDto
	 * @throws ServiceException
	 */
	public void createUsersInfo(SaoUsersDTO saoUsersDto) throws ServiceException {
		UsersDTO usersDTO = new UsersDTO();
		BeanHelper.copyRtnProperties(saoUsersDto, usersDTO);
		usersService.createUsersInfo(usersDTO);
	}

	/**
	 * 修改账号信息
	 * @param saoUsersDto
	 * @throws ServiceException
	 */
	public void updateUsersInfo(SaoUsersDTO saoUsersDto) throws ServiceException {
		UsersDTO usersDTO = new UsersDTO();
		BeanHelper.copyRtnProperties(saoUsersDto, usersDTO);
		usersService.updateUsersInfo(usersDTO);
	}

	/**
	 * 删除账号信息
	 * @param userOid
	 * @param refType
	 * @throws ServiceException
	 */
	public void deleteUsers(String userId,String refType) throws ServiceException {
		UsersDTO usersDTO = usersService.findUsersByUserId(userId);
		if(usersDTO!=null) {
			usersService.deleteUsers(DataConverUtils.toString(usersDTO.getUserOid()),refType);
		}
	}

	/**
	 * 创建或修改用户关系表信息
	 * @param saoUserRelationDTO
	 * @throws ServiceException
	 */
	public void createOrUpdateUserRelation(SaoUserRelationDTO saoUserRelationDTO) throws ServiceException {
		UserRelationDTO userRelationDTO = new UserRelationDTO();
		BeanHelper.copyProperties(saoUserRelationDTO, userRelationDTO);
		usersService.createOrUpdateUserRelation(userRelationDTO);
	}
	
	/**
	 * 通过来源OID和来源类型查询用户信息关系表信息
	 * @param refOid
	 * @param refType
	 * @return
	 * @throws ServiceException
	 */
	public SaoUserRelationDTO getUserRelationDTOByRefOidAndRefType(Long refOid, String refType) throws ServiceException {
		return BeanHelper.copyProperties(usersService.getUserRelationDTOByRefOidAndRefType(refOid, refType), SaoUserRelationDTO.class);
	}
	
	/**
	 * 增加用户和系统岗位关联关系
	 * @param userId
	 * @param systemPositionOid
	 * @throws ServiceException
	 */
	public void addPositionUser(String userId, Long systemPositionOid)throws ServiceException {
		systemPositionService.addPositionUser(userId, systemPositionOid);
	}
	
	/**
	 * 修改用户和系统岗位关联关系
	 * @param userId
	 * @param oldSystemPositionOid
	 * @param newSystemPositionOid
	 * @throws ServiceException
	 */
	public void updatePositionUser(String userId, Long oldSystemPositionOid, Long newSystemPositionOid) throws ServiceException {
		systemPositionService.updateUserSystemPosition(userId, oldSystemPositionOid, newSystemPositionOid);
	}
}
