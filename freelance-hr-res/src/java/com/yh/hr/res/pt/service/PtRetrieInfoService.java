package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtRetrieInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtRetrieInfoService {

	/**
	 * 新增离退休情况详细信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void createPtRetrieInfo(PtRetrieInfoDTO serdto)throws ServiceException;

	/**
	 * 根据ID查询离退休情况详细信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */	
	public PtRetrieInfoDTO getPtRetrieInfoByOid(Long bizPersonOid) throws ServiceException;

	/**
	 * 修改离退休情况详细信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void updatePtRetrieInfo(PtRetrieInfoDTO serdto) throws ServiceException;

	/**
	 * 根据bizPersonOid删除离退休情况详细信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public void deletePtRetrieInfoByOid(Long bizPersonOid)throws ServiceException ;

	/**
	 * 离退休情况详细信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRetrieInfoDTO> listPtRetrieInfo(Long bizPersonOid) throws ServiceException;


}
