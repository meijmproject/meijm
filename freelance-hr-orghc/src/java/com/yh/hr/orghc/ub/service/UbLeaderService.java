package com.yh.hr.orghc.ub.service;

import java.util.List;
import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.platform.core.exception.ServiceException;

public interface UbLeaderService {

	/**
	 * 获取领导职数信息
	 * @param leaderOid
	 * @return UbLeader
     * @throws ServiceException 
	 */
	public UbLeaderDTO getUbOrgDTOById(Long leaderOid) throws ServiceException;
	
	/**
	 * 根据单位OID获取领导职数列表
	 * @param unitOid
	 * @return List<UbOrgDTO>
     * @throws ServiceException 
	 */
	public List<UbLeaderDTO> listByUnitOid(Long unitOid) throws ServiceException;
	
	/**
	 * 新增领导职数
	 * @param ubOrgDTO
     * @throws ServiceException 
	 */
	public void createLeaderInfo(UbLeaderDTO ubLeaderDTO) throws ServiceException;

	/**
	 * 新增领导职数--单位信息校核
	 * @param ubOrgDTO
	 * @param chgCount
     * @throws ServiceException 
	 */
	public void createLeaderInfo(UbLeaderDTO ubLeaderDTO,Long chgCount) throws ServiceException;

	/**
	 * 修改领导职数
	 * @param ubOrgDTO
     * @throws ServiceException 
	 */
	public void updateLeaderInfo(UbLeaderDTO ubLeaderDTO) throws ServiceException;

	/**
	 * 修改领导职数--单位信息校核
	 * @param ubOrgDTO
	 * @param chgCount
     * @throws ServiceException 
	 */
	public void updateLeaderInfo(UbLeaderDTO ubLeaderDTO,Long chgCount) throws ServiceException;

	/**
	 * 删除领导职数
	 * @param orgOid
     * @throws ServiceException 
	 */
	public void deleteLeaderInfo(Long leaderOid) throws ServiceException;

	/**
	 * 获取主管单位领导职级核定数
	 * @param adminUnitOid
	 * @param positionLevelCode
	 * @return num
     * @throws ServiceException 
     * @author lenghh
	 */
	public int countAdminUnitLeader(Long adminUnitOid,String positionLevelCode) throws ServiceException;
}
