package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtPoliticInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 政治面貌与党派业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtPoliticInfoService {
	
	/**
	 * 创建政治面貌与党派业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtPoliticInfoDTO ptPoliticInfoDto) throws ServiceException;

	/**
	 * 通过主键ID获取政治面貌与党派业务信息
	 * @param ptPoliticInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtPoliticInfoDTO get(Long ptPoliticInfoId) throws ServiceException;
    
	/**
	 * 修改政治面貌与党派业务信息
	 * @param ptPoliticInfoDto
	 * @throws ServiceException
	 */   
	public void update(PtPoliticInfoDTO ptPoliticInfoDto) throws ServiceException;

	/**
	 * 删除政治面貌与党派业务信息
	 * @param ptPoliticInfoId
	 * @throws ServiceException
	 */       
	public void delete(Long ptPoliticInfoId) throws ServiceException;
    
	/**
	 * 查询所有政治面貌与党派信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtPoliticInfoDTO> list(TableTagBean ttb) throws ServiceException; 

	/**
	 * 根据业务人员OID查询政治面貌与党派经历
	 * @param bizPersonOid
	 * @return List<PtPoliticInfoDTO>
	 * @throws ServiceException
	 */
	public List<PtPoliticInfoDTO> listPtPoliticInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过基础OID查找该人员的政治面貌与党派业务信息
	 * @param basePoliticOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPoliticInfoDTO> listPtPoliticInfoByBasePoliticOid(Long basePoliticOid) throws ServiceException;

	/**
	 * 通过业务人员OID删除该人员的所有政治面貌与党派信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
      
}
