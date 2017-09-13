package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtEngageContractRenewDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 续签合同业务信息Service
 * @author xiongyx
 *
 * 
 */
public interface PtEngageContractRenewService {

	/**
	 * 新增续签合同业务信息
	 * @param contractRenewDTO
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public void createPtEngageContractRenew(PtEngageContractRenewDTO contractRenewDTO)throws ServiceException;

	/**
	 * 根据ID查询续签合同业务信息
	 * @param bizEngageContractOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public PtEngageContractRenewDTO getPtEngageContractRenewByOid(Long bizEngageContractOid) throws ServiceException;

	/**
	 * 更新续签合同业务信息
	 * @param contractRenewDTO
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public void updatePtEngageContractRenew(PtEngageContractRenewDTO contractRenewDTO) throws ServiceException;

	/**
	 * 根据ID删除续签合同业务信息
	 * @param bizEngageContractOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public void deletePtEngageContractRenewByOid(Long bizEngageContractOid) throws ServiceException;
}
