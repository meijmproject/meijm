package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtExserviceInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description 退役军人信息service
 * @author wuxq
 * @created 2016年11月21日
 * @version 1.0
 */
public interface PtExserviceInfoService {

	/**
	 * 得到退役军人信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public PtExserviceInfoDTO getPtExserviceInfo(Long bizPersonOid)throws ServiceException;
	
	/**
	 * 新增退役军人信息
	 * @param PtExserviceInfoDTO
	 */
	public void createPtExserviceInfo(PtExserviceInfoDTO PtExserviceInfoDTO)throws ServiceException;
	
	/**
	 * 修改退役军人信息
	 * @param PtExserviceInfoDTO
	 */
	public void modifyPtExserviceInfo(PtExserviceInfoDTO PtExserviceInfoDTO)throws ServiceException;
	
	/**
	 * 删除退役军人信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deletePtExserviceInfo(Long bizPersonOid)throws ServiceException;
	
	/**
	 * 退役军人信息列表
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public List<PtExserviceInfoDTO> listPtExserviceInfo(Long bizPersonOid)throws ServiceException;
}
