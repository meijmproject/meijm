package com.yh.hr.res.pt.service;

import java.util.List;
import com.yh.hr.res.pt.dto.PtWageHistoryItemsDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtWageHistoryItemsService {

	public List<PtWageHistoryItemsDTO> listPtWageHistoryItems(Long ptWageHistoryOid) throws ServiceException;
	
	public List<PtWageHistoryItemsDTO> getPtWageHistoryItemsList(Long ptWageHistoryOid, String wageItemCode) throws ServiceException;
	
	public PtWageHistoryItemsDTO getPtWageHistoryItems(Long ptWageHistoryOid,String wageItemCode) throws ServiceException;

	public void createPtWageHistoryItems(PtWageHistoryItemsDTO ptWHistoryItemsDTO) throws ServiceException;

	//public PtWageHistoryItemsDTO getPtWageHistoryItems(Long ptWageHistoryItemOid) throws ServiceException;

	public void deletePtWageHistoryItems(Long ptWageHistoryItemOid) throws ServiceException;

	public void modifyPtWageHistoryItems(PtWageHistoryItemsDTO ptWageHistoryItemsDTO) throws ServiceException;

	public PtWageHistoryItemsDTO getPtWageHistoryItems(Long ptWageHistoryItemOid)throws ServiceException;

	public void modifyOraddPtWageHistoryItems(PtWageHistoryItemsDTO ptWageHistoryItemsDTO)throws ServiceException;
	
	/**
	 * 新增工资历史明细信息（同时创建工资逻辑关系）
	 * @param ptWageHistoryItemsDTO
	 * @throws ServiceException
	 */
	public void save(PtWageHistoryItemsDTO ptWageHistoryItemsDTO) throws ServiceException ;
}
