package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtWageInfluenceDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringMap;

public interface PtWageInfluenceService {
	public List<PtWageInfluenceDTO> listWageHistory(Long bizPersonOid) throws ServiceException;
	/*
	 * 查询人员影响工资信息
	 * */
	public  PtWageInfluenceDTO getPtWageInfluenceDTOById(Long bizPersonOid) throws ServiceException;
	
	/*
	 * 修改人员影响工资信息
	 * */
	public  void updatePtWageInfluence(PtWageInfluenceDTO PtWageInfluenceDTO) throws ServiceException;

	/*
	 * 添加人员影响工资信息
	 * */
	public void addPtWageInfluence(PtWageInfluenceDTO ptWageInfluenceDTO)throws ServiceException;
	/*
	 * 删除人员影响工资信息
	 * */
	public void deletePtWageInfluence(Long bizPersonOid)throws ServiceException;
	
	/*
	 * 保存或新增方法
	 */
	public void saveOrUpdate(PtWageInfluenceDTO ptWageInfluenceDTO)
			throws ServiceException;
	
	
	/*
	 * 工龄计算
	 */
	public void  serviceYear(PtWageInfluenceDTO ptWageInfluenceDTO) throws ServiceException;
	
	
	/**
	 * 人员权限单位id集合
	 * @param userId
	 * @return
	 * @throws ServiceException
	 *//*
	public List<String> findAuthorityList(String userId) throws ServiceException;
	*/
	
	/**
	 * 查找已授权单位的详细信息
	 * @param authorizationOids
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> findUnitListByAuth(List<String> authorizationOids, StringMap params) throws ServiceException;
	
	//------我是分隔线------
	/*
	 * 修改人员影响工资信息
	 * */
	public void updateWageSyInfluence(PtWageInfluenceDTO wageSyInfluenceDTO)
			throws ServiceException ;
	/*
	 * 查询人员影响工资信息
	 * */

	public PtWageInfluenceDTO getWageSyInfluenceDTOById(Long personOid)
			throws ServiceException ;
	
	public List<PtWageInfluenceDTO> listverPbWageInfluenceDTO(Long personOid)
			throws ServiceException ;
	
	//查询业务库人员影响工资业务
	public List<PtWageInfluenceDTO> listWageInfluenceDTO(Long personOid)
			throws ServiceException ;
	/*
	 * 删除人员影响工资信息
	 * */
	public void deleteWageSyInfluence(Long personOid) throws ServiceException ;
	/*
	 * 添加人员影响工资信息
	 * */
	public void addWageSyInfluence(PtWageInfluenceDTO wageSyInfluenceDTO)
			throws ServiceException ;



	
	
}
