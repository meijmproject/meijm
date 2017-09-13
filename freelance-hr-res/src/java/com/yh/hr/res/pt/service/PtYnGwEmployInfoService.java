package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtYnGwEmployInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 院内岗位聘任业务信息service接口
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public interface PtYnGwEmployInfoService {

	/**
	 * 创建院内岗位聘任业务信息
	 * @param ptYnGwEmployInfoDto
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtYnGwEmployInfoDTO ptYnGwEmployInfoDto) throws ServiceException;

	/**
	 * 通过主键ID获取院内岗位聘任业务信息
	 * @param ptYnGwEmployInfoId
	 * @throws ServiceException
	 */
	public PtYnGwEmployInfoDTO get(Long ptYnGwEmployInfoId) throws ServiceException;
    
	/**
	 * 修改院内岗位聘任业务信息
	 * @param ptYnGwEmployInfoDto
	 * @throws ServiceException
	 */   
	public void update(PtYnGwEmployInfoDTO ptYnGwEmployInfoDto) throws ServiceException;

	/**
	 * 通过主键ID删除院内岗位聘任业务信息
	 * @param ptYnGwEmployInfoDto
	 * @throws ServiceException
	 */  
	public void delete(Long ptYnGwEmployInfoId) throws ServiceException;
	
	/**
	 * 通过业务人员OID删除院内岗位聘任业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */  
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException;
    
	/**
	 * 不在聘信息记录的唯一校验
	 * @param ptYnGwEmployInfoDTO
	 * @return
	 * @throws ServiceException
	 */
    public boolean checkStatus(PtYnGwEmployInfoDTO ptYnGwEmployInfoDTO) throws ServiceException; 
    
    /**
	 * 根据业务人员OID查询该人员所有的院内岗位聘任业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtYnGwEmployInfoDTO> listPtYnGwEmployInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
    
    /**
	 * 根据基础OID查询该人员所有的院内岗位聘任业务信息
	 * @param baseYnGwEmployOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtYnGwEmployInfoDTO> listPtYnGwEmployInfoByBaseYnGwEmployOid(Long baseYnGwEmployOid) throws ServiceException;
}
