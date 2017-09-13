package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtProfTechQualifInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 专业技术资格业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtProfTechQualifInfoService {
	
	/**
	 * 创建专业技术资格业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtProfTechQualifInfoDTO ptProfTechQualifInfoDto) throws ServiceException;

	/**
	 * 通过主键ID获取专业技术资格业务信息
	 * @param ptProfTechQualifInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtProfTechQualifInfoDTO get(Long ptProfTechQualifInfoId) throws ServiceException;
    
	/**
	 * 修改专业技术资格业务信息
	 * @param ptEducationTrainingInfoDto
	 * @throws ServiceException
	 */   
	public void update(PtProfTechQualifInfoDTO ptProfTechQualifInfoDto) throws ServiceException;

	/**
	 * 删除专业技术资格业务信息
	 * @param ptProfTechQualifInfoId
	 * @throws ServiceException
	 */       
	public void delete(Long ptProfTechQualifInfoId) throws ServiceException;
    
	/**
	 * 查询所有专业技术资格信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtProfTechQualifInfoDTO> list(TableTagBean ttb) throws ServiceException; 

	/**
	 * 根据业务人员OID查询专业技术资格经历
	 * @param bizPersonOid
	 * @return List<PtProfTechQualifInfoDTO>
	 * @throws ServiceException
	 */
	public List<PtProfTechQualifInfoDTO> listPtProfTechQualifInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的专业技术资格业务信息
	 * @param baseProfTechQualifOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtProfTechQualifInfoDTO> listPtProfTechQualifInfoByBaseProfTechQualifOid(Long baseProfTechQualifOid) throws ServiceException;

	/**
	 * 通过业务人员OID删除该人员的所有专业技术资格信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
      
}
