package com.yh.hr.orghc.ut.service;

import java.util.List;

import com.yh.hr.orghc.ut.dto.BizUtHcDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 编制信息Service
 * @author xiongyx
 *
 * 
 */
public interface BizHcInfoService {

	/**
	 * 新增
	 */
	public void create(BizUtHcDTO dto)throws ServiceException;

	/**
	 * 更新
	 */
	public void update(BizUtHcDTO dto) throws ServiceException;

	/**
	 * 根据ID删除
	 */	
	public void delete(Long utHcOid) throws ServiceException;
	
	/*
	 * 查询
	 */
	public BizUtHcDTO get(Long utHcOid) throws ServiceException;
	
	/**
	 * 列表
	 */	
	public List<BizUtHcDTO> list(Long utUnitOid) throws ServiceException;

}
