package com.yh.hr.res.pt.service;

import java.util.List;
import com.yh.hr.res.pt.dto.PtMilitaryInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 军队任职信息Service（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午05:34:32
 */
public interface PtMilitaryInfoService {

	/**
	 * 得到军队任职信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtMilitaryInfoDTO> getPtMilitaryInfoDTOList(Long bizPersonOid)throws ServiceException;
	
	/**
	 * 得到军队任职信息
	 * @return
	 * @throws ServiceException
	 */
	public PtMilitaryInfoDTO getPtMilitaryInfoDTO(Long ptMilitaryOid)throws ServiceException;
	
	/**
	 * 得到军队任职信息的记录数
	 * @return
	 * @throws ServiceException
	 */
	public int countPtMilitaryInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	
	/**
	 * 新增军队任职信息
	 * @param ptMilitaryInfoDTO
	 * @throws ServiceException
	 */
	public void createPtMilitaryInfo(PtMilitaryInfoDTO ptMilitaryInfoDTO)throws ServiceException;
	
	/**
	 * 修改军队任职信息
	 * @param ptMilitaryInfoDTO
	 */
	public void modifyPtMilitaryInfo(PtMilitaryInfoDTO ptMilitaryInfoDTO)throws ServiceException;
	
	/**
	 * 根据id删除军队任职信息
	 * @param ptMilitaryOid
	 * @throws ServiceException
	 */
	public void deletePtMilitaryInfo(Long ptMilitaryOid)throws ServiceException;
}
