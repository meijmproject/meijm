package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtEducationLevelDegreeDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 学历学位业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtEducationLevelDegreeService {
	
	/**
	 * 创建学历学位业务信息
	 * @param ptEducationLevelDegreeDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtEducationLevelDegreeDTO ptEducationLevelDegreeDto) throws ServiceException;

	/**
	 * 通过主键ID获取学历学位业务信息
	 * @param ptEducationLevelDegreeId
	 * @return 
	 * @throws ServiceException
	 */
	public PtEducationLevelDegreeDTO get(Long ptEducationLevelDegreeId) throws ServiceException;
    
	/**
	 * 修改学历学位业务信息
	 * @param ptEducationLevelDegreeDto
	 * @throws ServiceException
	 */    
	public void update(PtEducationLevelDegreeDTO ptEducationLevelDegreeDto) throws ServiceException;

	/**
	 * 删除学历学位业务信息
	 * @param ptEducationLevelDegreeId
	 * @throws ServiceException
	 */   
	public void delete(Long ptEducationLevelDegreeId) throws ServiceException;
    
	/**
	 * 查询所有学历学位业务信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtEducationLevelDegreeDTO> list(TableTagBean ttb) throws ServiceException; 
    
	/**
	 * 根据业务人员OID查询该人员所有的学历学位业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationLevelDegreeDTO> listPtEducationLevelDegreeByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的学历学位业务信息
	 * @param baseEducationLevelOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationLevelDegreeDTO> listPtEducationLevelDegreeByBaseEducationLevelOid(Long baseEducationLevelOid) throws ServiceException;

	/**
	 * 删除该人员的所有学历学位记录
	 * @param bizPersonOid
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;

	/**
	 * 根据提交查询学历信息
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationLevelDegreeDTO> find(TableTagBean ttb) throws ServiceException;
      
}
