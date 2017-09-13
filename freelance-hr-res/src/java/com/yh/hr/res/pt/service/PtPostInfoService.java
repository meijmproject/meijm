package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPostInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 岗位信息service（业务）
 * @author lihj
 *
 */
public interface PtPostInfoService
{
	/**
	 * 新增岗位信息（作废）
	 * @param PtPostInfoDto
	 * @throws ServiceException
	 */
	@Deprecated
	public void createPtPostInfo(PtPostInfoDTO ptPostInfoDto) throws ServiceException;
	/**
	 * 新增岗位信息
	 * @param PtPostInfoDto
	 * @throws ServiceException
	 */
	public void createPtPost(PtPostInfoDTO ptPostInfoDto) throws ServiceException;

	/**
	 * 得到岗位信息
	 * @param ptPostOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPostInfoDTO findPtPostInfoById(Long ptPostOid) throws ServiceException;
	
	/**
	 * 更新岗位信息
	 * @param ptPostInfoDto
	 * @throws ServiceException
	 */
	public void updatePtPostInfo(PtPostInfoDTO ptPostInfoDto) throws ServiceException;
	/**
	 * 修改岗位信息(多条任职)
	 *
	 * @param ptPostInfoDto
	 * @throws ServiceException
	 */
	public void updatePtPost(PtPostInfoDTO ptPostInfoDto) throws ServiceException;
	/**
	 * 删除岗位信息
	 * @param ptPostOid
	 * @throws ServiceException
	 */
	public void deletePtPostInfo(Long ptPostOid) throws ServiceException;
	
	/**
	 * 得到列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPostInfoDTO> findCurrentDuty(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtPostInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过人员ID，任职ID获取岗位信息
	 * @param personOid
	 * @param positioningOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPostInfoDTO getPtPositInfoByPtPositioningOid(Long bizPersonOid,Long ptPositioningOid) throws ServiceException;
	/**
	 * 得到列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPostInfoDTO> listPtPostInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
}
