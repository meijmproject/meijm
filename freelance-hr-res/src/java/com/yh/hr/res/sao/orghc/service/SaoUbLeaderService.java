package com.yh.hr.res.sao.orghc.service;

import java.util.List;
import com.yh.hr.res.sao.orghc.dto.SaoUbLeaderDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 领导职数信息查询、维护接口
 * @author	lenghh
 * @version	1.0,	16/09/05
 */

public interface SaoUbLeaderService {
	
	/**
	 * 获取领导职数基本信息
	 * @param leaderOid
	 * @return SaoUbLeaderDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public SaoUbLeaderDTO getSaoUbLeaderDTO(Long leaderOid) throws ServiceException;
	
	/**
	 * 增加领导职数列表
	 * @param unitOid
	 * @return List<SaoUbLeaderDTO>
	 * @throws ServiceException 
	 * @author lenghh
	 */
	public void addSaoUbLeaderDTO(SaoUbLeaderDTO saoUbLeaderDTO) throws ServiceException;
	
	/**
	 * 修改领导职数列表
	 * @param unitOid
	 * @return List<SaoUbLeaderDTO>
	 * @throws ServiceException 
	 * @author lenghh
	 */
	public void updateSaoUbLeaderDTO(SaoUbLeaderDTO saoUbLeaderDTO) throws ServiceException;
	
	/**
	 * 删除领导职数
	 * @param unitOid
	 * @return List<SaoUbLeaderDTO>
	 * @throws ServiceException 
	 * @author lenghh
	 */
	public void deleteSaoUbLeaderDTO(Long leaderOid) throws ServiceException;
	
	/**
	 * 根据单位获取领导职数列表
	 * @param unitOid
	 * @return List<SaoUbLeaderDTO>
     * @throws ServiceException 
     * @author lenghh
	 */
	public List<SaoUbLeaderDTO> listSaoUbLeaderDTOByUnitOid(Long unitOid) throws ServiceException;
	
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
