package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtDelayRetireInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtDelayRetireInfoService {

	/**
	 * 新增延迟退休信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void addPtDelayRetireInfo(PtDelayRetireInfoDTO ptDelayRetireInfoDTO)throws ServiceException;

	/**
	 * 根据ID查询延迟退休信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */	
	public PtDelayRetireInfoDTO getPtDelayRetireInfoBybizPersonOid(Long bizPersonOid) throws ServiceException;

	/**
	 * 修改延迟退休信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void updatePtDelayRetireInfo(PtDelayRetireInfoDTO ptDelayRetireInfoDTO) throws ServiceException;

	/**
	 * 根据bizPersonOid删除延迟退休信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public void deletePtDelayRetireInfoBybizPersonOid(Long bizPersonOid)throws ServiceException ;

	/**
	 * 延迟退休信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtDelayRetireInfoDTO> listPtDelayRetireInfo(Long bizPersonOid) throws ServiceException;


}
