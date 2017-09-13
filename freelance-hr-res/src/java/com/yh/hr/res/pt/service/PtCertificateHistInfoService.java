package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtCertificateHistInfoDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 执业注册资格历史信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtCertificateHistInfoService {

	/**
	 * 创建执业注册历史业务信息
	 * @param ptCertificateHistInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtCertificateHistInfoDTO ptCertificateHistInfoDTO) throws ServiceException;
	
	/**
	 * 修改执业注册历史业务信息
	 * @param ptCertificateHistInfoDTO
	 * @throws ServiceException
	 */
	public void update(PtCertificateHistInfoDTO ptCertificateHistInfoDTO) throws ServiceException;

	/**
	 * 通过主键ID删除执业注册历史业务信息
	 * @param certificateHistOid
	 * @throws ServiceException
	 */
	public void delete(Long certificateHistOid) throws ServiceException;
	
	/**
	 * 通过业务人员OID删除执业注册历史业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过主键ID获取执业注册历史信息
	 * @param certificateHistOid
	 * @return
	 * @throws ServiceException
	 */
	public PtCertificateHistInfoDTO get(Long certificateHistOid) throws ServiceException;
	
	/**
     * 根据业务人员OID查询该人员执业注册历史业务信息列表
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
	public List<PtCertificateHistInfoDTO> getPtCertificateHistInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
     * 根据基础OID查询该人员执业注册历史业务信息列表
     * @param baseCertificateHistOid
     * @return
     * @throws ServiceException
     */
	public List<PtCertificateHistInfoDTO> getPtCertificateHistInfoListByBaseCertificateHistOid(Long baseCertificateHistOid) throws ServiceException;
}
