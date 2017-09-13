package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtEducationTrainingInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 教育培训业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtEducationTrainingInfoService {
	
	/**
	 * 创建教育培训业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtEducationTrainingInfoDTO ptEducationTrainingInfoDto) throws ServiceException;

	/**
	 * 通过主键ID获取教育培训业务信息
	 * @param ptEducationTrainingInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtEducationTrainingInfoDTO get(Long ptEducationTrainingInfoId) throws ServiceException;
    
	/**
	 * 修改教育培训业务信息
	 * @param ptEducationTrainingInfoDto
	 * @throws ServiceException
	 */    
	public void update(PtEducationTrainingInfoDTO ptEducationTrainingInfoDto) throws ServiceException;

	/**
	 * 删除教育培训业务信息
	 * @param ptEducationTrainingInfoId
	 * @throws ServiceException
	 */   
	public void delete(Long ptEducationTrainingInfoId) throws ServiceException;
    
	/**
	 * 查询所有教育培训业务信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtEducationTrainingInfoDTO> list(TableTagBean ttb) throws ServiceException; 
    
	/**
	 * 根据业务人员OID查询该人员所有的教育培训业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationTrainingInfoDTO> listPtEducationTrainingInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的教育培训业务信息
	 * @param baseEducationTrainingOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationTrainingInfoDTO> listPtEducationTrainingInfoByBaseEducationTrainingOid(Long baseEducationTrainingOid) throws ServiceException;

	/**
	 * 删除该人员的所有教育培训记录
	 * @param bizPersonOid
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
      
}
