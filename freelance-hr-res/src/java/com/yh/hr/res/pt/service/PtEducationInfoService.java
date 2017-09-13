package com.yh.hr.res.pt.service;

import java.util.List;
import com.yh.hr.res.pt.dto.PtEducationInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 学历学位信息service（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午05:14:07
 */
public interface PtEducationInfoService
{
	/**
	 * 新增学历学位信息
	 * @param PtEducationInfoDto
	 * @throws ServiceException
	 */
	public void createPtEducationInfo(PtEducationInfoDTO ptEducationInfoDto) throws ServiceException;
	
	/**
	 * 得到学历学位信息
	 * @param ptEducationOid
	 * @return
	 * @throws ServiceException
	 */
	public PtEducationInfoDTO findPtEducationInfoById(Long ptEducationOid) throws ServiceException;
	
	/**
	 * 更新学历学位信息
	 * @param ptEducationInfoDto
	 * @throws ServiceException
	 */
	public void updatePtEducationInfo(PtEducationInfoDTO ptEducationInfoDto) throws ServiceException;
	
	/**
	 * 删除学历学位信息
	 * @param ptEducationOid
	 * @throws ServiceException
	 */
	public void deletePtEducationInfo(Long ptEducationOid) throws ServiceException;
	
	/**
	 * 得到列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationInfoDTO> listPtEducationInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtEducationInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	/**根据学历变动工资核定业务
	 * 新增学历学位信息
	 * @param PtEducationInfoDto
	 * @throws ServiceException
	 */
	public void createWagePtEducationInfo(PtEducationInfoDTO ptEducationInfoDto) throws ServiceException;
}
