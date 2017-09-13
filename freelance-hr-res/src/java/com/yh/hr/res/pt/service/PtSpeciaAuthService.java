package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtSpeciaAuthDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 特殊授权情况业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtSpeciaAuthService {
	
	/**
	 * 创建特殊授权情况业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtSpeciaAuthDTO ptSpeciaAuthDto) throws ServiceException;

	/**
	 * 通过主键ID获取特殊授权情况业务信息
	 * @param ptSpeciaAuthId
	 * @return 
	 * @throws ServiceException
	 */
	public PtSpeciaAuthDTO get(Long ptSpeciaAuthId) throws ServiceException;
    
	/**
	 * 修改特殊授权情况业务信息
	 * @param ptEducationTrainingInfoDto
	 * @throws ServiceException
	 */   
	public void update(PtSpeciaAuthDTO ptSpeciaAuthDto) throws ServiceException;

	/**
	 * 删除特殊授权情况业务信息
	 * @param ptSpeciaAuthId
	 * @throws ServiceException
	 */       
	public void delete(Long ptSpeciaAuthId) throws ServiceException;
    
	/**
	 * 查询所有特殊授权情况信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtSpeciaAuthDTO> list(TableTagBean ttb) throws ServiceException; 

	/**
	 * 根据业务人员OID查询特殊授权情况经历
	 * @param bizPersonOid
	 * @return List<PtSpeciaAuthDTO>
	 * @throws ServiceException
	 */
	public List<PtSpeciaAuthDTO> listPtSpeciaAuthByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的特殊授权情况业务信息
	 * @param baseSpeciaAuthOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtSpeciaAuthDTO> listPtSpeciaAuthByBaseSpeciaAuthOid(Long baseSpeciaAuthOid) throws ServiceException;

	/**
	 * 通过业务人员OID删除该人员的所有特殊授权情况信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
      
}
