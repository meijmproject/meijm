package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtEngageContractInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 合同业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtEngageContractInfoService {
	
	/**
	 * 新增合同业务信息
	 * @param ptEngageContractInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtEngageContractInfoDTO ptEngageContractInfoDto) throws ServiceException;

	/**
	 * 通过主键ID获取合同业务信息
	 * @param ptEngageContractInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtEngageContractInfoDTO get(Long ptEngageContractInfoId) throws ServiceException;
    
	/**
	 * 修改合同业务信息
	 * @param ptEngageContractInfoDto
	 * @throws ServiceException
	 */   
	public void update(PtEngageContractInfoDTO ptEngageContractInfoDto) throws ServiceException;

	/**
	 * 通过主键ID删除合同业务信息
	 * @param ptEngageContractInfoId
	 * @throws ServiceException
	 */   
	public void delete(Long ptEngageContractInfoId) throws ServiceException;

	/**
	 * 新增时检查合同编号是否重复
	 * @param ptEngageContractInfoDto
	 * @return
	 * @throws ServiceException
	 **/
	public boolean checkContractNo(PtEngageContractInfoDTO ptEngageContractInfoDto) throws ServiceException;
	
	/**
	 * 验证在聘状态规则
	 * @param ptEngageContractInfoDto
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkStatus(PtEngageContractInfoDTO ptEngageContractInfoDto) throws ServiceException;

	/**
	 * 通过业务人员OID查找该人员的合同业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEngageContractInfoDTO> listPtEngageContractInfoByPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的合同业务信息
	 * @param baseEngageContractOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEngageContractInfoDTO> listPtEngageContractInfoByBaseEngageContractOid(Long baseEngageContractOid) throws ServiceException;

	/**
	 * 通过业务人员OID删除合同业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
}
