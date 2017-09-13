package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPositioningOtherInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 人员其他职务信息业务逻辑层
 * @author lihj
 * @createDate 2016-10-26
 */
public interface PtPositioningOtherService {
	/**
	 * 新增其他职务信息
	 * @param PtPositioningOtherInfoDto
	 * @throws ServiceException
	 */
	public void createPtPositioningOtherInfo(PtPositioningOtherInfoDTO ptPositioningOtherInfoDto) throws ServiceException;
	
	/**
	 * 得到其他职务信息
	 * @param ptPositioningOtherOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPositioningOtherInfoDTO findPtPositioningOtherInfoById(Long ptPositioningOtherOid) throws ServiceException;
	
	/**
	 * 更新其他职务信息
	 * @param ptPositioningOtherInfoDto
	 * @throws ServiceException
	 */
	public void updatePtPositioningOtherInfo(PtPositioningOtherInfoDTO ptPositioningOtherInfoDto) throws ServiceException;
	
	/**
	 * 删除其他职务信息
	 * @param ptPositioningOtherOid
	 * @throws ServiceException
	 */
	public void deletePtPositioningOtherInfo(Long ptPositioningOtherOid) throws ServiceException;
	
	/**
	 * 得到列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPositioningOtherInfoDTO> listPtPositioningOtherInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtPositioningOtherInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
}