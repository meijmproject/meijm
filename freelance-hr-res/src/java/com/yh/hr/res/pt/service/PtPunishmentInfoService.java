package com.yh.hr.res.pt.service;

import java.util.List;


import com.yh.hr.res.pt.dto.PtPunishmentInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 惩处信息Service
 * @description The interface for PtPunishmentInfo service
 * @author xiongyx
 * @created 2016-10-10
 * @version 1.0
 */
public interface PtPunishmentInfoService {
	
	/**
	 * 新增惩处信息
	 * insert PtPunishmentInfoDTO obj
	 * @param PtPunishmentInfoDTO
	 * @throws ServiceException
	 */
	public void create(PtPunishmentInfoDTO PtPunishmentInfoDTO) throws ServiceException;
	
	/**
	 * 查询惩处信息
	 * get PtPunishmentInfo obj
	 * @param punishmentOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPunishmentInfoDTO find(Long rewardOid) throws ServiceException;
	
	/**
	 * 更新惩处信息
	 * update PtPunishmentInfoDTO obj
	 * @param PtPunishmentInfoDTO
	 * @throws ServiceException
	 */
	public void update(PtPunishmentInfoDTO PtPunishmentInfoDTO) throws ServiceException;
	
	/**
	 * 删除惩处信息
	 * delete PtPunishmentInfo obj
	 * @param punishmentOid
	 * @throws ServiceException
	 */
	public void delete(Long rewardOid) throws ServiceException;
	
	/**
	 * 列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPunishmentInfoDTO> findPtPunishmentInfoByBizPersonOid(Long bizPersonOid)
			throws ServiceException;
	
	
}
