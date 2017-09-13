package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtWageHistoryDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtWageHistoryService {
	
	/**
	 * 工资历史详细信息列表
	 * @param 
	 * @return
	 * @throws ServiceException
	 */	
	public List<PtWageHistoryDTO> listWageHistoryStruts(Long ptWageHistoryOid) throws ServiceException;
	/**
	 * 创建工资历史详细信息列表
	 * @param 
	 * @return
	 * @throws ServiceException
	 */	
	public void createPtWageHistory(PtWageHistoryDTO dto) throws ServiceException;
	
	/**
	 * 删除工资历史详细信息列表
	 * @param 
	 * @return
	 * @throws ServiceException
	 */	
	public void deletePtWageHistory(Long ptWageHistoryOid) throws ServiceException;
	/**
	 * 修改工资历史详细信息列表
	 * @param 
	 * @return
	 * @throws ServiceException
	 */		
	public void modifyPtWageHistory(PtWageHistoryDTO ptWageHistoryDTO) throws ServiceException;
	
	/**
	 * 根据业务人员ID、生效标识查询人员工资历史
	 * @param bizPersonOid 业务人员ID
	 * @param effectFlag 生效标识
	 * @return List<PtWageHistoryDTO> 业务工资历史列表
	 * @throws ServiceException
	 */
	public List<PtWageHistoryDTO> getPtWageHistoryList(Long bizPersonOid,String effectFlag) throws ServiceException;
	
	/**
	 * 根 根据业务工资历史id 查询此条数据有没有修正值
	 * @param bizPersonOid 业务人员ID
	 * @return List<PtWageHistoryDTO> 业务工资历史列表
	 * @throws ServiceException
	 */
	public List<PtWageHistoryDTO> getModifyPtWageHistoryList(Long calWageHistoryOid) throws ServiceException;
	/**
	 * 根据条件判断是修改还是新增操作
	 * @return  
	 * @throws ServiceException
	 */	
	public void modifyORaddPtWageHistory(PtWageHistoryDTO ptWageHistoryDTO)throws ServiceException;
	/**
	 * 根据人员ID，生效标识删除人员业务工资历史（先删除逻辑关系、工资项、后删除工资历史记录）
	 * @param bizPersonOid
	 * @param effectiveFlag
	 * @throws ServiceException
	 */
	public void removePtWageHistory(Long bizPersonOid,String effectiveFlag) throws ServiceException;

	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtWageHistoryByBizPersonOid(Long bizPersonOid) throws ServiceException;

	/**
	 * 创建PtWageHistory，同时创建DTO中的工资项、工资逻辑
	 * @param ptWageHistoryDTO
	 * @throws ServiceException
	 */
	public Long save(PtWageHistoryDTO ptWageHistoryDTO) throws ServiceException;

	/**
	 * 更新计算Id
	 * @param ptWageHistoryOid
	 * @param calPtWageHistoryOid
	 * @throws ServiceException
	 */
	public void updateCalPtWageHistoryOid(Long ptWageHistoryOid,Long calPtWageHistoryOid) throws ServiceException;
	
	/**
	 * 根据主键ID查询工资历史
	 * @param ptWageHistoryOid
	 * @return
	 * @throws ServiceExcption
	 */
	public PtWageHistoryDTO getPtWageHistoryDTO(Long ptWageHistoryOid) throws ServiceException;
	/**根据changeType(变动类型)、startDateOfWage(起薪时间)
	 * 查询工资历史详细信息
	 * @return
	 * @throws ServiceException
	 * */
	public PtWageHistoryDTO getPtWageHistoryDTOByChangeType(Long bizPersonOid , String changeType,String startDateOfWageStr,String effectiveFlag)throws ServiceException;
}
