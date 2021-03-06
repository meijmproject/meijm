﻿/**
 * @desctiption   This file is generated by  code generation tool version 0.2 ^_^
 * @Created   2017-02-10
 */
package com.yh.hr.res.pb.service;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.bo.PbEngageContractInfo;
import com.yh.hr.res.pb.dto.PbEngageContractInfoDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringMap;

import java.util.HashMap;
import java.util.List;


public interface PbEngageContractInfoService {
	/**
	 * 新增PbEngageContractInfo信息
	 * @param 
	 * @throws ServiceException
	 */
	public void create(PbEngageContractInfoDTO pbEngageContractInfoDto) throws ServiceException;

	/**
	 * 获取PbEngageContractInfo信息
	 * @param 
	 * @throws ServiceException
	 */
	public PbEngageContractInfoDTO get(java.lang.Long pbEngageContractInfoId) throws ServiceException;
    
	/**
	 * 修改PbEngageContractInfo信息
	 * @param 
	 * @throws ServiceException
	 */    
	public void update(PbEngageContractInfoDTO pbEngageContractInfoDto) throws ServiceException;

	/**
	 * 删除PbEngageContractInfo信息
	 * @param 
	 * @throws ServiceException
	 */    
	public void delete(java.lang.Long pbEngageContractInfoId) throws ServiceException;
    
	/**
	 * 查询所有PbEngageContractInfo信息
	 * @param 
	 * @throws ServiceException
	 */
	public List<PbEngageContractInfoDTO> find(TableTagBean ttb) throws ServiceException; 

	/**
	 * HQL拼装
	 * @param 
	 * @throws ServiceException
	 */
    void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException;

	public boolean checkContractNo(PbEngageContractInfoDTO pbEngageContractInfoDto) throws ServiceException;
	public boolean checkStatus(PbEngageContractInfoDTO pbEngageContractInfoDto) throws ServiceException;

	/**
	 * 用人员id查找该人员的合同信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public PbEngageContractInfoDTO getPbEngageContractInfoByPersonOid(Long personOid) throws ServiceException;
	/**
	 * 用人员id查找该人员的合同信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbEngageContractInfoDTO> listPbEngageContractInfoByPersonOid(Long personOid) throws ServiceException;

	/**
	 * 更新合同信息中的状态
	 * @param personOid 当前人员
	 * @param status 新的合同状态
	 * @throws ServiceException
	 */
	public PbEngageContractInfo updateContractStatus(Long personOid, String status) throws ServiceException;

	/**
	 * 通过人员id删除合同信息
	 * @param personOid
	 * @throws ServiceException 
	 */
	public void deleteByPersonOid(Long personOid) throws ServiceException;
}
