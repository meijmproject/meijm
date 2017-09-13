package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtResumeInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 工作简历信息service（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午05:50:27
 */
public interface PtResumeInfoService {
	
	/**
	 * 得到工作简历信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtResumeInfoDTO> listPtResumeInfo(Long bizPersonOid) throws ServiceException;
	
	
	public List<PtResumeInfoDTO> listNotEndPtResumeInfo(Long bizPersonOid) throws ServiceException;
	/**
	 * 得到工作简历信息记录数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public int  countPtResumeInfoByBizPersonOid (Long bizPersonOid) throws ServiceException;
	/**
	 * 新增工作简历信息
	 * @param ptResumeInfoDTO
	 * @throws ServiceException
	 */
	public void createPtResumeInfo(PtResumeInfoDTO ptResumeInfoDTO) throws ServiceException;
	
	/**
	 * 更新工作简历信息
	 * @param ptResumeInfoDTO
	 * @throws ServiceException
	 */
	public  void updatePtResumeInfo(PtResumeInfoDTO ptResumeInfoDTO) throws ServiceException;
	
	/**
	 * 删除工作简历信息
	 * @param ptResumeOid
	 * @throws ServiceException
	 */
	public  void deletePtResumeInfoById(Long ptResumeOid) throws ServiceException;
	
	/**
	 * 根据id获取工作简历信息
	 * @param ptResumeOid
	 * @return
	 * @throws ServiceException
	 */
	public PtResumeInfoDTO getPtResumeInfoById(Long ptResumeOid) throws ServiceException;
	
	/**
	 * 根据bizPersonOid删除人员工作简历信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的工作简历业务信息
	 * @param resumeOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtResumeInfoDTO> listPtResumeInfoByResumeOid(Long resumeOid) throws ServiceException;


	/**
	 * 根据条件查询工作经历信息 
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<PtResumeInfoDTO> find(TableTagBean ttb) throws ServiceException;
	
}