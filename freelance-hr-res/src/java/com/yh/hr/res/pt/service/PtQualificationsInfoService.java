package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtQualificationsInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 执业(职业)资格业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtQualificationsInfoService {
	
	/**
	 * 创建执业(职业)资格业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtQualificationsInfoDTO ptQualificationsInfoDto) throws ServiceException;

	/**
	 * 通过主键ID获取执业(职业)资格业务信息
	 * @param ptQualificationsInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtQualificationsInfoDTO get(Long ptQualificationsInfoId) throws ServiceException;
    
	/**
	 * 修改执业(职业)资格业务信息
	 * @param ptEducationTrainingInfoDto
	 * @throws ServiceException
	 */   
	public void update(PtQualificationsInfoDTO ptQualificationsInfoDto) throws ServiceException;

	/**
	 * 删除执业(职业)资格业务信息
	 * @param ptQualificationsInfoId
	 * @throws ServiceException
	 */       
	public void delete(Long ptQualificationsInfoId) throws ServiceException;
    
	/**
	 * 查询所有执业(职业)资格信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtQualificationsInfoDTO> list(TableTagBean ttb) throws ServiceException; 

	/**
	 * 根据业务人员OID查询执业(职业)资格经历
	 * @param bizPersonOid
	 * @return List<PtQualificationsInfoDTO>
	 * @throws ServiceException
	 */
	public List<PtQualificationsInfoDTO> listPtQualificationsInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的执业(职业)资格业务信息
	 * @param baseQualificationsOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtQualificationsInfoDTO> listPtQualificationsInfoByBaseQualificationsOid(Long baseQualificationsOid) throws ServiceException;

	/**
	 * 通过业务人员OID删除该人员的所有执业(职业)资格信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
      
}
