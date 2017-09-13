package com.yh.hr.res.sao.users;

import com.yh.hr.res.sao.users.dto.SaoUserRelationDTO;
import com.yh.hr.res.sao.users.dto.SaoUsersDTO;
import com.yh.platform.core.exception.ServiceException;

public interface SaoUserService {

	/**
	 * 通过账号ID查找该账号是否已存在
	 * @param usersId
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkUserByUsersID(String usersId) throws ServiceException;
	
	/**
	 * 创建账号信息
	 * @param saoUsersDto
	 * @throws ServiceException
	 */
	public void createUsersInfo(SaoUsersDTO saoUsersDto) throws ServiceException;
	
	/**
	 * 修改账号信息
	 * @param saoUsersDto
	 * @throws ServiceException
	 */
	public void updateUsersInfo(SaoUsersDTO saoUsersDto) throws ServiceException;
	
	/**
	 * 删除账号信息
	 * @param userOid
	 * @param refType
	 * @throws ServiceException
	 */
	public void deleteUsers(String userId,String refType)throws ServiceException;
	
	/**
	 * 通过账号ID获取账号信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public SaoUsersDTO findUsersByUserId(String userId)throws ServiceException;
	
	/**
	 * 创建或修改用户关系表信息
	 * @param saoUserRelationDTO
	 * @throws ServiceException
	 */
	public void createOrUpdateUserRelation(SaoUserRelationDTO saoUserRelationDTO) throws ServiceException;
	
	/**
	 * 通过来源OID和来源类型查询用户信息关系表信息
	 * @param refOid
	 * @param refType
	 * @return
	 * @throws ServiceException
	 */
	public SaoUserRelationDTO getUserRelationDTOByRefOidAndRefType(Long refOid, String refType) throws ServiceException;
	
	/**
	 * 增加用户和系统岗位关联关系
	 * @param userId
	 * @param systemPositionOid
	 * @throws ServiceException
	 */
	public void addPositionUser(String userId, Long systemPositionOid)throws ServiceException;

	/**
	 * 修改用户和系统岗位关联关系
	 * @param userId
	 * @param oldSystemPositionOid
	 * @param newSystemPositionOid
	 * @throws ServiceException
	 */
	public void updatePositionUser(String userId, Long oldSystemPositionOid, Long newSystemPositionOid)throws ServiceException;
}
