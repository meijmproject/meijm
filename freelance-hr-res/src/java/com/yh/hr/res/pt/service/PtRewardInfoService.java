package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.bo.PtRewardInfo;
import com.yh.hr.res.pt.dto.PtRewardInfoDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * @description The interface for PtRewardInfo service
 * @author xiongyx
 * @created 2016-10-10
 * @version 1.0
 */
public interface PtRewardInfoService {
	
	/**
	 * 新增奖励信息
	 * @param PtRewardInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public void create(PtRewardInfoDTO PtRewardInfoDTO) throws ServiceException;
	
	/**
	 * 查询单条奖励信息
	 * @param ptRewardOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRewardInfo find(Long ptRewardOid) throws ServiceException;
	
	/**
	 * 更新奖励信息
	 * @param PtRewardInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public void update(PtRewardInfoDTO PtRewardInfoDTO) throws ServiceException;
	
	/**
	 * 删除奖励信息
	 * @param ptRewardOid
	 * @return
	 * @throws ServiceException
	 */
	public void delete(Long rewardOid) throws ServiceException;
	
	/**
	 * 列表
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRewardInfoDTO> findPtRewardInfoByCondition(Long personOid)
			throws ServiceException;
	
	/**
	  * 通过业务人员OID获取奖励信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public List<PtRewardInfoDTO> listPtRewardInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的特殊授权情况业务信息
	 * @param rewardOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRewardInfoDTO> listPtRewardInfoByRewardOid(Long rewardOid) throws ServiceException;
	
	/**
	 * 通过业务人员OID删除该人员的所有奖励信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;

	/**
	 * 通过oid获取奖励信息
	 * @param ptRewardOid
	 * @return
	 */
	public PtRewardInfoDTO get(Long ptRewardOid) throws ServiceException;
}
