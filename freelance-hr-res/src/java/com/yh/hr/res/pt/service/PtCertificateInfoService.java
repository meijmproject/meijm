package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtCertificateInfoDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 执业注册资格信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtCertificateInfoService {

	/**
	 * 创建执业注册业务信息
	 * @param ptCertificateInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtCertificateInfoDTO ptCertificateInfoDTO) throws ServiceException;
	
	/**
	 * 修改执业注册业务信息
	 * @param ptCertificateInfoDTO
	 * @throws ServiceException
	 */
	public void update(PtCertificateInfoDTO ptCertificateInfoDTO) throws ServiceException;

	/**
	 * 通过主键ID删除执业注册业务信息
	 * @param certificateOid
	 * @throws ServiceException
	 */
	public void delete(Long certificateOid) throws ServiceException;
	
	/**
	 * 通过业务人员OID删除执业注册业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过主键ID获取执业注册信息
	 * @param certificateOid
	 * @return
	 * @throws ServiceException
	 */
	public PtCertificateInfoDTO get(Long certificateOid) throws ServiceException;
	
	/**
     * 根据业务人员OID查询该人员执业注册业务信息
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
	public List<PtCertificateInfoDTO> getPtCertificateInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
     * 根据基础OID查询该人员执业注册业务信息列表
     * @param baseCertificateOid
     * @return
     * @throws ServiceException
     */
	public List<PtCertificateInfoDTO> getPtCertificateInfoListByBaseCertificateOid(Long baseCertificateOid) throws ServiceException;
}
