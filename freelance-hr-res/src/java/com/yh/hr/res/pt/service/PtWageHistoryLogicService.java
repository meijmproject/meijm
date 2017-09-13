package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtWageHistoryLogicDTO;
import com.yh.platform.core.exception.ServiceException;


public interface PtWageHistoryLogicService {
	public List<PtWageHistoryLogicDTO> listPtWageHistoryLogic(Long ptWageHistoryItemOid) throws ServiceException;
	/**
	 * 工资历史逻辑表信息删除
	 * @param logicCode 
	 * @throws ServiceException
	 */
	public void deletePtWageHistoryLogic(Long ptWageHistoryItemOid, String logicCode)throws ServiceException;//, Long logicCode)throws ServiceException;
	/**
	 * 新增工资历史逻辑表信息
	 * @throws ServiceException
	 */	
	public void createPtWageHistoryLogic(PtWageHistoryLogicDTO ptWageHistoryLogicDTO)throws ServiceException;
	/**
	 * 获取工资历史逻辑表信息
	 * @throws ServiceException
	 */	
	public PtWageHistoryLogicDTO getPtWageHistoryLogic(Long ptWageHistoryItemOid)throws ServiceException;
	/**
	 * 修改工资历史逻辑表信息
	 * @throws ServiceException
	 */		
	public void modifyPtWageHistoryLogic(PtWageHistoryLogicDTO ptWageHistoryLogicDTO)throws ServiceException;
	/**
	 * 根据ptWageHistoryItemOid、logicCode
	 * 获得工资历史逻辑表信息
	 * @throws ServiceException
	 */	
	public PtWageHistoryLogicDTO getPtWageHistoryLogicBylogicCode(Long ptWageHistoryItemOid, String logicCode)throws ServiceException;
	/**
	 * 根据ptWageHistoryItemOid、wageItemCode
	 * 获得工资历史逻辑表信息
	 * @throws ServiceException
	 */	
	public List<PtWageHistoryLogicDTO> getPtWageHistoryLogicBywageItemCode(Long ptWageHistoryItemOid, String wageItemCode)throws ServiceException;
	/**
	/**判断是修改还是新增
	 * 工资历史逻辑表信息
	 */			
	public void modifyOrAddPtWageHistoryLogic(PtWageHistoryLogicDTO ptWageHistoryLogicDTO)throws ServiceException;
		


}
