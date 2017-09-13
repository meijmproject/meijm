package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.hr.res.pb.dto.PbPositioningOtherInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @description The interface for PbPositioningOtherInfo service
 * @author lihj
 * @created 2016-12-22
 * @version 1.0
 */
public interface PbPositioningOtherInfoService
{
	/**
	 * insert PbPositioningOtherInfo obj
	 * @param pbPositioningOtherInfoDto
	 * @throws ServiceException
	 */
	public void createPbPositioningOtherInfo(PbPositioningOtherInfoDTO pbPositioningOtherInfoDto) throws ServiceException;
	
	/**
	 * get PbPositioningOtherInfo obj
	 * @param positioningOtherOid
	 * @return
	 * @throws ServiceException
	 */
	public PbPositioningOtherInfoDTO findPbPositioningOtherInfoById(Long positioningOtherOid) throws ServiceException;
	
	/**
	 * update PbPositioningOtherInfo obj
	 * @param pbPositioningOtherInfoDto
	 * @throws ServiceException
	 */
	public void updatePbPositioningOtherInfo(PbPositioningOtherInfoDTO pbPositioningOtherInfoDto) throws ServiceException;
	
	/**
	 * delete PbPositioningOtherInfo obj
	 * @param positioningOtherOid
	 * @throws ServiceException
	 */
	public void deletePbPositioningOtherInfo(Long positioningOtherOid) throws ServiceException;
	/**
     * 删除任职信息(批量)
     * @param positioningOid
     * @return
     * @throws ServiceException
     */
    public void deletePbPositioningOtherInfoByIds(List<Long> positioningOtherOids) throws ServiceException;
	/**
	 * list PbPositioningOtherInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbPositioningOtherInfoDTO> listPbPositioningOtherInfoByPersonOid(Long personOid) throws ServiceException;
	
	/**
	 * count PbPositioningOtherInfo obj
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPbPositioningOtherInfoByPersonOid(Long personOid) throws ServiceException;
}
