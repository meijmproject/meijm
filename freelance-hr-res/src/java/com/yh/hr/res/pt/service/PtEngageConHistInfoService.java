package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtEngageConHistInfoDTO;
import com.yh.hr.res.pt.dto.PtEngageContractInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 合同变动历史业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtEngageConHistInfoService {
	
	/**
	 * 创建合同变动历史业务信息
	 * @param ptEngageConHistInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtEngageConHistInfoDTO ptEngageConHistInfoDto) throws ServiceException;

	/**
	 * 通过主键ID获取合同变动历史业务信息
	 * @param ptEngageConHistInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtEngageConHistInfoDTO get(Long ptEngageConHistInfoId) throws ServiceException;
    
	/**
	 * 通过主键ID获取合同变动历史业务信息
	 * @param ptEngageConHistInfoId
	 * @throws ServiceException
	 */  
	public void update(PtEngageConHistInfoDTO ptEngageConHistInfoDto) throws ServiceException;

	/**
	 * 通过主键ID删除合同变动历史业务信息
	 * @param ptEngageConHistInfoId
	 * @throws ServiceException
	 */   
	public void delete(java.lang.Long ptEngageConHistInfoId) throws ServiceException;
    
	/**
	 * 新增时检查合同编号是否重复
	 * @param ptEngageConHistInfoDto
	 * @return
	 * @throws ServiceException
	 **/
    public boolean checkContractNo(PtEngageConHistInfoDTO ptEngageConHistInfoDto) throws ServiceException;

	/**
	 * 通过业务人员OID查找该人员的合同历史信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEngageConHistInfoDTO> listPtEngageConHistInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的合同业务信息
	 * @param baseEngageContractOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEngageContractInfoDTO> listPtEngageContractInfoByBaseEngageContractOid(Long baseEngageContractOid) throws ServiceException;

	/**
	 * 通过业务人员OID删除该人员的所有合同历史信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
}
