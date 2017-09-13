package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtPersonExchangeDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 人员交流业务逻辑层
 * @author xiongyx
 * @createDate 2016-11-05
 */
public interface PtPersonExchangeService {
	
	/*
	 * 新增
	 */
	public  void createPtPersonExchange(PtPersonExchangeDTO ptPersonExchangeDTO) throws ServiceException;
	/*
	 * 更新
	 */
	public  void updatePtPersonExchange(PtPersonExchangeDTO ptPersonExchangeDTO) throws ServiceException;
	
	/*
	 * 获取
	 */
	public  PtPersonExchangeDTO getPtPersonExchangeDTOById(Long bizPersonOid) throws ServiceException;
	/**
	 * 根据personOid查找人员业务基础信息
	 * 
	 * @param bizTaskOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPersonExchangeDTO getByPersonOid(Long personOid) throws ServiceException;
}